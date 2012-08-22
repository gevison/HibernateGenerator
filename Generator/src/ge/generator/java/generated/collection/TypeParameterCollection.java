package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.*;

public class TypeParameterCollection extends ArrayList<TypeParameter> implements SingleLineContent
{
    public String generateContentString()
    {
        String retVal = "";

        if ( isEmpty() == false )
        {
            String typeParametersString = "";

            for ( TypeParameter typeParameter : this )
            {
                if ( typeParametersString.length() != 0 )
                {
                    typeParametersString += ", ";
                }

                typeParametersString += typeParameter.generateContentString();
            }

            retVal = "<" + typeParametersString + ">";
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( isEmpty() == false )
        {
            for ( TypeParameter typeParameter : this )
            {
                retVal.addAll( typeParameter );
            }
        }

        return retVal;
    }
}
