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
package demo.layout.router;

import demo.view.DemoBase;
import y.layout.router.BusRouter;
import y.option.CompoundEditor;
import y.option.ConstraintManager;
import y.option.DefaultEditorFactory;
import y.option.Editor;
import y.option.IntOptionItem;
import y.option.ItemEditor;
import y.option.OptionGroup;
import y.option.OptionHandler;
import y.option.OptionItem;
import y.option.ResourceBundleGuiFactory;
import y.view.EditMode;
import y.view.Graph2DView;
import y.view.MovePortMode;
import y.view.View2DConstants;
import y.view.ViewMode;

import javax.swing.JComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;

/**
 * Governs the settings which are present in the demo's side panel.
 */
class BusRouterDemoTools
{

    private static final String GROUP_GRID = "GROUP_GRID";

    private static final String GROUP_EDIT = "GROUP_EDIT";

    private static final String GRID_ENABLED = "GRID_ENABLED";

    private static final String GRID_SPACING = "GRID_SPACING";

    private static final String SNAPPING = "SNAPPING";

    private final OptionHandler optionHandler;

    private final DemoBase.SnappingConfiguration snappingConfiguration;

    private BusRouter busRouter;

    private Graph2DView view;

    /**
     * Creates a new instance.
     */
    BusRouterDemoTools()
    {
        this.snappingConfiguration = DemoBase.createDefaultSnappingConfiguration();
        this.snappingConfiguration.setGridType( View2DConstants.GRID_POINTS );
        this.optionHandler = createOptionHandler();
    }

    void setViewAndRouter( Graph2DView view, BusRouter busRouter )
    {
        this.view = view;
        this.busRouter = busRouter;
    }

    /**
     * Creates an option handler containing items for grid, orthogonal mode, snap lines and automatic routing.
     */
    OptionHandler createOptionHandler()
    {
        OptionHandler oh = new OptionHandler( "BUS_ROUTER_DEMO_SETTINGS" );
        OptionItem item;
        OptionGroup og;

        // items of group GRID
        item = oh.addBool( GRID_ENABLED, false );
        item.addPropertyChangeListener( "value", new PropertyChangeListener()
        {
            public void propertyChange( PropertyChangeEvent evt )
            {
                updateGrid();
            }
        } );
        item = oh.addInt( GRID_SPACING, 20 );
        item.setAttribute( IntOptionItem.ATTRIBUTE_MIN_VALUE, new Integer( 1 ) );
        item.addPropertyChangeListener( "value", new PropertyChangeListener()
        {
            public void propertyChange( PropertyChangeEvent evt )
            {
                updateGrid();
            }
        } );

        og = new OptionGroup();
        og.setAttribute( OptionGroup.ATTRIBUTE_TITLE, GROUP_GRID );
        og.addItem( oh.getItem( GRID_ENABLED ) );
        og.addItem( oh.getItem( GRID_SPACING ) );

        ConstraintManager cm = new ConstraintManager( oh );
        cm.setEnabledOnValueEquals( GRID_ENABLED, Boolean.TRUE, GRID_SPACING );

        item = oh.addBool( SNAPPING, true );
        item.addPropertyChangeListener( "value", new PropertyChangeListener()
        {
            public void propertyChange( PropertyChangeEvent evt )
            {
                updateSnapping();
            }
        } );

        og = new OptionGroup();
        og.setAttribute( OptionGroup.ATTRIBUTE_TITLE, GROUP_EDIT );
        og.addItem( oh.getItem( SNAPPING ) );

        return oh;
    }

    /**
     * Creates a component for this class's option handler.
     *
     * @return a component for this class's option handler.
     */
    JComponent createOptionComponent()
    {
        DefaultEditorFactory editorFactory = new DefaultEditorFactory();
        try
        {
            ResourceBundleGuiFactory gf = new ResourceBundleGuiFactory();
            gf.addBundle( BusRouterDemo.class.getName() );
            editorFactory.setGuiFactory( gf );
        }
        catch ( final MissingResourceException mre )
        {
            //noinspection UseOfSystemOutOrSystemErr
            System.err.println( "Could not find resources! " + mre );
        }

        Editor editor = editorFactory.createEditor( optionHandler );

        // set the editor to auto adopt and auto commit, so no OK button is needed
        final List stack = new ArrayList();
        stack.add( editor );
        while ( !stack.isEmpty() )
        {
            final Object last = stack.remove( stack.size() - 1 );
            if ( last instanceof CompoundEditor )
            {
                for ( Iterator it = ( ( CompoundEditor ) last ).editors(); it.hasNext(); )
                {
                    stack.add( it.next() );
                }
            }
            if ( last instanceof ItemEditor )
            {
                ( ( ItemEditor ) last ).setAutoCommit( true );
                ( ( ItemEditor ) last ).setAutoAdopt( true );
            }
        }

        return editor.getComponent();
    }

    /**
     * Updates the grid of the set view and bus router to the values specified by this class's option handler.
     */
    void updateGrid()
    {
        busRouter.setGridRoutingEnabled( optionHandler.getBool( GRID_ENABLED ) );
        busRouter.setGridSpacing( optionHandler.getInt( GRID_SPACING ) );
        snappingConfiguration.setGridSnappingEnabled( optionHandler.getBool( GRID_ENABLED ) );
        snappingConfiguration.setGridDistance( ( double ) optionHandler.getInt( GRID_SPACING ) );
        configureSnapping();
    }

    /**
     * Updates the snapping of the set view to the values specified by this class's option handler.
     */
    void updateSnapping()
    {
        snappingConfiguration.setSnappingEnabled( optionHandler.getBool( SNAPPING ) );
        configureSnapping();
    }

    /**
     * Configures the snapping of the set view according to this class's snapping configuration..
     */
    private void configureSnapping()
    {
        final EditMode editMode = ( EditMode ) view.getViewModes().next();
        snappingConfiguration.configureView( view );
        snappingConfiguration.configureEditMode( editMode );

        // configure snapping disables singleRealizerPortCandidates, so we enable it manually
        final ViewMode movePortMode = editMode.getMovePortMode();
        if ( movePortMode instanceof MovePortMode )
        {
            ( ( MovePortMode ) movePortMode ).setUsingRealizerPortCandidates( true );
        }
    }

}
