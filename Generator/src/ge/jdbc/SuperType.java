package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class SuperType
{
    @ResultSetValue("TYPE_CAT")
    private String catalog;

    @ResultSetValue("TYPE_SCHEM")
    private String schema;

    @ResultSetValue("TYPE_NAME")
    private String name;

    @ResultSetValue("SUPERTYPE_CAT")
    private String superCatalog;

    @ResultSetValue("SUPERTYPE_SCHEM")
    private String superSchema;

    @ResultSetValue("SUPERTYPE_NAME")
    private String superTypeName;

    public SuperType()
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

    public String getSuperCatalog()
    {
        return superCatalog;
    }

    public void setSuperCatalog( String superCatalog )
    {
        this.superCatalog = superCatalog;
    }

    public String getSuperSchema()
    {
        return superSchema;
    }

    public void setSuperSchema( String superSchema )
    {
        this.superSchema = superSchema;
    }

    public String getSuperTypeName()
    {
        return superTypeName;
    }

    public void setSuperTypeName( String superTypeName )
    {
        this.superTypeName = superTypeName;
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

        SuperType superType = ( SuperType ) o;

        if ( catalog != null ? !catalog.equals( superType.catalog ) : superType.catalog != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( superType.name ) : superType.name != null )
        {
            return false;
        }
        if ( schema != null ? !schema.equals( superType.schema ) : superType.schema != null )
        {
            return false;
        }
        if ( superCatalog != null ? !superCatalog.equals( superType.superCatalog ) : superType.superCatalog != null )
        {
            return false;
        }
        if ( superSchema != null ? !superSchema.equals( superType.superSchema ) : superType.superSchema != null )
        {
            return false;
        }
        if ( superTypeName != null ? !superTypeName.equals( superType.superTypeName ) :
             superType.superTypeName != null )
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
        result = 31 * result + ( superCatalog != null ? superCatalog.hashCode() : 0 );
        result = 31 * result + ( superSchema != null ? superSchema.hashCode() : 0 );
        result = 31 * result + ( superTypeName != null ? superTypeName.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "SuperType{" +
               "catalog='" + catalog + '\'' +
               ", schema='" + schema + '\'' +
               ", name='" + name + '\'' +
               ", superCatalog='" + superCatalog + '\'' +
               ", superSchema='" + superSchema + '\'' +
               ", superTypeName='" + superTypeName + '\'' +
               '}';
    }
}
