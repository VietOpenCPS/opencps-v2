
package org.opencps.datamgt.action.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.opencps.auth.api.exception.DataInUsedException;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.constants.DictCollectionTerm;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
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
import com.liferay.portal.kernel.json.JSONArray;
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
	public JSONObject getDictCollection(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictCollectionLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);
			//_log.info("data: "+hits);
			//_log.info("hits.toList(): "+hits.toList());

			result.put("data", hits.toList());

			long total = DictCollectionLocalServiceUtil.countLuceneSearchEngine(
				params, searchContext);

			result.put("total", total);

			/*
			if (DictCollectionLocalServiceUtil.initDictCollection(groupId)) {

				// create init dictcollection
//				Map<String, String> initDictCollection = new HashMap<String, String>();
//				for (Map.Entry<String, String> entry : map.entrySet()) {
//		            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//		        }
//				DataMGTConstants.DICTCOLLECTION_INIT;

				// create init dictcollection
				Map<String, String> initDictCollection = new HashMap<String, String>();
				initDictCollection.put(DataMGTConstants.ACTIVITY_TYPE, DataMGTConstants.VALUE_ACTIVITY_TYPE);
				initDictCollection.put(DataMGTConstants.DOCUMENT_TYPE, DataMGTConstants.VALUE_DOCUMENT_TYPE);

				for (String key : initDictCollection.keySet()) {
					try {
						DictCollectionLocalServiceUtil.addDictCollection(
							userId, groupId, key, initDictCollection.get(key),
							initDictCollection.get(key),
							initDictCollection.get(key), serviceContext);
					}
					catch (Exception e) {
						_log.error(e);
					}

				}

			}
			*/

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return result;
	}

	/**
	 * @author binhth
	 * @param dictCollectionCode
	 * @param groupId
	 * @return DictCollection
	 */
	public DictCollection getDictCollectionDetail(
		String dictCollectionCode, long groupId) {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				dictCollectionCode, groupId);

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
	public DictCollection addDictCollection(
		long userId, long groupId, String collectionCode, String collectionName,
		String collectionNameEN, String description,
		ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateCategoryException {

		DictCollection dictCollection = null;

		dictCollection = DictCollectionLocalServiceUtil.addDictCollection(
			userId, groupId, collectionCode, collectionName, collectionNameEN,
			description, serviceContext);

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
	public DictCollection updateDictCollection(
		long userId, long groupId, String code, String collectionCode,
		String collectionName, String collectionNameEN, String description,
		ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateCategoryException {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

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

		dictCollection = DictCollectionLocalServiceUtil.updateDictCollection(
			userId, dictCollection.getDictCollectionId(),
			dictCollection.getCollectionCode(),
			dictCollection.getCollectionName(),
			dictCollection.getCollectionNameEN(),
			dictCollection.getDescription(), dictCollection.getDataForm(),
			serviceContext);

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
	public boolean deleteDictCollection(
		String code, long groupId, ServiceContext serviceContext)
		throws NotFoundException, UnauthenticationException,
		UnauthorizationException {

		boolean flag = false;

		DictCollection dictColl =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		if (Validator.isNull(dictColl)) {

			flag = false;

		}
		else {

			DictCollectionLocalServiceUtil.deleteDictCollection(
				dictColl.getDictCollectionId(), serviceContext);

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
	public DictCollection addDataForm(
		long userId, long groupId, String code, String dataform,
		ServiceContext serviceContext)
		throws NoSuchUserException, NotFoundException,
		UnauthenticationException, UnauthorizationException,
		DuplicateCategoryException {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		if (Validator.isNotNull(dataform)) {

			dictCollection.setDataForm(dataform);

		}

		dictCollection = DictCollectionLocalServiceUtil.updateDictCollection(
			userId, dictCollection.getDictCollectionId(),
			dictCollection.getCollectionCode(),
			dictCollection.getCollectionName(),
			dictCollection.getCollectionNameEN(),
			dictCollection.getDescription(), dictCollection.getDataForm(),
			serviceContext);

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
	public JSONObject getDictgroups(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictGroupLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictGroupLocalServiceUtil.countLuceneSearchEngine(
				params, searchContext);

			result.put("total", total);

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
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
	public JSONObject getDictItemsGroup(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictItemGroupLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictItemGroupLocalServiceUtil.countLuceneSearchEngine(
				params, searchContext);

			result.put("total", total);

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
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
	public DictGroup addDictgroups(
		long userId, long groupId, String code, String groupCode,
		String groupName, String groupNameEN, String groupDescription,
		ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateCategoryException {

		DictGroup dictGroup = null;

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		dictGroup = DictGroupLocalServiceUtil.addDictGroup(
			userId, groupId, dictCollection.getDictCollectionId(), groupCode,
			groupName, groupNameEN, groupDescription, serviceContext);

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
	 * @throws NotFoundException
	 */
	public DictGroup updateDictgroups(
		long userId, long groupId, String code, String groupCodeRoot,
		String groupCode, String groupName, String groupNameEN,
		String groupDescription, ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateCategoryException,
		NotFoundException {

		DictGroup dictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(
			groupCodeRoot, groupId);

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

		dictGroup = DictGroupLocalServiceUtil.updateDictGroup(
			userId, dictGroup.getDictGroupId(), dictGroup.getDictCollectionId(),
			dictGroup.getGroupCode(), dictGroup.getGroupName(),
			dictGroup.getGroupNameEN(), dictGroup.getGroupDescription(),
			serviceContext);

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
	public boolean deleteDictgroups(
		String groupCode, long groupId, ServiceContext serviceContext)
		throws NotFoundException, UnauthenticationException,
		UnauthorizationException, DataInUsedException {

		boolean flag = false;

		DictGroup dictColl = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(
			groupCode, groupId);

		if (Validator.isNull(dictColl)) {

			flag = false;

		}
		else {

			DictGroupLocalServiceUtil.deleteDictGroup(
				dictColl.getDictGroupId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	/**
	 * @author binhth
	 * @param userId
	 * @param groupId
	 * @param groupCode
	 * @param serviceContext
	 * @return boolean
	 */
	public boolean deleteDictgroups(
		String collectionCode,
		String groupCode, long groupId, ServiceContext serviceContext)
		throws NotFoundException, UnauthenticationException,
		UnauthorizationException, DataInUsedException {

		boolean flag = false;

		DictCollection collection = getDictCollectionDetail(collectionCode, groupId);
		if (collection != null) {
			DictGroup dictColl = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());

			if (Validator.isNull(dictColl)) {

				flag = false;

			}
			else {

				DictGroupLocalServiceUtil.deleteDictGroup(
					dictColl.getDictGroupId(), serviceContext);

				flag = true;

			}			
		}
		else {
			flag = false;
		}
		return flag;
	}

	public DictItemGroup addDictgroupsDictItems(
		long userId, long groupId, String code, String groupCode,
		String itemCode, ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateCategoryException {

		DictItemGroup dictItemGroup = null;
		DictGroup dictGroup = null;
		DictItem dictItem = null;

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		if (dictCollection != null) {
			dictGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(
				groupCode, groupId, dictCollection.getDictCollectionId());
			dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(
				itemCode, dictCollection.getDictCollectionId(), groupId);
		}

		if (dictGroup != null && dictItem != null) {
			dictItemGroup = DictItemGroupLocalServiceUtil.addDictItemGroup(
					userId, groupId, dictGroup.getDictGroupId(),
					dictItem.getDictItemId(), groupCode, serviceContext);
		}

		return dictItemGroup;
	}

	public boolean deleteDictgroupsDictItems(
		long groupId, String code, String groupCode, String itemCode,
		ServiceContext serviceContext)
		throws NotFoundException, UnauthenticationException,
		UnauthorizationException {

		boolean flag = false;

		DictGroup dictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(
			groupCode, groupId);

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(
			itemCode, dictCollection.getDictCollectionId(), groupId);

		DictItemGroup dictItemGroup =
			DictItemGroupLocalServiceUtil.fetchByF_dictItemId_dictGroupId(
				groupId, dictGroup.getDictGroupId(), dictItem.getDictItemId());

		if (Validator.isNull(dictItemGroup)) {

			flag = false;

		}
		else {

			DictItemGroupLocalServiceUtil.deleteDictItemGroup(
				dictItemGroup.getDictItemGroupId(), serviceContext);

			flag = true;

		}
		return flag;
	}

	public JSONObject getDictgroupsDictItems(
		long userId, long companyId, long groupId, String code,
		String groupCode, boolean full, LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, ServiceContext serviceContext) {

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

				long dictCollectionId =
					DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
						code, groupId).getPrimaryKey();

				DictGroup dictGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(
					groupCode, groupId, dictCollectionId);

				hits = DictItemLocalServiceUtil.luceneSearchEngine(
					params, sorts, start, end, searchContext);

				List<Document> list = hits.toList();

				//_log.info(params);

				for (Document document : list) {

					DictItemGroup dictItemGroup =
						DictItemGroupLocalServiceUtil.fetchByF_dictItemId_dictGroupId(
							groupId, dictGroup.getDictGroupId(),
							Long.valueOf(document.get("entryClassPK")));

					String selected = Boolean.FALSE.toString();

					if (Validator.isNotNull(dictItemGroup)) {

						selected = Boolean.TRUE.toString();

					}

					//_log.info(document);

					document.addTextSortable(
						DictItemGroupTerm.SELECTED, selected);

				}

				result.put("data", list);

				long total = DictItemLocalServiceUtil.countLuceneSearchEngine(
					params, searchContext);

				result.put("total", total);

			}
			else {

				hits = DictItemGroupLocalServiceUtil.luceneSearchEngine(
					params, sorts, start, end, searchContext);

				/*
				 * for (Document doc : hits.toList()) { _log.info(doc); }
				 */

				result.put("data", hits.toList());

				long total =
					DictItemGroupLocalServiceUtil.countLuceneSearchEngine(
						params, searchContext);

				result.put("total", total);

			}

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return result;
	}

	public JSONObject getDictItems(
		long userId, long companyId, long groupId,
		LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
		ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		Hits hits = null;
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			hits = DictItemLocalServiceUtil.luceneSearchEngine(
				params, sorts, start, end, searchContext);

			result.put("data", hits.toList());

			long total = DictItemLocalServiceUtil.countLuceneSearchEngine(
				params, searchContext);

			result.put("total", total);

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		catch (SearchException e) {
			_log.debug(e);
			//_log.error(e);
		}

		return result;
	}

	public DictItem addDictItems(
		long userId, long groupId, String code, String parentItemCode,
		String itemCode, String itemName, String itemNameEN,
		String itemDescription, String sibling, int level, String metaData,
		ServiceContext serviceContext)
		throws NoSuchUserException, NoSuchDictItemException,
		UnauthenticationException, UnauthorizationException,
		DuplicateCategoryException, SystemException {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		long dictCollectionId = dictCollection.getDictCollectionId();

		if (Validator.isNull(itemNameEN)) {

			itemNameEN = itemName;

		}

		DictItem parentItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(
			parentItemCode, dictCollectionId, groupId);

		long parentItemId = 0;

		if (Validator.isNotNull(parentItem)) {

			parentItemId = parentItem.getDictItemId();

		}

		DictItem dictItem = DictItemLocalServiceUtil.addDictItem(
			userId, groupId, dictCollectionId, itemCode, itemName, itemNameEN,
			itemDescription, parentItemId, sibling, level, metaData,
			serviceContext);

		return dictItem;
	}

	public DictItem updateDictItemByItemCode(
		long userId, long groupId, ServiceContext serviceContext, String code,
		String itemCode, String itemCodeInput, String itemName,
		String itemNameEN, String itemDescription, String sibling,
		String parentItemCode)
		throws DuplicateCategoryException, UnauthenticationException,
		UnauthorizationException, NoSuchUserException, NotFoundException,
		PortalException {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		DictItem ett = DictItemLocalServiceUtil.fetchByF_dictItemCode(
			itemCode, dictCollection.getDictCollectionId(), groupId);

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

			DictItem ettParent = DictItemLocalServiceUtil.fetchByF_dictItemCode(
				parentItemCode, dictCollection.getDictCollectionId(), groupId);

			ett.setParentItemId(
				Validator.isNotNull(ettParent) ? ettParent.getDictItemId() : 0);

		}

		DictItem dictItem = DictItemLocalServiceUtil.updateDictItem(
			userId, ett.getDictItemId(), ett.getDictCollectionId(),
			ett.getItemCode(), ett.getItemName(), ett.getItemNameEN(),
			ett.getItemDescription(), ett.getParentItemId(), ett.getSibling(),
			ett.getLevel(), ett.getMetaData(), serviceContext);

		return dictItem;
	}

	public DictItem updateMetaDataByItemCode(
		long userId, long groupId, ServiceContext serviceContext, String code,
		String itemCode, String metaData)
		throws DuplicateCategoryException, UnauthenticationException,
		UnauthorizationException, NoSuchUserException, NotFoundException,
		PortalException {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		DictItem ett = DictItemLocalServiceUtil.fetchByF_dictItemCode(
			itemCode, dictCollection.getDictCollectionId(), groupId);

		if (Validator.isNotNull(metaData)) {

			ett.setMetaData(metaData);

		}

		DictItem dictItem = DictItemLocalServiceUtil.updateDictItem(
			userId, ett.getDictItemId(), ett.getDictCollectionId(),
			ett.getItemCode(), ett.getItemName(), ett.getItemNameEN(),
			ett.getItemDescription(), ett.getParentItemId(), ett.getSibling(),
			ett.getLevel(), ett.getMetaData(), serviceContext);

		return dictItem;
	}

	public Log _log = LogFactoryUtil.getLog(DictCollectionActions.class);

	@Override
	public DictItem getDictItemByItemCode(
		String code, String itemCode, long groupId,
		ServiceContext serviceContext) {

		DictCollection dictCollection =
			DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
				code, groupId);

		if (dictCollection != null) {
		DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(
			itemCode, dictCollection.getDictCollectionId(), groupId);
			return dictItem;			
		}
		else {
			return null;
		}
	}

	public String updateDictItemGroup(
		long userId, long groupId, long dictItemId, String groupCodes,
		String collectionCode, ServiceContext serviceContext)
		throws NoSuchUserException, UnauthenticationException,
		UnauthorizationException, DuplicateCategoryException {

		// Remove all dictItemGroup

		List<String> groupCodeList = new ArrayList<String>();

		List<DictItemGroup> dictItemGroups = new ArrayList<>();
		try {
			dictItemGroups = DictItemGroupLocalServiceUtil.findByF_dictItemId(
				groupId, dictItemId);
			for (DictItemGroup dictItemGroup : dictItemGroups) {
				DictItemGroupLocalServiceUtil.deleteDictItemGroup(
					dictItemGroup.getDictItemGroupId());
			}
		}
		catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			_log.info(
				"Can't not get DictItemGroups by groupId, dictItemId " +
					groupId + "|" + dictItemId);
		}
		if (Validator.isNotNull(groupCodes)) {
			String[] arrGroupCode = StringUtil.split(groupCodes);
			if (arrGroupCode != null && arrGroupCode.length > 0) {
				for (int i = 0; i < arrGroupCode.length; i++) {
					if (Validator.isNotNull(arrGroupCode[i])) {
						try {
							DictCollection dictCollection =
								DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
									collectionCode, groupId);
							DictGroup dictGroup =
								DictGroupLocalServiceUtil.getByGC_GI_DCI(
									arrGroupCode[i], groupId,
									dictCollection.getDictCollectionId());

							DictItemGroupLocalServiceUtil.addDictItemGroup(
								userId, groupId, dictGroup.getDictGroupId(),
								dictItemId, arrGroupCode[i], serviceContext);
							groupCodeList.add(arrGroupCode[i]);
						}
						catch (Exception e) {
							_log.debug(e);
							//_log.error(e);
						}
					}

				}
			}
		}

		return StringUtil.merge(groupCodeList);
	}

	@Override
	public JSONObject getDictCollectionsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<DictCollection> lstCollections = DictCollectionLocalServiceUtil.findOlderThanDate(date, groupId, start,
				end);

		JSONArray collectionArr = JSONFactoryUtil.createJSONArray();
		for (DictCollection dc : lstCollections) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put(DictCollectionTerm.COLLECTION_CODE, dc.getCollectionCode());
			obj.put(DictCollectionTerm.COLLECTION_NAME, dc.getCollectionName());
			obj.put(DictCollectionTerm.COLLECTION_NAME_EN, dc.getCollectionNameEN());
			obj.put(DictCollectionTerm.DESCRIPTION, dc.getDescription());
			obj.put(DictCollectionTerm.CREATE_DATE, dc.getCreateDate());
			obj.put(DictCollectionTerm.MODIFIED_DATE, dc.getModifiedDate());

			collectionArr.put(obj);
		}
		result.put("data", collectionArr);

		long total = DictCollectionLocalServiceUtil.countOlderThanDate(date, groupId);

		result.put("total", total);

		return result;	
	}

	@Override
	public JSONObject getDictItemsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<DictItem> lstDictItems = DictItemLocalServiceUtil.findByOlderThanDate(date, groupId, start, end);

		JSONArray collectionArr = JSONFactoryUtil.createJSONArray();
		for (DictItem dc : lstDictItems) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put(DictItemTerm.CREATE_DATE, dc.getCreateDate());
			obj.put(DictItemTerm.MODIFIED_DATE, dc.getModifiedDate());
			obj.put(DictItemTerm.ITEM_CODE, dc.getItemCode());
			obj.put(DictItemTerm.ITEM_NAME, dc.getItemName());
			obj.put(DictItemTerm.ITEM_NAME_EN, dc.getItemNameEN());
			obj.put(DictItemTerm.ITEM_DESCRIPTION, dc.getItemDescription());
			
			collectionArr.put(obj);
		}
		result.put("data", collectionArr);

		long total = DictItemLocalServiceUtil.countByOlderThanDate(date, groupId);

		result.put("total", total);

		return result;		
	}

	@Override
	public List<DictCollection> getListDictCollectionsOlderThanDate(long userId, long companyId, long groupId,
			Date date, int start, int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictCollectionLocalServiceUtil.findOlderThanDate(date, groupId, start, end);
	}

	@Override
	public List<DictItem> getListDictItemsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictItemLocalServiceUtil.findByOlderThanDate(date, groupId, start, end);
	}

	@Override
	public long countDictCollectionsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictCollectionLocalServiceUtil.countOlderThanDate(date, groupId);
	}

	@Override
	public long countDictItemsOlderThanDate(long userId, long companyId, long groupId, Date date, int start, int end,
			ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictItemLocalServiceUtil.countByOlderThanDate(date, groupId);
	}

	@Override
	public JSONObject getDictGroupsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<DictGroup> lstGroups = DictGroupLocalServiceUtil.findOlderThanDate(date, groupId, start, end);

		JSONArray groupArr = JSONFactoryUtil.createJSONArray();
		for (DictGroup dg : lstGroups) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			String collectionCode = null;
			
			try {
				DictCollection collection = DictCollectionLocalServiceUtil.fetchDictCollection(dg.getDictCollectionId());
				collectionCode = collection.getCollectionCode();
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}
			obj.put(DictGroupTerm.DICT_COLLECTION_CODE, collectionCode);
			obj.put(DictGroupTerm.GROUP_CODE, dg.getGroupCode());
			obj.put(DictGroupTerm.GROUP_NAME, dg.getGroupName());
			obj.put(DictGroupTerm.GROUP_NAME_EN, dg.getGroupNameEN());
			obj.put(DictGroupTerm.GROUP_DESCRIPTION, dg.getGroupDescription());
			
			groupArr.put(obj);
		}
		result.put("data", groupArr);

		long total = DictGroupLocalServiceUtil.countOlderThanDate(date, groupId);

		result.put("total", total);

		return result;	
	}

	@Override
	public List<DictGroup> getListDictGroupsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictGroupLocalServiceUtil.findOlderThanDate(date, groupId, start, end);
	}

	@Override
	public long countDictGroupsOlderThanDate(long userId, long companyId, long groupId, Date date, int start, int end,
			ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictGroupLocalServiceUtil.countOlderThanDate(date, groupId);
	}

	@Override
	public List<DictItemGroup> getListDictItemGroupsOlderThanDate(long userId, long companyId, long groupId, Date date,
			int start, int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictItemGroupLocalServiceUtil.findOlderThanDate(date, groupId, start, end);
	}

	@Override
	public long countDictItemGroupsOlderThanDate(long userId, long companyId, long groupId, Date date, int start,
			int end, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		return DictItemGroupLocalServiceUtil.countOlderThanDate(date, groupId);
	}

	@Override
	public boolean deleteDictgroupsAndSomethingUseIt(String collectionCode, String groupCode, long groupId,
			ServiceContext serviceContext)
			throws NotFoundException, UnauthenticationException, UnauthorizationException, DataInUsedException {
		// TODO Auto-generated method stub
		boolean flag = false;

		DictCollection collection = null;
		try {
			collection = getDictCollectionDetail(collectionCode, groupId);
		}
		catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
		if (collection != null) {
			DictGroup dictColl = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());

			if (Validator.isNull(dictColl)) {

				flag = false;

			}
			else {
				List<DictItemGroup> lstItemGroups = DictItemGroupLocalServiceUtil.findByDictGroupId(groupId, dictColl.getDictGroupId());
				if (lstItemGroups.size() > 0) {
					for (DictItemGroup itemGroup : lstItemGroups) {
						DictItemGroupLocalServiceUtil.deleteDictItemGroup(itemGroup);
					}
				}
				DictGroupLocalServiceUtil.deleteDictGroup(
					dictColl.getDictGroupId(), serviceContext);

				flag = true;

			}			
		}
		else {
			flag = false;
		}
		return flag;	
	}

	@Override
	public DictGroup getDictGroupDetail(String dictCollectionCode, String groupCode, long groupId) {
		// TODO Auto-generated method stub
		DictCollection collection = null;
		try {
			collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(dictCollectionCode, groupId);
		}
		catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}
		if (collection != null) {
			DictGroup dictGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());
			return dictGroup;
		}
		return null;
	}

	@Override
	public long updateDictCollectionDB(long userId, long groupId, String collectionCode, String collectionName,
			String collectionNameEN, String description, Integer status) throws NoSuchUserException {

		DictCollection dict = DictCollectionLocalServiceUtil.updateDictCollectionDB(userId, groupId, collectionCode, collectionName,
				collectionNameEN, description, status);
		if (dict != null) {
			return dict.getDictCollectionId();
		}
		return 0;
	}

	@Override
	public long getDictItemByItemCode(long dictCollectionId, String parent, long groupId) {

		DictItem dictItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(parent, dictCollectionId, groupId);
		if (dictItem != null) {
			return dictItem.getDictItemId();
		}
		return 0;
	}

	@Override
	public void updateDictItemDB(long userId, long groupId, long dictCollectionId, String itemCode, String itemName,
			String itemNameEN, String itemDescription, long dictItemParentId, Integer level, String sibling,
			String metadata) throws NoSuchUserException {

		DictItemLocalServiceUtil.updateDictItemDB(userId, groupId, dictCollectionId, itemCode, itemName, itemNameEN,
				itemDescription, dictItemParentId, level, sibling, metadata);
	}

	@Override
	public boolean deleteAllDictItem(long userId, long groupId, long dictCollectionId) {
		boolean flag = false;
		try {
			//_log.info("STARTTTTT");
			List<DictItem> itemList = DictItemLocalServiceUtil.findByF_dictCollectionId(dictCollectionId);
			//_log.info("itemList: "+itemList);
			//_log.info("STARTTTTT");
			if (itemList != null && itemList.size() > 0) {
				for (DictItem item : itemList) {
					DictItemLocalServiceUtil.deleteDictItem(item);
					flag = true;
				}
			} else {
				//_log.info("STARTTTTT");
				flag = true;
			}
		}catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return false;
		}

		return flag;
	}

	@Override
	public boolean deleteAllDictGroup(long userId, long groupId, long dictCollectionId) {
		boolean flag = false;
		List<DictGroup> groupList = DictGroupLocalServiceUtil.getDictGroupByDictCollection(groupId, dictCollectionId,
				-1, -1);
		if (groupList != null && groupList.size() > 0) {
			for (DictGroup group : groupList) {
				DictGroupLocalServiceUtil.deleteDictGroup(group);
				flag = true;
			}
		} else {
			flag = true;
		}

		return flag;
	}

	@Override
	public void updateDictGroupDB(long userId, long groupId, long dictCollectionId, String groupCode, String groupName,
			String groupNameEN, String groupDescription, ServiceContext serviceContext) throws NoSuchUserException {

		DictGroupLocalServiceUtil.updateDictGroupDB(userId, groupId, dictCollectionId, groupCode, groupName,
				groupNameEN, groupDescription, serviceContext);
	}

	//LGSP
	public JSONObject getDictCollectionLGSP(long userId, long companyId, long groupId,
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end, ServiceContext serviceContext) {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		try {

			Hits hits = DictCollectionLocalServiceUtil.luceneSearchEngine(params, sorts, start, end, searchContext);
			// _log.info("data: "+hits);
			// _log.info("hits.toList(): "+hits.toList());
			result.put("data", hits.toList());

			long total = DictCollectionLocalServiceUtil.countLuceneSearchEngine(params, searchContext);
			result.put("total", total);

		} catch (Exception e) {
			_log.debug(e);
			// _log.error(e);
		}

		return result;
	}

	public JSONObject getDictgroupsLGSP(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
			Sort[] sorts, int start, int end, ServiceContext serviceContext) {

			JSONObject result = JSONFactoryUtil.createJSONObject();
			Hits hits = null;
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(companyId);

			try {

				hits = DictGroupLocalServiceUtil.luceneSearchEngine(
					params, sorts, start, end, searchContext);

				result.put("data", hits.toList());

				long total = DictGroupLocalServiceUtil.countLuceneSearchEngine(
					params, searchContext);

				result.put("total", total);

			}
			catch (ParseException e) {
				_log.debug(e);
				//_log.error(e);
			}
			catch (SearchException e) {
				_log.debug(e);
				//_log.error(e);
			}

			return result;
		}

	public JSONObject getDictItemsLGSP(long userId, long companyId, long groupId, LinkedHashMap<String, Object> params,
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
			_log.debug(e);
			// _log.error(e);
		} catch (SearchException e) {
			_log.debug(e);
			// _log.error(e);
		}

		return result;
	}
}
