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
package demo.view.flowchart.painters;

import y.view.NodeRealizer;

import java.awt.geom.GeneralPath;

/**
 * This class is an implementation of {@link y.view.GenericNodeRealizer.Painter} that draws
 * the message symbols of flowchart diagrams. There are two types of messages: user messages
 * and network messages.
 */
public class FlowchartMessagePainter extends AbstractFlowchartPainter
{

    /**
     * Boolean which is true, if the painter draws a user message and false if it draws a
     * network message.
     */
    private final boolean isUserMessage;

    /**
     * Creates a new <code>FlowchartMessagePainter</code>
     *
     * @param type <code>true</code> if a user message painter is created.
     *             <code>false</code> if a network message is created.
     */
    public FlowchartMessagePainter( boolean type )
    {
        outline = new GeneralPath();
        isUserMessage = type;
    }

    /**
     * Changes the outline to the shape of the particular message node.
     *
     * @param context The node context
     */
    protected void updateOutline( NodeRealizer context )
    {
        GeneralPath shapePath = ( GeneralPath ) getOutline();
        shapePath.reset();

        double x = context.getX();
        double y = context.getY();
        double height = context.getHeight();
        double width = context.getWidth();
        double borderDistance = Math.min( FLOWCHART_DEFAULT_MESSAGE_INCLINATION * height,
                                          FLOWCHART_DEFAULT_MESSAGE_INCLINATION * width );

        if ( isUserMessage )
        {
            shapePath.moveTo( ( float ) x, ( float ) y );
            shapePath.lineTo( ( float ) ( x + width - borderDistance ), ( float ) y );
            shapePath.lineTo( ( float ) ( x + width ), ( float ) ( y + height * 0.5 ) );
            shapePath.lineTo( ( float ) ( x + width - borderDistance ), ( float ) ( y + height ) );
            shapePath.lineTo( ( float ) x, ( float ) ( y + height ) );
            shapePath.closePath();
        }
        else
        { // is network message
            shapePath.moveTo( ( float ) x, ( float ) y );
            shapePath.lineTo( ( float ) ( x + width ), ( float ) y );
            shapePath.lineTo( ( float ) ( x + width ), ( float ) ( y + height ) );
            shapePath.lineTo( ( float ) x, ( float ) ( y + height ) );
            shapePath.lineTo( ( float ) ( x + borderDistance ), ( float ) ( y + height * 0.5 ) );
            shapePath.closePath();
        }
    }
}
