package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ProxyManagement;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ProxyManagementImpl implements ProxyManagement {

	private static final Log _log = LogFactoryUtil.getLog(ProxyManagementImpl.class);

	@Override
	public Response proxy(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String url, String method, String data, String serverCode) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {
			String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : "SERVER_DVC";
			
			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
			
			if (sc != null) {
				JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
				String serverUrl = StringPool.BLANK;
		        String authStrEnc = StringPool.BLANK;
				
	
			    String apiUrl = StringPool.BLANK;
			    
			    StringBuilder sb = new StringBuilder();
			    try
			    {
			        URL urlVal = null;
			        String groupIdRequest = StringPool.BLANK;
			        StringBuilder postData = new StringBuilder();
					JSONObject dataObj = JSONFactoryUtil.createJSONObject(data);
					Iterator<?> keys = dataObj.keys();
					while(keys.hasNext() ) {
					    String key = (String)keys.next();
					    if (!"".equals(postData.toString())) {
					    	postData.append("&");
					    }
					    postData.append(key);
					    postData.append("=");
					    postData.append(dataObj.get(key));
					}
			        
					if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
							&& configObj.has(SyncServerTerm.SERVER_SECRET)
							&& configObj.has(SyncServerTerm.SERVER_URL)
							&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
						authStrEnc = Base64.getEncoder().encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME) + ":" + configObj.getString(SyncServerTerm.SERVER_SECRET)).getBytes());
						
						serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
				        groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
					}
			        
					apiUrl = serverUrl + url;
			        if ("GET".equals(method)) {
						urlVal = new URL(apiUrl + "?" + postData.toString());			        	
			        }
			        else {
			        	urlVal = new URL(apiUrl);
			        }

					java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
			        conn.setRequestProperty("groupId", groupIdRequest);
			        conn.setRequestMethod(method);
			        conn.setRequestProperty("Accept", "application/json");
			        conn.setRequestProperty("Authorization", "Basic " + authStrEnc);
			        
			        if ("POST".equals(method) || "PUT".equals(method)) {
				        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.toString().getBytes().length));

						conn.setUseCaches(false);
						conn.setDoInput(true);
						conn.setDoOutput(true);
						
						OutputStream os = conn.getOutputStream();
						os.write( postData.toString().getBytes() );    
						os.close();			        	
			        }

					
			        BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			        			        
			        int cp;
			        while ((cp = brf.read()) != -1) {
			          sb.append((char) cp);
			        }

					return Response.status(HttpURLConnection.HTTP_OK).entity(sb.toString()).build();			        
			    }
			    catch(IOException e)
			    {
			        _log.debug("Something went wrong while reading/writing in stream!!");
			    }
			    return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("").build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("").build();
			}		
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
}
