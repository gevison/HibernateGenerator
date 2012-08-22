package ge.generator.java.generated.part;

import java.util.*;

import ge.generator.java.interfaces.*;

public class InterfaceMethod extends MethodSignature
{
    public InterfaceMethod()
    {
        super();
    }

    public List<String> generateContentStrings()
    {
        List<String> retVal = new ArrayList<String>();

        if ( annotations != null )
        {
            retVal.addAll( annotations.generateContentStrings() );
        }

        String header = "";

        if ( modifiers != null )
        {
            header += modifiers.generateContentString();
        }

        if ( typeParameters != null )
        {
            header += " " + typeParameters.generateContentString();
        }

        if ( returnType instanceof ExtendedJavaObject )
        {
            ExtendedJavaObject extendedJavaObject = ( ExtendedJavaObject ) returnType;

            header += " " + extendedJavaObject.generateContentString();
        }
        else
        {
            header += " " + returnType.getSimpleName();
        }

        if ( returnArray == true )
        {
            header += "[]";
        }

        header += " " + identifier;

        if ( arguments != null )
        {
            header += arguments.generateContentString();
        }
        else
        {
            header += "()";
        }

        if ( exceptions != null )
        {
            header += " " + exceptions.generateContentString();
        }

        header += ";";

        retVal.add( header );

        return retVal;
    }
}
