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
package demo.view.rendering;

import demo.view.DemoBase;
import y.base.Edge;
import y.base.Node;
import y.geom.YPoint;
import y.view.BridgeCalculator;
import y.view.DefaultGraph2DRenderer;
import y.view.Graph2D;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * This class demonstrates how to utilize {@link BridgeCalculator} to draw bridges/gaps for crossing edges.
 * It demonstrates how {@link y.view.PolyLineEdgeRealizer} automatically makes use of
 * the {@link BridgeCalculator} registered with the default {@link y.view.Graph2DRenderer} instance to
 * incorporate the calculation of bridges.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/mvc_view.html#renderer">Section View Implementations</a> in the yFiles for Java Developer's Guide
 */
public class BridgeDemo extends DemoBase
{
    BridgeCalculator bridgeCalculator;

    public BridgeDemo()
    {
        // create the BridgeCalculator
        bridgeCalculator = new BridgeCalculator();

        // register it with the DefaultGraph2DRenderer
        ( ( DefaultGraph2DRenderer ) view.getGraph2DRenderer() ).setBridgeCalculator( bridgeCalculator );

        // create a nice graph that shows all possible configurations...
        final Graph2D graph = view.getGraph2D();
        Node[] nodes = new Node[ 16 ];

        int count = 0;
        double hDist = graph.getDefaultNodeRealizer().getWidth() + 10;
        double vDist = graph.getDefaultNodeRealizer().getHeight() + 10;

        for ( int i = 1; i < 5; i++ )
        {
            nodes[ count++ ] = graph.createNode( 50 + hDist * i, vDist * 5 + 50 );
            nodes[ count++ ] = graph.createNode( 50 + hDist * i, 40 );
            nodes[ count++ ] = graph.createNode( 40, 50 + vDist * i );
            nodes[ count++ ] = graph.createNode( 50 + hDist * 5, 50 + vDist * i );
        }

        graph.createEdge( nodes[ 0 ], nodes[ 1 ] );

        Edge e = graph.createEdge( nodes[ 0 ], nodes[ 1 ] );
        graph.setSourcePointRel( e, new YPoint( 5, 0 ) );
        graph.setTargetPointRel( e, new YPoint( 5, 0 ) );

        graph.createEdge( nodes[ 5 ], nodes[ 4 ] );

        graph.createEdge( nodes[ 2 ], nodes[ 3 ] );
        e = graph.createEdge( nodes[ 2 ], nodes[ 3 ] );
        graph.setSourcePointRel( e, new YPoint( 0, 5 ) );
        graph.setTargetPointRel( e, new YPoint( 0, 5 ) );

        graph.createEdge( nodes[ 7 ], nodes[ 6 ] );

        graph.createEdge( nodes[ 2 + 8 ], nodes[ 3 + 8 ] );
        graph.createEdge( nodes[ 7 + 8 ], nodes[ 6 + 8 ] );

        graph.createEdge( nodes[ 0 + 8 ], nodes[ 1 + 8 ] );
        graph.createEdge( nodes[ 5 + 8 ], nodes[ 4 + 8 ] );
    }

    protected JToolBar createToolBar()
    {
        final JComboBox crossingModeCB = new JComboBox(
                new Object[]{ "Horizontal Crosses Vertical", "Vertical Crosses Horizontal",
                              "Induced by Edge Ordering" } );
        crossingModeCB.setMaximumSize( crossingModeCB.getPreferredSize() );
        crossingModeCB.setSelectedIndex( 0 );
        crossingModeCB.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                bridgeCalculator.setCrossingMode( getCrossingModeForIndex( crossingModeCB.getSelectedIndex() ) );
                view.getGraph2D().updateViews();
            }
        } );
        final JComboBox crossingStyleCB = new JComboBox(
                new Object[]{ "Gap", "Arc", "Square", "Two Sides", "Scaled Arc", "Scaled Square",
                              "Scaled Two Sides" } );
        crossingStyleCB.setMaximumSize( crossingStyleCB.getPreferredSize() );
        crossingStyleCB.setSelectedIndex( 1 );
        crossingStyleCB.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                bridgeCalculator.setCrossingStyle( ( short ) crossingStyleCB.getSelectedIndex() );
                view.getGraph2D().updateViews();
            }
        } );
        final JComboBox orientationStyleCB = new JComboBox(
                new Object[]{ "Up", "Down", "Left", "Right", "Positive", "Negative", "Flow Left", "Flow Right" } );
        orientationStyleCB.setMaximumSize( orientationStyleCB.getPreferredSize() );
        orientationStyleCB.setSelectedIndex( 4 );
        orientationStyleCB.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                bridgeCalculator.setOrientationStyle( ( short ) ( orientationStyleCB.getSelectedIndex() + 1 ) );
                view.getGraph2D().updateViews();
            }
        } );

        final JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( new JLabel( "Bridge Style: " ) );
        toolBar.add( crossingStyleCB );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( orientationStyleCB );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( crossingModeCB );
        return toolBar;
    }

    private static short getCrossingModeForIndex( int index )
    {
        switch ( index )
        {
            case 0:
            default:
                return BridgeCalculator.CROSSING_MODE_HORIZONTAL_CROSSES_VERTICAL;
            case 1:
                return BridgeCalculator.CROSSING_MODE_VERTICAL_CROSSES_HORIZONTAL;
            case 2:
                return BridgeCalculator.CROSSING_MODE_ORDER_INDUCED;
        }
    }

    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                new BridgeDemo().start( "Bridge Demo" );
            }
        } );
    }
}
