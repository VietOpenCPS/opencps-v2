package org.opencps.api.controller.impl;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.DataManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DataManagementUtils;
import org.opencps.api.datamgt.model.DataSearchModel;
import org.opencps.api.datamgt.model.DictCollectionInputModel;
import org.opencps.api.datamgt.model.DictCollectionModel;
import org.opencps.api.datamgt.model.DictCollectionResults;
import org.opencps.api.datamgt.model.DictCollectionShortModel;
import org.opencps.api.datamgt.model.DictGroupInputModel;
import org.opencps.api.datamgt.model.DictGroupItemModel;
import org.opencps.api.datamgt.model.DictGroupItemResults;
import org.opencps.api.datamgt.model.DictGroupResults;
import org.opencps.api.datamgt.model.DictItemInputModel;
import org.opencps.api.datamgt.model.DictItemModel;
import org.opencps.api.datamgt.model.DictItemResults;
import org.opencps.api.datamgt.model.Groups;
import org.opencps.api.dictcollection.model.DictGroupModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.model.DictItemMapping;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.service.DictItemMappingLocalServiceUtil;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DataManagementImpl implements DataManagement {

	Log _log = LogFactoryUtil.getLog(DataManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query, String status) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionResults result = new DictCollectionResults();

		try {

			if (query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put("status", status);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictCollection(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictCollectionShortModel().addAll(
					DataManagementUtils.mapperDictCollectionShortModelList((List<Document>) jsonData.get("data")));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDictCollectionDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, Request requestCC) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictCollection dictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);
		EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
		ResponseBuilder builder = requestCC.evaluatePreconditions(etag);

		if (Validator.isNotNull(dictCollection)) {
			DictCollectionModel dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);
			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
				builder = Response.status(HttpURLConnection.HTTP_OK);
				CacheControl cc = new CacheControl();
				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
				cc.setPrivate(true);
				// return json object after update

				return builder.entity(dictCollectionModel).cacheControl(cc).build();
			} else {
				return builder.entity(dictCollectionModel).build();
			}

		} else {
			return null;
		}
	}

	@Override
	public Response addDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DictCollectionInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String collectionCode = HtmlUtil.escape(input.getCollectionCode());
			String collectionName = HtmlUtil.escape(input.getCollectionName());
			String collectionNameEN = HtmlUtil.escape(input.getCollectionNameEN());
			String description = HtmlUtil.escape(input.getDescription());

			DictCollection dictCollection = dictItemDataUtil.addDictCollection(user.getUserId(), groupId,
					collectionCode, collectionName, collectionNameEN, description, serviceContext);

			// return json object after update
			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictCollectionInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String collectionCode = HtmlUtil.escape(input.getCollectionCode());
			String collectionName = HtmlUtil.escape(input.getCollectionName());
			String collectionNameEN = HtmlUtil.escape(input.getCollectionNameEN());
			String description = HtmlUtil.escape(input.getDescription());

			String oldCode = HtmlUtil.escape(code);

			_log.info("Update dict collection: " + code);

			DictCollection dictCollection = dictItemDataUtil.updateDictCollection(user.getUserId(), groupId, oldCode,
					collectionCode, collectionName, collectionNameEN, description, serviceContext);

			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			boolean flag = dictItemDataUtil.deleteDictCollection(code, groupId, serviceContext);

			if (flag) {
				return Response.status(HttpURLConnection.HTTP_OK).build();
			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDataForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictCollection dictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);

		if (Validator.isNotNull(dictCollection)) {

			// return json object after update
			return Response.status(HttpURLConnection.HTTP_OK).entity(dictCollection.getDataForm()).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription("not found!");

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

		}
	}

	@Override
	public Response addDataForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String dataform, long modifiedDateTime) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictCollection oldCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);

			if (modifiedDateTime == 0 || (modifiedDateTime != 0
					&& oldCollection.getModifiedDate().compareTo(new Date(modifiedDateTime)) < 0)) {
				DictCollection dictCollection = dictItemDataUtil.addDataForm(user.getUserId(), groupId, code, dataform,
						serviceContext);

				return Response.status(HttpURLConnection.HTTP_OK)
						.entity(dictCollection != null ? dictCollection.getDataForm() : StringPool.BLANK).build();
			} else {
				throw new DuplicateCategoryException();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupResults result = new DictGroupResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(DictGroupTerm.DICT_COLLECTION_CODE, code);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictgroups(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getGroups().addAll(DataManagementUtils.mapperGroupsList((List<Document>) jsonData.get("data")));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictGroupInputModel input) {
		Groups dictGroupModel = new Groups();
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictGroup dictGroup = dictItemDataUtil.addDictgroups(user.getUserId(), groupId, code, input.getGroupCode(),
					input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), serviceContext);

			// return json object after update
			dictGroupModel = DataManagementUtils.mapperGroups(dictGroup);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictGroupModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String groupCode, DictGroupInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		Groups dictGroupModel = new Groups();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictGroup dictGroup = dictItemDataUtil.updateDictgroups(user.getUserId(), groupId, code, groupCode,
					input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
					serviceContext);

			dictGroupModel = DataManagementUtils.mapperGroups(dictGroup);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictGroupModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String groupCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			
//			boolean flag = dictItemDataUtil.deleteDictgroups(groupCode, groupId, serviceContext);
			boolean flag = dictItemDataUtil.deleteDictgroups(code, groupCode, groupId, serviceContext);

			if (flag) {

				return Response.status(HttpURLConnection.HTTP_OK).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

			}

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, boolean full) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());
		DictGroupItemResults result = new DictGroupItemResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);
			params.put(DictGroupTerm.GROUP_CODE, groupCode);

			JSONObject jsonData = dictItemDataUtil.getDictgroupsDictItems(user.getUserId(), company.getCompanyId(),
					groupId, code, groupCode, full, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictGroupItemModel()
					.addAll(DataManagementUtils.mapperDictGroupItemModelList((List<Document>) jsonData.get("data")));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupItemModel dictGroupItemModel = new DictGroupItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictItemGroup dictItemGroup = dictItemDataUtil.addDictgroupsDictItems(user.getUserId(), groupId, code,
					groupCode, itemCode, serviceContext);

			try {
				List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);

				DictGroup group = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

			} catch (Exception e) {
				_log.error(e);
			}

			dictGroupItemModel = DataManagementUtils.mapperDictGroupItemModel(dictItemGroup);
			dictGroupItemModel.setSelected(Boolean.TRUE);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictGroupItemModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

//			DictCollection collection = null;
//			try {
//				collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
//			} catch (Exception e) {
//				_log.error(e);
//			}
//			DictGroup group = null;
//			try {
//				if (collection != null) {
//					group = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());					
//				}
//			} catch (Exception e) {
//				_log.error(e);
//			}
//			DictItem item = null;
//
//			try {
//				if (collection != null)
//					item = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, collection.getDictCollectionId(),
//							groupId);
//			} catch (Exception e) {
//				_log.error(e);
//			}
//			DictItemGroup dictItemGroup = null;
//
//			try {
//				if (collection != null && group != null && item != null)
//					dictItemGroup = DictItemGroupLocalServiceUtil.fetchByF_dictItemId_dictGroupId(groupId,
//							group.getDictGroupId(), item.getDictItemId());
//			} catch (Exception e) {
//				_log.error(e);
//			}
			boolean flag = dictItemDataUtil.deleteDictgroupsDictItems(groupId, code, groupCode, itemCode,
					serviceContext);

			if (flag) {

				return Response.status(HttpURLConnection.HTTP_OK).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	
	@SuppressWarnings("unchecked")
	@Override
	public Response getDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query, Request requestCC) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemResults result = new DictItemResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if (ADMINISTRATIVE_REGION.equalsIgnoreCase(code))
				groupId = 0;

			params.put(Field.GROUP_ID, groupId);
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(ConstantUtils.API_JSON_DATA_ITEMLV, query.getLevel());
			params.put(DictItemTerm.PARENT_ITEM_CODE, query.getParent());
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);

			Sort[] sorts = null;

			if (Validator.isNull(query.getSort())) {
				sorts = new Sort[] { SortFactoryUtil.create(DictItemTerm.SIBLING_SEARCH + "_Number_sortable",
						Sort.INT_TYPE, false) };
			} else {
				sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };
			}

			JSONObject jsonData = dictItemDataUtil.getDictItems(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictItemModel()
					.addAll(DataManagementUtils.mapperDictItemModelList((List<Document>) jsonData.get("data")));

			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
			ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
				CacheControl cc = new CacheControl();
				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
				cc.setPrivate(true);
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).cacheControl(cc).build();
			} else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictItemInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemModel dictItemModel = new DictItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String itemCode = HtmlUtil.escape(input.getItemCode());
			String itemName = HtmlUtil.escape(input.getItemName());
			String itemNameEN = HtmlUtil.escape(input.getItemNameEN());
			String itemDescription = HtmlUtil.escape(input.getItemDescription());
			String parentItemCode = HtmlUtil.escape(input.getParentItemCode());
			String sibling = input.getSibling();
			String metaData = HtmlUtil.escape(input.getMetaData());

			DictItem dictItem = dictItemDataUtil.addDictItems(user.getUserId(), groupId, code, parentItemCode, itemCode,
					itemName, itemNameEN, itemDescription, sibling, input.getLevel(), metaData, serviceContext);

			// return json object after update
			dictItemModel = DataManagementUtils.mapperDictItemModel(dictItem, dictItemDataUtil, user.getUserId(),
					company.getCompanyId(), groupId, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictItemModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictItem dictItem = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			DictItemModel dictItemModel = DataManagementUtils.mapperDictItemModel(dictItem, dictItemDataUtil,
					user.getUserId(), company.getCompanyId(), groupId, serviceContext);
			CacheControl cc = new CacheControl();
			cc.setMaxAge(86400);
			cc.setPrivate(true);
			return Response.status(HttpURLConnection.HTTP_OK).entity(dictItemModel).cacheControl(cc).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription("not found!");

			return Response.status(HttpURLConnection.HTTP_CONFLICT).entity(error).build();

		}
	}

	@Override
	public Response updateDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemModel dictItemModel = new DictItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String newItemCode = HtmlUtil.escape(input.getItemCode());
			String itemName = HtmlUtil.escape(input.getItemName());
			String itemNameEN = HtmlUtil.escape(input.getItemNameEN());
			String itemDescription = HtmlUtil.escape(input.getItemDescription());
			String parentItemCode = HtmlUtil.escape(input.getParentItemCode());
			String sibling = input.getSibling();
//			String metaData = HtmlUtil.escape(input.getMetaData());

			DictItem ett = dictItemDataUtil.updateDictItemByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, newItemCode, itemName, itemNameEN, itemDescription, sibling, parentItemCode);

			if (Validator.isNull(ett)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

			} else {

				// return json object after update
				dictItemModel = DataManagementUtils.mapperDictItemModel(ett, dictItemDataUtil, user.getUserId(),
						company.getCompanyId(), groupId, serviceContext);

				return Response.status(HttpURLConnection.HTTP_OK).entity(dictItemModel).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response dateletDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictItem dictColl = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

			if (Validator.isNull(dictColl)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

			} else {
				DictItemLocalServiceUtil.deleteDictItem(groupId, itemCode, serviceContext);
				return Response.status(HttpURLConnection.HTTP_OK).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictItem dictItem = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictItem.getMetaData()).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(409).entity(error).build();

		}
	}

	@Override
	@Deprecated
	public Response deleteMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode, String key,
			String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getMetaDataOfDictItemByKey(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode, String key) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictItem dictItem = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			JSONObject jsonMetaData;

			String value = "Not Found!";

			try {

				jsonMetaData = JSONFactoryUtil.createJSONObject(dictItem.getMetaData());

				value = jsonMetaData.getString(key);

			} catch (JSONException e) {
				_log.error(e);
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(value).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(409).entity(error).build();

		}
	}

	@Override
	public Response updateMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input) {
		return null;
	}

	@Override
	public Response addMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String metaData = HtmlUtil.escape(input.getMetaData());

			DictItem ett = dictItemDataUtil.updateMetaDataByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, metaData);

			if (Validator.isNull(ett)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

			} else {

				return Response.status(HttpURLConnection.HTTP_OK).entity(ett.getMetaData()).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateOrCreateNewDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input, long modifiedDateTime) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemModel dictItemModel = new DictItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictItem oldEtt = null;

			try {
				oldEtt = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);
			} catch (Exception e) {
				_log.error(e);
			}

			String newItemCode = HtmlUtil.escape(input.getItemCode());
			String itemName = HtmlUtil.escape(input.getItemName());
			String itemNameEN = HtmlUtil.escape(input.getItemNameEN());
			String itemDescription = HtmlUtil.escape(input.getItemDescription());
			String parentItemCode = HtmlUtil.escape(input.getParentItemCode());
			String sibling = input.getSibling();
			String metaData = HtmlUtil.escape(input.getMetaData());

			DictItem ett = null;

			if (oldEtt != null) {
				if (modifiedDateTime != 0 && oldEtt.getModifiedDate().compareTo(new Date(modifiedDateTime)) < 0) {
					//Update DictItem
					ett = dictItemDataUtil.updateDictItemByItemCode(user.getUserId(), groupId, serviceContext, code,
							itemCode, newItemCode, itemName, itemNameEN, itemDescription, sibling, parentItemCode);
					List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(groupId,
							ett.getDictItemId());
					if (digList != null && digList.size() > 0) {
						Indexer<DictItemGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemGroup.class);
						for (DictItemGroup dig : digList) {
							indexer.reindex(dig);
						}
					}
				} else {
					throw new DuplicateCategoryException();
				}
			} else {
				//Update DictItem
				ett = dictItemDataUtil.addDictItems(user.getUserId(), groupId, code, parentItemCode, itemCode, itemName,
						itemNameEN, itemDescription, sibling, input.getLevel(), metaData, serviceContext);
				List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(groupId,
						ett.getDictItemId());
				if (digList != null && digList.size() > 0) {
					Indexer<DictItemGroup> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DictItemGroup.class);
					for (DictItemGroup dig : digList) {
						indexer.reindex(dig);
					}
				}
			}

			if (Validator.isNull(ett)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
				error.setDescription("not found!");

				return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

			} else {

				// return json object after update
				dictItemModel = DataManagementUtils.mapperDictItemModel(ett, dictItemDataUtil, user.getUserId(),
						company.getCompanyId(), groupId, serviceContext);

				return Response.status(HttpURLConnection.HTTP_OK).entity(dictItemModel).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateOrCreateNewDictCollection(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, DictCollectionInputModel input,
			long modifiedDateTime) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			String collectionCode = HtmlUtil.escape(input.getCollectionCode());
			String collectionName = HtmlUtil.escape(input.getCollectionName());
			String collectionNameEN = HtmlUtil.escape(input.getCollectionNameEN());
			String description = HtmlUtil.escape(input.getDescription());

			DictCollection oldDictCollection = null;

			try {
				oldDictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);
			} catch (Exception e) {
				_log.error(e);
			}
			DictCollection dictCollection = null;

			if (oldDictCollection != null) {
				if (modifiedDateTime != 0
						&& oldDictCollection.getModifiedDate().compareTo(new Date(modifiedDateTime)) < 0) {
					dictCollection = dictItemDataUtil.updateDictCollection(user.getUserId(), groupId, code,
							collectionCode, collectionName, collectionNameEN, description, serviceContext);
				} else {
					throw new DuplicateCategoryException();
				}
			} else {
				dictCollection = dictItemDataUtil.addDictCollection(user.getUserId(), groupId, code, collectionName,
						collectionNameEN, description, serviceContext);
			}

			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSyncDictCollections(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext,
			org.opencps.api.datamgtsync.model.DataSearchModel query) {
		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (Validator.isNotNull(query.getStart())) {
			start = Integer.valueOf(query.getStart());
		}

		if (Validator.isNotNull(query.getEnd())) {
			end = Integer.valueOf(query.getEnd());
		}
		try {
			Date date = new Date(query.getLastSync());

			org.opencps.api.datamgtsync.model.DictCollectionResults result = new org.opencps.api.datamgtsync.model.DictCollectionResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			List<DictCollection> lstCollections = dictItemDataUtil.getListDictCollectionsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictCollectionsOlderThanDate(user.getUserId(), company.getCompanyId(),
					groupId, date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictCollectionModel().addAll(DataManagementUtils.mapperDictCollectionList(lstCollections));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSyncDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, org.opencps.api.datamgtsync.model.DataSearchModel query) {
		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (Validator.isNotNull(query.getStart())) {
			start = Integer.valueOf(query.getStart());
		}

		if (Validator.isNotNull(query.getEnd())) {
			end = Integer.valueOf(query.getEnd());
		}
		try {
			Date date = new Date(query.getLastSync());

			org.opencps.api.datamgtsync.model.DictItemResults result = new org.opencps.api.datamgtsync.model.DictItemResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			List<DictItem> lstItems = dictItemDataUtil.getListDictItemsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictItemsOlderThanDate(user.getUserId(), company.getCompanyId(), groupId,
					date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictItemModel().addAll(DataManagementUtils.mapperDictItemList(lstItems));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSyncDictGroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, org.opencps.api.datamgtsync.model.DataSearchModel query) {
		// TODO Auto-generated method stub
		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (Validator.isNotNull(query.getStart())) {
			start = Integer.valueOf(query.getStart());
		}

		if (Validator.isNotNull(query.getEnd())) {
			end = Integer.valueOf(query.getEnd());
		}
		try {
			Date date = new Date(query.getLastSync());

			org.opencps.api.datamgtsync.model.DictGroupResults result = new org.opencps.api.datamgtsync.model.DictGroupResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			List<DictGroup> lstGroups = dictItemDataUtil.getListDictGroupsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictGroupsOlderThanDate(user.getUserId(), company.getCompanyId(),
					groupId, date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictGroupModel().addAll(DataManagementUtils.mapperDictGroupList(lstGroups));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateOrCreateNewDictgroups(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode,
			DictGroupInputModel input, long modifiedDateTime) {
		// TODO Auto-generated method stub
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		Groups dictGroupModel = new Groups();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictGroup oldDictGroup = null;

			try {
				oldDictGroup = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);
			} catch (Exception e) {
				_log.error(e);
			}
			DictGroup dictGroup = null;

			if (oldDictGroup != null) {
				if (modifiedDateTime != 0 && oldDictGroup.getModifiedDate().compareTo(new Date(modifiedDateTime)) < 0) {
					dictGroup = dictItemDataUtil.updateDictgroups(user.getUserId(), groupId, code, groupCode,
							input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(),
							input.getGroupDescription(), serviceContext);
				}
				else {
					throw new DuplicateCategoryException();
				}
			} else {
				dictGroup = dictItemDataUtil.addDictgroups(user.getUserId(), groupId, code, groupCode,
						input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), serviceContext);
			}

			dictGroupModel = DataManagementUtils.mapperGroups(dictGroup);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictGroupModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getSyncDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext,
			org.opencps.api.datamgtsync.model.DataSearchModel query) {
		int start = QueryUtil.ALL_POS;
		int end = QueryUtil.ALL_POS;

		if (Validator.isNotNull(query.getStart())) {
			start = Integer.valueOf(query.getStart());
		}

		if (Validator.isNotNull(query.getEnd())) {
			end = Integer.valueOf(query.getEnd());
		}
		try {
			Date date = new Date(query.getLastSync());

			org.opencps.api.datamgtsync.model.DictItemGroupResults result = new org.opencps.api.datamgtsync.model.DictItemGroupResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			List<DictItemGroup> lstItems = dictItemDataUtil.getListDictItemGroupsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictItemGroupsOlderThanDate(user.getUserId(), company.getCompanyId(),
					groupId, date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictItemGroupModel().addAll(DataManagementUtils.mapperDictItemGroupList(lstItems));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDictgroup(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String groupCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		DictGroup dictGroup = dictItemDataUtil.getDictGroupDetail(code, groupCode, groupId);

		if (Validator.isNotNull(dictGroup)) {

			// return json object after update
			DictGroupModel dictGroupModel = DataManagementUtils.mapperDictGroupModel(dictGroup);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictGroupModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription("not found!");

			return Response.status(HttpURLConnection.HTTP_CONFLICT).entity(error).build();

		}
	}

	@Override
	public Response activeDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictCollection dictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);

			if (dictCollection != null) {
				dictCollection = DictCollectionLocalServiceUtil.active(dictCollection.getDictCollectionId());
			}
			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response inActiveDictCollection(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DictCollection dictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);

			if (dictCollection != null) {
				dictCollection = DictCollectionLocalServiceUtil.inactive(dictCollection.getDictCollectionId());
			}
			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(HttpURLConnection.HTTP_OK).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	/** LGSP - START */
	@SuppressWarnings("unchecked")
	@Override
	public Response getDictCollectionLGSP(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, DataSearchModel query, String status) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionResults result = new DictCollectionResults();

		try {

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			//params.put("groupId", String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(ConstantUtils.API_JSON_STATUS, status);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictCollectionLGSP(user.getUserId(), company.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext);

			List<DictCollectionShortModel> dictCollectionList = DataManagementUtils
					.mapperDictCollectionLGSPModelList((List<Document>) jsonData.get("data"));
			if (dictCollectionList != null && dictCollectionList.size() > 0) {
				result.getDictCollectionShortModel().addAll(dictCollectionList);
				result.setTotal(dictCollectionList.size());
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictgroupsLGSP(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupResults result = new DictGroupResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(DictGroupTerm.DICT_COLLECTION_CODE, code);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictgroupsLGSP(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getGroups().addAll(DataManagementUtils.mapperGroupsList((List<Document>) jsonData.get("data")));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictItemsLGSP(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {

		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemResults result = new DictItemResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if (ADMINISTRATIVE_REGION.equalsIgnoreCase(code)) groupId = 0;

			params.put(Field.GROUP_ID, groupId);
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(ConstantUtils.API_JSON_DATA_ITEMLV, query.getLevel());
			params.put(DictItemTerm.PARENT_ITEM_CODE, query.getParent());
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictItemsLGSP(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			List<DictItemModel> dictItemList = DataManagementUtils
					.mapperDictItemModelListLGSP((List<Document>) jsonData.get("data"), code);
			if (dictItemList != null && dictItemList.size() > 0) {
				result.getDictItemModel().addAll(dictItemList);
				result.setTotal(dictItemList.size());
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDictItemMappingSuggest(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, DataSearchModel query,
			Request requestCC) {

		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DVCQGIntegrationActionImpl dvcqgIntegrationActionImpl = new DVCQGIntegrationActionImpl();

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		JSONObject results = JSONFactoryUtil.createJSONObject();

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if ("ADMINISTRATIVE_REGION".equalsIgnoreCase(code))
				groupId = 0;

			params.put("groupId", groupId);
			params.put("keywords", query.getKeywords());
			params.put("itemLv", query.getLevel());
			params.put(DictItemTerm.PARENT_ITEM_CODE, query.getParent());
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);

			Sort[] sorts = null;

			if (Validator.isNull(query.getSort())) {
				sorts = new Sort[] { SortFactoryUtil.create(DictItemTerm.SIBLING_SEARCH + "_Number_sortable",
						Sort.INT_TYPE, false) };
			} else {
				sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };
			}

			JSONObject jsonData = dictItemDataUtil.getDictItems(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);
			
			HashMap<String, String> _dictItemDVCQGMap = dvcqgIntegrationActionImpl.getDictItemDVCQGMap(user, serviceContext,
					query.getService());

			/*if(Validator.isNotNull(query.getService()) && query.getService().equals("LayDanhMucLinhVuc")) {
				if(_domainDictItemDVCQGMap == null) {
					_domainDictItemDVCQGMap = dvcqgIntegrationActionImpl.getDictItemDVCQGMap(user, serviceContext,
							query.getService());
					_dictItemDVCQGMap = _domainDictItemDVCQGMap;
				}
			}
			
			if(Validator.isNotNull(query.getService()) && query.getService().equals("LayDanhMucCoQuan")) {
				if(_govAgencyDictItemDVCQGMap == null) {
					_govAgencyDictItemDVCQGMap = dvcqgIntegrationActionImpl.getDictItemDVCQGMap(user, serviceContext,
							query.getService());
					_dictItemDVCQGMap = _govAgencyDictItemDVCQGMap;
				}
			}
			*/
			

			List<Document> documents = (List<Document>) jsonData.get("data");
			long collectionId = 0;
			if (documents != null) {
				collectionId = GetterUtil.getLong(documents.get(0).get(DictItemTerm.DICT_COLLECTION_ID));
			}
			List<DictItemMapping> dictItemMappings = DictItemMappingLocalServiceUtil.findByF_GID_CID(groupId,
					collectionId);
			List<String> mappedCode = new ArrayList<String>();

			if (dictItemMappings != null) {
				for (DictItemMapping dictItemMapping : dictItemMappings) {
					mappedCode.add(dictItemMapping.getItemCodeDVCQG());
				}
			}

			JSONArray data = JSONFactoryUtil.createJSONArray();

			for (Document document : documents) {
				JSONObject item = JSONFactoryUtil.createJSONObject();
				item.put("dictItemId", Long.valueOf(document.get("entryClassPK")));

				item.put("itemCode", document.get(DictItemTerm.ITEM_CODE));
				item.put("itemName", document.get(DictItemTerm.ITEM_NAME));
				item.put("itemNameEN", document.get(DictItemTerm.ITEM_NAME_EN));
				item.put("itemDescription", document.get(DictItemTerm.ITEM_DESCRIPTION));
				item.put("level", Integer.valueOf(document.get(DictItemTerm.LEVEL)));
				item.put("sibling", Integer.valueOf(document.get(DictItemTerm.LEVEL)));
				item.put("treeIndex", document.get(DictItemTerm.TREE_INDEX));

				item.put("createDate", Validator.isNotNull(document.get(DictItemTerm.CREATE_DATE))
						? APIDateTimeUtils.convertDateToString(document.getDate(DictItemTerm.CREATE_DATE),
								APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				item.put("modifiedDate",
						Validator.isNotNull(document.get("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				JSONArray mapping = dvcqgIntegrationActionImpl.getDictItemSimilarity(groupId, code,
						document.get(DictItemTerm.ITEM_CODE), document.get(DictItemTerm.ITEM_NAME), _dictItemDVCQGMap,
						mappedCode);
				item.put("similarity", mapping);
				data.put(item);
			}

			results.put("total", jsonData.getInt("total"));
			results.put("data", data);

			return Response.status(200).entity(results.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private static HashMap<String, String> _domainDictItemDVCQGMap = null;
	private static HashMap<String, String> _govAgencyDictItemDVCQGMap = null;
	public static String _service = StringPool.BLANK;

	@Override
	public Response doMappingDictItemDVCQG(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			String itemCodeDVCQG) {
		boolean result = false;
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			DictCollection collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);

			if (collection == null) {
				result = false;
			}

			DictItemMapping dictItemMapping = DictItemMappingLocalServiceUtil.fetchByF_GID_IC_CID(groupId, itemCode,
					collection.getDictCollectionId());

			if (dictItemMapping != null) {
				DictItemMappingLocalServiceUtil.deleteDictItemMapping(dictItemMapping);
			}

			dictItemMapping = DictItemMappingLocalServiceUtil.fetchByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG,
					collection.getDictCollectionId());

			if (dictItemMapping != null) {
				DictItemMappingLocalServiceUtil.deleteDictItemMapping(dictItemMapping);
			}

			DictItemMappingLocalServiceUtil.createDictItemMapping(serviceContext.getCompanyId(), groupId,
					user.getUserId(), itemCode, itemCodeDVCQG, collection.getDictCollectionId());

			result = true;

			return Response.status(200).entity(String.valueOf(result)).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response doRemoveMappingDictItemDVCQG(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, long mappingId) {
		boolean result = false;

		try {

			DictItemMappingLocalServiceUtil.deleteDictItemMapping(mappingId);

			result = true;

			return Response.status(200).entity(String.valueOf(result)).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDictItemDVCQG(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query, Request requestCC) {
		DVCQGIntegrationActionImpl dvcqgIntegrationActionImpl = new DVCQGIntegrationActionImpl();

		try {
			JSONObject result = dvcqgIntegrationActionImpl.getDictItemDVCQG(user, serviceContext, query.getService());

			return Response.status(200).entity(String.valueOf(result)).build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}
}
