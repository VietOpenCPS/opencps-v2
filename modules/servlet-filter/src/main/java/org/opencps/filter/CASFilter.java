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

package org.opencps.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Participates in every login and logout that triggers an HTTP request to
 * Liferay Portal.
 *
 * <p>
 * This class checks if the HTTP session attribute <code>CAS_FORCE_LOGOUT</code>
 * is set by CASAutoLogin and, if so, redirects the browser to the configured
 * CAS Logout URL.
 * </p>
 *
 * <p>
 * Next, if the session attribute <code>CAS_LOGIN</code> has not already been
 * set and no ticket parameter is received via the HTTP servlet request, the CAS
 * server login URL is constructed based on the configuration of the Login URL,
 * the Server name, and the Service URL and the browser is redirected to this
 * URL. If a ticket parameter was received, it will be validated.
 * </p>
 *
 * <p>
 * Validation includes sending a SAML request containing the ticket to the CAS
 * server, and in return receiving an assertion of user attributes. However,
 * only the principal attribute is used and it is set as the session attribute
 * <code>CAS_LOGIN</code> as mentioned earlier. It is important that the CAS
 * server issues a principal of the same type that the portal instance is
 * configured to use (e.g., screen name versus email address).
 * </p>
 *
 * @author Michael Young
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Tina Tian
 * @author Zsolt Balogh
 */
@Component(configurationPid = "com.liferay.portal.security.sso.cas.configuration.CASConfiguration", immediate = true, property = {
		"before-filter=Auto Login Filter", "dispatcher=FORWARD", "dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=SSO CAS Filter", "url-pattern=/c/portal/login",
		"url-pattern=/c/portal/logout" }, service = Filter.class)
public class CASFilter extends BaseFilter {
	public static final String CAS_FORCE_LOGOUT = "CAS_FORCE_LOGOUT";

	public static final String CAS_LOGIN = "CAS_LOGIN";

	public static final String CAS_NO_SUCH_USER_EXCEPTION = "CAS_NO_SUCH_USER_EXCEPTION";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException {

		try {
			super.doFilter(servletRequest, servletResponse, filterChain);
		} catch (Exception e) {
			_log.error(e);
			String redirectURL = PropsUtil.get("portal.cas.login.error.redirect");
						
			if (Validator.isNull(redirectURL)) {
				HttpServletRequest request = (HttpServletRequest) servletRequest;
				redirectURL = request.getServletPath();
			}
			
			_log.info("Login error redirect: " + redirectURL);
			
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendRedirect(redirectURL);

		}
	}

	@Override
	protected Log getLog() {

		return null;
	}

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;

	private static final Log _log = LogFactoryUtil.getLog(CASFilter.class);

}