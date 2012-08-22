package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class InterfaceCollection extends ArrayList<InterfaceJavaObject> implements HasImports
{
    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        for ( InterfaceJavaObject interfaceJavaObject : this )
        {
            retVal.add( interfaceJavaObject );
        }

        return retVal;
    }
}
