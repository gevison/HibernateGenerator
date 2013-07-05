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
package demo.view.entityrelationship.painters;

import y.base.YList;
import y.geom.OrientedRectangle;
import y.geom.YDimension;
import y.geom.YRectangle;
import y.io.graphml.NamespaceConstants;
import y.io.graphml.input.DeserializationEvent;
import y.io.graphml.input.DeserializationHandler;
import y.io.graphml.input.GraphMLParseException;
import y.io.graphml.output.GraphMLWriteException;
import y.io.graphml.output.SerializationEvent;
import y.io.graphml.output.SerializationHandler;
import y.io.graphml.output.XmlWriter;
import y.layout.NodeLabelCandidate;
import y.layout.NodeLabelLayout;
import y.layout.NodeLabelModel;
import y.layout.NodeLayout;
import y.view.NodeLabel;
import y.view.NodeRealizer;

/**
 * This label model computes the size and placement information for the
 * attributes label of an ERD entity node.
 * The area of the attributes label starts at the separator line and fills
 * the whole lower compartment.
 */
public class ErdAttributesNodeLabelModel implements NodeLabelModel
{

    /**
     * The horizontal distance of the label to the border of the node
     */
    private static final double OFFSET_X = 2;

    /**
     * The minimal vertical distance of the label to the separator line
     */
    private static final double MIN_OFFSET_Y = 2;

    /**
     * The model parameter that contains information about a specific label.
     * In this case it is an empty class because there is only one possible position
     * for the attributes label.
     */
    private static final ErdAttributesNodeLabelModelParameter PARAMETER =
            new ErdAttributesNodeLabelModelParameter();

    /**
     * Returns the default parameter of this label model.
     *
     * @return the default parameter
     */
    public Object getDefaultParameter()
    {
        return PARAMETER;
    }

    /**
     * Returns the oriented label position and bounds encoded by the given model
     * parameter.
     *
     * @param labelSize  The size of the label that should be placed.
     * @param nodeLayout The layout of the node to which the label belongs.
     * @param param      The model parameter that describes the abstract position of
     *                   the label within this model. The parameter must have been generated by
     *                   this model.
     *
     * @return the oriented label position and bounds.
     */
    public OrientedRectangle getLabelPlacement(
            final YDimension labelSize,
            final NodeLayout nodeLayout,
            final Object param
    )
    {
        final YRectangle tmp1 = getLabelBox( labelSize, nodeLayout );
        return new OrientedRectangle( tmp1 );
    }

    /**
     * Returns a list of candidate positions for the specified node and label data.
     *
     * @param nl         The label layout for which candidates should be generated.
     * @param nodeLayout The layout of the node to which the label belongs.
     *
     * @return a list of candidate positions for the specified node and label data.
     */
    public YList getLabelCandidates(
            final NodeLabelLayout nl,
            final NodeLayout nodeLayout
    )
    {
        final YList candidates = new YList();
        final YRectangle box = getLabelBox( nl.getBox(), nodeLayout );
        candidates.add( new NodeLabelCandidate(
                box.getLocation(),
                box,
                PARAMETER,
                nl,
                true ) );
        return candidates;
    }

    /**
     * Creates a model parameter that represents the given node label context best
     * within this model.
     * The created model parameter represents the closest parameter representation
     * of the given oriented label bounds that can be achieved within this model.
     *
     * @param labelBounds The bounds of the label for which a parameter representation is sought.
     * @param nodeLayout  The layout of the node to which the label belongs.
     *
     * @return the default parameter
     */
    public Object createModelParameter(
            final OrientedRectangle labelBounds,
            final NodeLayout nodeLayout
    )
    {
        return PARAMETER;
    }


    /**
     * Computes the size and position of the attributes label.
     *
     * @param labelSize  The size of the label
     * @param nodeLayout The layout of the node
     *
     * @return a <code>YRectangle</code> that defines the area of the label box
     */
    private static YRectangle getLabelBox(
            final YDimension labelSize,
            final NodeLayout nodeLayout
    )
    {
        if ( nodeLayout instanceof NodeRealizer )
        {
            //if ERD style is used, compute the borders of the area under the separator
            final NodeRealizer nr = ( NodeRealizer ) nodeLayout;
            if ( ErdNodePainter.useErdStyle( nr ) )
            {
                final NodeLabel nl = nr.getLabel();
                final double sy = ErdNodePainter.separator( nl );

                final double offsetY = Math.max( MIN_OFFSET_Y, nl.getDistance() );

                final double x = nr.getX();

                final double minX = x + OFFSET_X;
                final double minY = sy + offsetY;

                return new YRectangle(
                        minX,
                        minY,
                        Math.max( labelSize.width, x + nr.getWidth() - OFFSET_X - minX ),
                        Math.max( labelSize.height, nr.getY() + nr.getHeight() - offsetY - minY ) );
            }
        }

        // if no ERD style is used return a default box
        return getDefaultBox( labelSize, nodeLayout );
    }

    /**
     * Computes a default size and position for the label.
     *
     * @param labelSize  the size of the label
     * @param nodeLayout the layout of the node
     *
     * @return a <code>YRectangle</code> that defines the area of the label box
     */
    private static YRectangle getDefaultBox(
            final YDimension labelSize,
            final NodeLayout nodeLayout
    )
    {
        final double w = labelSize.width;
        final double h = labelSize.height;
        return new YRectangle(
                nodeLayout.getX() + ( nodeLayout.getWidth() - w ) * 0.5,
                nodeLayout.getY() + ( nodeLayout.getHeight() - h ) * 0.5,
                w,
                h );
    }

    /**
     * This parameter is used to provide information for a specific label.
     * As the attributes label always fills the whole lower compartment of the
     * entity node, this parameter has no function. However, it is needed to proper
     * serialization/deserialization.
     */
    private static final class ErdAttributesNodeLabelModelParameter
    {
    }


    /**
     * This handler provides a serialization/deserialization functionality for the
     * nodes in ERD style.
     * <p/>
     * It makes sure that the attributes label can be loaded/stored from/to GraphML properly.
     */
    public static final class Handler
            implements SerializationHandler, DeserializationHandler
    {

        /**
         * The GraphML element name for the ERD label model
         */
        private static final String MODEL_NAME = "ErdAttributesNodeLabelModel";

        /**
         * The GraphML element name for the parameter of the ERD label model
         */
        private static final String PARAM_NAME = "ErdAttributesNodeLabelModelParameter";

        /**
         * Handles the deserialization of the ERD attributes label.
         *
         * @param event an event that contains all data that is needed for deserialization.
         *
         * @throws GraphMLParseException
         */
        public void onHandleDeserialization(
                final DeserializationEvent event
        ) throws GraphMLParseException
        {
            final org.w3c.dom.Node node = event.getXmlNode();
            if ( node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE &&
                    NamespaceConstants.YFILES_JAVA_NS.equals( node.getNamespaceURI() ) )
            {
                final String ln = node.getLocalName();
                if ( MODEL_NAME.equals( ln ) )
                {
                    event.setResult( new ErdAttributesNodeLabelModel() );
                }
                else if ( PARAM_NAME.equals( ln ) )
                {
                    event.setResult( PARAMETER );
                }
            }
        }

        /**
         * Handles the serialization of the ERD attributes label.
         *
         * @param event an event that contains all data that is needed for serialization.
         *
         * @throws GraphMLWriteException
         */
        public void onHandleSerialization(
                final SerializationEvent event
        ) throws GraphMLWriteException
        {
            final Object item = event.getItem();
            if ( item instanceof ErdAttributesNodeLabelModel )
            {
                final XmlWriter writer = event.getWriter();
                writer.writeStartElement( MODEL_NAME, NamespaceConstants.YFILES_JAVA_NS );
                writer.writeEndElement();
                event.setHandled( true );
            }
            else if ( item == PARAMETER )
            {
                final XmlWriter writer = event.getWriter();
                writer.writeStartElement( PARAM_NAME, NamespaceConstants.YFILES_JAVA_NS );
                writer.writeEndElement();
                event.setHandled( true );
            }
        }
    }
}