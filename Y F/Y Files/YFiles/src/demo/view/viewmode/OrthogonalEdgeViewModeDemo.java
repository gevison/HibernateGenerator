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
package demo.view.viewmode;

import demo.view.DemoBase;
import y.base.Edge;
import y.util.DataProviderAdapter;
import y.view.CreateEdgeMode;
import y.view.EditMode;
import y.view.LineType;
import y.view.ViewMode;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Locale;

/**
 * Demonstrates how to customize {@link EditMode} to create and edit orthogonal edges.
 * <br>
 * This demo supports switching between the creation of orthogonal and polygonal edges.
 * Additionally, the demo shows the usage of the snapping feature of the various {@link ViewMode}s.
 * Toggling the buttons in the toolbar switches the type of newly created edges or toggle snapping on and off.
 * This affects the behavior of {@link CreateEdgeMode} and {@link EditMode}, as well as implicitly the minor modes
 * of {@link EditMode}.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/mvc_controller.html#orthogonal_edge_paths">Section User Interaction</a> in the yFiles for Java Developer's Guide
 */
public class OrthogonalEdgeViewModeDemo extends DemoBase
{

    private boolean orthogonalRouting;

    private boolean usingSnapping;

    private EditMode editMode;

    private JToggleButton orthogonalButton;

    private JToggleButton snapLineButton;

    private SnappingConfiguration snappingConfiguration;

    public OrthogonalEdgeViewModeDemo()
    {
        snappingConfiguration = DemoBase.createDefaultSnappingConfiguration();
        snappingConfiguration.setSnappingEnabled( true );
        snappingConfiguration.setRemovingInnerBends( true );
        snappingConfiguration.configureView( view );
        snappingConfiguration.configureEditMode( editMode );

        setOrthogonalRouting( true );
        setUsingSnapping( true );
        loadGraph( "resource/orthogonalEdge.graphml" );
    }

    protected JToolBar createToolBar()
    {
        orthogonalButton = new JToggleButton( new AbstractAction( "Orthogonal" )
        {
            public void actionPerformed( ActionEvent e )
            {
                setOrthogonalRouting( ( ( AbstractButton ) e.getSource() ).isSelected() );
            }
        } );
        orthogonalButton.setIcon( getIconResource( "resource/mode_orthogonal.png" ) );

        snapLineButton = new JToggleButton( new AbstractAction( "Snapping" )
        {
            public void actionPerformed( ActionEvent e )
            {
                setUsingSnapping( ( ( AbstractButton ) e.getSource() ).isSelected() );
            }
        } );
        snapLineButton.setIcon( getIconResource( "resource/mode_snapping.png" ) );

        JToolBar toolBar = super.createToolBar();
        toolBar.addSeparator();
        toolBar.add( orthogonalButton );
        toolBar.addSeparator( TOOLBAR_SMALL_SEPARATOR );
        toolBar.add( snapLineButton );
        return toolBar;
    }

    protected EditMode createEditMode()
    {
        editMode = super.createEditMode();

        // Route all red edges orthogonally.
        view.getGraph2D().addDataProvider( EditMode.ORTHOGONAL_ROUTING_DPKEY, new DataProviderAdapter()
        {
            public boolean getBool( Object dataHolder )
            {
                return view.getGraph2D().getRealizer( ( Edge ) dataHolder ).getLineColor() == Color.RED;
            }
        } );

        return editMode;
    }

    public boolean isOrthogonalRouting()
    {
        return orthogonalRouting;
    }

    public void setOrthogonalRouting( boolean orthogonalRouting )
    {
        this.orthogonalRouting = orthogonalRouting;
        this.orthogonalButton.setSelected( orthogonalRouting );
        ( ( CreateEdgeMode ) editMode.getCreateEdgeMode() ).setOrthogonalEdgeCreation( orthogonalRouting );
        view.getGraph2D().getDefaultEdgeRealizer().setLineColor( orthogonalRouting ? Color.RED : Color.BLACK );
        view.getGraph2D().getDefaultEdgeRealizer().setLineType( orthogonalRouting ? LineType.LINE_2 : LineType.LINE_1 );
    }

    public boolean isUsingSnapping()
    {
        return usingSnapping;
    }

    public void setUsingSnapping( boolean usingSnapping )
    {
        this.usingSnapping = usingSnapping;
        this.snapLineButton.setSelected( usingSnapping );
        snappingConfiguration.setSnappingEnabled( usingSnapping );
        snappingConfiguration.setRemovingInnerBends( usingSnapping );
        snappingConfiguration.configureView( view );
        snappingConfiguration.configureEditMode( editMode );
    }

    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                ( new OrthogonalEdgeViewModeDemo() ).start( "Orthogonal Edge ViewMode Demo" );
            }
        } );
    }
}
