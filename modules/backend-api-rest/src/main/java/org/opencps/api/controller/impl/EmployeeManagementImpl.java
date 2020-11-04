package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import java.net.HttpURLConnection;
import java.util.ArrayList;
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
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.EmployeeManagement;
import org.opencps.api.controller.util.EmployeeUtils;
import org.opencps.api.controller.util.MessageUtil;
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
import org.opencps.api.user.model.UserResults;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.action.UserInterface;
import org.opencps.usermgt.action.impl.EmployeeActions;
import org.opencps.usermgt.action.impl.UserActions;
import org.opencps.usermgt.constants.EmployeeJobPosTerm;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.persistence.EmployeeUtil;
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
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(EmployeeTerm.WORKING_UNIT_ID, query.getWorkingunit());
			params.put(EmployeeTerm.JOB_POS_ID, query.getJobpos());
			params.put(EmployeeTerm.WORKING_STATUS, query.getStatus());

			String jobposCode = query.getJobposCode();
			String jobposCodeSearch = StringPool.BLANK;
			if (Validator.isNotNull(jobposCode)) {
				jobposCodeSearch = SpecialCharacterUtils.splitSpecial(jobposCode);
			}
			params.put(EmployeeTerm.JOB_POS_CODE_SEARCH, jobposCodeSearch);

			String scopes = query.getScope();
			//String scopesSearch = StringPool.BLANK;
			if (Validator.isNotNull(jobposCode)) {
				String scopesSearch = SpecialCharacterUtils.splitSpecial(scopes);
				params.put(EmployeeTerm.SCOPES_SEARCH, scopesSearch);
			} else {
				params.put(EmployeeTerm.SCOPES_SEARCH, StringPool.BLANK);
			}

			if(Validator.isNotNull(query.getActive())){
				params.put(EmployeeTerm.ACTIVE,
						query.getActive().equals(Boolean.TRUE.toString())
								? String.valueOf(WorkflowConstants.STATUS_APPROVED)
								: String.valueOf(WorkflowConstants.STATUS_DENIED));
			}
			
			params.put(EmployeeTerm.MONTH, query.getMonth());

			_log.info("EmployeeManagementImpl.getEmployees()"+params);
			
			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
					query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getEmployeeModel().addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(employeeModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, EmployeeInputModel input) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeModel employeeModel = new EmployeeModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			Date birthdate = DateTimeUtils.convertStringToDateAPI(input.getBirthdate());
			Date recruitDate = DateTimeUtils.convertStringToDateAPI(input.getRecruitDate());
			Date leaveDate = DateTimeUtils.convertStringToDateAPI(input.getLeaveDate());

			String fullName = HtmlUtil.escape(input.getFullName());
			String employeeNo = HtmlUtil.escape(input.getEmployeeNo());
			String telNo = HtmlUtil.escape(input.getTelNo());
			String mobile = HtmlUtil.escape(input.getMobile());
			String email = HtmlUtil.escape(input.getEmail());
			String title = HtmlUtil.escape(input.getTitle());
			
			Employee employee = actions.create(user.getUserId(), company.getCompanyId(), groupId, employeeNo,
					fullName, email, input.getGender(), birthdate, telNo,
					mobile, title, input.getWorkingStatus(), recruitDate, leaveDate,
					serviceContext);

			employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(HttpURLConnection.HTTP_OK).entity(employeeModel).build();

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			_log.info("Log info" + input.getBirthdate());
			Date birthdate = APIDateTimeUtils
					.convertStringToDate(input.getBirthdate(), APIDateTimeUtils._NORMAL_DATE);
//			Date birthdate = DateTimeUtils.convertStringToDate(input.getBirthdate());
			Date recruitDate = DateTimeUtils.convertStringToDateAPI(input.getRecruitDate());
			Date leaveDate = DateTimeUtils.convertStringToDateAPI(input.getLeaveDate());

			String fullName = HtmlUtil.escape(input.getFullName());
			String employeeNo = HtmlUtil.escape(input.getEmployeeNo());
			String telNo = HtmlUtil.escape(input.getTelNo());
			String mobile = HtmlUtil.escape(input.getMobile());
			String email = HtmlUtil.escape(input.getEmail());
			String title = HtmlUtil.escape(input.getTitle());

			Employee employee = actions.update(user.getUserId(), company.getCompanyId(), groupId, id,
					employeeNo, fullName, email, input.getGender(), birthdate,
					telNo, mobile, title, input.getWorkingStatus(), recruitDate,
					leaveDate, serviceContext);

			employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(HttpURLConnection.HTTP_OK).entity(employeeModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		try {

			EmployeeLocalServiceUtil.deleteEmployee(id, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).build();

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
				String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileName);
				responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
						.header(HttpHeaders.CONTENT_TYPE, fileEntry.getMimeType());

				return responseBuilder.build();
			}else{
//				ErrorMsg error = new ErrorMsg();
//				error.setMessage("file not found!");
//				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
//				error.setDescription("file not found!");
				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(StringPool.BLANK).build();
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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			inputStream = dataHandler.getInputStream();

			File file = actions.uploadEmployeePhoto(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, ConstantUtils.EMPLOYEE_OFFICESITE_FOLDER, ConstantUtils.EMPLOYEE_OFFICESITE_DESC, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileNameRespone = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileNameRespone);
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(HttpHeaders.CONTENT_TYPE, fileEntry.getMimeType());

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
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(EmployeeJobPosTerm.EMPLOYEE_ID, String.valueOf(id));

			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getEmployeeJobpos(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getEmployeeJobposModel()
					.addAll(EmployeeUtils.mapperEmployeeJobposList((List<Document>) jsonData.get(ConstantUtils.DATA), id));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			EmployeeJobPos employeeJobPos = actions.createEmployeeJobpos(user.getUserId(), company.getCompanyId(),
					groupId, id, input.getWorkingUnitId(), input.getJobPosId(), Boolean.valueOf(input.getMainJobPos()),
					serviceContext);

			employeeJobposModel = EmployeeUtils.mapperEmployeeJobposModel(employeeJobPos);

			return Response.status(HttpURLConnection.HTTP_OK).entity(employeeJobposModel).build();

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			EmployeeJobPos employeeJobPos = actions.updateEmployeeJobpos(user.getUserId(), company.getCompanyId(),
					groupId, id, employeeJobPosId, input.getWorkingUnitId(), input.getJobPosId(),
					Boolean.valueOf(input.getMainJobPos()), serviceContext);

			employeeJobposModel = EmployeeUtils.mapperEmployeeJobposModel(employeeJobPos);

			return Response.status(HttpURLConnection.HTTP_OK).entity(employeeJobposModel).build();

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

			return Response.status(HttpURLConnection.HTTP_OK).build();

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			JSONObject jsonObject = actions.createEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getScreenName(), input.getEmail(), input.isExist(), serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			if (Validator.isNotNull(jsonObject.getString(ConstantUtils.EMPLOYEE_VALID_JSON_DUPLICATE))
					&& jsonObject.getString(ConstantUtils.EMPLOYEE_VALID_JSON_DUPLICATE).equals(Boolean.TRUE.toString())) {

				return Response.status(HttpURLConnection.HTTP_CONFLICT).entity(employeeAccountModel).build();

			} else {

				return Response.status(HttpURLConnection.HTTP_OK).entity(employeeAccountModel).build();

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			JSONObject jsonObject = actions.lockEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					locked, serviceContext);

			employeeAccountModel = EmployeeUtils.mapperEmployeeAccountModel(jsonObject);

			return Response.status(200).entity(employeeAccountModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response unlockEmployeeAccount(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, boolean unlocked) {
		EmployeeInterface actions = new EmployeeActions();
		EmployeeAccountModel employeeAccountModel = new EmployeeAccountModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			JSONObject jsonObject = actions.unlockEmployeeAccount(user.getUserId(), company.getCompanyId(), groupId, id,
					unlocked, serviceContext);

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

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			actions.validateExits(user.getUserId(), company.getCompanyId(), groupId, employeeNo, email, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).build();

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

				long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
				
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

					query.setStart(QueryUtil.ALL_POS);

					query.setEnd(QueryUtil.ALL_POS);

				}

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

				params.put(Field.GROUP_ID, String.valueOf(groupId));
				params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
				params.put(ConstantUtils.EMPLOYEE_JSON_USERIDLIST, strUserIdList.toString());

				String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
				Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
						Boolean.valueOf(query.getOrder())) };

				JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
						query.getStart(), query.getEnd(), serviceContext);

				result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
				result.getEmployeeModel().addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get(ConstantUtils.DATA)));

				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getEmployeeByGroupId(HttpServletRequest request, HttpHeaders header, Company company,
										 Locale locale, User user, ServiceContext serviceContext) {

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			Employee employee = EmployeeUtil.fetchByF_mappingUserId(groupId,user.getUserId());

			EmployeeModel employeeModel = EmployeeUtils.mapperEmployeeModel(employee);

			return Response.status(HttpURLConnection.HTTP_OK).entity(employeeModel).build();

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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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
						groupIdEmp = GetterUtil.getLong(jsonConfig.getString(Field.GROUP_ID));
					}
				}
			}
			if (groupIdEmp > 0) {
				String jobPosCode = query.getJobposCode();
				if (Validator.isNotNull(jobPosCode)) {

					long total = 0;
					List<EmployeeModel> empModelList = new ArrayList<EmployeeModel>();
					JobPos jobPos = JobPosLocalServiceUtil.getByJobCode(groupIdEmp, jobPosCode);
					if (jobPos != null) {
						List<EmployeeJobPos> empJobList = EmployeeJobPosLocalServiceUtil.getByJobPostId(groupIdEmp, jobPos.getJobPosId());
						if (empJobList != null && empJobList.size() > 0) {
							for (EmployeeJobPos employeeJobPos : empJobList) {
								Employee emp = EmployeeLocalServiceUtil.fetchEmployee(employeeJobPos.getEmployeeId());
								if (emp != null) {
									total += 1;
									empModelList.add(EmployeeUtils.mapperEmployeeModel(emp));
								}
							}
						}
					}
					
					result.setTotal(total);
					result.getEmployeeModel().addAll(empModelList);
					
				} else {
					if (query.getEnd() == 0) {
						query.setStart(QueryUtil.ALL_POS);
						query.setEnd(QueryUtil.ALL_POS);
					}

					LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

					params.put(Field.GROUP_ID, String.valueOf(groupIdEmp));
					params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
					params.put(EmployeeTerm.WORKING_UNIT_ID, query.getWorkingunit());
					params.put(EmployeeTerm.JOB_POS_ID, query.getJobpos());
					params.put(EmployeeTerm.WORKING_STATUS, query.getStatus());
					params.put(EmployeeTerm.FULL_NAME, query.getEmployeeName());

					String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
					Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
							Boolean.valueOf(query.getOrder())) };

					JSONObject jsonData = actions.getEmployees(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
							query.getStart(), query.getEnd(), serviceContext);

					result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
					result.getEmployeeModel()
							.addAll(EmployeeUtils.mapperEmployeeList((List<Document>) jsonData.get(ConstantUtils.DATA)));
				}
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
