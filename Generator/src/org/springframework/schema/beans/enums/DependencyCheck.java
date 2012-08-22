package org.springframework.schema.beans.enums;

import javax.xml.bind.annotation.*;

@XmlEnum
public enum DependencyCheck
{
    @XmlEnumValue( "none" )
    NONE,

    @XmlEnumValue( "simple" )
    SIMPLE,

    @XmlEnumValue( "objects" )
    OBJECTS,

    @XmlEnumValue( "all" )
    ALL;

    public String value()
    {
        return XmlEnumHelper.value(this.getClass(),name());
    }

    public static DefaultableBoolean fromValue( String value )
    {
        return XmlEnumHelper.fromValue(DefaultableBoolean.class,value);
    }
}
