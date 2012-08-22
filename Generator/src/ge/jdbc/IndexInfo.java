package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class IndexInfo
{
    @ResultSetValue("TABLE_CAT")
    private String tableCatalog;

    @ResultSetValue("TABLE_SCHEM")
    private String tableSchema;

    @ResultSetValue("TABLE_NAME")
    private String tableName;

    @ResultSetValue("NON_UNIQUE")
    private Boolean nonUnique;

    @ResultSetValue("INDEX_QUALIFIER")
    private String indexQualifier;

    @ResultSetValue("INDEX_NAME")
    private String indexName;

    @ResultSetValue("TYPE")
    private Short type;

    @ResultSetValue("ORDINAL_POSITION")
    private Short ordinalPosition;

    @ResultSetValue("COLUMN_NAME")
    private String columnName;

    @ResultSetValue("ASC_OR_DESC")
    private String ascOrDesc;

    @ResultSetValue("CARDINALITY")
    private Integer cardinality;

    @ResultSetValue("PAGES")
    private Integer pages;

    @ResultSetValue("FILTER_CONDITION")
    private String filterCondition;

    public IndexInfo()
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

    public Boolean getNonUnique()
    {
        return nonUnique;
    }

    public void setNonUnique( Boolean nonUnique )
    {
        this.nonUnique = nonUnique;
    }

    public String getIndexQualifier()
    {
        return indexQualifier;
    }

    public void setIndexQualifier( String indexQualifier )
    {
        this.indexQualifier = indexQualifier;
    }

    public String getIndexName()
    {
        return indexName;
    }

    public void setIndexName( String indexName )
    {
        this.indexName = indexName;
    }

    public Short getType()
    {
        return type;
    }

    public void setType( Short type )
    {
        this.type = type;
    }

    public Short getOrdinalPosition()
    {
        return ordinalPosition;
    }

    public void setOrdinalPosition( Short ordinalPosition )
    {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName( String columnName )
    {
        this.columnName = columnName;
    }

    public String getAscOrDesc()
    {
        return ascOrDesc;
    }

    public void setAscOrDesc( String ascOrDesc )
    {
        this.ascOrDesc = ascOrDesc;
    }

    public Integer getCardinality()
    {
        return cardinality;
    }

    public void setCardinality( Integer cardinality )
    {
        this.cardinality = cardinality;
    }

    public Integer getPages()
    {
        return pages;
    }

    public void setPages( Integer pages )
    {
        this.pages = pages;
    }

    public String getFilterCondition()
    {
        return filterCondition;
    }

    public void setFilterCondition( String filterCondition )
    {
        this.filterCondition = filterCondition;
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

        IndexInfo indexInfo = ( IndexInfo ) o;

        if ( ascOrDesc != null ? !ascOrDesc.equals( indexInfo.ascOrDesc ) : indexInfo.ascOrDesc != null )
        {
            return false;
        }
        if ( cardinality != null ? !cardinality.equals( indexInfo.cardinality ) : indexInfo.cardinality != null )
        {
            return false;
        }
        if ( columnName != null ? !columnName.equals( indexInfo.columnName ) : indexInfo.columnName != null )
        {
            return false;
        }
        if ( filterCondition != null ? !filterCondition.equals( indexInfo.filterCondition ) :
             indexInfo.filterCondition != null )
        {
            return false;
        }
        if ( indexName != null ? !indexName.equals( indexInfo.indexName ) : indexInfo.indexName != null )
        {
            return false;
        }
        if ( indexQualifier != null ? !indexQualifier.equals( indexInfo.indexQualifier ) :
             indexInfo.indexQualifier != null )
        {
            return false;
        }
        if ( nonUnique != null ? !nonUnique.equals( indexInfo.nonUnique ) : indexInfo.nonUnique != null )
        {
            return false;
        }
        if ( ordinalPosition != null ? !ordinalPosition.equals( indexInfo.ordinalPosition ) :
             indexInfo.ordinalPosition != null )
        {
            return false;
        }
        if ( pages != null ? !pages.equals( indexInfo.pages ) : indexInfo.pages != null )
        {
            return false;
        }
        if ( tableCatalog != null ? !tableCatalog.equals( indexInfo.tableCatalog ) : indexInfo.tableCatalog != null )
        {
            return false;
        }
        if ( tableName != null ? !tableName.equals( indexInfo.tableName ) : indexInfo.tableName != null )
        {
            return false;
        }
        if ( tableSchema != null ? !tableSchema.equals( indexInfo.tableSchema ) : indexInfo.tableSchema != null )
        {
            return false;
        }
        if ( type != null ? !type.equals( indexInfo.type ) : indexInfo.type != null )
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
        result = 31 * result + ( nonUnique != null ? nonUnique.hashCode() : 0 );
        result = 31 * result + ( indexQualifier != null ? indexQualifier.hashCode() : 0 );
        result = 31 * result + ( indexName != null ? indexName.hashCode() : 0 );
        result = 31 * result + ( type != null ? type.hashCode() : 0 );
        result = 31 * result + ( ordinalPosition != null ? ordinalPosition.hashCode() : 0 );
        result = 31 * result + ( columnName != null ? columnName.hashCode() : 0 );
        result = 31 * result + ( ascOrDesc != null ? ascOrDesc.hashCode() : 0 );
        result = 31 * result + ( cardinality != null ? cardinality.hashCode() : 0 );
        result = 31 * result + ( pages != null ? pages.hashCode() : 0 );
        result = 31 * result + ( filterCondition != null ? filterCondition.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "IndexInfo{" +
               "tableCatalog='" + tableCatalog + '\'' +
               ", tableSchema='" + tableSchema + '\'' +
               ", tableName='" + tableName + '\'' +
               ", nonUnique=" + nonUnique +
               ", indexQualifier='" + indexQualifier + '\'' +
               ", indexName='" + indexName + '\'' +
               ", type=" + type +
               ", ordinalPosition=" + ordinalPosition +
               ", columnName='" + columnName + '\'' +
               ", ascOrDesc='" + ascOrDesc + '\'' +
               ", cardinality=" + cardinality +
               ", pages=" + pages +
               ", filterCondition='" + filterCondition + '\'' +
               '}';
    }
}
