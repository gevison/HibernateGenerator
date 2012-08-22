package ge.generator.java.generated.collection;

import java.util.*;

import ge.generator.java.generated.*;
import ge.generator.java.generated.interfaces.*;

public class MemberCollection extends ArrayList<Member> implements MultiLineContent
{
    public List<String> generateContentStrings()
    {
        return null;
    }

    public ImportCollection generateImports()
    {
        ImportCollection retVal = new ImportCollection();

        if ( isEmpty() == false )
        {
            for ( Member member : this )
            {
                retVal.addAll( member );
            }
        }

        return retVal;
    }

    public void setParent( ParentObject parentObject )
    {
        for ( Member member : this )
        {
            if ( member instanceof ParentObject )
            {
                ( ( ParentObject ) member ).setParent( parentObject );
            }
        }
    }
}
