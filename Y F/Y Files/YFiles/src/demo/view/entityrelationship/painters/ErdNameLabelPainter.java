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

import y.geom.OrientedRectangle;
import y.view.AbstractCustomLabelPainter;
import y.view.NodeLabel;
import y.view.YLabel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.Map;

/**
 * This painter draws the name label of a detailed entity of an entity relationship diagram (ERD).
 * <p/>
 * The painter does not draw an opaque background as the <code>ErdNodePainter</code> already paints all
 * background colors.
 *
 * @see ErdNodePainter
 */
public class ErdNameLabelPainter implements YLabel.Painter
{
    private final YLabel.Painter painter;

    private final YLabel.Painter boxPainter;

    /**
     * Creates a new <code>ErdNameLabelPainter</code>
     */
    public ErdNameLabelPainter()
    {
        this( defaultPainter() );
    }

    /**
     * Creates a new <code>ErdNameLabelPainter</code> that uses the given label painter.
     *
     * @param painter the label painter
     */
    public ErdNameLabelPainter( final YLabel.Painter painter )
    {
        this.painter = painter;
        this.boxPainter = new BoxPainter();
    }

    /**
     * Paints the label.
     *
     * @param label the label context
     * @param gfx   the grapics object
     */
    public void paint( final YLabel label, final Graphics2D gfx )
    {
        if ( painter != null )
        {
            painter.paint( label, gfx );
        }
    }

    /**
     * Paints the label content to the specified location. In this case it is a string with contains the name of the entity.
     *
     * @param label  the label context
     * @param gfx    the graphics object
     * @param x      the x-coordinate of the label
     * @param y      the y-coordinate of the label
     * @param width  the width of the label
     * @param height the height of the label
     */
    public void paintContent(
            final YLabel label,
            final Graphics2D gfx,
            final double x,
            final double y,
            final double width,
            final double height
    )
    {
        if ( painter != null )
        {
            painter.paintContent( label, gfx, x, y, width, height );
        }
    }

    /**
     * Paints the background of the label.
     *
     * @param label  the label context
     * @param gfx    the graphics object
     * @param x      the x-coordinate of the label
     * @param y      the y-coordinate of the label
     * @param width  the width of the label
     * @param height the height of the label
     */
    public void paintBox(
            final YLabel label,
            final Graphics2D gfx,
            final double x,
            final double y,
            final double width,
            final double height
    )
    {
        // paint the box only if the *main* painter is not null
        if ( painter != null )
        {
            // the <code>BoxPainter</code> will only draw a label background if
            // the label is selected
            boxPainter.paintBox( label, gfx, x, y, width, height );
        }
    }

    /**
     * Returns the text box of a given label
     *
     * @param label the label context
     *
     * @return a rectangle with the size and position of the text box
     */
    public OrientedRectangle getTextBox( final YLabel label )
    {
        if ( painter != null )
        {
            return painter.getTextBox( label );
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the icon box of a given label
     *
     * @param label the label context
     *
     * @return a rectangle with the size and position of the icon box
     */
    public OrientedRectangle getIconBox( final YLabel label )
    {
        if ( painter != null )
        {
            return painter.getIconBox( label );
        }
        else
        {
            return null;
        }
    }


    /**
     * Returns the default painter if it exists.
     *
     * @return the default painter or null if it does not exist
     */
    static YLabel.Painter defaultPainter()
    {
        final YLabel.Factory factory = NodeLabel.getFactory();
        final Map c = factory.createDefaultConfigurationMap();
        final Object painter = c.get( YLabel.Painter.class );
        if ( painter instanceof YLabel.Painter )
        {
            return ( YLabel.Painter ) painter;
        }
        else
        {
            return null;
        }
    }


    /**
     * This painter draws the label box only if the label is selected. No background color is drawn
     * because the <code>ErdNodePainter</code> already draws all background colors.
     *
     * @see ErdNodePainter
     */
    private static final class BoxPainter extends AbstractCustomLabelPainter
    {

        /**
         * Paints a selection box, if the label is selected by the user.
         *
         * @param label    the label to paint.
         * @param graphics the graphics context to paint upon.
         * @param x        the x-coordinate of the upper left corner of the label's
         *                 background rectangle.
         * @param y        the y-coordinate of the upper left corner of the label's
         *                 background rectangle.
         * @param width    the width of the label's background rectangle.
         * @param height   the height of the label's background rectangle.
         */
        public void paintBox(
                final YLabel label,
                final Graphics2D graphics,
                final double x,
                final double y,
                final double width,
                final double height
        )
        {
            final Color oldColor = graphics.getColor();
            final Paint oldPaint = graphics.getPaint();
            final Stroke oldStroke = graphics.getStroke();

            paintSelectionBox( label, graphics, x, y, width, height );

            graphics.setStroke( oldStroke );
            graphics.setPaint( oldPaint );
            graphics.setColor( oldColor );
        }

        /**
         * Does nothing
         */
        public void paintContent(
                final YLabel label,
                final Graphics2D gfx,
                final double x,
                final double y,
                final double width,
                final double height
        )
        {
        }

        /**
         * Does nothing
         */
        public OrientedRectangle getTextBox( final YLabel label )
        {
            return null;
        }

        /**
         * Does nothing
         */
        public OrientedRectangle getIconBox( final YLabel label )
        {
            return null;
        }
    }

}
