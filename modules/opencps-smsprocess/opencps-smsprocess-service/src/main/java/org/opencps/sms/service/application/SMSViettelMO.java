//
//package org.opencps.sms.service.application;
//
//import java.text.SimpleDateFormat;
//
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebResult;
//import javax.jws.WebService;
//import javax.jws.soap.SOAPBinding;
//import javax.jws.soap.SOAPBinding.Style;
//
//import org.osgi.service.component.annotations.Component;
//import org.tempuri.MessageReceiver;
//import org.tempuri.MessageReceiverResponse;
//
//@Component(immediate = true, property = "jaxws=true", service = SMSViettelMO.class)
//@WebService(name = "VTMOWebServicePortType", serviceName = "VTMOWebService")
//@SOAPBinding(style = Style.DOCUMENT)
//public class SMSViettelMO {
//
//	@WebMethod()
//	@WebResult()
//	public MessageReceiverResponse messageReceiver(
//		@WebParam(name = "messageReceiver") MessageReceiver receiveMORequest) {
//
//		System.out.println(receiveMORequest.getRequestID());
//		System.out.println(receiveMORequest.getReceiveTime());
//		System.out.println(receiveMORequest.getReceiveTime());
//		System.out.println(receiveMORequest.getReceiveTime());
//		System.out.println(receiveMORequest.getReceiveTime());
//		System.out.println(receiveMORequest.getReceiveTime());
//		System.out.println(receiveMORequest.getReceiveTime());
//		System.out.println(receiveMORequest.getInfo());
//
//		MessageReceiverResponse output = new MessageReceiverResponse();
//		output.setCommandCode(1);
//		output.setMessageType(0);;
//		output.setMessageContent("HELLO YOU ARE OK? ok NONOONO");;
//		return output;
//	}
//
//	private SimpleDateFormat _dateFormat =
//		new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//
//}
