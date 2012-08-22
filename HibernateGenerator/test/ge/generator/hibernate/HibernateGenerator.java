package ge.generator.hibernate;

import javax.persistence.*;
import javax.xml.bind.*;
import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import ge.generator.java.*;
import ge.generator.java.existing.*;
import ge.generator.java.generated.*;
import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.part.*;
import ge.generator.java.generated.specialist.*;
import ge.generator.java.interfaces.*;
import ge.generator.java.sourcefile.*;
import ge.generator.jdbc.api.Column;
import ge.generator.jdbc.api.*;
import ge.generator.jdbc.api.Table;
import ge.generator.jdbc.api.links.*;
import ge.generator.jdbc.oracle.inferis.*;
import uk.gov.coal.inferis.publicsafety.util.service.*;
import uk.gov.coal.inferis.publicsafety.util.service.impl.*;

public class HibernateGenerator
{
    private static Map<Table, JavaObject> keyObjects = new HashMap<Table, JavaObject>();

    private static Map<Table, GeneratedClassJavaObject> domainObjects = new HashMap<Table, GeneratedClassJavaObject>();

    private static Map<Table, GeneratedInterfaceJavaObject> serviceInterfacesObjects =
            new HashMap<Table, GeneratedInterfaceJavaObject>();

    private static Map<Table, GeneratedClassJavaObject> serviceImplObjects =
            new HashMap<Table, GeneratedClassJavaObject>();

    public static void main( String[] args ) throws
                                             SQLException,
                                             IOException,
                                             JAXBException
    {
        Connection connection = DriverManager.getConnection( "jdbc:oracle:thin:@SVR-MET-COAL0:1521:devcent",
                                                             "ge_m5migration",
                                                             "hallo" );

        SchemaLoader schemaLoader = new InferisOracleSchemaLoader();

        Schema schema = schemaLoader.loadSchema( connection );

        PackageContainer domainPackage = createRawDomainObjects( schema );
        domainPackage.setName( "uk.gov.coal.inferis.publicsafety.domain" );

        PackageContainer keysPackage = createKeyObjects( schema );
        keysPackage.setName( "keys" );

        domainPackage.addPackageContainerElement( keysPackage );

        createDomainObjectKeyFields( schema );
        createDomainObjectFields( schema );
        createDomainObjectLinks( schema );

        PackageContainer serviceInterfacePackage = createServiceInterfaces( schema );

        serviceInterfacePackage.setName( "service" );

        domainPackage.addPackageContainerElement( serviceInterfacePackage );

        PackageContainer serviceClassPackage = createServiceClasses( schema );

        serviceClassPackage.setName( "impl" );

        serviceInterfacePackage.addPackageContainerElement( serviceClassPackage );

        File outputDir = new File( "D:\\TempOutput\\src" );

        domainPackage.generate( outputDir );
    }

    private static PackageContainer createRawDomainObjects( Schema schema )
    {
        PackageContainer retVal = new PackageContainer();

        List<Table> tables = schema.getTables();

        for ( Table table : tables )
        {
            retVal.addPackageContainerElement( createRawDomainObject( table ) );
        }

        return retVal;
    }

    private static PackageContainerElement createRawDomainObject( Table table )
    {
        PojoGeneratedClassJavaObject object = new PojoGeneratedClassJavaObject();

        object.addAnnotation( HibernateAnnotationHelper.createEntityAnnotation() );
        object.addAnnotation( HibernateAnnotationHelper.createTableAnnotation( table.getTableName() ) );

        object.addModifier( Modifier.PUBLIC );
        object.setIdentifier( generateDomainObjectName( table ) );

        domainObjects.put( table, object );

        ObjectSourceFile retVal = new ObjectSourceFile();
        retVal.setObject( object );
        return retVal;
    }

    private static PackageContainer createKeyObjects( Schema schema )
    {
        PackageContainer retVal = new PackageContainer();

        List<Table> tables = schema.getTables();

        for ( Table table : tables )
        {
            if ( table instanceof CompositeKeyTable )
            {
                CompositeKeyTable compositeKeyTable = ( CompositeKeyTable ) table;

                GeneratedClassJavaObject object = createCompositeKeyObject( compositeKeyTable );

                keyObjects.put( table, object );

                ObjectSourceFile objectSourceFile = new ObjectSourceFile();
                objectSourceFile.setObject( object );

                retVal.addPackageContainerElement( objectSourceFile );
            }
            else if ( table instanceof PrimaryKeyTable )
            {
                PrimaryKeyTable primaryKeyTable = ( PrimaryKeyTable ) table;

                PrimaryKeyColumn primaryKeyColumn = primaryKeyTable.getPrimaryKeyColumn();

                keyObjects.put( table, getColumnFieldType( primaryKeyColumn ) );
            }
        }

        return retVal;
    }

    private static GeneratedClassJavaObject createCompositeKeyObject( CompositeKeyTable compositeKeyTable )
    {
        PojoGeneratedClassJavaObject retVal = new PojoGeneratedClassJavaObject();

        retVal.addAnnotation( HibernateAnnotationHelper.createSuppressWarningsAnnotation( "serial" ) );
        retVal.addAnnotation( HibernateAnnotationHelper.createEmbeddableAnnotation() );

        retVal.addModifier( Modifier.PUBLIC );
        String identifier = generateDomainObjectKeyTypeName( compositeKeyTable );
        retVal.setIdentifier( identifier );

        retVal.addImplement( ( InterfaceJavaObject ) ExistingJavaObject.instance( Serializable.class ) );

        ClassMethod hashCodeMethod = new ClassMethod();
        hashCodeMethod.addModifier( Modifier.PUBLIC );
        hashCodeMethod.setReturnArray( false );
        hashCodeMethod.setReturnType( PrimitiveJavaObject.INT );
        hashCodeMethod.setIdentifier( "hashCode" );

        hashCodeMethod.addBodyString( "final int prime = 31;" );
        hashCodeMethod.addBodyString( "int result = 1;" );

        ClassMethod equalsMethod = new ClassMethod();
        equalsMethod.addModifier( Modifier.PUBLIC );
        equalsMethod.setReturnArray( false );
        equalsMethod.setReturnType( PrimitiveJavaObject.BOOLEAN );
        equalsMethod.setIdentifier( "equals" );
        equalsMethod.addBodyString( "if (this == obj)" );
        equalsMethod.addBodyString( "    return true;" );
        equalsMethod.addBodyString( "if (obj == null)" );
        equalsMethod.addBodyString( "    return false;" );
        equalsMethod.addBodyString( "if (getClass() != obj.getClass())" );
        equalsMethod.addBodyString( "    return false;" );
        equalsMethod.addBodyString(identifier+" other = ("+identifier+")obj;");
        Argument equalsArgument = new Argument();
        equalsArgument.setName("obj");
        equalsArgument.setType(ExistingJavaObject.instance(Object.class));
        equalsArgument.setArray(false);
        equalsMethod.addArgument( equalsArgument );

        ClassMethod toStringMethod = new ClassMethod();
        toStringMethod.addModifier( Modifier.PUBLIC );
        toStringMethod.setReturnArray( false );
        toStringMethod.setReturnType( ExistingJavaObject.instance(String.class) );
        toStringMethod.setIdentifier( "toString" );
        toStringMethod.addBodyString("return \""+identifier+" [\" +");

        List<CompositeKeyColumn> keyColumns = compositeKeyTable.getKeyColumns();

        for ( CompositeKeyColumn keyColumn : keyColumns )
        {
            retVal.addMember( createCompositeKeyField( keyColumn ) );

            String fieldName = generateKeyFieldName( keyColumn );

            hashCodeMethod.addBodyString(
                    "result = prime * result + ((" + fieldName + " == null) ? 0 : " + fieldName + ".hashCode());" );

            equalsMethod.addBodyString("if (" + fieldName + " == null)");
            equalsMethod.addBodyString("{");
            equalsMethod.addBodyString("    if (other." + fieldName + " != null)");
            equalsMethod.addBodyString("        return false;");
            equalsMethod.addBodyString("}");
            equalsMethod.addBodyString("else");
            equalsMethod.addBodyString("{");
            equalsMethod.addBodyString("    if (!" + fieldName + ".equals(other." + fieldName + "))");
            equalsMethod.addBodyString("        return false;");
            equalsMethod.addBodyString("}");

            toStringMethod.addBodyString("\""+fieldName+"=\" + "+fieldName+" +");
        }

        hashCodeMethod.addBodyString( "return result;" );
        equalsMethod.addBodyString( "return true;" );
        toStringMethod.addBodyString("\"]\";");

        retVal.addMember( hashCodeMethod );
        retVal.addMember( equalsMethod );
        retVal.addMember( toStringMethod );

        return retVal;
    }

    private static Field createCompositeKeyField( CompositeKeyColumn keyColumn )
    {
        Field retVal = new Field();

        retVal.addAnnotation( HibernateAnnotationHelper.createColumnAnnotation( keyColumn.getColumnName(),
                                                                                false,
                                                                                false ) );

        retVal.addModifier( Modifier.PRIVATE );

        retVal.setType( getColumnFieldType( keyColumn ) );

        retVal.setName( generateKeyFieldName( keyColumn ) );

        return retVal;
    }

    private static void createDomainObjectKeyFields( Schema schema )
    {
        List<Table> tables = schema.getTables();

        for ( Table table : tables )
        {
            createDomainObjectKeyDetails( table );
        }
    }

    private static void createDomainObjectKeyDetails( Table table )
    {
        GeneratedClassJavaObject domainObject = domainObjects.get( table );
        JavaObject keyObject = keyObjects.get( table );

        NonPrimitiveJavaObjectCollection extensions = new NonPrimitiveJavaObjectCollection();
        extensions.add( ( NonPrimitiveJavaObject ) keyObject );

        ExistingExtendedInterfaceJavaObject implemented =
                new ExistingExtendedInterfaceJavaObject( DomainObject.class, extensions );

        domainObject.addImplement( implemented );

        Field keyField = new Field();

        if ( table instanceof CompositeKeyTable )
        {
            keyField.addAnnotation( HibernateAnnotationHelper.createEmbeddedIdAnnotation() );
        }
        else if ( table instanceof PrimaryKeyTable )
        {
            PrimaryKeyTable primaryKeyTable = ( PrimaryKeyTable ) table;

            PrimaryKeyColumn primaryKeyColumn = primaryKeyTable.getPrimaryKeyColumn();

            keyField.addAnnotation( HibernateAnnotationHelper.createIdAnnotation() );
            keyField.addAnnotation( HibernateAnnotationHelper.createColumnAnnotation( primaryKeyColumn.getColumnName(),
                                                                                      null,
                                                                                      null ) );
        }

        keyField.addModifier( Modifier.PRIVATE );
        keyField.setType( keyObject );
        keyField.setName( "key" );

        domainObject.addMember( keyField );
    }

    private static void createDomainObjectFields( Schema schema )
    {
        List<Table> tables = schema.getTables();

        for ( Table table : tables )
        {
            createDomainObjectFields( table );
        }
    }

    private static void createDomainObjectFields( Table table )
    {
        GeneratedClassJavaObject domainObject = domainObjects.get( table );
        List<Column> columns = table.getColumns();

        Collections.sort( columns );

        for ( Column column : columns )
        {
            domainObject.addMember( createDomainObjectField( column ) );
        }
    }

    private static Field createDomainObjectField( Column column )
    {
        Field retVal = new Field();

        retVal.addAnnotation( HibernateAnnotationHelper.createColumnAnnotation( column.getColumnName(), null, null ) );

        if ( column.getType() == DataTypeEnum.BLOB )
        {
            retVal.addAnnotation(HibernateAnnotationHelper.createLobAnnotation());
        }

        retVal.addModifier( Modifier.PRIVATE );

        retVal.setType( getColumnFieldType( column ) );

        retVal.setName( generateFieldName( column ) );

        return retVal;
    }

    private static void createDomainObjectLinks( Schema schema )
    {
        List<Table> tables = schema.getTables();

        for ( Table table : tables )
        {
            createDomainObjectLinks( schema, table );
        }
    }

    private static void createDomainObjectLinks( Schema schema, Table table )
    {
        GeneratedClassJavaObject classJavaObject = domainObjects.get( table );
        List<Link> links = table.getLinks();

        for ( Link link : links )
        {
            if ( link instanceof ManyToManyLink )
            {
                classJavaObject.addMember( createManyToManyField( schema, ( ManyToManyLink ) link ) );
            }
            else if ( link instanceof ManyToOneLink )
            {
                classJavaObject.addMember( createManyToOneField( schema, ( ManyToOneLink ) link ) );
            }
            else if ( link instanceof OneToManyLink )
            {
                classJavaObject.addMember( createOneToManyField( schema, ( OneToManyLink ) link ) );
            }
            else if ( link instanceof OneToOneLink )
            {
                if ( link instanceof PrimaryKeyJoinOneToOneLink )
                {
                    classJavaObject.addMember( createPrimaryKeyJoinOneToOneField( schema,
                                                                                  ( PrimaryKeyJoinOneToOneLink ) link ) );
                }
                else
                {
                    System.err.println( "OneToOne link not created" );
                }
            }
        }
    }

    private static Field createManyToManyField( Schema schema, ManyToManyLink link )
    {
        Field retVal = new Field();

        retVal.addAnnotation( HibernateAnnotationHelper.createManyToManyAnnotation( FetchType.LAZY ) );
        retVal.addAnnotation( HibernateAnnotationHelper.createJoinTableAnnotation( link ) );

        retVal.addModifier( Modifier.PRIVATE );

        Table destinationTable = schema.getTable( link.getDestinationTableName() );

        NonPrimitiveJavaObjectCollection extensions = new NonPrimitiveJavaObjectCollection();
        extensions.add( domainObjects.get( destinationTable ) );

        retVal.setType( ExistingJavaObject.instance( List.class, extensions ) );

        String fieldName = null;

        if ( link.getJoinColumnName().equals( link.getJoinColumnReferencedName() ) == false )
        {
            fieldName = generateFieldName( link.getJoinColumnName() );

            if ( fieldName.endsWith( "Id" ) == true )
            {
                fieldName = fieldName.substring( 0, fieldName.length() - 2 );
            }
        }
        else
        {
            fieldName = generateFieldName( link.getDestinationTableName() );
        }

        if ( fieldName.endsWith( "s" ) == false )
        {
            if ( fieldName.endsWith( "y" ) == true )
            {
                fieldName = fieldName.substring( 0, fieldName.length() - 1 ) + "ies";
            }
            else
            {
                fieldName += "s";
            }
        }

        retVal.setName( fieldName );

        return retVal;
    }

    private static Field createPrimaryKeyJoinOneToOneField( Schema schema, PrimaryKeyJoinOneToOneLink link )
    {
        Field retVal = new Field();

        retVal.addAnnotation( HibernateAnnotationHelper.createOneToOneAnnotation( FetchType.LAZY ) );
        retVal.addAnnotation( HibernateAnnotationHelper.createPrimaryKeyJoinColumnAnnotation() );

        retVal.addModifier( Modifier.PRIVATE );

        String linkTableName = link.getLinkTableName();

        Table linkTable = schema.getTable( linkTableName );

        retVal.setType( domainObjects.get( linkTable ) );

        String fieldName = generateFieldName( linkTableName );

        if ( fieldName.endsWith( "Id" ) == true )
        {
            fieldName = fieldName.substring( 0, fieldName.length() - 2 );
        }

        retVal.setName( fieldName );

        return retVal;
    }

    private static Field createManyToOneField( Schema schema, ManyToOneLink link )
    {
        Field retVal = new Field();

        retVal.addAnnotation( HibernateAnnotationHelper.createManyToOneAnnotation( FetchType.LAZY ) );
        retVal.addAnnotation( HibernateAnnotationHelper.createJoinColumnAnnotation( link.getColumnName(),
                                                                                    false,
                                                                                    false ) );

        retVal.addModifier( Modifier.PRIVATE );

        Table linkTable = schema.getTable( link.getLinkTableName() );

        retVal.setType( domainObjects.get( linkTable ) );

        String fieldName = generateFieldName( link.getColumnName() );

        if ( fieldName.endsWith( "Id" ) == true )
        {
            fieldName = fieldName.substring( 0, fieldName.length() - 2 );
        }

        retVal.setName( fieldName );

        return retVal;
    }

    private static Field createOneToManyField( Schema schema, OneToManyLink link )
    {
        Field retVal = new Field();

        String mappedBy = generateFieldName( link.getLinkColumnName() );

        if ( mappedBy.endsWith( "Id" ) == true )
        {
            mappedBy = mappedBy.substring( 0, mappedBy.length() - 2 );
        }

        retVal.addAnnotation( HibernateAnnotationHelper.createOneToManyAnnotation( FetchType.LAZY, mappedBy ) );

        retVal.addModifier( Modifier.PRIVATE );

        Table linkTable = schema.getTable( link.getLinkTableName() );

        NonPrimitiveJavaObjectCollection extensions = new NonPrimitiveJavaObjectCollection();
        extensions.add( domainObjects.get( linkTable ) );

        retVal.setType( ExistingJavaObject.instance( List.class, extensions ) );

        String fieldName = mappedBy + generateDomainObjectName( linkTable );

        if ( fieldName.endsWith( "s" ) == false )
        {
            if ( fieldName.endsWith( "y" ) == true )
            {
                fieldName = fieldName.substring( 0, fieldName.length() - 1 ) + "ies";
            }
            else
            {
                fieldName += "s";
            }
        }

        retVal.setName( fieldName );

        return retVal;
    }

    private static PackageContainer createServiceClasses( Schema schema )
    {
        List<Table> tables = schema.getTables();
        PackageContainer retVal = new PackageContainer();

        for ( Table table : tables )
        {
            GeneratedClassJavaObject serviceImpl = new GeneratedClassJavaObject();
            serviceImpl.addModifier( Modifier.PUBLIC );

            serviceImpl.addAnnotation( HibernateAnnotationHelper.createRepositoryAnnotation() );

            GeneratedExtendedClassJavaObject extendedSimpleObjectServiceImpl = new GeneratedExtendedClassJavaObject();
            extendedSimpleObjectServiceImpl.setType( ( NonReferenceJavaObject ) ExistingJavaObject.instance(
                    SimpleObjectServiceImpl.class ) );
            extendedSimpleObjectServiceImpl.addExtension( domainObjects.get( table ) );
            extendedSimpleObjectServiceImpl.addExtension( ( NonPrimitiveJavaObject ) keyObjects.get( table ) );

            serviceImpl.setExtendsType( extendedSimpleObjectServiceImpl );

            serviceImpl.setIdentifier( generateServiceClassName( table ) );

            serviceImpl.addImplement( serviceInterfacesObjects.get( table ) );

            serviceImplObjects.put( table, serviceImpl );

            ObjectSourceFile sourceFile = new ObjectSourceFile();
            sourceFile.setObject( serviceImpl );
            retVal.addPackageContainerElement( sourceFile );
        }

        return retVal;
    }

    private static PackageContainer createServiceInterfaces( Schema schema )
    {
        List<Table> tables = schema.getTables();
        PackageContainer retVal = new PackageContainer();

        for ( Table table : tables )
        {
            GeneratedInterfaceJavaObject serviceInterface = new GeneratedInterfaceJavaObject();
            serviceInterface.addModifier( Modifier.PUBLIC );

            GeneratedExtendedInterfaceJavaObject extendedSimpleObjectService =
                    new GeneratedExtendedInterfaceJavaObject();

            extendedSimpleObjectService.setType( ( NonReferenceJavaObject ) ExistingJavaObject.instance(
                    SimpleObjectService.class ) );
            extendedSimpleObjectService.addExtension( domainObjects.get( table ) );
            extendedSimpleObjectService.addExtension( ( NonPrimitiveJavaObject ) keyObjects.get( table ) );

            serviceInterface.addExtends( extendedSimpleObjectService );

            serviceInterface.setIdentifier( generateServiceInterfaceName( table ) );

            serviceInterfacesObjects.put( table, serviceInterface );

            ObjectSourceFile sourceFile = new ObjectSourceFile();
            sourceFile.setObject( serviceInterface );
            retVal.addPackageContainerElement( sourceFile );
        }

        return retVal;
    }

    private static String generateDomainObjectName( Table table )
    {
        StringTokenizer tokenizer = new StringTokenizer( table.getTableName(), "_" );
        String retVal = "";

        while ( tokenizer.hasMoreTokens() == true )
        {
            String token = tokenizer.nextToken();

            retVal += token.substring( 0, 1 ).toUpperCase();
            retVal += token.substring( 1 ).toLowerCase();
        }

        return retVal;
    }

    private static String generateDomainObjectKeyTypeName( Table table )
    {
        return generateDomainObjectName( table ) + "KeyType";
    }

    private static String generateServiceInterfaceName( Table table )
    {
        return generateDomainObjectName( table ) + "Service";
    }

    private static String generateServiceClassName( Table table )
    {
        return generateServiceInterfaceName( table ) + "Impl";
    }

    private static String generateFieldName( String columnName )
    {
        StringTokenizer tokenizer = new StringTokenizer( columnName, "_" );
        String retVal = "";

        while ( tokenizer.hasMoreTokens() == true )
        {
            String token = tokenizer.nextToken();

            retVal += token.substring( 0, 1 ).toUpperCase();
            retVal += token.substring( 1 ).toLowerCase();
        }

        retVal = retVal.substring( 0, 1 ).toLowerCase() + retVal.substring( 1 );

        return retVal;
    }

    private static String generateFieldName( Column column )
    {
        return generateFieldName( column.getColumnName() );
    }

    private static String generateKeyFieldName( Column column )
    {
        String fieldName = generateFieldName( column );

        if ( fieldName.endsWith( "Id" ) == false )
        {
            fieldName += "Id";
        }

        return fieldName;
    }

    private static JavaObject getColumnFieldType( Column column )
    {
        JavaObject type;

        switch ( column.getType() )
        {
            case NUMBER:
            {
                if ( ( column.getDataScale() != null ) && ( column.getDataScale() > 0 ) )
                {
                    type = ExistingJavaObject.instance( Double.class );
                }
                else
                {
                    type = ExistingJavaObject.instance( Long.class );
                }
                break;
            }
            case DATE:
            {
                type = ExistingJavaObject.instance( Date.class );
                break;
            }
            case TIMESTAMP:
            {
                type = ExistingJavaObject.instance( Timestamp.class );
                break;
            }
            case BLOB:
            {
                type = ExistingJavaObject.instance( Blob.class );
                break;
            }
            case CHAR:
            case VARCHAR2:
            default:
            {
                type = ExistingJavaObject.instance( String.class );
                break;
            }
        }

        return type;
    }
}
