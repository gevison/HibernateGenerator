package ge.generator.java.generated.part.annotation;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class ElementValue implements SingleLineContent
{
    private AdditionalImportCollection additionalImportsCollection = new AdditionalImportCollection();

    private Object elementValue;

    public ElementValue()
    {
    }

    public Object getElementValue()
    {
        return elementValue;
    }

    public void setElementValue( NonPrimitiveJavaObject elementValue )
    {
        this.elementValue = elementValue;
    }

    public void setElementValue( Annotation elementValue )
    {
        this.elementValue = elementValue;
    }

    public void setElementValue( String elementValue )
    {
        this.elementValue = elementValue;
    }

    public void setElementValue( Number elementValue )
    {
        this.elementValue = elementValue;
    }

    public String generateContentString()
    {
        if ( elementValue == null )
            return "";

        String retVal;

        if ( elementValue instanceof NonReferenceJavaObject )
        {
            NonReferenceJavaObject javaObject = ( NonReferenceJavaObject ) elementValue;

            retVal = javaObject.getSimpleName() + ".class";
        }
        else if ( elementValue instanceof Annotation )
        {
            Annotation annotation = ( Annotation ) elementValue;

            retVal = annotation.generateContentString();
        }
        else
        {
            retVal = elementValue.toString();
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( elementValue instanceof NonReferenceJavaObject )
        {
            NonReferenceJavaObject javaObject = ( NonReferenceJavaObject ) elementValue;

            retVal.add( javaObject );
        }
        else if ( elementValue instanceof Annotation )
        {
            Annotation annotation = ( Annotation ) elementValue;

            retVal.addAll( annotation );
        }

        retVal.addAll( additionalImportsCollection );

        return retVal;
    }

    public boolean addAdditionalImport( NonReferenceJavaObject javaObject )
    {
        return additionalImportsCollection.add( javaObject );
    }
}
