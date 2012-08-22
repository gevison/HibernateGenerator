package ge.generator.java.existing;

import ge.generator.java.interfaces.*;

public class ExistingInterfaceJavaObject extends ExistingJavaObject implements InterfaceJavaObject
{
    ExistingInterfaceJavaObject( Class type )
    {
        super( type );
    }

    public String getFullyQualifiedName()
    {
        return type.getName();
    }
}
