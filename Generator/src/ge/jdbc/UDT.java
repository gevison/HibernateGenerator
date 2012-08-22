package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class UDT
{
    @ResultSetValue("TYPE_CAT")
    private String catalog;

    @ResultSetValue("TYPE_SCHEM")
    private String schema;

    @ResultSetValue("TYPE_NAME")
    private String name;

    @ResultSetValue("CLASS_NAME")
    private String className;

    @ResultSetValue("DATA_TYPE")
    private int dataType;

    @ResultSetValue("REMARKS")
    private String remarks;

    @ResultSetValue("BASE_TYPE")
    private Short baseType;

    public UDT()
    {
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

        UDT udt = ( UDT ) o;

        if ( dataType != udt.dataType )
        {
            return false;
        }
        if ( baseType != null ? !baseType.equals( udt.baseType ) : udt.baseType != null )
        {
            return false;
        }
        if ( catalog != null ? !catalog.equals( udt.catalog ) : udt.catalog != null )
        {
            return false;
        }
        if ( className != null ? !className.equals( udt.className ) : udt.className != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( udt.name ) : udt.name != null )
        {
            return false;
        }
        if ( remarks != null ? !remarks.equals( udt.remarks ) : udt.remarks != null )
        {
            return false;
        }
        if ( schema != null ? !schema.equals( udt.schema ) : udt.schema != null )
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
        result = 31 * result + ( className != null ? className.hashCode() : 0 );
        result = 31 * result + dataType;
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( baseType != null ? baseType.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "UDT{" +
               "catalog='" + catalog + '\'' +
               ", schema='" + schema + '\'' +
               ", name='" + name + '\'' +
               ", className='" + className + '\'' +
               ", dataType=" + dataType +
               ", remarks='" + remarks + '\'' +
               ", baseType=" + baseType +
               '}';
    }
}
