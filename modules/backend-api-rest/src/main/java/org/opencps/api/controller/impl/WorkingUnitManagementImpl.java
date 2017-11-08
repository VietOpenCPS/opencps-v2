package org.opencps.api.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.WorkingUnitManagement;
import org.opencps.api.controller.util.WorkingUnitUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.workingunit.model.DataSearchModel;
import org.opencps.api.workingunit.model.WorkingUnitInputModel;
import org.opencps.api.workingunit.model.WorkingUnitModel;
import org.opencps.api.workingunit.model.WorkingUnitResults;
import org.opencps.usermgt.action.WorkingUnitInterface;
import org.opencps.usermgt.action.impl.WorkingUnitActions;
import org.opencps.usermgt.constants.WorkingUnitTerm;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

public class WorkingUnitManagementImpl implements WorkingUnitManagement {

	private static final Log _log = LogFactoryUtil.getLog(WorkingUnitManagementImpl.class);

	@Override
	public Response getWorkingUnits(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {

		WorkingUnitInterface actions = new WorkingUnitActions();
		WorkingUnitResults result = new WorkingUnitResults();

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put(WorkingUnitTerm.PARENT_WORKING_UNIT_ID, query.getParent());
			
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create("treeIndex_sortable", Sort.STRING_TYPE, Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getWorkingUnits(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getWorkingUnitModel()
					.addAll(WorkingUnitUtils.mapperWorkingUnitList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("/ @GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(id);

		if (Validator.isNotNull(workingUnit)) {

			WorkingUnitModel workingUnitModel = WorkingUnitUtils.mapperWorkingUnitModel(workingUnit);

			return Response.status(200).entity(workingUnitModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, WorkingUnitInputModel input) {
		WorkingUnitInterface actions = new WorkingUnitActions();
		WorkingUnitModel workingUnitModel = new WorkingUnitModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			WorkingUnit workingUnit = actions.create(user.getUserId(), company.getCompanyId(), groupId,
					input.getAddress(), input.getEmail(), input.getEnName(), input.getFaxNo(), input.getGovAgencyCode(),
					input.getName(), input.getTelNo(), input.getWebsite(), input.getParentWorkingUnitId(),
					input.getSibling(), input.getCeremonyDate(), serviceContext);

			workingUnitModel = WorkingUnitUtils.mapperWorkingUnitModel(workingUnit);

			return Response.status(200).entity(workingUnitModel).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, WorkingUnitInputModel input) {
		WorkingUnitInterface actions = new WorkingUnitActions();
		WorkingUnitModel workingUnitModel = new WorkingUnitModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			WorkingUnit workingUnit = actions.update(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getAddress(), input.getEmail(), input.getEnName(), input.getFaxNo(), input.getGovAgencyCode(),
					input.getName(), input.getTelNo(), input.getWebsite(), input.getParentWorkingUnitId(),
					input.getSibling(), input.getCeremonyDate(), serviceContext);

			workingUnitModel = WorkingUnitUtils.mapperWorkingUnitModel(workingUnit);

			return Response.status(200).entity(workingUnitModel).build();

		} catch (Exception e) {

			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		try {

			WorkingUnitLocalServiceUtil.deleteWorkingUnit(id, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			_log.error("@DELETE: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@DELETE: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@DELETE: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@DELETE: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response updateLogo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		WorkingUnitInterface actions = new WorkingUnitActions();
		InputStream inputStream = null;

		DataHandler dataHandler = attachment.getDataHandler();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			inputStream = dataHandler.getInputStream();

			File file = actions.updateLogo(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, "WorkingUnit/", "WorkingUnit file upload", serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileNameRespone = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileNameRespone + "\"")
					.header("Content-Type", fileEntry.getMimeType());

			return responseBuilder.build();
		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();
			} else if (e instanceof UnauthorizationException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			} else if (e instanceof NoSuchUserException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();
			} else {
				return Response.status(500).build();
			}

		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response getLogo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		WorkingUnitInterface actions = new WorkingUnitActions();

		try {

			File file = actions.getLogo(id, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileName = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
					.header("Content-Type", fileEntry.getMimeType());

			return responseBuilder.build();

		} catch (Exception e) {

			ErrorMsg error = new ErrorMsg();
			error.setMessage("file not found!");
			error.setCode(404);
			error.setDescription("file not found!");
			return Response.status(404).entity(error).build();
		}
	}

}
