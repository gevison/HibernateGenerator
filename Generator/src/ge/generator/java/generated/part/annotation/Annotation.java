package ge.generator.java.generated.part.annotation;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.interfaces.*;

public class Annotation implements SingleLineContent
{
    private AnnotationJavaObject type;

    private AnnotationElement annotationElement;

    public Annotation()
    {
    }

    public AnnotationElement getAnnotationElement()
    {
        return annotationElement;
    }

    public void setAnnotationElement( AnnotationElement annotationElement )
    {
        this.annotationElement = annotationElement;
    }

    public AnnotationJavaObject getType()
    {
        return type;
    }

    public void setType( AnnotationJavaObject type )
    {
        this.type = type;
    }

    public String generateContentString()
    {
        String annotation = "@" + type.getSimpleName();

        if ( annotationElement != null )
        {
            annotation += annotationElement.generateContentString();
        }

        return annotation;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        retVal.add( type );

        if ( annotationElement != null )
        {
            retVal.addAll( annotationElement );
        }

        return retVal;
    }
}
