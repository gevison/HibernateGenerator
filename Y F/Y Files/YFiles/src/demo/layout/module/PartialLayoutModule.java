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
package demo.layout.module;

import y.layout.Layouter;
import y.layout.circular.CircularLayouter;
import y.layout.hierarchic.IncrementalHierarchicLayouter;
import y.layout.organic.SmartOrganicLayouter;
import y.layout.orthogonal.OrthogonalLayouter;
import y.layout.partial.PartialLayouter;
import y.module.LayoutModule;
import y.option.OptionHandler;
import y.view.Graph2D;
import y.view.Selections;

/**
 * This module represents an interactive configurator and launcher for
 * {@link y.layout.partial.PartialLayouter}.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/partial_layout.html#partial_layout">Section Partial Layout </a> in the yFiles for Java Developer's Guide
 */
public class PartialLayoutModule extends LayoutModule
{
    private static final String PARTIAL = "PARTIAL";

    private static final String GENERAL = "GENERAL";

    public static final String SUBGRAPH_LAYOUTER = "SUBGRAPH_LAYOUTER";

    public static final String SUBGRAPH_LAYOUTER_IHL = "SUBGRAPH_LAYOUTER_IHL";

    public static final String SUBGRAPH_LAYOUTER_ORGANIC = "SUBGRAPH_LAYOUTER_ORGANIC";

    public static final String SUBGRAPH_LAYOUTER_CIRCULAR = "SUBGRAPH_LAYOUTER_CIRCULAR";

    public static final String SUBGRAPH_LAYOUTER_ORTHOGONAL = "SUBGRAPH_LAYOUTER_ORTHOGONAL";

    private static final String SUBGRAPH_LAYOUTER_NO_LAYOUT = "SUBGRAPH_LAYOUTER_NO_LAYOUT";

    private static final String MIN_NODE_DIST = "MIN_NODE_DIST";

    public static final String SUBGRAPH_POSITION_STRATEGY = "SUBGRAPH_POSITION_STRATEGY";

    public static final String SUBGRAPH_POSITIONING_STRATEGY_BARYCENTER = "SUBGRAPH_POSITION_STRATEGY_BARYCENTER";

    public static final String SUBGRAPH_POSITIONING_STRATEGY_FROM_SKETCH = "SUBGRAPH_POSITION_STRATEGY_FROM_SKETCH";

    public static final String ROUTING_TO_SUBGRAPH = "ROUTING_TO_SUBGRAPH";

    public static final String ROUTING_TO_SUBGRAPH_STRAIGHT_LINE = "ROUTING_TO_SUBGRAPH_STRAIGHT_LINE";

    public static final String ROUTING_TO_SUBGRAPH_POLYLINE = "ROUTING_TO_SUBGRAPH_POLYLINE";

    public static final String ROUTING_TO_SUBGRAPH_ORTHOGONALLY = "ROUTING_TO_SUBGRAPH_ORTHOGONALLY";

    public static final String ROUTING_TO_SUBGRAPH_ORGANIC = "ROUTING_TO_SUBGRAPH_ORGANIC";

    public static final String ROUTING_TO_SUBGRAPH_AUTO = "ROUTING_TO_SUBGRAPH_AUTO";

    public static final String MODE_COMPONENT_ASSIGNMENT = "MODE_COMPONENT_ASSIGNMENT";

    public static final String MODE_COMPONENT_CLUSTERING = "MODE_COMPONENT_CLUSTERING";

    public static final String MODE_COMPONENT_CONNECTED = "MODE_COMPONENT_CONNECTED";

    public static final String MODE_COMPONENT_CUSTOMIZED = "MODE_COMPONENT_CUSTOMIZED";

    public static final String MODE_COMPONENT_SINGLE = "MODE_COMPONENT_SINGLE";

    public static final String HIERARCHY_REORGANIZATION = "HIERARCHY_REORGANIZATION";

    public static final String ORIENTATION_MAIN_GRAPH = "ORIENTATION_MAIN_GRAPH";

    public static final String ORIENTATION_MAIN_GRAPH_NONE = "ORIENTATION_MAIN_GRAPH_NONE";

    public static final String ORIENTATION_MAIN_GRAPH_AUTO_DETECT = "ORIENTATION_MAIN_GRAPH_AUTO_DETECT";

    public static final String ORIENTATION_MAIN_GRAPH_TOP_TO_DOWN = "ORIENTATION_MAIN_GRAPH_TOP_TO_DOWN";

    public static final String ORIENTATION_MAIN_GRAPH_DOWN_TO_TOP = "ORIENTATION_MAIN_GRAPH_DOWN_TO_TOP";

    public static final String ORIENTATION_MAIN_GRAPH_LEFT_TO_RIGHT = "ORIENTATION_MAIN_GRAPH_LEFT_TO_RIGHT";

    public static final String ORIENTATION_MAIN_GRAPH_RIGHT_TO_LEFT = "ORIENTATION_MAIN_GRAPH_RIGHT_TO_LEFT";

    public static final String CONSIDER_SNAPLINES = "CONSIDER_SNAPLINES";

    private static final String[] subgraphLayouterEnum = { SUBGRAPH_LAYOUTER_IHL, SUBGRAPH_LAYOUTER_ORGANIC,
                                                           SUBGRAPH_LAYOUTER_CIRCULAR, SUBGRAPH_LAYOUTER_ORTHOGONAL,
                                                           SUBGRAPH_LAYOUTER_NO_LAYOUT };

    private static final String[] subgraphPositionStrategyEnum = { SUBGRAPH_POSITIONING_STRATEGY_BARYCENTER,
                                                                   SUBGRAPH_POSITIONING_STRATEGY_FROM_SKETCH };

    private static final String[] routerToSubgraphEnum = { ROUTING_TO_SUBGRAPH_AUTO, ROUTING_TO_SUBGRAPH_STRAIGHT_LINE,
                                                           ROUTING_TO_SUBGRAPH_POLYLINE,
                                                           ROUTING_TO_SUBGRAPH_ORTHOGONALLY
            , ROUTING_TO_SUBGRAPH_ORGANIC };

    private static final String[] modeComponentAsignmentEnum = { MODE_COMPONENT_CONNECTED,
                                                                 MODE_COMPONENT_SINGLE, MODE_COMPONENT_CUSTOMIZED };

    private static final String[] orientationMainGraphEnum = { ORIENTATION_MAIN_GRAPH_AUTO_DETECT,
                                                               ORIENTATION_MAIN_GRAPH_TOP_TO_DOWN,
                                                               ORIENTATION_MAIN_GRAPH_DOWN_TO_TOP,
                                                               ORIENTATION_MAIN_GRAPH_LEFT_TO_RIGHT,
                                                               ORIENTATION_MAIN_GRAPH_RIGHT_TO_LEFT,
                                                               ORIENTATION_MAIN_GRAPH_NONE };


    private static final byte EDGE_ROUTING_ORTHOGONAL = 0;

    private static final byte EDGE_ROUTING_STRAIGHTLINE = 1;

    private static final byte EDGE_ROUTING_AUTOMATIC = 2;

    private static final byte EDGE_ROUTING_ORGANIC = 3;

    private static final byte EDGE_ROUTING_POLYLINE = 4;

    public PartialLayoutModule()
    {
        super( PARTIAL, "yFiles Layout Team", "An algorithm that lays out only a subset of a graph." );
    }

    public OptionHandler createOptionHandler()
    {
        OptionHandler op = new OptionHandler( getModuleName() );
        op.useSection( GENERAL );
        op.addEnum( ROUTING_TO_SUBGRAPH, routerToSubgraphEnum, 0 );
        op.addEnum( MODE_COMPONENT_ASSIGNMENT, modeComponentAsignmentEnum, 0 );
        op.addEnum( SUBGRAPH_LAYOUTER, subgraphLayouterEnum, 0 );
        op.addEnum( SUBGRAPH_POSITION_STRATEGY, subgraphPositionStrategyEnum, 0 );
        op.addInt( MIN_NODE_DIST, 30, 1, 100 );
        op.addEnum( ORIENTATION_MAIN_GRAPH, orientationMainGraphEnum, 0 );
        op.addBool( CONSIDER_SNAPLINES, true );
        return op;
    }

    protected void mainrun()
    {
        final Graph2D graph = getGraph2D();
        if ( graph.selectedNodes().size() + graph.selectedEdges().size() == 0 )
        {
            return; //nothing to do
        }

        OptionHandler op = getOptionHandler();

        //register dp for selected nodes/edges
        graph.addDataProvider( PartialLayouter.PARTIAL_NODES_DPKEY, Selections.createSelectionDataProvider( graph ) );
        graph.addDataProvider( PartialLayouter.PARTIAL_EDGES_DPKEY, Selections.createSelectionDataProvider( graph ) );

        //create layouter
        final PartialLayouter partialLayouter = new PartialLayouter();
        partialLayouter.setMinimalNodeDistance( op.getInt( MIN_NODE_DIST ) );
        partialLayouter.setConsiderNodeAlignment( op.getBool( CONSIDER_SNAPLINES ) );

        final byte subgraphPositioningStrategy = subgraphPositioningStrategyAsByte(
                subgraphPositionStrategyEnum[ op.getEnum( SUBGRAPH_POSITION_STRATEGY ) ] );
        partialLayouter.setPositioningStrategy( subgraphPositioningStrategy );

        final byte componentAssignment = componentAssignmentAsByte(
                modeComponentAsignmentEnum[ op.getEnum( MODE_COMPONENT_ASSIGNMENT ) ] );
        partialLayouter.setComponentAssignmentStrategy( componentAssignment );

        final byte mainGraphOrientation = graphOrientationAsByte(
                orientationMainGraphEnum[ op.getEnum( ORIENTATION_MAIN_GRAPH ) ] );
        partialLayouter.setLayoutOrientation( mainGraphOrientation );

        final byte routingToSubGraph =
                routingToSubGraphAsByte( routerToSubgraphEnum[ op.getEnum( ROUTING_TO_SUBGRAPH ) ] );
        partialLayouter.setEdgeRoutingStrategy( ( routingToSubGraph == EDGE_ROUTING_POLYLINE ) ?
                                                EDGE_ROUTING_STRAIGHTLINE : routingToSubGraph );

        //determine the subgraph layouter
        final String subgraphLayouterString = subgraphLayouterEnum[ op.getEnum( SUBGRAPH_LAYOUTER ) ];
        Layouter subgraphLayouter = null;
        if ( PartialLayouter.COMPONENT_ASSIGNMENT_STRATEGY_SINGLE == componentAssignment )
        {
            //trivial case: for single nodes components it should be no subgraph layouter
            subgraphLayouter = null;
        }
        else if ( SUBGRAPH_LAYOUTER_IHL.equals( subgraphLayouterString ) )
        {
            subgraphLayouter = new IncrementalHierarchicLayouter();
            ( ( IncrementalHierarchicLayouter ) subgraphLayouter ).setIntegratedEdgeLabelingEnabled( true );
            if ( PartialLayouter.EDGE_ROUTING_STRATEGY_ORTHOGONAL == routingToSubGraph )
            {
                ( ( IncrementalHierarchicLayouter ) subgraphLayouter ).setOrthogonallyRouted( true );
            }
            else
            {
                ( ( IncrementalHierarchicLayouter ) subgraphLayouter ).setOrthogonallyRouted( false );
            }
        }
        else if ( SUBGRAPH_LAYOUTER_ORGANIC.equals( subgraphLayouterString ) )
        {
            subgraphLayouter = new SmartOrganicLayouter();
            ( ( SmartOrganicLayouter ) subgraphLayouter ).setDeterministic( true );
        }
        else if ( SUBGRAPH_LAYOUTER_CIRCULAR.equals( subgraphLayouterString ) )
        {
            subgraphLayouter = new CircularLayouter();
        }
        else if ( SUBGRAPH_LAYOUTER_ORTHOGONAL.equals( subgraphLayouterString ) )
        {
            subgraphLayouter = new OrthogonalLayouter();
        }
        else if ( SUBGRAPH_LAYOUTER_NO_LAYOUT.equals( subgraphLayouterString ) )
        {
            subgraphLayouter = null;
        }
        partialLayouter.setCoreLayouter( subgraphLayouter );

        getLayoutExecutor().setConfiguringTableNodeRealizers( true );
        final boolean horizontalLayout = ( mainGraphOrientation == PartialLayouter.ORIENTATION_LEFT_TO_RIGHT )
                || ( mainGraphOrientation == PartialLayouter.ORIENTATION_RIGHT_TO_LEFT );
        getLayoutExecutor().getTableLayoutConfigurator().setHorizontalLayoutConfiguration( horizontalLayout );

        launchLayouter( partialLayouter );
    }

    /**
     * Determines the routing of inter edges (main graph to subgraph).
     */
    private static byte routingToSubGraphAsByte( final String routingToSubGraphString )
    {
        if ( ROUTING_TO_SUBGRAPH_ORTHOGONALLY.equals( routingToSubGraphString ) )
        {
            return EDGE_ROUTING_ORTHOGONAL;
        }
        else if ( ROUTING_TO_SUBGRAPH_STRAIGHT_LINE.equals( routingToSubGraphString ) )
        {
            return EDGE_ROUTING_STRAIGHTLINE;
        }
        else if ( ROUTING_TO_SUBGRAPH_AUTO.equals( routingToSubGraphString ) )
        {
            return EDGE_ROUTING_AUTOMATIC;
        }
        else if ( ROUTING_TO_SUBGRAPH_ORGANIC.equals( routingToSubGraphString ) )
        {
            return EDGE_ROUTING_ORGANIC;
        }
        else if ( ROUTING_TO_SUBGRAPH_POLYLINE.equals( routingToSubGraphString ) )
        {
            return EDGE_ROUTING_POLYLINE;
        }
        else
        {
            return PartialLayouter.EDGE_ROUTING_STRATEGY_STRAIGHTLINE;
        }
    }

    /**
     * Determines the subgraph position strategy.
     */
    private static byte subgraphPositioningStrategyAsByte( final String subgraphPositioningStrategyString )
    {
        if ( SUBGRAPH_POSITIONING_STRATEGY_FROM_SKETCH.equals( subgraphPositioningStrategyString ) )
        {
            return PartialLayouter.SUBGRAPH_POSITIONING_STRATEGY_FROM_SKETCH;
        }
        else
        {
            return PartialLayouter.SUBGRAPH_POSITIONING_STRATEGY_BARYCENTER;
        }
    }

    /**
     * Determines component by: {Clustering, Connected graph, Particular}.
     */
    private static byte componentAssignmentAsByte( final String componentAssignmentString )
    {
        if ( MODE_COMPONENT_SINGLE.equals( componentAssignmentString ) )
        {
            return PartialLayouter.COMPONENT_ASSIGNMENT_STRATEGY_SINGLE;
        }
        else if ( MODE_COMPONENT_CONNECTED.equals( componentAssignmentString ) )
        {
            return PartialLayouter.COMPONENT_ASSIGNMENT_STRATEGY_CONNECTED;
        }
        else if ( MODE_COMPONENT_CLUSTERING.equals( componentAssignmentString ) )
        {
            return PartialLayouter.COMPONENT_ASSIGNMENT_STRATEGY_CLUSTERING;
        }
        else
        {
            return PartialLayouter.COMPONENT_ASSIGNMENT_STRATEGY_CUSTOMIZED;
        }
    }

    /**
     * Determines the main graph Orientation.
     */
    private static byte graphOrientationAsByte( final String graphOrientationString )
    {
        if ( ORIENTATION_MAIN_GRAPH_AUTO_DETECT.equals( graphOrientationString ) )
        {
            return PartialLayouter.ORIENTATION_AUTO_DETECTION;
        }
        else if ( ORIENTATION_MAIN_GRAPH_TOP_TO_DOWN.equals( graphOrientationString ) )
        {
            return PartialLayouter.ORIENTATION_TOP_TO_BOTTOM;
        }
        else if ( ORIENTATION_MAIN_GRAPH_DOWN_TO_TOP.equals( graphOrientationString ) )
        {
            return PartialLayouter.ORIENTATION_BOTTOM_TO_TOP;
        }
        else if ( ORIENTATION_MAIN_GRAPH_LEFT_TO_RIGHT.equals( graphOrientationString ) )
        {
            return PartialLayouter.ORIENTATION_LEFT_TO_RIGHT;
        }
        else if ( ORIENTATION_MAIN_GRAPH_RIGHT_TO_LEFT.equals( graphOrientationString ) )
        {
            return PartialLayouter.ORIENTATION_RIGHT_TO_LEFT;
        }
        else if ( ORIENTATION_MAIN_GRAPH_NONE.equals( graphOrientationString ) )
        {
            return PartialLayouter.ORIENTATION_NONE;
        }
        else
        {
            return PartialLayouter.ORIENTATION_AUTO_DETECTION;
        }
    }
}
