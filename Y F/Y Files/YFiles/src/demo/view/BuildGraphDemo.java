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

import y.base.Node;
import y.view.Arrow;
import y.view.EdgeLabel;
import y.view.EdgeRealizer;
import y.view.EditMode;
import y.view.Graph2D;
import y.view.Graph2DView;
import y.view.ImageNodeRealizer;
import y.view.NodeLabel;
import y.view.NodeRealizer;
import y.view.ShapeNodeRealizer;
import y.view.SmartEdgeLabelModel;
import y.view.SmartNodeLabelModel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

/**
 * <p>
 * Demonstrates simple usage of {@link Graph2DView}, {@link Graph2D}
 * and {@link EditMode}.
 * </p>
 * <p>
 * This demo creates an initial graph by adding nodes and edges
 * to the {@link Graph2D} displayed by the main {@link Graph2DView}
 * view using API calls. It further shows how some graphical node
 * and edge properties can be set (see {@link #buildGraph()}).
 * </p>
 * <p>
 * Additionally it is shown how the appearance of the default nodes
 * and edges can be set (see {@link #configureDefaultRealizers()}).
 * This applies to new nodes and edges added to the initial graph.
 * Editing the initial graph is possible due to the {@link EditMode}
 * added to the view.
 * </p>
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/base.html#Creating%20Graphs%20and%20Graph%20Elements">Section Creating Graphs and Graph Elements</a> in the yFiles for Java Developer's Guide
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/mvc_view.html">Section View Implementations</a> in the yFiles for Java Developer's Guide
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/realizer_related.html">Section Realizer-Related Features</a> in the yFiles for Java Developer's Guide
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/realizers.html">Section Bringing Graph Elements to Life: The Realizer Concept</a> in the yFiles for Java Developer's Guide
 */
public class BuildGraphDemo extends JPanel
{
    Graph2DView view;

    public BuildGraphDemo()
    {
        setLayout( new BorderLayout() );
        view = new Graph2DView();
        EditMode mode = new EditMode();
        view.addViewMode( mode );
        add( view );

        configureDefaultRealizers();
        buildGraph();
    }

    protected void configureDefaultRealizers()
    {
        Graph2D graph = view.getGraph2D();

        //change the looks of the default edge
        EdgeRealizer er = graph.getDefaultEdgeRealizer();
        //a standard (target) arrow
        er.setArrow( Arrow.STANDARD );

        //change the looks (and type) of the default node
        ShapeNodeRealizer snr = new ShapeNodeRealizer( ShapeNodeRealizer.ROUND_RECT );
        snr.setSize( 80, 30 );
        snr.setFillColor( DemoDefaults.DEFAULT_NODE_COLOR );
        NodeLabel label = snr.getLabel();
        SmartNodeLabelModel model = new SmartNodeLabelModel();
        label.setLabelModel( model );
        label.setModelParameter( model.getDefaultParameter() );

        //use it as default node realizer
        graph.setDefaultNodeRealizer( snr );
    }

    void buildGraph()
    {
        Graph2D graph = view.getGraph2D();

        //register an image with ImageNodeRealizer.
        //must be a path name relative to your java CLASSPATH.
        ImageNodeRealizer inr = new ImageNodeRealizer();
        //set the image
        inr.setImageURL( getClass().getResource( "resource/yicon.png" ) );
        //set node size equals to half of original image size
        inr.setToImageSize();
        inr.setSize( inr.getWidth() / 2, inr.getHeight() / 2 );
        inr.setLocation( 60, 200 );
        //set a label text
        inr.setLabelText( "yFiles" );

        // set the label model to be a SmartNodeLabelModel
        // (free label placement with snapping to significant positions)
        // with an initial position south of the node
        SmartNodeLabelModel labelModel = new SmartNodeLabelModel();
        NodeLabel nodeLabel = inr.getLabel();
        nodeLabel.setLabelModel( labelModel );
        nodeLabel.setModelParameter( labelModel.createDiscreteModelParameter( SmartNodeLabelModel.POSITION_SOUTH ) );

        //create a node that displays the image
        Node v = graph.createNode( inr );


        //create some edges and new nodes
        for ( int i = 0; i < 5; i++ )
        {
            Node w = graph.createNode();

            //customize position and label of the created node
            NodeRealizer nr = graph.getRealizer( w );
            nr.setLocation( 300, 100 + i * 50 );
            nr.setLabelText( "Node " + ( i + 1 ) );

            graph.createEdge( v, w );

            //decorations for the created edge
            EdgeRealizer er = graph.getRealizer( graph.lastEdge() );
            if ( i % 2 == 0 )
            {
                //set diamond source arrow
                er.setSourceArrow( Arrow.WHITE_DIAMOND );
            }
            else
            {
                //a label for the edge
                EdgeLabel edgeLabel = er.getLabel();
                edgeLabel.setText( "Edge " + ( i + 1 ) );
                SmartEdgeLabelModel model = new SmartEdgeLabelModel();
                model.setDefaultDistance( 2 );
                model.setAutoRotationEnabled( true );
                edgeLabel.setLabelModel( model );
                int pos = ( ( i + 1 ) % 4 ) == 0
                          ? SmartEdgeLabelModel.POSITION_RIGHT
                          : SmartEdgeLabelModel.POSITION_LEFT;
                edgeLabel.setModelParameter( model.createDiscreteModelParameter( pos ) );
            }
        }
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
                ( new BuildGraphDemo() ).start();
            }
        } );
    }
}
