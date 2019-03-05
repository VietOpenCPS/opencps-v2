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
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.constants.ResourceRoleTerm;
import org.opencps.usermgt.exception.NoSuchResourceRoleException;
import org.opencps.usermgt.model.ResourceRole;
import org.opencps.usermgt.service.base.ResourceRoleLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the resource role local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.ResourceRoleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see ResourceRoleLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.ResourceRoleLocalServiceUtil
 */
@ProviderType
public class ResourceRoleLocalServiceImpl extends ResourceRoleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.usermgt.service.ResourceRoleLocalServiceUtil} to access the
	 * resource role local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ResourceRole addResourceRole(long userId, long groupId, String className, String classPK, long roleId,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException, NotFoundException {
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

		Role role = RoleLocalServiceUtil.fetchRole(roleId);

		if (Validator.isNull(role)) {
			throw new NotFoundException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long resourceRoleId = counterLocalService.increment(ResourceRole.class.getName());

		ResourceRole resourceRole = resourceRolePersistence.create(resourceRoleId);

		// Group instance
		resourceRole.setGroupId(groupId);

		// Audit fields
		resourceRole.setUuid(serviceContext.getUuid());
		resourceRole.setCompanyId(user.getCompanyId());
		resourceRole.setUserId(user.getUserId());
		resourceRole.setUserName(user.getFullName());
		resourceRole.setCreateDate(serviceContext.getCreateDate(now));
		resourceRole.setModifiedDate(serviceContext.getCreateDate(now));

		resourceRole.setClassName(className);
		resourceRole.setClassPK(classPK);
		resourceRole.setRoleId(roleId);

		resourceRole.setExpandoBridgeAttributes(serviceContext);

		resourceRolePersistence.update(resourceRole);

		return resourceRole;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ResourceRole deleteResourceRole(long resourceRoleId, ServiceContext serviceContext)
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

		ResourceRole resourceRole = null;

		try {

			resourceRole = resourceRolePersistence.remove(resourceRoleId);

		} catch (NoSuchResourceRoleException e) {
			// TODO Auto-generated catch block
			// throw new NotFoundException();
			_log.error(e);
		}

		return resourceRole;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ResourceRole updateResourceRole(long userId, long resourceRoleId, String className, String classPK,
			long roleId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {
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

		Role role = RoleLocalServiceUtil.fetchRole(roleId);

		if (Validator.isNull(role)) {
			throw new NotFoundException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		ResourceRole resourceRole = resourceRolePersistence.fetchByPrimaryKey(resourceRoleId);

		if (Validator.isNull(resourceRole)) {
			throw new NotFoundException();
		}

		// Audit fields
		resourceRole.setUserId(user.getUserId());
		resourceRole.setUserName(user.getFullName());
		resourceRole.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields

		resourceRole.setClassName(className);
		resourceRole.setClassPK(classPK);
		resourceRole.setRoleId(roleId);

		resourceRole.setExpandoBridgeAttributes(serviceContext);

		resourceRolePersistence.update(resourceRole);

		return resourceRole;
	}

	public ResourceRole fetchByF_className_classPK_roleId(long groupId, String className, String classPK, long roleId) {
		return resourceRolePersistence.fetchByF_className_classPK_roleId(groupId, className, classPK, roleId);
	}

	public List<ResourceRole> findByF_className_classPK(long groupId, String className, String classPK) {
		return resourceRolePersistence.findByF_className_classPK(groupId, className, classPK);
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<ResourceRole> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ResourceRole.class);

		searchContext.addFullQueryEntryClassName(ResourceRole.class.getName());
		searchContext.setEntryClassNames(new String[] { ResourceRole.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(ResourceRoleTerm.CLASS_NAME);
		String classPK = (String) params.get(ResourceRoleTerm.CLASS_PK);
		String roleId = (String) params.get(ResourceRoleTerm.ROLE_ID);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ResourceRoleTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(ResourceRoleTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(ResourceRoleTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(ResourceRoleTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(roleId)) {
			MultiMatchQuery query = new MultiMatchQuery(roleId);

			query.addFields(ResourceRoleTerm.ROLE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, ResourceRole.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<ResourceRole> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ResourceRole.class);

		searchContext.addFullQueryEntryClassName(ResourceRole.class.getName());
		searchContext.setEntryClassNames(new String[] { ResourceRole.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(ResourceRoleTerm.CLASS_NAME);
		String classPK = (String) params.get(ResourceRoleTerm.CLASS_PK);
		String roleId = (String) params.get(ResourceRoleTerm.ROLE_ID);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ResourceRoleTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(ResourceRoleTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(ResourceRoleTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(ResourceRoleTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(roleId)) {
			MultiMatchQuery query = new MultiMatchQuery(roleId);

			query.addFields(ResourceRoleTerm.ROLE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, ResourceRole.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ResourceRole adminProcessDelete(Long id) {

		ResourceRole object = resourceRolePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			resourceRolePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ResourceRole adminProcessData(JSONObject objectData) {

		ResourceRole object = null;

		if (objectData.getLong("resourceRoleId") > 0) {

			object = resourceRolePersistence.fetchByPrimaryKey(objectData.getLong("resourceRoleId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ResourceRole.class.getName());

			object = resourceRolePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setClassName(objectData.getString("className"));
		object.setClassPK(objectData.getString("classPK"));
		object.setRoleId(objectData.getLong("roleId"));
		object.setReadonly(objectData.getInt("readonly"));

		resourceRolePersistence.update(object);

		return object;
	}

	private static final Log _log = LogFactoryUtil.getLog(ResourceRoleLocalServiceImpl.class);
}