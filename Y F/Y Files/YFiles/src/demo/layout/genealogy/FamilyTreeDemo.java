/****************************************************************************
 **
 ** This file is part of yFiles-2.9. 
 **
 ** yWorks proprietary/confidential. Use is subject to license terms.
 **
 ** Redistribution of this file or of an unauthorized byte-code version
 ** of this file is strictly forbidden.
 **
 ** Copyright (c) 2000-2011 by yWorks GmbH, Vor dem Kreuzberg 28, 
 ** 72070 Tuebingen, Germany. All rights reserved.
 **
 ***************************************************************************/
package demo.layout.genealogy;

import demo.layout.genealogy.iohandler.GedcomHandler;
import demo.view.DemoBase;
import demo.view.DemoDefaults;
import y.base.Node;
import y.base.NodeCursor;
import y.base.NodeList;
import y.base.NodeMap;
import y.geom.YPoint;
import y.io.GraphMLIOHandler;
import y.io.graphml.KeyType;
import y.layout.BufferedLayouter;
import y.layout.LayoutTool;
import y.layout.genealogy.FamilyTreeLayouter;
import y.module.FamilyTreeLayoutModule;
import y.util.D;
import y.util.GraphHider;
import y.view.BendList;
import y.view.BevelNodePainter;
import y.view.BridgeCalculator;
import y.view.DefaultGraph2DRenderer;
import y.view.EdgeRealizer;
import y.view.GenericEdgePainter;
import y.view.GenericEdgeRealizer;
import y.view.GenericNodeRealizer;
import y.view.Graph2D;
import y.view.LineType;
import y.view.NavigationMode;
import y.view.NodeLabel;
import y.view.NodeRealizer;
import y.view.ShinyPlateNodePainter;
import y.view.SmartNodeLabelModel;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;


/**
 * This Demo shows how to use the FamilyTreeLayouter.
 * <p>
 * <b>Usage:</b>
 * <br>
 * Load a Gedcom file with "Load..." from the "File" menu. The gedcom file is converted on the fly into GraphML
 * and loaded into the graph by the {@link demo.layout.genealogy.iohandler.GedcomHandler}. After loading,
 * the graph will be laid out by the {@link y.layout.genealogy.FamilyTreeLayouter}.
 * <br>
 * NOTE: You will find some sample files in your &lt;src&gt;/demo/view/layout/genealogy/samples folder.
 * <br>
 * To re-layout the graph press the "layout" button. An options dialog will open where you can modify
 * some basic and advanced options. Clicking "OK" will induce a new layout with the new settings.
 * <br>
 * To load some sample graphs which are provided with this demo select one from the "Examples" ComboBox.
 * There are four different family trees with different complexity provided.
 * <br>
 * NOTE: For this feature, Gedcom files (ending: .ged) must be exported as resources.
 * <br>
 * Clicking on a node will collapse the graph to two generations around the clicked node. The "Show all" button
 * will expand the graph again
 * </p>
 * <p>
 * API usage:
 * <br>
 * The FamilyTreeLayouter needs to distinguish between family nodes, i.e. nodes representing a FAM entry
 * in the Gedcom file, and nodes representing individuals (i.e. persons, INDI entries). To do so,
 * a data provider with the key {@link y.layout.genealogy.FamilyTreeLayouter#DP_KEY_FAMILY_TYPE} has to be registered
 * to the graph. This data provider will return a String which denotes nodes representing individuals.
 * In this demo, this is achieved by comparing the node's background color with the color, family nodes are
 * painted with ({@link Color#BLACK}).
 * <br>
 * For writing, the GedcomHandler needs to distinguish between family nodes and individuals as well
 * as between male and female individuals. To do so, a data provider with the key
 * {@link y.layout.genealogy.FamilyTreeLayouter#DP_KEY_FAMILY_TYPE} has to be registered to the graph.
 * These data provider will return a String which can be used to distinguish between families, male and female
 * individual.
 * In this demo, this is achieved by comparing the node's background color with predefined values.
 * As this demo is a viewer without editing capabilities, the export function is not implemented in the GUI
 * (Although it is implemented in the code: see class ExportAction)
 * <br>
 * <br>
 * Note that the GraphML format offers the possibility to extract additional information from the original file.
 * In this demo, String attributes whether the node represents a family or a male or female individual are mapped
 * to the graph and can be used as data providers for the layouter and the GedcomHandler.
 * <br>
 * To use this feature, modify the code as indicated in the source code.
 * The methods to modify are {@link FamilyTreeDemo#loadGedcom(String)} and
 * {@link GedcomHandler#read(y.view.Graph2D, java.io.InputStream)}.
 * </p>
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/domain_specific_layouter.html#familytreee_layouter">Section Family Tree Layout</a> in the yFiles for Java Developer's Guide
 */
public class FamilyTreeDemo extends DemoBase
{
    private static final int PREFERRED_FONT_SIZE = 18;

    private FamilyTreeLayoutModule ftlm;

    private GraphHider graphHider;

    /**
     * Launches this demo.
     */
    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                new FamilyTreeDemo().start();
            }
        } );
    }

    /**
     * Add a NavigationMode
     */
    protected void registerViewModes()
    {
        view.addViewMode( new NavigationMode()
        {
            public void mouseClicked( double x, double y )
            {
                super.mouseClicked( x, y );
                if ( getHitInfo( x, y ).getHitNode() != null )
                {
                    setToMain( getLastHitInfo().getHitNode() );
                }
            }
        } );
    }

    /**
     * Hide nodes that are not related with the given node, i.e.,
     * show only the clicked node, its siblings, parents, parents of parents (recursively) as well as its families, children
     * and children of children (recursively).
     *
     * @param clickedNode The node to be the new main node of the graph
     */
    private void setToMain( final Node clickedNode )
    {
        final Graph2D graph = view.getGraph2D();
        final YPoint oldCenter = graph.getCenter( clickedNode );

        graphHider.unhideAll();  //undo the previous selection

        //mark nodes that should be shown
        final NodeMap showNodeMap = graph.createNodeMap();
        Node familyNode = ( clickedNode.inDegree() > 0 ) ? clickedNode.firstInEdge().source() : null;
        if ( familyNode != null )
        {
            for ( NodeCursor nc = familyNode.successors(); nc.ok(); nc.next() )
            {
                showNodeMap.setBool( nc.node(), true ); //mark clicked node and its siblings
            }
            NodeList queue = new NodeList( familyNode );
            while ( !queue.isEmpty() )
            { //mark all predecessors
                final Node n = queue.popNode();
                if ( !showNodeMap.getBool( n ) )
                { //prevents that a node is handled twice -> should not happen in most families ;-)
                    showNodeMap.setBool( n, true );
                    queue.addAll( n.predecessors() );
                }
            }
        }
        else
        {
            showNodeMap.setBool( clickedNode, true );
        }

        //also add the successors
        NodeList queue = new NodeList( clickedNode.successors() );
        while ( !queue.isEmpty() )
        {
            final Node n = queue.popNode();
            if ( !showNodeMap.getBool( n ) )
            {
                showNodeMap.setBool( n, true );
                queue.addAll( n.successors() );

                //also show n's direct predecessors, i.e., both parents of a family node
                for ( NodeCursor nc = n.predecessors(); nc.ok(); nc.next() )
                {
                    final Node pred = nc.node();
                    if ( !showNodeMap.getBool( pred ) )
                    {
                        showNodeMap.setBool( pred, true );
                    }
                }
            }
        }

        //hide non marked nodes
        for ( NodeCursor nc = graph.nodes(); nc.ok(); nc.next() )
        {
            final Node n = nc.node();
            if ( !showNodeMap.getBool( n ) )
            {
                graphHider.hide( n );
            }
        }
        graph.disposeNodeMap( showNodeMap );

        //apply the layout to the subgraph
        final FamilyTreeLayouter layouter = new FamilyTreeLayouter();
        getLayoutModule().configure( layouter );
        new BufferedLayouter( layouter ).doLayout( graph );

        //move clicked node to its old position
        final YPoint newCenter = graph.getCenter( clickedNode );
        LayoutTool.moveSubgraph( graph, graph.nodes(), oldCenter.getX() - newCenter.getX(),
                                 oldCenter.getY() - newCenter.getY() );
        view.updateView();
    }

    /**
     * Creates a toolbar for this demo.
     */
    protected JToolBar createToolBar()
    {
        final Action layoutAction = new AbstractAction(
                "Layout", SHARED_LAYOUT_ICON )
        {
            public void actionPerformed( ActionEvent e )
            {
                OptionSupport.showDialog( getLayoutModule(), view.getGraph2D(), true, view.getFrame() );
            }
        };

        final Action showAllAction = new AbstractAction( "Show all" )
        {
            public void actionPerformed( ActionEvent e )
            {
                if ( graphHider != null )
                {
                    graphHider.unhideAll();
                }
                getLayoutModule().start( view.getGraph2D() );
            }
        };

        JToolBar jToolBar = super.createToolBar();
        jToolBar.addSeparator();
        jToolBar.add( showAllAction );
        //jToolBar.add(new ExportAction());
        jToolBar.addSeparator();
        jToolBar.add( createActionControl( layoutAction ) );
        return jToolBar;
    }

    protected JMenuBar createMenuBar()
    {
        final JMenuBar menuBar = super.createMenuBar();
        createExamplesMenu( menuBar );
        return menuBar;
    }

    /**
     * Creates a menu to select the provided samples.
     */
    protected void createExamplesMenu( JMenuBar menuBar )
    {
        String fqResourceName =
                FamilyTreeDemo.class.getPackage().getName().replace( '.', '/' ) + "/samples/kennedy_clan.ged";

        URL resource = getClass().getResource( "samples/kennedy_clan.ged" );
        if ( resource == null )
        {
            D.showError( "Cannot load example files: missing resource " + fqResourceName + '\n' +
                                 "Please ensure that your IDE recognizes \"*.ged\" files as resource files. \n" +
                                 "Meanwhile you can load the sample files via the \"File/Load\" menu from the source folder of your distribution." );
            return;
        }

        final String name = resource.getFile();
        final String dirName = name.substring( 0, name.lastIndexOf( '/' ) );

        final String[] dir = new File( dirName ).list( new FilenameFilter()
        {
            public boolean accept( File dir, String name )
            {
                return name.toLowerCase().endsWith( ".ged" );
            }
        } );

        if ( dir == null )
        {
            D.showError( "Cannot load example files: " + dirName + " not found" );
            return;
        }

        if ( dir.length > 0 )
        {
            final JMenu menu = new JMenu( "Example Graphs" );
            menuBar.add( menu );

            for ( int i = 0; i < dir.length; i++ )
            {
                final String fileName = dir[ i ];
                menu.add( new AbstractAction( fileName )
                {
                    public void actionPerformed( ActionEvent e )
                    {
                        loadGedcom( dirName + System.getProperty( "file.separator" ) + fileName );
                    }
                } );
            }

            loadGedcom( dirName + System.getProperty( "file.separator" ) + dir[ 0 ] );
        }
    }

    /**
     * Loads a gedcom file with the provided filename into the editor.
     *
     * @param name the filename of the gedcom file to be read.
     */
    private void loadGedcom( String name )
    {
        final Graph2D graph = view.getGraph2D();
        if ( graphHider != null )
        {
            graphHider.unhideAll();
        }
        graph.clear();
        GedcomHandler gh = new GedcomHandler();
        GraphMLIOHandler delegate = new GraphMLIOHandler();
        NodeMap nodeTypeMap = graph.createNodeMap();
        graph.addDataProvider( FamilyTreeLayouter.DP_KEY_FAMILY_TYPE, nodeTypeMap );
        delegate.getGraphMLHandler().addInputDataAcceptor( "GedcomType", nodeTypeMap, KeyType.STRING );

        gh.setReaderDelegate( delegate );
        try
        {
            gh.read( graph, name );
        }
        catch ( IOException e1 )
        {
            D.show( e1 );
        }

        for ( NodeCursor nodeCursor = graph.nodes(); nodeCursor.ok(); nodeCursor.next() )
        {
            Node node = nodeCursor.node();
            final NodeRealizer realizer = graph.getRealizer( node );
            if ( realizer.labelCount() > 0 )
            {
                final NodeLabel label = realizer.getLabel();
                if ( label.getFontSize() < PREFERRED_FONT_SIZE )
                {
                    label.setFontSize( PREFERRED_FONT_SIZE );

                    if ( label.getWidth() + 2 * label.getDistance() > realizer.getWidth() )
                    {
                        realizer.setWidth( label.getWidth() + 2 * label.getDistance() + 8 );
                    }
                }
                SmartNodeLabelModel labelModel = new SmartNodeLabelModel();
                label.setLabelModel( labelModel );
                label.setModelParameter( labelModel.getDefaultParameter() );
            }
        }

        try
        {
            getLayoutModule().start( view.getGraph2D() );
        }
        catch ( Exception e1 )
        {
            D.show( e1 );
        }
    }


    /**
     * Overrides the default method which creates the loadGedcom entry in the file menu to import a gedcom file
     * rather than to loadGedcom a graph.
     *
     * @return A new instance of ImportAction
     */
    protected Action createLoadAction()
    {
        return new ImportAction();
    }


    /**
     * Action that loads a Gedcom file.
     */
    public class ImportAction extends AbstractAction
    {
        JFileChooser chooser;

        public ImportAction()
        {
            super( "Load..." );
            chooser = null;
        }

        public void actionPerformed( ActionEvent e )
        {
            if ( chooser == null )
            {
                chooser = new JFileChooser();
                chooser.setFileFilter( new FileFilter()
                {
                    public boolean accept( File f )
                    {
                        return f.isDirectory() || f.getName().toLowerCase().endsWith( ".ged" );
                    }

                    public String getDescription()
                    {
                        return "Gedcom files";
                    }
                } );
            }

            if ( chooser.showOpenDialog( contentPane ) == JFileChooser.APPROVE_OPTION )
            {
                loadGedcom( chooser.getSelectedFile().toString() );
            }
        }


    }

    private FamilyTreeLayoutModule getLayoutModule()
    {
        if ( ftlm == null )
        {
            ftlm = new FamilyTreeLayoutModule();
            ftlm.getOptionHandler().set( "MALE_COLOR", DemoDefaults.DEFAULT_CONTRAST_COLOR );
            ftlm.getOptionHandler().set( "FEMALE_COLOR", DemoDefaults.DEFAULT_NODE_COLOR );
        }
        return ftlm;
    }

    /**
     * Action to export the graph into a gedcom file
     * NOTE: This action is not added to the toolbar
     */
    protected class ExportAction extends AbstractAction
    {

        private JFileChooser chooser;

        public ExportAction()
        {
            super( "Export" );
        }

        /**
         * Invoked when an action occurs.
         */
        public void actionPerformed( ActionEvent e )
        {

            if ( chooser == null )
            {
                chooser = new JFileChooser();
                chooser.setFileFilter( new FileFilter()
                {
                    public boolean accept( File f )
                    {
                        return f.isDirectory()
                                || f.getName().toLowerCase().endsWith( ".ged" );
                    }

                    public String getDescription()
                    {
                        return "Gedcom files";
                    }
                } );
            }
            if ( chooser.showOpenDialog( contentPane ) == JFileChooser.APPROVE_OPTION )
            {
                String name = chooser.getSelectedFile().toString();
                if ( !name.toLowerCase().endsWith( ".ged" ) )
                {
                    name = name + ".ged";
                }

                GedcomHandler gh = new GedcomHandler();
                final Graph2D graph = view.getGraph2D();

                // Write the graph using the GedcomHandler
                try
                {
                    gh.write( graph, name );
                }
                catch ( IOException e1 )
                {
                    D.show( e1 );
                }
            }
        }

    }

    //////////////////////////////////////// Optical improvements :-) /////////////////////////////////////////////////

    /**
     * Initialize the node and edge style
     */
    protected void initialize()
    {

        // Use a BevelNodePainter for the Individuals
        GenericNodeRealizer.Factory factory = GenericNodeRealizer.getFactory();
        Map implementationsMap = factory.createDefaultConfigurationMap();
        ShinyPlateNodePainter spnp = new ShinyPlateNodePainter();
        spnp.setDrawShadow( true );
        implementationsMap.put( GenericNodeRealizer.Painter.class, spnp );
        factory.addConfiguration( "Individual", implementationsMap );

        // Use a BevelNodePainter with rounded corners for the families
        implementationsMap = factory.createDefaultConfigurationMap();
        BevelNodePainter painter = new BevelNodePainter();
        painter.setRadius( 10 );
        painter.setDrawShadow( true );
        implementationsMap.put( GenericNodeRealizer.Painter.class, painter );
        factory.addConfiguration( "Family", implementationsMap );

        // Use a custom edge realizer
        GenericEdgeRealizer.Factory edgeFactory = GenericEdgeRealizer.getFactory();
        implementationsMap = edgeFactory.createDefaultConfigurationMap();
        implementationsMap.put( GenericEdgeRealizer.Painter.class, new CustomEdgePainter() );
        edgeFactory.addConfiguration( "Edge", implementationsMap );
        GenericEdgeRealizer ger = new GenericEdgeRealizer();
        ger.setConfiguration( "Edge" );
        ger.setLineColor( new Color( 0x808080 ) );
        ger.setLineType( LineType.LINE_2 );
        view.getGraph2D().setDefaultEdgeRealizer( ger );

        // Crossing/Bridges: Vertical edges over horizontal edges display gaps in horizontal edges
        BridgeCalculator bc = new BridgeCalculator();
        bc.setCrossingStyle( BridgeCalculator.CROSSING_STYLE_GAP );
        bc.setCrossingMode( BridgeCalculator.CROSSING_MODE_ORDER_INDUCED );
        ( ( DefaultGraph2DRenderer ) view.getGraph2DRenderer() ).setBridgeCalculator( bc );

        graphHider = new GraphHider( view.getGraph2D() );


    }


    /**
     * A custom EdgePainter implementation that draws the edge path 3D-ish and adds
     * a drop shadow also. (see demo.view.realizer.GenericEdgePainterDemo)
     */
    static final class CustomEdgePainter extends GenericEdgePainter
    {

        protected GeneralPath adjustPath( EdgeRealizer context, BendList bends, GeneralPath path,
                                          BridgeCalculator bridgeCalculator,
                                          boolean selected )
        {
            if ( bridgeCalculator != null )
            {
                GeneralPath p = new GeneralPath();
                PathIterator pathIterator = bridgeCalculator.insertBridges( path.getPathIterator( null, 1.0d ) );
                p.append( pathIterator, true );
                return super.adjustPath( context, bends, p, bridgeCalculator, selected );
            }
            else
            {
                return super.adjustPath( context, bends, path, bridgeCalculator, selected );
            }
        }


        protected void paintPath( EdgeRealizer context, BendList bends, GeneralPath path, Graphics2D gfx,
                                  boolean selected )
        {
            Stroke s = gfx.getStroke();
            Color oldColor = gfx.getColor();
            if ( s instanceof BasicStroke )
            {
                Color c;
                if ( selected )
                {
                    initializeSelectionLine( context, gfx, selected );
                    c = gfx.getColor();
                }
                else
                {
                    initializeLine( context, gfx, selected );
                    c = gfx.getColor();
                    gfx.setColor( new Color( 128, 128, 128, 40 ) );
                    gfx.translate( 4, 4 );
                    gfx.draw( path );
                    gfx.translate( -4, -4 );
                }
                Color newC = selected ? Color.RED : c;
                gfx.setColor(
                        new Color( 128 + newC.getRed() / 2, 128 + newC.getGreen() / 2, 128 + newC.getBlue() / 2 ) );
                gfx.translate( -1, -1 );
                gfx.draw( path );
                gfx.setColor( new Color( newC.getRed() / 2, newC.getGreen() / 2, newC.getBlue() / 2 ) );
                gfx.translate( 2, 2 );
                gfx.draw( path );
                gfx.translate( -1, -1 );
                gfx.setColor( c );
                gfx.draw( path );
                gfx.setColor( oldColor );
            }
            else
            {
                gfx.draw( path );
            }
        }
    }
}
