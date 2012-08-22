package ge.generator.java.generated.part.annotation;

import java.util.*;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;

public class AnnotationElement implements SingleLineContent
{
    private ElementValue elementValue;
    private Map<String, ElementValue> elementValues = new HashMap<String, ElementValue>();

    public AnnotationElement()
    {
    }

    public ElementValue getElementValue()
    {
        return elementValue;
    }

    public void setElementValue( ElementValue elementValue )
    {
        this.elementValue = elementValue;
    }

    public void clearElementValues()
    {
        elementValues.clear();
    }

    public ElementValue getElementValue( Object key )
    {
        return elementValues.get( key );
    }

    public boolean isElementValuesEmpty()
    {
        return elementValues.isEmpty();
    }

    public ElementValue putElementValue( String key, ElementValue value )
    {
        return elementValues.put( key, value );
    }

    public ElementValue removeElementValue( Object key )
    {
        return elementValues.remove( key );
    }

    public int elementValueSize()
    {
        return elementValues.size();
    }

    public String generateContentString()
    {
        String retVal = "(";

        if ( elementValue != null )
        {
            retVal += elementValue.generateContentString();
        }
        else if ( elementValues != null )
        {
            String values = "";

            for ( String key : elementValues.keySet() )
            {
                if ( values.length() != 0 )
                {
                    values += ", ";
                }

                ElementValue elementValue = elementValues.get( key );

                values += key + " = " + elementValue.generateContentString();
            }

            retVal += values;
        }

        retVal += ")";
        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( elementValue != null )
        {
            retVal.addAll( elementValue );
        }
        else if ( elementValues != null )
        {
            for ( String key : elementValues.keySet() )
            {
                ElementValue elementValue = elementValues.get( key );

                retVal.addAll( elementValue );
            }
        }

        return retVal;
    }
}
