package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "content" } )
@XmlRootElement( name = "value" )
public class Value implements EntryTypePart, ListOrSetTypePart
{

    @XmlMixed
    protected List<String> content = new ArrayList<String>();

    @XmlAttribute
    protected String type;

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
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
