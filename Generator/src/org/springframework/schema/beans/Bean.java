package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import java.util.*;

import ge.generator.java.interfaces.*;
import org.springframework.schema.beans.adapters.*;
import org.springframework.schema.beans.enums.*;
import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "bean" )
public class Bean extends IdentifiedType implements BeansPart,
                                                    EntryTypePart, KeyPart, ListOrSetTypePart
{
    protected Description description;

    @XmlElementRefs( { @XmlElementRef( name = "meta",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = MetaType.class ),

                       @XmlElementRef( name = "qualifier",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = Qualifier.class ),

                       @XmlElementRef( name = "replaced-method",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = ReplacedMethod.class ),

                       @XmlElementRef( name = "constructor-arg",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = ConstructorArg.class ),

                       @XmlElementRef( name = "lookup-method",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = LookupMethod.class ),

                       @XmlElementRef( name = "property",
                                       namespace = "http://www.springframework.org/schema/beans",
                                       type = PropertyType.class ) } )
    @XmlAnyElement( lax = true )
    protected List<BeanPart> beanParts = new ArrayList<BeanPart>();

    @XmlAttribute
    protected String name;

    @XmlAttribute( name = "class" )
    @XmlJavaTypeAdapter(JavaObjectTypeAdapter.class)
    protected NonReferenceJavaObject clazz;

    @XmlAttribute
    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    protected IdentifiedType parent;

    @XmlAttribute
    protected String scope;

    @XmlAttribute( name = "abstract" )
    protected Boolean isAbstract;

    @XmlAttribute( name = "lazy-init" )
    protected DefaultableBoolean lazyInit;

    @XmlAttribute
    protected DefaultAutowire autowire;

    @XmlAttribute( name = "dependency-check" )
    protected DefaultDependencyCheck dependencyCheck;

    @XmlAttribute( name = "depends-on" )
    protected String dependsOn;

    @XmlAttribute( name = "autowire-candidate" )
    protected DefaultableBoolean autowireCandidate;

    @XmlAttribute
    protected Boolean primary;

    @XmlAttribute( name = "init-method" )
    protected String initMethod;

    @XmlAttribute( name = "destroy-method" )
    protected String destroyMethod;

    @XmlAttribute( name = "factory-method" )
    protected String factoryMethod;

    @XmlIDREF
    @XmlSchemaType( name = "IDREF" )
    @XmlAttribute( name = "factory-bean" )
    protected IdentifiedType factoryBean;

    public Boolean getAbstract()
    {
        return isAbstract;
    }

    public void setAbstract( Boolean anAbstract )
    {
        isAbstract = anAbstract;
    }

    public DefaultAutowire getAutowire()
    {
        return autowire;
    }

    public void setAutowire( DefaultAutowire autowire )
    {
        this.autowire = autowire;
    }

    public DefaultableBoolean getAutowireCandidate()
    {
        return autowireCandidate;
    }

    public void setAutowireCandidate( DefaultableBoolean autowireCandidate )
    {
        this.autowireCandidate = autowireCandidate;
    }

    public NonReferenceJavaObject getClazz()
    {
        return clazz;
    }

    public void setClazz( NonReferenceJavaObject clazz )
    {
        this.clazz = clazz;
    }

    public DefaultDependencyCheck getDependencyCheck()
    {
        return dependencyCheck;
    }

    public void setDependencyCheck( DefaultDependencyCheck dependencyCheck )
    {
        this.dependencyCheck = dependencyCheck;
    }

    public String getDependsOn()
    {
        return dependsOn;
    }

    public void setDependsOn( String dependsOn )
    {
        this.dependsOn = dependsOn;
    }

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }

    public String getDestroyMethod()
    {
        return destroyMethod;
    }

    public void setDestroyMethod( String destroyMethod )
    {
        this.destroyMethod = destroyMethod;
    }

    public IdentifiedType getFactoryBean()
    {
        return factoryBean;
    }

    public void setFactoryBean( IdentifiedType factoryBean )
    {
        this.factoryBean = factoryBean;
    }

    public String getFactoryMethod()
    {
        return factoryMethod;
    }

    public void setFactoryMethod( String factoryMethod )
    {
        this.factoryMethod = factoryMethod;
    }

    public String getInitMethod()
    {
        return initMethod;
    }

    public void setInitMethod( String initMethod )
    {
        this.initMethod = initMethod;
    }

    public DefaultableBoolean getLazyInit()
    {
        return lazyInit;
    }

    public void setLazyInit( DefaultableBoolean lazyInit )
    {
        this.lazyInit = lazyInit;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public IdentifiedType getParent()
    {
        return parent;
    }

    public void setParent( IdentifiedType parent )
    {
        this.parent = parent;
    }

    public Boolean getPrimary()
    {
        return primary;
    }

    public void setPrimary( Boolean primary )
    {
        this.primary = primary;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope( String scope )
    {
        this.scope = scope;
    }

    public boolean addBeanPart( BeanPart value )
    {
        return beanParts.add( value );
    }

    public boolean containsBeanPart( BeanPart value )
    {
        return beanParts.contains( value );
    }

    public BeanPart getBeanPart( int index )
    {
        return ( BeanPart ) beanParts.get( index );
    }

    public boolean isBeanPartsEmpty()
    {
        return beanParts.isEmpty();
    }

    public boolean removeBeanPart( BeanPart value )
    {
        return beanParts.remove( value );
    }

    public int beanPartsSize()
    {
        return beanParts.size();
    }

    public BeanPart[] beanPartsToArray(  )
    {
        return beanParts.toArray( new BeanPart[ beanParts.size()] );
    }
}
