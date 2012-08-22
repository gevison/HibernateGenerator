package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "idref" )
public class Idref implements EntryTypePart, KeyPart, ListOrSetTypePart
{

    @XmlAttribute
    protected String bean;

    @XmlAttribute
    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    protected IdentifiedType local;

    public String getBean()
    {
        return bean;
    }

    public void setBean( String bean )
    {
        this.bean = bean;
    }

    public IdentifiedType getLocal()
    {
        return local;
    }

    public void setLocal( IdentifiedType local )
    {
        this.local = local;
    }
}
