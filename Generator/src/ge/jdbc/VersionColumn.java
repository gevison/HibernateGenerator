package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class VersionColumn
{
    @ResultSetValue("SCOPE")
    private String scope;

    @ResultSetValue("COLUMN_NAME")
    private String name;

    @ResultSetValue("DATA_TYPE")
    private Integer dataType;

    @ResultSetValue("TYPE_NAME")
    private String typeName;

    @ResultSetValue("COLUMN_SIZE")
    private Integer columnSize;

    @ResultSetValue("BUFFER_LENGTH")
    private Integer bufferLength;

    @ResultSetValue("DECIMAL_DIGITS")
    private Short decimalDigits;

    @ResultSetValue("PSEUDO_COLUMN")
    private Short pseudoColumn;

    public VersionColumn()
    {
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope( String scope )
    {
        this.scope = scope;
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

    public Integer getBufferLength()
    {
        return bufferLength;
    }

    public void setBufferLength( Integer bufferLength )
    {
        this.bufferLength = bufferLength;
    }

    public Short getDecimalDigits()
    {
        return decimalDigits;
    }

    public void setDecimalDigits( Short decimalDigits )
    {
        this.decimalDigits = decimalDigits;
    }

    public Short getPseudoColumn()
    {
        return pseudoColumn;
    }

    public void setPseudoColumn( Short pseudoColumn )
    {
        this.pseudoColumn = pseudoColumn;
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

        VersionColumn that = ( VersionColumn ) o;

        if ( bufferLength != null ? !bufferLength.equals( that.bufferLength ) : that.bufferLength != null )
        {
            return false;
        }
        if ( columnSize != null ? !columnSize.equals( that.columnSize ) : that.columnSize != null )
        {
            return false;
        }
        if ( dataType != null ? !dataType.equals( that.dataType ) : that.dataType != null )
        {
            return false;
        }
        if ( decimalDigits != null ? !decimalDigits.equals( that.decimalDigits ) : that.decimalDigits != null )
        {
            return false;
        }
        if ( name != null ? !name.equals( that.name ) : that.name != null )
        {
            return false;
        }
        if ( pseudoColumn != null ? !pseudoColumn.equals( that.pseudoColumn ) : that.pseudoColumn != null )
        {
            return false;
        }
        if ( scope != null ? !scope.equals( that.scope ) : that.scope != null )
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
        int result = scope != null ? scope.hashCode() : 0;
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( dataType != null ? dataType.hashCode() : 0 );
        result = 31 * result + ( typeName != null ? typeName.hashCode() : 0 );
        result = 31 * result + ( columnSize != null ? columnSize.hashCode() : 0 );
        result = 31 * result + ( bufferLength != null ? bufferLength.hashCode() : 0 );
        result = 31 * result + ( decimalDigits != null ? decimalDigits.hashCode() : 0 );
        result = 31 * result + ( pseudoColumn != null ? pseudoColumn.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "VersionColumn{" +
               "scope='" + scope + '\'' +
               ", name='" + name + '\'' +
               ", dataType=" + dataType +
               ", typeName='" + typeName + '\'' +
               ", columnSize=" + columnSize +
               ", bufferLength=" + bufferLength +
               ", decimalDigits=" + decimalDigits +
               ", pseudoColumn=" + pseudoColumn +
               '}';
    }
}
