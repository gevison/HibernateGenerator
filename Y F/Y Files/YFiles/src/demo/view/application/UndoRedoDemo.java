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
package demo.view.application;

import demo.view.DemoBase;
import y.view.Graph2DUndoManager;

import javax.swing.Action;
import javax.swing.JToolBar;
import java.awt.EventQueue;
import java.util.Locale;

/**
 * This Demo shows how to use Undo/Redo functionality built into yFiles.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/advanced_stuff.html#undo_redo">Section Undo/Redo</a> in the yFiles for Java Developer's Guide
 */
public class UndoRedoDemo extends DemoBase
{
    private Graph2DUndoManager undoManager;

    /**
     * Returns the undo manager for this application. Also, if not already done - it creates
     * and configures it.
     */
    protected Graph2DUndoManager getUndoManager()
    {
        if ( undoManager == null )
        {
            //create one and make it listen to graph structure changes
            undoManager = new Graph2DUndoManager( view.getGraph2D() );

            //assign the graph view as view container so we get view updates
            //after undo/redo actions have been performed.
            undoManager.setViewContainer( view );
        }
        return undoManager;
    }


    public JToolBar createToolBar()
    {
        JToolBar bar = super.createToolBar();

        bar.addSeparator();

        //add undo action to toolbar
        Action action = getUndoManager().getUndoAction();
        action.putValue( Action.SMALL_ICON, getIconResource( "resource/undo.png" ) );
        action.putValue( Action.SHORT_DESCRIPTION, "Undo" );
        bar.add( action );

        //add redo action to toolbar
        action = getUndoManager().getRedoAction();
        action.putValue( Action.SMALL_ICON, getIconResource( "resource/redo.png" ) );
        action.putValue( Action.SHORT_DESCRIPTION, "Redo" );
        bar.add( action );
        return bar;
    }

    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                ( new UndoRedoDemo() ).start( "Undo/Redo Demo" );
            }
        } );
    }

}

    

      
