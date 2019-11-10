/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package backend.admin.config.whiteboard;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Binhth
 */
@Component(
	immediate = true,
	property = {
		"servlet-context-name=",
		"servlet-filter-name=Rest Auth Filter",
		"url-pattern=/o/v1/socket/*",
		"url-pattern=/o/v1/opencps/users/*"
		,
		"url-pattern=/o/v1/opencps/login",
		"url-pattern=/o/rest/v2/*",
		"url-pattern=/o/rest/v2_1/*"
	}, service = Filter.class
)
public class RestAuthFilter implements Filter {
	Log _log = LogFactoryUtil.getLog(RestAuthFilter.class);
	
	public final static String P_AUTH = "Token";
	public final static String USER_ID = "USER_ID";
	public final static String AUTHORIZATION = "Authorization";
	final static String[] IGNORE_PATTERN = new String[] { "/o/rest/v2/serviceinfos/\\S+/filetemplates/\\S+", "/o/rest/v2/barcode", "/o/rest/v2/qrcode", "/o/rest/v2/dossiers/\\S+/files/\\S+", "/o/rest/v2/dossiers/\\S+", /*"/o/rest/v2/dictcollections/GOVERNMENT_AGENCY/dictitems", "/o/rest/v2/dictcollections/SERVICE_DOMAIN/dictitems",*/ "/o/rest/v2/serviceinfos", "/o/rest/v2/postal/votings/statistic", "/o/rest/v2/postal/invoice", "/o/rest/v2/sms/inet", "/o/rest/v2/sms/zaloid", "/o/rest/v2/jaspers/preview" };
	public final static String OPENCPS_GZIP_FILTER = "org.opencps.servlet.filters.GZipFilter";
	
	final static String[] DISALLOW_METHODS = new String[] { "OPTIONS" };
	final static String[] DISALLOW_METHODS_IGNORE_PATTERN = new String[] { "/o/rest/v2/faq" };
	public static final String OPENCPS_ALLOW_CORS_IPS = "org.opencps.allow.cors.ips";
	
	private static final String hostExtractorRegexString = "(?:https?://)?(?:www\\.)?(.+\\.)(com|au\\.uk|co\\.in|be|in|uk|org\\.in|org|net|edu|gov\\.vn|mil)";
	private static final Pattern hostExtractorRegexPattern = Pattern.compile(hostExtractorRegexString);
	private static final String CONTENT_TYPE = "application/json; charset=utf-8";
	private static final String ENCODING = "UTF-8";
	private static final String ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	private static final String ALLOW_HEADER = "Access-Control-Allow-Headers";
	private static final String ALLOW_METHOD = "Access-Control-Allow-Methods";
	private static final String ALLOW_CREDENTIAL = "Access-Control-Allow-Credentials";
	private static final String METHOD_ACCESS = "DELETE,POST,GET,PUT,HEAD";
	private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
	private static final String HEADER_ORIGIN = "Origin";
	private static final String LOCAL_ACCESSS = "localaccess";
	private static final String USER_REQUEST_ID = "userid";

	public static String getDomainName(String url){
	    if (url == null) return null;
	    url = url.trim();
	    Matcher m = hostExtractorRegexPattern.matcher(url);
	    if(m.find() && m.groupCount() == 2) {
	        return m.group(1) + m.group(2);
	    }
	    else {
	        return null;
	    }
	}
		
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		_log.debug("RestAuthFilter.doFilter()");
		
		String allowIps = PropsUtil.get(OPENCPS_ALLOW_CORS_IPS);
		List<String> lstIps = new ArrayList<>();
		String[] ipSplit = allowIps != null ? allowIps.split(StringPool.COMMA) : (new String[] {});
		for (String ip : ipSplit) {
			lstIps.add(ip);
		}
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

		String pAuth = httpRequest.getHeader(P_AUTH);
		String path = httpRequest.getRequestURI();
		boolean exclude = false;
		for (String pattern : IGNORE_PATTERN) {
			if (path.matches(pattern)) {
				exclude = true;
				break;
			}
		}
		
		String method = httpRequest.getMethod();
		String ipAddress = httpRequest.getHeader(X_FORWARDED_FOR);  
		if (ipAddress == null) {  
		   ipAddress = httpRequest.getRemoteAddr();  
		} 
		String origin = httpRequest.getHeader(HEADER_ORIGIN);
		
//		_log.debug("Request IP: " + ipAddress);
//		_log.debug("Allow ips: " + allowIps);
//		_log.debug("Origin: " + origin);
		String domain = StringPool.BLANK;
		if (origin != null) {
			domain = getDomainName(origin);			
		}
		_log.debug("Domain: " + domain);
		
		for (String disallow : DISALLOW_METHODS) {
			if (disallow.equals(method)) {
				boolean excludeMethod = false;
				for (String pattern : DISALLOW_METHODS_IGNORE_PATTERN) {
					if (path.contains(pattern)) {
						excludeMethod = true;
						break;
					}
				}
				if (!excludeMethod) {
					authFailure(servletResponse);
					return;					
				}
				else if (lstIps.contains(ipAddress) || lstIps.contains(domain)) {
					_log.debug("CORS Ok");
					
					long userId = 0;
					Object userObj = httpRequest.getSession(true).getAttribute(USER_ID);
					if (userObj != null) 
						userId = (Long)userObj;
					authOKCORS(servletRequest, servletResponse, filterChain, userId);
					return;
				}
			}
		}
		if (Validator.isNotNull(httpRequest.getParameter(P_AUTH))) {
			pAuth = httpRequest.getParameter(P_AUTH);
		}
		if (exclude || AuthTokenUtil.getToken(httpRequest).equals(pAuth) || (Validator.isNotNull(httpRequest.getHeader(LOCAL_ACCESSS)) ? httpRequest.getHeader(LOCAL_ACCESSS).equals(pAuth) : false) ) {
			Object userObj = httpRequest.getSession(true).getAttribute(USER_ID);
			//System.out.println("RestAuthFilter.doFilter()" + userObj);
			if (Validator.isNotNull(userObj) || exclude) {
				httpRequest.setAttribute(USER_ID, userObj);
				if (!exclude) {
					authOK(servletRequest, servletResponse, filterChain, (Long) userObj);
				}
				else {
					authOK(servletRequest, servletResponse, filterChain, 0);
				}
			} else {
				long sockId = Validator.isNotNull(httpRequest.getHeader(USER_REQUEST_ID)) ? Long.valueOf(httpRequest.getHeader(USER_REQUEST_ID)) : 0;
				httpRequest.setAttribute(USER_ID, sockId);
				authOK(servletRequest, servletResponse, filterChain, sockId);
			}
		
		} else {
			
			Enumeration<String> headerNames = httpRequest.getHeaderNames();
			
			boolean isBasic = false;
			String strBasic = StringPool.BLANK;
			
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
		            String key = (String) headerNames.nextElement();
		            String value = httpRequest.getHeader(key);
		            if (key.trim().equalsIgnoreCase(AUTHORIZATION)) {
		            	strBasic = value;
		            	isBasic = true;
		            	break;
		            }
		        }
			}
			if (isBasic) {

				try {
					// Get encoded user and password, comes after "BASIC "
					String userpassEncoded = strBasic.substring(6);
					String decodetoken = new String(Base64.decode(userpassEncoded), StringPool.UTF8);
					String account[] = decodetoken.split(StringPool.COLON);

					String email = account[0];
					String password = account[1];

					long userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(httpRequest, email, password,
							CompanyConstants.AUTH_TYPE_EA);

					authOK(servletRequest, servletResponse, filterChain, userId);

				} catch (PortalException e) {
					_log.debug(e);
					authFailure(servletResponse);
				}

			} else {
				authFailure(servletResponse);
			}
			
		}

	}

	private void authOKCORS(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain,
			long userId) throws IOException, ServletException {
		servletRequest.setAttribute(USER_ID, userId);
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		httpResponse.addHeader(ALLOW_ORIGIN, StringPool.STAR);
		httpResponse.addHeader(ALLOW_HEADER, StringPool.STAR);
		httpResponse.addHeader(ALLOW_METHOD, METHOD_ACCESS);
		httpResponse.addHeader(ALLOW_CREDENTIAL, StringPool.TRUE);

		filterChain.doFilter(servletRequest, httpResponse);
	}
	
	private void authOK(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain,
			long userId) throws IOException, ServletException {
		servletRequest.setAttribute(USER_ID, userId);
	    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
	    	filterChain.doFilter(servletRequest, httpResponse);
	}

	private void authFailure(ServletResponse servletResponse) throws IOException {
		servletResponse.setCharacterEncoding(ENCODING);
		servletResponse.setContentType(CONTENT_TYPE);
		
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(CONTENT_TYPE);
		
		PrintWriter out = response.getWriter();
		
		OpenCPSErrorDetails error = new OpenCPSErrorDetails(new Date(), "permission denied", StringPool.BLANK);
		
		out.println(error.toString());
		out.flush();
		out.close();
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

}