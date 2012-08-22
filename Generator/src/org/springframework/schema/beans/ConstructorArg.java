package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.parts.*;


@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "",
          propOrder = { "description", "bean", "idref", "_null", "list", "set", "map", "props", "any",
                        "value", "ref" } )
@XmlRootElement( name = "constructor-arg" )
public class ConstructorArg implements BeanPart
{
    protected Description description;

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

    @XmlAttribute
    protected String index;

    @XmlAttribute
    protected String type;

    @XmlAttribute( name = "ref" )
    protected String refString;

    @XmlAttribute( name = "value" )
    protected String valueString;

    protected Value value;

    protected Ref ref;

    public Null getNull()
    {
        return _null;
    }

    public void setNull( Null _null )
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

    public String getIndex()
    {
        return index;
    }

    public void setIndex( String index )
    {
        this.index = index;
    }

    public Props getProps()
    {
        return props;
    }

    public void setProps( Props props )
    {
        this.props = props;
    }

    public Ref getRef()
    {
        return ref;
    }

    public void setRef( Ref ref )
    {
        this.ref = ref;
    }

    public String getRefString()
    {
        return refString;
    }

    public void setRefString( String refString )
    {
        this.refString = refString;
    }

    public SpringSet getSet()
    {
        return set;
    }

    public void setSet( SpringSet set )
    {
        this.set = set;
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

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

    public Value getValue()
    {
        return value;
    }

    public void setValue( Value value )
    {
        this.value = value;
    }

    public String getValueString()
    {
        return valueString;
    }

    public void setValueString( String valueString )
    {
        this.valueString = valueString;
    }
}
