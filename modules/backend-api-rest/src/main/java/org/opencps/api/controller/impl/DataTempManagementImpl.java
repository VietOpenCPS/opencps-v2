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
import org.opencps.api.datatempmgt.model.DictItemTempDetailModel;
import org.opencps.api.datatempmgt.model.DictItemTempInputModel;
import org.opencps.api.datatempmgt.model.DictItemTempResults;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.constants.DictGroupTerm;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.synchronization.action.DictCollectionTempInterface;
import org.opencps.synchronization.action.impl.DictCollectionActions;
import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.service.DictGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.DictItemTempLocalServiceUtil;
import org.opencps.synchronization.service.SyncQueueLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

public class DataTempManagementImpl implements DataTempManagement {

	Log _log = LogFactoryUtil.getLog(DataTempManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		
		DictCollectionTempResults result = new DictCollectionTempResults();
				
		try {
			if (Validator.isNull(query.getEnd())) {
				query.setStart(-1);

				query.setEnd(-1);

			}
			
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

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
			return BusinessExceptionImpl.processException(e);
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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictCollection));
									
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictCollectionTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_HIGHEST, SyncServerTerm.METHOD_CREATE, serviceContext);
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
			dictCollectionModel = DataTempManagementUtils.mapperDictCollectionTempModel(dictCollection);

			return Response.status(200).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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

			DictCollectionTemp oldCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);
			
			DictCollectionTemp dictCollection = dictItemDataUtil.updateDictCollectionTemp(user.getUserId(), groupId, code,
					input.getCollectionCode(), input.getCollectionName(), input.getCollectionNameEN(),
					input.getDescription(), 
					input.getStatus(),
					input.getMustSync(),
					serviceContext);

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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("old", DataTempManagementUtils.convertObject(oldCollection));
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictCollection));
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictCollectionTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_HIGHEST, SyncServerTerm.METHOD_UPDATE, serviceContext);
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
			
			dictCollectionModel = DataTempManagementUtils.mapperDictCollectionTempModel(dictCollection);

			return Response.status(200).entity(dictCollectionModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDictCollection(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollectionTemp dictCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);
			
			boolean flag = dictItemDataUtil.deleteDictCollectionTemp(code, groupId, serviceContext);

			if (flag) {
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
										&& configObj.has(SyncServerTerm.SERVER_SECRET)
										&& configObj.has(SyncServerTerm.SERVER_URL)
										&& (configObj.has(SyncServerTerm.PUSH)
												&& configObj.getBoolean(SyncServerTerm.PUSH))) {
									if (groupId == sc.getGroupId()) {
										JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
										jsonObject.put("new", DataTempManagementUtils.convertObject(dictCollection));
										SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictCollectionTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_HIGHEST, SyncServerTerm.METHOD_DELETE, serviceContext);
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

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
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
			User user, ServiceContext serviceContext, String code, String dataform) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictCollectionTemp dictCollection = dictItemDataUtil.addDataForm(user.getUserId(), groupId, code, dataform,
						serviceContext);
			
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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictCollection));
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictCollectionTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_HIGHEST, SyncServerTerm.METHOD_UPDATE_DATAFORM, serviceContext);
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
			
			return Response.status(200).entity(dictCollection.getDataForm()).build();				
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public Response getDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictGroupTempResults result = new DictGroupTempResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (Validator.isNull(query.getEnd())) {

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
			return BusinessExceptionImpl.processException(e);
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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictGroup));
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictGroupTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORIOTY_HIGH, SyncServerTerm.METHOD_CREATE, serviceContext);
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
			dictGroupModel = DataTempManagementUtils.mapperGroups(dictGroup);

			return Response.status(200).entity(dictGroupModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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

			DictCollectionTemp dictCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);
			DictGroupTemp oldGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, dictCollection.getDictCollectionId());
			
			DictGroupTemp dictGroup = dictItemDataUtil.updateDictGroupsTemp(user.getUserId(), groupId, code, groupCode,
					input.getGroupCode(), input.getGroupName(), input.getGroupNameEN(), input.getGroupDescription(),
					input.getStatus(),
					serviceContext);
			
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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("old", DataTempManagementUtils.convertObject(oldGroup));
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictGroup));
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictGroupTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORIOTY_HIGH, SyncServerTerm.METHOD_UPDATE, serviceContext);
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
			dictGroupModel = DataTempManagementUtils.mapperGroups(dictGroup);

			return Response.status(200).entity(dictGroupModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDictgroups(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, String groupCode) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		
		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			
			DictCollectionTemp dictCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);
			
			DictGroupTemp dictGroup = null;
			
			if (dictCollection != null) {
				dictGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, dictCollection.getDictCollectionId());
			}
			
//			boolean flag = dictItemDataUtil.deleteDictGroupsTemp(groupCode, groupId, serviceContext);
			
			boolean flag = dictItemDataUtil.deleteDictGroupsTemp(code, groupCode, groupId, serviceContext);

			if (flag) {
				if (dictGroup != null) {
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
											&& configObj.has(SyncServerTerm.SERVER_SECRET)
											&& configObj.has(SyncServerTerm.SERVER_URL)
											&& (configObj.has(SyncServerTerm.PUSH)
													&& configObj.getBoolean(SyncServerTerm.PUSH))) {
										if (groupId == sc.getGroupId()) {
											JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
											jsonObject.put("new", DataTempManagementUtils.convertObject(dictGroup));
											SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictGroupTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORIOTY_HIGH, SyncServerTerm.METHOD_DELETE, serviceContext);
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
				}
				return Response.status(200).build();

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
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
			result.getDictGroupItemTempModel()
					.addAll(DataTempManagementUtils.mapperDictGroupItemTempModelList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictItemGroup));
									
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictItemGroupTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_LOWEST, SyncServerTerm.METHOD_ADD_TO_GROUP, serviceContext);
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
						
			dictGroupItemModel = DataTempManagementUtils.mapperDictGroupItemTempModel(dictItemGroup);
			dictGroupItemModel.setSelected(Boolean.TRUE);

			return Response.status(200).entity(dictGroupItemModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteDictgroupsDictItems(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String groupCode, String itemCode) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			DictCollectionTemp dictCollection = dictItemDataUtil.getDictCollectionTempDetail(code, groupId);
			DictItemTemp dictItem = dictItemDataUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);
			DictGroupTemp dictGroup = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, groupId, dictCollection.getDictCollectionId());
			
			DictItemGroupTemp dictItemGroup = DictItemGroupTempLocalServiceUtil.fetchByF_dictItemId_dictGroupId(groupId, dictGroup.getDictGroupId(), dictItem.getDictItemId());
			
			boolean flag = dictItemDataUtil.deleteDictGroupsDictItemsTemp(groupId, code, groupCode, itemCode,
					serviceContext);

			if (flag) {

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
										&& configObj.has(SyncServerTerm.SERVER_SECRET)
										&& configObj.has(SyncServerTerm.SERVER_URL)
										&& (configObj.has(SyncServerTerm.PUSH)
												&& configObj.getBoolean(SyncServerTerm.PUSH))) {
									if (groupId == sc.getGroupId()) {
										JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
										jsonObject.put("new", DataTempManagementUtils.convertObject(dictItemGroup));
										
										SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictItemGroupTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_LOWEST, SyncServerTerm.METHOD_REMOVE_FROM_GROUP, serviceContext);
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

			} else {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DataSearchModel query) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictItemTempResults result = new DictItemTempResults();
		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(company.getCompanyId());

		try {

			if (Validator.isNull(query.getEnd())) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if ("ADMINISTRATIVE_REGION".equals(code))
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
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response addDictItems(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String code, DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictItemTempDetailModel dictItemModel = new DictItemTempDetailModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItemTemp dictItem = dictItemDataUtil.addDictItemsTemp(
					user.getUserId(), 
					groupId, 
					code,
					input.getParentItemCode(), 
					input.getItemCode(), 
					input.getItemName(), 
					input.getItemNameEN(),
					input.getItemDescription(), 
					String.valueOf(input.getSibling()), 
					input.getLevel(), 
					input.getMetaData(),
					input.getStatus(),	
					serviceContext);

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
									&& configObj.has(SyncServerTerm.SERVER_SECRET)
									&& configObj.has(SyncServerTerm.SERVER_URL)
									&& (configObj.has(SyncServerTerm.PUSH)
											&& configObj.getBoolean(SyncServerTerm.PUSH))) {
								if (groupId == sc.getGroupId()) {
									JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
									jsonObject.put("new", DataTempManagementUtils.convertObject(dictItem));
									
									SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictItemTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_LOW, SyncServerTerm.METHOD_CREATE, serviceContext);
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
			dictItemModel = DataTempManagementUtils.mapperDictItemTempModel(dictItem, dictItemDataUtil, user.getUserId(),
					company.getCompanyId(), groupId, serviceContext);

			return Response.status(200).entity(dictItemModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictItemTemp dictItem = dictItemDataUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			DictItemTempDetailModel dictItemModel = DataTempManagementUtils.mapperDictItemTempModel(dictItem, dictItemDataUtil,
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
			DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();
		DictItemTempDetailModel dictItemModel = new DictItemTempDetailModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			
			DictItemTemp oldItem = dictItemDataUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);
			
			DictItemTemp ett = dictItemDataUtil.updateDictItemTempByItemCode(user.getUserId(), groupId, serviceContext, code,
					itemCode, input.getItemCode(), input.getItemName(), input.getItemNameEN(),
					input.getItemDescription(), String.valueOf(input.getSibling()), input.getParentItemCode(), input.getStatus());

			if (Validator.isNull(ett)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			} else {

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
										&& configObj.has(SyncServerTerm.SERVER_SECRET)
										&& configObj.has(SyncServerTerm.SERVER_URL)
										&& (configObj.has(SyncServerTerm.PUSH)
												&& configObj.getBoolean(SyncServerTerm.PUSH))) {
									if (groupId == sc.getGroupId()) {
										JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
										jsonObject.put("old", DataTempManagementUtils.convertObject(oldItem));
										jsonObject.put("new", DataTempManagementUtils.convertObject(ett));
										
										SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictItemTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_LOW, SyncServerTerm.METHOD_UPDATE, serviceContext);
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
				dictItemModel = DataTempManagementUtils.mapperDictItemTempModel(ett, dictItemDataUtil, user.getUserId(),
						company.getCompanyId(), groupId, serviceContext);

				return Response.status(200).entity(dictItemModel).build();

			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response deleteDictItemByItemCode(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItemTemp dictColl = dictItemDataUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);

			if (Validator.isNull(dictColl)) {

				ErrorMsg error = new ErrorMsg();

				error.setMessage("not found!");
				error.setCode(404);
				error.setDescription("not found!");

				return Response.status(404).entity(error).build();

			} else {				
				DictItemTempLocalServiceUtil.deleteDictItemTemp(groupId, itemCode, serviceContext);

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
										&& configObj.has(SyncServerTerm.SERVER_SECRET)
										&& configObj.has(SyncServerTerm.SERVER_URL)
										&& (configObj.has(SyncServerTerm.PUSH)
												&& configObj.getBoolean(SyncServerTerm.PUSH))) {
									if (groupId == sc.getGroupId()) {
										JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
										jsonObject.put("new", DataTempManagementUtils.convertObject(dictColl));
										
										SyncQueueLocalServiceUtil.addSyncQueue(user.getUserId(), groupId, sc.getServerNo(), DictItemTemp.class.getName(), jsonObject.toJSONString(), SyncServerTerm.QUEUE_STATUS_NEW, 0, SyncServerTerm.PRIORITY_LOW, SyncServerTerm.METHOD_DELETE, serviceContext);
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
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response getMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictItemTemp dictItem = dictItemDataUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);

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
	public Response getMetaDataOfDictItemByKey(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode, String key) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		DictItemTemp dictItem = dictItemDataUtil.getDictItemTempByItemCode(code, itemCode, groupId, serviceContext);

		if (Validator.isNotNull(dictItem)) {

			JSONObject jsonMetaData;

			String value = "Not Found!";

			try {

				jsonMetaData = JSONFactoryUtil.createJSONObject(dictItem.getMetaData());

				value = jsonMetaData.getString(key);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				_log.error(e);
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
	public Response addMetaDataOfDictItem(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String code, String itemCode,
			DictItemTempInputModel input) {
		// TODO Auto-generated method stub
		DictCollectionTempInterface dictItemDataUtil = new DictCollectionActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			DictItemTemp ett = dictItemDataUtil.updateMetaDataByItemCode(user.getUserId(), groupId, serviceContext, code,
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
			return BusinessExceptionImpl.processException(e);
		}
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
