package org.springframework.schema.beans.enums;

import javax.xml.bind.annotation.*;
import java.lang.reflect.*;

public class XmlEnumHelper
{
    public static String value( Class<? extends Enum> aClass, String name )
    {
        try
        {
            Field field = aClass.getField( name );

            XmlEnumValue annotation = field.getAnnotation( XmlEnumValue.class );

            return annotation.value();
        }
        catch ( NoSuchFieldException e )
        {
            throw new IllegalStateException();
        }
    }

    public static <ENUM extends Enum> ENUM fromValue( Class<ENUM> aClass, String value )
    {
        try
        {
            for ( ENUM c : aClass.getEnumConstants() )
            {
                Field field = aClass.getField( c.name() );

                XmlEnumValue annotation = field.getAnnotation( XmlEnumValue.class );

                if ( annotation.value().equals( value ) )
                {
                    return c;
                }
            }
        }
        catch ( NoSuchFieldException e )
        {
        }
        throw new IllegalArgumentException( value );
    }
}
