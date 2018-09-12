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

package org.opencps.synchronization.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.ModelNameKeys;
import org.opencps.synchronization.constants.DictCollectionTempTerm;
import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.constants.DictItemGroupTempTerm;
import org.opencps.synchronization.exception.NoSuchDictItemGroupTempException;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.impl.DictItemGroupTempImpl;
import org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.base.DictItemGroupTempLocalServiceBaseImpl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
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

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict item group temp local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.DictItemGroupTempLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see DictItemGroupTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil
 */
@ProviderType
public class DictItemGroupTempLocalServiceImpl
	extends DictItemGroupTempLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil} to access the dict item group temp local service.
	 */
	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param DictItemGroupId
	 * @param dictItemId
	 * @param serviceContext
	 * @return DictItemGroupTemp
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictItemGroupTemp addDictItemGroupTemp(long userId, long groupId, long dictGroupId, long dictItemId,
			String groupCode, ServiceContext serviceContext) throws DuplicateCategoryException,
			UnauthenticationException, UnauthorizationException, NoSuchUserException {

		DictItemGroupTemp dictItemColl = dictItemGroupTempPersistence.fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
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

		long dictItemGroupId = counterLocalService.increment(DictItemGroupTemp.class.getName());

		DictItemGroupTemp dictItemGroup = dictItemGroupTempPersistence.create(dictItemGroupId);

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

		dictItemGroupTempPersistence.update(dictItemGroup);

		return dictItemGroup;
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
	public DictItemGroupTemp deleteDictItemGroupTemp(long dictItemGroupId, ServiceContext serviceContext)
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
		DictItemGroupTemp dictItemGroup = null;
		try {

			dictItemGroup = dictItemGroupTempPersistence.remove(dictItemGroupId);

		} catch (NoSuchDictItemGroupTempException e) {
			_log.error(e);
			//throw new NotFoundException();

		}

		return dictItemGroup;

	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictItemGroupTemp deleteDictItemGroupTempNoneAuthen(long dictItemGroupId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		DictItemGroupTemp dictItemGroup = dictItemGroupTempPersistence.fetchByPrimaryKey(dictItemGroupId);
		try {

			dictItemGroup = dictItemGroupTempPersistence.remove(dictItemGroupId);

		} catch (NoSuchDictItemGroupTempException e) {
			_log.error(e);
			//throw new NotFoundException();

		}

		return dictItemGroup;

	}

	/**
	 * @author binhth
	 * @param userId
	 * @param dictItemGroupId
	 * @param dictItemId
	 * @param serviceContext
	 * @return DictItemGroupTemp
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictItemGroupTemp updateDictItemGroupTemp(long userId, long dictItemGroupId, long dictItemId,
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

		DictItemGroupTemp dictItemGroup = dictItemGroupTempPersistence.fetchByPrimaryKey(dictItemGroupId);

		DictItemGroupTemp dictItemColl = dictItemGroupTempPersistence.fetchByF_dictItemId_dictGroupId(
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

		dictItemGroupTempPersistence.update(dictItemGroup);

		return dictItemGroup;
	}

	/**
	 * @author binhth
	 * @param groupId
	 * @param dictItemId
	 * @param DictItemGroupId
	 * @return DictItemGroupTemp
	 */
	public DictItemGroupTemp fetchByF_dictItemId_dictGroupId(long groupId, long dictGroupId, long dictItemId) {

		return dictItemGroupTempPersistence.fetchByF_dictItemId_dictGroupId(groupId, dictGroupId, dictItemId);

	}

	/**
	 * @author binhth
	 * @param groupId
	 * @param dictGroupId
	 * @param dictItemId
	 * @return List<DictItemGroupTemp>
	 */
	public List<DictItemGroupTemp> findByF_dictItemId(long groupId, long dictItemId) {

		return dictItemGroupTempPersistence.findByF_dictItemId(groupId, dictItemId);

	}

	public List<DictItemGroupTemp> findByDictGroupId(long groupId, long dictGroupId) {

		return dictItemGroupTempPersistence.findByF_dictGroupId(groupId, dictGroupId);

	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictItemGroupTempTerm.GROUP_ID);
		String userId = (String) params.get(DictItemGroupTempTerm.USER_ID);
		String dictItemId = (String) params.get(DictItemGroupTempTerm.DICT_ITEM_ID);
		String groupCode = (String) params.get(DictGroupTempTerm.GROUP_CODE);
		String collectionCode = (String) params.get(DictGroupTempTerm.DICT_COLLECTION_CODE);
		
		//_log.info(collectionCode);
		
		//_log.info(groupCode);

		Indexer<DictItemGroupTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemGroupTemp.class);

		searchContext.addFullQueryEntryClassName(DictItemGroupTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictItemGroupTemp.class.getName() });
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

			query.addFields(DictItemGroupTempTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictItemGroupTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictItemId)) {

			MultiMatchQuery query = new MultiMatchQuery(dictItemId);

			query.addFields(DictItemGroupTempTerm.DICT_ITEM_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
		
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictGroupTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
		

		if (Validator.isNotNull(groupCode)) {

			MultiMatchQuery query = new MultiMatchQuery(groupCode);

			query.addFields(DictGroupTempTerm.GROUP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}


		
/*
		if (Validator.isNotNull(groupCode)) {

			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);
			TermQuery catQuery1 = new TermQueryImpl(DictGroupTempTerm.GROUP_CODE, groupCode);
			TermQuery catQuery2 = new TermQueryImpl(DictGroupTempTerm.DICT_COLLECTION_CODE, collectionCode);

			categoryQuery.add(catQuery1, BooleanClauseOccur.MUST);
			categoryQuery.add(catQuery2, BooleanClauseOccur.MUST);
			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);

		}*/
/*		
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictGroupTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
*/
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictItemGroupTemp.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictItemGroupTempTerm.GROUP_ID);
		String userId = (String) params.get(DictItemGroupTempTerm.USER_ID);
		String dictItemId = (String) params.get(DictItemGroupTempTerm.DICT_ITEM_ID);
		String groupCode = (String) params.get(DictGroupTempTerm.GROUP_CODE);

		String collectionCode = (String) params.get(DictGroupTempTerm.DICT_COLLECTION_CODE);

		Indexer<DictItemGroupTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemGroupTemp.class);

		searchContext.addFullQueryEntryClassName(DictItemGroupTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictItemGroupTemp.class.getName() });
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

			query.addFields(DictItemGroupTempTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictItemGroupTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictItemId)) {

			MultiMatchQuery query = new MultiMatchQuery(dictItemId);

			query.addFields(DictItemGroupTempTerm.DICT_ITEM_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

/*		if (Validator.isNotNull(groupCode)) {

			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);
			TermQuery catQuery1 = new TermQueryImpl(DictGroupTempTerm.GROUP_CODE, groupCode);
			TermQuery catQuery2 = new TermQueryImpl(DictGroupTempTerm.DICT_COLLECTION_CODE, collectionCode);

			categoryQuery.add(catQuery1, BooleanClauseOccur.MUST);
			categoryQuery.add(catQuery2, BooleanClauseOccur.MUST);
			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);

		}
*/
/*		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictGroupTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
*/
				
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictGroupTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupCode)) {

			MultiMatchQuery query = new MultiMatchQuery(groupCode);

			query.addFields(DictGroupTempTerm.GROUP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictItemGroupTemp.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}
	
	@Override
	public List<DictItemGroupTemp> findOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictItemGroupTemp> comparator = OrderByComparatorFactoryUtil.create(DictItemGroupTempImpl.TABLE_NAME, DictCollectionTempTerm.MODIFIED_DATE, true);
		
		return dictItemGroupTempPersistence.findByF_newerThan(date, groupId, start, end, comparator);
	}
	
	@Override
	public long countOlderThanDate(Date date, long groupId) {
		return dictItemGroupTempPersistence.countByF_newerThan(date, groupId);
	}

	@Override
	public List<DictItemGroupTemp> findByDictItemTemp(long groupId, long dictItemId, int start, int end) {
		return dictItemGroupTempPersistence.findByF_dictItemId(groupId, dictItemId, start, end);
	}
	
	Log _log = LogFactoryUtil.getLog(DictItemGroupTempLocalServiceUtil.class);		
}