package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.api.datamgt.model.DictCollectionModel;
import org.opencps.api.datamgt.model.DictCollectionShortModel;
import org.opencps.api.datamgt.model.DictGroupItemModel;
import org.opencps.api.datamgt.model.DictItemModel;
import org.opencps.api.datamgt.model.Groups;
import org.opencps.api.datamgt.model.ParentItem;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.constants.DictCollectionTerm;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.auth.utils.APIDateTimeUtils;

public class DataManagementUtils {

	public static List<DictCollectionShortModel> mapperDictCollectionShortModelList(List<Document> listDocument) {

		List<DictCollectionShortModel> results = new ArrayList<>();

		try {

			DictCollectionShortModel ett = null;

			for (Document document : listDocument) {
				ett = new DictCollectionShortModel();

				ett.setCollectionCode(document.get(DictCollectionTerm.COLLECTION_CODE));
				ett.setCollectionName(document.get(DictCollectionTerm.COLLECTION_NAME));
				ett.setCollectionNameEN(document.get(DictCollectionTerm.COLLECTION_NAME_EN));
				ett.setDescription(document.get(DictCollectionTerm.DESCRIPTION));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<Groups> mapperGroupsList(List<Document> listDocument) {

		List<Groups> results = new ArrayList<>();

		try {

			Groups ett = null;

			for (Document document : listDocument) {
				ett = new Groups();

				ett.setDictGroupId(Long.valueOf(document.get("entryClassPK")));
				ett.setGroupCode(document.get(DictGroupTerm.GROUP_CODE));
				ett.setGroupName(document.get(DictGroupTerm.GROUP_NAME));
				ett.setGroupNameEN(document.get(DictGroupTerm.GROUP_NAME_EN));
				ett.setGroupDescription(document.get(DictGroupTerm.GROUP_DESCRIPTION));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<DictGroupItemModel> mapperDictGroupItemModelList(List<Document> listDocument) {

		List<DictGroupItemModel> results = new ArrayList<>();

		try {

			DictGroupItemModel ett = null;

			for (Document document : listDocument) {
				ett = new DictGroupItemModel();

				ett.setDictItemId(Long.valueOf(document.get(DictItemGroupTerm.DICT_ITEM_ID)));
				ett.setItemCode(document.get(DictItemGroupTerm.ITEM_CODE));
				ett.setItemName(document.get(DictItemGroupTerm.ITEM_NAME));
				ett.setItemNameEN(document.get(DictItemGroupTerm.ITEM_NAME_EN));
				ett.setItemDescription(document.get(DictItemGroupTerm.ITEM_DESCRIPTION));
				ett.setSelected(Boolean.valueOf(document.get(DictItemGroupTerm.SELECTED)));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static List<DictItemModel> mapperDictItemModelList(List<Document> listDocument) {

		List<DictItemModel> results = new ArrayList<>();

		try {

			DictItemModel ett = null;

			for (Document document : listDocument) {
				ett = new DictItemModel();

				ett.setDictItemId(Long.valueOf(document.get("entryClassPK")));
				ett.setItemCode(document.get(DictItemTerm.ITEM_CODE));
				ett.setItemName(document.get(DictItemTerm.ITEM_NAME));
				ett.setItemNameEN(document.get(DictItemTerm.ITEM_NAME_EN));
				ett.setItemDescription(document.get(DictItemTerm.ITEM_DESCRIPTION));
				ett.setLevel(Integer.valueOf(document.get(DictItemTerm.LEVEL)));
				ett.setSibling(Integer.valueOf(document.get(DictItemTerm.LEVEL)));
				ett.setTreeIndex(document.get(DictItemTerm.TREE_INDEX));

				ett.setCreateDate(Validator.isNotNull(document.get(DictItemTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(DictItemTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.get("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				DictItem parentItem = DictItemLocalServiceUtil
						.fetchDictItem(Long.valueOf(document.get(DictItemTerm.PARENT_ITEM_ID)));

				ParentItem parentItemModel = new ParentItem();

				if (Validator.isNotNull(parentItem)) {

					parentItemModel.setItemCode(parentItem.getItemCode());
					parentItemModel.setItemName(parentItem.getItemName());
					parentItemModel.setItemNameEN(parentItem.getItemNameEN());

				}

				ett.getParentItem().add(parentItemModel);

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static DictCollectionModel mapperDictCollectionModel(DictCollection dictCollection) {

		DictCollectionModel ett = new DictCollectionModel();

		try {

			ett.setDictCollectionId(dictCollection.getDictCollectionId());
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

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static Groups mapperGroups(DictGroup dictGroup) {

		Groups ett = new Groups();

		try {

			ett.setDictGroupId(dictGroup.getDictGroupId());
			ett.setGroupCode(dictGroup.getGroupCode());
			ett.setGroupName(dictGroup.getGroupName());
			ett.setGroupNameEN(dictGroup.getGroupNameEN());
			ett.setGroupDescription(dictGroup.getGroupDescription());
			// ett.setCreateDate(Validator.isNotNull(dictCollection.getCreateDate())
			// ?
			// APIDateTimeUtils.convertDateToString(dictCollection.getCreateDate(),
			// APIDateTimeUtils._TIMESTAMP)
			// : StringPool.BLANK);
			// ett.setModifiedDate(
			// Validator.isNotNull(dictCollection.getModifiedDate()) ?
			// APIDateTimeUtils.convertDateToString(
			// dictCollection.getModifiedDate(), APIDateTimeUtils._TIMESTAMP) :
			// StringPool.BLANK);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static DictGroupItemModel mapperDictGroupItemModel(DictItemGroup dictItemGroup) {

		DictGroupItemModel ett = new DictGroupItemModel();

		try {

			long dictItemId = dictItemGroup.getDictItemId();

			DictItem dictItem = DictItemLocalServiceUtil.fetchDictItem(dictItemId);

			ett.setDictItemId(dictItemId);
			ett.setItemCode(dictItem.getItemCode());
			ett.setItemName(dictItem.getItemName());
			ett.setItemNameEN(dictItem.getItemNameEN());
			ett.setItemDescription(String.valueOf(dictItem.getItemDescription()));
			// ett.setCreateDate(Validator.isNotNull(dictCollection.getCreateDate())
			// ?
			// APIDateTimeUtils.convertDateToString(dictCollection.getCreateDate(),
			// APIDateTimeUtils._TIMESTAMP)
			// : StringPool.BLANK);
			// ett.setModifiedDate(
			// Validator.isNotNull(dictCollection.getModifiedDate()) ?
			// APIDateTimeUtils.convertDateToString(
			// dictCollection.getModifiedDate(), APIDateTimeUtils._TIMESTAMP) :
			// StringPool.BLANK);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static DictItemModel mapperDictItemModel(DictItem dictItem, DictcollectionInterface dictItemDataUtil,
			long userId, long companyId, long groupId, ServiceContext serviceContext) {

		DictItemModel ett = new DictItemModel();

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

			DictItem parentItem = DictItemLocalServiceUtil.fetchDictItem(dictItem.getParentItemId());

			ParentItem parentItemModel = new ParentItem();

			if (Validator.isNotNull(parentItem)) {
				parentItemModel.setItemCode(parentItem.getItemCode());
				parentItemModel.setItemName(parentItem.getItemName());
				parentItemModel.setItemNameEN(parentItem.getItemNameEN());
			}

			ett.getParentItem().add(parentItemModel);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(DictItemGroupTerm.DICT_ITEM_ID, String.valueOf(dictItem.getDictItemId()));

			JSONObject jsonData = dictItemDataUtil.getDictItemsGroup(userId, companyId, groupId, params, new Sort[] {},
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			List<Groups> listGroup = new ArrayList<>();

			try {

				Groups ettGroup = null;

				for (Document document : (List<Document>) jsonData.get("data")) {

					ettGroup = new Groups();

					ettGroup.setDictGroupId(Long.valueOf(document.get(DictGroupTerm.DICT_GROUPID)));
					ettGroup.setGroupCode(document.get(DictGroupTerm.GROUP_CODE));
					ettGroup.setGroupName(document.get(DictGroupTerm.GROUP_NAME));
					ettGroup.setGroupNameEN(document.get(DictGroupTerm.GROUP_NAME_EN));
					ettGroup.setGroupDescription(document.get(DictGroupTerm.GROUP_DESCRIPTION));

					listGroup.add(ettGroup);
				}

			} catch (Exception e) {
				_log.error(e);
			}

			ett.getGroups().addAll(listGroup);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static Log _log = LogFactoryUtil.getLog(DataManagementUtils.class);

}
