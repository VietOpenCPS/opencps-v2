package org.opencps.api.controller.impl;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SignatureManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.digitalsignature.model.DigitalSignatureInputModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.scheduler.InvokeREST;
import org.opencps.dossiermgt.scheduler.RESTFulConfiguration;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

public class SignatureManagementImpl implements SignatureManagement{

	Log _log = LogFactoryUtil.getLog(SignatureManagementImpl.class.getName());

	@Override
	public Response updateDossierFileBySignature(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Long id, DigitalSignatureInputModel input) throws PortalException {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = Long.valueOf(id);

		if (!auth.isAuth(serviceContext)) {
			throw new UnauthenticationException();
		}

		String sign = input.getSign();
		String signFieldName = input.getSignFieldName();
		String fileName = input.getFileName();
		_log.info("sign: "+sign);
		_log.info("signFieldName: "+signFieldName);
		_log.info("fileName: "+fileName);
		String actionCode = input.getActionCode();
		String actionUser = input.getActionUser();
		String actionNote = input.getActionNote();
		long assignUserId = Long.valueOf(input.getAssignUserId());
		String subUsers = input.getSubUsers();
		_log.info("actionCode: "+actionCode);
		_log.info("actionUser: "+actionUser);
		_log.info("actionNote: "+actionNote);
		_log.info("assignUserId: "+assignUserId);
		_log.info("subUsers: "+subUsers);

		JSONObject signatureCompleted = callSignatureSync(groupId, user, id, sign, signFieldName, fileName, serviceContext);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		if (signatureCompleted.getInt(RESTFulConfiguration.STATUS) == HttpURLConnection.HTTP_OK) {
			long fileEntryId = Long.valueOf(input.getFileEntryId());
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

//			_log.info("user.getUserId(): "+user.getUserId());
//			_log.info("dlFileEntry.getFileEntryId(): "+dlFileEntry.getFileEntryId());
//			_log.info("dlFileEntry.getTitle(): "+dlFileEntry.getTitle());
//			_log.info("dlFileEntry.getMimeType(): "+dlFileEntry.getMimeType());
//			_log.info("dlFileEntry.getTitle(): "+dlFileEntry.getTitle());
//			_log.info("dlFileEntry.getDescription(): "+dlFileEntry.getDescription());
//			serviceContext = new ServiceContext();
//			serviceContext.setAddGroupPermissions(true);
//			serviceContext.setAddGuestPermissions(true);
			DLAppLocalServiceUtil.updateFileEntry(user.getUserId(), dlFileEntry.getFileEntryId(), dlFileEntry.getTitle(),
					dlFileEntry.getMimeType(), dlFileEntry.getTitle(), dlFileEntry.getDescription(),
					StringPool.BLANK, true, fileSigned, serviceContext);
			
			//Next action
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			if (dossier != null) {
				_log.info("dossierId: "+dossier.getDossierId());
				_log.info("ReferenceId: "+dossier.getReferenceUid());
				DossierActions dossierAction = new DossierActionsImpl();
				dossierAction.doAction(groupId, dossierId, dossier.getReferenceUid(), actionCode,
						0l, actionUser, actionNote, assignUserId, user.getUserId(), subUsers,
						serviceContext);
				// Process success
				result.put("msg", "success");
			}

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
		
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String strIdArr = input.getStrIdArr();
//			_log.info("array Id: "+strIdArr);

			String[] idSplit = strIdArr.split(StringPool.SEMICOLON);
//			_log.info("idSplit Id: "+idSplit);

			JSONObject hashComputed = null;
			for (String strId : idSplit) {
				String[] idArr = strId.split(StringPool.COMMA);
				DossierPart dossierPart = DossierPartLocalServiceUtil.fetchDossierPart(Long.valueOf(idArr[1]));
//				_log.info("Dossier Part: "+dossierPart);
				DossierFile dossierFile = null;
				if (dossierPart != null && dossierPart.getESign()) {
					dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(idArr[0]));
//					_log.info("Dossier File: "+dossierFile);
					if (dossierFile != null && dossierFile.getFileEntryId() > 0) {
						long fileEntryId = dossierFile.getFileEntryId();
						_log.info("fileEntryId: "+fileEntryId);

						hashComputed = callHashComputedSync(groupId, user, fileEntryId, input.getActionCode(), serviceContext);
						_log.info("hashComputed: "+hashComputed);
						break;
					}
				}
			}

			JSONObject results = JSONFactoryUtil.createJSONObject(hashComputed.getString(RESTFulConfiguration.MESSAGE));
			_log.info("results: "+results);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

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

	private JSONObject callHashComputedSync(long groupId, User user, long fileEntryId, String actionCode,
			ServiceContext serviceContext) throws PortalException {

		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();

		// Call initDossier to SERVER
		String httpMethod = HttpMethods.POST;

		String endPoint = "signature/requestsToken";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileEntryId", fileEntryId);
		params.put("emailUser", user.getEmailAddress());
		params.put("typeSignature", Integer.valueOf(actionCode));

		JSONObject resPostHashComputed = rest.callPostAPI(groupId, httpMethod, "application/json",
				RESTFulConfiguration.SERVER_PATH_BASE, endPoint, RESTFulConfiguration.SERVER_USER,
				RESTFulConfiguration.SERVER_PASS, properties, params, serviceContext);

		return resPostHashComputed;

	}

}
