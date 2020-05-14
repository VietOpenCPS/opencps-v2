package org.opencps.api.constants;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.util.ConstantUtils;

public class LGSPTerm {

	private static final Log _log = LogFactoryUtil.getLog(LGSPTerm.class);

	public static final String LGSP_SERVER_NO_SYNC = "LGSP_SERVER_NO_SYNC";
	public static final String DICTCOLLECTION_REGION = "ADMINISTRATIVE_REGION";
	public static final String URI_GET_TOKEN = "uri_get_token";
	public static final String URI_GET_REGION = "uri_get_region";
	public static final String URI_GET_REGION_BY_ID = "uri_get_region_by_id";

	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String GRANT_TYPE = "grant_type";

	public static final String ACCESS_TOKEN = "access_token";
	public static final String SCOPE = "scope";
	public static final String TOKEN_TYPE = "token_type";
	public static final String EXPIRES_IN = "expires_in";

	public static final String OBJECTS = "objects";
	public static final String OBJECT = "object";

	public static final String REGION_ID = "id";
	public static final String REGION_MA = "ma";
	public static final String REGION_TEN = "ten";
	public static final String REGION_CAP = "cap";
	public static final String REGION_CHA_ID = "chaId";

	public static JSONObject callPostAPI(String httpMethod, String urlPath, HashMap<String, String> properties,
			Map<String, Object> params) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

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
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			StringBuilder postData = new StringBuilder();

			for (Map.Entry<String, Object> param : params.entrySet()) {

				if (postData.length() != 0) {
					postData.append(StringPool.AMPERSAND.charAt(0));
				}
				postData.append(java.net.URLEncoder.encode(param.getKey(), ConstantUtils.UTF_8));
				postData.append(StringPool.EQUAL.charAt(0));
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), ConstantUtils.UTF_8));
			}

			if (javax.ws.rs.HttpMethod.GET.equals(httpMethod)) {

				urlPath += StringPool.QUESTION + postData;
				_log.debug(urlPath);
			}
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			_log.debug(postData);
			if (javax.ws.rs.HttpMethod.POST.equals(httpMethod)) {

				byte[] postDataBytes = postData.toString().getBytes(ConstantUtils.UTF_8);

				conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, String.valueOf(postDataBytes.length));

				conn.getOutputStream().write(postDataBytes);
			}

			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			_log.debug(sb);
			response = JSONFactoryUtil.createJSONObject(sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {
			_log.error("Can't invoke api " + urlPath);
		} catch (IOException e) {
			_log.error("Can't invoke api " + urlPath);
		} catch (Exception e) {
			_log.error("Can't invoke api " + urlPath);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.error(e);
				}
			}

		}

		return response;
	}

	public static JSONObject getToken(ServerConfig sc) throws JSONException {

		JSONObject config = JSONFactoryUtil.createJSONObject(sc.getConfigs());
		String urlToken = config.getString(URI_GET_TOKEN);
		String clientId = config.getString(CLIENT_ID);
		String clientSecret = config.getString(CLIENT_SECRET);
		String grantType = config.getString(GRANT_TYPE);

		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		properties.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, Object> params = new HashMap<>();

		params.put(CLIENT_ID, clientId);
		params.put(CLIENT_SECRET, clientSecret);
		params.put(GRANT_TYPE, grantType);

		JSONObject token = callPostAPI(javax.ws.rs.HttpMethod.POST, urlToken, properties, params);
		return token;
	}

	public static JSONObject getRegion(int regionId) {

		JSONObject region = JSONFactoryUtil.createJSONObject();
		try {
			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(LGSP_SERVER_NO_SYNC);
			JSONObject token = getToken(sc);
			JSONObject config = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			String urlRegion = config.getString(URI_GET_REGION_BY_ID);

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			properties.put(HttpHeaders.AUTHORIZATION,
					token.getString(TOKEN_TYPE) + StringPool.SPACE + token.getString(ACCESS_TOKEN));

			Map<String, Object> params = new HashMap<>();

			params.put(REGION_ID, regionId);

			region = callPostAPI(javax.ws.rs.HttpMethod.GET, urlRegion, properties, params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return region;
	}

	public static JSONObject syncAllRegion() {

		JSONObject regions = JSONFactoryUtil.createJSONObject();
		try {
			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(LGSP_SERVER_NO_SYNC);
			JSONObject token = getToken(sc);
			JSONObject config = JSONFactoryUtil.createJSONObject(sc.getConfigs());
			String urlRegion = config.getString(URI_GET_REGION);

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			properties.put(HttpHeaders.AUTHORIZATION,
					token.getString(TOKEN_TYPE) + StringPool.SPACE + token.getString(ACCESS_TOKEN));

			Map<String, Object> params = new HashMap<>();

			params.put(REGION_ID, 80);

			regions = callPostAPI(javax.ws.rs.HttpMethod.GET, urlRegion, properties, params);
			if (!regions.has(OBJECTS)) {
				return regions;
			}
			JSONArray objects = regions.getJSONObject(OBJECTS).getJSONArray(OBJECT);

			DictCollection regionAdmin = DictCollectionLocalServiceUtil
					.fetchByF_dictCollectionCode(DICTCOLLECTION_REGION, 0);
			int total = 0;
			System.out.println("Sync regions total: " + objects.length());
			for (int i = 0; i < objects.length(); i++) {

				JSONObject re = objects.getJSONObject(i);
				DictItem dict = DictItemLocalServiceUtil.fetchByF_dictItemCode(re.getString(REGION_MA),
						regionAdmin.getDictCollectionId(), regionAdmin.getGroupId());
				if (Validator.isNotNull(dict)) {
					dict.setIdLGSP(re.getLong(REGION_ID));
					DictItemLocalServiceUtil.updateDictItem(dict);
					total++;
				}
			}
			System.out.println("Synced regions total: " + total);
			regions.put(ConstantUtils.TOTAL, total);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return regions;
	}
}
