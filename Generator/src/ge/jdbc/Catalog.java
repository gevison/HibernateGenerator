package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Catalog
{
    @ResultSetValue("TABLE_CAT")
    private String name;

    public Catalog()
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

        Catalog catalog = ( Catalog ) o;

        if ( name != null ? !name.equals( catalog.name ) : catalog.name != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Catalog{" +
               "name='" + name + '\'' +
               '}';
    }
}
