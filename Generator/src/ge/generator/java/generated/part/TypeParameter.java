package ge.generator.java.generated.part;

import ge.generator.java.generated.*;
import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class TypeParameter implements SingleLineContent
{
    private ReferenceType identifier;

    private NonPrimitiveJavaObject extendsType;

    public TypeParameter()
    {
    }

    public NonPrimitiveJavaObject getExtendsType()
    {
        return extendsType;
    }

    public void setExtendsType( NonPrimitiveJavaObject extendsType )
    {
        this.extendsType = extendsType;
    }

    public ReferenceType getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier( ReferenceType identifier )
    {
        this.identifier = identifier;
    }

    public String generateContentString()
    {
        String retVal = identifier.getSimpleName();

        if ( extendsType != null )
        {
            retVal += " extends " + extendsType.getSimpleName();
        }
        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( extendsType != null )
        {
            retVal.add( extendsType );
        }

        return retVal;
    }
}
