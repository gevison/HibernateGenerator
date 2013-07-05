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
package demo.layout.module;

import demo.view.DemoBase;
import y.module.CircularLayoutModule;
import y.module.IncrementalHierarchicLayoutModule;
import y.module.LayoutModule;
import y.module.OrthogonalLayoutModule;
import y.module.RandomLayoutModule;
import y.module.SmartOrganicLayoutModule;
import y.module.TreeLayoutModule;
import y.module.YModule;
import y.option.OptionHandler;
import y.view.Arrow;
import y.view.hierarchy.HierarchyManager;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Demonstrates how layout modules can be added to the GUI of an application.
 * A layout module is a layout algorithm combined
 * with an option dialog, that allows to change the
 * options of a layout algorithm interactively
 * (only available if layout is part of distribution).
 */
public class LayoutModuleDemo extends DemoBase
{
    public LayoutModuleDemo()
    {
        //use a delta arrow to make edge directions clear
        view.getGraph2D().getDefaultEdgeRealizer().setArrow( Arrow.DELTA );

        //to enable loading of hierarchical grouped graphs
        new HierarchyManager( view.getGraph2D() );
        loadGraph( "resource/sample.graphml" );
    }

    /**
     * Creates a toolbar for choosing, configuring, and running layout algorithms.
     */
    protected JToolBar createToolBar()
    {
        final JComboBox modules = new JComboBox();
        modules.setRenderer( new YModuleListCellRenderer() );
        modules.addItem( new IncrementalHierarchicLayoutModule() );
        modules.addItem( new SmartOrganicLayoutModule() );
        modules.addItem( new OrthogonalLayoutModule() );
        modules.addItem( new CircularLayoutModule() );
        modules.addItem( new DiagonalLayoutModule() );
        modules.addItem( new RandomLayoutModule() );
        modules.setSelectedIndex( 1 );
        modules.setMaximumSize( modules.getPreferredSize() );
        modules.addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                final LayoutModule module = ( LayoutModule ) modules.getSelectedItem();
                if ( module != null )
                {
                    module.start( view.getGraph2D() );
                }
            }
        } );

        final Action propertiesAction = new AbstractAction(
                "Settings...", getIconResource( "resource/properties.png" ) )
        {
            public void actionPerformed( final ActionEvent e )
            {
                final LayoutModule module = ( LayoutModule ) modules.getSelectedItem();
                if ( module != null )
                {
                    final OptionHandler settings = module.getOptionHandler();
                    if ( settings != null )
                    {
                        OptionSupport.showDialog( module, view.getGraph2D(), false, view.getFrame() );
                    }
                }
            }
        };

        final Action layoutAction = new AbstractAction(
                "Layout", SHARED_LAYOUT_ICON )
        {
            public void actionPerformed( final ActionEvent e )
            {
                final LayoutModule module = ( LayoutModule ) modules.getSelectedItem();
                if ( module != null )
                {
                    module.start( view.getGraph2D() );
                }
            }
        };

        final JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( createActionControl( layoutAction ) );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( modules );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( createActionControl( propertiesAction ) );

        return toolBar;
    }

    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                new LayoutModuleDemo().start();
            }
        } );
    }

    static class YModuleListCellRenderer implements ListCellRenderer
    {
        final DefaultListCellRenderer dlcr = new DefaultListCellRenderer();

        public Component getListCellRendererComponent(
                final JList list,
                final Object value,
                final int index,
                final boolean isSelected,
                final boolean cellHasFocus
        )
        {
            if ( value instanceof LayoutModule )
            {
                final String name;
                if ( value instanceof CircularLayoutModule )
                {
                    name = "Circular";
                }
                else if ( value instanceof DiagonalLayoutModule )
                {
                    name = "Diagonal";
                }
                else if ( value instanceof IncrementalHierarchicLayoutModule )
                {
                    name = "Hierarchic";
                }
                else if ( value instanceof SmartOrganicLayoutModule )
                {
                    name = "Organic";
                }
                else if ( value instanceof OrthogonalLayoutModule )
                {
                    name = "Orthogonal";
                }
                else if ( value instanceof RandomLayoutModule )
                {
                    name = "Random";
                }
                else if ( value instanceof TreeLayoutModule )
                {
                    name = "Tree";
                }
                else
                {
                    name = ( ( YModule ) value ).getModuleName().toLowerCase();
                }
                return dlcr.getListCellRendererComponent( list, name, index, isSelected, cellHasFocus );
            }
            else
            {
                return dlcr.getListCellRendererComponent( list, value, index, isSelected, cellHasFocus );
            }
        }
    }
}
