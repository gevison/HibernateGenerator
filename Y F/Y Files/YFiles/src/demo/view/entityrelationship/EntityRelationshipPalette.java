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
package demo.view.entityrelationship;

import demo.view.entityrelationship.painters.ErdRealizerFactory;
import demo.view.flowchart.FlowchartPalette;
import y.view.Arrow;
import y.view.EdgeRealizer;
import y.view.GenericNodeRealizer;
import y.view.Graph2D;
import y.view.Graph2DView;
import y.view.NodeRealizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a component, which provides templates for entity relationship diagram (ERD)
 * nodes and edges that can be dragged into a Graph2DView.
 */
public class EntityRelationshipPalette extends FlowchartPalette
{

    private Map arrowNames;

    private Map nodeNames;

    /**
     * Creates a new <code>EntityRelationshipPalette</code> with a pre-configured list of
     * node and edge realizers.
     *
     * @param view a view of the graph the palette is assigned to
     */
    public EntityRelationshipPalette( final Graph2DView view )
    {
        super( view );

        arrowNames = new HashMap();
        arrowNames.put( Arrow.NONE, "Unspecified" );
        arrowNames.put( Arrow.CROWS_FOOT_ONE, "(1)" );
        arrowNames.put( Arrow.CROWS_FOOT_MANY, "(N)" );
        arrowNames.put( Arrow.CROWS_FOOT_ONE_OPTIONAL, "(0,1)" );
        arrowNames.put( Arrow.CROWS_FOOT_ONE_MANDATORY, "(1,1)" );
        arrowNames.put( Arrow.CROWS_FOOT_MANY_OPTIONAL, "(0,N)" );
        arrowNames.put( Arrow.CROWS_FOOT_MANY_MANDATORY, "(1,N)" );
    }

    /**
     * Initializes default realizers for the specified view's associated graph.
     *
     * @param view The respective Graph2DView.
     */
    protected void initializeDefaultRealizers( Graph2DView view )
    {
        Graph2D graph2D = view.getGraph2D();
        graph2D.setDefaultNodeRealizer( ErdRealizerFactory.createBigEntity() );
        graph2D.setDefaultEdgeRealizer( ErdRealizerFactory.createRelation( Arrow.NONE ) );
    }

    /**
     * Adds default ERD templates to the palette list.
     *
     * @param realizers The list of all template realizers
     */
    protected void addDefaultTemplates( final List realizers )
    {

        //create node templates
        final NodeRealizer bigEntity = ErdRealizerFactory.createBigEntity();
        final GenericNodeRealizer smallEntity = ErdRealizerFactory.createSmallEntity( "Entity Name" );
        final GenericNodeRealizer weakSmallEntity = ErdRealizerFactory.createWeakSmallEntity( "Entity Name" );
        final GenericNodeRealizer attribute = ErdRealizerFactory.createAttribute( "Attribute" );
        final GenericNodeRealizer multiValuedAttribute = ErdRealizerFactory.createMultiValuedAttribute( "Attribute" );
        final GenericNodeRealizer primaryKeyAttribute = ErdRealizerFactory.createPrimaryKeyAttribute( "Attribute" );
        final GenericNodeRealizer derivedAttribute = ErdRealizerFactory.createDerivedAttribute( "Attribute" );
        final GenericNodeRealizer relationship = ErdRealizerFactory.createRelationship( "Relation" );
        final GenericNodeRealizer weakRelationship = ErdRealizerFactory.createWeakRelationship( "Relation" );

        //add node templates to list
        realizers.add( bigEntity );
        realizers.add( smallEntity );
        realizers.add( weakSmallEntity );
        realizers.add( attribute );
        realizers.add( multiValuedAttribute );
        realizers.add( primaryKeyAttribute );
        realizers.add( derivedAttribute );
        realizers.add( relationship );
        realizers.add( weakRelationship );

        //register tooltips for node templates
        nodeNames = new HashMap();
        nodeNames.put( bigEntity, "Entity with Attributes" );
        nodeNames.put( smallEntity, "Entity" );
        nodeNames.put( weakSmallEntity, "Weak Entity" );
        nodeNames.put( attribute, "Attribute" );
        nodeNames.put( multiValuedAttribute, "Multi-Valued Attribute" );
        nodeNames.put( primaryKeyAttribute, "Primary Key" );
        nodeNames.put( derivedAttribute, "Derived Attribute" );
        nodeNames.put( relationship, "Relationship" );
        nodeNames.put( weakRelationship, "Weak Relationship" );

        //add edge templates to list
        realizers.add( ErdRealizerFactory.createRelation( Arrow.NONE ) );
        realizers.add( ErdRealizerFactory.createRelation( Arrow.CROWS_FOOT_ONE ) );
        realizers.add( ErdRealizerFactory.createRelation( Arrow.CROWS_FOOT_MANY ) );
        realizers.add( ErdRealizerFactory.createRelation( Arrow.CROWS_FOOT_ONE_OPTIONAL ) );
        realizers.add( ErdRealizerFactory.createRelation( Arrow.CROWS_FOOT_ONE_MANDATORY ) );
        realizers.add( ErdRealizerFactory.createRelation( Arrow.CROWS_FOOT_MANY_OPTIONAL ) );
        realizers.add( ErdRealizerFactory.createRelation( Arrow.CROWS_FOOT_MANY_MANDATORY ) );
    }

    /**
     * Creates text for a tooltip that appears if you move the mouse over an
     * edge icon in the palette.
     *
     * @param realizer the realizer of the selected edge template
     *
     * @return a string that shows the text assigned to the edge template
     */
    protected String createEdgeToolTipText( EdgeRealizer realizer )
    {
        return ( String ) arrowNames.get( realizer.getSourceArrow() );
    }

    /**
     * Creates text for a tooltip that appears if you move the mouse over a
     * node icon in the palette.
     *
     * @param realizer the realizer of the selected node template
     *
     * @return a string that shows the text assigned to the node template
     */
    protected String createNodeToolTipText( NodeRealizer realizer )
    {
        return ( String ) nodeNames.get( realizer );
    }

}
