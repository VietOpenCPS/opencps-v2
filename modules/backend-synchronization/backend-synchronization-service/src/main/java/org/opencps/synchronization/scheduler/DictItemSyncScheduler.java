package org.opencps.synchronization.scheduler;

import java.util.HashMap;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalService;
import org.opencps.synchronization.constants.PushDictItemTerm;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.PushDictItem;
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

@Component(immediate = true, service = DictItemSyncScheduler.class)
public class DictItemSyncScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("-------SYNC DICT ITEM-------");
		
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
					
			List<PushDictItem> lstSyncDicts = _pushDictItemLocalService.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			List<ServerConfig> lstServers = _serverConfigLocalService.getServerConfigs(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
						
			for (ServerConfig sc : lstServers) {
				String configs = sc.getConfigs();
				if (Validator.isNotNull(configs)) {
					try {
						JSONObject configObj = JSONFactoryUtil.createJSONObject(configs);
						if (configObj.has(SyncServerTerm.SERVER_TYPE) 
								&& configObj.getString(SyncServerTerm.SERVER_TYPE).equals(SyncServerTerm.SYNC_SERVER_TYPE)
								&& configObj.has(SyncServerTerm.SERVER_USERNAME)
								&& configObj.has(SyncServerTerm.SERVER_PASSWORD)
								&& configObj.has(SyncServerTerm.SERVER_URL)
								&& configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
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
	}
	
	private void synchronizeDictItem(List<PushDictItem> lstSyncDicts, ServerConfig serverConfig, JSONObject configObj) {
		_log.info("----SYNC DICT ITEM FROM SERVER " + serverConfig.getServerName() + "-------");
		InvokeREST rest = new InvokeREST();
		
		HashMap<String, String> properties = new HashMap<String, String>();
		HashMap<String, Object> params = new HashMap<String, Object>();

		String dictCollectionEndPoint = "/dictcollections";
		StringBuilder putDictItemRestUrl = new StringBuilder();
		String rootApiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
		
		if (rootApiUrl.charAt(rootApiUrl.length() - 1) == '/') {
			rootApiUrl = rootApiUrl.substring(0, rootApiUrl.length() - 2);
		}
		
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
			
			for (PushDictItem pitem : lstSyncDicts) {
				if (pitem.getMethod().equals(SyncServerTerm.METHOD_CREATE)) {
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
									
					JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
							rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
							configObj.getString(SyncServerTerm.SERVER_PASSWORD), properties, params, serviceContext);
					
					if (resDictItem.getInt(RESTFulConfiguration.STATUS) == 200) {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
					}													
				}
				else if (pitem.getMethod().equals(SyncServerTerm.METHOD_UPDATE)) {
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
									
					JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
							rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
							configObj.getString(SyncServerTerm.SERVER_PASSWORD), properties, params, serviceContext);
					
					if (resDictItem.getInt(RESTFulConfiguration.STATUS) == 200) {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
					}									
				}
				else if (pitem.getMethod().equals(SyncServerTerm.METHOD_DELETE)) {
					putDictItemRestUrl.setLength(0);
					putDictItemRestUrl.append(dictCollectionEndPoint);
					putDictItemRestUrl.append("/");
					putDictItemRestUrl.append(pitem.getCollectionCode());
					putDictItemRestUrl.append("/dictitems");
					putDictItemRestUrl.append("/" + pitem.getItemCode());
														
					JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE, "application/json",
							rootApiUrl, putDictItemRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
							configObj.getString(SyncServerTerm.SERVER_PASSWORD), properties, params, serviceContext);
					
					if (resDictItem.getInt(RESTFulConfiguration.STATUS) == 200) {
						_pushDictItemLocalService.deletePushDictItem(pitem.getPushDictItemId());
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
	private PushDictItemLocalService _pushDictItemLocalService;
	
	@Reference
	private ServerConfigLocalService _serverConfigLocalService;
	
	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(DictItemSyncScheduler.class);	
}
