package ge.generator.java.generated.part;

import java.util.*;

import ge.generator.java.generated.*;
import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.interfaces.*;

public class Argument implements SingleLineContent
{
    private AnnotationCollection annotations = new AnnotationCollection();

    private ModifierCollection modifiers = new ModifierCollection();

    private JavaObject type;

    private boolean isArray;

    private String name;

    public Argument()
    {
    }

    public boolean isArray()
    {
        return isArray;
    }

    public void setArray( boolean array )
    {
        isArray = array;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public JavaObject getType()
    {
        return type;
    }

    public void setType( JavaObject type )
    {
        this.type = type;
    }

    public void clearAnnotations()
    {
        this.annotations.clear();
    }

    public Annotation[] toAnnotationArray()
    {
        return this.annotations.toArray( new Annotation[ this.annotations.size() ] );
    }

    public boolean addAnnotationsAll( Collection<Annotation> annotations )
    {
        return this.annotations.addAll( annotations );
    }

    public int annotationsSize()
    {
        return this.annotations.size();
    }

    public boolean removeAnnotation( Annotation annotation )
    {
        return this.annotations.remove( annotation );
    }

    public boolean addAnnotation( Annotation annotation )
    {
        return this.annotations.add( annotation );
    }

    public Annotation getAnnotation( int index )
    {
        return this.annotations.get( index );
    }

    public boolean isAnnotationsEmpty()
    {
        return this.annotations.isEmpty();
    }

    public void clearModifiers()
    {
        this.modifiers.clear();
    }

    public Modifier[] toModifierArray()
    {
        return this.modifiers.toArray( new Modifier[ this.modifiers.size() ] );
    }

    public boolean addModifiersAll( Collection<Modifier> modifiers )
    {
        return this.modifiers.addAll( modifiers );
    }

    public int modifiersSize()
    {
        return this.modifiers.size();
    }

    public boolean removeModifier( Modifier modifier )
    {
        return this.modifiers.remove( modifier );
    }

    public boolean addModifier( Modifier modifier )
    {
        return this.modifiers.add( modifier );
    }

    public Modifier getModifier( int index )
    {
        return this.modifiers.get( index );
    }

    public boolean isModifiersEmpty()
    {
        return this.modifiers.isEmpty();
    }

    public String generateContentString()
    {
        String retVal = "";

        if ( ( annotations != null ) && ( annotations.isEmpty() == false ) )
        {
            String annotationString = "";

            for ( Annotation annotation : annotations )
            {
                if ( annotationString.length() != 0 )
                    annotationString += " ";
                retVal += annotation.generateContentString();
            }
        }

        if ( ( modifiers != null ) && ( modifiers.size() != 0 ) )
        {
            for ( Modifier modifier : modifiers )
            {
                retVal += " ";
                retVal += modifier.name().toLowerCase();
            }
        }

        if ( type instanceof ExtendedJavaObject )
        {
            ExtendedJavaObject extendedJavaObject = ( ExtendedJavaObject ) type;

            retVal += " " + extendedJavaObject.generateContentString();
        }
        else
        {
            retVal += " " + type.getSimpleName();
        }

        if ( isArray == true )
        {
            retVal += "[]";
        }

        retVal += " " + name;

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.addAll( annotations );
        retVal.add( type );

        return retVal;
    }
}
