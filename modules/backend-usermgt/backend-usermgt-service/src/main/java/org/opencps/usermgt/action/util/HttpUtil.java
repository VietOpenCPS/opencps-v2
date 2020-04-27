package org.opencps.usermgt.action.util;

import javax.servlet.http.HttpServletRequest;

import org.opencps.usermgt.constants.CommonTerm;

public class HttpUtil {
	public static String getPublicIP(HttpServletRequest request) {
		String clientIP = request.getHeader(CommonTerm.LOGIN_ACTION_X_FORWARDED_FOR);
		if (clientIP == null) {
			clientIP = request.getRemoteAddr();
		}
		return clientIP;
	}
}
