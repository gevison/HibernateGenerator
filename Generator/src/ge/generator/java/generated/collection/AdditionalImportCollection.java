package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class AdditionalImportCollection extends ArrayList<NonReferenceJavaObject> implements HasImports
{
    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        for ( NonReferenceJavaObject javaObject : this )
        {
            retVal.add(javaObject.getFullyQualifiedName());
        }

        return retVal;
    }
}
