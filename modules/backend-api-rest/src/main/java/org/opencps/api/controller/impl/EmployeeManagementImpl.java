package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

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
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.constants.EmployeeJobPosTerm;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.utils.DateTimeUtils;

import backend.auth.api.exception.BusinessExceptionImpl;

public class EmployeeManagementImpl implements EmployeeManagement {

	private static final Log _log = LogFactoryUtil.getLog(EmployeeManagementImpl.class);
	private static final String SERVER = "SERVER_";

	@SuppressWarnings("unchecked")
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
			params.put(EmployeeTerm.WORKING_UNIT_ID, query.getWorkingunit());
			params.put(EmployeeTerm.JOB_POS_ID, query.getJobpos());
			params.put(EmployeeTerm.WORKING_STATUS, query.getStatus());

			String jobposCode = query.getJobposCode();
			String jobposCodeSearch = StringPool.BLANK;
			if (Validator.isNotNull(jobposCode)) {
				jobposCodeSearch = SpecialCharacterUtils.splitSpecial(jobposCode);
			}
			params.put(EmployeeTerm.JOB_POS_CODE_SEARCH, jobposCodeSearch);

			if(Validator.isNotNull(query.getActive())){
				params.put(EmployeeTerm.ACTIVE,
						query.getActive().equals(Boolean.TRUE.toString())
								? String.valueOf(WorkflowConstants.STATUS_APPROVED)
								: String.valueOf(WorkflowConstants.STATUS_DENIED));
			}
			
			params.put(EmployeeTerm.MONTH, query.getMonth());

			_log.info("EmployeeManagementImpl.getEmployees()"+params);
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getEmployeeModel().addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			Date recruitDate = DateTimeUtils.convertStringToDateAPI(input.getRecruitDate());
			Date leaveDate = DateTimeUtils.convertStringToDateAPI(input.getLeaveDate());

			String fullName = HtmlUtil.escape(input.getFullName());
			String employeeNo = HtmlUtil.escape(input.getEmployeeNo());
			String telNo = HtmlUtil.escape(input.getTelNo());
			String mobile = HtmlUtil.escape(input.getMobile());
			String email = HtmlUtil.escape(input.getEmail());
			String title = HtmlUtil.escape(input.getTitle());
			
			Employee employee = actions.create(user.getUserId(), company.getCompanyId(), groupId, employeeNo,
					fullName, email, input.getGender(), birthDate, telNo,
					mobile, title, input.getWorkingStatus(), recruitDate, leaveDate,
					serviceContext);

			employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(200).entity(employeeModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			Date recruitDate = DateTimeUtils.convertStringToDateAPI(input.getRecruitDate());
			Date leaveDate = DateTimeUtils.convertStringToDateAPI(input.getLeaveDate());

			String fullName = HtmlUtil.escape(input.getFullName());
			String employeeNo = HtmlUtil.escape(input.getEmployeeNo());
			String telNo = HtmlUtil.escape(input.getTelNo());
			String mobile = HtmlUtil.escape(input.getMobile());
			String email = HtmlUtil.escape(input.getEmail());
			String title = HtmlUtil.escape(input.getTitle());

			Employee employee = actions.update(user.getUserId(), company.getCompanyId(), groupId, id,
					employeeNo, fullName, email, input.getGender(), birthDate,
					telNo, mobile, title, input.getWorkingStatus(), recruitDate,
					leaveDate, serviceContext);

			employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(200).entity(employeeModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		try {

			EmployeeLocalServiceUtil.deleteEmployee(id, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getEmployeePhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		EmployeeInterface actions = new EmployeeActions();

		try {

			File file = actions.getEmployeePhoto(id, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);
			
			if(file != null && fileEntry != null){
				String fileName = fileEntry.getFileName();

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
						.header("Content-Type", fileEntry.getMimeType());

				return responseBuilder.build();
			}else{
//				ErrorMsg error = new ErrorMsg();
//				error.setMessage("file not found!");
//				error.setCode(404);
//				error.setDescription("file not found!");
				return Response.status(404).entity("").build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response uploadEmployeePhoto(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		EmployeeInterface actions = new EmployeeActions();
		InputStream inputStream = null;

		try {
			DataHandler dataHandler = attachment.getDataHandler();

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
			return BusinessExceptionImpl.processException(e);

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
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
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getEmployeeJobpos(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getEmployeeJobposModel()
					.addAll(EmployeeUtils.mapperEmployeeJobposList((List<Document>) jsonData.get("data"), id));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response createEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, EmployeeJobposInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeJobposModel employeeJobposModel = new EmployeeJobposModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			EmployeeJobPos employeeJobPos = actions.createEmployeeJobpos(user.getUserId(), company.getCompanyId(),
					groupId, id, input.getWorkingUnitId(), input.getJobPosId(), Boolean.valueOf(input.getMainJobPos()),
					serviceContext);

			employeeJobposModel = EmployeeUtils.mapperEmployeeJobposModel(employeeJobPos);

			return Response.status(200).entity(employeeJobposModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, EmployeeJobposInputModel input, long employeeJobPosId) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeJobposModel employeeJobposModel = new EmployeeJobposModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			EmployeeJobPos employeeJobPos = actions.updateEmployeeJobpos(user.getUserId(), company.getCompanyId(),
					groupId, id, employeeJobPosId, input.getWorkingUnitId(), input.getJobPosId(),
					Boolean.valueOf(input.getMainJobPos()), serviceContext);

			employeeJobposModel = EmployeeUtils.mapperEmployeeJobposModel(employeeJobPos);

			return Response.status(200).entity(employeeJobposModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteEmployeeJobpos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long employeeJobPosId) {
		EmployeeInterface actions = new EmployeeActions();

		try {
			actions.deleteEmployeeJobPos(id, employeeJobPosId, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response createEmployeeAccount(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, EmployeeAccountInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();
		
//		try {
//			_log.info("RESET USER ERROR");
//
//			Role adminRole = RoleLocalServiceUtil.getRole(20122);
//			
//			RoleLocalServiceUtil.addUserRole(
//					user.getUserId(), adminRole.getRoleId());
//		} catch (Exception e) {
//			_log.info("RESET USER ERROR");
//		}

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonObject = actions.createEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getScreenName(), input.getEmail(), input.isExist(), serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			if (Validator.isNotNull(jsonObject.getString("duplicate"))
					&& jsonObject.getString("duplicate").equals(Boolean.TRUE.toString())) {

				return Response.status(409).entity(employeeAccountModel).build();

			} else {

				return Response.status(200).entity(employeeAccountModel).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response lockEmployeeAccount(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, boolean locked) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonObject = actions.lockEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					locked, serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			return Response.status(200).entity(employeeAccountModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getEmployeesByRole(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long roleId, DataSearchModel query) {
			EmployeeInterface actions = new EmployeeActions();
			EmployeeResults result = new EmployeeResults();
			try {

				long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
				
				List<User> users = UserLocalServiceUtil.getRoleUsers(roleId);
				StringBuilder strUserIdList = new StringBuilder();
				if (users != null && users.size() > 0) {
					int length = users.size();
					for (int i = 0; i < length; i++) {
						User userDetail = users.get(i);
						long userId = userDetail.getUserId();
						if (Validator.isNotNull(userId) && userId > 0) {
							if (i == length - 1) {
								strUserIdList.append(userId);
							} else {
								strUserIdList.append(userId);
								strUserIdList.append(StringPool.COMMA);
							}
						}
					}
				}

				if (query.getEnd() == 0) {

					query.setStart(-1);

					query.setEnd(-1);

				}

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

				params.put("groupId", String.valueOf(groupId));
				params.put("keywords", query.getKeywords());
				params.put("userIdList", strUserIdList.toString());

				Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
						Boolean.valueOf(query.getOrder())) };

				JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
						query.getStart(), query.getEnd(), serviceContext);

				result.setTotal(jsonData.getLong("total"));
				result.getEmployeeModel().addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get("data")));

				return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getEmployeesByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String itemCode, DataSearchModel query) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeResults result = new EmployeeResults();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			String serverNo = SERVER + itemCode;
//			String jobPos = query.getJobpos();
			ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, serverNo);

			long groupIdEmp = 0;
			if (serverConfig != null) {
				String config = serverConfig.getConfigs();
				if (Validator.isNotNull(config)) {
					JSONObject jsonConfig = JSONFactoryUtil.createJSONObject(config);
					if (jsonConfig != null) {
						groupIdEmp = GetterUtil.getLong(jsonConfig.getString("groupId"));
					}
				}
			}
			if (groupIdEmp > 0) {
				if (query.getEnd() == 0) {
					query.setStart(-1);
					query.setEnd(-1);
				}

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

				params.put("groupId", String.valueOf(groupIdEmp));
				params.put("keywords", query.getKeywords());
				params.put(EmployeeTerm.WORKING_UNIT_ID, query.getWorkingunit());
				params.put(EmployeeTerm.JOB_POS_ID, query.getJobpos());
				params.put(EmployeeTerm.WORKING_STATUS, query.getStatus());
				params.put(EmployeeTerm.FULL_NAME, query.getEmployeeName());

				Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
						Boolean.valueOf(query.getOrder())) };

				JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
						query.getStart(), query.getEnd(), serviceContext);

//				int total = 0;
//				if (jsonData != null && jsonData.getLong("total") > 0) {
					// if (Validator.isNotNull(jobPos)) {
					// List<Document> docList = (List<Document>)
					// jsonData.get("data");
					// if (docList != null && docList.size() > 0) {
					// for (Document document : docList) {
					// String jobPosCode =
					// GetterUtil.getString(document.get(""));
					// }
					// } else {
					// result.setTotal(0);
					// }
					// } else {
					// result.setTotal(jsonData.getLong("total"));
					// result.getEmployeeModel().addAll(EmployeeUtils.mapperEmployeeList((List<Document>)
					// jsonData.get("data")));
					// }
					// } else {
					// result.setTotal(jsonData.getLong("total"));
					// }
					result.setTotal(jsonData.getLong("total"));
					result.getEmployeeModel()
							.addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get("data")));
//				} else {
//					result.setTotal(0);
//				}
			}
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
