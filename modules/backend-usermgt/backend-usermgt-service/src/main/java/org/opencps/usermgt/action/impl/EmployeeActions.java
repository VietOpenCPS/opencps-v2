package org.opencps.usermgt.action.impl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.action.EmployeeInterface;
import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.Constants;
import backend.utils.FileUploadUtils;

public class EmployeeActions implements EmployeeInterface {

	private static final Locale locale = new Locale("vi", "VN");
	private static Log _log = LogFactoryUtil.getLog(EmployeeActions.class);

	@Override
	public JSONObject getEmployees(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = EmployeeLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = EmployeeLocalServiceUtil.countLuceneSearchEngine(
				params, searchContext);

			result.put("total", total);

		}
		catch (ParseException e) {
			_log.error(e);
		}
		catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public Employee create(
		long userId, long companyId, long groupId, String employeeNo,
		String fullName, String email, String gender, Date birthDate,
		String telNo, String mobile, String title, String workingStatus,
		Date recruitDate, Date leaveDate, ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateEmployeeNoException,
		DuplicateEmployeeEmailException, PortalException {

		Employee ett = null;

		ett = EmployeeLocalServiceUtil.addEmployee(
			userId, groupId, fullName, employeeNo, GetterUtil.get(gender, 0),
			birthDate, telNo, mobile, email, GetterUtil.get(workingStatus, 1),
			0l, title, false, recruitDate, leaveDate, serviceContext);

		return ett;
	}

	@Override
	public Employee update(
		long userId, long companyId, long groupId, long id, String employeeNo,
		String fullName, String email, String gender, Date birthDate,
		String telNo, String mobile, String title, String workingStatus,
		Date recruitDate, Date leaveDate, ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
		PortalException {

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

		if (Validator.isNotNull(recruitDate)) {
			employee.setRecruitDate(recruitDate);
		}

		if (Validator.isNotNull(leaveDate)) {
			employee.setLeaveDate(leaveDate);
		}

		employee = EmployeeLocalServiceUtil.updateEmployee(
			userId, employee.getEmployeeId(), employee.getFullName(),
			employee.getEmployeeNo(), employee.getGender(),
			employee.getBirthdate(), employee.getTelNo(), employee.getMobile(),
			employee.getEmail(), employee.getWorkingStatus(),
			employee.getMainJobPostId(), employee.getPhotoFileEntryId(),
			employee.getMappingUserId(), employee.getTitle(),
			employee.getRecruitDate(), employee.getLeaveDate(), serviceContext);

		return employee;
	}

	@Override
	public File getEmployeePhoto(long id, ServiceContext serviceContext) {

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		long fileEntryId = employee.getPhotoFileEntryId();

		FileEntry fileEntry;

		File file = null;
		try {

			if (fileEntryId > 0) {

				fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

				file = DLFileEntryLocalServiceUtil.getFile(
					fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

			}

		}
		catch (PortalException e) {
			_log.error(
				"Can not get Employee photo employeeId = " + id + " | " + e);
		}

		return file;
	}

	@Override
	public FileEntry getFileEntry(long id, ServiceContext serviceContext) {

		FileEntry fileEntry = null;

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		try {
			if (employee.getPhotoFileEntryId() > 0) {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(
					employee.getPhotoFileEntryId());
			}
		}
		catch (PortalException e) {
			_log.error(
				"Can not get employee photo with employee.getPhotoFileEntryId() " +
					employee.getPhotoFileEntryId() + " | " + e);
		}

		return fileEntry;
	}

	@Override
	public File uploadEmployeePhoto(
		long userId, long companyId, long groupId, long id,
		InputStream inputStream, String fileName, String fileType,
		long fileSize, String destination, String desc,
		ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateCategoryException, PortalException {

		File file = null;

		FileEntry fileEntry;
		try {

			fileEntry = FileUploadUtils.uploadFile(
				userId, companyId, groupId, inputStream, fileName, fileType,
				fileSize, destination, desc, serviceContext);

			Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

			EmployeeLocalServiceUtil.updateEmployee(
				userId, employee.getEmployeeId(), employee.getFullName(),
				employee.getEmployeeNo(), employee.getGender(),
				employee.getBirthdate(), employee.getTelNo(),
				employee.getMobile(), employee.getEmail(),
				employee.getWorkingStatus(), employee.getMainJobPostId(),
				fileEntry.getFileEntryId(), employee.getMappingUserId(),
				employee.getTitle(), employee.getRecruitDate(),
				employee.getLeaveDate(), serviceContext);

			file = DLFileEntryLocalServiceUtil.getFile(
				fileEntry.getFileEntryId(), fileEntry.getVersion(), false);

		}
		catch (Exception e) {
			_log.error(e);
		}

		return file;
	}

	@Override
	public EmployeeJobPos createEmployeeJobpos(
		long userId, long companyId, long groupId, long id, long workingUnitId,
		long jobPosId, Boolean isMain, ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateCategoryException, PortalException {

		EmployeeJobPos ett = null;

		List<EmployeeJobPos> listJobPos =
			EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(id);

		if (Validator.isNull(listJobPos) || listJobPos.isEmpty()) {

			isMain = true;

		}

		ett = EmployeeJobPosLocalServiceUtil.addEmployeeJobPos(
			userId, groupId, id, jobPosId, workingUnitId, serviceContext);

		if (isMain) {

			Employee employee =
				EmployeeLocalServiceUtil.fetchEmployee(ett.getEmployeeId());

			employee.setMainJobPostId(jobPosId);

			EmployeeLocalServiceUtil.updateEmployee(
				userId, employee.getEmployeeId(), employee.getFullName(),
				employee.getEmployeeNo(), employee.getGender(),
				employee.getBirthdate(), employee.getTelNo(),
				employee.getMobile(), employee.getEmail(),
				employee.getWorkingStatus(), ett.getEmployeeJobPosId(),
				employee.getPhotoFileEntryId(), employee.getMappingUserId(),
				employee.getTitle(), employee.getRecruitDate(),
				employee.getLeaveDate(), serviceContext);

		}

		return ett;
	}

	@Override
	public EmployeeJobPos updateEmployeeJobpos(
		long userId, long companyId, long groupId, long id,
		long employeeJobPosId, long workingUnitId, long jobPosId,
		Boolean isMain, ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateCategoryException, PortalException {

		EmployeeJobPos ett = EmployeeJobPosLocalServiceUtil.fetchEmployeeJobPos(
			employeeJobPosId);

		ett.setWorkingUnitId(workingUnitId);
		ett.setJobPostId(jobPosId);

		ett = EmployeeJobPosLocalServiceUtil.updateEmployeeJobPos(
			userId, ett.getEmployeeJobPosId(), ett.getJobPostId(),
			ett.getWorkingUnitId(), serviceContext);

		if (isMain) {

			Employee employee =
				EmployeeLocalServiceUtil.fetchEmployee(ett.getEmployeeId());

			employee.setMainJobPostId(jobPosId);

			EmployeeLocalServiceUtil.updateEmployee(
				userId, employee.getEmployeeId(), employee.getFullName(),
				employee.getEmployeeNo(), employee.getGender(),
				employee.getBirthdate(), employee.getTelNo(),
				employee.getMobile(), employee.getEmail(),
				employee.getWorkingStatus(), ett.getEmployeeJobPosId(),
				employee.getPhotoFileEntryId(), employee.getMappingUserId(),
				employee.getTitle(), employee.getRecruitDate(),
				employee.getLeaveDate(), serviceContext);

		}

		return ett;
	}

	@Override
	public JSONObject getEmployeeJobpos(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = EmployeeJobPosLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = EmployeeJobPosLocalServiceUtil.countLuceneSearchEngine(
				params, searchContext);

			result.put("total", total);

		}
		catch (ParseException e) {
			_log.error(e);
		}
		catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject createEmployeeAccount(
		long userId, long companyId, long groupId, long id, String screenName,
		String email, boolean exist, ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateEmployeeEmailException, DuplicateEmployeeNoException,
		PortalException {

		if (Validator.isNull(screenName)) {
			screenName = email.substring(0, email.indexOf(StringPool.AT));
		}
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		User user =
			UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);

		List<Role> roleIdsInit = new ArrayList<Role>();

		List<EmployeeJobPos> listEmJobPos =
			EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(
				employee.getEmployeeId());

		for (EmployeeJobPos ett : listEmJobPos) {
			roleIdsInit.add(
				RoleLocalServiceUtil.fetchRole(
					JobPosLocalServiceUtil.fetchJobPos(
						ett.getJobPostId()).getMappingRoleId()));
		}

		if (Validator.isNotNull(employee) && employee.getMappingUserId() > 0) {
			throw new DuplicateCategoryException();
		}
		else {

			if (exist) {

				jsonObject.put(CommonTerm.SCREEN_NAME, user.getScreenName());
				jsonObject.put(CommonTerm.EMAIL, user.getEmailAddress());
				jsonObject.put(CommonTerm.EXITS, exist);
				jsonObject.put(CommonTerm.DUPLICATE, Boolean.FALSE.toString());

				EmployeeLocalServiceUtil.updateEmployee(
					userId, employee.getEmployeeId(), employee.getFullName(),
					employee.getEmployeeNo(), employee.getGender(),
					employee.getBirthdate(), employee.getTelNo(),
					employee.getMobile(), employee.getEmail(),
					employee.getWorkingStatus(), employee.getMainJobPostId(),
					employee.getPhotoFileEntryId(), user.getUserId(),
					employee.getTitle(), employee.getRecruitDate(),
					employee.getLeaveDate(), serviceContext);

				// TODO

				if (roleIdsInit.isEmpty() || Validator.isNull(roleIdsInit)) {
					Role role =
						RoleLocalServiceUtil.fetchRole(companyId, CommonTerm.EMPLOYEE);

					if (Validator.isNotNull(role)) {
						roleIdsInit.add(role);
					}

				}

				RoleLocalServiceUtil.deleteUserRoles(
					user.getUserId(), roleIdsInit);
				RoleLocalServiceUtil.clearUserRoles(user.getUserId());
				
				

				for (Role role : roleIdsInit) {
					try {
						RoleLocalServiceUtil.addUserRole(
							user.getUserId(), role.getRoleId());
					}
					catch (Exception e) {
						_log.error(e);
					}
				}

			}
			else {

				try {

					long[] userGroupIds = {};
					List<Long> roleIds = new ArrayList<>();

					Role role =
						RoleLocalServiceUtil.fetchRole(companyId, CommonTerm.EMPLOYEE);

					if (Validator.isNotNull(role)) {
						roleIds.add(role.getRoleId());
					}

					for (Role ett : roleIdsInit) {
						if (ett != null) {
							roleIds.add(ett.getRoleId());
						}
					}

					long[] resultRoles =
						roleIds.stream().mapToLong(l -> l).toArray();

					long[] organizationIds = new long[] {};
					long[] groupIds = {
						groupId,
						20143
					};

					//String passWord = PwdGenerator.getPassword();
					// changePassWord
					String secretKey1 = PwdGenerator.getPassword(2 , new String[] { "0123456789" });
					String secretKey2 = PwdGenerator.getPassword(2 , new String[] { "ABCDEFGHIJKLMNOPQRSTUVWXYZ" });
					String secretKey3 = PwdGenerator.getPassword(2 , new String[] { "abcdefghijklmnopqrstuvwxyz" });
					String secretKey4 = PwdGenerator.getPassword(1 , new String[] { "@$" });
					String secretKey5 = PwdGenerator.getPassword(4 , new String[] { "0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "abcdefghijklmnopqrstuvwxyz", "~!@#$%^&*" });
					String passWord = secretKey1 + secretKey2 + secretKey3 + secretKey4 + secretKey5;
					
					//_log.info("passWord:"+passWord);

					String fullName = employee.getFullName();
					String[] fml = new String[3];

					String[] splitName =
						StringUtil.split(fullName, StringPool.SPACE);

					if (splitName != null && splitName.length > 0) {
						fml[0] = splitName[0];
						fml[1] = splitName.length >= 3
							? StringUtil.merge(
								ArrayUtil.subset(
									splitName, 1, splitName.length - 1),
								StringPool.SPACE)
							: StringPool.BLANK;
						fml[2] = splitName.length >= 2
							? splitName[splitName.length - 1] : splitName[0];
					}
					else {
						fml[0] = screenName;
						fml[1] = StringPool.BLANK;
						fml[2] = screenName;
					}

					// _log.info("////////////////////////////////////////////////////
					// " + fml[0]);
					// _log.info("////////////////////////////////////////////////////
					// " + fml[1]);
					// _log.info("////////////////////////////////////////////////////
					// " + fml[2]);

					User newUser = UserLocalServiceUtil.addUser(
						0, companyId, false, passWord, passWord, false,
						screenName.toLowerCase(), email, 0, StringPool.BLANK,
						serviceContext.getLocale(), fml[0], fml[1], fml[2], 0,
						0, true, Calendar.JANUARY, 1, 1979, StringPool.BLANK,
						groupIds, organizationIds, resultRoles, userGroupIds,
						false, serviceContext);

					Indexer<User> indexer =
						IndexerRegistryUtil.nullSafeGetIndexer(User.class);

					indexer.reindex(newUser);

					employee.setMappingUserId(newUser.getUserId());

					employee = EmployeeLocalServiceUtil.updateEmployee(
						userId, employee.getEmployeeId(),
						employee.getFullName(), employee.getEmployeeNo(),
						employee.getGender(), employee.getBirthdate(),
						employee.getTelNo(), employee.getMobile(),
						employee.getEmail(), employee.getWorkingStatus(),
						employee.getMainJobPostId(),
						employee.getPhotoFileEntryId(),
						employee.getMappingUserId(), employee.getTitle(),
						employee.getRecruitDate(), employee.getLeaveDate(),
						serviceContext);

					User fromUser = UserLocalServiceUtil.fetchUser(userId);

					JSONObject payLoad = JSONFactoryUtil.createJSONObject();

					payLoad.put(CommonTerm.USER_NAME, newUser.getScreenName());
					payLoad.put(CommonTerm.USER_EMAIL, newUser.getEmailAddress());
					payLoad.put(CommonTerm.PASS_WORD, passWord);

					NotificationQueueLocalServiceUtil.addNotificationQueue(
						userId, groupId, Constants.USER_01,
						User.class.getName(),
						String.valueOf(newUser.getUserId()),
						payLoad.toJSONString(), fromUser.getFullName(),
						employee.getFullName(), employee.getMappingUserId(),
						employee.getEmail(), employee.getTelNo(), new Date(),
						null, serviceContext);

					jsonObject.put(CommonTerm.SCREEN_NAME, newUser.getScreenName());
					jsonObject.put(CommonTerm.EMAIL, newUser.getEmailAddress());
					jsonObject.put(CommonTerm.EXITS, exist);
					jsonObject.put(CommonTerm.DUPLICATE, Boolean.FALSE.toString());
				}
				catch (Exception e) {
					_log.error(e);
					jsonObject.put(CommonTerm.SCREEN_NAME, user.getScreenName());
					jsonObject.put(CommonTerm.EMAIL, user.getEmailAddress());
					jsonObject.put(CommonTerm.EXITS, Boolean.TRUE);
					jsonObject.put(CommonTerm.DUPLICATE, Boolean.TRUE.toString());
				}

			}

		}

		return jsonObject;
	}

	@Override
	public JSONObject lockEmployeeAccount(
		long userId, long companyId, long groupId, long id, boolean locked,
		ServiceContext serviceContext)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		// Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);
		Employee employee = EmployeeLocalServiceUtil.getEmployee(id);

		if (Validator.isNotNull(employee) && employee.getMappingUserId() < 0) {

			throw new NoSuchUserException();
		}
		else {
			User user =
				UserLocalServiceUtil.fetchUser(employee.getMappingUserId());

			if (locked) {
				user.setStatus(WorkflowConstants.STATUS_INACTIVE);
			}
			else {
				user.setStatus(WorkflowConstants.STATUS_APPROVED);
			}

			UserLocalServiceUtil.updateUser(user);

			Indexer<User> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

			jsonObject.put(CommonTerm.SCREEN_NAME, user.getScreenName());
			jsonObject.put(CommonTerm.EMAIL, user.getEmailAddress());
			jsonObject.put(CommonTerm.EXITS, true);

			JSONObject payLoad = JSONFactoryUtil.createJSONObject();

			payLoad.put(CommonTerm.USER_NAME, user.getScreenName());
			payLoad.put(CommonTerm.USER_EMAIL, user.getEmailAddress());
			payLoad.put(
					CommonTerm.USER_STATUS,
					LanguageUtil.get(locale, "user-status-" + user.getStatus()));

			NotificationQueueLocalServiceUtil.addNotificationQueue(
				user.getUserId(), groupId, Constants.USER_02,
				User.class.getName(), String.valueOf(user.getUserId()),
				payLoad.toJSONString(), "SYSTEM", user.getFullName(),
				employee.getMappingUserId(), employee.getEmail(),
				employee.getTelNo(), new Date(), null, serviceContext);

		}

		return jsonObject;
	}

	@Override
	public void validateExits(
		long userId, long companyId, long groupId, String employeeNo,
		String email, ServiceContext serviceContext)
		throws DuplicateEmployeeEmailException, DuplicateEmployeeNoException {

		EmployeeLocalServiceUtil.isExits(groupId, employeeNo, email);
	}

	@Override
	public void deleteEmployeeJobPos(
		long id, long employeeJobPosId, ServiceContext serviceContext)
		throws NoSuchUserException, DuplicateEmployeeEmailException,
		DuplicateEmployeeNoException, PortalException {

		Employee employee = EmployeeLocalServiceUtil.fetchEmployee(id);

		EmployeeJobPosLocalServiceUtil.deleteEmployeeJobPos(
			employeeJobPosId, serviceContext);

		if (employee.getMainJobPostId() == employeeJobPosId) {

			employee.setMainJobPostId(0);

			EmployeeLocalServiceUtil.updateEmployee(
				employee.getUserId(), employee.getEmployeeId(),
				employee.getFullName(), employee.getEmployeeNo(),
				employee.getGender(), employee.getBirthdate(),
				employee.getTelNo(), employee.getMobile(), employee.getEmail(),
				employee.getWorkingStatus(), employee.getMainJobPostId(),
				employee.getPhotoFileEntryId(), employee.getMappingUserId(),
				employee.getTitle(), employee.getRecruitDate(),
				employee.getLeaveDate(), serviceContext);

		}

	}

	public Employee getEmployeeByMappingUserId(
		long groupId, long mappingUserId) {

		return EmployeeLocalServiceUtil.fetchByF_mappingUserId(
			groupId, mappingUserId);
	}

	@Override
	public JSONObject lockEmployeeAccount(
		Employee employee, boolean locked, ServiceContext serviceContext)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(employee) && employee.getMappingUserId() > 0) {

			User user =
				UserLocalServiceUtil.fetchUser(employee.getMappingUserId());

			if (locked) {
				user.setStatus(WorkflowConstants.STATUS_INACTIVE);
			}
			else {
				user.setStatus(WorkflowConstants.STATUS_APPROVED);
			}

			UserLocalServiceUtil.updateUser(user);

			Indexer<User> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(User.class);

			indexer.reindex(user);

			jsonObject.put("screenName", user.getScreenName());
			jsonObject.put("email", user.getEmailAddress());
			jsonObject.put("exist", true);

			JSONObject payLoad = JSONFactoryUtil.createJSONObject();

			payLoad.put("USERNAME", user.getScreenName());
			payLoad.put("USEREMAIL", user.getEmailAddress());
			payLoad.put(
				"USERSTATUS",
				LanguageUtil.get(locale, "user-status-" + user.getStatus()));

			NotificationQueueLocalServiceUtil.addNotificationQueue(
				user.getUserId(), employee.getGroupId(), Constants.USER_02,
				User.class.getName(), String.valueOf(user.getUserId()),
				payLoad.toJSONString(), "SYSTEM", user.getFullName(),
				employee.getMappingUserId(), employee.getEmail(),
				employee.getTelNo(), new Date(), null, serviceContext);

		}

		return jsonObject;
	}

	private static final String DATE_PATTERN = "dd/MM/yyyy";
	@Override
	public void updateEmployeeDB(long userId, long groupId, String employeeNo, String fullName, String title,
			Integer gender, String birthDate, String telNo, String email, Integer workingStatus, String jobTitle,
			String roles, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException, PortalException {

		// Convert String to Date
		Date birthDay = null;
		if (Validator.isNotNull(birthDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			try {
				birthDay = sdf.parse(birthDate);
			} catch (java.text.ParseException e) {
			}
		}
		
		Employee employee = EmployeeLocalServiceUtil.getEmployeeByEmpNo(groupId, employeeNo);
		boolean isNew = false;

		//Update or create Employee
		if (employee == null) {
			employee = EmployeeLocalServiceUtil.addEmployee(
					userId, groupId, fullName, employeeNo, GetterUtil.get(gender, 0),
					birthDay, telNo, null, email, GetterUtil.get(workingStatus, 0),
					0l, title, false, null, null, serviceContext);
			isNew = true;
		} else {
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
				employee.setBirthdate(birthDay);
			}
			if (Validator.isNotNull(telNo)) {
				employee.setTelNo(telNo);
			}
			if (Validator.isNotNull(title)) {
				employee.setTitle(title);
			}
			if (Validator.isNotNull(workingStatus)) {
				employee.setWorkingStatus(GetterUtil.get(workingStatus, 0));
			}

			employee = EmployeeLocalServiceUtil.updateEmployee(
				userId, employee.getEmployeeId(), employee.getFullName(),
				employee.getEmployeeNo(), employee.getGender(),
				employee.getBirthdate(), employee.getTelNo(), employee.getMobile(),
				employee.getEmail(), employee.getWorkingStatus(),
				employee.getMainJobPostId(), employee.getPhotoFileEntryId(),
				employee.getMappingUserId(), employee.getTitle(),
				employee.getRecruitDate(), employee.getLeaveDate(), serviceContext);
		}
		//_log.info("Employee Create: "+employee);

		//Check exits account and create new account
		if (isNew) {
			employee = createNewEmployeeAccount(userId, groupId, employee, StringPool.BLANK, email, serviceContext);
		}
		//_log.info("Employee UPUP: "+employee);
		//_log.info("roles: "+roles);
		//Import employee JobPos
		if (Validator.isNotNull(roles)) {
			String[] roleArr = StringUtil.split(roles);
			if (roleArr != null && roleArr.length > 0) {
				long employeeId = employee.getEmployeeId();
				if (isNew) {
					processUpdateEmpJobPos(userId, groupId, roleArr, employee, null, serviceContext);
				} else {
					List<EmployeeJobPos> empJobPosList =
							EmployeeJobPosLocalServiceUtil.findByF_EmployeeId(employeeId);

					if (Validator.isNull(empJobPosList) || empJobPosList.isEmpty()) {
						processUpdateEmpJobPos(userId, groupId, roleArr, employee, null, serviceContext);
					} else {
						processUpdateEmpJobPos(userId, groupId, roleArr, employee, empJobPosList, serviceContext);
					}

				}
			}
		}

	}

	//Process add Employee JobPos
	private void processUpdateEmpJobPos(long userId, long groupId, String[] roleArr, Employee employee,
			List<EmployeeJobPos> empJobPosList, ServiceContext serviceContext) throws PortalException {
		long jobPosId = 0;
//		String jobCode = StringPool.BLANK;
		if (empJobPosList != null && empJobPosList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			long jobPosEmpId = 0;
			for (int i = 0; i < empJobPosList.size(); i++) {
				EmployeeJobPos employeeJobPos = empJobPosList.get(i);
				jobPosEmpId = employeeJobPos.getJobPostId();
				JobPos job = JobPosLocalServiceUtil.fetchJobPos(jobPosEmpId);
				if (i == 0) {
					sb.append(job.getJobPosCode());
				} else {
					sb.append(StringPool.COMMA);
					sb.append(job.getJobPosCode());
				}
			}
			String strJobPos = sb.toString();
			if (Validator.isNotNull(strJobPos)) {
				for (int i = 0; i < roleArr.length; i++) {
					String jobCode = roleArr[i];
					if (!strJobPos.contains(jobCode)) {
						JobPos job = JobPosLocalServiceUtil.getByJobCode(groupId, jobCode);
						if (job != null) {
							jobPosId = job.getJobPosId();
							EmployeeJobPosLocalServiceUtil.addEmployeeJobPos(
									userId, groupId, employee.getEmployeeId(), jobPosId, 0, serviceContext);
						}
					}
				}
			}
		} else {
			for (int i = 0; i < roleArr.length; i++) {
				//_log.info("roleArr.length: "+roleArr.length);
				String jobCode = roleArr[i];
				JobPos job = JobPosLocalServiceUtil.getByJobCode(groupId, jobCode);
				if (job != null) {
					jobPosId = job.getJobPosId();
					EmployeeJobPosLocalServiceUtil.addEmployeeJobPos(
							userId, groupId, employee.getEmployeeId(), jobPosId, 0, serviceContext);
					if (i == 0) {
						employee.setMainJobPostId(jobPosId);
						EmployeeLocalServiceUtil.updateEmployee(employee);
					}
				}
			}
		}
	}

	//Create account
	private Employee createNewEmployeeAccount(long userId, long groupId, Employee employee, String screenName,
			String email, ServiceContext serviceContext) throws PortalException {

		try {
			if (Validator.isNull(screenName)) {
				screenName = email.substring(0, email.indexOf(StringPool.AT));
			}
			//_log.info("companyId: " + serviceContext.getCompanyId());
			long companyId = serviceContext.getCompanyId();
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, email);
			if (user != null) {
				employee.setMappingUserId(user.getUserId());

				employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(),
						employee.getFullName(), employee.getEmployeeNo(), employee.getGender(), employee.getBirthdate(),
						employee.getTelNo(), employee.getMobile(), employee.getEmail(), employee.getWorkingStatus(),
						employee.getMainJobPostId(), employee.getPhotoFileEntryId(), employee.getMappingUserId(),
						employee.getTitle(), employee.getRecruitDate(), employee.getLeaveDate(), serviceContext);
			} else {
				long[] userGroupIds = {};
				List<Long> roleIds = new ArrayList<>();
				Role role = RoleLocalServiceUtil.fetchRole(companyId, "employee");
				if (Validator.isNotNull(role)) {
					roleIds.add(role.getRoleId());
				}
				long[] resultRoles = roleIds.stream().mapToLong(l -> l).toArray();
				long[] organizationIds = new long[] {};
				long[] groupIds = { groupId, 20143 };

				// String passWord = PwdGenerator.getPassword();
				String secret = "12345";

				String fullName = employee.getFullName();
				String[] fml = new String[3];

				String[] splitName = StringUtil.split(fullName, StringPool.SPACE);

				if (splitName != null && splitName.length > 0) {
					fml[0] = splitName[0];
					fml[1] = splitName.length >= 3
							? StringUtil.merge(ArrayUtil.subset(splitName, 1, splitName.length - 1), StringPool.SPACE)
							: StringPool.BLANK;
					fml[2] = splitName.length >= 2 ? splitName[splitName.length - 1] : splitName[0];
				} else {
					fml[0] = screenName;
					fml[1] = StringPool.BLANK;
					fml[2] = screenName;
				}

				//_log.info("/////0" + fml[0]);
				//_log.info("//////1" + fml[1]);
				//_log.info("//////2" + fml[2]);

				User newUser = UserLocalServiceUtil.addUser(0, companyId, false, secret, secret, false,
						screenName.toLowerCase(), email, 0, StringPool.BLANK, serviceContext.getLocale(), fml[0],
						fml[1], fml[2], 0, 0, true, Calendar.JANUARY, 1, 1979, StringPool.BLANK, groupIds,
						organizationIds, resultRoles, userGroupIds, false, serviceContext);
				if (newUser != null) {
					newUser.setPasswordReset(false);
					UserLocalServiceUtil.updateUser(newUser);

					Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
					indexer.reindex(newUser);

					employee.setMappingUserId(newUser.getUserId());

					employee = EmployeeLocalServiceUtil.updateEmployee(userId, employee.getEmployeeId(),
							employee.getFullName(), employee.getEmployeeNo(), employee.getGender(),
							employee.getBirthdate(), employee.getTelNo(), employee.getMobile(), employee.getEmail(),
							employee.getWorkingStatus(), employee.getMainJobPostId(), employee.getPhotoFileEntryId(),
							employee.getMappingUserId(), employee.getTitle(), employee.getRecruitDate(),
							employee.getLeaveDate(), serviceContext);

					User fromUser = UserLocalServiceUtil.fetchUser(userId);

					JSONObject payLoad = JSONFactoryUtil.createJSONObject();

					payLoad.put("USERNAME", newUser.getScreenName());
					payLoad.put("USEREMAIL", newUser.getEmailAddress());
					payLoad.put("PASSWORD", secret);

					NotificationQueueLocalServiceUtil.addNotificationQueue(userId, groupId, Constants.USER_01,
							User.class.getName(), String.valueOf(newUser.getUserId()), payLoad.toJSONString(),
							fromUser.getFullName(), employee.getFullName(), employee.getMappingUserId(),
							employee.getEmail(), employee.getTelNo(), new Date(), null, serviceContext);
				}
			}
			// JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			//_log.info("Employee Update: " + employee);

			// jsonObject.put("screenName", newUser.getScreenName());
			// jsonObject.put("email", newUser.getEmailAddress());
			// jsonObject.put("exist", false);
			// jsonObject.put("duplicate", Boolean.FALSE.toString());

		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			// jsonObject.put("screenName", StringPool.BLANK);
			// jsonObject.put("email", StringPool.BLANK);
			// jsonObject.put("exist", Boolean.TRUE);
			// jsonObject.put("duplicate", Boolean.TRUE.toString());
		}

		return employee;
	}

}
