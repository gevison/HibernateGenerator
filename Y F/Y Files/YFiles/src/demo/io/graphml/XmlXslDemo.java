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


import y.io.XmlXslIOHandler;
import y.util.D;
import y.view.Graph2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileFilter;
import javax.xml.transform.stream.StreamSource;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;


/**
 * This demo shows how XML files can imported as
 * GraphML by the means of an XSLT stylesheet.
 * Sample stylesheets for the following XML data are provided:
 * <ul>
 * <li><a href="resources/xsl/ant2graphml.xsl">Ant build scripts</a></li>
 * <li><a href="resources/xsl/owl2graphml.xsl">OWL web ontology data</a></li>
 * <li><a href="resources/xsl/xmltree2graphml.xsl">the XML tree structure</a></li>
 * </ul>
 *
 * @see <a href="http://docs.yworks.com/yfiles/doc/developers-guide/graphml.html#graphml_xslt">Section yFiles XSLT Support for GraphML</a> in the yFiles for Java Developer's Guide
 */
public class XmlXslDemo extends GraphMLDemo
{
    private String[][] sampleFiles;

    public XmlXslDemo()
    {
        graphMLPane.setEditable( false );
    }

    protected void loadInitialGraph()
    {
        if ( sampleFiles != null )
        {
            loadXml( getClass().getResource( sampleFiles[ 0 ][ 0 ] ), getClass().getResource( sampleFiles[ 0 ][ 1 ] ) );
        }
    }

    protected void initialize()
    {
        super.initialize();

        sampleFiles = new String[][]{
                { "resources/xml/ant-build.xml", "resources/xsl/ant2graphml.xsl" },
                { "resources/xml/food.owl", "resources/xsl/owl2graphml.xsl" },
                { "resources/xml/food.owl", "resources/xsl/xmltree2graphml.xsl" },
        };
    }

    protected JMenuBar createMenuBar()
    {
        final JMenuBar menuBar = super.createMenuBar();
        createExamplesMenu( menuBar );
        return menuBar;
    }

    protected void createExamplesMenu( JMenuBar menuBar )
    {
        final JMenu menu = new JMenu( "Example Graphs" );
        menuBar.add( menu );

        for ( int i = 0; i < sampleFiles.length; i++ )
        {
            final String xml = sampleFiles[ i ][ 0 ];
            final String xsl = sampleFiles[ i ][ 1 ];
            final String name = xml.substring( xml.lastIndexOf( '/' ) + 1 )
                    + " + " + xsl.substring( xsl.lastIndexOf( '/' ) + 1 );

            menu.add( new AbstractAction( name )
            {
                public void actionPerformed( ActionEvent e )
                {
                    loadXml( getClass().getResource( xml ), getClass().getResource( xsl ) );
                }
            } );
        }
    }

    protected String[] getExampleResources()
    {
        return null;
    }

    public void loadXml( URL xmlResource, URL xslResource )
    {
        Graph2D graph = view.getGraph2D();
        try
        {
            XmlXslIOHandler ioh = new XmlXslIOHandler( createGraphMLIOHandler() );
            ioh.setXslSource( new StreamSource( xslResource.openStream() ) );
            ioh.read( graph, xmlResource );
            view.fitContent();
            view.updateView();
        }
        catch ( IOException ioe )
        {
            D.show( ioe );
        }
        finally
        {
            graphMLPane.updateGraphMLText( graph );
        }
    }

    protected Action createLoadAction()
    {
        return new AbstractAction( "Load..." )
        {

            public void actionPerformed( ActionEvent e )
            {
                JFileChooser chooser = new JFileChooser();
                chooser.setAcceptAllFileFilterUsed( true );
                chooser.setDialogTitle( "XML input" );

                URL xmlResource = null;
                if ( chooser.showOpenDialog( contentPane ) == JFileChooser.APPROVE_OPTION )
                {
                    try
                    {
                        xmlResource = chooser.getSelectedFile().toURI().toURL();
                    }
                    catch ( MalformedURLException urlex )
                    {
                        urlex.printStackTrace();
                    }
                }
                if ( xmlResource != null )
                {
                    chooser.setAcceptAllFileFilterUsed( false );
                    chooser.setDialogTitle( "XSL stylesheet" );
                    chooser.addChoosableFileFilter( new FileFilter()
                    {
                        public boolean accept( File f )
                        {
                            return f.isDirectory() || f.getName().endsWith( ".xsl" );
                        }

                        public String getDescription()
                        {
                            return "XML stylesheets (.xsl)";
                        }
                    } );

                    if ( chooser.showOpenDialog( contentPane ) == JFileChooser.APPROVE_OPTION )
                    {
                        try
                        {
                            URL xslResource = chooser.getSelectedFile().toURI().toURL();
                            if ( xslResource != null )
                            {
                                loadXml( xmlResource, xslResource );
                            }
                        }
                        catch ( MalformedURLException urlex )
                        {
                            urlex.printStackTrace();
                        }

                    }
                }
            }

        };
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
                new XmlXslDemo().start();
            }
        } );
    }
}
