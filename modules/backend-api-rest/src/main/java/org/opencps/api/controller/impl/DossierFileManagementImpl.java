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

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
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
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.CPSDossierBusinessLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierFileManagementImpl implements DossierFileManagement {

	private static final Log _log = LogFactoryUtil.getLog(DossierFileManagementImpl.class);

	@Override
	public Response getDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String password) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
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
			}
			catch (NumberFormatException e) {
				_log.debug(e);
			}
			if (dossier == null) {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}
//			Dossier groupDossier = null;
//			if (dossier != null && dossier.getGroupDossierId() != 0) {
//				groupDossier = DossierLocalServiceUtil.fetchDossier(dossier.getGroupDossierId());
//			}
			List<DossierFile> fileResults = new ArrayList<>();
			
			if (dossier != null && dossier.getOriginDossierId() == 0) {
				List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.getDossierId());
//				if (groupDossier != null) {
//					List<DossierFile> groupFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.getGroupDossierId());
//					if (groupFiles != null)
//						fileResults.addAll(groupFiles);
//				}
				if (dossierFiles != null && dossierFiles.size() > 0) {
					fileResults.addAll(dossierFiles);
					results.setTotal(dossierFiles.size());
					results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));
				} else {
					results.setTotal(0);
				}
			}
			else if (dossier != null && dossier.getOriginDossierId() != 0) {
				List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.getOriginDossierId());
//				if (groupDossier != null) {
//					List<DossierFile> groupFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.getGroupDossierId());
//					if (groupFiles != null)
//						fileResults.addAll(groupFiles);
//				}
				if (dossierFiles != null && dossierFiles.size() > 0) {
					fileResults.addAll(dossierFiles);

					results.setTotal(dossierFiles.size());
					results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));
				} else {
					results.setTotal(0);
				}
			}
			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.debug(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String displayName, String fileType,
			String isSync, String formData, String removed, String eForm, Long modifiedDate) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.debug("In dossier file create");
		try {
			DossierFile dossierFile = CPSDossierBusinessLocalServiceUtil.addDossierFileByDossierId(groupId, company, user, serviceContext, file, id, referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, fileType, isSync, formData, removed, eForm, modifiedDate);
			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
	
			_log.debug("__End bind to dossierFile" + new Date());

			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cloneDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DossierFileCopyInputModel input) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();
		
			DossierFile dossierFile = action.cloneDossierFile(groupId, id, input.getDossierFileId(),
					input.getDossierTemplateNo(), input.getDossierPartNo(), serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response downloadByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String password) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);
			
			// download file with dossierFileID
			if (Validator.isNull(dossierFile) && Validator.isNumber(referenceUid)) {
				dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(referenceUid));
			}

			if (dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());

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

	@Override
	public Response updateDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid, Attachment file) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			DossierFile dossierFile = CPSDossierBusinessLocalServiceUtil.updateDossierFile(groupId, company, serviceContext, groupId, referenceUid, file);
			
			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormDataByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			if (dossier != null) {
				if (dossier.getOriginDossierId() != 0) {
					dossier = DossierLocalServiceUtil.fetchDossier(dossier.getOriginDossierId());
					id = dossier.getOriginDossierId();
				}
			}
			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);

			return Response.status(200).entity(dossierFile.getFormData()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormScriptByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);

			return Response.status(200).entity(dossierFile.getFormScript()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			DossierFile dossierFile = CPSDossierBusinessLocalServiceUtil.updateDossierFileFormData(groupId, company, serviceContext, id, referenceUid, formdata);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.deleteDossierFile(groupId, id, referenceUid, serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierFiles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierFileSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileSearchResultsModel results = new DossierFileSearchResultsModel();

			DossierFileActions action = new DossierFileActionsImpl();

			JSONObject dossierFileJsonObject = action.getDossierFiles(groupId, query.getKeyword(), query.getTemplate(),
					query.getType(), query.isOwner(), query.isOriginal(), query.getStart(), query.getEnd(),
					query.getSort(), query.getOrder(), serviceContext);
			
			List<Document> documents = (List<Document>) dossierFileJsonObject.get("data");

			results.setTotal(dossierFileJsonObject.getInt("total"));
			
			results.getData().addAll(DossierFileUtils.mappingToDossierFileSearchResultsModel(documents));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response downloadByDossierId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String password) {
		String pathName = "";
		String realPath = "";
		BackendAuth auth = new BackendAuthImpl();
		DossierFileActions action = new DossierFileActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			int partType = 2;
			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByD_DP(id, partType);

			if (dossierFiles != null && dossierFiles.size() > 0) {
			if (dossierFiles.size() > 0) {
				if (dossierFiles.get(0).getFileEntryId() > 0) {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFiles.get(0).getFileEntryId());
	
					File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
					realPath = file.getPath();
					pathName = file.getPath() + "_" + String.valueOf(id);
				}
			}
	//			int index = realPath.lastIndexOf("\\");
				int index = realPath.lastIndexOf("/");
				File d = null;
				if (index > 0) {
					d = new File(pathName.substring(0, index));
				}
				if (d != null) {
			for (File f : d.listFiles()) {
				if ("zip".equals(f.getName().substring(f.getName().lastIndexOf(".") + 1))) {
					f.delete();
				}
				if (f.isDirectory()) {
					f.delete();
				}
	
			}
				}
	
			for (DossierFile dossierFile : dossierFiles) {
				if (dossierFile.getFileEntryId() > 0) {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
	
					File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(),
							true);
	//					String fileName = pathName + "\\" + fileEntry.getFileName();
						String fileName = pathName + "/" + fileEntry.getFileName();
					File dir = new File(pathName);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					action.copyFile(file.getPath(), fileName);
				}
			}
	
			File dirName = new File(pathName);
	//			action.zipDirectory(dirName,
	//					pathName.substring(0, index) + "\\" + pathName.substring(index + 1, pathName.length()) + ".zip");
			action.zipDirectory(dirName,
						pathName.substring(0, index) + "/" + pathName.substring(index + 1, pathName.length()) + ".zip");
			// TODO:
			// Nen danh sach dossierFiles thanh file zip sau day gui lai client
	
	//			File fi = new File(
	//					pathName.substring(0, index) + "\\" + pathName.substring(index + 1, pathName.length()) + ".zip");
			File fi = new File(
						pathName.substring(0, index) + "/" + pathName.substring(index + 1, pathName.length()) + ".zip");
	
				ResponseBuilder responseBuilder = Response.ok(fi);
			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fi.getName() + "\"");
			responseBuilder.header("Content-Type", "application/zip");
	
			return responseBuilder.build();
			} else {
				return Response.status(HttpURLConnection.HTTP_NO_CONTENT).entity("No Content").build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response resetformdataDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			DossierFile dossierFile = CPSDossierBusinessLocalServiceUtil.resetformdataDossierFileFormData(groupId, company, serviceContext, id, referenceUid, formdata);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeAllDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id,
			String fileTemplateNo) {
		
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			//Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			
			DossierFileActions action = new DossierFileActionsImpl();

			action.deleteAllDossierFile(groupId, id, fileTemplateNo, serviceContext);

			//DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			
			result.put("status", "success");

			return Response.status(200).entity(JSONFactoryUtil.serialize(result)).build();

		} catch (Exception e) {
			_log.debug("DOSSIER_LOG_"+e);
			
			JSONObject result = JSONFactoryUtil.createJSONObject();
			
			result.put("status", "error");
			result.put("message", "error");

			return Response.status(500).entity(JSONFactoryUtil.serialize(result)).build();
		}
	}

	@Override
	public Response getDossierFileByDeliverableCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String deliverableCode) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.debug("********START *********");
		try {
			if(!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions actions = new DossierFileActionsImpl();

			DossierFile dossierFile = null;
			if (Validator.isNotNull(deliverableCode)) {
				_log.debug("********START GET DOSSIERFILE*********"+ new Date().getTime());
				dossierFile = actions.getDossierFileByDeliverableCode(groupId, deliverableCode);
				_log.debug("********END GET DOSSIERFILE *********"+ new Date().getTime());
			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			if (dossierFile != null) {
				results.put("dossierId", dossierFile.getDossierId());
				results.put("referenceUid", dossierFile.getReferenceUid());
			}

			_log.debug("********END *********");
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getAllDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		DossierFileResultsModel results = new DossierFileResultsModel();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long dossierId = GetterUtil.getLong(id);

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = null;
			if (dossierId > 0) {
				dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(dossierId);
			} else {
				Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				if (dossier != null) {
					dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(dossier.getDossierId());
				}
			}

			if (dossierFiles != null) {
				results.setTotal(dossierFiles.size());
				results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));
			} else {
				results.setTotal(0);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDossierFileByDossierId_FileTemplateNo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String fileTemplateNo) {

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.getDossierFileByFileTemplateNo(id, fileTemplateNo);
			
			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response downloadByPublicUser(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid, String password) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			boolean isAuthenticated = false;
			if (dossier.getPassword() != null && dossier.getPassword().equals(password)) {
				isAuthenticated = true;
			}
			if (!auth.isAuth(serviceContext) && !isAuthenticated) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);
			
			//download file with dossierFileID
			if (Validator.isNull(dossierFile) && Validator.isNumber(referenceUid)) {
				dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(Long.valueOf(referenceUid));
			}

			if (dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());

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

	@Override
	public Response uploadFileEntry(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file) {
		_log.info("uploadFileEntry");
		System.out.println("uploadFileEntry");
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
		InputStream fileInputStream = null;

		try {
			DataHandler dataHandle = file.getDataHandler();

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext, "admin")) {
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity("User not permission process!").build();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			action.uploadFileEntry(dataHandle.getName(), dataHandle.getInputStream(), serviceContext);

			String result = StringPool.BLANK;

			//
			List<Group> groupList = GroupLocalServiceUtil.getActiveGroups(company.getCompanyId(), true);
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
			//Check group
			if (!strGroupId.contains(String.valueOf(groupId))) {
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("GroupId not exits!").build();
			}
			
			//Process FILE
			fileInputStream = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			String extFile = ImportZipFileUtils.getExtendFileName(fileName);
			_log.info("extFile: "+extFile);
			if (Validator.isNotNull(extFile)) {
				if ("zip".equals(extFile.toLowerCase())) {
					String pathFolder = ImportZipFileUtils.getFolderPath(fileName, ConstantUtils.DEST_DIRECTORY);
//					//delete folder if exits
					File fileOld = new File(pathFolder);
					_log.info("fileOld: "+fileOld);
					if (fileOld.exists()) {
						boolean flag = ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
						_log.info("LamTV_Delete DONE: "+flag);
					}
//					_log.info("LamTV_pathFolder: "+pathFolder);
					ImportZipFileUtils.unzip(fileInputStream, ConstantUtils.DEST_DIRECTORY);
					File fileList = new File(pathFolder);
//					//Validate xml
					String strError = ReadXMLFileUtils.validateXML(fileList, true);
					_log.info("strError: "+strError);
					if (Validator.isNotNull(strError)) {
						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(strError).build();
					}

//					String errorCheck = ReadXMLFileUtils.getStrError();
//					_log.info("errorCheck: "+errorCheck);
//					if (Validator.isNotNull(errorCheck)) {
//						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(errorCheck).build();
//					}
					result = ReadXMLFileUtils.listFilesForParentFolder(fileList, groupId, userId, serviceContext);
					if (Validator.isNull(result)) {
						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("Folder is not structure").build();
					}
					_log.info("LamTV_IMPORT DONE_ZIP");
				} else if ("xml".equals(extFile.toLowerCase())) {
					String pathFile = ConstantUtils.DEST_DIRECTORY + StringPool.SLASH + fileName;
//					//delete folder if exits
					File fileOld = new File(pathFile);
					_log.info("fileOld: "+fileOld.getAbsolutePath());
					if (fileOld.exists()) {
						boolean flag = ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
						_log.info("LamTV_Delete DONE: "+flag);
					}
					_log.info("LamTV_pathFolder: "+pathFile);
					File fileList = new File(pathFile);
					FileOutputStream out = new FileOutputStream(fileList);
					IOUtils.copy(fileInputStream, out);
//					FileUtils.copyInputStreamToFile(fileInputStream, fileList);
					_log.info("fileList: "+fileList);
//					_log.info("LamTV_fileList: "+fileList.getPath());
					String subFileName = ImportZipFileUtils.getSubFileName(fileName);
					if (Validator.isNotNull(subFileName)) {
						String strError = ReadXMLFileUtils.validateXML(fileList, false);
						if (Validator.isNotNull(strError)) {
							return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(strError).build();
						}
						String xmlString = ReadXMLFileUtils.convertFiletoString(fileList);
						result = ReadXMLFileUtils.compareParentFile(ConstantUtils.DEST_DIRECTORY, fileName, xmlString, groupId, userId, serviceContext);
					}
					_log.info("LamTV_IMPORT DONE_FILE");
				}
			}

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getAlreadyHasFileTemplateNo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String applicantIdNo, String fileTemplateNo) {
		DossierFileResultsModel results = new DossierFileResultsModel();

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			Dossier dossier = DossierLocalServiceUtil.fetchDossier(id);
			if (dossier != null) {
				List<Dossier> lstDossiers = DossierLocalServiceUtil.getByG_AN(groupId, applicantIdNo);
				List<DossierFile> resultFiles = new ArrayList<>();
				if (lstDossiers.size() > 0) {
					long[] dossierIds = new long[lstDossiers.size()];
					int i = 0;
					for (Dossier d : lstDossiers) {
						dossierIds[i++] = d.getDossierId();
					}
					
					String[] ftns = StringUtil.split(fileTemplateNo);
					
					for (String ftn : ftns) {
						List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getByG_DID_FTN_R_O(groupId, dossierIds, ftn, false, true);
						resultFiles.addAll(dossierFiles);
					}
				}
				results.setTotal(resultFiles.size());
				results.getData().addAll(DossierFileUtils.mappingToDossierFileData(resultFiles));			
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response useOriginalDossierFile(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, DossierFileCopyInputModel input) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();
		
			DossierFile dossierFile = action.cloneDossierFile(groupId, id, input.getDossierFileId(),
					input.getDossierTemplateNo(), input.getDossierPartNo(), serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response getFileDoneOfApplicant(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String applicantIdNo,
			DossierFileSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			DossierFileResultsModel results = new DossierFileResultsModel();
			List<Dossier> lstDossiers = DossierLocalServiceUtil.getByF_GID_AN_DS(groupId, applicantIdNo,
					DossierTerm.DOSSIER_STATUS_DONE);

			int countDossierFile = 0;
			if (lstDossiers != null && lstDossiers.size() > 0) {
				long[] dossierIds = new long[lstDossiers.size()];
				int i = 0;
				for (Dossier d : lstDossiers) {
					dossierIds[i++] = d.getDossierId();
				}

				countDossierFile = DossierFileLocalServiceUtil.countByF_GID_DID_R_O(groupId, dossierIds, false, true);
				if (countDossierFile > 0) {
					results.setTotal(countDossierFile);
					List<DossierFile> dossierFileList = DossierFileLocalServiceUtil.getByF_GID_DID_R_O(groupId,
							dossierIds, false, true, query.getStart(), query.getEnd());
					results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFileList));
				}
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response removeDossierOfProfileApplicant(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long dossierFileId) {

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileModel results = null;
			DossierFile dossierFile = DossierFileLocalServiceUtil.fetchDossierFile(dossierFileId);
			if (dossierFile != null) {
				dossierFile.setOriginal(false);
				dossierFile = DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
			}
			
			if (dossierFile != null) {
				results = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addDossierFiles(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext,
		String dossierIds, String referenceUid, String dossierTemplateNo,
		String dossierPartNo, int dossierPartType, String fileTemplateNo,
		String displayName, String formData, long fileEntryId, Boolean original,
		Boolean eForm, Boolean isNew, Boolean removed, int signCheck,
		String signInfo, String formScript, String formReport,
		String formSchema, String deliverableCode) {

		try {

			BackendAuth auth = new BackendAuthImpl();
			DossierFileResultsModel results = new DossierFileResultsModel();
			long groupId =
				GetterUtil.getLong(header.getHeaderString("groupId"));

			if (!auth.isAuth(serviceContext)) {

				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = new ArrayList<>();
			String[] dossierIdList = dossierIds.split(StringPool.COMMA);
			for (String dossierIdStr : dossierIdList) {

				try {

					long dossierId = GetterUtil.getLong(dossierIdStr);
					if (dossierId > 0) {
						DossierFile dossierFile =
							DossierFileLocalServiceUtil.updateDossierFile(
								0, groupId, company.getCompanyId(),
								user.getUserId(), user.getFullName(), dossierId,
								referenceUid, dossierTemplateNo, dossierPartNo,
								dossierPartType, fileTemplateNo, displayName,
								formData, fileEntryId, original, eForm, isNew,
								removed, signCheck, signInfo, formScript,
								formReport, formSchema, deliverableCode);

						dossierFiles.add(dossierFile);
					}
				}
				catch (Exception e) {

					_log.error(e);
				}
			}

			results.setTotal(dossierFiles.size());
			results.getData().addAll(
				DossierFileUtils.mappingToDossierFileData(dossierFiles));
			return Response.status(200).entity(results).build();
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

			return Response.status(200).entity(results).build();

		}
		catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

}
