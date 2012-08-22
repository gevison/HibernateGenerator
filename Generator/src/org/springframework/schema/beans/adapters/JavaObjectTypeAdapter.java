package org.springframework.schema.beans.adapters;

import javax.xml.bind.annotation.adapters.*;

import ge.generator.java.interfaces.*;

public class JavaObjectTypeAdapter extends XmlAdapter<String,NonReferenceJavaObject>
{
    @Override
    public NonReferenceJavaObject unmarshal( String v ) throws
                                            Exception
    {
        return null;
    }

    @Override
    public String marshal( NonReferenceJavaObject v ) throws
                                          Exception
    {
        if ( v != null )
        {
            return v.getFullyQualifiedName();
        }
        return null;
    }
}
