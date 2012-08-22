package ge.generator.jdbc.impl;

public class KeyData
{
    private final String columnName;

    private final String tableName;

    public KeyData( String columnName, String tableName )
    {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public String getTableName()
    {
        return tableName;
    }
}
