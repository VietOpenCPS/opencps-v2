package org.opencps.thirdparty.system.scheduler;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.opencps.thirdparty.system.nsw.model.RequestPayload;

public class SOAPConverter {
	public static String convertNSWRequest(RequestPayload request) {
		JAXBContext context;
		String xmlString = "";
		
		try {
			context = JAXBContext.newInstance(RequestPayload.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	        StringWriter sw = new StringWriter();
	        m.marshal(request, sw);
	        xmlString = sw.toString();		
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return xmlString;
	}
}
