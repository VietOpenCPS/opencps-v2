package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.DataTempManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.DataTempManagementUtils;
import org.opencps.api.datatempmgt.model.DataSearchModel;
import org.opencps.api.datatempmgt.model.DictCollectionTempInputModel;
import org.opencps.api.datatempmgt.model.DictCollectionTempModel;
import org.opencps.api.datatempmgt.model.DictCollectionTempResults;
import org.opencps.api.datatempmgt.model.DictGroupItemTempModel;
import org.opencps.api.datatempmgt.model.DictGroupItemTempResults;
import org.opencps.api.datatempmgt.model.DictGroupTempInputModel;
import org.opencps.api.datatempmgt.model.DictGroupTempModel;
import org.opencps.api.datatempmgt.model.DictGroupTempResults;
import org.opencps.api.datatempmgt.model.DictItemTempInputModel;
import org.opencps.api.datatempmgt.model.DictItemTempResults;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.synchronization.action.DictCollectionTempInterface;
import org.opencps.synchronization.action.impl.DictCollectionActions;
import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
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

public class DataTempManagementImpl implements DataTempManagement {

	Log _log = LogFactoryUtil.getLog(DataTempManagementImpl.class);

	@Override
	public Response getDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		
		DictCollectionTempResults result = new DictCollectionTempResults();

		try {

			if (query.getEnd() == 0) {
				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			_log.info("groupId: " + groupId);
			_log.info("keywords: " + query.getKeywords());

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictCollectionTemp(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictCollectionTempModel().addAll(
					DataTempManagementUtils.mapperDictCollectionTempModelList((List<Document>) jsonData.get("data")));

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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictCollectionTemp dictCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);

		if (Validator.isNotNull(dictCollection)) {

			// return json object after update
			DictCollectionTempModel dictCollectionModel = DataTempManagementUtils.mapperDictCollectionTempModel(dictCollection);

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
			User user, ServiceContext serviceContext, DictCollectionTempInputModel input) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionTempModel dictCollectionModel = new DictCollectionTempModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollectionTemp dictCollection = dictItemDataUtil.addDictCollectionTemp(user.getUserId(), groupId,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), 
					input.getStatus(),
					input.getMustSync(),
					serviceContext);

			// return json object after update
			dictCollectionModel = DataTempManagementUtils.mapperDictCollectionTempModel(dictCollection);

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
			User user, ServiceContext serviceContext, String code, DictCollectionTempInputModel input) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionTempModel dictCollectionModel = new DictCollectionTempModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollectionTemp dictCollection = dictItemDataUtil.updateDictCollectionTemp(user.getUserId(), groupId, code,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), 
					input.getStatus(),
					input.getMustSync(),
					serviceContext);

			dictCollectionModel = DataTempManagementUtils.mapperDictCollectionTempModel(dictCollection);

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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = dictItemDataUtil.deleteDictCollectionTemp(code, groupId, serviceContext);

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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictCollectionTemp dictCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);

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
			User user, ServiceContext serviceContext, String code, String dataform, long modifiedDateTime) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollectionTemp dictCollection = dictItemDataUtil.addDataForm(user.getUserId(), groupId, code, dataform,
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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupTempResults result = new DictGroupTempResults();
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

			JSONObject jsonData = dictItemDataUtil.getDictGroupsTemp(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictGroupTempModel().addAll(DataTempManagementUtils.mapperGroupsList((List<Document>) jsonData.get("data")));

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
			User user, ServiceContext serviceContext, String code, DictGroupTempInputModel input) {
		// TODO Auto-generated method stub
		DictGroupTempModel dictGroupModel = new DictGroupTempModel();
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictGroupTemp dictGroup = dictItemDataUtil.addDictGroupsTemp(user.getUserId(), groupId, code, input.getGroupCode(),
					input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), input.getStatus(), serviceContext);

			// return json object after update
			dictGroupModel = DataTempManagementUtils.mapperGroups(dictGroup);

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
			User user, ServiceContext serviceContext, String code, String groupCode, DictGroupTempInputModel input) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupTempModel dictGroupModel = new DictGroupTempModel();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictGroupTemp dictGroup = dictItemDataUtil.updateDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
					input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
					input.getStatus(),
					serviceContext);
			
			dictGroupModel = DataTempManagementUtils.mapperGroups(dictGroup);

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
			User user, ServiceContext serviceContext, String code, String groupCode) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			
			boolean flag = dictItemDataUtil.deleteDictGroupsTemp(groupCode, groupId, serviceContext);
			
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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());
		DictGroupItemTempResults result = new DictGroupItemTempResults();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put(DictItemTempTerm.DICT_COLLECTION_CODE, code);
			params.put(DictGroupTempTerm.GROUP_CODE, groupCode);

			JSONObject jsonData = dictItemDataUtil.getDictGroupsDictItemsTemp(user.getUserId(), company.getCompanyId(),
					groupId, code, groupCode, full, params, new Sort[] {}, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictGroupItemModel()
					.addAll(DataTempManagementUtils.mapperDictGroupItemTempModelList((List<Document>) jsonData.get("data")));

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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupItemTempModel dictGroupItemModel = new DictGroupItemTempModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItemGroupTemp dictItemGroup = dictItemDataUtil.addDictGroupsDictItemsTemp(user.getUserId(), groupId, code,
					groupCode, itemCode, serviceContext);

			dictGroupItemModel = DataTempManagementUtils.mapperDictGroupItemTempModel(dictItemGroup);
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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = dictItemDataUtil.deleteDictGroupsDictItemsTemp(groupId, code, groupCode, itemCode,
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
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictItemTempResults result = new DictItemTempResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if (code.equalsIgnoreCase("ADMINISTRATIVE_REGION"))
				groupId = 0;

			params.put("groupId", groupId);
			params.put("keywords", query.getKeywords());
			params.put("itemLv", query.getLevel());
			params.put(DictItemTerm.PARENT_ITEM_CODE, query.getParent());
			params.put(DictItemTerm.DICT_COLLECTION_CODE, code);

			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE, false) };

			JSONObject jsonData = dictItemDataUtil.getDictItemsTemp(user.getUserId(), company.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getDictItemTempModel()
					.addAll(DataTempManagementUtils.mapperDictItemTempModelList((List<Document>) jsonData.get("data")));

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
			User user, ServiceContext serviceContext, String code, DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response dateletDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getMetaDataOfDictItemByKey(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode, String key,
			String body) {
		// TODO Auto-generated method stub
		return null;
	}
}
