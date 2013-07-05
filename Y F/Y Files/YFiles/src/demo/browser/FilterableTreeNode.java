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

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A tree node that supports filtering its children.
 */
class FilterableTreeNode implements TreeNode
{
    private static final FilterableTreeNode[] EMTPY = new FilterableTreeNode[ 0 ];

    private final Displayable data;

    private final boolean allowsChildren;

    private final List children;

    private final List filtered;

    private FilterableTreeNode parent;

    FilterableTreeNode( final Displayable data )
    {
        this.data = data;
        this.allowsChildren = !( data instanceof Demo );

        if ( allowsChildren )
        {
            children = new ArrayList( 4 );
            filtered = new ArrayList( 4 );
        }
        else
        {
            children = null;
            filtered = null;
        }
    }

    Displayable getUserObject()
    {
        return data;
    }

    public Enumeration children()
    {
        if ( allowsChildren )
        {
            return new IteratorAdaptor( filtered.iterator() );
        }
        else
        {
            return new Enumeration()
            {
                public boolean hasMoreElements()
                {
                    return false;
                }

                public Object nextElement()
                {
                    throw new NoSuchElementException();
                }
            };
        }
    }

    public int getChildCount()
    {
        if ( allowsChildren )
        {
            return filtered.size();
        }
        else
        {
            return 0;
        }
    }

    public boolean getAllowsChildren()
    {
        return allowsChildren;
    }

    public boolean isLeaf()
    {
        return getChildCount() == 0;
    }

    public TreeNode getParent()
    {
        return parent;
    }

    public TreeNode getChildAt( final int childIndex )
    {
        if ( allowsChildren )
        {
            return ( TreeNode ) filtered.get( childIndex );
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException( "node has no children" );
        }
    }

    public int getIndex( final TreeNode node )
    {
        if ( node == null )
        {
            return -1;
        }
        else
        {
            if ( allowsChildren )
            {
                if ( node.getParent() == this )
                {
                    return filtered.indexOf( node );
                }
                else
                {
                    return -1;
                }
            }
            else
            {
                return -1;
            }
        }
    }

    void add( final FilterableTreeNode child )
    {
        child.parent = this;
        children.add( child );
        filtered.add( child );
    }

    void removeAllChildren()
    {
        for ( Iterator it = children.iterator(); it.hasNext(); )
        {
            final FilterableTreeNode next = ( FilterableTreeNode ) it.next();
            next.parent = null;
        }
        children.clear();
        filtered.clear();
    }

    boolean filterPost( final Filter filter )
    {
        if ( allowsChildren )
        {
            if ( children.isEmpty() )
            {
                return filter == null || filter.accept( data );
            }
            else
            {
                filtered.clear();
                for ( Iterator it = children.iterator(); it.hasNext(); )
                {
                    final FilterableTreeNode next = ( FilterableTreeNode ) it.next();
                    if ( next.filterPost( filter ) )
                    {
                        filtered.add( next );
                    }
                }
                return !filtered.isEmpty();
            }
        }
        else
        {
            return filter == null || filter.accept( data );
        }
    }

    boolean filterPre( final Filter filter )
    {
        final boolean accepted = filter == null || filter.accept( data );
        if ( allowsChildren )
        {
            if ( children.isEmpty() )
            {
                return accepted;
            }
            else
            {
                filtered.clear();
                final Filter tmp = accepted ? null : filter;
                for ( Iterator it = children.iterator(); it.hasNext(); )
                {
                    final FilterableTreeNode next = ( FilterableTreeNode ) it.next();
                    if ( next.filterPre( tmp ) )
                    {
                        filtered.add( next );
                    }
                }
                return accepted || !filtered.isEmpty();
            }
        }
        else
        {
            return accepted;
        }
    }

    FilterableTreeNode[] toArray()
    {
        if ( allowsChildren )
        {
            final FilterableTreeNode[] array = new FilterableTreeNode[ filtered.size() ];
            filtered.toArray( array );
            return array;
        }
        else
        {
            return EMTPY;
        }
    }

    Iterator unfiltered()
    {
        if ( allowsChildren )
        {
            return new ViewIterator( children.iterator() );
        }
        else
        {
            return new Iterator()
            {
                public void remove()
                {
                    throw new UnsupportedOperationException();
                }

                public boolean hasNext()
                {
                    return false;
                }

                public Object next()
                {
                    throw new NoSuchElementException();
                }
            };
        }
    }

    void unfilter( final FilterableTreeNode node )
    {
        if ( filtered.isEmpty() )
        {
            filtered.add( node );
        }
        else
        {
            final HashMap idx = new HashMap();
            int i = 0;
            for ( Iterator it = children.iterator(); it.hasNext(); ++i )
            {
                idx.put( it.next(), new Integer( i ) );
            }

            final int cIdx = ( ( Integer ) idx.get( node ) ).intValue();
            int add = 0;
            for ( Iterator it = filtered.iterator(); it.hasNext(); ++add )
            {
                final int tmp = ( ( Integer ) idx.get( it.next() ) ).intValue();
                if ( tmp > cIdx )
                {
                    break;
                }
            }
            filtered.add( add, node );
        }
    }

    private static final class IteratorAdaptor implements Enumeration
    {
        private final Iterator it;

        IteratorAdaptor( final Iterator it )
        {
            this.it = it;
        }

        public boolean hasMoreElements()
        {
            return it.hasNext();
        }

        public Object nextElement()
        {
            return it.next();
        }
    }

    private static final class ViewIterator implements Iterator
    {
        private final Iterator it;

        ViewIterator( final Iterator it )
        {
            this.it = it;
        }

        public boolean hasNext()
        {
            return it.hasNext();
        }

        public Object next()
        {
            return it.next();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
