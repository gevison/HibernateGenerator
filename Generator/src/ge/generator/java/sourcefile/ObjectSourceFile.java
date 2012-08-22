package ge.generator.java.sourcefile;

import java.util.*;

import ge.generator.java.generated.*;
import ge.generator.java.utils.*;

public class ObjectSourceFile extends SourceFile
{
    private GeneratedObject object = null;

    public ObjectSourceFile(  )
    {
    }

    public GeneratedObject getObject()
    {
        return object;
    }

    public void setObject( GeneratedObject object )
    {
        if ( this.object != null )
        {
            ParentObject parentObject = ( ParentObject ) this.object;
            parentObject.setParent( null );
        }
        this.object = object;
        ParentObject parentObject = ( ParentObject ) this.object;
        parentObject.setParent(this);
    }

    @Override
    protected List<String> generateFileContents()
    {
        List<String> retVal = new ArrayList<String>();

        String packageName = getPackageName();

        if ( packageName != null )
        {
            retVal.add( "package " + packageName + ";" );
        }

        List<String> imports = object.generateImports();

        imports = ImportHelper.organiseImports( imports, getPackageName(), object.getFullyQualifiedName() );

        if ( ( imports != null ) && ( imports.size() != 0 ) )
        {
            retVal.add( "" );
            for ( String anImport : imports )
            {
                retVal.add( "import " + anImport + ";" );
            }
        }

        retVal.add( "" );

        retVal.addAll( object.generateContentStrings() );

        return retVal;
    }

    @Override
    protected String getFileName()
    {
        return object.getSimpleName() + ".java";
    }
}
