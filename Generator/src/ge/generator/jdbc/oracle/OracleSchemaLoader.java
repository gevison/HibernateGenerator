package ge.generator.jdbc.oracle;

import java.sql.*;
import java.util.*;

import ge.generator.jdbc.api.*;
import ge.generator.jdbc.impl.*;

public class OracleSchemaLoader extends SchemaLoader
{
    protected final List<LinkData> getLinkData( Connection connection, List<String> tableNames ) throws
                                                                                         SQLException
    {
        String sql = "SELECT COL.TABLE_NAME AS TABLE_NAME1, COL.COLUMN_NAME AS COLUMN_NAME1, " +
                     "REL.TABLE_NAME AS TABLE_NAME2, REL.COLUMN_NAME AS COLUMN_NAME2 " +
                     "FROM USER_TAB_COLUMNS COL " +
                     "JOIN USER_CONS_COLUMNS CON ON COL.TABLE_NAME = CON.TABLE_NAME AND COL.COLUMN_NAME = CON.COLUMN_NAME " +
                     "JOIN USER_CONSTRAINTS CC ON CON.CONSTRAINT_NAME = CC.CONSTRAINT_NAME " +
                     "JOIN USER_CONS_COLUMNS REL ON CC.R_CONSTRAINT_NAME = REL.CONSTRAINT_NAME AND CON.POSITION = REL.POSITION " +
                     "WHERE CC.CONSTRAINT_TYPE = 'R'";

        String whereClause = createTableWhereClause( tableNames, "COL.TABLE_NAME" );

        if ( whereClause.length() != 0 )
            sql += " AND ("+whereClause+")";

        sql += " ORDER BY COL.TABLE_NAME, COL.COLUMN_NAME";

        PreparedStatement relationshipsByTableStatement = connection.prepareStatement( sql );

        ResultSet resultSet = relationshipsByTableStatement.executeQuery();

        List<LinkData> retVal = new ArrayList<LinkData>();

        while ( resultSet.next() == true )
        {
            String tableName1 = resultSet.getString("TABLE_NAME1");
            String columnName1 = resultSet.getString("COLUMN_NAME1");
            String tableName2 = resultSet.getString("TABLE_NAME2");
            String columnName2 = resultSet.getString("COLUMN_NAME2");

            retVal.add(new LinkData(tableName1,columnName1,tableName2,columnName2));
        }

        return retVal;
    }

    protected final List<String> getTableNames( Connection connection ) throws
                                                                   SQLException
    {
        PreparedStatement userTablesStatement =
                connection.prepareStatement( "SELECT TABLE_NAME FROM user_tables ORDER BY table_name" );

        ResultSet userTablesResultSet = userTablesStatement.executeQuery();

        List<String> retVal = new ArrayList<String>();

        while (userTablesResultSet.next() == true)
        {
            String tableName = userTablesResultSet.getString("TABLE_NAME");

            retVal.add(tableName);
        }

        return retVal;
    }

    protected final List<ColumnData> getColumnData( Connection connection, List<String> tableNames ) throws
                                                                                       SQLException
    {
        String sql = "SELECT TABLE_NAME, COLUMN_NAME, COLUMN_ID, DATA_TYPE, DATA_LENGTH, DATA_PRECISION, DATA_SCALE, NULLABLE FROM COLS";

        String whereClause = createTableWhereClause(tableNames,"TABLE_NAME");

        if ( whereClause.length() != 0 )
            sql += " WHERE "+whereClause;

        sql += " ORDER BY TABLE_NAME, COLUMN_ID";

        PreparedStatement columnsByTableStatement = connection.prepareStatement(sql);

        ResultSet columnsResultSet = columnsByTableStatement.executeQuery();

        List<ColumnData> retVal = new ArrayList<ColumnData>();

        while (columnsResultSet.next() == true)
        {
            String tableName = columnsResultSet.getString("TABLE_NAME");
            String columnName = columnsResultSet.getString("COLUMN_NAME");

            ColumnData columnData = new ColumnData(columnName,tableName);

            columnData.setColumnIndex(columnsResultSet.getInt("COLUMN_ID"));
            columnData.setNullable(YesNoBooleanEnum.valueOfByPrefix(columnsResultSet.getString("NULLABLE")));
            columnData.setDataType(DataTypeEnum.valueOfByPrefix(columnsResultSet.getString("DATA_TYPE")));

            if ( columnsResultSet.getObject( "DATA_LENGTH" ) != null )
                columnData.setDataLength(columnsResultSet.getInt("DATA_LENGTH"));

            if ( columnsResultSet.getObject( "DATA_PRECISION" ) != null )
                columnData.setDataPrecision(columnsResultSet.getInt("DATA_PRECISION"));

            if ( columnsResultSet.getObject( "DATA_SCALE" ) != null )
                columnData.setDataScale(columnsResultSet.getInt("DATA_SCALE"));

            retVal.add(columnData);
        }

        return retVal;
    }

    protected final List<KeyData> getKeyData( Connection connection, List<String> tableNames ) throws
                                                                                               SQLException
    {
        String sql ="SELECT COLS.TABLE_NAME TABLE_NAME, COLS.COLUMN_NAME COLUMN_NAME FROM ALL_CONSTRAINTS CONS, ALL_CONS_COLUMNS COLS" +
                    " WHERE  CONS.CONSTRAINT_TYPE = 'P' AND CONS.CONSTRAINT_NAME = COLS.CONSTRAINT_NAME AND CONS.OWNER = COLS.OWNER";

        String whereClause = createTableWhereClause( tableNames, "COLS.TABLE_NAME" );

        if ( whereClause.length() != 0 )
            sql += " AND ("+whereClause+")";

        sql += " ORDER BY COLS.TABLE_NAME, COLS.COLUMN_NAME";

        PreparedStatement primaryKeyByTableStatement = connection.prepareStatement(sql);

        ResultSet primaryKeyResultSet = primaryKeyByTableStatement.executeQuery();

        List<KeyData> retVal = new ArrayList<KeyData>();

        while (primaryKeyResultSet.next() == true)
        {
            String tableName = primaryKeyResultSet.getString("TABLE_NAME");
            String columnName = primaryKeyResultSet.getString("COLUMN_NAME");

            retVal.add(new KeyData(columnName,tableName));
        }

        return retVal;
    }

    private String createTableWhereClause( List<String> tableNames, String table_name )
    {
        String whereClause = "";

        for ( String tableName : tableNames )
        {
            if ( whereClause.length() != 0 )
                whereClause += " OR ";

            whereClause += table_name+" = '"+tableName+"'";
        }

        return whereClause;
    }
}
