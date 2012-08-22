package ge.generator.java.generated.part;

import java.util.*;

import ge.generator.java.generated.*;
import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.interfaces.*;

@SuppressWarnings( "unused" )
public abstract class AbstractMethod extends ParentObject implements HasImports
{
    protected ModifierCollection modifiers = new ModifierCollection();

    protected AnnotationCollection annotations = new AnnotationCollection();

    protected TypeParameterCollection typeParameters = new TypeParameterCollection();

    protected ArgumentCollection arguments = new ArgumentCollection();

    protected ThrowsCollection exceptions = new ThrowsCollection();

    protected AbstractMethod()
    {
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

    public void clearTypeParameters()
    {
        this.typeParameters.clear();
    }

    public TypeParameter[] toTypeParameterArray()
    {
        return this.typeParameters.toArray( new TypeParameter[ this.typeParameters.size() ] );
    }

    public boolean addTypeParametersAll( Collection<TypeParameter> typeParameters )
    {
        return this.typeParameters.addAll( typeParameters );
    }

    public int typeParametersSize()
    {
        return this.typeParameters.size();
    }

    public boolean removeTypeParameter( TypeParameter typeParameter )
    {
        return this.typeParameters.remove( typeParameter );
    }

    public boolean addTypeParameter( TypeParameter typeParameter )
    {
        return this.typeParameters.add( typeParameter );
    }

    public TypeParameter getTypeParameter( int index )
    {
        return this.typeParameters.get( index );
    }

    public boolean isTypeParametersEmpty()
    {
        return this.typeParameters.isEmpty();
    }

    public void clearArguments()
    {
        this.arguments.clear();
    }

    public Argument[] toArgumentArray()
    {
        return this.arguments.toArray( new Argument[ this.arguments.size() ] );
    }

    public boolean addArgumentsAll( Collection<Argument> arguments )
    {
        return this.arguments.addAll( arguments );
    }

    public int argumentsSize()
    {
        return this.arguments.size();
    }

    public boolean removeArgument( Argument argument )
    {
        return this.arguments.remove( argument );
    }

    public boolean addArgument( Argument argument )
    {
        return this.arguments.add( argument );
    }

    public Argument getArgument( int index )
    {
        return this.arguments.get( index );
    }

    public boolean isArgumentsEmpty()
    {
        return this.arguments.isEmpty();
    }

    public void clearExceptions()
    {
        this.exceptions.clear();
    }

    public NonPrimitiveJavaObject[] toExceptionArray()
    {
        return this.exceptions.toArray( new NonPrimitiveJavaObject[ this.exceptions.size() ] );
    }

    public boolean addExceptionsAll( Collection<NonPrimitiveJavaObject> exceptions )
    {
        return this.exceptions.addAll( exceptions );
    }

    public int exceptionsSize()
    {
        return this.exceptions.size();
    }

    public boolean removeException( NonPrimitiveJavaObject exception )
    {
        return this.exceptions.remove( exception );
    }

    public boolean addException( NonPrimitiveJavaObject exception )
    {
        return this.exceptions.add( exception );
    }

    public NonPrimitiveJavaObject getException( int index )
    {
        return this.exceptions.get( index );
    }

    public boolean isExceptionsEmpty()
    {
        return this.exceptions.isEmpty();
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.addAll( annotations );
        retVal.addAll( typeParameters );
        retVal.addAll( arguments );
        retVal.addAll( exceptions );

        return retVal;
    }
}
