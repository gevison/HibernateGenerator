package ge.generator.java.generated;

import java.util.*;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.interfaces.*;

public class GeneratedClassJavaObject extends ParentObject implements ClassJavaObject,
                                                                      GeneratedObject,
                                                                      MultiLineContent,
                                                                      Comparable<GeneratedClassJavaObject>
{
    protected AnnotationCollection annotations = new AnnotationCollection();

    protected ModifierCollection modifiers = new ModifierCollection();

    protected String identifier;

    protected TypeParameterCollection typeParameters = new TypeParameterCollection();

    protected ClassJavaObject extendsType;

    protected InterfaceCollection implementsTypes = new InterfaceCollection();

    protected MemberCollection members = new MemberCollection();

    public GeneratedClassJavaObject()
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

    public ClassJavaObject getExtendsType()
    {
        return extendsType;
    }

    public void setExtendsType( ClassJavaObject extendsType )
    {
        this.extendsType = extendsType;
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

    public void clearImplements()
    {
        this.implementsTypes.clear();
    }

    public InterfaceJavaObject[] toImplementArray()
    {
        return this.implementsTypes.toArray( new InterfaceJavaObject[ this.implementsTypes.size() ] );
    }

    public boolean addImplementsAll( Collection<InterfaceJavaObject> implemented )
    {
        return this.implementsTypes.addAll( implemented );
    }

    public int ImplementsSize()
    {
        return this.implementsTypes.size();
    }

    public boolean removeImplement( InterfaceJavaObject implemented )
    {
        return this.implementsTypes.remove( implemented );
    }

    public boolean addImplement( InterfaceJavaObject implemented )
    {
        return this.implementsTypes.add( implemented );
    }

    public InterfaceJavaObject getImplement( int index )
    {
        return this.implementsTypes.get( index );
    }

    public boolean isImplementsEmpty()
    {
        return this.implementsTypes.isEmpty();
    }

    public void clearMembers()
    {
        this.members.clear();
    }

    public Member[] toMemberArray()
    {
        return this.members.toArray( new Member[ this.members.size() ] );
    }

    public boolean addMembersAll( Collection<Member> members )
    {
        return this.members.addAll( members );
    }

    public int membersSize()
    {
        return this.members.size();
    }

    public boolean removeMember( Member member )
    {
        return this.members.remove( member );
    }

    public boolean addMember( Member member )
    {
        if ( member != null )
            return this.members.add( member );
        else
            return false;
    }

    public Member getMember( int index )
    {
        return this.members.get( index );
    }

    public boolean isMembersEmpty()
    {
        return this.members.isEmpty();
    }

    public String getFullyQualifiedName()
    {
        return getPackageName() + "." + identifier;
    }

    public String getSimpleName()
    {
        return identifier;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.addAll( annotations );
        retVal.addAll( typeParameters );

        retVal.add( extendsType );

        retVal.addAll( implementsTypes );

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

        header += " class " + identifier;

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

        if ( extendsType != null )
        {
            header += " extends ";
            if ( extendsType instanceof ExtendedClassJavaObject )
            {
                ExtendedClassJavaObject extendedClassJavaObject = ( ExtendedClassJavaObject ) extendsType;
                header += extendedClassJavaObject.generateContentString();
            }
            else
            {
                header += extendsType.getSimpleName();
            }
        }

        if ( ( implementsTypes != null ) && ( implementsTypes.isEmpty() == false ) )
        {
            String implementsString = "";

            for ( InterfaceJavaObject implementsType : implementsTypes )
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

            header += " implements " + implementsString;
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

    public int compareTo( GeneratedClassJavaObject o )
    {
        return identifier.compareTo( o.identifier );
    }
}
