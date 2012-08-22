package ge.generator.java;

import javax.xml.bind.*;

import java.io.*;
import java.util.*;

import com.sun.xml.internal.messaging.saaj.util.*;
import ge.generator.java.existing.*;
import ge.generator.java.interfaces.*;
import org.junit.*;
import org.springframework.schema.beans.*;
import org.springframework.schema.beans.enums.*;
import uk.gov.coal.inferis.publicsafety.util.service.impl.*;

public class TestBeans
{
    private static String result;

    @Ignore
    @Test
    public void testBeans() throws
                            JAXBException
    {
        Beans beans = new Beans();
        beans.setDefaultLazyInit(DefaultableBoolean.TRUE);
        beans.setDefaultAutowire( Autowire.BY_NAME);

        Bean bean = new Bean();
        bean.setClazz( ( NonReferenceJavaObject ) ExistingJavaObject.instance( SimpleObjectServiceImpl.class ) );

        bean.setId( "Bean" );

        Bean bean2 = new Bean();

        MetaType beanPart = new MetaType();
        beanPart.setKey( "name" );
        beanPart.setValue( "value" );


        bean2.addBeanPart(beanPart);

        bean2.setId( "Bean2" );
        bean2.setParent(bean);

        beans.addBeansPart( bean );
        beans.addBeansPart( bean2 );

        ByteOutputStream bos = new ByteOutputStream();
        beans.marshal( bos );
        result = new String(bos.getBytes());
        System.out.println(result);
    }

    @Ignore
    @Test
    public void testIn() throws
                         JAXBException
    {
        JAXBContext context = JAXBContext.newInstance( Beans.class );

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ByteInputStream bis = new ByteInputStream(result.trim().getBytes(),result.length());
        Beans beans = ( Beans ) unmarshaller.unmarshal(bis);

        System.out.println(beans);
    }
}
