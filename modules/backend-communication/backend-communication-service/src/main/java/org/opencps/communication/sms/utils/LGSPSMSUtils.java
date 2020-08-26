package org.opencps.communication.sms.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.communication.utils.LGSPRestfulUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

public class LGSPSMSUtils {

	public static String sendSMS(long groupId, String body, String title, String toTelNo) {
		JSONObject jsonToken = LGSPRestfulUtils.createTokenLGSP("Bearer");
		if (jsonToken != null && jsonToken.has("token") && jsonToken.has("refreshToken")
				&& jsonToken.has("expiryDate")) {

			String strUrlSendSMS = "http://api.dongthap.gov.vn/api/v1/congdan/SendSms";
			String authStrEnc = "Bearer" + StringPool.SPACE + jsonToken.getString("token");
			//_log.info("authStrEnc: "+ authStrEnc);
			String toTelNoRpl = toTelNo;
			if ("+84".equals(toTelNo.substring(0, 2))) {
				toTelNoRpl = toTelNo.replace("+84", "0");
			} else if ("84".equals(toTelNo.substring(0, 1))) {
				toTelNoRpl = toTelNo.replace("84", "0");
			}

			for (int i = 0; i < 8; i++) {
				StringBuilder sbSendSMS = new StringBuilder();
				try {
					URL urlSendSMS = new URL(strUrlSendSMS);

					JSONObject jsonBody = JSONFactoryUtil.createJSONObject();

					jsonBody.put("content", body);
					jsonBody.put("sender", "He thong dich vu cong va mot cua dien tu Tinh Dong Thap");
					jsonBody.put("receiver", toTelNoRpl);
					jsonBody.put("applicationTitle", title);
					//
					java.net.HttpURLConnection conSendSMS = (java.net.HttpURLConnection) urlSendSMS.openConnection();
					conSendSMS.setRequestMethod(HttpMethod.POST);
					conSendSMS.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
					conSendSMS.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
					conSendSMS.setRequestProperty(HttpHeaders.AUTHORIZATION, authStrEnc);
					_log.debug("BASIC AUTHEN: " + authStrEnc);
					conSendSMS.setRequestProperty(HttpHeaders.CONTENT_LENGTH,
							StringPool.BLANK + Integer.toString(jsonBody.toString().getBytes().length));

					conSendSMS.setUseCaches(false);
					conSendSMS.setDoInput(true);
					conSendSMS.setDoOutput(true);
					_log.debug("POST DATA: " + jsonBody.toString());
					OutputStream osSMS = conSendSMS.getOutputStream();
					osSMS.write(jsonBody.toString().getBytes());
					osSMS.close();

					BufferedReader brfSMS = new BufferedReader(new InputStreamReader(conSendSMS.getInputStream()));

					int cpSendSMS;
					while ((cpSendSMS = brfSMS.read()) != -1) {
						sbSendSMS.append((char) cpSendSMS);
					}
					_log.info("RESULT PROXY: " + sbSendSMS.toString());
					if (Validator.isNotNull(sbSendSMS.toString()) ) {
						JSONObject jsonResult = JSONFactoryUtil.createJSONObject(sbSendSMS.toString());
						if (jsonResult != null && jsonResult.has("result") &&
								"SUCCESSFUL".equals(jsonResult.getString("result"))) {
							break;
						}
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						_log.info("Time not delay");
						Thread.currentThread().interrupt();
					}
					//
				} catch (Exception e) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						_log.info("Time not delay");
						Thread.currentThread().interrupt();
					}
					_log.debug(e);
				}
			}

		}

		return StringPool.BLANK;
	}

	private static final Log _log =
		LogFactoryUtil.getLog(LGSPSMSUtils.class);
}
