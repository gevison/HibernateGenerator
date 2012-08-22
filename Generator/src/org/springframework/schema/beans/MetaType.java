package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "metaType" )
@XmlRootElement( name = "meta" )
public class MetaType implements BeanPart
{

    @XmlAttribute( required = true )
    protected String key;

    @XmlAttribute( required = true )
    protected String value;

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( String key )
    {
        this.key = key;
    }
}
