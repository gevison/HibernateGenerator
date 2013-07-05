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

import y.view.GenericNodeRealizer;
import y.view.NodeRealizer;

import java.awt.geom.GeneralPath;

/**
 * This class is an implementation of {@link y.view.GenericNodeRealizer.Painter} that draws the delay symbol of flowchart diagrams.
 */
public class FlowchartDelayPainter extends AbstractFlowchartPainter
{


    public FlowchartDelayPainter()
    {
        super();
        outline = new GeneralPath();
    }

    protected void updateOutline( NodeRealizer context )
    {
        GeneralPath shapePath = ( GeneralPath ) getOutline();
        shapePath.reset();
        double height = context.getHeight();
        double x = context.getX();
        double y = context.getY();
        double width = context.getWidth();
        GenericNodeRealizer cast_gnr = ( GenericNodeRealizer ) context;
        double capRadiusHighDependency;
        if ( cast_gnr.getStyleProperty( PROPERTY_RADIUS ) != null )
        {
            capRadiusHighDependency = ( ( Double ) cast_gnr.getStyleProperty( PROPERTY_RADIUS ) ).doubleValue();
            capRadiusHighDependency = Math.min( capRadiusHighDependency, 0.5 );
        }
        else
        {
            capRadiusHighDependency = FLOWCHART_DEFAULT_DELAY_RADIUS;
        }
        double borderDistance = capRadiusHighDependency * Math.min( width, height );

        shapePath.moveTo( ( float ) x, ( float ) y );
        shapePath.lineTo( ( float ) ( x + width - borderDistance ), ( float ) y );
        shapePath.quadTo( ( float ) ( x + width + borderDistance ), ( float ) ( y + height / 2 ),
                          ( float ) ( x + width - borderDistance ), ( float ) ( y + height ) );
        shapePath.lineTo( ( float ) x, ( float ) ( y + height ) );
        shapePath.closePath();
    }
}
