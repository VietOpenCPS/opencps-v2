package org.graphql.api.controller.impl;

import com.liferay.portal.kernel.util.PortalUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
//		InetAddress IP= InetAddress.getLocalHost();
//		System.out.println(IP.toString());
		System.out.println("Main.main()" + PortalUtil.getPortalLocalInetAddress(true));
		
	}

}
