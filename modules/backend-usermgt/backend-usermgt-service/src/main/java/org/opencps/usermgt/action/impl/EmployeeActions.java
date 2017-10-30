package org.opencps.usermgt.action.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.Constants;
import backend.utils.FileUploadUtils;
import backend.utils.SendMailUtils;

public class EmployeeActions implements EmployeeInterface {

	public static Locale locale = new Locale("vi", "VN");
	private static final Log _log = LogFactoryUtil.getLog(EmployeeActions.class);

	@Override
	public JSONObject getEmployees(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = EmployeeLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = EmployeeLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public Employee create(long userId, long companyId, long groupId, String employeeNo, String fullName, String email,
			String gender, Date birthDate, String telNo, String mobile, String title, String workingStatus,
			ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateEmployeeNoException, DuplicateEmployeeEmailException, PortalException {
		Employee ett = null;

		ett = EmployeeLocalServiceUtil.addEmployee(userId, groupId, fullName, employeeNo, GetterUtil.get(gender, 0),
				birthDate, telNo, mobile, email, GetterUtil.get(workingStatus, 1), 0l, title, false, serviceContext);

		return ett;
	}

	@Override
	public Employee update(long userId, long companyId, long groupId, long id, String employeeNo, String fullName,
			String email, String gender, Date birthDate, String telNo, String mobile, String title,
			String workingStatus, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateEmployeeNoException, DuplicateEmployeeEmailException, PortalException {

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		if (Validator.isNotNull(employeeNo)) {
			employee.setEmployeeNo(employeeNo);
		}

		if (Validator.isNotNull(fullName)) {
			employee.setFullName(fullName);
		}

		if (Validator.isNotNull(email)) {
			employee.setEmail(email);
		}

		if (Validator.isNotNull(gender)) {
			employee.setGender(GetterUtil.get(gender, 0));
		}

		if (Validator.isNotNull(birthDate)) {
			employee.setBirthdate(birthDate);
		}

		if (Validator.isNotNull(telNo)) {
			employee.setTelNo(telNo);
		}

		if (Validator.isNotNull(mobile)) {
			employee.setMobile(mobile);
		}

		if (Validator.isNotNull(title)) {
			employee.setTitle(title);
		}

		if (Validator.isNotNull(workingStatus)) {
			employee.setWorkingStatus(GetterUtil.get(workingStatus, 0));
		}
		employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(), employee.getFullName(),
				employee.getEmployeeNo(), employee.getGender(), employee.getBirthdate(), employee.getTelNo(),
				employee.getMobile(), employee.getEmail(), employee.getWorkingStatus(), employee.getMainJobPostId(),
				employee.getPhotoFileEntryId(), employee.getMappingUserId(), employee.getTitle(), serviceContext);

		return employee;
	}

	@Override
	public File getEmployeePhoto(long id, ServiceContext serviceContext) {
		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		long fileEntryId = employee.getPhotoFileEntryId();

		FileEntry fileEntry;

		File file = null;
		try {

			fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

			file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

		} catch (PortalException e) {
			_log.error(e);
		}

		return file;
	}

	@Override
	public FileEntry getFileEntry(long id, ServiceContext serviceContext) {
		FileEntry fileEntry = null;

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		try {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(employee.getPhotoFileEntryId());
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return fileEntry;
	}

	@Override
	public File uploadEmployeePhoto(long userId, long companyId, long groupId, long id, InputStream inputStream,
			String fileName, String fileType, long fileSize, String destination, String desc,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException, PortalException {
		File file = null;

		FileEntry fileEntry;
		try {

			fileEntry = FileUploadUtils.uploadFile(userId, companyId, groupId, inputStream, fileName, fileType,
					fileSize, destination, desc, serviceContext);

			Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

			employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(), employee.getFullName(),
					employee.getEmployeeNo(), employee.getGender(), employee.getBirthdate(), employee.getTelNo(),
					employee.getMobile(), employee.getEmail(), employee.getWorkingStatus(), employee.getMainJobPostId(),
					fileEntry.getFileEntryId(), employee.getMappingUserId(), employee.getTitle(), serviceContext);

			file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	@Override
	public EmployeeJobPos createEmployeeJobpos(long userId, long companyId, long groupId, long id, long workingUnitId,
			long jobPosId, Boolean isMain, ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException, PortalException {
		EmployeeJobPos ett = null;

		ett = EmployeeJobPosLocalServiceUtil.addEmployeeJobPos(userId, groupId, id, jobPosId, workingUnitId,
				serviceContext);

		if (isMain) {

			Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ett.getEmployeeId());

			employee.setMainJobPostId(jobPosId);

			employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(), employee.getFullName(),
					employee.getEmployeeNo(), employee.getGender(), employee.getBirthdate(), employee.getTelNo(),
					employee.getMobile(), employee.getEmail(), employee.getWorkingStatus(), employee.getMainJobPostId(),
					employee.getPhotoFileEntryId(), employee.getMappingUserId(), employee.getTitle(), serviceContext);

		}

		return ett;
	}

	@Override
	public EmployeeJobPos updateEmployeeJobpos(long userId, long companyId, long groupId, long id,
			long employeeJobPosId, long workingUnitId, long jobPosId, Boolean isMain, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException, PortalException {
		EmployeeJobPos ett = EmployeeJobPosLocalServiceUtil.fetchEmployeeJobPos(employeeJobPosId);

		ett.setWorkingUnitId(workingUnitId);
		ett.setJobPostId(jobPosId);

		ett = EmployeeJobPosLocalServiceUtil.updateEmployeeJobPos(userId, ett.getEmployeeJobPosId(), ett.getJobPostId(),
				ett.getWorkingUnitId(), serviceContext);

		if (isMain) {

			Employee employee = EmployeeLocalServiceUtil.fetchEmployee(ett.getEmployeeId());

			employee.setMainJobPostId(jobPosId);

			employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(), employee.getFullName(),
					employee.getEmployeeNo(), employee.getGender(), employee.getBirthdate(), employee.getTelNo(),
					employee.getMobile(), employee.getEmail(), employee.getWorkingStatus(), employee.getMainJobPostId(),
					employee.getPhotoFileEntryId(), employee.getMappingUserId(), employee.getTitle(), serviceContext);

		}

		return ett;
	}

	@Override
	public JSONObject getEmployeeJobpos(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = EmployeeJobPosLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = EmployeeJobPosLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject createEmployeeAccount(long userId, long companyId, long groupId, long id, String screenName,
			String email, boolean exist, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateEmployeeEmailException, DuplicateEmployeeNoException, PortalException {

		if (Validator.isNull(screenName)) {
			screenName = email.substring(0, email.indexOf("@"));
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);

		if (Validator.isNotNull(employee) && employee.getMappingUserId() > 0) {
			throw new DuplicateCategoryException();
		} else {

			if (exist) {

				jsonObject.put("screenName", user.getScreenName());
				jsonObject.put("email", user.getEmailAddress());
				jsonObject.put("exist", exist);
				jsonObject.put("duplicate", Boolean.FALSE.toString());

				employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(),
						employee.getFullName(), employee.getEmployeeNo(), employee.getGender(), employee.getBirthdate(),
						employee.getTelNo(), employee.getMobile(), employee.getEmail(), employee.getWorkingStatus(),
						employee.getMainJobPostId(), employee.getPhotoFileEntryId(), user.getUserId(),
						employee.getTitle(), serviceContext);

			} else {

				try {

					long[] userGroupIds = {};
					long[] roleIds = {};

					Role role = RoleLocalServiceUtil.fetchRole(companyId, "employee");

					if (Validator.isNotNull(role)) {
						roleIds = new long[] { role.getRoleId() };
					}

					long[] organizationIds = new long[] {};
					long[] groupIds = { groupId };

					String passWord = PwdGenerator.getPassword();

					User newUser = UserLocalServiceUtil.addUser(0, companyId, false, passWord, passWord, false,
							screenName.toLowerCase(), email, 0, StringPool.BLANK, serviceContext.getLocale(),
							screenName, StringPool.BLANK, screenName, 0, 0, true, Calendar.JANUARY, 1, 1979,
							StringPool.BLANK, groupIds, organizationIds, roleIds, userGroupIds, false, serviceContext);

					Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

					indexer.reindex(newUser);

					employee.setMappingUserId(newUser.getUserId());

					employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(),
							employee.getFullName(), employee.getEmployeeNo(), employee.getGender(),
							employee.getBirthdate(), employee.getTelNo(), employee.getMobile(), employee.getEmail(),
							employee.getWorkingStatus(), employee.getMainJobPostId(), employee.getPhotoFileEntryId(),
							employee.getMappingUserId(), employee.getTitle(), serviceContext);

					User fromUser = UserLocalServiceUtil.fetchUser(userId);

					JSONObject payLoad = JSONFactoryUtil.createJSONObject();
					
					payLoad.put("USERNAME", newUser.getScreenName());
					payLoad.put("USEREMAIL", newUser.getEmailAddress());
					payLoad.put("PASSWORD", passWord);
					
					NotificationQueueLocalServiceUtil.addNotificationQueue(userId, groupId, Constants.USER_01,
							User.class.getName(), String.valueOf(newUser.getUserId()),
							payLoad.toJSONString(), fromUser.getFullName(), employee.getFullName(),
							employee.getMappingUserId(), employee.getEmail(), employee.getTelNo(), new Date(),
							null, serviceContext);

					jsonObject.put("screenName", newUser.getScreenName());
					jsonObject.put("email", newUser.getEmailAddress());
					jsonObject.put("exist", exist);
					jsonObject.put("duplicate", Boolean.FALSE.toString());

				} catch (Exception e) {
					jsonObject.put("screenName", user.getScreenName());
					jsonObject.put("email", user.getEmailAddress());
					jsonObject.put("exist", Boolean.TRUE);
					jsonObject.put("duplicate", Boolean.TRUE.toString());
				}

			}

		}

		return jsonObject;
	}

	@Override
	public JSONObject lockEmployeeAccount(long userId, long companyId, long groupId, long id, boolean locked,
			ServiceContext serviceContext) throws PortalException {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		if (Validator.isNotNull(employee) && employee.getMappingUserId() < 0) {
			throw new NoSuchUserException();
		} else {

			User user = UserLocalServiceUtil.fetchUser(employee.getMappingUserId());

			if (locked) {
				user.setStatus(WorkflowConstants.STATUS_DENIED);
			} else {
				user.setStatus(WorkflowConstants.STATUS_APPROVED);
			}

			UserLocalServiceUtil.updateUser(user);

			Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

			jsonObject.put("screenName", user.getScreenName());
			jsonObject.put("email", user.getEmailAddress());
			jsonObject.put("exist", true);
			
			JSONObject payLoad = JSONFactoryUtil.createJSONObject();
			
			payLoad.put("USERNAME", user.getScreenName());
			payLoad.put("USEREMAIL", user.getEmailAddress());
			payLoad.put("USERSTATUS", user.getStatus());
			
			NotificationQueueLocalServiceUtil.addNotificationQueue(user.getUserId(), groupId, Constants.USER_02,
					User.class.getName(), String.valueOf(user.getUserId()),
					payLoad.toJSONString(), "SYSTEM", user.getFullName(),
					employee.getMappingUserId(), employee.getEmail(), employee.getTelNo(), new Date(),
					null, serviceContext);
			
		}

		return jsonObject;
	}

	@Override
	public void validateExits(long userId, long companyId, long groupId, String employeeNo, String email,
			ServiceContext serviceContext) throws DuplicateEmployeeEmailException, DuplicateEmployeeNoException {
		EmployeeLocalServiceUtil.isExits(groupId, employeeNo, email);
	}

}
