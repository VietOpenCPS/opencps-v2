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
import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.exception.NoSuchDictItemTempException;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.model.impl.DictItemTempImpl;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.base.DictItemTempLocalServiceBaseImpl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
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
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dict item temp local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.synchronization.service.DictItemTempLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author trungdk
 * @see DictItemTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.DictItemTempLocalServiceUtil
 */
@ProviderType
public class DictItemTempLocalServiceImpl
	extends DictItemTempLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.synchronization.service.DictItemTempLocalServiceUtil} to access the dict item temp local service.
	 */
	
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictItemTemp addDictItemTemp(long userId, long groupId, long dictCollectionId, String itemCode, String itemName,
			String itemNameEN, String itemDescription, long parentItemId, String sibling, int level, String metaData,
			int status,
			ServiceContext serviceContext) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NoSuchDictItemTempException, SystemException {
		// KhoaVD sua tam cho nay
		// DictItemTemp dictColl =
		// dictItemTempPersistence.fetchByF_dictItemCode(itemCode, groupId);
		DictItemTemp dictColl = dictItemTempPersistence.fetchByF_dictItemCode_dictCollectionId(itemCode, dictCollectionId,
				groupId);

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

		long dictItemId = counterLocalService.increment(DictItemTemp.class.getName());

		DictItemTemp dictItem = dictItemTempPersistence.create(dictItemId);

		sibling = getSibling(groupId, dictCollectionId, parentItemId, sibling, level);

		String treeIndex = getTreeIndex(dictItemId, parentItemId, sibling);

		// Group instance
		dictItem.setGroupId(groupId);

		// Audit fields
		dictItem.setUuid(serviceContext.getUuid());
		dictItem.setCompanyId(user.getCompanyId());
		dictItem.setUserId(user.getUserId());
		dictItem.setUserName(user.getFullName());
		dictItem.setCreateDate(serviceContext.getCreateDate(now));
		dictItem.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictItem.setDictCollectionId(dictCollectionId);
		itemCode = itemCode;
		dictItem.setItemCode(itemCode);
		dictItem.setItemName(itemName);
		dictItem.setItemNameEN(itemNameEN);
		dictItem.setItemDescription(itemDescription);
		dictItem.setParentItemId(parentItemId);
		dictItem.setSibling(Validator.isNotNull(sibling) ? sibling : String.valueOf(1));
		dictItem.setTreeIndex(treeIndex);
		dictItem.setLevel(StringUtil.count(treeIndex, StringPool.PERIOD));
		dictItem.setMetaData(metaData);
		dictItem.setStatus(status);
		
		// referent dictcollection
		BaseModel<?> baseModel = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictCollectionId);

		dictItem.setExpandoBridgeAttributes(baseModel);

		dictItemTempPersistence.update(dictItem);

		return dictItem;
	}

	@Indexable(type = IndexableType.REINDEX)
	public DictItemTemp updateDictItemTempListener(long userId, long dictItemId, long dictCollectionId, String itemCode,
			String itemName, String itemNameEN, String itemDescription, long parentItemId, String sibling, int level,
			String metaData, 
			int status,
			ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException, NoSuchUserException,
			NotFoundException, PortalException {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		DictItemTemp dictItem = dictItemTempPersistence.fetchByPrimaryKey(dictItemId);

		DictItemTemp dictColl = dictItemTempPersistence.fetchByF_dictItemCode(itemCode, dictItem.getGroupId());

		if (Validator.isNotNull(dictColl) && dictColl.getDictItemId() != dictItemId) {

			throw new DuplicateCategoryException();

		}

		if (Validator.isNull(dictItem)) {
			throw new NotFoundException();
		}

		// Audit fields
		dictItem.setUserId(user.getUserId());
		dictItem.setUserName(user.getFullName());
		dictItem.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictItem.setDictCollectionId(dictCollectionId);
		dictItem.setItemCode(itemCode);
		dictItem.setItemName(itemName);
		dictItem.setItemNameEN(itemNameEN);
		dictItem.setItemDescription(itemDescription);
		dictItem.setParentItemId(parentItemId);
		dictItem.setSibling(sibling);
		dictItem.setMetaData(metaData);
		dictItem.setStatus(status);

		String treeIndex = getTreeIndex(dictItemId, parentItemId, sibling);

		dictItem.setTreeIndex(treeIndex);
		dictItem.setLevel(StringUtil.count(treeIndex, StringPool.PERIOD));
		// referent dictcollection
		BaseModel<?> baseModel = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictCollectionId);

		dictItem.setExpandoBridgeAttributes(baseModel);
		// dictItem.setExpandoBridgeAttributes(serviceContext);

		dictItemTempPersistence.update(dictItem);

		return dictItem;
	}

	/**
	 * @param parentItemId
	 * @return
	 */
	public List<DictItemTemp> findByF_parentItemId(long parentItemId) {

		return dictItemTempPersistence.findByF_parentItemId(parentItemId);
	}

	protected String getSibling(long groupId, long dictCollectionId, long parentItemId, String sibling, int level) {

		if (parentItemId == 0) {

		} else {

			DictItemTemp parentItem = dictItemTempPersistence.fetchByPrimaryKey(parentItemId);

			level = Validator.isNotNull(parentItem) ? (parentItem.getLevel() + 1) : 0;
		}

		DictItemTemp dictItem = dictItemTempPersistence.fetchByF_parentItemId_level_Last(groupId, dictCollectionId,
				parentItemId, level, null);
		if ((Validator.isNotNull(dictItem) && "0".equals(sibling)) || "0".equals(sibling)) {
			try {
				sibling = GetterUtil.getInteger(dictItem.getSibling(), 1) + 1 + StringPool.BLANK;
			} catch (Exception e) {
				_log.error(e);
				sibling = String.valueOf(1);
			}
		}
		return sibling;

	}

	protected String getTreeIndex(long dictItemId, long dictParentItemId, String sibling)
			throws NoSuchDictItemTempException {

		if (Validator.isNull(sibling)) {
			sibling = String.valueOf(1);
		}

		if (dictParentItemId == 0) {

			String ext = "";

			for (int i = 0; i < 4 - sibling.length(); i++) {

				ext += "0";

			}

			return ext + sibling;

		} else if (dictParentItemId > 0) {

			DictItemTemp parentItem = dictItemTempPersistence.findByPrimaryKey(dictParentItemId);

			// if(Validator.isNull(sibling) || GetterUtil.get(sibling, 0) == 0){
			// DictItemTemp ett =
			// dictItemTempPersistence.fetchByF_parentItemId_Last(parentItem.getDictItemId(),
			// null);
			// sibling
			// }

			String ext = "";

			for (int i = 0; i < 4 - sibling.length(); i++) {
				ext += "0";
			}

			return parentItem.getTreeIndex() + StringPool.PERIOD + ext + Integer.toHexString(Integer.valueOf(sibling));
		} else {
			throw new NoSuchDictItemTempException();
		}
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public DictItemTemp deleteDictItemTemp(long dictItemId, ServiceContext serviceContext)
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
		DictItemTemp dictItem = null;

		try {

			List<DictItemTemp> listItem = dictItemTempPersistence.findByF_parentItemId(dictItemId);

			if (Validator.isNotNull(listItem) && listItem.size() > 0) {
				throw new UnauthorizationException();
			} else {
				dictItem = dictItemTempPersistence.remove(dictItemId);
			}
		} catch (NoSuchDictItemTempException e) {
			_log.error(e);
			//throw new NotFoundException();
		}

		return dictItem;
	}

	public void deleteDictItemTemp(long groupId, String itemCode, ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException, NoSuchUserException,
			NotFoundException, PortalException {
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
		
		DictItemTemp dictItem = dictItemTempPersistence.fetchByF_dictItemCode(itemCode, groupId);
		
		List<DictItemGroupTemp> lsDictItem = dictItemGroupTempPersistence.findByF_dictItemId(groupId, dictItem.getPrimaryKey());
		
		_log.info("DictItemGroupSize_" + dictItem.getPrimaryKey() + "_" + groupId + "_" + lsDictItem.size());
		
		try {
			dictItemTempLocalService.deleteDictItemTemp(dictItem.getDictItemId());
			//remove DictItemTemp
			for (DictItemGroupTemp dig : lsDictItem) {
				dictItemGroupTempPersistence.remove(dig);
			}

		} catch (NoSuchDictItemTempException e) {
			_log.error(e);
			//throw new NotFoundException();
		}

	}

	/**
	 * @param userId
	 * @param dictCollectionId
	 * @param fullName
	 * @param companyName
	 * @param telNo
	 * @param email
	 * @param mobilinkId
	 * @param userMappingId
	 * @param outSide
	 * @param isOrg
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DictItemTemp updateDictItemTemp(long userId, long dictItemId, long dictCollectionId, String itemCode,
			String itemName, String itemNameEN, String itemDescription, long parentItemId, String sibling, int level,
			String metaData, 
			int status,
			ServiceContext serviceContext)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException, NoSuchUserException,
			NotFoundException, PortalException {
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

		DictItemTemp dictItem = dictItemTempPersistence.fetchByPrimaryKey(dictItemId);
		// Khoavd tam sua
		// DictItemTemp dictColl =
		// dictItemTempPersistence.fetchByF_dictItemCode(itemCode,
		// dictItem.getGroupId());
		DictItemTemp dictColl = dictItemTempPersistence.fetchByF_dictItemCode_dictCollectionId(itemCode, dictCollectionId,
				dictItem.getGroupId());

		if (Validator.isNotNull(dictColl) && dictColl.getDictItemId() != dictItemId) {

			throw new DuplicateCategoryException();

		}

		if (Validator.isNull(dictItem)) {
			throw new NotFoundException();
		}

		// Audit fields
		dictItem.setUserId(user.getUserId());
		dictItem.setUserName(user.getFullName());
		dictItem.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		dictItem.setDictCollectionId(dictCollectionId);
		dictItem.setItemCode(itemCode);
		dictItem.setItemName(itemName);
		dictItem.setItemNameEN(itemNameEN);
		dictItem.setItemDescription(itemDescription);
		dictItem.setParentItemId(parentItemId);
		dictItem.setSibling(sibling);
		dictItem.setMetaData(metaData);
		dictItem.setStatus(status);

		String treeIndex = getTreeIndex(dictItemId, parentItemId, sibling);

		dictItem.setTreeIndex(treeIndex);
		dictItem.setLevel(StringUtil.count(treeIndex, StringPool.PERIOD));
		// referent dictcollection
		BaseModel<?> baseModel = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictCollectionId);

		dictItem.setExpandoBridgeAttributes(baseModel);
		// dictItem.setExpandoBridgeAttributes(serviceContext);

		dictItemTempPersistence.update(dictItem);

		return dictItem;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		Indexer<DictItemTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemTemp.class);

		searchContext.addFullQueryEntryClassName(DictItemTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictItemTemp.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		// params.put(DictCollectionTerm.COLLECTION_CODE, keywords);
		// params.put("expandoAttributes", keywords);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String dictCollectionId = (String) params.get(DictItemTempTerm.DICT_COLLECTION_ID);
		String dictItemParentId = String.valueOf(params.get(DictItemTempTerm.PARENT_ITEM_ID));
		String parentItemCode = (String) params.get(DictItemTempTerm.PARENT_ITEM_CODE);
		
		/*
		 * ThanhNv: fixbug get all DictItemTemp in a collection
		if (Validator.isNull(parentItemCode)) {
			parentItemCode = "0";
		}
		*/

		String dictItemCode = (String) params.get(DictItemTempTerm.ITEM_CODE);
		String keywords = (String) params.get("keywords");
		String groupId = String.valueOf((params.get("groupId")));
		String userId = (String) params.get("userId");
		String itemLv = (String) params.get("itemLv");
		String dictCollectionCode = (String) params.get(DictItemTempTerm.DICT_COLLECTION_CODE);

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

				query.addFields(DictItemTempTerm.ITEM_NAME, DictItemTempTerm.ITEM_NAME_EN, DictItemTempTerm.ITEM_CODE);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		// DUNG BO SUNG.
		if (Validator.isNotNull(dictCollectionId)) {
			MultiMatchQuery query = new MultiMatchQuery(dictCollectionId);

			query.addFields(DictItemTempTerm.DICT_COLLECTION_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dictItemParentId)) {
			MultiMatchQuery query = new MultiMatchQuery(dictItemParentId);

			query.addFields(DictItemTempTerm.PARENT_ITEM_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(parentItemCode)) {
			MultiMatchQuery query = new MultiMatchQuery(parentItemCode);

			query.addFields(DictItemTempTerm.PARENT_ITEM_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(itemLv)) {
			MultiMatchQuery query = new MultiMatchQuery(itemLv);

			query.addFields(DictItemTempTerm.LEVEL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictItemCode)) {
			MultiMatchQuery query = new MultiMatchQuery(dictItemCode);

			query.addFields(DictItemTempTerm.ITEM_CODE);

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

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictItemTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dictCollectionCode)) {
			MultiMatchQuery query = new MultiMatchQuery(dictCollectionCode);

			query.addFields(DictItemTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictItemTemp.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		Indexer<DictItemTemp> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemTemp.class);

		searchContext.addFullQueryEntryClassName(DictItemTemp.class.getName());
		searchContext.setEntryClassNames(new String[] { DictItemTemp.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		// params.put(DictCollectionTerm.COLLECTION_CODE, keywords);
		// params.put("expandoAttributes", keywords);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String dictCollectionId = (String) params.get(DictItemTempTerm.DICT_COLLECTION_ID);
		String dictItemParentId = String.valueOf(params.get(DictItemTempTerm.PARENT_ITEM_ID));
		String parentItemCode = (String) params.get(DictItemTempTerm.PARENT_ITEM_CODE);

		if (Validator.isNull(parentItemCode)) {
			parentItemCode = "0";
		}

		String dictItemCode = (String) params.get(DictItemTempTerm.ITEM_CODE);
		String keywords = (String) params.get("keywords");
		String groupId = String.valueOf(params.get("groupId"));
		String userId = (String) params.get("userId");
		String itemLv = (String) params.get("itemLv");
		String dictCollectionCode = (String) params.get(DictItemTempTerm.DICT_COLLECTION_CODE);

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

				query.addFields(DictItemTempTerm.ITEM_NAME, DictItemTempTerm.ITEM_NAME_EN, DictItemTempTerm.ITEM_CODE);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		// DUNG BO SUNG.
		if (Validator.isNotNull(dictCollectionId)) {
			MultiMatchQuery query = new MultiMatchQuery(dictCollectionId);

			query.addFields(DictItemTempTerm.DICT_COLLECTION_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dictItemParentId)) {
			MultiMatchQuery query = new MultiMatchQuery(dictItemParentId);

			query.addFields(DictItemTempTerm.PARENT_ITEM_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(parentItemCode)) {
			MultiMatchQuery query = new MultiMatchQuery(parentItemCode);

			query.addFields(DictItemTempTerm.PARENT_ITEM_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(itemLv)) {
			MultiMatchQuery query = new MultiMatchQuery(itemLv);

			query.addFields(DictItemTempTerm.LEVEL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(dictItemCode)) {
			MultiMatchQuery query = new MultiMatchQuery(dictItemCode);

			query.addFields(DictItemTempTerm.ITEM_CODE);

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

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(DictItemTempTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dictCollectionCode)) {
			MultiMatchQuery query = new MultiMatchQuery(dictCollectionCode);

			query.addFields(DictItemTempTerm.DICT_COLLECTION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, DictItemTemp.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	public List<DictItemTemp> findByF_dictCollectionId(long dictCollectionId) {

		return dictItemTempPersistence.findByF_dictCollectionId(dictCollectionId);

	}

	public List<DictItemTemp> findByF_dictCollectionId(long dictCollectionId, int stat, int end,
			OrderByComparator<DictItemTemp> comparator) {

		return dictItemTempPersistence.findByF_dictCollectionId(dictCollectionId, stat, end, comparator);

	}

	public List<DictItemTemp> findByF_dictCollectionId_parentItemId(long dictCollectionId, long parentItemId) {

		return dictItemTempPersistence.findByF_dictCollectionId_parentItemId(dictCollectionId, parentItemId);

	}

	public boolean initDictItemTemp(long groupId, long dictCollectionId) {

		boolean result = false;

		if (dictItemTempPersistence.findByF_dictItemByGroup(dictCollectionId, groupId).size() <= 0) {

			result = true;

		}

		return result;

	}

	public DictItemTemp fetchByF_dictItemCode(String itemCode, long dictCollectionId, long groupId) {

		return dictItemTempPersistence.fetchByIC_DCI(itemCode, dictCollectionId);
		// return
		// dictItemTempPersistence.fetchByF_dictItemCode_dictCollectionId(itemCode,
		// dictCollectionId, groupId);

	}

	public List<DictItemTemp> findByF_treeIndex(long dictCollectionId, long parentItemId, String treeIndex,
			OrderByComparator<DictItemTemp> comparator) {

		return dictItemTempPersistence.findByF_treeIndex(dictCollectionId, parentItemId,
				treeIndex + StringPool.PERIOD + StringPool.PERCENT, QueryUtil.ALL_POS, QueryUtil.ALL_POS, comparator);
	}

	@Override
	public List<DictItemTemp> findByOlderThanDate(Date date, long groupId, int start, int end) {
		OrderByComparator<DictItemTemp> comparator = OrderByComparatorFactoryUtil.create(DictItemTempImpl.TABLE_NAME, DictItemTempTerm.MODIFIED_DATE, true);
		return dictItemTempPersistence.findByF_dictItemNewerThan(date, groupId, start, end, comparator);
	}
	
	@Override
	public long countByOlderThanDate(Date date, long groupId) {
		return dictItemTempPersistence.countByF_dictItemNewerThan(date, groupId);
	}
	private static final Log _log = LogFactoryUtil.getLog(DictItemTempLocalServiceImpl.class);	
}