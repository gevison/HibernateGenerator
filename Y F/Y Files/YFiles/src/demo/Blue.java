package demo;

import demo.view.DemoDefaults;
import y.base.GraphEvent;
import y.base.GraphListener;
import y.base.Node;
import y.base.NodeMap;
import y.layout.NodeLabelLayout;
import y.layout.NodeLabelModel;
import y.layout.hierarchic.AsIsLayerer;
import y.layout.hierarchic.GivenLayersLayerer;
import y.layout.hierarchic.IncrementalHierarchicLayouter;
import y.layout.hierarchic.incremental.IncrementalHintsFactory;
import y.option.BoolOptionItem;
import y.option.CompoundEditor;
import y.option.DefaultEditorFactory;
import y.option.Editor;
import y.option.GuiFactory;
import y.option.ItemEditor;
import y.option.OptionHandler;
import y.option.OptionItem;
import y.option.OptionSection;
import y.option.PropertiesGuiFactory;
import y.option.TableEditorFactory;
import y.view.AutoDragViewMode;
import y.view.BevelNodePainter;
import y.view.CreateEdgeMode;
import y.view.EditMode;
import y.view.GenericNodeRealizer;
import y.view.Graph2D;
import y.view.Graph2DView;
import y.view.Graph2DViewActions;
import y.view.MovePortMode;
import y.view.NodeLabel;
import y.view.SimpleUserDataHandler;
import y.view.SmartNodeLabelModel;
import y.view.TooltipMode;
import y.view.hierarchy.GenericGroupNodeRealizer;
import y.view.hierarchy.HierarchyManager;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: evison_g
 * Date: 04/07/13
 * Time: 10:56
 */
public class Blue extends JFrame implements PropertyChangeListener, VetoableChangeListener, GraphListener
{
    private Boolean level1Boolean = Boolean.FALSE;

    private Boolean level2Boolean = Boolean.TRUE;

    private Color color = Color.BLACK;

    private GuiFactory i18n;

    private Editor editor1;

    private Editor editor2;

    private Graph2DView view;

    private HierarchyManager hierarchyManager;

    private IncrementalHierarchicLayouter hierarchicLayouter;

    private IncrementalHintsFactory hintsFactory;

    private GivenLayersLayerer gll;

    private NodeMap layerIdMap;

    public Blue( String title )
    {
        super( title );
        DemoDefaults.initLnF();

        Properties properties = new Properties();
        properties.setProperty( "Blue.Level 1", "Level 1" );
        properties.setProperty( "Blue.Level 1.Level 1 Boolean", "Level 1 Boolean" );
        properties.setProperty( "Blue.Level 2", "Level 2" );
        properties.setProperty( "Blue.Level 2.Level 2 Boolean", "Level 2 Boolean" );
        properties.setProperty( "Blue.Level 2.Colour", "Colour" );

        i18n = new PropertiesGuiFactory( properties );

//        setContentPane( createContentPane() );
        setContentPane( createGraphContentPane() );

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        pack();
    }

    private Container createGraphContentPane()
    {
        List configNames = new ArrayList();
        GenericNodeRealizer.Factory factory = GenericNodeRealizer.getFactory();

        String configName = "Bevel";
        factory.addConfiguration( configName, createBevelNodeConfiguration( factory ) );
        configNames.add( configName );

        GenericNodeRealizer nr = new GenericNodeRealizer( configName );
        nr.setLabelText( configName );
        nr.setWidth( 120 );
        nr.setFillColor( Color.ORANGE );
        nr.setLineColor( Color.ORANGE );

        NodeLabel label = nr.getLabel();
        SmartNodeLabelModel model = new SmartNodeLabelModel();
        label.setLabelModel( model );
        label.setModelParameter( model.getDefaultParameter() );

        view = new Graph2DView();
        view.setFitContentOnResize( true );
        DemoDefaults.configureDefaultRealizers( view );
        view.getGraph2D().clear();
//        view.getGraph2D().setURL( resource );
        view.fitContent();
        view.updateView();

        registerViewModes();
        registerViewActions();

        Graph2DViewActions actions = new Graph2DViewActions( view );
        ActionMap amap = view.getCanvasComponent().getActionMap();
        if ( amap != null )
        {
            InputMap imap = actions.createDefaultInputMap( amap );

            amap.remove( Graph2DViewActions.DELETE_SELECTION );

            view.getCanvasComponent().setInputMap( JComponent.WHEN_FOCUSED, imap );
        }

        // create and configure the layout algorithm
        hierarchicLayouter = new IncrementalHierarchicLayouter();
        hierarchicLayouter.setFixedElementsLayerer( gll = new GivenLayersLayerer() );
        hintsFactory = hierarchicLayouter.createIncrementalHintsFactory();
        hierarchicLayouter.setComponentLayouterEnabled( false );
        hierarchicLayouter.setLayoutMode( IncrementalHierarchicLayouter.LAYOUT_MODE_INCREMENTAL );

        hierarchicLayouter.getEdgeLayoutDescriptor().setSourcePortOptimizationEnabled( true );
        hierarchicLayouter.getEdgeLayoutDescriptor().setTargetPortOptimizationEnabled( true );
        hierarchicLayouter.setOrthogonallyRouted( true );

        Graph2D graph2D = view.getGraph2D();
        hierarchyManager = new HierarchyManager( graph2D );
        graph2D.setDefaultNodeRealizer( nr );

        layerIdMap = graph2D.createNodeMap();
        graph2D.addDataProvider( GivenLayersLayerer.LAYER_ID_KEY, layerIdMap );

        Node node1 = graph2D.createNode(  );
        layerIdMap.setInt( node1, 0 );

        graph2D.setLabelText( node1, "Tree" );
        Node node2 = graph2D.createNode(  );
        layerIdMap.setInt( node2, 1 );

        graph2D.setLabelText( node2, "Leafjhdsfkjashdlkfjhasldkjfhlhlasedf" );

        NodeLabelLayout[] labelLayout = graph2D.getLabelLayout( node2 );

        for ( NodeLabelLayout nodeLabelLayout : labelLayout )
        {
            NodeLabel nodeLabel = ( NodeLabel ) nodeLabelLayout;

            graph2D.setSize( node2, nodeLabel.getWidth() + 20, nodeLabel.getHeight() + 20 );
        }

        graph2D.createEdge( node1, node2 );

        graph2D.addGraphListener( this );

        calcLayout();
        view.updateView();

        return view;
    }

    /**
     * Animated layout assignment
     */
    public void calcLayout()
    {
        hierarchicLayouter.setFixedElementsLayerer( gll );

        if ( !view.getGraph2D().isEmpty() )
        {
            gll.normalize( view.getGraph2D(), layerIdMap, layerIdMap );
            Cursor oldCursor = view.getViewCursor();
            try
            {
                view.setViewCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
                view.applyLayoutAnimated( hierarchicLayouter );
            }
            finally
            {
                view.setViewCursor( oldCursor );
            }
        }
//        layerDrawable.updateLayers();
        view.updateView();
    }

    protected void registerViewActions()
    {
        // register keyboard actions
        Graph2DViewActions actions = new Graph2DViewActions( view );
        ActionMap amap = view.getCanvasComponent().getActionMap();
        if ( amap != null )
        {
            InputMap imap = actions.createDefaultInputMap( amap );
            amap.remove( Graph2DViewActions.DELETE_SELECTION );
            view.getCanvasComponent().setInputMap( JComponent.WHEN_FOCUSED, imap );
        }
    }

    protected void registerViewModes()
    {
        EditMode editMode = createEditMode();
//        editMode.allowEdgeCreation( false );
//        editMode.allowNodeCreation( false );
//        editMode.allowNodeEditing( false );
//        editMode.allowBendCreation( false );
//        editMode.allowResizeNodes( false );
        if ( editMode != null )
        {
            view.addViewMode( editMode );
        }

        TooltipMode tooltipMode = createTooltipMode();
        if ( tooltipMode != null )
        {
            view.addViewMode( tooltipMode );
        }



        view.addViewMode( new AutoDragViewMode() );
    }

    /**
     * Callback used by {@link #registerViewModes()} to create the default
     * EditMode.
     *
     * @return an instance of {@link EditMode} with showNodeTips enabled.
     */
    protected EditMode createEditMode()
    {
        EditMode editMode = new EditMode();
        // show the highlighting which is turned off by default
        if ( editMode.getCreateEdgeMode() instanceof CreateEdgeMode )
        {
            ( ( CreateEdgeMode ) editMode.getCreateEdgeMode() ).setIndicatingTargetNode( true );
        }
        if ( editMode.getMovePortMode() instanceof MovePortMode )
        {
            ( ( MovePortMode ) editMode.getMovePortMode() ).setIndicatingTargetNode( true );
        }

        //allow moving view port with right drag gesture
        editMode.allowMovingWithPopup( true );

        return editMode;
    }

    /**
     * Callback used by {@link #registerViewModes()} to create a default
     * <code>TooltipMode</code>.
     *
     * @return an instance of {@link TooltipMode} where only node tooltips are enabled.
     */
    protected TooltipMode createTooltipMode()
    {
        TooltipMode tooltipMode = new TooltipMode();
        tooltipMode.setEdgeTipEnabled( false );
        return tooltipMode;
    }

    private Map createBevelNodeConfiguration( GenericNodeRealizer.Factory factory )
    {
        Map implementationsMap = factory.createDefaultConfigurationMap();

        BevelNodePainter painter = new BevelNodePainter();
        //BevelNodePainter has an own option to draw a drop shadow that is more efficient than wrapping it with
        // {@link y.view.ShadowNodePainter}
        painter.setDrawShadow( true );
        implementationsMap.put( GenericNodeRealizer.Painter.class, painter );
        implementationsMap.put( GenericNodeRealizer.ContainsTest.class, painter );

        // User-defined data objects that implement both the Cloneable and Serializable
        // interfaces are taken care of (when serializing/deserializing the realizer).
        implementationsMap.put( GenericNodeRealizer.UserDataHandler.class,
                                new SimpleUserDataHandler( SimpleUserDataHandler.REFERENCE_ON_FAILURE ) );

        return implementationsMap;
    }

    private JPanel createContentPane()
    {
        OptionHandler handler = getOptionHandler();

        DefaultEditorFactory defaultFactory = new DefaultEditorFactory();
        defaultFactory.setGuiFactory( i18n );

        TableEditorFactory tableFactory = new TableEditorFactory();
        tableFactory.setItemFactory( defaultFactory );
        tableFactory.setGuiFactory( i18n );

        editor1 = defaultFactory.createEditor( handler );
        setAutoCommit( true, editor1 );
        setAutoAdopt( true, editor1 );

        editor2 = tableFactory.createEditor( handler );
        setAutoCommit( true, editor2 );
        setAutoAdopt( true, editor2 );

        JPanel editorPane = new JPanel( new BorderLayout() );

        editorPane.add( editor1.getComponent(), BorderLayout.WEST );
        editorPane.add( editor2.getComponent(), BorderLayout.CENTER );

        JComponent component = editor1.getComponent();

        Dimension dimension = new Dimension( 300, 462 );

        component.setSize( dimension );
        component.setPreferredSize( dimension );
        component.setMinimumSize( dimension );

        return editorPane;
    }

    /**
     * Sets the <code>autoAdopt</code> property for all items of the specified
     * option handler.
     */
    private static void setAutoAdopt( final boolean autoAdopt,
                                      final Editor editor )
    {
        if ( editor instanceof CompoundEditor )
        {
            for ( Iterator it = ( ( CompoundEditor ) editor ).editors(); it.hasNext(); )
            {
                setAutoAdopt( autoAdopt, ( Editor ) it.next() );
            }
        }
        if ( editor instanceof ItemEditor )
        {
            ( ( ItemEditor ) editor ).setAutoAdopt( autoAdopt );
        }
    }

    /**
     * Sets the <code>autoCommit</code> property to the specified value,
     * if the specified editor support setting said property.
     */
    private static void setAutoCommit( final boolean autoCommit,
                                       final Editor editor )
    {
        if ( editor instanceof CompoundEditor )
        {
            for ( Iterator it = ( ( CompoundEditor ) editor ).editors(); it.hasNext(); )
            {
                setAutoCommit( autoCommit, ( Editor ) it.next() );
            }
        }
        if ( editor instanceof ItemEditor )
        {
            ( ( ItemEditor ) editor ).setAutoCommit( autoCommit );
        }
    }

    private OptionHandler getOptionHandler()
    {
        OptionHandler handler = new OptionHandler( "Blue" );

        OptionSection optionSection = handler.useSection( "Level 1" );
        optionSection.setAttribute( OptionSection.ATTRIBUTE_LONG_DESCRIPTION, "Level 1 Long" );

        OptionItem optionItem = handler.addBool( "Level 1 Boolean", level1Boolean );
        optionItem.setAttribute( OptionItem.ATTRIBUTE_LONG_DESCRIPTION, "Level 1 Boolean Long" );

        handler.useSection( "Level 2" );
        handler.addBool( "Level 2 Boolean", level2Boolean );
        handler.addColor( "Colour", color, true );
        return handler;
    }

    public static void main( String[] args )
    {
        Blue blue = new Blue( "Blue" );

        blue.setVisible( true );
    }

    @Override
    public void propertyChange( PropertyChangeEvent evt )
    {
        if ( evt.getSource() instanceof OptionItem )
        {
            OptionItem optionItem = ( OptionItem ) evt.getSource();

            System.out.println( optionItem.getName() );

            System.out.println( level1Boolean );
            System.out.println( level2Boolean );
            System.out.println( color );
        }
    }

    @Override
    public void vetoableChange( PropertyChangeEvent evt ) throws PropertyVetoException
    {
        Object source = evt.getSource();
        if ( source instanceof BoolOptionItem )
        {
            BoolOptionItem boolOptionItem = ( BoolOptionItem ) source;

            String name = boolOptionItem.getName();

            if ( name.contains( "2" ) == true )
            {
                System.out.println( "Vetoing " + name );
                throw new PropertyVetoException( "Error", evt );
            }
        }
    }

    @Override
    public void onGraphEvent( GraphEvent graphEvent )
    {
        byte eventType = graphEvent.getType();
        Object data = graphEvent.getData();

        if ( eventType == GraphEvent.EDGE_CREATION)
        {
            calcLayout();
        }
        else if ( eventType == GraphEvent.NODE_CREATION )
        {
            layerIdMap.setInt( data,2 );


            NodeLabelLayout[] labelLayout = graph2D.getLabelLayout( node2 );

            for ( NodeLabelLayout nodeLabelLayout : labelLayout )
            {
                NodeLabel nodeLabel = ( NodeLabel ) nodeLabelLayout;

                graph2D.setSize( node2, nodeLabel.getWidth() + 20, nodeLabel.getHeight() + 20 );
            }
            calcLayout();
        }
    }
}
