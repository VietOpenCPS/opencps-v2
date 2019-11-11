
package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DossierFileManagement;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.ImportZipFileUtils;
import org.opencps.api.controller.util.ReadXMLFileUtils;
import org.opencps.api.dossierfile.model.DossierFileCopyInputModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.api.dossierfile.model.DossierFileResultsModel;
import org.opencps.api.dossierfile.model.DossierFileSearchModel;
import org.opencps.api.dossierfile.model.DossierFileSearchResultsModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.util.CheckFileUtils;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierFileManagementImpl implements DossierFileManagement {

	private static final Log _log =
		LogFactoryUtil.getLog(DossierFileManagementImpl.class);

	@Override
	public Response getDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DossierFileResultsModel results = new DossierFileResultsModel();

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = null;

			try {
				long dossierId = GetterUtil.getLong(id);
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			} catch (NumberFormatException e) {
				_log.debug(e);
			}
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
			List<DossierFile> fileResults = new ArrayList<>();

			if (dossier != null && dossier.getOriginDossierId() == 0) {
				List<DossierFile> dossierFiles = DossierFileLocalServiceUtil
						.getDossierFilesByDossierId(dossier.getDossierId());
				if (dossierFiles != null && dossierFiles.size() > 0) {
					fileResults.addAll(dossierFiles);
					results.setTotal(dossierFiles.size());
					results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));
				} else {
					results.setTotal(0);
				}
			} else if (dossier != null && dossier.getOriginDossierId() != 0) {
				List<DossierFile> dossierFiles = DossierFileLocalServiceUtil
						.getDossierFilesByDossierId(dossier.getOriginDossierId());
				if (dossierFiles != null && dossierFiles.size() > 0) {
					fileResults.addAll(dossierFiles);

					results.setTotal(dossierFiles.size());
					results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));
				} else {
					results.setTotal(0);
				}
			}
			return Response.status(200).entity(results).build();

		}
		catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String displayName, String fileType,
			String isSync, String formData, String removed, String eForm, Long modifiedDate) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		_log.debug("In dossier file create");
		try {
			
			boolean flagCheck = CheckFileUtils.checkFileUpload(file);
			
			if (!flagCheck) {
				return Response.status(HttpStatus.SC_FORBIDDEN)
						.entity(ReadFilePropertiesUtils.get(ConstantUtils.ATTACHMENT_ERROR)).build();
			}
			if (modifiedDate == null) {
				modifiedDate = (new Date()).getTime();
			}
			DossierFile dossierFile =
				CPSDossierBusinessLocalServiceUtil.addDossierFileByDossierId(
					groupId, company, user, serviceContext, file, id,
					referenceUid, dossierTemplateNo, dossierPartNo,
					fileTemplateNo, displayName, fileType, isSync, formData,
					removed, eForm, modifiedDate);
			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			_log.debug("__End bind to dossierFile" + new Date());

			return Response.status(HttpStatus.SC_OK).entity(result).build();
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cloneDossierFile(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		DossierFileCopyInputModel input) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.cloneDossierFile(
				groupId, id, input.getDossierFileId(),
				input.getDossierTemplateNo(), input.getDossierPartNo(),
				serviceContext);

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response downloadByDossierId_ReferenceUid(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id,
		String referenceUid, String password) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			//
			long dossierId = GetterUtil.getLong(id);
			if (dossierId == 0) {
				Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				if (dossier != null) {
					dossierId = dossier.getDossierId();
				}
			}

			DossierFile dossierFile =
				DossierFileLocalServiceUtil.getDossierFileByReferenceUid(
						dossierId, referenceUid);

			// download file with dossierFileID
			if (Validator.isNull(dossierFile) &&
				Validator.isNumber(referenceUid)) {
				dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
					Long.valueOf(referenceUid));
			}

			if (dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
					dossierFile.getFileEntryId());

				File file = DLFileEntryLocalServiceUtil.getFile(
					fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header(
					ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
					ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + fileEntry.getFileName() + "\"");
				responseBuilder.header(ConstantUtils.CONTENT_TYPE, fileEntry.getMimeType());

				return responseBuilder.build();
			}
			else {
				return Response.status(
					HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDossierFile(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid, Attachment file) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			boolean flagCheck = CheckFileUtils.checkFileUpload(file);
			
			if (!flagCheck) {
				return Response.status(HttpStatus.SC_FORBIDDEN)
						.entity(ReadFilePropertiesUtils.get(ConstantUtils.ATTACHMENT_ERROR)).build();
			}

			DossierFile dossierFile =
				CPSDossierBusinessLocalServiceUtil.updateDossierFile(
					groupId, company, serviceContext, groupId, referenceUid,
					file);

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormDataByDossierId_ReferenceUid(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			if (dossier != null) {
				if (dossier.getOriginDossierId() != 0) {
					dossier = DossierLocalServiceUtil.fetchDossier(
						dossier.getOriginDossierId());
					id = dossier.getOriginDossierId();
				}
			}
			DossierFile dossierFile =
				DossierFileLocalServiceUtil.getDossierFileByReferenceUid(
					id, referenceUid);

			return Response.status(HttpStatus.SC_OK).entity(
				dossierFile.getFormData()).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormScriptByDossierId_ReferenceUid(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile =
				DossierFileLocalServiceUtil.getDossierFileByReferenceUid(
					id, referenceUid);

			return Response.status(HttpStatus.SC_OK).entity(
				dossierFile.getFormScript()).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDossierFileFormData(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid, String formdata) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			DossierFile dossierFile =
				CPSDossierBusinessLocalServiceUtil.updateDossierFileFormData(
					groupId, company, serviceContext, id, referenceUid,
					formdata);

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDossierFile(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.deleteDossierFile(
				groupId, id, referenceUid, serviceContext);

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierFiles(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		DossierFileSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileSearchResultsModel results =
				new DossierFileSearchResultsModel();

			DossierFileActions action = new DossierFileActionsImpl();

			JSONObject dossierFileJsonObject = action.getDossierFiles(
				groupId, query.getKeyword(), query.getTemplate(),
				query.getType(), query.isOwner(), query.isOriginal(),
				query.getStart(), query.getEnd(), query.getSort(),
				query.getOrder(), serviceContext);

			List<Document> documents =
				(List<Document>) dossierFileJsonObject.get(ConstantUtils.DATA);

			results.setTotal(dossierFileJsonObject.getInt(ConstantUtils.TOTAL));

			results.getData().addAll(
				DossierFileUtils.mappingToDossierFileSearchResultsModel(
					documents));

			return Response.status(200).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response downloadByDossierId(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String password) {

		String pathName = StringPool.BLANK;
		String realPath = StringPool.BLANK;
		BackendAuth auth = new BackendAuthImpl();
		DossierFileActions action = new DossierFileActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			int partType = 2;
			List<DossierFile> dossierFiles =
				DossierFileLocalServiceUtil.getDossierFilesByD_DP(id, partType);

			if (dossierFiles != null && dossierFiles.size() > 0) {
				if (dossierFiles.size() > 0) {
					if (dossierFiles.get(0).getFileEntryId() > 0) {
						FileEntry fileEntry =
							DLAppLocalServiceUtil.getFileEntry(
								dossierFiles.get(0).getFileEntryId());

						File file = DLFileEntryLocalServiceUtil.getFile(
							fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
						realPath = file.getPath();
						pathName = file.getPath() + StringPool.UNDERLINE + String.valueOf(id);
					}
				}
				// int index = realPath.lastIndexOf("\\");
				int index = realPath.lastIndexOf(StringPool.FORWARD_SLASH);
				File d = null;
				if (index > 0) {
					d = new File(pathName.substring(0, index));
				}
				if (d != null) {
					for (File f : d.listFiles()) {
						if (ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP)
								.equals(f.getName().substring(f.getName().lastIndexOf(StringPool.PERIOD) + 1))) {
							f.delete();
						}
						if (f.isDirectory()) {
							f.delete();
						}

					}
				}

				for (DossierFile dossierFile : dossierFiles) {
					if (dossierFile.getFileEntryId() > 0) {
						FileEntry fileEntry =
							DLAppLocalServiceUtil.getFileEntry(
								dossierFile.getFileEntryId());

						File file = DLFileEntryLocalServiceUtil.getFile(
							fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
						// String fileName = pathName + "\\" +
						// fileEntry.getFileName();
						String fileName =
							pathName + StringPool.FORWARD_SLASH + fileEntry.getFileName();
						File dir = new File(pathName);
						if (!dir.exists()) {
							dir.mkdirs();
						}
						action.copyFile(file.getPath(), fileName);
					}
				}

				File dirName = new File(pathName);
				action.zipDirectory(dirName,
						pathName.substring(0, index) + StringPool.FORWARD_SLASH
								+ pathName.substring(index + 1, pathName.length()) + StringPool.PERIOD
								+ ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP));

				File fi = new File(pathName.substring(0, index) + StringPool.FORWARD_SLASH
						+ pathName.substring(index + 1, pathName.length()) + StringPool.PERIOD
						+ ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP));

				ResponseBuilder responseBuilder = Response.ok(fi);
				responseBuilder.header(
					ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
					ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + fi.getName() + StringPool.QUOTE);
				responseBuilder.header(ConstantUtils.CONTENT_TYPE, ReadFilePropertiesUtils.get(ConstantUtils.MIME_TYPE_ZIP));

				return responseBuilder.build();
			}
			else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT)
						.entity(ReadFilePropertiesUtils.get(ConstantUtils.NO_CONTENT)).build();
			}
		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response resetformdataDossierFileFormData(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid, String formdata) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			DossierFile dossierFile = CPSDossierBusinessLocalServiceUtil.resetformdataDossierFileFormData(groupId,
					company, serviceContext, id, referenceUid, formdata);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(HttpStatus.SC_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeAllDossierFileFormData(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String fileTemplateNo) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			// Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);

			DossierFileActions action = new DossierFileActionsImpl();

			action.deleteAllDossierFile(
				groupId, id, fileTemplateNo, serviceContext);

			// DossierFileModel result =
			// DossierFileUtils.mappingToDossierFileModel(dossierFile);

			JSONObject result = JSONFactoryUtil.createJSONObject();

			result.put(ReadFilePropertiesUtils.get(ConstantUtils.MSG_STATUS), ReadFilePropertiesUtils.get(ConstantUtils.MSG_SUCCESS));

			return Response.status(200).entity(
				JSONFactoryUtil.serialize(result)).build();

		}
		catch (Exception e) {
			_log.debug("DOSSIER_LOG_" + e);

			JSONObject result = JSONFactoryUtil.createJSONObject();
			result.put(ReadFilePropertiesUtils.get(ConstantUtils.MSG_STATUS), ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));
			result.put(ReadFilePropertiesUtils.get(ConstantUtils.MESSAGE_MSG), ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));

			return Response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).entity(
				JSONFactoryUtil.serialize(result)).build();
		}
	}

	@Override
	public Response getDossierFileByDeliverableCode(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String deliverableCode) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		//_log.debug("********START *********");
		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions actions = new DossierFileActionsImpl();

			DossierFile dossierFile = null;
			if (Validator.isNotNull(deliverableCode)) {
				dossierFile = actions.getDossierFileByDeliverableCode(groupId, deliverableCode);
			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			if (dossierFile != null) {
				results.put("dossierId", dossierFile.getDossierId());
				results.put("referenceUid", dossierFile.getReferenceUid());
			}

			_log.debug("********END *********");
			return Response.status(HttpStatus.SC_OK).entity(
				JSONFactoryUtil.looseSerialize(results)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getAllDossierFilesByDossierId(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, String id) {

		DossierFileResultsModel results = new DossierFileResultsModel();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long dossierId = GetterUtil.getLong(id);

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = null;
			if (dossierId > 0) {
				dossierFiles =
					DossierFileLocalServiceUtil.getAllDossierFile(dossierId);
			}
			else {
				Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				if (dossier != null) {
					dossierFiles =
						DossierFileLocalServiceUtil.getAllDossierFile(
							dossier.getDossierId());
				}
			}

			if (dossierFiles != null) {
				results.setTotal(dossierFiles.size());
				results.getData().addAll(
					DossierFileUtils.mappingToDossierFileData(dossierFiles));
			}
			else {
				results.setTotal(0);
			}

			return Response.status(200).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierFileByDossierId_FileTemplateNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String fileTemplateNo) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile =
				action.getDossierFileByFileTemplateNo(id, fileTemplateNo);

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(HttpStatus.SC_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response downloadByPublicUser(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String referenceUid, String password) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			boolean isAuthenticated = false;
			if (dossier.getPassword() != null &&
				dossier.getPassword().equals(password)) {
				isAuthenticated = true;
			}
			if (!auth.isAuth(serviceContext) && !isAuthenticated) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile =
				DossierFileLocalServiceUtil.getDossierFileByReferenceUid(
					id, referenceUid);

			// download file with dossierFileID
			if (Validator.isNull(dossierFile) &&
				Validator.isNumber(referenceUid)) {
				dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(
					Long.valueOf(referenceUid));
			}

			if (dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
					dossierFile.getFileEntryId());

				File file = DLFileEntryLocalServiceUtil.getFile(
					fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header(
					ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
					ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + fileEntry.getFileName() + StringPool.QUOTE);
				responseBuilder.header(ConstantUtils.CONTENT_TYPE, fileEntry.getMimeType());

				return responseBuilder.build();
			}
			else {
				return Response.status(
					HttpURLConnection.HTTP_NO_CONTENT).build();
			}

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response uploadFileEntry(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		Attachment file) {

		//_log.info("uploadFileEntry");
		System.out.println("uploadFileEntry");
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 =
			new backend.auth.api.BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		InputStream fileInputStream = null;

		try {
			DataHandler dataHandle = file.getDataHandler();

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, ReadFilePropertiesUtils.get(ConstantUtils.USER_ADMIN))) {
				return Response.status(
					HttpURLConnection.HTTP_UNAUTHORIZED).entity(
						"User not permission process!").build();
			}

			String result = StringPool.BLANK;
			//
			List<Group> groupList = GroupLocalServiceUtil.getActiveGroups(
				company.getCompanyId(), true);
			String strGroupId = StringPool.BLANK;
			if (groupList != null && groupList.size() > 0) {
				List<String> groupIdList = new ArrayList<>();
				for (Group group : groupList) {
					if (group.isSite()) {
						groupIdList.add(String.valueOf(group.getGroupId()));
					}
				}
				if (groupIdList != null && groupIdList.size() > 0) {
					strGroupId = String.join(StringPool.COMMA, groupIdList);
				}
			}
			// Check group
			if (!strGroupId.contains(String.valueOf(groupId))) {
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
						.entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_GROUP)).build();
			}

			// Process FILE
			boolean flagCheck = CheckFileUtils.checkFileUpload(file);
			
			if (!flagCheck) {
				return Response.status(HttpStatus.SC_FORBIDDEN)
						.entity(ReadFilePropertiesUtils.get(ConstantUtils.ATTACHMENT_ERROR)).build();
			}

			fileInputStream = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			String extFile = ImportZipFileUtils.getExtendFileName(fileName);
			_log.info("extFile: " + extFile);
			if (Validator.isNotNull(extFile)) {
				if (ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP).equals(extFile.toLowerCase())
						|| ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XML).equals(extFile.toLowerCase())) {
					if (ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_ZIP).equals(extFile.toLowerCase())) {
						String pathFolder = ImportZipFileUtils.getFolderPath(
							fileName, ConstantUtils.DEST_DIRECTORY);
						// //delete folder if exits
						File fileOld = new File(pathFolder);
						_log.info("fileOld: " + fileOld);
						if (fileOld.exists()) {
							boolean flag =
								ReadXMLFileUtils.deleteFilesForParentFolder(
									fileOld);
							_log.info("LamTV_Delete DONE: " + flag);
						}
						// _log.info("LamTV_pathFolder: "+pathFolder);
						ImportZipFileUtils.unzip(
							fileInputStream, ConstantUtils.DEST_DIRECTORY);
						File fileList = new File(pathFolder);
						// //Validate xml
						String strError =
							ReadXMLFileUtils.validateXML(fileList, true);
						_log.info("strError: " + strError);
						if (Validator.isNotNull(strError)) {
							return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(strError).build();
						}

						result = ReadXMLFileUtils.listFilesForParentFolder(
							fileList, groupId, userId, serviceContext);
						if (Validator.isNull(result)) {
							return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR)
									.entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_FOLDER)).build();
						}
						_log.info("LamTV_IMPORT DONE_ZIP");
					}
					else if (ReadFilePropertiesUtils.get(ConstantUtils.EXTENTION_XML).equals(extFile.toLowerCase())) {
						String pathFile = ConstantUtils.DEST_DIRECTORY +
							StringPool.SLASH + fileName;
						// //delete folder if exits
						File fileOld = new File(pathFile);
						_log.info("fileOld: " + fileOld.getAbsolutePath());
						if (fileOld.exists()) {
							boolean flag =
								ReadXMLFileUtils.deleteFilesForParentFolder(
									fileOld);
							_log.info("LamTV_Delete DONE: " + flag);
						}
						_log.info("LamTV_pathFolder: " + pathFile);
						File fileList = new File(pathFile);
						FileOutputStream out = new FileOutputStream(fileList);
						IOUtils.copy(fileInputStream, out);
						// FileUtils.copyInputStreamToFile(fileInputStream,
						// fileList);
						_log.info("fileList: " + fileList);
						// _log.info("LamTV_fileList: "+fileList.getPath());
						String subFileName =
							ImportZipFileUtils.getSubFileName(fileName);
						if (Validator.isNotNull(subFileName)) {
							String strError =
								ReadXMLFileUtils.validateXML(fileList, false);
							if (Validator.isNotNull(strError)) {
								return Response.status(
									HttpURLConnection.HTTP_INTERNAL_ERROR).entity(
										strError).build();
							}
							String xmlString =
								ReadXMLFileUtils.convertFiletoString(fileList);
							result = ReadXMLFileUtils.compareParentFile(
								ConstantUtils.DEST_DIRECTORY, fileName, xmlString,
								groupId, userId, serviceContext);
						}
						_log.info("LamTV_IMPORT DONE_FILE");
					}
					return Response.status(HttpStatus.SC_OK).entity(result).build();
				}
			}
			return Response.status(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)
					.entity(ReadFilePropertiesUtils.get(ConstantUtils.NO_CONTENT)).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getAlreadyHasFileTemplateNo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		String applicantIdNo, String fileTemplateNo) {

		DossierFileResultsModel results = new DossierFileResultsModel();

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long startTime = System.currentTimeMillis();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			if (dossier != null) {
				List<Dossier> lstDossiers = null;
				if (!OpenCPSConfigUtil.isDLFileEntryEnable()) {
					lstDossiers = DossierLocalServiceUtil.getByF_GID_AN_DS(groupId, applicantIdNo,
							DossierTerm.DOSSIER_STATUS_DONE);
				} else {
					lstDossiers = DossierLocalServiceUtil.getByG_AN(groupId, applicantIdNo);
				}
				
				List<DossierFile> resultFiles = new ArrayList<>();
				if (lstDossiers != null && lstDossiers.size() > 0) {
					long[] dossierIds = new long[lstDossiers.size()];
					int i = 0;
					for (Dossier d : lstDossiers) {
						dossierIds[i++] = d.getDossierId();
					}

					String[] ftns = StringUtil.split(fileTemplateNo);
					_log.debug("DOSSIER TEMPLATE NO: " + dossierIds.length);
					for (String ftn : ftns) {
						startTime = System.currentTimeMillis();
						List<DossierFile> dossierFiles =
							DossierFileLocalServiceUtil.getByG_DID_FTN_R_O(
								groupId, dossierIds, ftn, false, true);
						_log.debug("END TIME: " + (System.currentTimeMillis() - startTime) + " ms");
						resultFiles.addAll(dossierFiles);
					}
				}
				results.setTotal(resultFiles.size());
				results.getData().addAll(
					DossierFileUtils.mappingToDossierFileData(resultFiles));
			}

			return Response.status(HttpStatus.SC_OK).entity(results).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response useOriginalDossierFile(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext, long id,
		DossierFileCopyInputModel input) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.cloneDossierFile(
				groupId, id, input.getDossierFileId(),
				input.getDossierTemplateNo(), input.getDossierPartNo(),
				serviceContext);

			DossierFileModel result =
				DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(HttpStatus.SC_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFileDoneOfApplicant(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String applicantIdNo, DossierFileSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			DossierFileResultsModel results = new DossierFileResultsModel();
			List<Dossier> lstDossiers =
				DossierLocalServiceUtil.getByF_GID_AN_DS(
					groupId, applicantIdNo, DossierTerm.DOSSIER_STATUS_DONE);

			int countDossierFile = 0;
			if (lstDossiers != null && lstDossiers.size() > 0) {
				long[] dossierIds = new long[lstDossiers.size()];
				int i = 0;
				for (Dossier d : lstDossiers) {
					dossierIds[i++] = d.getDossierId();
				}

				countDossierFile =
					DossierFileLocalServiceUtil.countByF_GID_DID_R_O(
						groupId, dossierIds, false, true);
				if (countDossierFile > 0) {
					results.setTotal(countDossierFile);
					List<DossierFile> dossierFileList =
						DossierFileLocalServiceUtil.getByF_GID_DID_R_O(
							groupId, dossierIds, false, true, query.getStart(),
							query.getEnd());
					results.getData().addAll(
						DossierFileUtils.mappingToDossierFileData(
							dossierFileList));
				}
			}

			return Response.status(HttpStatus.SC_OK).entity(results).build();

		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDossierOfProfileApplicant(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long dossierFileId) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileModel results = null;
			DossierFile dossierFile =
				DossierFileLocalServiceUtil.fetchDossierFile(dossierFileId);
			if (dossierFile != null) {
				dossierFile.setOriginal(false);
				dossierFile =
					DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
			}

			if (dossierFile != null) {
				results =
					DossierFileUtils.mappingToDossierFileModel(dossierFile);
			}

			return Response.status(HttpStatus.SC_OK).entity(results).build();

		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addDossierFiles(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String rootDossierFileIds, String dossierIds) {

		try {

			BackendAuth auth = new BackendAuthImpl();

			if (!auth.isAuth(serviceContext)) {

				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();
			List<DossierFile> dossierFiles = action.addDossierFile(
				rootDossierFileIds.split(StringPool.COMMA),
				dossierIds.split(StringPool.COMMA),
				GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID)),
				company.getCompanyId(), user.getUserId(), user.getFullName(),
				serviceContext);

			DossierFileResultsModel results = new DossierFileResultsModel();

			results.setTotal(dossierFiles.size());
			results.getData().addAll(
				DossierFileUtils.mappingToDossierFileData(dossierFiles));

			return Response.status(HttpStatus.SC_OK).entity(results).build();
		}
		catch (Exception e) {

			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cleanDossierFile(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		long dossierFileId) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {

				throw new UnauthenticationException();
			}

			DossierFileModel results = null;
			DossierFile dossierFile =
				DossierFileLocalServiceUtil.permanentDeleteDossierFile(
					dossierFileId);

			if (dossierFile != null) {

				results =
					DossierFileUtils.mappingToDossierFileModel(dossierFile);
			}

			return Response.status(HttpStatus.SC_OK).entity(results).build();
		}
		catch (Exception e) {

			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

}
