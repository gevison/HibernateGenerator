package ge.generator.java.generated.specialist;

import java.util.*;

import ge.generator.java.existing.*;
import ge.generator.java.generated.*;
import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.interfaces.*;

public class PojoGeneratedClassJavaObject extends GeneratedClassJavaObject
{
    public PojoGeneratedClassJavaObject()
    {
        super();
    }

    @Override
    public ImportCollection generateImports()
    {
        ImportCollection retVal = super.generateImports();

        if ( ( members != null ) && ( members.isEmpty() == false ) )
        {
            retVal.addAll( createGettersAndSetters() );
        }

        return retVal;
    }

    @Override
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

            MemberCollection gettersAndSetters = createGettersAndSetters();

            for ( Member member : gettersAndSetters )
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

    private MemberCollection createGettersAndSetters()
    {
        MemberCollection retVal = new MemberCollection();

        Member[] membersArray = members.toArray( new Member[ members.size() ] );

        for ( Member member : membersArray )
        {
            if ( member instanceof Field )
            {
                retVal.add( createGetter( ( Field ) member ) );
                retVal.add( createSetter( ( Field ) member ) );
            }
        }

        return retVal;
    }

    private Member createGetter( Field member )
    {
        ClassMethod retVal = new ClassMethod();

        retVal.addModifier( Modifier.PUBLIC );

        if ( member.modifiersContains( Modifier.STATIC ) == true )
        {
            retVal.addModifier( Modifier.STATIC );
        }

        retVal.setReturnType( member.getType() );
        retVal.setReturnArray( member.isArray() );

        String fieldName = member.getName();

        String identifier = "get" + fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );

        retVal.setIdentifier( identifier );

        retVal.addBodyString( "return this." + fieldName + ";" );

        return retVal;
    }

    private Member createSetter( Field member )
    {
        ClassMethod retVal = new ClassMethod();

        retVal.addModifier( Modifier.PUBLIC );

        if ( member.modifiersContains( Modifier.STATIC ) == true )
        {
            retVal.addModifier( Modifier.STATIC );
        }

        retVal.setReturnType( PrimitiveJavaObject.VOID );
        retVal.setReturnArray( false );

        String fieldName = member.getName();

        String identifier = "set" + fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );

        retVal.setIdentifier( identifier );

        Argument setterArgument = new Argument();

        setterArgument.setType( member.getType() );
        setterArgument.setArray( member.isArray() );
        setterArgument.setName( member.getName() );

        retVal.addArgument( setterArgument );

        retVal.addBodyString( "this." + fieldName + " = " + fieldName + ";" );

        return retVal;
    }
}
