package org.opencps.communication.sms.utils;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import ws.bulkSms.impl.CcApi_PortType;
import ws.bulkSms.impl.CcApi_ServiceLocator;
import ws.bulkSms.impl.Result;


public class ViettelSMSUtils {
	public static Result sendSMS(String body, String title, String toTelNo) throws RemoteException, ServiceException {
		CcApi_ServiceLocator locator = new CcApi_ServiceLocator();
		CcApi_PortType portType = locator.getCcApiPort();
		
		Result result = portType.wsCpMt("viettelmcdt", "789456a@#123", 
				"VIETTELMCDT", "1", "84976969454", toTelNo, "ViettelMCDT", "bulksms", body, "F");
		
		return result;
	}
}
