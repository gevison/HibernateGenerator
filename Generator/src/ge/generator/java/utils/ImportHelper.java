package ge.generator.java.utils;

import java.util.*;

public class ImportHelper
{
    private static final String JAVA_LANG_PACKAGE_NAME = "java.lang";

    public static List<String> organiseImports( List<String> imports, String packageName, String className )
    {
        if ( ( imports == null ) || ( imports.size() == 0 ) )
            return null;

        if ( packageName == null )
            packageName = "";

        if ( className == null )
            className = "";

        List<String> retVal = new ArrayList<String>();

        for ( String anImport : imports )
        {
            String importPackageName = anImport.substring( 0, anImport.lastIndexOf( "." ) );

            if ( ( packageName.equals( importPackageName ) == false ) &&
                 ( anImport.startsWith( className ) == false ) &&
                 ( JAVA_LANG_PACKAGE_NAME.equals( importPackageName ) == false ) )
            {
                if ( retVal.contains( anImport ) == false )
                {
                    retVal.add( anImport );
                }
            }
        }
        return retVal;
    }
}
