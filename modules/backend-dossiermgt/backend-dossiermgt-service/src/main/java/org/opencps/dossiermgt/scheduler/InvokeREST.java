package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.action.util.MultipartUtility;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;

public class InvokeREST {
	
	public JSONObject callAPI(long groupId, String httpMethod, String accept, String pathBase, String endPoint,
			String username, String password, HashMap<String, String> properties, ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {
			String urlPath;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
			
			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
			
			conn.setRequestMethod(httpMethod);
			conn.setRequestProperty("Accept", accept);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("groupId", String.valueOf(groupId));

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
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
//			e.printStackTrace();
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
//			e.printStackTrace();

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

			String urlPath = pathBase;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", accept);
			conn.setRequestProperty("groupId", String.valueOf(groupId));

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			StringBuilder postData = new StringBuilder();

			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(java.net.URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}

			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

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
			_log.error(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);
		} catch (IOException e) {
			_log.debug(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);

		} finally {
			if (conn != null) {
				conn.disconnect();
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.debug(e);
					//_log.error(e);
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

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			String requestURL;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				requestURL = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				requestURL = pathBase + endPoint;
			}
			else {
				requestURL = pathBase + "/" + endPoint;
			}

			
			MultipartUtility multipart = new MultipartUtility(requestURL, "UTF-8", groupId, authStringEnc);
			// TODO; check logic here, if ref fileId in SERVER equal CLIENT

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					multipart.addFormField(m.getKey().toString(), m.getValue().toString());
				}
			}

			if (file != null) {
				multipart.addFilePart("file", file);				
			}
			else {
				multipart.addFormField("file", StringPool.BLANK);
			}

			List<String> res = multipart.finish();

			StringBuilder sb = new StringBuilder();

			for (String line : res) {
				sb.append(line);
			}

			response.put(RESTFulConfiguration.STATUS, HttpURLConnection.HTTP_OK);
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			response.put(RESTFulConfiguration.STATUS, HttpURLConnection.HTTP_FORBIDDEN);
			response.put(RESTFulConfiguration.MESSAGE, e.getMessage());
		}

		return response;
	}

	public JSONObject callPostFileAPIWithFileName(long groupId, String httpMethod, String accept, String pathBase, String endPoint,
			String username, String password, HashMap<String, String> properties, File file, String fileName,
			ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			String requestURL;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				requestURL = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				requestURL = pathBase + endPoint;
			}
			else {
				requestURL = pathBase + "/" + endPoint;
			}
			
			MultipartUtility multipart = new MultipartUtility(requestURL, "UTF-8", groupId, authStringEnc);
			// TODO; check logic here, if ref fileId in SERVER equal CLIENT

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					multipart.addFormField(m.getKey().toString(), m.getValue().toString());
				}
			}

			if (file != null) {
				multipart.addFilePartWithFileName("file", file, fileName);				
			}
			else {
				multipart.addFormField("file", StringPool.BLANK);
			}

			List<String> res = multipart.finish();
			StringBuilder sb = new StringBuilder();

			for (String line : res) {
				sb.append(line);
			}

			response.put(RESTFulConfiguration.STATUS, HttpURLConnection.HTTP_OK);
			response.put(RESTFulConfiguration.MESSAGE, sb.toString());

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			response.put(RESTFulConfiguration.STATUS, HttpURLConnection.HTTP_FORBIDDEN);
			response.put(RESTFulConfiguration.MESSAGE, e.getMessage());
		}

		return response;
	}

	//Process delete dossier
	public JSONObject callDeleteAPI(long groupId, String httpMethod, String accept, String pathBase, String endPoint,
			String username, String password, HashMap<String, String> properties, ServiceContext serviceContext) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			String urlPath = pathBase;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);

			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", accept);
			conn.setRequestProperty("groupId", String.valueOf(groupId));

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

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
			_log.debug(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);
		} catch (IOException e) {
			_log.debug(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);

		} finally {
			if (conn != null) {
				conn.disconnect();
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.debug(e);
					//_log.error(e);
				}
			}

		}

		return response;
	}
	
	public JSONObject callPostAPI(String httpMethod, String accept, String pathBase, String endPoint,
			HashMap<String, String> properties, Map<String, Object> params) {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			String urlPath = pathBase;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());

			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", accept);

			if (!properties.isEmpty()) {
				for (Map.Entry m : properties.entrySet()) {
					conn.setRequestProperty(m.getKey().toString(), m.getValue().toString());
				}
			}

			StringBuilder postData = new StringBuilder();

			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0)
					postData.append('&');
				postData.append(java.net.URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}

			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

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
			_log.error(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);
		} catch (IOException e) {
			_log.debug(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);

		} finally {
			if (conn != null) {
				conn.disconnect();
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.debug(e);
					//_log.error(e);
				}
			}

		}

		return response;
	}
	
	public JSONObject callPostAPIRaw(String token, String httpMethod, String accept, String pathBase, String endPoint, String raw) {
		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpURLConnection conn = null;

		BufferedReader br = null;

		try {

			String urlPath = pathBase;
			if (pathBase.endsWith("/") && endPoint.startsWith("/")) {
				String endPoint2 = endPoint.substring(1);
				urlPath = pathBase + endPoint2;
			}
			else if ((!pathBase.endsWith("/") && endPoint.startsWith("/"))
					|| (pathBase.endsWith("/") && !endPoint.startsWith("/"))) {
				urlPath = pathBase + endPoint;
			}
			else {
				urlPath = pathBase + "/" + endPoint;
			}
			URL url = new URL(urlPath);

			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
			conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());

			conn.setRequestMethod(httpMethod);
			conn.setDoInput(true);
			conn.setDoOutput(true);

			conn.setRequestProperty("Accept", accept);
			conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setRequestProperty("Content-Type", "application/json");
			
			byte[] postDataBytes = raw.getBytes("UTF-8");

			conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
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
			_log.error(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);
		} catch (IOException e) {
			_log.debug(e);
			//_log.error(e);
			_log.error("Can't invoke api " + pathBase + endPoint);

		} finally {
			if (conn != null) {
				conn.disconnect();
			}

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					_log.debug(e);
					//_log.error(e);
				}
			}

		}

		return response;
	}
	
	private Log _log = LogFactoryUtil.getLog(InvokeREST.class.getName());
}
