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

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * This class is an implementation of {@link y.view.GenericNodeRealizer.Painter} that draws the sequential data symbol of flowchart diagrams.
 */
public class FlowchartSequentialDataPainter extends AbstractFlowchartPainter
{
    public FlowchartSequentialDataPainter()
    {
        super();
        outline = new Ellipse2D.Double();
        innerShape = new Line2D.Double();
    }

    protected void updateOutline( NodeRealizer context )
    {
        Ellipse2D shape = ( Ellipse2D ) getOutline();
        double height = context.getHeight();
        double width = context.getWidth();
        double x = context.getX();
        double y = context.getY();
        double diameter = Math.min( height, width );
        double borderDistanceX = Math.max( ( width - diameter ) / 2, 0 );
        double borderDistanceY = Math.max( ( height - diameter ) / 2, 0 );
        shape.setFrame( x + borderDistanceX, y + borderDistanceY, diameter, diameter );
    }

    public void updateInsideShape( NodeRealizer context )
    {
        Line2D shape = ( Line2D ) getInnerShape();
        double height = context.getHeight();
        double width = context.getWidth();
        double x = context.getX();
        double y = context.getY();
        double diameter = Math.min( height, width );
        double borderDistanceX = Math.max( ( width - diameter ) / 2, 0 );
        double borderDistanceY = Math.max( ( height - diameter ) / 2, 0 );
        shape.setLine( x + borderDistanceX + diameter / 2, y + borderDistanceY + diameter, x + width - borderDistanceX,
                       y + borderDistanceY + diameter );
    }
}