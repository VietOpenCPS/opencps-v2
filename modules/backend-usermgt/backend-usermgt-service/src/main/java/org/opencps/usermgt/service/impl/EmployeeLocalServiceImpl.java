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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.constants.EmployeeTerm;
import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.exception.NoSuchEmployeeException;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the employee local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.EmployeeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see EmployeeLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.EmployeeLocalServiceUtil
 */
@ProviderType
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.EmployeeLocalServiceUtil} to access
	 * the employee local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Employee addEmployee(long userId, long groupId, String fullName, String employeeNo, int gender,
			Date birthDate, String telNo, String mobile, String email, int workingStatus, long mainJobPostId,
			String title, boolean isCreateUser, Date recruitDate, Date leaveDate, ServiceContext serviceContext)
			throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, PortalException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.UPDATE_EMPLOYEE);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		List<Employee> employeeCheck = employeePersistence.findByF_employeeNo(groupId, employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 && Validator.isNotNull(employeeNo)) {
			throw new DuplicateEmployeeNoException();
		}

		employeeCheck = employeePersistence.findByF_email(groupId, email);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0 && Validator.isNotNull(email)) {
			throw new DuplicateEmployeeEmailException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long employeeId = counterLocalService.increment(Employee.class.getName());

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
		employee.setBirthdate(birthDate);
		employee.setTelNo(telNo);
		employee.setMobile(mobile);
		employee.setEmail(email);
		employee.setWorkingStatus(workingStatus);
		employee.setTitle(title);

		employee.setMainJobPostId(mainJobPostId);

		employee.setRecruitDate(recruitDate);
		employee.setLeaveDate(leaveDate);

		employee.setExpandoBridgeAttributes(serviceContext);

		employeePersistence.update(employee);

		return employee;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Employee deleteEmployee(long employeeId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.UPDATE_EMPLOYEE);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Employee employee;
		try {
			employee = employeePersistence.remove(employeeId);
		} catch (NoSuchEmployeeException e) {
			throw new NotFoundException();
		}

		return employee;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Employee updateEmployee(long userId, long employeeId, String fullName, String employeeNo, int gender,
			Date birthDate, String telNo, String mobile, String email, int workingStatus, long mainJobPostId,
			long photoFileEntryId, long mappingUserId, String title, Date recruitDate, Date leaveDate,
			ServiceContext serviceContext)
			throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.UPDATE_EMPLOYEE);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Employee employee = employeePersistence.fetchByPrimaryKey(employeeId);

		List<Employee> employeeCheck = employeePersistence.findByF_employeeNo(employee.getGroupId(), employeeNo);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0
				&& employeeCheck.get(0).getEmployeeId() != employeeId) {
			throw new DuplicateEmployeeNoException();
		}

		employeeCheck = employeePersistence.findByF_email(employee.getGroupId(), email);

		if (Validator.isNotNull(employeeCheck) && employeeCheck.size() > 0
				&& employeeCheck.get(0).getEmployeeId() != employeeId) {
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
		employee.setBirthdate(birthDate);
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

		employeePersistence.update(employee);

		return employee;
	}

	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId) {
		return employeePersistence.fetchByF_mappingUserId(groupId, mappingUserId);
	}

	public void isExits(long groupId, String employeeNo, String email)
			throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException {

		List<Employee> employeeCheck = employeePersistence.findByF_employeeNo(groupId, employeeNo);

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
		String keywords = (String) params.get("keywords");
		String employeeId = (String) params.get(EmployeeTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(EmployeeTerm.GROUP_ID);
		String mainJobPostId = (String) params.get(EmployeeTerm.MAIN_JOBPOST_ID);
		// String[] advFilterOptions = (String[])
		// params.get("advFilterOptions");
		String isAccount = (String) params.get("isAccount");
		String workingUnitId = (String) params.get(EmployeeTerm.WORKING_UNIT_ID);
		String jobPostId = (String) params.get(EmployeeTerm.JOB_POS_ID);
		String status = (String) params.get(EmployeeTerm.WORKING_STATUS);
		String active = (String) params.get(EmployeeTerm.ACTIVE);
		String month = (String) params.get(EmployeeTerm.MONTH);
		String strUserIdList = (String) params.get("userIdList");

		Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Employee.class);

		searchContext.addFullQueryEntryClassName(Employee.class.getName());
		searchContext.setEntryClassNames(new String[] { Employee.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(EmployeeTerm.EMPLOYEE_NO, EmployeeTerm.FULL_NAME, EmployeeTerm.EMAIL,
						EmployeeTerm.TELNO);

				// for (String moreField : advFilterOptions) {
				// if (Validator.isNotNull(moreField)) {
				// query.addField("alpaca_" + moreField);
				// }
				// }

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
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

			query.addFields(EmployeeTerm.GROUP_ID);

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
			} else {
				MultiMatchQuery query = new MultiMatchQuery(strUserIdList);

				query.addFields(EmployeeTerm.MAPPING_USER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Employee.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
		String employeeId = (String) params.get(EmployeeTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(EmployeeTerm.GROUP_ID);
		String mainJobPostId = (String) params.get(EmployeeTerm.MAIN_JOBPOST_ID);
		// String[] advFilterOptions = (String[])
		// params.get("advFilterOptions");
		String isAccount = (String) params.get("isAccount");
		String workingUnitId = (String) params.get(EmployeeTerm.WORKING_UNIT_ID);
		String jobPostId = (String) params.get(EmployeeTerm.JOB_POS_ID);
		String status = (String) params.get(EmployeeTerm.WORKING_STATUS);
		String active = (String) params.get(EmployeeTerm.ACTIVE);
		String month = (String) params.get(EmployeeTerm.MONTH);
		String strUserIdList = (String) params.get("userIdList");

		Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Employee.class);

		searchContext.addFullQueryEntryClassName(Employee.class.getName());
		searchContext.setEntryClassNames(new String[] { Employee.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(EmployeeTerm.EMPLOYEE_NO, EmployeeTerm.FULL_NAME, EmployeeTerm.EMAIL,
						EmployeeTerm.TELNO);

				// for (String moreField : advFilterOptions) {
				// if (Validator.isNotNull(moreField)) {
				// query.addField("alpaca_" + moreField);
				// }
				// }

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
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

			query.addFields(EmployeeTerm.GROUP_ID);

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
			} else {
				MultiMatchQuery query = new MultiMatchQuery(strUserIdList);

				query.addFields(EmployeeTerm.MAPPING_USER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Employee.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Employee updatePayload(long userId, long groupId, long fileCertId, long fileSignId, String fileCertPath,
			String fileSignPath, ServiceContext serviceContext) throws PortalException {

		Employee employee = employeePersistence.fetchByF_mappingUserId(groupId, userId);
		
		if (Validator.isNotNull(employee)) {
			
			User user = userPersistence.fetchByPrimaryKey(serviceContext.getUserId());
			
			String userName = StringPool.BLANK;
			
			if (Validator.isNotNull(user)) {
				userName = user.getFullName();
			}
			
			Date now = new Date();
			
			if (fileCertId != 0) {
				//update fileId of certFile
				employee.setFileCertId(fileCertId);
			}
			if (fileSignId != 0) {
				//update fileSignId
				employee.setFileSignId(fileSignId);
			}
			if (fileCertPath.trim().length() != 0) {
				//update fileCertPath
				employee.setFileCertPath(fileCertPath);
			}
			if (fileSignPath.trim().length() != 0) {
				//update fileId of certFile
				employee.setFileSignPath(fileSignPath);
			}
			
			employee.setUserId(serviceContext.getUserId());
			employee.setUserName(userName);
			employee.setModifiedDate(now);
		}
		
		employeePersistence.update(employee);

		return employee;
	}

		
	public List<Employee> getLstEmployee(long groupId, long userId){
		return employeePersistence.findByG_UID(groupId, userId);
	}
}