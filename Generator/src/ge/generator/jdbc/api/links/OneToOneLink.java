package ge.generator.jdbc.api.links;

public class OneToOneLink implements Link
{
    private String linkTableName;

    public void setLinkTableName( String linkTableName )
    {
        this.linkTableName = linkTableName;
    }

    public String getLinkTableName()
    {
        return linkTableName;
    }
}
