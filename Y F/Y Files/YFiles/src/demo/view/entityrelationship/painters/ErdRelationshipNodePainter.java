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

import demo.view.flowchart.painters.FlowchartDecisionPainter;
import y.view.GenericNodeRealizer;
import y.view.LineType;
import y.view.NodeRealizer;

import java.awt.geom.GeneralPath;

/**
 * This is a painter to display a relationship node for entity relationship diagrams (ERD).
 * <p/>
 * A relationship is represented by a diamond shape. It is possible to display a weak
 * relationship by drawing a double border.
 */
public class ErdRelationshipNodePainter extends FlowchartDecisionPainter
{
    private static final double EPSILON = 1.0e-12;

    /**
     * The border of the diamond shaped node
     */
    private GeneralPath path;

    /**
     * Calculates the interior shape for the specified node.
     *
     * @param context The node context
     */
    protected void updateInsideShape( final NodeRealizer context )
    {
        if ( hasDoubleBorder( context ) )
        {
            if ( path == null )
            {
                path = new GeneralPath();
            }
            innerShape = path;
            updateInsideShapeImpl( context );
        }
        else
        {
            if ( path != null )
            {
                path.reset();
            }
            innerShape = null;
        }
    }

    /**
     * Calculates the path of the border segments for the interior shape.
     *
     * @param context The node context
     */
    private void updateInsideShapeImpl( final NodeRealizer context )
    {
        final GeneralPath shapePath = ( GeneralPath ) getInnerShape();
        shapePath.reset();

        final double width = context.getWidth();
        final double height = context.getHeight();
        if ( Math.abs( width ) < EPSILON || Math.abs( height ) < EPSILON )
        {
            return;
        }

        final double x = context.getX();
        final double y = context.getY();

        final LineType lineType = context.getLineType();
        final float lw = lineType.getLineWidth();

        final double offset = 2 + lw;

        final double w2 = width * 0.5;
        final double h2 = height * 0.5;

        // slope vector  s = (w / 2,   h / 2)
        // normal vector n = (h / 2, - w / 2)
        // length of normal vector
        final double nl = Math.sqrt( w2 * w2 + h2 * h2 );

        // origin of line parallel to 0 + t*s with distance offset
        final double ox = offset * h2 / nl;
        final double oy = -offset * w2 / nl;

        // intersection of line o + t*s with y == 0
        final double ix = ox + ( -oy / h2 ) * w2;
        // intersection of line o + t*s with x == w / 2
        final double iy = oy + ( 1 - ox / w2 ) * h2;

        final double offsetX = ix;
        final double offsetY = h2 - iy;

        if ( offsetX + lw < w2 && offsetY + lw < h2 )
        {
            shapePath.moveTo( ( float ) ( x + w2 ), ( float ) ( y + offsetY ) );
            shapePath.lineTo( ( float ) ( x + width - offsetX ), ( float ) ( y + h2 ) );
            shapePath.lineTo( ( float ) ( x + w2 ), ( float ) ( y + height - offsetY ) );
            shapePath.lineTo( ( float ) ( x + offsetX ), ( float ) ( y + h2 ) );
            shapePath.closePath();
        }
    }

    /**
     * Tests if the style property {@link ErdRealizerFactory#DOUBLE_BORDER} is set for the context realizer.
     *
     * @param context The context node
     *
     * @return <code>true</code>, if style property border is set, <code>false</code> otherwise
     */
    private boolean hasDoubleBorder( NodeRealizer context )
    {
        return Boolean.TRUE.equals(
                ( ( GenericNodeRealizer ) context ).getStyleProperty( ErdRealizerFactory.DOUBLE_BORDER ) );
    }

}
