package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "propsType", propOrder = { "props" } )
@XmlSeeAlso( { Props.class } )
public class PropsType extends CollectionType
{

    protected List<Prop> props = new ArrayList<Prop>();

    public boolean addProp( Prop prop )
    {
        return props.add( prop );
    }

    public Prop getProp( int index )
    {
        return props.get( index );
    }

    public Prop[] toPropsArray()
    {
        return props.toArray(new Prop[props.size()]);
    }

    public boolean isPropsEmpty()
    {
        return props.isEmpty();
    }

    public int propsSize()
    {
        return props.size();
    }

    public boolean removeProp( Prop prop )
    {
        return props.remove( prop );
    }

    public boolean containsProp( Prop prop )
    {
        return props.contains( prop );
    }
}
