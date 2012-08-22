package ge.generator.java.existing;

public class PrimitiveJavaObject extends ExistingJavaObject
{
    public static PrimitiveJavaObject BOOLEAN = new PrimitiveJavaObject( Boolean.TYPE );
    public static PrimitiveJavaObject CHAR = new PrimitiveJavaObject( Character.TYPE );
    public static PrimitiveJavaObject BYTE = new PrimitiveJavaObject( Byte.TYPE );
    public static PrimitiveJavaObject SHORT = new PrimitiveJavaObject( Short.TYPE );
    public static PrimitiveJavaObject INT = new PrimitiveJavaObject( Integer.TYPE );
    public static PrimitiveJavaObject LONG = new PrimitiveJavaObject( Long.TYPE );
    public static PrimitiveJavaObject FLOAT = new PrimitiveJavaObject( Float.TYPE );
    public static PrimitiveJavaObject DOUBLE = new PrimitiveJavaObject( Double.TYPE );
    public static PrimitiveJavaObject VOID = new PrimitiveJavaObject( Void.TYPE );

    PrimitiveJavaObject( Class type )
    {
        super( type );
    }
}
