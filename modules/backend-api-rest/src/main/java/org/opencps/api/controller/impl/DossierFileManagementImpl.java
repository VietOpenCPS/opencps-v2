package org.opencps.api.controller.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DossierFileManagement;
import org.opencps.api.controller.exception.ErrorMsg;
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
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierFileManagementImpl implements DossierFileManagement {

	private static final Log _log = LogFactoryUtil.getLog(DossierFileManagementImpl.class);

	@Override
	public Response getDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String password) {

		DossierFileResultsModel results = new DossierFileResultsModel();

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(id);

			results.setTotal(dossierFiles.size());
			results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/*
	 * @Override public Response
	 * getDossierFilesByDossierReferenceUid(HttpServletRequest request,
	 * HttpHeaders header, Company company, Locale locale, User user,
	 * ServiceContext serviceContext, String referenceUid) {
	 * DossierFileResultsModel results = new DossierFileResultsModel();
	 * 
	 * // TODO: check user is loged or password for access dossier file
	 * BackendAuth auth = new BackendAuthImpl();
	 * 
	 * long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	 * 
	 * try {
	 * 
	 * if (!auth.isAuth(serviceContext)) { throw new
	 * UnauthenticationException(); }
	 * 
	 * Dossier dossier = DossierLocalServiceUtil.getByRef(groupId,
	 * referenceUid);
	 * 
	 * List<DossierFile> dossierFiles =
	 * DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.
	 * getDossierId());
	 * 
	 * results.setTotal(dossierFiles.size());
	 * results.getData().addAll(DossierFileUtils.mappingToDossierFileData(
	 * dossierFiles));
	 * 
	 * return Response.status(200).entity(results).build();
	 * 
	 * } catch (Exception e) { return processException(e); } }
	 */
	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String displayName, String fileType,
			String isSync, String formData) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("In dossier file create");
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);

			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			if (Validator.isNull(referenceUid)) {
				referenceUid = UUID.randomUUID().toString();
			}

			DataHandler dataHandler = file.getDataHandler();

			DossierFileActions action = new DossierFileActionsImpl();
			
			
			_log.info("__Start add file at:" + new Date());

			DossierFile dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid,
					dossierTemplateNo, dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
					dataHandler.getInputStream(), fileType, isSync, serviceContext);
			
			_log.info("__End add file at:" + new Date());
			
			if(Validator.isNotNull(formData)) {
				dossierFile.setFormData(formData);
			}
			
			_log.info("__Start update dossier file at:" + new Date());

			DossierFileLocalServiceUtil.updateDossierFile(dossierFile);

			_log.info("__End update dossier file at:" + new Date());

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
			
			_log.info("__End bind to dossierFile" + new Date());

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	/*
	 * @Override public Response
	 * addDossierFileByDossierReferenceUid(HttpServletRequest request,
	 * HttpHeaders header, Company company, Locale locale, User user,
	 * ServiceContext serviceContext, Attachment file, String
	 * dosserReferenceUid, String referenceUid, String dossierTemplateNo, String
	 * dossierPartNo, String fileTemplateNo, String displayName) {
	 * 
	 * BackendAuth auth = new BackendAuthImpl();
	 * 
	 * long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	 * 
	 * try {
	 * 
	 * if (!auth.isAuth(serviceContext)) { throw new
	 * UnauthenticationException(); }
	 * 
	 * Dossier dossier = DossierLocalServiceUtil.getByRef(groupId,
	 * referenceUid);
	 * 
	 * DataHandler dataHandler = file.getDataHandler();
	 * 
	 * DossierFileActions action = new DossierFileActionsImpl();
	 * 
	 * DossierFile dossierFile = action.addDossierFile(groupId,
	 * dossier.getDossierId(), referenceUid, dossierTemplateNo, dossierPartNo,
	 * fileTemplateNo, displayName, dataHandler.getName(), 0,
	 * dataHandler.getInputStream(), serviceContext);
	 * 
	 * DossierFileModel result =
	 * DossierFileUtils.mappingToDossierFileModel(dossierFile);
	 * 
	 * return Response.status(200).entity(result).build();
	 * 
	 * } catch (Exception e) { return processException(e); } }
	 */
	
	
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
		    _log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response downloadByDossierId_ReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String password) {

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);
			
			// TODO download file with dossierFileID
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
			return processException(e);
		}
	}

	/*
	 * @Override public Response
	 * downloadByDossierReferenceUid_ReferenceUid(HttpServletRequest request,
	 * HttpHeaders header, Company company, Locale locale, User user,
	 * ServiceContext serviceContext, String dossierReferenceUid, String
	 * referenceUid, String password) {
	 * 
	 * // TODO: check user is loged or password for access dossier file
	 * BackendAuth auth = new BackendAuthImpl();
	 * 
	 * long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
	 * 
	 * try {
	 * 
	 * if (!auth.isAuth(serviceContext)) { throw new
	 * UnauthenticationException(); }
	 * 
	 * Dossier dossier = DossierLocalServiceUtil.getByRef(groupId,
	 * dossierReferenceUid);
	 * 
	 * DossierFile dossierFile =
	 * DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossier.
	 * getDossierId(), referenceUid);
	 * 
	 * if(dossierFile.getFileEntryId() > 0) { FileEntry fileEntry =
	 * DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
	 * 
	 * File file =
	 * DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(),
	 * fileEntry.getVersion(), true);
	 * 
	 * ResponseBuilder responseBuilder = Response.ok((Object) file);
	 * 
	 * responseBuilder.header("Content-Disposition", "attachment; filename=\"" +
	 * fileEntry.getFileName() + "\""); responseBuilder.header("Content-Type",
	 * fileEntry.getMimeType());
	 * 
	 * return responseBuilder.build(); } else { return
	 * Response.status(HttpURLConnection.HTTP_NO_CONTENT).build(); }
	 * 
	 * } catch (Exception e) { return processException(e); } }
	 */
	@Override
	public Response updateDossierFile(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid, Attachment file) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DataHandler dataHandle = file.getDataHandler();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.updateDossierFile(groupId, id, referenceUid, dataHandle.getName(),
					dataHandle.getInputStream(), serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
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

			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(id, referenceUid);

			return Response.status(200).entity(dossierFile.getFormData()).build();

		} catch (Exception e) {
			return processException(e);
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
			return processException(e);
		}
	}

	@Override
	public Response updateDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.updateDossierFileFormData(groupId, id, referenceUid, formdata,
					serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			e.printStackTrace();
			return processException(e);
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
			return processException(e);
		}
	}

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
			return processException(e);
		}
	}

	@Override
	public Response downloadByDossierId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String password) {
		String pathName = "";
		String realPath = "";
		// TODO: check user is loged or password for access dossier file
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
				if (f.getName().substring(f.getName().lastIndexOf(".") + 1).equals("zip")) {
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
			return processException(e);
		}
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

				error.setMessage("No Content.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription(e.getMessage());

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}

	@Override
	public Response resetformdataDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			DossierFile dossierFile = action.resetDossierFileFormData(groupId, id, referenceUid, formdata,
					serviceContext);

			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			e.printStackTrace();
			return processException(e);
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
			_log.info("DOSSIER_LOG_"+e);
			
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
		_log.info("********START *********");
		try {
			if(!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions actions = new DossierFileActionsImpl();

			DossierFile dossierFile = null;
			if (Validator.isNotNull(deliverableCode)) {
				_log.info("********START GET DOSSIERFILE*********"+ new Date().getTime());
				dossierFile = actions.getDossierFileByDeliverableCode(groupId, deliverableCode);
				_log.info("********END GET DOSSIERFILE *********"+ new Date().getTime());
			}

			JSONObject results = JSONFactoryUtil.createJSONObject();
			if (dossierFile != null) {
				results.put("dossierId", dossierFile.getDossierId());
				results.put("referenceUid", dossierFile.getReferenceUid());
			}

			_log.info("********END *********");
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			e.printStackTrace();
			return processException(e);
		}

	}

	@Override
	public Response getAllDossierFilesByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		DossierFileResultsModel results = new DossierFileResultsModel();

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getAllDossierFile(id);

			results.setTotal(dossierFiles.size());
			results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
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
			_log.error(e);
			return processException(e);
		}
	}

	@Override
	public Response downloadByPublicUser(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid, String password) {
		// TODO Auto-generated method stub
		// TODO: check user is loged or password for access dossier file
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
			
			// TODO download file with dossierFileID
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
			return processException(e);
		}	
	}

	@Override
	public Response uploadFileEntry(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file) {
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
		DataHandler dataHandle = file.getDataHandler();
		InputStream fileInputStream = null;
		try {
			fileInputStream = dataHandle.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			_log.error(e1);
		}

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			DossierFileActions action = new DossierFileActionsImpl();

			action.uploadFileEntry(dataHandle.getName(), dataHandle.getInputStream(), serviceContext);

			DossierFileModel result = null;

			//Process FILE
			String fileName = dataHandle.getName();
			String pathFolder = ImportZipFileUtils.getFolderPath(fileName, ConstantUtils.DEST_DIRECTORY);
			_log.info("LamTV_pathFolder: "+pathFolder);
			ImportZipFileUtils.unzip(fileInputStream, ConstantUtils.DEST_DIRECTORY);
			//TODO:Test multiple file
			File fileList = new File(pathFolder);
			_log.info("LamTV_fileList: "+fileList.getPath());
			ReadXMLFileUtils.listFilesForParentFolder(fileList, groupId, userId, serviceContext);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return processException(e);
		}
	}

}
