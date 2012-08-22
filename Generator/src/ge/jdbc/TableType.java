package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class TableType
{
    @ResultSetValue("TABLE_TYPE")
    private String type;

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
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

        TableType tableType = ( TableType ) o;

        if ( type != null ? !type.equals( tableType.type ) : tableType.type != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "TableType{" +
               "type='" + type + '\'' +
               '}';
    }
}
