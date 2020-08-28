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

package org.graphql.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnect;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectFlowState;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectServiceException;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.constants.OpenIdConnectWebKeys;
//import com.liferay.portal.security.sso.openid.connect.internal.exception.StrangersNotAllowedException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.graphql.security.sso.openid.OpenIdConnectServiceHandler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Edward C. Han
 */
@Component(
	immediate = true,
	property = {
		"before-filter=Auto Login Filter", "servlet-context-name=",
		"servlet-filter-name=OpenCPS SSO OpenId Connect Filter",
		"url-pattern=/c/opencps/login/openidconnect"
	},
	service = {Filter.class, OpenIdConnectFilter.class}
)
public class OpenIdConnectFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {
		
		_log.info("===OpenIdConnectSessionValidationFilter OpenIdConnectFilter===");

		long companyId = _portal.getCompanyId(request);

		return _openIdConnect.isEnabled(companyId);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected void processAuthenticationResponse(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {
		
		_log.info("====OpenIdConnectFilter====");

		HttpSession httpSession = httpServletRequest.getSession(false);

		if (httpSession == null) {
			return;
		}

		try {
			OpenIdConnectSession openIdConnectSession =
				(OpenIdConnectSession)httpSession.getAttribute(
					OpenIdConnectWebKeys.OPEN_ID_CONNECT_SESSION);

			if (openIdConnectSession == null) {
				return;
			}

			OpenIdConnectFlowState openIdConnectFlowState =
				openIdConnectSession.getOpenIdConnectFlowState();

			if (OpenIdConnectFlowState.INITIALIZED.equals(
					openIdConnectFlowState)) {

				throw new OpenIdConnectServiceException.AuthenticationException(
					"OpenId Connect authentication flow not started");
			}
			else if (OpenIdConnectFlowState.AUTH_COMPLETE.equals(
						openIdConnectFlowState) ||
					 OpenIdConnectFlowState.PORTAL_AUTH_COMPLETE.equals(
						 openIdConnectFlowState)) {

				if (_log.isDebugEnabled()) {
					_log.debug("User has already been logged in");
				}
			}
			else {
				_openIdConnectServiceHandler.processAuthenticationResponse(
					httpServletRequest, httpServletResponse);

				String actionURL = (String)httpSession.getAttribute(
					OpenIdConnectWebKeys.OPEN_ID_CONNECT_ACTION_URL);

				if (actionURL != null) {
					httpServletResponse.sendRedirect(actionURL);
				}
			}
		}
//		catch (Exception e) {
//
//			Class<?> clazz = e.getClass();
//
//			httpSession.removeAttribute(
//				OpenIdConnectWebKeys.OPEN_ID_CONNECT_SESSION);
//
//			sendError(
//				clazz.getSimpleName(), httpServletRequest, httpServletResponse);
//		}
		catch (Exception e) {
			_log.error("Unable to process the OpenID login", e);

			httpSession.removeAttribute(
				OpenIdConnectWebKeys.OPEN_ID_CONNECT_SESSION);

			_portal.sendError(e, httpServletRequest, httpServletResponse);
		}
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		processAuthenticationResponse(request, response);

		processFilter(
			OpenIdConnectFilter.class.getName(), request, response,
			filterChain);
	}

	protected void sendError(
			String error, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		String actionURL = (String)session.getAttribute(
			OpenIdConnectWebKeys.OPEN_ID_CONNECT_ACTION_URL);

		if (actionURL == null) {
			return;
		}

		actionURL = _http.addParameter(actionURL, "error", error);

		response.sendRedirect(actionURL);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OpenIdConnectFilter.class);

	@Reference
	private Http _http;

	@Reference
	private OpenIdConnect _openIdConnect;

	@Reference
	private OpenIdConnectServiceHandler _openIdConnectServiceHandler;

	@Reference
	private Portal _portal;

}