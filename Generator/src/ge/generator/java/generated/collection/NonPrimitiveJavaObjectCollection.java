package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class NonPrimitiveJavaObjectCollection extends ArrayList<NonPrimitiveJavaObject> implements HasImports
{
    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( isEmpty() == false )
        {
            for ( NonPrimitiveJavaObject javaObject : this )
            {
                retVal.add( javaObject );
            }
        }

        return retVal;
    }
}
