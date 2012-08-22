package ge.generator.jdbc.impl;

public class LinkData
{
    private final String tableName1;

    private final String columnName1;

    private final String tableName2;

    private final String columnName2;

    public LinkData( String tableName1, String columnName1, String tableName2, String columnName2 )
    {
        this.tableName1 = tableName1;
        this.columnName1 = columnName1;
        this.tableName2 = tableName2;
        this.columnName2 = columnName2;
    }

    public String getColumnName1()
    {
        return columnName1;
    }

    public String getColumnName2()
    {
        return columnName2;
    }

    public String getTableName1()
    {
        return tableName1;
    }

    public String getTableName2()
    {
        return tableName2;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
            return true;
        if ( !( o instanceof LinkData ) )
            return false;

        LinkData linkData = ( LinkData ) o;

        if ( columnName1 != null ? !columnName1.equals( linkData.columnName1 ) : linkData.columnName1 != null )
            return false;
        if ( columnName2 != null ? !columnName2.equals( linkData.columnName2 ) : linkData.columnName2 != null )
            return false;
        if ( tableName1 != null ? !tableName1.equals( linkData.tableName1 ) : linkData.tableName1 != null )
            return false;
        if ( tableName2 != null ? !tableName2.equals( linkData.tableName2 ) : linkData.tableName2 != null )
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tableName1 != null ? tableName1.hashCode() : 0;
        result = 31 * result + ( columnName1 != null ? columnName1.hashCode() : 0 );
        result = 31 * result + ( tableName2 != null ? tableName2.hashCode() : 0 );
        result = 31 * result + ( columnName2 != null ? columnName2.hashCode() : 0 );
        return result;
    }
}
