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

/**
 * Builder for text based filters.
 */
class FilterBuilder
{
    public static final byte MATCH_ANYWHERE = 0;

    public static final byte MATCH_EXACTLY = 1;

    public static final byte MATCH_FROM_START = 2;

    private byte matchMode;

    private boolean caseSensitive;

    private String needle = "";

    byte getMatchMode()
    {
        return matchMode;
    }

    FilterBuilder setMatchMode( final byte matchMode )
    {
        switch ( matchMode )
        {
            case MATCH_ANYWHERE:
            case MATCH_EXACTLY:
            case MATCH_FROM_START:
                this.matchMode = matchMode;
                break;
            default:
                throw new IllegalArgumentException( "Unsupported mode: " + matchMode );
        }
        return this;
    }

    boolean isCaseSensitive()
    {
        return caseSensitive;
    }

    FilterBuilder setCaseSensitive( final boolean caseSensitive )
    {
        this.caseSensitive = caseSensitive;
        return this;
    }

    String getNeedle()
    {
        return needle;
    }

    FilterBuilder setNeedle( final String needle )
    {
        if ( needle == null )
        {
            throw new IllegalArgumentException( "null" );
        }
        this.needle = needle;
        return this;
    }

    Filter build()
    {
        if ( "".equals( needle ) )
        {
            return null;
        }
        else
        {
            return new Filter()
            {
                public boolean accept( final Displayable data )
                {
                    final String haystack = data.getDisplayName();
                    if ( haystack == null )
                    {
                        return false;
                    }
                    else
                    {
                        if ( caseSensitive )
                        {
                            return match( haystack, needle );
                        }
                        else
                        {
                            return match( haystack.toLowerCase(), needle.toLowerCase() );
                        }
                    }
                }

                boolean match( final String haystack, final String needle )
                {
                    switch ( matchMode )
                    {
                        case MATCH_ANYWHERE:
                            return haystack.indexOf( needle ) > -1;
                        case MATCH_EXACTLY:
                            return haystack.equals( needle );
                        case MATCH_FROM_START:
                            return haystack.startsWith( needle );
                        default:
                            throw new IllegalStateException();
                    }
                }
            };
        }
    }
}
