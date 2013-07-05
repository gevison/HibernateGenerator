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
package demo.view.entityrelationship;

import demo.view.DemoBase;
import demo.view.entityrelationship.painters.ErdAttributesNodeLabelModel;
import demo.view.entityrelationship.painters.ErdRealizerFactory;
import demo.view.flowchart.FlowchartView;
import y.base.Edge;
import y.io.GraphMLIOHandler;
import y.io.graphml.graph2d.Graph2DGraphMLHandler;
import y.layout.orthogonal.EdgeLayoutDescriptor;
import y.layout.orthogonal.OrthogonalLayouter;
import y.module.ModuleEvent;
import y.module.ModuleListener;
import y.module.OrthogonalLayoutModule;
import y.option.OptionHandler;
import y.util.DataProviderAdapter;
import y.view.Arrow;
import y.view.EdgeRealizer;
import y.view.EditMode;
import y.view.Graph2D;
import y.view.Graph2DClipboard;
import y.view.Graph2DUndoManager;
import y.view.Graph2DView;
import y.view.Graph2DViewActions;
import y.view.HitInfo;
import y.view.NodeLabel;
import y.view.NodeRealizer;
import y.view.ViewMode;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.Locale;

/**
 * A viewer and editor for entity relationship diagrams (ERD). It shows how to
 * <ul>
 * <li>add a palette of ERD symbols, the {@link EntityRelationshipPalette}, to ease the creation of diagrams</li>
 * <li>implement a {@link y.view.GenericNodeRealizer.Painter} tailored for the drawing of ERD symbols
 * with two labels</li>
 * <li>convert the notation of the diagram with a custom class, the {@link ErdNotationConverter}</li>
 * <li>apply an orthogonal layout with suitable default values</li>
 * <li>add undo/redo/cut/copy/paste</li>
 * </ul>
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/orthogonal_layouter.html">Section Orthogonal Layoutt</a> in the yFiles for Java Developer's Guide
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/directed_orthogonal_layouter.html">Section Directed Orthogonal Layout</a> in the yFiles for Java Developer's Guide
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/orthogonal_group_layouter.html">Section Orthogonal Layout of Grouped Graphst</a> in the yFiles for Java Developer's Guide
 */
public class EntityRelationshipDemo extends DemoBase
{

    /**
     * Names of the provided example graphs
     */
    private static final String[] EXAMPLES_FILE_NAMES = {
            "chen.graphml",
            "alaska_geologic_database.graphml",
            "crows_foot.graphml",
            "space_database.graphml"
    };

    /**
     * Component that provides the symbols of ERD diagrams
     */
    EntityRelationshipPalette palette;

    /**
     * Manager of undo- and redo-events
     */
    private Graph2DUndoManager undoManager;

    /**
     * Clipboard to provide cut/copy/past
     */
    private Graph2DClipboard clipboard;

    /**
     * Module for execution of orthogonal layout
     */
    private OrthogonalLayoutModule module;

    /**
     * Instantiates this demo and builds the GUI.
     */
    public EntityRelationshipDemo()
    {
        super();

        JPanel panelPalette = createTitledPanel( palette, "ERD Palette" );
        contentPane.add( new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, panelPalette, view ), BorderLayout.CENTER );

        loadGraph( "resource/graphs/" + EXAMPLES_FILE_NAMES[ 0 ] );
    }

    /**
     * Initializes the Flowchart palette, the undo manager, the clipboard and the layout module
     */
    protected void initialize()
    {
        palette = new EntityRelationshipPalette( view );
        palette.setSnapMode( true );

        undoManager = new Graph2DUndoManager( view.getGraph2D() );
        undoManager.setViewContainer( view );

        clipboard = new Graph2DClipboard( view );
        clipboard.setCopyFactory( view.getGraph2D().getGraphCopyFactory() );

        // orthogonal layout with default settings
        module = new OrthogonalLayoutModule();
        OptionHandler defaultSettings = module.getOptionHandler();
        defaultSettings.set( "LAYOUT", "STYLE", "NORMAL_TREE" );
        defaultSettings.set( "LAYOUT", "GRID", new Integer( 5 ) );
        defaultSettings.set( "LAYOUT", "USE_FACE_MAXIMIZATION", Boolean.TRUE );
        defaultSettings.set( "LABELING", "EDGE_LABELING", "GENERIC" );
        defaultSettings.set( "LABELING", "EDGE_LABEL_MODEL", "AS_IS" );
        defaultSettings.getItem( "LAYOUT", "MINIMUM_FIRST_SEGMENT_LENGTH" ).setEnabled( false );
        defaultSettings.getItem( "LAYOUT", "MINIMUM_LAST_SEGMENT_LENGTH" ).setEnabled( false );
        defaultSettings.getItem( "LAYOUT", "MINIMUM_SEGMENT_LENGTH" ).setEnabled( false );

        // register module listener in order to assign minimal first/last segment lengths for every
        // individually before running layout using a DataProvider and remove the DataProvider afterwards
        module.addModuleListener( new ModuleListener()
        {
            public void moduleEventHappened( ModuleEvent moduleEvent )
            {
                short type = moduleEvent.getEventType();
                final Graph2D graph = view.getGraph2D();
                if ( type == ModuleEvent.TYPE_MODULE_INITIALIZING )
                {
                    // add DataProvider to ensure a minimal first/last segment length if there are arrows
                    graph.addDataProvider( OrthogonalLayouter.EDGE_LAYOUT_DESCRIPTOR_DPKEY,
                                           new DataProviderAdapter()
                                           {
                                               public Object get( Object dataHolder )
                                               {
                                                   EdgeRealizer realizer = graph.getRealizer(
                                                           ( ( Edge ) dataHolder ) );
                                                   Arrow sourceArrow = realizer.getSourceArrow();
                                                   Arrow targetArrow = realizer.getTargetArrow();
                                                   EdgeLayoutDescriptor descriptor = new EdgeLayoutDescriptor();
                                                   descriptor.setMinimumFirstSegmentLength(
                                                           getArrowLength( sourceArrow ) + 10 );
                                                   descriptor.setMinimumLastSegmentLength(
                                                           getArrowLength( targetArrow ) + 10 );
                                                   return descriptor;
                                               }
                                           } );
                }
                else if ( type == ModuleEvent.TYPE_MODULE_DISPOSED )
                {
                    // remove DataProvider with information about the minimal first/last segment length
                    graph.removeDataProvider( OrthogonalLayouter.EDGE_LAYOUT_DESCRIPTOR_DPKEY );
                }
            }

            private double getArrowLength( Arrow arrow )
            {
                switch ( arrow.getType() )
                {
                    case Arrow.CROWS_FOOT_ONE_TYPE:
                        return 10;
                    case Arrow.CROWS_FOOT_ONE_MANDATORY_TYPE:
                        return 15;
                    case Arrow.CROWS_FOOT_ONE_OPTIONAL_TYPE:
                        return 20;
                    case Arrow.CROWS_FOOT_MANY_TYPE:
                        return 10;
                    case Arrow.CROWS_FOOT_MANY_MANDATORY_TYPE:
                        return 15;
                    case Arrow.CROWS_FOOT_MANY_OPTIONAL_TYPE:
                        return 20;
                    default:
                        return arrow.getArrowLength();
                }
            }
        } );
    }

    /**
     * Registers the default view actions and an additional handler that reacts to label changes
     */
    protected void registerViewActions()
    {
        super.registerViewActions();
        final Action action = view.getCanvasComponent().getActionMap().get( Graph2DViewActions.EDIT_LABEL );
        action.putValue( "PROPERTY_CHANGE_LISTENER", new LabelChangeHandler() );
    }

    /**
     * Prevents view from registering view modes automatically because the <code>FlowchartView</code> will register its own view modes
     */
    protected void registerViewModes()
    {
    }

    /**
     * Creates a <code>GraphMLOIHandler</code> with additionally (de-)serialization
     * support for the custom label model that is used in big entities.
     *
     * @return an extended <code>GraphMLOIHandler</code> with support for bit entities
     *
     * @see ErdAttributesNodeLabelModel
     */
    protected GraphMLIOHandler createGraphMLIOHandler()
    {
        GraphMLIOHandler graphMLIOHandler = super.createGraphMLIOHandler();
        Graph2DGraphMLHandler graphMLHandler = graphMLIOHandler.getGraphMLHandler();
        ErdAttributesNodeLabelModel.Handler handler = new ErdAttributesNodeLabelModel.Handler();
        graphMLHandler.addSerializationHandler( handler );
        graphMLHandler.addDeserializationHandler( handler );

        return graphMLIOHandler;
    }

    /**
     * Adds menu items for example graphs to the default menu bar.
     *
     * @return the menu bar for this demo.
     */
    protected JMenuBar createMenuBar()
    {
        JMenu examplesMenu = new JMenu( "Examples" );
        for ( int i = 0; i < EXAMPLES_FILE_NAMES.length; i++ )
        {
            final String fileName = EXAMPLES_FILE_NAMES[ i ];
            examplesMenu.add( new JMenuItem( new AbstractAction( fileName )
            {
                public void actionPerformed( ActionEvent e )
                {
                    loadGraph( "resource/graphs/" + fileName );
                }
            } ) );
        }

        JMenuBar menuBar = super.createMenuBar();
        menuBar.add( examplesMenu );
        return menuBar;
    }

    /**
     * Adds undo/redo actions, cut/copy/paste actions, an orthogonal layout editor action
     * and notation converter actions to the default toolbar.
     *
     * @return the toolbar for this demo.
     */
    protected JToolBar createToolBar()
    {
        JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( createUndoAction() );
        toolBar.add( createRedoAction() );
        toolBar.addSeparator();
        toolBar.add( createCutAction() );
        toolBar.add( createCopyAction() );
        toolBar.add( createPasteAction() );
        toolBar.addSeparator();
        toolBar.add( createActionControl( createOrthogonalLayoutAction() ) );
        toolBar.addSeparator();
        toolBar.add( createCrowsFootNotationAction() );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( createChenNotationAction() );
        return toolBar;
    }

    /**
     * Creates an action to trigger a conversion to Crow's Foot notation.
     *
     * @return the converter action
     */
    private Action createCrowsFootNotationAction()
    {
        Action action = new AbstractAction( "Convert to Crow's Foot" )
        {

            public void actionPerformed( ActionEvent e )
            {
                Graph2D graph = view.getGraph2D();
                try
                {
                    graph.firePreEvent();
                    ErdNotationConverter.convertToCrowFoot( graph );
                    module.start( view.getGraph2D() );
                }
                finally
                {
                    graph.firePostEvent();
                }

                view.fitContent();
                view.updateView();
            }
        };

        return action;
    }

    /**
     * Creates an action to trigger a conversion to Chen notation.
     *
     * @return the converter action
     */
    private Action createChenNotationAction()
    {
        Action action = new AbstractAction( "Convert to Chen" )
        {

            public void actionPerformed( ActionEvent e )
            {
                Graph2D graph = view.getGraph2D();
                try
                {
                    graph.firePreEvent();
                    ErdNotationConverter.convertToChen( graph );
                    module.start( view.getGraph2D() );
                }
                finally
                {
                    graph.firePostEvent();
                }

                view.fitContent();
                view.updateView();
            }
        };

        return action;
    }

    /**
     * Creates an action that shows an editor to adjust and execute orthogonal
     * layout.
     *
     * @return the orthogonal layout action
     */
    private Action createOrthogonalLayoutAction()
    {
        Action action = new AbstractAction( "Layout" )
        {
            public void actionPerformed( ActionEvent e )
            {
                OptionSupport.showDialog( module, view.getGraph2D(), true, view.getFrame() );
            }
        };
        action.putValue( Action.SHORT_DESCRIPTION, "Configure and run the layout algorithm" );
        action.putValue( Action.SMALL_ICON, SHARED_LAYOUT_ICON );

        return action;
    }

    /**
     * Creates and configures the undo action.
     *
     * @return the undo action.
     */
    protected Action createUndoAction()
    {
        Action undoAction = undoManager.getUndoAction();
        undoAction.putValue( Action.SMALL_ICON, getIconResource( "resource/undo.png" ) );
        undoAction.putValue( Action.SHORT_DESCRIPTION, "Undo" );

        return undoAction;
    }

    /**
     * Creates and configures the redo action.
     *
     * @return the redo action.
     */
    protected Action createRedoAction()
    {
        Action redoAction = undoManager.getRedoAction();
        redoAction.putValue( Action.SMALL_ICON, getIconResource( "resource/redo.png" ) );
        redoAction.putValue( Action.SHORT_DESCRIPTION, "Redo" );
        return redoAction;
    }

    /**
     * Creates and configures the cut action.
     *
     * @return the cut action.
     */
    protected Action createCutAction()
    {
        Action cutAction = clipboard.getCutAction();
        cutAction.putValue( Action.SMALL_ICON, getIconResource( "resource/cut.png" ) );
        cutAction.putValue( Action.SHORT_DESCRIPTION, "Cut" );

        view.getCanvasComponent().getActionMap().put( "CUT", cutAction );
        view.getCanvasComponent().getInputMap().put(
                KeyStroke.getKeyStroke( KeyEvent.VK_X, InputEvent.CTRL_MASK ), "CUT" );

        return cutAction;
    }

    /**
     * Creates and configures the copy action.
     *
     * @return the copy action.
     */
    Action createCopyAction()
    {
        Action copyAction = clipboard.getCopyAction();
        copyAction.putValue( Action.SMALL_ICON, getIconResource( "resource/copy.png" ) );
        copyAction.putValue( Action.SHORT_DESCRIPTION, "Copy" );

        view.getCanvasComponent().getActionMap().put( "COPY", copyAction );
        view.getCanvasComponent().getInputMap().put(
                KeyStroke.getKeyStroke( KeyEvent.VK_C, InputEvent.CTRL_MASK ), "COPY" );

        return copyAction;
    }

    /**
     * Creates and configures the paste action.
     *
     * @return the paste action.
     */
    Action createPasteAction()
    {
        Action pasteAction = clipboard.getPasteAction();
        pasteAction.putValue( Action.SMALL_ICON, getIconResource( "resource/paste.png" ) );
        pasteAction.putValue( Action.SHORT_DESCRIPTION, "Paste" );

        view.getCanvasComponent().getActionMap().put( "PASTE", pasteAction );
        view.getCanvasComponent().getInputMap().put(
                KeyStroke.getKeyStroke( KeyEvent.VK_V, InputEvent.CTRL_MASK ), "PASTE" );

        return pasteAction;
    }

    /**
     * Creates a panel which contains the specified component and a title on top.
     *
     * @param content the Component that will be shown in the panel
     * @param title   the text that will be displayed on top of the panel
     *
     * @return the panel
     */
    protected JPanel createTitledPanel( JComponent content, String title )
    {
        JLabel label = new JLabel( title );
        label.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
        label.setBackground( new Color( 231, 219, 182 ) );
        label.setOpaque( true );
        label.setForeground( Color.DARK_GRAY );
        label.setFont( label.getFont().deriveFont( Font.BOLD ) );
        label.setFont( label.getFont().deriveFont( 13.0f ) );

        JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout() );
        panel.add( label, BorderLayout.NORTH );
        panel.add( content, BorderLayout.CENTER );
        return panel;
    }

    /**
     * Creates a view of the graph that supports label editing on double-click.
     */
    protected Graph2DView createGraphView()
    {
        Graph2DView view = new FlowchartView();
        for ( Iterator iterator = view.getViewModes(); iterator.hasNext(); )
        {
            final Object next = iterator.next();
            if ( next instanceof EditMode )
            {
                final EditMode editMode = ( EditMode ) next;
                editMode.setCyclicSelectionEnabled( true );
                editMode.setPopupMode( new EntityRelationshipPopupMode() );
            }
        }
        view.addViewMode( new ViewMode()
        {

            // Reacts to double-click on nodes/labels by presenting a label editor
            public void mouseClicked( double x, double y )
            {
                if ( lastClickEvent != null && lastClickEvent.getClickCount() == 2 )
                {
                    final HitInfo hitInfo = getHitInfo( x, y );
                    if ( hitInfo.hasHitNodeLabels() )
                    {
                        view.openLabelEditor( hitInfo.getHitNodeLabel(), x, y, new LabelChangeHandler(), true );
                    }
                    else
                    {
                        if ( hitInfo.hasHitNodes() )
                        {
                            final NodeRealizer realizer = view.getGraph2D().getRealizer( hitInfo.getHitNode() );
                            for ( int i = realizer.labelCount(); i-- > 0; )
                            {
                                final NodeLabel label = realizer.getLabel( i );
                                if ( label.contains( x, y ) )
                                {
                                    view.openLabelEditor( label, x, y, new LabelChangeHandler(), true );
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } );
        view.setFitContentOnResize( true );
        return view;
    }

    /**
     * Starts the <code>EntityRelationshipDemo</code>
     *
     * @param args --
     */
    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {

            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                final EntityRelationshipDemo demo = new EntityRelationshipDemo();
                demo.start();
            }
        } );
    }

    /**
     * This handler listens for label changes and adjusts the node size to
     * the label size.
     */
    private class LabelChangeHandler implements PropertyChangeListener
    {
        public void propertyChange( PropertyChangeEvent e )
        {
            final Object source = e.getSource();
            if ( source instanceof NodeLabel )
            {
                NodeLabel srcLabel = ( NodeLabel ) source;
                NodeRealizer realizer = view.getGraph2D().getRealizer( srcLabel.getNode() );
                if ( ErdRealizerFactory.isBigEntityRealizer( realizer )
                        || ErdRealizerFactory.isSmallEntityRealizer( realizer ) )
                {
                    double newHeight = 0;
                    double newWidth = 0;
                    for ( int i = 0; i < realizer.labelCount(); i++ )
                    {
                        newHeight += realizer.getLabel( i ).getBox().getHeight();
                        newWidth = Math.max( newWidth, realizer.getLabel( i ).getBox().getWidth() );
                    }
                    if ( newHeight > realizer.getHeight() )
                    {
                        realizer.setHeight( newHeight + 15 );
                    }
                    if ( newWidth > realizer.getWidth() )
                    {
                        realizer.setWidth( newWidth + 15 );
                    }
                }
            }
        }
    }
}