package ge.generator.jdbc.impl;

import ge.generator.jdbc.api.*;

public class ColumnData
{
    private final String columnName;

    private final String tableName;

    private int columnIndex;

    private Integer dataLength;

    private YesNoBooleanEnum nullable;

    private DataTypeEnum dataType;

    private Integer dataPrecision;

    private Integer dataScale;

    public ColumnData( String columnName, String tableName )
    {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    public void setColumnIndex( int columnIndex )
    {
        this.columnIndex = columnIndex;
    }

    public void setDataLength( Integer dataLength )
    {
        this.dataLength = dataLength;
    }

    public void setNullable( YesNoBooleanEnum nullable )
    {
        this.nullable = nullable;
    }

    public void setDataType( DataTypeEnum dataType )
    {
        this.dataType = dataType;
    }

    public void setDataPrecision( Integer dataPrecision )
    {
        this.dataPrecision = dataPrecision;
    }

    public void setDataScale( Integer dataScale )
    {
        this.dataScale = dataScale;
    }

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public Integer getDataLength()
    {
        return dataLength;
    }

    public Integer getDataPrecision()
    {
        return dataPrecision;
    }

    public Integer getDataScale()
    {
        return dataScale;
    }

    public DataTypeEnum getDataType()
    {
        return dataType;
    }

    public YesNoBooleanEnum getNullable()
    {
        return nullable;
    }

    public String getTableName()
    {
        return tableName;
    }
}
