
package org.opencps.synchronization.action.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.synchronization.action.DictCollectionTempInterface;
import org.opencps.synchronization.exception.NoSuchDictItemTempException;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.DictGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemTempLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DictCollectionActions implements DictCollectionTempInterface {

	@Override
	public JSONObject getDictCollectionTemp(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictCollectionTempLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictCollectionTempLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

			if (DictCollectionTempLocalServiceUtil.initDictCollectionTemp(groupId)) {

				// create init dictcollection
//				Map<String, String> initDictCollection = DataMGTTempConstants.DICTCOLLECTION_INIT;
//
//				for (String key : initDictCollection.keySet()) {
//
//					try {
//
//						DictCollectionTempLocalServiceUtil.addDictCollectionTemp(userId, groupId, key,
//								initDictCollection.get(key), initDictCollection.get(key), initDictCollection.get(key),
//								DataMGTTempConstants.DATA_STATUS_DEACTIVE,
//								DataMGTTempConstants.DATA_MUST_NOT_SYNCHRONIZED, serviceContext);
//
//					} catch (Exception e) {
//						_log.error(e);
//					}
//
//				}

			}

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public DictCollectionTemp getDictCollectionTempDetail(String dictCollectionCode, long groupId) {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil
				.fetchByF_dictCollectionCode(dictCollectionCode, groupId);

		return dictCollection;
	}

	@Override
	public DictCollectionTemp addDictCollectionTemp(long userId, long groupId, String collectionCode,
			String collectionName, String collectionNameEN, String description, int status, int mustSync,
			ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = null;

		dictCollection = DictCollectionTempLocalServiceUtil.addDictCollectionTemp(userId, groupId, collectionCode,
				collectionName, collectionNameEN, description, status, mustSync, serviceContext);

		return dictCollection;
	}

	@Override
	public DictCollectionTemp updateDictCollectionTemp(long userId, long groupId, String code, String collectionCode,
			String collectionName, String collectionNameEN, String description, int status, int mustSync,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

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
		}

		if (Validator.isNotNull(mustSync)) {
			dictCollection.setMustSync(mustSync);
		}

		dictCollection = DictCollectionTempLocalServiceUtil.updateDictCollectionTemp(userId,
				dictCollection.getDictCollectionId(), dictCollection.getCollectionCode(),
				dictCollection.getCollectionName(), dictCollection.getCollectionNameEN(),
				dictCollection.getDescription(), status, mustSync, dictCollection.getDataForm(), serviceContext);

		return dictCollection;
	}

	@Override
	public boolean deleteDictCollectionTemp(String code, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		// TODO Auto-generated method stub
		boolean flag = false;

		DictCollectionTemp dictColl = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);

		if (Validator.isNull(dictColl)) {

			flag = false;

		} else {

			DictCollectionTempLocalServiceUtil.deleteDictCollectionTemp(dictColl.getDictCollectionId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	@Override
	public DictCollectionTemp addDataForm(long userId, long groupId, String code, String dataform,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		if (Validator.isNotNull(dataform)) {

			dictCollection.setDataForm(dataform);

		}

		dictCollection = DictCollectionTempLocalServiceUtil.updateDictCollectionTemp(userId,
				dictCollection.getDictCollectionId(), dictCollection.getCollectionCode(),
				dictCollection.getCollectionName(), dictCollection.getCollectionNameEN(),
				dictCollection.getDescription(), dictCollection.getStatus(), dictCollection.getMustSync(),
				dictCollection.getDataForm(), serviceContext);

		return dictCollection;
	}

	@Override
	public JSONObject getDictGroupsTemp(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictGroupTempLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictGroupTempLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDictItemsGroupTemp(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictItemGroupTempLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictItemGroupTempLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public DictGroupTemp addDictGroupsTemp(long userId, long groupId, String code, String groupCode, String groupName,
			String groupNameEN, String groupDescription, int status, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException {
		// TODO Auto-generated method stub
		DictGroupTemp dictGroup = null;

		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		dictGroup = DictGroupTempLocalServiceUtil.addDictGroupTemp(userId, groupId,
				dictCollection.getDictCollectionId(), groupCode, groupName, groupNameEN, groupDescription, status,
				serviceContext);

		return dictGroup;
	}

	@Override
	public DictGroupTemp updateDictGroupsTemp(long userId, long groupId, String code, String groupCodeRoot,
			String groupCode, String groupName, String groupNameEN, String groupDescription, int status,
			ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException, NotFoundException {
		// TODO Auto-generated method stub
		DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.fetchByF_DictGroupCode(groupCodeRoot, groupId);

		if (Validator.isNotNull(groupCode)) {

			dictGroup.setGroupCode(groupCode);

		}

		if (Validator.isNotNull(groupName)) {

			dictGroup.setGroupName(groupName);

		}

		if (Validator.isNotNull(groupNameEN)) {

			dictGroup.setGroupNameEN(groupNameEN);

		}

		if (Validator.isNotNull(groupDescription)) {

			dictGroup.setGroupDescription(groupDescription);

		}

		if (Validator.isNotNull(status)) {
			dictGroup.setStatus(status);
		}

		dictGroup = DictGroupTempLocalServiceUtil.updateDictGroupTemp(userId, dictGroup.getDictGroupId(),
				dictGroup.getDictCollectionId(), dictGroup.getGroupCode(), dictGroup.getGroupName(),
				dictGroup.getGroupNameEN(), dictGroup.getGroupDescription(), dictGroup.getStatus(), serviceContext);

		return dictGroup;
	}

	@Override
	public boolean deleteDictGroupsTemp(String groupCode, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		// TODO Auto-generated method stub
		boolean flag = false;

		DictGroupTemp dictColl = DictGroupTempLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

		if (Validator.isNull(dictColl)) {

			flag = false;

		} else {

			DictGroupTempLocalServiceUtil.deleteDictGroupTemp(dictColl.getDictGroupId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	@Override
	public boolean deleteDictGroupsTemp(String collectionCode, String groupCode, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		// TODO Auto-generated method stub
		boolean flag = false;

		DictCollectionTemp collection = null;
		
		try {
			collection = getDictCollectionTempDetail(collectionCode, groupId);
		}
		catch (Exception e) {
			_log.error(e);
		}
		
		if (collection != null) {
			DictGroupTemp dictColl = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());

			if (Validator.isNull(dictColl)) {

				flag = false;

			} else {

				DictGroupTempLocalServiceUtil.deleteDictGroupTemp(dictColl.getDictGroupId(), serviceContext);

				flag = true;

			}			
		}
		else {
			flag = false;
		}
		return flag;
	}

	@Override
	public DictItemGroupTemp addDictGroupsDictItemsTemp(long userId, long groupId, String code, String groupCode,
			String itemCode, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {
		// TODO Auto-generated method stub
		DictItemGroupTemp dictItemGroup = null;
		DictGroupTemp dictGroup = null;
		DictItemTemp dictItem = null;

		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		_log.info("Dict collection: " + dictCollection.getCollectionCode());

		if (dictCollection != null) {
			dictGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId,
					dictCollection.getDictCollectionId());
			_log.info("Dict group: " + dictGroup.getGroupCode());
			dictItem = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(itemCode,
					dictCollection.getDictCollectionId(), groupId);
			_log.info("Dict item: " + dictItem.getItemCode());
		}

		dictItemGroup = DictItemGroupTempLocalServiceUtil.addDictItemGroupTemp(userId, groupId,
				dictGroup.getDictGroupId(), dictItem.getDictItemId(), groupCode, serviceContext);

		_log.info("Dict item group: " + dictItemGroup.getDictItemGroupId());
		return dictItemGroup;
	}

	@Override
	public boolean deleteDictGroupsDictItemsTemp(long groupId, String code, String groupCode, String itemCode,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		// TODO Auto-generated method stub
		boolean flag = false;

		DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		DictItemTemp dictItem = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(itemCode,
				dictCollection.getDictCollectionId(), groupId);

		DictItemGroupTemp dictItemGroup = DictItemGroupTempLocalServiceUtil.fetchByF_dictItemId_dictGroupId(groupId,
				dictGroup.getDictGroupId(), dictItem.getDictItemId());

		if (Validator.isNull(dictItemGroup)) {

			flag = false;

		} else {

			DictItemGroupTempLocalServiceUtil.deleteDictItemGroupTemp(dictItemGroup.getDictItemGroupId(),
					serviceContext);

			flag = true;

		}
		return flag;
	}

	@Override
	public JSONObject getDictGroupsDictItemsTemp(long userId, long companyId, long groupId, String code,
			String groupCode, boolean full, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			if (full) {
				// Khoavd tam thoi update
				// DictGroup dictGroup =
				// DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode,
				// groupId);

				long dictCollectionId = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId)
						.getPrimaryKey();

				DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId,
						dictCollectionId);

				hits = DictItemTempLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

				List<Document> list = hits.toList();

				_log.info(params);

				for (Document document : list) {

					DictItemGroupTemp dictItemGroup = DictItemGroupTempLocalServiceUtil.fetchByF_dictItemId_dictGroupId(
							groupId, dictGroup.getDictGroupId(), Long.valueOf(document.get("entryClassPK")));

					String selected = Boolean.FALSE.toString();

					if (Validator.isNotNull(dictItemGroup)) {

						selected = Boolean.TRUE.toString();

					}

					_log.info(document);

					document.addTextSortable(DictItemGroupTerm.SELECTED, selected);

				}

				result.put("data", list);

				long total = DictItemTempLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

				result.put("total", total);

			} else {

				hits = DictItemGroupTempLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

				/*
				 * for (Document doc : hits.toList()) { _log.info(doc); }
				 */

				result.put("data", hits.toList());

				long total = DictItemGroupTempLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

				result.put("total", total);

			}

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public JSONObject getDictItemsTemp(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictItemTempLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictItemTempLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	@Override
	public DictItemTemp addDictItemsTemp(long userId, long groupId, String code, String parentItemCode, String itemCode,
			String itemName, String itemNameEN, String itemDescription, String sibling, int level, String metaData,
			int status, ServiceContext serviceContext) throws NoSuchUserException, NoSuchDictItemTempException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException, SystemException {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		long dictCollectionId = dictCollection.getDictCollectionId();

		if (Validator.isNull(itemNameEN)) {

			itemNameEN = itemName;

		}

		DictItemTemp parentItem = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(parentItemCode, dictCollectionId,
				groupId);

		long parentItemId = 0;

		if (Validator.isNotNull(parentItem)) {

			parentItemId = parentItem.getDictItemId();

		}

		DictItemTemp dictItem = DictItemTempLocalServiceUtil.addDictItemTemp(userId, groupId, dictCollectionId,
				itemCode, itemName, itemNameEN, itemDescription, parentItemId, sibling, level, metaData, status,
				serviceContext);

		return dictItem;
	}

	@Override
	public DictItemTemp updateDictItemTempByItemCode(long userId, long groupId, ServiceContext serviceContext,
			String code, String itemCode, String itemCodeInput, String itemName, String itemNameEN,
			String itemDescription, String sibling, String parentItemCode, int status)
			throws DuplicateCategoryException, UnauthenticationException, UnauthorizationException, NoSuchUserException,
			NotFoundException, PortalException {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		DictItemTemp ett = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(itemCode,
				dictCollection.getDictCollectionId(), groupId);

		if (Validator.isNotNull(itemCodeInput)) {

			ett.setItemCode(itemCodeInput);

		}

		if (Validator.isNotNull(itemName)) {

			ett.setItemName(itemName);

		}

		if (Validator.isNotNull(itemNameEN)) {

			ett.setItemNameEN(itemNameEN);

		}

		if (Validator.isNotNull(itemDescription)) {

			ett.setItemDescription(itemDescription);

		}

		if (Validator.isNotNull(sibling)) {

			ett.setSibling(sibling);

		}

		if (Validator.isNotNull(parentItemCode)) {

			DictItemTemp ettParent = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(parentItemCode,
					dictCollection.getDictCollectionId(), groupId);

			ett.setParentItemId(Validator.isNotNull(ettParent) ? ettParent.getDictItemId() : 0);

		}

		if (Validator.isNotNull(status)) {
			ett.setStatus(status);
		}
		DictItemTemp dictItem = DictItemTempLocalServiceUtil.updateDictItemTemp(userId, ett.getDictItemId(),
				ett.getDictCollectionId(), ett.getItemCode(), ett.getItemName(), ett.getItemNameEN(),
				ett.getItemDescription(), ett.getParentItemId(), ett.getSibling(), ett.getLevel(), ett.getMetaData(),
				ett.getStatus(), serviceContext);

		return dictItem;
	}

	@Override
	public DictItemTemp updateMetaDataByItemCode(long userId, long groupId, ServiceContext serviceContext, String code,
			String itemCode, String metaData) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		DictItemTemp ett = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(itemCode,
				dictCollection.getDictCollectionId(), groupId);

		if (Validator.isNotNull(metaData)) {

			ett.setMetaData(metaData);

		}

		DictItemTemp dictItem = DictItemTempLocalServiceUtil.updateDictItemTemp(userId, ett.getDictItemId(),
				ett.getDictCollectionId(), ett.getItemCode(), ett.getItemName(), ett.getItemNameEN(),
				ett.getItemDescription(), ett.getParentItemId(), ett.getSibling(), ett.getLevel(), ett.getMetaData(),
				ett.getStatus(), serviceContext);

		return dictItem;
	}

	@Override
	public DictItemTemp getDictItemTempByItemCode(String code, String itemCode, long groupId,
			ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(code,
				groupId);

		DictItemTemp dictItem = DictItemTempLocalServiceUtil.fetchByF_dictItemCode(itemCode,
				dictCollection.getDictCollectionId(), groupId);

		return dictItem;
	}

	public String updateDictItemGroupTemp(long userId, long groupId, long dictItemId, String groupCodes,
			String collectionCode, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {

		// Remove all dictItemGroup

		List<String> groupCodeList = new ArrayList<String>();

		List<DictItemGroupTemp> dictItemGroups = new ArrayList<>();
		try {
			dictItemGroups = DictItemGroupTempLocalServiceUtil.findByF_dictItemId(groupId, dictItemId);
			for (DictItemGroupTemp dictItemGroup : dictItemGroups) {
				DictItemGroupTempLocalServiceUtil.deleteDictItemGroupTemp(dictItemGroup.getDictItemGroupId());
			}
		} catch (Exception e) {
			_log.warn("Can't not get DictItemGroupsTemp by groupId, dictItemId " + groupId + "|" + dictItemId+ "|" + e);
		}
		if (Validator.isNotNull(groupCodes)) {
			String[] arrGroupCode = StringUtil.split(groupCodes);
			if (arrGroupCode != null && arrGroupCode.length > 0) {
				for (int i = 0; i < arrGroupCode.length; i++) {
					if (Validator.isNotNull(arrGroupCode[i])) {
						try {
							DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil
									.fetchByF_dictCollectionCode(collectionCode, groupId);
							DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(arrGroupCode[i],
									groupId, dictCollection.getDictCollectionId());

							DictItemGroupTempLocalServiceUtil.addDictItemGroupTemp(userId, groupId,
									dictGroup.getDictGroupId(), dictItemId, arrGroupCode[i], serviceContext);
							groupCodeList.add(arrGroupCode[i]);
						} catch (Exception e) {
							_log.error(e);
						}
					}

				}
			}
		}

		return StringUtil.merge(groupCodeList);
	}

	@Override
	public DictGroupTemp getDictGroupTempDetail(String dictCollectionCode, String groupCode, long groupId) {
		// TODO Auto-generated method stub
		DictCollectionTemp collection = null;
		try {
			collection = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(dictCollectionCode, groupId);
		}
		catch (Exception e) {
			_log.error(e);
		}
		if (collection != null) {
			DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());
			return dictGroup;
		}
		return null;
	}

	public Log _log = LogFactoryUtil.getLog(DictCollectionActions.class);	
}
