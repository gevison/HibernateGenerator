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
 * This class is an implementation of {@link y.view.GenericNodeRealizer.Painter} that draws the preparation symbol of flowchart diagrams.
 */
public class FlowchartPreparationPainter extends AbstractFlowchartPainter
{
    public FlowchartPreparationPainter()
    {
        super();
        outline = new GeneralPath();
    }

    protected void updateOutline( NodeRealizer context )
    {
        GeneralPath shapePath = ( GeneralPath ) getOutline();
        shapePath.reset();

        double x = context.getX();
        double y = context.getY();
        double height = context.getHeight();
        double width = context.getWidth();
        double borderDistance = Math.min( FLOWCHART_DEFAULT_PREPARATION_INCLINATION * height,
                                          FLOWCHART_DEFAULT_PREPARATION_INCLINATION * width );

        shapePath.moveTo( ( float ) ( x + borderDistance ), ( float ) y );
        shapePath.lineTo( ( float ) ( x + width - borderDistance ), ( float ) y );
        shapePath.lineTo( ( float ) ( x + width ), ( float ) ( y + height / 2 ) );
        shapePath.lineTo( ( float ) ( x + width - borderDistance ), ( float ) ( y + height ) );
        shapePath.lineTo( ( float ) ( x + borderDistance ), ( float ) ( y + height ) );
        shapePath.lineTo( ( float ) x, ( float ) ( y + height / 2 ) );
        shapePath.closePath();
    }
}
