package org.springframework.schema.beans;

import javax.xml.bind.annotation.*;

import org.springframework.schema.beans.enums.*;
import org.springframework.schema.beans.parts.*;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
@XmlRootElement( name = "props" )
public class Props extends PropsType implements EntryTypePart, KeyPart, ListOrSetTypePart
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
