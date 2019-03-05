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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.constants.ResourceUserTerm;
import org.opencps.usermgt.exception.NoSuchResourceUserException;
import org.opencps.usermgt.model.ResourceUser;
import org.opencps.usermgt.service.base.ResourceUserLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the resource user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.ResourceUserLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see ResourceUserLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.ResourceUserLocalServiceUtil
 */
@ProviderType
public class ResourceUserLocalServiceImpl extends ResourceUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.usermgt.service.ResourceUserLocalServiceUtil} to
	 * access the resource user local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ResourceUser addResourceUser(long userId, long groupId, String className, String classPK, long toUserId,
			String fullname, String email, boolean readonly, ServiceContext serviceContext)
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
		
		User toUser = userPersistence.fetchByPrimaryKey(toUserId);
		
		if (Validator.isNull(toUser)) {
			throw new NotFoundException();
		}
		
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long resourceUserId = counterLocalService.increment(ResourceUser.class.getName());

		ResourceUser resourceUser = resourceUserPersistence.create(resourceUserId);

		// Group instance
		resourceUser.setGroupId(groupId);

		// Audit fields
		resourceUser.setUuid(serviceContext.getUuid());
		resourceUser.setCompanyId(user.getCompanyId());
		resourceUser.setUserId(user.getUserId());
		resourceUser.setUserName(user.getFullName());
		resourceUser.setCreateDate(serviceContext.getCreateDate(now));
		resourceUser.setModifiedDate(serviceContext.getCreateDate(now));

		resourceUser.setClassName(className);
		resourceUser.setClassPK(classPK);
		resourceUser.setToUserId(toUserId);
		resourceUser.setFullname(fullname);
		resourceUser.setEmail(email);
		resourceUser.setReadonly(readonly);

		resourceUser.setExpandoBridgeAttributes(serviceContext);

		resourceUserPersistence.update(resourceUser);

		return resourceUser;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ResourceUser deleteResourceUser(long resourceUserId, ServiceContext serviceContext)
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

		ResourceUser resourceUser = null;

		try {

			resourceUser = resourceUserPersistence.remove(resourceUserId);

		} catch (NoSuchResourceUserException e) {
			// TODO Auto-generated catch block
			//throw new NotFoundException();
			_log.error(e);
		}

		return resourceUser;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ResourceUser updateResourceUser(long userId, long resourceUserId, String className, String classPK,
			long toUserId, String fullname, String email, boolean readonly, ServiceContext serviceContext)
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

		User toUser = userPersistence.fetchByPrimaryKey(toUserId);
		
		if (Validator.isNull(toUser)) {
			throw new NotFoundException();
		}
		
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		ResourceUser resourceUser = resourceUserPersistence.fetchByPrimaryKey(resourceUserId);

		if (Validator.isNull(resourceUser)) {
			throw new NotFoundException();
		}

		// Audit fields
		resourceUser.setUserId(user.getUserId());
		resourceUser.setUserName(user.getFullName());
		resourceUser.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields

		resourceUser.setClassName(className);
		resourceUser.setClassPK(classPK);
		resourceUser.setToUserId(toUserId);
		resourceUser.setFullname(fullname);
		resourceUser.setEmail(email);
		resourceUser.setReadonly(readonly);
		
		resourceUser.setExpandoBridgeAttributes(serviceContext);

		resourceUserPersistence.update(resourceUser);

		return resourceUser;
	}

	public ResourceUser fetchByF_className_classPK_toUserId(long groupId, String className, String classPK,
			long toUserId) {
		return resourceUserPersistence.fetchByF_className_classPK_toUserId(groupId, className, classPK, toUserId);
	}

	public List<ResourceUser> findByF_className_classPK(long groupId, String className, String classPK) {
		return resourceUserPersistence.findByF_className_classPK(groupId, className, classPK);
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<ResourceUser> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ResourceUser.class);

		searchContext.addFullQueryEntryClassName(ResourceUser.class.getName());
		searchContext.setEntryClassNames(new String[] { ResourceUser.class.getName() });
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
		String className = (String) params.get(ResourceUserTerm.CLASS_NAME);
		String classPK = (String) params.get(ResourceUserTerm.CLASS_PK);
		String toUserId = (String) params.get(ResourceUserTerm.TO_USERID);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ResourceUserTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(ResourceUserTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(ResourceUserTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(ResourceUserTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(toUserId)) {
			MultiMatchQuery query = new MultiMatchQuery(toUserId);

			query.addFields(ResourceUserTerm.TO_USERID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, ResourceUser.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<ResourceUser> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ResourceUser.class);

		searchContext.addFullQueryEntryClassName(ResourceUser.class.getName());
		searchContext.setEntryClassNames(new String[] { ResourceUser.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String className = (String) params.get(ResourceUserTerm.CLASS_NAME);
		String classPK = (String) params.get(ResourceUserTerm.CLASS_PK);
		String toUserId = (String) params.get(ResourceUserTerm.TO_USERID);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ResourceUserTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(ResourceUserTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(ResourceUserTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(ResourceUserTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(toUserId)) {
			MultiMatchQuery query = new MultiMatchQuery(toUserId);

			query.addFields(ResourceUserTerm.TO_USERID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, ResourceUser.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}


	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ResourceUser adminProcessDelete(Long id) {

		ResourceUser object = resourceUserPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			resourceUserPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ResourceUser adminProcessData(JSONObject objectData) {

		ResourceUser object = null;

		if (objectData.getLong("resourceUserId") > 0) {

			object = resourceUserPersistence.fetchByPrimaryKey(objectData.getLong("resourceUserId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ResourceUser.class.getName());

			object = resourceUserPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setClassName(objectData.getString("className"));
		object.setClassPK(objectData.getString("classPK"));
		object.setToUserId(objectData.getLong("toUserId"));
		object.setFullname(objectData.getString("fullname"));
		object.setEmail(objectData.getString("email"));
		object.setReadonly(objectData.getBoolean("readonly"));

		resourceUserPersistence.update(object);

		return object;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(ResourceUserLocalServiceImpl.class);
}