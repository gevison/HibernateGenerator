package ge.generator.jdbc.api.links;

public class ManyToManyLink implements Link
{
    private String joinTableName;

    private String joinColumnName;

    private String joinColumnReferencedName;

    private String inverseJoinColumnName;

    private String inverseJoinColumnReferencedName;

    private String destinationTableName;

    public void setJoinTableName( String joinTableName )
    {
        this.joinTableName = joinTableName;
    }

    public void setJoinColumnName( String joinColumnName )
    {
        this.joinColumnName = joinColumnName;
    }

    public void setJoinColumnReferencedName( String joinColumnReferencedName )
    {
        this.joinColumnReferencedName = joinColumnReferencedName;
    }

    public void setInverseJoinColumnName( String inverseJoinColumnName )
    {
        this.inverseJoinColumnName = inverseJoinColumnName;
    }

    public void setInverseJoinColumnReferencedName( String inverseJoinColumnReferencedName )
    {
        this.inverseJoinColumnReferencedName = inverseJoinColumnReferencedName;
    }

    public void setDestinationTableName( String destinationTableName )
    {
        this.destinationTableName = destinationTableName;
    }

    public String getDestinationTableName()
    {
        return destinationTableName;
    }

    public String getInverseJoinColumnName()
    {
        return inverseJoinColumnName;
    }

    public String getInverseJoinColumnReferencedName()
    {
        return inverseJoinColumnReferencedName;
    }

    public String getJoinColumnName()
    {
        return joinColumnName;
    }

    public String getJoinColumnReferencedName()
    {
        return joinColumnReferencedName;
    }

    public String getJoinTableName()
    {
        return joinTableName;
    }
}
