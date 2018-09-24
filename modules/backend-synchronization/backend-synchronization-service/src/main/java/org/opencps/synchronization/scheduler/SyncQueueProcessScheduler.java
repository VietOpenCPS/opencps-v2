package org.opencps.synchronization.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalService;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.synchronization.constants.DictCollectionTempTerm;
import org.opencps.synchronization.constants.DictGroupTempTerm;
import org.opencps.synchronization.constants.DictItemTempTerm;
import org.opencps.synchronization.constants.PushCollectionTerm;
import org.opencps.synchronization.constants.PushDictGroupTerm;
import org.opencps.synchronization.constants.PushDictItemTerm;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.DictCollectionTemp;
import org.opencps.synchronization.model.DictGroupTemp;
import org.opencps.synchronization.model.DictItemGroupTemp;
import org.opencps.synchronization.model.DictItemTemp;
import org.opencps.synchronization.model.SyncQueue;
import org.opencps.synchronization.rest.client.DictDataRestClient;
import org.opencps.synchronization.service.DictCollectionTempLocalServiceUtil;
import org.opencps.synchronization.service.DictGroupTempLocalServiceUtil;
import org.opencps.synchronization.service.SyncQueueLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

//@Component(immediate = true, service = SyncQueueProcessScheduler.class)
public class SyncQueueProcessScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("Processing synchronized queue is starting at " + APIDateTimeUtils.convertDateToString(new Date()));

		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());

			List<ServerConfig> lstServers = _serverConfigLocalService.getServerConfigs(QueryUtil.ALL_POS,
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
								&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)
								&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))) {
							List<SyncQueue> lstSyncs = _syncQueueLocalService.findByF_groupId_serverNo(sc.getGroupId(),
									sc.getServerNo(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
							synchronizeQueue(lstSyncs, sc, configObj);
						}
					} catch (Exception e) {
						_log.error(e);
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}
		_log.info("Processing synchronized queue finished at " + APIDateTimeUtils.convertDateToString(new Date()));	
	}

	private void synchronizeQueue(List<SyncQueue> lstSyncs, ServerConfig serverConfig, JSONObject configObj) {
		InvokeREST rest = new InvokeREST();

		HashMap<String, String> properties = new HashMap<String, String>();
		HashMap<String, Object> params = new HashMap<String, Object>();

		String dictCollectionEndPoint = "/dictcollections";
		StringBuilder putDictCollectionRestUrl = new StringBuilder();
		String rootApiUrl = configObj.getString(SyncServerTerm.SERVER_URL);

		DictcollectionInterface dictItemDataUtil = new DictCollectionActions();

		if (rootApiUrl.charAt(rootApiUrl.length() - 1) == '/') {
			rootApiUrl = rootApiUrl.substring(0, rootApiUrl.length() - 2);
		}

		DictDataRestClient restClient = DictDataRestClient.fromJSONObject(configObj);
		List<String> lstExcludes = new ArrayList<String>();
		if (configObj.has(SyncServerTerm.SERVER_EXCLUDES)) {
			lstExcludes = Arrays.asList(StringUtil.split(configObj.getString(SyncServerTerm.SERVER_EXCLUDES)));
		}
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
			serviceContext.setUserId(serverConfig.getUserId());
			serviceContext.setScopeGroupId(serverConfig.getGroupId());
			serviceContext.setSignedIn(true);
			for (SyncQueue pqueue : lstSyncs) {
				Class<?> queueClass = Class.forName(pqueue.getClassName());
				
				if (queueClass.isAssignableFrom(DictCollectionTemp.class)) {
					boolean isFound = false;
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(pqueue.getJsonObject());

					JSONObject collectionObj = jsonObject.getJSONObject("new");
					String collectionCode = collectionObj.getString(DictCollectionTempTerm.COLLECTION_CODE);
					String collectionName = collectionObj.getString(DictCollectionTempTerm.COLLECTION_NAME);
					String collectionNameEN = collectionObj.getString(DictCollectionTempTerm.COLLECTION_NAME_EN);
					String description = collectionObj.getString(DictCollectionTempTerm.DESCRIPTION);
					Date modifiedDate = APIDateTimeUtils.convertStringToDate(
							collectionObj.getString(DictCollectionTempTerm.MODIFIED_DATE), APIDateTimeUtils._TIMESTAMP);
					String dataForm = collectionObj.getString(DictCollectionTempTerm.DATAFORM);

					JSONObject oldCollectionObj = jsonObject.getJSONObject("old");
					String oldCollectionCode = (oldCollectionObj != null)
							? oldCollectionObj.getString(DictCollectionTempTerm.COLLECTION_CODE) : collectionCode;

					if (restClient != null) {
						if (restClient.getCollectionDetail(collectionCode) != null) {
							isFound = true;
						}
					}

					if (pqueue.getMethod().equals(SyncServerTerm.METHOD_CREATE)) {
						putDictCollectionRestUrl.setLength(0);
						putDictCollectionRestUrl.append(dictCollectionEndPoint);
						putDictCollectionRestUrl.append("/" + collectionCode);

						params.put(PushCollectionTerm.COLLECTION_CODE, collectionCode);
						params.put(PushCollectionTerm.COLLECTION_NAME, collectionName);
						params.put(PushCollectionTerm.COLLECTION_NAME_EN, collectionNameEN);
						params.put(PushCollectionTerm.DESCRIPTION, description);

						if (!lstExcludes.contains(collectionCode)) {
							if (!isFound) {
								JSONObject resDictCollection = rest.callPostAPI(
										configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST,
										"application/json", rootApiUrl, putDictCollectionRestUrl.toString(),
										configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params,
										serviceContext);

								if (SyncServerUtil.isSyncOk(resDictCollection.getInt(RESTFulConfiguration.STATUS))) {
									DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode,
											serverConfig.getGroupId());

									if (oldDict != null) {
										dictItemDataUtil.updateDictCollection(serverConfig.getUserId(),
												serverConfig.getGroupId(), collectionCode, collectionCode,
												collectionName, collectionNameEN, description, serviceContext);
									} else {
										dictItemDataUtil.addDictCollection(serverConfig.getUserId(),
												serverConfig.getGroupId(), collectionCode, collectionName,
												collectionNameEN, description, serviceContext);
									}
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								} else {
									break;
								}
							} else {
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
						} else {
							DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode,
									serverConfig.getGroupId());

							if (oldDict != null) {
								dictItemDataUtil.updateDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, collectionCode, collectionName, collectionNameEN, description, serviceContext);
							}
							else {
								dictItemDataUtil.addDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, collectionName, collectionNameEN, description, serviceContext);										
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
						}
					}
					else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_UPDATE)) {
						putDictCollectionRestUrl.setLength(0);
						putDictCollectionRestUrl.append(dictCollectionEndPoint);
						putDictCollectionRestUrl.append("/" + collectionCode);

						params.put(PushCollectionTerm.MODIFIED_DATE, modifiedDate.getTime());
						params.put(PushCollectionTerm.COLLECTION_CODE, collectionCode);
						params.put(PushCollectionTerm.COLLECTION_NAME, collectionName);
						params.put(PushCollectionTerm.COLLECTION_NAME_EN, collectionNameEN);
						params.put(PushCollectionTerm.DESCRIPTION, description);

						if (!lstExcludes.contains(collectionCode) && !lstExcludes.contains(oldCollectionCode)) {
							if (isFound) {
								JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
										rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
								
								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(oldCollectionCode, serverConfig.getGroupId());
									
									if (oldDict != null) {
										dictItemDataUtil.updateDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), oldCollectionCode, collectionCode, collectionName, collectionNameEN, description, serviceContext);							
									}
									else {
										dictItemDataUtil.addDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, collectionName, collectionNameEN, description, serviceContext);
									}
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());								
								}	
								else {
									break;
								}
							}
							else {
								JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
										rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
								
								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(oldCollectionCode, serverConfig.getGroupId());
									
									if (oldDict != null) {
										dictItemDataUtil.updateDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), oldCollectionCode, collectionCode, collectionName, collectionNameEN, description, serviceContext);							
									}
									else {
										dictItemDataUtil.addDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, collectionName, collectionNameEN, description, serviceContext);
									}

									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());								
								}	
								else {
									break;
								}
							}
						}
						else {
							DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(oldCollectionCode, serverConfig.getGroupId());
							
							if (oldDict != null) {
								dictItemDataUtil.updateDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), oldCollectionCode, collectionCode, collectionName, collectionNameEN, description, serviceContext);							
							}
							else {
								dictItemDataUtil.addDictCollection(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, collectionName, collectionNameEN, description, serviceContext);
						}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());															
						}
					}
					else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_DELETE)) {
						putDictCollectionRestUrl.setLength(0);
						putDictCollectionRestUrl.append(dictCollectionEndPoint);
						putDictCollectionRestUrl.append("/" + collectionCode);

						if (!lstExcludes.contains(collectionCode)) {
							try {
								if (isFound) {
									JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE, "application/json",
											rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
											configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
									if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
										DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
										
										if (oldDict != null) {
											dictItemDataUtil.deleteDictCollection(collectionCode, serverConfig.getGroupId(), serviceContext);
										}
										_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
									}		
									else {
										break;
									}
								}
								else {
									DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
									
									if (oldDict != null) {
										dictItemDataUtil.deleteDictCollection(collectionCode, serverConfig.getGroupId(), serviceContext);
									}
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
								}
							}
							catch (Exception e) {
								_log.error(e);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());						
							}
						}
						else {
							DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
							
							if (oldDict != null) {
								dictItemDataUtil.deleteDictCollection(collectionCode, serverConfig.getGroupId(), serviceContext);
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());														
						}
					}
					else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_UPDATE_DATAFORM)) {
						putDictCollectionRestUrl.setLength(0);
						putDictCollectionRestUrl.append(dictCollectionEndPoint);
						putDictCollectionRestUrl.append("/" + collectionCode);
						putDictCollectionRestUrl.append("/dataform");

						params.put(PushCollectionTerm.DATA_FORM, dataForm);
						params.put(PushCollectionTerm.MODIFIED_DATE, modifiedDate.getTime());

						if (!lstExcludes.contains(collectionCode)) {
							if (isFound) {
								JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.PUT, "application/json",
										rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
								
								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
									
									if (oldDict != null) {
										dictItemDataUtil.addDataForm(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, dataForm, serviceContext);
									}

									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								}		
								else {
									break;
								}
							}
							else {
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());						
							}
						}
						else {
							DictCollection oldDict = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
							
							if (oldDict != null) {
								dictItemDataUtil.addDataForm(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, dataForm, serviceContext);
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
						}
					}					
					}
				else if (queueClass.isAssignableFrom(DictGroupTemp.class)) {
					StringBuilder putDictGroupRestUrl = new StringBuilder();
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(pqueue.getJsonObject());

					JSONObject groupObj = jsonObject.getJSONObject("new");
					String collectionCode = groupObj.getString(DictCollectionTempTerm.COLLECTION_CODE);
					String groupCode = groupObj.getString(DictGroupTempTerm.GROUP_CODE);
					String groupName = groupObj.getString(DictGroupTempTerm.GROUP_NAME);
					String groupNameEN = groupObj.getString(DictGroupTempTerm.GROUP_NAME_EN);
					String groupDescription = groupObj.getString(DictGroupTempTerm.GROUP_DESCRIPTION);
					Date modifiedDate = APIDateTimeUtils.convertStringToDate(groupObj.getString(DictGroupTempTerm.MODIFIED_DATE), APIDateTimeUtils._TIMESTAMP);
					
					JSONObject oldGroupObj = jsonObject.getJSONObject("old");
					String oldGroupCode = (oldGroupObj != null) ? oldGroupObj.getString(DictGroupTempTerm.GROUP_CODE) : groupCode;
					boolean isFound = false;
					
					if (restClient != null) {
						if (restClient.getGroupDetail(collectionCode, groupCode) != null) {
							isFound = true;
						}
					}
				
					if (pqueue.getMethod().equals(SyncServerTerm.METHOD_CREATE)) {
						putDictGroupRestUrl.setLength(0);
						putDictGroupRestUrl.append(dictCollectionEndPoint);
						putDictGroupRestUrl.append("/" + collectionCode);
						putDictGroupRestUrl.append("/dictgroups");

						params.put(PushDictGroupTerm.GROUP_CODE, groupCode);
						params.put(PushDictGroupTerm.GROUP_NAME, groupName);
						params.put(PushDictGroupTerm.GROUP_NAME_EN, groupNameEN);
						params.put(PushDictGroupTerm.GROUP_DESCRIPTION, groupDescription);

						if (!lstExcludes.contains(collectionCode)) {
							JSONObject resDictGroup = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
									rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
									configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
							
							if (SyncServerUtil.isSyncOk(resDictGroup.getInt(RESTFulConfiguration.STATUS))) {
								DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
								
								DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
								
								if (oldGroup == null) {
									dictItemDataUtil.addDictgroups(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, groupCode, groupName, groupNameEN, groupDescription, serviceContext);									
								}
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
							else {
								break;
							}
						}
						else {
							DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
							
							DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
							if (oldGroup == null) {
								dictItemDataUtil.addDictgroups(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, groupCode, groupName, groupNameEN, groupDescription, serviceContext);
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
						}
						}
					else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_UPDATE)) {
						putDictGroupRestUrl.setLength(0);
						putDictGroupRestUrl.append(dictCollectionEndPoint);
						putDictGroupRestUrl.append("/" + collectionCode);
						putDictGroupRestUrl.append("/dictgroups");
						putDictGroupRestUrl.append("/" + groupCode);

						params.put(PushDictGroupTerm.MODIFIED_DATE, modifiedDate.getTime());
						params.put(PushDictGroupTerm.GROUP_NAME, groupName);
						params.put(PushDictGroupTerm.GROUP_NAME_EN, groupNameEN);
						params.put(PushDictGroupTerm.GROUP_DESCRIPTION, groupDescription);

						if (!lstExcludes.contains(collectionCode)) {
							JSONObject resDictGroup = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
									rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
									configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
							
							if (SyncServerUtil.isSyncOk(resDictGroup.getInt(RESTFulConfiguration.STATUS))) {
								DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
								
								DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(oldGroupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
								if (oldGroup != null) {
									dictItemDataUtil.updateDictgroups(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, oldGroupCode, groupCode, groupName, groupNameEN, groupDescription, serviceContext);
								}

								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}	
							else {
								break;
							}
						}
						else {
							DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
							
							DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(oldGroupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
							if (oldGroup != null) {
								dictItemDataUtil.updateDictgroups(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, oldGroupCode, groupCode, groupName, groupNameEN, groupDescription, serviceContext);
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
						}
						}
					else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_DELETE)) {
						putDictGroupRestUrl.setLength(0);
						putDictGroupRestUrl.append(dictCollectionEndPoint);
						putDictGroupRestUrl.append("/" + collectionCode);
						putDictGroupRestUrl.append("/dictgroups");
						putDictGroupRestUrl.append("/" + groupCode);

						if (!lstExcludes.contains(collectionCode)) {
							if (isFound) {
								JSONObject resDictGroup = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE, "application/json",
										rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
								
								if (resDictGroup != null && resDictGroup.has(RESTFulConfiguration.STATUS) && SyncServerUtil.isSyncDeleteGroupOk(resDictGroup.getInt(RESTFulConfiguration.STATUS))) {
									DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
									
									if (collection != null) {
										DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
								if (oldGroup != null) {
											dictItemDataUtil.deleteDictgroupsAndSomethingUseIt(collectionCode, groupCode, serverConfig.getGroupId(), serviceContext);
										}									
								}

								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								}	
								else if (resDictGroup != null && !resDictGroup.has(RESTFulConfiguration.STATUS)) {
									DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
									
									if (collection != null) {
										DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
										if (oldGroup != null) {
											dictItemDataUtil.deleteDictgroupsAndSomethingUseIt(collectionCode, groupCode, serverConfig.getGroupId(), serviceContext);
										}									
							}

									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());								
								}								
								else {
									break;
								}								
							}
							else {
								DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
								
								if (collection != null) {
									DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
							if (oldGroup != null) {
										dictItemDataUtil.deleteDictgroupsAndSomethingUseIt(collectionCode, groupCode, serverConfig.getGroupId(), serviceContext);
									}									
							}
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());																
						}
					}
						else {
							DictCollection collection = dictItemDataUtil.getDictCollectionDetail(collectionCode, serverConfig.getGroupId());
							
							if (collection != null) {
								DictGroup oldGroup = DictGroupLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collection.getDictCollectionId());
								if (oldGroup != null) {
									dictItemDataUtil.deleteDictgroupsAndSomethingUseIt(collectionCode, groupCode, serverConfig.getGroupId(), serviceContext);
								}								
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
						}
					}
				}
				else if (queueClass.isAssignableFrom(DictItemTemp.class)) {
					boolean isFound = false;
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(pqueue.getJsonObject());

					JSONObject itemObj = jsonObject.getJSONObject("new");

					String itemCode = itemObj.getString(DictItemTempTerm.ITEM_CODE);
					String itemName = itemObj.getString(DictItemTempTerm.ITEM_NAME);
					String itemNameEN = itemObj.getString(DictItemTempTerm.ITEM_NAME_EN);
					String itemDescription = itemObj.getString(DictItemTempTerm.ITEM_DESCRIPTION);
					String metaData = itemObj.getString(DictItemTempTerm.META_DATA);
					String collectionCode = itemObj.getString(DictCollectionTempTerm.COLLECTION_CODE);
					String parentItemCode = itemObj.getString(DictItemTempTerm.PARENT_ITEM_CODE);
					String sibling = itemObj.getString(DictItemTempTerm.SIBLING);
					int level = itemObj.getInt(DictItemTempTerm.LEVEL);
					
					Date modifiedDate = APIDateTimeUtils.convertStringToDate(itemObj.getString(DictItemTempTerm.MODIFIED_DATE), APIDateTimeUtils._TIMESTAMP);
					
					JSONObject oldItemObj = jsonObject.getJSONObject("old");
					String oldItemCode = (oldItemObj != null) ? oldItemObj.getString(DictItemTempTerm.ITEM_CODE) : itemCode;
					
					if (restClient != null) {
						if (restClient.getItemDetail(collectionCode, itemCode) != null) {
							isFound = true;
						}
					}
					StringBuilder putDictItemRestUrl = new StringBuilder();

					if (pqueue.getMethod().equals(SyncServerTerm.METHOD_CREATE)) {
						putDictItemRestUrl.setLength(0);
						putDictItemRestUrl.append(dictCollectionEndPoint);
						putDictItemRestUrl.append("/");
						putDictItemRestUrl.append(collectionCode);
						putDictItemRestUrl.append("/dictitems");
						putDictItemRestUrl.append("/" + itemCode);

						params.put(PushDictItemTerm.COLLECTION_CODE, collectionCode);
						params.put(PushDictItemTerm.ITEM_NAME, itemName);
						params.put(PushDictItemTerm.ITEM_NAME_EN, itemNameEN);
						params.put(PushDictItemTerm.ITEM_DESCRIPTION, itemDescription);
						params.put(PushDictItemTerm.PARENT_ITEM_CODE, parentItemCode);
						params.put(PushDictItemTerm.SIBLING, sibling);

						if (!lstExcludes.contains(collectionCode)) {
							if (!isFound) {
								JSONObject resDictItem = rest.callPostAPI(
										configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST,
										"application/json", rootApiUrl, putDictItemRestUrl.toString(),
										configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params,
										serviceContext);

								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									try {
										DictItem dictItemCreate = dictItemDataUtil.addDictItems(serverConfig.getUserId(),
												serverConfig.getGroupId(), collectionCode, parentItemCode, itemCode,
												itemName, itemNameEN, itemDescription, sibling, level, metaData,
												serviceContext);
										// TODO: Reindex dictItemGroup
										List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
												serverConfig.getGroupId(), dictItemCreate.getDictItemId());
										if (digList != null && digList.size() > 0) {
											Indexer<DictItemGroup> indexer = IndexerRegistryUtil
													.nullSafeGetIndexer(DictItemGroup.class);
											for (DictItemGroup dig : digList) {
												indexer.reindex(dig);
											}
										}
										_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
									} catch (DuplicateCategoryException e) {
										_log.error(e);
										_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
									}
								} else {
									break;
								}
							} else {
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
						} else {
							try {
								DictItem dictItemCreate = dictItemDataUtil.addDictItems(serverConfig.getUserId(), serverConfig.getGroupId(),
										collectionCode, parentItemCode, itemCode, itemName, itemNameEN, itemDescription,
										sibling, level, metaData, serviceContext);
								// TODO: Reindex dictItemGroup
								List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
										serverConfig.getGroupId(), dictItemCreate.getDictItemId());
								if (digList != null && digList.size() > 0) {
									Indexer<DictItemGroup> indexer = IndexerRegistryUtil
											.nullSafeGetIndexer(DictItemGroup.class);
									for (DictItemGroup dig : digList) {
										indexer.reindex(dig);
									}
								}
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							} catch (DuplicateCategoryException e) {
								_log.error(e);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
						}
					} else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_UPDATE)) {
						putDictItemRestUrl.setLength(0);
						putDictItemRestUrl.append(dictCollectionEndPoint);
						putDictItemRestUrl.append("/");
						putDictItemRestUrl.append(collectionCode);
						putDictItemRestUrl.append("/dictitems");
						putDictItemRestUrl.append("/" + itemCode);

						params.put(PushDictItemTerm.MODIFIED_DATE, modifiedDate.getTime());
						params.put(PushDictItemTerm.COLLECTION_CODE, collectionCode);
						params.put(PushDictItemTerm.ITEM_NAME, itemName);
						params.put(PushDictItemTerm.ITEM_NAME_EN, itemNameEN);
						params.put(PushDictItemTerm.ITEM_DESCRIPTION, itemDescription);
						params.put(PushDictItemTerm.PARENT_ITEM_CODE, parentItemCode);
						params.put(PushDictItemTerm.SIBLING, sibling);
						
						_log.info("GROUPID====: "+serverConfig.getGroupId());

						if (!lstExcludes.contains(collectionCode)) {
							if (isFound) {
								JSONObject resDictItem = rest.callPostAPI(
										configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST,
										"application/json", rootApiUrl, putDictItemRestUrl.toString(),
										configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params,
										serviceContext);

								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode,
											oldItemCode, serverConfig.getGroupId(), serviceContext);

									if (oldItem != null) {
										DictItem dictItemUpdate = dictItemDataUtil.updateDictItemByItemCode(
												serverConfig.getUserId(), serverConfig.getGroupId(), serviceContext,
												collectionCode, oldItemCode, itemCode, itemName, itemNameEN,
												itemDescription, sibling, parentItemCode);
										// TODO: Reindex dictItemGroup
										List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
												serverConfig.getGroupId(), dictItemUpdate.getDictItemId());
										if (digList != null && digList.size() > 0) {
											Indexer<DictItemGroup> indexer = IndexerRegistryUtil
													.nullSafeGetIndexer(DictItemGroup.class);
											for (DictItemGroup dig : digList) {
												indexer.reindex(dig);
											}
										}
									} else {
										DictItem dictItemUpdate = dictItemDataUtil.addDictItems(
												serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode,
												parentItemCode, itemCode, itemName, itemNameEN, itemDescription,
												sibling, level, metaData, serviceContext);
										// TODO: Reindex dictItemGroup
										List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
												serverConfig.getGroupId(), dictItemUpdate.getDictItemId());
										if (digList != null && digList.size() > 0) {
											Indexer<DictItemGroup> indexer = IndexerRegistryUtil
													.nullSafeGetIndexer(DictItemGroup.class);
											for (DictItemGroup dig : digList) {
												indexer.reindex(dig);
											}
										}
									}
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								} else {
									break;
								}
							} else {
								JSONObject resDictItem = rest.callPostAPI(
										configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST,
										"application/json", rootApiUrl, putDictItemRestUrl.toString(),
										configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params,
										serviceContext);

								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode,
											oldItemCode, serverConfig.getGroupId(), serviceContext);

									if (oldItem != null) {
										DictItem dictItemUpdate = dictItemDataUtil.updateDictItemByItemCode(
												serverConfig.getUserId(), serverConfig.getGroupId(), serviceContext,
												collectionCode, oldItemCode, itemCode, itemName, itemNameEN,
												itemDescription, sibling, parentItemCode);
										// TODO: Reindex dictItemGroup
										List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
												serverConfig.getGroupId(), dictItemUpdate.getDictItemId());
										if (digList != null && digList.size() > 0) {
											Indexer<DictItemGroup> indexer = IndexerRegistryUtil
													.nullSafeGetIndexer(DictItemGroup.class);
											for (DictItemGroup dig : digList) {
												indexer.reindex(dig);
											}
										}
									} else {
										DictItem dictItemUpdate = dictItemDataUtil.addDictItems(
												serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode,
												parentItemCode, itemCode, itemName, itemNameEN, itemDescription,
												sibling, level, metaData, serviceContext);
										// TODO: Reindex dictItemGroup
										List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
												serverConfig.getGroupId(), dictItemUpdate.getDictItemId());
										if (digList != null && digList.size() > 0) {
											Indexer<DictItemGroup> indexer = IndexerRegistryUtil
													.nullSafeGetIndexer(DictItemGroup.class);
											for (DictItemGroup dig : digList) {
												indexer.reindex(dig);
											}
										}
									}

									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								} else {
									break;
								}
							}
						} else {
							DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode, oldItemCode,
									serverConfig.getGroupId(), serviceContext);

							if (oldItem != null) {
								DictItem dictItemUpdate = dictItemDataUtil.updateDictItemByItemCode(
										serverConfig.getUserId(), serverConfig.getGroupId(), serviceContext,
										collectionCode, oldItemCode, itemCode, itemName, itemNameEN, itemDescription,
										sibling, parentItemCode);
								// TODO: Reindex dictItemGroup
								List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
										serverConfig.getGroupId(), dictItemUpdate.getDictItemId());
								if (digList != null && digList.size() > 0) {
									Indexer<DictItemGroup> indexer = IndexerRegistryUtil
											.nullSafeGetIndexer(DictItemGroup.class);
									for (DictItemGroup dig : digList) {
										indexer.reindex(dig);
									}
								}
							} else {
								DictItem dictItemUpdate = dictItemDataUtil.addDictItems(serverConfig.getUserId(),
										serverConfig.getGroupId(), collectionCode, parentItemCode, itemCode, itemName,
										itemNameEN, itemDescription, sibling, level, metaData, serviceContext);
								// TODO: Reindex dictItemGroup
								List<DictItemGroup> digList = DictItemGroupLocalServiceUtil.findByF_dictItemId(
										serverConfig.getGroupId(), dictItemUpdate.getDictItemId());
								if (digList != null && digList.size() > 0) {
									Indexer<DictItemGroup> indexer = IndexerRegistryUtil
											.nullSafeGetIndexer(DictItemGroup.class);
									for (DictItemGroup dig : digList) {
										indexer.reindex(dig);
									}
								}
							}
							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
						}
					} else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_DELETE)) {
						putDictItemRestUrl.setLength(0);
						putDictItemRestUrl.append(dictCollectionEndPoint);
						putDictItemRestUrl.append("/");
						putDictItemRestUrl.append(collectionCode);
						putDictItemRestUrl.append("/dictitems");
						putDictItemRestUrl.append("/" + itemCode);

						if (!lstExcludes.contains(collectionCode)) {
							if (isFound) {
								JSONObject resDictItem = rest.callPostAPI(
										configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE,
										"application/json", rootApiUrl, putDictItemRestUrl.toString(),
										configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params,
										serviceContext);

								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode, itemCode,
											serverConfig.getGroupId(), serviceContext);

									if (oldItem != null) {
										DictItemLocalServiceUtil.deleteDictItem(serverConfig.getGroupId(), itemCode,
												serviceContext);
									}

									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								} else {
									break;
								}
							} else {
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
						} else {
							DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode, itemCode,
									serverConfig.getGroupId(), serviceContext);

							if (oldItem != null) {
								DictItemLocalServiceUtil.deleteDictItem(serverConfig.getGroupId(), itemCode,
										serviceContext);
							}

							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
						}
					} else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_UPDATE_METADATA)) {
						putDictItemRestUrl.setLength(0);
						putDictItemRestUrl.append(dictCollectionEndPoint);
						putDictItemRestUrl.append("/");
						putDictItemRestUrl.append(collectionCode);
						putDictItemRestUrl.append("/dictitems");
						putDictItemRestUrl.append("/" + itemCode);
						putDictItemRestUrl.append("/metadata");

						params.put(PushDictItemTerm.META_DATA, metaData);

						if (!lstExcludes.contains(collectionCode)) {
							if (isFound) {
								JSONObject resDictItem = rest.callPostAPI(
										configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.PUT,
										"application/json", rootApiUrl, putDictItemRestUrl.toString(),
										configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params,
										serviceContext);

								if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
									DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode, itemCode,
											serverConfig.getGroupId(), serviceContext);

									if (oldItem != null) {
										dictItemDataUtil.updateMetaDataByItemCode(serverConfig.getUserId(),
												serverConfig.getGroupId(), serviceContext, collectionCode, itemCode,
												metaData);
									}

									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								}
							} else {
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
						} else {
							DictItem oldItem = dictItemDataUtil.getDictItemByItemCode(collectionCode, itemCode,
									serverConfig.getGroupId(), serviceContext);

							if (oldItem != null) {
								dictItemDataUtil.updateMetaDataByItemCode(serverConfig.getUserId(),
										serverConfig.getGroupId(), serviceContext, collectionCode, itemCode, metaData);
							}

							_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
						}
					}
				} else if (queueClass.isAssignableFrom(DictItemGroupTemp.class)) {
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(pqueue.getJsonObject());

					JSONObject dictItemGroupObj = jsonObject.getJSONObject("new");
					String collectionCode = dictItemGroupObj.getString(DictCollectionTempTerm.COLLECTION_CODE);
					String groupCode = dictItemGroupObj.getString(DictGroupTempTerm.GROUP_CODE);
					String itemCode = dictItemGroupObj.getString(DictItemTempTerm.ITEM_CODE);

					StringBuilder putDictGroupRestUrl = new StringBuilder();
					boolean isFoundGroup = false;
					if (restClient != null) {
						if (restClient.getGroupDetail(collectionCode, groupCode) != null) {
							isFoundGroup = true;
						}
					}

					if (pqueue.getMethod().equals(SyncServerTerm.METHOD_ADD_TO_GROUP)) {
						putDictGroupRestUrl.setLength(0);
						putDictGroupRestUrl.append(dictCollectionEndPoint);
						putDictGroupRestUrl.append("/" + collectionCode);
						putDictGroupRestUrl.append("/dictgroups");
						putDictGroupRestUrl.append("/" + groupCode);
						putDictGroupRestUrl.append("/dictitems");

						params.put(PushDictGroupTerm.ITEM_CODE, itemCode);

						if (!lstExcludes.contains(collectionCode)) {
							if (isFoundGroup) {
								JSONObject resDictGroup = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
										rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
										configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
								
							if (SyncServerUtil.isSyncOk(resDictGroup.getInt(RESTFulConfiguration.STATUS))) {
								try {
										dictItemDataUtil.addDictgroupsDictItems(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, groupCode, itemCode, serviceContext);
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								}
									catch (DuplicateCategoryException e) {
										_log.error(e);
										_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());									
							}
								}														
								else {
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
								}								
							}
							else {
								DictCollectionTemp collectionTemp = DictCollectionTempLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, serverConfig.getGroupId());
								if (collectionTemp != null) {
									DictGroupTemp groupTemp = DictGroupTempLocalServiceUtil.getByGC_GI_DCI(groupCode, serverConfig.getGroupId(), collectionTemp.getDictCollectionId());									
									putDictGroupRestUrl.setLength(0);
									putDictGroupRestUrl.append(dictCollectionEndPoint);
									putDictGroupRestUrl.append("/" + groupCode);
									putDictGroupRestUrl.append("/dictgroups");
									
									params.put(PushDictGroupTerm.GROUP_CODE, groupCode);
									params.put(PushDictGroupTerm.GROUP_NAME, groupTemp.getGroupName());
									params.put(PushDictGroupTerm.GROUP_NAME_EN, groupTemp.getGroupNameEN());
									params.put(PushDictGroupTerm.GROUP_DESCRIPTION, groupTemp.getGroupDescription());
									
									if (!lstExcludes.contains(collectionCode)) {
										JSONObject resDictGroup = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
												rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
												configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
										
										if (SyncServerUtil.isSyncOk(resDictGroup.getInt(RESTFulConfiguration.STATUS))) {
											JSONObject resDictGroupAdd = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
													rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
													configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
											
											if (SyncServerUtil.isSyncOk(resDictGroupAdd.getInt(RESTFulConfiguration.STATUS))) {
							try {
													dictItemDataUtil.addDictgroupsDictItems(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, groupCode, itemCode, serviceContext);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
												catch (DuplicateCategoryException e) {
													_log.error(e);
													_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());									
												}
											}														
											else {
												_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
											}								
										}
										else {
											break;
										}
									}
									else {
									}
								
								}
								
							}
						}
						else {
							try {
								dictItemDataUtil.addDictgroupsDictItems(serverConfig.getUserId(), serverConfig.getGroupId(), collectionCode, groupCode, itemCode, serviceContext);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());							
							}
							catch (DuplicateCategoryException e) {
								_log.error(e);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());								
							}
						}
						}
					else if (pqueue.getMethod().equals(SyncServerTerm.METHOD_REMOVE_FROM_GROUP)) {
						putDictGroupRestUrl.setLength(0);
						putDictGroupRestUrl.append(dictCollectionEndPoint);
						putDictGroupRestUrl.append("/" + collectionCode);
						putDictGroupRestUrl.append("/dictgroups");
						putDictGroupRestUrl.append("/" + groupCode);
						putDictGroupRestUrl.append("/dictitems");
						putDictGroupRestUrl.append("/" + itemCode);
							
						if (!lstExcludes.contains(collectionCode)) {
							JSONObject resDictGroup = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE, "application/json",
									rootApiUrl, putDictGroupRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
									configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
												
							if (SyncServerUtil.isSyncOk(resDictGroup.getInt(RESTFulConfiguration.STATUS))) {
								try {
									dictItemDataUtil.deleteDictgroupsDictItems(serverConfig.getGroupId(), collectionCode, groupCode, itemCode, serviceContext);
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
								}
								catch (NotFoundException e) {
									_log.error(e);
									_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());									
								}
							}		
							else {
								break;
							}
						}
						else {
							try {
								dictItemDataUtil.deleteDictgroupsDictItems(serverConfig.getGroupId(), collectionCode, groupCode, itemCode, serviceContext);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());
							}
							catch (NotFoundException e) {
								_log.error(e);
								_syncQueueLocalService.deleteSyncQueue(pqueue.getSyncQueueId());								
						}
					}
					}	
				}				
				}
			}
		catch (Exception e) {
			_log.error(e);
		}
	}
	
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 15, TimeUnit.SECOND));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}

	@Reference
	private SyncQueueLocalService _syncQueueLocalService;

	@Reference
	private ServerConfigLocalService _serverConfigLocalService;

	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(SyncQueueProcessScheduler.class);

}
