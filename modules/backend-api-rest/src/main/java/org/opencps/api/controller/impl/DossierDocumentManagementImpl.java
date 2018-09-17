package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierDocumentManagement;
import org.opencps.api.controller.util.DossierDucumentUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossierdocument.model.DossierDocumentInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierDocumentManagementImpl implements DossierDocumentManagement {

	private static Log _log = LogFactoryUtil.getLog(DossierDocumentManagementImpl.class);
	@Override
	public Response getPreview(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String typeCode) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierUtils.getDossier(id, groupId);

			if (Validator.isNotNull(dossier)) {

				long dossierActionId = dossier.getDossierActionId();

				DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, typeCode);
				String documentScript = StringPool.BLANK;
				if (docType != null) {
					documentScript = docType.getDocumentScript();
				}

				if (dossierActionId != 0) {
					JSONObject jsonData = JSONFactoryUtil.createJSONObject();
					DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
					String payload = StringPool.BLANK;
					if (dAction != null) {
						payload = dAction.getPayload();
						jsonData = processMergeDossierProcessRole(dossier, 1, jsonData, dAction);
					}
					
					if (Validator.isNotNull(payload)) {
						jsonData = JSONFactoryUtil.createJSONObject(payload);
					}
					jsonData = DossierDucumentUtils.processMergeDossierFormData(dossier, jsonData);
					jsonData.put("url", serviceContext.getPortalURL());
					Message message = new Message();
					message.put("formReport", documentScript);
					message.put("formData", jsonData.toJSONString());

					try {
						String previewResponse = (String) MessageBusUtil
								.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

						if (Validator.isNotNull(previewResponse)) {
						}

						File file = new File(previewResponse);

						ResponseBuilder responseBuilder = Response.ok((Object) file);

						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + file.getName() + "\"");
						responseBuilder.header("Content-Type", "application/pdf");

						return responseBuilder.build();

					} catch (MessageBusException e) {
						_log.error(e);
						throw new Exception("Preview rendering not avariable");
					}

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}

	}

	@Override
	public Response printDossierDocument(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		BackendAuth auth = new BackendAuthImpl();
		long dossierId = GetterUtil.getLong(id);
		_log.info("START");

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierDocument> dossierDocList = DossierDocumentLocalServiceUtil.getDossierDocumentList(dossierId, 0, 5);
			DossierDocument dossierDoc = null;
			if (dossierDocList  != null && dossierDocList.size() > 0) {
				dossierDoc = dossierDocList.get(0);
				if (dossierDoc != null) {
					long docFileId = dossierDoc.getDocumentFileId();
					if (docFileId > 0) {
						FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(docFileId);

						File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(),
								fileEntry.getVersion(), true);

						ResponseBuilder responseBuilder = Response.ok((Object) file);
	
						responseBuilder.header("Content-Disposition",
								"attachment; filename=\"" + fileEntry.getFileName() + "\"");
						responseBuilder.header("Content-Type", fileEntry.getMimeType());
	
						return responseBuilder.build();
					}
				}
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	@Override
	public Response previewDossierDocumentList(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String typeCode, DossierDocumentInputModel input) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			String serviceCode = input.getServiceCode();
//			String govAgencyCode = input.getGovAgencyCode();
			String strDossiers = input.getDossiers();
			JSONArray  dossierIdArr = null;
			if (Validator.isNotNull(strDossiers)) {
				dossierIdArr = JSONFactoryUtil.createJSONArray(strDossiers);
			}

			_log.info("START");
			JSONObject formDataJSON = null;
			if (dossierIdArr != null && dossierIdArr.length() > 0) {
				//Declare Object
				JSONObject jsonDossier = null;
				JSONObject jsonData = null;
				Dossier dossier = null;
				long dossierId = 0;
				//
				int length = dossierIdArr.length();
				formDataJSON = JSONFactoryUtil.createJSONObject();
				formDataJSON.put(DossierTerm.TOTAL, length);
				//Get formScript
				DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, typeCode);
				String documentScript = StringPool.BLANK;
				_log.info("typeCode: "+typeCode);
				_log.info("docType: "+docType);
				if (docType != null) {
					documentScript = docType.getDocumentScript();
				}
				//
				JSONArray formDataArr = JSONFactoryUtil.createJSONArray();
				for (int i = 0; i < length; i++) {
					jsonDossier = dossierIdArr.getJSONObject(i);
					dossierId = Long.valueOf(jsonDossier.getString(DossierTerm.DOSSIER_ID));
					if (Validator.isNotNull(dossierId) ) {
						dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
						if (Validator.isNotNull(dossier)) {
							long dossierActionId = dossier.getDossierActionId();
							_log.info("dossierActionId: "+dossierActionId);
							String payload = StringPool.BLANK;
							if (dossierActionId != 0) {
								DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
								if (dAction != null) {
									payload = dAction.getPayload();
								}
								if (i == 0) {
									formDataJSON = processMergeDossierProcessRole(dossier, length, formDataJSON,  dAction);
								}
								//Mapping FormData with dossier
								jsonData = JSONFactoryUtil.createJSONObject(payload);
								jsonData = DossierDucumentUtils.processMergeDossierFormData(dossier, jsonData);
								formDataArr.put(jsonData);
								_log.info("jsonData: "+jsonData);
								_log.info("formDataArr: "+formDataArr);
								_log.info("payload: "+payload);
							}
						}
					}
				}
				formDataJSON.put(DossierTerm.MAPPING_DOSSIER, formDataArr);

				Message message = new Message();
				message.put("formReport", documentScript);
				message.put("formData", formDataJSON.toJSONString());
//				message.put("className", DossierDocument.class.getName());

				try {
					String previewResponse = (String) MessageBusUtil
							.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

					if (Validator.isNotNull(previewResponse)) {
					}

					File file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);

					responseBuilder.header("Content-Disposition",
							"attachment; filename=\"" + file.getName() + "\"");
					responseBuilder.header("Content-Type", "application/pdf");

					return responseBuilder.build();

				} catch (MessageBusException e) {
					_log.error(e);
					throw new Exception("Preview rendering not avariable");
				}
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}

		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	@Override
	public Response downloadDocByReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String referenceUid) {

//		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		Long dossierId = GetterUtil.getLong(id);

		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}

			DossierDocument dossierDoc = DossierDocumentLocalServiceUtil.getDocByReferenceUid(groupId, dossierId, referenceUid);
//			
//			// download file with dossierDocumentFileId
			long documentFileId = 0;
			if (dossierDoc != null) {
				documentFileId = dossierDoc.getDocumentFileId();
			}

			if (documentFileId > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(documentFileId);

				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
						true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition",
						"attachment; filename=\"" + fileEntry.getFileName() + "\"");
				responseBuilder.header("Content-Type", fileEntry.getMimeType());

				return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	//LamTV_ Mapping process dossier and formData
	private JSONObject processMergeDossierProcessRole(Dossier dossier, int length, JSONObject jsonData,
			DossierAction dAction) {
		//
		long groupId = dossier.getGroupId();
		if (dAction != null) {
			long serviceProcessId = dAction.getServiceProcessId();
			jsonData.put(DossierTerm.GOV_AGENCY_NAME, dossier.getGovAgencyName());
			jsonData.put(DossierTerm.TOTAL, length);
			jsonData.put(DossierTerm.ACTION_USER, dAction.getActionUser());
			String sequenceNo = dAction.getSequenceNo();
			if (Validator.isNotNull(sequenceNo)) {
				ProcessSequence sequence = ProcessSequenceLocalServiceUtil.findBySID_SNO(groupId, serviceProcessId,
						sequenceNo);
				if (sequence != null) {
					jsonData.put(DossierTerm.SEQUENCE_ROLE, sequence.getSequenceRole());
				} else {
					jsonData.put(DossierTerm.SEQUENCE_ROLE, StringPool.BLANK);
				}
			} else {
				jsonData.put(DossierTerm.SEQUENCE_ROLE, StringPool.BLANK);
			}
			// Process get Next sequence Role
			List<ProcessSequence> sequenceList = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId,
					serviceProcessId);
			String[] sequenceArr = null;
			if (sequenceList != null && !sequenceList.isEmpty()) {
				int lengthSeq = sequenceList.size();
				sequenceArr = new String[lengthSeq];
				for (int i = 0; i < lengthSeq; i++) {
					ProcessSequence processSequence = sequenceList.get(i);
					if (processSequence != null) {
						sequenceArr[i] = processSequence.getSequenceNo();
					}
				}
			}

			if (sequenceArr != null && sequenceArr.length > 0) {
				for (int i = 0; i < sequenceArr.length; i++) {
						_log.info("sequenceArr[i]: "+sequenceArr[i]);
				}
				Arrays.sort(sequenceArr);
				for (int i = 0; i < sequenceArr.length - 1; i++) {
					String seq = sequenceArr[i];
					if (sequenceNo.equals(seq)) {
						String nextSequenceNo = sequenceArr[i + 1];
						if (Validator.isNotNull(nextSequenceNo)) {
							ProcessSequence sequence = ProcessSequenceLocalServiceUtil.findBySID_SNO(groupId,
									serviceProcessId, nextSequenceNo);
							if (sequence != null) {
								jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, sequence.getSequenceRole());
							} else {
								jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, StringPool.BLANK);
							}
						} else {
							jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, StringPool.BLANK);
						}
					}
				}
			} else {
				jsonData.put(DossierTerm.NEXT_SEQUENCE_ROLE, StringPool.BLANK);
			}
		}

		return jsonData;
	}

}
