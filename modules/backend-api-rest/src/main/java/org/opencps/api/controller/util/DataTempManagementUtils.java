package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.api.datatempmgt.model.DictCollectionTempModel;
import org.opencps.api.datatempmgt.model.DictGroupItemTempModel;
import org.opencps.api.datatempmgt.model.DictGroupTempModel;
import org.opencps.api.datatempmgt.model.DictItemTempDetailModel;
import org.opencps.api.datatempmgt.model.DictItemTempModel;
import org.opencps.api.datatempmgt.model.ParentItemModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.synchronization.action.DictCollectionTempInterface;
import org.opencps.synchronization.constants.DictCollectionTempTerm;
import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.constants.DictItemGroupTempTerm;
import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.DictGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemTempLocalServiceUtil;

public class DataTempManagementUtils {

	public static List<DictCollectionTempModel> mapperDictCollectionTempModelList(List<Document> listDocument) {

		List<DictCollectionTempModel> results = new ArrayList<>();

		try {

			DictCollectionTempModel ett = null;

			for (Document document : listDocument) {
				ett = new DictCollectionTempModel();

				ett.setCompanyId(Long.valueOf(document.get(DictCollectionTempTerm.COMPANY_ID)));
				ett.setGroupId(Long.valueOf(document.get(DictCollectionTempTerm.GROUP_ID)));
				ett.setUserId(Long.valueOf(document.get(DictCollectionTempTerm.USER_ID)));
				
				ett.setDictCollectionId(Long.valueOf(document.get(DictCollectionTempTerm.DICT_COLLECTION_ID)));
				ett.setCollectionCode(document.get(DictCollectionTempTerm.COLLECTION_CODE));
				ett.setCollectionName(document.get(DictCollectionTempTerm.COLLECTION_NAME));
				ett.setCollectionNameEN(document.get(DictCollectionTempTerm.COLLECTION_NAME_EN));
				ett.setDescription(document.get(DictCollectionTempTerm.DESCRIPTION));
				ett.setStatus(GetterUtil.getInteger(document.get(DictCollectionTempTerm.STATUS)));
				ett.setMustSync(GetterUtil.getInteger(document.get(DictCollectionTempTerm.MUST_SYNC)));
				
				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static DictCollectionTempModel mapperDictCollectionTempModel(DictCollectionTemp dictCollection) {

		DictCollectionTempModel ett = new DictCollectionTempModel();

		try {

			if (ett != null) {
				ett.setDictCollectionId(dictCollection.getDictCollectionId());
				ett.setCompanyId(dictCollection.getCompanyId());
				ett.setGroupId(dictCollection.getGroupId());
				ett.setUserId(dictCollection.getUserId());
				
				ett.setCollectionCode(dictCollection.getCollectionCode());
				ett.setCollectionName(dictCollection.getCollectionName());
				ett.setCollectionNameEN(dictCollection.getCollectionNameEN());
				ett.setDescription(dictCollection.getDescription());
				ett.setCreateDate(Validator.isNotNull(dictCollection.getCreateDate())
						? APIDateTimeUtils.convertDateToString(dictCollection.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(dictCollection.getModifiedDate()) ? APIDateTimeUtils.convertDateToString(
								dictCollection.getModifiedDate(), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);				
				ett.setStatus(dictCollection.getStatus());
				ett.setMustSync(dictCollection.getMustSync());
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static List<DictGroupTempModel> mapperGroupsList(List<Document> listDocument) {

		List<DictGroupTempModel> results = new ArrayList<>();

		try {

			DictGroupTempModel ett = null;

			for (Document document : listDocument) {
				ett = new DictGroupTempModel();

				ett.setDictGroupId(Long.valueOf(document.get(DictGroupTempTerm.DICT_GROUPID)));
				ett.setGroupCode(document.get(DictGroupTempTerm.GROUP_CODE));
				ett.setGroupName(document.get(DictGroupTempTerm.GROUP_NAME));
				ett.setGroupNameEN(document.get(DictGroupTempTerm.GROUP_NAME_EN));
				ett.setGroupDescription(document.get(DictGroupTempTerm.GROUP_DESCRIPTION));
				ett.setStatus(GetterUtil.getInteger(document.get(DictGroupTempTerm.STATUS)));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static DictGroupTempModel mapperGroups(DictGroupTemp dictGroup) {

		DictGroupTempModel ett = new DictGroupTempModel();

		try {

			ett.setDictGroupId(dictGroup.getDictGroupId());
			ett.setGroupCode(dictGroup.getGroupCode());
			ett.setGroupName(dictGroup.getGroupName());
			ett.setGroupNameEN(dictGroup.getGroupNameEN());
			ett.setGroupDescription(dictGroup.getGroupDescription());
			ett.setStatus(dictGroup.getStatus());
			
		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}	
	
	public static List<DictGroupItemTempModel> mapperDictGroupItemTempModelList(List<Document> listDocument) {

		List<DictGroupItemTempModel> results = new ArrayList<>();

		try {

			DictGroupItemTempModel ett = null;

			for (Document document : listDocument) {
				ett = new DictGroupItemTempModel();

				ett.setDictItemId(Long.valueOf(document.get(DictItemGroupTempTerm.DICT_ITEM_ID)));
				ett.setItemCode(document.get(DictItemGroupTempTerm.ITEM_CODE));
				ett.setItemName(document.get(DictItemGroupTempTerm.ITEM_NAME));
				ett.setItemNameEN(document.get(DictItemGroupTempTerm.ITEM_NAME_EN));
				ett.setItemDescription(document.get(DictItemGroupTempTerm.ITEM_DESCRIPTION));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}
	
	public static DictGroupItemTempModel mapperDictGroupItemTempModel(DictItemGroupTemp dictItemGroup) {

		DictGroupItemTempModel ett = new DictGroupItemTempModel();

		try {

			long dictItemId = dictItemGroup.getDictItemId();

			DictItemTemp dictItem = DictItemTempLocalServiceUtil.fetchDictItemTemp(dictItemId);

			ett.setDictItemId(dictItemId);
			ett.setItemCode(dictItem.getItemCode());
			ett.setItemName(dictItem.getItemName());
			ett.setItemNameEN(dictItem.getItemNameEN());
			ett.setItemDescription(String.valueOf(dictItem.getItemDescription()));
			
		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static List<DictItemTempModel> mapperDictItemTempModelList(List<Document> listDocument) {

		List<DictItemTempModel> results = new ArrayList<>();

		try {

			DictItemTempModel ett = null;

			for (Document document : listDocument) {
				ett = new DictItemTempModel();

				ett.setDictItemId(Long.valueOf(document.get("entryClassPK")));
				ett.setItemCode(document.get(DictItemTempTerm.ITEM_CODE));
				ett.setItemName(document.get(DictItemTempTerm.ITEM_NAME));
				ett.setItemDescription(document.get(DictItemTempTerm.ITEM_DESCRIPTION));
				ett.setLevel(Integer.valueOf(document.get(DictItemTempTerm.LEVEL)));
				ett.setSibling(Integer.valueOf(document.get(DictItemTempTerm.SIBLING)));
				ett.setTreeIndex(document.get(DictItemTempTerm.TREE_INDEX));

				ett.setCreateDate(Validator.isNotNull(document.get(DictItemTempTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(DictItemTempTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.get("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				DictItemTemp parentItem = DictItemTempLocalServiceUtil
						.fetchDictItemTemp(Long.valueOf(document.get(DictItemTempTerm.PARENT_ITEM_ID)));

				ParentItemModel parentItemModel = new ParentItemModel();

				if (Validator.isNotNull(parentItem)) {

					parentItemModel.setItemCode(parentItem.getItemCode());
					parentItemModel.setItemName(parentItem.getItemName());
					parentItemModel.setItemNameEN(parentItem.getItemNameEN());

				}

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}	
	
	@SuppressWarnings("unchecked")
	public static DictItemTempDetailModel mapperDictItemTempModel(DictItemTemp dictItem, DictCollectionTempInterface dictItemDataUtil,
			long userId, long companyId, long groupId, ServiceContext serviceContext) {

		DictItemTempDetailModel ett = new DictItemTempDetailModel();

		try {

			ett.setDictItemId(dictItem.getDictItemId());
			ett.setItemCode(dictItem.getItemCode());
			ett.setItemDescription(dictItem.getItemDescription());
			ett.setItemName(dictItem.getItemName());
			ett.setItemNameEN(dictItem.getItemNameEN());
			ett.setTreeIndex(dictItem.getTreeIndex());
			ett.setSibling(GetterUtil.get(dictItem.getSibling(), 1));
			ett.setLevel(dictItem.getLevel());
			ett.setCreateDate(Validator.isNotNull(dictItem.getCreateDate())
					? APIDateTimeUtils.convertDateToString(dictItem.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setModifiedDate(Validator.isNotNull(dictItem.getModifiedDate())
					? APIDateTimeUtils.convertDateToString(dictItem.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);

			DictItemTemp parentItem = DictItemTempLocalServiceUtil.fetchDictItemTemp(dictItem.getParentItemId());

			ParentItemModel parentItemModel = new ParentItemModel();

			if (Validator.isNotNull(parentItem)) {
				parentItemModel.setItemCode(parentItem.getItemCode());
				parentItemModel.setItemName(parentItem.getItemName());
				parentItemModel.setItemNameEN(parentItem.getItemNameEN());
			}

			ett.getParentItemModel().add(parentItemModel);
			
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(DictItemGroupTerm.DICT_ITEM_ID, String.valueOf(dictItem.getDictItemId()));

			JSONObject jsonData = dictItemDataUtil.getDictItemsGroupTemp(userId, companyId, groupId, params, new Sort[] {},
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			List<DictGroupTempModel> listGroup = new ArrayList<>();

			try {

				DictGroupTempModel ettGroup = null;

				for (Document document : (List<Document>) jsonData.get("data")) {

					ettGroup = new DictGroupTempModel();

					ettGroup.setGroupCode(document.get(DictGroupTempTerm.GROUP_CODE));
					ettGroup.setGroupName(document.get(DictGroupTempTerm.GROUP_NAME));
					ettGroup.setGroupNameEN(document.get(DictGroupTempTerm.GROUP_NAME_EN));
					ettGroup.setGroupDescription(document.get(DictGroupTempTerm.GROUP_DESCRIPTION));

					listGroup.add(ettGroup);
				}

			} catch (Exception e) {
				//_log.error(e);
				_log.debug(e);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}
	
	public static JSONObject convertObject(Object o) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		if (o instanceof DictCollectionTemp) {
			DictCollectionTemp dictCollection = (DictCollectionTemp)o;
			result.put(DictCollectionTempTerm.MODIFIED_DATE, APIDateTimeUtils.convertDateToString(dictCollection.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			result.put(DictCollectionTempTerm.COLLECTION_CODE, dictCollection.getCollectionCode());
			result.put(DictCollectionTempTerm.COLLECTION_NAME, dictCollection.getCollectionName());
			result.put(DictCollectionTempTerm.COLLECTION_NAME_EN, dictCollection.getCollectionNameEN());
			result.put(DictCollectionTempTerm.DESCRIPTION, dictCollection.getDescription());
			result.put(DictCollectionTempTerm.DATAFORM, dictCollection.getDataForm());
		}
		else if (o instanceof DictGroupTemp) {
			DictGroupTemp dictGroup = (DictGroupTemp)o;
			result.put(DictGroupTempTerm.MODIFIED_DATE, APIDateTimeUtils.convertDateToString(dictGroup.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			result.put(DictGroupTempTerm.GROUP_CODE, dictGroup.getGroupCode());
			result.put(DictGroupTempTerm.GROUP_NAME, dictGroup.getGroupName());
			result.put(DictGroupTempTerm.GROUP_NAME_EN, dictGroup.getGroupNameEN());
			result.put(DictGroupTempTerm.GROUP_DESCRIPTION, dictGroup.getGroupDescription());
			try {
				DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictGroup.getDictCollectionId());
				result.put(DictCollectionTempTerm.COLLECTION_CODE, dictCollection.getCollectionCode());
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}
		}
		else if (o instanceof DictItemTemp) {
			DictItemTemp dictItem = (DictItemTemp)o;
			
			result.put(DictItemTempTerm.MODIFIED_DATE, APIDateTimeUtils.convertDateToString(dictItem.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));
			result.put(DictItemTempTerm.ITEM_CODE, dictItem.getItemCode());
			result.put(DictItemTempTerm.ITEM_NAME, dictItem.getItemName());
			result.put(DictItemTempTerm.ITEM_NAME_EN, dictItem.getItemNameEN());
			result.put(DictItemTempTerm.ITEM_DESCRIPTION, dictItem.getItemDescription());
			result.put(DictItemTempTerm.META_DATA, dictItem.getMetaData());
			result.put(DictItemTempTerm.SIBLING, dictItem.getSibling());
			result.put(DictItemTempTerm.LEVEL, dictItem.getLevel());
			
			try {
				DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictItem.getDictCollectionId());
				result.put(DictCollectionTempTerm.COLLECTION_CODE, dictCollection.getCollectionCode());
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}			
			try {
				DictItemTemp parentItem = DictItemTempLocalServiceUtil.fetchDictItemTemp(dictItem.getParentItemId());
				result.put(DictItemTempTerm.PARENT_ITEM_CODE, parentItem.getItemCode());
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}			
		}
		else if (o instanceof DictItemGroupTemp) {
			DictItemGroupTemp dictItemGroup = (DictItemGroupTemp)o;
			
			result.put(DictItemGroupTempTerm.DICT_GROUP_ID, dictItemGroup.getDictGroupId());
			result.put(DictItemGroupTempTerm.DICT_GROUP_NAME, dictItemGroup.getDictGroupName());
			result.put(DictItemGroupTempTerm.DICT_ITEM_ID, dictItemGroup.getDictItemId());
			result.put(DictItemGroupTempTerm.DICT_ITEM_GROUP_ID, dictItemGroup.getDictItemGroupId());
			try {
				DictItemTemp dictItem = DictItemTempLocalServiceUtil.fetchDictItemTemp(dictItemGroup.getDictItemId());
				result.put(DictItemTempTerm.ITEM_CODE, dictItem.getItemCode());
				try {
					DictCollectionTemp dictCollection = DictCollectionTempLocalServiceUtil.fetchDictCollectionTemp(dictItem.getDictCollectionId());
					result.put(DictCollectionTempTerm.COLLECTION_CODE, dictCollection.getCollectionCode());
				}
				catch (Exception e) {
					_log.debug(e);
					//_log.error(e);
				}			
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}			
			try {
				DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.fetchDictGroupTemp(dictItemGroup.getDictGroupId());
				result.put(DictGroupTempTerm.GROUP_CODE, dictGroup.getGroupCode());
			}
			catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
			}
		}
		
		return result;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(DataTempManagementUtils.class);

}
