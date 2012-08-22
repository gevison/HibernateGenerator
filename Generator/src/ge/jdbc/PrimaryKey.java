package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class PrimaryKey
{
    @ResultSetValue("TABLE_CAT")
    private String tableCatalog;

    @ResultSetValue("TABLE_SCHEM")
    private String tableSchema;

    @ResultSetValue("TABLE_NAME")
    private String tableName;

    @ResultSetValue("COLUMN_NAME")
    private String columnName;

    @ResultSetValue("KEY_SEQ")
    private Short keySequence;

    @ResultSetValue("PK_NAME")
    private String primaryKeyName;

    public PrimaryKey()
    {
    }

    public String getTableCatalog()
    {
        return tableCatalog;
    }

    public void setTableCatalog( String tableCatalog )
    {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema()
    {
        return tableSchema;
    }

    public void setTableSchema( String tableSchema )
    {
        this.tableSchema = tableSchema;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName( String tableName )
    {
        this.tableName = tableName;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName( String columnName )
    {
        this.columnName = columnName;
    }

    public Short getKeySequence()
    {
        return keySequence;
    }

    public void setKeySequence( Short keySequence )
    {
        this.keySequence = keySequence;
    }

    public String getPrimaryKeyName()
    {
        return primaryKeyName;
    }

    public void setPrimaryKeyName( String primaryKeyName )
    {
        this.primaryKeyName = primaryKeyName;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }

        PrimaryKey that = ( PrimaryKey ) o;

        if ( columnName != null ? !columnName.equals( that.columnName ) : that.columnName != null )
        {
            return false;
        }
        if ( keySequence != null ? !keySequence.equals( that.keySequence ) : that.keySequence != null )
        {
            return false;
        }
        if ( primaryKeyName != null ? !primaryKeyName.equals( that.primaryKeyName ) : that.primaryKeyName != null )
        {
            return false;
        }
        if ( tableCatalog != null ? !tableCatalog.equals( that.tableCatalog ) : that.tableCatalog != null )
        {
            return false;
        }
        if ( tableName != null ? !tableName.equals( that.tableName ) : that.tableName != null )
        {
            return false;
        }
        if ( tableSchema != null ? !tableSchema.equals( that.tableSchema ) : that.tableSchema != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + ( tableSchema != null ? tableSchema.hashCode() : 0 );
        result = 31 * result + ( tableName != null ? tableName.hashCode() : 0 );
        result = 31 * result + ( columnName != null ? columnName.hashCode() : 0 );
        result = 31 * result + ( keySequence != null ? keySequence.hashCode() : 0 );
        result = 31 * result + ( primaryKeyName != null ? primaryKeyName.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "PrimaryKey{" +
               "tableCatalog='" + tableCatalog + '\'' +
               ", tableSchema='" + tableSchema + '\'' +
               ", tableName='" + tableName + '\'' +
               ", columnName='" + columnName + '\'' +
               ", keySequence='" + keySequence + '\'' +
               ", primaryKeyName='" + primaryKeyName + '\'' +
               '}';
    }
}
