package org.springframework.schema.beans.enums;

import javax.xml.bind.annotation.*;

@XmlEnum
public enum DefaultAutowire
{
    @XmlEnumValue( "default" )
    DEFAULT,

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

    public static DefaultAutowire fromValue( String value )
    {
        return XmlEnumHelper.fromValue(DefaultAutowire.class,value);
    }
}
