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
package demo.view.flowchart;

import y.base.Edge;
import y.base.Node;
import y.view.AutoDragViewMode;
import y.view.CreateEdgeMode;
import y.view.EditMode;
import y.view.Graph2DView;
import y.view.Graph2DViewActions;
import y.view.Graph2DViewMouseWheelZoomListener;
import y.view.MovePortMode;
import y.view.PopupMode;
import y.view.TooltipMode;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Component that visualizes Flowchart diagrams.
 */
public class FlowchartView extends Graph2DView
{
    private Graph2DViewActions flowchartActions;

    /**
     * Creates a new Graph2DView containing an empty graph and register Flowchart specific view modes and actions
     * The constructor calls the following methods, in oder to register respective view modes and actions:
     * <ul>
     * <li> {@link #registerViewModes()}</li>
     * <li> {@link #registerViewActions()} </li>
     * <li> {@link #registerViewListeners()}</li>
     * </ul>
     */
    public FlowchartView()
    {
        super();
        //Some default behaviour
        this.setFitContentOnResize( true );

        //init
        registerViewModes();
        registerViewActions();
        registerViewListeners();
    }

    /**
     * Callback method, which registers Flowchart specific view actions.
     */
    protected void registerViewActions()
    {
        flowchartActions = new Graph2DViewActions( this );
        flowchartActions.install();
    }

    /**
     * Callback method, which registers Flowchart specific view modes and configures them.
     */
    protected void registerViewModes()
    {
        EditMode editMode = new EditMode();
        // Route all edges orthogonally.
        editMode.setOrthogonalEdgeRouting( true );

        CreateEdgeMode createEdgeMode = ( CreateEdgeMode ) editMode.getCreateEdgeMode();
        createEdgeMode.setOrthogonalEdgeCreation( true );
        createEdgeMode.setIndicatingTargetNode( true );
        editMode.setSnappingEnabled( true );

        //add hierarchy actions to the views popup menu
        editMode.setPopupMode( new FlowchartPopupMode() );
        editMode.getMouseInputMode().setNodeSearchingEnabled( true );
        editMode.assignNodeLabel( false );

        ( ( MovePortMode ) editMode.getMovePortMode() ).setIndicatingTargetNode( true );

        //add view mode to display tooltips for node
        TooltipMode tooltipMode = new TooltipMode();
        tooltipMode.setEdgeTipEnabled( false );
        addViewMode( tooltipMode );

        //allow moving view port with right drag gesture
        editMode.allowMovingWithPopup( true );
        addViewMode( editMode );

        //Auto drag mode
        addViewMode( new AutoDragViewMode() );
    }

    /**
     * Callback method, which registers  specific listeners and configures them.
     */
    protected void registerViewListeners()
    {
        Graph2DViewMouseWheelZoomListener wheelZoomListener = new Graph2DViewMouseWheelZoomListener();
        //zoom in/out at mouse pointer location
        wheelZoomListener.setCenterZooming( false );
        this.getCanvasComponent().addMouseWheelListener( wheelZoomListener );
    }


    //////////////////////////////////////////////////////////////////////////////
    // VIEW MODES ////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    /**
     * provides the context sensitive popup menus
     */
    private class FlowchartPopupMode extends PopupMode
    {

        public JPopupMenu getNodePopup( Node v )
        {
            JPopupMenu pm = new JPopupMenu();
            if ( v != null )
            {
                JMenuItem deleteItem = pm.add( flowchartActions.getDeleteSelectionAction() );
                deleteItem.setText( "Delete Node" );
            }
            return pm;
        }

        public JPopupMenu getEdgePopup( Edge e )
        {
            JPopupMenu pm = new JPopupMenu();
            if ( e != null )
            {
                JMenuItem deleteItem = pm.add( flowchartActions.getDeleteSelectionAction() );
                deleteItem.setText( "Delete Edge" );
            }
            return pm;
        }

        public JPopupMenu getSelectionPopup( double x, double y )
        {
            JPopupMenu pm = new JPopupMenu();
            JMenuItem deleteItem = pm.add( flowchartActions.getDeleteSelectionAction() );
            deleteItem.setText( "Delete Selection" );
            return pm;
        }
    }
}
