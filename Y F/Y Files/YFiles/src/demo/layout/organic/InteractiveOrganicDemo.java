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
package demo.layout.organic;

import demo.view.DemoBase;
import y.layout.CopiedLayoutGraph;
import y.layout.LayoutTool;
import y.layout.organic.InteractiveOrganicLayouter;
import y.view.EditMode;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

/**
 * This demo shows the very basic usage of the
 * {@link y.layout.organic.InteractiveOrganicLayouter}.
 * The layouter is started within a thread. A swing timer is used to update the
 * positions of the nodes.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/interactive_organic_layouter.html#interactive_organic_layouter">Section Interactive Organic Layout</a> in the yFiles for Java Developer's Guide
 */
public class InteractiveOrganicDemo extends DemoBase
{
    private InteractiveOrganicLayouter layouter;

    protected void initialize()
    {
        layouter = new InteractiveOrganicLayouter();
        layouter.setAutomaticStructureUpdateEnabled( true );
        layouter.enableOnlyCore();

        loadGraph( "resource/peopleNav.graphml" );
        //Reset the paths and the locations of the nodes.
        LayoutTool.initDiagram( view.getGraph2D() );

        view.setPaintDetailThreshold( 0.0 );
        view.fitContent();

    }

    /**
     * Callback used by {@link #registerViewModes()} to create the default EditMode
     *
     * @return an instance of {@link y.view.EditMode} with showNodeTips enabled
     */
    protected EditMode createEditMode()
    {
        EditMode editMode = super.createEditMode();
        editMode.allowBendCreation( false );
        editMode.allowNodeCreation( true );
        editMode.allowResizeNodes( false );
        editMode.allowEdgeCreation( true );

        //This view mode offers support for "touching the graph"
        editMode.setMoveSelectionMode( new InteractiveMoveSelectionMode( layouter ) );

        return editMode;
    }

    protected JToolBar createToolBar()
    {
        final JButton startButton = new JButton( "Start", SHARED_LAYOUT_ICON );
        startButton.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                //Disable the button
                startButton.setEnabled( false );

                //Start the layout thread
                layouter.startLayout( new CopiedLayoutGraph( view.getGraph2D() ) );

                //Update timer
                Timer timer = new Timer( 21, new ActionListener()
                {
                    //This listener is notified about 24 times a second.
                    public void actionPerformed( ActionEvent e )
                    {
                        //Write the calculated positions back to the realizers
                        if ( layouter.commitPositionsSmoothly( 50, 0.15 ) > 0 )
                        {
                            //... and update the view, if something has changed
                            view.updateView();
                        }
                    }
                } );
                timer.setInitialDelay( 500 );
                timer.start();
            }
        } );

        JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( startButton );
        return toolBar;
    }


    public static void main( String[] args ) throws IOException
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                InteractiveOrganicDemo demo = new InteractiveOrganicDemo();
                demo.start();
            }
        } );

    }
}