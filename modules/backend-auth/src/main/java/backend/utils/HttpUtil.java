package backend.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	public static final String HEADER_X_FORWARDED_FOR_KEY = "X-Forwarded-For";
	public static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
	public static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
	public static final String HEADER_HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
	public static final String HEADER_HTTP_X_FORWARDER_FOR = "HTTP_X_FORWARDED_FOR";
	public static final String HEADER_UNKNOWN = "unknown";
	
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader(HEADER_X_FORWARDED_FOR_KEY);
        if (ip == null || ip.length() == 0 || HEADER_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || HEADER_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || HEADER_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_HTTP_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || HEADER_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HEADER_HTTP_X_FORWARDER_FOR);
        }
        if (ip == null || ip.length() == 0 || HEADER_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        return ip;
	}
}
