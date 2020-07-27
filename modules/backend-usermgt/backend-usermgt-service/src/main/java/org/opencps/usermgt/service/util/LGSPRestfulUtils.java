package org.opencps.usermgt.service.util;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.LGSPToken;
import org.opencps.communication.service.LGSPTokenLocalServiceUtil;
import org.opencps.usermgt.model.impl.LGSPTokenImpl;

public class LGSPRestfulUtils {

	private static Log _log = LogFactoryUtil.getLog(LGSPRestfulUtils.class);

	public static JSONObject createTokenLGSP(String tokenType) {

		JSONObject jsonToken = null;
		List<LGSPToken> tokenList = LGSPTokenLocalServiceUtil.getByTokenType(tokenType);
		boolean isGetToken = true;
		if (tokenList != null && tokenList.size() > 0) {
			LGSPToken token = tokenList.get(0);
			long expiryDate = token.getExpiryDate().getTime();
			long now = (new Date()).getTime();
			if (token != null && expiryDate - now > 300000) {
				jsonToken = JSONFactoryUtil.createJSONObject();
				jsonToken.put("token", token.getToken());
				jsonToken.put("refreshToken", token.getRefreshToken());
				jsonToken.put("expiryDate", token.getExpiryDate());
				//
				return jsonToken;
			}
		}

		if (isGetToken) {
			StringBuilder sbToken = new StringBuilder();
			try {

				URL urlToken = new URL("http://api.dongthap.gov.vn/api/v1/Authentication/Token");

				java.net.HttpURLConnection conToken = (java.net.HttpURLConnection) urlToken.openConnection();
				conToken.setRequestMethod(HttpMethod.POST);
				conToken.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
				conToken.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
				conToken.setRequestProperty("Auth", "WVdSdGFXND06WVdSdGFXNUFNZz09");
				conToken.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(0));

				conToken.setUseCaches(false);
				conToken.setDoInput(true);
				conToken.setDoOutput(true);

				OutputStream os = conToken.getOutputStream();
				os.close();

				BufferedReader brfToken = new BufferedReader(new InputStreamReader(conToken.getInputStream()));

				int cpToken;
				while ((cpToken = brfToken.read()) != -1) {
					sbToken.append((char) cpToken);
				}
			} catch (Exception e) {
				_log.debug(e);
			}
			if (sbToken != null && Validator.isNotNull(sbToken.toString())) {
				try {
					jsonToken = JSONFactoryUtil.createJSONObject(sbToken.toString());
					//
					if (jsonToken.has("token") && jsonToken.has("refreshToken") && jsonToken.has("expiryDate")) {
						Date expiryDate = APIDateTimeUtils.convertStringToDate(jsonToken.getString("expiryDate"),
								APIDateTimeUtils._TIMESTAMP);
						if (expiryDate != null) {
							LGSPToken lgspToken = LGSPTokenLocalServiceUtil.updateLGSPToken(
									jsonToken.getString("token"), "Bearer", jsonToken.getString("refreshToken"),
									expiryDate);
							if (lgspToken != null) {
								return jsonToken;
							}
						}
					}
				} catch (JSONException e1) {
					_log.debug(e1);
				}
			}
		}

		return jsonToken;
	}


}
