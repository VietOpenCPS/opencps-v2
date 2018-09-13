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
import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.exception.NoSuchDictGroupTempException;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.impl.DictGroupTempImpl;
import org.opencps.synchronization.service.base.DictGroupTempLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.search.generic.MatchQuery.Operator;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict group temp local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.DictGroupTempLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see DictGroupTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.DictGroupTempLocalServiceUtil
 */
@ProviderType
public class DictGroupTempLocalServiceImpl
	extends DictGroupTempLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.DictGroupTempLocalServiceUtil} to access the dict group temp local service.
	 */
	
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

	private static Log _log = LogFactoryUtil.getLog(DictGroupTempLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictGroupTemp addDictGroupTemp(long userId, long groupId, long dictCollectionId, String groupCode, String groupName,
			String groupNameEN, String groupDescription, 
			int status,
			ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException,
			NoSuchUserException {

		/*
		 * DictGroupTemp dictColl = dictGroupTempPersistence.fetchByF_groupCode(groupCode, groupId);
		 * ThanhNV: hotFix check duplicate
		 * 
		 */
		DictGroupTemp dictColl = dictGroupTempPersistence.fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId);
		
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

		long dictGroupId = counterLocalService.increment(DictGroupTemp.class.getName());

		DictGroupTemp dictGroup = dictGroupTempPersistence.create(dictGroupId);

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
		dictGroup.setStatus(status);

		dictGroup.setExpandoBridgeAttributes(serviceContext);

		dictGroupTempPersistence.update(dictGroup);

		return dictGroup;
	}

	/**
	 * @author binhth
	 * @param dictGroupId
	 * @param serviceContext
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictGroupTemp deleteDictGroupTemp(long dictGroupId, ServiceContext serviceContext)
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

		DictGroupTemp dictGroup = dictGroupTempPersistence.fetchByPrimaryKey(dictGroupId);
		try {

			if (dictItemGroupTempPersistence.findByF_dictGroupId(dictGroup.getGroupId(), dictGroupId)
					.size() > 0) {
				throw new UnauthorizationException();
			} else {

				dictGroup = dictGroupTempPersistence.remove(dictGroupId);

			}

		} catch (NoSuchDictGroupTempException e) {
			_log.error(e);
			//throw new NotFoundException();

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
	 * @return DictGroupTemp
	 * @throws DuplicateCategoryException
	 * @throws UnauthenticationException
	 * @throws UnauthorizationException
	 * @throws NoSuchUserException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictGroupTemp updateDictGroupTemp(long userId, long dictGroupId, long dictCollectionId, String groupCode,
			String groupName, String groupNameEN, String groupDescription, 
			int status,
			ServiceContext serviceContext)
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

		DictGroupTemp dictGroup = dictGroupTempPersistence.fetchByPrimaryKey(dictGroupId);

		/*
		 * DictGroupTemp dictColl = dictGroupTempPersistence.fetchByF_groupCode(groupCode, dictGroup.getGroupId());
		 * ThanhNV: hotFix check duplicate
		 * 
		 */
		DictGroupTemp dictColl = dictGroupTempPersistence.fetchByGC_GI_DCI(groupCode, dictGroup.getGroupId(), dictCollectionId);

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
		dictGroup.setStatus(status);

		dictGroup.setExpandoBridgeAttributes(serviceContext);

		dictGroupTempPersistence.update(dictGroup);

		return dictGroup;
	}

	/**
	 * @author binhth
	 * @param groupCode
	 * @param groupId
	 * @return DictGroupTemp
	 */
	public DictGroupTemp fetchByF_DictGroupCode(String groupCode, long groupId) {

		return dictGroupTempPersistence.fetchByF_groupCode(groupCode, groupId);

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
		String groupId = (String) params.get(DictGroupTempTerm.GROUP_ID);
		String userId = (String) params.get(DictGroupTempTerm.USER_ID);
		String dictCollectionCode = (String) params.get(DictGroupTempTerm.DICT_COLLECTION_CODE);
		
		Indexer<DictGroupTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictGroupTemp.class);

		searchContext.addFullQueryEntryClassName(DictGroupTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictGroupTemp.class.getName() });
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

				query.addFields(DictGroupTempTerm.GROUP_NAME, DictGroupTempTerm.GROUP_NAME, DictGroupTempTerm.GROUP_NAME_EN);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}

		if (Validator.isNotNull(groupId)) {

			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(DictGroupTempTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictGroupTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
		
		if (Validator.isNotNull(dictCollectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(dictCollectionCode);

			query.addFields(DictGroupTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
		
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictGroupTemp.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	@SuppressWarnings("deprecation")
	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(DictGroupTempTerm.GROUP_ID);
		String userId = (String) params.get(DictGroupTempTerm.USER_ID);
		String dictCollectionCode = (String) params.get(DictGroupTempTerm.DICT_COLLECTION_CODE);
		
		Indexer<DictGroupTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictGroupTemp.class);

		searchContext.addFullQueryEntryClassName(DictGroupTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictGroupTemp.class.getName() });
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

				query.addFields(DictGroupTempTerm.GROUP_NAME, DictGroupTempTerm.GROUP_NAME, DictGroupTempTerm.GROUP_NAME_EN);

				Operator operator = Operator.AND;

				query.setOperator(operator);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}

		}

		if (Validator.isNotNull(groupId)) {

			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(DictGroupTempTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(userId)) {

			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictGroupTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictCollectionCode)) {

			MultiMatchQuery query = new MultiMatchQuery(dictCollectionCode);

			query.addFields(DictGroupTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictGroupTemp.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}
	
	public List<DictGroupTemp> getDictGroupTempByDictCollection(long groupId, long dictCollectionId, int start, int end) {
		return dictGroupTempPersistence.findByGID_DC(dictCollectionId, groupId, start, end);
	}
	
	public DictGroupTemp getByGC_GI_DCI(String groupCode, long groupId, long dictCollectionId) {
		return dictGroupTempPersistence.fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}
	
	@Override
	public List<DictGroupTemp> findOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictGroupTemp> comparator = OrderByComparatorFactoryUtil.create(DictGroupTempImpl.TABLE_NAME, DictGroupTempTerm.MODIFIED_DATE, true);
		
		return dictGroupTempPersistence.findByF_dictGroupNewerThan(date, groupId, start, end, comparator);
	}
	
	@Override
	public long countOlderThanDate(Date date, long groupId) {
		return dictGroupTempPersistence.countByF_dictGroupNewerThan(date, groupId);
	}	
}