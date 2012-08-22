package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class ClientInfoProperty
{
    @ResultSetValue("NAME")
    private String name;

    @ResultSetValue("MAX_LEN")
    private Integer maxLength;

    @ResultSetValue("DEFAULT_VALUE")
    private String defaultValue;

    @ResultSetValue("DESCRIPTION")
    private String description;

    public ClientInfoProperty()
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

    public Integer getMaxLength()
    {
        return maxLength;
    }

    public void setMaxLength( Integer maxLength )
    {
        this.maxLength = maxLength;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue( String defaultValue )
    {
        this.defaultValue = defaultValue;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
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

        ClientInfoProperty that = ( ClientInfoProperty ) o;

        if ( defaultValue != null ? !defaultValue.equals( that.defaultValue ) : that.defaultValue != null )
        {
            return false;
        }
        if ( description != null ? !description.equals( that.description ) : that.description != null )
        {
            return false;
        }
        if ( maxLength != null ? !maxLength.equals( that.maxLength ) : that.maxLength != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( that.name ) : that.name != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ( maxLength != null ? maxLength.hashCode() : 0 );
        result = 31 * result + ( defaultValue != null ? defaultValue.hashCode() : 0 );
        result = 31 * result + ( description != null ? description.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "ClientInfoProperty{" +
               "name='" + name + '\'' +
               ", maxLength=" + maxLength +
               ", defaultValue='" + defaultValue + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
