package ge.generator.jdbc.impl;

import java.util.*;

import org.apache.log4j.*;

public class SchemaData
{
    private static Logger logger = Logger.getLogger(SchemaData.class);

    private Map<String,TableData> tables = new HashMap<String, TableData>();

    public void addTable( String tableName )
    {
        if ( tables.containsKey(tableName) == false )
        {
            logger.info("Adding table: "+tableName);
            TableData tableData = new TableData(tableName);

            tables.put(tableName,tableData);
        }
    }

    public void removeTableData( TableData data )
    {
        if ( tables.containsKey(data.getTableName()) == true )
        {
            tables.remove(data.getTableName());
        }
    }

    public void addColumnData( ColumnData data )
    {
        if ( tables.containsKey(data.getTableName()) == true )
        {
            TableData tableData = tables.get(data.getTableName());

            tableData.addColumn( data );
        }
    }

    public void removeColumnData( ColumnData data )
    {
        if ( tables.containsKey(data.getTableName()) == true )
        {
            TableData tableData = tables.get(data.getTableName());

            tableData.removeColumn( data );
        }
    }

    public void addKeyData( KeyData data )
    {
        if ( tables.containsKey(data.getTableName()) == true )
        {
            TableData tableData = tables.get(data.getTableName());

            tableData.addKey( data );
        }
    }

    public void addLinkData( LinkData data )
    {
        if ( tables.containsKey(data.getTableName1()) == true )
        {
            TableData tableData = tables.get(data.getTableName1());

            tableData.addLink(data);
        }

        if ( tables.containsKey(data.getTableName2()) == true )
        {
            TableData tableData = tables.get(data.getTableName2());

            tableData.addLink( data );
        }
    }

    public List<ColumnData> getAllColumnData()
    {
        List<ColumnData> retVal = new ArrayList<ColumnData>();

        for ( TableData tableData : tables.values() )
        {
            retVal.addAll(tableData.getColumnData());
        }

        return retVal;
    }

    public List<TableData> getTables()
    {
        return new ArrayList<TableData>( tables.values() );
    }

    public TableData getTableData( String tableName )
    {
        return tables.get(tableName);
    }
}
