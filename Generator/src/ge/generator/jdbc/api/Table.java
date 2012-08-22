package ge.generator.jdbc.api;

import java.util.*;

import ge.generator.jdbc.api.links.*;

public abstract class Table
{
    private String tableName;

    private List<Column> columns = new ArrayList<Column>();

    private List<Link> links = new ArrayList<Link>();

    public Table( String tableName )
    {
        this.tableName = tableName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void addColumn( Column column )
    {
        columns.add(column);
    }

    public void addLink( Link link )
    {
        links.add(link);
    }

    public List<Column> getColumns()
    {
        return columns;
    }

    public List<Link> getLinks()
    {
        return links;
    }
}
