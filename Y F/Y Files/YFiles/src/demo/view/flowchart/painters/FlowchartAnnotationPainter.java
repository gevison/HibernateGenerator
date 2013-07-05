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

import y.base.Node;
import y.view.AbstractCustomNodePainter;
import y.view.GenericNodeRealizer;
import y.view.Graph2D;
import y.view.NodeRealizer;
import y.view.YRenderingHints;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * This class is an implementation of {@link GenericNodeRealizer.Painter} that draws the annotation symbol of flowchart diagrams.
 */
public class FlowchartAnnotationPainter extends AbstractCustomNodePainter implements GenericNodeRealizer.ContainsTest,
                                                                                     FlowchartRealizerConstants
{
    private GeneralPath shape;

    private Rectangle2D outline;


    public FlowchartAnnotationPainter()
    {
        outline = new Rectangle2D.Double();
        shape = new GeneralPath();
    }

    /**
     * Paints a flowchart annotation symbol.
     *
     * @param context  the context node
     * @param graphics the graphics context to use
     * @param sloppy   whether to draw the node sloppily
     */
    protected void paintNode( NodeRealizer context, Graphics2D graphics, boolean sloppy )
    {
        updateShape( context );
        updateOutline( context );

        final boolean useSelectionStyle = useSelectionStyle( context, graphics );
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
                double height = context.getHeight();
                GradientPaint gp =
                        new GradientPaint( ( float ) x, ( float ) y, fillColor1, ( float ) x + ( float ) width,
                                           ( float ) y + ( float ) height, fillColor2 );
                graphics.setPaint( gp );
            }
            else
            {
                graphics.setColor( fillColor1 );
            }
            graphics.fill( outline );

            if ( lineColor != null )
            {
                graphics.setColor( lineColor );
                graphics.setStroke( new BasicStroke( 2 ) );
                graphics.draw( shape );
            }
        }
    }

    static boolean useGradientStyle( final Graphics2D graphics )
    {
        return YRenderingHints.isGradientPaintingEnabled( graphics );
    }

    static boolean useSelectionStyle( final NodeRealizer context, final Graphics2D gfx )
    {
        return context.isSelected() && YRenderingHints.isSelectionPaintingEnabled( gfx );
    }

    /**
     * Calculates the annotation outline for the specified node.
     *
     * @param context The node context
     */
    protected void updateOutline( NodeRealizer context )
    {
        double x = context.getX();
        double y = context.getY();
        double width = context.getWidth();
        double height = context.getHeight();
        outline.setFrame( x, y, width, height );
    }

    /**
     * Calculates the annotation interior for the specified node.
     *
     * @param context The node context
     */
    protected void updateShape( NodeRealizer context )
    {
        shape.reset();

        byte orientation;
        GenericNodeRealizer gnr = ( GenericNodeRealizer ) context;
        if ( gnr.getStyleProperty( PROPERTY_ORIENTATION ) != null )
        {
            orientation = ( ( Byte ) gnr.getStyleProperty( PROPERTY_ORIENTATION ) ).byteValue();
        }
        else
        {
            orientation = PROPERTY_ORIENTATION_VALUE_LEFT;
        }
        if ( orientation == PROPERTY_ORIENTATION_VALUE_AUTO )
        {
            orientation = determineBracketOrientation( context );
        }
        switch ( orientation )
        {
            case PROPERTY_ORIENTATION_VALUE_DOWN:
            {
                createDownBracket( gnr.getX(), gnr.getY(), gnr.getWidth(), gnr.getHeight() );
                break;
            }
            case PROPERTY_ORIENTATION_VALUE_RIGHT:
            {
                createRightBracket( gnr.getX(), gnr.getY(), gnr.getWidth(), gnr.getHeight() );
                break;
            }
            case PROPERTY_ORIENTATION_VALUE_TOP:
            {
                createTopBracket( gnr.getX(), gnr.getY(), gnr.getWidth(), gnr.getHeight() );
                break;
            }
            case PROPERTY_ORIENTATION_VALUE_LEFT:
            {
                createLeftBracket( gnr.getX(), gnr.getY(), gnr.getWidth(), gnr.getHeight() );
                break;
            }
            default:
            {
                createLeftBracket( gnr.getX(), gnr.getY(), gnr.getWidth(), gnr.getHeight() );
            }
        }
    }

    private void createLeftBracket( double x, double y, double width, double height )
    {
        shape.moveTo( ( float ) ( x + 0.125 * width ), ( float ) y );
        shape.lineTo( ( float ) x, ( float ) y );
        shape.lineTo( ( float ) x, ( float ) ( y + height ) );
        shape.lineTo( ( float ) ( x + 0.125 * width ), ( float ) ( y + height ) );
    }

    private void createRightBracket( double x, double y, double width, double height )
    {
        shape.moveTo( ( float ) ( x + 0.875 * width ), ( float ) y );
        shape.lineTo( ( float ) ( x + width ), ( float ) y );
        shape.lineTo( ( float ) ( x + width ), ( float ) ( y + height ) );
        shape.lineTo( ( float ) ( x + 0.875 * width ), ( float ) ( y + height ) );
    }

    private void createTopBracket( double x, double y, double width, double height )
    {
        shape.moveTo( ( float ) x, ( float ) ( y + 0.125 * height ) );
        shape.lineTo( ( float ) x, ( float ) y );
        shape.lineTo( ( float ) ( x + width ), ( float ) y );
        shape.lineTo( ( float ) ( x + width ), ( float ) ( y + 0.125 * height ) );
    }

    private void createDownBracket( double x, double y, double width, double height )
    {
        shape.moveTo( ( float ) x, ( float ) ( y + 0.875 * height ) );
        shape.lineTo( ( float ) x, ( float ) ( y + height ) );
        shape.lineTo( ( float ) ( x + width ), ( float ) ( y + height ) );
        shape.lineTo( ( float ) ( x + width ), ( float ) ( y + 0.875 * height ) );
    }

    /**
     * Returns a constant representing the orientation/placement of the
     * annotation's bracket. One of
     * <ul>
     * <li>{@link #PROPERTY_ORIENTATION_VALUE_DOWN}</li>
     * <li>{@link #PROPERTY_ORIENTATION_VALUE_RIGHT}</li>
     * <li>{@link #PROPERTY_ORIENTATION_VALUE_TOP}</li>
     * <li>{@link #PROPERTY_ORIENTATION_VALUE_LEFT}</li>
     * </ul>
     *
     * @param context the context node
     *
     * @return one of {@link #PROPERTY_ORIENTATION_VALUE_DOWN},
     *         {@link #PROPERTY_ORIENTATION_VALUE_RIGHT},
     *         {@link #PROPERTY_ORIENTATION_VALUE_TOP}, and
     *         {@link #PROPERTY_ORIENTATION_VALUE_LEFT}.
     */
    private byte determineBracketOrientation( NodeRealizer context )
    {
        final Node node = context.getNode();
        if ( node != null && node.degree() == 1 )
        {
            final Graph2D graph = ( Graph2D ) node.getGraph();

            final Point2D intersection =
                    node.inDegree() == 1
                    ? graph.getRealizer( node.firstInEdge() ).getTargetIntersection()
                    : graph.getRealizer( node.firstOutEdge() ).getSourceIntersection();

            final double x = intersection.getX();
            final double y = intersection.getY();

            final double epsilon = 0.1;

            final double minX = context.getX();
            if ( ( x + epsilon ) > minX && ( x - epsilon ) < minX )
            {
                return PROPERTY_ORIENTATION_VALUE_LEFT;
            }
            else
            {
                final double maxX = minX + context.getWidth();
                if ( ( ( x + epsilon ) > maxX && ( ( x - epsilon ) < maxX ) ) )
                {
                    return PROPERTY_ORIENTATION_VALUE_RIGHT;
                }
                else
                {
                    final double minY = context.getY();
                    if ( ( y + epsilon ) > minY && ( y - epsilon ) < minY )
                    {
                        return PROPERTY_ORIENTATION_VALUE_TOP;
                    }
                    else
                    {
                        final double maxY = minY + context.getHeight();
                        if ( ( ( y + epsilon ) > maxY && ( ( y - epsilon ) < maxY ) ) )
                        {
                            return PROPERTY_ORIENTATION_VALUE_DOWN;
                        }
                    }
                }
            }
        }

        return PROPERTY_ORIENTATION_VALUE_LEFT;
    }

    public boolean contains( NodeRealizer context, double x, double y )
    {
        updateOutline( context );
        return outline != null && outline.contains( x, y );
    }
}
