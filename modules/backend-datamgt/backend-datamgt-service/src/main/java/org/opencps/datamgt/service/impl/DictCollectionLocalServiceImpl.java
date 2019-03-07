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
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.GroupBy;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.QueryConfig;
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
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.exception.NoSuchDictCollectionException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.impl.DictCollectionImpl;
import org.opencps.datamgt.service.base.DictCollectionLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict collection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.DictCollectionLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see DictCollectionLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.DictCollectionLocalServiceUtil
 */
@ProviderType
public class DictCollectionLocalServiceImpl extends DictCollectionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.DictCollectionLocalServiceUtil} to
	 * access the dict collection local service.
	 */
	private static Log _log = LogFactoryUtil.getLog(DictCollectionLocalServiceImpl.class);
	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param collectionCode
	 * @param collectionName
	 * @param collectionNameEN
	 * @param description
	 * @param serviceContext
	 * @return DictCollection
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictCollection addDictCollection(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException,
			NoSuchUserException {
		
		
		DictCollection dictColl = dictCollectionPersistence.fetchByF_dictCollectionCode(collectionCode, groupId);

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

		long dictCollectionId = counterLocalService.increment(DictCollection.class.getName());

		DictCollection dictCollection = dictCollectionPersistence.create(dictCollectionId);

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

		dictCollection.setExpandoBridgeAttributes(serviceContext);

		return dictCollectionPersistence.update(dictCollection);

//		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param dictCollectionId
	 * @param serviceContext
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictCollection deleteDictCollection(long dictCollectionId, ServiceContext serviceContext)
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

		DictCollection dictCollection = null;
		
		try {
			
			List<DictItem> listItem = dictItemPersistence.findByF_dictCollectionId(dictCollectionId);
			
			if (Validator.isNotNull(listItem) && listItem.size() > 0) {

				throw new UnauthorizationException();

			} else {

				dictCollection = dictCollectionPersistence.remove(dictCollectionId);

			}

		} catch (NoSuchDictCollectionException e) {
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
	 * @return DictCollection
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictCollection updateDictCollection(long userId, long dictCollectionId, String collectionCode,
			String collectionName, String collectionNameEN, String description, String dataForm, ServiceContext serviceContext)
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

		DictCollection dictCollection = dictCollectionPersistence.fetchByPrimaryKey(dictCollectionId);

		DictCollection dictColl = dictCollectionPersistence.fetchByF_dictCollectionCode(collectionCode,
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
		
		dictCollection.setExpandoBridgeAttributes(serviceContext);

		return dictCollectionPersistence.update(dictCollection);

//		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param collectionCode
	 * @param groupId
	 * @return DictCollection
	 */
	@ThreadLocalCachable
	public DictCollection fetchByF_dictCollectionCode(String collectionCode, long groupId) {
		
		if ("ADMINISTRATIVE_REGION".toLowerCase().equalsIgnoreCase(collectionCode)) {
			groupId = 0;
		}

		return dictCollectionPersistence.fetchByF_dictCollectionCode(collectionCode, groupId);

	}
	
	/**
	 * @author binhth
	 * @param groupId
	 * @return true if dict collection not found, init Record, false not thing todo
	 */
	public boolean initDictCollection(long groupId) {

		boolean result = false;

		if (dictCollectionPersistence.findByF_dictCollectionByGroup(groupId).size() <= 0) {

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
		String groupId = (String) params.get(DictCollectionTerm.GROUP_ID);
		String userId = (String) params.get(DictCollectionTerm.USER_ID);
		String collectionCode = (String) params.get(DictCollectionTerm.COLLECTION_CODE);
		String status = (String) params.get(DictCollectionTerm.STATUS);
		
		Indexer<DictCollection> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictCollection.class);

		searchContext.addFullQueryEntryClassName(DictCollection.class.getName());
		searchContext.setEntryClassNames(new String[] { DictCollection.class.getName() });
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

				query.addFields(DictCollectionTerm.COLLECTION_NAME, DictCollectionTerm.COLLECTION_CODE);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictCollectionTerm.COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupId) && !"0".equals(groupId)) {
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
		// query.addFields(DictCollectionTerm.GROUP_ID);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictCollectionTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(status)) {

			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(DictCollectionTerm.STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictCollection.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictCollectionTerm.GROUP_ID);
		String userId = (String) params.get(DictCollectionTerm.USER_ID);
		String collectionCode = (String) params.get(DictCollectionTerm.COLLECTION_CODE);
		String status = (String) params.get(DictCollectionTerm.STATUS);
		
		Indexer<DictCollection> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictCollection.class);

		searchContext.addFullQueryEntryClassName(DictCollection.class.getName());
		searchContext.setEntryClassNames(new String[] { DictCollection.class.getName() });
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

				query.addFields(DictCollectionTerm.COLLECTION_NAME, DictCollectionTerm.COLLECTION_CODE);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}
		if (Validator.isNotNull(collectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(collectionCode);

			query.addFields(DictCollectionTerm.COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(groupId) && !"0".equals(groupId)) {
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
		// query.addFields(DictCollectionTerm.GROUP_ID);
		//
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		//
		// }

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictCollectionTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(status)) {

			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(DictCollectionTerm.STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictCollection.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}
	
	@Override
	public List<DictCollection> findOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictCollection> comparator = OrderByComparatorFactoryUtil.create(DictCollectionImpl.TABLE_NAME, DictCollectionTerm.MODIFIED_DATE, true);
		
		return dictCollectionPersistence.findByF_dictCollectionNewerThan(date, groupId, start, end, comparator);
	}
	
	@Override
	public long countOlderThanDate(Date date, long groupId) {
		return dictCollectionPersistence.countByF_dictCollectionNewerThan(date, groupId);
	}

	//LamTV_ Process output DictCollection to DB
	@Indexable(type = IndexableType.REINDEX)
	public DictCollection updateDictCollectionDB(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, Integer status) throws NoSuchUserException {
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

		DictCollection dictCollection = dictCollectionPersistence.fetchByF_dictCollectionCode(collectionCode, groupId);
//		_log.info("collectionCode: "+collectionCode);
//		_log.info("dictCollection: "+dictCollection);
		if (dictCollection != null) {
			dictCollection.setModifiedDate(now);

			// Other fields
			if (Validator.isNotNull(collectionCode)) {
				dictCollection.setCollectionCode(collectionCode);
			}
			if (Validator.isNotNull(collectionName)) {
				dictCollection.setCollectionName(collectionName);
			}
			if (Validator.isNotNull(collectionNameEN)) {
				dictCollection.setCollectionNameEN(collectionNameEN);
			}
			if (Validator.isNotNull(description)) {
				dictCollection.setDescription(description);
			}
			if (Validator.isNotNull(status)) {
				dictCollection.setStatus(status);
			} else {
				dictCollection.setStatus(1);
			}
		} else {
			long dictCollectionId = counterLocalService.increment(DictCollection.class.getName());

			dictCollection = dictCollectionPersistence.create(dictCollectionId);

			// Group instance
			dictCollection.setGroupId(groupId);

			// Audit fields
			dictCollection.setCompanyId(user.getCompanyId());
			dictCollection.setUserId(user.getUserId());
			dictCollection.setUserName(user.getFullName());
			dictCollection.setCreateDate(now);
			dictCollection.setModifiedDate(now);

			// Other fields
			dictCollection.setCollectionCode(collectionCode);
			dictCollection.setCollectionName(collectionName);
			dictCollection.setCollectionNameEN(collectionNameEN);
			dictCollection.setDescription(description);
			if (Validator.isNotNull(status)) {
				dictCollection.setStatus(status);
			} else {
				dictCollection.setStatus(1);
			}
		}

		return dictCollectionPersistence.update(dictCollection);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public DictCollection active(long dictCollectionId) {
		DictCollection dc = dictCollectionPersistence.fetchByPrimaryKey(dictCollectionId);
		if (dc != null) {
			dc.setStatus(DictCollectionTerm.STATUS_ACTIVE);
			return dictCollectionPersistence.update(dc);
		}
		else {
			return dc;
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public DictCollection inactive(long dictCollectionId) {
		DictCollection dc = dictCollectionPersistence.fetchByPrimaryKey(dictCollectionId);
		if (dc != null) {
			dc.setStatus(DictCollectionTerm.STATUS_INACTIVE);
			return dictCollectionPersistence.update(dc);
		}
		else {
			return dc;
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public DictCollection changeStatus(long dictCollectionId, int status) {
		DictCollection dc = dictCollectionPersistence.fetchByPrimaryKey(dictCollectionId);
		if (dc != null) {
			dc.setStatus(status);
			return dictCollectionPersistence.update(dc);
		}
		else {
			return dc;
		}
	}
	

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DictCollection adminProcessDelete(Long id) {

		DictCollection object = dictCollectionPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dictCollectionPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DictCollection adminProcessData(JSONObject objectData) {

		DictCollection object = null;

		if (objectData.getLong("dictCollectionId") > 0) {

			object = dictCollectionPersistence.fetchByPrimaryKey(objectData.getLong("dictCollectionId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DictCollection.class.getName());

			object = dictCollectionPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		
		object.setCollectionCode(objectData.getString("collectionCode"));
		object.setCollectionName(objectData.getString("collectionName"));
		object.setCollectionNameEN(objectData.getString("collectionNameEN"));
		object.setDescription(objectData.getString("description"));
		object.setDataForm(objectData.getString("dataForm"));
		object.setStatus(objectData.getInt("status"));

		dictCollectionPersistence.update(object);

		return object;
	}

	//Add DictCollection Publish
	@Indexable(type = IndexableType.REINDEX)
	public DictCollection updateDictCollectionPublish(long companyId, long userId, long groupId, String userName,
			String collectionCode, String collectionName, String collectionNameEN, String description, int status) {

		long dictCollectionId = counterLocalService.increment(DictCollection.class.getName());

		DictCollection dictCollection = dictCollectionPersistence.create(dictCollectionId);

		// Group instance
		dictCollection.setGroupId(groupId);

		// Audit fields
		dictCollection.setCompanyId(companyId);
		dictCollection.setUserId(userId);
		dictCollection.setUserName(userName);
		
		Date now = new Date();
		dictCollection.setCreateDate(now);
		dictCollection.setModifiedDate(now);

		// Other fields
		dictCollection.setCollectionCode(collectionCode);
		dictCollection.setCollectionName(collectionName);
		dictCollection.setCollectionNameEN(collectionNameEN);
		dictCollection.setDescription(description);
		dictCollection.setStatus(status);

		return dictCollectionPersistence.update(dictCollection);
	}

	public List<DictCollection> findByG(long groupId) {
		return dictCollectionPersistence.findByF_dictCollectionByGroup(groupId);
	}
}