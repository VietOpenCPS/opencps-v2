package org.opencps.usermgt.action.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.usermgt.action.DVCQGSSOInterface;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.util.DateTimeUtils;

public class DVCQGSSOActionImpl implements DVCQGSSOInterface {
	private Log _log = LogFactoryUtil.getLog(DVCQGSSOActionImpl.class);

	@Override
	public String getAuthURL(User user, long groupId, ServiceContext serviceContext, int vnconnect, String currentURL) {

		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG-OPENID");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);

			try {

				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String auth_server = config.getString("auth_server");
				String auth_endpoint = config.getString("auth_endpoint");
				String clientid = config.getString("clientid");
				String callback_url = config.getString("callback_url");

				String endpoint = auth_server + auth_endpoint + "?response_type=code" + "&client_id=" + clientid
						+ "&redirect_uri=" + callback_url + "&scope=openid" + "&acr_values=LoA1";

				return endpoint;

			} catch (Exception e) {
				_log.error(e);
				return StringPool.BLANK;
			}

		}
		return StringPool.BLANK;
	}

	@Override
	public String checkAuth(User user, long groupId, ServiceContext serviceContext, int vnconnect, String currentURL) {

		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG-OPENID");
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);

			HttpURLConnection conn = null;
			try {

				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String auth_server = config.getString("auth_server");
				String auth_endpoint = config.getString("auth_endpoint");
				String clientid = config.getString("clientid");
				String callback_url = config.getString("callback_url");

				String endpoint = auth_server + auth_endpoint + "?response_type=code" + "&client_id=" + clientid
						+ "&redirect_uri=" + callback_url + "&scope=openid" + "&acr_values=LoA1";

				URL url = new URL(endpoint);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				conn.setRequestProperty("Accept", "text/html");
				conn.setRequestProperty("Content-Type", "text/html");
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

					System.out.println("response: " + sb.toString());

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
	public JSONObject getUserInfo(User user, long groupId, HttpServletRequest request, ServiceContext serviceContext, String authToken) {
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG-OPENID");
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			JSONObject accessTokenInfo = getAccessToken(user, groupId, serviceContext, authToken, serverConfig);
			if (accessTokenInfo.length() > 0 && accessTokenInfo.has("access_token")) {
				String accessToken = accessTokenInfo.getString("access_token");
				result = invokeUserInfo(user, groupId, serviceContext, accessToken, serverConfig);
				request.getSession().setAttribute("accessToken", accessToken);
				_log.info(">>>>>>>>>>>>>>>>getUserInfo " + request.getRequestedSessionId());
			}
		}
		return result;
	}

	private JSONObject invokeUserInfo(User user, long groupId, ServiceContext serviceContext, String accessToken,
			ServerConfig serverConfig) {

		HttpURLConnection conn = null;
		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String auth_server = config.getString("auth_server");
			String userinfo_endpoint = config.getString("userinfo_endpoint");

			String endpoint = auth_server + userinfo_endpoint;

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setReadTimeout(60 * 1000);

			conn.setUseCaches(false);

			conn.connect();

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

				String output = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				while ((output = bufferedReader.readLine()) != null) {
					sb.append(output);
				}

				System.out.println("response: " + sb.toString());

				int responseCode = conn.getResponseCode();

				if (responseCode == 200) {
					JSONObject result = JSONFactoryUtil.createJSONObject(sb.toString());
					result.put("groupId", config.get("groupId"));
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
		List<ServerConfig> serverConfigs = ServerConfigLocalServiceUtil.getByProtocol("DVCQG-OPENID");

		if (serverConfigs != null && !serverConfigs.isEmpty()) {
			ServerConfig serverConfig = serverConfigs.get(0);
			HttpURLConnection conn = null;
			try {

				JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String auth_server = config.getString("auth_server");
				String validatetoken_endpoint = config.getString("validatetoken_endpoint");

				String endpoint = auth_server + validatetoken_endpoint;

				URL url = new URL(endpoint);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				conn.setRequestProperty("Authorization", "Bearer " + accessToken);
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.setReadTimeout(60 * 1000);

				conn.setUseCaches(false);

				StringBuffer params = new StringBuffer();
				params.append("token=" + accessToken);
				params.append("&state=0");

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				conn.setReadTimeout(60 * 1000);

				conn.setUseCaches(false);
				
				byte[] postData = params.toString().getBytes("UTF-8");
				int postDataLength = postData.length;
				conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
				try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
					wr.write(postData);
				}
				conn.connect();

				try (BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader((conn.getInputStream())))) {

					String output = StringPool.BLANK;

					StringBuilder sb = new StringBuilder();

					while ((output = bufferedReader.readLine()) != null) {
						sb.append(output);
					}

					int responseCode = conn.getResponseCode();
					
					System.out.println("response: " + sb.toString() + "|" + responseCode);

					if (responseCode == 200) {
						JSONObject object = JSONFactoryUtil.createJSONObject(sb.toString());

						String client_id = object.getString("client_id");
						if (client_id == config.getString("clientid")) {
							// TODO check exp
							return true;
						}
						//Fix tam
						return true;
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

		return false;
	}

	private JSONObject getAccessToken(User user, long groupId, ServiceContext serviceContext, String authToken,
			ServerConfig serverConfig) {

		HttpURLConnection conn = null;
		try {

			JSONObject config = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
			String auth_server = config.getString("auth_server");
			String accesstoken_endpoint = config.getString("accesstoken_endpoint");
			String clientid = config.getString("clientid");
			String callback_url = config.getString("callback_url");
			String client_secret = config.getString("client_secret");

			StringBuffer params = new StringBuffer();
			params.append("grant_type=authorization_code");
			params.append("&code=" + authToken);
			params.append("&redirect_uri=" + callback_url);
			params.append("&client_id=" + clientid);
			params.append("&client_secret=" + client_secret);

			String endpoint = auth_server + accesstoken_endpoint;

			_log.info(params.toString());

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setReadTimeout(60 * 1000);

			conn.setUseCaches(false);
			byte[] postData = params.toString().getBytes("UTF-8");
			int postDataLength = postData.length;
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
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

				System.out.println("response: " + sb.toString());

				int responseCode = conn.getResponseCode();

				if (responseCode == 200) {
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
		result.put("status", -1);
		result.put("message", error);
		return result;
	}

	@Override
	public JSONObject doAuth(User user, HttpServletRequest request, ServiceContext serviceContext, String userInfo)
			throws Exception {
		JSONObject userInfoObject = JSONFactoryUtil.createJSONObject(userInfo);
		int LoaiTaiKhoan = userInfoObject.getInt("LoaiTaiKhoan");
		String HoChieu = userInfoObject.getString("HoChieu");
		String SoCMND = userInfoObject.getString("SoCMND");
		String MaSoThue = userInfoObject.getString("MaSoThue");
		int GioiTinh = userInfoObject.getInt("GioiTinh");
		String DiaChi = userInfoObject.getString("DiaChi");
		String SoDienThoai = userInfoObject.getString("SoDienThoai");
		String ThuDienTu = userInfoObject.getString("ThuDienTu");
		String HoVaTen = userInfoObject.getString("HoVaTen");
		String sub = userInfoObject.getString("sub");
		String TechID = userInfoObject.getString("TechID");
		String TenDoanhNghiep = userInfoObject.getString("TenDoanhNghiep");
		String MaSoDoanhNghiep = userInfoObject.getString("MaSoDoanhNghiep");
		String SoDinhDanh = userInfoObject.getString("SoDinhDanh");
		long groupId = userInfoObject.getLong("groupId");
		Applicant applicant = null;
		JSONObject result = JSONFactoryUtil.createJSONObject();
		// applicantIdNo)
		// ca nhan
		_log.info(">>>>>>>>>>>>>>>>>>>>>>>> userInfoObject " + userInfoObject.toJSONString());
		if (LoaiTaiKhoan == 1) {
		
			if (Validator.isNull(SoCMND) && Validator.isNull(SoDinhDanh)) {
				return createErrorMessage("Unknown SoCMND, SoDinhDanh");
			}
			applicant = ApplicantLocalServiceUtil.fetchByF_APLC_GID(groupId,
					Validator.isNotNull(SoCMND) ? SoCMND : SoDinhDanh);
		
			if (applicant == null) {
				applicant = ApplicantLocalServiceUtil.fetchByEmail(ThuDienTu);
			}
			
		} else if (LoaiTaiKhoan == 2) {
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
		}

		if (applicant == null) {
			// create
			if (Validator.isNull(ThuDienTu)) {
				return createErrorMessage("Unknown ThuDienTu");
			}
		
			applicant = ApplicantLocalServiceUtil.updateApplication(serviceContext, groupId, 0L,
					LoaiTaiKhoan == 1 ? HoVaTen : TenDoanhNghiep, LoaiTaiKhoan == 1? "citizen" : "business",
					LoaiTaiKhoan == 1 ? (Validator.isNotNull(SoCMND) ? SoCMND : SoDinhDanh)
							: (Validator.isNotNull(MaSoThue) ? MaSoThue : MaSoDoanhNghiep),
					DateTimeUtils.dateToString(new Date(), DateTimeUtils._TYPEDATE), DiaChi, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
					StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, SoDienThoai, ThuDienTu, StringPool.BLANK,
					TechID, true);
		}

		long mappingUserId = applicant.getMappingUserId();
		_log.info("------------>>> accessToken: " + request.getSession().getAttribute("accessToken") + "|" + request.getRequestedSessionId());
		result.put("status", 0);
		result.put("message", "success");
		result.put("userId", mappingUserId);
		result.put("groupId", groupId);
		result.put("accessToken", request.getSession().getAttribute("accessToken"));
		result.put("email", applicant.getContactEmail());
		//invokeValidateAccessToken(mappingUserId, groupId, request.getSession().getAttribute("accessToken").toString());
		return result;
	}
	
	private void invokeValidateAccessToken(long userId, long groupId, String accessToken) {

		HttpURLConnection conn = null;
		
		try {

			
			String endpoint = "http://localhost:8080/o/auth/dvcqg/validatetoken";

			URL url = new URL(endpoint);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("userId", String.valueOf(userId));
			conn.setRequestProperty("groupId", String.valueOf(groupId));
			conn.setRequestProperty("accessToken", accessToken);
			conn.setReadTimeout(60 * 1000);

			conn.setUseCaches(false);

			conn.connect();
			
			_log.info(">>>>>>>>>>>>>>>>> isValid " + conn.toString());

		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
}
