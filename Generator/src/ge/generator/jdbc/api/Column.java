package ge.generator.jdbc.api;

public class Column implements Comparable<Column>
{
    private String columnName;

    private DataTypeEnum type;

    private Integer index;

    private Integer dataScale;

    public Column( String columnName )
    {
        this.columnName = columnName;
    }

    public void setType( DataTypeEnum type )
    {
        this.type = type;
    }

    public void setIndex( Integer index )
    {
        this.index = index;
    }

    public void setDataScale( Integer dataScale )
    {
        this.dataScale = dataScale;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public int getIndex()
    {
        return index;
    }

    public DataTypeEnum getType()
    {
        return type;
    }

    public Integer getDataScale()
    {
        return dataScale;
    }

    public int compareTo( Column otherColumnDetails )
    {
        return index.compareTo(otherColumnDetails.index);
    }
}
