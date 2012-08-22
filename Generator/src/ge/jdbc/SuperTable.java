package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class SuperTable
{
    @ResultSetValue("TABLE_CAT")
    private String catalog;

    @ResultSetValue("TABLE_SCHEM")
    private String schema;

    @ResultSetValue("TABLE_NAME")
    private String name;

    @ResultSetValue("SUPERTABLE_NAME")
    private String superTableName;

    public SuperTable()
    {
    }

    public String getCatalog()
    {
        return catalog;
    }

    public void setCatalog( String catalog )
    {
        this.catalog = catalog;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema( String schema )
    {
        this.schema = schema;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getSuperTableName()
    {
        return superTableName;
    }

    public void setSuperTableName( String superTableName )
    {
        this.superTableName = superTableName;
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

        SuperTable that = ( SuperTable ) o;

        if ( catalog != null ? !catalog.equals( that.catalog ) : that.catalog != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( that.name ) : that.name != null )
        {
            return false;
        }
        if ( schema != null ? !schema.equals( that.schema ) : that.schema != null )
        {
            return false;
        }
        if ( superTableName != null ? !superTableName.equals( that.superTableName ) : that.superTableName != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = catalog != null ? catalog.hashCode() : 0;
        result = 31 * result + ( schema != null ? schema.hashCode() : 0 );
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( superTableName != null ? superTableName.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "SuperTable{" +
               "catalog='" + catalog + '\'' +
               ", schema='" + schema + '\'' +
               ", name='" + name + '\'' +
               ", superTableName='" + superTableName + '\'' +
               '}';
    }
}
