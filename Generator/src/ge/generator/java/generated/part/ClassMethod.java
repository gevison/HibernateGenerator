package ge.generator.java.generated.part;

import java.util.*;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class ClassMethod extends MethodSignature
{
    private StatementCollection body = new StatementCollection();

    public ClassMethod()
    {
        super();
    }

    public void clearBody()
    {
        this.body.clear();
    }

    public String[] bodyToArray()
    {
        return this.body.toArray( new String[ this.body.size() ] );
    }

    public boolean addAllStringsToBody( Collection<String> strings )
    {
        return this.body.addAll( strings );
    }

    public int bodySize()
    {
        return this.body.size();
    }

    public boolean removeBodyString( String string )
    {
        return this.body.remove( string );
    }

    public boolean addBodyString( String string )
    {
        return this.body.add( string );
    }

    public String getBodyString( int index )
    {
        return this.body.get( index );
    }

    public boolean isBodyEmpty()
    {
        return this.body.isEmpty();
    }

    public List<String> generateContentStrings()
    {
        List<String> retVal = new ArrayList<String>();

        if ( isAnnotationsEmpty() == false )
        {
            retVal.addAll( annotations.generateContentStrings() );
        }

        String header = "";

        if ( isModifiersEmpty() == false )
        {
            header += modifiers.generateContentString();
        }

        if ( isTypeParametersEmpty() == false )
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

        if ( isArgumentsEmpty() == false )
        {
            header += arguments.generateContentString();
        }
        else
        {
            header += "()";
        }

        if ( isExceptionsEmpty() == false )
        {
            header += " " + exceptions.generateContentString();
        }

        retVal.add( header );
        retVal.add( "{" );

        if ( isBodyEmpty() == false )
        {
            List<String> contents = body.generateContentStrings();
            for ( String content : contents )
            {
                retVal.add( "\t" + content );
            }
        }

        retVal.add( "}" );

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection importCollection = super.generateImports();

        importCollection.addAll( ( HasImports ) body );

        return importCollection;
    }
}
