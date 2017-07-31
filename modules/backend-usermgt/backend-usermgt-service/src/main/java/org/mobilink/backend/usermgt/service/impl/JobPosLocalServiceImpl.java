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

package org.mobilink.backend.usermgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.mobilink.backend.usermgt.constants.JobPosTerm;
import org.mobilink.backend.usermgt.constants.UserMGTConstants;
import org.mobilink.backend.usermgt.model.JobPos;
import org.mobilink.backend.usermgt.model.WorkingUnit;
import org.mobilink.backend.usermgt.service.base.JobPosLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
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
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the job pos local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.JobPosLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see JobPosLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.JobPosLocalServiceUtil
 */
@ProviderType
public class JobPosLocalServiceImpl extends JobPosLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.JobPosLocalServiceUtil} to access
	 * the job pos local service.
	 */
	// assign permission to role JobPos
	public void assignPermission(long JobPosId, String[] actionIds, ServiceContext serviceContext)
			throws PortalException {

		JobPos jobPos = jobPosPersistence.fetchByPrimaryKey(JobPosId);

		long roleId = jobPos.getMappingRoleId();

		String modelName = UserMGTConstants.WORKINGUNIT_MGT_CENTER;

		String[] listPermission = UserMGTConstants.LIST_PERMISSION;

		// for (int i = 0; i < listPermission.length; i++) {
		//
		// String actionId = listPermission[i];
		// System.out.println("JobPosLocalServiceImpl.mAssignPermission()" +
		// actionId);
		// ResourceAction resourceAction = ResourceActionLocalServiceUtil
		// .fetchResourceAction(MWorkingUnitConstants.WORKINGUNIT_MGT_CENTER,
		// actionId);
		//
		// if (Validator.isNotNull(resourceAction)) {
		// resourceAction.setBitwiseValue((long) Math.pow(2, i));
		// ResourceActionLocalServiceUtil.updateResourceAction(resourceAction);
		// }
		// }

		ResourcePermissionLocalServiceUtil.setResourcePermissions(jobPos.getCompanyId(), modelName,
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(roleId), roleId, actionIds);

		// for (String actionKey : actionIds) {
		// try {
		// if
		// (!ResourcePermissionLocalServiceUtil.hasResourcePermission(JobPos.getCompanyId(),
		// modelName,
		// ResourceConstants.SCOPE_INDIVIDUAL, "" + roleId, roleId, actionKey))
		// {
		// ResourcePermissionLocalServiceUtil.setResourcePermissions(JobPos.getCompanyId(),
		// modelName,
		// ResourceConstants.SCOPE_INDIVIDUAL, "" + roleId, roleId, actionKey);
		// } else {
		// System.out.println("Role(" + roleId + ") already have this
		// permission(" + actionKey
		// + ") for this model(" + modelName + ")");
		// }
		// } catch (PortalException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JobPos addJobPos(long userId, long groupId, long workingUnitId, String title, String description,
			long directWorkingUnitId, int leader, long mappingRoleId, boolean hiddenJobPos,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long jobPosId = counterLocalService.increment(JobPos.class.getName());

		JobPos jobPos = jobPosPersistence.create(jobPosId);

		// Group instance
		jobPos.setGroupId(groupId);

		// Audit fields
		jobPos.setUuid(serviceContext.getUuid());
		jobPos.setCompanyId(user.getCompanyId());
		jobPos.setUserId(user.getUserId());
		jobPos.setUserName(user.getFullName());
		jobPos.setCreateDate(serviceContext.getCreateDate(now));
		jobPos.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		jobPos.setWorkingUnitId(workingUnitId);
		jobPos.setTitle(title);
		jobPos.setDescription(description);
		jobPos.setDirectWorkingUnitId(directWorkingUnitId);
		jobPos.setLeader(leader);

		WorkingUnit mWorkingUnit = workingUnitPersistence.fetchByPrimaryKey(workingUnitId);
		// add role
		Role role = RoleLocalServiceUtil.addRole(userId, JobPos.class.getName(), counterLocalService.increment(),
				title + " " + mWorkingUnit.getName(), null, null, 1, "", serviceContext);

		RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

		jobPos.setMappingRoleId(role.getRoleId());

		jobPos.setHiddenJobPos(hiddenJobPos);

		jobPos.setExpandoBridgeAttributes(serviceContext);

		jobPosPersistence.update(jobPos);

		return jobPos;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public JobPos deleteJobPos(long JobPosId, ServiceContext serviceContext) throws Exception {

		JobPos jobPos = jobPosPersistence.remove(JobPosId);

		return jobPos;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JobPos updateJobPos(long userId, long JobPosId, String title, String description,
			long directWorkingUnitId, int leader, long mappingRoleId, boolean hiddenJobPos,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		JobPos jobPos = jobPosPersistence.fetchByPrimaryKey(JobPosId);

		// Audit fields
		jobPos.setUserId(user.getUserId());
		jobPos.setUserName(user.getFullName());
		jobPos.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		jobPos.setTitle(title);
		jobPos.setDescription(description);
		jobPos.setDirectWorkingUnitId(directWorkingUnitId);
		jobPos.setLeader(leader);

		jobPosPersistence.update(jobPos);

		return jobPos;
	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
		String keywords = (String) params.get("keywords");
		String workingUnitId = (String) params.get(JobPosTerm.WORKING_UNIT_ID);
		String groupId = (String) params.get(JobPosTerm.GROUP_ID);

		Indexer<JobPos> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JobPos.class);

		searchContext.addFullQueryEntryClassName(JobPos.class.getName());
		searchContext.setEntryClassNames(new String[] { JobPos.class.getName() });
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

				query.addFields(JobPosTerm.TITLE);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(workingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(workingUnitId);

			query.addFields(JobPosTerm.WORKING_UNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(JobPosTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPos.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
}