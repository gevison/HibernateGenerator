package ge.generator.java;

import java.io.*;
import java.util.*;

public class PackageContainer extends PackageContainerElement
{
    private String name;

    private List<PackageContainerElement> packageContainerElements = new ArrayList<PackageContainerElement>();

    public PackageContainer( )
    {
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setName( Package aPackage )
    {
        this.name = aPackage.getName();
    }

    public void clearPackageContainerElements()
    {
        for ( PackageContainerElement packageContainerElement : packageContainerElements )
        {
            packageContainerElement.setParent(null);
        }
        this.packageContainerElements.clear();
    }

    public PackageContainerElement[] toPackageContainerElementArray()
    {
        return this.packageContainerElements.toArray( new PackageContainerElement[ this.packageContainerElements.size() ] );
    }

    public boolean addPackageContainerElementsAll( Collection<PackageContainerElement> packageContainerElementCollection )
    {
        for ( PackageContainerElement packageContainerElement : packageContainerElementCollection )
        {
            packageContainerElement.setParent( this );
        }
        return this.packageContainerElements.addAll( packageContainerElementCollection );
    }

    public int packageContainerElementsSize()
    {
        return this.packageContainerElements.size();
    }

    public boolean removePackageContainerElement( PackageContainerElement packageContainerElement )
    {
        packageContainerElement.setParent( null );
        return this.packageContainerElements.remove( packageContainerElement );
    }

    public boolean addPackageContainerElement( PackageContainerElement packageContainerElement )
    {
        packageContainerElement.setParent( this );
        return this.packageContainerElements.add( packageContainerElement );
    }

    public PackageContainerElement getPackageContainerElement( int index )
    {
        return this.packageContainerElements.get( index );
    }

    public boolean isPackageContainerElementsEmpty()
    {
        return this.packageContainerElements.isEmpty();
    }

    @Override
    public void generate( File directory ) throws
                                           IOException
    {
        String directoryName = name.replace( ".", System.getProperty( "file.separator" ) );

        File packageDirectory = new File( directory, directoryName );

        if ( packageDirectory.exists() == false )
        {
            packageDirectory.mkdirs();
        }

        for ( PackageContainerElement packageContainerElement : packageContainerElements )
        {
            packageContainerElement.generate( packageDirectory );
        }
    }

    @Override
    public String getPackageName()
    {
        String parentPackageName = super.getPackageName();

        if ( parentPackageName == null )
        {
            return name;
        }
        else
        {
            return parentPackageName + "." + name;
        }
    }
}
