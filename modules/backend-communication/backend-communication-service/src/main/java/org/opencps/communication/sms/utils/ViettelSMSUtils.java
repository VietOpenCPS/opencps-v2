package org.opencps.communication.sms.utils;

import com.viettel.smsbrand.CpBalance;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import ws.bulkSms.impl.CcApi_PortType;
import ws.bulkSms.impl.CcApi_ServiceLocator;
import ws.bulkSms.impl.Result;


public class ViettelSMSUtils {
	public static void main(String[] args) throws RemoteException, ServiceException {
		System.out.println(sendSMS("", "", ""));
	}
	
	public static String sendSMS(String body, String title, String toTelNo) throws RemoteException, ServiceException {
		CcApi_ServiceLocator locator = new CcApi_ServiceLocator();
		CcApi_PortType portType = locator.getCcApiPort();
		
//		Result result = portType.wsCpMt("viettelmcdt", "789456a@#123", 
//				"VIETTELMCDT", "1", "84976969454", "84916676884", "ViettelMCDT", "bulksms", "Test SMS", "F");
		
		CpBalance result = portType.checkBalance("viettelmcdt", "789456a@#123", 
				"VIETTELMCDT");
		return result.getErrDesc();
	}
}
