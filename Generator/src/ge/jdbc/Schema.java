package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Schema
{
    @ResultSetValue("TABLE_SCHEM")
    private String name;

    @ResultSetValue("TABLE_CATALOG")
    private String catalog;

    public Schema()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getCatalog()
    {
        return catalog;
    }

    public void setCatalog( String catalog )
    {
        this.catalog = catalog;
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

        Schema schema = ( Schema ) o;

        if ( catalog != null ? !catalog.equals( schema.catalog ) : schema.catalog != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( schema.name ) : schema.name != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ( catalog != null ? catalog.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "Schema{" +
               "catalog='" + catalog + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}
