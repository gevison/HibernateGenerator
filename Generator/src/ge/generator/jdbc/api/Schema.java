package ge.generator.jdbc.api;

import java.util.*;

public class Schema
{
    private Map<String, Table> tables = new HashMap<String, Table>();

    public void addTable( Table table )
    {
        tables.put( table.getTableName(), table );
    }

    public Table getTable( String tableName )
    {
        return tables.get(tableName);
    }

    public List<Table> getTables()
    {
        return new ArrayList<Table>(tables.values());
    }
}
