package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DataManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DataManagementUtils;
import org.opencps.api.datamgt.model.DataSearchModel;
import org.opencps.api.datamgt.model.DictCollectionInputModel;
import org.opencps.api.datamgt.model.DictCollectionModel;
import org.opencps.api.datamgt.model.DictCollectionResults;
import org.opencps.api.datamgt.model.DictGroupInputModel;
import org.opencps.api.datamgt.model.DictGroupItemModel;
import org.opencps.api.datamgt.model.DictGroupItemResults;
import org.opencps.api.datamgt.model.DictGroupResults;
import org.opencps.api.datamgt.model.DictItemInputModel;
import org.opencps.api.datamgt.model.DictItemModel;
import org.opencps.api.datamgt.model.DictItemResults;
import org.opencps.api.datamgt.model.Groups;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;


public class DataManagementImpl implements DataManagement {

	Log _log = LogFactoryUtil.getLog(DataManagementImpl.class);

	@Override
	public Response getDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionResults result = new DictCollectionResults();

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictCollection(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictCollectionShortModel().addAll(
					DataManagementUtils.mapperDictCollectionShortModelList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response getDictCollectionDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictCollection dictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);

		if (Validator.isNotNull(dictCollection)) {

			// return json object after update
			DictCollectionModel dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(200).entity(dictCollectionModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(409).entity(error).build();

		}
	}

	@Override
	public Response addDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DictCollectionInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollection dictCollection = dictItemDataUtil.addDictCollection(user.getUserId(), groupId,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), serviceContext);

			// return json object after update
			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(200).entity(dictCollectionModel).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response updateDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictCollectionInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollection dictCollection = dictItemDataUtil.updateDictCollection(user.getUserId(), groupId, code,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), serviceContext);

			dictCollectionModel = DataManagementUtils.mapperDictCollectionModel(dictCollection);

			return Response.status(200).entity(dictCollectionModel).build();

		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response deleteDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = dictItemDataUtil.deleteDictCollection(code, groupId, serviceContext);

			if (flag) {

				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {

			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NotFoundException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response getDataForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictCollection dictCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);

		if (Validator.isNotNull(dictCollection)) {

			// return json object after update
			return Response.status(200).entity(dictCollection.getDataForm()).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response addDataForm(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String dataform) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollection dictCollection = dictItemDataUtil.addDataForm(user.getUserId(), groupId, code, dataform,
					serviceContext);

			return Response.status(200).entity(dictCollection.getDataForm()).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response getDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupResults result = new DictGroupResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put(DictGroupTerm.DICT_COLLECTION_CODE, code);
			
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictgroups(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getGroups().addAll(DataManagementUtils.mapperGroupsList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictGroupInputModel input) {
		Groups dictGroupModel = new Groups();
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictGroup dictGroup = dictItemDataUtil.addDictgroups(user.getUserId(), groupId, code, input.getGroupCode(),
					input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), serviceContext);

			// return json object after update
			dictGroupModel = DataManagementUtils.mapperGroups(dictGroup);

			return Response.status(200).entity(dictGroupModel).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response updateDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String groupCode, DictGroupInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		Groups dictGroupModel = new Groups();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictGroup dictGroup = dictItemDataUtil.updateDictgroups(user.getUserId(), groupId, code, groupCode,
					input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
					serviceContext);

			dictGroupModel = DataManagementUtils.mapperGroups(dictGroup);

			return Response.status(200).entity(dictGroupModel).build();

		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response deleteDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String groupCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = dictItemDataUtil.deleteDictgroups(groupCode, groupId, serviceContext);

			if (flag) {

				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {

			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NotFoundException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response getDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, boolean full) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());
		DictGroupItemResults result = new DictGroupItemResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);
			params.put(DictGroupTerm.GROUP_CODE, groupCode);
			
			JSONObject jsonData = dictItemDataUtil.getDictgroupsDictItems(user.getUserId(), company.getCompanyId(),
					groupId, code, groupCode, full, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictGroupItemModel()
					.addAll(DataManagementUtils.mapperDictGroupItemModelList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupItemModel dictGroupItemModel = new DictGroupItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItemGroup dictItemGroup = dictItemDataUtil.addDictgroupsDictItems(user.getUserId(), groupId, code,
					groupCode, itemCode, serviceContext);

			dictGroupItemModel = DataManagementUtils.mapperDictGroupItemModel(dictItemGroup);
			dictGroupItemModel.setSelected(Boolean.TRUE);

			return Response.status(200).entity(dictGroupItemModel).build();

		} catch (Exception e) {
			_log.error("@POST: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response deleteDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = dictItemDataUtil.deleteDictgroupsDictItems(groupId, code, groupCode, itemCode,
					serviceContext);

			if (flag) {

				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {
			_log.error("@DELETE: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NotFoundException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response getDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemResults result = new DictItemResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());
			params.put("itemLv", query.getLevel());
			params.put(DictItemTerm.PARENT_ITEM_CODE, query.getParent());
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);
			
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictItems(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictItemModel()
					.addAll(DataManagementUtils.mapperDictItemModelList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error("@GET: " + e);
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response addDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictItemInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemModel dictItemModel = new DictItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem dictItem = dictItemDataUtil.addDictItems(user.getUserId(), groupId, code,
					input.getParentItemCode(), input.getItemCode(), input.getItemName(), input.getItemNameEN(),
					input.getItemDescription(), input.getSibling(), input.getLevel(), input.getMetaData(),
					serviceContext);

			// return json object after update
			dictItemModel = DataManagementUtils.mapperDictItemModel(dictItem, dictItemDataUtil, user.getUserId(),
					company.getCompanyId(), groupId, serviceContext);

			return Response.status(200).entity(dictItemModel).build();

		} catch (Exception e) {
			_log.error(e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response getDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictItem dictItem = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			DictItemModel dictItemModel = DataManagementUtils.mapperDictItemModel(dictItem, dictItemDataUtil,
					user.getUserId(), company.getCompanyId(), groupId, serviceContext);

			return Response.status(200).entity(dictItemModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(409).entity(error).build();

		}
	}

	@Override
	public Response updateDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemModel dictItemModel = new DictItemModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem ett = dictItemDataUtil.updateDictItemByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(), input.getItemDescription(),
					input.getSibling(), input.getParentItemCode());

			if (Validator.isNull(ett)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			} else {

				// return json object after update
				dictItemModel = DataManagementUtils.mapperDictItemModel(ett, dictItemDataUtil, user.getUserId(),
						company.getCompanyId(), groupId, serviceContext);

				return Response.status(200).entity(dictItemModel).build();

			}

		} catch (Exception e) {
			_log.info(e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}
			
			if (e instanceof DuplicateCategoryException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}
			
			return Response.status(500).build();
		}
	}

	@Override
	public Response dateletDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem dictColl = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

			if (Validator.isNull(dictColl)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			} else {

				DictItemLocalServiceUtil.deleteDictItem(groupId, itemCode, serviceContext);

				return Response.status(200).build();

			}

		} catch (Exception e) {
			_log.error("@DELETE: " + e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NotFoundException) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

	@Override
	public Response getMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictItem dictItem = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			return Response.status(200).entity(dictItem.getMetaData()).build();

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

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictItem dictItem = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			JSONObject jsonMetaData;

			String value = "Not Found!";

			try {

				jsonMetaData = JSONFactoryUtil.createJSONObject(dictItem.getMetaData());

				value = jsonMetaData.getString(key);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return Response.status(200).entity(value).build();

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem ett = dictItemDataUtil.updateMetaDataByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, input.getMetaData());

			if (Validator.isNull(ett)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			} else {

				return Response.status(200).entity(ett.getMetaData()).build();

			}

		} catch (Exception e) {
			_log.info(e);
			if (e instanceof UnauthenticationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("authentication failed!");
				error.setCode(401);
				error.setDescription("authentication failed!");

				return Response.status(401).entity(error).build();

			}

			if (e instanceof UnauthorizationException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("permission denied!");
				error.setCode(403);
				error.setDescription("permission denied!");

				return Response.status(403).entity(error).build();

			}

			if (e instanceof NoSuchUserException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("conflict!");
				error.setCode(409);
				error.setDescription("conflict!");

				return Response.status(409).entity(error).build();

			}

			return Response.status(500).build();
		}
	}

}
