
package org.opencps.api.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SMSManagement;
import org.opencps.api.sms.model.IPacificSearchSMS;
import org.opencps.communication.constants.SendSMSTerm;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.communication.sms.utils.ViettelSMSUtils;
import org.opencps.datamgt.util.DueDateUtils;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.BusinessExceptionImpl;
import ws.bulkSms.impl.Result;

public class SMSManagementImpl implements SMSManagement {

	@Override
	public Response sendSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String body,
		String toTelNo) {

		try {
			long groupId =
				GetterUtil.getLong(header.getHeaderString("groupId"));
			Result result = ViettelSMSUtils.sendSMS(
				groupId, body, StringPool.BLANK, toTelNo);

			return Response.status(200).entity(result.getMessage()).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getInetSMS(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		IPacificSearchSMS input) {

		// Bgtvt mshs mtc -> 8085
		// ====GET=====input.getCommandCode()=======BGTVT
		// ====GET=====input.getInfo()==============Bgtvt mshs mtc
		// ============input.getPassword()==========Gt.sms@201620182019
		// ============input.getServiceNumber()=====8085
		// ============input.getUser()==============gtsms2019
		// ============input.getUserId()============84978266524

		_log.info("===============" + input.getPassword());
		_log.info("===============" + input.getServiceNumber());
		_log.info("===============" + input.getUser());

		return Response.status(200).entity(_buiderResponseSMS(input)).build();
	}

	@Override
	public Response getZaloUIdByTelNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String toTelNo) {

		try {
			JSONObject zaloConfig = _getZaloInfo();
			String zaloAccessToken =
				zaloConfig.getString(SendSMSTerm.OAID_TOKEN_ACCESS);
			JSONObject resultApi = JSONFactoryUtil.createJSONObject(
				_getZaloUidByTelNo(zaloAccessToken, toTelNo));
			String uid = "not found";
			if (resultApi.has("data")) {

				uid = resultApi.getJSONObject("data").getString("user_id");
			}
			return Response.status(200).entity(uid).build();
		}
		catch (Exception e) {
			_log.debug(e);
			return Response.status(500).entity("").build();
		}

	}

	private String _buiderResponseSMS(IPacificSearchSMS iPacific) {

		// contentType;messageType;info;[mobile]
		// contentType: Loại nội dung trả về.
		// 0:Text
		// 2:Logo
		// 8:wappush
		// MessageType:Bản tin có hợp lệ không->Có tính tiền được không
		// 71:Valid->Tính tiền
		// 72:Invalid->Hoàn cước cho khách hàng
		// info:Nội dung trả về.
		// mobile:Số điện thoại khách hàng nhận tin nhắn
		// "0;72;HE THONG XIN THONG BAO\n BAN KHONG THE NHAN TIN NHAN;|0;0;Nhan
		// den ban b;0362219930"

		String result = "0;72;He thong chua cau hinh;";
		JSONObject epacifConfig = JSONFactoryUtil.createJSONObject();
		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_EPACIFIC);
			epacifConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			Dossier dossier = null;

			if (epacifConfig.has(SendSMSTerm.EPACIFIC_GROUPID) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_MINE) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_USER) &&
				epacifConfig.has(SendSMSTerm.EPACIFIC_PASSWORD)) {

				String[] strArr = iPacific.getInfo().split(" ");
				String dossierNo = strArr[1];
				String secretCode = strArr[2];

				dossier = DossierLocalServiceUtil.getByDossierNo(
					epacifConfig.getLong(SendSMSTerm.EPACIFIC_GROUPID),
					dossierNo);

				if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_MINE).equalsIgnoreCase(
						iPacific.getCommandCode())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_MINE_ERROR_MES);

				}
				else if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_USER).equalsIgnoreCase(
						iPacific.getUser())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_USER_ERROR_MES);

				}
				else if (!epacifConfig.getString(
					SendSMSTerm.EPACIFIC_PASSWORD).equalsIgnoreCase(
						iPacific.getPassword())) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_PASSWORD_ERROR_MES);

				}
				else if (Validator.isNull(dossier)) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_D_NOT_FOUND_MES);

				}
				else if (!dossier.getPassword().equalsIgnoreCase(secretCode)) {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_D_PASSWORD_ERROR_MES);

				}
				else {

					result = epacifConfig.getString(
						SendSMSTerm.EPACIFIC_SUCCESS_MES);
					result = result.replaceAll(
						epacifConfig.getString(
							SendSMSTerm.EPACIFIC_DOSSIER_NO_REPLACE),
						dossierNo);
					result = result.replaceAll(
						epacifConfig.getString(
							SendSMSTerm.EPACIFIC_DOSSIER_STATUS_REPLACE),
						_removeAccent(dossier.getDossierStatusText()));
				}
			}
			else {

				result = "0;72;He thong chua cau hinh.;";
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			_log.debug(e);
			result = epacifConfig.getString(SendSMSTerm.EPACIFIC_SYNTAX_ERROR_MES);
		}
		catch (Exception e) {
			_log.debug(e);
			result = "0;72;He thong chua cau hinh;";
		}

		_log.info(result);
		return result;
	}

	private String _removeAccent(String textConvert) {

		String temp = Normalizer.normalize(textConvert, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll(
			"Đ", "D").replaceAll("đ", "d");
	}

	@Override
	public Response calculateDueDate(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String startDate, double durationCount, int durationUnit,
		long groupId) {

		try {

			Date startDateS =
				new SimpleDateFormat("dd-MM-yyyy-HH-mm").parse(startDate);
			String dueDate2 =
				new SimpleDateFormat("dd-MM-yyyy-HH-mm").format(startDateS);
			_log.info(startDateS);
			_log.info(dueDate2);
			DueDateUtils dueDateUtils = new DueDateUtils(
				startDateS, (int)durationCount, durationUnit, groupId);
			String dueDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm").format(
				dueDateUtils.getDueDate());
			return Response.status(200).entity(dueDate).build();
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(500).entity(StringPool.BLANK).build();
	}

	private String _getZaloUidByTelNo(String token, String toTelNo) {

		try {

			HashMap<String, String> properties = new HashMap<String, String>();

			JSONObject data = JSONFactoryUtil.createJSONObject();

			data.put("user_id", toTelNo);

			String endPoint = "/v2.0/oa/getprofile?access_token=" + token +
				"&data=" + data.toJSONString();
			_log.info("end point=========" + endPoint);

			JSONObject resPostDossier = _callAPI(
				HttpMethods.GET, "application/json", "https://openapi.zalo.me",
				endPoint, StringPool.BLANK, StringPool.BLANK, properties);
			String uid = resPostDossier.getString("message");

			if (Validator.isNotNull(uid)) {

				return uid;
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
		return StringPool.BLANK;
	}

	private static JSONObject _getZaloInfo() {

		JSONObject zaloInfoConfig = JSONFactoryUtil.createJSONObject();

		try {

			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(
				SendSMSTerm.SERVER_CONFIG_SERVERNO_ZALO);

			zaloInfoConfig = JSONFactoryUtil.createJSONObject(sc.getConfigs());
		}
		catch (Exception e) {
			_log.debug(e);
		}

		return zaloInfoConfig;
	}

	private JSONObject _callAPI(
		String httpMethod, String accept, String pathBase, String endPoint,
		String username, String password, HashMap<String, String> properties) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {
			String urlPath;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/")) ||
				(pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(RESTFulConfiguration.TIME_OUT);

			conn.setRequestMethod(httpMethod);
			conn.setRequestProperty("Accept", accept);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("groupId", StringPool.BLANK);

			if (Validator.isNotNull(username) &&
				Validator.isNotNull(password)) {

				String authString = username + ":" + password;

				String authStringEnc = new String(
					java.util.Base64.getEncoder().encodeToString(
						authString.getBytes()));
				conn.setRequestProperty(
					"Authorization", "Basic " + authStringEnc);
			}

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(
						m.getKey().toString(), m.getValue().toString());
				}
			}

			BufferedReader br = new BufferedReader(
				new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response.put(RESTFulConfiguration.STATUS, conn.getResponseCode());
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

			conn.disconnect();

		}
		catch (MalformedURLException e) {
			_log.debug(e);
		}
		catch (IOException e1) {
			_log.debug(e1);
		}

		return response;
	}

	static Log _log = LogFactoryUtil.getLog(SMSManagementImpl.class.getName());
}

class RESTFulConfiguration {

	public static final String STATUS = "status";
	public static final String MESSAGE = "message";

	public static final String SUBMIT = "submit";
	public static final String TIMER = "timer";

	public static final int TIME_OUT = 3000;

}
