package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "collectionType" )
@XmlSeeAlso( { PropsType.class, MapType.class, ListOrSetType.class } )
public class CollectionType
{
    @XmlAttribute( name = "value-type" )
    protected Class valueType;

    public Class getValueType()
    {
        return valueType;
    }

    public void setValueType( Class valueType )
    {
        this.valueType = valueType;
    }
}
