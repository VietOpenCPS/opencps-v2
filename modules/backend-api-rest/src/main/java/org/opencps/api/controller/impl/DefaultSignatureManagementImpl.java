
package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DefaultSignatureManagement;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.digitalsignature.model.DigitalSignatureInputModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.utils.DLFolderUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
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
import org.osgi.service.component.annotations.Reference;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.ErrorMsgModel;

/**
 * @author trungnt
 */
public class DefaultSignatureManagementImpl
	implements DefaultSignatureManagement {

	@Reference
	private DLAppService _dlAppService;
	Log _log = LogFactoryUtil.getLog(SignatureManagementImpl.class.getName());
	// private static final String TYPE_KYSO = "1135, 1158, 1160, 1032";
	// private static final String TYPE_DONGDAU = "1137, 1162, 105";

	@Override
	public Response updateDossierFileBySignature(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		DigitalSignatureInputModel input)
		throws PortalException {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
			// _log.info("sign: "+sign);
			// _log.info("signFieldName: "+signFieldName);
			// _log.info("fileName: "+fileName);
			String actionCode = input.getActionCode();
			String actionUser = input.getActionUser();
			String actionNote = input.getActionNote();
			long assignUserId = Long.valueOf(input.getAssignUserId());
			String subUsers = input.getSubUsers();
			// _log.info("actionCode: "+actionCode);
			// _log.info("actionUser: "+actionUser);
			// _log.info("actionNote: "+actionNote);
			// _log.info("assignUserId: "+assignUserId);
			// _log.info("subUsers: "+subUsers);

			JSONObject signatureCompleted = callSignatureSync(
				groupId, user, id, sign, signFieldName, fileName,
				serviceContext);

			if (signatureCompleted.getInt(
				RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
				// long fileEntryId = Long.valueOf(input.getFileEntryId());
				_log.info("fileEntryId: " + fileEntryId);
				String message =
					signatureCompleted.getString(RESTFulConfiguration.MESSAGE);
				// _log.info("message: "+message);
				JSONObject jsonData = JSONFactoryUtil.createJSONObject(message);
				// _log.info("jsonData: "+jsonData.toJSONString());
				// String fullPath = String.valueOf(jsonData.get("fullPath"));
				// _log.info("fullPath: "+fullPath);
				String signedFilePath = jsonData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_SIGNEDFILE);
				File fileSigned = new File(signedFilePath);
				// _log.info("TEST long file: "+fileSigned.length());
				// _log.info("TEST file sign: "+fileSigned.lastModified());
				// long modifiedLong = fileSigned.lastModified();
				// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy
				// HH:mm:ss");
				// _log.info("TEST file modifiedDate:
				// "+sdf.format(modifiedLong));
				// _log.info("fileSigned Path: "+fileSigned.getAbsolutePath());
				// _log.info("fileSigned Name: "+fileSigned.getName());
				DLFileEntry dlFileEntry =
					DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
				// _log.info("dlFileEntry: "+dlFileEntry.getFileName());

				DLAppLocalServiceUtil.updateFileEntry(
					user.getUserId(), dlFileEntry.getFileEntryId(),
					dlFileEntry.getTitle(), dlFileEntry.getMimeType(),
					dlFileEntry.getTitle(), dlFileEntry.getDescription(),
					StringPool.BLANK, true, fileSigned, serviceContext);

				// Next action
				Dossier dossier =
					DossierLocalServiceUtil.fetchDossier(dossierId);
				if (dossier != null) {
					// _log.info("dossierId: "+dossier.getDossierId());
					// _log.info("ReferenceId: "+dossier.getReferenceUid());
					// _log.info("actionCode: "+actionCode);
					// _log.info("actionUser: "+actionUser);
					// _log.info("actionNote: "+actionNote);
					// _log.info("assignUserId: "+assignUserId);
					// _log.info("subUsers: "+subUsers);
					DossierActions dossierAction = new DossierActionsImpl();
					// if (TYPE_KYSO.contains(actionCode)) {
					dossierAction.doAction(
						groupId, dossierId, dossier.getReferenceUid(),
						actionCode, 0L, actionUser, actionNote, assignUserId,
						user.getUserId(), subUsers, serviceContext);

					// if (TYPE_KYSO.contains(actionCode)) {
					// dossierAction.doAction(groupId, dossierId,
					// dossier.getReferenceUid(), actionCode,
					// 0L, actionUser, actionNote, assignUserId,
					// user.getUserId(), subUsers,
					// serviceContext);
					// } else if(TYPE_DONGDAU.contains(actionCode)) {
					// ProcessOption option =
					// getProcessOption(dossier.getServiceCode(),
					// dossier.getGovAgencyCode(),
					// dossier.getDossierTemplateNo(), groupId);
					//
					// ProcessAction action = getProcessAction(groupId,
					// dossier.getDossierId(), dossier.getReferenceUid(),
					// input.getActionCode(), option.getServiceProcessId());
					//
					// dossierAction.doAction(groupId, dossierId,
					// dossier.getReferenceUid(), actionCode,
					// action.getProcessActionId(), actionUser, actionNote,
					// assignUserId, user.getUserId(), subUsers,
					// serviceContext);
					// } else {
					// //TODO
					// }
					// Update deliverable with deliverableType
					DossierFile dossierFile =
						DossierFileLocalServiceUtil.getByFileEntryId(
							fileEntryId);
					if (dossierFile != null) {
						String deliverableCode =
							dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable =
								DeliverableLocalServiceUtil.getByCode(
									deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(
									deliverable.getDeliverableState());
								if (!"1".equals(deliState)) {
									deliverable.setDeliverableState(1);
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								}
							}
						}
					}
					// Process success
					result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
				}
			}
		}
		else {
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();

	}

	private JSONObject callSignatureSync(
		long groupId, User user, long id, String sign, String signFieldName,
		String fileName, ServiceContext serviceContext)
		throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		// Call initDossier to SERVER

		String httpMethod = HttpMethods.POST;

		String endPoint = "signature/completeSignature";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_SIGN, sign);
		params.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_SIGNFIELDNAME, signFieldName);
		params.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_FILENAME, fileName);

		JSONObject resPostDossier = rest.callPostAPI(
			groupId, httpMethod, ConstantUtils.API_JSON_DEFAULTSIGNATURE_FILENAME,
			RESTFulConfiguration.SERVER_PATH_BASE, endPoint,
			RESTFulConfiguration.SERVER_USER, RESTFulConfiguration.SERVER_PASS,
			properties, params, serviceContext);

		return resPostDossier;

	}

	@Override
	public Response getHashComputedBySignature(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		DigitalSignatureInputModel input) {

		_log.info("START*************");
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String strIdArr = input.getStrIdArr();
			// _log.info("array Id: "+strIdArr);

			String[] idSplit = strIdArr.split(StringPool.SEMICOLON);
			// _log.info("idSplit Id: "+idSplit);

			JSONObject hashComputed = JSONFactoryUtil.createJSONObject();
			JSONObject results = null;
			JSONArray hashComputedArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray signFieldNamesArrResult =
				JSONFactoryUtil.createJSONArray();
			JSONArray fileNamesArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray fullPathOfSignedFilesArrResult =
				JSONFactoryUtil.createJSONArray();
			JSONArray messagesArrResult = JSONFactoryUtil.createJSONArray();
			JSONArray fileEntryArrResult = JSONFactoryUtil.createJSONArray();

			for (String strId : idSplit) {
				String[] idArr = strId.split(StringPool.COMMA);
				DossierPart dossierPart =
					DossierPartLocalServiceUtil.fetchDossierPart(
						Long.valueOf(idArr[1]));
				_log.info("Dossier Part: " + dossierPart.getDossierPartId());
				DossierFile dossierFile = null;
				if (dossierPart != null && dossierPart.getESign()) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						Long.valueOf(idArr[0]));
					_log.info(
						"Dossier File: " + dossierFile.getDossierFileId());
					if (dossierFile != null &&
						dossierFile.getFileEntryId() > 0) {
						long fileEntryId = dossierFile.getFileEntryId();
						_log.info("fileEntryId: " + fileEntryId);

						try {
							// _log.info("START CALL HASHCOMPUTE: ");
							JSONObject newHashComputedResult =
								callHashComputedSync(
									groupId, user, fileEntryId,
									input.getActionCode(),
									input.getPostStepCode(), serviceContext);
							// _log.info("Obj: " + newHashComputedResult);
							String newHashComputedStr =
								newHashComputedResult.getString(ConstantUtils.HASHCOMPUTED_MESSAGE_KEY);

							JSONObject newHashComputed =
								JSONFactoryUtil.createJSONObject(
									newHashComputedStr);

							// _log.info("New hash computed: " +
							// newHashComputed.toJSONString());
							JSONArray hashComputedArr =
								newHashComputed.getJSONArray(ConstantUtils.HASHCOMPUTED_HASHCOMPUTERS_KEY);
							JSONArray signFieldNamesArr =
								newHashComputed.getJSONArray(ConstantUtils.HASHCOMPUTED_SIGNFIELDNAMES_KEY);
							JSONArray fileNamesArr =
								newHashComputed.getJSONArray(ConstantUtils.HASHCOMPUTED_FILENAMES_KEY);
							JSONArray fullPathOfSignedFilesArr =
								newHashComputed.getJSONArray(ConstantUtils.HASHCOMPUTED_FULLPATHSIGNED_KEY);
							JSONArray messagesArr =
								newHashComputed.getJSONArray(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG);
							long fileEntrySigned =
								newHashComputed.getLong(ConstantUtils.API_JSON_DEFAULTSIGNATURE_FILEENTRYID);
							// _log.info("Hash computers: " +
							// hashComputedArr.toJSONString());
							for (int i = 0; i < hashComputedArr.length(); i++) {
								hashComputedArrResult.put(
									hashComputedArr.get(i));
							}
							for (int i =
								0; i < signFieldNamesArr.length(); i++) {
								signFieldNamesArrResult.put(
									signFieldNamesArr.get(i));
							}
							// _log.info("Sign field names: " +
							// signFieldNamesArrResult.toJSONString());
							for (int i = 0; i < fileNamesArr.length(); i++) {
								fileNamesArrResult.put(fileNamesArr.get(i));
							}
							// _log.info("File names: " +
							// fileNamesArrResult.toJSONString());
							for (int i =
								0; i < fullPathOfSignedFilesArr.length(); i++) {
								fullPathOfSignedFilesArrResult.put(
									fullPathOfSignedFilesArr.get(i));
							}
							// _log.info("Full path signed files: " +
							// fullPathOfSignedFilesArrResult.toJSONString());
							for (int i = 0; i < messagesArr.length(); i++) {
								messagesArrResult.put(messagesArr.get(i));
							}
							// _log.info("Messages: " +
							// messagesArrResult.toJSONString());
							fileEntryArrResult.put(fileEntrySigned);

							// _log.info("hashComputed: "+hashComputed);
						}
						catch (Exception e) {
							// _log.info("hashComputed: "+hashComputed);
							_log.info(e);
						}

						// results =
						// JSONFactoryUtil.createJSONObject(hashComputed.getString(RESTFulConfiguration.MESSAGE));
						// _log.info("results: "+results);
					}
					else {
						// results = JSONFactoryUtil.createJSONObject();
						// results.put("msg", "fileEntryId");
					}
					// break;
				}
			}

			if (hashComputedArrResult.length() == 0) {
				results = JSONFactoryUtil.createJSONObject();
				results.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
			}
			else {
				hashComputed.put(ConstantUtils.HASHCOMPUTED_HASHCOMPUTERS_KEY, hashComputedArrResult);
				hashComputed.put(ConstantUtils.HASHCOMPUTED_SIGNFIELDNAMES_KEY, signFieldNamesArrResult);
				hashComputed.put(ConstantUtils.HASHCOMPUTED_FILENAMES_KEY, fileNamesArrResult);
				hashComputed.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, messagesArrResult);
				hashComputed.put(
					ConstantUtils.HASHCOMPUTED_FULLPATHSIGNED_KEY, fullPathOfSignedFilesArrResult);
				hashComputed.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_FILEENTRYID, fileEntryArrResult);

				results = JSONFactoryUtil.createJSONObject(
					hashComputed.toJSONString());
			}
			// results =
			// JSONFactoryUtil.createJSONObject(hashComputed.getString(RESTFulConfiguration.MESSAGE));
			// _log.info("results: "+results);

			return Response.status(HttpURLConnection.HTTP_OK).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private JSONObject callHashComputedSync(
		long groupId, User user, long fileEntryId, String actionCode,
		String postStepCode, ServiceContext serviceContext)
		throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		// Call initDossier to SERVER
		String httpMethod = HttpMethods.POST;

		String endPoint = ConstantUtils.DEFAULTSIGNATURE_HASHCOMPUTED_END_POINT;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_FILEENTRYID, fileEntryId);
		params.put(ConstantUtils.HASHCOMPUTED_EMAILUSER_KEY, user.getEmailAddress());
		params.put(ConstantUtils.HASHCOMPUTED_TYPESIGNATURE_KEY, actionCode);
		params.put(ConstantUtils.HASHCOMPUTED_POSTSTEPCODE_KEY, postStepCode);

		JSONObject resPostHashComputed = rest.callPostAPI(
			groupId, httpMethod, ConstantUtils.CONTENT_TYPE_JSON,
			RESTFulConfiguration.SERVER_PATH_BASE, endPoint,
			RESTFulConfiguration.SERVER_USER, RESTFulConfiguration.SERVER_PASS,
			properties, params, serviceContext);

		return resPostHashComputed;

	}

	// private ProcessOption getProcessOption(String serviceInfoCode, String
	// govAgencyCode, String dossierTemplateNo,
	// long groupId) throws PortalException {
	//
	// ServiceConfig config =
	// ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode,
	// govAgencyCode);
	//
	// return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId,
	// dossierTemplateNo,
	// config.getServiceConfigId());
	// }
	//
	// protected ProcessAction getProcessAction(long groupId, long dossierId,
	// String refId, String actionCode,
	// long serviceProcessId) throws PortalException {
	//
	// _log.info("GET PROCESS ACTION____");
	//
	// ProcessAction action = null;
	//
	// try {
	// List<ProcessAction> actions =
	// ProcessActionLocalServiceUtil.getByActionCode(groupId, actionCode,
	// serviceProcessId);
	//
	// Dossier dossier = getDossier(groupId, dossierId, refId);
	//
	// String dossierStatus = dossier.getDossierStatus();
	//
	// String dossierSubStatus = dossier.getDossierSubStatus();
	//
	// for (ProcessAction act : actions) {
	//
	// String preStepCode = act.getPreStepCode();
	//
	// ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(preStepCode,
	// groupId, serviceProcessId);
	//
	// if (Validator.isNull(step)) {
	// action = act;
	// break;
	// } else {
	// if (step.getDossierStatus().contentEquals(dossierStatus)
	// && StringUtil.containsIgnoreCase(step.getDossierSubStatus(),
	// dossierSubStatus)) {
	//
	// action = act;
	// break;
	// }
	// }
	// }
	//
	// } catch (Exception e) {
	// _log.error(e);
	// throw new NotFoundException("NotProcessActionFound");
	// }
	//
	// return action;
	// }

	protected Dossier getDossier(long groupId, long dossierId, String refId)
		throws PortalException {

		Dossier dossier = null;

		if (dossierId != 0) {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		}
		else {
			dossier = DossierLocalServiceUtil.getByRef(groupId, refId);
		}

		return dossier;
	}

	@Override
	public Response updateDossierFilesBySignature(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		DigitalSignatureInputModel input)
		throws PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		_log.info(
			"SONDT SIGNNATUREMGT_IMPL ==============  " +
				JSONFactoryUtil.looseSerialize(input));

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		String signs = input.getSign();
		String signFieldNames = input.getSignFieldName();
		String fileNames = input.getFileName();
		// _log.info("Sign: " + signs + ", field name: " + signFieldNames + ",
		// file name: " + fileNames + ", file entry id: " + fileEntryIds);
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
		String[] signArr = StringUtil.split(signs);
		String[] signFieldNameArr = Validator.isNotNull(signFieldNames)
			? StringUtil.split(signFieldNames)
			: new String[fileEntryIdArr.length];
		String[] fileNameArr = Validator.isNotNull(fileNames)
			? StringUtil.split(fileNames) : new String[fileEntryIdArr.length];
		String actionCode = input.getActionCode();
		// String actionUser = input.getActionUser();
		// String actionNote = input.getActionNote();
		// String strAssignUserId = input.getAssignUserId() != null ?
		// input.getAssignUserId(): "0";
		// long assignUserId = Long.valueOf(strAssignUserId);
		// String subUsers = input.getSubUsers();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;

		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
				String sign = signArr[i];
				String signFieldName = signFieldNameArr[i];
				String fileName = fileNameArr[i];
				// _log.info("sign: "+sign);
				// _log.info("signFieldName: "+signFieldName);
				// _log.info("fileName: "+fileName);
				// _log.info("actionCode: "+actionCode);
				// _log.info("actionUser: "+actionUser);
				// _log.info("actionNote: "+actionNote);
				// _log.info("assignUserId: "+assignUserId);
				// _log.info("subUsers: "+subUsers);

				JSONObject signatureCompleted = callSignatureSync(
					groupId, user, id, sign, signFieldName, fileName,
					serviceContext);

				if (signatureCompleted.getInt(
					RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
					// long fileEntryId = Long.valueOf(input.getFileEntryId());
					_log.info("fileEntryId: " + fileEntryId);
					String message = signatureCompleted.getString(
						RESTFulConfiguration.MESSAGE);
					// _log.info("message: "+message);
					JSONObject jsonData =
						JSONFactoryUtil.createJSONObject(message);
					// _log.info("jsonData: "+jsonData.toJSONString());
					// String fullPath =
					// String.valueOf(jsonData.get("fullPath"));
					// _log.info("fullPath: "+fullPath);
					String signedFilePath = jsonData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_SIGNEDFILE);
					File fileSigned = new File(signedFilePath);
					// _log.info("TEST long file: "+fileSigned.length());
					// _log.info("TEST file sign: "+fileSigned.lastModified());
					// long modifiedLong = fileSigned.lastModified();
					// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy
					// HH:mm:ss");
					// _log.info("TEST file modifiedDate:
					// "+sdf.format(modifiedLong));
					// _log.info("fileSigned Path:
					// "+fileSigned.getAbsolutePath());
					// _log.info("fileSigned Name: "+fileSigned.getName());
					DLFileEntry dlFileEntry =
						DLFileEntryLocalServiceUtil.fetchDLFileEntry(
							fileEntryId);
					DLAppLocalServiceUtil.updateFileEntry(
						user.getUserId(), dlFileEntry.getFileEntryId(),
						dlFileEntry.getTitle(), dlFileEntry.getMimeType(),
						dlFileEntry.getTitle(), dlFileEntry.getDescription(),
						StringPool.BLANK, true, fileSigned, serviceContext);
					// Update deliverable with deliverableType
					DossierFile dossierFile =
						DossierFileLocalServiceUtil.getByFileEntryId(
							fileEntryId);
					if (dossierFile != null) {
						String deliverableCode =
							dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable =
								DeliverableLocalServiceUtil.getByCode(
									deliverableCode);
							if (deliverable != null && Validator.isNotNull(deliverable.getFormData())) {
								JSONObject formData = JSONFactoryUtil.createJSONObject(deliverable.getFormData());
								String deliState = String.valueOf(
									deliverable.getDeliverableState());
								if (formData.has(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)){
									
									deliverable.setDeliverableState(GetterUtil.getInteger(formData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)));
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								} else if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
									deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								}
							} else if (deliverable != null) {
								
								String deliState = String.valueOf(
									deliverable.getDeliverableState());
								if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
									deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								}
							}
						}
					}
				}
			}
			else {
				// result.put("msg", "fileEntryId");
				signOk = false;
			}
		}

		// Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			// _log.info("dossierId: "+dossier.getDossierId());
			// _log.info("ReferenceId: "+dossier.getReferenceUid());
			// _log.info("actionCode: "+actionCode);
			// _log.info("actionUser: "+actionUser);
			// _log.info("actionNote: "+actionNote);
			// _log.info("assignUserId: "+assignUserId);
			// _log.info("subUsers: "+subUsers);
			DossierActions dossierAction = new DossierActionsImpl();
			// dossierAction.doAction(groupId, dossierId,
			// dossier.getReferenceUid(), actionCode,
			// 0L, actionUser, actionNote, assignUserId, user.getUserId(),
			// subUsers,
			// serviceContext);
			if (Validator.isNotNull(actionCode)) {
				ActionConfig actConfig =
					ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
				_log.info("Action config: " + actConfig);
				String serviceCode = dossier.getServiceCode();
				String govAgencyCode = dossier.getGovAgencyCode();
				String dossierTempNo = dossier.getDossierTemplateNo();
				ErrorMsgModel errorModel = new ErrorMsgModel();
				if (actConfig != null) {
					boolean insideProcess = actConfig.getInsideProcess();
					ProcessOption option = DossierUtils.getProcessOption(
						serviceCode, govAgencyCode, dossierTempNo, groupId);
					if (insideProcess) {
						if (option != null) {
							long serviceProcessId =
								option.getServiceProcessId();
							ProcessAction proAction =
								DossierUtils.getProcessAction(
									user,
									groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(
									groupId, userId, dossier, option, proAction,
									actionCode, input.getActionUser(),
									input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(),
									actConfig.getSyncType(), serviceContext,
									errorModel);
							}
						}
					}
					else {
						dossierAction.doAction(
							groupId, userId, dossier, option, null, actionCode,
							input.getActionUser(), input.getActionNote(),
							input.getPayload(), input.getAssignUsers(),
							input.getPayment(), actConfig.getSyncType(),
							serviceContext, errorModel);
					}
					// Process send email or sms
				}
				else {
					ProcessOption option = DossierUtils.getProcessOption(
						serviceCode, govAgencyCode, dossierTempNo, groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(
							user,
							groupId, dossier, actionCode, serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(
								groupId, userId, dossier, option, proAction,
								actionCode, input.getActionUser(),
								input.getActionNote(), input.getPayload(),
								input.getAssignUsers(), input.getPayment(), 0,
								serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
		}

		if (!signOk) {
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFileByCaptcha(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		DigitalSignatureInputModel input)
		throws PortalException {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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

			// Next action
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier != null) {
				DossierActions dossierAction = new DossierActionsImpl();
				dossierAction.doAction(
					groupId, dossierId, dossier.getReferenceUid(), actionCode,
					0L, actionUser, actionNote, assignUserId, user.getUserId(),
					subUsers, serviceContext);

				DossierFile dossierFile =
					DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
				if (dossierFile == null) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						fileEntryId);
				}
				if (dossierFile != null) {
					String deliverableCode = dossierFile.getDeliverableCode();
					if (Validator.isNotNull(deliverableCode)) {
						Deliverable deliverable =
							DeliverableLocalServiceUtil.getByCode(
								deliverableCode);
						if (deliverable != null && Validator.isNotNull(deliverable.getFormData())) {
							JSONObject formData = JSONFactoryUtil.createJSONObject(deliverable.getFormData());
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (formData.has(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)){
								
								deliverable.setDeliverableState(GetterUtil.getInteger(formData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)));
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							} else if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						} else if (deliverable != null) {
							
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						}
					}
				}
				// Process success
				result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
			}
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFilesByCaptcha(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		DigitalSignatureInputModel input)
		throws PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		_log.info(
			"SONDT SIGNNATUREMGT_IMPL ==============  " +
				JSONFactoryUtil.looseSerialize(input));

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		// _log.info("Sign: " + signs + ", field name: " + signFieldNames + ",
		// file name: " + fileNames + ", file entry id: " + fileEntryIds);
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
		String actionCode = input.getActionCode();
		// String actionUser = input.getActionUser();
		// String actionNote = input.getActionNote();
		// String strAssignUserId = input.getAssignUserId() != null ?
		// input.getAssignUserId(): "0";
		// long assignUserId = Long.valueOf(strAssignUserId);
		// String subUsers = input.getSubUsers();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;

		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
				DossierFile dossierFile =
					DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
				if (dossierFile == null) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						fileEntryId);
				}
				if (dossierFile != null) {
					String deliverableCode = dossierFile.getDeliverableCode();
					if (Validator.isNotNull(deliverableCode)) {
						Deliverable deliverable =
							DeliverableLocalServiceUtil.getByCode(
								deliverableCode);
						if (deliverable != null && Validator.isNotNull(deliverable.getFormData())) {
							JSONObject formData = JSONFactoryUtil.createJSONObject(deliverable.getFormData());
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (formData.has(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)){
								
								deliverable.setDeliverableState(GetterUtil.getInteger(formData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)));
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							} else if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						} else if (deliverable != null) {
							
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						}
					}
				}
			}
		}

		// Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierActions dossierAction = new DossierActionsImpl();
			if (Validator.isNotNull(actionCode)) {
				ActionConfig actConfig =
					ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
				String serviceCode = dossier.getServiceCode();
				String govAgencyCode = dossier.getGovAgencyCode();
				String dossierTempNo = dossier.getDossierTemplateNo();
				ErrorMsgModel errorModel = new ErrorMsgModel();
				if (actConfig != null) {
					boolean insideProcess = actConfig.getInsideProcess();
					ProcessOption option = DossierUtils.getProcessOption(
						serviceCode, govAgencyCode, dossierTempNo, groupId);
					if (insideProcess) {
						if (option != null) {
							long serviceProcessId =
								option.getServiceProcessId();
							ProcessAction proAction =
								DossierUtils.getProcessAction(
									user,
									groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(
									groupId, userId, dossier, option, proAction,
									actionCode, input.getActionUser(),
									input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(),
									actConfig.getSyncType(), serviceContext,
									errorModel);
							}
						}
					}
					else {
						dossierAction.doAction(
							groupId, userId, dossier, option, null, actionCode,
							input.getActionUser(), input.getActionNote(),
							input.getPayload(), input.getAssignUsers(),
							input.getPayment(), actConfig.getSyncType(),
							serviceContext, errorModel);
					}
					// Process send email or sms
				}
				else {
					ProcessOption option = DossierUtils.getProcessOption(
						serviceCode, govAgencyCode, dossierTempNo, groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(
							user,
							groupId, dossier, actionCode, serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(
								groupId, userId, dossier, option, proAction,
								actionCode, input.getActionUser(),
								input.getActionNote(), input.getPayload(),
								input.getAssignUsers(), input.getPayment(), 0,
								serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
		}

		if (!signOk) {
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFileBySignatureDefault(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, Long id,
		DigitalSignatureInputModel input)
		throws PortalException {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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

			// Next action
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier != null) {
				DossierActions dossierAction = new DossierActionsImpl();
				dossierAction.doAction(
					groupId, dossierId, dossier.getReferenceUid(), actionCode,
					0L, actionUser, actionNote, assignUserId, user.getUserId(),
					subUsers, serviceContext);

				DossierFile dossierFile =
					DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
				if (dossierFile == null) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						fileEntryId);
				}
				if (dossierFile != null) {
					String deliverableCode = dossierFile.getDeliverableCode();
					if (Validator.isNotNull(deliverableCode)) {
						Deliverable deliverable =
							DeliverableLocalServiceUtil.getByCode(
								deliverableCode);
						if (deliverable != null && Validator.isNotNull(deliverable.getFormData())) {
							JSONObject formData = JSONFactoryUtil.createJSONObject(deliverable.getFormData());
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (formData.has(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)){
								
								deliverable.setDeliverableState(GetterUtil.getInteger(formData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)));
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							} else if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						} else if (deliverable != null) {
							
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						}
					}
				}
				// Process success
				result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
			}
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFilesBySignatureDefault(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		DigitalSignatureInputModel input)
		throws PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		_log.info(
			"SONDT SIGNNATUREMGT_IMPL ==============  " +
				JSONFactoryUtil.looseSerialize(input));

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		// _log.info("Sign: " + signs + ", field name: " + signFieldNames + ",
		// file name: " + fileNames + ", file entry id: " + fileEntryIds);
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
		String actionCode = input.getActionCode();
		// String actionUser = input.getActionUser();
		// String actionNote = input.getActionNote();
		// String strAssignUserId = input.getAssignUserId() != null ?
		// input.getAssignUserId(): "0";
		// long assignUserId = Long.valueOf(strAssignUserId);
		// String subUsers = input.getSubUsers();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;

		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
				DossierFile dossierFile =
					DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
				if (dossierFile == null) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						fileEntryId);
				}
				if (dossierFile != null) {
					String deliverableCode = dossierFile.getDeliverableCode();
					if (Validator.isNotNull(deliverableCode)) {
						if (dossierFile.getEForm()) {
							dossierFile.setIsNew(false);
							DossierFileLocalServiceUtil.updateDossierFile(
								dossierFile);
						}
						Deliverable deliverable =
							DeliverableLocalServiceUtil.getByCode(
								deliverableCode);
						if (deliverable != null && Validator.isNotNull(deliverable.getFormData())) {
							JSONObject formData = JSONFactoryUtil.createJSONObject(deliverable.getFormData());
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (formData.has(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)){
								
								deliverable.setDeliverableState(GetterUtil.getInteger(formData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)));
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							} else if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						} else if (deliverable != null) {
							
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
								deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
								DeliverableLocalServiceUtil.updateDeliverable(
									deliverable);
							}
						}
					}
				}
			}
		}

		// Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierActions dossierAction = new DossierActionsImpl();
			if (Validator.isNotNull(actionCode)) {
				ActionConfig actConfig =
					ActionConfigLocalServiceUtil.getByCode(groupId, actionCode);
				String serviceCode = dossier.getServiceCode();
				String govAgencyCode = dossier.getGovAgencyCode();
				String dossierTempNo = dossier.getDossierTemplateNo();
				ErrorMsgModel errorModel = new ErrorMsgModel();
				if (actConfig != null) {
					boolean insideProcess = actConfig.getInsideProcess();
					ProcessOption option = DossierUtils.getProcessOption(
						serviceCode, govAgencyCode, dossierTempNo, groupId);
					if (insideProcess) {
						if (option != null) {
							long serviceProcessId =
								option.getServiceProcessId();
							ProcessAction proAction =
								DossierUtils.getProcessAction(
									user,
									groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(
									groupId, userId, dossier, option, proAction,
									actionCode, input.getActionUser(),
									input.getActionNote(), input.getPayload(),
									input.getAssignUsers(), input.getPayment(),
									actConfig.getSyncType(), serviceContext,
									errorModel);
							}
						}
					}
					else {
						dossierAction.doAction(
							groupId, userId, dossier, option, null, actionCode,
							input.getActionUser(), input.getActionNote(),
							input.getPayload(), input.getAssignUsers(),
							input.getPayment(), actConfig.getSyncType(),
							serviceContext, errorModel);
					}
					// Process send email or sms
				}
				else {
					ProcessOption option = DossierUtils.getProcessOption(
						serviceCode, govAgencyCode, dossierTempNo, groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(
							user,
							groupId, dossier, actionCode, serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(
								groupId, userId, dossier, option, proAction,
								actionCode, input.getActionUser(),
								input.getActionNote(), input.getPayload(),
								input.getAssignUsers(), input.getPayment(), 0,
								serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
		}

		if (!signOk) {
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response updateDossierFilesBySignatureDefault2(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file, long id, DigitalSignatureInputModel input)
		throws PortalException, Exception {

		BackendAuth auth = new BackendAuthImpl();

		_log.info(
			"SONDT SIGNNATUREMGT_IMPL ==============  " +
				JSONFactoryUtil.looseSerialize(input));

//		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
//		long dossierId = Long.valueOf(id);
//		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String fileEntryIds = input.getFileEntryId();
		String[] fileEntryIdArr = StringUtil.split(fileEntryIds);
//		String actionCode = input.getActionCode();
		JSONObject result = JSONFactoryUtil.createJSONObject();
//		boolean signOk = true;

		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
				DossierFile dossierFile =
					DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
				if (dossierFile == null) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
						fileEntryId);
				}
				if (dossierFile != null) {
					String deliverableCode = dossierFile.getDeliverableCode();
					if (Validator.isNotNull(deliverableCode)) {
						if (dossierFile.getEForm()) {
							dossierFile.setIsNew(false);
							// DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
						}
						Deliverable deliverable =
							DeliverableLocalServiceUtil.getByCode(
								deliverableCode);
						if (deliverable != null) {
							String deliState = String.valueOf(
								deliverable.getDeliverableState());
							if (deliverable != null && Validator.isNotNull(deliverable.getFormData())) {
								JSONObject formData = JSONFactoryUtil.createJSONObject(deliverable.getFormData());
								if (formData.has(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)){
									
									deliverable.setDeliverableState(GetterUtil.getInteger(formData.getString(ConstantUtils.API_JSON_DEFAULTSIGNATURE_EXPERTSTATE)));
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								} else if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
									deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								}
							} else if (deliverable != null) {
								
								if (!DeliverableTerm.DELIVERABLE_STATE_VALID.equals(deliState)) {
									deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_VALID_INT);
									DeliverableLocalServiceUtil.updateDeliverable(
										deliverable);
								}
							}
							if (deliverable.getFileEntryId() > 0 &&
								file != null) {
								FileEntry fileEntry =
									DLAppLocalServiceUtil.getFileEntry(
										deliverable.getFileEntryId());
//								DataHandler dataHandler = file.getDataHandler();
								System.out.println(
									"update======================fileEntry===========" +
										fileEntry.getFileEntryId());
								// fileEntry =
								// DLAppLocalServiceUtil.updateFileEntry(
								// userId, deliverable.getFileEntryId(),
								// fileEntry.getFileName(),
								// fileEntry.getMimeType(),
								// fileEntry.getTitle(),
								// fileEntry.getDescription(),
								// StringPool.BLANK, false,
								// dataHandler.getInputStream(), 0,
								// serviceContext);
							}
						}
					}
				}
			}
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(
			JSONFactoryUtil.looseSerialize(result)).build();
	}

	@Override
	public Response vgcaDossierFilesBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String fileEntries, String dossierFiles,
			String actionCode, String actionUser, String actionNote, String assignUserId, String subUsers,
			String postStepCode, String payload, String payment, String assignUsers, String userNote)
			throws PortalException, Exception {
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = Long.valueOf(id);
		long userId = user.getUserId();

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String[] fileEntryIdArr = StringUtil.split(fileEntries);
		JSONObject result = JSONFactoryUtil.createJSONObject();
		boolean signOk = true;
		String[] dossierFileIdArr = StringUtil.split(dossierFiles);
		
		for (int i = 0; i < fileEntryIdArr.length; i++) {
			long fileEntryId = Long.valueOf(fileEntryIdArr[i]);
			if (fileEntryId > 0) {
					DossierFile df = DossierFileLocalServiceUtil.fetchDossierFile(Long.parseLong(dossierFileIdArr[i]));
					long oldFileEntryId = df.getFileEntryId();
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(oldFileEntryId);
					DLFileEntry newFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
					File fileSigned = DLFileEntryLocalServiceUtil.getFile(fileEntryId, newFileEntry.getVersion(), false);
					
					DLAppLocalServiceUtil.updateFileEntry(user.getUserId(), dlFileEntry.getFileEntryId(), dlFileEntry.getTitle(),
							dlFileEntry.getMimeType(), dlFileEntry.getTitle(), dlFileEntry.getDescription(),
							StringPool.BLANK, true, fileSigned, serviceContext);
					
					DLAppLocalServiceUtil.deleteFileEntry(newFileEntry.getFileEntryId());
					
					// Update deliverable with deliverableType
					DossierFile dossierFile = DossierFileLocalServiceUtil.getByFileEntryId(fileEntryId);
					if (dossierFile != null) {
						String deliverableCode = dossierFile.getDeliverableCode();
						if (Validator.isNotNull(deliverableCode)) {
							Deliverable deliverable = DeliverableLocalServiceUtil.getByCode(deliverableCode);
							if (deliverable != null) {
								String deliState = String.valueOf(deliverable.getDeliverableState());
								if (!DeliverableTerm.DELIVERABLE_STATE_INVALID.equals(deliState)) {
									deliverable.setDeliverableState(DeliverableTerm.DELIVERABLE_STATE_INVALID_INT);
									DeliverableLocalServiceUtil.updateDeliverable(deliverable);
								}
							}
						}
					}
			} else {
				signOk = false;
			}			
		}

		//Next action
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		if (dossier != null) {
			DossierActions dossierAction = new DossierActionsImpl();
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
							ProcessAction proAction = DossierUtils.getProcessAction(user, groupId, dossier, actionCode,
									serviceProcessId);
							if (proAction != null) {
								dossierAction.doAction(groupId, userId, dossier, option, proAction,
										actionCode, actionUser, actionNote,
										payload, assignUsers, payment,
										actConfig.getSyncType(), serviceContext, errorModel);
							}
						}
					} else {
						dossierAction.doAction(groupId, userId, dossier, option, null, actionCode,
								actionUser, actionNote, payload,
								assignUsers, payment, actConfig.getSyncType(),
								serviceContext, errorModel);
					}
					//Process send email or sms
				} else {
					ProcessOption option = DossierUtils.getProcessOption(serviceCode, govAgencyCode, dossierTempNo,
							groupId);
					if (option != null) {
						long serviceProcessId = option.getServiceProcessId();
						ProcessAction proAction = DossierUtils.getProcessAction(user, groupId, dossier, actionCode,
								serviceProcessId);
						if (proAction != null) {
							dossierAction.doAction(groupId, userId, dossier, option, proAction,
									actionCode, actionUser, actionNote, payload,
									assignUsers, payment, 0, serviceContext, errorModel);
						}
					}
				}
			}

			// Process success
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
		}
		
		if (!signOk) {
			result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
		}
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();	
	}	
		
	@Override
	public Response vtcaUploadController(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String singedFileName) throws IOException {
		
		JSONObject result = JSONFactoryUtil.createJSONObject();		
		File pdfFile  = new File(singedFileName);
		ContentDisposition cd = new ContentDisposition(
			      "form-data; name=\"input\"; filename=\"" + pdfFile.getName() + "\"");
		Attachment file = new Attachment("input", new FileInputStream(pdfFile ), cd);
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		if (file.getDataHandler() != null) {
			result.put(ConstantUtils.VGCA_STATUS, true);		
			try {
				FileEntry fileEntry = null;
				InputStream inputStream = file.getDataHandler().getInputStream();
				String sourceFileName = file.getDataHandler().getName();
				if (sourceFileName == null || sourceFileName.length() == 0) {
					sourceFileName = pdfFile.getName();
				}
				String fileType = StringPool.BLANK;
				long fileSize = 0;
				String destination = StringPool.BLANK;
				if (inputStream != null && Validator.isNotNull(sourceFileName)) {
					
					if(Validator.isNull(fileType)) {
						fileType = MimeTypesUtil.getContentType(sourceFileName);
					}
					
					if(fileSize == 0) {
						fileSize = inputStream.available();
					}
					String ext = FileUtil.getExtension(sourceFileName);					
					//String title = Validator.isNotNull(ext) ? (System.currentTimeMillis() + StringPool.PERIOD + ext) :  String.valueOf(System.currentTimeMillis());
					String title = sourceFileName;
					serviceContext.setAddGroupPermissions(true);
					serviceContext.setAddGuestPermissions(true);

					Calendar calendar = Calendar.getInstance();

					calendar.setTime(new Date());
					
					if (destination == null) {
						destination = StringPool.BLANK;
					}

					destination += calendar.get(Calendar.YEAR) + StringPool.SLASH;
					destination += calendar.get(Calendar.MONTH) + StringPool.SLASH;
					destination += calendar.get(Calendar.DAY_OF_MONTH);
//					System.out.println("FILE NAME: " + destination);
					DLFolder dlFolder = DLFolderUtil.getTargetFolder(user.getUserId(), groupId, groupId, false, 0, destination,
							StringPool.BLANK, false, serviceContext);
					PermissionChecker checker = PermissionCheckerFactoryUtil.create(user);
					PermissionThreadLocal.setPermissionChecker(checker);
					JSONObject fileServerObj = JSONFactoryUtil.createJSONObject();
					
					fileEntry = DLAppLocalServiceUtil.addFileEntry(user.getUserId(), groupId, dlFolder.getFolderId(), title,
						fileType, title, title,
						StringPool.BLANK, inputStream, fileSize, serviceContext);

//					System.out.println("File entry: " + fileEntry);
					String fileName = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY), StringPool.BLANK);
//					System.out.println("File name: " + fileName);
					URL url = new URL(request.getAttribute(WebKeys.CURRENT_COMPLETE_URL).toString());
			        String host = url.getHost();
					result.put(ConstantUtils.VGCA_FILENAME, fileName);
					if (fileEntry != null) {
						fileServerObj.put(ConstantUtils.VGCA_FILEENTRYID, fileEntry.getFileEntryId());
						String urlPath = String.format(MessageUtil.getMessage(ConstantUtils.VGCA_URL_PATH), url.getProtocol(), host, (url.getPort() == -1 ? 80 : url.getPort()), fileName);
						
						fileServerObj.put(ConstantUtils.VGCA_URL, urlPath);
						result.put(ConstantUtils.VGCA_FILESERVER, fileServerObj.toJSONString());
					}
				}
				
				
			} catch (IOException e) {
				_log.debug(e);
			} catch (Exception e) {
				_log.debug(e);
			}
			
			result.put(ConstantUtils.VGCA_DOCUMENTNUMBER, StringPool.BLANK);	
		}
		else {
			result.put(ConstantUtils.VGCA_STATUS, false);			
		}
		
		return Response.status(200).entity(result.toJSONString()).build();
	}
	
	/*@Override
	public Response vtcaUpdateFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String fileEntryIdStr, String dossierFileIdStr)
			throws PortalException, Exception {
		
		_log.info("START*************");
		
		BackendAuth auth = new BackendAuthImpl();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		InputStream inputStream = null;
		try {
			
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			long fileEntryId = Long.valueOf(fileEntryIdStr);
			FileEntry fileEntry = null;
			String fileName = null;
			long fileSize = 0;
			String fileType = null;
			if (fileEntryId > 0) {
				
				DLFileEntry newFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
				if (newFileEntry != null) {
					inputStream = newFileEntry.getContentStream();
					fileName = newFileEntry.getFileName();
					fileSize = newFileEntry.getSize();
					fileType = newFileEntry.getMimeType();
				}
				
				DossierFile df = DossierFileLocalServiceUtil.fetchDossierFile(Long.parseLong(dossierFileIdStr));
				long oldFileEntryId = df.getFileEntryId();
				
				if (inputStream != null && fileSize >0 && Validator.isNotNull(fileName)) {
					fileEntry = DLAppLocalServiceUtil.getFileEntry(oldFileEntryId);
					serviceContext.setAddGroupPermissions(true);
					serviceContext.setAddGuestPermissions(true);
					serviceContext.setAttribute("manualCheckInRequired", Boolean.TRUE);

					PermissionChecker checker =
							PermissionCheckerFactoryUtil.create(user);
						PermissionThreadLocal.setPermissionChecker(checker);
					
					fileEntry = DLAppLocalServiceUtil.updateFileEntry(
							user.getUserId(), oldFileEntryId, fileName, fileType,
							System.currentTimeMillis() + StringPool.DASH + fileName, fileEntry.getDescription(),
							StringPool.BLANK, true, inputStream, fileSize, serviceContext);
				}				
				//result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_SUCCESS));
			}else {
				
				//result.put(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG, MessageUtil.getMessage(ConstantUtils.API_JSON_DEFAULTSIGNATURE_MSG_FILEENTRYID));
			}
			return Response.status(HttpURLConnection.HTTP_OK).
					entity(JSONFactoryUtil.looseSerialize(fileEntry)).build();
			
		} catch (Exception e) {
			_log.info(e);
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception io) {
					_log.error(io);
				}
			}
		}
		//return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(result)).build();
	}*/

	@Override
	public Response vtcaUpdateFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String fileEntryIdStr, String dossierFileIdStr)
			throws PortalException, Exception {
		
		_log.info("START*************");		
		BackendAuth auth = new BackendAuthImpl();
		try {
			
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			long newFileEntryId = Long.valueOf(fileEntryIdStr);
			DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.parseLong(dossierFileIdStr));							
			
			if (dossierFile != null) {
				Date now = new Date();
				dossierFile.setModifiedDate(now);
				
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(newFileEntryId);
				if (dlFileEntry != null) {
					dossierFile.setFileEntryId(dlFileEntry.getFileEntryId());
					dossierFile.setDisplayName(dlFileEntry.getFileName());
				}
				DossierFileLocalServiceUtil.updateDossierFile(dossierFile);			
			}
			DossierFileModel result =
					DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			
		} catch (Exception e) {
			_log.info(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

}
