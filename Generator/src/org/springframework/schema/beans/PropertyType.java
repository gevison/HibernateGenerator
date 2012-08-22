package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "",
          propOrder = { "description", "meta", "bean", "idref", "_null", "list", "set", "map", "props",
                        "any" } )
@XmlRootElement( name = "property" )
public class PropertyType implements BeanPart
{

    protected Description description;

    protected MetaType meta;

    protected Bean bean;

    protected Idref idref;

    @XmlElement( name = "null" )
    protected Null _null;

    protected SpringList list;

    protected SpringSet set;

    protected SpringMap map;

    protected Props props;

    @XmlAnyElement( lax = true )
    protected Object any;

    @XmlAttribute( required = true )
    protected String name;

    @XmlAttribute
    protected String ref;

    @XmlAttribute
    protected String value;

    public Null get_null()
    {
        return _null;
    }

    public void set_null( Null _null )
    {
        this._null = _null;
    }

    public Object getAny()
    {
        return any;
    }

    public void setAny( Object any )
    {
        this.any = any;
    }

    public Bean getBean()
    {
        return bean;
    }

    public void setBean( Bean bean )
    {
        this.bean = bean;
    }

    public Description getDescription()
    {
        return description;
    }

    public void setDescription( Description description )
    {
        this.description = description;
    }

    public Idref getIdref()
    {
        return idref;
    }

    public void setIdref( Idref idref )
    {
        this.idref = idref;
    }

    public MetaType getMeta()
    {
        return meta;
    }

    public void setMeta( MetaType meta )
    {
        this.meta = meta;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Props getProps()
    {
        return props;
    }

    public void setProps( Props props )
    {
        this.props = props;
    }

    public String getRef()
    {
        return ref;
    }

    public void setRef( String ref )
    {
        this.ref = ref;
    }

    public SpringSet getSet()
    {
        return set;
    }

    public void setSet( SpringSet set )
    {
        this.set = set;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public SpringList getList()
    {
        return list;
    }

    public void setList( SpringList list )
    {
        this.list = list;
    }

    public SpringMap getMap()
    {
        return map;
    }

    public void setMap( SpringMap map )
    {
        this.map = map;
    }
}
