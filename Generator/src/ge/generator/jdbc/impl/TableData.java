package ge.generator.jdbc.impl;

import java.util.*;

import org.apache.log4j.*;

public class TableData
{
    private static final Logger logger = Logger.getLogger(TableData.class);

    private String tableName;

    private Map<String, ColumnData> columns = new HashMap<String, ColumnData>();

    private Map<String, KeyData> keys = new HashMap<String, KeyData>();

    private List<LinkData> links = new ArrayList<LinkData>();

    public TableData( String tableName )
    {
        this.tableName = tableName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void addColumn( ColumnData data )
    {
        if ( columns.containsKey(data.getColumnName()) == false )
        {
            logger.info("Adding Column: "+data.getColumnName()+" to table: "+tableName);
            columns.put(data.getColumnName(),data);
        }
    }

    public void addKey( KeyData data )
    {
        if (( columns.containsKey(data.getColumnName()) == true ) && ( keys.containsKey(data.getColumnName()) == false ))
        {
            logger.info("Adding Key: "+data.getColumnName()+" to table: "+tableName);
            keys.put(data.getColumnName(),data);
        }
    }

    public void addLink( LinkData data )
    {
        if ( tableName.equals(data.getTableName1()) == true )
        {
            if ( columns.containsKey(data.getColumnName1()) == true )
            {
                logger.info("Adding Link: "+data.getTableName1()+"."+data.getColumnName1()+" -> "+data.getTableName2()+"."+data.getColumnName2()+" to "+tableName);
                links.add(data);
            }
        }
        else if ( tableName.equals(data.getTableName2()) == true )
        {
            if ( columns.containsKey(data.getColumnName2()) == true )
            {
                logger.info("Adding Link: "+data.getTableName1()+"."+data.getColumnName1()+" -> "+data.getTableName2()+"."+data.getColumnName2()+" to "+tableName);
                links.add(data);
            }
        }
    }


    public List<ColumnData> getColumnData()
    {
        return new ArrayList<ColumnData>(columns.values());
    }

    public ColumnData getColumnData( String columnName )
    {
        return columns.get(columnName);
    }

    public void removeColumn( ColumnData data )
    {
        String columnName = data.getColumnName();

        if ( columns.containsKey( columnName ) == true )
        {
            logger.info("Removing Column: "+data.getColumnName()+" from table: "+tableName);
            columns.remove( columnName );
        }

        if ( keys.containsKey( columnName ) == true )
        {
            logger.info("Removing Key: "+data.getColumnName()+" from table: "+tableName);
            keys.remove( columnName );
        }

        List<LinkData> remove = new ArrayList<LinkData>();

        for ( LinkData link : links )
        {
            if (( tableName.equals(link.getTableName1()) == true ) && ( columnName.equals(link.getColumnName1()) == true ))
            {
                remove.add(link);
            }
            else if (( tableName.equals(link.getTableName2()) == true ) && ( columnName.equals(link.getColumnName2()) == true ))
            {
                remove.add(link);
            }
        }

        for ( LinkData link: remove )
        {
            logger.info("Removing Link: "+link.getTableName1()+"."+link.getColumnName1()+" -> "+link.getTableName2()+"."+link.getColumnName2()+" from "+tableName);
            links.remove(link);
        }
    }

    public boolean isLinkTable()
    {
        if (( keys.size() > 1 ) && ( columns.size() == keys.size()))
            return true;

        return false;
    }

    public boolean isCompositeTable()
    {
        if ( keys.size() > 1 )
            return true;

        return false;
    }

    public List<KeyData> getKeyData()
    {
        return new ArrayList<KeyData>(keys.values());
    }

    public boolean isColumnKey(String columnName)
    {
        return keys.containsKey(columnName);
    }

    public List<LinkData> getLinks( String columnName )
    {
        List<LinkData> retVal = new ArrayList<LinkData>();

        for ( LinkData link : links )
        {
            if (( tableName.equals(link.getTableName1()) == true ) && ( columnName.equals(link.getColumnName1()) == true ))
            {
                retVal.add(link);
            }
            else if (( tableName.equals(link.getTableName2()) == true ) && ( columnName.equals(link.getColumnName2()) == true ))
            {
                retVal.add(link);
            }
        }

        return retVal;
    }

    public List<LinkData> getLinks()
    {
        return links;
    }
}
