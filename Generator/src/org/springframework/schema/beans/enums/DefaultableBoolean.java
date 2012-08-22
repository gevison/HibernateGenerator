package org.springframework.schema.beans.enums;

import javax.xml.bind.annotation.*;
import java.lang.reflect.*;

@XmlType( name = "defaultable-boolean" )
@XmlEnum
public enum DefaultableBoolean
{
    @XmlEnumValue( "default" )
    DEFAULT,
    @XmlEnumValue( "true" )
    TRUE,
    @XmlEnumValue( "false" )
    FALSE;

    public String value()
    {
        return XmlEnumHelper.value(this.getClass(),name());
    }

    public static DefaultableBoolean fromValue( String value )
    {
        return XmlEnumHelper.fromValue(DefaultableBoolean.class,value);
    }
}
