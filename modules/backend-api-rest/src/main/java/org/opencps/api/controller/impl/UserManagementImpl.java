package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.UserManagement;
import org.opencps.api.controller.util.ApplicantUtils;
import org.opencps.api.controller.util.CaptchaServiceSingleton;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.UserUtils;
import org.opencps.api.jobpos.model.JobposPermissionResults;
import org.opencps.api.user.model.UserAccountModel;
import org.opencps.api.user.model.UserModel;
import org.opencps.api.user.model.UserProfileModel;
import org.opencps.api.user.model.UserResults;
import org.opencps.api.user.model.UserRolesResults;
import org.opencps.api.user.model.UserSitesResults;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.communication.utils.LGSPRestfulUtils;
import org.opencps.kernel.prop.PropValues;
import org.opencps.usermgt.action.JobposInterface;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.action.impl.JobposActions;
import org.opencps.usermgt.action.impl.UserActions;
import org.opencps.usermgt.constants.UserRegisterTerm;
import org.opencps.usermgt.constants.UserTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.scheduler.utils.RegisterLGSPUtils;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.springframework.dao.PermissionDeniedDataAccessException;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;

public class UserManagementImpl implements UserManagement {

	private static final Log _log = LogFactoryUtil.getLog(UserManagementImpl.class);

	@Override
	public Response getUserPhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();

		try {

			File file = actions.getPhoto(id, serviceContext);

			String type = actions.getType(id, serviceContext);

			ResponseBuilder responseBuilder = Response.ok((Object) file);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.API_IMAGE_EXTENSION + type);

			return responseBuilder.build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response uploadPhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		UserInterface actions = new UserActions();
		InputStream inputStream = null;

		try {
			DataHandler dataHandler = attachment.getDataHandler();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			inputStream = dataHandler.getInputStream();
			
			

			File file = actions.uploadPhoto(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, ConstantUtils.USER_PHOTO_FOLDER, ConstantUtils.USER_PHOTO_DESC, serviceContext);

			String type = actions.getType(id, serviceContext);

			ResponseBuilder responseBuilder = Response.ok((Object) file);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.API_IMAGE_EXTENSION + type);

			return responseBuilder.build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response getUserProfile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserProfileModel result = new UserProfileModel();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			Document document = actions.getUserProfile(id, groupId, serviceContext);

			result = UserUtils.mapperUserProfileModel(document);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getSites(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserSitesResults result = new UserSitesResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			JSONObject jsonData = actions.getSites(id, groupId, serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getUserSitesModel().addAll(UserUtils.mapperUserSitesList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getRoles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserRolesResults result = new UserRolesResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			JSONObject jsonData = actions.getRoles(id, groupId, serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getUserRolesModel().addAll(UserUtils.mapperUserRolesList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getPreferences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String result = actions.getPreference(id, groupId, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getPreferenceByKey(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String key) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String result = actions.getPreferenceByKey(id, groupId, key, serviceContext);
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addPreferences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String preferences) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String result = actions.addPreferences(id, groupId, preferences, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updatePreferences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String key, String value) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String result = actions.updatePreferences(id, groupId, key, value, serviceContext);
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addChangepass(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String oldPassword, String newPassword) {
		UserInterface actions = new UserActions();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		BackendAuth auth = new BackendAuthImpl();
		
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			if (user == null || (user.getUserId() != id && !auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER))) {
				throw new PermissionDeniedDataAccessException(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION), null);
			}
			_log.info("groupId: "+groupId+ "|company.getCompanyId(): "+company.getCompanyId()+"|id: "+id
					+"oldPass: "+oldPassword+ "|newPassword: "+newPassword);
			int flagNo = actions.addChangepass(groupId, company.getCompanyId(), id, oldPassword, newPassword,
					serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(flagNo)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}
	}

	@Override
	public Response getPermissions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String full) {
		JobposInterface actions = new JobposActions();
		JobposPermissionResults result = new JobposPermissionResults();
		try {

			JSONObject jsonData = actions.getJobposPermissions();

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getJobposPermissionModel()
					.addAll(UserUtils.mapperUsersPermissionsList((String[]) jsonData.get(ConstantUtils.DATA), id, serviceContext));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getForgot(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String screenname_email, String jCaptchaResponse) {
		UserInterface actions = new UserActions();
		String captchaType = PropValues.CAPTCHA_TYPE;
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			if (Validator.isNotNull(captchaType) && "jcaptcha".contentEquals(captchaType)) {
				ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
				String captchaId = request.getSession().getId();
		        try {
		        	_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
		        	boolean isResponseCorrect = instance.validateResponseForID(captchaId,
		        			jCaptchaResponse);
		        	_log.info("Check captcha result: " + isResponseCorrect);
		        	if (!isResponseCorrect) {
		        		ErrorMsgModel error = new ErrorMsgModel();
		        		error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
		    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
		    			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
	
		    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		        	}
		        } catch (CaptchaServiceException e) {
		        	_log.debug(e);
	        		ErrorMsgModel error = new ErrorMsgModel();
	        		error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
	    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
	    			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
	
	    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
		        }
			} else {		
			ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();		
				boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
						serviceContext, jCaptchaResponse);
				
				if (!isValid) {
					ErrorMsgModel error = new ErrorMsgModel();
					error.setMessage("Captcha incorrect");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Captcha incorrect");
	
					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
				}
			}
			
			Document document = actions.getForgot(groupId, company.getCompanyId(), screenname_email, serviceContext);

			UserAccountModel userAccountModel = UserUtils.mapperUserAccountModel(document);

			return Response.status(HttpURLConnection.HTTP_OK).entity(userAccountModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getForgotConfirm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String screenname_email, String code, String jCaptchaResponse) {
		UserInterface actions = new UserActions();
		
		String captchaType = PropValues.CAPTCHA_TYPE;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if (Validator.isNotNull(captchaType) && "jcaptcha".equals(captchaType)) {
				
			ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
			String captchaId = request.getSession().getId();
	        try {
	        	_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
	        	boolean isResponseCorrect = instance.validateResponseForID(captchaId,
	        			jCaptchaResponse);
	        	_log.info("Check captcha result: " + isResponseCorrect);
	        	if (!isResponseCorrect) {
	        		ErrorMsgModel error = new ErrorMsgModel();
	        		error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
	    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
	    			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));

	    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
	        	}
	        } catch (CaptchaServiceException e) {
	        	_log.debug(e);
        		ErrorMsgModel error = new ErrorMsgModel();
        		error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
    			error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
    			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));

    			return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
	        }
			}else {
				ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();
				
				boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
						serviceContext, jCaptchaResponse);
			
				if (!isValid) {
					ErrorMsgModel error = new ErrorMsgModel();
					error.setMessage("Captcha incorrect");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Captcha incorrect");

					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
				}
			}
			
			Document document = null;
			boolean syncUserLGSP = Validator.isNotNull(PropsUtil.get("opencps.register.lgsp"))
					? GetterUtil.getBoolean(PropsUtil.get("opencps.register.lgsp")) : false;
			if (syncUserLGSP) {
				//Forgot LGSP
				// Create a trust manager that does not validate certificate chains
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
				
				//
//				String strToken = ApplicantUtils.getTokenLGSP();
//				if (Validator.isNotNull(strToken)) {
//					JSONObject jsonToken = JSONFactoryUtil.createJSONObject(strToken);
//					//
//					if (jsonToken.has("access_token") && jsonToken.has("token_type")
//							&& Validator.isNotNull(jsonToken.getString("access_token"))
//							&& Validator.isNotNull(jsonToken.getString("token_type"))) {
//						String accessToken = jsonToken.getString("access_token");
//						String tokenType = jsonToken.getString("token_type");
//
//						_log.info("accessToken: " + accessToken);
//						_log.info("tokenType: " + tokenType);
//
//						// Dang ky tk cong dan
//						String message = RegisterLGSPUtils.forgotLGSP(jsonToken, screenname_email);
//						if (Validator.isNull(message)) {
//							return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("{error}").build();
//						} else {
//							JSONObject jsonMessage =  JSONFactoryUtil.createJSONObject(message);
//							if (jsonMessage != null && jsonMessage.has("matKhau") && jsonMessage.has("taiKhoan")
//									&& Validator.isNotNull(jsonMessage.getString("matKhau"))
//									&& Validator.isNotNull(jsonMessage.getString("taiKhoan"))) {
//								secretKey = jsonMessage.getString("matKhau");
//								
//							} else {
//								return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("{error}").build();
//							}
//						}
//					}
//				} else {
//					return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("{error}").build();
//				}
				String secretKey = StringPool.BLANK;
				try {
					/** Get Token */
					// String strToken = ApplicantUtils.getTokenNewLGSP();
					// _log.debug("RESULT PROXY: " + strToken);
					// if (Validator.isNotNull(strToken)) {
					// JSONObject jsonToken = JSONFactoryUtil.createJSONObject(strToken);
					JSONObject jsonToken = LGSPRestfulUtils.createTokenLGSP("Bearer");
					//
					if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
							&& jsonToken.has("expiryDate")) {
						String accessToken = jsonToken.getString("token");
						String refreshToken = jsonToken.getString("refreshToken");

						_log.info("accessToken: " + accessToken);
						_log.info("refreshToken: " + refreshToken);

						String strResult = RegisterLGSPUtils.forgotNewLGSP("Bearer", accessToken, screenname_email);

						boolean flagFogot = false;
						if (Validator.isNotNull(strResult)) {
							JSONObject jsonResult = JSONFactoryUtil.createJSONObject(strResult);
							if (jsonResult != null && jsonResult.has("password") && jsonResult.has("userName")
									&& Validator.isNotNull(jsonResult.getString("password"))
									&& Validator.isNotNull(jsonResult.getString("userName"))) {
								flagFogot = true;
								secretKey = jsonResult.getString("password");
							}
						}

						if (!flagFogot) {
							ErrorMsgModel error = new ErrorMsgModel();
							error.setMessage("Change pass error");
							error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
							error.setDescription("Change pass error");
							return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();
						}
					}
					// }
				} catch (Exception e) {
					_log.error(e);
					_log.debug("Something went wrong while reading/writing in stream!!");
					return BusinessExceptionImpl.processException(e);
				}
				
				_log.info("secretKey: "+secretKey);
				document = actions.getLGSPForgotConfirm(groupId, company.getCompanyId(), screenname_email, code,
						secretKey, serviceContext);
			} else {
				document = actions.getForgotConfirm(groupId, company.getCompanyId(), screenname_email, code,
						serviceContext);

			}
			
			if (document != null) {
				UserAccountModel userAccountModel = UserUtils.mapperUserAccountModel(document);

				return Response.status(HttpURLConnection.HTTP_OK).entity(userAccountModel).build();
			} else {
				ErrorMsgModel error = new ErrorMsgModel();
				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("No Content.");
//				e.printStackTrace();
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getUsers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext) {
		UserInterface actions = new UserActions();
		UserResults result = new UserResults();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			JSONObject jsonData = actions.getUsers(groupId, serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getUserModel().addAll(UserUtils.mapperUserList((List<User>) jsonData.get(ConstantUtils.DATA), groupId));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getUserById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		UserInterface actions = new UserActions();
		UserModel userModel = new UserModel();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			User userCustom = actions.getUserById(groupId, company.getCompanyId(), id, serviceContext);

			userModel = UserUtils.mapperUserModel(userCustom, groupId);

			return Response.status(HttpURLConnection.HTTP_OK).entity(userModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getCheckpass(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String password) {
		UserInterface actions = new UserActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			boolean flag = actions.getCheckpass(groupId, company.getCompanyId(), id, password, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(flag)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getUserWorks(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		return null;
	}

	@Override
	public Response uploadEsign(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {

//		UserInterface actions = new UserActions();
		InputStream inputStream = null;

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {
			DataHandler dataHandler = attachment.getDataHandler();
			// long groupId =
			// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			
			inputStream = dataHandler.getInputStream();
			BufferedImage image = ImageIO.read(inputStream);
			
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
			
//			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + "data/cer/" + employee.getEmail() + StringPool.PERIOD + "png";
			String buildFileName = String.format(MessageUtil.getMessage(ConstantUtils.DATACER_PATH), PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH, employee.getEmail());
			File targetFile = new File(buildFileName);

			ImageIO.write(image, ConstantUtils.PNG, targetFile);
			
			_log.info("Absolute Path buildFileName " + buildFileName);
			
			//FileUtils.copyInputStreamToFile(inputStream, targetFile);
			
			EmployeeLocalServiceUtil.updatePayload(id, groupId, 0, 0, StringPool.BLANK, buildFileName, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(buildFileName).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response uploadEsignCert(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {

//		UserInterface actions = new UserActions();
		InputStream inputStream = null;

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {

			DataHandler dataHandler = attachment.getDataHandler();
			// long groupId =
			// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			inputStream = dataHandler.getInputStream();
			
			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);

			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + ConstantUtils.DATA_CER + employee.getEmail() + StringPool.PERIOD + ConstantUtils.CER;
			File targetFile = new File(buildFileName);

			try (FileOutputStream outStream = new FileOutputStream(targetFile)) {

				int bytesRead = -1;
				byte[] buffer = new byte[4096];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
	
				outStream.close();
				inputStream.close();
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(buildFileName).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}

	}

	@Override
	public Response getUserEsign(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

//		UserInterface actions = new UserActions();

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {


			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
			
			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + ConstantUtils.DATA_CER + employee.getEmail() + StringPool.PERIOD + ConstantUtils.PNG;
			File targetFile = new File(buildFileName);

			ResponseBuilder responseBuilder = Response.ok((Object) targetFile);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), targetFile.getName());
			
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_PNG);

			return responseBuilder.build();
			
			
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getUserEsignCert(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		// HARD CODE groupId = 55301

		long groupId = 55301;

		try {


			Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(groupId, id);
			
			String buildFileName = PropsUtil.get(PropsKeys.LIFERAY_HOME) + StringPool.FORWARD_SLASH + ConstantUtils.DATA_CER + employee.getEmail() + StringPool.PERIOD + ConstantUtils.PNG;
			File targetFile = new File(buildFileName);

			ResponseBuilder responseBuilder = Response.ok((Object) targetFile);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), targetFile.getName());
			
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_X509);

			return responseBuilder.build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}
	}

	@Override
	public Response addChangepassApplication(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String oldPassword, String newPassword) {
		
		UserInterface actions = new UserActions();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		BackendAuth auth = new BackendAuthImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			if (user == null || (user.getUserId() != id && !auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER))) {
				throw new PermissionDeniedDataAccessException(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION), null);
			}

			_log.info("groupId: "+groupId+ "|company.getCompanyId(): "+company.getCompanyId()+"|id: "+id
					+"oldPass: "+oldPassword+ "|newPassword: "+newPassword);
			boolean syncUserLGSP = Validator.isNotNull(PropsUtil.get("opencps.register.lgsp"))
					? GetterUtil.getBoolean(PropsUtil.get("opencps.register.lgsp")) : false;
			if (syncUserLGSP) {

				JSONObject jsonToken = LGSPRestfulUtils.createTokenLGSP("Bearer");
				if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
						&& jsonToken.has("expiryDate")) {

					String strUrlChange = UserRegisterTerm.NEW_BASE_URL + UserRegisterTerm.NEW_ENDPOINT_CHANGE_PASS;
					_log.info("strUrlChange: " + strUrlChange);
					String authStrEnc = "Bearer" + StringPool.SPACE + jsonToken.getString("token");
					_log.info("authStrEnc: " + authStrEnc);

					StringBuilder sbChange = new StringBuilder();
					try {
						URL urlChange = new URL(strUrlChange);

						JSONObject jsonBody = JSONFactoryUtil.createJSONObject();
						jsonBody.put(UserRegisterTerm.USER_NAME, user.getEmailAddress());
						jsonBody.put(UserRegisterTerm.OLD_SECRECT_KEY, oldPassword);
						jsonBody.put(UserRegisterTerm.SECRECT_KEY, newPassword);
						//

						java.net.HttpURLConnection conChange = (java.net.HttpURLConnection) urlChange.openConnection();
						conChange.setRequestMethod(HttpMethod.POST);
						conChange.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
						conChange.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
						conChange.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
						_log.debug("BASIC AUTHEN: " + authStrEnc);
						conChange.setRequestProperty("Content-Length",
								StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

						conChange.setUseCaches(false);
						conChange.setDoInput(true);
						conChange.setDoOutput(true);
						_log.debug("POST DATA: " + jsonBody.toString());
						OutputStream osChange = conChange.getOutputStream();
						osChange.write(jsonBody.toString().getBytes());
						osChange.close();

						BufferedReader brfChange = new BufferedReader(
								new InputStreamReader(conChange.getInputStream()));

						int cpChange;
						while ((cpChange = brfChange.read()) != -1) {
							sbChange.append((char) cpChange);
						}
						_log.info("RESULT PROXY: " + sbChange.toString());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			boolean flag = actions.addChangepass(groupId, company.getCompanyId(), id, oldPassword, newPassword, 0,
					serviceContext);
			_log.info("flag: "+flag);

			return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(flag)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addChangepassEmployee(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String oldPassword, String newPassword) {
		
		UserInterface actions = new UserActions();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();
		BackendAuth auth = new BackendAuthImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			if (user == null || (user.getUserId() != id && !auth2.isAdmin(serviceContext, ConstantUtils.ROLE_ADMIN_LOWER))) {
				throw new PermissionDeniedDataAccessException(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION), null);
			}

			boolean flag = actions.addChangepass(groupId, company.getCompanyId(), id, oldPassword, newPassword, 1,
					serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(String.valueOf(flag)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getUserLoginInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Request requestCC) {
		JSONArray dataUser = JSONFactoryUtil.createJSONArray();

		try {
			List<Role> roles = user.getRoles();
			if (roles != null && roles.size() > 0)
				_log.info("roles:" + roles.size());
			for (Role role : roles) {
//				String roleName = StringPool.BLANK;
				//String roleName;

				JSONObject result = JSONFactoryUtil.createJSONObject();

				//result.put(UserTerm.EMAIL, StringPool.BLANK);
				//result.put(UserTerm.ROLE, StringPool.BLANK);
				//result.put(UserTerm.DEACTIVE_ACCOUNT_FLAG, 0);

//				if ("Administrator".equalsIgnoreCase(role.getName())) {
//					roleName = "Administrator";
//				}
//
//				if ("Administrator_data".equalsIgnoreCase(role.getName())) {
//					roleName = "Administrator_data";
//				}
				
				//String roleName = role.getName();
				result.put(UserTerm.EMAIL,
						Validator.isNotNull(user.getEmailAddress()) ? user.getEmailAddress() : StringPool.BLANK);
				result.put(UserTerm.ROLE, Validator.isNotNull(role.getName()) ? role.getName() : StringPool.BLANK);
				result.put(UserTerm.DEACTIVE_ACCOUNT_FLAG, user.getStatus());

				dataUser.put(result);
			}

		} catch (Exception e) {
			_log.debug(e);
		}

//		EntityTag etag = new EntityTag(String.valueOf(("USER_LOGIN_INFO_" + user.getGroupId() + "_" + user.getUserId()).hashCode()));
//	    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
//		CacheControl cc = new CacheControl();
//		cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//		cc.setPrivate(true);	
//
//	    if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
//			builder = Response.ok(dataUser.toJSONString());
//			builder.tag(etag);
//		}
//	    
//	    builder.cacheControl(cc);
//	    return builder.build();
	    return Response.status(HttpURLConnection.HTTP_OK).entity(dataUser.toJSONString()).build();
	    
//	    if (OpenCPSConfigUtil.isHttpCacheEnable()) {
//			CacheControl cc = new CacheControl();
//		    cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//		    cc.setPrivate(true);
//		    
//			return Response.status(HttpURLConnection.HTTP_OK).cacheControl(cc).entity(dataUser.toJSONString()).build();	    	
//	    }
//	    else {
//			return Response.status(HttpURLConnection.HTTP_OK).entity(dataUser.toJSONString()).build();	    		    	
//	    }
	}

	public Response unlockAccount(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user,
			ServiceContext serviceContext, long id,
			String email, boolean unlocked) {
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			UserInterface actions = new UserActions();

			JSONObject result = actions.unlockAccount(user.getUserId(), company.getCompanyId(), groupId, id, email,
					unlocked, serviceContext);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getLGSPForgotConfirm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String screenname_email, String code, String jCaptchaResponse) {
		UserInterface actions = new UserActions();
		
		String captchaType = PropValues.CAPTCHA_TYPE;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			if (Validator.isNotNull(captchaType) && "jcaptcha".equals(captchaType)) {

				ImageCaptchaService instance = CaptchaServiceSingleton.getInstance();
				String captchaId = request.getSession().getId();
				try {
					_log.info("Captcha: " + captchaId + "," + jCaptchaResponse);
					boolean isResponseCorrect = instance.validateResponseForID(captchaId, jCaptchaResponse);
					_log.info("Check captcha result: " + isResponseCorrect);
					if (!isResponseCorrect) {
						ErrorMsgModel error = new ErrorMsgModel();
						error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
						error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
						error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));

						return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
					}
				} catch (CaptchaServiceException e) {
					_log.debug(e);
					ErrorMsgModel error = new ErrorMsgModel();
					error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_CAPTCHA_INCORRECT));

					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
				}
			} else {
				ApplicantActionsImpl actionsImpl = new ApplicantActionsImpl();

				boolean isValid = actionsImpl.validateSimpleCaptcha(request, header, company, locale, user,
						serviceContext, jCaptchaResponse);

				if (!isValid) {
					ErrorMsgModel error = new ErrorMsgModel();
					error.setMessage("Captcha incorrect");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Captcha incorrect");

					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
				}
			}

			//Forgot LGSP
			String secretKey = StringPool.BLANK;
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };
			// Install the all-trusting trust manager
			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			}
			
			//
			String strToken = ApplicantUtils.getTokenLGSP();
			if (Validator.isNotNull(strToken)) {
				JSONObject jsonToken = JSONFactoryUtil.createJSONObject(strToken);
				//
				if (jsonToken.has("access_token") && jsonToken.has("token_type")
						&& Validator.isNotNull(jsonToken.getString("access_token"))
						&& Validator.isNotNull(jsonToken.getString("token_type"))) {
					String accessToken = jsonToken.getString("access_token");
					String tokenType = jsonToken.getString("token_type");

					_log.info("accessToken: " + accessToken);
					_log.info("tokenType: " + tokenType);

					// Dang ky tk cong dan
					//String message = RegisterLGSPUtils.forgotLGSP(jsonToken, screenname_email);
					String message = "";
					if (Validator.isNull(message)) {
						return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("{error}").build();
					} else {
						JSONObject jsonMessage =  JSONFactoryUtil.createJSONObject(message);
						if (jsonMessage != null && jsonMessage.has("matKhau") && jsonMessage.has("taiKhoan")
								&& Validator.isNotNull(jsonMessage.getString("matKhau"))
								&& Validator.isNotNull(jsonMessage.getString("taiKhoan"))) {
							secretKey = jsonMessage.getString("matKhau");
							
						} else {
							return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("{error}").build();
						}
					}
				}
			} else {
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("{error}").build();
			}
			
			_log.info("secretKey: "+secretKey);
			
			Document document = actions.getLGSPForgotConfirm(groupId, company.getCompanyId(), screenname_email, code,
					secretKey, serviceContext);

			UserAccountModel userAccountModel = UserUtils.mapperUserAccountModel(document);

			return Response.status(HttpURLConnection.HTTP_OK).entity(userAccountModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}
	}

	@Override
	public Response getLiveUser(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			
			UserInterface actions = new UserActions();

			JSONObject result = actions.getLiveUser(user.getUserId(), company.getCompanyId(), groupId, serviceContext);
			
			return Response.status(200).entity(result.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
