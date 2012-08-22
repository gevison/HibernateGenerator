package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Column
{
    @ResultSetValue("TABLE_CAT")
    private String tableCatalog;

    @ResultSetValue("TABLE_SCHEM")
    private String tableSchema;

    @ResultSetValue("TABLE_NAME")
    private String tableName;

    @ResultSetValue("COLUMN_NAME")
    private String name;

    @ResultSetValue("DATA_TYPE")
    private Integer dataType;

    @ResultSetValue("TYPE_NAME")
    private String typeName;

    @ResultSetValue("COLUMN_SIZE")
    private Integer columnSize;

    @ResultSetValue("DECIMAL_DIGITS")
    private Integer decimalDigits;

    @ResultSetValue("NUM_PREC_RADIX")
    private Integer numPrecRadix;

    @ResultSetValue("NULLABLE")
    private Integer nullable;

    @ResultSetValue("REMARKS")
    private String remarks;

    @ResultSetValue("COLUMN_DEF")
    private String columnDef;

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

    @ResultSetValue("IS_AUTOINCREMENT")
    private String isAutoIncrement;

    @ResultSetValue("IS_GENERATEDCOLUMN")
    private String isGeneratedColumn;

    public Column()
    {
    }

    public String getTableCatalog()
    {
        return tableCatalog;
    }

    public void setTableCatalog( String tableCatalog )
    {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema()
    {
        return tableSchema;
    }

    public void setTableSchema( String tableSchema )
    {
        this.tableSchema = tableSchema;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName( String tableName )
    {
        this.tableName = tableName;
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

    public Integer getColumnSize()
    {
        return columnSize;
    }

    public void setColumnSize( Integer columnSize )
    {
        this.columnSize = columnSize;
    }

    public Integer getDecimalDigits()
    {
        return decimalDigits;
    }

    public void setDecimalDigits( Integer decimalDigits )
    {
        this.decimalDigits = decimalDigits;
    }

    public Integer getNumPrecRadix()
    {
        return numPrecRadix;
    }

    public void setNumPrecRadix( Integer numPrecRadix )
    {
        this.numPrecRadix = numPrecRadix;
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

    public String getAutoIncrement()
    {
        return isAutoIncrement;
    }

    public void setAutoIncrement( String autoIncrement )
    {
        isAutoIncrement = autoIncrement;
    }

    public String getGeneratedColumn()
    {
        return isGeneratedColumn;
    }

    public void setGeneratedColumn( String generatedColumn )
    {
        isGeneratedColumn = generatedColumn;
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

    public String getColumnDef()
    {
        return columnDef;
    }

    public void setColumnDef( String columnDef )
    {
        this.columnDef = columnDef;
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

        Column column = ( Column ) o;

        if ( charOctetLength != null ? !charOctetLength.equals( column.charOctetLength ) :
             column.charOctetLength != null )
        {
            return false;
        }
        if ( columnDef != null ? !columnDef.equals( column.columnDef ) : column.columnDef != null )
        {
            return false;
        }
        if ( columnSize != null ? !columnSize.equals( column.columnSize ) : column.columnSize != null )
        {
            return false;
        }
        if ( dataType != null ? !dataType.equals( column.dataType ) : column.dataType != null )
        {
            return false;
        }
        if ( decimalDigits != null ? !decimalDigits.equals( column.decimalDigits ) : column.decimalDigits != null )
        {
            return false;
        }
        if ( isAutoIncrement != null ? !isAutoIncrement.equals( column.isAutoIncrement ) :
             column.isAutoIncrement != null )
        {
            return false;
        }
        if ( isGeneratedColumn != null ? !isGeneratedColumn.equals( column.isGeneratedColumn ) :
             column.isGeneratedColumn != null )
        {
            return false;
        }
        if ( isNullable != null ? !isNullable.equals( column.isNullable ) : column.isNullable != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( column.name ) : column.name != null )
        {
            return false;
        }
        if ( nullable != null ? !nullable.equals( column.nullable ) : column.nullable != null )
        {
            return false;
        }
        if ( numPrecRadix != null ? !numPrecRadix.equals( column.numPrecRadix ) : column.numPrecRadix != null )
        {
            return false;
        }
        if ( ordinalPosition != null ? !ordinalPosition.equals( column.ordinalPosition ) :
             column.ordinalPosition != null )
        {
            return false;
        }
        if ( remarks != null ? !remarks.equals( column.remarks ) : column.remarks != null )
        {
            return false;
        }
        if ( scopeCatalog != null ? !scopeCatalog.equals( column.scopeCatalog ) : column.scopeCatalog != null )
        {
            return false;
        }
        if ( scopeSchema != null ? !scopeSchema.equals( column.scopeSchema ) : column.scopeSchema != null )
        {
            return false;
        }
        if ( scopeTable != null ? !scopeTable.equals( column.scopeTable ) : column.scopeTable != null )
        {
            return false;
        }
        if ( sourceDataType != null ? !sourceDataType.equals( column.sourceDataType ) : column.sourceDataType != null )
        {
            return false;
        }
        if ( sqlDataType != null ? !sqlDataType.equals( column.sqlDataType ) : column.sqlDataType != null )
        {
            return false;
        }
        if ( sqlDateTimeSub != null ? !sqlDateTimeSub.equals( column.sqlDateTimeSub ) : column.sqlDateTimeSub != null )
        {
            return false;
        }
        if ( tableCatalog != null ? !tableCatalog.equals( column.tableCatalog ) : column.tableCatalog != null )
        {
            return false;
        }
        if ( tableName != null ? !tableName.equals( column.tableName ) : column.tableName != null )
        {
            return false;
        }
        if ( tableSchema != null ? !tableSchema.equals( column.tableSchema ) : column.tableSchema != null )
        {
            return false;
        }
        if ( typeName != null ? !typeName.equals( column.typeName ) : column.typeName != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = tableCatalog != null ? tableCatalog.hashCode() : 0;
        result = 31 * result + ( tableSchema != null ? tableSchema.hashCode() : 0 );
        result = 31 * result + ( tableName != null ? tableName.hashCode() : 0 );
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( dataType != null ? dataType.hashCode() : 0 );
        result = 31 * result + ( typeName != null ? typeName.hashCode() : 0 );
        result = 31 * result + ( columnSize != null ? columnSize.hashCode() : 0 );
        result = 31 * result + ( decimalDigits != null ? decimalDigits.hashCode() : 0 );
        result = 31 * result + ( numPrecRadix != null ? numPrecRadix.hashCode() : 0 );
        result = 31 * result + ( nullable != null ? nullable.hashCode() : 0 );
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( columnDef != null ? columnDef.hashCode() : 0 );
        result = 31 * result + ( sqlDataType != null ? sqlDataType.hashCode() : 0 );
        result = 31 * result + ( sqlDateTimeSub != null ? sqlDateTimeSub.hashCode() : 0 );
        result = 31 * result + ( charOctetLength != null ? charOctetLength.hashCode() : 0 );
        result = 31 * result + ( ordinalPosition != null ? ordinalPosition.hashCode() : 0 );
        result = 31 * result + ( isNullable != null ? isNullable.hashCode() : 0 );
        result = 31 * result + ( scopeCatalog != null ? scopeCatalog.hashCode() : 0 );
        result = 31 * result + ( scopeSchema != null ? scopeSchema.hashCode() : 0 );
        result = 31 * result + ( scopeTable != null ? scopeTable.hashCode() : 0 );
        result = 31 * result + ( sourceDataType != null ? sourceDataType.hashCode() : 0 );
        result = 31 * result + ( isAutoIncrement != null ? isAutoIncrement.hashCode() : 0 );
        result = 31 * result + ( isGeneratedColumn != null ? isGeneratedColumn.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "Column{" +
               "tableCatalog='" + tableCatalog + '\'' +
               ", tableSchema='" + tableSchema + '\'' +
               ", tableName='" + tableName + '\'' +
               ", name='" + name + '\'' +
               ", dataType=" + dataType +
               ", typeName='" + typeName + '\'' +
               ", columnSize=" + columnSize +
               ", decimalDigits=" + decimalDigits +
               ", numPrecRadix=" + numPrecRadix +
               ", nullable=" + nullable +
               ", remarks='" + remarks + '\'' +
               ", columnDef='" + columnDef + '\'' +
               ", sqlDataType=" + sqlDataType +
               ", sqlDateTimeSub=" + sqlDateTimeSub +
               ", charOctetLength=" + charOctetLength +
               ", ordinalPosition=" + ordinalPosition +
               ", isNullable='" + isNullable + '\'' +
               ", scopeCatalog='" + scopeCatalog + '\'' +
               ", scopeSchema='" + scopeSchema + '\'' +
               ", scopeTable='" + scopeTable + '\'' +
               ", sourceDataType=" + sourceDataType +
               ", isAutoIncrement='" + isAutoIncrement + '\'' +
               ", isGeneratedColumn='" + isGeneratedColumn + '\'' +
               '}';
    }
}
