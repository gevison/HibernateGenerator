package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class Function
{
    @ResultSetValue("FUNCTION_CAT")
    private String functionCatalog;

    @ResultSetValue("FUNCTION_SCHEM")
    private String functionSchema;

    @ResultSetValue("FUNCTION_NAME")
    private String functionName;

    @ResultSetValue("REMARKS")
    private String remarks;

    @ResultSetValue("FUNCTION_TYPE")
    private Short functionType;

    @ResultSetValue("SPECIFIC_NAME")
    private String specificName;

    public Function()
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

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String remarks )
    {
        this.remarks = remarks;
    }

    public Short getFunctionType()
    {
        return functionType;
    }

    public void setFunctionType( Short functionType )
    {
        this.functionType = functionType;
    }

    public String getSpecificName()
    {
        return specificName;
    }

    public void setSpecificName( String specificName )
    {
        this.specificName = specificName;
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

        Function function = ( Function ) o;

        if ( functionCatalog != null ? !functionCatalog.equals( function.functionCatalog ) :
             function.functionCatalog != null )
        {
            return false;
        }
        if ( functionName != null ? !functionName.equals( function.functionName ) : function.functionName != null )
        {
            return false;
        }
        if ( functionSchema != null ? !functionSchema.equals( function.functionSchema ) :
             function.functionSchema != null )
        {
            return false;
        }
        if ( functionType != null ? !functionType.equals( function.functionType ) : function.functionType != null )
        {
            return false;
        }
        if ( remarks != null ? !remarks.equals( function.remarks ) : function.remarks != null )
        {
            return false;
        }
        if ( specificName != null ? !specificName.equals( function.specificName ) : function.specificName != null )
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
        result = 31 * result + ( remarks != null ? remarks.hashCode() : 0 );
        result = 31 * result + ( functionType != null ? functionType.hashCode() : 0 );
        result = 31 * result + ( specificName != null ? specificName.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "Function{" +
               "functionCatalog='" + functionCatalog + '\'' +
               ", functionSchema='" + functionSchema + '\'' +
               ", functionName='" + functionName + '\'' +
               ", remarks='" + remarks + '\'' +
               ", functionType=" + functionType +
               ", specificName='" + specificName + '\'' +
               '}';
    }
}
