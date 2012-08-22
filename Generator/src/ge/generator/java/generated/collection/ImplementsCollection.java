package ge.generator.java.generated.collection;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class ImplementsCollection extends InterfaceCollection implements SingleLineContent
{
    public String generateContentString()
    {
        String retVal = "";

        if ( isEmpty() == false )
        {
            String implementsString = "";

            for ( InterfaceJavaObject implementsType : this )
            {
                if ( implementsString.length() != 0 )
                {
                    implementsString += ", ";
                }

                if ( implementsType instanceof ExtendedInterfaceJavaObject )
                {
                    ExtendedInterfaceJavaObject extendedInterfaceJavaObject =
                            ( ExtendedInterfaceJavaObject ) implementsType;

                    implementsString += extendedInterfaceJavaObject.generateContentString();
                }
                else
                {
                    implementsString += implementsType.getSimpleName();
                }
            }

            retVal += " implements " + implementsString;
        }

        return retVal;
    }
}
