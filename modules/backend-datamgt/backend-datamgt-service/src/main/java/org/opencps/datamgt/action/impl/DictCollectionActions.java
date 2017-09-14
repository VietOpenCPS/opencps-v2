package org.opencps.datamgt.action.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.exception.NoSuchDictItemException;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;

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
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

public class DictCollectionActions implements DictcollectionInterface {

	/**
	 * @author binhth
	 * @param userId
	 * @param companyId
	 * @param groupId
	 * @param params
	 * @param sorts
	 * @param start
	 * @param end
	 * @param serviceContext
	 * @return JSONObject
	 */
	public JSONObject getDictCollection(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictCollectionLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictCollectionLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

			if (DictCollectionLocalServiceUtil.initDictCollection(groupId)) {

				// create init dictcollection
				Map<String, String> initDictCollection = DataMGTConstants.DICTCOLLECTION_INIT;

				for (String key : initDictCollection.keySet()) {

					try {

						DictCollectionLocalServiceUtil.addDictCollection(userId, groupId, key,
								initDictCollection.get(key), initDictCollection.get(key), initDictCollection.get(key),
								serviceContext);

					} catch (Exception e) {
						_log.error(e);
					}

				}

			}

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	/**
	 * @author binhth
	 * @param dictCollectionCode
	 * @param groupId
	 * @return DictCollection
	 */
	public DictCollection getDictCollectionDetail(String dictCollectionCode, long groupId) {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(dictCollectionCode,
				groupId);

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param collectionCode
	 * @param collectionName
	 * @param collectionNameEN
	 * @param description
	 * @param serviceContext
	 * @throws NoSuchUserException,
	 *             UnauthenticationException, UnauthorizationException,
	 *             DuplicateCategoryException
	 * @return DictCollection
	 */
	public DictCollection addDictCollection(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException {
		DictCollection dictCollection = null;

		dictCollection = DictCollectionLocalServiceUtil.addDictCollection(userId, groupId, collectionCode,
				collectionName, collectionNameEN, description, serviceContext);

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param code
	 *            ( collectionCode unique )
	 * @param collectionCode
	 *            changed
	 * @param collectionName
	 * @param collectionNameEN
	 * @param description
	 * @param serviceContext
	 * @throws NoSuchUserException,
	 *             NotFoundException, UnauthenticationException,
	 *             UnauthorizationException, DuplicateCategoryException
	 * @return DictCollection
	 */
	public DictCollection updateDictCollection(long userId, long groupId, String code, String collectionCode,
			String collectionName, String collectionNameEN, String description, ServiceContext serviceContext)
			throws NoSuchUserException, NotFoundException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code.toUpperCase(),
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

		dictCollection = DictCollectionLocalServiceUtil.updateDictCollection(userId,
				dictCollection.getDictCollectionId(), dictCollection.getCollectionCode().toUpperCase(),
				dictCollection.getCollectionName(), dictCollection.getCollectionNameEN(),
				dictCollection.getDescription(), dictCollection.getDataForm(), serviceContext);

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param code
	 *            ( collectionCode unique )
	 * @param groupId
	 * @param serviceContext
	 * @throws NotFoundException,
	 *             UnauthenticationException, UnauthorizationException
	 * @return boolean
	 */
	public boolean deleteDictCollection(String code, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		DictCollection dictColl = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code.toUpperCase(),
				groupId);

		if (Validator.isNull(dictColl)) {

			flag = false;

		} else {

			DictCollectionLocalServiceUtil.deleteDictCollection(dictColl.getDictCollectionId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param code
	 *            ( collectionCode unique )
	 * @param dataform
	 * @param serviceContext
	 * @throws NoSuchUserException,
	 *             NotFoundException, UnauthenticationException,
	 *             UnauthorizationException, DuplicateCategoryException
	 * @return DictCollection
	 */
	public DictCollection addDataForm(long userId, long groupId, String code, String dataform,
			ServiceContext serviceContext) throws NoSuchUserException, NotFoundException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code.toUpperCase(),
				groupId);

		if (Validator.isNotNull(dataform)) {

			dictCollection.setDataForm(dataform);

		}

		dictCollection = DictCollectionLocalServiceUtil.updateDictCollection(userId,
				dictCollection.getDictCollectionId(), dictCollection.getCollectionCode().toUpperCase(),
				dictCollection.getCollectionName(), dictCollection.getCollectionNameEN(),
				dictCollection.getDescription(), dictCollection.getDataForm(), serviceContext);

		return dictCollection;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param companyId
	 * @param groupId
	 * @param params
	 * @param sorts
	 * @param start
	 * @param end
	 * @param serviceContext
	 * @return JSONObject
	 */
	public JSONObject getDictgroups(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictGroupLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictGroupLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}
	
	/**
	 * @author binhth
	 * @param userId
	 * @param companyId
	 * @param groupId
	 * @param params
	 * @param sorts
	 * @param start
	 * @param end
	 * @param serviceContext
	 * @return JSONObject
	 */
	public JSONObject getDictItemsGroup(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictItemGroupLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictItemGroupLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}
	
	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param code
	 * @param groupCode
	 * @param groupName
	 * @param groupNameEN
	 * @param groupDescription
	 * @param serviceContext
	 * @return DictGroup
	 */
	public DictGroup addDictgroups(long userId, long groupId, String code, String groupCode, String groupName,
			String groupNameEN, String groupDescription, ServiceContext serviceContext) throws NoSuchUserException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException {
		DictGroup dictGroup = null;

		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code.toUpperCase(),
				groupId);

		dictGroup = DictGroupLocalServiceUtil.addDictGroup(userId, groupId, dictCollection.getDictCollectionId(),
				groupCode, groupName, groupNameEN, groupDescription, serviceContext);

		return dictGroup;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param code
	 * @param groupCodeRoot
	 *            ( groupCode unique )
	 * @param groupCode
	 * @param groupName
	 * @param groupNameEN
	 * @param groupDescription
	 * @param serviceContext
	 * @return DictGroup
	 */
	public DictGroup updateDictgroups(long userId, long groupId, String code, String groupCodeRoot, String groupCode,
			String groupName, String groupNameEN, String groupDescription, ServiceContext serviceContext)
			throws NoSuchUserException, UnauthenticationException, UnauthorizationException,
			DuplicateCategoryException {
		DictGroup dictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCodeRoot.toUpperCase(), groupId);

		if (Validator.isNotNull(groupCode)) {

			dictGroup.setGroupCode(groupCode);

		}

		if (Validator.isNotNull(groupName)) {

			dictGroup.setGroupName(groupNameEN);

		}

		if (Validator.isNotNull(groupNameEN)) {

			dictGroup.setGroupNameEN(groupNameEN);

		}

		if (Validator.isNotNull(groupDescription)) {

			dictGroup.setGroupDescription(groupDescription);

		}

		dictGroup = DictGroupLocalServiceUtil.addDictGroup(userId, groupId, dictGroup.getDictCollectionId(),
				dictGroup.getGroupCode(), dictGroup.getGroupName(), dictGroup.getGroupNameEN(),
				dictGroup.getGroupDescription(), serviceContext);

		return dictGroup;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param groupCode
	 * @param serviceContext
	 * @return boolean
	 */
	public boolean deleteDictgroups(String groupCode, long groupId, ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		DictGroup dictColl = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

		if (Validator.isNull(dictColl)) {

			flag = false;

		} else {

			DictGroupLocalServiceUtil.deleteDictGroup(dictColl.getDictGroupId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	public DictItemGroup addDictgroupsDictItems(long userId, long groupId, String code, String groupCode,
			String itemCode, ServiceContext serviceContext) throws NoSuchUserException, UnauthenticationException,
			UnauthorizationException, DuplicateCategoryException {
		DictItemGroup dictItemGroup = null;

		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
		
		DictGroup dictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

		DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dictCollection.getDictCollectionId(), groupId);

		dictItemGroup = DictItemGroupLocalServiceUtil.addDictItemGroup(userId, groupId, dictGroup.getDictGroupId(),
				dictItem.getDictItemId(), serviceContext);

		return dictItemGroup;
	}

	public boolean deleteDictgroupsDictItems(long groupId, String code, String groupCode, String itemCode,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException {
		boolean flag = false;

		DictGroup dictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
		
		DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dictCollection.getDictCollectionId(), groupId);

		DictItemGroup dictItemGroup = DictItemGroupLocalServiceUtil.fetchByF_dictItemId_dictGroupId(groupId,
				dictItem.getDictItemId(), dictGroup.getDictGroupId());

		if (Validator.isNull(dictItemGroup)) {

			flag = false;

		} else {

			DictItemGroupLocalServiceUtil.deleteDictItemGroup(dictItemGroup.getDictItemGroupId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	public JSONObject getDictgroupsDictItems(long userId, long companyId, long groupId, String code, String groupCode,
			boolean full, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			if (full) {

				DictGroup dictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

				hits = DictItemLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

				List<Document> list = hits.toList();

				for (Document document : list) {

					DictItemGroup dictItemGroup = DictItemGroupLocalServiceUtil.fetchByF_dictItemId_dictGroupId(groupId,
							dictGroup.getDictGroupId(), Long.valueOf(document.get("entryClassPK")));

					String selected = Boolean.FALSE.toString();

					if (Validator.isNotNull(dictItemGroup)) {

						selected = Boolean.TRUE.toString();

					}

					document.addTextSortable(DictItemGroupTerm.SELECTED, selected);

				}

				result.put("data", list);

				long total = DictItemLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

				result.put("total", total);

			} else {

				hits = DictItemGroupLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

				result.put("data", hits.toList());

				long total = DictItemGroupLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

				result.put("total", total);

			}

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	public JSONObject getDictItems(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictItemLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictItemLocalServiceUtil.countLuceneSearchEngine(params, searchContext);

			result.put("total", total);

		} catch (ParseException e) {
			_log.error(e);
		} catch (SearchException e) {
			_log.error(e);
		}

		return result;
	}

	public DictItem addDictItems(long userId, long groupId, String code, String parentItemCode, String itemCode,
			String itemName, String itemNameEN, String itemDescription, String sibling, int level, String metaData,
			ServiceContext serviceContext) throws NoSuchUserException, NoSuchDictItemException,
			UnauthenticationException, UnauthorizationException, DuplicateCategoryException, SystemException {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);

		long dictCollectionId = dictCollection.getDictCollectionId();

		if (Validator.isNull(itemNameEN)) {

			itemNameEN = itemName;

		}

		DictItem parentItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(parentItemCode, dictCollectionId, groupId);
		
		long parentItemId = 0;

		if (Validator.isNotNull(parentItem)) {

			parentItemId = parentItem.getDictItemId();

		}

		DictItem dictItem = DictItemLocalServiceUtil.addDictItem(userId, groupId, dictCollectionId, itemCode, itemName,
				itemNameEN, itemDescription, parentItemId, sibling, level, metaData, serviceContext);

		return dictItem;
	}

	public DictItem updateDictItemByItemCode(long userId, long groupId, ServiceContext serviceContext, String code, String itemCode,
			String itemName, String itemNameEN, String itemDescription, String sibling) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
		
		DictItem ett = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dictCollection.getDictCollectionId(), groupId);
		
		if (Validator.isNotNull(itemCode)) {

			ett.setItemCode(itemCode);

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

		DictItem dictItem = DictItemLocalServiceUtil.updateDictItem(userId, ett.getDictItemId(),
				ett.getDictCollectionId(), ett.getItemCode(), ett.getItemName(), ett.getItemNameEN(),
				ett.getItemDescription(), ett.getParentItemId(), ett.getSibling(), ett.getLevel(),
				ett.getMetaData(), serviceContext);
		
		return dictItem;
	}

	public DictItem updateMetaDataByItemCode(long userId, long groupId, ServiceContext serviceContext, String code, String itemCode,
			String metaData) throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, PortalException {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
		
		DictItem ett = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dictCollection.getDictCollectionId(), groupId);
		
		if (Validator.isNotNull(metaData)) {

			ett.setMetaData(metaData);

		}

		DictItem dictItem = DictItemLocalServiceUtil.updateDictItem(userId, ett.getDictItemId(),
				ett.getDictCollectionId(), ett.getItemCode(), ett.getItemName(), ett.getItemNameEN(),
				ett.getItemDescription(), ett.getParentItemId(), ett.getSibling(), ett.getLevel(),
				ett.getMetaData(), serviceContext);
		
		return dictItem;
	}
	
	public Log _log = LogFactoryUtil.getLog(DictCollectionActions.class);

	@Override
	public DictItem getDictItemByItemCode(String code, String itemCode, long groupId, ServiceContext serviceContext) {
		DictCollection dictCollection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
		
		DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dictCollection.getDictCollectionId(), groupId);
		
		return dictItem;
	}

}
