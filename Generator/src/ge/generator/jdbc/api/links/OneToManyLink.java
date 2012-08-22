package ge.generator.jdbc.api.links;

public class OneToManyLink implements Link
{
    private String linkTableName;

    private String linkColumnName;

    public void setLinkTableName( String linkTableName )
    {
        this.linkTableName = linkTableName;
    }

    public void setLinkColumnName( String linkColumnName )
    {
        this.linkColumnName = linkColumnName;
    }

    public String getLinkColumnName()
    {
        return linkColumnName;
    }

    public String getLinkTableName()
    {
        return linkTableName;
    }
}
