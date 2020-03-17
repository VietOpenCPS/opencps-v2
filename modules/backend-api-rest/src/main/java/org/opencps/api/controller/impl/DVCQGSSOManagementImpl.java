package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DVCQGSSOManagement;
import org.opencps.usermgt.action.impl.DVCQGSSOActionImpl;

public class DVCQGSSOManagementImpl implements DVCQGSSOManagement {
	private static final Log _log = LogFactoryUtil.getLog(DVCQGSSOManagementImpl.class);

	@Override
	public Response checkAuth(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, int vnconnect,
			String currentURL) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();
		String endpoint = action.checkAuth(user, groupId, request, serviceContext, vnconnect, currentURL);
		HttpSession session = request.getSession();
		session.setAttribute("_GROUP_ID", groupId);
		session.setAttribute("_CURRENT_URL", currentURL);
		session.setMaxInactiveInterval(36000);

		return Response.status(200).entity(endpoint).build();
	}

	@Override
	public Response getUserInfo(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String authToken, String state) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();
		JSONObject result = action.getUserInfo(user, groupId, request, serviceContext, authToken, state);

		return Response.status(200).entity(result.toJSONString()).build();
	}

	@Override
	public Response doAuth(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String userinfo) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(userinfo)) {
			DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();

			try {
				result = action.doAuth(user, request, response, serviceContext, userinfo);
				return Response.status(200).entity(result.toJSONString()).build();
			} catch (Exception e) {
				_log.error(e);
				result.put("message", "authentication failed");
				result.put("description", "authentication failed");
				return Response.status(401).entity(result.toJSONString()).build();
			}

		} else {
			result.put("message", "authentication failed");
			result.put("description", "can't get user info");
			return Response.status(401).entity(result.toJSONString()).build();
		}

	}

	@Override
	public Response getAuthURL(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String state, String redirectURL) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();

		String endpoint = action.getAuthURL(user, groupId, request, serviceContext, state, redirectURL);

		return Response.status(200).entity(endpoint).build();
	}

}
