/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.usermgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.cache.actions.CacheActions;
import org.opencps.cache.actions.impl.CacheActionsImpl;
import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.exception.NoSuchEmployeeException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

/**
 * The implementation of the employee local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.EmployeeLocalService} interface.
 * <p> This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author Binhth
 * @see EmployeeLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.EmployeeLocalServiceUtil
 */
@ProviderType
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link org.mobilink.backend.usermgt.service.EmployeeLocalServiceUtil} to
	 * access the employee local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Employee addEmployee(
		long userId, long groupId, String fullName, String employeeNo,
		int gender, Date birthdate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPostId, String title,
		boolean isCreateUser, Date recruitDate, Date leaveDate,
		ServiceContext serviceContext)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
		UnauthenticationException, UnauthorizationException,
		NoSuchUserException, PortalException {
		// authen

		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }

		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.UPDATE_EMPLOYEE);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		List<Employee> employeeCheck =
			employeePersistence.findByF_employeeNo(groupId, employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			Validator.isNotNull(employeeNo)) {
			throw new DuplicateEmployeeNoException();
		}

		employeeCheck = employeePersistence.findByF_email(groupId, email);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			Validator.isNotNull(email)) {
			throw new DuplicateEmployeeEmailException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long employeeId =
			counterLocalService.increment(Employee.class.getName());

		Employee employee = employeePersistence.create(employeeId);

		// Group instance
		employee.setGroupId(groupId);

		// Audit fields
		employee.setUuid(serviceContext.getUuid());
		employee.setCompanyId(user.getCompanyId());
		employee.setUserId(user.getUserId());
		employee.setUserName(user.getFullName());
		employee.setCreateDate(serviceContext.getCreateDate(now));
		employee.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		employee.setFullName(fullName);
		employee.setEmployeeNo(employeeNo);
		employee.setGender(gender);
		employee.setBirthdate(birthdate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setTitle(title);

		employee.setMainJobPostId(mainJobPostId);

		employee.setRecruitDate(recruitDate);
		employee.setLeaveDate(leaveDate);

		employee.setExpandoBridgeAttributes(serviceContext);

		return employeePersistence.update(employee);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Employee addEmployee(
		long userId, long groupId, String fullName, String employeeNo,
		int gender, Date birthdate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPostId, String title,
		String scope, String jobPosTitle,
		boolean isCreateUser, Date recruitDate, Date leaveDate,
		ServiceContext serviceContext)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
		UnauthenticationException, UnauthorizationException,
		NoSuchUserException, PortalException {
		// authen

		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }

		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.UPDATE_EMPLOYEE);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		List<Employee> employeeCheck =
			employeePersistence.findByF_employeeNo(groupId, employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			Validator.isNotNull(employeeNo)) {
			throw new DuplicateEmployeeNoException();
		}

		employeeCheck = employeePersistence.findByF_email(groupId, email);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			Validator.isNotNull(email)) {
			throw new DuplicateEmployeeEmailException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long employeeId =
			counterLocalService.increment(Employee.class.getName());

		Employee employee = employeePersistence.create(employeeId);

		// Group instance
		employee.setGroupId(groupId);

		// Audit fields
		employee.setUuid(serviceContext.getUuid());
		employee.setCompanyId(user.getCompanyId());
		employee.setUserId(user.getUserId());
		employee.setUserName(user.getFullName());
		employee.setCreateDate(serviceContext.getCreateDate(now));
		employee.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		employee.setFullName(fullName);
		employee.setEmployeeNo(employeeNo);
		employee.setGender(gender);
		employee.setBirthdate(birthdate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setTitle(title);
		employee.setScope(scope);
		if (Validator.isNotNull(jobPosTitle))
			employee.setJobPosTitle(jobPosTitle);
		
		employee.setMainJobPostId(mainJobPostId);

		employee.setRecruitDate(recruitDate);
		employee.setLeaveDate(leaveDate);

		employee.setExpandoBridgeAttributes(serviceContext);

		return employeePersistence.update(employee);

	}
	
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Employee deleteEmployee(
		long employeeId, ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
		NotFoundException {

		// authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();

		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }

		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.UPDATE_EMPLOYEE);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		Employee employee = null;
		try {
			employee = employeePersistence.fetchByPrimaryKey(employeeId);
			if (employee != null) {
				long mappingUserId = employee.getMappingUserId();
				if (mappingUserId > 0) {
					try {
						//DELETE USER
						Group group = GroupLocalServiceUtil.fetchUserGroup(employee.getCompanyId(), mappingUserId);
						if (group != null) {
							GroupLocalServiceUtil.deleteGroup(group);
						}

						userPersistence.remove(mappingUserId);
					} catch (NoSuchUserException e) {
						_log.debug(e);
					}
				}
			}
			employee = employeePersistence.remove(employeeId);
		}
		catch (PortalException e) {
			// throw new NotFoundException();
			_log.error(e);
		}

		return employee;

	}

	private static Log _log =
		LogFactoryUtil.getLog(EmployeeLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Employee updateEmployee(
		long userId, long employeeId, String fullName, String employeeNo,
		int gender, Date birthdate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPostId, long photoFileEntryId,
		long mappingUserId, String title, Date recruitDate, Date leaveDate,
		ServiceContext serviceContext)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
		UnauthenticationException, UnauthorizationException,
		NoSuchUserException, NotFoundException, PortalException {

		// authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }

		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.UPDATE_EMPLOYEE);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Employee employee = employeePersistence.fetchByPrimaryKey(employeeId);

		// if (!hasPermission && userId != employee.getMappingUserId()) {
		// throw new UnauthorizationException();
		// }

		List<Employee> employeeCheck = employeePersistence.findByF_employeeNo(
			employee.getGroupId(), employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			employeeCheck.get(0).getEmployeeId() != employeeId) {
			throw new DuplicateEmployeeNoException();
		}

		// _log.info("employeeId:" + employeeId);
		employeeCheck =
			employeePersistence.findByF_email(employee.getGroupId(), email);
		// _log.info(
		// "employeeCheck:" + employeeCheck.size() + "| employeeCheckId: " +
		// employeeCheck.get(0).getEmployeeId());
		// _log.info("employeeCheck:" + employeeCheck.get(0));

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			employeeCheck.get(0).getEmployeeId() != employeeId) {
			throw new DuplicateEmployeeEmailException();
		}

		// Audit fields
		employee.setUserId(user.getUserId());
		employee.setUserName(user.getFullName());
		employee.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		employee.setFullName(fullName);
		employee.setEmployeeNo(employeeNo);
		employee.setGender(gender);
		employee.setBirthdate(birthdate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setMainJobPostId(mainJobPostId);
		employee.setPhotoFileEntryId(photoFileEntryId);
		employee.setMappingUserId(mappingUserId);
		employee.setTitle(title);
		employee.setRecruitDate(recruitDate);
		employee.setLeaveDate(leaveDate);
		// User newUser =
		// UserLocalServiceUtil.fetchUser(employee.getMappingUserId());
		//
		// JobPos mJobPos = JobPosLocalServiceUtil.fetchJobPos(mainJobPostId);
		//
		// List<Role> roleIds = new ArrayList<Role>();
		// roleIds.add(RoleLocalServiceUtil.fetchRole(mJobPos.getMappingRoleId()));
		//
		// List<EmployeeJobPos> listEmJobPos =
		// employeeJobPosPersistence.findByF_EmployeeId(employee.getEmployeeId());
		//
		// for (EmployeeJobPos EmployeeJobPos : listEmJobPos) {
		// roleIds.add(RoleLocalServiceUtil
		// .fetchRole(JobPosLocalServiceUtil.fetchJobPos(EmployeeJobPos.getJobPostId()).getMappingRoleId()));
		// }
		//
		// RoleLocalServiceUtil.clearUserRoles(newUser.getUserId());
		// RoleLocalServiceUtil.addUserRoles(newUser.getUserId(), roleIds);
		//
		// Indexer<User> indexer =
		// IndexerRegistryUtil.nullSafeGetIndexer(User.class);
		//
		// indexer.reindex(newUser);

		employee.setExpandoBridgeAttributes(serviceContext);

		return employeePersistence.update(employee);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Employee updateEmployee(
		long userId, long employeeId, String fullName, String employeeNo,
		int gender, Date birthdate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPostId, long photoFileEntryId,
		long mappingUserId, String title, String scope, Date recruitDate, Date leaveDate,
		ServiceContext serviceContext)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
		UnauthenticationException, UnauthorizationException,
		NoSuchUserException, NotFoundException, PortalException {

		// authen
		// BackendAuthImpl authImpl = new BackendAuthImpl();
		//
		// boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK,
		// StringPool.BLANK);
		//
		// if (!isAuth) {
		// throw new UnauthenticationException();
		// }

		// boolean hasPermission = authImpl.hasResource(serviceContext,
		// ModelNameKeys.WORKINGUNIT_MGT_CENTER,
		// ActionKeys.UPDATE_EMPLOYEE);
		//
		// if (!hasPermission) {
		// throw new UnauthorizationException();
		// }

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Employee employee = employeePersistence.fetchByPrimaryKey(employeeId);

		// if (!hasPermission && userId != employee.getMappingUserId()) {
		// throw new UnauthorizationException();
		// }

		List<Employee> employeeCheck = employeePersistence.findByF_employeeNo(
			employee.getGroupId(), employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			employeeCheck.get(0).getEmployeeId() != employeeId) {
			throw new DuplicateEmployeeNoException();
		}

		// _log.info("employeeId:" + employeeId);
		employeeCheck =
			employeePersistence.findByF_email(employee.getGroupId(), email);
		// _log.info(
		// "employeeCheck:" + employeeCheck.size() + "| employeeCheckId: " +
		// employeeCheck.get(0).getEmployeeId());
		// _log.info("employeeCheck:" + employeeCheck.get(0));

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 &&
			employeeCheck.get(0).getEmployeeId() != employeeId) {
			throw new DuplicateEmployeeEmailException();
		}

		// Audit fields
		employee.setUserId(user.getUserId());
		employee.setUserName(user.getFullName());
		employee.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		employee.setFullName(fullName);
		employee.setEmployeeNo(employeeNo);
		employee.setGender(gender);
		employee.setBirthdate(birthdate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setMainJobPostId(mainJobPostId);
		employee.setPhotoFileEntryId(photoFileEntryId);
		employee.setMappingUserId(mappingUserId);
		employee.setTitle(title);
		employee.setScope(scope);
		
		employee.setRecruitDate(recruitDate);
		employee.setLeaveDate(leaveDate);
		// User newUser =
		// UserLocalServiceUtil.fetchUser(employee.getMappingUserId());
		//
		// JobPos mJobPos = JobPosLocalServiceUtil.fetchJobPos(mainJobPostId);
		//
		// List<Role> roleIds = new ArrayList<Role>();
		// roleIds.add(RoleLocalServiceUtil.fetchRole(mJobPos.getMappingRoleId()));
		//
		// List<EmployeeJobPos> listEmJobPos =
		// employeeJobPosPersistence.findByF_EmployeeId(employee.getEmployeeId());
		//
		// for (EmployeeJobPos EmployeeJobPos : listEmJobPos) {
		// roleIds.add(RoleLocalServiceUtil
		// .fetchRole(JobPosLocalServiceUtil.fetchJobPos(EmployeeJobPos.getJobPostId()).getMappingRoleId()));
		// }
		//
		// RoleLocalServiceUtil.clearUserRoles(newUser.getUserId());
		// RoleLocalServiceUtil.addUserRoles(newUser.getUserId(), roleIds);
		//
		// Indexer<User> indexer =
		// IndexerRegistryUtil.nullSafeGetIndexer(User.class);
		//
		// indexer.reindex(newUser);

		employee.setExpandoBridgeAttributes(serviceContext);

		return employeePersistence.update(employee);

	}
	
	@ThreadLocalCachable
	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId) {

		return employeePersistence.fetchByF_mappingUserId(
			groupId, mappingUserId);
	}

	int ttl = 86400;
	CacheActions cache = new CacheActionsImpl();
	
	@ThreadLocalCachable
	public Employee fetchByFB_MUID(long mappingUserId) {
		Serializable userSerialize = null;
		try {
			userSerialize = cache.getFromCache("EmployeeMapping", mappingUserId + "");
		} catch (PortalException e) {
			_log.debug(e);
		}
		if (userSerialize != null) {
			return (Employee)userSerialize;
		}
		else {
			List<Employee> lstTempEs = employeePersistence.findByFB_MUID(mappingUserId);
			
			Employee tempE = lstTempEs.size() > 0 ? lstTempEs.get(0) : null;
			if (tempE != null) {
				try {
					cache.addToCache("EmployeeMapping",
						mappingUserId + "", (Serializable)tempE, ttl);
				}
				catch (PortalException e) {
					_log.debug(e);
				}
			}
			return tempE;
		}
	}

	public void isExits(long groupId, String employeeNo, String email)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException {

		List<Employee> employeeCheck =
			employeePersistence.findByF_employeeNo(groupId, employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0) {
			throw new DuplicateEmployeeNoException();
		}

		employeeCheck = employeePersistence.findByF_email(groupId, email);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0) {
			throw new DuplicateEmployeeEmailException();
		}

	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		String keywords = (String) params.get(EmployeeTerm.KEYWORDS);
		String employeeId = (String) params.get(EmployeeTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(Field.GROUP_ID);
		String mainJobPostId = (String) params.get(EmployeeTerm.MAIN_JOBPOST_ID);
		// String[] advFilterOptions = (String[])
		// params.get("advFilterOptions");
		String isAccount = (String) params.get(EmployeeTerm.IS_ACCOUNT);
		String workingUnitId = (String) params.get(EmployeeTerm.WORKING_UNIT_ID);
		String jobPostId = (String) params.get(EmployeeTerm.JOB_POS_ID);
		String status = (String) params.get(EmployeeTerm.WORKING_STATUS);
		String active = (String) params.get(EmployeeTerm.ACTIVE);
		String month = (String) params.get(EmployeeTerm.MONTH);
		String strUserIdList = (String) params.get(EmployeeTerm.USER_ID_LIST);
		String employeeName = (String) params.get(EmployeeTerm.FULL_NAME);
		String jobposCodeSearch = (String) params.get(EmployeeTerm.JOB_POS_CODE);
		String scopesSearch = (String) params.get(EmployeeTerm.SCOPES_SEARCH);

		Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Employee.class);

		searchContext.addFullQueryEntryClassName(Employee.class.getName());
		searchContext.setEntryClassNames(new String[] { Employee.class.getName() });
		searchContext.setAttribute(EmployeeTerm.PAGINATION_TYPE, ConfigConstants.PAGINATION_TYPE_REGULAR);
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		}
		else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// if (Validator.isNotNull(keywords)) {
		//
		// String[] keyword = keywords.split(StringPool.SPACE);
		//
		// for (String string : keyword) {
		//
		// MultiMatchQuery query = new MultiMatchQuery(string);
		//
		// query.addFields(EmployeeTerm.EMPLOYEE_NO, EmployeeTerm.FULL_NAME,
		// EmployeeTerm.EMAIL,
		// EmployeeTerm.TELNO);
		//
		// // for (String moreField : advFilterOptions) {
		// // if (Validator.isNotNull(moreField)) {
		// // query.addField("alpaca_" + moreField);
		// // }
		// // }
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }
		// }

		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] {
				EmployeeTerm.EMPLOYEE_NO, EmployeeTerm.FULL_NAME,
				EmployeeTerm.EMAIL, EmployeeTerm.TELNO
			};

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(
						fieldSearch,
						StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(employeeId)) {
			MultiMatchQuery query = new MultiMatchQuery(employeeId);

			query.addFields(EmployeeTerm.EMPLOYEE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(isAccount)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

			query.addFields(EmployeeTerm.MAPPING_USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
		}

		if (Validator.isNotNull(mainJobPostId)) {
			MultiMatchQuery query = new MultiMatchQuery(mainJobPostId);

			query.addFields(EmployeeTerm.MAIN_JOBPOST_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(workingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(workingUnitId);

			query.addFields(EmployeeTerm.WORKING_UNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(jobPostId)) {
			MultiMatchQuery query = new MultiMatchQuery(jobPostId);

			query.addFields(EmployeeTerm.JOB_POS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {
			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(EmployeeTerm.WORKING_STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(active)) {
			MultiMatchQuery query = new MultiMatchQuery(active);

			query.addFields(EmployeeTerm.ACTIVE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

			query = new MultiMatchQuery(String.valueOf(0));

			query.addFields(EmployeeTerm.MAPPING_USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);

		}

		if (Validator.isNotNull(month)) {
			MultiMatchQuery query = new MultiMatchQuery(month);

			query.addFields(EmployeeTerm.MONTH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(strUserIdList)) {
			String[] sliptUserId = strUserIdList.split(StringPool.COMMA);
			if (sliptUserId != null && sliptUserId.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (String strUserId : sliptUserId) {
					if (Validator.isNotNull(strUserId)) {

						MultiMatchQuery query = new MultiMatchQuery(strUserId);

						query.addFields(EmployeeTerm.MAPPING_USER_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(strUserIdList);

				query.addFields(EmployeeTerm.MAPPING_USER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(employeeName)) {

			String[] keywordArr = employeeName.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : keywordArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
					EmployeeTerm.FULL_NAME,
					StringPool.STAR + key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(jobposCodeSearch)) {

			String[] jobposArr = StringUtil.split(jobposCodeSearch);
			if (jobposArr != null && jobposArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < jobposArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(jobposArr[i]);
					query.addField(EmployeeTerm.JOB_POS_CODE_SEARCH);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(jobposCodeSearch);
				query.addFields(EmployeeTerm.JOB_POS_CODE_SEARCH);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(scopesSearch)) {

			String[] scopesArr = StringUtil.split(scopesSearch);
			if (scopesArr != null && scopesArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < scopesArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(scopesArr[i]);
					query.addField(EmployeeTerm.SCOPES_SEARCH);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(scopesSearch);
				query.addFields(EmployeeTerm.SCOPES_SEARCH);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(
			Field.ENTRY_CLASS_NAME, Employee.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(
		LinkedHashMap<String, Object> params, SearchContext searchContext)
		throws ParseException, SearchException {
		String keywords = (String) params.get(EmployeeTerm.KEYWORDS);
		String employeeId = (String) params.get(EmployeeTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(Field.GROUP_ID);
		String mainJobPostId = (String) params.get(EmployeeTerm.MAIN_JOBPOST_ID);
		// String[] advFilterOptions = (String[])
		// params.get("advFilterOptions");
		String isAccount = (String) params.get(EmployeeTerm.IS_ACCOUNT);
		String workingUnitId = (String) params.get(EmployeeTerm.WORKING_UNIT_ID);
		String jobPostId = (String) params.get(EmployeeTerm.JOB_POS_ID);
		String status = (String) params.get(EmployeeTerm.WORKING_STATUS);
		String active = (String) params.get(EmployeeTerm.ACTIVE);
		String month = (String) params.get(EmployeeTerm.MONTH);
		String strUserIdList = (String) params.get(EmployeeTerm.USER_ID_LIST);
		String employeeName = (String) params.get(EmployeeTerm.FULL_NAME);
//		String jobposCode = (String) params.get(EmployeeTerm.JOB_POS_CODE);
		String jobposCodeSearch =
			(String) params.get(EmployeeTerm.JOB_POS_CODE_SEARCH);
		String scopesSearch = (String) params.get(EmployeeTerm.SCOPES_SEARCH);

		Indexer<Employee> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Employee.class);

		searchContext.addFullQueryEntryClassName(Employee.class.getName());
		searchContext.setEntryClassNames(new String[] { Employee.class.getName() });
		searchContext.setAttribute(EmployeeTerm.PAGINATION_TYPE, ConfigConstants.PAGINATION_TYPE_REGULAR);
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		}
		else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// if (Validator.isNotNull(keywords)) {
		//
		// String[] keyword = keywords.split(StringPool.SPACE);
		//
		// for (String string : keyword) {
		//
		// MultiMatchQuery query = new MultiMatchQuery(string);
		//
		// query.addFields(EmployeeTerm.EMPLOYEE_NO, EmployeeTerm.FULL_NAME,
		// EmployeeTerm.EMAIL,
		// EmployeeTerm.TELNO);
		//
		// // for (String moreField : advFilterOptions) {
		// // if (Validator.isNotNull(moreField)) {
		// // query.addField("alpaca_" + moreField);
		// // }
		// // }
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }
		// }

		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] {
				EmployeeTerm.EMPLOYEE_NO, EmployeeTerm.FULL_NAME,
				EmployeeTerm.EMAIL, EmployeeTerm.TELNO
			};

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(
						fieldSearch,
						StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(employeeId)) {
			MultiMatchQuery query = new MultiMatchQuery(employeeId);

			query.addFields(EmployeeTerm.EMPLOYEE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(isAccount)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

			query.addFields(EmployeeTerm.MAPPING_USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
		}

		if (Validator.isNotNull(mainJobPostId)) {
			MultiMatchQuery query = new MultiMatchQuery(mainJobPostId);

			query.addFields(EmployeeTerm.MAIN_JOBPOST_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(workingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(workingUnitId);

			query.addFields(EmployeeTerm.WORKING_UNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(jobPostId)) {
			MultiMatchQuery query = new MultiMatchQuery(jobPostId);

			query.addFields(EmployeeTerm.JOB_POS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {
			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(EmployeeTerm.WORKING_STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(active)) {
			MultiMatchQuery query = new MultiMatchQuery(active);

			query.addFields(EmployeeTerm.ACTIVE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

			query = new MultiMatchQuery(String.valueOf(0));

			query.addFields(EmployeeTerm.MAPPING_USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);

		}

		if (Validator.isNotNull(month)) {
			MultiMatchQuery query = new MultiMatchQuery(month);

			query.addFields(EmployeeTerm.MONTH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(strUserIdList)) {
			String[] sliptUserId = strUserIdList.split(StringPool.COMMA);
			if (sliptUserId != null && sliptUserId.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (String strUserId : sliptUserId) {
					if (Validator.isNotNull(strUserId)) {

						MultiMatchQuery query = new MultiMatchQuery(strUserId);

						query.addFields(EmployeeTerm.MAPPING_USER_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(strUserIdList);

				query.addFields(EmployeeTerm.MAPPING_USER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(employeeName)) {

			String[] keywordArr = employeeName.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : keywordArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
					EmployeeTerm.FULL_NAME,
					StringPool.STAR + key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(jobposCodeSearch)) {
			String[] jobposArr = StringUtil.split(jobposCodeSearch);
			if (jobposArr != null && jobposArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < jobposArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(jobposArr[i]);
					query.addField(EmployeeTerm.JOB_POS_CODE_SEARCH);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(jobposCodeSearch);
				query.addFields(EmployeeTerm.JOB_POS_CODE_SEARCH);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(scopesSearch)) {

			String[] scopesArr = StringUtil.split(scopesSearch);
			if (scopesArr != null && scopesArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < scopesArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(scopesArr[i]);
					query.addField(EmployeeTerm.SCOPES_SEARCH);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(scopesSearch);
				query.addFields(EmployeeTerm.SCOPES_SEARCH);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(
			Field.ENTRY_CLASS_NAME, Employee.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Employee updatePayload(
		long userId, long groupId, long fileCertId, long fileSignId,
		String fileCertPath, String fileSignPath, ServiceContext serviceContext)
		throws PortalException {

		Employee employee =
			employeePersistence.fetchByF_mappingUserId(groupId, userId);

		if (Validator.isNotNull(employee)) {

			User user =
				userPersistence.fetchByPrimaryKey(serviceContext.getUserId());

			String userName = StringPool.BLANK;

			if (Validator.isNotNull(user)) {
				userName = user.getFullName();
			}

			Date now = new Date();

			if (fileCertId != 0) {
				// update fileId of certFile
				employee.setFileCertId(fileCertId);
			}
			if (fileSignId != 0) {
				// update fileSignId
				employee.setFileSignId(fileSignId);
			}
			if (fileCertPath.trim().length() != 0) {
				// update fileCertPath
				employee.setFileCertPath(fileCertPath);
			}
			if (fileSignPath.trim().length() != 0) {
				// update fileId of certFile
				employee.setFileSignPath(fileSignPath);
			}

			employee.setUserId(serviceContext.getUserId());
			employee.setUserName(userName);
			employee.setModifiedDate(now);
		}

		employeePersistence.update(employee);

		return employee;
	}

	public List<Employee> getLstEmployee(long groupId, long userId) {

		return employeePersistence.findByG_UID(groupId, userId);
	}

	public Employee getEmployeeByEmpNo(long groupId, String employeeNo) {

		return employeePersistence.fetchByF_GID_EMPNO(groupId, employeeNo);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Employee adminProcessDelete(Long id) {

		Employee object = employeePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		}
		else {
			// delete user
			long userId = object.getMappingUserId();
			if (userId > 0) {
				try {
					userPersistence.remove(userId);
				}
				catch (NoSuchUserException e) {
					_log.error(e);
				}
			}
			employeePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Employee adminProcessData(JSONObject objectData)
			throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException {

		Employee object = null;

		if (objectData.getLong(EmployeeTerm.EMPLOYEE_ID) > 0) {

			object = employeePersistence.fetchByPrimaryKey(objectData.getLong(EmployeeTerm.EMPLOYEE_ID));

			object.setModifiedDate(new Date());

		}
		else {

			int checkExitsEmp = employeePersistence.countByF_email(objectData.getLong(Field.GROUP_ID), objectData.getString(EmployeeTerm.EMAIL));
			if (checkExitsEmp > 0) {
				throw new DuplicateEmployeeEmailException("email_exits");
			} else {
				checkExitsEmp = employeePersistence.countByF_employeeNo(objectData.getLong(Field.GROUP_ID), objectData.getString(EmployeeTerm.EMPLOYEE_NO));
				if (checkExitsEmp > 0) {
					throw new DuplicateEmployeeEmailException("employeeNo_exits");
				}
			}

			long id =
				CounterLocalServiceUtil.increment(Employee.class.getName());

			object = employeePersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(EmployeeTerm.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		long mappingUserId = objectData.getLong(EmployeeTerm.MAPPING_USER_ID);
		//_log.info("mappingUserId: "+mappingUserId);
		//User user = null;
		if (mappingUserId > 0) {
			object.setMappingUserId(objectData.getLong(EmployeeTerm.MAPPING_USER_ID));
			//Get info User
			//user = UserLocalServiceUtil.fetchUser(mappingUserId);
			//_log.info("user: "+JSONFactoryUtil.looseSerialize(user));
			//user = UserLocalServiceUtil.fetchUser(20139);
			//_log.info("userHe Thong: "+JSONFactoryUtil.looseSerialize(user));
			//User
		} else if (objectData.getInt(EmployeeTerm.WORKING_STATUS) == EmployeeTerm.WORKING_STATUS_WORKED) {

			List<Employee> emps = employeePersistence.findByEmail(objectData.getString(EmployeeTerm.EMAIL));
			for (Employee e : emps) {
				if (e.getMappingUserId() > 0) {
					object.setMappingUserId(e.getMappingUserId());
					break;
				}
			}
		}

		object.setUserId(objectData.getLong(EmployeeTerm.USER_ID));
		object.setEmployeeNo(objectData.getString(EmployeeTerm.EMPLOYEE_NO));
		object.setFullName(objectData.getString(EmployeeTerm.FULL_NAME));
		object.setTitle(objectData.getString(EmployeeTerm.TITLE));
		object.setGender(objectData.getInt(EmployeeTerm.GENDER));
		object.setTelNo(objectData.getString(EmployeeTerm.TELNO));
		object.setMobile(objectData.getString(EmployeeTerm.MOBILE));
		object.setEmail(objectData.getString(EmployeeTerm.EMAIL));
		object.setWorkingStatus(objectData.getInt(EmployeeTerm.WORKING_STATUS));
		object.setMainJobPostId(objectData.getLong(EmployeeTerm.MAIN_JOBPOST_ID));
		// object.setPhotoFileEntryId(objectData.getString("photoFileEntryId"));
		if(objectData.getLong(EmployeeTerm.BIRTH_DATE) > 0)
			object.setBirthdate(new Date(objectData.getLong(EmployeeTerm.BIRTH_DATE)));
		if(objectData.getLong(EmployeeTerm.RECRUIT_DATE) > 0)
		object.setRecruitDate(new Date(objectData.getLong(EmployeeTerm.RECRUIT_DATE)));
		if(objectData.getLong(EmployeeTerm.LEAVE_DATE) > 0)
		object.setLeaveDate(new Date(objectData.getLong(EmployeeTerm.LEAVE_DATE)));
		object.setScope(objectData.getString(EmployeeTerm.SCOPE));
		// object.setFileCertId(fileCertId);
		// object.setFileSignId(fileSignId);
		// object.setFileCertPath(fileCertPath);
		// object.setFileSignPath(fileSignPath);
		// if (user != null) {
		// _log.info("EMAIL: "+user.getEmailAddress());
		// String fullName = objectData.getString("fullName");
		// _log.info("fullName: "+fullName);
		// if (Validator.isNotNull(fullName)) {
		// int indexFisrt = fullName.indexOf(" ");
		// _log.info("indexFisrt: "+indexFisrt);
		// if (indexFisrt > 0) {
		// _log.info("fullName.substring(0, indexFisrt - 1):
		// "+fullName.substring(0, indexFisrt));
		// user.setModifiedDate(new Date());
		// user.setFirstName(fullName.substring(0, indexFisrt));
		// user.setMiddleName(StringPool.BLANK);
		// user.setLastName(fullName.substring(indexFisrt + 1));
		// //Update user
		// UserLocalServiceUtil.updateUser(user);
		// }
		// }
		// }
		// _log.info("user: "+JSONFactoryUtil.looseSerialize(user));
		User u = userLocalService.fetchUser(object.getMappingUserId());
		if (u != null) {
			if (object.getWorkingStatus() == EmployeeTerm.WORKING_STATUS_RETIRED) {
				if (object.getMappingUserId() > 0) {
					userLocalService.deleteGroupUser(object.getGroupId(), object.getMappingUserId());
				}
			}
			else if (object.getWorkingStatus() == EmployeeTerm.WORKING_STATUS_WORKED) {
				if (object.getMappingUserId() > 0) {
					userLocalService.addGroupUser(object.getGroupId(), object.getMappingUserId());
				}
			}
		}
		_log.debug("object" + object);
		_log.debug("objectData" + objectData.toJSONString());
		return employeePersistence.update(object);

	}

	
	public List<Employee> findByG_EMPID(long groupId, long[] employeeIds) {

		return employeePersistence.findByG_EMPID(groupId, employeeIds);
	}

	public long countByG_EMPID(long groupId, long[] employeeIds) {

		return employeePersistence.countByG_EMPID(groupId, employeeIds);
	}

	public List<Employee> findByG(long groupId) {

		return employeePersistence.findByF_groupId(groupId);
	}

	public List<Employee> findByWorkstatus(
		long mappingUserId, int workingStatus) {

		return employeePersistence.findByF_EMP_WORK(
			mappingUserId, workingStatus);
	}

	public Employee findByWorkingStatusAndEmployeeNo(int workingStatus, String employeeNo) throws NoSuchEmployeeException {
		return employeePersistence.findByF_WS_EMPNO(workingStatus, employeeNo);
	}

	public List<Employee> findByG_MUSERID(long groupId, long[] userIds) {

		return employeePersistence.findByG_MUSERID(groupId, userIds);
	}
	
	public List<Employee> findByEmail(String email) {
		return employeePersistence.findByEmail(email);
	}
}
