package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils
{
    public static List<TableType> getTableTypes( DatabaseMetaData databaseMetaData )
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
                   InstantiationException, IllegalAccessException
    {
        return createListFromResultSet(databaseMetaData.getTableTypes(),TableType.class);
    }

    public static List<TypeInfo> getTypeInfo( DatabaseMetaData databaseMetaData )
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
                   InstantiationException, IllegalAccessException
    {
        return createListFromResultSet(databaseMetaData.getTypeInfo(),TypeInfo.class);
    }

    public static List<Catalog> getCatalogs(DatabaseMetaData databaseMetaData)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getCatalogs(),Catalog.class);
    }

    public static List<Schema> getSchemas( DatabaseMetaData databaseMetaData )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getSchemas(),Schema.class);
    }

    public static List<Table> getTables( DatabaseMetaData databaseMetaData,String catalog,
                                         String schemaPattern,
                                         String tableNamePattern,
                                         String[] types )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getTables( catalog, schemaPattern, tableNamePattern, types ),Table.class);
    }

    public static List<SuperTable> getSuperTables( DatabaseMetaData databaseMetaData,String catalog,
                                                   String schemaPattern,
                                                   String tableNamePattern )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getSuperTables( catalog, schemaPattern, tableNamePattern ),SuperTable.class);
    }

    public static List<Column> getColumns(DatabaseMetaData databaseMetaData,String catalog,
                                          String schemaPattern,
                                          String tableNamePattern,
                                          String columnNamePattern)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getColumns( catalog, schemaPattern, tableNamePattern,
                                                                    columnNamePattern ),Column.class);
    }

    public static List<VersionColumn> getVersionColumns(DatabaseMetaData databaseMetaData,String catalog,
                                          String schema,
                                          String tableName)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getVersionColumns( catalog, schema, tableName ),VersionColumn.class);
    }

    public static List<UDT> getUDTs( DatabaseMetaData databaseMetaData,String catalog,
                                         String schemaPattern,
                                         String typeNamePattern,
                                         int[] types )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getUDTs( catalog, schemaPattern, typeNamePattern, types ),UDT.class);
    }

    public static List<SuperType> getSuperTypes( DatabaseMetaData databaseMetaData,String catalog,
                                                   String schemaPattern,
                                                   String typeNamePattern )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getSuperTypes( catalog, schemaPattern, typeNamePattern ),SuperType.class);
    }

    public static List<Attribute> getAttributes(DatabaseMetaData databaseMetaData,String catalog,
                                                          String schemaPattern,
                                                          String typeNamePattern,
                                                          String attributeNamePattern)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getAttributes( catalog, schemaPattern, typeNamePattern,
                                                                       attributeNamePattern ),Attribute.class);
    }

    public static List<Function> getFunctions( DatabaseMetaData databaseMetaData,String catalog,
                                         String schemaPattern,
                                         String functionNamePattern,
                                         String[] types )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getFunctions( catalog, schemaPattern, functionNamePattern ),Function.class);
    }

    public static List<FunctionColumn> getFunctionColumns(DatabaseMetaData databaseMetaData,String catalog,
                                          String schemaPattern,
                                          String functionNamePattern,
                                          String columnNamePattern)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getFunctionColumns( catalog, schemaPattern, functionNamePattern,
                                                                            columnNamePattern ),FunctionColumn.class);
    }

    public static List<PrimaryKey> getPrimaryKeys(DatabaseMetaData databaseMetaData,String catalog,
                                                  String schema,
                                                  String table)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getPrimaryKeys( catalog, schema, table ),PrimaryKey.class);
    }

    public static List<IndexInfo> getIndexInfo(DatabaseMetaData databaseMetaData,String catalog,
                                               String schema,
                                               String table)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getIndexInfo( catalog, schema, table, false, true ),IndexInfo.class);
    }

    public static List<Key> getImportedKeys(DatabaseMetaData databaseMetaData,String catalog,
                                            String schema,
                                            String table)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getImportedKeys( catalog, schema, table ),Key.class);
    }

    public static List<Key> getExportedKeys(DatabaseMetaData databaseMetaData,String catalog,
                                            String schema,
                                            String table)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getExportedKeys( catalog, schema, table ),Key.class);
    }

    public static List<Key> getCrossReference(DatabaseMetaData databaseMetaData,String parentCatalog,
                                              String parentSchema,
                                              String parentTable,
                                              String foreignCatalog,
                                              String foreignSchema,
                                              String foreignTable)
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {
        return createListFromResultSet(databaseMetaData.getCrossReference( parentCatalog, parentSchema, parentTable,
                                                                           foreignCatalog, foreignSchema, foreignTable ),Key.class);
    }

    private static <T> List<T> createListFromResultSet(ResultSet resultSet, Class<T> aClass)
            throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
                   InstantiationException, IllegalAccessException
    {
        List<T> retVal = new ArrayList<T>();

        while ( resultSet.next() )
        {
            T type = createFromResultSet(resultSet,aClass);
            retVal.add( type );
        }

        return retVal;
    }

    private static <T> T createFromResultSet( ResultSet resultSet, Class<T> aClass )
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException,
                   SQLException, ClassNotFoundException
    {
        Constructor<T> constructor = aClass.getConstructor(  );
        T type = constructor.newInstance(  );
        Field[] fields = getFields(aClass,ResultSetValue.class);

        ResultSetMetaData metaData = resultSet.getMetaData();

        for ( Field field : fields )
        {
            if ( field.isAnnotationPresent(ResultSetValue.class) == true )
            {
                ResultSetValue annotation = field.getAnnotation( ResultSetValue.class );

                String columnName = annotation.value();

                Class javaClass = getJavaClassForColumn(metaData,columnName);

                if ( javaClass != null )
                {
                    Object object = null;

                    Class<?> fieldType = field.getType();

                    if ( fieldType.isAssignableFrom( javaClass ) == true )
                    {
                        object = resultSet.getObject(columnName);
                    }
                    else if ( fieldType.isAssignableFrom(Integer.class) && javaClass.isAssignableFrom( BigDecimal.class))
                    {
                        object = resultSet.getInt(columnName);
                    }
                    else if ( fieldType.isAssignableFrom(Short.class) && javaClass.isAssignableFrom( BigDecimal.class))
                    {
                        object = resultSet.getShort(columnName);
                    }
                    else if ( fieldType.isAssignableFrom(Boolean.class) && javaClass.isAssignableFrom( BigDecimal.class))
                    {
                        object = resultSet.getBoolean(columnName);
                    }
                    else if ( fieldType.isAssignableFrom(Short.class) && javaClass.isAssignableFrom( String.class))
                    {
                        object = resultSet.getShort( columnName );
                    }
                    else if ( fieldType.isAssignableFrom(Integer.class) && javaClass.isAssignableFrom( String.class))
                    {
                        object = resultSet.getInt(columnName);
                    }
                    else
                    {
                        throw new SQLException("Don't know what to do: "+columnName);
                    }

                    boolean accessible = field.isAccessible();
                    if ( accessible == false )
                    {
                        field.setAccessible(true);
                    }

                    field.set(type,object);

                    field.setAccessible(accessible);
                }
            }
        }

        return type;
    }

    private static Class getJavaClassForColumn( ResultSetMetaData metaData, String columnName )
            throws SQLException, ClassNotFoundException
    {
        for ( int i = 0; i < metaData.getColumnCount(); i++ )
        {
            if ( columnName.equals(metaData.getColumnName(i+1)) == true )
            {
                return Class.forName( metaData.getColumnClassName( i + 1 ) );
            }
        }
        return null;
    }

    private static Field[] getFields( Class aClass, Class annotationClass )
    {
        List<Field> fields = new ArrayList<Field>();

        Class<?> clazz = aClass;

        while ( clazz.isAssignableFrom( Object.class ) == false )
        {
            Field[] declaredFields = clazz.getDeclaredFields();

            for ( Field declaredField : declaredFields )
            {
                if ( declaredField.isAnnotationPresent(annotationClass) == true )
                    fields.add(declaredField);
            }
            clazz = clazz.getSuperclass();
        }

        return fields.toArray( new Field[ fields.size() ] );
    }
}
