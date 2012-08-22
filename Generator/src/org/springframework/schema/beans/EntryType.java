package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "entryType", propOrder = { "description", "entryTypeParts" } )
public class EntryType
{
    protected Description description;

    @XmlElementRefs( { @XmlElementRef( name = "map",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = SpringMap.class ), @XmlElementRef( name = "ref",
                                                                                 namespace = "http://www.springframework.org/schema/beans",
                                                                                 type = Ref.class ),
                       @XmlElementRef( name = "bean",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Bean.class ), @XmlElementRef( name = "value",
                                                                            namespace = "http://www.springframework.org/schema/beans",
                                                                            type = Value.class ),
                       @XmlElementRef( name = "idref",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Idref.class ), @XmlElementRef( name = "set",
                                                                             namespace = "http://www.springframework.org/schema/beans",
                                                                             type = SpringSet.class ),
                       @XmlElementRef( name = "list",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = SpringList.class ), @XmlElementRef( name = "null",
                                                                                  namespace = "http://www.springframework.org/schema/beans",
                                                                                  type = Null.class ),
                       @XmlElementRef( name = "props",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Props.class ) } )
    @XmlAnyElement( lax = true )
    protected List<EntryTypePart> entryTypeParts = new ArrayList<EntryTypePart>();

    @XmlAttribute
    protected String key;

    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    @XmlAttribute( name = "key-ref" )
    protected IdentifiedType keyRef;

    @XmlAttribute
    protected String value;

    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    @XmlAttribute( name = "value-ref" )
    protected IdentifiedType valueRef;

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( String key )
    {
        this.key = key;
    }

    public IdentifiedType getKeyRef()
    {
        return keyRef;
    }

    public void setKeyRef( IdentifiedType keyRef )
    {
        this.keyRef = keyRef;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public IdentifiedType getValueRef()
    {
        return valueRef;
    }

    public void setValueRef( IdentifiedType valueRef )
    {
        this.valueRef = valueRef;
    }

    public boolean addEntryTypePart( EntryTypePart value )
    {
        return entryTypeParts.add( value );
    }

    public boolean containsEntryTypePart( EntryTypePart value )
    {
        return entryTypeParts.contains( value );
    }

    public EntryTypePart getEntryTypePart( int index )
    {
        return entryTypeParts.get( index );
    }

    public boolean isEntryTypePartsEmpty()
    {
        return entryTypeParts.isEmpty();
    }

    public boolean removeEntryTypePart( EntryTypePart value )
    {
        return entryTypeParts.remove( value );
    }

    public int entryTypePartsSize()
    {
        return entryTypeParts.size();
    }

    public EntryTypePart[] entryTypePartsToArray(  )
    {
        return entryTypeParts.toArray( new EntryTypePart[ entryTypeParts.size()] );
    }
}
