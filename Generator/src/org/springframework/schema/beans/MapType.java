package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "mapType", propOrder = { "description", "entryTypes" } )
@XmlSeeAlso( { SpringMap.class } )
public class MapType extends CollectionType
{

    protected Description description;

    protected List<EntryType> entryTypes = new ArrayList<EntryType>();

    @XmlAttribute( name = "key-type" )
    protected String keyType;

    public String getKeyType()
    {
        return keyType;
    }

    public void setKeyType( String keyType )
    {
        this.keyType = keyType;
    }

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }

    public boolean addEntryType( EntryType entryType )
    {
        return entryTypes.add( entryType );
    }

    public EntryType getEntryType( int index )
    {
        return entryTypes.get( index );
    }

    public EntryType[] toEntryTypeArray()
    {
        return entryTypes.toArray(new EntryType[ entryTypes.size()]);
    }

    public boolean isEntryTypesEmpty()
    {
        return entryTypes.isEmpty();
    }

    public boolean containsEntryType( EntryType entryType )
    {
        return entryTypes.contains( entryType );
    }

    public int EntryTypesSize()
    {
        return entryTypes.size();
    }

    public boolean removeEntryType( EntryType entryType )
    {
        return entryTypes.remove( entryType );
    }
}
