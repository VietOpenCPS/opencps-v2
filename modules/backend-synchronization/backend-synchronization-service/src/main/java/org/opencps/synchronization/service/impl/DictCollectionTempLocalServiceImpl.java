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
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.synchronization.constants.DictCollectionTempTerm;
import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.exception.NoSuchDictCollectionTempException;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.model.impl.DictCollectionTempImpl;
import org.opencps.synchronization.service.base.DictCollectionTempLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.generic.MatchQuery.Operator;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict collection temp local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.DictCollectionTempLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see DictCollectionTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil
 */
@ProviderType
public class DictCollectionTempLocalServiceImpl
	extends DictCollectionTempLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil} to access the dict collection temp local service.
	 */
	private static Log _log = LogFactoryUtil.getLog(DictCollectionTempLocalServiceImpl.class);
	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param collectionCode
	 * @param collectionName
	 * @param collectionNameEN
	 * @param description
	 * @param serviceContext
	 * @return DictCollectionTemp
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictCollectionTemp addDictCollectionTemp(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, 
			int status,
			int mustSync,
			ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException,
			NoSuchUserException {

		DictCollectionTemp dictColl = dictCollectionTempPersistence.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dictColl)) {

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

		if (Validator.isNotNull(collectionCode)) {

			collectionCode = collectionCode;

		}

		long dictCollectionId = counterLocalService.increment(DictCollectionTemp.class.getName());

		DictCollectionTemp dictCollection = dictCollectionTempPersistence.create(dictCollectionId);

		// Group instance
		dictCollection.setGroupId(groupId);

		// Audit fields
		dictCollection.setUuid(serviceContext.getUuid());
		dictCollection.setCompanyId(user.getCompanyId());
		dictCollection.setUserId(user.getUserId());
		dictCollection.setUserName(user.getFullName());
		dictCollection.setCreateDate(serviceContext.getCreateDate(now));
		dictCollection.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictCollection.setCollectionCode(collectionCode);
		dictCollection.setCollectionName(collectionName);
		dictCollection.setCollectionNameEN(collectionNameEN);
		dictCollection.setDescription(description);
		dictCollection.setStatus(status);
		dictCollection.setMustSync(mustSync);

		dictCollection.setExpandoBridgeAttributes(serviceContext);

		dictCollectionTempPersistence.update(dictCollection);

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param dictCollectionId
	 * @param serviceContext
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictCollectionTemp deleteDictCollectionTemp(long dictCollectionId, ServiceContext serviceContext)
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

		DictCollectionTemp dictCollection = null;
		
		try {
			
			List<DictItemTemp> listItem = dictItemTempPersistence.findByF_dictCollectionId(dictCollectionId);
			
			if (Validator.isNotNull(listItem) && listItem.size() > 0) {

				throw new UnauthorizationException();

			} else {

				dictCollection = dictCollectionTempPersistence.remove(dictCollectionId);

			}

		} catch (NoSuchDictCollectionTempException e) {
			_log.error(e);
			//throw new NotFoundException();

		}

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param dictCollectionId
	 * @param collectionCode
	 * @param collectionName
	 * @param collectionNameEN
	 * @param description
	 * @param serviceContext
	 * @return DictCollectionTemp
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictCollectionTemp updateDictCollectionTemp(long userId, long dictCollectionId, String collectionCode,
			String collectionName, String collectionNameEN, String description, 
			int status,
			int mustSync,
			String dataForm, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException,
			DuplicateCategoryException {

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

		DictCollectionTemp dictCollection = dictCollectionTempPersistence.fetchByPrimaryKey(dictCollectionId);

		DictCollectionTemp dictColl = dictCollectionTempPersistence.fetchByF_dictCollectionCode(collectionCode,
				dictCollection.getGroupId());

		if ( Validator.isNotNull(dictColl) && dictColl.getDictCollectionId() != dictCollectionId) {

			throw new DuplicateCategoryException();

		}

		if (Validator.isNull(dictCollection)) {
			throw new NotFoundException();
		}

		if (Validator.isNotNull(collectionCode)) {

			collectionCode = collectionCode;

		}

		// Audit fields
		dictCollection.setUserId(user.getUserId());
		dictCollection.setUserName(user.getFullName());
		dictCollection.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictCollection.setCollectionCode(collectionCode);
		dictCollection.setCollectionName(collectionName);
		dictCollection.setCollectionNameEN(collectionNameEN);
		dictCollection.setDescription(description);
		dictCollection.setDataForm(dataForm);
		dictCollection.setStatus(status);
		dictCollection.setMustSync(mustSync);
		
		dictCollection.setExpandoBridgeAttributes(serviceContext);

		dictCollectionTempPersistence.update(dictCollection);

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param collectionCode
	 * @param groupId
	 * @return DictCollectionTemp
	 */
	public DictCollectionTemp fetchByF_dictCollectionCode(String collectionCode, long groupId) {
		
		if ("ADMINISTRATIVE_REGION".equalsIgnoreCase(collectionCode)) {
			groupId = 0;
		}

		return dictCollectionTempPersistence.fetchByF_dictCollectionCode(collectionCode, groupId);

	}
	
	/**
	 * @author binhth
	 * @param groupId
	 * @return true if dict collection not found, init Record, false not thing todo
	 */
	public boolean initDictCollectionTemp(long groupId) {

		boolean result = false;

		if (dictCollectionTempPersistence.findByF_dictCollectionByGroup(groupId).size() <= 0) {

			result = true;

		}

		return result;

	}
	
	/**
	 * @author binhth
	 * @param params
	 * 
	 *            <pre>
	 * <ol>
	 * <li> keywords </li>
	 * <li> groupId </li>
	 * <li> userId </li>
	 * <li> collectionCode </li>
	 * </ol>
	 *            </pre>
	 * 
	 * @param sorts
	 * @param start
	 * @param end
	 * @param searchContext
	 * @throws ParseException,
	 *             SearchException
	 */
	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictCollectionTempTerm.GROUP_ID);
		String userId = (String) params.get(DictCollectionTempTerm.USER_ID);
		String collectionCode = (String) params.get(DictCollectionTempTerm.COLLECTION_CODE);

		Indexer<DictCollectionTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictCollectionTemp.class);

		searchContext.addFullQueryEntryClassName(DictCollectionTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictCollectionTemp.class.getName() });
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

				query.addFields(DictCollectionTempTerm.COLLECTION_NAME, DictCollectionTempTerm.COLLECTION_CODE);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictCollectionTempTerm.COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupId)) {
			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);

			TermQuery catQuery1 = new TermQueryImpl(DictItemTempTerm.GROUP_ID, groupId);
			TermQuery catQuery2 = new TermQueryImpl(DictItemTempTerm.GROUP_ID, String.valueOf(0));

			categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);
			categoryQuery.add(catQuery2, BooleanClauseOccur.SHOULD);
			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);
		}

		// if (Validator.isNotNull(groupId)) {
		//
		// MultiMatchQuery query = new MultiMatchQuery(groupId);
		//
		// query.addFields(DictCollectionTempTerm.GROUP_ID);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictCollectionTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictCollectionTemp.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictCollectionTempTerm.GROUP_ID);
		String userId = (String) params.get(DictCollectionTempTerm.USER_ID);
		String collectionCode = (String) params.get(DictCollectionTempTerm.COLLECTION_CODE);

		Indexer<DictCollectionTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictCollectionTemp.class);

		searchContext.addFullQueryEntryClassName(DictCollectionTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictCollectionTemp.class.getName() });
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

				query.addFields(DictCollectionTempTerm.COLLECTION_NAME, DictCollectionTempTerm.COLLECTION_CODE);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictCollectionTempTerm.COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupId)) {
			BooleanQuery categoryQuery = Validator.isNotNull((String) keywords)
					? BooleanQueryFactoryUtil.create((SearchContext) searchContext)
					: indexer.getFullQuery(searchContext);

			TermQuery catQuery1 = new TermQueryImpl(DictItemTerm.GROUP_ID, groupId);
			TermQuery catQuery2 = new TermQueryImpl(DictItemTerm.GROUP_ID, String.valueOf(0));

			categoryQuery.add(catQuery1, BooleanClauseOccur.SHOULD);
			categoryQuery.add(catQuery2, BooleanClauseOccur.SHOULD);
			booleanQuery.add(categoryQuery, BooleanClauseOccur.MUST);
		}

		// if (Validator.isNotNull(groupId)) {
		//
		// MultiMatchQuery query = new MultiMatchQuery(groupId);
		//
		// query.addFields(DictCollectionTempTerm.GROUP_ID);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictCollectionTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictCollectionTemp.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}
	
	@Override
	public List<DictCollectionTemp> findOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictCollectionTemp> comparator = OrderByComparatorFactoryUtil.create(DictCollectionTempImpl.TABLE_NAME, DictCollectionTempTerm.MODIFIED_DATE, true);
		
		return dictCollectionTempPersistence.findByF_dictCollectionNewerThan(date, groupId, start, end, comparator);
	}
	
	@Override
	public long countOlderThanDate(Date date, long groupId) {
		return dictCollectionTempPersistence.countByF_dictCollectionNewerThan(date, groupId);
	}	
}