package org.opencps.api.controller.impl;

import java.util.Date;
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
import org.opencps.api.dictcollection.model.DictGroupModel;
import org.opencps.auth.api.exception.DataInUsedException;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
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
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.synchronization.action.DictCollectionTempInterface;
import org.opencps.synchronization.action.PushDictGroupInterface;
import org.opencps.synchronization.action.PushDictItemInterface;
import org.opencps.synchronization.action.impl.PushDictGroupActions;
import org.opencps.synchronization.action.impl.PushDictItemActions;
import org.opencps.synchronization.constants.DataMGTTempConstants;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictItemTempLocalServiceUtil;

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
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollection dictCollection = dictItemDataUtil.addDictCollection(user.getUserId(), groupId,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), serviceContext);

			DictCollectionTemp oldCollectionTemp = dictItemDataTempUtil.getDictCollectionTempDetail(input.getCollectionCode(), groupId);
			
			if (oldCollectionTemp == null) {
				dictItemDataTempUtil.addDictCollectionTemp(user.getUserId(), groupId,
						input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
						input.getDescription(), DataMGTTempConstants.DATA_STATUS_ACTIVE, DataMGTTempConstants.DATA_MUST_SYNCHRONIZED, serviceContext);				
			}
			else {
				dictItemDataTempUtil.updateDictCollectionTemp(user.getUserId(), groupId, input.getCollectionCode(), input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(), input.getDescription(), DataMGTTempConstants.DATA_STATUS_ACTIVE, DataMGTTempConstants.DATA_MUST_SYNCHRONIZED, serviceContext);
			}
			
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
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			_log.info("Update dict collection: " + code);
			
			DictCollection dictCollection = dictItemDataUtil.updateDictCollection(user.getUserId(), groupId, code,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), serviceContext);

			DictCollectionTemp temp = dictItemDataTempUtil.getDictCollectionTempDetail(code, groupId);
			if (temp != null) {
				dictItemDataTempUtil.updateDictCollectionTemp(user.getUserId(), groupId, code,
						input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
						input.getDescription(), 
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						DataMGTTempConstants.DATA_MUST_SYNCHRONIZED,
						serviceContext);				
			}
			else {
				dictItemDataTempUtil.addDictCollectionTemp(user.getUserId(), groupId, code,
						input.getCollectionName(), input.getCollectionNameEN(),
						input.getDescription(), 
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						DataMGTTempConstants.DATA_MUST_SYNCHRONIZED,
						serviceContext);								
			}

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
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			boolean flag = dictItemDataUtil.deleteDictCollection(code, groupId, serviceContext);
			
			DictCollectionTemp temp = dictItemDataTempUtil.getDictCollectionTempDetail(code, groupId);
			if (temp != null) {
				dictItemDataTempUtil.deleteDictCollectionTemp(code, groupId, serviceContext);				
			}
			
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
			User user, ServiceContext serviceContext, String code, String dataform,
			long modifiedDateTime) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollection oldCollection = dictItemDataUtil.getDictCollectionDetail(code, groupId);
			
			if (modifiedDateTime == 0 || (modifiedDateTime != 0 && oldCollection.getModifiedDate().compareTo(new Date(modifiedDateTime)) < 0)) {
				DictCollection dictCollection = dictItemDataUtil.addDataForm(user.getUserId(), groupId, code, dataform,
						serviceContext);
				if (dictCollection != null) {
					dictItemDataTempUtil.addDataForm(user.getUserId(), groupId, code, dataform,
							serviceContext);					
				}

				return Response.status(200).entity(dictCollection.getDataForm()).build();				
			}
			else {
				throw new DuplicateCategoryException();
			}
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
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictGroup dictGroup = dictItemDataUtil.addDictgroups(user.getUserId(), groupId, code, input.getGroupCode(),
					input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), serviceContext);
			
			DictGroupTemp temp = dictItemDataTempUtil.getDictGroupTempDetail(code, input.getGroupCode(), groupId);
			if (temp == null) {
				dictItemDataTempUtil.addDictGroupsTemp(user.getUserId(), groupId, code, input.getGroupCode(),
						input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), 
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						serviceContext);				
			}
			else {
				dictItemDataTempUtil.updateDictGroupsTemp(user.getUserId(), groupId, code, 
						input.getGroupCode(),
						input.getGroupCode(),
						input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), 
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						serviceContext);								
			}

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
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictGroup dictGroup = dictItemDataUtil.updateDictgroups(user.getUserId(), groupId, code, groupCode,
					input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
					serviceContext);
			
			DictGroupTemp temp = dictItemDataTempUtil.getDictGroupTempDetail(code, groupCode, groupId);
			if (temp != null) {
				dictItemDataTempUtil.updateDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
						input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						serviceContext);				
			}
			else {
				dictItemDataTempUtil.addDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
						input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						serviceContext);								
			}

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
			User user, ServiceContext serviceContext, String code, String groupCode) {
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			
//			boolean flag = dictItemDataUtil.deleteDictgroups(groupCode, groupId, serviceContext);
			boolean flag = dictItemDataUtil.deleteDictgroups(code, groupCode, groupId, serviceContext);
			
			DictGroupTemp temp = dictItemDataTempUtil.getDictGroupTempDetail(code, groupCode, groupId);
			if (temp != null) {
				dictItemDataTempUtil.deleteDictGroupsTemp(groupCode, groupId, serviceContext);				
			}
			
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

			if (e instanceof DataInUsedException) {

				_log.error("@POST: " + e);
				ErrorMsg error = new ErrorMsg();

				error.setMessage("data is in used!");
				error.setCode(406);
				error.setDescription("data is in used!");

				return Response.status(406).entity(error).build();

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
		PushDictGroupInterface pushDictGroupUtil = new PushDictGroupActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItemGroup dictItemGroup = dictItemDataUtil.addDictgroupsDictItems(user.getUserId(), groupId, code,
					groupCode, itemCode, serviceContext);

			try {
				dictItemDataTempUtil.addDictGroupsDictItemsTemp(user.getUserId(), groupId, code,
						groupCode, itemCode, serviceContext);
			}
			catch (DuplicateCategoryException e) {
				e.printStackTrace();
			}
			try {
				List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);

				DictGroup group = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(groupCode, groupId);

				for (ServerConfig sc : lstServers) {
					String configs = sc.getConfigs();
					if (Validator.isNotNull(configs)) {
						try {
							JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
							if (configObj.has(SyncServerTerm.SERVER_TYPE)
									&& configObj.getString(SyncServerTerm.SERVER_TYPE)
											.equals(SyncServerTerm.SYNC_SERVER_TYPE)
									&& configObj.has(SyncServerTerm.SERVER_USERNAME)
									&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									pushDictGroupUtil.addPushDictGroup(user.getUserId(), groupId, 
											sc.getServerNo(),
											code, groupCode,
											group.getGroupName(), group.getGroupNameEN(), group.getGroupDescription(),
											itemCode, SyncServerTerm.METHOD_ADD_TO_GROUP, serviceContext);
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
					}
				}
			} catch (Exception e) {
				_log.error(e);
			}

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
		PushDictGroupInterface pushDictGroupUtil = new PushDictGroupActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollection collection = null;
			try {
				collection = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(code, groupId);
			} catch (Exception e) {

			}
			DictGroup group = null;
			try {
				if (collection != null) {
					group = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, collection.getDictCollectionId());					
				}
			} catch (Exception e) {

			}
			DictItem item = null;

			try {
				if (collection != null)
					item = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, collection.getDictCollectionId(),
							groupId);
			} catch (Exception e) {

			}
			DictItemGroup dictItemGroup = null;

			try {
				if (collection != null && group != null && item != null)
					dictItemGroup = DictItemGroupLocalServiceUtil.fetchByF_dictItemId_dictGroupId(groupId,
							group.getDictGroupId(), item.getDictItemId());
			} catch (Exception e) {

			}
			boolean flag = dictItemDataUtil.deleteDictgroupsDictItems(groupId, code, groupCode, itemCode,
					serviceContext);
			
			try {
				dictItemDataTempUtil.deleteDictGroupsDictItemsTemp(groupId, code, groupCode, itemCode,
						serviceContext);
			}
			catch (NotFoundException e) {
				e.printStackTrace();
			}
			try {
				List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);

				if (group != null) {
					for (ServerConfig sc : lstServers) {
						String configs = sc.getConfigs();
						if (Validator.isNotNull(configs)) {
							try {
								JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
								if (configObj.has(SyncServerTerm.SERVER_TYPE)
										&& configObj.getString(SyncServerTerm.SERVER_TYPE)
												.equals(SyncServerTerm.SYNC_SERVER_TYPE)
										&& configObj.has(SyncServerTerm.SERVER_USERNAME)
										&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
										&& configObj.has(SyncServerTerm.SERVER_URL)
										&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
									if (groupId == sc.getGroupId()) {
										if (dictItemGroup != null)
											pushDictGroupUtil.addPushDictGroup(user.getUserId(), groupId, 
													sc.getServerNo(),
													code,
													groupCode, group.getGroupName(), group.getGroupNameEN(),
													group.getGroupDescription(), itemCode,
													SyncServerTerm.METHOD_REMOVE_FROM_GROUP, serviceContext);
									}
								}
							} catch (Exception e) {
								_log.error(e);
							}
						}
					}
				}
			} catch (Exception e) {
				_log.error(e);
			}

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

			if (code.equalsIgnoreCase("ADMINISTRATIVE_REGION"))
				groupId = 0;

			params.put("groupId", groupId);
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
		PushDictItemInterface pushDictItemUtil = new PushDictItemActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem dictItem = dictItemDataUtil.addDictItems(user.getUserId(), groupId, code,
					input.getParentItemCode(), input.getItemCode(), input.getItemName(), input.getItemNameEN(),
					input.getItemDescription(), input.getSibling(), input.getLevel(), input.getMetaData(),
					serviceContext);
			DictItemTemp temp = dictItemDataTempUtil.getDictItemTempByItemCode(code, input.getItemCode(), groupId, serviceContext);
			if (temp == null) {
				dictItemDataTempUtil.addDictItemsTemp(user.getUserId(), groupId, code,
						input.getParentItemCode(), input.getItemCode(), input.getItemName(), input.getItemNameEN(),
						input.getItemDescription(), input.getSibling(), input.getLevel(), input.getMetaData(),
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						serviceContext);				
			}
			else {
				dictItemDataTempUtil.updateDictItemTempByItemCode(
						user.getUserId(), groupId, 
						serviceContext,
						code,
						input.getItemCode(), 
						input.getItemCode(),
						input.getItemName(), input.getItemNameEN(),
						input.getItemDescription(), input.getSibling(),
						input.getParentItemCode(),
						DataMGTTempConstants.DATA_STATUS_ACTIVE);								
			}

			try {
				List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);
				DictItem item = dictItemDataUtil.getDictItemByItemCode(code, input.getItemCode(), groupId,
						serviceContext);

				for (ServerConfig sc : lstServers) {
					String configs = sc.getConfigs();
					if (Validator.isNotNull(configs)) {
						try {
							JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
							if (configObj.has(SyncServerTerm.SERVER_TYPE)
									&& configObj.getString(SyncServerTerm.SERVER_TYPE)
											.equals(SyncServerTerm.SYNC_SERVER_TYPE)
									&& configObj.has(SyncServerTerm.SERVER_USERNAME)
									&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									pushDictItemUtil.addPushDictItem(user.getUserId(), groupId, 
											sc.getServerNo(),
											code,
											input.getItemCode(), item.getItemName(), item.getItemNameEN(),
											item.getItemDescription(), input.getParentItemCode(), item.getSibling(),
											SyncServerTerm.METHOD_CREATE, "", serviceContext);
								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
					}
				}
			} catch (Exception e) {
				_log.error(e);
			}

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
		PushDictItemInterface pushDictItemUtil = new PushDictItemActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem ett = dictItemDataUtil.updateDictItemByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(),
					input.getItemDescription(), input.getSibling(), input.getParentItemCode());

			DictItemTemp temp = dictItemDataTempUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);
			if (temp != null) {
				dictItemDataTempUtil.updateDictItemTempByItemCode(user.getUserId(), groupId, serviceContext, code,
						itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(),
						input.getItemDescription(), input.getSibling(), input.getParentItemCode(), DataMGTTempConstants.DATA_STATUS_ACTIVE);				
			}
			else {
				dictItemDataTempUtil.addDictItemsTemp(user.getUserId(), groupId, code,
						input.getParentItemCode(), input.getItemCode(), input.getItemName(), input.getItemNameEN(),
						input.getItemDescription(), input.getSibling(), input.getLevel(), input.getMetaData(),
						DataMGTTempConstants.DATA_STATUS_ACTIVE,
						serviceContext);				
			}

			List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);
			for (ServerConfig sc : lstServers) {
				String configs = sc.getConfigs();
				if (Validator.isNotNull(configs)) {
					try {
						JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
						if (configObj.has(SyncServerTerm.SERVER_TYPE)
								&& configObj.getString(SyncServerTerm.SERVER_TYPE)
										.equals(SyncServerTerm.SYNC_SERVER_TYPE)
								&& configObj.has(SyncServerTerm.SERVER_USERNAME)
								&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
								&& configObj.has(SyncServerTerm.SERVER_URL)
								&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
							if (groupId == sc.getGroupId()) {
								pushDictItemUtil.addPushDictItem(user.getUserId(), groupId, 
										sc.getServerNo(),
										code, itemCode,
										input.getItemName(), input.getItemNameEN(), input.getItemDescription(),
										input.getParentItemCode(), input.getSibling(), SyncServerTerm.METHOD_UPDATE, "",
										serviceContext);
							}
						}
					} catch (Exception e) {
						_log.error(e);
					}
				}
			}

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
		PushDictItemInterface pushDictItemUtil = new PushDictItemActions();

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
				DictItem item = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);

				DictItemLocalServiceUtil.deleteDictItem(groupId, itemCode, serviceContext);
				try {
					DictItemTempLocalServiceUtil.deleteDictItemTemp(groupId, itemCode, serviceContext);
				}
				catch (NotFoundException e) {
					e.printStackTrace();
				}
				try {
					List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
							QueryUtil.ALL_POS);

					for (ServerConfig sc : lstServers) {
						String configs = sc.getConfigs();
						if (Validator.isNotNull(configs)) {
							try {
								JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
								if (configObj.has(SyncServerTerm.SERVER_TYPE)
										&& configObj.getString(SyncServerTerm.SERVER_TYPE)
												.equals(SyncServerTerm.SYNC_SERVER_TYPE)
										&& configObj.has(SyncServerTerm.SERVER_USERNAME)
										&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
										&& configObj.has(SyncServerTerm.SERVER_URL)
										& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
									if (groupId == sc.getGroupId()) {
										pushDictItemUtil.addPushDictItem(user.getUserId(), groupId, 
												sc.getServerNo(),
												code, itemCode,
												item.getItemName(), item.getItemNameEN(), item.getItemDescription(), "",
												item.getSibling(), SyncServerTerm.METHOD_DELETE, "", serviceContext);
									}
								}
							} catch (Exception e) {
								_log.error(e);
							}
						}
					}
				} catch (Exception e) {
					_log.error(e);
				}
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
		PushDictItemInterface pushDictItemUtil = new PushDictItemActions();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem ett = dictItemDataUtil.updateMetaDataByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, input.getMetaData());
			
			try {
			dictItemDataTempUtil.updateMetaDataByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, input.getMetaData());
			}
			catch (DuplicateCategoryException | NotFoundException e) {
				e.printStackTrace();
			}
			DictItem parentEtt = null;
			try {
				parentEtt = DictItemLocalServiceUtil.fetchDictItem(ett.getParentItemId());
			} catch (Exception e) {

			}
			try {
				List<ServerConfig> lstServers = ServerConfigLocalServiceUtil.getServerConfigs(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);
				String parentItemCode = (Validator.isNotNull(parentEtt) ? parentEtt.getItemCode() : null);
				for (ServerConfig sc : lstServers) {
					String configs = sc.getConfigs();
					if (Validator.isNotNull(configs)) {
						try {
							JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
							if (configObj.has(SyncServerTerm.SERVER_TYPE)
									&& configObj.getString(SyncServerTerm.SERVER_TYPE)
											.equals(SyncServerTerm.SYNC_SERVER_TYPE)
									&& configObj.has(SyncServerTerm.SERVER_USERNAME)
									&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									pushDictItemUtil.addPushDictItem(sc.getUserId(), groupId, 
											sc.getServerNo(),
											code, itemCode,
											ett.getItemName(), ett.getItemNameEN(), ett.getItemDescription(),
											parentItemCode, ett.getSibling(), SyncServerTerm.METHOD_UPDATE_METADATA,
											input.getMetaData(), serviceContext);

								}
							}
						} catch (Exception e) {
							_log.error(e);
						}
					}
				}
			} catch (Exception e) {
				_log.error(e);
			}

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

	@Override
	public Response updateOrCreateNewDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemInputModel input, long modifiedDateTime) {
		// TODO Auto-generated method stub
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictItemModel dictItemModel = new DictItemModel();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItem oldEtt = null;

			try {
				oldEtt = dictItemDataUtil.getDictItemByItemCode(code, itemCode, groupId, serviceContext);
			} catch (Exception e) {

			}

			DictItem ett = null;

			if (oldEtt != null) {
				if (modifiedDateTime != 0 && oldEtt.getModifiedDate().compareTo(new Date(modifiedDateTime)) < 0) {
					ett = dictItemDataUtil.updateDictItemByItemCode(user.getUserId(), groupId, serviceContext, code,
							itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(),
							input.getItemDescription(), input.getSibling(), input.getParentItemCode());
					DictItemTemp temp = dictItemDataTempUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);
					if (temp != null) {
						dictItemDataTempUtil.updateDictItemTempByItemCode(user.getUserId(), groupId, serviceContext, code,
								itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(),
								input.getItemDescription(), input.getSibling(), input.getParentItemCode(), DataMGTTempConstants.DATA_STATUS_ACTIVE);							
					}
					else {
						dictItemDataTempUtil.addDictItemsTemp(user.getUserId(), groupId, code,
								input.getParentItemCode(), input.getItemCode(), input.getItemName(), input.getItemNameEN(),
								input.getItemDescription(), input.getSibling(), input.getLevel(), input.getMetaData(),
								DataMGTTempConstants.DATA_STATUS_ACTIVE,
								serviceContext);										
					}
				}
				else {
					throw new DuplicateCategoryException();
				}
			} else {
				ett = dictItemDataUtil.addDictItems(user.getUserId(), groupId, code, input.getParentItemCode(),
						itemCode, input.getItemName(), input.getItemNameEN(), input.getItemDescription(),
						input.getSibling(), input.getLevel(), input.getMetaData(), serviceContext);
				DictItemTemp temp = dictItemDataTempUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);
				if (temp == null) {
					dictItemDataTempUtil.addDictItemsTemp(user.getUserId(), groupId, code, input.getParentItemCode(),
							itemCode, input.getItemName(), input.getItemNameEN(), input.getItemDescription(),
							input.getSibling(), input.getLevel(), input.getMetaData(), DataMGTTempConstants.DATA_STATUS_ACTIVE, serviceContext);					
				}
				else {
					dictItemDataTempUtil.updateDictItemTempByItemCode(user.getUserId(), groupId, serviceContext, code,
							itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(),
							input.getItemDescription(), input.getSibling(), input.getParentItemCode(), DataMGTTempConstants.DATA_STATUS_ACTIVE);												
				}
			}

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
	public Response updateOrCreateNewDictCollection(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, DictCollectionInputModel input,
			long modifiedDateTime) {
		// TODO Auto-generated method stub
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		DictCollectionModel dictCollectionModel = new DictCollectionModel();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
							input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
							input.getDescription(), serviceContext);
					DictCollectionTemp temp = dictItemDataTempUtil.getDictCollectionTempDetail(code, groupId);
					if (temp != null) {
						dictItemDataTempUtil.updateDictCollectionTemp(user.getUserId(), groupId, code,
								input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
								input.getDescription(), DataMGTTempConstants.DATA_STATUS_ACTIVE, 
								DataMGTTempConstants.DATA_MUST_SYNCHRONIZED,
								serviceContext);						
					}
					else {
						dictItemDataTempUtil.addDictCollectionTemp(user.getUserId(), groupId, code,
								input.getCollectionName(), input.getCollectionNameEN(), input.getDescription(), 
								DataMGTTempConstants.DATA_STATUS_ACTIVE, 
								DataMGTTempConstants.DATA_MUST_SYNCHRONIZED,
								serviceContext);						
					}
				}
				else {
					throw new DuplicateCategoryException();
				}
			} else {
				dictCollection = dictItemDataUtil.addDictCollection(user.getUserId(), groupId, code,
						input.getCollectionName(), input.getCollectionNameEN(), input.getDescription(), serviceContext);
				DictCollectionTemp temp = dictItemDataTempUtil.getDictCollectionTempDetail(code, groupId);
				if (temp == null) {
					dictItemDataTempUtil.addDictCollectionTemp(user.getUserId(), groupId, code,
							input.getCollectionName(), input.getCollectionNameEN(), input.getDescription(), 
							DataMGTTempConstants.DATA_STATUS_ACTIVE, 
							DataMGTTempConstants.DATA_MUST_SYNCHRONIZED,
							serviceContext);					
				}
				else {
					dictItemDataTempUtil.updateDictCollectionTemp(user.getUserId(), groupId, code,
							input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
							input.getDescription(), DataMGTTempConstants.DATA_STATUS_ACTIVE, 
							DataMGTTempConstants.DATA_MUST_SYNCHRONIZED,
							serviceContext);											
				}
			}

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
	public Response getSyncDictCollections(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext,
			org.opencps.api.datamgtsync.model.DataSearchModel query) {
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

			org.opencps.api.datamgtsync.model.DictCollectionResults result = new org.opencps.api.datamgtsync.model.DictCollectionResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			List<DictCollection> lstCollections = dictItemDataUtil.getListDictCollectionsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictCollectionsOlderThanDate(user.getUserId(), company.getCompanyId(),
					groupId, date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictCollectionModel().addAll(DataManagementUtils.mapperDictCollectionList(lstCollections));

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
	public Response getSyncDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
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

			org.opencps.api.datamgtsync.model.DictItemResults result = new org.opencps.api.datamgtsync.model.DictItemResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			List<DictItem> lstItems = dictItemDataUtil.getListDictItemsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictItemsOlderThanDate(user.getUserId(), company.getCompanyId(), groupId,
					date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictItemModel().addAll(DataManagementUtils.mapperDictItemList(lstItems));

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

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			List<DictGroup> lstGroups = dictItemDataUtil.getListDictGroupsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictGroupsOlderThanDate(user.getUserId(), company.getCompanyId(),
					groupId, date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictGroupModel().addAll(DataManagementUtils.mapperDictGroupList(lstGroups));

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
	public Response updateOrCreateNewDictgroups(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode,
			DictGroupInputModel input, long modifiedDateTime) {
		// TODO Auto-generated method stub
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		Groups dictGroupModel = new Groups();
		DictCollectionTempInterface dictItemDataTempUtil = new org.opencps.synchronization.action.impl.DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
					DictGroupTemp temp = dictItemDataTempUtil.getDictGroupTempDetail(code, groupCode, groupId);
					if (temp != null) {
						dictItemDataTempUtil.updateDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
								input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(),
								input.getGroupDescription(), 
								DataMGTTempConstants.DATA_STATUS_ACTIVE,
								serviceContext);						
					}
					else {
						dictItemDataTempUtil.addDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
								input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), 
								DataMGTTempConstants.DATA_STATUS_ACTIVE,
								serviceContext);						
					}
				}
				else {
					throw new DuplicateCategoryException();
				}
			} else {
				dictGroup = dictItemDataUtil.addDictgroups(user.getUserId(), groupId, code, groupCode,
						input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), serviceContext);
				DictGroupTemp temp = dictItemDataTempUtil.getDictGroupTempDetail(code, groupCode, groupId);

				if (temp == null) {
					dictItemDataTempUtil.addDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
							input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(), 
							DataMGTTempConstants.DATA_STATUS_ACTIVE,
							serviceContext);					
				}
				else {
					dictItemDataTempUtil.updateDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
							input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(),
							input.getGroupDescription(), 
							DataMGTTempConstants.DATA_STATUS_ACTIVE,
							serviceContext);											
				}
			}

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
	public Response getSyncDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext,
			org.opencps.api.datamgtsync.model.DataSearchModel query) {
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

			org.opencps.api.datamgtsync.model.DictItemGroupResults result = new org.opencps.api.datamgtsync.model.DictItemGroupResults();

			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			List<DictItemGroup> lstItems = dictItemDataUtil.getListDictItemGroupsOlderThanDate(user.getUserId(),
					company.getCompanyId(), groupId, date, start, end, serviceContext);

			long total = dictItemDataUtil.countDictItemGroupsOlderThanDate(user.getUserId(), company.getCompanyId(),
					groupId, date, start, end, serviceContext);

			result.setTotal(total);

			result.getDictItemGroupModel().addAll(DataManagementUtils.mapperDictItemGroupList(lstItems));

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
	public Response getDictgroup(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String groupCode) {
		// TODO Auto-generated method stub
		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictGroup dictGroup = dictItemDataUtil.getDictGroupDetail(code, groupCode, groupId);
		
		if (Validator.isNotNull(dictGroup)) {

			// return json object after update
			DictGroupModel dictGroupModel = DataManagementUtils.mapperDictGroupModel(dictGroup);

			return Response.status(200).entity(dictGroupModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(409).entity(error).build();

		}
	}
}
