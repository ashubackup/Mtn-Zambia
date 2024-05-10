package com.Model;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class XMLUtils {
	public static String marshal(Object object) throws CustomJAXBException {
		try
		{
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, writer);
			return writer.toString();
		}
		catch (JAXBException e) {
			// TODO: handle exception
			 throw new CustomJAXBException("Error occurred during JAXB marshalling", e);
		}
	}
	// Define your custom JAXB exception
    public static class CustomJAXBException extends Exception {
        public CustomJAXBException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    public static String marshalToString(Object obj, Marshaller marshaller) throws JAXBException {
        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);
        return sw.toString();
    }
}
