package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.api.datamgt.model.DictCollectionModel;
import org.opencps.api.datamgt.model.DictCollectionShortModel;
import org.opencps.api.datamgt.model.DictGroupItemModel;
import org.opencps.api.datamgt.model.DictItemModel;
import org.opencps.api.datamgt.model.Groups;
import org.opencps.api.datamgt.model.ParentItem;
import org.opencps.api.dictcollection.model.DictGroupModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.constants.DictCollectionTerm;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;

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
			//_log.error(e);
			_log.debug(e);
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
			//_log.error(e);
			_log.debug(e);
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
			//_log.error(e);
			_log.debug(e);
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
			//_log.error(e);
			_log.debug(e);
		}

		return results;
	}

	public static DictCollectionModel mapperDictCollectionModel(DictCollection dictCollection) {

		DictCollectionModel ett = new DictCollectionModel();

		try {

			if (ett != null) {
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
			}

		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
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
			//_log.error(e);
			_log.debug(e);
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
			//_log.error(e);
			_log.debug(e);
		}

		return ett;
	}

	@SuppressWarnings("unchecked")
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
				//_log.error(e);
				_log.debug(e);
			}

			ett.getGroups().addAll(listGroup);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	public static List<org.opencps.api.datamgtsync.model.DictCollectionModel> mapperDictCollectionList(List<DictCollection> lstCollections) {		
		List<org.opencps.api.datamgtsync.model.DictCollectionModel> results = new ArrayList<>();
		
		for (DictCollection dc : lstCollections) {
			org.opencps.api.datamgtsync.model.DictCollectionModel model = new org.opencps.api.datamgtsync.model.DictCollectionModel();
			model.setCollectionCode(dc.getCollectionCode());
			model.setCollectionName(dc.getCollectionName());
			model.setCollectionNameEN(dc.getCollectionNameEN());
			model.setDataForm(dc.getDataForm());
			model.setDescription(dc.getDescription());
			if (Validator.isNotNull(dc.getCreateDate())) {
				model.setCreateDate(dc.getCreateDate().getTime());				
			}
			if (Validator.isNotNull(dc.getModifiedDate())) {
				model.setModifiedDate(dc.getModifiedDate().getTime());				
			}
			
			results.add(model);
		}
		return results;
	}
	public static List<org.opencps.api.datamgtsync.model.DictItemModel> mapperDictItemList(List<DictItem> lstItems) {		
		List<org.opencps.api.datamgtsync.model.DictItemModel> results = new ArrayList<>();
		
		for (DictItem dc : lstItems) {
			org.opencps.api.datamgtsync.model.DictItemModel model = new org.opencps.api.datamgtsync.model.DictItemModel();
			try {
				DictCollection collection = DictCollectionLocalServiceUtil.fetchDictCollection(dc.getDictCollectionId());
				DictItem parentItem = null;
				try {
					parentItem = DictItemLocalServiceUtil.fetchDictItem(dc.getParentItemId());
				}
				catch (Exception e) {
					//_log.error(e);
					_log.debug(e);
				}
				model.setDictCollectionCode(collection.getCollectionCode());
				model.setItemCode(dc.getItemCode());
				model.setItemDescription(dc.getItemDescription());
				model.setItemName(dc.getItemName());
				model.setLevel(dc.getLevel());
				model.setMetaData(dc.getMetaData());
				model.setSibling(dc.getSibling());
				model.setItemNameEN(dc.getItemNameEN());
				if (parentItem != null) {
					model.setParentItemCode(parentItem.getItemCode());					
				}
				model.setTreeIndex(dc.getTreeIndex());
				
				if (Validator.isNotNull(dc.getCreateDate())) {
					model.setCreateDate(dc.getCreateDate().getTime());				
				}
				if (Validator.isNotNull(dc.getModifiedDate())) {
					model.setModifiedDate(dc.getModifiedDate().getTime());				
				}
				
				results.add(model);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
		return results;
	}	

	public static List<org.opencps.api.datamgtsync.model.DictGroupModel> mapperDictGroupList(List<DictGroup> lstGroups) {		
		List<org.opencps.api.datamgtsync.model.DictGroupModel> results = new ArrayList<>();
		
		for (DictGroup dg : lstGroups) {
			org.opencps.api.datamgtsync.model.DictGroupModel model = new org.opencps.api.datamgtsync.model.DictGroupModel();
			try {
				DictCollection collection = DictCollectionLocalServiceUtil.fetchDictCollection(dg.getDictCollectionId());

				model.setCollectionCode(collection.getCollectionCode());
				
				model.setGroupCode(dg.getGroupCode());
				model.setGroupName(dg.getGroupName());
				model.setGroupNameEN(dg.getGroupNameEN());
				model.setGroupDescription(dg.getGroupDescription());
				
				if (Validator.isNotNull(dg.getCreateDate())) {
					model.setCreateDate(dg.getCreateDate().getTime());				
				}
				if (Validator.isNotNull(dg.getModifiedDate())) {
					model.setModifiedDate(dg.getModifiedDate().getTime());				
				}
				
				results.add(model);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
		return results;
	}	
	public static List<org.opencps.api.datamgtsync.model.DictItemGroupModel> mapperDictItemGroupList(List<DictItemGroup> lstDictItemGroups) {		
		List<org.opencps.api.datamgtsync.model.DictItemGroupModel> results = new ArrayList<>();
		
		for (DictItemGroup dig : lstDictItemGroups) {
			org.opencps.api.datamgtsync.model.DictItemGroupModel model = new org.opencps.api.datamgtsync.model.DictItemGroupModel();
			try {
				DictGroup group = DictGroupLocalServiceUtil.fetchDictGroup(dig.getDictGroupId());
				DictCollection collection = DictCollectionLocalServiceUtil.fetchDictCollection(group.getDictCollectionId());
				DictItem item = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
				
				model.setCollectionCode(collection.getCollectionCode());
				model.setGroupCode(group.getGroupCode());
				model.setItemCode(item.getItemCode());
				
				if (Validator.isNotNull(dig.getCreateDate())) {
					model.setCreateDate(dig.getCreateDate().getTime());				
				}
				if (Validator.isNotNull(dig.getModifiedDate())) {
					model.setModifiedDate(dig.getModifiedDate().getTime());				
				}
				
				results.add(model);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
		return results;
	}	
	
	public static DictGroupModel mapperDictGroupModel(DictGroup dictGroup) {
		DictGroupModel model = new DictGroupModel();
		model.setGroupCode(dictGroup.getGroupCode());
		model.setGroupDescription(dictGroup.getGroupDescription());
		model.setGroupName(dictGroup.getGroupName());
		model.setGroupNameEN(dictGroup.getGroupNameEN());
		return model;
	}

	private static final Log _log = LogFactoryUtil.getLog(DataManagementUtils.class);

//	public static void main(String []args) {
//		List<String> collectionList = new ArrayList<>();
//		collectionList.add("abc");
//		collectionList.add("def");
//		collectionList.add("ghi");
//		collectionList.add("xyz");
//		
//		if (collectionList.contains("abc")) {
//			System.out.println("abc");
//		}
//		if (collectionList.contains("abcc")) {
//			System.out.println("ABc");
//		}
//		if (collectionList.contains("def")) {
//			System.out.println("def");
//		}
//		
//	}
	
	/** LGSP - START */
	public static List<DictCollectionShortModel> mapperDictCollectionLGSPModelList(List<Document> listDocument) {

		List<DictCollectionShortModel> results = new ArrayList<>();

		try {

			DictCollectionShortModel ett = null;

			StringBuilder sbCollect = new StringBuilder();
			for (Document doc : listDocument) {
				String collectionCode = doc.get(DictCollectionTerm.COLLECTION_CODE);
				String strCollect = sbCollect.toString();
				if (Validator.isNotNull(strCollect)) {
					if (!strCollect.contains(collectionCode)) {
						sbCollect.append(StringPool.COMMA);
						sbCollect.append(collectionCode);
					}
				} else {
					sbCollect.append(collectionCode);
				}
			}
			String[] splitCollect = StringUtil.split(sbCollect.toString(), StringPool.COMMA);
			for (String collect : splitCollect) {
				ett = new DictCollectionShortModel();

				for (Document doc : listDocument) {
					if (Validator.isNotNull(collect)
							&& collect.equals(doc.get(DictCollectionTerm.COLLECTION_CODE))) {
						ett.setCollectionCode(doc.get(DictCollectionTerm.COLLECTION_CODE));
						ett.setCollectionName(doc.get(DictCollectionTerm.COLLECTION_NAME));
						ett.setCollectionNameEN(doc.get(DictCollectionTerm.COLLECTION_NAME_EN));
						ett.setDescription(doc.get(DictCollectionTerm.DESCRIPTION));

						results.add(ett);
						break;
					}
				}
			}

		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
			_log.info("kkkk@@: "+ (11+ 1));
		}

		return results;
	}

	public static List<Groups> mapperGroupsListLGSP(List<Document> listDocument) {

		List<Groups> results = new ArrayList<>();

		try {

			Groups ett = null;

			StringBuilder sbGroup = new StringBuilder();
			for (Document doc : listDocument) {
				String groupCode = doc.get(DictGroupTerm.GROUP_CODE);
				String strGroup = sbGroup.toString();
				if (Validator.isNotNull(strGroup)) {
					if (!strGroup.contains(groupCode)) {
						sbGroup.append(StringPool.COMMA);
						sbGroup.append(groupCode);
					}
				} else {
					sbGroup.append(groupCode);
				}
			}
			String[] splitGroup = StringUtil.split(sbGroup.toString(), StringPool.COMMA);
			for (String group : splitGroup) {
				ett = new Groups();

				for (Document doc : listDocument) {
					if (Validator.isNotNull(group)
							&& group.equals(doc.get(DictGroupTerm.GROUP_CODE))) {
						ett.setDictGroupId(Long.valueOf(doc.get("entryClassPK")));
						ett.setGroupCode(doc.get(DictGroupTerm.GROUP_CODE));
						ett.setGroupName(doc.get(DictGroupTerm.GROUP_NAME));
						ett.setGroupNameEN(doc.get(DictGroupTerm.GROUP_NAME_EN));
						ett.setGroupDescription(doc.get(DictGroupTerm.GROUP_DESCRIPTION));

						results.add(ett);
						break;
					}
				}
			}
		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
		}

		return results;
	}

	public static List<DictItemModel> mapperDictItemModelListLGSP(List<Document> listDocument, String collectionCode) {

		List<DictItemModel> results = new ArrayList<>();

		try {

			DictItemModel ett = null;

			if ("ADMINISTRATIVE_REGION".equalsIgnoreCase(collectionCode)) {
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
			} else {
				StringBuilder sbItem = new StringBuilder();
				for (Document doc : listDocument) {
					String itemCode = doc.get(DictItemTerm.ITEM_CODE);
					String strItem = sbItem.toString();
					if (Validator.isNotNull(strItem)) {
						if (!strItem.contains(itemCode)) {
							sbItem.append(StringPool.COMMA);
							sbItem.append(itemCode);
						}
					} else {
						sbItem.append(collectionCode);
					}
				}
				_log.info("sbItem: "+sbItem.toString());
				String[] splitItem = StringUtil.split(sbItem.toString(), StringPool.COMMA);
				for (String item : splitItem) {
					ett = new DictItemModel();
					_log.info("item: "+item);

					for (Document doc : listDocument) {
						_log.info("flagEqual 11111: "+doc.get(DictItemTerm.ITEM_CODE));
						if (Validator.isNotNull(item)
								&& item.equals(doc.get(DictItemTerm.ITEM_CODE))) {
							ett.setDictItemId(Long.valueOf(doc.get("entryClassPK")));
							ett.setItemCode(doc.get(DictItemTerm.ITEM_CODE));
							ett.setItemName(doc.get(DictItemTerm.ITEM_NAME));
							ett.setItemNameEN(doc.get(DictItemTerm.ITEM_NAME_EN));
							ett.setItemDescription(doc.get(DictItemTerm.ITEM_DESCRIPTION));
							ett.setLevel(Integer.valueOf(doc.get(DictItemTerm.LEVEL)));
							ett.setSibling(Integer.valueOf(doc.get(DictItemTerm.LEVEL)));
							ett.setTreeIndex(doc.get(DictItemTerm.TREE_INDEX));

							ett.setCreateDate(Validator.isNotNull(doc.get(DictItemTerm.CREATE_DATE)) ? APIDateTimeUtils
									.convertDateToString(doc.getDate(DictItemTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
									: StringPool.BLANK);
							ett.setModifiedDate(
									Validator.isNotNull(doc.get("modified")) ? APIDateTimeUtils.convertDateToString(
											doc.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

							DictItem parentItem = DictItemLocalServiceUtil
									.fetchDictItem(Long.valueOf(doc.get(DictItemTerm.PARENT_ITEM_ID)));

							ParentItem parentItemModel = new ParentItem();

							if (Validator.isNotNull(parentItem)) {

								parentItemModel.setItemCode(parentItem.getItemCode());
								parentItemModel.setItemName(parentItem.getItemName());
								parentItemModel.setItemNameEN(parentItem.getItemNameEN());

							}

							ett.getParentItem().add(parentItemModel);

							results.add(ett);
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			//_log.error(e);
			_log.debug(e);
		}

		return results;
	}

}
