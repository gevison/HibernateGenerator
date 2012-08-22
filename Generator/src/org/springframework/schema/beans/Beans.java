//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2012.06.08 at 10:53:21 AM BST
//


package org.springframework.schema.beans;

import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

import com.sun.xml.internal.messaging.saaj.util.*;
import org.springframework.schema.beans.enums.*;
import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "", propOrder = { "description", "beansParts" } )
@XmlRootElement( name = "beans" )
public class Beans
{

    protected Description description;

    @XmlElementRefs( { @XmlElementRef( name = "bean",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Bean.class ), @XmlElementRef( name = "alias",
                                                                            namespace = "http://www.springframework.org/schema/beans",
                                                                            type = Alias.class ),
                       @XmlElementRef( name = "import",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Import.class ) } )
    @XmlAnyElement( lax = true )
    protected List<BeansPart> beansParts = new ArrayList<BeansPart>();

    @XmlAttribute( name = "default-lazy-init" )
    protected DefaultableBoolean defaultLazyInit;

    @XmlAttribute( name = "default-merge" )
    protected DefaultableBoolean defaultMerge;

    @XmlAttribute( name = "default-autowire" )
    protected Autowire defaultAutowire;

    @XmlAttribute( name = "default-dependency-check" )
    protected DependencyCheck defaultDependencyCheck;

    @XmlAttribute( name = "default-autowire-candidates" )
    protected String defaultAutowireCandidates;

    @XmlAttribute( name = "default-init-method" )
    protected String defaultInitMethod;

    @XmlAttribute( name = "default-destroy-method" )
    protected String defaultDestroyMethod;

    public Autowire getDefaultAutowire()
    {
        return defaultAutowire;
    }

    public void setDefaultAutowire( Autowire defaultAutowire )
    {
        this.defaultAutowire = defaultAutowire;
    }

    public String getDefaultAutowireCandidates()
    {
        return defaultAutowireCandidates;
    }

    public void setDefaultAutowireCandidates( String defaultAutowireCandidates )
    {
        this.defaultAutowireCandidates = defaultAutowireCandidates;
    }

    public DependencyCheck getDefaultDependencyCheck()
    {
        return defaultDependencyCheck;
    }

    public void setDefaultDependencyCheck( DependencyCheck defaultDependencyCheck )
    {
        this.defaultDependencyCheck = defaultDependencyCheck;
    }

    public String getDefaultDestroyMethod()
    {
        return defaultDestroyMethod;
    }

    public void setDefaultDestroyMethod( String defaultDestroyMethod )
    {
        this.defaultDestroyMethod = defaultDestroyMethod;
    }

    public String getDefaultInitMethod()
    {
        return defaultInitMethod;
    }

    public void setDefaultInitMethod( String defaultInitMethod )
    {
        this.defaultInitMethod = defaultInitMethod;
    }

    public DefaultableBoolean getDefaultLazyInit()
    {
        return defaultLazyInit;
    }

    public void setDefaultLazyInit( DefaultableBoolean defaultLazyInit )
    {
        this.defaultLazyInit = defaultLazyInit;
    }

    public DefaultableBoolean getDefaultMerge()
    {
        return defaultMerge;
    }

    public void setDefaultMerge( DefaultableBoolean defaultMerge )
    {
        this.defaultMerge = defaultMerge;
    }

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }

    public boolean addBeansPart( BeansPart value )
    {
        return beansParts.add( value );
    }

    public boolean containsBeansPart( BeansPart value )
    {
        return beansParts.contains( value );
    }

    public BeansPart getBeansPart( int index )
    {
        return beansParts.get( index );
    }

    public boolean isBeansPartsEmpty()
    {
        return beansParts.isEmpty();
    }

    public boolean removeBeansPart( BeansPart value )
    {
        return beansParts.remove( value );
    }

    public int beansPartsSize()
    {
        return beansParts.size();
    }

    public BeansPart[] beansPartsToArray(  )
    {
        return beansParts.toArray( new BeansPart[ beansParts.size()] );
    }

    public void marshal(OutputStream outputStream) throws
                                                    JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(Beans.class);

        Marshaller m = context.createMarshaller();
        m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal( this, outputStream );
    }
}