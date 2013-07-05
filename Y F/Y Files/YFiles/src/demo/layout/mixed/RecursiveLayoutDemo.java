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
package demo.layout.mixed;

import demo.view.hierarchy.GroupingDemo;
import y.base.DataProvider;
import y.base.Edge;
import y.base.EdgeCursor;
import y.base.EdgeList;
import y.base.EdgeMap;
import y.layout.LayoutGraph;
import y.layout.LayoutTool;
import y.layout.Layouter;
import y.layout.ParallelEdgeLayouter;
import y.layout.circular.CircularLayouter;
import y.layout.grouping.RecursiveGroupLayouter;
import y.layout.hierarchic.IncrementalHierarchicLayouter;
import y.layout.organic.SmartOrganicLayouter;
import y.layout.orthogonal.OrthogonalGroupLayouter;
import y.layout.router.GroupNodeRouterStage;
import y.layout.router.OrthogonalEdgeRouter;
import y.util.DataProviderAdapter;
import y.view.Graph2D;
import y.view.Graph2DLayoutExecutor;
import y.view.Graph2DView;
import y.view.Graph2DViewActions;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Shows the Recursive Group Layouter. The content of each group node is recursively laid out with the specified
 * layouter, i.e., the layouter is applied to each group node separately. Note that the
 * {@link y.layout.grouping.RecursiveGroupLayouter}
 * also supports to specify different layout algorithms for different group nodes, see {@link MixedLayoutDemo}.
 */
public class RecursiveLayoutDemo extends GroupingDemo
{
    private static final int TYPE_ORTHOGONAL = 0;

    private static final int TYPE_HIERARCHIC = 1;

    private static final int TYPE_CIRCULAR = 2;

    private static final int TYPE_ORGANIC = 3;

    private int layoutType;

    private boolean useInterEdgeRouter;

    public RecursiveLayoutDemo()
    {
        this( null );
    }

    public RecursiveLayoutDemo( final String helpFilePath )
    {
        super();
        addHelpPane( helpFilePath );
    }

    protected void loadInitialGraph()
    {
        loadGraph( "resource/recursive.graphml" );
    }

    /**
     * Adds an extra layout action to the toolbar
     */
    protected JToolBar createToolBar()
    {
        layoutType = TYPE_ORTHOGONAL;
        final JComboBox layoutTypeSelection = new JComboBox(
                new String[]{ "Orthogonal Style", "Hierarchic Style", "Circular Style", "Organic Style" } );
        layoutTypeSelection.setSelectedIndex( layoutType );
        layoutTypeSelection.setMaximumSize( layoutTypeSelection.getPreferredSize() );
        layoutTypeSelection.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                switch ( layoutTypeSelection.getSelectedIndex() )
                {
                    default:
                    case 0:
                        layoutType = TYPE_ORTHOGONAL;
                        break;
                    case 1:
                        layoutType = TYPE_HIERARCHIC;
                        break;
                    case 2:
                        layoutType = TYPE_CIRCULAR;
                        break;
                    case 3:
                        layoutType = TYPE_ORGANIC;
                        break;
                }
                doLayout();
            }
        } );

        useInterEdgeRouter = true;
        final JToggleButton toggleRouteInterEdges = new JToggleButton( "Inter-Edge Routing", useInterEdgeRouter );
        toggleRouteInterEdges.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                useInterEdgeRouter = toggleRouteInterEdges.isSelected();
                doLayout();
            }
        } );

        final Action layoutAction = new AbstractAction(
                "Layout", SHARED_LAYOUT_ICON )
        {
            public void actionPerformed( ActionEvent e )
            {
                doLayout();
            }
        };

        JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( createActionControl( layoutAction ) );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( layoutTypeSelection );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( toggleRouteInterEdges );
        return toolBar;
    }

    /**
     * Register key bindings for our custom actions.
     */
    protected void registerViewActions()
    {
        super.registerViewActions();

        ActionMap actionMap = view.getCanvasComponent().getActionMap();
        actionMap.put( Graph2DViewActions.CLOSE_GROUPS, new MyCloseGroupsAction( view ) );
        actionMap.put( Graph2DViewActions.OPEN_FOLDERS, new MyOpenFoldersAction( view ) );
    }

    /**
     * Populates the "Grouping" menu with grouping specific actions.
     */
    protected void populateGroupingMenu( JMenu hierarchyMenu )
    {
        // Predefined actions for open/close groups
        registerAction( hierarchyMenu, Graph2DViewActions.CLOSE_GROUPS, true );
        registerAction( hierarchyMenu, Graph2DViewActions.OPEN_FOLDERS, true );

        hierarchyMenu.addSeparator();

        // Predefined actions for group/fold/ungroup
        registerAction( hierarchyMenu, Graph2DViewActions.GROUP_SELECTION, true );
        registerAction( hierarchyMenu, Graph2DViewActions.UNGROUP_SELECTION, true );
        registerAction( hierarchyMenu, Graph2DViewActions.FOLD_SELECTION, true );
    }

    /**
     * Performs the common behavior and applies a layout afterwards.
     */
    class MyCloseGroupsAction extends Graph2DViewActions.CloseGroupsAction
    {

        MyCloseGroupsAction( Graph2DView view )
        {
            super( view );
        }

        public void actionPerformed( ActionEvent e )
        {
            super.actionPerformed( e );
            doLayout();
        }
    }

    /**
     * Performs the common behavior and applies a layout afterwards.
     */
    class MyOpenFoldersAction extends Graph2DViewActions.OpenFoldersAction
    {

        MyOpenFoldersAction( Graph2DView view )
        {
            super( view );
        }

        public void actionPerformed( ActionEvent e )
        {
            super.actionPerformed( e );
            doLayout();
        }
    }

    void doLayout()
    {
        final RecursiveGroupLayouter rgl;
        final Layouter coreLayout;

        if ( layoutType == TYPE_ORTHOGONAL )
        {
            coreLayout = new OrthogonalGroupLayouter();
            rgl = createOrthogonalRecursiveGroupLayout( coreLayout, OrthogonalEdgeRouter.MONOTONIC_NONE );

        }
        else if ( layoutType == TYPE_HIERARCHIC )
        {
            final IncrementalHierarchicLayouter ihl = new IncrementalHierarchicLayouter();
            ihl.setOrthogonallyRouted( true );
            coreLayout = ihl;
            rgl = createOrthogonalRecursiveGroupLayout( coreLayout, OrthogonalEdgeRouter.MONOTONIC_VERTICAL );

        }
        else if ( layoutType == TYPE_ORGANIC )
        {
            coreLayout = new SmartOrganicLayouter();
            rgl = createRecursiveGroupLayout( coreLayout );
            rgl.setAutoAssignPortCandidatesEnabled( true );

        }
        else
        {
            final CircularLayouter cl = new CircularLayouter();
            cl.setParallelEdgeLayouter( createParallelEdgeLayouter() );

            coreLayout = cl;
            rgl = createRecursiveGroupLayout( coreLayout );
        }

        final Graph2D graph = view.getGraph2D();
        try
        {
            // map each group node to its corresponding layout algorithm
            graph.addDataProvider( RecursiveGroupLayouter.GROUP_NODE_LAYOUTER_DPKEY, new DataProviderAdapter()
            {
                public Object get( Object dataHolder )
                {
                    return coreLayout;
                }
            } );

            new Graph2DLayoutExecutor().doLayout( view, rgl );
            view.fitContent();

        }
        finally
        {
            graph.removeDataProvider( RecursiveGroupLayouter.GROUP_NODE_LAYOUTER_DPKEY );
        }
    }

    RecursiveGroupLayouter createOrthogonalRecursiveGroupLayout( Layouter coreLayout, final byte monotonicPathType )
    {
        final RecursiveGroupLayouter rgl = new RecursiveGroupLayouter( coreLayout )
        {
            protected void routeInterEdges( LayoutGraph graph, EdgeList interEdges )
            {
                if ( useInterEdgeRouter )
                {
                    DataProvider selectedEdges =
                            graph.getDataProvider( Layouter.SELECTED_EDGES ); //backup selected edges

                    EdgeMap edge2IsInterEdge = graph.createEdgeMap();
                    for ( EdgeCursor ec = interEdges.edges(); ec.ok(); ec.next() )
                    {
                        edge2IsInterEdge.setBool( ec.edge(), true );
                    }
                    graph.addDataProvider( Layouter.SELECTED_EDGES, edge2IsInterEdge );

                    //route inter-edges
                    OrthogonalEdgeRouter oer = createOrthogonalEdgeRouter();
                    if ( monotonicPathType != OrthogonalEdgeRouter.MONOTONIC_NONE )
                    {
                        oer.setMonotonicPathRestriction( monotonicPathType );
                    }
                    new GroupNodeRouterStage( oer ).doLayout( graph );

                    //restore originally selected edges
                    if ( selectedEdges != null )
                    {
                        graph.addDataProvider( Layouter.SELECTED_EDGES, selectedEdges );
                    }
                    else
                    {
                        graph.removeDataProvider( Layouter.SELECTED_EDGES );
                    }
                    graph.disposeEdgeMap( edge2IsInterEdge );
                }
                else
                {
                    super.routeInterEdges( graph, interEdges );
                }
            }
        };

        rgl.setConsiderSketchEnabled( true );

        return rgl;
    }

    RecursiveGroupLayouter createRecursiveGroupLayout( Layouter coreLayout )
    {
        RecursiveGroupLayouter rgl = new RecursiveGroupLayouter( coreLayout )
        {
            protected void routeInterEdges( LayoutGraph graph, EdgeList interEdges )
            {
                if ( useInterEdgeRouter )
                {
                    //reset paths of inter-edges
                    EdgeMap edge2IsInterEdge = graph.createEdgeMap();
                    for ( EdgeCursor ec = interEdges.edges(); ec.ok(); ec.next() )
                    {
                        Edge e = ec.edge();
                        edge2IsInterEdge.setBool( e, true );
                        LayoutTool.resetPath( graph, e );
                    }

                    //layout parallel edges
                    graph.addDataProvider( ParallelEdgeLayouter.SCOPE_DPKEY, edge2IsInterEdge );
                    createParallelEdgeLayouter().doLayout( graph );
                    graph.removeDataProvider( ParallelEdgeLayouter.SCOPE_DPKEY );
                    graph.disposeEdgeMap( edge2IsInterEdge );
                }
                else
                {
                    super.routeInterEdges( graph, interEdges );
                }
            }
        };

        rgl.setConsiderSketchEnabled( true );

        return rgl;
    }

    static OrthogonalEdgeRouter createOrthogonalEdgeRouter()
    {
        OrthogonalEdgeRouter oer = new OrthogonalEdgeRouter();
        oer.setCrossingCost( 2.0 );
        oer.setLocalCrossingMinimizationEnabled( true );
        oer.setReroutingEnabled( true );
        oer.setSphereOfAction( OrthogonalEdgeRouter.ROUTE_SELECTED_EDGES );
        return oer;
    }

    static ParallelEdgeLayouter createParallelEdgeLayouter()
    {
        ParallelEdgeLayouter pel = new ParallelEdgeLayouter();
        pel.setLineDistance( 10.0 );
        pel.setJoinEndsEnabled( true );
        return pel;
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
                new RecursiveLayoutDemo( "resource/recursivelayouthelp.html" ).start();
            }
        } );
    }
}
