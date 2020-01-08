package org.opencps.synchronization.scheduler;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.opencps.synchronization.util.MultipartUtility;

public class InvokeREST {

	private static final String FILE_PARAM_KEY = "file";

	public JSONObject callAPI(long groupId, String httpMethod, String accept, String pathBase, String endPoint,
			String username, String password, HashMap<String, String> properties, ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {

			URL url = new URL(pathBase + endPoint);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			String authString = username + StringPool.COLON + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
			conn.setRequestProperty(HttpHeaders.AUTHORIZATION,
					HttpServletRequest.BASIC_AUTH + StringPool.SPACE + authStringEnc);

			conn.setRequestMethod(httpMethod);
			conn.setRequestProperty(HttpHeaders.ACCEPT, accept);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty(Field.GROUP_ID, String.valueOf(groupId));

			if (!properties.isEmpty()) {
				for (Map.Entry<String, String> m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response.put(RESTFulConfiguration.STATUS, conn.getResponseCode());
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
		}

		return response;
	}

	public JSONObject callPostAPI(long groupId, String httpMethod, String accept, String pathBase, String endPoint,
			String username, String password, HashMap<String, String> properties, Map<String, Object> params,
			ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			URL url = new URL(pathBase + endPoint);

			conn = (HttpURLConnection) url.openConnection();

			String authString = username + StringPool.COLON + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			conn.setRequestProperty(HttpHeaders.AUTHORIZATION,
					HttpServletRequest.BASIC_AUTH + StringPool.SPACE + authStringEnc);

			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty(HttpHeaders.ACCEPT, accept);
			conn.setRequestProperty(Field.GROUP_ID, String.valueOf(groupId));

			if (!properties.isEmpty()) {
				for (Map.Entry<String, String> m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			StringBuilder postData = new StringBuilder();

			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append(StringPool.AMPERSAND);
				postData.append(java.net.URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8.name()));
				postData.append(StringPool.EQUAL);
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), StandardCharsets.UTF_8.name()));
			}

			byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8.name());

			conn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(postDataBytes.length));

			conn.getOutputStream().write(postDataBytes);

			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			StringBuilder sb = new StringBuilder();

			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			response.put(RESTFulConfiguration.STATUS, conn.getResponseCode());
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

			conn.disconnect();

		} catch (MalformedURLException e) {
			_log.error("Can't invoke api " + pathBase + endPoint, e);
		} catch (IOException e) {
			_log.error("Can't invoke api " + pathBase + endPoint, e);

		} finally {
			if (conn != null) {
				try {
				conn.disconnect();
				} catch (Exception e) {
					_log.error(e);
				}
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

	public JSONObject callPostFileAPI(long groupId, String httpMethod, String accept, String pathBase, String endPoint,
			String username, String password, HashMap<String, String> properties, File file,
			ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {

			String authString = username + StringPool.COLON + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			String requestURL = pathBase + endPoint;

			MultipartUtility multipart = new MultipartUtility(requestURL, StandardCharsets.UTF_8.name(), groupId, authStringEnc);
			// TODO; check logic here, if ref fileId in SERVER equal CLIENT

			multipart.addFilePart(FILE_PARAM_KEY, file);

			if (!properties.isEmpty()) {
				for (Map.Entry<String, String> m : properties.entrySet()) {
					multipart.addFormField(m.getKey().toString(), m.getValue().toString());

				}
			}

			List<String> res = multipart.finish();

			StringBuilder sb = new StringBuilder();

			for (String line : res) {
				sb.append(line);
			}

			response.put(RESTFulConfiguration.STATUS, HttpURLConnection.HTTP_OK);
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

		} catch (Exception e) {
			_log.error(e);
			response.put(RESTFulConfiguration.STATUS, HttpURLConnection.HTTP_FORBIDDEN);
			response.put(RESTFulConfiguration.MESSAGE, e.getMessage());
		}

		return response;
	}

	private Log _log = LogFactoryUtil.getLog(InvokeREST.class.getName());
}
