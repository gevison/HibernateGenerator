package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "argType" } )
@XmlRootElement( name = "replaced-method" )
public class ReplacedMethod implements BeanPart
{
    @XmlElement( name = "arg-type" )
    protected List<ArgType> argType = new ArrayList<ArgType>();

    @XmlAttribute
    protected String name;

    @XmlAttribute
    protected String replacer;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getReplacer()
    {
        return replacer;
    }

    public void setReplacer( String replacer )
    {
        this.replacer = replacer;
    }

    public boolean addArgType( ArgType value )
    {
        return argType.add( value );
    }

    public boolean containsArgType( ArgType value )
    {
        return argType.contains( value );
    }

    public ArgType getArgType( int index )
    {
        return argType.get( index );
    }

    public boolean isArgTypesEmpty()
    {
        return argType.isEmpty();
    }

    public boolean removeArgType( ArgType value )
    {
        return argType.remove( value );
    }

    public int argTypesSize()
    {
        return argType.size();
    }

    public ArgType[] argTypesToArray(  )
    {
        return argType.toArray( new ArgType[ argType.size()] );
    }
}
