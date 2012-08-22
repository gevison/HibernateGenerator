package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "identifiedType" )
@XmlSeeAlso( { Bean.class } )
public abstract class IdentifiedType
{

    @XmlAttribute
    @XmlJavaTypeAdapter( CollapsedStringAdapter.class )
    @XmlID
    @XmlSchemaType( name = "ID" )
    protected String id;

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }
}
