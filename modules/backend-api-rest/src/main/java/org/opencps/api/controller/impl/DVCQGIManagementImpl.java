package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
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
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;

public class DVCQGIManagementImpl implements DVCQGIManagement {

	private Log _log = LogFactoryUtil.getLog(DVCQGIManagementImpl.class);

	@Override
	public Response doSyncDossier(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String strDossierId,
			String isUpdating) {

		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {

			JSONObject result = actionImpl.syncDossier(user, groupId, serviceContext, strDossierId, isUpdating, request);
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
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {

			JSONObject result = actionImpl.syncDossierStatus(user, groupId, serviceContext, strDossierId, request);
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
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		String result = actionImpl.getAccessToken(user, request, response,  serviceContext);

		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	@Override
	public Response getSharingDictCollection(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			_log.info("Ton ngo khong da dao choi o day.");
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
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			JSONObject result = actionImpl.getSharingData(user, serviceContext, JSONFactoryUtil.createJSONObject(body));
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_REQUESTBODYINCORRECT)).build();
		}
	}

	@Override
	public Response doMappingServiceInfo(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String serviceCode,
			String serviceCodeDVCQG, String serviceNameDVCQG) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {

			JSONObject result = actionImpl.mappingServiceInfo(user, groupId, serviceContext, serviceCode,
					serviceCodeDVCQG, serviceNameDVCQG);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response doRemoveMappingServiceInfo(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, long id) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			return Response.status(200)
					.entity(String.valueOf(actionImpl.removeMappingServiceInfo(user, groupId, serviceContext, id)))
					.build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response doSyncServiceInfo(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String serviceCodes,
			String type) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {

			JSONObject result = actionImpl.syncServiceInfo(user, groupId, serviceContext, serviceCodes, type);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("error").build();
		}
	}

	@Override
	public Response getSharingQA(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {

		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			JSONObject result = actionImpl.getSharingQA(user, serviceContext, JSONFactoryUtil.createJSONObject(body));
			//kiem tra da dong bo hay chua
			JSONArray data = result.getJSONArray("result");
			if (data != null) {
				for (int i = 0; i < data.length(); i++) {
					String hoidapid = data.getJSONObject(i).getString("HOIDAPID");
					Question question = QuestionLocalServiceUtil.fetchByG_CN_CPK(groupId, "dvcqg_question", hoidapid);
					if (question == null) {
						data.getJSONObject(i).put("SYNC", false);
					} else {
						data.getJSONObject(i).put("SYNC", true);
					}
				}
			}
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doSyncSharingQA(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			JSONObject result = actionImpl.doSyncSharingQA(user, serviceContext,
					JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doSyncServiceDomain(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			JSONObject result = actionImpl.doSyncServiceDomain(user, serviceContext,
					JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doSyncGovernmentAgency(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		try {
			JSONObject result = actionImpl.doSyncGovernmentAgency(user, serviceContext,
					JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doSyncServiceAdministration(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			JSONObject result = actionImpl.doSyncServiceAdministration(user, serviceContext,
					JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response getServiceInfoDVCQG(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String serviceCode,
			String serviceName) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {
			JSONArray data = actionImpl.getServiceInfoDVCQG(user, groupId, serviceContext, serviceCode, serviceName);
			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put("total", data.length());
			result.put("data", data);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response getServiceInfoDVCQGDetail(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String code) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setCompanyId(company.getCompanyId());
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		try {

			JSONObject result = actionImpl.getServiceInfoDVCQGDetail(user, groupId, serviceContext, code);

			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doCreateDossierFromDVCQG(HttpServletRequest request, HttpServletResponse response,
			HttpHeaders header, Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		serviceContext.setScopeGroupId(groupId);
		
		serviceContext.setCompanyId(company.getCompanyId());
		
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		
		_log.debug("doCreateDossierFromDVCQG API " + body);
		
		try {
			JSONObject result = actionImpl.doCreateDossierFromDVCQG(company, user, groupId, serviceContext, JSONFactoryUtil.createJSONObject(body));
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}

	@Override
	public Response doSyncServiceConfig(HttpServletRequest request, HttpServletResponse response, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String body) {
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		serviceContext.setScopeGroupId(groupId);
		
		serviceContext.setCompanyId(company.getCompanyId());
		
		DVCQGIntegrationActionImpl actionImpl = new DVCQGIntegrationActionImpl();
		
		_log.debug("doSyncServiceConfig " + body);
		
		try {
			JSONObject result = actionImpl.doSyncServiceConfig(user, groupId, body, serviceContext);
			return Response.status(200).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity("request body incorrect").build();
		}
	}
}
