package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "content" } )
@XmlRootElement( name = "arg-type" )
public class ArgType
{
    @XmlMixed
    protected List<String> content = new ArrayList<String>();

    @XmlAttribute
    protected String match;

    public String getMatch()
    {
        return match;
    }

    public void setMatch( String match )
    {
        this.match = match;
    }

    public boolean addContent( String value )
    {
        return content.add( value );
    }

    public boolean containsContent( String value )
    {
        return content.contains( value );
    }

    public String getContent( int index )
    {
        return content.get( index );
    }

    public boolean isContentsEmpty()
    {
        return content.isEmpty();
    }

    public boolean removeContent( String value )
    {
        return content.remove( value );
    }

    public int contentsSize()
    {
        return content.size();
    }

    public String[] contentsToArray(  )
    {
        return content.toArray( new String[ content.size()] );
    }
}
