package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "attribute" } )
@XmlRootElement( name = "qualifier" )
public class Qualifier implements BeanPart
{
    protected List<MetaType> attribute = new ArrayList<MetaType>();

    @XmlAttribute
    protected String type;

    @XmlAttribute
    protected String value;

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public boolean addAttribute( MetaType value )
    {
        return attribute.add( value );
    }

    public boolean containsAttribute( MetaType value )
    {
        return attribute.contains( value );
    }

    public MetaType getAttribute( int index )
    {
        return attribute.get( index );
    }

    public boolean isAttributesEmpty()
    {
        return attribute.isEmpty();
    }

    public boolean removeAttribute( MetaType value )
    {
        return attribute.remove( value );
    }

    public int attributesSize()
    {
        return attribute.size();
    }

    public MetaType[] attributesToArray(  )
    {
        return attribute.toArray( new MetaType[ attribute.size()] );
    }
}
