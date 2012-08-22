package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class ThrowsCollection extends ArrayList<NonPrimitiveJavaObject> implements SingleLineContent
{
    public String generateContentString()
    {
        String retVal = "";

        if ( isEmpty() == false )
        {
            String exceptionsString = "";

            for ( NonPrimitiveJavaObject exception : this )
            {
                if ( exceptionsString.length() != 0 )
                    exceptionsString += ", ";

                exceptionsString += exception.getSimpleName();
            }

            retVal += "throws " + exceptionsString.trim();
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        for ( NonPrimitiveJavaObject javaObject : this )
        {
            retVal.add( javaObject );
        }

        return retVal;
    }
}
