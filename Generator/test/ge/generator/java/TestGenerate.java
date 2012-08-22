package ge.generator.java;

import java.io.*;
import java.util.*;

import ge.generator.java.existing.*;
import ge.generator.java.generated.*;
import ge.generator.java.generated.collection.*;
import ge.generator.java.generated.interfaces.*;
import ge.generator.java.generated.part.*;
import ge.generator.java.generated.specialist.*;
import ge.generator.java.interfaces.*;
import ge.generator.java.sourcefile.*;
import org.junit.*;

public class TestGenerate
{
    @Test
    public void generationTest() throws
                                 IOException
    {
        PackageContainer packageContainer = new PackageContainer( );

        packageContainer.setName("ge.test");

        packageContainer.addPackageContainerElement( createClassSourceObject() );

        packageContainer.generate( new File( "D:\\TempOutput\\src" ) );
    }

    private PackageContainerElement createClassSourceObject()
    {
        ObjectSourceFile retVal = new ObjectSourceFile();

        retVal.setObject(createClassObject());

        return retVal;
    }

    private GeneratedClassJavaObject createClassObject()
    {
        PojoGeneratedClassJavaObject retVal = new PojoGeneratedClassJavaObject();

        retVal.addModifier( Modifier.PUBLIC );

        retVal.setIdentifier( "TestClass" );

        retVal.addMember( createFieldMember() );

        return retVal;
    }

    private Member createFieldMember()
    {
        Field retVal = new Field();

        retVal.addModifier( Modifier.PRIVATE );

        retVal.setType( PrimitiveJavaObject.LONG );

        retVal.setArray(false);

        retVal.setName("id");

        retVal.setVariableInitializer( "null" );

        return retVal;
    }
}
