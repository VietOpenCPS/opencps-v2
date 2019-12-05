package org.opencps.api.controller.util;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.HttpMethod;

public class ElasticQueryWrapUtil {
	private static Log _log = LogFactoryUtil.getLog(ElasticQueryWrapUtil.class);
	private static final String ELASTIC_SERVER_API_URL = "http://localhost:9200/_search";
	private static final String OPENCPS_SERVER_API_URL = "org.opencps.elasticsearch.server.api.url";
	
	private static String getElasticSearchApiUrl() {
		String apiUrl = PropsUtil.get(OPENCPS_SERVER_API_URL);
		return (Validator.isNull(apiUrl)) ? ELASTIC_SERVER_API_URL : apiUrl;
	}
	
	public static JSONObject query(String q) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		try {
			URL url = new URL(getElasticSearchApiUrl());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod(HttpMethod.POST);
			conn.setRequestProperty("Accept", "Content-Type: application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write(q);
			osw.flush();
			osw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			result = JSONFactoryUtil.createJSONObject(sb.toString());
		} catch (JSONException e) {
			_log.error("JSONException Message: "+e);
		} catch (IOException e) {
			_log.error("IOException Message: "+e);
		}

		return result;
	}

}
