package ge.generator.java.generated;

import java.util.*;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.interfaces.*;

public class GeneratedInterfaceJavaObject extends ParentObject implements InterfaceJavaObject,
                                                                          GeneratedObject,
                                                                          MultiLineContent
{
    private AnnotationCollection annotations = new AnnotationCollection();

    private ModifierCollection modifiers = new ModifierCollection();

    private String identifier;

    private TypeParameterCollection typeParameters = new TypeParameterCollection();

    private ExtendsCollection extendsTypes = new ExtendsCollection();

    private MemberCollection members = new MemberCollection();

    public GeneratedInterfaceJavaObject()
    {
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier( String identifier )
    {
        this.identifier = identifier;
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

    public void clearExtends()
    {
        extendsTypes.clear();
    }

    public InterfaceJavaObject[] toExtendsArray()
    {
        return extendsTypes.toArray( new InterfaceJavaObject[ extendsTypes.size() ] );
    }

    public boolean addAllExtends( Collection<InterfaceJavaObject> interfaceJavaObject )
    {
        return extendsTypes.addAll( interfaceJavaObject );
    }

    public int extendsSize()
    {
        return extendsTypes.size();
    }

    public boolean removeExtends( InterfaceJavaObject interfaceJavaObject )
    {
        return extendsTypes.remove( interfaceJavaObject );
    }

    public boolean addExtends( InterfaceJavaObject interfaceJavaObject )
    {
        return extendsTypes.add( interfaceJavaObject );
    }

    public InterfaceJavaObject getExtends( int index )
    {
        return extendsTypes.get( index );
    }

    public boolean isExtendsEmpty()
    {
        return extendsTypes.isEmpty();
    }

    public void clearMembers()
    {
        for ( Member member : members )
        {
            if ( member instanceof ParentObject )
            {
                ( ( ParentObject ) member ).setParent( null );
            }
        }
        this.members.clear();
    }

    public Member[] toMemberArray()
    {
        return this.members.toArray( new Member[ this.members.size() ] );
    }

    public boolean addMembersAll( Collection<Member> members )
    {
        for ( Member member : members )
        {
            if ( member instanceof ParentObject )
            {
                ( ( ParentObject ) member ).setParent( this );
            }
        }
        return this.members.addAll( members );
    }

    public int membersSize()
    {
        return this.members.size();
    }

    public boolean removeMember( Member member )
    {
        if ( member instanceof ParentObject )
        {
            ( ( ParentObject ) member ).setParent( null );
        }
        return this.members.remove( member );
    }

    public boolean addMember( Member member )
    {
        if ( member instanceof ParentObject )
        {
            ( ( ParentObject ) member ).setParent( this );
        }
        return this.members.add( member );
    }

    public Member getMember( int index )
    {
        return this.members.get( index );
    }

    public boolean isMembersEmpty()
    {
        return this.members.isEmpty();
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.addAll( annotations );
        retVal.addAll( typeParameters );

        retVal.addAll( extendsTypes );

        retVal.addAll( members );

        return retVal;
    }

    public List<String> generateContentStrings()
    {
        List<String> retVal = new ArrayList<String>();

        if ( ( annotations != null ) && ( annotations.isEmpty() == false ) )
        {
            for ( Annotation annotation : annotations )
            {
                retVal.add( annotation.generateContentString() );
            }
        }

        String header = "";

        if ( ( modifiers != null ) && ( modifiers.size() != 0 ) )
        {
            for ( Modifier modifier : modifiers )
            {
                header += " ";
                header += modifier.name().toLowerCase();
            }
        }

        header += " interface " + identifier;

        if ( ( typeParameters != null ) && ( typeParameters.isEmpty() == false ) )
        {
            String typeParametersString = "";

            for ( TypeParameter typeParameter : typeParameters )
            {
                if ( typeParametersString.length() != 0 )
                {
                    typeParametersString += ", ";
                }

                typeParametersString += typeParameter.generateContentString();
            }

            header += "<" + typeParametersString + ">";
        }

        if ( ( extendsTypes != null ) && ( extendsTypes.isEmpty() == false ) )
        {
            String implementsString = "";

            for ( InterfaceJavaObject implementsType : extendsTypes )
            {
                if ( implementsString.length() != 0 )
                {
                    implementsString += ", ";
                }

                if ( implementsType instanceof ExtendedInterfaceJavaObject )
                {
                    ExtendedInterfaceJavaObject extendedInterfaceJavaObject =
                            ( ExtendedInterfaceJavaObject ) implementsType;

                    implementsString += extendedInterfaceJavaObject.generateContentString();
                }
                else
                {
                    implementsString += implementsType.getSimpleName();
                }
            }

            header += " extends " + implementsString;
        }

        retVal.add( header.trim() );

        retVal.add( "{" );

        if ( ( members != null ) && ( members.isEmpty() == false ) )
        {
            List<String> memberContent = new ArrayList<String>();

            for ( Member member : members )
            {
                List<String> content = member.generateContentStrings();

                if ( ( content != null ) && ( content.isEmpty() == false ) )
                {
                    if ( memberContent.isEmpty() == false )
                        memberContent.add( "" );

                    for ( String s : content )
                    {
                        memberContent.add( "\t" + s );
                    }
                }
            }

            retVal.addAll( memberContent );
        }
        retVal.add( "}" );

        return retVal;
    }

    public String getFullyQualifiedName()
    {
        return getPackageName() + "." + identifier;
    }

    public String getSimpleName()
    {
        return identifier;
    }
}
