package ge.generator.java.existing;

import ge.generator.java.generated.collection.*;
import ge.generator.java.interfaces.*;

public class ExistingExtendedClassJavaObject extends ExistingClassJavaObject implements ExtendedClassJavaObject
{
    private NonPrimitiveJavaObjectCollection extensions;

    ExistingExtendedClassJavaObject( Class type, NonPrimitiveJavaObjectCollection extensions )
    {
        super( type );
        this.extensions = extensions;
    }

    public String generateContentString()
    {
        String retVal = getSimpleName();

        if ( ( extensions != null ) && ( extensions.isEmpty() == false ) )
        {
            String values = "";

            for ( NonPrimitiveJavaObject extension : extensions )
            {
                if ( values.length() != 0 )
                {
                    values += ", ";
                }

                if ( extension instanceof ExtendedJavaObject )
                {
                    ExtendedJavaObject extendedJavaObject = ( ExtendedJavaObject ) extension;

                    values += extendedJavaObject.generateContentString();
                }
                else
                {
                    values += extension.getSimpleName();
                }
            }

            retVal += "<" + values + ">";
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.add( getFullyQualifiedName() );

        retVal.addAll( extensions );

        return retVal;
    }
}
