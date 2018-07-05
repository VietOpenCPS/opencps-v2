package org.opencps.dossiermgt.rest.utils;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.opencps.dossiermgt.action.util.MultipartUtility;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.scheduler.InvokeREST;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

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
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = OpenCPSConverter.convertHttpParams(model);
		ServiceContext context = new ServiceContext();
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.PUT, "application/json",
				baseUrl, DOSSIERS_BASE_PATH, username,
				password, properties, params, context);

		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}
	
	public DossierFileModel postDossierFile(File file, String dossierUnique, DossierFileModel model) {
		DossierFileModel result = null;

		try {

			String authString = username + ":" + password;

			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));

			String requestURL = DOSSIERS_BASE_PATH + "/" + dossierUnique + "/files";

			MultipartUtility multipart = new MultipartUtility(requestURL, "UTF-8", groupId, authStringEnc);

			multipart.addFilePart("file", file);

			if (!Validator.isNull(model.getDisplayName())) {
				multipart.addFormField(DossierFileTerm.DISPLAY_NAME, model.getDisplayName());
			}

			List<String> res = multipart.finish();

			StringBuilder sb = new StringBuilder();

			for (String line : res) {
				sb.append(line);
			}

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject(sb.toString());
			
			result = OpenCPSConverter.convertDossierFile(jsonObj);
			
			return result;
		} catch (Exception e) {
		}

		return result;
		
	}
}
