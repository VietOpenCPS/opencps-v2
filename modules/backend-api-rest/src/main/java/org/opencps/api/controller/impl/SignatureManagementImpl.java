package org.opencps.api.controller.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.security.cert.Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SignatureManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierSyncUtils;
import org.opencps.api.digitalsignature.model.DigitalSignatureInputModel;
import org.opencps.api.dossiersync.model.DossierSyncSendingModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class SignatureManagementImpl implements SignatureManagement{

	Log _log = LogFactoryUtil.getLog(SignatureManagementImpl.class.getName());

	@Override
	public Response updateDossierFileBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String sign = input.getSign();
			String signFieldName = input.getSignFieldName();
			String fileName = input.getFileName();

			callSignatureSync(groupId, user, id, sign, signFieldName, fileName, serviceContext);

			String fullPath = "";
			long fileEntryId = 0;
			JSONObject result = null;
//				result = DossierSyncUtils.mappingToSending(dossierSync);
			result = JSONFactoryUtil.createJSONObject();
			
//			long fileEntryId = msgData.getLong("fileEntryId");
//
//			long userId = msgData.getLong("userId");

//			boolean eSign = msgData.getBoolean("eSign");

//			long dossierFileId = msgData.getLong("dossierFileId");
			String msg = "success";
			File fileSigned = new File(fullPath.replace(".pdf", ".signed.pdf"));
			DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(fileEntryId);
			
//			ServiceContext serviceContext = new ServiceContext();

			DLAppLocalServiceUtil.updateFileEntry(user.getUserId(), dlFileEntry.getFileEntryId(), dlFileEntry.getTitle(),
					dlFileEntry.getMimeType(), dlFileEntry.getTitle(), dlFileEntry.getDescription(),
					StringPool.BLANK, false, fileSigned, serviceContext);


			return Response.status(200).entity(result).build();

		} catch (Exception e) {
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

	private void callSignatureSync(long groupId, User user, long id, String sign, String signFieldName, String fileName,
			ServiceContext serviceContext) throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

//		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

			// Call initDossier to SERVER

			String httpMethod = HttpMethods.POST;

			String endPoint = "dossiers/completeSignature";

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("sign", sign);
			params.put("signFieldName", signFieldName);
			params.put("fileName", fileName);
			params.put("user", user);

			JSONObject resPostDossier = rest.callPostAPI(groupId, httpMethod, "application/json",
					RESTFulConfiguration.SERVER_PATH_BASE, endPoint, RESTFulConfiguration.SERVER_USER,
					RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);


//		}

		// SyncAction

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

		// SyncDossierFile
//		if (method == 1) {
//
//			// TODO add case update file
//			String endPointSyncDossierFile = "dossiers/" + refId + "/files";
//
//			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFile(classPK);
//
//			properties.put("referenceUid", dossierFile.getReferenceUid());
//			properties.put("dossierTemplateNo", dossierFile.getDossierTemplateNo());
//			properties.put("dossierPartNo", dossierFile.getDossierPartNo());
//			properties.put("fileTemplateNo", dossierFile.getFileTemplateNo());
//			properties.put("displayName", dossierFile.getDisplayName());
//			properties.put("isSync", StringPool.FALSE);
//			properties.put("formData", dossierFile.getFormData());
//
//			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
//
//			properties.put("fileType", fileEntry.getExtension());
//
//			File file = getFile(dossierFile.getFileEntryId());
//
//			// TODO review extention file
//			JSONObject resSynFile = rest.callPostFileAPI(groupId, HttpMethods.POST, "application/json",
//					RESTFulConfiguration.SERVER_PATH_BASE, endPointSyncDossierFile, RESTFulConfiguration.SERVER_USER,
//					RESTFulConfiguration.SERVER_PASS, properties, file, serviceContext);
//
//			if (resSynFile.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
//				// remove DossierSync
//				DossierSyncLocalServiceUtil.deleteDossierSync(dossierSyncId);
//
//			} else {
//				_log.info(resSynFile.get(RESTFulConfiguration.MESSAGE));
//			}
//
//			// Reset isNew
//			dossierFile.setIsNew(false);
//
//			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
//
//		}

		// SyncPaymentFile and paymentfile status

		// Sync paymentFile
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


		// remove pending in DossierAction
//		int countDossierSync = DossierSyncLocalServiceUtil.countByDossierId(dossierId);
//
//		if (countDossierSync == 0 && clientDossierActionId > 0) {
//			DossierActionLocalServiceUtil.updatePending(clientDossierActionId, false);
//		}

	}

	@Override
	public Response getHashComputedBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input) {
		
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String strIdArr = input.getStrIdArr();
			_log.info("array Id: "+strIdArr);

			String[] idSplit = strIdArr.split(StringPool.SEMICOLON);
			_log.info("idSplit Id: "+idSplit);

			JSONObject hashComputed = null;
			for (String strId : idSplit) {
				String[] idArr = strId.split(StringPool.COMMA);
				DossierPart dossierPart = DossierPartLocalServiceUtil.fetchDossierPart(Long.valueOf(idArr[1]));
				_log.info("Dossier Part: "+dossierPart);
				DossierFile dossierFile = null;
				if (dossierPart != null && dossierPart.getESign()) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(idArr[0]));
					_log.info("Dossier File: "+dossierFile);
					if (dossierFile != null && dossierFile.getFileEntryId() > 0) {
						long fileEntryId = dossierFile.getFileEntryId();
						_log.info("fileEntryId: "+fileEntryId);

						hashComputed = callHashComputedSync(groupId, user, fileEntryId, serviceContext);
						_log.info("hashComputed: "+hashComputed);
						break;
					}
				}
			}

			String message = hashComputed.getString(RESTFulConfiguration.MESSAGE);
			_log.info("message: "+message);
			
//			JSONObject result = null;
//			result = JSONFactoryUtil.createJSONObject();

			return Response.status(200).entity(message).build();

		} catch (Exception e) {
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

	private JSONObject callHashComputedSync(long groupId, User user, long fileEntryId, ServiceContext serviceContext) throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		// Call initDossier to SERVER
		String httpMethod = HttpMethods.POST;

		String endPoint = "signature/requestsToken";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileEntryId", fileEntryId);
		params.put("emailUser", user.getEmailAddress());

		JSONObject resPostHashComputed = rest.callPostAPI(groupId, httpMethod, "application/json",
				RESTFulConfiguration.SERVER_PATH_BASE, endPoint, RESTFulConfiguration.SERVER_USER,
				RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);

		return resPostHashComputed;

	}

}
