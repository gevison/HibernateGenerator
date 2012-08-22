package ge.generator.jdbc.api;

public class PrimaryKeyTable extends Table
{
    private PrimaryKeyColumn primaryKeyColumn;

    public PrimaryKeyTable( String tableName )
    {
        super(tableName);
    }

    public PrimaryKeyColumn getPrimaryKeyColumn()
    {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn( PrimaryKeyColumn primaryKeyColumn )
    {
        this.primaryKeyColumn = primaryKeyColumn;
    }
}
