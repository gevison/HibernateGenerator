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
package demo.view;

import y.view.EditMode;
import y.view.Graph2DView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

/**
 * The yFiles view says "Hello World."
 * <br>
 * Demonstrates basic usage of {@link y.view.Graph2DView}, the yFiles graph
 * viewer component, and shows how to provide editing support through
 * {@link y.view.EditMode}.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/mvc_view.html">Section View Implementations</a> in the yFiles for Java Developer's Guide
 */
public class SimpleDemo extends JPanel
{
    Graph2DView view;

    public SimpleDemo()
    {
        setLayout( new BorderLayout() );
        view = new Graph2DView();
        EditMode mode = new EditMode();
        view.addViewMode( mode );
        add( view );
    }

    public void start()
    {
        JFrame frame = new JFrame( getClass().getName() );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        addContentTo( frame.getRootPane() );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }

    public final void addContentTo( final JRootPane rootPane )
    {
        rootPane.setContentPane( this );
    }

    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                ( new SimpleDemo() ).start();
            }
        } );
    }
}


      
