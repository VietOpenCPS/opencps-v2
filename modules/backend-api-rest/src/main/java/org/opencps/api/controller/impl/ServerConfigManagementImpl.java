package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.ServerConfigManagement;
import org.opencps.api.controller.util.ServerConfigUtils;
import org.opencps.api.serverconfig.model.ServerConfigDetailModel;
import org.opencps.api.serverconfig.model.ServerConfigInputModel;
import org.opencps.api.serverconfig.model.ServerConfigResultsModel;
import org.opencps.api.serverconfig.model.ServerConfigSearchModel;
import org.opencps.api.serverconfig.model.ServerConfigSingleInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ServerConfigManagementImpl implements ServerConfigManagement {
	Log _log = LogFactoryUtil.getLog(ServerConfigManagementImpl.class);
	
	@Override
	public Response getServerConfigs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServerConfigSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			List<ServerConfig> configs = ServerConfigLocalServiceUtil.getGroupId(groupId);
			int count = configs.size();

			ServerConfigResultsModel results = new ServerConfigResultsModel();

			results.setTotal(count);
			results.getData().addAll(ServerConfigUtils.mappingTOData(configs));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addServerConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServerConfigInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String serverNo = HtmlUtil.escape(input.getServerNo());
			String serverName = HtmlUtil.escape(input.getServerName());
			String protocol = HtmlUtil.escape(input.getProtocol());
//			String configs = HtmlUtil.escape(input.getConfigs());
						
			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, 0l, govAgencyCode,
					serverNo, serverName, protocol, StringPool.BLANK, new Date(),
					serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getServerConfigDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		try {
			boolean checkAuth = true;
			long serverId = GetterUtil.getLong(id);
			ServerConfig config = null;
			if (serverId == 0) {
				config = ServerConfigLocalServiceUtil.getByCode(groupId,  id);
				if (config != null && ReadFilePropertiesUtils.get(ConstantUtils.MULTIMEDIA).equalsIgnoreCase(config.getProtocol())) {
					checkAuth = false;
				}
			}

			if (checkAuth && !auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (serverId != 0) {
				config = ServerConfigLocalServiceUtil.fetchServerConfig(serverId);

				if (Validator.isNull(config)) {
					config = ServerConfigLocalServiceUtil.getByCode(groupId, id);
				}

			}

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateServerConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServerConfigInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String serverNo = HtmlUtil.escape(input.getServerNo());
			String serverName = HtmlUtil.escape(input.getServerName());
			String protocol = HtmlUtil.escape(input.getProtocol());
//			String configs = HtmlUtil.escape(input.getConfigs());
			String configs = input.getConfigs();
			
			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, id, govAgencyCode,
					serverNo, serverName, protocol, configs, new Date(),
					serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeServerConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServerConfig config = ServerConfigLocalServiceUtil.deleteServerConfig(id);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ServerConfig config = ServerConfigLocalServiceUtil.getServerConfig(id);

			String configStr = config.getConfigs();

			ServerConfigSingleInputModel result = new ServerConfigSingleInputModel();

			result.setValue(configStr);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, ServerConfigSingleInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			String value = HtmlUtil.escape(input.getValue());

			ServerConfig oldConfig = ServerConfigLocalServiceUtil.getServerConfig(id);

			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, id,
					oldConfig.getGovAgencyCode(), oldConfig.getServerNo(), oldConfig.getServerName(),
					oldConfig.getProtocol(), value, new Date(), serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServerConfigSingleInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServerConfig.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}
			
			String value = HtmlUtil.escape(input.getValue());
			ServerConfig oldConfig = ServerConfigLocalServiceUtil.getServerConfig(id);

			ServerConfig config = ServerConfigLocalServiceUtil.updateServerConfig(groupId, id,
					oldConfig.getGovAgencyCode(), oldConfig.getServerNo(), oldConfig.getServerName(),
					oldConfig.getProtocol(), value, new Date(), serviceContext);

			ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getBasicServerConfigs(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServerConfigSearchModel query) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			List<ServerConfig> configs = ServerConfigLocalServiceUtil.getGroupId(groupId);
			int count = configs.size();
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			
			result.put(ConstantUtils.TOTAL, count);
			JSONArray sLists = JSONFactoryUtil.createJSONArray();
			for (ServerConfig sc : configs) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put(DossierTerm.SERVER_NO, sc.getServerNo());
				if (Validator.isNotNull(sc.getConfigs())) {
					JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
					if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
							&& configObj.has(SyncServerTerm.SERVER_SECRET)
							&& configObj.has(SyncServerTerm.SERVER_URL)
							&& configObj.has(Field.GROUP_ID)) {
				        obj.put(Field.GROUP_ID, configObj.getString(Field.GROUP_ID));
					}
				}
				
				sLists.put(obj);
			}

			result.put(ConstantUtils.DATA, sLists);
			
			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
