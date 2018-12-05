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
import com.liferay.portal.kernel.search.generic.MatchQuery.Operator;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.DataInUsedException;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.ModelNameKeys;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.exception.NoSuchDictGroupException;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.impl.DictItemGroupImpl;
import org.opencps.datamgt.service.base.DictGroupLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.DictGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see DictGroupLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.DictGroupLocalServiceUtil
 */
@ProviderType
public class DictGroupLocalServiceImpl extends DictGroupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.DictGroupLocalServiceUtil} to access the dict
	 * group local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(DictGroupLocalServiceImpl.class);

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param dictCollectionId
	 * @param groupCode
	 * @param groupName
	 * @param groupNameEN
	 * @param groupDescription
	 * @param serviceContext
	 * @return DictGroup
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictGroup addDictGroup(long userId, long groupId, long dictCollectionId, String groupCode, String groupName,
			String groupNameEN, String groupDescription, ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException,
			NoSuchUserException {

		/*
		 * DictGroup dictColl = dictGroupPersistence.fetchByF_groupCode(groupCode,
		 * groupId); ThanhNV: hotFix check duplicate
		 * 
		 */
		DictGroup dictColl = dictGroupPersistence.fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId);

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

		if (Validator.isNotNull(groupCode)) {

			groupCode = groupCode;

		}

		long dictGroupId = counterLocalService.increment(DictGroup.class.getName());

		DictGroup dictGroup = dictGroupPersistence.create(dictGroupId);

		// Group instance
		dictGroup.setGroupId(groupId);

		// Audit fields
		dictGroup.setUuid(serviceContext.getUuid());
		dictGroup.setCompanyId(user.getCompanyId());
		dictGroup.setUserId(user.getUserId());
		dictGroup.setUserName(user.getFullName());
		dictGroup.setCreateDate(serviceContext.getCreateDate(now));
		dictGroup.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictGroup.setDictCollectionId(dictCollectionId);
		dictGroup.setGroupCode(groupCode);
		dictGroup.setGroupName(groupName);
		dictGroup.setGroupNameEN(groupNameEN);
		dictGroup.setGroupDescription(groupDescription);

		dictGroup.setExpandoBridgeAttributes(serviceContext);

		return dictGroupPersistence.update(dictGroup);

		// return dictGroup;
	}

	/**
	 * @author binhth
	 * @param dictGroupId
	 * @param serviceContext
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictGroup deleteDictGroup(long dictGroupId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, DataInUsedException {
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

		DictGroup dictGroup = dictGroupPersistence.fetchByPrimaryKey(dictGroupId);
		try {

			if (dictItemGroupPersistence.findByF_dictGroupId(dictGroup.getGroupId(), dictGroupId).size() > 0) {
				throw new DataInUsedException();
			} else {

				dictGroup = dictGroupPersistence.remove(dictGroupId);

			}

		} catch (NoSuchDictGroupException e) {
			_log.error(e);
			// throw new NotFoundException();

		}

		return dictGroup;

	}

	/**
	 * @author binhth
	 * @param userId
	 * @param dictGroupId
	 * @param dictCollectionId
	 * @param groupCode
	 * @param groupName
	 * @param groupNameEN
	 * @param groupDescription
	 * @param serviceContext
	 * @return DictGroup
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictGroup updateDictGroup(long userId, long dictGroupId, long dictCollectionId, String groupCode,
			String groupName, String groupNameEN, String groupDescription, ServiceContext serviceContext)
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

		DictGroup dictGroup = dictGroupPersistence.fetchByPrimaryKey(dictGroupId);

		/*
		 * DictGroup dictColl = dictGroupPersistence.fetchByF_groupCode(groupCode,
		 * dictGroup.getGroupId()); ThanhNV: hotFix check duplicate
		 * 
		 */
		DictGroup dictColl = dictGroupPersistence.fetchByGC_GI_DCI(groupCode, dictGroup.getGroupId(), dictCollectionId);

		if (Validator.isNotNull(dictColl) && dictColl.getDictGroupId() != dictGroupId) {

			throw new DuplicateCategoryException();

		}

		if (Validator.isNull(dictGroup)) {
			throw new NotFoundException();
		}

		if (Validator.isNotNull(groupCode)) {

			groupCode = groupCode;

		}

		// Audit fields
		dictGroup.setUserId(user.getUserId());
		dictGroup.setUserName(user.getFullName());
		dictGroup.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictGroup.setDictCollectionId(dictCollectionId);
		dictGroup.setGroupCode(groupCode);
		dictGroup.setGroupName(groupName);
		dictGroup.setGroupNameEN(groupNameEN);
		dictGroup.setGroupDescription(groupDescription);

		dictGroup.setExpandoBridgeAttributes(serviceContext);

		return dictGroupPersistence.update(dictGroup);

		// return dictGroup;
	}

	/**
	 * @author binhth
	 * @param groupCode
	 * @param groupId
	 * @return DictGroup
	 */
	public DictGroup fetchByF_DictGroupCode(String groupCode, long groupId) {

		return dictGroupPersistence.fetchByF_groupCode(groupCode, groupId);

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
		String groupId = (String) params.get(DictGroupTerm.GROUP_ID);
		String userId = (String) params.get(DictGroupTerm.USER_ID);
		String dictCollectionCode = (String) params.get(DictGroupTerm.DICT_COLLECTION_CODE);

		Indexer<DictGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictGroup.class);

		searchContext.addFullQueryEntryClassName(DictGroup.class.getName());
		searchContext.setEntryClassNames(new String[] { DictGroup.class.getName() });
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

				query.addFields(DictGroupTerm.GROUP_NAME, DictGroupTerm.GROUP_NAME, DictGroupTerm.GROUP_NAME_EN);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}

		if (Validator.isNotNull(groupId) && !"0".equals(groupId)) {

			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(DictGroupTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictGroupTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictCollectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(dictCollectionCode);

			query.addFields(DictGroupTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictGroup.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictGroupTerm.GROUP_ID);
		String userId = (String) params.get(DictGroupTerm.USER_ID);
		String dictCollectionCode = (String) params.get(DictGroupTerm.DICT_COLLECTION_CODE);

		Indexer<DictGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictGroup.class);

		searchContext.addFullQueryEntryClassName(DictGroup.class.getName());
		searchContext.setEntryClassNames(new String[] { DictGroup.class.getName() });
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

				query.addFields(DictGroupTerm.GROUP_NAME, DictGroupTerm.GROUP_NAME, DictGroupTerm.GROUP_NAME_EN);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}

		if (Validator.isNotNull(groupId) && !"0".equals(groupId)) {

			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(DictGroupTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictGroupTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictCollectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(dictCollectionCode);

			query.addFields(DictGroupTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictGroup.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	public List<DictGroup> getDictGroupByDictCollection(long groupId, long dictCollectionId, int start, int end) {
		return dictGroupPersistence.findByGID_DC(dictCollectionId, groupId, start, end);
	}

	public DictGroup getByGC_GI_DCI(String groupCode, long groupId, long dictCollectionId) {
		return dictGroupPersistence.fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}

	@Override
	public List<DictGroup> findOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictGroup> comparator = OrderByComparatorFactoryUtil.create(DictItemGroupImpl.TABLE_NAME,
				DictGroupTerm.MODIFIED_DATE, true);

		return dictGroupPersistence.findByF_dictGroupNewerThan(date, groupId, start, end, comparator);
	}

	@Override
	public long countOlderThanDate(Date date, long groupId) {
		return dictGroupPersistence.countByF_dictGroupNewerThan(date, groupId);
	}

	// LamTV_Process output DB
	@Indexable(type = IndexableType.REINDEX)
	public DictGroup updateDictGroupDB(long userId, long groupId, long dictCollectionId, String groupCode,
			String groupName, String groupNameEN, String groupDescription, ServiceContext serviceContext)
			throws NoSuchUserException {

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

		long dictGroupId = counterLocalService.increment(DictGroup.class.getName());

		DictGroup dictGroup = dictGroupPersistence.create(dictGroupId);

		// Group instance
		dictGroup.setGroupId(groupId);

		// Audit fields
		dictGroup.setUuid(serviceContext.getUuid());
		dictGroup.setCompanyId(user.getCompanyId());
		dictGroup.setUserId(user.getUserId());
		dictGroup.setUserName(user.getFullName());
		dictGroup.setCreateDate(serviceContext.getCreateDate(now));
		dictGroup.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictGroup.setDictCollectionId(dictCollectionId);
		dictGroup.setGroupCode(groupCode);
		dictGroup.setGroupName(groupName);
		dictGroup.setGroupNameEN(groupNameEN);
		dictGroup.setGroupDescription(groupDescription);

		return dictGroupPersistence.update(dictGroup);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public DictGroup adminProcessDelete(Long id) {

		DictGroup object = dictGroupPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dictGroupPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DictGroup adminProcessData(JSONObject objectData) {

		DictGroup object = null;

		if (objectData.getLong("dictGroupId") > 0) {

			object = dictGroupPersistence.fetchByPrimaryKey(objectData.getLong("dictGroupId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DictGroup.class.getName());

			object = dictGroupPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setDictCollectionId(objectData.getLong("dictCollectionId"));
		object.setGroupCode(objectData.getString("groupCode"));
		object.setGroupName(objectData.getString("groupName"));
		object.setGroupNameEN(objectData.getString("groupNameEN"));
		object.setGroupDescription(objectData.getString("groupDescription"));

		dictGroupPersistence.update(object);

		return object;
	}

}