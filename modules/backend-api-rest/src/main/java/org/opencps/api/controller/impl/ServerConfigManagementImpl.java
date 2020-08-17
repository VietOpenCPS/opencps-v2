package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

import java.io.BufferedReader;
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
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ServerConfigManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.ServerConfigUtils;
import org.opencps.api.serverconfig.model.ServerConfigDetailModel;
import org.opencps.api.serverconfig.model.ServerConfigInputModel;
import org.opencps.api.serverconfig.model.ServerConfigResultsModel;
import org.opencps.api.serverconfig.model.ServerConfigSearchModel;
import org.opencps.api.serverconfig.model.ServerConfigSingleInputModel;
import org.opencps.api.test.model.TestConfigModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
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
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			List<ServerConfig> configs = ServerConfigLocalServiceUtil.getGroupId(groupId);
			int count = configs.size();

			ServerConfigResultsModel results = new ServerConfigResultsModel();

			results.setTotal(count);
			results.getData().addAll(ServerConfigUtils.mappingTOData(configs));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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
				if (config != null && ConstantUtils.API_PROTOCOL_MULTIMEDIA.equalsIgnoreCase(config.getProtocol())) {
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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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
				obj.put(ServerConfigTerm.SERVER_NO, sc.getServerNo());
				if (Validator.isNotNull(sc.getConfigs())) {
					JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
					if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
							&& configObj.has(SyncServerTerm.SERVER_SECRET)
							&& configObj.has(SyncServerTerm.SERVER_URL)
							&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
				        obj.put(Field.GROUP_ID, configObj.getString(SyncServerTerm.SERVER_GROUP_ID));
					}
				}
				
				sLists.put(obj);
			}

			result.put(ConstantUtils.DATA, sLists);
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getProtocolOfServerConfigs(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String protocolCode) {

		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			List<ServerConfig> configList = ServerConfigLocalServiceUtil.getByProtocol(groupId, protocolCode);

			if (configList != null && configList.size() > 0) {
				ServerConfig config = configList.get(0);

				ServerConfigDetailModel result = ServerConfigUtils.mappingToDetailModel(config);

				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}

			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_INTERNAL_SERVER_ERROR)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getProtocolConnectOfServerConfigs(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String serverNo, String protocolCode,
			ServerConfigSearchModel query) {
		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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
			String loai = query.getLoai();
			String ten = query.getTen();
			String cqTen = query.getCqTen();
			String cqId = query.getCqId();
			String soHoChieu = query.getSoHoChieu();
			String hoTen = query.getHoTen();
			String ngaySinh = query.getNgaySinh();
			String noiSinh = query.getNoiSinh();
			String flagSearch = query.getFlagSearch();
			String classNameInput = query.getClassName();
			String classPKInput = query.getClassPK();
			String dossierId = query.get_dossierId();
			String dossierCounter = query.get_dossierCounter();
			String imageName = query.getImageName();
			_log.debug("eFormNo: "+eFormNo);
			StringBuilder sb = new StringBuilder();
			if ("API_CONNECT".equals(protocolCode)) {
				_log.debug("protocolCode: "+protocolCode);
				ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(groupId, serverNo, protocolCode);
				_log.debug("serverConfig: "+serverConfig);
				if (serverConfig != null) {

					String configs = serverConfig.getConfigs();
					JSONObject jsonConfig = JSONFactoryUtil.createJSONObject(configs);
					if (jsonConfig != null) {
						String method = jsonConfig.getString("method");
						_log.debug("method: "+method);
						
						if ("GET".equalsIgnoreCase(method)) {
							//System.out.println("methodEQUAL: "+method);
							_log.debug("jsonConfig.getString(\"url\"): "+jsonConfig.getString("url"));
							String urlGet = "";
							try {
								urlGet = jsonConfig.getString("url");
								if (urlGet.contains("{eFormNo}")) {
									urlGet = urlGet.replace("{eFormNo}", Validator.isNotNull(eFormNo) ? URLEncoder.encode(String.valueOf(eFormNo), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{maCha}")) {
									urlGet = urlGet.replace("{maCha}", Validator.isNotNull(maCha) ? URLEncoder.encode(String.valueOf(maCha), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{nameDM}")) {
									urlGet = urlGet.replace("{nameDM}", Validator.isNotNull(nameDM) ? URLEncoder.encode(String.valueOf(nameDM), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{parentId}")) {
									urlGet = urlGet.replace("{parentId}", Validator.isNotNull(parentId) ? URLEncoder.encode(String.valueOf(parentId), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{govAgencyName}")) {
									urlGet = urlGet.replace("{govAgencyName}", Validator.isNotNull(govAgencyName) ? URLEncoder.encode(String.valueOf(govAgencyName), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{employeeName}")) {
									urlGet = urlGet.replace("{employeeName}", Validator.isNotNull(employeeName) ? URLEncoder.encode(String.valueOf(employeeName), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{ma_ng_ky}")) {
									urlGet = urlGet.replace("{ma_ng_ky}", maNgKy);
								}
								if (urlGet.contains("{ma_cqql}")) {
									urlGet = urlGet.replace("{ma_cqql}", maCQQL);
								}
								//HCTN
								if (urlGet.contains("{loai}")) {
									urlGet = urlGet.replace("{loai}", Validator.isNotNull(loai) ? URLEncoder.encode(String.valueOf(loai), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{ten}")) {
									urlGet = urlGet.replace("{ten}", Validator.isNotNull(ten) ? URLEncoder.encode(String.valueOf(ten), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{cqTen}")) {
									urlGet = urlGet.replace("{cqTen}", Validator.isNotNull(cqTen) ? URLEncoder.encode(String.valueOf(cqTen), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{cqId}")) {
									urlGet = urlGet.replace("{cqId}", Validator.isNotNull(cqId) ? URLEncoder.encode(String.valueOf(cqId), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{soHoChieu}")) {
									urlGet = urlGet.replace("{soHoChieu}", Validator.isNotNull(soHoChieu) ? URLEncoder.encode(String.valueOf(soHoChieu), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{hoTen}")) {
									urlGet = urlGet.replace("{hoTen}", Validator.isNotNull(hoTen) ? URLEncoder.encode(String.valueOf(hoTen), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{ngaySinh}")) {
									urlGet = urlGet.replace("{ngaySinh}", Validator.isNotNull(ngaySinh) ? URLEncoder.encode(String.valueOf(ngaySinh), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{noiSinh}")) {
									urlGet = urlGet.replace("{noiSinh}", Validator.isNotNull(noiSinh) ? URLEncoder.encode(String.valueOf(noiSinh), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{flagSearch}")) {
									urlGet = urlGet.replace("{flagSearch}", Validator.isNotNull(flagSearch) ? URLEncoder.encode(String.valueOf(flagSearch), "UTF-8") : StringPool.BLANK);
								}
								if (urlGet.contains("{_dossierId}")) {
									urlGet = urlGet.replace("{_dossierId}", Validator.isNotNull(dossierId) ? dossierId : "0");
								}
								if (urlGet.contains("{_dossierCounter}")) {
									urlGet = urlGet.replace("{_dossierCounter}", Validator.isNotNull(dossierCounter) ? dossierCounter : StringPool.BLANK);
								}
								if (urlGet.contains("{imageName}"))
									urlGet = urlGet.replace("{imageName}",Validator.isNotNull(imageName) ? URLEncoder.encode(String.valueOf(imageName) ,"UTF-8") : StringPool.BLANK);

//								urlGet = jsonConfig.getString("url").replaceAll("{eFormNo}", eFormNo).
//										replaceAll("{maCha}", maCha)
//										.replaceAll("{parentId}", parentId)
//										.replaceAll("{govAgencyName}", govAgencyName)
//										.replaceAll("{employeeName}", employeeName);
								_log.debug("urlGet: "+urlGet);
							} catch (Exception e) {
								_log.debug(e);
//								System.out.println("error: "+e);
							}
							
							//
							long groupIdGet = 0;
							String authStrEnc = "";
							//
							String params = jsonConfig.getString("params");
							_log.debug("params: "+params);
							if (Validator.isNotNull(params)) {
								JSONObject jsonParams = JSONFactoryUtil.createJSONObject(params);
								//
								String strHeader = jsonParams.getString("header");
								if (Validator.isNotNull(strHeader)) {
									JSONObject jsonHeader = JSONFactoryUtil.createJSONObject(strHeader);
									//
									groupIdGet = jsonHeader.getLong("groupId");
									_log.debug("groupIdGet: "+groupIdGet);
									
								}
							}
							
							//AUTHEN
							String authenticate = jsonConfig.getString("authenticate");
							_log.debug("authenticate: "+authenticate);
							if (Validator.isNotNull(authenticate)) {
								JSONObject jsonAuthen = JSONFactoryUtil.createJSONObject(authenticate);
								//
								String type = jsonAuthen.getString("type");
								_log.debug("type: "+type);
								if ("base".equals(type)) {
									String userName = jsonAuthen.getString("username");
									String password = jsonAuthen.getString("password");
									_log.debug("userName: "+userName);
									_log.debug("password: "+password);
									//
									authStrEnc = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
									
								}
							}
							
							URL urlVal = new URL(urlGet);

							_log.debug("API URL: " + urlGet);
							java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
							conn.setRequestProperty("groupId", String.valueOf(groupIdGet));
							conn.setRequestMethod(method);
							conn.setRequestProperty("Accept", "application/json");
							conn.setRequestProperty("Authorization", "Basic " + authStrEnc);
							_log.debug("BASIC AUTHEN: " + authStrEnc);

							//JSONFactoryUtil.looseSerialize(conn);
							BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
							int cp;
							while ((cp = brf.read()) != -1) {
								sb.append((char) cp);
							}
							//_log.debug("RESULT PROXY: " + sb.toString());
							return Response.status(HttpURLConnection.HTTP_OK).entity(sb.toString()).build();
						}
						else if ("POST".equals(method) || "PUT".equals(method)) {
							System.out.println("method INSERT OR UPDATE: "+method);
							System.out.println("jsonConfig.getString(\"url\"): "+jsonConfig.getString("url"));
							String urlUpdate = jsonConfig.getString("url");
							System.out.println("urlUpdate: "+urlUpdate);
							
							//
							long groupIdUpdate = 0;
							String authStrEnc = StringPool.BLANK;
							String classNameUpdate = StringPool.BLANK;
							String classPKUpdate = StringPool.BLANK;
							//
							String params = jsonConfig.getString("params");
							System.out.println("params: "+params);
							JSONObject jsonBodyData = JSONFactoryUtil.createJSONObject();
							if (Validator.isNotNull(params)) {
								JSONObject jsonParams = JSONFactoryUtil.createJSONObject(params);
								//
								String strHeader = jsonParams.getString("header");
								if (Validator.isNotNull(strHeader)) {
									JSONObject jsonHeader = JSONFactoryUtil.createJSONObject(strHeader);
									//
									groupIdUpdate = jsonHeader.getLong("groupId");
									System.out.println("groupIdUpdate: "+groupIdUpdate);
									
								}
								//
								String strBody = jsonParams.getString("body");
								if (Validator.isNotNull(strBody)) {
									JSONObject jsonBody = JSONFactoryUtil.createJSONObject(strBody);
									//
									classNameUpdate = jsonBody.getString("className");
									if (classNameUpdate.contains("{className}")) {
										classNameUpdate = classNameUpdate.replace("{className}", Validator.isNotNull(classNameInput) ? URLEncoder.encode(String.valueOf(classNameInput), "UTF-8") : StringPool.BLANK);
									}
									classPKUpdate = jsonBody.getString("classPK");
									if (classPKUpdate.contains("{classPK}")) {
										classPKUpdate = classPKUpdate.replace("{classPK}", Validator.isNotNull(classPKInput) ? URLEncoder.encode(String.valueOf(classPKInput), "UTF-8") : StringPool.BLANK);
									}

									jsonBodyData.put("className", classNameUpdate);
									jsonBodyData.put("classPK", classPKUpdate);
									System.out.println("className: "+classNameUpdate);
									System.out.println("classPK: "+classPKUpdate);

									Iterator<?> keys = jsonBody.keys();
									while (keys.hasNext())
									{
										String key = (String) keys.next();
										Object value = jsonBody.getString(key);
										jsonBodyData.put(key,value);
									}
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
									if (Validator.isNotNull(userName) && Validator.isNotNull(password)) {
										authStrEnc = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
									}
								}
							}

							StringBuilder postData = new StringBuilder();
							Iterator<?> keys = jsonBodyData.keys();
							while (keys.hasNext()) {
								String key = (String) keys.next();
								if (!"".equals(postData.toString())) {
									postData.append("&");
								}
								postData.append(key);
								postData.append("=");
								postData.append(jsonBodyData.get(key));
							}

							URL urlVal = new URL(urlUpdate);
							_log.debug("API URL: " + urlVal);
							java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
							conn.setRequestProperty("groupId", GetterUtil.getString(groupIdUpdate));
							conn.setRequestMethod(method);
							conn.setRequestProperty("Accept", "application/json");
							if (Validator.isNotNull(authStrEnc)) {
								conn.setRequestProperty("Authorization", "Basic " + authStrEnc);
							}
							_log.debug("BASIC AUTHEN: " + authStrEnc);
							conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
							conn.setRequestProperty("Content-Length",
									"" + Integer.toString(postData.toString().getBytes().length));

							conn.setUseCaches(false);
							conn.setDoInput(true);
							conn.setDoOutput(true);
							_log.debug("POST DATA: " + postData.toString());
							OutputStream os = conn.getOutputStream();
							os.write(postData.toString().getBytes());
							os.close();

								
							BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

							int cp;
							while ((cp = brf.read()) != -1) {
								sb.append((char) cp);
							}
							_log.debug("RESULT PROXY: " + sb.toString());
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

	@Override
	public Response getTest(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
				 User user, ServiceContext serviceContext, TestConfigModel query) {

		try {
			int id = query.getServerId();
			String serverNo = query.getServerNo();
			String serverName = query.getServerName();
			String serverName111 = query.getServerName11();
			String serverName22 = query.getServerName22();
			String serverName33 = query.getServerName33();
			System.out.println("serverNo: "+serverNo);
			System.out.println("serverName: "+serverName);
			System.out.println("serverName111: "+serverName111);
			System.out.println("serverName22: "+serverName22);
			System.out.println("serverName33: "+serverName33);
			System.out.println("id: "+id);

			return Response.status(200).entity("OK").build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}
}
