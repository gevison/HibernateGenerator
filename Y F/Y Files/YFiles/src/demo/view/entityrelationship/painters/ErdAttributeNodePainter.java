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

import demo.view.flowchart.painters.FlowchartStart1Painter;
import y.view.GenericNodeRealizer;
import y.view.LineType;
import y.view.NodeRealizer;

import java.awt.geom.Ellipse2D;

/**
 * This is a painter to display an attribute node for entity relationship diagrams (ERD).
 * <p/>
 * An attribute is represented by an elliptic shape. It is possible to display a weak
 * attribute by drawing a double border.
 */
public class ErdAttributeNodePainter extends FlowchartStart1Painter
{

    /**
     * The border of the elliptic shaped node
     */
    private Ellipse2D ellipse;

    /**
     * Calculates the interior shape for the specified node.
     *
     * @param context The node context
     */
    protected void updateInsideShape( final NodeRealizer context )
    {
        if ( hasDoubleBorder( context ) )
        {
            if ( ellipse == null )
            {
                ellipse = new Ellipse2D.Double();
            }
            innerShape = ellipse;
            updateInsideShapeImpl( context );
        }
        else
        {
            innerShape = null;
        }
    }

    /**
     * Calculates the ellipse for the interior shape.
     *
     * @param context The node context
     */
    private void updateInsideShapeImpl( final NodeRealizer context )
    {
        final Ellipse2D shape = ( Ellipse2D ) getInnerShape();

        final LineType lineType = context.getLineType();
        final float lw = lineType.getLineWidth();

        final double offset = 2 + lw;

        final double x = context.getX();
        final double y = context.getY();
        final double width = context.getWidth();
        final double height = context.getHeight();

        if ( offset + lw < width * 0.5 && offset + lw < height * 0.5 )
        {
            shape.setFrame( x + offset, y + offset, width - 2 * offset, height - 2 * offset );
        }
        else
        {
            shape.setFrame( x, y + height * 0.5, 0, 0 );
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
