package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class EInvoiceVNPTTerm {
	
	private static List<Element> elements(NodeList nodes) {
	    List<Element> result = new ArrayList<Element>(nodes.getLength());
	    for (int i = 0; i < nodes.getLength(); i++) {
	      Node node = nodes.item(i);
	      if (node instanceof Element)
	        result.add((Element)node);
	    }
	    return result;
	  }

	  private static Element named(Element elem, String name) {
	    if (!elem.getNodeName().equals(name))
	      throw new IllegalArgumentException("Expected " + name + ", got " + elem.getNodeName());
	    return elem;
	  }
	  
	  
	//common
	public static final String NAME_SPACE = "tem";
	public static final String NAME_SPACE_URI = "http://tempuri.org/"; 
	
	public static final String UPCUS = "tem:UpdateCus";
	public static final String UPCUS_XMLCUSDATA = "tem:XMLCusData";
	public static final String UPCUS_USERNAME = "tem:username";
	public static final String UPCUS_PASS = "tem:pass";
	public static final String UPCUS_CONVERT = "tem:convert";
	public static final String UPCUS_SOAP_ENDPOINT = "";
	public static final String UPCUS_SOAP_ACTION = "";

}
