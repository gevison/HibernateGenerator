package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class FunctionColumn
{
    @ResultSetValue("FUNCTION_CAT")
    private String functionCatalog;

    @ResultSetValue("FUNCTION_SCHEM")
    private String functionSchema;

    @ResultSetValue("FUNCTION_NAME")
    private String functionName;

    @ResultSetValue("COLUMN_NAME")
    private String name;

    @ResultSetValue("COLUMN_TYPE")
    private Short columnType;

    @ResultSetValue("DATA_TYPE")
    private Integer dataType;

    @ResultSetValue("TYPE_NAME")
    private String typeName;

    @ResultSetValue("PRECISION")
    private Integer precision;

    @ResultSetValue("LENGTH")
    private Integer length;

    @ResultSetValue("SCALE")
    private Short scale;

    @ResultSetValue("RADIX")
    private Short radix;

    @ResultSetValue("NULLABLE")
    private Short nullable;

    @ResultSetValue("REMARKS")
    private String remarks;

    @ResultSetValue("CHAR_OCTET_LENGTH")
    private Integer charOctetLength;

    @ResultSetValue("ORDINAL_POSITION")
    private Integer ordinalPosition;

    @ResultSetValue("IS_NULLABLE")
    private String isNullable;

    @ResultSetValue("SPECIFIC_NAME")
    private String specificName;

    public FunctionColumn()
    {
    }

    public String getFunctionCatalog()
    {
        return functionCatalog;
    }

    public void setFunctionCatalog( String functionCatalog )
    {
        this.functionCatalog = functionCatalog;
    }

    public String getFunctionSchema()
    {
        return functionSchema;
    }

    public void setFunctionSchema( String functionSchema )
    {
        this.functionSchema = functionSchema;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName( String functionName )
    {
        this.functionName = functionName;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Short getColumnType()
    {
        return columnType;
    }

    public void setColumnType( Short columnType )
    {
        this.columnType = columnType;
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

    public Integer getPrecision()
    {
        return precision;
    }

    public void setPrecision( Integer precision )
    {
        this.precision = precision;
    }

    public Integer getLength()
    {
        return length;
    }

    public void setLength( Integer length )
    {
        this.length = length;
    }

    public Short getScale()
    {
        return scale;
    }

    public void setScale( Short scale )
    {
        this.scale = scale;
    }

    public Short getRadix()
    {
        return radix;
    }

    public void setRadix( Short radix )
    {
        this.radix = radix;
    }

    public Short getNullable()
    {
        return nullable;
    }

    public void setNullable( String nullable )
    {
        isNullable = nullable;
    }

    public String getSpecificName()
    {
        return specificName;
    }

    public void setSpecificName( String specificName )
    {
        this.specificName = specificName;
    }

    public void setNullable( Short nullable )
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

        FunctionColumn that = ( FunctionColumn ) o;

        if ( charOctetLength != null ? !charOctetLength.equals( that.charOctetLength ) : that.charOctetLength != null )
        {
            return false;
        }
        if ( columnType != null ? !columnType.equals( that.columnType ) : that.columnType != null )
        {
            return false;
        }
        if ( dataType != null ? !dataType.equals( that.dataType ) : that.dataType != null )
        {
            return false;
        }
        if ( functionCatalog != null ? !functionCatalog.equals( that.functionCatalog ) : that.functionCatalog != null )
        {
            return false;
        }
        if ( functionName != null ? !functionName.equals( that.functionName ) : that.functionName != null )
        {
            return false;
        }
        if ( functionSchema != null ? !functionSchema.equals( that.functionSchema ) : that.functionSchema != null )
        {
            return false;
        }
        if ( isNullable != null ? !isNullable.equals( that.isNullable ) : that.isNullable != null )
        {
            return false;
        }
        if ( length != null ? !length.equals( that.length ) : that.length != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( that.name ) : that.name != null )
        {
            return false;
        }
        if ( nullable != null ? !nullable.equals( that.nullable ) : that.nullable != null )
        {
            return false;
        }
        if ( ordinalPosition != null ? !ordinalPosition.equals( that.ordinalPosition ) : that.ordinalPosition != null )
        {
            return false;
        }
        if ( precision != null ? !precision.equals( that.precision ) : that.precision != null )
        {
            return false;
        }
        if ( radix != null ? !radix.equals( that.radix ) : that.radix != null )
        {
            return false;
        }
        if ( remarks != null ? !remarks.equals( that.remarks ) : that.remarks != null )
        {
            return false;
        }
        if ( scale != null ? !scale.equals( that.scale ) : that.scale != null )
        {
            return false;
        }
        if ( specificName != null ? !specificName.equals( that.specificName ) : that.specificName != null )
        {
            return false;
        }
        if ( typeName != null ? !typeName.equals( that.typeName ) : that.typeName != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = functionCatalog != null ? functionCatalog.hashCode() : 0;
        result = 31 * result + ( functionSchema != null ? functionSchema.hashCode() : 0 );
        result = 31 * result + ( functionName != null ? functionName.hashCode() : 0 );
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( columnType != null ? columnType.hashCode() : 0 );
        result = 31 * result + ( dataType != null ? dataType.hashCode() : 0 );
        result = 31 * result + ( typeName != null ? typeName.hashCode() : 0 );
        result = 31 * result + ( precision != null ? precision.hashCode() : 0 );
        result = 31 * result + ( length != null ? length.hashCode() : 0 );
        result = 31 * result + ( scale != null ? scale.hashCode() : 0 );
        result = 31 * result + ( radix != null ? radix.hashCode() : 0 );
        result = 31 * result + ( nullable != null ? nullable.hashCode() : 0 );
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( charOctetLength != null ? charOctetLength.hashCode() : 0 );
        result = 31 * result + ( ordinalPosition != null ? ordinalPosition.hashCode() : 0 );
        result = 31 * result + ( isNullable != null ? isNullable.hashCode() : 0 );
        result = 31 * result + ( specificName != null ? specificName.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "FunctionColumn{" +
               "functionCatalog='" + functionCatalog + '\'' +
               ", functionSchema='" + functionSchema + '\'' +
               ", functionName='" + functionName + '\'' +
               ", name='" + name + '\'' +
               ", columnType=" + columnType +
               ", dataType=" + dataType +
               ", typeName='" + typeName + '\'' +
               ", precision=" + precision +
               ", length=" + length +
               ", scale=" + scale +
               ", radix=" + radix +
               ", nullable=" + nullable +
               ", remarks='" + remarks + '\'' +
               ", charOctetLength=" + charOctetLength +
               ", ordinalPosition=" + ordinalPosition +
               ", isNullable='" + isNullable + '\'' +
               ", specificName='" + specificName + '\'' +
               '}';
    }
}
