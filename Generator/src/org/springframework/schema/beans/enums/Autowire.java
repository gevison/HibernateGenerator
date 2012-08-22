package org.springframework.schema.beans.enums;

import javax.xml.bind.annotation.*;

@XmlEnum
public enum Autowire
{
    @XmlEnumValue( "no" )
    NO,

    @XmlEnumValue( "byName" )
    BY_NAME,

    @XmlEnumValue( "byType" )
    BY_TYPE,

    @XmlEnumValue( "constructor" )
    CONSTRUCTOR,

    @XmlEnumValue( "autodetect" )
    AUTODETECT;

    public String value()
    {
        return XmlEnumHelper.value(this.getClass(),name());
    }

    public static Autowire fromValue( String value )
    {
        return XmlEnumHelper.fromValue(Autowire.class,value);
    }
}
