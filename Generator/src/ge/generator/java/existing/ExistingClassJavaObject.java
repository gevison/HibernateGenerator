package ge.generator.java.existing;

import ge.generator.java.interfaces.*;

public class ExistingClassJavaObject extends ExistingJavaObject implements ClassJavaObject
{
    ExistingClassJavaObject( Class type )
    {
        super( type );
    }

    public String getFullyQualifiedName()
    {
        return type.getName();
    }
}
