package org.opencps.synchronization.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalService;
import org.opencps.synchronization.constants.PushDictItemTerm;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.PushDictItem;
import org.opencps.synchronization.rest.client.DictDataRestClient;
import org.opencps.synchronization.service.PushDictItemLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

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
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

//@Component(immediate = true, service = DictItemSyncScheduler.class)
public class DictItemSyncScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
//		_log.info("PUSH DICT ITEM IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
		
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
					
			List<ServerConfig> lstServers = _serverConfigLocalService.getServerConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						
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
								&& (configObj.has(SyncServerTerm.PUSH) && configObj.getBoolean(SyncServerTerm.PUSH))
								) {
							List<PushDictItem> lstSyncDicts = _pushDictItemLocalService.findByGroupId_ServerNo(sc.getGroupId(), sc.getServerNo(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
							synchronizeDictItem(lstSyncDicts, sc, configObj);
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
//		_log.info("PUSH DICT ITEM HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
	}
	
	private void synchronizeDictItem(List<PushDictItem> lstSyncDicts, ServerConfig serverConfig, JSONObject configObj) {
		InvokeREST rest = new InvokeREST();
		
		HashMap<String, String> properties = new HashMap<String, String>();
		HashMap<String, Object> params = new HashMap<String, Object>();

		String dictCollectionEndPoint = "/dictcollections";
		StringBuilder putDictItemRestUrl = new StringBuilder();
		String rootApiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
		
		if (rootApiUrl.charAt(rootApiUrl.length() - 1) == '/') {
			rootApiUrl = rootApiUrl.substring(0, rootApiUrl.length() - 2);
		}
		
		DictDataRestClient restClient = DictDataRestClient.fromJSONObject(configObj);
		
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
			
			for (PushDictItem pitem : lstSyncDicts) {
				boolean isFound = false;
				
				if (restClient != null) {
					if (restClient.getItemDetail(pitem.getCollectionCode(), pitem.getCollectionCode()) != null) {
						isFound = true;
					}
				}
	
				if (pitem.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pitem.getMethod().equals(SyncServerTerm.METHOD_CREATE)) {
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictItemRestUrl.setLength(0);
					putDictItemRestUrl.append(dictCollectionEndPoint);
					putDictItemRestUrl.append("/");
					putDictItemRestUrl.append(pitem.getCollectionCode());
					putDictItemRestUrl.append("/dictitems");
					putDictItemRestUrl.append("/" + pitem.getItemCode());
					
					params.put(PushDictItemTerm.COLLECTION_CODE, pitem.getCollectionCode());
					params.put(PushDictItemTerm.ITEM_NAME, pitem.getItemName());
					params.put(PushDictItemTerm.ITEM_NAME_EN, pitem.getItemNameEN());
					params.put(PushDictItemTerm.ITEM_DESCRIPTION, pitem.getItemDescription());
					params.put(PushDictItemTerm.PARENT_ITEM_CODE, pitem.getParentItemCode());
					params.put(PushDictItemTerm.SIBLING, pitem.getSibling());
									
					if (isFound) {
						JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
								rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
							_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
						}																			
					}
					else {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());						
					}
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
				}
				else if (pitem.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pitem.getMethod().equals(SyncServerTerm.METHOD_UPDATE)) {
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictItemRestUrl.setLength(0);
					putDictItemRestUrl.append(dictCollectionEndPoint);
					putDictItemRestUrl.append("/");
					putDictItemRestUrl.append(pitem.getCollectionCode());
					putDictItemRestUrl.append("/dictitems");
					putDictItemRestUrl.append("/" + pitem.getItemCode());
					
					params.put(PushDictItemTerm.MODIFIED_DATE, pitem.getModifiedDate().getTime());
					params.put(PushDictItemTerm.COLLECTION_CODE, pitem.getCollectionCode());
					params.put(PushDictItemTerm.ITEM_NAME, pitem.getItemName());
					params.put(PushDictItemTerm.ITEM_NAME_EN, pitem.getItemNameEN());
					params.put(PushDictItemTerm.ITEM_DESCRIPTION, pitem.getItemDescription());
					params.put(PushDictItemTerm.PARENT_ITEM_CODE, pitem.getParentItemCode());
					params.put(PushDictItemTerm.SIBLING, pitem.getSibling());
								
					if (isFound) {
						JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
								rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
							_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
						}															
					}
					else {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());						
					}
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
				}
				else if (pitem.getMethod().equals(SyncServerTerm.METHOD_DELETE)) {
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictItemRestUrl.setLength(0);
					putDictItemRestUrl.append(dictCollectionEndPoint);
					putDictItemRestUrl.append("/");
					putDictItemRestUrl.append(pitem.getCollectionCode());
					putDictItemRestUrl.append("/dictitems");
					putDictItemRestUrl.append("/" + pitem.getItemCode());
											
					if (isFound) {
						JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE, "application/json",
								rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
							_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
						}																				
					}
					else {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());						
					}
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
				}
				else if (pitem.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pitem.getMethod().equals(SyncServerTerm.METHOD_UPDATE_METADATA)) {
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictItemRestUrl.setLength(0);
					putDictItemRestUrl.append(dictCollectionEndPoint);
					putDictItemRestUrl.append("/");
					putDictItemRestUrl.append(pitem.getCollectionCode());
					putDictItemRestUrl.append("/dictitems");
					putDictItemRestUrl.append("/" + pitem.getItemCode());
					putDictItemRestUrl.append("/metadata");
					
					params.put(PushDictItemTerm.META_DATA, pitem.getMetaData());
									
					if (isFound) {
						JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.PUT, "application/json",
								rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
							_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
						}															
					}
					else {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());						
					}
					_log.info("PUSH DICT ITEM FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
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
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 1, TimeUnit.SECOND));
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
	private PushDictItemLocalService _pushDictItemLocalService;
	
	@Reference
	private ServerConfigLocalService _serverConfigLocalService;
	
	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(DictItemSyncScheduler.class);	
}
