package org.graphql.api.controller.impl;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.sso.openid.connect.OpenIdConnect;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.graphql.api.controller.utils.CaptchaServiceSingleton;
import org.graphql.api.controller.utils.CheckFileUtils;
import org.graphql.api.controller.utils.DeliverableUtils;
import org.graphql.api.controller.utils.ElasticQueryWrapUtil;
import org.graphql.api.controller.utils.GraphQLUtils;
import org.graphql.api.controller.utils.WebKeys;
import org.graphql.api.errors.OpenCPSNotFoundException;
import org.graphql.api.model.FileTemplateMiniItem;
import org.graphql.api.model.UsersUserItem;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.dossiermgt.action.DeliverableTypesActions;
import org.opencps.dossiermgt.action.impl.DeliverableTypesActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;
import org.opencps.usermgt.action.impl.UserActions;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.constants.UserRegisterTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.util.LGSPRestfulUtils;
import org.opencps.usermgt.service.util.SendMailLGSPUtils;
import org.opencps.usermgt.service.util.ServiceProps;
import org.osgi.service.component.annotations.Reference;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import backend.admin.config.whiteboard.BundleLoader;
import backend.utils.FileUploadUtils;
import io.swagger.annotations.ApiParam;

/**
 * Rest Controller
 *
 * @author binhth
 */
@RestController
public class RestfulController {

	@RequestMapping(value = "/user/{id}/deactive", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void deactiveAccount(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") long id,
			@RequestBody String body) {

		try {

			JSONObject bodyData = JSONFactoryUtil.createJSONObject(body);

			User user = UserLocalServiceUtil.getUser(id);

			boolean locked = bodyData.getBoolean("locked");

			if (locked) {
				user.setStatus(WorkflowConstants.STATUS_INACTIVE);
			} else {
				user.setStatus(WorkflowConstants.STATUS_APPROVED);
			}

			UserLocalServiceUtil.updateUser(user);

			Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/user/{id}/changepass", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void changePassWordUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") long id, @RequestBody String body) {

		try {

			JSONObject bodyData = JSONFactoryUtil.createJSONObject(body);

			String password = bodyData.getString("password");

			User user = UserLocalServiceUtil.updatePassword(id, password, password, Boolean.FALSE);

			Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getUserId(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") long id) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("email", StringPool.BLANK);
		result.put("screenName", StringPool.BLANK);
		result.put("deactiveAccountFlag", 0);

		try {

			User user = UserLocalServiceUtil.fetchUser(id);

			result.put("email", user.getEmailAddress());
			result.put("screenName", user.getScreenName());
			result.put("deactiveAccountFlag", user.getStatus());

		} catch (Exception e) {
			_log.debug(e);
		}

		return result.toJSONString();
	}

	@Cacheable
	@RequestMapping(value = "/users/login", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getUserLoginInfo(HttpServletRequest request, HttpServletResponse response) {

		JSONArray dataUser = JSONFactoryUtil.createJSONArray();

		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("email", StringPool.BLANK);
		result.put("role", StringPool.BLANK);
		result.put("deactiveAccountFlag", 0);
		try {

			long userId = 0;
			if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
				userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());

				User user = UserLocalServiceUtil.fetchUser(userId);

				List<Role> roles = user.getRoles();

				String roleName = StringPool.BLANK;

				for (Role role : roles) {

					if ("Administrator".equals(role.getName())) {
						roleName = "Administrator";
						break;
					}

					if ("Administrator_data".equals(role.getName())) {
						roleName = "Administrator_data";
						break;
					}

				}

				result.put("email", user.getEmailAddress());
				result.put("role", roleName);
				result.put("deactiveAccountFlag", user.getStatus());

			}

		} catch (Exception e) {
			_log.debug(e);
		}

		dataUser.put(result);

		return dataUser.toJSONString();
	}
	
	
	@RequestMapping(value = "/is-enabled-sso-login", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	public String isEnabledSSOLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String isEnabledOpenIdConnect = "/c/opencps/login/openidconnectrequest";
		
		long companyId = PortalUtil.getCompanyId(request);
		
		OpenIdConnectUtils openIdConnectUtils = new OpenIdConnectUtils();
		
		boolean isEnabled =  openIdConnectUtils.isEnabled(companyId);
		
		if(isEnabled) {
			return isEnabledOpenIdConnect;
		}
		
		return null;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		long checkUserId = -1;
		String emailAddress = StringPool.BLANK;
		String loginMax = PropsUtil.get("opencps.user.login.max");
		String secretKey = PropsUtil.get("opencps.jwt.secret");
		String SECRET = Validator.isNotNull(secretKey) ? secretKey : "secret";
		//Custom login
		boolean syncUserLGSP = Validator.isNotNull(PropsUtil.get("opencps.register.lgsp"))
				? GetterUtil.getBoolean(PropsUtil.get("opencps.register.lgsp")) : false;

		try {
			
			String jCaptchaResponse = request.getParameter("j_captcha_response");
			_log.info("jCaptchaResponse: "+jCaptchaResponse);
			if (Validator.isNotNull(jCaptchaResponse)) {
				
				ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
				String captchaId = request.getSession().getId();
				_log.info("captchaId: "+captchaId);
				try {
					boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
					_log.info("isResponseCorrect: "+isResponseCorrect);
					if (!isResponseCorrect) {
						response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
						return "captcha";
					}
				} catch (CaptchaServiceException e) {
					_log.debug(e);
					_log.info("ERROR AUTHEN LOGIN: "+e);
					response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
					return "captcha";
				}
			}

			Enumeration<String> headerNames = request.getHeaderNames();

			String strBasic = StringPool.BLANK;

			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String key = (String) headerNames.nextElement();
					String value = request.getHeader(key);
					if (key.trim().equalsIgnoreCase(WebKeys.AUTHORIZATION)) {
						strBasic = value;
						break;
					}
				}
			}

			_log.info("syncUserLGSP: "+ syncUserLGSP);
			if (syncUserLGSP) {
				// Get encoded user and password, comes after "BASIC "
				String userpassEncoded = strBasic.substring(6);
				String decodetoken = new String(Base64.decode(userpassEncoded), StringPool.UTF8);

				String account[] = decodetoken.split(":");

				String email = account[0];
				String password = account[1];
				emailAddress += email;
				
				//Check applicant authen
				String passKey = StringPool.BLANK;
				long applicantId = 0;
				Applicant app = null;
				if (Validator.isNotNull(email)) {
					List<Applicant> appList = ApplicantLocalServiceUtil.findByContactEmailList(email);
					if (appList != null && appList.size() > 0) {
						for (Applicant applicant : appList) {
							if (applicant.getMappingUserId() > 0 && applicant.getGroupId() > 0) {
								passKey = applicant.getTmpPass();
								applicantId = applicant.getApplicantId();
								app = applicant;
							}
						}
					}
				}
				
				if (Validator.isNull(passKey)) {
					response.setStatus(HttpServletResponse.SC_OK);
					return "";
				}
				
//				// Create a trust manager that does not validate certificate chains
//				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//					public X509Certificate[] getAcceptedIssuers() {
//						return null;
//					}
//					public void checkClientTrusted(X509Certificate[] certs, String authType) {
//					}
//					public void checkServerTrusted(X509Certificate[] certs, String authType) {
//					}
//				} };
//				// Install the all-trusting trust manager
//				try {
//					SSLContext sc = SSLContext.getInstance("SSL");
//					sc.init(null, trustAllCerts, new SecureRandom());
//					HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//				} catch (Exception e) {
//				}
				
				JSONObject jsonToken = LGSPRestfulUtils.createTokenLGSP("Bearer");
				if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
						&& jsonToken.has("expiryDate")) {

					String strUrlLogin = UserRegisterTerm.NEW_BASE_URL + UserRegisterTerm.NEW_ENDPOINT_LOGIN;
					_log.info("strUrlLogin: "+ strUrlLogin);
					String authStrEnc = "Bearer" + StringPool.SPACE + jsonToken.getString("token");
					_log.info("authStrEnc: "+ authStrEnc);
					_log.info("email: "+ email);
					_log.info("password: "+ password);
					
					StringBuilder sbLogin = new StringBuilder();
					try {
						URL urlLogin = new URL(strUrlLogin);

						JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
						jsonBody.put(UserRegisterTerm.USER_NAME, email);
						jsonBody.put(UserRegisterTerm.SECRECT_KEY, password);
						//

						java.net.HttpURLConnection conLogin = (java.net.HttpURLConnection) urlLogin.openConnection();
						conLogin.setRequestMethod(HttpMethod.POST);
						conLogin.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
						conLogin.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
						conLogin.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
						_log.debug("BASIC AUTHEN: " + authStrEnc);
						conLogin.setRequestProperty("Content-Length",
								StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

						conLogin.setUseCaches(false);
						conLogin.setDoInput(true);
						conLogin.setDoOutput(true);
						_log.debug("POST DATA: " + jsonBody.toString());
						OutputStream osLogin = conLogin.getOutputStream();
						osLogin.write(jsonBody.toString().getBytes());
						osLogin.close();

						BufferedReader brfLogin = new BufferedReader(new InputStreamReader(conLogin.getInputStream()));

						int cpLogin;
						while ((cpLogin = brfLogin.read()) != -1) {
							sbLogin.append((char) cpLogin);
						}
						_log.info("RESULT PROXY: " + sbLogin.toString());
						//
						if (Validator.isNotNull(sbLogin.toString())) {
							//
							_log.error("sbReg:" + sbLogin.toString());
							JSONObject jsonLogin = JSONFactoryUtil.createJSONObject(sbLogin.toString());
							if (jsonLogin.has("success")
									&& jsonLogin.has(UserRegisterTerm.USER_NAME)
									&& jsonLogin.has("isRequireVerify")
									&& jsonLogin.has("isRequireChangePassword")) {
								boolean isSuccess = jsonLogin.getBoolean("success");
								boolean isRequireVerify = jsonLogin.getBoolean("isRequireVerify");
								boolean isRequireChangePassword = jsonLogin.getBoolean("isRequireChangePassword");

								if (isRequireChangePassword) {
									long userId = 0;
									try {
										userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, passKey,
												CompanyConstants.AUTH_TYPE_EA);
									} catch (PortalException e) {
										userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, password,
												CompanyConstants.AUTH_TYPE_EA);
										//Update applicant
										passKey = password;
										if (app != null) {
											app.setTmpPass(password);
											ApplicantLocalServiceUtil.updateApplicant(app);
										}
									}
									_log.info("userId: "+userId);
//										if (userId == 0) {
//											userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, password,
//													CompanyConstants.AUTH_TYPE_EA);
//											//Update applicant
//											passKey = password;
//											if (app != null) {
//												app.setTmpPass(password);
//												ApplicantLocalServiceUtil.updateApplicant(app);
//											}
//										}
									if (userId > 0 && userId != 20103) {
										checkUserId = userId;
										//Remember me false
										AuthenticatedSessionManagerUtil.login(request, response, email, passKey, false,
												CompanyConstants.AUTH_TYPE_EA);

										User user = UserLocalServiceUtil.fetchUser(userId);
//											Algorithm algorithm = Algorithm.HMAC256(SECRET);
//											String token = JWT.create()
//													.withClaim("screenName", Validator.isNotNull(user) ? user.getScreenName() : StringPool.BLANK)
//													.sign(algorithm);
//											response.setHeader("jwt-token", token);
										
										if (userId != 20139) {
											_log.info("changeSecrect: OK");
											response.setStatus(HttpServletResponse.SC_OK);
											return "changeSecrect";
										} else {
											_log.info("NOT CHANGE OK");
											response.setStatus(HttpServletResponse.SC_OK);
											return "ok";
										}
										
									} // Create userId
//										else {
//											//Get userInfo
//											String strUrlInfo = UserRegisterTerm.NEW_BASE_URL
//													+ UserRegisterTerm.NEW_ENDPOINT_GET_USER + StringPool.FORWARD_SLASH
//													+ email;
//											_log.info("strUrlInfo: "+ strUrlInfo);
//											URL urlInfo = new URL(strUrlInfo);
//
//											java.net.HttpURLConnection conInfo = (java.net.HttpURLConnection) urlInfo.openConnection();
//											conInfo.setRequestMethod(HttpMethod.POST);
//											conInfo.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
//											conInfo.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
//											conInfo.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
//											_log.debug("BASIC AUTHEN: " + authStrEnc);
//											conInfo.setRequestProperty("Content-Length", String.valueOf(0));
//
//											conInfo.setUseCaches(false);
//											conInfo.setDoInput(true);
//											conInfo.setDoOutput(true);
//											OutputStream osInfo = conInfo.getOutputStream();
//											osInfo.close();
//
//											BufferedReader brfInfo = new BufferedReader(new InputStreamReader(conInfo.getInputStream()));
//
//											int cpInfo;
//											StringBuilder sbInfo = new StringBuilder();
//											while ((cpInfo = brfInfo.read()) != -1) {
//												sbInfo.append((char) cpInfo);
//											}
//											_log.info("RESULT PROXY: " + sbInfo.toString());
//											if (Validator.isNotNull(sbInfo.toString())) {
//												JSONObject jsonInfo = JSONFactoryUtil.createJSONObject(sbInfo.toString());
//												//
//												ApplicantActions actions = new ApplicantActionsImpl();
//												Applicant applicant = actions.registerApproved(serviceContext, groupId, applicantName,
//														applicantIdType, applicantIdNo, applicantIdDate, contactEmail, address, cityCode,
//														cityName, districtCode, districtName, wardCode, wardName, contactName, contactTelNo,
//														StringPool.BLANK, input.getPassword());
//											}
//										}
								} else if (isRequireVerify) {
									if (app != null) {
										String activationCode = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
										app.setActivationCode(activationCode);
										ApplicantLocalServiceUtil.updateApplicant(app);
										//Send activeCode
										SendMailLGSPUtils.sendMailVerifyAcc(app, activationCode,
												GetterUtil.getLong(request.getHeader("groupId")));
									}
									response.setStatus(HttpServletResponse.SC_OK);
									response.setHeader("applicantId", String.valueOf(applicantId));
									return "verify";
								} else if (isSuccess) {
									//Sau khi check authen xong
									_log.info("passKey: "+ passKey);
									long userId = 0;
									try {
										userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, passKey,
												CompanyConstants.AUTH_TYPE_EA);
									} catch (PortalException e) {
										userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, password,
												CompanyConstants.AUTH_TYPE_EA);
									}
									if (userId > 0 && userId != 20103) {
										checkUserId = userId;
										//Remember me false
										AuthenticatedSessionManagerUtil.login(request, response, email, passKey, false,
												CompanyConstants.AUTH_TYPE_EA);

										User user = UserLocalServiceUtil.fetchUser(userId);
//											Algorithm algorithm = Algorithm.HMAC256(SECRET);
//											String token = JWT.create()
//													.withClaim("screenName", Validator.isNotNull(user) ? user.getScreenName() : StringPool.BLANK)
//													.sign(algorithm);
//											response.setHeader("jwt-token", token);
										
										if (userId != 20139) {
											Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);

											if (Validator.isNotNull(employee)) {

												if (user != null && user.getStatus() == WorkflowConstants.STATUS_PENDING && employee.getWorkingStatus() == 0) {
													response.setStatus(HttpServletResponse.SC_OK);
													return "pending";
												} else {
													response.setStatus(HttpServletResponse.SC_OK);
													return "/c";
												}
												
											} else {
												if (user != null && user.getStatus() == WorkflowConstants.STATUS_PENDING) {
													Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(user.getUserId());
													if (applicant != null) {
														response.setHeader(Field.USER_ID, String.valueOf(applicant.getApplicantId()));
													}
													else {
														response.setHeader(Field.USER_ID, String.valueOf(user.getUserId()));
													}

													response.setStatus(HttpServletResponse.SC_OK);
													return "pending";
												} else {
													response.setStatus(HttpServletResponse.SC_OK);
													return "ok";
												}
											}
										} else {
											response.setStatus(HttpServletResponse.SC_OK);
											return "ok";
										}
										
									}
								} else {
									Company company = null;
									try {
										company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
									} catch (PortalException ex) {
										_log.debug(ex);
									}
									if (company != null) {
										User checkUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), emailAddress);

										if (checkUser != null && checkUser.getStatus() == WorkflowConstants.STATUS_PENDING) {
											_log.info("checkUser: "+JSONFactoryUtil.looseSerialize(checkUser));
											_log.info("checkUserId: "+checkUser.getUserId());
											Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(checkUser.getUserId());
											_log.info("applicant: "+JSONFactoryUtil.looseSerialize(applicant));
											if (applicant != null) {
												response.setHeader(Field.USER_ID, String.valueOf(applicant.getApplicantId()));
											}
											else {
												response.setHeader(Field.USER_ID, String.valueOf(checkUser.getUserId()));
											}
											response.setStatus(HttpServletResponse.SC_OK);
											return "pending";
										} else {
											return "";
										}
									}
									else {
										return "";
									}
								}
							}
						}
					} catch (Exception e) {
						_log.error(e);
						_log.debug("Something went wrong while reading/writing in stream!!");
					}
				}
			} else {
				// Get encoded user and password, comes after "BASIC "
				String userpassEncoded = strBasic.substring(6);
				String decodetoken = new String(Base64.decode(userpassEncoded), StringPool.UTF8);

				String account[] = decodetoken.split(":");

				String email = account[0];
				String password = account[1];
				emailAddress += email;
				
				long userId = AuthenticatedSessionManagerUtil.getAuthenticatedUserId(request, email, password,
						CompanyConstants.AUTH_TYPE_EA);
				if (userId > 0 && userId != 20103) {
					checkUserId = userId;
//					AuthenticatedSessionManagerUtil.login(request, response, email, password, true,
//							CompanyConstants.AUTH_TYPE_EA);
					//Remember me false
					AuthenticatedSessionManagerUtil.login(request, response, email, password, false,
							CompanyConstants.AUTH_TYPE_EA);

					User user = UserLocalServiceUtil.fetchUser(userId);
//					Algorithm algorithm = Algorithm.HMAC256(SECRET);
//					String token = JWT.create()
//							.withClaim("screenName", Validator.isNotNull(user) ? user.getScreenName() : StringPool.BLANK)
//							.sign(algorithm);
//					response.setHeader("jwt-token", token);
					
					if (userId != 20139) {
						Employee employee = EmployeeLocalServiceUtil.fetchByFB_MUID(userId);

//						String sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
//						
//						UserLoginLocalServiceUtil.updateUserLogin(user.getCompanyId(), user.getGroupId(), userId, user.getFullName(), new Date(), new Date(), 0l, sessionId, 0, null, request.getRemoteAddr());
//						String userAgent = request.getHeader("User-Agent") != null ? request.getHeader("User-Agent") : StringPool.BLANK;
//						ArrayList<UserTrackerPath> userTrackerPath = new ArrayList<UserTrackerPath>();
//						UserTrackerLocalServiceUtil.addUserTracker(
//								user.getCompanyId(), 
//								userId, 
//								new Date(), 
//								sessionId, 
//								request.getRemoteAddr(), 
//								request.getRemoteHost(), 
//								userAgent, 
//								userTrackerPath);
						if (Validator.isNotNull(employee)) {

							if (user != null && user.getStatus() == WorkflowConstants.STATUS_PENDING && employee.getWorkingStatus() == 0) {
								response.setStatus(HttpServletResponse.SC_OK);
								return "pending";
							} else {
								response.setStatus(HttpServletResponse.SC_OK);
								return "/c";
							}
						} else {
							if (user != null && user.getStatus() == WorkflowConstants.STATUS_PENDING) {
								_log.info("checkUser: "+JSONFactoryUtil.looseSerialize(user));
								Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(user.getUserId());
								_log.info("applicant: "+JSONFactoryUtil.looseSerialize(applicant));
								if (applicant != null) {
									response.setHeader(Field.USER_ID, String.valueOf(applicant.getApplicantId()));
								}
								else {
									response.setHeader(Field.USER_ID, String.valueOf(user.getUserId()));
								}
								response.setStatus(HttpServletResponse.SC_OK);
								return "pending";
							} else {
								response.setStatus(HttpServletResponse.SC_OK);
								return "ok";
							}
						}
					} else {
						response.setStatus(HttpServletResponse.SC_OK);
						return "ok";
					}
					
				}
			}
			
			

		} 
		catch (AuthException ae) {
			//System.out.println("AUTH EXCEPTION: " + checkUserId);
			_log.debug(ae);

			try {
				int max = 5;
				if (Validator.isNotNull(loginMax)) {
					max = Integer.parseInt(loginMax);
				}
				User checkUser = null;
				if (checkUserId != -1) {

					checkUser = UserLocalServiceUtil.fetchUser(checkUserId);
				}
				else {

					Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
					checkUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), emailAddress);
				}
				if (checkUser != null && checkUser.getFailedLoginAttempts() >= max) {
					
					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
					String jCaptchaResponse = request.getParameter("j_captcha_response");
					String captchaId = request.getSession().getId();
					try {
						boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
						if (!isResponseCorrect) {
							response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
							return "captcha";
						}
					} catch (CaptchaServiceException e) {
						_log.debug(e);
						response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
						return "captcha";
					}
				}
				else if (checkUser != null && checkUser.getStatus() == WorkflowConstants.STATUS_PENDING) {
					Applicant applicant = ApplicantLocalServiceUtil.fetchByMappingID(checkUser.getUserId());
					_log.info("checkUser.getUserId(): "+checkUser.getUserId());
					_log.info("applicant: "+JSONFactoryUtil.looseSerialize(applicant));
					if (applicant != null) {
						response.setHeader(Field.USER_ID, String.valueOf(applicant.getApplicantId()));
					}
					else {
						response.setHeader(Field.USER_ID, String.valueOf(checkUser.getUserId()));
					}
					response.setStatus(HttpServletResponse.SC_OK);
					return "pending";
				}
			} catch (PortalException e) {
				_log.debug(e);
			}
		}
		catch (PortalException pe) {
			System.out.println("PORTAL EXCEPTION: " + emailAddress);
			_log.debug(pe);
			try {
				Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
				User checkUser = UserLocalServiceUtil.fetchUserByEmailAddress(company.getCompanyId(), emailAddress);
				
				if (checkUser.isLockout()) {
					response.setStatus(HttpServletResponse.SC_OK);
					return "lockout";
				}
//				if (checkUser != null && checkUser.getFailedLoginAttempts() >= 5) {
//					ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
//					String jCaptchaResponse = request.getParameter("j_captcha_response");
//					String captchaId = request.getSession().getId();
//			        try {
//			        	boolean isResponseCorrect = instance.validateResponseForID(captchaId,
//			        			jCaptchaResponse);
//			        	if (!isResponseCorrect) 
//			        		return "captcha";
//			        } catch (CaptchaServiceException e) {
//			        	_log.debug(e);
//			        	return "captcha";
//			        }				
//				}		
			} catch (PortalException e) {
				_log.debug(e);
			}
			
		}		
		catch (Exception e) {
			System.out.println("EXCEPTION");
			_log.debug(e);
		}

		response.setStatus(HttpServletResponse.SC_OK);
		return "";
	}

	@RequestMapping(value = "/users/avatar/{className}/{pk}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getAttachment(HttpServletRequest request, @PathVariable("className") String className,
			@PathVariable("pk") String pk) {

		String result = StringPool.BLANK;

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader("groupId"))) {
			groupId = Long.valueOf(request.getHeader("groupId"));
		}

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(groupId, className, pk);

		if (Validator.isNotNull(fileAttachs) && fileAttachs.size() > 0) {

			FileAttach fileAttach = fileAttachs.get(fileAttachs.size() - 1);

			try {

//				DLFileEntry file = DLFileEntryLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());
				boolean flagCheckFile = CheckFileUtils.checkFileEntryUpload(fileEntry);
				if (!flagCheckFile) {
					throw new ResourceNotFoundException();
				}
				
//				result = "/documents/" + file.getGroupId() + StringPool.FORWARD_SLASH + file.getFolderId()
//						+ StringPool.FORWARD_SLASH + HtmlUtil.escape(file.getTitle()) + StringPool.FORWARD_SLASH + file.getUuid();
				result = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				_log.debug(e);
			}

		}

		return result;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "File Attachment incorrect format")
	public class ResourceNotFoundException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	@ResponseStatus(value = HttpStatus.OK, reason = "Success")
	public class SucessException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}

	@RequestMapping(value = "/users/upload/{code}/{className}/{pk}", method = RequestMethod.POST)
	public void uploadAttachment(MultipartHttpServletRequest request, @PathVariable("code") String code,
			@PathVariable("className") String className, @PathVariable("pk") String pk) {

		CommonsMultipartFile multipartFile = null;

		Iterator<String> iterator = request.getFileNames();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			// create multipartFile array if you upload multiple files
			multipartFile = (CommonsMultipartFile) request.getFile(key);
		}

		boolean flagCheck = CheckFileUtils.checkFileUpload(multipartFile);
		
		if (!flagCheck) {
//			return Response.status(403)
//					.entity("File Attachment incorrect format!").build();
			throw new ResourceNotFoundException();
		}

		long userId = 0;

		if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
			userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
		}

		long groupId = 0;
		if (Validator.isNotNull(request.getHeader("groupId"))) {
			groupId = Long.valueOf(request.getHeader("groupId"));
		}
		long companyId = CompanyThreadLocal.getCompanyId();
		String desc = "FileAttach file upload";
		String destination = "FileAttach/";

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);
		
		try {
			if (multipartFile != null) {
				String fileName = HtmlUtil.escape(multipartFile.getOriginalFilename());
				FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, multipartFile.getInputStream(),
						UUID.randomUUID() + "_" + fileName,
						multipartFile.getOriginalFilename()
								.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1),
						multipartFile.getSize(), destination, desc, serviceContext);

				if ("opencps_adminconfig".equals(code)) {

					ServiceFileTemplateLocalServiceUtil.addServiceFileTemplate(Long.valueOf(pk),
							fileEntry.getFileEntryId() + StringPool.BLANK, fileName,
							fileEntry.getFileEntryId(), serviceContext);

				} else {

					User user = UserLocalServiceUtil.fetchUser(userId);

					FileAttach fileAttach = FileAttachLocalServiceUtil.addFileAttach(userId, groupId, className, pk,
							user.getFullName(), user.getEmailAddress(), fileEntry.getFileEntryId(), StringPool.BLANK,
							StringPool.BLANK, 0, fileEntry.getFileName(), serviceContext);

					if ("opencps_employee".equals(code)) {
						Employee employee = EmployeeLocalServiceUtil.fetchEmployee(Long.valueOf(pk));
						employee.setPhotoFileEntryId(fileAttach.getFileEntryId());
						EmployeeLocalServiceUtil.updateEmployee(employee);
					} else if ("opencps_deliverabletype".equals(code)) {

						DeliverableType openCPSDeliverableType = DeliverableTypeLocalServiceUtil
								.fetchDeliverableType(Long.valueOf(pk));

						if (className.endsWith("FORM")) {
							openCPSDeliverableType.setFormScriptFileId(fileAttach.getFileEntryId());
						} else if (className.endsWith("JASPER")) {
							openCPSDeliverableType.setFormReportFileId(fileAttach.getFileEntryId());
						}

						DeliverableTypeLocalServiceUtil.updateDeliverableType(openCPSDeliverableType);

					} else if ("opencps_applicant".equals(code)) {

						System.out.println("RestfulController.uploadAttachment()" + Long.valueOf(pk));
						Employee employee = EmployeeLocalServiceUtil.fetchEmployee(Long.valueOf(pk));
						System.out.println("RestfulController.uploadAttachment(className)" + className);
						File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
								true);
						if ("org.opencps.usermgt.model.ApplicantEsign".equals(className)) {
							String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "png";
							File targetFile = new File(buildFileName);
							employee.setFileSignId(fileAttach.getFileEntryId());
							Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						} else {
							String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "cer";
							File targetFile = new File(buildFileName);
							employee.setFileCertId(fileAttach.getFileEntryId());
							Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
						}

						EmployeeLocalServiceUtil.updateEmployee(employee);

					} else if ("opencps_deliverable".equals(code)) {

						Deliverable openCPSDeliverable = DeliverableLocalServiceUtil
								.fetchDeliverable(Long.valueOf(pk));

//						openCPSDeliverable.setFileEntryId(fileAttach.getFileEntryId());
						openCPSDeliverable.setFileAttachs(String.valueOf(fileAttach.getFileEntryId()));
						String formData = openCPSDeliverable.getFormData();
						if (Validator.isNotNull(formData)) {
							JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
							jsonData.put("fileAttach", true);
							openCPSDeliverable.setFormData(jsonData.toJSONString());
						}

						DeliverableLocalServiceUtil.updateDeliverable(openCPSDeliverable);

					}

				}
			}
		} catch (Exception e) {
			_log.debug(e);
		}
		
//		return Response.status(200)
//				.entity("Success!").build();
	}

	@RequestMapping(value = "/users/upload/{code}/{className}/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.POST)
	public void uploadServiceFileAttachment(MultipartHttpServletRequest request, @PathVariable("code") String code,
			@PathVariable("className") String className, @PathVariable("serviceInfoId") String serviceInfoId,
			@PathVariable("fileTemplateNo") String fileTemplateNo) {

		CommonsMultipartFile multipartFile = null;

		Iterator<String> iterator = request.getFileNames();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			// create multipartFile array if you upload multiple files
			multipartFile = (CommonsMultipartFile) request.getFile(key);
		}

		boolean flagCheck = CheckFileUtils.checkFileUpload(multipartFile);
		
		if (!flagCheck) {
//			return Response.status(403)
//					.entity("File Attachment incorrect format!").build();
			throw new ResourceNotFoundException();
		}
		long userId = 0;
		if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
			userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
		}
		long groupId = 0;
		if (Validator.isNotNull(request.getHeader("groupId"))) {
			groupId = Long.valueOf(request.getHeader("groupId"));
		}
		long companyId = CompanyThreadLocal.getCompanyId();
		String desc = "FileAttach file upload";
		String destination = "FileAttach/";

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(groupId);

		try {
			if (multipartFile != null) {
				String fileName = HtmlUtil.escape(multipartFile.getOriginalFilename());
				FileEntry fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, multipartFile.getInputStream(),
						UUID.randomUUID() + "_" + fileName,
						multipartFile.getOriginalFilename()
								.substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1),
						multipartFile.getSize(), destination, desc, serviceContext);

				User user = UserLocalServiceUtil.fetchUser(userId);

				FileAttach fileAttach = FileAttachLocalServiceUtil.addFileAttach(userId, groupId, className, serviceInfoId+ "_"+fileTemplateNo,
						user.getFullName(), user.getEmailAddress(), fileEntry.getFileEntryId(), StringPool.BLANK,
						StringPool.BLANK, 0, fileEntry.getFileName(), serviceContext);

				if ("opencps_services_filetemplates".equals(code)) {

					ServiceFileTemplate fileTemplate = ServiceFileTemplateLocalServiceUtil
							.fetchByF_serviceInfoId_fileTemplateNo(Long.valueOf(serviceInfoId), fileTemplateNo);

					if (className.endsWith("FORM")) {
						fileTemplate.setFormScriptFileId(fileAttach.getFileEntryId());
					} else if (className.endsWith("JASPER")) {
						fileTemplate.setFormReportFileId(fileAttach.getFileEntryId());
					}

					ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplate(fileTemplate);
				}
			}

		} catch (Exception e) {
			_log.debug(e);
		}

//		return Response.status(200)
//				.entity("Success!").build();
	}

	@RequestMapping(value = "/filetemplate/{pk}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getServiceFileTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("pk") long pk) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray resultArray = JSONFactoryUtil.createJSONArray();

		List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil.getByServiceInfoId(pk);

		result.put("total", serviceFileTemplates.size());

		JSONObject object = null;
		for (ServiceFileTemplate serviceFileTemplate : serviceFileTemplates) {

			try {

				object = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(serviceFileTemplate));

				long fileEntryId = serviceFileTemplate.getFileEntryId();

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

				object.put("extension", fileEntry.getExtension());
				object.put("size", fileEntry.getSize());

				resultArray.put(object);

			} catch (Exception e) {
				_log.debug(e);
			}

		}
		result.put("data", resultArray);

		return result.toJSONString();

	}

	@RequestMapping(value = "/fileattach/{className}/{pk}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getAttachFileData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("className") String className, @PathVariable("pk") String pk) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray resultArray = JSONFactoryUtil.createJSONArray();

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader("groupId"))) {
			groupId = Long.valueOf(request.getHeader("groupId"));
		}

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(groupId, className, pk);

		result.put("total", fileAttachs.size());

		JSONObject object = null;
		for (FileAttach ett : fileAttachs) {

			try {

				String newName = ett.getFileName();

				if (newName.indexOf("_") > 0) {

					ett.setFileName(newName.substring(newName.indexOf("_") + 1));

				}

				object = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(ett));

				long fileEntryId = ett.getFileEntryId();

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

				object.put("extension", fileEntry.getExtension());
				object.put("size", fileEntry.getSize());

				resultArray.put(object);

			} catch (Exception e) {
				_log.debug(e);
			}

		}
		result.put("data", resultArray);

		return result.toJSONString();

	}

	@RequestMapping(value = "/users/upload/delete/{code}/{className}/{pk}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String deleteAttachFileData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("className") String className, @PathVariable("pk") String pk,
			@PathVariable("code") String code) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		long groupId = 0;

		if (Validator.isNotNull(request.getHeader("groupId"))) {
			groupId = Long.valueOf(request.getHeader("groupId"));
		}

		List<FileAttach> fileAttachs = FileAttachLocalServiceUtil.findByF_className_classPK(groupId, className, pk);

		for (FileAttach ett : fileAttachs) {

			FileAttachLocalServiceUtil.deleteFileAttach(ett);

		}

		if ("opencps_deliverable".equals(code)) {
			Deliverable openCPSDeliverable = DeliverableLocalServiceUtil
					.fetchDeliverable(Long.valueOf(pk));

			openCPSDeliverable.setFileEntryId(0);

			DeliverableLocalServiceUtil.updateDeliverable(openCPSDeliverable);
		}

		return result.toJSONString();

	}

	@RequestMapping(value = "/users/upload/download/{code}/{className}/{pk}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody byte[] downloadFileAttach(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("className") String className, @PathVariable("pk") String pk,
			@PathVariable("code") String code) {

		try {

			FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(Long.valueOf(pk));

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());

			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + fileEntry.getFileName() + fileEntry.getExtension());

			InputStream inputStream = fileEntry.getContentStream();

			return IOUtils.toByteArray(inputStream);

		} catch (Exception exception) {
			_log.debug(exception);
		}
		return null;
	}

	@RequestMapping(value = "/filetemplate/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeServiceFileTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("serviceInfoId") long serviceInfoId, @PathVariable("fileTemplateNo") String fileTemplateNo) {

		try {
			ServiceFileTemplate serviceFileTemplate = ServiceFileTemplateLocalServiceUtil
					.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, fileTemplateNo);

			long fileEntryId = serviceFileTemplate.getFileEntryId();

			ServiceFileTemplateLocalServiceUtil.deleteServiceFileTemplate(serviceFileTemplate);
			DLAppLocalServiceUtil.deleteFileEntry(fileEntryId);

		} catch (Exception e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/filetemplate/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody byte[] downloadServiceFileTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("serviceInfoId") long serviceInfoId, @PathVariable("fileTemplateNo") String fileTemplateNo) {

		ServiceFileTemplate serviceFileTemplate = ServiceFileTemplateLocalServiceUtil
				.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, fileTemplateNo);

		if (Validator.isNotNull(serviceFileTemplate)) {
			try {

				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(serviceFileTemplate.getFileEntryId());

				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + serviceFileTemplate.getTemplateName() + fileEntry.getExtension());

				InputStream inputStream = fileEntry.getContentStream();

				return IOUtils.toByteArray(inputStream);

			} catch (Exception exception) {
				_log.debug(exception);
			}
		}
		return null;
	}

	@RequestMapping(value = "/filetemplate/{serviceInfoId}/{fileTemplateNo}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_OCTET_STREAM)
	@ResponseStatus(HttpStatus.OK)
	public void upDateServiceFileTemplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("serviceInfoId") long serviceInfoId, @PathVariable("fileTemplateNo") String fileTemplateNo,
			@RequestBody FileTemplateMiniItem fileTemplateMiniItem) {

		ServiceFileTemplate serviceFileTemplate = ServiceFileTemplateLocalServiceUtil
				.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, fileTemplateNo);

		ServiceFileTemplatePK serviceFileTemplatePK = new ServiceFileTemplatePK(serviceInfoId, fileTemplateNo);

		ServiceFileTemplate serviceFileTemplateNew;
		try {
			serviceFileTemplateNew = ServiceFileTemplateLocalServiceUtil.getServiceFileTemplate(serviceFileTemplatePK);

			ServiceFileTemplateLocalServiceUtil.deleteServiceFileTemplate(serviceFileTemplate);

			if (Validator.isNotNull(serviceFileTemplateNew)) {

				if (Validator.isNotNull(fileTemplateMiniItem.getFileTemplateNo())) {
					serviceFileTemplateNew.setFileTemplateNo(fileTemplateMiniItem.getFileTemplateNo());
				}
				if (Validator.isNotNull(fileTemplateMiniItem.getTemplateName())) {
					serviceFileTemplateNew.setTemplateName(fileTemplateMiniItem.getTemplateName());
				}

				ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplate(serviceFileTemplateNew);
			}
		} catch (PortalException e) {
			_log.debug(e);
		}

	}

	@RequestMapping(value = "/upload/", method = RequestMethod.POST)
	public String uploadFile(MultipartHttpServletRequest request) {
		//CommonsMultipartFile multipartFile = null; // multipart file class depends on which class you use assuming you
													// are using
													// org.springframework.web.multipart.commons.CommonsMultipartFile

		//Iterator<String> iterator = request.getFileNames();

		//while (iterator.hasNext()) {
			//String key = (String) iterator.next();
			// create multipartFile array if you upload multiple files
			//multipartFile = (CommonsMultipartFile) request.getFile(key);
		//}

		//try {
			//System.out.println("LiferayRestController.uploadFile()" + multipartFile.getInputStream());
		//} catch (IOException e) {
		//	_log.debug(e);
		//}

		return "sdfds";
	}

	@RequestMapping(value = "/jexcel/{bundleName}/{modelName}/{serviceName}/{idCol}/{textCol}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getJExcelAutoComplate(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("bundleName") String bundleName, @PathVariable("modelName") String modelName,
			@PathVariable("serviceName") String serviceName, @PathVariable("idCol") String idCol,
			@PathVariable("textCol") String textCol) {

		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {

			BundleLoader bundleLoader = new BundleLoader(bundleName);

			Class<?> model = bundleLoader.getClassLoader().loadClass(modelName);

			Method method = bundleLoader.getClassLoader().loadClass(serviceName).getMethod("dynamicQuery");

			DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);

			ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

			projectionList.add(ProjectionFactoryUtil.property(idCol));
			projectionList.add(ProjectionFactoryUtil.property(textCol));

			dynamicQuery.setProjection(projectionList);

			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
			disjunction.add(RestrictionsFactoryUtil.eq("groupId", 0l));
			if (Validator.isNotNull(request.getHeader("groupId"))) {
				disjunction.add(RestrictionsFactoryUtil.eq("groupId", Long.valueOf(request.getHeader("groupId"))));
			}
			dynamicQuery.add(disjunction);

			if (Validator.isNotNull(request.getParameter("pk")) && Validator.isNotNull(request.getParameter("col"))) {
				dynamicQuery.add(PropertyFactoryUtil.forName(request.getParameter("col"))
						.eq(Validator.isNumber(request.getParameter("pk")) ? Long.valueOf(request.getParameter("pk"))
								: request.getParameter("pk")));
			}
			if (Validator.isNotNull(request.getParameter("collectionCode"))
					&& Validator.isNotNull(request.getParameter("column"))
					&& Validator.isNotNull(request.getParameter("type"))) {

				if ("int".equals(request.getParameter("type"))) {
					DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
							request.getParameter("collectionCode"), Long.valueOf(request.getHeader("groupId")));

					if (Validator.isNotNull(dictCollection)) {
						dynamicQuery.add(PropertyFactoryUtil.forName(request.getParameter("column"))
								.eq(dictCollection.getDictCollectionId()));
					}
				} else {
					dynamicQuery.add(PropertyFactoryUtil.forName(request.getParameter("column"))
							.eq(request.getParameter("collectionCode")));
				}
			}

			method = bundleLoader.getClassLoader().loadClass(serviceName).getMethod("dynamicQuery", DynamicQuery.class,
					int.class, int.class);

			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) method.invoke(model, dynamicQuery, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			//_log.info("List object: "+JSONFactoryUtil.looseSerialize(list));
			JSONObject object = null;
			for (Object[] objects : list) {

				object = JSONFactoryUtil.createJSONObject();

				//_log.info("objects[0]: "+objects[0]);
				object.put("id", objects[0]);

				if (modelName.equals(EmployeeJobPos.class.getName())) {

					long jobPostId = (long) objects[1];

					JobPos jobPos = JobPosLocalServiceUtil.fetchJobPos(jobPostId);

					String name = Validator.isNotNull(jobPos) ? jobPos.getTitle() : StringPool.BLANK;

					object.put("name", name);
					//_log.debug("name: "+name);

				} else {
					object.put("name", objects[1]);
					//_log.debug("name: "+objects[1]);
				}

				result.put(object);

				//_log.info("result: "+JSONFactoryUtil.looseSerialize(result));
			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return result.toJSONString();

	}

	@RequestMapping(value = "/users/{id}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	public ResponseEntity<UsersUserItem> getUserById(HttpServletRequest request, HttpServletResponse response,
			@ApiParam(value = "id của user", required = true) @PathVariable("id") String id) {

		if (Validator.isNull(id)) {
			throw new OpenCPSNotFoundException(User.class.getName());

		} else {

			long userIdPath = Long.valueOf(id);
			long userId = GetterUtil.getLong(request.getAttribute(WebKeys.USER_ID).toString());
			long groupId = GetterUtil.getLong(request.getHeader(Field.GROUP_ID));
			
			if (userId == 0 || (userId > 0 && userId != userIdPath)) {
				throw new OpenCPSNotFoundException(User.class.getName());
			}
			UserActions actions = new UserActions();

			String userData = actions.getUserById(Long.valueOf(id), groupId);

			if (Validator.isNull(userData)) {
				throw new OpenCPSNotFoundException(User.class.getName());
			}
			_log.info("UserData: " + JSONFactoryUtil.looseSerialize(userData));
//			List<Dossier> dossiers = DossierLocalServiceUtil.findByG_U_DO(groupId,userId);
//			try {
//				if (dossiers != null) {
//					Applicant checkApplicant = null;
//					boolean checkDone = false;
//					boolean checkReceiving = false;
//					int countDossier = 0;
//					for (Dossier dossier : dossiers) {
//						checkApplicant = dossier.getUserId() > 0 ? ApplicantLocalServiceUtil.fetchByMappingID(dossier.getUserId()) : null;
//						if (checkApplicant != null) {
//							if (DossierTerm.DOSSIER_STATUS_DONE.contentEquals(dossier.getDossierStatus())
//									&& dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
//								checkDone = true;
//								break;
//							} else if (DossierTerm.DOSSIER_STATUS_RECEIVING.contentEquals(dossier.getDossierStatus())
//									&& dossier.getOriginality() == DossierTerm.ORIGINALITY_DVCTT) {
//								countDossier = DossierLocalServiceUtil.countByG_UID_DS(dossier.getGroupId(), dossier.getUserId(),
//										DossierTerm.DOSSIER_STATUS_RECEIVING);
//								checkReceiving = true;
//							}
//						}
//					}
//					if (Validator.isNotNull(checkApplicant)) {
//						if (checkReceiving && !checkDone) {
//							_log.debug("DOSSIER_STATUS_RECEIVING ");
//							_log.debug("APPLICANT NUMBER OF CREATE DOSSIER: " + countDossier);
//							ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, ServerConfigTerm.COUNTER_VERIFY_CREATEDOSSIER);
//							if (Validator.isNotNull(serverConfig)) {
//								JSONObject configObj = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
//								int counter = Integer.valueOf(configObj.getString(DossierTerm.COUNTER));
//								_log.debug("CONFIG COUNTER " + counter);
//								if (Validator.isNotNull(counter)) {
//									if (countDossier >= counter) {
//											checkApplicant.setVerification(ApplicantTerm.LOCKED_DOSSIER);
//											ApplicantLocalServiceUtil.updateApplicant(checkApplicant);
//									} else if (countDossier < counter) {
//										checkApplicant.setVerification(ApplicantTerm.UNLOCKED);
//										ApplicantLocalServiceUtil.updateApplicant(checkApplicant);
//									}
//								}
//							}
//						} else if (checkDone) {
//							_log.debug(" DOSSIER_STATUS_DONE");
//							if (checkApplicant.getVerification() == ApplicantTerm.LOCKED
//									|| checkApplicant.getVerification() == ApplicantTerm.LOCKED_DOSSIER) {
//								checkApplicant.setVerification(ApplicantTerm.UNLOCKED);
//								ApplicantLocalServiceUtil.updateApplicant(checkApplicant);
//							}
//						}
//					}
//				}
//			}catch (Exception e){
//				e.getMessage();
//			}

			String token = GraphQLUtils.buildTokenLogin(userData, groupId);
			_log.info("Token: " + token);
			response.setHeader("jwt-token", token);

			return new ResponseEntity<UsersUserItem>(JSONFactoryUtil.looseDeserialize(userData, UsersUserItem.class),
					HttpStatus.OK);

		}

	}

	@RequestMapping(value = "/fileattach/{id}/text", produces = {
			"text/plain; charset=utf-8" }, method = RequestMethod.GET)
	public @ResponseBody String getTextFromFileEntryId(HttpServletResponse response,
			@ApiParam(value = "id của user", required = true) @PathVariable("id") Long id) {

		String result = StringPool.BLANK;
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		InputStream is = null;

		try {

			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(id);

			is = dlFileEntry.getContentStream();

			result = IOUtils.toString(is, StandardCharsets.UTF_8);

		} catch (Exception e) {
			_log.debug(e);
			result = StringPool.BLANK;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}

		return result.toString();

	}

	@RequestMapping(value = "/deliverable/test/{type}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getDeliverable(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("type") String type, @QueryParam("start") Integer start, @QueryParam("end") Integer end,
			@QueryParam("keyword") String keyword,
			@QueryParam("formDataKey") String formDataKey) {

		System.out.println("VAO CHUAE :"+request.getAttribute(WebKeys.USER_ID));
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {

			long userId = 0;
			if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
				userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());

				long groupId = 0;

				if (Validator.isNotNull(request.getHeader("groupId"))) {
					groupId = Long.valueOf(request.getHeader("groupId"));
				}

				try {
					String[] subQuerieArr = new String[] { DeliverableTerm.DELIVERABLE_TYPE, DeliverableTerm.DELIVERABLE_NAME,
							DeliverableTerm.GOV_AGENCY_NAME, DeliverableTerm.APPLICANT_NAME,
							DeliverableTerm.DELIVERABLE_CODE_SEARCH };
					String queryBuilder = StringPool.BLANK;
					String queryBuilderLike = StringPool.BLANK;
					StringBuilder sbBuilder = new StringBuilder();
					if (Validator.isNotNull(keyword)) {
						//LamTV_Process search LIKE
						String keySearch = SpecialCharacterUtils.splitSpecial(keyword);
						sbBuilder.append(" AND (");
						int length = subQuerieArr.length;
						for (int i = 0; i < length; i++) {
							sbBuilder.append(subQuerieArr[i] + ": *" + keySearch + "*");
							if (i < length - 1) {
								sbBuilder.append(" OR ");
							} else {
								sbBuilder.append(" ) ");
							}
						}
					} else {
						DeliverableTypesActions actions = new DeliverableTypesActionsImpl();

						DeliverableType deliverableType = actions.getByTypeCode(userId, groupId, type,
								new ServiceContext());

						JSONArray filterData = JSONFactoryUtil.createJSONArray(deliverableType.getDataConfig());

						for (int i = 0; i < filterData.length(); i++) {

							if (Validator
									.isNotNull(request.getParameter(filterData.getJSONObject(i).getString("fieldName")))) {

								if ("like".equals(filterData.getJSONObject(i).getString("compare"))) {

									queryBuilderLike += " AND " + filterData.getJSONObject(i).getString("fieldName") + ": *"
											+ request.getParameter(filterData.getJSONObject(i).getString("fieldName"))
											+ "*";

								} else {

									queryBuilder += " AND " + filterData.getJSONObject(i).getString("fieldName") + ":"
											+ request.getParameter(filterData.getJSONObject(i).getString("fieldName"));

								}

							}

						}
					}
					
					String queryDataFrom = GraphQLUtils.buildDeliverableSearchDataForm(formDataKey);

					System.out.println("queryBuilderLike:" + queryBuilderLike);
					System.out.println("queryBuilder:" + queryBuilder);
					int size = 0;
					if (Validator.isNull(end) || end == 0) {
						start = -1;
						//end = -1;
						size = -1;
					} else {
						size = end - start;
					}

					JSONObject query = JSONFactoryUtil.createJSONObject(" { \"from\" : " + start
							+ ", \"size\" : " + size
							+ ", \"sort\" : [{\"issueDate_Number_sortable\" : { \"order\" : \"desc\"}}]"
							+ ", \"query\": { \"query_string\": { \"query\" : \"(entryClassName:(entryClassName:org.opencps.dossiermgt.model.Deliverable) AND groupId:"
							+ groupId + " AND deliverableType: " + type + queryBuilder + queryBuilderLike + sbBuilder.toString() + queryDataFrom + " )\" }}"
							+ "}");

					JSONObject countQuery = JSONFactoryUtil.createJSONObject(" { "
							+ "\"query\": { \"query_string\": { \"query\" : \"(entryClassName:(entryClassName:org.opencps.dossiermgt.model.Deliverable) AND groupId:"
							+ groupId + " AND deliverableType: " + type + queryBuilder + queryBuilderLike + sbBuilder.toString() + queryDataFrom + " )\" }}"
							+ "}");

					System.out.println("query:" + query);
					JSONObject count = ElasticQueryWrapUtil.count(countQuery.toJSONString());

					System.out.println("RestfulController.getDeliverable(count)" + count.toJSONString());

					result = ElasticQueryWrapUtil.query(query.toJSONString());

					result.getJSONObject("hits").put("total", count.getLong("count"));

				} catch (JSONException e) {
					_log.debug(e);
				}

			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return result.toJSONString();
	}

	@RequestMapping(value = "/deliverable/{type}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getDeliverableTest(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("type") String type, @QueryParam("start") Integer start, @QueryParam("end") Integer end,
			@QueryParam("keyword") String keyword,
			@QueryParam("formDataKey") String formDataKey) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {

			long userId = 0;
			if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
				userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
				long groupId = 0;

				if (Validator.isNotNull(request.getHeader("groupId"))) {
					groupId = Long.valueOf(request.getHeader("groupId"));
				}

//				String[] subQuerieArr = new String[] { DeliverableTerm.DELIVERABLE_TYPE,
//						DeliverableTerm.DELIVERABLE_NAME, DeliverableTerm.GOV_AGENCY_NAME,
//						DeliverableTerm.APPLICANT_NAME, DeliverableTerm.DELIVERABLE_CODE_SEARCH };
				//String queryBuilder = StringPool.BLANK;
				//String queryBuilderLike = StringPool.BLANK;
				//StringBuilder sbBuilder = new StringBuilder();
				Map<String, String> mapFilter = null;
				String keySearch = null;
				if (Validator.isNotNull(keyword)) {
					// LamTV_Process search LIKE
					keySearch = SpecialCharacterUtils.splitSpecial(keyword);
				} else {
					//DeliverableTypesActions actions = new DeliverableTypesActionsImpl();
					DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.getByTypeCode(type, groupId);
					//Lấy danh sách deliverable theo deliverableType ==> null ==> lấy theo groupId = 0
					if(Validator.isNull(deliverableType)){
						deliverableType = DeliverableTypeLocalServiceUtil.getByTypeCode(type, 0);
					}

					JSONArray filterData = JSONFactoryUtil.createJSONArray(deliverableType.getDataConfig());

					if (filterData != null && filterData.length() > 0) {
						mapFilter = new HashMap<String, String>();
						//
						for (int i = 0; i < filterData.length(); i++) {
							JSONObject jsonDetail = filterData.getJSONObject(i);
							if (Validator.isNotNull(request.getParameter(jsonDetail.getString("fieldName")))) {

								if ("like".equals(jsonDetail.getString("compare"))) {

									mapFilter.put(jsonDetail.getString("fieldName") + "@LIKE",
											request.getParameter(jsonDetail.getString("fieldName")));

								} else {
									mapFilter.put(jsonDetail.getString("fieldName") + "@EQUAL",
											request.getParameter(jsonDetail.getString("fieldName")));

								}

							}

						}
					}
					
				}

				mapFilter = GraphQLUtils.buildSearchDataForm(formDataKey, mapFilter);

				System.out.println("mapFilter:" + mapFilter);
				if (Validator.isNull(end) || end == 0) {
					start = -1;
					end = -1;
					// size = -1;
				}

				Hits hits = null;

				SearchContext searchContext = new SearchContext();
				searchContext.setCompanyId(20099);

				Sort[] sorts = new Sort[] { SortFactoryUtil.create("issueDate_Number_sortable", Sort.LONG_TYPE, true) };
				try {

					hits = DeliverableLocalServiceUtil.searchLucene(keySearch, String.valueOf(groupId), type, mapFilter, sorts,
							start, end, searchContext, userId);

					if 	(hits != null) {
						List<Document> docList = hits.toList();
						JSONArray data = DeliverableUtils.mappingToDeliverableResult(docList,userId,groupId);
						result.put(ConstantUtils.DATA, data);
						//System.out.println("hits: " + hits.toList());
						//
//						if (hits.toList() != null && hits.toList().size() > 0) {
//							Document doc = hits.toList().get(0);
//							Map<String, Field> map = doc.getFields();
	//
//							if (map != null && map.size() > 0) {
//								for (Map.Entry<String, Field> entry: map.entrySet()) {
//									int i = 0;
//									if (i < 2) {
//										String key1 = entry.getKey();
//										Field value1 = entry.getValue();
//										i++;
//										System.out.println("key1: " + key1 + "| value1: "+value1.getValue());
//									}
//								}
//							}
//						}

						long total = DeliverableLocalServiceUtil.countLucene(keySearch, String.valueOf(groupId), type, mapFilter,
								searchContext, userId);

						result.put(ConstantUtils.TOTAL, total);
						System.out.println("total: " + total);
					} else {
						result.put(ConstantUtils.TOTAL, 0);
					}

				} catch (Exception e) {
					_log.error(e);
				}

			}

		} catch (Exception e) {
			_log.debug(e);
		}

		System.out.println("result: " + result);
		return result.toJSONString();
	}

	@RequestMapping(value = "/deliverable/{id}/detail", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getDeliverableById(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			long userId = 0;
			if (Validator.isNotNull(request.getAttribute(WebKeys.USER_ID))) {
				userId = Long.valueOf(request.getAttribute(WebKeys.USER_ID).toString());
			}
				long groupId = 0;
			if (Validator.isNotNull(request.getHeader("groupId"))) {
				groupId = Long.valueOf(request.getHeader("groupId"));
			}

				//JSONObject query = JSONFactoryUtil.createJSONObject(
				//		" { \"from\" : 0, \"size\" : 1, \"query\": { \"query_string\": { \"query\" : \"(entryClassName:(entryClassName:org.opencps.deliverable.model.OpenCPSDeliverable) AND groupId:"
				//				+ groupId + " AND entryClassPK: " + id + " )\" }}}");
				//result = ElasticQueryWrapUtil.query(query.toJSONString());

			Deliverable deliverable = DeliverableLocalServiceUtil.fetchDeliverable(id);
			JSONObject jsonObject = DeliverableUtils.mappingToDeliverable(deliverable);
			DeliverableTypesActions actions = new DeliverableTypesActionsImpl();
			try {
				User user = UserLocalServiceUtil.getUser(userId);
				Long[] longObjects = ArrayUtils.toObject(user.getRoleIds());
				List<Long> roleIds = Arrays.asList(longObjects);
				String typeValue = jsonObject.getString(DeliverableTerm.DELIVERABLE_TYPE);
				if (Validator.isNotNull(typeValue)) {
					DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.fetchByG_DLT(groupId, typeValue);
					if (Validator.isNotNull(deliverableType)) {
						List<DeliverableTypeRole> deliverableTypeRoles = actions.getRolesByType(deliverableType.getDeliverableTypeId());
						if (deliverableTypeRoles != null && deliverableTypeRoles.size() > 0) {
							for (DeliverableTypeRole deliverableTypeRole : deliverableTypeRoles) {
								if (roleIds.contains(deliverableTypeRole.getRoleId()) && deliverableTypeRole.getModerator()) {
										jsonObject.put(DeliverableTerm.MODERATOR, deliverableTypeRole.getModerator());
								}
							}
						}
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			if (jsonObject != null) {
				result.put(ConstantUtils.DATA,jsonObject);
				return JSONFactoryUtil.looseSerialize(result);
			}

		} catch (Exception e) {
			_log.debug(e);
		}

		return StringPool.BLANK;
	}

	@RequestMapping(value = "/deliverable/file/{id}", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {

		String result = StringPool.BLANK;

		DLFileEntry fileEntry;
		try {

			fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(id);

			boolean flagCheckFile = CheckFileUtils.checkDLFileEntryUpload(fileEntry);
			if (!flagCheckFile) {
				throw new ResourceNotFoundException();
			}
			result = "/documents/" + fileEntry.getGroupId() + StringPool.FORWARD_SLASH + fileEntry.getFolderId()
					+ StringPool.FORWARD_SLASH + fileEntry.getTitle() + StringPool.FORWARD_SLASH + fileEntry.getUuid();

		} catch (Exception e) {
			_log.debug(e);
		}

		return result;
	}

	@RequestMapping(value = "/admin/{bundleName}/{modelName}/{serviceName}/data", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getAdminToolData(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("bundleName") String bundleName, @PathVariable("modelName") String modelName,
			@PathVariable("serviceName") String serviceName) {

		// JSONArray result = JSONFactoryUtil.createJSONArray();
		String result = JSONFactoryUtil.createJSONArray().toJSONString();
		try {

			BundleLoader bundleLoader = new BundleLoader(bundleName);

			System.out.println("RestfulController.getAdminToolData(bundleLoader)" + bundleLoader.getClassLoader());
			Class<?> model = bundleLoader.getClassLoader().loadClass(modelName);

			System.out.println("RestfulController.getAdminToolData(model)" + model);

			System.out.println("RestfulController.getAdminToolData(serviceName)" + serviceName);
			Method method = bundleLoader.getClassLoader().loadClass(serviceName).getMethod("dynamicQuery");
			System.out.println("RestfulController.getAdminToolData(method)" + method);

			DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);

			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
			disjunction.add(RestrictionsFactoryUtil.eq("groupId", 0l));
			if (Validator.isNotNull(request.getHeader("groupId"))) {
				disjunction.add(RestrictionsFactoryUtil.eq("groupId", Long.valueOf(request.getHeader("groupId"))));
			}
			dynamicQuery.add(disjunction);

			method = bundleLoader.getClassLoader().loadClass(serviceName).getMethod("dynamicQuery", DynamicQuery.class,
					int.class, int.class);

			result = JSONFactoryUtil.looseSerialize(method.invoke(model, dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS)).toString();

		} catch (Exception e) {
			_log.debug(e);
		}

		return result;

	}
	
	@RequestMapping(value = "/site/name", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseStatus(HttpStatus.OK)
	public String getSiteName(HttpServletRequest request, HttpServletResponse response) {

		String result = StringPool.BLANK;
		
		long groupId = 0;

		if (Validator.isNotNull(request.getHeader("groupId"))) {
			groupId = Long.valueOf(request.getHeader("groupId"));
		}
		
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);

		if (Validator.isNotNull(group)) {
			
			result = group.getGroupKey();
			
		}
		
		return result.toUpperCase();

	}
	
	@RequestMapping(value = "/users/login/jcaptcha", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource> getJCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
			
		    String captchaId = request.getSession().getId();
			File destDir = new File("jcaptcha");
			if (!destDir.exists()) {
				destDir.mkdir();
			}
			File file = new File("jcaptcha/" + captchaId  + ".png");
			if (!file.exists()) {
				file.createNewFile();				
			}
	
			if (file.exists()) {
			    BufferedImage challengeImage = instance.getImageChallengeForID(
			    captchaId, Locale.US );
			    try {
					ImageIO.write( challengeImage, "png", file );
				    
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		
			Path path = Paths.get(file.getAbsolutePath());
		    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		    return ResponseEntity.ok()
		            .headers(new HttpHeaders())
		            .contentLength(file.length())
		            .contentType(org.springframework.http.MediaType.parseMediaType("application/octet-stream"))
		            .body(resource);
		}
		catch (Exception e) {
			_log.debug(e);
			return null;
		}
		
	}

	public static final Log _log = LogFactoryUtil.getLog(RestfulController.class);
}
