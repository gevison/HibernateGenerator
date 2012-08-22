package org.springframework.schema.beans;

import org.springframework.schema.beans.parts.ListOrSetTypePart;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "listOrSetType", propOrder = { "description", "listOrSetTypeParts" } )
@XmlSeeAlso( { SpringList.class, SpringSet.class } )
public class ListOrSetType extends CollectionType
{

    protected Description description;

    @XmlElementRefs( { @XmlElementRef( name = "ref",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Ref.class ), @XmlElementRef( name = "map",
                                                                           namespace = "http://www.springframework.org/schema/beans",
                                                                           type = SpringMap.class ),
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
                       @XmlElementRef( name = "null",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Null.class ), @XmlElementRef( name = "list",
                                                                            namespace = "http://www.springframework.org/schema/beans",
                                                                            type = SpringList.class ),
                       @XmlElementRef( name = "props",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Props.class ) } )
    @XmlAnyElement( lax = true )
    protected List<ListOrSetTypePart> listOrSetTypeParts = new ArrayList<ListOrSetTypePart>();

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }      

    public boolean addListOrSetTypePart( ListOrSetTypePart value )
    {
        return listOrSetTypeParts.add( value );
    }

    public boolean containsListOrSetTypePart( ListOrSetTypePart value )
    {
        return listOrSetTypeParts.contains( value );
    }

    public ListOrSetTypePart getListOrSetTypePart( int index )
    {
        return ( ListOrSetTypePart ) listOrSetTypeParts.get( index );
    }

    public boolean isListOrSetTypePartsEmpty()
    {
        return listOrSetTypeParts.isEmpty();
    }

    public boolean removeListOrSetTypePart( ListOrSetTypePart value )
    {
        return listOrSetTypeParts.remove( value );
    }

    public int listOrSetTypePartsSize()
    {
        return listOrSetTypeParts.size();
    }

    public ListOrSetTypePart[] listOrSetTypePartsToArray(  )
    {
        return listOrSetTypeParts.toArray( new ListOrSetTypePart[ listOrSetTypeParts.size()] );
    }
}
