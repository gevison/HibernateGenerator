package ge.generator.jdbc.api;

public enum YesNoBooleanEnum
{
    YES("Y"), NO("N");

    String prefix;

    private YesNoBooleanEnum( String prefix )
    {
        this.prefix = prefix;
    }

    public static YesNoBooleanEnum valueOfByPrefix(String value)
    {
        YesNoBooleanEnum[] values = values();

        for (YesNoBooleanEnum dataTypeEnum : values)
        {
            if (value.startsWith(dataTypeEnum.prefix) == true)
            {
                return dataTypeEnum;
            }
        }

        throw new IllegalArgumentException(
                "No enum const class "+YesNoBooleanEnum.class.getName()+" with prefix for value: " + value);
    }
}
