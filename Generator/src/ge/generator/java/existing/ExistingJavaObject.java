package ge.generator.java.existing;

import ge.generator.java.generated.collection.*;
import ge.generator.java.interfaces.*;

public class ExistingJavaObject implements JavaObject
{
    protected Class type;

    protected ExistingJavaObject( Class type )
    {
        this.type = type;
    }

    public static NonPrimitiveJavaObject instance( Class type )
    {
        if ( type.isPrimitive() == true )
        {
            throw new IllegalArgumentException();
        }
        else if ( type.isEnum() == true )
        {
            return new ExistingEnumJavaObject( type );
        }
        else if ( type.isAnnotation() == true )
        {
            return new ExistingAnnotationJavaObject( type );
        }
        else if ( type.isInterface() == true )
        {
            return new ExistingInterfaceJavaObject( type );
        }
        else
        {
            return new ExistingClassJavaObject( type );
        }
    }

    public static ExistingJavaObject instance( Class type, NonPrimitiveJavaObjectCollection extensions )
    {
        if ( type.isPrimitive() == true )
        {
            throw new IllegalArgumentException();
        }
        else if ( type.isEnum() == true )
        {
            throw new IllegalArgumentException();
        }
        else if ( type.isAnnotation() == true )
        {
            throw new IllegalArgumentException();
        }
        else if ( type.isInterface() == true )
        {
            return new ExistingExtendedInterfaceJavaObject( type, extensions );
        }
        else
        {
            return new ExistingExtendedClassJavaObject( type, extensions );
        }
    }

    public String getSimpleName()
    {
        return type.getSimpleName();
    }
}
