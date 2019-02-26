package org.opencps.synchronization.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalService;
import org.opencps.datamgt.action.DictcollectionInterface;
import org.opencps.datamgt.action.impl.DictCollectionActions;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictCollectionLocalService;
import org.opencps.datamgt.service.DictGroupLocalService;
import org.opencps.datamgt.service.DictItemGroupLocalService;
import org.opencps.datamgt.service.DictItemLocalService;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.PushCollection;
import org.opencps.synchronization.model.PushDictGroup;
import org.opencps.synchronization.model.PushDictItem;
import org.opencps.synchronization.service.PushCollectionLocalService;
import org.opencps.synchronization.service.PushDictGroupLocalService;
import org.opencps.synchronization.service.PushDictItemLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

//@Component(immediate = true, service = DataPullScheduler.class)
public class DataPullScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		try {	
			List<ServerConfig> lstServers = _serverConfigLocalService.getServerConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
			
			for (ServerConfig sc : lstServers) {
				String configs = sc.getConfigs();
				if (Validator.isNotNull(configs)) {
					try {
						JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
						if (configObj.has(SyncServerTerm.SERVER_TYPE) 
								&& configObj.getString(SyncServerTerm.SERVER_TYPE).equals(SyncServerTerm.SYNC_SERVER_TYPE)
								&& configObj.has(SyncServerTerm.SERVER_USERNAME)
								&& configObj.has(SyncServerTerm.SERVER_SECRET)
								&& configObj.has(SyncServerTerm.SERVER_URL)
								&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)
								&& (configObj.has(SyncServerTerm.PULL) && configObj.getBoolean(SyncServerTerm.PULL))
								) {
							_log.info("PULL DICTIONARY DATA FROM SERVER " + sc.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
							Date pullDictCollectionDate = pullDictCollection(sc, configObj);
							Date pullDictItemDate = pullDictItem(sc, configObj);
							Date pullDictGroupDate = pullDictGroup(sc, configObj);
							Date pullDictItemGroupDate = pullDictItemGroup(sc, configObj);
							Date maxModifiedDate = pullDictCollectionDate;
							if (maxModifiedDate.compareTo(pullDictItemDate) < 0) {
								maxModifiedDate = pullDictItemDate;
							}
							if (maxModifiedDate.compareTo(pullDictGroupDate) < 0) {
								maxModifiedDate = pullDictGroupDate;
							}
							if (maxModifiedDate.compareTo(pullDictItemGroupDate) < 0) {
								maxModifiedDate = pullDictItemGroupDate;
							}
							serviceContext.setUserId(sc.getUserId());
							if (Validator.isNull(sc.getLastSync()) || (sc.getLastSync().compareTo(maxModifiedDate) < 0)) {
								sc.setLastSync(maxModifiedDate);
								_serverConfigLocalService.updateLastSync(sc.getServerConfigId(), maxModifiedDate, serviceContext);
							}
							_log.info("PULL DICTIONARY DATA FROM SERVER " + sc.getServerName() + " HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));		
						}
					}
					catch (Exception e) {
						_log.error(e);
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}
	
	private Date pullDictCollection(ServerConfig serverConfig, JSONObject configObj) {
//		_log.info("PULL DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
		Date maxModifiedDate = new Date(0);
		
		try {
			InvokeREST rest = new InvokeREST();
			HashMap<String, String> properties = new HashMap<String, String>();
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
				
			String dataEndpoint = "/dictcollections/sync";
			if (Validator.isNotNull(serverConfig.getLastSync())) {
				dataEndpoint += "?lastSync=" + serverConfig.getLastSync().getTime();
			}
			
			JSONObject resDictCollection = rest.callAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.GET, "application/json",
					configObj.getString(SyncServerTerm.SERVER_URL), dataEndpoint, configObj.getString(SyncServerTerm.SERVER_USERNAME),
					configObj.getString(SyncServerTerm.SERVER_SECRET), properties, serviceContext);
			
			JSONObject jsData = JSONFactoryUtil
					.createJSONObject(resDictCollection.getString(RESTFulConfiguration.MESSAGE));

			JSONArray jsArrayData = JSONFactoryUtil.createJSONArray(jsData.getString("data"));
			for (int i = 0; i < jsArrayData.length(); i++) {
				JSONObject object = jsArrayData.getJSONObject(i);
				String collectionName = object.getString("collectionName");
				String collectionCode = object.getString("collectionCode");
				long modifiedDateTime = object.getLong("modifiedDate");
				long createDateTime = object.getLong("createDate");
				String collectionNameEN = object.getString("collectionNameEN");
				String description = object.getString("description");
				String dataForm = object.getString("dataForm");
				
				serviceContext.setUserId(serverConfig.getUserId());
				serviceContext.setScopeGroupId(serverConfig.getGroupId());
				serviceContext.setSignedIn(true);
				
				if (object.getLong("modifiedDate") > maxModifiedDate.getTime()) {
					maxModifiedDate.setTime(object.getLong("modifiedDate"));
				}
				DictCollection oldCollection = null;
				
				try {
					oldCollection = _dictCollectionLocalService.fetchByF_dictCollectionCode(object.getString("collectionCode"), serverConfig.getGroupId());
				}
				catch (Exception e) {
					_log.error(e);
				}
				try {
					if (oldCollection == null) {
						PushCollection foundDeleteCollection = null;
						try {
							foundDeleteCollection = _pushCollectionLocalService.findByCollectionCode_Method(serverConfig.getGroupId(), collectionCode, SyncServerTerm.METHOD_DELETE);
						}
						catch (Exception e) {
							_log.error(e);
						}
						
						if (foundDeleteCollection == null) {
							DictCollection newCollection = _dictCollectionLocalService.addDictCollection(serverConfig.getUserId(), 
									serverConfig.getGroupId(), 
									collectionCode, 
									collectionName, 
									collectionNameEN, 
									description, serviceContext);
								
								newCollection.setModifiedDate(new Date(modifiedDateTime));
								newCollection.setCreateDate(new Date(createDateTime));
								newCollection.setDataForm(dataForm);
								_dictCollectionLocalService.updateDictCollection(newCollection);							
						}
					}
					else {
						Date modifiedDate = new Date(modifiedDateTime);
						if (modifiedDate.compareTo(oldCollection.getModifiedDate()) > 0) {
							oldCollection.setModifiedDate(new Date(modifiedDateTime));
							oldCollection.setCreateDate(new Date(createDateTime));
							oldCollection.setDataForm(dataForm);
							oldCollection.setCollectionCode(collectionCode);
							oldCollection.setCollectionName(collectionName);
							oldCollection.setCollectionNameEN(collectionNameEN);
							oldCollection.setDescription(description);
							_dictCollectionLocalService.updateDictCollection(oldCollection);							
						}
					}					
				}
				catch (Exception e) {
					_log.error(e);
				}
			}			
		}
		catch (Exception e) {
			_log.error(e);
		}
		
//		_log.info("PULL DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));
		
		return maxModifiedDate;
	}
	
	private Date pullDictGroup(ServerConfig serverConfig, JSONObject configObj) {
//		_log.info("PULL DICT GROUP FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
		Date maxModifiedDate = new Date(0);
		
		try {
			InvokeREST rest = new InvokeREST();
			HashMap<String, String> properties = new HashMap<String, String>();
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
				
			String dataEndpoint = "/dictcollections/all/dictgroups/sync";
			if (Validator.isNotNull(serverConfig.getLastSync())) {
				dataEndpoint += "?lastSync=" + serverConfig.getLastSync().getTime();
			}
			
			JSONObject resDictCollection = rest.callAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.GET, "application/json",
					configObj.getString(SyncServerTerm.SERVER_URL), dataEndpoint, configObj.getString(SyncServerTerm.SERVER_USERNAME),
					configObj.getString(SyncServerTerm.SERVER_SECRET), properties, serviceContext);
			
			JSONObject jsData = JSONFactoryUtil
					.createJSONObject(resDictCollection.getString(RESTFulConfiguration.MESSAGE));

			JSONArray jsArrayData = JSONFactoryUtil.createJSONArray(jsData.getString("data"));
			for (int i = 0; i < jsArrayData.length(); i++) {
				JSONObject object = jsArrayData.getJSONObject(i);
	
				String collectionCode = object.getString("collectionCode");
				long modifiedDateTime = object.getLong("modifiedDate");
				long createDateTime = object.getLong("createDate");
				String groupCode = object.getString("groupCode");
				String groupName= object.getString("groupName");
				String groupNameEN = object.getString("groupNameEN");
				String groupDescription = object.getString("groupDescription");
				
				serviceContext.setUserId(serverConfig.getUserId());
				serviceContext.setScopeGroupId(serverConfig.getGroupId());
				serviceContext.setSignedIn(true);
				
				if (object.getLong("modifiedDate") > maxModifiedDate.getTime()) {
					maxModifiedDate.setTime(object.getLong("modifiedDate"));
				}
				DictGroup oldGroup = null;
				
				try {
					oldGroup = _dictGroupLocalService.fetchByF_DictGroupCode(groupCode, serverConfig.getGroupId());
				}
				catch (Exception e) {
					_log.error(e);
				}
				
				DictCollection collection = null;
				try {
					collection = _dictCollectionLocalService.fetchByF_dictCollectionCode(collectionCode, serverConfig.getGroupId());
				}
				catch (Exception e) {
					_log.error(e);
				}
				try {
					if (oldGroup == null) {
						PushDictGroup foundDeleteDictGroup = null;
						try {
							foundDeleteDictGroup = _pushDictGroupLocalService.findByCollectionCode_GroupCode_Method(serverConfig.getGroupId(), collectionCode, groupCode, SyncServerTerm.METHOD_DELETE);
						}
						catch (Exception e) {
							_log.error(e);
						}
						
						if (foundDeleteDictGroup == null) {
							if (collection != null) {
								DictGroup newDictGroup = _dictGroupLocalService.addDictGroup(
										serverConfig.getUserId(), 
										serverConfig.getGroupId(), 
										collection.getDictCollectionId(), 
										groupCode, 
										groupName, 
										groupNameEN, 
										groupDescription, 
										serviceContext);
									
								newDictGroup.setModifiedDate(new Date(modifiedDateTime));
								newDictGroup.setCreateDate(new Date(createDateTime));
								_dictGroupLocalService.updateDictGroup(newDictGroup);															
							}
						}
					}
					else {
						Date modifiedDate = new Date(modifiedDateTime);
						if (modifiedDate.compareTo(oldGroup.getModifiedDate()) > 0) {
							oldGroup.setModifiedDate(new Date(modifiedDateTime));
							oldGroup.setCreateDate(new Date(createDateTime));
							oldGroup.setGroupCode(groupCode);
							oldGroup.setGroupName(groupName);
							oldGroup.setGroupNameEN(groupNameEN);
							oldGroup.setGroupDescription(groupDescription);
							if (collection != null) {
								oldGroup.setDictCollectionId(collection.getDictCollectionId());
							}
							_dictGroupLocalService.updateDictGroup(oldGroup);							
						}
					}					
				}
				catch (Exception e) {
					_log.error(e);
				}
			}			
		}
		catch (Exception e) {
			_log.error(e);
		}
		
//		_log.info("PULL DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));
		
		return maxModifiedDate;
	}
	
	private Date pullDictItemGroup(ServerConfig serverConfig, JSONObject configObj) {
//		_log.info("PULL DICT ITEM GROUP FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
		Date maxModifiedDate = new Date(0);
		
		try {
			InvokeREST rest = new InvokeREST();
			HashMap<String, String> properties = new HashMap<String, String>();
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
				
			String dataEndpoint = "/dictcollections/all/dictgroups/all/dictitems/sync";
			if (Validator.isNotNull(serverConfig.getLastSync())) {
				dataEndpoint += "?lastSync=" + serverConfig.getLastSync().getTime();
			}
			
			JSONObject resDictItemGroup = rest.callAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.GET, "application/json",
					configObj.getString(SyncServerTerm.SERVER_URL), dataEndpoint, configObj.getString(SyncServerTerm.SERVER_USERNAME),
					configObj.getString(SyncServerTerm.SERVER_SECRET), properties, serviceContext);
			
			JSONObject jsData = JSONFactoryUtil
					.createJSONObject(resDictItemGroup.getString(RESTFulConfiguration.MESSAGE));

			JSONArray jsArrayData = JSONFactoryUtil.createJSONArray(jsData.getString("data"));
			for (int i = 0; i < jsArrayData.length(); i++) {
				JSONObject object = jsArrayData.getJSONObject(i);
	
				String collectionCode = object.getString("collectionCode");
				long modifiedDateTime = object.getLong("modifiedDate");
				long createDateTime = object.getLong("createDate");
				String groupCode = object.getString("groupCode");
				String itemCode = object.getString("itemCode");
				
				serviceContext.setUserId(serverConfig.getUserId());
				serviceContext.setScopeGroupId(serverConfig.getGroupId());
				serviceContext.setSignedIn(true);
				
				if (object.getLong("modifiedDate") > maxModifiedDate.getTime()) {
					maxModifiedDate.setTime(object.getLong("modifiedDate"));
				}
				
				DictItemGroup oldDictItemGroup = null;
				DictCollection collection = null;
				DictGroup group = null;
				DictItem item = null;
				
				try {
					collection = _dictCollectionLocalService.fetchByF_dictCollectionCode(collectionCode, serverConfig.getGroupId());
					group = _dictGroupLocalService.fetchByF_DictGroupCode(groupCode, serverConfig.getGroupId());
					item = _dictItemLocalService.fetchByF_dictItemCode(itemCode, collection.getDictCollectionId(), serverConfig.getGroupId());
					
					oldDictItemGroup = _dictItemGroupLocalService.fetchByF_dictItemId_dictGroupId(serverConfig.getGroupId(), group.getDictGroupId(), item.getDictItemId());
				}
				catch (Exception e) {
					_log.error(e);
				}
				
				try {
					if (oldDictItemGroup == null) {
						PushDictGroup foundDeleteDictGroup = null;
						try {
							foundDeleteDictGroup =  _pushDictGroupLocalService.findByCollectionCode_GroupCode_ItemCode_Method(serverConfig.getGroupId(), collectionCode, groupCode, itemCode, SyncServerTerm.METHOD_REMOVE_FROM_GROUP);
						}
						catch (Exception e) {
							_log.error(e);
						}
						
						if (foundDeleteDictGroup == null) {
							if (collection != null && group != null && item != null) {
								DictItemGroup newDictItemGroup = _dictItemGroupLocalService.addDictItemGroup(
										serverConfig.getUserId(), 
										serverConfig.getGroupId(), 
										group.getDictGroupId(), 
										item.getDictItemId(), 
										groupCode, 
										serviceContext);
									
								newDictItemGroup.setModifiedDate(new Date(modifiedDateTime));
								newDictItemGroup.setCreateDate(new Date(createDateTime));
								_dictItemGroupLocalService.updateDictItemGroup(newDictItemGroup);															
							}
						}
					}
					else {
						Date modifiedDate = new Date(modifiedDateTime);
						if (modifiedDate.compareTo(oldDictItemGroup.getModifiedDate()) > 0) {
							oldDictItemGroup.setModifiedDate(new Date(modifiedDateTime));
							oldDictItemGroup.setCreateDate(new Date(createDateTime));
							if (group != null)
								oldDictItemGroup.setDictGroupId(group.getDictGroupId());
							if (item != null)
								oldDictItemGroup.setDictItemId(item.getDictItemId());
							_dictItemGroupLocalService.updateDictItemGroup(oldDictItemGroup);							
						}
					}					
				}
				catch (Exception e) {
					_log.error(e);
				}
			}			
		}
		catch (Exception e) {
			_log.error(e);
		}
		
//		_log.info("PULL DICT ITEM GROUP FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));
		
		return maxModifiedDate;
	}

	private Date pullDictItem(ServerConfig serverConfig, JSONObject configObj) {
//		_log.info("PULL DICT ITEM FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
		Date maxModifiedDate = new Date(0);
		
		try {
			InvokeREST rest = new InvokeREST();
			HashMap<String, String> properties = new HashMap<String, String>();
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
				
			String dataEndpoint = "/dictcollections/all/dictitems/sync";
			if (Validator.isNotNull(serverConfig.getLastSync())) {
				dataEndpoint += "?lastSync=" + serverConfig.getLastSync().getTime();
			}
			
			JSONObject resDictCollection = rest.callAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.GET, "application/json",
					configObj.getString(SyncServerTerm.SERVER_URL), dataEndpoint, configObj.getString(SyncServerTerm.SERVER_USERNAME),
					configObj.getString(SyncServerTerm.SERVER_SECRET), properties, serviceContext);
			
			JSONObject jsData = JSONFactoryUtil
					.createJSONObject(resDictCollection.getString(RESTFulConfiguration.MESSAGE));

			JSONArray jsArrayData = JSONFactoryUtil.createJSONArray(jsData.getString("data"));
			DictcollectionInterface dictItemDataUtil = new DictCollectionActions();
			
			for (int i = 0; i < jsArrayData.length(); i++) {
				JSONObject object = jsArrayData.getJSONObject(i);
				long modifiedDateTime = object.getLong("modifiedDate");
				long createDateTime = object.getLong("createDate");
				String dictCollectionCode = object.getString("dictCollectionCode");
				String itemCode = object.getString("itemCode");
				String itemName = object.getString("itemName");
				String itemNameEN = object.getString("itemNameEN");
				String itemDescription = object.getString("itemDescription");
				String parentItemCode = object.getString("parentItemCode");
				String treeIndex = object.getString("treeIndex");
				String sibling = object.getString("sibling");
				int level = object.getInt("level");
				String metaData = object.getString("metaData");
				
				serviceContext.setUserId(serverConfig.getUserId());
				serviceContext.setScopeGroupId(serverConfig.getGroupId());
				serviceContext.setSignedIn(true);
				
				if (object.getLong("modifiedDate") > maxModifiedDate.getTime()) {
					maxModifiedDate.setTime(object.getLong("modifiedDate"));
				}
				DictItem oldItem = null;
				
				try {
					oldItem = dictItemDataUtil.getDictItemByItemCode(dictCollectionCode, itemCode, serverConfig.getGroupId(), serviceContext);					
				}
				catch (Exception e) {
					_log.error(e);
				}
				try {
					if (oldItem == null) {
						PushDictItem foundDeleteDictItem = null;
						try {
							foundDeleteDictItem = _pushDictItemLocalService.findByCollectionCode_ItemCode_Method(serverConfig.getGroupId(), dictCollectionCode, itemCode, SyncServerTerm.METHOD_DELETE);
						}
						catch (Exception e) {
							_log.error(e);
						}
						if (foundDeleteDictItem == null) {
							DictItem newItem = dictItemDataUtil.addDictItems(
									serverConfig.getUserId(), 
									serverConfig.getGroupId(), 
									dictCollectionCode, 
									parentItemCode, 
									itemCode, 
									itemName, 
									itemNameEN, 
									itemDescription, 
									sibling, 
									level, 
									metaData, 
									serviceContext);
							
							newItem.setTreeIndex(treeIndex);
							newItem.setModifiedDate(new Date(modifiedDateTime));
							newItem.setCreateDate(new Date(createDateTime));
							_dictItemLocalService.updateDictItem(newItem);							
						}
					}
					else {
						DictCollection collection = _dictCollectionLocalService.fetchByF_dictCollectionCode(dictCollectionCode, serverConfig.getGroupId());
						DictItem parentItem = null;
						
						try {
							if (Validator.isNotNull(parentItemCode))
								parentItem = _dictItemLocalService.fetchByF_dictItemCode(parentItemCode, collection.getDictCollectionId(), serverConfig.getGroupId());
						}
						catch (Exception e) {
							_log.error(e);
						}
						Date modifiedDate = new Date(modifiedDateTime);
						if (modifiedDate.compareTo(oldItem.getModifiedDate()) > 0) {
							oldItem.setModifiedDate(new Date(modifiedDateTime));
							oldItem.setCreateDate(new Date(createDateTime));
							oldItem.setItemCode(itemCode);
							oldItem.setItemName(itemName);
							oldItem.setItemNameEN(itemNameEN);
							oldItem.setItemDescription(itemDescription);
							oldItem.setSibling(sibling);
							oldItem.setTreeIndex(treeIndex);
							oldItem.setLevel(level);
							oldItem.setMetaData(metaData);
							oldItem.setDictCollectionId(collection.getDictCollectionId());
							if (parentItem != null) {
								oldItem.setParentItemId(parentItem.getDictItemId());							
							}
							else {
								oldItem.setParentItemId(0);
							}	
							
							_dictItemLocalService.updateDictItem(oldItem);
						}						
					}					
				}
				catch (Exception e) {
					_log.error(e);
				}
			}			
		}
		catch (Exception e) {
			_log.error(e);
		}
		
//		_log.info("PULL DICT ITEM FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));
		
		return maxModifiedDate;
	}	
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 45, TimeUnit.SECOND));
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
	private DictItemLocalService _dictItemLocalService;
	
	@Reference
	private DictCollectionLocalService _dictCollectionLocalService;
	
	@Reference
	private DictGroupLocalService _dictGroupLocalService;
	
	@Reference
	private ServerConfigLocalService _serverConfigLocalService;
	
	@Reference
	private PushCollectionLocalService _pushCollectionLocalService;
	
	@Reference
	private PushDictItemLocalService _pushDictItemLocalService;
	
	@Reference
	private PushDictGroupLocalService _pushDictGroupLocalService;
	
	@Reference
	private DictItemGroupLocalService _dictItemGroupLocalService;
	
	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(DataPullScheduler.class);	
	
}
