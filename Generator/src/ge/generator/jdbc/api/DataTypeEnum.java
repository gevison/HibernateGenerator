package ge.generator.jdbc.api;

public enum DataTypeEnum
{
    NUMBER("NUMBER"), VARCHAR2("VARCHAR2"), BLOB("BLOB"), DATE("DATE"), TIMESTAMP("TIMESTAMP"), CHAR("CHAR");

    String prefix;

    private DataTypeEnum( String prefix )
    {
        this.prefix = prefix;
    }

    public static DataTypeEnum valueOfByPrefix(String value)
    {
        DataTypeEnum[] values = values();

        for (DataTypeEnum dataTypeEnum : values)
        {
            if (value.startsWith(dataTypeEnum.prefix) == true)
            {
                return dataTypeEnum;
            }
        }

        throw new IllegalArgumentException(
                "No enum const class "+DataTypeEnum.class.getName()+" with prefix for value: " + value);
    }
}
