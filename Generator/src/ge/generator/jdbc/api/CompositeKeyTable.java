package ge.generator.jdbc.api;

import java.util.*;

public class CompositeKeyTable extends Table
{
    private List<CompositeKeyColumn> keyColumns = new ArrayList<CompositeKeyColumn>();

    public CompositeKeyTable( String tableName )
    {
        super(tableName);
    }

    public boolean addKeyColumn( CompositeKeyColumn column )
    {
        return keyColumns.add( column );
    }

    public List<CompositeKeyColumn> getKeyColumns()
    {
        return keyColumns;
    }
}
