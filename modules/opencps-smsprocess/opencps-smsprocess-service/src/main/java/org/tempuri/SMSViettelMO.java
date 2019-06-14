
package org.tempuri;

import java.text.SimpleDateFormat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = "jaxws=true", service = SMSViettelMO.class)
@WebService(name = "VTMOWebServicePortType", serviceName = "VTMOWebService")
@SOAPBinding(style = Style.DOCUMENT)
public class SMSViettelMO {
	
	/**
	@WebMethod()
	@WebResult()
	public MessageReceiverResponseModel messageReceiver(
		@WebParam(targetNamespace="", name = "messageReceiver") MessageReceiverModel receiveMORequest) {

		System.out.println(receiveMORequest.getRequestID());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getInfo());

		MessageReceiverResponseModel output = new MessageReceiverResponseModel();
		output.setCommandCode(1);
		output.setMessageType(0);;
		output.setMessageContent(
			"HELLO YOU ARE OK? ok NONOO n onon onNO=======" + receiveMORequest.getRequestID());;
		return output;
	}
	*/
	@WebMethod()
	@WebResult()
	public MessageReceiverResponseModel messageReceiver(
		@WebParam(name = "RequestID") String RequestID,
		@WebParam(name = "UserID") String UserID,
		@WebParam(name = "ReceiverID") String ReceiverID,
		@WebParam(name = "ServiceID") String ServiceID,
		@WebParam(name = "CommandCode") String CommandCode,
		@WebParam(name = "Info") String Info,
		@WebParam(name = "ReceiveTime") String ReceiveTime) {

		MessageReceiverModel receiveMORequest = new MessageReceiverModel();
		receiveMORequest.setRequestID(RequestID);
		System.out.println(receiveMORequest.getRequestID());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getReceiveTime());
		System.out.println(receiveMORequest.getInfo());

		MessageReceiverResponseModel output = new MessageReceiverResponseModel();
		output.setCommandCode(1);
		output.setMessageType(0);;
		output.setMessageContent(
			"HELLO YOU ARE OK? ok NONOO n onon onNO=======" + RequestID);;
		return output;
	}

	private SimpleDateFormat _dateFormat =
		new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

}