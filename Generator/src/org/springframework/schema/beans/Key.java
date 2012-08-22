package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import java.util.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "description", "keyParts" } )
@XmlRootElement( name = "key" )
public class Key
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
    protected List<KeyPart> keyParts = new ArrayList<KeyPart>();

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }

    public boolean addKeyPart( KeyPart value )
    {
        return keyParts.add( value );
    }

    public boolean containsKeyPart( KeyPart value )
    {
        return keyParts.contains( value );
    }

    public KeyPart getKeyPart( int index )
    {
        return keyParts.get( index );
    }

    public boolean isKeyPartsEmpty()
    {
        return keyParts.isEmpty();
    }

    public boolean removeKeyPart( KeyPart value )
    {
        return keyParts.remove( value );
    }

    public int keyPartsSize()
    {
        return keyParts.size();
    }

    public KeyPart[] keyPartsToArray(  )
    {
        return keyParts.toArray( new KeyPart[ keyParts.size()] );
    }
}
