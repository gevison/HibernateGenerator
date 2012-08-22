package ge.generator.java.sourcefile;

import java.io.*;
import java.util.*;

import ge.generator.java.*;

public abstract class SourceFile extends PackageContainerElement
{
    @Override
    public final void generate( File directory ) throws
                                                 IOException
    {
        File file = new File( directory, getFileName() );

        List<String> contents = generateFileContents();

        FileOutputStream fileOutputStream = new FileOutputStream( file );

        for ( String content : contents )
        {
            fileOutputStream.write( content.getBytes() );
            fileOutputStream.write( System.getProperty( "line.separator" ).getBytes() );
        }

        fileOutputStream.flush();
        fileOutputStream.close();
    }

    protected abstract List<String> generateFileContents();

    protected abstract String getFileName();
}
