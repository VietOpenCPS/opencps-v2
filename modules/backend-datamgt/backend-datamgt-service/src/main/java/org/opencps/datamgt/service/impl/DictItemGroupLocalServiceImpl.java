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

package org.opencps.datamgt.service.impl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.ModelNameKeys;
import org.opencps.datamgt.constants.DictCollectionTerm;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.exception.NoSuchDictItemGroupException;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.model.impl.DictItemGroupImpl;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.base.DictItemGroupLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict item group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.DictItemGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see DictItemGroupLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.DictItemGroupLocalServiceUtil
 */
@ProviderType
public class DictItemGroupLocalServiceImpl extends DictItemGroupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.DictItemGroupLocalServiceUtil} to access the dict
	 * item group local service.
	 */
	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param DictItemGroupId
	 * @param dictItemId
	 * @param serviceContext
	 * @return DictItemGroup
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictItemGroup addDictItemGroup(long userId, long groupId, long dictGroupId, long dictItemId,
			String groupCode, ServiceContext serviceContext) throws DuplicateCategoryException,
			UnauthenticationException, UnauthorizationException, NoSuchUserException {

		DictItemGroup dictItemColl = dictItemGroupPersistence.fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
				dictItemId);

		if (Validator.isNotNull(dictItemColl)) {

			throw new DuplicateCategoryException();

		}

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

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

		long dictItemGroupId = counterLocalService.increment(DictItemGroup.class.getName());

		DictItemGroup dictItemGroup = dictItemGroupPersistence.create(dictItemGroupId);

		// Group instance
		dictItemGroup.setGroupId(groupId);

		// Audit fields
		dictItemGroup.setUuid(serviceContext.getUuid());
		dictItemGroup.setCompanyId(user.getCompanyId());
		dictItemGroup.setUserId(user.getUserId());
		dictItemGroup.setUserName(user.getFullName());
		dictItemGroup.setCreateDate(serviceContext.getCreateDate(now));
		dictItemGroup.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictItemGroup.setDictGroupId(dictGroupId);
		dictItemGroup.setDictItemId(dictItemId);
		dictItemGroup.setDictGroupName(groupCode);

		dictItemGroup.setExpandoBridgeAttributes(serviceContext);

		return dictItemGroupPersistence.update(dictItemGroup);

		// return dictItemGroup;
	}

	/**
	 * @author binhth
	 * @param dictItemGroupId
	 * @param serviceContext
	 * @throws UnauthenticationException,
	 *             UnauthorizationException, NotFoundException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictItemGroup deleteDictItemGroup(long dictItemGroupId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}
		DictItemGroup dictItemGroup = null;
		try {

			dictItemGroup = dictItemGroupPersistence.remove(dictItemGroupId);

		} catch (NoSuchDictItemGroupException e) {
			_log.error(e);
			// throw new NotFoundException();

		}

		return dictItemGroup;

	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictItemGroup deleteDictItemGroupNoneAuthen(long dictItemGroupId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		DictItemGroup dictItemGroup = dictItemGroupPersistence.fetchByPrimaryKey(dictItemGroupId);
		try {

			dictItemGroup = dictItemGroupPersistence.remove(dictItemGroupId);

		} catch (NoSuchDictItemGroupException e) {
			_log.error(e);
			// throw new NotFoundException();
		}

		return dictItemGroup;

	}

	/**
	 * @author binhth
	 * @param userId
	 * @param dictItemGroupId
	 * @param dictItemId
	 * @param serviceContext
	 * @return DictItemGroup
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictItemGroup updateDictItemGroup(long userId, long dictItemGroupId, long dictItemId,
			ServiceContext serviceContext) throws UnauthenticationException, UnauthorizationException,
			NotFoundException, NoSuchUserException, DuplicateCategoryException {

		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

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

		DictItemGroup dictItemGroup = dictItemGroupPersistence.fetchByPrimaryKey(dictItemGroupId);

		DictItemGroup dictItemColl = dictItemGroupPersistence.fetchByF_dictItemId_dictGroupId(
				dictItemGroup.getGroupId(), dictItemGroup.getDictItemGroupId(), dictItemId);

		if (Validator.isNotNull(dictItemColl)) {

			throw new DuplicateCategoryException();

		}

		if (Validator.isNull(dictItemGroup)) {
			throw new NotFoundException();
		}

		dictItemGroup.setUserId(user.getUserId());
		dictItemGroup.setUserName(user.getFullName());
		dictItemGroup.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictItemGroup.setDictItemId(dictItemId);

		dictItemGroup.setExpandoBridgeAttributes(serviceContext);

		return dictItemGroupPersistence.update(dictItemGroup);

		// return dictItemGroup;
	}

	/**
	 * @author binhth
	 * @param groupId
	 * @param dictItemId
	 * @param DictItemGroupId
	 * @return DictItemGroup
	 */
	public DictItemGroup fetchByF_dictItemId_dictGroupId(long groupId, long dictGroupId, long dictItemId) {

		return dictItemGroupPersistence.fetchByF_dictItemId_dictGroupId(groupId, dictGroupId, dictItemId);

	}

	/**
	 * @author binhth
	 * @param groupId
	 * @param dictGroupId
	 * @param dictItemId
	 * @return List<DictItemGroup>
	 */
	public List<DictItemGroup> findByF_dictItemId(long groupId, long dictItemId) {

		return dictItemGroupPersistence.findByF_dictItemId(groupId, dictItemId);

	}

	public List<DictItemGroup> findByDictGroupId(long groupId, long dictGroupId) {

		return dictItemGroupPersistence.findByF_dictGroupId(groupId, dictGroupId);

	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictItemGroupTerm.GROUP_ID);
		String userId = (String) params.get(DictItemGroupTerm.USER_ID);
		String dictItemId = (String) params.get(DictItemGroupTerm.DICT_ITEM_ID);
		String groupCode = (String) params.get(DictGroupTerm.GROUP_CODE);
		String collectionCode = (String) params.get(DictGroupTerm.DICT_COLLECTION_CODE);

		// _log.info(collectionCode);

		// _log.info(groupCode);

		Indexer<DictItemGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemGroup.class);

		searchContext.addFullQueryEntryClassName(DictItemGroup.class.getName());
		searchContext.setEntryClassNames(new String[] { DictItemGroup.class.getName() });
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

		if (Validator.isNotNull(groupId)) {

			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(DictItemGroupTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictItemGroupTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictItemId)) {

			MultiMatchQuery query = new MultiMatchQuery(dictItemId);

			query.addFields(DictItemGroupTerm.DICT_ITEM_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictGroupTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupCode)) {

			MultiMatchQuery query = new MultiMatchQuery(groupCode);

			query.addFields(DictGroupTerm.GROUP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		/*
		 * if (Validator.isNotNull(groupCode)) {
		 * 
		 * BooleanQuery categoryQuery = Validator.isNotNull((String) keywords) ?
		 * BooleanQueryFactoryUtil.create((SearchContext) searchContext) :
		 * indexer.getFullQuery(searchContext); TermQuery catQuery1 = new
		 * TermQueryImpl(DictGroupTerm.GROUP_CODE, groupCode); TermQuery catQuery2 = new
		 * TermQueryImpl(DictGroupTerm.DICT_COLLECTION_CODE, collectionCode);
		 * 
		 * categoryQuery.add(catQuery1, BooleanClauseOccur.MUST);
		 * categoryQuery.add(catQuery2, BooleanClauseOccur.MUST);
		 * booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);
		 * 
		 * }
		 */
		/*
		 * if (Validator.isNotNull(collectionCode)) {
		 * 
		 * MultiMatchQuery query = new MultiMatchQuery(collectionCode);
		 * 
		 * query.addFields(DictGroupTerm.DICT_COLLECTION_CODE);
		 * 
		 * booleanQuery.add(query, BooleanClauseOccur.MUST);
		 * 
		 * }
		 */
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictItemGroup.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictItemGroupTerm.GROUP_ID);
		String userId = (String) params.get(DictItemGroupTerm.USER_ID);
		String dictItemId = (String) params.get(DictItemGroupTerm.DICT_ITEM_ID);
		String groupCode = (String) params.get(DictGroupTerm.GROUP_CODE);

		String collectionCode = (String) params.get(DictGroupTerm.DICT_COLLECTION_CODE);

		Indexer<DictItemGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemGroup.class);

		searchContext.addFullQueryEntryClassName(DictItemGroup.class.getName());
		searchContext.setEntryClassNames(new String[] { DictItemGroup.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {

			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);

		} else {

			booleanQuery = indexer.getFullQuery(searchContext);

		}

		if (Validator.isNotNull(groupId)) {

			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(DictItemGroupTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictItemGroupTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictItemId)) {

			MultiMatchQuery query = new MultiMatchQuery(dictItemId);

			query.addFields(DictItemGroupTerm.DICT_ITEM_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		/*
		 * if (Validator.isNotNull(groupCode)) {
		 * 
		 * BooleanQuery categoryQuery = Validator.isNotNull((String) keywords) ?
		 * BooleanQueryFactoryUtil.create((SearchContext) searchContext) :
		 * indexer.getFullQuery(searchContext); TermQuery catQuery1 = new
		 * TermQueryImpl(DictGroupTerm.GROUP_CODE, groupCode); TermQuery catQuery2 = new
		 * TermQueryImpl(DictGroupTerm.DICT_COLLECTION_CODE, collectionCode);
		 * 
		 * categoryQuery.add(catQuery1, BooleanClauseOccur.MUST);
		 * categoryQuery.add(catQuery2, BooleanClauseOccur.MUST);
		 * booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);
		 * 
		 * }
		 */
		/*
		 * if (Validator.isNotNull(collectionCode)) {
		 * 
		 * MultiMatchQuery query = new MultiMatchQuery(collectionCode);
		 * 
		 * query.addFields(DictGroupTerm.DICT_COLLECTION_CODE);
		 * 
		 * booleanQuery.add(query, BooleanClauseOccur.MUST);
		 * 
		 * }
		 */

		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictGroupTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupCode)) {

			MultiMatchQuery query = new MultiMatchQuery(groupCode);

			query.addFields(DictGroupTerm.GROUP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictItemGroup.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	@Override
	public List<DictItemGroup> findOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictItemGroup> comparator = OrderByComparatorFactoryUtil.create(DictItemGroupImpl.TABLE_NAME,
				DictCollectionTerm.MODIFIED_DATE, true);

		return dictItemGroupPersistence.findByF_newerThan(date, groupId, start, end, comparator);
	}

	@Override
	public long countOlderThanDate(Date date, long groupId) {
		return dictItemGroupPersistence.countByF_newerThan(date, groupId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DictItemGroup adminProcessDelete(Long id) {

		DictItemGroup object = dictItemGroupPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dictItemGroupPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DictItemGroup adminProcessData(JSONObject objectData) {

		DictItemGroup object = null;

		if (objectData.getLong("dictItemGroupId") > 0) {

			object = dictItemGroupPersistence.fetchByPrimaryKey(objectData.getLong("dictItemGroupId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DictItemGroup.class.getName());

			object = dictItemGroupPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setDictGroupId(objectData.getLong("dictGroupId"));
		object.setDictItemId(objectData.getLong("dictItemId"));
		object.setDictGroupName(objectData.getString("dictGroupName"));

		dictItemGroupPersistence.update(object);

		return object;
	}

	Log _log = LogFactoryUtil.getLog(DictItemGroupLocalServiceUtil.class);
}