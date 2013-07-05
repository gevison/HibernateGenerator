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
package demo.browser;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A tree model that supports filtering its tree nodes.
 */
class FilterableTreeModel implements TreeModel
{
    EventListenerList listenerList;

    private final FilterableTreeNode root;

    FilterableTreeModel( final FilterableTreeNode root )
    {
        this.root = root;
        listenerList = new EventListenerList();
    }

    public void addTreeModelListener( final TreeModelListener l )
    {
        listenerList.add( TreeModelListener.class, l );
    }

    public void removeTreeModelListener( final TreeModelListener l )
    {
        listenerList.remove( TreeModelListener.class, l );
    }

    public Object getRoot()
    {
        return root;
    }

    public int getChildCount( final Object parent )
    {
        return ( ( FilterableTreeNode ) parent ).getChildCount();
    }

    public boolean isLeaf( final Object node )
    {
        return ( ( FilterableTreeNode ) node ).isLeaf();
    }

    public Object getChild( final Object parent, final int index )
    {
        return ( ( FilterableTreeNode ) parent ).getChildAt( index );
    }

    public int getIndexOfChild( final Object parent, final Object child )
    {
        return ( ( FilterableTreeNode ) parent ).getIndex( ( FilterableTreeNode ) child );
    }

    public void valueForPathChanged( final TreePath path, final Object newValue )
    {
        // not supported
    }

    void filterPre( final Filter filter )
    {
        root.filterPre( filter );
        fireTreeStructureChanged();
    }

    void filterPost( final Filter filter )
    {
        root.filterPost( filter );
        fireTreeStructureChanged();
    }

    FilterableTreeNode find( final Filter filter )
    {
        final FilterableTreeNode node = findUnfiltered( filter, root );
        if ( node != null )
        {
            // the node corresponding to the specified qualified name may
            // not be part of the model due o filtering
            // if this is the case, reinsert the node and all its ancestors
            TreeNode filtered = null;
            for ( TreeNode child = node, parent = child.getParent();
                  parent != null;
                  child = parent, parent = child.getParent() )
            {
                if ( parent.getIndex( child ) < 0 )
                {
                    ( ( FilterableTreeNode ) parent ).unfilter( ( FilterableTreeNode ) child );
                    filtered = child;
                }
            }
            if ( filtered != null )
            {
                fireTreeNodesInserted( createPath( filtered.getParent() ), filtered );
            }
        }
        return node;
    }

    private static FilterableTreeNode findUnfiltered(
            final Filter filter, final FilterableTreeNode node
    )
    {
        if ( filter.accept( node.getUserObject() ) )
        {
            return node;
        }

        FilterableTreeNode result = null;
        for ( Iterator it = node.unfiltered(); it.hasNext(); )
        {
            result = findUnfiltered( filter, ( FilterableTreeNode ) it.next() );
            if ( result != null )
            {
                return result;
            }
        }
        return result;
    }

    static TreeNode[] createPath( final TreeNode node )
    {
        final LinkedList list = new LinkedList();
        for ( TreeNode tn = node; tn != null; tn = tn.getParent() )
        {
            list.addFirst( tn );
        }
        final TreeNode[] array = new TreeNode[ list.size() ];
        list.toArray( array );
        return array;
    }

    private void fireTreeNodesInserted( final TreeNode[] path, final TreeNode newNode )
    {
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        for ( int i = listeners.length - 2; i >= 0; i -= 2 )
        {
            if ( listeners[ i ] == TreeModelListener.class )
            {
                if ( e == null )
                {
                    final TreeNode parent = newNode.getParent();
                    e = new TreeModelEvent(
                            this,
                            path,
                            new int[]{ parent.getIndex( newNode ) },
                            new Object[]{ newNode } );
                }
                ( ( TreeModelListener ) listeners[ i + 1 ] ).treeNodesInserted( e );
            }
        }
    }

    private void fireTreeStructureChanged()
    {
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        for ( int i = listeners.length - 2; i >= 0; i -= 2 )
        {
            if ( listeners[ i ] == TreeModelListener.class )
            {
                if ( e == null )
                {
                    final int[] idcs = new int[ root.getChildCount() ];
                    for ( int j = 0; j < idcs.length; ++j )
                    {
                        idcs[ j ] = j;
                    }
                    e = new TreeModelEvent( this, new Object[]{ root }, idcs, root.toArray() );
                }
                ( ( TreeModelListener ) listeners[ i + 1 ] ).treeStructureChanged( e );
            }
        }
    }
}
