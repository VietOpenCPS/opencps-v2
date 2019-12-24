package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
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
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ServerConfigManagementImpl implements ServerConfigManagement {
	Log _log = LogFactoryUtil.getLog(ServerConfigManagementImpl.class);
	
	@Override
	public Response getServerConfigs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServerConfigSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {
			boolean checkAuth = true;
			long serverId = GetterUtil.getLong(id);
			ServerConfig config = null;
			if (serverId == 0) {
				config = ServerConfigLocalServiceUtil.getByCode(groupId,  id);
				if (config != null && "MULTIMEDIA".equalsIgnoreCase(config.getProtocol())) {
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
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			List<ServerConfig> configs = ServerConfigLocalServiceUtil.getGroupId(groupId);
			int count = configs.size();
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			
			result.put("total", count);
			JSONArray sLists = JSONFactoryUtil.createJSONArray();
			for (ServerConfig sc : configs) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put("serverNo", sc.getServerNo());
				if (Validator.isNotNull(sc.getConfigs())) {
					JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
					if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
							&& configObj.has(SyncServerTerm.SERVER_SECRET)
							&& configObj.has(SyncServerTerm.SERVER_URL)
							&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
				        obj.put("groupId", configObj.getString(SyncServerTerm.SERVER_GROUP_ID));
					}
				}
				
				sLists.put(obj);
			}

			result.put("data", sLists);
			
			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getProtocolOfServerConfigs(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String protocolCode) {

		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			List<ServerConfig> configList = ServerConfigLocalServiceUtil.getByProtocol(groupId, protocolCode);

			if (configList != null && configList.size() > 0) {
				ServerConfig config = configList.get(0);

				ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

				return Response.status(200).entity(result).build();
			}

			return Response.status(500).entity("Internal Server").build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getProtocolConnectOfServerConfigs(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String serverNo, String protocolCode,
			ServerConfigSearchModel query) {
		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
			String eFormNo = query.geteFormNo();
			String maCha = query.getMaCha();
			String nameDM = query.getNameDM();
			String parentId = query.getParentId();
			String govAgencyName = query.getGovAgencyName();
			String employeeName = query.getEmployeeName();
			String maNgKy = query.getMa_ng_ky();
			String maCQQL = query.getMa_cqql();
			System.out.println("eFormNo: "+eFormNo);
			StringBuilder sb = new StringBuilder();
			if ("API_CONNECT".equals(protocolCode)) {
				System.out.println("protocolCode: "+protocolCode);
				ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId, serverNo, protocolCode);
				System.out.println("serverConfig: "+serverConfig);
				if (serverConfig != null) {

					String configs = serverConfig.getConfigs();
					JSONObject jsonConfig = JSONFactoryUtil.createJSONObject(configs);
					if (jsonConfig != null) {
						String method = jsonConfig.getString("method");
						System.out.println("method: "+method);
						
						if ("GET".equalsIgnoreCase(method)) {
							System.out.println("methodEQUAL: "+method);
							System.out.println("jsonConfig.getString(\"url\"): "+jsonConfig.getString("url"));
							String urlGet = "";
							try {
								urlGet = jsonConfig.getString("url");
								if (urlGet.contains("{eFormNo}")) {
									urlGet = urlGet.replace("{eFormNo}", URLEncoder.encode(String.valueOf(eFormNo), "UTF-8"));
								}
								if (urlGet.contains("{maCha}")) {
									urlGet = urlGet.replace("{maCha}", URLEncoder.encode(String.valueOf(maCha), "UTF-8"));
								}
								if (urlGet.contains("{nameDM}")) {
									urlGet = urlGet.replace("{nameDM}", URLEncoder.encode(String.valueOf(nameDM), "UTF-8"));
								}
								if (urlGet.contains("{parentId}")) {
									urlGet = urlGet.replace("{parentId}", URLEncoder.encode(String.valueOf(parentId), "UTF-8"));
								}
								if (urlGet.contains("{govAgencyName}")) {
									urlGet = urlGet.replace("{govAgencyName}", URLEncoder.encode(String.valueOf(govAgencyName), "UTF-8"));
								}
								if (urlGet.contains("{employeeName}")) {
									urlGet = urlGet.replace("{employeeName}", URLEncoder.encode(String.valueOf(employeeName), "UTF-8"));
								}
								if (urlGet.contains("{ma_ng_ky}")) {
									urlGet = urlGet.replace("{ma_ng_ky}", maNgKy);
								}
								if (urlGet.contains("{ma_cqql}")) {
									urlGet = urlGet.replace("{ma_cqql}", maCQQL);
								}
//								urlGet = jsonConfig.getString("url").replaceAll("{eFormNo}", eFormNo).
//										replaceAll("{maCha}", maCha)
//										.replaceAll("{parentId}", parentId)
//										.replaceAll("{govAgencyName}", govAgencyName)
//										.replaceAll("{employeeName}", employeeName);
								System.out.println("urlGet: "+urlGet);
							} catch (Exception e) {
								System.out.println("error: "+e);
							}
							
							//
							long groupIdGet = 0;
							String authStrEnc = "";
							//
							String params = jsonConfig.getString("params");
							System.out.println("params: "+params);
							if (Validator.isNotNull(params)) {
								JSONObject jsonParams = JSONFactoryUtil.createJSONObject(params);
								//
								String strHeader = jsonParams.getString("header");
								if (Validator.isNotNull(strHeader)) {
									JSONObject jsonHeader = JSONFactoryUtil.createJSONObject(strHeader);
									//
									groupIdGet = jsonHeader.getLong("groupId");
									System.out.println("groupIdGet: "+groupIdGet);
									
								}
							}
							
							//AUTHEN
							String authenticate = jsonConfig.getString("authenticate");
							System.out.println("authenticate: "+authenticate);
							if (Validator.isNotNull(authenticate)) {
								JSONObject jsonAuthen = JSONFactoryUtil.createJSONObject(authenticate);
								//
								String type = jsonAuthen.getString("type");
								System.out.println("type: "+type);
								if ("base".equals(type)) {
									String userName = jsonAuthen.getString("username");
									String password = jsonAuthen.getString("password");
									System.out.println("userName: "+userName);
									System.out.println("password: "+password);
									//
									authStrEnc = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
									
								}
							}
							
							URL urlVal = new URL(urlGet);

							System.out.println("API URL: " + urlGet);
							java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
							conn.setRequestProperty("groupId", String.valueOf(groupIdGet));
							conn.setRequestMethod(method);
							conn.setRequestProperty("Accept", "application/json");
							conn.setRequestProperty("Authorization", "Basic " + authStrEnc);
							System.out.println("BASIC AUTHEN: " + authStrEnc);

							JSONFactoryUtil.looseSerialize(conn);
							BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
							int cp;
							while ((cp = brf.read()) != -1) {
								sb.append((char) cp);
							}
							System.out.println("RESULT PROXY: " + sb.toString());
							return Response.status(HttpURLConnection.HTTP_OK).entity(sb.toString()).build();
						}
						
					}
				}
			}

			return Response.status(500).entity("Internal Server").build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
