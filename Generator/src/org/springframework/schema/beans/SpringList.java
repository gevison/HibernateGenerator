package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.enums.*;
import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "list" )
public class SpringList extends ListOrSetType implements EntryTypePart, ListOrSetTypePart
{
    @XmlAttribute
    protected DefaultableBoolean merge;

    public DefaultableBoolean getMerge()
    {
        return merge;
    }

    public void setMerge( DefaultableBoolean merge )
    {
        this.merge = merge;
    }
}
