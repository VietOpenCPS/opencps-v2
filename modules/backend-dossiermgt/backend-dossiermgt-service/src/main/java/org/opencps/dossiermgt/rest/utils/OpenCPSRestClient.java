package org.opencps.dossiermgt.rest.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class OpenCPSRestClient {
	private Log _log = LogFactoryUtil.getLog(OpenCPSRestClient.class);
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
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = OpenCPSConverter.convertHttpParams(model);
		ServiceContext context = new ServiceContext();
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json",
				baseUrl,DOSSIERS_BASE_PATH, username,
				password, properties, params, context);

		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}
	
	public DossierFileModel postDossierFile(File file, String dossierUnique, DossierFileModel model) {
		DossierFileModel result = null;

		try {

			String requestURL = DOSSIERS_BASE_PATH + "/" + dossierUnique + "/files";
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierFileHttpParams(model);
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostFileAPI(groupId, HttpMethod.POST, "application/json", 
					 baseUrl, requestURL, username,
					password, properties, file, context);
			_log.info("Post dossier file: " + jsonObj);
			result = OpenCPSConverter.convertDossierFile(jsonObj);
			
			return result;
		} catch (Exception e) {
		}

		return result;
		
	}
	
	public ExecuteOneAction postDossierAction(long dossierId, ExecuteOneAction model) {
		ExecuteOneAction result = new ExecuteOneAction();

		try {

			String requestURL = DOSSIERS_BASE_PATH + "/" + dossierId + "/actions";
			
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertExecuteActionHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.PUT, "application/json",
					baseUrl, requestURL, username,
					password, properties, params, context);
			
			
			result = OpenCPSConverter.convertProcessAction(jsonObj);
			
			return result;
		} catch (Exception e) {
		}
		
		return result;
	}
}
