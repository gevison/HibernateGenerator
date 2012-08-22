package ge.generator.java.existing;

import ge.generator.java.interfaces.*;

public class ExistingAnnotationJavaObject extends ExistingJavaObject implements AnnotationJavaObject
{
    ExistingAnnotationJavaObject( Class type )
    {
        super( type );
    }

    public String getFullyQualifiedName()
    {
        return type.getName();
    }
}
