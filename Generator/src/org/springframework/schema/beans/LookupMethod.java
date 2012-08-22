package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

import org.springframework.schema.beans.adapters.*;
import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "lookup-method" )
public class LookupMethod implements BeanPart
{

    @XmlAttribute
    protected String name;

    @XmlAttribute
    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    protected Bean bean;

    public Bean getBean()
    {
        return bean;
    }

    public void setBean( Bean bean )
    {
        this.bean = bean;
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
