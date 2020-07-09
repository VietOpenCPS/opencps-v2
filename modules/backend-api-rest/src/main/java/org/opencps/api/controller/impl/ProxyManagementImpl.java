package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ProxyManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.MultipartUtility;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ProxyManagementImpl implements ProxyManagement {

	private static final Log _log = LogFactoryUtil.getLog(ProxyManagementImpl.class);

	public static byte[] readAllBytes(InputStream inputStream) throws IOException {
	    final int bufLen = 4 * 0x400; // 4KB
	    byte[] buf = new byte[bufLen];
	    int readLen;
	    IOException exception = null;

	    try {
	        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
	                outputStream.write(buf, 0, readLen);

	            return outputStream.toByteArray();
	        }
	    } catch (IOException e) {
	        exception = e;
	        throw e;
	    } finally {
	        if (exception == null) inputStream.close();
	        else try {
	            inputStream.close();
	        } catch (IOException e) {
	            exception.addSuppressed(e);
	        }
	    }
	}
	
	@Override
	public Response proxy(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String url, String method, String data, String serverCode, String dataType) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : ConstantUtils.PROXY_SERVER_DVC;
			
			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
			_log.debug("SERVER PROXY: " + sc.getConfigs());
			if (sc != null) {
				JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
				String serverUrl = StringPool.BLANK;
		        String authStrEnc = StringPool.BLANK;
				
	
			    String apiUrl;
			    
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
					    if (!StringPool.BLANK.equals(postData.toString())) {
					    	postData.append(StringPool.AMPERSAND);
					    }
					    postData.append(key);
					    postData.append(StringPool.EQUAL);
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
			        
					
					if (ConstantUtils.PROXY_STATISTICS_ENDPOINT.equalsIgnoreCase(url) && serverUrl.contains(ConstantUtils.PROXY_V2_ENDPOINT)) {
						apiUrl = serverUrl.replace(ConstantUtils.PROXY_V2_ENDPOINT, url);
					} else {
						apiUrl = serverUrl + url;
					}
			        if (ConstantUtils.METHOD_GET.equals(method)) {
						urlVal = new URL(apiUrl + StringPool.QUESTION + postData.toString());			        	
			        }
			        else {
			        	urlVal = new URL(apiUrl);
			        }
			        _log.debug("API URL: " + apiUrl);
					java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
			        conn.setRequestProperty(Field.GROUP_ID, groupIdRequest);
			        conn.setRequestMethod(method);
			        conn.setRequestProperty(HttpHeaders.ACCEPT, ConstantUtils.CONTENT_TYPE_JSON);
			        conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
			        
			        String authorization = String.format(MessageUtil.getMessage(ConstantUtils.HTTP_HEADER_BASICAUTH), authStrEnc);
			        conn.setRequestProperty(HttpHeaders.AUTHORIZATION, authorization);
			        _log.debug("BASIC AUTHEN: " + authStrEnc);
			        if (ConstantUtils.METHOD_POST.equals(method) || ConstantUtils.METHOD_PUT.equals(method)) {
				        conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, ConstantUtils.CONTENT_TYPE_XXX_FORM_URLENCODED);
						conn.setRequestProperty(ConstantUtils.CONTENT_LENGTH, StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));

						conn.setUseCaches(false);
						conn.setDoInput(true);
						conn.setDoOutput(true);
						_log.debug("POST DATA: " + postData.toString());
						OutputStream os = conn.getOutputStream();
						os.write( postData.toString().getBytes() );    
						os.close();			        	
			        }
			        if (dataType != null && "binary".contentEquals(dataType)) {
					    byte[] bytes = readAllBytes(conn.getInputStream());
					   _log.debug("byte result:" + bytes != null);
						return Response.status(HttpURLConnection.HTTP_OK).entity(bytes).
								header(HttpHeaders.CONTENT_TYPE, conn.getContentType())
								.build();			        			        	
			        }
			        else {
				        BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			        
				        int cp;
					    while ((cp = brf.read()) != -1) {
					      sb.append((char) cp);
					    }
					    _log.debug("sb.tostring : "+ sb.toString());
						return Response.status(HttpURLConnection.HTTP_OK).entity(sb.toString()).
								build();			        			        	
			        }
			    }
				catch (IOException e) {
					_log.error("err ",e);
					_log.debug("Something went wrong while reading/writing in stream!!");
				}
			    //return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("").build();
			}
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(StringPool.BLANK).build();
		}
		catch (Exception e) {
			_log.debug("exception :" +e );
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response proxyMultipart(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file, String url, String method, String data,
			String serverCode) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {
			String serverCodeFind = Validator.isNotNull(serverCode) ? serverCode : ConstantUtils.PROXY_SERVER_DVC;
			
			ServerConfig sc = ServerConfigLocalServiceUtil.getByCode(groupId, serverCodeFind);
			_log.debug("SERVER PROXY: " + sc.getConfigs());
			if (sc != null) {
				JSONObject configObj = JSONFactoryUtil.createJSONObject(sc.getConfigs());
				String serverUrl = StringPool.BLANK;
		        String authStrEnc = StringPool.BLANK;
				
	
			    String apiUrl;
			    
			    StringBuilder sb = new StringBuilder();
			    try
			    {
			        URL urlVal = null;
			        String groupIdRequest = StringPool.BLANK;
			        
					if (configObj.has(SyncServerTerm.SERVER_USERNAME) 
							&& configObj.has(SyncServerTerm.SERVER_SECRET)
							&& configObj.has(SyncServerTerm.SERVER_URL)
							&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
						authStrEnc = Base64.getEncoder().encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME) + ":" + configObj.getString(SyncServerTerm.SERVER_SECRET)).getBytes());
						
						serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
				        groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
					}
			        
					
					if (ConstantUtils.PROXY_STATISTICS_ENDPOINT.equalsIgnoreCase(url) && serverUrl.contains(ConstantUtils.PROXY_V2_ENDPOINT)) {
						apiUrl = serverUrl.replace(ConstantUtils.PROXY_V2_ENDPOINT, url);
					} else {
						apiUrl = serverUrl + url;
					}
			        _log.debug("API URL: " + apiUrl);
			        if (ConstantUtils.METHOD_POST.equals(method) || ConstantUtils.METHOD_PUT.equals(method)) {
						JSONObject dataObj = JSONFactoryUtil.createJSONObject(data);
						Iterator<?> keys = dataObj.keys();
						MultipartUtility multipart = new MultipartUtility(apiUrl, "UTF-8", Integer.parseInt(groupIdRequest), authStrEnc, method);
						while(keys.hasNext() ) {
						    String key = (String)keys.next();
						    multipart.addFormField(key, dataObj.getString(key));
						}
						
						if (file != null) {
							multipart.addFilePartDataHandler("file", file);				
						}
						else {
							//multipart.addFormField("file", StringPool.BLANK);
						}
						List<String> res = multipart.finish();
						sb = new StringBuilder();

						for (String line : res) {
							sb.append(line);
						}
			        }

					
			        _log.debug("RESULT PROXY: " + sb.toString());
					return Response.status(HttpURLConnection.HTTP_OK).entity(sb.toString()).build();			        
			    }
				catch (IOException e) {
					_log.error(e);
					_log.debug("Something went wrong while reading/writing in stream!!");
				}
			    //return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("").build();
			}
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(StringPool.BLANK).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}
}
