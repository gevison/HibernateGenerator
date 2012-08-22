package ge.generator.java.generated.part;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public abstract class MethodSignature extends AbstractMethod implements Member
{
    protected JavaObject returnType;
    protected boolean returnArray;
    protected String identifier;

    public MethodSignature()
    {
        super();
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier( String identifier )
    {
        this.identifier = identifier;
    }

    public boolean isReturnArray()
    {
        return returnArray;
    }

    public void setReturnArray( boolean returnArray )
    {
        this.returnArray = returnArray;
    }

    public JavaObject getReturnType()
    {
        return returnType;
    }

    public void setReturnType( JavaObject returnType )
    {
        this.returnType = returnType;
    }

    @Override
    public ImportCollection generateImports()
    {
        ImportCollection retVal = super.generateImports();

        retVal.add( returnType );

        return retVal;
    }
}
