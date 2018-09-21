package org.opencps.api.sendmt.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.opencps.api.sendmt.model.SendMT;

public class SendMTConverterUtils {

//	public s

	public static SendMT convertXMLToSendMT(String xmlString) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(SendMT.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xmlString);
			SendMT  sendMT = (SendMT) jaxbUnmarshaller.unmarshal(reader);
			//
			return sendMT;
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}
}
