package org.opencps.synchronization.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.rest.model.DictCollectionModel;
import org.opencps.synchronization.rest.model.DictGroupModel;
import org.opencps.synchronization.rest.model.DictItemModel;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DictDataRestClient {
	private String username;
	private String password;
	private String baseUrl;
	private long groupId;
	private static Log _log = LogFactoryUtil.getLog(DictDataRestClient.class);
	
	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	private static final String DICT_COLLLECTIONS_BASE_PATH = "/dictcollections";
	
	public static DictDataRestClient fromJSONObject(JSONObject configObj) {
		if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
				&& configObj.has(SyncServerTerm.SERVER_SECRET)
				&& configObj.has(SyncServerTerm.SERVER_URL)
				&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
			return new DictDataRestClient(
					configObj.getString(SyncServerTerm.SERVER_USERNAME), 
					configObj.getString(SyncServerTerm.SERVER_SECRET), 
					configObj.getString(SyncServerTerm.SERVER_URL),
					configObj.getLong(SyncServerTerm.SERVER_GROUP_ID));
		}
		else {
			return null;
		}
	}
	
	public DictDataRestClient(String username, String password, String baseUrl, long groupId) {
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
	
	public DictCollectionModel getCollectionDetail(String collectionCode) {
		DictCollectionModel result = null;
		
		try {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials
			 = new UsernamePasswordCredentials(username, password);
			provider.setCredentials(AuthScope.ANY, credentials);
			  
			CloseableHttpClient httpClient = HttpClientBuilder.create()
			  .setDefaultCredentialsProvider(provider)
			  .build();
			HttpGet getRequest = new HttpGet(baseUrl + DICT_COLLLECTIONS_BASE_PATH + "/" + URLEncoder.encode(collectionCode, StandardCharsets.UTF_8.toString()));
 			Header groupHeader = new BasicHeader("groupId", String.valueOf(groupId));
 			getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
 			getRequest.addHeader(groupHeader);
 			
			HttpResponse getqueryresponse = httpClient.execute(getRequest);
 			 			
			if (getqueryresponse.getStatusLine().getStatusCode() == SyncServerTerm.STATUS_OK) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader((getqueryresponse.getEntity().getContent())))) {
					String output = "";
					StringBuilder jsonStr = new StringBuilder();
					
					while ((output = br.readLine()) != null) {
						jsonStr.append(output);
					}
	
					JSONObject obj = JSONFactoryUtil.createJSONObject(jsonStr.toString());
	
					httpClient.close();	
					
					result = DictCollectionModel.fromJSONObject(obj);
				}
			}
		} catch (MalformedURLException e) {
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}			
		
		return result;
	}
	
	public DictItemModel getItemDetail(String code, String itemCode) {
		DictItemModel result = null;
		
		try {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials
			 = new UsernamePasswordCredentials(username, password);
			provider.setCredentials(AuthScope.ANY, credentials);
			  
			CloseableHttpClient httpClient = HttpClientBuilder.create()
			  .setDefaultCredentialsProvider(provider)
			  .build();
			HttpGet getRequest = new HttpGet(baseUrl + DICT_COLLLECTIONS_BASE_PATH + "/" + URLEncoder.encode(code, StandardCharsets.UTF_8.toString()) + "/dictitems/" + URLEncoder.encode(itemCode, StandardCharsets.UTF_8.toString()));
 			
			Header groupHeader = new BasicHeader("groupId", String.valueOf(groupId));
 			getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
 			getRequest.addHeader(groupHeader);
 			
			HttpResponse getqueryresponse = httpClient.execute(getRequest);
						
			if (getqueryresponse.getStatusLine().getStatusCode() == SyncServerTerm.STATUS_OK) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader((getqueryresponse.getEntity().getContent())))) {
					String output = "";
					StringBuilder jsonStr = new StringBuilder();
					
					while ((output = br.readLine()) != null) {
						jsonStr.append(output);
					}
					
					JSONObject obj = JSONFactoryUtil.createJSONObject(jsonStr.toString());
	
					httpClient.close();	
					
					result = DictItemModel.fromJSONObject(obj);
				}
			}
		} catch (MalformedURLException e) {
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}	
	
	public DictGroupModel getGroupDetail(String collectionCode, String groupCode) {
		DictGroupModel result = null;
		
		try {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials
			 = new UsernamePasswordCredentials(username, password);
			provider.setCredentials(AuthScope.ANY, credentials);
			  
			CloseableHttpClient httpClient = HttpClientBuilder.create()
			  .setDefaultCredentialsProvider(provider)
			  .build();
			HttpGet getRequest = new HttpGet(baseUrl + DICT_COLLLECTIONS_BASE_PATH + "/" + URLEncoder.encode(collectionCode, StandardCharsets.UTF_8.toString()) + "/dictgroups/" + URLEncoder.encode(groupCode, StandardCharsets.UTF_8.toString()));
 			Header groupHeader = new BasicHeader("groupId", String.valueOf(groupId));
 			getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
 			getRequest.addHeader(groupHeader);
 			
			HttpResponse getqueryresponse = httpClient.execute(getRequest);
 			 			
			if (getqueryresponse.getStatusLine().getStatusCode() == SyncServerTerm.STATUS_OK) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader((getqueryresponse.getEntity().getContent())))) {
					String output = "";
					StringBuilder jsonStr = new StringBuilder();
					
					while ((output = br.readLine()) != null) {
						jsonStr.append(output);
					}
	
					JSONObject obj = JSONFactoryUtil.createJSONObject(jsonStr.toString());
	
					httpClient.close();	
					
					result = DictGroupModel.fromJSONObject(obj);
				}
			}
		} catch (MalformedURLException e) {
			_log.error(e);
		} catch (IOException e) {
			_log.error(e);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}	
}
