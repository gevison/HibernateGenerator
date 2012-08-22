package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Table
{
    @ResultSetValue("TABLE_CAT")
    private String catalog;

    @ResultSetValue("TABLE_SCHEM")
    private String schema;

    @ResultSetValue("TABLE_NAME")
    private String name;

    @ResultSetValue("TABLE_TYPE")
    private String type;

    @ResultSetValue("REMARKS")
    private String remarks;

    @ResultSetValue("TYPE_CAT")
    private String typesCatalog;

    @ResultSetValue("TYPE_SCHEM")
    private String typesSchema;

    @ResultSetValue("TYPE_NAME")
    private String typesName;

    @ResultSetValue("SELF_REFERENCING_COL_NAME")
    private String selfReferencingColumnName;

    @ResultSetValue("REF_GENERATION")
    private String referenceGeneration;

    public Table()
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

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getReferenceGeneration()
    {
        return referenceGeneration;
    }

    public void setReferenceGeneration( String referenceGeneration )
    {
        this.referenceGeneration = referenceGeneration;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String remarks )
    {
        this.remarks = remarks;
    }

    public String getSchema()
    {
        return schema;
    }

    public void setSchema( String schema )
    {
        this.schema = schema;
    }

    public String getSelfReferencingColumnName()
    {
        return selfReferencingColumnName;
    }

    public void setSelfReferencingColumnName( String selfReferencingColumnName )
    {
        this.selfReferencingColumnName = selfReferencingColumnName;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public String getTypesCatalog()
    {
        return typesCatalog;
    }

    public void setTypesCatalog( String typesCatalog )
    {
        this.typesCatalog = typesCatalog;
    }

    public String getTypesName()
    {
        return typesName;
    }

    public void setTypesName( String typesName )
    {
        this.typesName = typesName;
    }

    public String getTypesSchema()
    {
        return typesSchema;
    }

    public void setTypesSchema( String typesSchema )
    {
        this.typesSchema = typesSchema;
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

        Table table = ( Table ) o;

        if ( catalog != null ? !catalog.equals( table.catalog ) : table.catalog != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( table.name ) : table.name != null )
        {
            return false;
        }
        if ( referenceGeneration != null ? !referenceGeneration.equals( table.referenceGeneration ) :
             table.referenceGeneration != null )
        {
            return false;
        }
        if ( remarks != null ? !remarks.equals( table.remarks ) : table.remarks != null )
        {
            return false;
        }
        if ( schema != null ? !schema.equals( table.schema ) : table.schema != null )
        {
            return false;
        }
        if ( selfReferencingColumnName != null ? !selfReferencingColumnName.equals( table.selfReferencingColumnName ) :
             table.selfReferencingColumnName != null )
        {
            return false;
        }
        if ( type != null ? !type.equals( table.type ) : table.type != null )
        {
            return false;
        }
        if ( typesCatalog != null ? !typesCatalog.equals( table.typesCatalog ) : table.typesCatalog != null )
        {
            return false;
        }
        if ( typesName != null ? !typesName.equals( table.typesName ) : table.typesName != null )
        {
            return false;
        }
        if ( typesSchema != null ? !typesSchema.equals( table.typesSchema ) : table.typesSchema != null )
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
        result = 31 * result + ( type != null ? type.hashCode() : 0 );
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( typesCatalog != null ? typesCatalog.hashCode() : 0 );
        result = 31 * result + ( typesSchema != null ? typesSchema.hashCode() : 0 );
        result = 31 * result + ( typesName != null ? typesName.hashCode() : 0 );
        result = 31 * result + ( selfReferencingColumnName != null ? selfReferencingColumnName.hashCode() : 0 );
        result = 31 * result + ( referenceGeneration != null ? referenceGeneration.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "Table{" +
               "catalog='" + catalog + '\'' +
               ", schema='" + schema + '\'' +
               ", name='" + name + '\'' +
               ", type='" + type + '\'' +
               ", remarks='" + remarks + '\'' +
               ", typesCatalog='" + typesCatalog + '\'' +
               ", typesSchema='" + typesSchema + '\'' +
               ", typesName='" + typesName + '\'' +
               ", selfReferencingColumnName='" + selfReferencingColumnName + '\'' +
               ", referenceGeneration='" + referenceGeneration + '\'' +
               '}';
    }
}
