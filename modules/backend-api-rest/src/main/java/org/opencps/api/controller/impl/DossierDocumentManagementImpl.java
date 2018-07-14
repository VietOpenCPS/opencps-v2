package org.opencps.api.controller.impl;

import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierDocumentManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.dossierdocument.model.DossierDocumentInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierDocument;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierDocumentLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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

					DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
					String payload = StringPool.BLANK;
					if (dAction != null) {
						payload = dAction.getPayload();
					}
					JSONObject jsonData = null;
					if (Validator.isNotNull(payload)) {
						jsonData = JSONFactoryUtil.createJSONObject(payload);
						jsonData = processMergeDossierFormData(dossier, jsonData);
					}

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
						throw new Exception("Preview rendering not avariable");
					}

				} else {
					throw new Exception("The dossier wasn't on process");
				}

			} else {
				throw new Exception("Cant get dossier with id_" + id);
			}

		} catch (Exception e) {
			_log.error(e);
			return processException(e);

		}

	}

	@Override
	public Response printDossierDocument(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();
		long dossierId = GetterUtil.getLong(id);

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
			return processException(e);
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

			String serviceCode = input.getServiceCode();
			String govAgencyCode = input.getGovAgencyCode();
			String strDossiers = input.getDossiers();
			JSONArray  dossierIdArr = null;
			if (Validator.isNotNull(strDossiers)) {
				dossierIdArr = JSONFactoryUtil.createJSONArray(strDossiers);
			}

			_log.info("START");
			JSONArray formDataArr = null;
			JSONArray formReportArr = null;
			if (dossierIdArr != null && dossierIdArr.length() > 0) {
				int length = dossierIdArr.length();
				JSONObject jsonDossier = null;
				Dossier dossier = null;
				long dossierId = 0;
				formDataArr = JSONFactoryUtil.createJSONArray();
				DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, typeCode);
				String documentScript = StringPool.BLANK;
				_log.info("typeCode: "+typeCode);
				_log.info("docType: "+docType);
				if (docType != null) {
					documentScript = docType.getDocumentScript();
				}
				for (int i = 0; i < length; i++) {
					jsonDossier = dossierIdArr.getJSONObject(i);
					dossierId = Long.valueOf(jsonDossier.getString(DossierTerm.DOSSIER_ID));
					if (Validator.isNotNull(dossierId) ) {
						dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
						if (Validator.isNotNull(dossier)) {
							long dossierActionId = dossier.getDossierActionId();
							_log.info("formReportArr: "+formReportArr);
							String payload = StringPool.BLANK;
							if (dossierActionId != 0) {
								DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
								if (dAction != null) {
									payload = dAction.getPayload();
								}
							}
							JSONObject jsonData = null;
							jsonData = JSONFactoryUtil.createJSONObject(payload);
							jsonData = processMergeDossierFormData(dossier, jsonData);
							formDataArr.put(jsonData);
							_log.info("jsonData: "+jsonData);
							_log.info("formDataArr: "+formDataArr);
							_log.info("payload: "+payload);
						}
					}
				}

				Message message = new Message();
				message.put("formReport", documentScript);
				message.put("formData", formDataArr.toJSONString());
				message.put("className", DossierDocument.class.getName());

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
					throw new Exception("Preview rendering not avariable");
				}
			}

		} catch (Exception e) {
			_log.error(e);
			return processException(e);

		}

		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	private Response processException(Exception e) {
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

	//LamTV_ Mapping process dossier and formData
	private JSONObject processMergeDossierFormData(Dossier dossier, JSONObject jsonData) {
		jsonData.put(DossierTerm.APPLICANT_NAME, dossier.getApplicantName());
		jsonData.put(DossierTerm.APPLICANT_ID_NO, dossier.getApplicantIdNo());
		jsonData.put(DossierTerm.APPLICANT_ID_TYPE, dossier.getApplicantIdType());
		jsonData.put(DossierTerm.APPLICANT_ID_DATE, dossier.getApplicantIdDate());
		jsonData.put(DossierTerm.ADDRESS, dossier.getAddress());
		jsonData.put(DossierTerm.CITY_CODE, dossier.getCityCode());
		jsonData.put(DossierTerm.CITY_NAME, dossier.getCityName());
		jsonData.put(DossierTerm.DISTRICT_CODE, dossier.getDistrictCode());
		jsonData.put(DossierTerm.DISTRICT_NAME, dossier.getDistrictName());
		jsonData.put(DossierTerm.WARD_CODE, dossier.getWardCode());
		jsonData.put(DossierTerm.WARD_NAME, dossier.getWardName());
		jsonData.put(DossierTerm.CONTACT_NAME, dossier.getContactName());
		jsonData.put(DossierTerm.CONTACT_TEL_NO, dossier.getContactTelNo());
		jsonData.put(DossierTerm.CONTACT_EMAIL, dossier.getContactEmail());

		return jsonData;
	}

}
