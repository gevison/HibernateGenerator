package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.annotation.*;

public class AnnotationCollection extends ArrayList<Annotation> implements Content
{
    public boolean addAll( AnnotationCollection c )
    {
        return super.addAll( c );
    }

    public boolean addAll( int index, AnnotationCollection c )
    {
        return super.addAll( index, c );
    }

    public boolean containsAll( AnnotationCollection c )
    {
        return super.containsAll( c );
    }

    public boolean removeAll( AnnotationCollection c )
    {
        return super.removeAll( c );
    }

    public boolean retainAll( AnnotationCollection c )
    {
        return super.retainAll( c );
    }

    public List<String> generateContentStrings()
    {
        List<String> retVal = new ArrayList<String>();
        if ( isEmpty() == false )
        {
            for ( Annotation annotation : this )
            {
                retVal.add( annotation.generateContentString() );
            }
        }

        return retVal;
    }

    public String generateContentString()
    {
        String retVal = "";

        if ( isEmpty() == false )
        {
            for ( Annotation annotation : this )
            {
                if ( retVal.length() != 0 )
                    retVal += " ";

                retVal += annotation.generateContentString();
            }
        }

        return retVal;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( isEmpty() == false )
        {
            for ( Annotation annotation : this )
            {
                retVal.addAll( annotation );
            }
        }

        return retVal;
    }
}
