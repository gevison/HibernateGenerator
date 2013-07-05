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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Tree component based on {@link JTree} that supports filtering if its
 * model is of type {@link FilterableTreeModel} as well as convenience methods
 * to retrieve {@link Displayable} user objects from tree nodes.
 */
public class FilterableTree extends JTree
{
    private static final String DELETE_ICON = "/demo/browser/resource/Delete24.png";

    private static final String OPTIONS_ICON = "/demo/browser/resource/Properties24.png";

    private static final String EXEC_ICON = "/demo/browser/resource/Play12.gif";

    private static final String NOEXEC_ICON = "/demo/browser/resource/Noexec12.gif";

    private static final ImageIcon deleteIcon = createIcon( DELETE_ICON );

    private static final ImageIcon optionsIcon = createIcon( OPTIONS_ICON );

    private static final ImageIcon execIcon = createIcon( EXEC_ICON );

    private static final ImageIcon noexecIcon = createIcon( NOEXEC_ICON );


    public FilterableTree( final TreeNode root )
    {
        super( createModel( root ) );

        setCellRenderer( new DisplayableRenderer() );
        setRootVisible( false );
        setShowsRootHandles( true );
        getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        setExpandsSelectedPaths( true );
        expandRow( 0 );
        ToolTipManager.sharedInstance().registerComponent( this );
    }

    protected void paintComponent( final Graphics g )
    {
        final Graphics2D gfx = ( Graphics2D ) g;
        final RenderingHints.Key key = RenderingHints.KEY_TEXT_ANTIALIASING;
        final Object oldAAHint = gfx.getRenderingHint( key );
        gfx.setRenderingHint( key, RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

        super.paintComponent( g );

        gfx.setRenderingHint(
                key,
                oldAAHint != null
                ? oldAAHint
                : RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT );
    }

    private void filter( final Filter filter, final boolean matchInnerNodes )
    {
        final TreeModel model = getModel();
        if ( model instanceof FilterableTreeModel )
        {
            final TreePath selection = getSelectionPath();

            if ( matchInnerNodes )
            {
                ( ( FilterableTreeModel ) model ).filterPre( filter );
            }
            else
            {
                ( ( FilterableTreeModel ) model ).filterPost( filter );
            }

            if ( filter == null )
            {
                expandRow( 0 );
            }
            else
            {
                for ( int i = 0; i < getRowCount(); ++i )
                {
                    expandRow( i );
                }
            }

            if ( selection != null )
            {
                boolean included = true;
                for ( TreeNode node = ( TreeNode ) selection.getLastPathComponent(), parent = node.getParent();
                      parent != null;
                      node = parent, parent = node.getParent() )
                {
                    if ( parent.getIndex( node ) < 0 )
                    {
                        included = false;
                        break;
                    }
                }
                if ( included )
                {
                    setSelectionPath( selection );
                }
            }
        }
    }

    public JComponent createFilterControls()
    {
        final FilterBuilder builder = new FilterBuilder();
        final boolean[] matchInnerNodes = new boolean[ 1 ];

        final JRadioButtonMenuItem cs = new JRadioButtonMenuItem( "Case sensitive" );
        final JRadioButtonMenuItem ci = new JRadioButtonMenuItem( "Case insensitive" );
        ci.setSelected( true );
        final ButtonGroup c = new ButtonGroup();
        c.add( cs );
        c.add( ci );

        final JRadioButtonMenuItem mfs = new JRadioButtonMenuItem( "Match from start" );
        final JRadioButtonMenuItem me = new JRadioButtonMenuItem( "Match exactly" );
        final JRadioButtonMenuItem ma = new JRadioButtonMenuItem( "Match anywhere" );
        ma.setSelected( true );
        final ButtonGroup m = new ButtonGroup();
        m.add( mfs );
        m.add( me );
        m.add( ma );

        final JRadioButtonMenuItem fPre = new JRadioButtonMenuItem( "Keep children if ancestors match" );
        final JRadioButtonMenuItem fPost = new JRadioButtonMenuItem( "Match leaf nodes only" );
        fPost.setSelected( true );
        final ButtonGroup f = new ButtonGroup();
        f.add( fPre );
        f.add( fPost );


        final ActionListener filter = new ActionListener()
        {
            public void actionPerformed( final ActionEvent e )
            {
                final Object src = e.getSource();
                if ( src == cs )
                {
                    filter( builder.setCaseSensitive( true ).build(), matchInnerNodes[ 0 ] );
                }
                else if ( src == ci )
                {
                    filter( builder.setCaseSensitive( false ).build(), matchInnerNodes[ 0 ] );
                }
                else if ( src == mfs )
                {
                    filter( builder.setMatchMode( FilterBuilder.MATCH_FROM_START ).build(), matchInnerNodes[ 0 ] );
                }
                else if ( src == me )
                {
                    filter( builder.setMatchMode( FilterBuilder.MATCH_EXACTLY ).build(), matchInnerNodes[ 0 ] );
                }
                else if ( src == ma )
                {
                    filter( builder.setMatchMode( FilterBuilder.MATCH_ANYWHERE ).build(), matchInnerNodes[ 0 ] );
                }
                else if ( src == fPre )
                {
                    matchInnerNodes[ 0 ] = true;
                    filter( builder.build(), matchInnerNodes[ 0 ] );
                }
                else if ( src == fPost )
                {
                    matchInnerNodes[ 0 ] = false;
                    filter( builder.build(), matchInnerNodes[ 0 ] );
                }
            }
        };

        cs.addActionListener( filter );
        ci.addActionListener( filter );
        mfs.addActionListener( filter );
        me.addActionListener( filter );
        ma.addActionListener( filter );
        fPre.addActionListener( filter );
        fPost.addActionListener( filter );

        final JPopupMenu jpm = new JPopupMenu();
        jpm.add( cs );
        jpm.add( ci );
        jpm.addSeparator();

        jpm.add( mfs );
        jpm.add( me );
        jpm.add( ma );
        jpm.addSeparator();

        jpm.add( fPost );
        jpm.add( fPre );

        final PopupHandler handler = new PopupHandler( jpm );
        final JButton options = new JButton();
        options.setToolTipText( "Filter Settings" );
        options.addActionListener( handler );
        options.addMouseListener( handler );
        if ( optionsIcon == null )
        {
            options.setText( "?" );
        }
        else
        {
            options.setIcon( optionsIcon );
            options.setMargin( new Insets( 0, 0, 0, 0 ) );
        }

        final JTextField jtf = new JTextField( 10 );
        jtf.setPreferredSize( new Dimension( jtf.getPreferredSize().width, options.getPreferredSize().height ) );
        jtf.setText( "Filter" );
        jtf.setToolTipText( "Filter Criterion" );
        jtf.getDocument().addDocumentListener( new DocumentListener()
        {
            public void changedUpdate( final DocumentEvent e )
            {
                update();
            }

            public void insertUpdate( final DocumentEvent e )
            {
                update();
            }

            public void removeUpdate( final DocumentEvent e )
            {
                update();
            }

            private void update()
            {
                filter( ( builder.setNeedle( jtf.getText() ) ).build(), matchInnerNodes[ 0 ] );
            }
        } );

        final JPanel pane = new JPanel( new BorderLayout() );
        pane.add( options );
        pane.add( jtf );
        final JButton clear = new JButton();
        if ( deleteIcon == null )
        {
            clear.setText( "x" );
        }
        else
        {
            clear.setIcon( deleteIcon );
            clear.setMargin( new Insets( 0, 0, 0, 0 ) );
        }
        clear.setToolTipText( "Clear Filter" );
        clear.addActionListener( new ActionListener()
        {
            public void actionPerformed( final ActionEvent e )
            {
                jtf.setText( "" );
            }
        } );
        pane.add( clear );
        return pane;
    }

    public TreeNode findNodeForFirstExecutable()
    {
        final TreeModel model = getModel();
        return findNodeForFirstExecutable( ( TreeNode ) model.getRoot() );
    }

    private static TreeNode findNodeForFirstExecutable( final TreeNode node )
    {
        final Displayable displayable = getDisplayable( node );
        if ( displayable != null && displayable.isDemo() && displayable.isExecutable() )
        {
            return node;
        }

        if ( node.getChildCount() > 0 )
        {
            for ( Enumeration en = node.children(); en.hasMoreElements(); )
            {
                final TreeNode child = findNodeForFirstExecutable( ( TreeNode ) en.nextElement() );
                if ( child != null )
                {
                    return child;
                }
            }
        }

        return null;
    }

    public TreeNode findNodeForNextExecutable()
    {
        final TreePath s = getSelectionPath();
        if ( s == null )
        {
            return null;
        }
        else
        {
            final LinkedHashMap node2successor = new LinkedHashMap();
            final LinkedList predecessors = new LinkedList();
            buildSuccessorMap( ( TreeNode ) getModel().getRoot(), predecessors, node2successor );
            if ( !node2successor.isEmpty() )
            {
                final Object node = ( ( Map.Entry ) node2successor.entrySet().iterator().next() ).getValue();
                for ( Iterator it = predecessors.iterator(); it.hasNext(); )
                {
                    node2successor.put( it.next(), node );
                }
            }
            return ( TreeNode ) node2successor.get( s.getLastPathComponent() );
        }
    }

    private static void buildSuccessorMap(
            final TreeNode node,
            final List predecessors,
            final Map node2successor
    )
    {
        final Displayable displayable = getDisplayable( node );
        if ( displayable != null )
        {
            if ( displayable.isDemo() && displayable.isExecutable() )
            {
                for ( Iterator it = predecessors.iterator(); it.hasNext(); )
                {
                    node2successor.put( it.next(), node );
                }
                predecessors.clear();
            }
        }

        predecessors.add( node );

        if ( node.getChildCount() > 0 )
        {
            for ( Enumeration en = node.children(); en.hasMoreElements(); )
            {
                buildSuccessorMap( ( TreeNode ) en.nextElement(), predecessors, node2successor );
            }
        }
    }

    public TreeNode findNodeFor( final String qn )
    {
        final TreeModel model = getModel();
        if ( model instanceof FilterableTreeModel )
        {
            return ( ( FilterableTreeModel ) model ).find( new Filter()
            {
                public boolean accept( final Displayable data )
                {
                    return data != null && qn.equals( data.getQualifiedName() );
                }
            } );
        }
        else
        {
            return findNodeFor( qn, ( TreeNode ) model.getRoot() );
        }
    }

    private static TreeNode findNodeFor( final String qn, final TreeNode node )
    {
        final Displayable displayable = getDisplayable( node );
        if ( displayable != null && qn.equals( displayable.getQualifiedName() ) )
        {
            return node;
        }

        if ( node.getChildCount() > 0 )
        {
            TreeNode result = null;
            for ( Enumeration en = node.children(); en.hasMoreElements(); )
            {
                result = findNodeFor( qn, ( TreeNode ) en.nextElement() );
                if ( result != null )
                {
                    return result;
                }
            }
            return result;
        }
        else
        {
            return null;
        }
    }


    public Demo getSelectedDemo()
    {
        final Displayable d = getSelectedDisplayable();
        if ( d instanceof Demo )
        {
            return ( Demo ) d;
        }
        else
        {
            return null;
        }
    }

    public Displayable getSelectedDisplayable()
    {
        final TreePath s = getSelectionPath();
        if ( s == null )
        {
            return null;
        }
        else
        {
            return getDisplayable( ( TreeNode ) s.getLastPathComponent() );
        }
    }


    public static Displayable getDisplayable( final TreeNode node )
    {
        if ( node instanceof FilterableTreeNode )
        {
            return ( ( FilterableTreeNode ) node ).getUserObject();
        }
        else if ( node instanceof DefaultMutableTreeNode )
        {
            final Object uo = ( ( DefaultMutableTreeNode ) node ).getUserObject();
            if ( uo instanceof Displayable )
            {
                return ( Displayable ) uo;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    private static TreeModel createModel( final TreeNode root )
    {
        if ( root instanceof FilterableTreeNode )
        {
            return new FilterableTreeModel( ( FilterableTreeNode ) root );
        }
        else
        {
            return new DefaultTreeModel( root, false );
        }
    }

    public static TreePath createPath( final TreeNode node )
    {
        if ( node instanceof DefaultMutableTreeNode )
        {
            return new TreePath( ( ( DefaultMutableTreeNode ) node ).getPath() );
        }
        else
        {
            return new TreePath( FilterableTreeModel.createPath( node ) );
        }
    }


    private static ImageIcon createIcon( final String path )
    {
        try
        {
            final URL resource = FilterableTree.class.getResource( path );
            if ( resource == null )
            {
                return null;
            }
            else
            {
                return new ImageIcon( resource );
            }
        }
        catch ( Throwable t )
        {
            // this method is used to initialize static fields
            // any uncaught error or exception that is thrown here prevents class
            // loading and therefore crashes the whole app with no chance for
            // recovery
            return null;
        }
    }

    private static final class DisplayableRenderer implements TreeCellRenderer
    {
        private static final Font fontDefault = new Font( "SansSerif", Font.PLAIN, 12 );

        private static final Font fontBold = new Font( "SansSerif", Font.BOLD, 12 );

        private static final Font fontItalic = new Font( "SansSerif", Font.ITALIC, 12 );

        private final DefaultTreeCellRenderer delegate;

        private final Color colorBackground;

        private final Color colorBorder;

        private final Color colorSelection;

        private final Color colorTextSelection;

        DisplayableRenderer()
        {
            this.delegate = new DefaultTreeCellRenderer();
            this.delegate.setIcon( null );
            this.delegate.setOpenIcon( null );
            this.delegate.setLeafIcon( null );
            this.delegate.setClosedIcon( null );
            this.colorBackground = delegate.getBackgroundSelectionColor();
            this.colorBorder = delegate.getBorderSelectionColor();
            this.colorSelection = delegate.getTextNonSelectionColor();
            this.colorTextSelection = delegate.getTextSelectionColor();
        }

        public Component getTreeCellRendererComponent(
                final JTree tree,
                Object value,
                final boolean selected,
                final boolean expanded,
                final boolean leaf,
                final int row,
                final boolean hasFocus
        )
        {
            delegate.setBackgroundSelectionColor( colorBackground );
            delegate.setBorderSelectionColor( colorBorder );
            delegate.setTextNonSelectionColor( colorSelection );
            delegate.setTextSelectionColor( colorTextSelection );

            //try to get icon for executable demos

            //catch all cases not handled below (folder nodes etc.)
            if ( !leaf )
            {
                delegate.setFont( fontBold );
            }
            else
            {
                delegate.setFont( fontItalic );
            }

            delegate.setLeafIcon( null );

            if ( value instanceof FilterableTreeNode )
            {
                value = ( ( FilterableTreeNode ) value ).getUserObject();
            }
            else if ( value instanceof DefaultMutableTreeNode )
            {
                value = ( ( DefaultMutableTreeNode ) value ).getUserObject();
            }
            if ( value instanceof Displayable )
            {
                final Displayable displayable = ( Displayable ) value;
                value = displayable.getDisplayName();
                delegate.setToolTipText( displayable.getSummary() );
                if ( displayable.isDemo() )
                {
                    if ( displayable.isExecutable() )
                    {
                        //this is always safe, even for null icons
                        delegate.setLeafIcon( execIcon );
                        delegate.setFont( fontDefault );

                    }
                    else
                    {
                        delegate.setLeafIcon( noexecIcon );
                        delegate.setFont( fontItalic );
                    }
                }
            }
            return delegate.getTreeCellRendererComponent(
                    tree, value, selected, expanded, leaf, row, hasFocus );
        }
    }

    private static final class PopupHandler
            extends MouseAdapter
            implements ActionListener, PopupMenuListener
    {
        private long lastPress;

        private long lastHide;

        private final JPopupMenu jpm;

        PopupHandler( final JPopupMenu jpm )
        {
            this.jpm = jpm;
            this.jpm.addPopupMenuListener( this );
        }

        public void actionPerformed( final ActionEvent e )
        {
            if ( lastPress - lastHide < 10 )
            {
                jpm.setVisible( false );
            }
            else
            {
                final JButton src = ( JButton ) e.getSource();
                jpm.show( src, 0, src.getHeight() );
            }
        }

        public void mousePressed( final MouseEvent e )
        {
            lastPress = e.getWhen();
        }

        public void popupMenuCanceled( final PopupMenuEvent e )
        {
        }

        public void popupMenuWillBecomeInvisible( final PopupMenuEvent e )
        {
            lastHide = System.currentTimeMillis();
        }

        public void popupMenuWillBecomeVisible( final PopupMenuEvent e )
        {
        }
    }
}
