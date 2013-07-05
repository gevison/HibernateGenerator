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
package demo.io.graphml;

import y.io.GraphMLIOHandler;
import y.view.Arrow;
import y.view.PolyLineEdgeRealizer;

import java.awt.EventQueue;
import java.util.Locale;

/**
 * A simple customization of {@link demo.io.graphml.GraphMLDemo} that uses objects of type
 * {@link demo.io.graphml.CustomNodeRealizer} as the graph's default node realizer.
 * To enable encoding and parsing of this node realizer type a specific serializer
 * implementation is registered with GraphMLIOHandler.
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/graphml.html#graphml_custom_realizer_serializer">Section Support for Custom Realizer</a> in the yFiles for Java Developer's Guide
 */
public class CustomNodeRealizerSerializerDemo extends GraphMLDemo
{
    /**
     * Creates a new instance of CustomNodeRealizerSerializerDemo.
     */
    public CustomNodeRealizerSerializerDemo()
    {
        // Use another default node realizer (the one used in the example graph).
        view.getGraph2D().setDefaultNodeRealizer( new CustomNodeRealizer() );

        // Use a default edge realizer as in the example graph.
        PolyLineEdgeRealizer edgeRealizer = new PolyLineEdgeRealizer();
        edgeRealizer.setTargetArrow( Arrow.NONE );
        view.getGraph2D().setDefaultEdgeRealizer( edgeRealizer );
    }


    protected void loadInitialGraph()
    {
        // Load example graph.
        loadGraph( "resources/custom/custom-noderealizer-serializer.graphml" );
    }

    protected String[] getExampleResources()
    {
        return null;
    }

    protected GraphMLIOHandler createGraphMLIOHandler()
    {
        GraphMLIOHandler ioHandler = new GraphMLIOHandler();
        // Register the node realizer's specific serializer that knows how to encode
        // valid XML markup and also how to parse the encoded data.
        ioHandler.addNodeRealizerSerializer( new CustomNodeRealizerSerializer() );
        return ioHandler;
    }

    /**
     * Launches this demo.
     */
    public static void main( String[] args )
    {
        EventQueue.invokeLater( new Runnable()
        {
            public void run()
            {
                Locale.setDefault( Locale.ENGLISH );
                initLnF();
                new CustomNodeRealizerSerializerDemo().start();
            }
        } );
    }
}
