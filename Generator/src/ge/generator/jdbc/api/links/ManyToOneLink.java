package ge.generator.jdbc.api.links;

public class ManyToOneLink implements Link
{
    private String columnName;

    private String linkTableName;

    public void setColumnName( String columnName )
    {
        this.columnName = columnName;
    }

    public void setLinkTableName( String linkTableName )
    {
        this.linkTableName = linkTableName;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public String getLinkTableName()
    {
        return linkTableName;
    }
}
