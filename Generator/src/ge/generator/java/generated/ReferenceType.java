package ge.generator.java.generated;

import ge.generator.java.interfaces.*;

public class ReferenceType implements NonPrimitiveJavaObject
{
    private String referenceName;

    public ReferenceType( String referenceName )
    {
        this.referenceName = referenceName;
    }

    public String getSimpleName()
    {
        return referenceName;
    }
}
