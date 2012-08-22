package ge.generator.java.generated;

import java.util.*;

import ge.generator.java.generated.collection.*;
import ge.generator.java.interfaces.*;

public class GeneratedExtendedInterfaceJavaObject implements ExtendedInterfaceJavaObject
{
    private NonReferenceJavaObject type;
    private NonPrimitiveJavaObjectCollection extensions = new NonPrimitiveJavaObjectCollection();

    public GeneratedExtendedInterfaceJavaObject(  )
    {
        super();
    }

    public NonReferenceJavaObject getType()
    {
        return type;
    }

    public void setType( NonReferenceJavaObject type )
    {
        this.type = type;
    }

    public void clearExtensions()
    {
        this.extensions.clear();
    }

    public NonPrimitiveJavaObject[] toExtensionArray()
    {
        return this.extensions.toArray( new NonPrimitiveJavaObject[ this.extensions.size() ] );
    }

    public boolean addExtensionsAll( Collection<NonPrimitiveJavaObject> Extensioned )
    {
        return this.extensions.addAll( Extensioned );
    }

    public int ExtensionsSize()
    {
        return this.extensions.size();
    }

    public boolean removeExtension( NonPrimitiveJavaObject Extensioned )
    {
        return this.extensions.remove( Extensioned );
    }

    public boolean addExtension( NonPrimitiveJavaObject Extensioned )
    {
        return this.extensions.add( Extensioned );
    }

    public NonPrimitiveJavaObject getExtension( int index )
    {
        return this.extensions.get( index );
    }

    public boolean isExtensionsEmpty()
    {
        return this.extensions.isEmpty();
    }

    public String generateContentString()
    {
        String retVal = getSimpleName();

        if ( ( extensions != null ) && ( extensions.isEmpty() == false ) )
        {
            String values = "";

            for ( NonPrimitiveJavaObject extension : extensions )
            {
                if ( values.length() != 0 )
                {
                    values += ", ";
                }

                if ( extension instanceof ExtendedJavaObject )
                {
                    ExtendedJavaObject extendedJavaObject = ( ExtendedJavaObject ) extension;

                    values += extendedJavaObject.generateContentString();
                }
                else
                {
                    values += extension.getSimpleName();
                }
            }

            retVal += "<" + values + ">";
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.add( getFullyQualifiedName() );

        retVal.addAll( extensions );

        return retVal;
    }

    public String getFullyQualifiedName()
    {
        return type.getFullyQualifiedName();
    }

    public String getSimpleName()
    {
        return type.getSimpleName();
    }
}
