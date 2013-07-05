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

import y.view.AbstractCustomNodePainter;
import y.view.GenericNodeRealizer;
import y.view.NodeRealizer;
import y.view.YRenderingHints;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 * Abstract painter class for flowchart symbols.
 */
public abstract class AbstractFlowchartPainter extends AbstractCustomNodePainter implements
                                                                                 GenericNodeRealizer.ContainsTest,
                                                                                 FlowchartRealizerConstants
{
    protected Shape outline;

    protected Shape innerShape;


    /**
     * Paints outline and interior of a flowchart symbol.
     *
     * @param context  the context node
     * @param graphics the graphics context to use
     * @param sloppy   whether to draw the node sloppily
     */
    protected void paintNode( NodeRealizer context, Graphics2D graphics, boolean sloppy )
    {
        updateOutline( context );
        updateInsideShape( context );

        boolean useSelectionStyle = useSelectionStyle( context, graphics );
        graphics.setStroke( getLineStroke( context, useSelectionStyle ) );

        Color fillColor1 = getFillColor( context, useSelectionStyle );
        Color fillColor2 = getFillColor2( context, useSelectionStyle );
        Color lineColor = getLineColor( context, useSelectionStyle );

        if ( fillColor1 != null )
        {
            if ( fillColor2 != null && useGradientStyle( graphics ) )
            {
                double x = context.getX();
                double y = context.getY();
                double width = context.getWidth();
                double heigh = context.getHeight();
                GradientPaint gp =
                        new GradientPaint( ( float ) x, ( float ) y, fillColor1, ( float ) x + ( float ) width,
                                           ( float ) y + ( float ) heigh, fillColor2 );
                graphics.setPaint( gp );
            }
            else
            {
                graphics.setColor( fillColor1 );
            }
            if ( outline != null )
            {
                graphics.fill( outline );
            }
        }
        if ( lineColor != null )
        {
            graphics.setColor( lineColor );
            if ( outline != null )
            {
                graphics.draw( outline );
            }
            if ( innerShape != null )
            {
                graphics.draw( innerShape );
            }
        }
    }

    /**
     * Returns the outline
     *
     * @return The outline shape
     */
    Shape getOutline()
    {
        return outline;
    }

    /**
     * Returns the current inner shape
     *
     * @return The inner shape
     */
    public Shape getInnerShape()
    {
        return innerShape;
    }

    /**
     * Calculates the outline shape for the specified node.
     *
     * @param context The node context
     */
    protected abstract void updateOutline( NodeRealizer context );

    /**
     * Calculates the interior shape for the specified node.
     *
     * @param context The node context
     */
    protected void updateInsideShape( NodeRealizer context )
    {
    }

    public boolean contains( NodeRealizer context, double x, double y )
    {
        updateOutline( context );
        return outline != null && outline.contains( x, y );
    }


    static boolean useGradientStyle( final Graphics2D graphics )
    {
        return YRenderingHints.isGradientPaintingEnabled( graphics );
    }

    static boolean useSelectionStyle(
            final NodeRealizer context,
            final Graphics2D gfx
    )
    {
        return context.isSelected() && YRenderingHints.isSelectionPaintingEnabled( gfx );
    }
}
