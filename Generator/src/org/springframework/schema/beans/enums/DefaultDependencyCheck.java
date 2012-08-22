package org.springframework.schema.beans.enums;

import javax.xml.bind.annotation.*;

@XmlEnum
public enum DefaultDependencyCheck
{
    @XmlEnumValue( "default" )
    DEFAULT,

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

    public static DefaultDependencyCheck fromValue( String value )
    {
        return XmlEnumHelper.fromValue(DefaultDependencyCheck.class,value);
    }
}
