package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Attribute
{
    @ResultSetValue("TYPE_CAT")
    private String dataTypeCatalog;

    @ResultSetValue("TYPE_SCHEM")
    private String dataTypeSchema;

    @ResultSetValue("TYPE_NAME")
    private String dataTypeName;

    @ResultSetValue("ATTR_NAME")
    private String name;

    @ResultSetValue("DATA_TYPE")
    private Integer dataType;

    @ResultSetValue("ATTR_TYPE_NAME")
    private String typeName;

    @ResultSetValue("ATTR_SIZE")
    private Integer size;

    @ResultSetValue("DECIMAL_DIGITS")
    private Integer decimalDigits;

    @ResultSetValue("NUM_PREC_RADIX")
    private Integer radix;

    @ResultSetValue("NULLABLE")
    private Integer nullable;

    @ResultSetValue("REMARKS")
    private String remarks;

    @ResultSetValue("ATTR_DEF")
    private String definition;

    @ResultSetValue("SQL_DATA_TYPE")
    private Integer sqlDataType;

    @ResultSetValue("SQL_DATETIME_SUB")
    private Integer sqlDateTimeSub;

    @ResultSetValue("CHAR_OCTET_LENGTH")
    private Integer charOctetLength;

    @ResultSetValue("ORDINAL_POSITION")
    private Integer ordinalPosition;

    @ResultSetValue("IS_NULLABLE")
    private String isNullable;

    @ResultSetValue("SCOPE_CATALOG")
    private String scopeCatalog;

    @ResultSetValue("SCOPE_SCHEMA")
    private String scopeSchema;

    @ResultSetValue("SCOPE_TABLE")
    private String scopeTable;

    @ResultSetValue("SOURCE_DATA_TYPE")
    private Short sourceDataType;

    public Attribute()
    {
    }

    public String getDataTypeCatalog()
    {
        return dataTypeCatalog;
    }

    public void setDataTypeCatalog( String dataTypeCatalog )
    {
        this.dataTypeCatalog = dataTypeCatalog;
    }

    public String getDataTypeSchema()
    {
        return dataTypeSchema;
    }

    public void setDataTypeSchema( String dataTypeSchema )
    {
        this.dataTypeSchema = dataTypeSchema;
    }

    public String getDataTypeName()
    {
        return dataTypeName;
    }

    public void setDataTypeName( String dataTypeName )
    {
        this.dataTypeName = dataTypeName;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Integer getDataType()
    {
        return dataType;
    }

    public void setDataType( Integer dataType )
    {
        this.dataType = dataType;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize( Integer size )
    {
        this.size = size;
    }

    public Integer getDecimalDigits()
    {
        return decimalDigits;
    }

    public void setDecimalDigits( Integer decimalDigits )
    {
        this.decimalDigits = decimalDigits;
    }

    public Integer getRadix()
    {
        return radix;
    }

    public void setRadix( Integer radix )
    {
        this.radix = radix;
    }

    public Integer getNullable()
    {
        return nullable;
    }

    public void setNullable( String nullable )
    {
        isNullable = nullable;
    }

    public String getScopeCatalog()
    {
        return scopeCatalog;
    }

    public void setScopeCatalog( String scopeCatalog )
    {
        this.scopeCatalog = scopeCatalog;
    }

    public String getScopeSchema()
    {
        return scopeSchema;
    }

    public void setScopeSchema( String scopeSchema )
    {
        this.scopeSchema = scopeSchema;
    }

    public String getScopeTable()
    {
        return scopeTable;
    }

    public void setScopeTable( String scopeTable )
    {
        this.scopeTable = scopeTable;
    }

    public Short getSourceDataType()
    {
        return sourceDataType;
    }

    public void setSourceDataType( Short sourceDataType )
    {
        this.sourceDataType = sourceDataType;
    }

    public void setNullable( Integer nullable )
    {
        this.nullable = nullable;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String remarks )
    {
        this.remarks = remarks;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition( String definition )
    {
        this.definition = definition;
    }

    public Integer getSqlDataType()
    {
        return sqlDataType;
    }

    public void setSqlDataType( Integer sqlDataType )
    {
        this.sqlDataType = sqlDataType;
    }

    public Integer getSqlDateTimeSub()
    {
        return sqlDateTimeSub;
    }

    public void setSqlDateTimeSub( Integer sqlDateTimeSub )
    {
        this.sqlDateTimeSub = sqlDateTimeSub;
    }

    public Integer getCharOctetLength()
    {
        return charOctetLength;
    }

    public void setCharOctetLength( Integer charOctetLength )
    {
        this.charOctetLength = charOctetLength;
    }

    public Integer getOrdinalPosition()
    {
        return ordinalPosition;
    }

    public void setOrdinalPosition( Integer ordinalPosition )
    {
        this.ordinalPosition = ordinalPosition;
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

        Attribute attribute = ( Attribute ) o;

        if ( charOctetLength != null ? !charOctetLength.equals( attribute.charOctetLength ) :
             attribute.charOctetLength != null )
        {
            return false;
        }
        if ( dataType != null ? !dataType.equals( attribute.dataType ) : attribute.dataType != null )
        {
            return false;
        }
        if ( dataTypeCatalog != null ? !dataTypeCatalog.equals( attribute.dataTypeCatalog ) :
             attribute.dataTypeCatalog != null )
        {
            return false;
        }
        if ( dataTypeName != null ? !dataTypeName.equals( attribute.dataTypeName ) : attribute.dataTypeName != null )
        {
            return false;
        }
        if ( dataTypeSchema != null ? !dataTypeSchema.equals( attribute.dataTypeSchema ) :
             attribute.dataTypeSchema != null )
        {
            return false;
        }
        if ( decimalDigits != null ? !decimalDigits.equals( attribute.decimalDigits ) :
             attribute.decimalDigits != null )
        {
            return false;
        }
        if ( definition != null ? !definition.equals( attribute.definition ) : attribute.definition != null )
        {
            return false;
        }
        if ( isNullable != null ? !isNullable.equals( attribute.isNullable ) : attribute.isNullable != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( attribute.name ) : attribute.name != null )
        {
            return false;
        }
        if ( nullable != null ? !nullable.equals( attribute.nullable ) : attribute.nullable != null )
        {
            return false;
        }
        if ( ordinalPosition != null ? !ordinalPosition.equals( attribute.ordinalPosition ) :
             attribute.ordinalPosition != null )
        {
            return false;
        }
        if ( radix != null ? !radix.equals( attribute.radix ) : attribute.radix != null )
        {
            return false;
        }
        if ( remarks != null ? !remarks.equals( attribute.remarks ) : attribute.remarks != null )
        {
            return false;
        }
        if ( scopeCatalog != null ? !scopeCatalog.equals( attribute.scopeCatalog ) : attribute.scopeCatalog != null )
        {
            return false;
        }
        if ( scopeSchema != null ? !scopeSchema.equals( attribute.scopeSchema ) : attribute.scopeSchema != null )
        {
            return false;
        }
        if ( scopeTable != null ? !scopeTable.equals( attribute.scopeTable ) : attribute.scopeTable != null )
        {
            return false;
        }
        if ( size != null ? !size.equals( attribute.size ) : attribute.size != null )
        {
            return false;
        }
        if ( sourceDataType != null ? !sourceDataType.equals( attribute.sourceDataType ) :
             attribute.sourceDataType != null )
        {
            return false;
        }
        if ( sqlDataType != null ? !sqlDataType.equals( attribute.sqlDataType ) : attribute.sqlDataType != null )
        {
            return false;
        }
        if ( sqlDateTimeSub != null ? !sqlDateTimeSub.equals( attribute.sqlDateTimeSub ) :
             attribute.sqlDateTimeSub != null )
        {
            return false;
        }
        if ( typeName != null ? !typeName.equals( attribute.typeName ) : attribute.typeName != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = dataTypeCatalog != null ? dataTypeCatalog.hashCode() : 0;
        result = 31 * result + ( dataTypeSchema != null ? dataTypeSchema.hashCode() : 0 );
        result = 31 * result + ( dataTypeName != null ? dataTypeName.hashCode() : 0 );
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( dataType != null ? dataType.hashCode() : 0 );
        result = 31 * result + ( typeName != null ? typeName.hashCode() : 0 );
        result = 31 * result + ( size != null ? size.hashCode() : 0 );
        result = 31 * result + ( decimalDigits != null ? decimalDigits.hashCode() : 0 );
        result = 31 * result + ( radix != null ? radix.hashCode() : 0 );
        result = 31 * result + ( nullable != null ? nullable.hashCode() : 0 );
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( definition != null ? definition.hashCode() : 0 );
        result = 31 * result + ( sqlDataType != null ? sqlDataType.hashCode() : 0 );
        result = 31 * result + ( sqlDateTimeSub != null ? sqlDateTimeSub.hashCode() : 0 );
        result = 31 * result + ( charOctetLength != null ? charOctetLength.hashCode() : 0 );
        result = 31 * result + ( ordinalPosition != null ? ordinalPosition.hashCode() : 0 );
        result = 31 * result + ( isNullable != null ? isNullable.hashCode() : 0 );
        result = 31 * result + ( scopeCatalog != null ? scopeCatalog.hashCode() : 0 );
        result = 31 * result + ( scopeSchema != null ? scopeSchema.hashCode() : 0 );
        result = 31 * result + ( scopeTable != null ? scopeTable.hashCode() : 0 );
        result = 31 * result + ( sourceDataType != null ? sourceDataType.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "Attribute{" +
               "dataTypeCatalog='" + dataTypeCatalog + '\'' +
               ", dataTypeSchema='" + dataTypeSchema + '\'' +
               ", dataTypeName='" + dataTypeName + '\'' +
               ", name='" + name + '\'' +
               ", dataType=" + dataType +
               ", typeName='" + typeName + '\'' +
               ", size=" + size +
               ", decimalDigits=" + decimalDigits +
               ", radix=" + radix +
               ", nullable=" + nullable +
               ", remarks='" + remarks + '\'' +
               ", definition='" + definition + '\'' +
               ", sqlDataType=" + sqlDataType +
               ", sqlDateTimeSub=" + sqlDateTimeSub +
               ", charOctetLength=" + charOctetLength +
               ", ordinalPosition=" + ordinalPosition +
               ", isNullable='" + isNullable + '\'' +
               ", scopeCatalog='" + scopeCatalog + '\'' +
               ", scopeSchema='" + scopeSchema + '\'' +
               ", scopeTable='" + scopeTable + '\'' +
               ", sourceDataType=" + sourceDataType +
               '}';
    }
}
