package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Key
{
    @ResultSetValue("PKTABLE_CAT")
    private String primaryKeyCatalog;

    @ResultSetValue("PKTABLE_SCHEM")
    private String primaryKeySchema;

    @ResultSetValue("PKTABLE_NAME")
    private String primaryKeyTable;

    @ResultSetValue("PKCOLUMN_NAME")
    private String primaryKeyColumnName;

    @ResultSetValue("FKTABLE_CAT")
    private String foreignKeyCatalog;

    @ResultSetValue("FKTABLE_SCHEM")
    private String foreignKeySchema;

    @ResultSetValue("FKTABLE_NAME")
    private String foreignKeyTable;

    @ResultSetValue("FKCOLUMN_NAME")
    private String foreignKeyColumnName;

    @ResultSetValue("KEY_SEQ")
    private Short keySequence;

    @ResultSetValue("UPDATE_RULE")
    private Short updateRule;

    @ResultSetValue("DELETE_RULE")
    private Short deleteRule;

    @ResultSetValue("FK_NAME")
    private String foreignKeyName;

    @ResultSetValue("PK_NAME")
    private String primaryKeyName;

    @ResultSetValue("DEFERRABILITY")
    private Short deferrability;

    public Key()
    {
    }

    public String getPrimaryKeyCatalog()
    {
        return primaryKeyCatalog;
    }

    public void setPrimaryKeyCatalog( String primaryKeyCatalog )
    {
        this.primaryKeyCatalog = primaryKeyCatalog;
    }

    public String getPrimaryKeySchema()
    {
        return primaryKeySchema;
    }

    public void setPrimaryKeySchema( String primaryKeySchema )
    {
        this.primaryKeySchema = primaryKeySchema;
    }

    public String getPrimaryKeyTable()
    {
        return primaryKeyTable;
    }

    public void setPrimaryKeyTable( String primaryKeyTable )
    {
        this.primaryKeyTable = primaryKeyTable;
    }

    public String getPrimaryKeyColumnName()
    {
        return primaryKeyColumnName;
    }

    public void setPrimaryKeyColumnName( String primaryKeyColumnName )
    {
        this.primaryKeyColumnName = primaryKeyColumnName;
    }

    public String getForeignKeyCatalog()
    {
        return foreignKeyCatalog;
    }

    public void setForeignKeyCatalog( String foreignKeyCatalog )
    {
        this.foreignKeyCatalog = foreignKeyCatalog;
    }

    public String getForeignKeySchema()
    {
        return foreignKeySchema;
    }

    public void setForeignKeySchema( String foreignKeySchema )
    {
        this.foreignKeySchema = foreignKeySchema;
    }

    public String getForeignKeyTable()
    {
        return foreignKeyTable;
    }

    public void setForeignKeyTable( String foreignKeyTable )
    {
        this.foreignKeyTable = foreignKeyTable;
    }

    public String getForeignKeyColumnName()
    {
        return foreignKeyColumnName;
    }

    public void setForeignKeyColumnName( String foreignKeyColumnName )
    {
        this.foreignKeyColumnName = foreignKeyColumnName;
    }

    public Short getKeySequence()
    {
        return keySequence;
    }

    public void setKeySequence( Short keySequence )
    {
        this.keySequence = keySequence;
    }

    public Short getUpdateRule()
    {
        return updateRule;
    }

    public void setUpdateRule( Short updateRule )
    {
        this.updateRule = updateRule;
    }

    public Short getDeleteRule()
    {
        return deleteRule;
    }

    public void setDeleteRule( Short deleteRule )
    {
        this.deleteRule = deleteRule;
    }

    public String getForeignKeyName()
    {
        return foreignKeyName;
    }

    public void setForeignKeyName( String foreignKeyName )
    {
        this.foreignKeyName = foreignKeyName;
    }

    public String getPrimaryKeyName()
    {
        return primaryKeyName;
    }

    public void setPrimaryKeyName( String primaryKeyName )
    {
        this.primaryKeyName = primaryKeyName;
    }

    public Short getDeferrability()
    {
        return deferrability;
    }

    public void setDeferrability( Short deferrability )
    {
        this.deferrability = deferrability;
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

        Key key = ( Key ) o;

        if ( deferrability != null ? !deferrability.equals( key.deferrability ) : key.deferrability != null )
        {
            return false;
        }
        if ( deleteRule != null ? !deleteRule.equals( key.deleteRule ) : key.deleteRule != null )
        {
            return false;
        }
        if ( foreignKeyCatalog != null ? !foreignKeyCatalog.equals( key.foreignKeyCatalog ) :
             key.foreignKeyCatalog != null )
        {
            return false;
        }
        if ( foreignKeyColumnName != null ? !foreignKeyColumnName.equals( key.foreignKeyColumnName ) :
             key.foreignKeyColumnName != null )
        {
            return false;
        }
        if ( foreignKeyName != null ? !foreignKeyName.equals( key.foreignKeyName ) : key.foreignKeyName != null )
        {
            return false;
        }
        if ( foreignKeySchema != null ? !foreignKeySchema.equals( key.foreignKeySchema ) :
             key.foreignKeySchema != null )
        {
            return false;
        }
        if ( foreignKeyTable != null ? !foreignKeyTable.equals( key.foreignKeyTable ) : key.foreignKeyTable != null )
        {
            return false;
        }
        if ( keySequence != null ? !keySequence.equals( key.keySequence ) : key.keySequence != null )
        {
            return false;
        }
        if ( primaryKeyCatalog != null ? !primaryKeyCatalog.equals( key.primaryKeyCatalog ) :
             key.primaryKeyCatalog != null )
        {
            return false;
        }
        if ( primaryKeyColumnName != null ? !primaryKeyColumnName.equals( key.primaryKeyColumnName ) :
             key.primaryKeyColumnName != null )
        {
            return false;
        }
        if ( primaryKeyName != null ? !primaryKeyName.equals( key.primaryKeyName ) : key.primaryKeyName != null )
        {
            return false;
        }
        if ( primaryKeySchema != null ? !primaryKeySchema.equals( key.primaryKeySchema ) :
             key.primaryKeySchema != null )
        {
            return false;
        }
        if ( primaryKeyTable != null ? !primaryKeyTable.equals( key.primaryKeyTable ) : key.primaryKeyTable != null )
        {
            return false;
        }
        if ( updateRule != null ? !updateRule.equals( key.updateRule ) : key.updateRule != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = primaryKeyCatalog != null ? primaryKeyCatalog.hashCode() : 0;
        result = 31 * result + ( primaryKeySchema != null ? primaryKeySchema.hashCode() : 0 );
        result = 31 * result + ( primaryKeyTable != null ? primaryKeyTable.hashCode() : 0 );
        result = 31 * result + ( primaryKeyColumnName != null ? primaryKeyColumnName.hashCode() : 0 );
        result = 31 * result + ( foreignKeyCatalog != null ? foreignKeyCatalog.hashCode() : 0 );
        result = 31 * result + ( foreignKeySchema != null ? foreignKeySchema.hashCode() : 0 );
        result = 31 * result + ( foreignKeyTable != null ? foreignKeyTable.hashCode() : 0 );
        result = 31 * result + ( foreignKeyColumnName != null ? foreignKeyColumnName.hashCode() : 0 );
        result = 31 * result + ( keySequence != null ? keySequence.hashCode() : 0 );
        result = 31 * result + ( updateRule != null ? updateRule.hashCode() : 0 );
        result = 31 * result + ( deleteRule != null ? deleteRule.hashCode() : 0 );
        result = 31 * result + ( foreignKeyName != null ? foreignKeyName.hashCode() : 0 );
        result = 31 * result + ( primaryKeyName != null ? primaryKeyName.hashCode() : 0 );
        result = 31 * result + ( deferrability != null ? deferrability.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "Key{" +
               "primaryKeyCatalog='" + primaryKeyCatalog + '\'' +
               ", primaryKeySchema='" + primaryKeySchema + '\'' +
               ", primaryKeyTable='" + primaryKeyTable + '\'' +
               ", primaryKeyColumnName='" + primaryKeyColumnName + '\'' +
               ", foreignKeyCatalog='" + foreignKeyCatalog + '\'' +
               ", foreignKeySchema='" + foreignKeySchema + '\'' +
               ", foreignKeyTable='" + foreignKeyTable + '\'' +
               ", foreignKeyColumnName='" + foreignKeyColumnName + '\'' +
               ", keySequence=" + keySequence +
               ", updateRule=" + updateRule +
               ", deleteRule=" + deleteRule +
               ", foreignKeyName='" + foreignKeyName + '\'' +
               ", primaryKeyName='" + primaryKeyName + '\'' +
               ", deferrability=" + deferrability +
               '}';
    }
}
