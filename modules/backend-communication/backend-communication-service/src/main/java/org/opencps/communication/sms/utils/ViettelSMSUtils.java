package org.opencps.communication.sms.utils;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import ws.bulkSms.impl.CcApi_PortType;
import ws.bulkSms.impl.CcApi_ServiceLocator;


public class ViettelSMSUtils {
	public static void main(String[] args) throws RemoteException, ServiceException {
		System.out.println(sendSMS("", "", ""));
	}
	
	public static String sendSMS(String body, String title, String toTelNo) throws RemoteException, ServiceException {
		CcApi_ServiceLocator locator = new CcApi_ServiceLocator();
		CcApi_PortType portType = locator.getCcApiPort();
		
		return portType.getIp();
	}
}
