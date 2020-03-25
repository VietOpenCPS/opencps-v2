package org.opencps.usermgt.scheduler.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

public class AdministrativeRegionUtils {

	private static Log _log = LogFactoryUtil.getLog(AdministrativeRegionUtils.class);
	private static final String URL_TOKEN = "https://lgsp.dongthap.gov.vn/token";
	private static final String CLIENT_SECRET = "UADxohCzodLZ3kRVaMMLFKixo5ka";
	private static final String CLIENT_ID = "fcgEIj6eC1yPuM4ugImfLLBkudYa";
	private static final String GRANT_TYPE = "client_credentials";
	private static final String urlLGSPRoot = "https://lgsp.dongthap.gov.vn/dldc/1.0.0";
	
	public static Map<String, String> getInfoRegion(String regionId) throws Exception {
		/** Register LGSP */
		Map<String, String> mapResult = new HashMap<>();
		// Get token
		trustAPISSL();

		StringBuilder sb = new StringBuilder();
		try {
			URL urlVal = new URL(URL_TOKEN);
			String postData = getParamAPI();
			_log.info("API URL: " + urlVal);
			_log.info("postData: " + postData);
			//
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
			conn.setRequestMethod(HttpMethod.POST);
			conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH,
					StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));

			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(postData.getBytes());
			os.close();

			BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			int cp;
			while ((cp = brf.read()) != -1) {
				sb.append((char) cp);
			}
			_log.info("RESULT PROXY: " + sb.toString());
		}
		catch (IOException e) {
			_log.error(e);
			_log.debug("Something went wrong while reading/writing in stream!!");
		}
		if (sb.toString().length() > 0) {
			JSONObject jsonToken = JSONFactoryUtil.createJSONObject(sb.toString());
			if (Validator.isNotNull(jsonToken)) {
				String tokenType = jsonToken.getString("token_type");
				String accessToken = jsonToken.getString("access_token");

				//Convert Object to JSONObject
				URL urlValCity = new URL(urlLGSPRoot + StringPool.QUESTION + "id=" + regionId);
				//
				java.net.HttpURLConnection connCity = (java.net.HttpURLConnection) urlValCity.openConnection();
				connCity.setRequestMethod(HttpMethod.GET);
				connCity.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
				connCity.setRequestProperty(HttpHeaders.AUTHORIZATION, tokenType + StringPool.SPACE + accessToken);

				connCity.setUseCaches(false);
				connCity.setDoInput(true);
				connCity.setDoOutput(true);

				BufferedReader brfRegion = new BufferedReader(new InputStreamReader(connCity.getInputStream()));

				int cpRegion;
				StringBuilder sbRegion = new StringBuilder();
				while ((cpRegion = brfRegion.read()) != -1) {
					sbRegion.append((char) cpRegion);
				}
				_log.info("RESULT Tinh: " + sbRegion.toString());
				//Kich hoat tai khoan cong dan
				if (sbRegion.toString().length() > 0) {
					JSONObject jsonCity = JSONFactoryUtil.createJSONObject(sbRegion.toString());
					if (Validator.isNotNull(jsonCity) && jsonCity.has("objects")) {
						JSONObject jsonDetailCity = JSONFactoryUtil.createJSONObject(jsonCity.getString("object"));
						//
						mapResult.put("ma", jsonDetailCity.getString("ma"));
						mapResult.put("ten", jsonDetailCity.getString("ten"));
					}
				}
			}
		}
		return mapResult;
	}

	private static void trustAPISSL() {
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
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		}
	}

	private static String getParamAPI() {
		StringBuilder postData = new StringBuilder();
		//
		postData.append("client_secret");
		postData.append(StringPool.EQUAL);
		postData.append(CLIENT_SECRET);
		//
		if (!StringPool.BLANK.equals(postData.toString())) {
			postData.append(StringPool.AMPERSAND);
		}
		postData.append("client_id");
		postData.append(StringPool.EQUAL);
		postData.append(CLIENT_ID);
		//
		if (!StringPool.BLANK.equals(postData.toString())) {
			postData.append(StringPool.AMPERSAND);
		}
		postData.append("grant_type");
		postData.append(StringPool.EQUAL);
		postData.append(GRANT_TYPE);
		//
		return postData.toString();
	}
}
