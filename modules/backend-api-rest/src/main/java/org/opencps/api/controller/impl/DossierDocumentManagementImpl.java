package org.opencps.api.controller.impl;

import java.io.File;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.DossierDocumentManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
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
					

					// String formReport = plugin.getPluginForm();
//					String formCode = plugin.getSampleData();

//					String pluginForm = plugin.getPluginForm();

//					String[] splipPluginForms = StringUtil.split(pluginForm, StringPool.AT);

//					boolean original = false;

//					if (splipPluginForms.length == 3 && splipPluginForms[2].contentEquals("original")) {
//						original = true;
//					}

//					boolean autoRun = plugin.getAutoRun();

//					String formData = StringPool.BLANK;
//					String formReport = StringPool.BLANK;

//					if (formCode.startsWith("#")) {
//						formData = _getFormData(groupId, formCode, dossier.getDossierId(), autoRun,
//								dossier.getDossierTemplateNo(), original, serviceContext);
//
//						formReport = _getFormScript(formCode, dossier.getDossierId());
//					}

					//_log.info("Form data to preview: " + formData);
					Message message = new Message();

					message.put("formReport", documentScript);
//
					message.put("formData", jsonData.toJSONString());

//					message.setResponseId(String.valueOf(dossier.getPrimaryKeyObj()));
//					message.setResponseDestinationName("jasper/engine/preview/callback");

					try {
						String previewResponse = (String) MessageBusUtil
								.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

						// JSONObject jsonObject =
						// JSONFactoryUtil.createJSONObject();

						if (Validator.isNotNull(previewResponse)) {
							// jsonObject =
							// JSONFactoryUtil.createJSONObject(previewResponse);
						}

						// String fileDes = jsonObject.getString("fileDes");

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
	public Response getPreviewHtml(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, long pluginid) {
		// TODO Auto-generated method stub
		return null;
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
