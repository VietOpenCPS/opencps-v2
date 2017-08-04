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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.constants.EmployeeJobPosTerm;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.JobPosLocalServiceUtil;
import org.opencps.usermgt.service.base.EmployeeJobPosLocalServiceBaseImpl;
import org.opencps.usermgt.service.persistence.EmployeeJobPosPersistence;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
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
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the employee job pos local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.EmployeeJobPosLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see EmployeeJobPosLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.EmployeeJobPosLocalServiceUtil
 */
@ProviderType
public class EmployeeJobPosLocalServiceImpl extends EmployeeJobPosLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.EmployeeJobPosLocalServiceUtil} to
	 * access the employee job pos local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EmployeeJobPos addEmployeeJobPos(long userId, long groupId, long employeeId, long jobPostId,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long employeeJobPosId = counterLocalService.increment(EmployeeJobPos.class.getName());

		EmployeeJobPos employeeJobPos = employeeJobPosPersistence.create(employeeJobPosId);

		// Group instance
		employeeJobPos.setGroupId(groupId);

		// Audit fields
		employeeJobPos.setUuid(serviceContext.getUuid());
		employeeJobPos.setCompanyId(user.getCompanyId());
		employeeJobPos.setUserId(user.getUserId());
		employeeJobPos.setUserName(user.getFullName());
		employeeJobPos.setCreateDate(serviceContext.getCreateDate(now));
		employeeJobPos.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		employeeJobPos.setEmployeeId(employeeId);
		employeeJobPos.setJobPostId(jobPostId);

		// role
		Employee mEmployee = employeePersistence.fetchByPrimaryKey(employeeJobPos.getEmployeeId());

		User newUser = UserLocalServiceUtil.fetchUser(mEmployee.getMappingUserId());

		JobPos mJobPos = JobPosLocalServiceUtil.fetchJobPos(mEmployee.getMainJobPostId());
		JobPos currentJobPos = JobPosLocalServiceUtil.fetchJobPos(jobPostId);

		List<Role> roleIds = new ArrayList<Role>();
		roleIds.add(RoleLocalServiceUtil.fetchRole(mJobPos.getMappingRoleId()));
		roleIds.add(RoleLocalServiceUtil.fetchRole(currentJobPos.getMappingRoleId()));

		List<EmployeeJobPos> listEmJobPos = employeeJobPosPersistence.findByF_EmployeeId(mEmployee.getEmployeeId());

		for (EmployeeJobPos ett : listEmJobPos) {
			roleIds.add(RoleLocalServiceUtil
					.fetchRole(JobPosLocalServiceUtil.fetchJobPos(ett.getJobPostId()).getMappingRoleId()));
		}

		RoleLocalServiceUtil.clearUserRoles(newUser.getUserId());
		RoleLocalServiceUtil.addUserRoles(newUser.getUserId(), roleIds);

		Indexer<User> indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(newUser);

		employeeJobPos.setExpandoBridgeAttributes(serviceContext);

		employeeJobPosPersistence.update(employeeJobPos);

		return employeeJobPos;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public EmployeeJobPos deleteEmployeeJobPos(long employeeJobPosId, ServiceContext serviceContext) throws Exception {

		EmployeeJobPos EmployeeJobPos = employeeJobPosPersistence.remove(employeeJobPosId);

		Indexer<EmployeeJobPos> indexer = IndexerRegistryUtil.nullSafeGetIndexer(EmployeeJobPos.class);

		indexer.delete(EmployeeJobPos);

		// role
		Employee mEmployee = employeePersistence.fetchByPrimaryKey(EmployeeJobPos.getEmployeeId());

		User newUser = UserLocalServiceUtil.fetchUser(mEmployee.getMappingUserId());

		JobPos mJobPos = JobPosLocalServiceUtil.fetchJobPos(mEmployee.getMainJobPostId());

		List<Role> roleIds = new ArrayList<Role>();
		roleIds.add(RoleLocalServiceUtil.fetchRole(mJobPos.getMappingRoleId()));

		List<EmployeeJobPos> listEmJobPos = employeeJobPosPersistence.findByF_EmployeeId(mEmployee.getEmployeeId());

		for (EmployeeJobPos ett : listEmJobPos) {
			roleIds.add(RoleLocalServiceUtil
					.fetchRole(JobPosLocalServiceUtil.fetchJobPos(ett.getJobPostId()).getMappingRoleId()));
		}

		RoleLocalServiceUtil.clearUserRoles(newUser.getUserId());
		RoleLocalServiceUtil.addUserRoles(newUser.getUserId(), roleIds);

		return EmployeeJobPos;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EmployeeJobPos updateEmployeeJobPos(long userId, long employeeJobPosId, String documentName,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		EmployeeJobPos employeeJobPos = employeeJobPosPersistence.fetchByPrimaryKey(employeeJobPosId);

		// Audit fields
		employeeJobPos.setUserId(user.getUserId());
		employeeJobPos.setUserName(user.getFullName());
		employeeJobPos.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		employeeJobPosPersistence.update(employeeJobPos);

		return employeeJobPos;
	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
		String keywords = (String) params.get("keywords");
		String employeeId = (String) params.get(EmployeeJobPosTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(EmployeeJobPosTerm.GROUP_ID);

		Indexer<EmployeeJobPos> indexer = IndexerRegistryUtil.nullSafeGetIndexer(EmployeeJobPos.class);

		searchContext.addFullQueryEntryClassName(EmployeeJobPos.class.getName());
		searchContext.setEntryClassNames(new String[] { EmployeeJobPos.class.getName() });
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

		if (Validator.isNotNull(employeeId)) {
			MultiMatchQuery query = new MultiMatchQuery(employeeId);

			query.addFields(EmployeeJobPosTerm.EMPLOYEE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(EmployeeJobPosTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, EmployeeJobPos.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
}