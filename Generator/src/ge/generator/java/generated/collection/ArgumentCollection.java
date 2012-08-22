package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.*;

public class ArgumentCollection extends ArrayList<Argument> implements SingleLineContent
{
    public String generateContentString()
    {
        String retVal = "";
        if ( isEmpty() == false )
        {
            String argumentsString = "";

            for ( Argument argument : this )
            {
                if ( argumentsString.length() != 0 )
                    argumentsString += ", ";
                argumentsString += argument.generateContentString();
            }

            retVal += "(" + argumentsString.trim() + ")";
        }
        else
        {
            retVal += "()";
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( isEmpty() == false )
        {
            for ( Argument argument : this )
            {
                retVal.addAll( argument );
            }
        }

        return retVal;
    }
}
