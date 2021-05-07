package org.opencps.dossiermgt.rest.utils;

import com.liferay.petra.string.StringPool;
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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierFileTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.model.DossierDetailModel;
import org.opencps.dossiermgt.rest.model.DossierDocumentModel;
import org.opencps.dossiermgt.rest.model.DossierFileModel;
import org.opencps.dossiermgt.rest.model.DossierInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkInputModel;
import org.opencps.dossiermgt.rest.model.DossierMarkResultModel;
import org.opencps.dossiermgt.rest.model.DossierPublishModel;
import org.opencps.dossiermgt.rest.model.ExecuteOneAction;
import org.opencps.dossiermgt.rest.model.InformDossierInputModel;
import org.opencps.dossiermgt.rest.model.PaymentFileInputModel;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierLocalService;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.impl.DossierLocalServiceImpl;

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
		if (baseUrl.charAt(baseUrl.length() - 1) == StringPool.SLASH.charAt(0) && baseUrl.length() >= 2) {
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
		if (baseUrl.charAt(baseUrl.length() - 1) == StringPool.FORWARD_SLASH.charAt(0) && baseUrl.length() >= 2) {
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
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
				baseUrl, ConstantUtils.DOSSIERS_BASE_PATH, username,
				password, properties, params, context);
		_log.debug("Call post API result: " + resultObj.toJSONString());
		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}
	
	public DossierDetailModel putDossier(InformDossierInputModel model) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		Map<String, Object> params = OpenCPSConverter.convertInformHttpParams(model);
		ServiceContext context = new ServiceContext();
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.PUT, "application/json",
				baseUrl,DOSSIERS_BASE_PATH + "/inform/" + model.getReferenceUid(), username,
				password, properties, params, context);
		_log.debug("Call post API result: " + resultObj.toJSONString());
		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}

	public DossierFileModel postDossierFile(File file, String dossierUnique, DossierFileModel model) {
		DossierFileModel result = null;

		try {

			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + dossierUnique
					+ StringPool.FORWARD_SLASH + DossierActionTerm.FILES;
			_log.info("Them file cho hs : " + requestURL);
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierFileHttpParams(model);
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostFileAPIWithFileName(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
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

	public DossierFileModel postDossierFileInform(File file, Dossier dossier, String dossierUnique, DossierFileModel model) {
		DossierFileModel result = null;

		try {

			Dossier hslt = null;
			if (Validator.isNotNull(dossier.getOriginDossierNo()) && dossier.getOriginDossierId() == 0) {
				if (dossier.getReferenceUid().contains(DossierTerm.PREFIX_UUID)) {
					hslt = DossierLocalServiceUtil.getByDossierNo(groupId, dossier.getOriginDossierNo());
				}
			}
			// nên bỏ đk hslt.getReferenceUid().contains(DossierTerm.PREFIX_UUID) chỉ đúng khi trả về sở => referenceUid có xxx-cps còn xã thì ko
			if (hslt != null) {
				dossierUnique = hslt.getReferenceUid();
			}

			_log.info("dossierUnique LT: "+dossierUnique);
			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + dossierUnique
					+ StringPool.FORWARD_SLASH + DossierActionTerm.FILES;
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierFileHttpParams(model);
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostFileAPIWithFileName(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
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

			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + dossierUnique
					+ StringPool.FORWARD_SLASH + DossierFileTerm.URL_EFROM_PATH + StringPool.FORWARD_SLASH
					+ model.getDossierPartNo();
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierFileEFormHttpParams(model);
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostFileAPIWithFileName(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
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

			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + dossierId
					+ StringPool.FORWARD_SLASH + ProcessActionTerm.KEY_ACTIONS;
			
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertExecuteActionHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();

			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
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
			properties.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);

			String path = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + id + StringPool.FORWARD_SLASH
					+ ReadFilePropertiesUtils.get(ConstantUtils.VALUE_ALL) + StringPool.FORWARD_SLASH
					+ DossierActionTerm.FILES;
			_log.debug("id: "+id);

			ServiceContext serviceContext = new ServiceContext();

			JSONObject resDossierFile = rest.callAPI(groupId, HttpMethods.GET, MediaType.APPLICATION_JSON,
					baseUrl, path, username,
					password, properties, serviceContext);

			if (GetterUtil.getInteger(resDossierFile.get(RESTFulConfiguration.STATUS)) != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + resDossierFile.get(RESTFulConfiguration.STATUS));
			} else {

				JSONObject jsData = JSONFactoryUtil
						.createJSONObject(resDossierFile.getString(RESTFulConfiguration.MESSAGE));

				JSONArray array = JSONFactoryUtil.createJSONArray(jsData.getString(ConstantUtils.DATA));

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

			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + id
					+ StringPool.FORWARD_SLASH + ProcessActionTerm.PAYMENT;
			
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertPaymentFileInputHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
					baseUrl, requestURL, username,
					password, properties, params, context);
			
			
			result = OpenCPSConverter.convertPaymentFile(jsonObj);
			
			return result;
		} catch (Exception e) {
			_log.error(e);
		}
		
		return result;
	}
		
	public PaymentFileInputModel updatePaymentsConfirmFile(File file, String id, PaymentFileInputModel model) {
		PaymentFileInputModel result = null;

		try {

			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + id
					+ StringPool.FORWARD_SLASH + ProcessActionTerm.PAYMENTS + StringPool.FORWARD_SLASH + ProcessActionTerm.CONFIRM_FILE;
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = new HashMap<String, String>();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostFileAPIWithFileName(groupId, HttpMethod.PUT, MediaType.APPLICATION_JSON,
					 baseUrl, requestURL, username,
					password, properties, file, file.getName(), context);
			_log.debug("PUT payment confirm file: " + jsonObj);
			result = OpenCPSConverter.convertPaymentConfirmFile(jsonObj);
			
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
			
			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + "/" + dossierUnique + "/documents";
			InvokeREST callRest = new InvokeREST();
			HashMap<String, String> properties = OpenCPSConverter.convertDossierDocumentHttpParams(model);
			ServiceContext context = new ServiceContext();
			JSONObject jsonObj = callRest.callPostFileAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, 
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
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON,
				baseUrl, ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + ConstantUtils.VALUE_PUBLISH, username,
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

		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST,
				MediaType.APPLICATION_JSON, baseUrl,
				ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + ConstantUtils.VALUE_PUBLISH, username,
				password, properties, params, context);
		result = OpenCPSConverter.convertDossierDetail(resultObj);
		
		return result;
	}	
	
	public DossierMarkResultModel postDossierMark(String id, String dossierPartNo, DossierMarkInputModel model) {
		DossierMarkResultModel result = new DossierMarkResultModel();

		try {

			String requestURL = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + id
					+ StringPool.FORWARD_SLASH + ConstantUtils.VALUE_MARK + StringPool.FORWARD_SLASH + dossierPartNo;
//			_log.info("requestURL: "+requestURL);
			HashMap<String, String> properties = new HashMap<String, String>();
			
			Map<String, Object> params = OpenCPSConverter.convertDossierMarkInputHttpParams(model);
			InvokeREST callRest = new InvokeREST();
			ServiceContext context = new ServiceContext();
			
			JSONObject jsonObj = callRest.callPostAPI(groupId, HttpMethod.POST,
					MediaType.APPLICATION_JSON, baseUrl, requestURL, username,
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
			String endPoint = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + referenceUid;

			JSONObject resultObj = callRest.callDeleteAPI(groupId, HttpMethod.DELETE, MediaType.APPLICATION_JSON, baseUrl,
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
		String endPoint = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + id + StringPool.FORWARD_SLASH
				+ ConstantUtils.VALUE_GOTO + StringPool.FORWARD_SLASH + stepCode;
		Map<String, Object> params = new HashMap<>();

		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
				endPoint, username, password, properties, params, context);
			
		result = OpenCPSConverter.convertDossierDetail(resultObj);

		return result;
	}	
	public DossierDetailModel rollback(String id) {
		DossierDetailModel result = null;
		InvokeREST callRest = new InvokeREST();
		HashMap<String, String> properties = new HashMap<String, String>();
		ServiceContext context = new ServiceContext();
		String endPoint = ConstantUtils.DOSSIERS_BASE_PATH + StringPool.FORWARD_SLASH + id + StringPool.FORWARD_SLASH
				+ ConstantUtils.VALUE_ROLLBACK;
		Map<String, Object> params = new HashMap<>();
		
		JSONObject resultObj = callRest.callPostAPI(groupId, HttpMethod.POST, MediaType.APPLICATION_JSON, baseUrl,
				endPoint, username, password, properties, params, context);
			
		result = OpenCPSConverter.convertDossierDetail(resultObj);

		return result;
	}		
}
