package org.opencps.api.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierSyncManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21DataModel;
import org.opencps.api.v21.dossiersync.model.DossierSyncV21ResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierSyncActions;
import org.opencps.dossiermgt.action.impl.DossierSyncActionsImpl;
import org.opencps.dossiermgt.model.DossierSync;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierSyncManagementImpl implements DossierSyncManagement {
//	private final String baseUrl = "http://localhost:8080/o/rest/v2/";
//	private final String username = "test@liferay.com";
//	private final String password = "test";
	//private final String serectKey = "OPENCPSV2";

//	@Override
//	public Response getDossierSyncs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
//			User user, ServiceContext serviceContext, String serverNo) {
//
//		BackendAuth auth = new BackendAuthImpl();
//
//		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			if (!auth.hasResource(serviceContext, DossierTemplate.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}
//
//			List<DossierSync> dossierSyncs = DossierSyncLocalServiceUtil.fetchByServerNo(serverNo, QueryUtil.ALL_POS,
//					QueryUtil.ALL_POS);
//
//			DossierSyncResultsModel result = new DossierSyncResultsModel();
//
//			result.setTotal(dossierSyncs.size());
//			result.getData().addAll(DossierSyncUtils.mappingToData(dossierSyncs));
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			ErrorMsg error = new ErrorMsg();
//
//			if (e instanceof UnauthenticationException) {
//				error.setMessage("Non-Authoritative Information.");
//				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//				error.setDescription("Non-Authoritative Information.");
//
//				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
//			} else {
//				if (e instanceof UnauthorizationException) {
//					error.setMessage("Unauthorized.");
//					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//					error.setDescription("Unauthorized.");
//
//					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();
//
//				} else {
//
//					error.setMessage("Internal Server Error");
//					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
//					error.setDescription(e.getMessage());
//
//					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
//
//				}
//			}
//		}
//	}
//
//	@Override
//	public Response sendDossierSync(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
//			User user, ServiceContext serviceContext, long id) {
//
//		BackendAuth auth = new BackendAuthImpl();
//
//		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
//
//		try {
//
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			DossierSync dossierSync = DossierSyncLocalServiceUtil.fetchDossierSync(id);
//
//			DossierSyncSendingModel result = new DossierSyncSendingModel();
//
//			if (Validator.isNotNull(dossierSync)) {
//				// Get DOSSIER in SERVER
//				Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierSync.getDossierId());
//
//				// Get the latest ACTION of DOSSIER has been done
//				// long dossierActionId = Validator.isNotNull(dossier) ?
//				// dossierActionId = dossier.getDossierActionId() : 0l;
//
//				long dossierActionId = dossierSync.getMethod() == 0 ? dossierSync.getClassPK() : 0;
//
//				DossierAction action = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
//
//				long actionPenddingId = Validator.isNotNull(dossier) ? dossier.getDossierActionId() : 0l;
//
//				_log.info("DOSSIER_ACTION_ID===" + dossierActionId);
//
//				callDossierSync(groupId, dossierSync.getMethod(),
//						Validator.isNotNull(action) ? action.getSyncActionCode() : StringPool.BLANK,
//						Validator.isNotNull(action) ? action.getActionUser() : StringPool.BLANK,
//						Validator.isNotNull(action) ? action.getActionNote() : StringPool.BLANK, 0l,
//						dossier.getReferenceUid(), actionPenddingId, id, dossierSync.getDossierId(),
//						dossierSync.getClassPK(), dossierSync.getCreateDossier(), serviceContext);
//
//				result = DossierSyncUtils.mappingToSending(dossierSync);
//
//			} else {
//				throw new NotFoundException("NotFoundDossierSync");
//			}
//
//			return Response.status(200).entity(result).build();
//
//		} catch (Exception e) {
//			ErrorMsg error = new ErrorMsg();
//
//			if (e instanceof UnauthenticationException) {
//				error.setMessage("Non-Authoritative Information.");
//				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//				error.setDescription("Non-Authoritative Information.");
//
//				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
//			} else {
//				if (e instanceof UnauthorizationException) {
//					error.setMessage("Unauthorized.");
//					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
//					error.setDescription("Unauthorized.");
//
//					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();
//
//				} else {
//
//					error.setMessage("Internal Server Error");
//					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
//					error.setDescription(e.getMessage());
//
//					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
//
//				}
//			}
//		}
//
//	}
//
//	private void callDossierSync(long groupId, int method, String actionCode, String actionUser, String actionNote,
//			long assignUserId, String refId, long clientDossierActionId, long dossierSyncId, long dossierId,
//			long classPK, boolean isCreateDossier, ServiceContext serviceContext) throws PortalException {
//
//		InvokeREST rest = new InvokeREST();
//
//		HashMap<String, String> properties = new HashMap<String, String>();
//
//		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
//
//		if (isCreateDossier) {
//			// Call initDossier to SERVER
//
//			String httpMethod = HttpMethods.POST;
//
//			String endPoint = "dossiers";
//
//			Map<String, Object> params = getParamsPostDossier(dossierSyncId);
//
//			JSONObject resPostDossier = rest.callPostAPI(groupId, httpMethod, "application/json",
//					RESTFulConfiguration.SERVER_PATH_BASE, endPoint, RESTFulConfiguration.SERVER_USER,
//					RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);
//
//			// reset creaetDossier flag
//			if (resPostDossier.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//				DossierSyncLocalServiceUtil.shiftCreateDossierStatus(dossierSyncId);
//			}
//
//		}
//
//		// SyncAction
//
//		if (method == 0) {
//			String endPointSynAction = "dossiers/" + refId + "/actions";
//
//			String endPointSynDossierNo = "dossiers/" + refId + "/dossierno";
//
//			Map<String, Object> params = new LinkedHashMap<>();
//
//			params.put("actionCode", actionCode);
//			params.put("actionUser", actionUser);
//			params.put("actionNote", actionNote);
//			params.put("assignUserId", assignUserId);
//			params.put("isSynAction", 1);
//
//			JSONObject resSynsActions = rest.callPostAPI(groupId, HttpMethods.POST, "application/json",
//					RESTFulConfiguration.SERVER_PATH_BASE, endPointSynAction, RESTFulConfiguration.SERVER_USER,
//					RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);
//
//			if (resSynsActions.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//				// remove DossierSync
//				DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//			}
//
//			if (dossier != null && Validator.isNotNull(dossier.getDossierNo())) {
//				Map<String, Object> updateDossierNoParams = new LinkedHashMap<>();
//
//				properties.put("dossierno", dossier.getDossierNo());
//
//				// endPointSynDossierNo = endPointSynDossierNo +
//				// HttpUtil.encodeURL(dossier.getDossierNo());
//
//				JSONObject resSynsDossierNo = rest.callPostAPI(groupId, HttpMethods.PUT, "application/json",
//						RESTFulConfiguration.SERVER_PATH_BASE, endPointSynDossierNo, RESTFulConfiguration.SERVER_USER,
//						RESTFulConfiguration.SERVER_PASS, properties, updateDossierNoParams, serviceContext);
//
//				if (resSynsDossierNo.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//					// TODO ?
//
//				}
//			}
//		}
//
//		// SyncDossierFile
//		if (method == 1) {
//			
//			// Need check some case:
//			// 1. Add new file
//			// 2. Remove new file
//			// 3. Update file
//			
//			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFile(classPK);
//			
//			if (dossierFile.getRemoved()) {
//				
//				//remove file in server
//				long serverGroupId = 55217l;
//				
//				DossierFile dossierServerFile = DossierFileLocalServiceUtil.getByRefAndGroupId(serverGroupId, dossierFile.getReferenceUid());
//				
//				dossierServerFile.setRemoved(true);
//				
//				//update dossierFile
//				DossierFileLocalServiceUtil.updateDossierFile(dossierServerFile);
//				
//				//remove dossierSync
//				DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//			} else {
//				// TODO add case update file
//				String endPointSyncDossierFile = "dossiers/" + refId + "/files";
//
//				properties.put("referenceUid", dossierFile.getReferenceUid());
//				properties.put("dossierTemplateNo", dossierFile.getDossierTemplateNo());
//				properties.put("dossierPartNo", dossierFile.getDossierPartNo());
//				properties.put("fileTemplateNo", dossierFile.getFileTemplateNo());
//				properties.put("displayName", dossierFile.getDisplayName());
//				properties.put("isSync", StringPool.FALSE);
//				properties.put("formData", dossierFile.getFormData());
//
//				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
//
//				properties.put("fileType", fileEntry.getExtension());
//
//				File file = getFile(dossierFile.getFileEntryId());
//
//				// TODO review extention file
//				JSONObject resSynFile = rest.callPostFileAPI(groupId, HttpMethods.POST, "application/json",
//						RESTFulConfiguration.SERVER_PATH_BASE, endPointSyncDossierFile, RESTFulConfiguration.SERVER_USER,
//						RESTFulConfiguration.SERVER_PASS, properties, file, serviceContext);
//
//				if (resSynFile.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//					// remove DossierSync
//					DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//				} else {
//					_log.info(resSynFile.get(RESTFulConfiguration.MESSAGE));
//				}
//
//			}
//			
//
//			// Reset isNew
//			dossierFile.setIsNew(false);
//
//			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
//
//		}
//
//		// SyncPaymentFile and paymentfile status
//
//		// Sync paymentFile
//		if (method == 2) {
//
//			DossierSync sync = DossierSyncLocalServiceUtil.getDossierSync(dossierSyncId);
//
//			String endPointSynAction = "dossiers/" + sync.getDossierReferenceUid() + "/payments";
//
//			PaymentFile paymentFileClient = PaymentFileLocalServiceUtil.fectPaymentFile(sync.getDossierId(),
//					sync.getFileReferenceUid());
//
//			Map<String, Object> params = new LinkedHashMap<>();
//			params.put("referenceUid", paymentFileClient.getReferenceUid());
//			params.put("govAgencyCode", paymentFileClient.getGovAgencyCode());
//			params.put("govAgencyName", paymentFileClient.getGovAgencyName());
//			params.put("applicantName", StringPool.BLANK);
//			params.put("applicantIdNo", StringPool.BLANK);
//			params.put("paymentFee", paymentFileClient.getPaymentFee());
//			params.put("paymentAmount", paymentFileClient.getPaymentAmount());
//			params.put("paymentNote", paymentFileClient.getPaymentNote());
//			params.put("epaymentProfile", paymentFileClient.getEpaymentProfile());
//			params.put("invoiceTemplateNo", paymentFileClient.getInvoiceTemplateNo());
//			params.put("bankInfo", paymentFileClient.getBankInfo());
//			// TODO update payload
//			params.put("invoicePayload", StringPool.BLANK);
//
//			JSONObject resSynFile = rest.callPostAPI(groupId, HttpMethods.POST, "application/json",
//					RESTFulConfiguration.SERVER_PATH_BASE, endPointSynAction, RESTFulConfiguration.SERVER_USER,
//					RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);
//
//			if (resSynFile.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//				// remove DossierSync
//				DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//				// Reset isNew
//
//				paymentFileClient.setIsNew(false);
//				PaymentFileLocalServiceUtil.updatePaymentFile(paymentFileClient);
//				// DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
//			}
//		}
//
//		// Sync paymentStatus
//		if (method == 3) {
//
//			_log.info("SYN_METHOD_3");
//
//			DossierSync sync = DossierSyncLocalServiceUtil.getDossierSync(dossierSyncId);
//
//			PaymentFile paymentFileClient = PaymentFileLocalServiceUtil.fectPaymentFile(sync.getDossierId(),
//					sync.getFileReferenceUid());
//
//			try {
//				File file = File.createTempFile(String.valueOf(System.currentTimeMillis()), StringPool.PERIOD + "tmp");
//
//				if (paymentFileClient.getInvoiceFileEntryId() != 0) {
//					// get invoice file
//					file = getFile(paymentFileClient.getInvoiceFileEntryId());
//				}
//
//				SimpleDateFormat format = new SimpleDateFormat("DD-MM-YYYY HH:MM:SS");
//				Map<String, Object> params = new LinkedHashMap<>();
//
//				params.put("approveDatetime", Validator.isNotNull(paymentFileClient.getApproveDatetime())
//						? format.format(paymentFileClient.getApproveDatetime()) : format.format(new Date()));
//				params.put("accountUserName", paymentFileClient.getAccountUserName());
//				params.put("govAgencyTaxNo", paymentFileClient.getGovAgencyTaxNo());
//				params.put("invoiceTemplateNo", paymentFileClient.getInvoiceTemplateNo());
//				params.put("invoiceIssueNo", paymentFileClient.getInvoiceIssueNo());
//				params.put("invoiceNo", paymentFileClient.getInvoiceNo());
//				params.put("isSync", StringPool.FALSE);
//
//				String endPointSynAction = "dossiers/" + sync.getDossierReferenceUid() + "/payments/" + paymentFileClient.getReferenceUid()
//						+ "/approval/noattachment";
//
//				JSONObject resSynFile = rest.callPostAPI(groupId, HttpMethod.PUT, "application/json",
//						RESTFulConfiguration.SERVER_PATH_BASE, endPointSynAction, RESTFulConfiguration.SERVER_USER,
//						RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);
//
//				if (resSynFile.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//					// remove DossierSync
//
//					DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//					// Reset isNew
//
//					paymentFileClient.setIsNew(false);
//					PaymentFileLocalServiceUtil.updatePaymentFile(paymentFileClient);
//					// DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
//
//				} else {
//					_log.info("Not Approve_" + resSynFile.getString(RESTFulConfiguration.MESSAGE));
//				}
//			} catch (Exception e) {
//				_log.error(e);
//			}
//
//		}
//
//		// remove pending in DossierAction
//		int countDossierSync = DossierSyncLocalServiceUtil.countByDossierId(dossierId);
//
//		if (countDossierSync == 0 && clientDossierActionId > 0) {
//			DossierActionLocalServiceUtil.updatePending(clientDossierActionId, false);
//		}
//
//	}
//	
//	private void syncDossierFile(long dossierFileId) throws PortalException {
//		
//		try {
//			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFile(dossierFileId);
//			//in case add 
//			
//			//in case update
//			
//			//in case remove, check remove flag
//			
//		} catch (Exception e) {
//			if (e instanceof PortalException) {
//				throw new PortalException("Can not get file with dossierFileId = " + dossierFileId);
//			}
//		}
//		
//	}
//
//	protected Dossier getDossier(String id, long groupId) throws PortalException {
//		// TODO update logic here
//		long dossierId = GetterUtil.getLong(id);
//
//		Dossier dossier = null;
//
//		dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
//
//		if (Validator.isNull(dossier)) {
//			dossier = DossierLocalServiceUtil.getByRef(groupId, id);
//		}
//
//		return dossier;
//	}
//
//	private File getFile(long fileEntryId) {
//		File tempFile = null;
//		try {
//			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);
//
//			DLFileVersion dlFileVersion = DLFileVersionLocalServiceUtil.getLatestFileVersion(fileEntry.getFileEntryId(),
//					true);
//
//			tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),
//					StringPool.PERIOD + fileEntry.getExtension());
//
//			InputStream io = DLFileEntryLocalServiceUtil.getFileAsStream(fileEntryId, dlFileVersion.getVersion());
//			OutputStream outStream = new FileOutputStream(tempFile);
//			byte[] buffer = new byte[8 * 1024];
//			int bytesRead;
//			while ((bytesRead = io.read(buffer)) != -1) {
//				outStream.write(buffer, 0, bytesRead);
//			}
//
//			io.close();
//			// flush OutputStream to write any buffered data to file
//			outStream.flush();
//			outStream.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		return tempFile;
//	}
//
//	private Map<String, Object> getParamsPostDossier(long dossierSyncId) throws PortalException {
//
//		Map<String, Object> params = new HashMap<String, Object>();
//
//		try {
//
//			long dossierId = DossierSyncLocalServiceUtil.getDossierSync(dossierSyncId).getDossierId();
//
//			Dossier dossier = DossierLocalServiceUtil.getDossier(dossierId);
//			params.put(DossierTerm.REFERENCE_UID, dossier.getReferenceUid());
//			params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
//			params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
//
//			params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
//			params.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
//			params.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
//			params.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
//			params.put(DossierTerm.APPLICANT_ID_DATE, dossier.getApplicantIdDate());
//			params.put(DossierTerm.ADDRESS, dossier.getAddress());
//			params.put(DossierTerm.CITY_CODE, dossier.getCityCode());
//			params.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
//			params.put(DossierTerm.WARD_CODE, dossier.getWardCode());
//			params.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
//			params.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
//			params.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());
//			params.put(DossierTerm.PASSWORD, dossier.getPassword());
//			params.put(DossierTerm.ONLINE, dossier.getOnline());
//			params.put(DossierTerm.NOTIFICATION, dossier.getNotification());
//			params.put(DossierTerm.APPLICANT_NOTE, dossier.getApplicantNote());
//			params.put(DossierTerm.VIA_POSTAL, dossier.getViaPostal());
//			params.put(DossierTerm.POSTAL_ADDRESS, dossier.getPostalAddress());
//			params.put(DossierTerm.POSTAL_CITY_CODE, dossier.getPostalCityCode());
//			params.put(DossierTerm.POSTAL_TEL_NO, dossier.getPostalTelNo());
//		} catch (Exception e) {
//			throw new PortalException("DossierNotFound");
//		}
//
//		return params;
//	}
//
//	@Deprecated
//	private void doSync(long groupId, String actionCode, String actionUser, String actionNote, long assignUserId,
//			String refId, long clientDossierActionId, long dossierSyncId) {
//		try {
//			String path = "dossiers/" + refId + "/actions";
//			URL url = new URL(baseUrl + path);
//
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//			String authString = username + ":" + password;
//
//			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
//			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
//
//			conn.setRequestMethod(HttpMethods.POST);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.setRequestProperty("Accept", "application/json");
//			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			conn.setRequestProperty("groupId", String.valueOf(groupId));
//
//			Map<String, Object> params = new LinkedHashMap<>();
//			params.put("actionCode", actionCode);
//			params.put("actionUser", actionUser);
//			params.put("actionNote", actionNote);
//			params.put("assignUserId", assignUserId);
//			params.put("isSynAction", 1);
//
//			StringBuilder postData = new StringBuilder();
//			for (Map.Entry<String, Object> param : params.entrySet()) {
//				if (postData.length() != 0)
//					postData.append('&');
//				postData.append(java.net.URLEncoder.encode(param.getKey(), "UTF-8"));
//				postData.append('=');
//				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//			}
//
//			byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//
//			conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//
//			conn.getOutputStream().write(postDataBytes);
//
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//			} else {
//				try {
//					// remove flag pending
//					DossierActionLocalServiceUtil.updatePending(clientDossierActionId, false);
//					// remove DOSSIER_SYNC
//					DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//			String output;
//
//			StringBuffer sb = new StringBuffer();
//
//			while ((output = br.readLine()) != null) {
//				sb.append(output);
//
//			}
//
//			conn.disconnect();
//
//		} catch (MalformedURLException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//	}
//
//	protected ProcessAction getProcessAction(long groupId, long dossierId, String refId, String actionCode,
//			long serviceProcessId) throws PortalException {
//
//		ProcessAction action = null;
//
//		try {
//			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode);
//
//			Dossier dossier = getDossier(groupId, dossierId, refId);
//
//			String dossierStatus = dossier.getDossierStatus();
//
//			for (ProcessAction act : actions) {
//
//				String preStepCode = act.getPreStepCode();
//
//				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
//
//				if (Validator.isNotNull(step)) {
//					if (step.getDossierStatus().equalsIgnoreCase(dossierStatus)) {
//						action = act;
//						break;
//					}
//				} else {
//					action = act;
//					break;
//				}
//
//			}
//
//		} catch (Exception e) {
//			throw new NotFoundException("NotProcessActionFound");
//		}
//
//		return action;
//	}
//
//	protected Dossier getDossier(long groupId, long dossierId, String refId) throws PortalException {
//
//		Dossier dossier = null;
//
//		if (dossierId != 0) {
//			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
//		} else {
//			dossier = DossierLocalServiceUtil.getByRef(groupId, refId);
//		}
//
//		return dossier;
//	}
//
//	private void resetDossier(long groupId, String refId) {
//		try {
//			String path = "dossiers/" + refId + "/reset";
//			URL url = new URL(baseUrl + path);
//
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//			String authString = username + ":" + password;
//
//			String authStringEnc = new String(Base64.getEncoder().encodeToString(authString.getBytes()));
//			conn.setRequestProperty("Authorization", "Basic " + authStringEnc);
//
//			conn.setRequestMethod(HttpMethods.GET);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.setRequestProperty("Accept", "application/json");
//			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//			conn.setRequestProperty("groupId", String.valueOf(groupId));
//
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//			String output;
//
//			StringBuffer sb = new StringBuffer();
//
//			while ((output = br.readLine()) != null) {
//				sb.append(output);
//
//			}
//
//			conn.disconnect();
//
//		} catch (MalformedURLException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//	}

	Log _log = LogFactoryUtil.getLog(DossierSyncManagementImpl.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierSyncsByApplicant(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String action, Integer start, Integer end) {
		BackendAuth auth = new BackendAuthImpl();
		DossierSyncActions actions = new DossierSyncActionsImpl();
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (start == null || start == 0) {
				start = -1;
				end = -1;
			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonData = actions.getDossierSyncByAction(groupId, action, start, end, serviceContext);
			DossierSyncV21ResultsModel results = new DossierSyncV21ResultsModel();
			
			results.setTotal(jsonData.getInt("total"));
			if (jsonData != null && jsonData.getInt("total") > 0) {
				List<DossierSyncV21DataModel> lstDatas = new ArrayList<>();
				List<DossierSync> lstSyncs = (List<DossierSync>)jsonData.get("data");
				for (DossierSync ds : lstSyncs) {
					DossierSyncV21DataModel model = new DossierSyncV21DataModel();
					model.setActionCode(ds.getActionCode());
					model.setActionName(ds.getActionName());
					model.setActionNote(ds.getActionNote());
					model.setActionUser(ds.getActionUser());
					model.setCreateDate(ds.getCreateDate().getTime());
					model.setDossierId(ds.getDossierId());
					model.setDossierRefUid(ds.getDossierRefUid());
					model.setDossierSyncId(ds.getDossierSyncId());
					model.setInfoType(ds.getInfoType());
					model.setPayload(ds.getPayload());
					model.setSyncRefUid(ds.getSyncRefUid());
					model.setSyncType(ds.getSyncType());
					model.setUserId(ds.getUserId());
					
					lstDatas.add(model);
				}
				results.getData().addAll(lstDatas);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}	
	}

}
