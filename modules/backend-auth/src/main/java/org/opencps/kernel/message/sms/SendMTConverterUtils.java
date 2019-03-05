package org.opencps.kernel.message.sms;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.opencps.auth.api.keys.Constants;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.kernel.prop.PropValues;
import org.opencps.kernel.template.SendMT;

public class SendMTConverterUtils {

	private static Log _log = LogFactoryUtil.getLog(SendMTConverterUtils.class);
	private static final String MT_SEQ = "111";
	private static final String MT_MOID = "12311";
	private static final String MT_MOSEQ = "12345";
	private static final String MT_CMD_CODE = "GAP1";
	private static final String MT_MSG_TYPE = "text";
	private static final String MT_TOTAL_SEG = "1";
	private static final String MT_SEQ_REF = "1";
	private static final String MT_CPID = "10682";
	private static final String MT_SERVICE_ID = "Service01";
	private static final String MT_PRO_RESULT = "1";

	public static String sendSMS(String body, String title, String toTelNo) {
		
		SendMT sendMT = new SendMT();
		sendMT.setMtseq(MT_SEQ);
		sendMT.setMoid(MT_MOID);
		sendMT.setMoseq(MT_MOSEQ);
		sendMT.setSrc(PropValues.SMS_SWITCH_BOARD);
		sendMT.setDest(toTelNo);
		sendMT.setCmdcode(MT_CMD_CODE);
		sendMT.setMsgbody(body);
		sendMT.setMsgtype(MT_MSG_TYPE);
		sendMT.setMsgtitle(title);
		sendMT.setMttotalseg(MT_TOTAL_SEG);
		sendMT.setMtseqref(MT_SEQ_REF);
		sendMT.setCpid(MT_CPID);
		sendMT.setServiceid(MT_SERVICE_ID);
		//add time send
		String strDateSend = APIDateTimeUtils.convertDateToString(new Date());
		sendMT.setReqtime(strDateSend);
		sendMT.setProcresult(MT_PRO_RESULT);
		sendMT.setUsername(PropValues.SMS_JAXRS_USERNAME);
		sendMT.setPassword(PropValues.SMS_JAXRS_SECRETKEY);

		String results = "khong the ket noi den server HDDT !!!!!";

		try {
//			String soapEndpointUrl = "http://210.245.81.131/AgentWS_VT/SMSAgentWS.asmx";
			String soapEndpointUrl = PropValues.SMS_END_POINT;
//			String soapEndpointUrl = config.getEndPointUrl();

			SOAPMessage message = convertSendMTToXML(sendMT);
			if (message != null) {
				//
				MimeHeaders mimeHeader = message.getMimeHeaders();
				mimeHeader.setHeader("SOAPACTION", "http://tempuri.org/SendMT");
				mimeHeader.setHeader("Content-Type", "text/xml; charset=utf-8");
				mimeHeader.setHeader("Proxy-Connection", "keep-alive");
	
//				ByteArrayOutputStream stream = null;
				try {
					// Create SOAP Connection
					SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
					SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	
					// Set timeout to call soap
					URL endpoint = new URL(null, soapEndpointUrl, new URLStreamHandler() {
						protected URLConnection openConnection(URL url) throws IOException {
							URL clone = new URL(url.toString());
							HttpURLConnection connection = (HttpURLConnection) clone.openConnection();
	
							connection.setRequestProperty("Content-Type", "text/xml");
							connection.setRequestProperty("Accept", "application/soap+xml, text/*");
	
							connection.setDoOutput(true);
							connection.setConnectTimeout(3 * 1000);
							connection.setReadTimeout(3 * 1000);
	
							return connection;
						}
					});
	
					// Send SOAP Message to SOAP Server
					SOAPMessage soapResponse = soapConnection.call(message, endpoint);
	
					SOAPBody bodyResponse = soapResponse.getSOAPBody();
					results = bodyResponse.getTextContent();
					//_log.info("results: "+results);
	
					// Print the SOAP Response
	//				_log.info("Response SOAP Message:");
	//				stream = new ByteArrayOutputStream();
	//				soapResponse.writeTo(stream);
	////				soapResponse.writeTo(System.out);
	//				results = new String(stream.toByteArray(), "utf-8");
	//				_log.info("results: "+results);
	//				//Convert xml to Object
	//				SendMTResponse sendMTResponse = convertXMLToSendMTResponse(results);
	//				if (sendMTResponse != null) {
	//					return sendMTResponse.getSendMTResult();
	//				}
	
					soapConnection.close();
				} catch (Exception e) {
					_log.debug(e);
					_log.info(
							"\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
				} finally {
//					if (stream != null) {
//						stream.close();
//					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	private static SOAPMessage convertSendMTToXML(SendMT sendMT) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(SendMT.class);
			MessageFactory mf = MessageFactory.newInstance();
			SOAPMessage message = mf.createMessage();

			SOAPBody body = message.getSOAPBody();

			SOAPHeader soapheader = message.getSOAPHeader();
			soapheader.detachNode();

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//process marshaller
			jaxbMarshaller.marshal(sendMT, body);
			//jaxbMarshaller.marshal(sendMT, System.out);
			message.saveChanges();

			// Process convert SOAP-ENV to soapenv
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope envelope = soapPart.getEnvelope();
			// SOAPHeader header = message.getSOAPHeader();
			SOAPBody bodyConvert = message.getSOAPBody();
			SOAPFault fault = bodyConvert.getFault();
			envelope.removeNamespaceDeclaration(envelope.getPrefix());
			envelope.addNamespaceDeclaration(Constants.PREFERRED_PREFIX, Constants.SOAP_ENV_NAMESPACE);
			envelope.addNamespaceDeclaration(Constants.PREFERRED_PREFIX_TEM, Constants.SOAP_ENV_NAMESPACE_TEM);
			envelope.setPrefix(Constants.PREFERRED_PREFIX);
			bodyConvert.setPrefix(Constants.PREFERRED_PREFIX);
			if (fault != null) {
				fault.setPrefix(Constants.PREFERRED_PREFIX);
			}
			message.saveChanges();

			return message;
		} catch (Exception e) {
			_log.error(e);
		}

		return null;
	}

}
