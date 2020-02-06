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

import java.net.HttpURLConnection;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DVCQGIManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;

public class DVCQGIManagementImpl implements DVCQGIManagement {

	private Log _log = LogFactoryUtil.getLog(DVCQGIManagementImpl.class);

	@Override
	public Response doSyncDossier(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String strDossierId,
			String isUpdating) {
		
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			JSONObject result = actionImpl.syncDossier(user, groupId, serviceContext, strDossierId, isUpdating);
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_JSON_ERROR)).build();
		}
	}

	@Override
	public Response doSyncDossierStatus(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String strDossierId) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			JSONObject result = actionImpl.syncDossierStatus(user, groupId, serviceContext, strDossierId);
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_JSON_ERROR)).build();
		}
	}

	@Override
	public Response doConfirm(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		String result = actionImpl.getAccessToken(user, serviceContext);

		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	@Override
	public Response getSharingDictCollection(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
//			_log.info("Ton ngo khong da dao choi o day1.");
			JSONObject result = actionImpl.getSharingDictCollection(user, serviceContext,
					JSONFactoryUtil.createJSONObject(body));
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_REQUESTBODYINCORRECT)).build();
		}
	}

	@Override
	public Response getSharingData(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			JSONObject result = actionImpl.getSharingData(user, serviceContext, JSONFactoryUtil.createJSONObject(body));
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_REQUESTBODYINCORRECT)).build();
		}
	}
}
