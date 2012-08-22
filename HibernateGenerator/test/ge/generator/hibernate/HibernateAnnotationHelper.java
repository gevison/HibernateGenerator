package ge.generator.hibernate;

import javax.persistence.*;

import ge.generator.java.existing.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.interfaces.*;
import ge.generator.jdbc.api.links.*;
import org.springframework.stereotype.*;

public class HibernateAnnotationHelper
{
    public static Annotation createEntityAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Entity.class ) );

        return retVal;
    }

    public static Annotation createTableAnnotation( String tableName )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Table.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        ElementValue nameElementValue = new ElementValue();
        nameElementValue.setElementValue("\""+tableName+"\"");
        annotationElement.putElementValue("name",nameElementValue);

        retVal.setAnnotationElement( annotationElement );

        return retVal;
    }

    public static Annotation createSuppressWarningsAnnotation( String... warnings )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( SuppressWarnings.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        ElementValue elementValue = new ElementValue();

        String elementValueString = "";

        if ( ( warnings != null ) && ( warnings.length == 1 ) )
        {
            elementValueString = "\"" + warnings[ 0 ] + "\"";
        }
        else if ( ( warnings != null ) && ( warnings.length != 0 ) )
        {
            String values = "";

            for ( String warning : warnings )
            {
                if ( values.length() != 0 )
                {
                    values += ",";
                }

                values += "\"" + warning + "\"";
            }

            elementValueString = "{" + values + "}";
        }

        elementValue.setElementValue( elementValueString );

        annotationElement.setElementValue( elementValue );

        retVal.setAnnotationElement( annotationElement );

        return retVal;
    }

    public static Annotation createEmbeddableAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Embeddable.class ) );

        return retVal;
    }

    public static Annotation createColumnAnnotation( String columnName, Boolean nullable, Boolean updatable )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Column.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        ElementValue nameElementValue = new ElementValue();
        nameElementValue.setElementValue("\""+columnName+"\"");
        annotationElement.putElementValue("name",nameElementValue);

        if ( nullable != null )
        {
            ElementValue nullableElementValue = new ElementValue();
            nullableElementValue.setElementValue(nullable.toString());
            annotationElement.putElementValue("nullable",nullableElementValue);
        }

        if ( updatable != null )
        {
            ElementValue updatableElementValue = new ElementValue();
            updatableElementValue.setElementValue( updatable.toString() );
            annotationElement.putElementValue("updatable",updatableElementValue);
        }

        retVal.setAnnotationElement( annotationElement );

        return retVal;
    }

    public static Annotation createJoinColumnAnnotation( String columnName, Boolean insertable, Boolean updatable )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( JoinColumn.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        ElementValue nameElementValue = new ElementValue();
        nameElementValue.setElementValue("\""+columnName+"\"");
        annotationElement.putElementValue("name",nameElementValue);

        if ( updatable != null )
        {
            ElementValue updatableElementValue = new ElementValue();
            updatableElementValue.setElementValue( updatable.toString() );
            annotationElement.putElementValue("updatable",updatableElementValue);
        }

        if ( insertable != null )
        {
            ElementValue insertableElementValue = new ElementValue();
            insertableElementValue.setElementValue( insertable.toString() );
            annotationElement.putElementValue("insertable",insertableElementValue);
        }

        retVal.setAnnotationElement( annotationElement );

        return retVal;
    }

    public static Annotation createIdAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Id.class ) );

        return retVal;
    }

    public static Annotation createEmbeddedIdAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( EmbeddedId.class ) );

        return retVal;
    }

    public static Annotation createManyToOneAnnotation( FetchType fetchType )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( ManyToOne.class ) );

        if ( fetchType != null )
        {
            AnnotationElement annotationElement = new AnnotationElement();

            ElementValue fetchElementValue = new ElementValue();
            fetchElementValue.setElementValue( FetchType.class.getSimpleName()+"."+fetchType.name() );
            fetchElementValue.addAdditionalImport( ( NonReferenceJavaObject ) ExistingJavaObject.instance( FetchType.class ) );
            annotationElement.putElementValue( "fetch", fetchElementValue );

            retVal.setAnnotationElement( annotationElement );
        }

        return retVal;
    }

    public static Annotation createOneToOneAnnotation( FetchType fetchType )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( OneToOne.class ) );

        if ( fetchType != null )
        {
            AnnotationElement annotationElement = new AnnotationElement();

            ElementValue fetchElementValue = new ElementValue();
            fetchElementValue.setElementValue( FetchType.class.getSimpleName()+"."+fetchType.name() );
            fetchElementValue.addAdditionalImport( ( NonReferenceJavaObject ) ExistingJavaObject.instance( FetchType.class ) );
            annotationElement.putElementValue( "fetch", fetchElementValue );

            retVal.setAnnotationElement( annotationElement );
        }

        return retVal;
    }

    public static Annotation createManyToManyAnnotation( FetchType fetchType )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( ManyToMany.class ) );

        if ( fetchType != null )
        {
            AnnotationElement annotationElement = new AnnotationElement();

            ElementValue fetchElementValue = new ElementValue();
            fetchElementValue.setElementValue( FetchType.class.getSimpleName()+"."+fetchType.name() );
            fetchElementValue.addAdditionalImport( ( NonReferenceJavaObject ) ExistingJavaObject.instance( FetchType.class ) );
            annotationElement.putElementValue( "fetch", fetchElementValue );

            retVal.setAnnotationElement( annotationElement );
        }

        return retVal;
    }

    public static Annotation createPrimaryKeyJoinColumnAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( PrimaryKeyJoinColumn.class ) );

        return retVal;
    }

    public static Annotation createOneToManyAnnotation( FetchType fetchType, String mappedBy )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( OneToMany.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        if ( fetchType != null )
        {
            ElementValue fetchElementValue = new ElementValue();
            fetchElementValue.setElementValue( FetchType.class.getSimpleName()+"."+fetchType.name() );
            fetchElementValue.addAdditionalImport( ( NonReferenceJavaObject ) ExistingJavaObject.instance( FetchType.class ) );
            annotationElement.putElementValue( "fetch", fetchElementValue );
        }

        if ( mappedBy != null )
        {
            ElementValue mappedByElementValue = new ElementValue();
            mappedByElementValue.setElementValue( "\"" + mappedBy + "\"" );
            annotationElement.putElementValue( "mappedBy", mappedByElementValue );
        }

        retVal.setAnnotationElement( annotationElement );

        return retVal;
    }

    public static Annotation createJoinTableAnnotation( ManyToManyLink link )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( JoinTable.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        ElementValue nameElementValue = new ElementValue();
        nameElementValue.setElementValue("\""+link.getJoinTableName()+"\"");
        annotationElement.putElementValue("name", nameElementValue);

        ElementValue joinColumnsElementValue = new ElementValue();
        joinColumnsElementValue.setElementValue(createJoinColumnAnnotation(link.getJoinColumnName(),link.getJoinColumnReferencedName()));
        annotationElement.putElementValue( "joinColumns", joinColumnsElementValue );

        ElementValue inverseJoinColumnsElementValue = new ElementValue();
        inverseJoinColumnsElementValue.setElementValue(createJoinColumnAnnotation(link.getInverseJoinColumnName(),link.getInverseJoinColumnReferencedName()));
        annotationElement.putElementValue( "inverseJoinColumns", inverseJoinColumnsElementValue );

        retVal.setAnnotationElement(annotationElement);

        return retVal;
    }

    private static Annotation createJoinColumnAnnotation( String columnName, String columnReferencedName )
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( JoinColumn.class ) );

        AnnotationElement annotationElement = new AnnotationElement();

        ElementValue nameElementValue = new ElementValue();
        nameElementValue.setElementValue("\""+columnName+"\"");
        annotationElement.putElementValue("name",nameElementValue);

        ElementValue referencedColumnNameElementValue = new ElementValue();
        referencedColumnNameElementValue.setElementValue("\""+columnReferencedName+"\"");
        annotationElement.putElementValue("referencedColumnName",referencedColumnNameElementValue);

        retVal.setAnnotationElement( annotationElement );

        return retVal;
    }

    public static Annotation createRepositoryAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Repository.class ) );

        return retVal;
    }

    public static Annotation createLobAnnotation()
    {
        Annotation retVal = new Annotation();

        retVal.setType( ( AnnotationJavaObject ) ExistingJavaObject.instance( Lob.class ) );

        return retVal;
    }
}
