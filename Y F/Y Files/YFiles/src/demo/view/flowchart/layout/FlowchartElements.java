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
package demo.view.flowchart.layout;

import y.base.DataProvider;
import y.base.Edge;
import y.base.Graph;
import y.base.GraphInterface;
import y.base.Node;

/**
 * Provides type constants and corresponding <code>isXYZType()</code> methods for Flowchart symbols. These constants and
 * methods are used by {@link FlowchartLayouter} and its associated classes to identify specific nodes and handle them
 * appropriately.
 *
 * @noinspection ImplicitNumericConversion
 */
public class FlowchartElements
{
    /**
     * Type constant for an invalid type.
     */
    public static final byte TYPE_INVALID = 0;

    /**
     * Type constant for an event type.
     */
    public static final byte NODE_TYPE_EVENT = 1;

    /**
     * Type constant for a start event type.
     */
    public static final byte NODE_TYPE_START_EVENT = 7;

    /**
     * Type constant for a end event type.
     */
    public static final byte NODE_TYPE_END_EVENT = 9;

    /**
     * Type constant for a decision type.
     */
    public static final byte NODE_TYPE_DECISION = 2;

    /**
     * Type constant for a process type.
     */
    public static final byte NODE_TYPE_PROCESS = 3;

    /**
     * Type constant for a group type.
     */
    public static final byte NODE_TYPE_GROUP = 8;

    /**
     * Type constant for a annotation type.
     */
    public static final byte NODE_TYPE_ANNOTATION = 10;

    /**
     * Type constant for a pool type.
     */
    public static final byte NODE_TYPE_POOL = 12;

    /**
     * Type constant for a data type.
     */
    public static final byte NODE_TYPE_DATA = 11;

    /**
     * Type constant for a connection type (sequence flow).
     */
    public static final byte EDGE_TYPE_SEQUENCE_FLOW = 4;

    /**
     * Type constant for a connection type (message flow).
     */
    public static final byte EDGE_TYPE_MESSAGE_FLOW = 5;

    /**
     * Type constant for a connection type (association).
     */
    public static final byte EDGE_TYPE_ASSOCIATION = 6;

    /**
     * Returns true for activity nodes. For Flowcharts, this are Process, Data, and Group. For BPMN, this are Task and
     * Sub-Process.
     */
    static boolean isActivity( final Graph graph, final Node node )
    {
        final byte type = getType( graph, node );
        return ( type == NODE_TYPE_PROCESS ) || ( type == NODE_TYPE_DATA ) || ( type == NODE_TYPE_GROUP );
    }

    /**
     * Returns true for group nodes. For BPMN, this is Sub-Process.
     */
    static boolean isGroup( final Graph graph, final Node node )
    {
        return getType( graph, node ) == NODE_TYPE_GROUP;
    }

    /**
     * Returns true for annotation nodes.
     */
    static boolean isAnnotation( final Graph graph, final Node node )
    {
        return getType( graph, node ) == NODE_TYPE_ANNOTATION;
    }

    /**
     * Returns true for event nodes. For Flowchart, this are start and terminator, delay, display, manual operation and
     * preparation. For BPMN, this are start, end and other events.
     */
    static boolean isEvent( final Graph graph, final Node node )
    {
        final byte type = getType( graph, node );
        return ( type == NODE_TYPE_START_EVENT ) || ( type == NODE_TYPE_EVENT ) || ( type == NODE_TYPE_END_EVENT );
    }

    /**
     * Returns true for start event nodes.
     */
    static boolean isStartEvent( final Graph graph, final Node node )
    {
        return getType( graph, node ) == NODE_TYPE_START_EVENT;
    }

    /**
     * Returns true for end event nodes.
     */
    static boolean isEndEvent( final Graph graph, final Node node )
    {
        return getType( graph, node ) == NODE_TYPE_END_EVENT;
    }

    /**
     * Returns true for decision nodes. For BPMN, this are all Gateways.
     */
    static boolean isDecision( final Graph graph, final Node node )
    {
        return getType( graph, node ) == NODE_TYPE_DECISION;
    }

    static boolean isUndefined( final Graph graph, final Edge edge )
    {
        return getType( graph, edge ) == TYPE_INVALID;
    }

    static boolean isRegularEdge( final Graph graph, final Edge edge )
    {
        return getType( graph, edge ) == EDGE_TYPE_SEQUENCE_FLOW;
    }

    static boolean isMessageFlow( final Graph graph, final Edge edge )
    {
        return getType( graph, edge ) == EDGE_TYPE_MESSAGE_FLOW;
    }

    static byte getType( final GraphInterface graph, final Edge dataHolder )
    {
        final DataProvider dataProvider = graph.getDataProvider( FlowchartLayouter.EDGE_TYPE_DPKEY );
        return dataProvider == null ? TYPE_INVALID : ( byte ) dataProvider.getInt( dataHolder );
    }

    static byte getType( final GraphInterface graph, final Node dataHolder )
    {
        final DataProvider dataProvider = graph.getDataProvider( FlowchartLayouter.NODE_TYPE_DPKEY );
        return dataProvider == null ? TYPE_INVALID : ( byte ) dataProvider.getInt( dataHolder );
    }

    private FlowchartElements()
    {
    }
}
