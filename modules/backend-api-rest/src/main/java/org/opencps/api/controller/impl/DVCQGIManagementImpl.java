package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DVCQGIManagement;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;

public class DVCQGIManagementImpl implements DVCQGIManagement {

	private Log _log = LogFactoryUtil.getLog(DVCQGIManagementImpl.class);

	@Override
	public Response doSyncDossier(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String strDossierId,
			String isUpdating) {

		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			JSONObject result = actionImpl.syncDossier(user, groupId, serviceContext, strDossierId, isUpdating);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response doSyncDossierStatus(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String strDossierId) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			JSONObject result = actionImpl.syncDossierStatus(user, groupId, serviceContext, strDossierId);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response doConfirm(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		String result = actionImpl.getAccessToken(user, serviceContext);

		return Response.status(200).entity(result).build();
	}

	@Override
	public Response getSharingDictCollection(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		
		try {
			_log.info("Ton ngo khong da dao choi o day1.");
			JSONObject result = actionImpl.getSharingDictCollection(user, serviceContext,
					JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response getSharingData(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			JSONObject result = actionImpl.getSharingData(user, serviceContext, JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doMappingServiceInfo(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			JSONObject result = actionImpl.mappingServiceInfo(user, groupId, serviceContext, serviceCode,
					serviceCodeDVCQG);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response doRemoveMappingServiceInfo(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext,
			String serviceCode) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			return Response.status(200)
					.entity(String
							.valueOf(actionImpl.removeMappingServiceInfo(user, groupId, serviceContext, serviceCode)))
					.build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response doSyncServiceInfo(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String serviceCodes) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			JSONObject result = actionImpl.syncServiceInfo(user, groupId, serviceContext, serviceCodes);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response getSharingQA(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		
		return null;
	}
}
