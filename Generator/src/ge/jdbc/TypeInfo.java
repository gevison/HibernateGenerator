package ge.jdbc;

import ge.jdbc.annotations.ResultSetValue;

public class TypeInfo
{
    @ResultSetValue("TYPE_NAME")
    private String typeName;

    @ResultSetValue("DATA_TYPE")
    private Integer dataType;

    @ResultSetValue("PRECISION")
    private Integer precision;

    @ResultSetValue("LITERAL_PREFIX")
    private String literalPrefix;

    @ResultSetValue("LITERAL_SUFFIX")
    private String literalSuffix;

    @ResultSetValue("CREATE_PARAMS")
    private String createParameters;

    @ResultSetValue("NULLABLE")
    private Short nullable;

    @ResultSetValue("CASE_SENSITIVE")
    private Boolean caseSensitive;

    @ResultSetValue("SEARCHABLE")
    private Short searchable;

    @ResultSetValue("UNSIGNED_ATTRIBUTE")
    private Boolean unsignedAttribute;

    @ResultSetValue("FIXED_PREC_SCALE")
    private Boolean fixedPrecScale;

    @ResultSetValue("AUTO_INCREMENT")
    private Boolean autoIncrement;

    @ResultSetValue("LOCAL_TYPE_NAME")
    private String localTypeName;

    @ResultSetValue("MINIMUM_SCALE")
    private Short minimumScale;

    @ResultSetValue("MAXIMUM_SCALE")
    private Short maximumScale;

    @ResultSetValue("SQL_DATA_TYPE")
    private Integer sqlDataType;

    @ResultSetValue("SQL_DATETIME_SUB")
    private Integer sqlDateTimeSub;

    @ResultSetValue("NUM_PREC_RADIX")
    private Integer munPrecRadix;

    public TypeInfo()
    {
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName( String typeName )
    {
        this.typeName = typeName;
    }

    public Integer getDataType()
    {
        return dataType;
    }

    public void setDataType( Integer dataType )
    {
        this.dataType = dataType;
    }

    public Integer getPrecision()
    {
        return precision;
    }

    public void setPrecision( Integer precision )
    {
        this.precision = precision;
    }

    public String getLiteralPrefix()
    {
        return literalPrefix;
    }

    public void setLiteralPrefix( String literalPrefix )
    {
        this.literalPrefix = literalPrefix;
    }

    public String getLiteralSuffix()
    {
        return literalSuffix;
    }

    public void setLiteralSuffix( String literalSuffix )
    {
        this.literalSuffix = literalSuffix;
    }

    public String getCreateParameters()
    {
        return createParameters;
    }

    public void setCreateParameters( String createParameters )
    {
        this.createParameters = createParameters;
    }

    public Short getNullable()
    {
        return nullable;
    }

    public void setNullable( Short nullable )
    {
        this.nullable = nullable;
    }

    public Boolean getCaseSensitive()
    {
        return caseSensitive;
    }

    public void setCaseSensitive( Boolean caseSensitive )
    {
        this.caseSensitive = caseSensitive;
    }

    public Short getSearchable()
    {
        return searchable;
    }

    public void setSearchable( Short searchable )
    {
        this.searchable = searchable;
    }

    public Boolean getUnsignedAttribute()
    {
        return unsignedAttribute;
    }

    public void setUnsignedAttribute( Boolean unsignedAttribute )
    {
        this.unsignedAttribute = unsignedAttribute;
    }

    public Boolean getFixedPrecScale()
    {
        return fixedPrecScale;
    }

    public void setFixedPrecScale( Boolean fixedPrecScale )
    {
        this.fixedPrecScale = fixedPrecScale;
    }

    public Boolean getAutoIncrement()
    {
        return autoIncrement;
    }

    public void setAutoIncrement( Boolean autoIncrement )
    {
        this.autoIncrement = autoIncrement;
    }

    public String getLocalTypeName()
    {
        return localTypeName;
    }

    public void setLocalTypeName( String localTypeName )
    {
        this.localTypeName = localTypeName;
    }

    public Short getMinimumScale()
    {
        return minimumScale;
    }

    public void setMinimumScale( Short minimumScale )
    {
        this.minimumScale = minimumScale;
    }

    public Short getMaximumScale()
    {
        return maximumScale;
    }

    public void setMaximumScale( Short maximumScale )
    {
        this.maximumScale = maximumScale;
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

    public Integer getMunPrecRadix()
    {
        return munPrecRadix;
    }

    public void setMunPrecRadix( Integer munPrecRadix )
    {
        this.munPrecRadix = munPrecRadix;
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

        TypeInfo typeInfo = ( TypeInfo ) o;

        if ( autoIncrement != null ? !autoIncrement.equals( typeInfo.autoIncrement ) : typeInfo.autoIncrement != null )
        {
            return false;
        }
        if ( caseSensitive != null ? !caseSensitive.equals( typeInfo.caseSensitive ) : typeInfo.caseSensitive != null )
        {
            return false;
        }
        if ( createParameters != null ? !createParameters.equals( typeInfo.createParameters ) :
             typeInfo.createParameters != null )
        {
            return false;
        }
        if ( dataType != null ? !dataType.equals( typeInfo.dataType ) : typeInfo.dataType != null )
        {
            return false;
        }
        if ( fixedPrecScale != null ? !fixedPrecScale.equals( typeInfo.fixedPrecScale ) :
             typeInfo.fixedPrecScale != null )
        {
            return false;
        }
        if ( literalPrefix != null ? !literalPrefix.equals( typeInfo.literalPrefix ) : typeInfo.literalPrefix != null )
        {
            return false;
        }
        if ( literalSuffix != null ? !literalSuffix.equals( typeInfo.literalSuffix ) : typeInfo.literalSuffix != null )
        {
            return false;
        }
        if ( localTypeName != null ? !localTypeName.equals( typeInfo.localTypeName ) : typeInfo.localTypeName != null )
        {
            return false;
        }
        if ( maximumScale != null ? !maximumScale.equals( typeInfo.maximumScale ) : typeInfo.maximumScale != null )
        {
            return false;
        }
        if ( minimumScale != null ? !minimumScale.equals( typeInfo.minimumScale ) : typeInfo.minimumScale != null )
        {
            return false;
        }
        if ( munPrecRadix != null ? !munPrecRadix.equals( typeInfo.munPrecRadix ) : typeInfo.munPrecRadix != null )
        {
            return false;
        }
        if ( nullable != null ? !nullable.equals( typeInfo.nullable ) : typeInfo.nullable != null )
        {
            return false;
        }
        if ( precision != null ? !precision.equals( typeInfo.precision ) : typeInfo.precision != null )
        {
            return false;
        }
        if ( searchable != null ? !searchable.equals( typeInfo.searchable ) : typeInfo.searchable != null )
        {
            return false;
        }
        if ( sqlDataType != null ? !sqlDataType.equals( typeInfo.sqlDataType ) : typeInfo.sqlDataType != null )
        {
            return false;
        }
        if ( sqlDateTimeSub != null ? !sqlDateTimeSub.equals( typeInfo.sqlDateTimeSub ) :
             typeInfo.sqlDateTimeSub != null )
        {
            return false;
        }
        if ( typeName != null ? !typeName.equals( typeInfo.typeName ) : typeInfo.typeName != null )
        {
            return false;
        }
        if ( unsignedAttribute != null ? !unsignedAttribute.equals( typeInfo.unsignedAttribute ) :
             typeInfo.unsignedAttribute != null )
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = typeName != null ? typeName.hashCode() : 0;
        result = 31 * result + ( dataType != null ? dataType.hashCode() : 0 );
        result = 31 * result + ( precision != null ? precision.hashCode() : 0 );
        result = 31 * result + ( literalPrefix != null ? literalPrefix.hashCode() : 0 );
        result = 31 * result + ( literalSuffix != null ? literalSuffix.hashCode() : 0 );
        result = 31 * result + ( createParameters != null ? createParameters.hashCode() : 0 );
        result = 31 * result + ( nullable != null ? nullable.hashCode() : 0 );
        result = 31 * result + ( caseSensitive != null ? caseSensitive.hashCode() : 0 );
        result = 31 * result + ( searchable != null ? searchable.hashCode() : 0 );
        result = 31 * result + ( unsignedAttribute != null ? unsignedAttribute.hashCode() : 0 );
        result = 31 * result + ( fixedPrecScale != null ? fixedPrecScale.hashCode() : 0 );
        result = 31 * result + ( autoIncrement != null ? autoIncrement.hashCode() : 0 );
        result = 31 * result + ( localTypeName != null ? localTypeName.hashCode() : 0 );
        result = 31 * result + ( minimumScale != null ? minimumScale.hashCode() : 0 );
        result = 31 * result + ( maximumScale != null ? maximumScale.hashCode() : 0 );
        result = 31 * result + ( sqlDataType != null ? sqlDataType.hashCode() : 0 );
        result = 31 * result + ( sqlDateTimeSub != null ? sqlDateTimeSub.hashCode() : 0 );
        result = 31 * result + ( munPrecRadix != null ? munPrecRadix.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString()
    {
        return "TypeInfo{" +
               "typeName='" + typeName + '\'' +
               ", dataType=" + dataType +
               ", precision=" + precision +
               ", literalPrefix='" + literalPrefix + '\'' +
               ", literalSuffix='" + literalSuffix + '\'' +
               ", createParameters='" + createParameters + '\'' +
               ", nullable=" + nullable +
               ", caseSensitive=" + caseSensitive +
               ", searchable=" + searchable +
               ", unsignedAttribute=" + unsignedAttribute +
               ", fixedPrecScale=" + fixedPrecScale +
               ", autoIncrement=" + autoIncrement +
               ", localTypeName='" + localTypeName + '\'' +
               ", minimumScale=" + minimumScale +
               ", maximumScale=" + maximumScale +
               ", sqlDataType=" + sqlDataType +
               ", sqlDateTimeSub=" + sqlDateTimeSub +
               ", munPrecRadix=" + munPrecRadix +
               '}';
    }
}
