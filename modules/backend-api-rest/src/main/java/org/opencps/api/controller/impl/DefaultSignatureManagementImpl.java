
package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DefaultSignatureManagement;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.digitalsignature.model.DigitalSignatureInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;

/**
 * @author trungnt
 */
public class DefaultSignatureManagementImpl implements DefaultSignatureManagement {

	Log _log = LogFactoryUtil.getLog(SignatureManagementImpl.class.getName());
//	private static final String TYPE_KYSO = "1135, 1158, 1160, 1032";
//	private static final String TYPE_DONGDAU = "1137, 1162, 105";

	@Override
	public Response updateDossierFileBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input) throws PortalException {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		long fileEntryId = Long.valueOf(input.getFileEntryId());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (fileEntryId > 0) {
			String sign = input.getSign();
			String signFieldName = input.getSignFieldName();
			String fileName = input.getFileName();
//			_log.info("sign: "+sign);
//			_log.info("signFieldName: "+signFieldName);
//			_log.info("fileName: "+fileName);
			String actionCode = input.getActionCode();
			String actionUser = input.getActionUser();
			String actionNote = input.getActionNote();
			long assignUserId = Long.valueOf(input.getAssignUserId());
			String subUsers = input.getSubUsers();
//			_log.info("actionCode: "+actionCode);
//			_log.info("actionUser: "+actionUser);
//			_log.info("actionNote: "+actionNote);
//			_log.info("assignUserId: "+assignUserId);
//			_log.info("subUsers: "+subUsers);
	
			JSONObject signatureCompleted = callSignatureSync(groupId, user, id, sign, signFieldName, fileName,
					serviceContext);
	
			if (signatureCompleted.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
	//			long fileEntryId = Long.valueOf(input.getFileEntryId());
				_log.info("fileEntryId: "+fileEntryId);
				String message = signatureCompleted.getString(RESTFulConfiguration.MESSAGE);
	//			_log.info("message: "+message);
				JSONObject jsonData = JSONFactoryUtil.createJSONObject(message);
	//			_log.info("jsonData: "+jsonData.toJSONString());
	//			String fullPath = String.valueOf(jsonData.get("fullPath"));
	//			_log.info("fullPath: "+fullPath);
				String signedFilePath = jsonData.getString("signedFile");
				File fileSigned = new File(signedFilePath);
	//			_log.info("TEST long file: "+fileSigned.length());
	//			_log.info("TEST file sign: "+fileSigned.lastModified());
	//			long modifiedLong = fileSigned.lastModified();
	//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	//			_log.info("TEST file modifiedDate: "+sdf.format(modifiedLong));
	//			_log.info("fileSigned Path: "+fileSigned.getAbsolutePath());
	//			_log.info("fileSigned Name: "+fileSigned.getName());
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
	//			_log.info("dlFileEntry: "+dlFileEntry.getFileName());
	
				DLAppLocalServiceUtil.updateFileEntry(user.getUserId(), dlFileEntry.getFileEntryId(), dlFileEntry.getTitle(),
						dlFileEntry.getMimeType(), dlFileEntry.getTitle(), dlFileEntry.getDescription(),
						StringPool.BLANK, true, fileSigned, serviceContext);
			
				//Next action
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (dossier != null) {
//					_log.info("dossierId: "+dossier.getDossierId());
//					_log.info("ReferenceId: "+dossier.getReferenceUid());
//					_log.info("actionCode: "+actionCode);
//					_log.info("actionUser: "+actionUser);
//					_log.info("actionNote: "+actionNote);
//					_log.info("assignUserId: "+assignUserId);
//					_log.info("subUsers: "+subUsers);
					DossierActions dossierAction = new DossierActionsImpl();
					//if (TYPE_KYSO.contains(actionCode)) {
						dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
								0L, actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
								serviceContext);

//					if (TYPE_KYSO.contains(actionCode)) {
//						dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
//								0L, actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
//								serviceContext);
//					} else if(TYPE_DONGDAU.contains(actionCode)) {
//						ProcessOption option = getProcessOption(dossier.getServiceCode(), dossier.getGovAgencyCode(),
//								dossier.getDossierTemplateNo(), groupId);
//	
//						ProcessAction action = getProcessAction(groupId, dossier.getDossierId(), dossier.getReferenceUid(),
//								input.getActionCode(), option.getServiceProcessId());
//	
//						dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
//								action.getProcessActionId(), actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
//								serviceContext);
//					} else {
//						//TODO
//					}
					// Update deliverable with deliverableType
					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
					// Process success
					result.put("msg", "success");
				}
			}
		} else {
			result.put("msg", "fileEntryId");
		}

		return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();

	}

	private JSONObject callSignatureSync(long groupId, User user, long id, String sign, String signFieldName, String fileName,
			ServiceContext serviceContext) throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

			// Call initDossier to SERVER

			String httpMethod = HttpMethods.POST;

			String endPoint = "signature/completeSignature";

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sign", sign);
			params.put("signFieldName", signFieldName);
			params.put("fileName", fileName);

			JSONObject resPostDossier = rest.callPostAPI(groupId, httpMethod, "application/json",
					RESTFulConfiguration.SERVER_PATH_BASE, endPoint, RESTFulConfiguration.SERVER_USER,
					RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);

			return resPostDossier;

	}

	@Override
	public Response getHashComputedBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input) {
		_log.info("START*************");
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String strIdArr = input.getStrIdArr();
			//_log.info("array Id: "+strIdArr);

			String[] idSplit = strIdArr.split(StringPool.SEMICOLON);
			//_log.info("idSplit Id: "+idSplit);

			JSONObject hashComputed = JSONFactoryUtil.createJSONObject();
			JSONObject results = null;
			JSONArray hashComputedArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray signFieldNamesArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray fileNamesArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray fullPathOfSignedFilesArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray messagesArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray fileEntryArrResult = JSONFactoryUtil.createJSONArray();
			
			for (String strId : idSplit) {
				String[] idArr = strId.split(StringPool.COMMA);
				DossierPart dossierPart = DossierPartLocalServiceUtil.fetchDossierPart(Long.valueOf(idArr[1]));
				_log.info("Dossier Part: "+dossierPart.getDossierPartId());
				DossierFile dossierFile = null;
				if (dossierPart != null && dossierPart.getESign()) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(idArr[0]));
					_log.info("Dossier File: "+dossierFile.getDossierFileId());
					if (dossierFile != null && dossierFile.getFileEntryId() > 0) {
						long fileEntryId = dossierFile.getFileEntryId();
						_log.info("fileEntryId: "+fileEntryId);

						try {
							//_log.info("START CALL HASHCOMPUTE: ");
							JSONObject newHashComputedResult = callHashComputedSync(groupId, user, fileEntryId, input.getActionCode(),
									input.getPostStepCode(), serviceContext);
							//_log.info("Obj: " + newHashComputedResult);
							String newHashComputedStr = newHashComputedResult.getString("message");
							
							JSONObject newHashComputed = JSONFactoryUtil.createJSONObject(newHashComputedStr);

//							_log.info("New hash computed: " + newHashComputed.toJSONString());
							JSONArray hashComputedArr = newHashComputed.getJSONArray("hashComputers");
							JSONArray signFieldNamesArr = newHashComputed.getJSONArray("signFieldNames");
							JSONArray fileNamesArr = newHashComputed.getJSONArray("fileNames");
							JSONArray fullPathOfSignedFilesArr = newHashComputed.getJSONArray("fullPathSigned");
							JSONArray messagesArr = newHashComputed.getJSONArray("msg");
							long fileEntrySigned = newHashComputed.getLong("fileEntryId");
//							_log.info("Hash computers: " + hashComputedArr.toJSONString());
							for (int i = 0; i < hashComputedArr.length(); i++) {
								hashComputedArrResult.put(hashComputedArr.get(i));
							}
							for (int i = 0; i < signFieldNamesArr.length(); i++) {
								signFieldNamesArrResult.put(signFieldNamesArr.get(i));
							}
//							_log.info("Sign field names: " + signFieldNamesArrResult.toJSONString());
							for (int i = 0; i < fileNamesArr.length(); i++) {
								fileNamesArrResult.put(fileNamesArr.get(i));
							}
//							_log.info("File names: " + fileNamesArrResult.toJSONString());
							for (int i = 0; i < fullPathOfSignedFilesArr.length(); i++) {
								fullPathOfSignedFilesArrResult.put(fullPathOfSignedFilesArr.get(i));
							}
//							_log.info("Full path signed files: " + fullPathOfSignedFilesArrResult.toJSONString());
							for (int i = 0; i < messagesArr.length(); i++) {
								messagesArrResult.put(messagesArr.get(i));
							}
//							_log.info("Messages: " + messagesArrResult.toJSONString());
							fileEntryArrResult.put(fileEntrySigned);
							
//							_log.info("hashComputed: "+hashComputed);
						} catch (Exception e) {
//							_log.info("hashComputed: "+hashComputed);
							_log.info(e);
						}
						
//						results = JSONFactoryUtil.createJSONObject(hashComputed.getString(RESTFulConfiguration.MESSAGE));
//						_log.info("results: "+results);
					} else {
//						results = JSONFactoryUtil.createJSONObject();
//						results.put("msg", "fileEntryId");
					}
//					break;
				}
			}

			if (hashComputedArrResult.length() == 0) {
				results = JSONFactoryUtil.createJSONObject();
				results.put("msg", "fileEntryId");				
			}
			else {
				hashComputed.put("hashComputers", hashComputedArrResult);
				hashComputed.put("signFieldNames", signFieldNamesArrResult);
				hashComputed.put("fileNames", fileNamesArrResult);
				hashComputed.put("msg", messagesArrResult);
				hashComputed.put("fullPathSigned", fullPathOfSignedFilesArrResult);
				hashComputed.put("fileEntryId", fileEntryArrResult);
				
				results = JSONFactoryUtil.createJSONObject(hashComputed.toJSONString());
			}
//			results = JSONFactoryUtil.createJSONObject(hashComputed.getString(RESTFulConfiguration.MESSAGE));
//			_log.info("results: "+results);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private JSONObject callHashComputedSync(long groupId, User user, long fileEntryId, String actionCode,
			String postStepCode, ServiceContext serviceContext) throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		// Call initDossier to SERVER
		String httpMethod = HttpMethods.POST;

		String endPoint = "signature/requestsToken";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileEntryId", fileEntryId);
		params.put("emailUser", user.getEmailAddress());
		params.put("typeSignature", actionCode);
		params.put("postStepCode", postStepCode);

		JSONObject resPostHashComputed = rest.callPostAPI(groupId, httpMethod, "application/json",
				RESTFulConfiguration.SERVER_PATH_BASE, endPoint, RESTFulConfiguration.SERVER_USER,
				RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);

		return resPostHashComputed;

	}

//	private ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
//			long groupId) throws PortalException {
//
//		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);
//
//		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
//				config.getServiceConfigId());
//	}
//
//	protected ProcessAction getProcessAction(long groupId, long dossierId, String refId, String actionCode,
//			long serviceProcessId) throws PortalException {
//		
//		_log.info("GET PROCESS ACTION____");
//		
//		ProcessAction action = null;
//		
//		try {
//			List<ProcessAction> actions = ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
//					serviceProcessId);
//
//			Dossier dossier = getDossier(groupId, dossierId, refId);
//
//			String dossierStatus = dossier.getDossierStatus();
//
//			String dossierSubStatus = dossier.getDossierSubStatus();
//
//			for (ProcessAction act : actions) {
//
//				String preStepCode = act.getPreStepCode();
//
//				ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
//
//				if (Validator.isNull(step)) {
//					action = act;
//					break;
//				} else {
//					if (step.getDossierStatus().contentEquals(dossierStatus)
//							&& StringUtil.containsIgnoreCase(step.getDossierSubStatus(), dossierSubStatus)) {
//
//						action = act;
//						break;
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			_log.error(e);
//			throw new NotFoundException("NotProcessActionFound");
//		}
//
//		return action;
//	}

	protected Dossier getDossier(long groupId, long dossierId, String refId) throws PortalException {

		Dossier dossier = null;

		if (dossierId != 0) {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		} else {
			dossier = DossierLocalServiceUtil.getByRef(groupId, refId);
		}

		return dossier;
	}

	@Override
	public Response updateDossierFilesBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, DigitalSignatureInputModel input)
			throws PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();
		
		_log.info("SONDT SIGNNATUREMGT_IMPL ==============  " + JSONFactoryUtil.looseSerialize(input));

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		String signs = input.getSign();
		String signFieldNames = input.getSignFieldName();
		String fileNames = input.getFileName();
		//_log.info("Sign: " + signs + ", field name: " + signFieldNames + ", file name: " + fileNames + ", file entry id: " + fileEntryIds);
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
		String[] signArr = StringUtil.split(signs);
		String[] signFieldNameArr = Validator.isNotNull(signFieldNames) ? StringUtil.split(signFieldNames): new String[fileEntryIdArr.length];
		String[] fileNameArr = Validator.isNotNull(fileNames) ? StringUtil.split(fileNames): new String[fileEntryIdArr.length];
		String actionCode = input.getActionCode();
		//String actionUser = input.getActionUser();
		//String actionNote = input.getActionNote();
		//String strAssignUserId = input.getAssignUserId() != null ? input.getAssignUserId(): "0";
		//long assignUserId = Long.valueOf(strAssignUserId);
		//String subUsers = input.getSubUsers();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;
		
		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
				String sign = signArr[i];
				String signFieldName = signFieldNameArr[i];
				String fileName = fileNameArr[i];
//				_log.info("sign: "+sign);
//				_log.info("signFieldName: "+signFieldName);
//				_log.info("fileName: "+fileName);
//				_log.info("actionCode: "+actionCode);
//				_log.info("actionUser: "+actionUser);
//				_log.info("actionNote: "+actionNote);
//				_log.info("assignUserId: "+assignUserId);
//				_log.info("subUsers: "+subUsers);
		
				JSONObject signatureCompleted = callSignatureSync(groupId, user, id, sign, signFieldName, fileName,
						serviceContext);
		
				if (signatureCompleted.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
		//			long fileEntryId = Long.valueOf(input.getFileEntryId());
					_log.info("fileEntryId: "+fileEntryId);
					String message = signatureCompleted.getString(RESTFulConfiguration.MESSAGE);
		//			_log.info("message: "+message);
					JSONObject jsonData = JSONFactoryUtil.createJSONObject(message);
		//			_log.info("jsonData: "+jsonData.toJSONString());
		//			String fullPath = String.valueOf(jsonData.get("fullPath"));
		//			_log.info("fullPath: "+fullPath);
					String signedFilePath = jsonData.getString("signedFile");
					File fileSigned = new File(signedFilePath);
		//			_log.info("TEST long file: "+fileSigned.length());
		//			_log.info("TEST file sign: "+fileSigned.lastModified());
		//			long modifiedLong = fileSigned.lastModified();
		//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//			_log.info("TEST file modifiedDate: "+sdf.format(modifiedLong));
		//			_log.info("fileSigned Path: "+fileSigned.getAbsolutePath());
		//			_log.info("fileSigned Name: "+fileSigned.getName());
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
					DLAppLocalServiceUtil.updateFileEntry(user.getUserId(), dlFileEntry.getFileEntryId(), dlFileEntry.getTitle(),
							dlFileEntry.getMimeType(), dlFileEntry.getTitle(), dlFileEntry.getDescription(),
							StringPool.BLANK, true, fileSigned, serviceContext);
					// Update deliverable with deliverableType
					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
				}
			} else {
//				result.put("msg", "fileEntryId");
				signOk = false;
			}			
		}

		//Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
//			_log.info("dossierId: "+dossier.getDossierId());
//			_log.info("ReferenceId: "+dossier.getReferenceUid());
//			_log.info("actionCode: "+actionCode);
//			_log.info("actionUser: "+actionUser);
//			_log.info("actionNote: "+actionNote);
//			_log.info("assignUserId: "+assignUserId);
//			_log.info("subUsers: "+subUsers);
			DossierActions dossierAction = new DossierActionsImpl();
//			dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
//			0L, actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
//			serviceContext);
			if (Validator.isNotNull(actionCode)) {
				ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
				_log.info("Action config: " + actConfig);
				String serviceCode = dossier.getServiceCode();
				String govAgencyCode = dossier.getGovAgencyCode();
				String dossierTempNo = dossier.getDossierTemplateNo();
				ErrorMsgModel errorModel = new ErrorMsgModel();
				if (actConfig != null) {
					boolean insideProcess = actConfig.getInsideProcess();
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
							dossierTempNo, groupId);
					if (insideProcess) {
						if (option != null) {
							long serviceProcessId = option.getServiceProcessId();
							ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(groupId, userId, dossier, option, proAction,
										actionCode, input.getActionUser(), input.getActionNote(),
										input.getPayload(), input.getAssignUsers(), input.getPayment(),
										actConfig.getSyncType(), serviceContext, errorModel);
							}
						}
					} else {
						dossierAction.doAction(groupId, userId, dossier, option, null, actionCode,
								input.getActionUser(), input.getActionNote(), input.getPayload(),
								input.getAssignUsers(), input.getPayment(), actConfig.getSyncType(),
								serviceContext, errorModel);
					}
					//Process send email or sms
				} else {
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode, dossierTempNo,
							groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
								serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(groupId, userId, dossier, option, proAction,
									actionCode, input.getActionUser(), input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(), 0, serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put("msg", "success");
		}
		
		if (!signOk) {
			result.put("msg", "fileEntryId");
		}
		return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFileByCaptcha(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input)
			throws PortalException {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		long fileEntryId = Long.valueOf(input.getFileEntryId());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (fileEntryId > 0) {
			String actionCode = input.getActionCode();
			String actionUser = input.getActionUser();
			String actionNote = input.getActionNote();
			long assignUserId = Long.valueOf(input.getAssignUserId());
			String subUsers = input.getSubUsers();
	
				//Next action
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (dossier != null) {
					DossierActions dossierAction = new DossierActionsImpl();
						dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
								0L, actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
								serviceContext);

					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile == null) {
						dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(fileEntryId);
					}
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
					// Process success
					result.put("msg", "success");
				}
			}

		return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFilesByCaptcha(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, DigitalSignatureInputModel input)
			throws PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();
		
		_log.info("SONDT SIGNNATUREMGT_IMPL ==============  " + JSONFactoryUtil.looseSerialize(input));

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		//_log.info("Sign: " + signs + ", field name: " + signFieldNames + ", file name: " + fileNames + ", file entry id: " + fileEntryIds);
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
		String actionCode = input.getActionCode();
		//String actionUser = input.getActionUser();
		//String actionNote = input.getActionNote();
		//String strAssignUserId = input.getAssignUserId() != null ? input.getAssignUserId(): "0";
		//long assignUserId = Long.valueOf(strAssignUserId);
		//String subUsers = input.getSubUsers();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;
		
		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile == null) {
						dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(fileEntryId);
					}
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
				}
		}

		//Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierActions dossierAction = new DossierActionsImpl();
			if (Validator.isNotNull(actionCode)) {
				ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
				String serviceCode = dossier.getServiceCode();
				String govAgencyCode = dossier.getGovAgencyCode();
				String dossierTempNo = dossier.getDossierTemplateNo();
				ErrorMsgModel errorModel = new ErrorMsgModel();
				if (actConfig != null) {
					boolean insideProcess = actConfig.getInsideProcess();
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
							dossierTempNo, groupId);
					if (insideProcess) {
						if (option != null) {
							long serviceProcessId = option.getServiceProcessId();
							ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(groupId, userId, dossier, option, proAction,
										actionCode, input.getActionUser(), input.getActionNote(),
										input.getPayload(), input.getAssignUsers(), input.getPayment(),
										actConfig.getSyncType(), serviceContext, errorModel);
							}
						}
					} else {
						dossierAction.doAction(groupId, userId, dossier, option, null, actionCode,
								input.getActionUser(), input.getActionNote(), input.getPayload(),
								input.getAssignUsers(), input.getPayment(), actConfig.getSyncType(),
								serviceContext, errorModel);
					}
					//Process send email or sms
				} else {
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode, dossierTempNo,
							groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
								serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(groupId, userId, dossier, option, proAction,
									actionCode, input.getActionUser(), input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(), 0, serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put("msg", "success");
		}
		
		if (!signOk) {
			result.put("msg", "fileEntryId");
		}
		return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
	}
	@Override
	public Response updateDossierFileBySignatureDefault(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input)
			throws PortalException {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		long fileEntryId = Long.valueOf(input.getFileEntryId());
		JSONObject result = JSONFactoryUtil.createJSONObject();
		if (fileEntryId > 0) {
			String actionCode = input.getActionCode();
			String actionUser = input.getActionUser();
			String actionNote = input.getActionNote();
			long assignUserId = Long.valueOf(input.getAssignUserId());
			String subUsers = input.getSubUsers();
	
				//Next action
				Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (dossier != null) {
					DossierActions dossierAction = new DossierActionsImpl();
						dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
								0L, actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
								serviceContext);

					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile == null) {
						dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(fileEntryId);
					}
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
					// Process success
					result.put("msg", "success");
				}
			}

		return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFilesBySignatureDefault(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long id,
			DigitalSignatureInputModel input) throws PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();
		
		_log.info("SONDT SIGNNATUREMGT_IMPL ==============  " + JSONFactoryUtil.looseSerialize(input));

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		//_log.info("Sign: " + signs + ", field name: " + signFieldNames + ", file name: " + fileNames + ", file entry id: " + fileEntryIds);
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
		String actionCode = input.getActionCode();
		//String actionUser = input.getActionUser();
		//String actionNote = input.getActionNote();
		//String strAssignUserId = input.getAssignUserId() != null ? input.getAssignUserId(): "0";
		//long assignUserId = Long.valueOf(strAssignUserId);
		//String subUsers = input.getSubUsers();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;
		
		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile == null) {
						dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(fileEntryId);
					}
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
				}
		}

		//Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierActions dossierAction = new DossierActionsImpl();
			if (Validator.isNotNull(actionCode)) {
				ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
				String serviceCode = dossier.getServiceCode();
				String govAgencyCode = dossier.getGovAgencyCode();
				String dossierTempNo = dossier.getDossierTemplateNo();
				ErrorMsgModel errorModel = new ErrorMsgModel();
				if (actConfig != null) {
					boolean insideProcess = actConfig.getInsideProcess();
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode,
							dossierTempNo, groupId);
					if (insideProcess) {
						if (option != null) {
							long serviceProcessId = option.getServiceProcessId();
							ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(groupId, userId, dossier, option, proAction,
										actionCode, input.getActionUser(), input.getActionNote(),
										input.getPayload(), input.getAssignUsers(), input.getPayment(),
										actConfig.getSyncType(), serviceContext, errorModel);
							}
						}
					} else {
						dossierAction.doAction(groupId, userId, dossier, option, null, actionCode,
								input.getActionUser(), input.getActionNote(), input.getPayload(),
								input.getAssignUsers(), input.getPayment(), actConfig.getSyncType(),
								serviceContext, errorModel);
					}
					//Process send email or sms
				} else {
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode, dossierTempNo,
							groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(groupId, dossier, actionCode,
								serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(groupId, userId, dossier, option, proAction,
									actionCode, input.getActionUser(), input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(), 0, serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put("msg", "success");
		}
		
		if (!signOk) {
			result.put("msg", "fileEntryId");
		}
		return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();
	}

}
