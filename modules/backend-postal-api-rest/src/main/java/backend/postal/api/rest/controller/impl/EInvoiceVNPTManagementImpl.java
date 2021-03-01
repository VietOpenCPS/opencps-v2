package backend.postal.api.rest.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.opencps.api.controller.util.EInvoiceVNPTTerm;
import org.opencps.api.einvoice.vnpt.model.CustomerInputModel;
import org.opencps.api.einvoice.vnpt.model.CustomerModel;
import org.opencps.api.einvoice.vnpt.model.UpdateCusRequest;
import org.opencps.api.einvoice.vnpt.model.UpdateCusResponse;
import org.opencps.api.invoice.model.InvoiceInputModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import backend.postal.api.rest.controller.EInvoiceVNPTManagement;

public class EInvoiceVNPTManagementImpl implements EInvoiceVNPTManagement{

	private static final Log _log = LogFactoryUtil.getLog(EInvoiceVNPTManagementImpl.class);

	@Override
	public Response updateCus(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, CustomerModel input) {
		
		String nodeValue = convertJaxbToXml(input);
		File xmlFile = new File("UpdateCus.xml");
		String soapRequest = "";
		if (xmlFile.exists()) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				
				dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();
	            
	            // Change node value
	            NodeList nodeList = doc.getElementsByTagName(EInvoiceVNPTTerm.UPCUS);
	            Element element = null;
	            for (int i =0; i< nodeList.getLength(); i++) {
	            	element = (Element) nodeList.item(i);
	            	Node xmlCusDataNode = element.getElementsByTagName(EInvoiceVNPTTerm.UPCUS_XMLCUSDATA).item(0).getFirstChild();
	            	xmlCusDataNode.setNodeValue(nodeValue);	            	
	            }
	            
	            doc.getDocumentElement().normalize();
	            StringWriter sw = new StringWriter();
	            TransformerFactory tf = TransformerFactory.newInstance();
	            Transformer transformer = tf.newTransformer();
	            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	            transformer.transform(new DOMSource(doc), new StreamResult(sw));
	            soapRequest = sw.toString();
	            
	            String urlPath = "https://vpboxaydungadmindemo.vnpt-invoice.com.vn/PublishService.asmx";
	            String soapAction = "http://tempuri.org/UpdateCus";
	            HashMap<String, String> properties = new HashMap<String, String>();
	            properties.put("Content-Type","text/xml; charset=utf-8");
	            properties.put("SOAPAction", soapAction);
	            
				String result = callSoapApi("POST", urlPath, properties, soapRequest, soapAction);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	private static String convertJaxbToXml(CustomerModel input){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CustomerInputModel.class);

			CustomerInputModel cuInputModel = new CustomerInputModel();
			cuInputModel.setListCustomerModels(new ArrayList<CustomerModel>());
			cuInputModel.getListCustomerModels().add(input);
			
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			StringWriter sw = new StringWriter();
			marshaller.marshal(cuInputModel, sw);
			return "<![CDATA[\r\n" + sw.toString() + "\r\n" + "]]>";
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private String callSoapApi(String httpMethod, String urlPath, HashMap<String, String> properties,
			String soapRequest, String soapAction) {
		
		String response = "";

		HttpURLConnection conn = null;

		BufferedReader br = null;
		
		try {
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);
            
			// Set the appropriate HTTP parameters.
            byte[] soapRequestBytes = new byte[soapRequest.length()];
            soapRequestBytes = soapRequest.getBytes("UTF-8");
            conn.setRequestProperty("Content-Length", String.valueOf(soapRequestBytes.length));
            
            if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}    
            conn.getOutputStream().write(soapRequestBytes);
            conn.getOutputStream().close();
            
            // Read the response
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;

			StringBuilder sb = new StringBuilder();
			
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			response = sb.toString();
			
			// Get the response from the web service call
            Document document = parseXmlFile(response);
			
            conn.disconnect();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public String formatXML(String unformattedXml) {
		try {
		Document document = parseXmlFile(unformattedXml);
		OutputFormat format = new OutputFormat(document);
		format.setIndenting(true);
		format.setIndent(3);
		format.setOmitXMLDeclaration(true);
		Writer out = new StringWriter();
		XMLSerializer serializer = new XMLSerializer(out, format);
		serializer.serialize(document);
		return out.toString();
		} catch (IOException e) {
		throw new RuntimeException(e);
		}
		}
	
	private Document parseXmlFile(String in) {
		try {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(in));
		return db.parse(is);
		} catch (ParserConfigurationException e) {
		throw new RuntimeException(e);
		} catch (SAXException e) {
		throw new RuntimeException(e);
		} catch (IOException e) {
		throw new RuntimeException(e);
		}
		}

}
