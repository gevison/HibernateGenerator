package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class ImportCollection extends ArrayList<String> implements HasImports
{
    public boolean addAll( HasImports hasImports )
    {
        if ( hasImports != null )
        {
            List<String> imports = hasImports.generateImports();

            if ( ( imports != null ) && ( imports.isEmpty() == false ) )
            {
                return addAll( imports );
            }
        }
        return false;
    }

    public boolean add( JavaObject javaObject )
    {
        if ( javaObject instanceof ExtendedJavaObject )
        {
            ExtendedJavaObject extendedJavaObject = ( ExtendedJavaObject ) javaObject;

            return addAll( extendedJavaObject );
        }
        else if ( javaObject instanceof NonPrimitiveJavaObject )
        {
            NonReferenceJavaObject nonReferenceJavaObject = ( NonReferenceJavaObject ) javaObject;
            return add( nonReferenceJavaObject.getFullyQualifiedName() );
        }

        return false;
    }

    public ImportCollection generateImports()
    {
        return this;
    }
}
