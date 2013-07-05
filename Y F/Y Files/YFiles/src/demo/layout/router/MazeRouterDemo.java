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
package demo.layout.router;

import demo.view.DemoBase;
import demo.view.DemoDefaults;
import y.base.DataProvider;
import y.base.Edge;
import y.base.EdgeMap;
import y.base.Node;
import y.base.NodeList;
import y.io.IOHandler;
import y.layout.LayoutGraph;
import y.layout.Layouter;
import y.layout.PortConstraintConfigurator;
import y.layout.PortConstraintKeys;
import y.layout.router.ChannelEdgeRouter;
import y.layout.router.OrthogonalEdgeRouter;
import y.module.ChannelEdgeRouterModule;
import y.module.OrthogonalEdgeRouterModule;
import y.module.YModule;
import y.util.DataProviderAdapter;
import y.view.CreateEdgeMode;
import y.view.DefaultGraph2DRenderer;
import y.view.Drawable;
import y.view.EditMode;
import y.view.Graph2D;
import y.view.Graph2DView;
import y.view.HotSpotMode;
import y.view.MoveSelectionMode;
import y.view.Selections;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

/**
 * A demo that shows how Orthogonal Edge Router and Channel Edge Router can be used to find routes through a maze.
 * Not only will it find a way but also one with fewest possible changes in direction.
 * <br>
 * The following aspects of using the edge routers are demonstrated.
 * <ol>
 * <li>How to use OrthogonalEdgeRouterModule and ChannelEdgeRouterModules respectively as
 * a convenient means to launch and
 * configure the edge routers.</li>
 * <li>How to modify the yFiles EditMode in order to trigger the
 * orthogonal edge router whenever
 * <ul>
 * <li>new edges get created</li>
 * <li>nodes get resized</li>
 * <li>selected nodes will be moved</li>
 * </ul></li>
 * </ol>
 * Additionally this demo shows how non-editable background-layer graphs can be displayed inside
 * the graph view.
 * <br/>
 * Usage: Create nodes and edges. The edges will be routed immediately. To reroute all edges use
 * the toolbar button "Route Edges".
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/orthogonal_edge_router.html">Section Orthogonal Edge Routing</a> in the yFiles for Java Developer's Guide
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/channel_edge_router.html">Section Channel Edge Routing</a> in the yFiles for Java Developer's Guide
 */
public class MazeRouterDemo extends DemoBase
{
    private RouterStrategy strategy;

    private Graph2D mazeG;

    private Drawable mazeD;

    private NodeList mazeNodes;

    private OrthogonalEdgeRouterStrategy orthogonalEdgeRouterStrategy;

    private ChannelEdgeRouterStrategy channelEdgeRouterStrategy;

    public MazeRouterDemo()
    {
        initializeMaze();

        initializeGraph();
    }

    protected void initialize()
    {
        channelEdgeRouterStrategy = new ChannelEdgeRouterStrategy();
        orthogonalEdgeRouterStrategy = new OrthogonalEdgeRouterStrategy();
        view.setContentPolicy( Graph2DView.CONTENT_POLICY_BACKGROUND_DRAWABLES );
    }

    protected void configureDefaultRealizers()
    {
        super.configureDefaultRealizers();
        view.getGraph2D().getDefaultNodeRealizer().setSize( 30, 30 );
    }

    /**
     * Returns ViewActionDemo toolbar plus actions to trigger some layout algorithms
     */
    protected JToolBar createToolBar()
    {
        final JComboBox comboBox = new JComboBox( new Object[]{ "Orthogonal Edge Router", "Channel Edge Router" } );
        comboBox.setMaximumSize( comboBox.getPreferredSize() );
        comboBox.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                strategy = comboBox.getSelectedIndex() == 0 ?
                           ( RouterStrategy ) orthogonalEdgeRouterStrategy :
                           channelEdgeRouterStrategy;
                doLayout();
            }
        } );
        strategy = orthogonalEdgeRouterStrategy;

        JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( createActionControl( new LayoutAction() ) );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( comboBox );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( createActionControl( new OptionAction() ) );

        return toolBar;
    }

    /**
     * Provides configuration options for the edge router.
     */
    class OptionAction extends AbstractAction
    {
        OptionAction()
        {
            super( "Settings...", getIconResource( "resource/properties.png" ) );
        }

        public void actionPerformed( ActionEvent e )
        {
            final ActionListener listener = new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    doLayout();
                }
            };
            OptionSupport.showDialog( strategy.getModule().getOptionHandler(), listener, false, view.getFrame() );
        }
    }

    /**
     * Launches the Orthogonal Edge Router.
     */
    class LayoutAction extends AbstractAction
    {
        LayoutAction()
        {
            super( "Route Edges", SHARED_LAYOUT_ICON );
        }

        public void actionPerformed( ActionEvent e )
        {
            doLayout();
        }
    }

    /**
     * Modified action to fit the content nicely inside the view.
     */
    class FitContent extends AbstractAction
    {
        FitContent()
        {
            super( "Fit Content" );
        }

        public void actionPerformed( ActionEvent e )
        {
            Graph2D graph = view.getGraph2D();

            Rectangle r = graph.getBoundingBox();
            r.add( mazeD.getBounds() );
            view.fitRectangle( r );
            graph.updateViews();
        }
    }

    void doLayout()
    {
        Graph2D graph = view.getGraph2D();
        addMazeGraph();
        // Start the module.
        strategy.getModule().start( graph );
        subtractMazeGraph();
    }

    /**
     * Adds a specially configured EditMode that will automatically route all
     * newly created edges orthogonally. The orthogonal edge router will also
     * be activated on some edges, when nodes get resized or a node selection gets
     * moved.
     */
    protected void registerViewModes()
    {
        EditMode mode = new EditMode();
        view.addViewMode( mode );

        mode.setMoveSelectionMode( new MyMoveSelectionMode() );
        mode.setCreateEdgeMode( new MyCreateEdgeMode() );
        mode.setHotSpotMode( new MyHotSpotMode() );
    }

    /**
     * A special mode for creating edges.
     */
    class MyCreateEdgeMode extends CreateEdgeMode
    {
        private Node source;

        protected boolean acceptSourceNode( Node s, double x, double y )
        {
            source = s;
            return true;
        }

        protected boolean acceptTargetNode( Node t, double x, double y )
        {
            return ( source != t );
        }

        protected void edgeCreated( final Edge e )
        {
            routeNewEdge( e );
        }
    }

    void routeNewEdge( Edge e )
    {
        final Graph2D graph = view.getGraph2D();
        addMazeGraph();
        strategy.routeNewEdge( e );
        subtractMazeGraph();
        graph.updateViews();
    }

    /**
     * A special mode for resizing nodes.
     */
    class MyHotSpotMode extends HotSpotMode
    {
        public void mouseReleasedLeft( double x, double y )
        {
            super.mouseReleasedLeft( x, y );

            final Graph2D graph = view.getGraph2D();

            DataProvider selectedNodes = Selections.createSelectionDataProvider( graph );
            addMazeGraph();
            strategy.rerouteAdjacentEdges( selectedNodes, graph );
            subtractMazeGraph();
            graph.updateViews();
        }
    }

    /**
     * A special mode for moving a selection of the graph.
     */
    class MyMoveSelectionMode extends MoveSelectionMode
    {
        private static final boolean ROUTE_EDGES_ON_MOVE = false;

        protected void selectionOnMove( double dx, double dy, double x, double y )
        {
            if ( ROUTE_EDGES_ON_MOVE )
            {
                routeEdgesToSelection();
            }
        }

        protected void selectionMovedAction( double dx, double dy, double x, double y )
        {
            routeEdgesToSelection();
        }

        void routeEdgesToSelection()
        {
            final Graph2D graph = view.getGraph2D();

            if ( graph.selectedNodes().ok() )
            {
                addMazeGraph();
                strategy.routeEdgesToSelection( graph );
                subtractMazeGraph();
                graph.updateViews();
            }
        }
    }

    /**
     * Adds the maze to the user-given graph, so that the edge router can lay
     * Ariadne's thread...
     */
    private void addMazeGraph()
    {
        mazeNodes = new NodeList( mazeG.nodes() );
        mazeG.moveSubGraph( mazeNodes, view.getGraph2D() );
    }

    /**
     * The maze gets removed from the user-given graph again.
     */
    private void subtractMazeGraph()
    {
        view.getGraph2D().moveSubGraph( mazeNodes, mazeG );
    }

    /**
     * Initializes the maze the first time.
     */
    private void initializeMaze()
    {
        mazeG = new Graph2D();
        try
        {
            IOHandler ioHandler = createGraphMLIOHandler();
            ioHandler.read( mazeG, getClass().getResource( "resource/maze.graphml" ) );
            DemoDefaults.applyFillColor( mazeG, DemoDefaults.DEFAULT_CONTRAST_COLOR );
            DemoDefaults.applyLineColor( mazeG, DemoDefaults.DEFAULT_CONTRAST_COLOR );

        }
        catch ( IOException e )
        {
            System.out.println( "Could not initialize maze!" );
            e.printStackTrace();
            System.exit( -1 );
        }
        // Create a drawable and add it to the graph as a visual representation
        // of the maze. This way it is not possible to move the maze's walls.
        mazeD = new MazeDrawable( mazeG );
        view.addBackgroundDrawable( mazeD );
        view.fitRectangle( mazeD.getBounds() );
    }

    /**
     * Creates an initial graph and initially routes the contained edge through the maze
     */
    private void initializeGraph()
    {
        final Graph2D graph = view.getGraph2D();
        Node start = graph.createNode();
        graph.setLocation( start, 20, 600 );
        Node end = graph.createNode();
        graph.setLocation( end, 600, 20 );
        Edge edge = graph.createEdge( start, end );
        routeNewEdge( edge );
    }

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
                ( new MazeRouterDemo() ).start();
            }
        } );
    }

    /**
     * To transform the whole maze graph into a maze drawable.
     */
    static class MazeDrawable implements Drawable
    {
        private Graph2D mazeG;

        private DefaultGraph2DRenderer render;

        public MazeDrawable( Graph2D g )
        {
            mazeG = g;
            render = new DefaultGraph2DRenderer();
        }

        public Rectangle getBounds()
        {
            return mazeG.getBoundingBox();
        }

        public void paint( Graphics2D gfx )
        {
            render.paint( gfx, mazeG );
        }
    }

    abstract static class RouterStrategy
    {
        abstract YModule getModule();

        abstract void routeNewEdge( Edge e );

        abstract void rerouteAdjacentEdges( DataProvider selectedNodes, LayoutGraph graph );

        abstract void routeEdgesToSelection( Graph2D graph );

        abstract void route( Layouter router, LayoutGraph graph );

        protected void routeNewEdge( Layouter router, final Edge e, Graph2D graph )
        {
            EdgeMap spc = ( EdgeMap ) graph.getDataProvider( PortConstraintKeys.SOURCE_PORT_CONSTRAINT_KEY );
            EdgeMap tpc = ( EdgeMap ) graph.getDataProvider( PortConstraintKeys.TARGET_PORT_CONSTRAINT_KEY );

            PortConstraintConfigurator pcc = new PortConstraintConfigurator();
            if ( spc != null && tpc != null )
            {
                spc.set( e, pcc.createPortConstraintFromSketch( graph, e, true, false ) );
                tpc.set( e, pcc.createPortConstraintFromSketch( graph, e, false, false ) );
                route( router, graph );
                spc.set( e, null );
                tpc.set( e, null );
            }
            else
            {
                route( router, graph );
            }
        }

        protected void routeEdgesToSelection( final Graph2D graph, Layouter router, Object affectedEdgesKey )
        {
            graph.addDataProvider( affectedEdgesKey, new DataProviderAdapter()
            {
                public boolean getBool( Object dataHolder )
                {
                    return graph.isSelected( ( ( Edge ) dataHolder ).source() ) ||
                            graph.isSelected( ( ( Edge ) dataHolder ).target() );
                }
            } );
            route( router, graph );
            graph.removeDataProvider( affectedEdgesKey );
        }

        protected void routeNewEdge( final Edge e, Graph2D graph, Layouter router, Object selectedEdgesKey )
        {
            DataProvider activeEdges = new DataProviderAdapter()
            {
                public boolean getBool( Object o )
                {
                    return e == o;
                }
            };
            graph.addDataProvider( selectedEdgesKey, activeEdges );
            routeNewEdge( router, e, graph );
            graph.removeDataProvider( selectedEdgesKey );
        }
    }

    static class OrthogonalEdgeRouterStrategy extends RouterStrategy
    {
        private OrthogonalEdgeRouterModule module = new OrthogonalEdgeRouterModule();

        public YModule getModule()
        {
            return module;
        }

        public void routeNewEdge( final Edge e )
        {
            Graph2D graph = ( Graph2D ) e.getGraph();
            OrthogonalEdgeRouter router = new OrthogonalEdgeRouter();
            module.configure( router );
            router.setSphereOfAction( OrthogonalEdgeRouter.ROUTE_SELECTED_EDGES );
            routeNewEdge( e, graph, router, Layouter.SELECTED_EDGES );
        }

        public void rerouteAdjacentEdges( DataProvider selectedNodes, LayoutGraph graph )
        {
            OrthogonalEdgeRouter router = new OrthogonalEdgeRouter();
            module.configure( router );
            router.setSphereOfAction( OrthogonalEdgeRouter.ROUTE_EDGES_AT_SELECTED_NODES );
            graph.addDataProvider( Layouter.SELECTED_NODES, selectedNodes );
            this.route( router, graph );
            graph.removeDataProvider( Layouter.SELECTED_NODES );
        }

        public void routeEdgesToSelection( final Graph2D graph )
        {
            OrthogonalEdgeRouter router = new OrthogonalEdgeRouter();
            module.configure( router );
            router.setSphereOfAction( OrthogonalEdgeRouter.ROUTE_SELECTED_EDGES );
            routeEdgesToSelection( graph, router, Layouter.SELECTED_EDGES );
        }

        void route( Layouter router, LayoutGraph graph )
        {
            router.doLayout( graph );
        }
    }

    static class ChannelEdgeRouterStrategy extends RouterStrategy
    {
        private ChannelEdgeRouterModule module;

        public ChannelEdgeRouterStrategy()
        {
            module = new ChannelEdgeRouterModule();
            module.getOptionHandler().set( "PATHFINDER", "ORTHOGONAL_SHORTESTPATH_PATH_FINDER" );
        }

        public YModule getModule()
        {
            return module;
        }

        public void routeNewEdge( final Edge e )
        {
            final Graph2D graph = ( Graph2D ) e.getGraph();
            ChannelEdgeRouter router = new ChannelEdgeRouter();
            module.configure( router );
            routeNewEdge( e, graph, router, ChannelEdgeRouter.AFFECTED_EDGES );
        }

        public void rerouteAdjacentEdges( final DataProvider selectedNodes, LayoutGraph graph )
        {
            ChannelEdgeRouter router = new ChannelEdgeRouter();
            module.configure( router );
            graph.addDataProvider( ChannelEdgeRouter.AFFECTED_EDGES, new DataProviderAdapter()
            {
                public boolean getBool( Object dataHolder )
                {
                    return selectedNodes.getBool( ( ( ( Edge ) dataHolder ).source() ) ) || selectedNodes.getBool(
                            ( ( Edge ) dataHolder ).target() );
                }
            } );
            this.route( router, graph );
            graph.removeDataProvider( ChannelEdgeRouter.AFFECTED_EDGES );
        }

        public void routeEdgesToSelection( final Graph2D graph )
        {
            ChannelEdgeRouter router = new ChannelEdgeRouter();
            module.configure( router );
            routeEdgesToSelection( graph, router, ChannelEdgeRouter.AFFECTED_EDGES );
        }

        void route( Layouter router, LayoutGraph graph )
        {
            router.doLayout( graph );
        }
    }
}
