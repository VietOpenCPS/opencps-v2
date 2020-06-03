package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DVCQGSSOManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.usermgt.action.impl.DVCQGSSOActionImpl;

public class DVCQGSSOManagementImpl implements DVCQGSSOManagement {
	private static final Log _log = LogFactoryUtil.getLog(DVCQGSSOManagementImpl.class);

	@Override
	public Response checkAuth(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, int vnconnect,
			String currentURL) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();
		String endpoint = action.checkAuth(user, groupId, request, serviceContext, vnconnect, currentURL);
		HttpSession session = request.getSession();
		session.setAttribute(ConstantUtils.DVCQG_SSO_GROUP_ID_KEY, groupId);
		session.setAttribute(ConstantUtils.DVCQG_SSO_CURRENT_URL_KEY, currentURL);
		session.setMaxInactiveInterval(36000);

		return Response.status(HttpURLConnection.HTTP_OK).entity(endpoint).build();
	}

	@Override
	public Response getUserInfo(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String authToken, String state) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();
		JSONObject result = action.getUserInfo(user, groupId, request, serviceContext, authToken, state);

		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}

	@Override
	public Response doAuth(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String userinfo) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(userinfo)) {
			DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();

			try {
				result = action.doAuth(user, request, response, serviceContext, userinfo);
				return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
			} catch (Exception e) {
				_log.error(e);
				result.put(ConstantUtils.API_JSON_MESSAGE,
						MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_AUTHENTICATEDFAILURE));
				result.put(ConstantUtils.API_JSON_DESCRIPTION,
						MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_AUTHENTICATEDFAILURE));
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(result.toJSONString()).build();
			}

		} else {
			result.put(ConstantUtils.API_JSON_MESSAGE,
					MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_AUTHENTICATEDFAILURE));
			result.put(ConstantUtils.API_JSON_DESCRIPTION,
					MessageUtil.getMessage(ConstantUtils.DVCQG_SSO_CANNOTGETUSERINFO));
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(result.toJSONString()).build();
		}

	}

	@Override
	public Response getAuthURL(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String state,
			String redirectURL) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();

		String endpoint = action.getAuthURL(user, groupId, request, serviceContext, state, redirectURL);

		return Response.status(HttpURLConnection.HTTP_OK).entity(endpoint).build();
	}

	@Override
	public Response doChangeEmail(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String oldEmail, String newEmail,
			String techId) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			result = action.doChangeEmail(user, company.getCompanyId(), groupId, request, response, serviceContext,
					oldEmail, newEmail, techId);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			result.put("message", "authentication failed");
			result.put("description", "authentication failed");
			return Response.status(401).entity(result.toJSONString()).build();
		}
	}

	@Override
	public Response doLogout(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String accessToken,
			String redirectURL, String state) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DVCQGSSOActionImpl action = new DVCQGSSOActionImpl();

		String data = action.getLogout(user, groupId, request, serviceContext, accessToken, redirectURL, state);

		return Response.status(HttpURLConnection.HTTP_OK).entity(data).build();
	}
}
