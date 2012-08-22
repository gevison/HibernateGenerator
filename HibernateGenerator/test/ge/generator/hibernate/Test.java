package ge.generator.hibernate;

import ge.jdbc.JdbcUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Test
{
    public static void main( String[] args )
            throws SQLException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
                   IllegalAccessException, InstantiationException
    {

        Connection connection = DriverManager.getConnection( "jdbc:oracle:thin:@SVR-MET-COAL0:1521:devcent",
                                                             "ge_m5migration",
                                                             "hallo" );

        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println(JdbcUtils.getTypeInfo(metaData));
        System.out.println(JdbcUtils.getTableTypes(metaData));
        System.out.println(JdbcUtils.getCatalogs(metaData));
        System.out.println(JdbcUtils.getSchemas(metaData));
        System.out.println(JdbcUtils.getTables(metaData,null,"GE_M5MIGRATION",null,null));
        System.out.println(JdbcUtils.getColumns(metaData,null,"GE_M5MIGRATION","ACCESS_RESTRICTIONS",null));
        System.out.println(JdbcUtils.getPrimaryKeys(metaData,null,"GE_M5MIGRATION","ACCESS_RESTRICTIONS"));
        System.out.println(JdbcUtils.getIndexInfo(metaData,null,"GE_M5MIGRATION","ACCESS_RESTRICTIONS"));
        System.out.println(JdbcUtils.getImportedKeys(metaData,null,"GE_M5MIGRATION","ACCESS_RESTRICTIONS"));
        System.out.println(JdbcUtils.getExportedKeys(metaData,null,"GE_M5MIGRATION","ACCESS_RESTRICTION_SET"));
        System.out.println(JdbcUtils.getCrossReference(metaData,null,"GE_M5MIGRATION","ACCESS_RESTRICTION_SET",null,"GE_M5MIGRATION","ACCESS_RESTRICTIONS"));
    }
}
