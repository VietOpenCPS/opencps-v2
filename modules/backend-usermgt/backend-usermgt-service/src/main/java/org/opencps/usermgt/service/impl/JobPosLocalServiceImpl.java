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

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
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
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.opencps.usermgt.constants.JobPosTerm;
import org.opencps.usermgt.constants.UserMGTConstants;
import org.opencps.usermgt.exception.NoSuchJobPosException;
import org.opencps.usermgt.model.JobPos;
import org.opencps.usermgt.service.base.JobPosLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

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
	 * org.mobilink.backend.usermgt.service.JobPosLocalServiceUtil} to access the
	 * job pos local service.
	 */
	public void assignPermission(long jobPosId, String[] actionIds, ServiceContext serviceContext) {

		JobPos jobPos = jobPosPersistence.fetchByPrimaryKey(jobPosId);

		long roleId = jobPos.getMappingRoleId();

		String modelName = UserMGTConstants.WORKINGUNIT_MGT_CENTER;

		String[] listPermission = ActionKeys.getListPermission();

		try {

			ResourcePermissionLocalServiceUtil.setResourcePermissions(jobPos.getCompanyId(), modelName,
					ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(roleId), roleId, actionIds);

		} catch (PortalException e) {
			_log.debug(e);
			for (int i = 0; i < listPermission.length; i++) {

				String actionId = listPermission[i];

				ResourceAction resourceAction = ResourceActionLocalServiceUtil
						.fetchResourceAction(UserMGTConstants.WORKINGUNIT_MGT_CENTER, actionId);

				if (Validator.isNotNull(resourceAction)) {
					resourceAction.setBitwiseValue((long) Math.pow(2, i));
					ResourceActionLocalServiceUtil.updateResourceAction(resourceAction);
				} else {
					ResourceActionLocalServiceUtil.addResourceAction(Role.class.getName(), actionId,
							(long) Math.pow(2, i));
				}

			}

			// for (String actionKey : actionIds) {
			// try {
			// if
			// (!ResourcePermissionLocalServiceUtil.hasResourcePermission(jobPos.getCompanyId(),
			// modelName,
			// ResourceConstants.SCOPE_INDIVIDUAL, "" + roleId, roleId, actionKey)) {
			// ResourcePermissionLocalServiceUtil.setResourcePermissions(jobPos.getCompanyId(),
			// modelName,
			// ResourceConstants.SCOPE_INDIVIDUAL, "" + roleId, roleId, actionIds);
			// } else {
			// System.out.println("Role(" + roleId + ") already have this permission(" +
			// actionKey
			// + ") for this model(" + modelName + ")");
			// }
			// } catch (PortalException ex) {
			// ex.printStackTrace();
			// }
			// }

		}

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JobPos addJobPos(long userId, long groupId, String title, String description, int leader,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, PortalException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		JobPos jobPosCheck = jobPosPersistence.fetchByF_title(groupId, title);

		if (Validator.isNotNull(jobPosCheck)) {
			throw new DuplicateCategoryException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long jobPosId = counterLocalService.increment(JobPos.class.getName());

		// create role name
		// String role_name = title;

		String role_name = title + jobPosId;

		// add role
		// Role role = RoleLocalServiceUtil.addRole(userId, Role.class.getName(),
		// counterLocalService.increment(),
		// role_name, null, null, 1, "", serviceContext);
		Map<Locale, String> titleMap = new HashMap<>();
		titleMap.put(Locale.getDefault(), title);

		Role role = RoleLocalServiceUtil.addRole(userId, Role.class.getName(), counterLocalService.increment(),
				role_name, titleMap, null, RoleConstants.TYPE_SITE, "", serviceContext);

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
		jobPos.setTitle(title);
		jobPos.setDescription(description);
		jobPos.setLeader(leader);

		if (Validator.isNotNull(role)) {
			// TODO: user_group
			// RoleLocalServiceUtil.addGroupRole(groupId, role.getRoleId());

			jobPos.setMappingRoleId(role.getRoleId());
		}

		jobPos.setExpandoBridgeAttributes(serviceContext);

		jobPosPersistence.update(jobPos);

		return jobPos;

	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public JobPos deleteJobPos(long JobPosId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		JobPos jobPos = null;

		try {

			jobPos = jobPosPersistence.remove(JobPosId);

		} catch (NoSuchJobPosException e) {
			// throw new NotFoundException();
			_log.error(e);
		}

		return jobPos;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JobPos updateJobPos(long userId, long jobPosId, String title, String description, int leader,
			ServiceContext serviceContext) throws UnauthenticationException, UnauthorizationException,
			NoSuchUserException, NotFoundException, DuplicateCategoryException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		JobPos jobPos = jobPosPersistence.fetchByPrimaryKey(jobPosId);

		JobPos jobPosCheck = jobPosPersistence.fetchByF_title(jobPos.getGroupId(), title);

		if (Validator.isNotNull(jobPosCheck) && jobPosCheck.getJobPosId() != jobPosId) {
			throw new DuplicateCategoryException();
		}

		// Audit fields
		jobPos.setUserId(user.getUserId());
		jobPos.setUserName(user.getFullName());
		jobPos.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		jobPos.setTitle(title);
		jobPos.setDescription(description);
		jobPos.setLeader(leader);

		jobPosPersistence.update(jobPos);

		return jobPos;
	}

	public JobPos fetchByF_mappingRoleId(long groupId, long mappingRoleId) {
		return jobPosPersistence.fetchByF_mappingRoleId(groupId, mappingRoleId);
	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
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

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(JobPosTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPos.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(JobPosTerm.GROUP_ID);

		Indexer<JobPos> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JobPos.class);

		searchContext.addFullQueryEntryClassName(JobPos.class.getName());
		searchContext.setEntryClassNames(new String[] { JobPos.class.getName() });
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

				query.addFields(JobPosTerm.TITLE);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(JobPosTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPos.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	// LamTV_ADD
	private static Log _log = LogFactoryUtil.getLog(JobPosLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	public JobPos updateJobPosDB(long userId, long groupId, String jobPosCode, String title, String description,
			ServiceContext serviceContext) throws PortalException {

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

		JobPos jobPos = jobPosPersistence.fetchByF_CODE(groupId, jobPosCode);

		// JobPos jobPosCheck = jobPosPersistence.fetchByF_title(jobPos.getGroupId(),
		// title);

		// if (Validator.isNotNull(jobPosCheck) && jobPosCheck.getJobPosId() !=
		// jobPosId) {
		// throw new DuplicateCategoryException();
		// }
		if (jobPos != null) {
			// Audit field
			jobPos.setUserId(user.getUserId());
			jobPos.setUserName(user.getFullName());
			jobPos.setModifiedDate(serviceContext.getCreateDate(now));
			// Other field
			if (Validator.isNotNull(title))
				jobPos.setTitle(title);
			if (Validator.isNotNull(description))
				jobPos.setDescription(description);

		} else {
			long jobPosId = counterLocalService.increment(JobPos.class.getName());
			jobPos = jobPosPersistence.create(jobPosId);
			// create role name
			// String role_name = title;
			String role_name = jobPosCode + StringPool.UNDERLINE + jobPosId;

			// add role
			//_log.info("role_name:" + role_name);
			// Role role = RoleLocalServiceUtil.addRole(userId, Role.class.getName(),
			// counterLocalService.increment(),
			// role_name, null, null, 1, "", serviceContext);

			Map<Locale, String> titleMap = new HashMap<>();
			titleMap.put(Locale.getDefault(), title);

			Role role = RoleLocalServiceUtil.addRole(userId, Role.class.getName(), counterLocalService.increment(),
					role_name, titleMap, null, RoleConstants.TYPE_SITE, "", serviceContext);

			// Audit fields
			jobPos.setUserId(user.getUserId());
			jobPos.setUserName(user.getFullName());
			jobPos.setGroupId(groupId);
			jobPos.setCreateDate(now);
			jobPos.setModifiedDate(serviceContext.getCreateDate(now));
			jobPos.setCompanyId(serviceContext.getCompanyId());

			// Other fields
			jobPos.setJobPosCode(jobPosCode);
			jobPos.setTitle(title);
			jobPos.setDescription(description);
			jobPos.setMappingRoleId(role.getRoleId());
		}

		jobPosPersistence.update(jobPos);

		return jobPos;
	}

	public JobPos getByJobCode(long groupId, String jobCode) {
		return jobPosPersistence.fetchByF_CODE(groupId, jobCode);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public JobPos adminProcessDelete(Long id) {

		JobPos object = jobPosPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			jobPosPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public JobPos adminProcessData(JSONObject objectData) {

		JobPos object = null;

		if (objectData.getLong("jobPosId") > 0) {

			object = jobPosPersistence.fetchByPrimaryKey(objectData.getLong("jobPosId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(JobPos.class.getName());

			object = jobPosPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setUserId(objectData.getLong("userId"));

			String role_name = objectData.getString("jobPosCode") + StringPool.UNDERLINE + id;

			Map<Locale, String> titleMap = new HashMap<>();
			titleMap.put(Locale.getDefault(), objectData.getString("title"));

			Role role;

			try {

				role = RoleLocalServiceUtil.addRole(objectData.getLong("userId"), Role.class.getName(),
						counterLocalService.increment(), role_name, titleMap, null, RoleConstants.TYPE_SITE, "",
						serviceContext);

				object.setMappingRoleId(role.getRoleId());

			} catch (PortalException e) {
				_log.error(e);
			}

		}

		object.setUserId(objectData.getLong("userId"));

		object.setJobPosCode(objectData.getString("jobPosCode"));
		object.setTitle(objectData.getString("title"));
		object.setDescription(objectData.getString("description"));
		object.setLeader(objectData.getInt("leader"));

		jobPosPersistence.update(object);

		return object;
	}

	public List<JobPos> findByF_mappingRoleIds(long groupId, long[] mappingRoleIds) {
		return jobPosPersistence.findByF_mappingRoleIds(groupId, mappingRoleIds);
	}
	
	public List<JobPos> findByG(long groupId) {
		return jobPosPersistence.findByG(groupId);
	}
}