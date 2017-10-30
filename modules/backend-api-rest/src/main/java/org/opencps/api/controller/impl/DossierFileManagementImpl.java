package org.opencps.api.controller.impl;

import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.DossierFileManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DossierFileUtils;
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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class DossierFileManagementImpl implements DossierFileManagement{

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

	@Override
	public Response getDossierFilesByDossierReferenceUid(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String referenceUid) {
		DossierFileResultsModel results = new DossierFileResultsModel();

		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, referenceUid);
			
			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(dossier.getDossierId());

			results.setTotal(dossierFiles.size());
			results.getData().addAll(DossierFileUtils.mappingToDossierFileData(dossierFiles));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, long id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String displayName) {

		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			DataHandler dataHandler = file.getDataHandler();
			
			DossierFileActions action = new DossierFileActionsImpl();
			
			DossierFile dossierFile = action.addDossierFile(groupId, id, referenceUid, 
					dossierTemplateNo, dossierPartNo, fileTemplateNo,
					displayName, dataHandler.getName(), 0, dataHandler.getInputStream(), serviceContext);
			
			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response addDossierFileByDossierReferenceUid(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String dosserReferenceUid,
			String referenceUid, String dossierTemplateNo, String dossierPartNo, String fileTemplateNo,
			String displayName) {
		
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, referenceUid);
			
			DataHandler dataHandler = file.getDataHandler();
			
			DossierFileActions action = new DossierFileActionsImpl();
			
			DossierFile dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), 
					referenceUid, dossierTemplateNo, dossierPartNo, fileTemplateNo,
					displayName, dataHandler.getName(), 0, dataHandler.getInputStream(), serviceContext);
			
			DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return processException(e);
		}
	}

	@Override
	public Response cloneDossierFile(HttpServletRequest request, HttpHeaders header, 
			Company company, Locale locale,
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

			if(dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
	
				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);
	
				ResponseBuilder responseBuilder = Response.ok((Object) file);
	
				responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileEntry.getFileName() + "\"");
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
	public Response downloadByDossierReferenceUid_ReferenceUid(HttpServletRequest request, HttpHeaders header,
			Company company, Locale locale, User user, ServiceContext serviceContext, String dossierReferenceUid,
			String referenceUid, String password) {
		
		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {
			
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			Dossier dossier = DossierLocalServiceUtil.getByRef(groupId, dossierReferenceUid);
			
			DossierFile dossierFile = DossierFileLocalServiceUtil.getDossierFileByReferenceUid(dossier.getDossierId(), referenceUid);

			if(dossierFile.getFileEntryId() > 0) {
				FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(dossierFile.getFileEntryId());
	
				File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);
	
				ResponseBuilder responseBuilder = Response.ok((Object) file);
	
				responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileEntry.getFileName() + "\"");
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
	public Response updateDossierFile(HttpServletRequest request, HttpHeaders header, 
			Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, 
			String referenceUid, Attachment file) {
		
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
	public Response updateDossierFileFormData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String referenceUid, String formdata) {
		
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
			
			JSONObject dossierFileJsonObject = action.getDossierFiles(groupId, query.getKeyword(), 
					query.getTemplate(), query.getType(), query.isOwner(), query.isOriginal(), query.getStart(),
					query.getEnd(), query.getSort(), query.getOrder(), serviceContext);
			
			List<Document> documents = (List<Document>) dossierFileJsonObject.get("data");
			
			results.setTotal(dossierFileJsonObject.getInt("total"));
			results.getData().addAll(DossierFileUtils.mappingToDossierFileSearchResultsModel(documents));

			return Response.status(200).entity(dossierFileJsonObject).build();

		} catch (Exception e) {
			return processException(e);
		}
	}
	
	@Override
	public Response downloadByDossierId(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String password) {
		
		// TODO: check user is loged or password for access dossier file
		BackendAuth auth = new BackendAuthImpl();
		
		try {
			
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			List<DossierFile> dossierFiles = DossierFileLocalServiceUtil.getDossierFilesByDossierId(id);

			// TODO:
			// Nen danh sach dossierFiles thanh file zip sau day gui lai client
			
			return Response.ok().build();

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
				error.setDescription("No Content.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

			}
		}
	}
}
