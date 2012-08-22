package ge.generator.java;

import java.io.*;

import ge.generator.java.generated.*;

public abstract class PackageContainerElement extends ParentObject
{
    public abstract void generate( File directory ) throws
                                                    IOException;

}
