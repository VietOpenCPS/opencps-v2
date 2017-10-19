package org.opencps.api.controller.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.EmployeeManagement;
import org.opencps.api.controller.util.EmployeeUtils;
import org.opencps.api.employee.model.DataSearchModel;
import org.opencps.api.employee.model.EmployeeAccountInputModel;
import org.opencps.api.employee.model.EmployeeAccountModel;
import org.opencps.api.employee.model.EmployeeInputModel;
import org.opencps.api.employee.model.EmployeeJobposInputModel;
import org.opencps.api.employee.model.EmployeeJobposModel;
import org.opencps.api.employee.model.EmployeeJobposResults;
import org.opencps.api.employee.model.EmployeeModel;
import org.opencps.api.employee.model.EmployeeResults;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.constants.EmployeeJobPosTerm;
import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.utils.DateTimeUtils;

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

public class EmployeeManagementImpl implements EmployeeManagement {

	private static final Log _log = LogFactoryUtil.getLog(EmployeeManagementImpl.class);

	@Override
	public Response getEmployees(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeResults result = new EmployeeResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getEmployeeModel().addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get("data")));

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
		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		if (Validator.isNotNull(employee)) {

			EmployeeModel employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(200).entity(employeeModel).build();

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
			ServiceContext serviceContext, EmployeeInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeModel employeeModel = new EmployeeModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Date birthDate = DateTimeUtils.convertStringToDateAPI(input.getBirthdate());

			Employee employee = actions.create(user.getUserId(), company.getCompanyId(), groupId, input.getEmployeeNo(),
					input.getFullName(), input.getEmail(), input.getGender(), birthDate, input.getTelNo(),
					input.getMobile(), input.getTitle(), input.getWorkingStatus(), serviceContext);

			employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(200).entity(employeeModel).build();

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

				error.setMessage("conflict! User");
				error.setCode(409);
				error.setDescription("conflict! User");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateEmployeeNoException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("duplicate-employee-no");
				error.setCode(409);
				error.setDescription("duplicate-employee-no");

				return Response.status(409).entity(error).build();

			}
			
			if (e instanceof DuplicateEmployeeEmailException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("duplicate-employee-email");
				error.setCode(409);
				error.setDescription("duplicate-employee-email");

				return Response.status(409).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, EmployeeInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeModel employeeModel = new EmployeeModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			Date birthDate = DateTimeUtils.convertStringToDateAPI(input.getBirthdate());

			Employee employee = actions.update(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getEmployeeNo(), input.getFullName(), input.getEmail(), input.getGender(), birthDate,
					input.getTelNo(), input.getMobile(), input.getTitle(), input.getWorkingStatus(), serviceContext);

			employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(200).entity(employeeModel).build();

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
			
			if (e instanceof DuplicateEmployeeNoException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("duplicate employeeNo");
				error.setCode(409);
				error.setDescription("duplicate employeeNo");

				return Response.status(409).entity(error).build();

			}
			
			if (e instanceof DuplicateEmployeeEmailException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("duplicate employee email");
				error.setCode(409);
				error.setDescription("duplicate employee email");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		try {

			EmployeeLocalServiceUtil.deleteEmployee(id, serviceContext);

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
	public Response getEmployeePhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		EmployeeInterface actions = new EmployeeActions();

		try {

			File file = actions.getEmployeePhoto(id, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileName = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
					.header("Content-Type", fileEntry.getMimeType());

			return responseBuilder.build();

		} catch (Exception e) {
			_log.error(e);

			ErrorMsg error = new ErrorMsg();
			error.setMessage("file not found!");
			error.setCode(404);
			error.setDescription("file not found!");
			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response uploadEmployeePhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		EmployeeInterface actions = new EmployeeActions();
		InputStream inputStream = null;

		DataHandler dataHandler = attachment.getDataHandler();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			inputStream = dataHandler.getInputStream();

			File file = actions.uploadEmployeePhoto(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, "OfficeSite/", "OfficeSite file upload", serviceContext);

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
	public Response getEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, DataSearchModel query) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeJobposResults result = new EmployeeJobposResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put(EmployeeJobPosTerm.EMPLOYEE_ID, String.valueOf(id));
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getEmployeeJobpos(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getEmployeeJobposModel().addAll(EmployeeUtils.mapperEmployeeJobposList((List<Document>) jsonData.get("data"), id));

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
	public Response createEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, EmployeeJobposInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeJobposModel employeeJobposModel = new EmployeeJobposModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			EmployeeJobPos employeeJobPos = actions.createEmployeeJobpos(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getWorkingUnitId(), input.getJobPosId(), Boolean.valueOf(input.getMainJobPos()), serviceContext);

			employeeJobposModel = EmployeeUtils.mapperEmployeeJobposModel(employeeJobPos);

			return Response.status(200).entity(employeeJobposModel).build();

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
	public Response updateEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, EmployeeJobposInputModel input, long employeeJobPosId) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeJobposModel employeeJobposModel = new EmployeeJobposModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			EmployeeJobPos employeeJobPos = actions.updateEmployeeJobpos(user.getUserId(), company.getCompanyId(), groupId, id, employeeJobPosId,
					input.getWorkingUnitId(), input.getJobPosId(), Boolean.valueOf(input.getMainJobPos()), serviceContext);

			employeeJobposModel = EmployeeUtils.mapperEmployeeJobposModel(employeeJobPos);

			return Response.status(200).entity(employeeJobposModel).build();

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

			return Response.status(500).build();
		}
	}

	@Override
	public Response deleteEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long employeeJobPosId) {
		try {
			EmployeeJobPosLocalServiceUtil.deleteEmployeeJobPos(employeeJobPosId, serviceContext);

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
	public Response createEmployeeAccount(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, EmployeeAccountInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonObject = actions.createEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id, input.getScreenName(),
					input.getEmail(), input.isExist(), serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			if(Validator.isNotNull(jsonObject.getString("duplicate")) && jsonObject.getString("duplicate").equals(Boolean.TRUE.toString())){
				
				return Response.status(409).entity(employeeAccountModel).build();
				
			} else {
				
				return Response.status(200).entity(employeeAccountModel).build();
				
			}
			

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

				error.setMessage("account already exits!");
				error.setCode(409);
				error.setDescription("account already exits!");

				return Response.status(200).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response lockEmployeeAccount(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, boolean locked) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonObject = actions.lockEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id, locked, serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			return Response.status(200).entity(employeeAccountModel).build();

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

			return Response.status(500).build();
		}
	}

	@Override
	public Response validateExits(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String employeeNo, String email) {
		EmployeeInterface actions = new EmployeeActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			actions.validateExits(user.getUserId(), company.getCompanyId(), groupId, employeeNo, email, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {

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

}
