package ge.generator.java.sourcefile;

import java.util.*;

import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.part.annotation.*;
import ge.generator.java.utils.*;

public class PackageInfoSourceFile extends SourceFile
{
    private static final String PACKAGE_INFO_FILENAME = "package-info.java";

    private AnnotationCollection annotations = new AnnotationCollection();

    public PackageInfoSourceFile()
    {
    }

    public void clearAnnotations()
    {
        this.annotations.clear();
    }

    public Annotation[] toAnnotationArray()
    {
        return this.annotations.toArray( new Annotation[ this.annotations.size() ] );
    }

    public boolean addAnnotationsAll( Collection<Annotation> annotations )
    {
        return this.annotations.addAll( annotations );
    }

    public int annotationsSize()
    {
        return this.annotations.size();
    }

    public boolean removeAnnotation( Annotation annotation )
    {
        return this.annotations.remove( annotation );
    }

    public boolean addAnnotation( Annotation annotation )
    {
        return this.annotations.add( annotation );
    }

    public Annotation getAnnotation( int index )
    {
        return this.annotations.get( index );
    }

    public boolean isAnnotationsEmpty()
    {
        return this.annotations.isEmpty();
    }

    @Override
    protected List<String> generateFileContents()
    {
        List<String> retVal = new ArrayList<String>();

        for ( Annotation annotation : annotations )
        {
            String content = annotation.generateContentString();

            if ( ( content != null ) && ( content.length() != 0 ) )
            {
                retVal.add( content );
            }
        }

        String packageName = getPackageName();

        if ( packageName != null )
        {
            retVal.add( "package " + packageName + ";" );
        }

        List<String> imports = new ArrayList<String>();

        for ( Annotation annotation : annotations )
        {
            List<String> annotationImports = annotation.generateImports();

            if ( ( annotationImports != null ) && ( annotationImports.size() != 0 ) )
            {
                imports.addAll( annotationImports );
            }
        }

        imports = ImportHelper.organiseImports( imports, getPackageName(), null );

        if ( ( imports != null ) && ( imports.size() != 0 ) )
        {
            retVal.add( "" );
            for ( String anImport : imports )
            {
                retVal.add( "import " + anImport + ";" );
            }
        }

        return retVal;
    }

    @Override
    protected String getFileName()
    {
        return PACKAGE_INFO_FILENAME;
    }
}
