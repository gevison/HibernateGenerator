package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.*;
import ge.generator.java.generated.interfaces.*;

public class ModifierCollection extends ArrayList<Modifier> implements SingleLineContent
{
    public String generateContentString()
    {
        String retVal = "";

        if ( isEmpty() == false )
        {
            for ( Modifier modifier : this )
            {
                if ( retVal.length() != 0 )
                    retVal += " ";

                retVal += modifier.name().toLowerCase();
            }
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        return null;
    }
}
