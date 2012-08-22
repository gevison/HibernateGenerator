package ge.generator.java.existing;

import ge.generator.java.interfaces.*;

public class ExistingEnumJavaObject extends ExistingJavaObject implements EnumJavaObject
{
    ExistingEnumJavaObject( Class type )
    {
        super( type );
    }

    public String getFullyQualifiedName()
    {
        return type.getName();
    }
}
