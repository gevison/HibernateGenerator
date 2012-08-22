package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "ref" )
public class Ref implements EntryTypePart, KeyPart, ListOrSetTypePart
{

    @XmlAttribute
    protected String bean;

    @XmlAttribute
    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    protected IdentifiedType local;

    @XmlAttribute
    protected String parent;

    public String getParent()
    {
        return parent;
    }

    public void setParent( String parent )
    {
        this.parent = parent;
    }

    public IdentifiedType getLocal()
    {
        return local;
    }

    public void setLocal( IdentifiedType local )
    {
        this.local = local;
    }

    public String getBean()
    {
        return bean;
    }

    public void setBean( String bean )
    {
        this.bean = bean;
    }
}
