package org.opencps.dossiermgt.rest.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierDocumentModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkResultModel;
import org.opencps.dossiermgt.rest.model.DossierPublishModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;

public class OpenCPSRestClient {
	private Log _log = LogFactoryUtil.getLog(OpenCPSRestClient.class);
	private String username;
	private String password;
	private long groupId;
	private boolean writeLog;
	
	public boolean isWriteLog() {
		return writeLog;
	}

	public void setWriteLog(boolean writeLog) {
		this.writeLog = writeLog;
	}

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
				&& configObj.has(SyncServerTerm.SERVER_SECRET)
				&& configObj.has(SyncServerTerm.SERVER_URL)
				&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
			OpenCPSRestClient client = new OpenCPSRestClient(
					configObj.getString(SyncServerTerm.SERVER_USERNAME), 
					configObj.getString(SyncServerTerm.SERVER_SECRET), 
					configObj.getString(SyncServerTerm.SERVER_URL),
					configObj.getLong(SyncServerTerm.SERVER_GROUP_ID));
			if (configObj.has(SyncServerTerm.WRITE_LOG)) {
				client.setWriteLog(configObj.getBoolean(SyncServerTerm.WRITE_LOG));
			}
			
			return client;
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
		_log.debug("Call post API result: " + resultObj.toJSONString());
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
			
			JSONObject jsonObj = callRest.callPostFileAPIWithFileName(groupId, HttpMethod.POST, "application/json", 
					 baseUrl, requestURL, username,
					password, properties, file, model.getDisplayName(), context);
			_log.debug("Post dossier file: " + jsonObj);
			result = OpenCPSConverter.convertDossierFile(jsonObj);
			
			return result;
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
		
	}
	
	public DossierFileModel postDossierFileEForm(File file, String dossierUnique, DossierFileModel model) {
		DossierFileModel result = null;

		try {

			String requestURL = DOSSIERS_BASE_PATH + "/" + dossierUnique + "/eforms/" + model.getDossierPartNo();
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierFileEFormHttpParams(model);
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostFileAPIWithFileName(groupId, HttpMethod.POST, "application/json", 
					 baseUrl, requestURL, username,
					password, properties, file, model.getDisplayName(), context);
//			_log.info("Post dossier file eform: " + jsonObj);
			result = OpenCPSConverter.convertDossierFile(jsonObj);
			
			return result;
		} catch (Exception e) {
			_log.error(e);
		}

		return result;
		
	}
	
	public ExecuteOneAction postDossierAction(String dossierId, ExecuteOneAction model) {
		ExecuteOneAction result = null;

		try {

			String requestURL = DOSSIERS_BASE_PATH + "/" + dossierId + "/actions";
			
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertExecuteActionHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json",
					baseUrl, requestURL, username,
					password, properties, params, context);
			
			
			result = OpenCPSConverter.convertProcessAction(jsonObj);
			
			return result;
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}
	
	public List<DossierFileModel> getAllFilesByDossier(String id) {
		List<DossierFileModel> lstDossierFiles = new ArrayList<>();
		
		try {

			InvokeREST rest = new InvokeREST();

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put("Content-Type", "application/x-www-form-urlencoded");

			String path = DOSSIERS_BASE_PATH + "/" + id + "/all/files";
			_log.debug("id: "+id);

			ServiceContext serviceContext = new ServiceContext();

			JSONObject resDossierFile = rest.callAPI(groupId, HttpMethods.GET, "application/json",
					baseUrl, path, username,
					password, properties, serviceContext);

			if (GetterUtil.getInteger(resDossierFile.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + resDossierFile.get(RESTFulConfiguration.STATUS));
			} else {

				JSONObject jsData = JSONFactoryUtil
						.createJSONObject(resDossierFile.getString(RESTFulConfiguration.MESSAGE));

				JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString("data"));

				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					
					DossierFileModel fileModel = new DossierFileModel();
					fileModel.setReferenceUid(object.getString(DossierFileTerm.REFERENCE_UID));
					fileModel.setDossierPartType(GetterUtil.getInteger(object.getString(DossierFileTerm.DOSSIER_PART_TYPE)));
					
					lstDossierFiles.add(fileModel);
				}

			}

		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
		}
		
		return lstDossierFiles;
	}	
	
	public PaymentFileInputModel postPaymentFiles(String id, PaymentFileInputModel model) {
		PaymentFileInputModel result = new PaymentFileInputModel();

		try {

			String requestURL = DOSSIERS_BASE_PATH + "/" + id + "/payment";
			
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertPaymentFileInputHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json",
					baseUrl, requestURL, username,
					password, properties, params, context);
			
			
			result = OpenCPSConverter.convertPaymentFile(jsonObj);
			
			return result;
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}
	
	
	public DossierDocumentModel postDossierDocument(File file, String dossierUnique, DossierDocumentModel model) {
		DossierDocumentModel result = null;

		try {
			URL url = new URL(baseUrl);
			String protocol = url.getProtocol();
			String host = url.getHost();
			int port = url.getPort();
			String baseV21Url = protocol + "://" + host + ":" + port + "/o/rest/v2_1";
			
			String requestURL = DOSSIERS_BASE_PATH + "/" + dossierUnique + "/documents";
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierDocumentHttpParams(model);
			ServiceContext context = new ServiceContext();
			JSONObject jsonObj = callRest.callPostFileAPI(groupId, HttpMethod.POST, "application/json", 
					baseV21Url, requestURL, username,
					password, properties, file, context);
			result = OpenCPSConverter.convertDossierDocument(jsonObj);
			
			return result;
		} catch (Exception e) {
//			e.printStackTrace();
			_log.error(e);
		}

		return result;
		
	}		
	
	public DossierDetailModel publishDossier(DossierPublishModel model) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = OpenCPSConverter.convertPublishHttpParams(model);
		ServiceContext context = new ServiceContext();
		
		_log.info("baseUrl  : " + baseUrl);
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json",
				baseUrl,DOSSIERS_BASE_PATH + "/publish", username,
				password, properties, params, context);
		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}

	public DossierDetailModel publishDossier(Dossier dossier) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = OpenCPSConverter.convertPublishHttpParams(dossier);
		ServiceContext context = new ServiceContext();
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json",
				baseUrl,DOSSIERS_BASE_PATH + "/publish", username,
				password, properties, params, context);
		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}	
	
	public DossierMarkResultModel postDossierMark(String id, String dossierPartNo, DossierMarkInputModel model) {
		DossierMarkResultModel result = new DossierMarkResultModel();

		try {

			String requestURL = DOSSIERS_BASE_PATH + "/" + id + "/marks/" + dossierPartNo;
			
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertDossierMarkInputHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json",
					baseUrl, requestURL, username,
					password, properties, params, context);
			
			
			result = OpenCPSConverter.convertDossierMark(jsonObj);
			
			return result;
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}

	public DossierDetailModel removeDossier(Dossier model) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		//HashMap<String, String> properties = new HashMap<String, String>();
		//Map<String, Object> params = OpenCPSConverter.convertHttpParams(model);
		String referenceUid = model.getReferenceUid();
		if (Validator.isNotNull(referenceUid)) {
			HashMap<String, String> properties = new HashMap<String, String>();
			ServiceContext context = new ServiceContext();
			String endPoint = DOSSIERS_BASE_PATH + "/" + referenceUid;

			JSONObject resultObj = callRest.callDeleteAPI(groupId, HttpMethod.DELETE, "application/json", baseUrl,
					endPoint, username, password, properties, context);
			
			result = OpenCPSConverter.convertDossierDetail(resultObj);
		}

		return result;
	}
	public DossierDetailModel gotoStep(String id, String stepCode) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		ServiceContext context = new ServiceContext();
		String endPoint = DOSSIERS_BASE_PATH + "/" + id + "/goto/" + stepCode;
		Map<String, Object> params = new HashMap<>();

		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json", baseUrl,
				endPoint, username, password, properties, params, context);
			
		result = OpenCPSConverter.convertDossierDetail(resultObj);

		return result;
	}	
	public DossierDetailModel rollback(String id) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		ServiceContext context = new ServiceContext();
		String endPoint = DOSSIERS_BASE_PATH + "/" + id + "/rollback";
		Map<String, Object> params = new HashMap<>();
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, "application/json", baseUrl,
				endPoint, username, password, properties, params, context);
			
		result = OpenCPSConverter.convertDossierDetail(resultObj);

		return result;
	}		
}
