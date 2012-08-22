package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "alias" )
public class Alias implements BeansPart
{
    @XmlAttribute( required = true )
    protected String name;

    @XmlAttribute( required = true )
    protected String alias;

    public String getAlias()
    {
        return alias;
    }

    public void setAlias( String alias )
    {
        this.alias = alias;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }
}
