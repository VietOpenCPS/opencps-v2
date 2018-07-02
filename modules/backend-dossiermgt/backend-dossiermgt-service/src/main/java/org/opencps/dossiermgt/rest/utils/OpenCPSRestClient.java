package org.opencps.dossiermgt.rest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

public class OpenCPSRestClient {
	private String username;
	private String password;
	private long groupId;
	
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	private String baseUrl;

	public OpenCPSRestClient(String baseUrl) {
		if (baseUrl.charAt(baseUrl.length() - 1) == '/' && baseUrl.length() >= 2) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 2);
		} else {
			this.baseUrl = baseUrl;
		}
	}

	private static final String DOSSIERS_BASE_PATH = "/dossiers";
	
	public static OpenCPSRestClient fromJSONObject(JSONObject configObj) {
		if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
				&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
				&& configObj.has(SyncServerTerm.SERVER_URL)
				&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
			return new OpenCPSRestClient(
					configObj.getString(SyncServerTerm.SERVER_USERNAME), 
					configObj.getString(SyncServerTerm.SERVER_PASSWORD), 
					configObj.getString(SyncServerTerm.SERVER_URL),
					configObj.getLong(SyncServerTerm.SERVER_GROUP_ID));
		}
		else {
			return null;
		}
	}
	
	public OpenCPSRestClient(String username, String password, String baseUrl, long groupId) {
		this.username = username;
		this.password = password;
		if (baseUrl.charAt(baseUrl.length() - 1) == '/' && baseUrl.length() >= 2) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 2);
		}
		else {
			this.baseUrl = baseUrl;
		}
		this.groupId = groupId;
	}
		
	public DossierDetailModel postDossier(DossierInputModel model) {
		DossierDetailModel result = null;
		
		try {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials
			 = new UsernamePasswordCredentials(username, password);
			provider.setCredentials(AuthScope.ANY, credentials);
			  
			CloseableHttpClient httpClient = HttpClientBuilder.create()
			  .setDefaultCredentialsProvider(provider)
			  .build();
			
		    List<NameValuePair> params = OpenCPSConverter.convertHttpParams(model);
		    
			
			HttpPost postRequest = new HttpPost(baseUrl + DOSSIERS_BASE_PATH);
			postRequest.setHeader("groupId", String.valueOf(groupId));
			postRequest.setHeader("Accept", "application/json");
			
			String base64String = username + ":" + password;
			postRequest.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString(base64String.getBytes()));
			postRequest.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
 
			CloseableHttpResponse httpresponse = httpClient.execute(postRequest);
						
			if (httpresponse.getStatusLine().getStatusCode() >= 401) {
				return result;
			}			
			
			BufferedReader br = new BufferedReader(new InputStreamReader((httpresponse.getEntity().getContent())));
			String output = "";
			
			StringBuilder jsonString = new StringBuilder();

			while ((output = br.readLine()) != null) {
				jsonString.append(output);
			}
			
			JSONObject jsonObj = JSONFactoryUtil.createJSONObject(jsonString.toString());

			result = OpenCPSConverter.convertDossierDetail(jsonObj);
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
}
