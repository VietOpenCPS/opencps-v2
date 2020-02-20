package org.opencps.usermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.HttpMethod;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.usermgt.action.DVCQGSSOInterface;
import org.opencps.usermgt.constants.DVCQGSSOTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;

/**
 * @author trungnt
 *
 */
public class DVCQGSSOActionImpl implements DVCQGSSOInterface {
	private Log _log = LogFactoryUtil.getLog(DVCQGSSOActionImpl.class);

	@Override
	public String getAuthURL(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext,
			String state) {

		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGSSOTerm.DVCQG_OPENID_PROTOCOL);

		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			try {
				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String auth_server = config.getString(DVCQGSSOTerm.AUTH_SERVER);
				String auth_endpoint = config.getString(DVCQGSSOTerm.AUTH_ENDPOINT);
				String clientid = config.getString(DVCQGSSOTerm.CLIENTID);
				String callback_url = config.getString(DVCQGSSOTerm.CALLBACK_URL);
				String scope = config.getString(DVCQGSSOTerm.SCOPE);
				String acr_values = config.getString(DVCQGSSOTerm.ACR_VALUES);
				String endpoint = auth_server + auth_endpoint + "?response_type=code" + "&client_id=" + clientid
						+ "&redirect_uri=" + callback_url + "&scope=" + scope + "&acr_values=" + acr_values + "&state="
						+ state;

				return endpoint;

			} catch (Exception e) {
				_log.error(e);
				return StringPool.BLANK;
			}

		}

		return StringPool.BLANK;
	}

	@Override
	public String checkAuth(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext,
			int vnconnect, String currentURL) {

		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGSSOTerm.DVCQG_OPENID_PROTOCOL);
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);

			HttpURLConnection conn = null;
			try {

				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String auth_server = config.getString(DVCQGSSOTerm.AUTH_SERVER);
				String auth_endpoint = config.getString(DVCQGSSOTerm.AUTH_ENDPOINT);
				String clientid = config.getString(DVCQGSSOTerm.CLIENTID);
				String callback_url = config.getString(DVCQGSSOTerm.CALLBACK_URL);
				String scope = config.getString(DVCQGSSOTerm.OPENID);
				String acr_values = config.getString(DVCQGSSOTerm.ACR_VALUES);
//				String endpoint = auth_server + auth_endpoint + "?response_type=code" + "&client_id=" + clientid
//						+ "&redirect_uri=" + callback_url + "&scope=" + scope + "&acr_values=" + acr_values + "&state="
//						+ AuthTokenUtil.getToken(request);

				String endpoint = String.format(DVCQGSSOTerm.CHECK_AUTH_FORMAT, auth_server + auth_endpoint, clientid, callback_url, scope, acr_values, AuthTokenUtil.getToken(request));
				URL url = new URL(endpoint);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod(HttpMethod.GET);
				conn.setDoInput(true);
				conn.setDoOutput(true);

				conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGSSOTerm.TEXT_HTML);
				conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGSSOTerm.TEXT_HTML);
				conn.setInstanceFollowRedirects(true);
				HttpURLConnection.setFollowRedirects(true);
				conn.setReadTimeout(60 * 1000);
				conn.connect();

				try (BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader((conn.getInputStream())))) {

					String output = StringPool.BLANK;

					StringBuilder sb = new StringBuilder();

					while ((output = bufferedReader.readLine()) != null) {
						sb.append(output);
					}

					_log.info("response: " + sb.toString());

					// int responseCode = conn.getResponseCode();

					return sb.toString();

				}

			} catch (Exception e) {
				_log.error(e);
				return StringPool.BLANK;
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

		}
		return StringPool.BLANK;
	}

	@Override
	public JSONObject getUserInfo(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext,
			String authToken, String state) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGSSOTerm.DVCQG_OPENID_PROTOCOL);
	
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject accessTokenInfo = getAccessToken(user, groupId, serviceContext, authToken, serverConfig);
			if (accessTokenInfo.length() > 0 && accessTokenInfo.has(DVCQGSSOTerm.ACCESS_TOKEN)) {
				String accessToken = accessTokenInfo.getString(DVCQGSSOTerm.ACCESS_TOKEN);
				result = invokeUserInfo(user, groupId, serviceContext, accessToken, serverConfig);
				
				Applicant applicant = ApplicantLocalServiceUtil.fetchByF_GID_MCN_MCPK(result.getLong(Field.GROUP_ID), _DEFAULT_CLASS_NAME, result.getString(DVCQGSSOTerm.TECH_ID));
				
				result.put(Field.USER_ID, applicant != null ? applicant.getMappingUserId() : 0);
				result.put(DVCQGSSOTerm.STATE, HttpURLConnection.HTTP_OK);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute(DVCQGSSOTerm.SSO_STATE, state);
				httpSession.setAttribute(DVCQGSSOTerm.ACCESS_TOKEN_UPPER, accessToken);
			}
		}
		return result;
	}

	private JSONObject invokeUserInfo(User user, long groupId, ServiceContext serviceContext, String accessToken,
			ServerConfig serverConfig) {

		HttpURLConnection conn = null;
		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String auth_server = config.getString(DVCQGSSOTerm.AUTH_SERVER);
			String userinfo_endpoint = config.getString(DVCQGSSOTerm.USERINFO_ENDPOINT);

			String endpoint = auth_server + userinfo_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			String bearer = String.format(DVCQGSSOTerm.BEARER_HEADER, accessToken);
			conn.setRequestProperty(HttpHeaders.AUTHORIZATION, bearer);
			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGSSOTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGSSOTerm.APPLICATION_JSON);
			conn.setReadTimeout(60 * 1000);

			conn.setUseCaches(false);

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				_log.info("response: " + sb.toString());

				int responseCode = conn.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK) {
					JSONObject result = JSONFactoryUtil.createJSONObject(sb.toString());
					result.put(Field.GROUP_ID, config.get(Field.GROUP_ID));
					return result;
				} else {
					return JSONFactoryUtil.createJSONObject();
				}

			}

		} catch (Exception e) {
			_log.error(e);
			return JSONFactoryUtil.createJSONObject();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public boolean isValidAccessToken(String accessToken) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol(DVCQGSSOTerm.DVCQG_OPENID_PROTOCOL);

		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			return isValidAccessToken(serverConfig, accessToken);
		}

		return false;
	}

	private boolean isValidAccessToken(ServerConfig serverConfig, String accessToken) {

		HttpURLConnection conn = null;
		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String auth_server = config.getString(DVCQGSSOTerm.AUTH_SERVER);
			String validatetoken_endpoint = config.getString("validatetoken_endpoint");

			String endpoint = auth_server + validatetoken_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			String bearer = String.format(DVCQGSSOTerm.BEARER_HEADER, accessToken);
			conn.setRequestProperty(HttpHeaders.AUTHORIZATION, bearer);
			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGSSOTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGSSOTerm.APPLCATION_X_WWW_FORM_URLENCODED);
			conn.setUseCaches(false);
			conn.setReadTimeout(60 * 1000);
			StringBuffer params = new StringBuffer();
			String validateTokenParam = String.format(DVCQGSSOTerm.VALIDATE_TOKEN_FORMAT, accessToken);
//			params.append("token=" + accessToken);
//			params.append("&state=0");
			params.append(validateTokenParam);
			byte[] postData = params.toString().getBytes(StringPool.UTF8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}
			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				int responseCode = conn.getResponseCode();

				_log.info("response: " + sb.toString() + "|" + responseCode);

				if (responseCode == HttpURLConnection.HTTP_OK) {
					JSONObject object = JSONFactoryUtil.createJSONObject(sb.toString());

					String client_id = object.getString(DVCQGSSOTerm.CLIENT_ID);
					long exp = object.getLong(DVCQGSSOTerm.EXP);
					if (client_id.equals(config.getString(DVCQGSSOTerm.CLIENTID)) && exp > System.currentTimeMillis() / 1000) {
						_log.info("------------------>>>>>>> accessToken " + accessToken + "| has expire");
						return true;
					}

					return false;
				} else {
					return false;
				}

			}

		} catch (Exception e) {
			_log.error(e);
			return false;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(c.getTimeInMillis());
	}

	private JSONObject getAccessToken(User user, long groupId, ServiceContext serviceContext, String authToken,
			ServerConfig serverConfig) {

		HttpURLConnection conn = null;
		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String auth_server = config.getString(DVCQGSSOTerm.AUTH_SERVER);
			String accesstoken_endpoint = config.getString(DVCQGSSOTerm.ACCESS_TOKEN_ENDPOINT);
			String clientid = config.getString(DVCQGSSOTerm.CLIENTID);
			String callback_url = config.getString(DVCQGSSOTerm.CALLBACK_URL);
			String client_secret = config.getString(DVCQGSSOTerm.CLIENT_SECRET);

			StringBuffer params = new StringBuffer();
//			params.append("grant_type=authorization_code");
//			params.append("&code=" + authToken);
//			params.append("&redirect_uri=" + callback_url);
//			params.append("&client_id=" + clientid);
//			params.append("&client_secret=" + client_secret);
			String accessTokenParam = String.format(DVCQGSSOTerm.ACCESS_TOKEN_FORMAT, authToken, callback_url, clientid, client_secret);
			params.append(accessTokenParam);
			String endpoint = auth_server + accesstoken_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, DVCQGSSOTerm.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, DVCQGSSOTerm.APPLCATION_X_WWW_FORM_URLENCODED);
			conn.setReadTimeout(60 * 1000);

			conn.setUseCaches(false);
			byte[] postData = params.toString().getBytes(StringPool.UTF8);
			int postDataLength = postData.length;
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, Integer.toString(postDataLength));
			try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				wr.write(postData);
			}

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				_log.info("response: " + sb.toString());

				int responseCode = conn.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK) {
					return JSONFactoryUtil.createJSONObject(sb.toString());
				} else {
					return JSONFactoryUtil.createJSONObject();
				}

			}

		} catch (Exception e) {
			_log.error(e);
			return JSONFactoryUtil.createJSONObject();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	private JSONObject createErrorMessage(String error) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put(DVCQGSSOTerm.STATUS, 401);
		result.put(DVCQGSSOTerm.MESSAGE, error);
		return result;
	}

	@Override
	public JSONObject doAuth(User user, HttpServletRequest request, HttpServletResponse response,
			ServiceContext serviceContext, String userInfo) throws Exception {
		JSONObject userInfoObject = JSONFactoryUtil.createJSONObject(userInfo);
		//int LoaiTaiKhoan = userInfoObject.getInt("LoaiTaiKhoan");
		//String HoChieu = userInfoObject.getString("HoChieu");
		//String SoCMND = userInfoObject.getString("SoCMND");
		//String MaSoThue = userInfoObject.getString("MaSoThue");
		//int GioiTinh = userInfoObject.getInt("GioiTinh");
		//String DiaChi = userInfoObject.getString("DiaChi");
		//String SoDienThoai = userInfoObject.getString("SoDienThoai");
		//String ThuDienTu = userInfoObject.getString("ThuDienTu");
		//String HoVaTen = userInfoObject.getString("HoVaTen");
		//String sub = userInfoObject.getString("sub");
		//String TenDoanhNghiep = userInfoObject.getString("TenDoanhNghiep");
		//String MaSoDoanhNghiep = userInfoObject.getString("MaSoDoanhNghiep");
		//String SoDinhDanh = userInfoObject.getString("SoDinhDanh");
		String TechID = userInfoObject.getString(DVCQGSSOTerm.TECH_ID);
		long groupId = userInfoObject.getLong(Field.GROUP_ID);
		
		Applicant applicant = null;
		
		JSONObject result = JSONFactoryUtil.createJSONObject();

		_log.info(">>>>>>>>>>>>>>>>>>>>>>>> userInfoObject " + userInfoObject.toJSONString());
	
		//Bỏ phần check theo loại tk, và cmtnd -> check theo trường mappingClassName và mappingClassPK
		// ca nhan
		/*if (LoaiTaiKhoan == 1) {
		
			if (Validator.isNull(SoCMND) && Validator.isNull(SoDinhDanh)) {
				return createErrorMessage("Unknown SoCMND, SoDinhDanh");
			}
			applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId,
					Validator.isNotNull(SoCMND) ? SoCMND : SoDinhDanh);
		
			if (applicant == null) {
				applicant = ApplicantLocalServiceUtil.fetchByEmail(ThuDienTu);
			}
		
		}
		// doanh nghiep
		else if (LoaiTaiKhoan == 2) {
			if (Validator.isNull(MaSoThue) && Validator.isNull(MaSoDoanhNghiep)) {
				return createErrorMessage("Unknown MaSoThue, MaSoDoanhNghiep");
			}
			applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId,
					Validator.isNotNull(MaSoThue) ? MaSoThue : MaSoDoanhNghiep);
			if (applicant == null) {
				applicant = ApplicantLocalServiceUtil.fetchByEmail(ThuDienTu);
			}
		} else {
			return createErrorMessage("Unknown LoaiTaiKhoan");
		}*/

		//bỏ tự tạo tài khoản
		/*if (applicant == null) {
			// create
			if (Validator.isNull(ThuDienTu)) {
				return createErrorMessage("Unknown ThuDienTu");
			}
		
			applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId, 0L,
					LoaiTaiKhoan == 1 ? HoVaTen : TenDoanhNghiep, LoaiTaiKhoan == 1 ? "citizen" : "business",
					LoaiTaiKhoan == 1 ? (Validator.isNotNull(SoCMND) ? SoCMND : SoDinhDanh)
							: (Validator.isNotNull(MaSoThue) ? MaSoThue : MaSoDoanhNghiep),
					DateTimeUtils.dateToString(new Date(), DateTimeUtils._TYPEDATE), DiaChi, StringPool.BLANK,
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, SoDienThoai, ThuDienTu, StringPool.BLANK, TechID, true);
		}*/

		applicant = ApplicantLocalServiceUtil.fetchByF_GID_MCN_MCPK(groupId, _DEFAULT_CLASS_NAME, TechID);

		String accessToken = StringPool.BLANK;
		
		/*Enumeration<String> enumeration = request.getSession().getAttributeNames();
		
		List<String> values = Collections.list(enumeration);
		
		for (String value : values) {
			_log.info("========================== > session.getAttributeNames() " + value);
		}*/
		
		if (request.getSession().getAttribute(DVCQGSSOTerm.ACCESS_TOKEN_UPPER) != null) {
			accessToken = request.getSession().getAttribute(DVCQGSSOTerm.ACCESS_TOKEN_UPPER).toString();
		}
		
		String state = StringPool.BLANK;
		if (request.getSession().getAttribute(DVCQGSSOTerm.SSO_STATE) != null) {
			state = request.getSession().getAttribute(DVCQGSSOTerm.SSO_STATE).toString();
		}
		
		long mappingUserId = 0;
		
		_log.info("------------>>> accessToken: " + accessToken + "|state " + state);

		if (applicant == null && DVCQGSSOTerm.AUTH.equalsIgnoreCase(state)) {
			userInfoObject.put(Field.USER_ID, 0);
			userInfoObject.put(DVCQGSSOTerm.STATUS, HttpURLConnection.HTTP_OK);
			return userInfoObject;
		}

		if (applicant != null && DVCQGSSOTerm.AUTH.equalsIgnoreCase(state)) {

			mappingUserId = applicant.getMappingUserId();

			User mappingUser = UserLocalServiceUtil.fetchUser(mappingUserId);

			if (mappingUser == null) {
				return createErrorMessage("account not exist with userId = " + mappingUserId);
			}

			if (mappingUser.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				return createErrorMessage("the account has been locked");
			}
			
			/*AuthenticatedSessionManagerUtil.login(request, response, mappingUser.getEmailAddress(), "", false,
					CompanyConstants.AUTH_TYPE_EA);*/
			// AuthenticatedSessionManagerUtil.login(request, response,
			// applicant.getContactEmail(), applicant.getTmpPass(),
			// false, CompanyConstants.AUTH_TYPE_EA);

			// @See AuthenticatedSessionManagerImpl.java
			

			HttpSession session = request.getSession();
			session = renewSession(request, session);
			session.setAttribute(DVCQGSSOTerm._GROUP_ID, groupId);
			session.setAttribute(DVCQGSSOTerm._MAPPING_CLASS_NAME, applicant.getMappingClassName());
			session.setAttribute(DVCQGSSOTerm._MAPPING_CLASS_PK, applicant.getMappingClassPK());
			session.setAttribute(DVCQGSSOTerm._ACCESS_TOKEN, accessToken);
			
			/*CookieKeys.validateSupportCookie(request);
			Company company = PortalUtil.getCompany(request);
			
			User userLogin = UserLocalServiceUtil.getUser(mappingUserId);
			
			// Set cookies
			
			
			
			String domain = CookieKeys.getDomain(request);
			
			if (Validator.isNull(domain)) {
				domain = null;
			}
			
			String userIdString = String.valueOf(userLogin.getUserId());
			
			session.setAttribute("j_username", userIdString);
			
			session.setAttribute("j_password", userLogin.getPassword());
			
			session.setAttribute("j_remoteuser", userIdString);
			
			Cookie companyIdCookie = new Cookie(CookieKeys.COMPANY_ID, String.valueOf(company.getCompanyId()));
			
			if (domain != null) {
				companyIdCookie.setDomain(domain);
			}
			
			companyIdCookie.setPath(StringPool.SLASH);
			
			Cookie idCookie = new Cookie(CookieKeys.ID, Encryptor.encrypt(company.getKeyObj(), userIdString));
			
			if (domain != null) {
				idCookie.setDomain(domain);
			}
			
			idCookie.setPath(StringPool.SLASH);
			
			int loginMaxAge = 31536000;
			
			boolean rememberMe = true;
			
			if (rememberMe) {
				companyIdCookie.setMaxAge(loginMaxAge);
				idCookie.setMaxAge(loginMaxAge);
			} else {
			
				// This was explicitly changed from 0 to -1 so that the cookie lasts
				// as long as the browser. This allows an external servlet wrapped
				// in AutoLoginFilter to work throughout the client connection. The
				// cookies ARE removed on an actual logout, so there is no security
				// issue. See LEP-4678 and LEP-5177.
			
				companyIdCookie.setMaxAge(-1);
				idCookie.setMaxAge(-1);
			}
			
			boolean secure = request.isSecure();
			
			Boolean httpsInitial = (Boolean) session.getAttribute(WebKeys.HTTPS_INITIAL);
			
			if ((httpsInitial == null) || !httpsInitial.booleanValue()) {
				secure = false;
			}
			
			CookieKeys.addCookie(request, response, companyIdCookie, secure);
			
			CookieKeys.addCookie(request, response, idCookie, secure);
			
			if (rememberMe) {
				Cookie loginCookie = new Cookie(CookieKeys.LOGIN, mappingUser.getEmailAddress());
			
				if (domain != null) {
					loginCookie.setDomain(domain);
				}
			
				loginCookie.setMaxAge(loginMaxAge);
				loginCookie.setPath(StringPool.SLASH);
			
				CookieKeys.addCookie(request, response, loginCookie, secure);
			
				Cookie passwordCookie = new Cookie(CookieKeys.PASSWORD,
						Encryptor.encrypt(company.getKeyObj(), applicant.getTmpPass()));
				
				if (domain != null) {
					passwordCookie.setDomain(domain);
				}
				
				passwordCookie.setMaxAge(loginMaxAge);
				passwordCookie.setPath(StringPool.SLASH);
				
				CookieKeys.addCookie(request, response, passwordCookie, secure);
			
				Cookie rememberMeCookie = new Cookie(CookieKeys.REMEMBER_ME, Boolean.TRUE.toString());
			
				if (domain != null) {
					rememberMeCookie.setDomain(domain);
				}
			
				rememberMeCookie.setMaxAge(loginMaxAge);
				rememberMeCookie.setPath(StringPool.SLASH);
			
				CookieKeys.addCookie(request, response, rememberMeCookie, secure);
			
				Cookie screenNameCookie = new Cookie(CookieKeys.SCREEN_NAME,
						Encryptor.encrypt(company.getKeyObj(), userLogin.getScreenName()));
			
				if (domain != null) {
					screenNameCookie.setDomain(domain);
				}
			
				screenNameCookie.setMaxAge(loginMaxAge);
				screenNameCookie.setPath(StringPool.SLASH);
			
				CookieKeys.addCookie(request, response, screenNameCookie, secure);
			}
			
			boolean AUTH_USER_UUID_STORE_ENABLED = false;
			if (AUTH_USER_UUID_STORE_ENABLED) {
				String userUUID = userIdString.concat(StringPool.PERIOD).concat(String.valueOf(System.nanoTime()));
			
				Cookie userUUIDCookie = new Cookie(CookieKeys.USER_UUID,
						Encryptor.encrypt(company.getKeyObj(), userUUID));
			
				userUUIDCookie.setPath(StringPool.SLASH);
			
				session.setAttribute(WebKeys.USER_UUID, userUUID);
			
				if (rememberMe) {
					userUUIDCookie.setMaxAge(loginMaxAge);
				} else {
					userUUIDCookie.setMaxAge(-1);
				}
			
				CookieKeys.addCookie(request, response, userUUIDCookie, secure);
			
				AuthenticatedUserUUIDStoreUtil.register(userUUID);
			}*/
		} else if (DVCQGSSOTerm.MAPPING.equalsIgnoreCase(state)) {
			mappingUserId = user.getUserId();
			applicant = ApplicantLocalServiceUtil.fetchByMappingID(mappingUserId);
			if (applicant == null) {
				return createErrorMessage("not found applicant with userId = " + mappingUserId);
			}

			applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId, applicant.getApplicantId(),
					_DEFAULT_CLASS_NAME, TechID);
			HttpSession session = request.getSession();
			session = renewSession(request, session);
			session = renewSession(request, session);
			session.setAttribute(DVCQGSSOTerm._GROUP_ID, groupId);
			session.setAttribute(DVCQGSSOTerm._MAPPING_CLASS_NAME, applicant.getMappingClassName());
			session.setAttribute(DVCQGSSOTerm._MAPPING_CLASS_PK, applicant.getMappingClassPK());
			session.setAttribute(DVCQGSSOTerm._ACCESS_TOKEN, accessToken);
		}
		
		result.put(DVCQGSSOTerm.STATUS, HttpURLConnection.HTTP_OK);
		result.put(DVCQGSSOTerm.MESSAGE, DVCQGSSOTerm.SUCCESS);
		result.put(Field.USER_ID, mappingUserId);
		result.put(Field.GROUP_ID, groupId);
		// result.put("accessToken", accessToken);
		result.put(DVCQGSSOTerm.EMAIL, applicant != null ? applicant.getContactEmail() : StringPool.BLANK);
		return result;
	}

	public HttpSession renewSession(HttpServletRequest request, HttpSession session) throws Exception {

		String[] protectedAttributeNames = new String[] { DVCQGSSOTerm.CAS_LOGIN, DVCQGSSOTerm.HTTPS_INITIAL, DVCQGSSOTerm.LAST_PATH,
				DVCQGSSOTerm.OPEN_ID_CONNECT_SESSION };

		Map<String, Object> protectedAttributes = new HashMap<>();

		for (String protectedAttributeName : protectedAttributeNames) {
			Object protectedAttributeValue = session.getAttribute(protectedAttributeName);

			if (protectedAttributeValue == null) {
				continue;
			}

			protectedAttributes.put(protectedAttributeName, protectedAttributeValue);
		}

		session.invalidate();

		session = request.getSession(true);

		for (String protectedAttributeName : protectedAttributeNames) {
			Object protectedAttributeValue = protectedAttributes.get(protectedAttributeName);

			if (protectedAttributeValue == null) {
				continue;
			}

			session.setAttribute(protectedAttributeName, protectedAttributeValue);
		}

		return session;
	}

	private String _DEFAULT_CLASS_NAME = "dvcqg";
}
