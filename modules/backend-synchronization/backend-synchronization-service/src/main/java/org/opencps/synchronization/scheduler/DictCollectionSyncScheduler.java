package org.opencps.synchronization.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalService;
import org.opencps.synchronization.constants.PushCollectionTerm;
import org.opencps.synchronization.constants.SyncServerTerm;
import org.opencps.synchronization.model.PushCollection;
import org.opencps.synchronization.rest.client.DictDataRestClient;
import org.opencps.synchronization.service.PushCollectionLocalService;
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

//@Component(immediate = true, service = DictCollectionSyncScheduler.class)
public class DictCollectionSyncScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
//		_log.info("PUSH DICT COLLECTION IS STARTING " + APIDateTimeUtils.convertDateToString(new Date()));
		
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
							List<PushCollection> lstSyncDicts = _pushCollectionLocalService.findByGroupId_ServerNo(sc.getGroupId(), sc.getServerNo(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
							synchronizeCollection(lstSyncDicts, sc, configObj);
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
//		_log.info("PUSH DICT COLLECTION HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
	}
	
	private void synchronizeCollection(List<PushCollection> lstSyncDicts, ServerConfig serverConfig, JSONObject configObj) {
		InvokeREST rest = new InvokeREST();
		
		HashMap<String, String> properties = new HashMap<String, String>();
		HashMap<String, Object> params = new HashMap<String, Object>();

		String dictCollectionEndPoint = "/dictcollections";
		StringBuilder putDictCollectionRestUrl = new StringBuilder();
		String rootApiUrl = configObj.getString(SyncServerTerm.SERVER_URL);
		
		if (rootApiUrl.charAt(rootApiUrl.length() - 1) == '/') {
			rootApiUrl = rootApiUrl.substring(0, rootApiUrl.length() - 2);
		}

		DictDataRestClient restClient = DictDataRestClient.fromJSONObject(configObj);
		
		try {
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setCompanyId(company.getCompanyId());
			
			for (PushCollection pcollection : lstSyncDicts) {
				boolean isFound = false;
				
				if (restClient != null) {
					if (restClient.getCollectionDetail(pcollection.getCollectionCode()) != null) {
						isFound = true;
					}
				}
	
				if (pcollection.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pcollection.getMethod().equals(SyncServerTerm.METHOD_CREATE)) {
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " IS STARING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictCollectionRestUrl.setLength(0);
					putDictCollectionRestUrl.append(dictCollectionEndPoint);
					putDictCollectionRestUrl.append("/" + pcollection.getCollectionCode());
					
					params.put(PushCollectionTerm.COLLECTION_CODE, pcollection.getCollectionCode());
					params.put(PushCollectionTerm.COLLECTION_NAME, pcollection.getCollectionName());
					params.put(PushCollectionTerm.COLLECTION_NAME_EN, pcollection.getCollectionNameEN());
					params.put(PushCollectionTerm.DESCRIPTION, pcollection.getDescription());
														
					if (!isFound) {
						JSONObject resDictCollection = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
								rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictCollection.getInt(RESTFulConfiguration.STATUS))) {
							_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());
	 					}																			
					}
					else {
						_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());						
					}
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
				}
				else if (pcollection.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pcollection.getMethod().equals(SyncServerTerm.METHOD_UPDATE)) {
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " IS STARING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictCollectionRestUrl.setLength(0);
					putDictCollectionRestUrl.append(dictCollectionEndPoint);
					putDictCollectionRestUrl.append("/" + pcollection.getCollectionCode());
					
					params.put(PushCollectionTerm.MODIFIED_DATE, pcollection.getModifiedDate().getTime());
					params.put(PushCollectionTerm.COLLECTION_CODE, pcollection.getCollectionCode());
					params.put(PushCollectionTerm.COLLECTION_NAME, pcollection.getCollectionName());
					params.put(PushCollectionTerm.COLLECTION_NAME_EN, pcollection.getCollectionNameEN());
					params.put(PushCollectionTerm.DESCRIPTION, pcollection.getDescription());
									
					if (isFound) {
						JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.POST, "application/json",
								rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
							_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());
						}															
					}
					else {
						_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());						
					}
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
				}
				else if (pcollection.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pcollection.getMethod().equals(SyncServerTerm.METHOD_DELETE)) {
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " IS STARING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictCollectionRestUrl.setLength(0);
					putDictCollectionRestUrl.append(dictCollectionEndPoint);
					putDictCollectionRestUrl.append("/" + pcollection.getCollectionCode());
														
					try {
						if (isFound) {
							JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.DELETE, "application/json",
									rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
									configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
							if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
								_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());
							}																					
						}
						else {
							_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());							
						}
					}
					catch (Exception e) {
						_log.error(e);
						_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());						
					}
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
				}
				else if (pcollection.getGroupId() != configObj.getLong(SyncServerTerm.SERVER_GROUP_ID) && pcollection.getMethod().equals(SyncServerTerm.METHOD_UPDATE_DATAFORM)) {
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " IS STARING " + APIDateTimeUtils.convertDateToString(new Date()));
					putDictCollectionRestUrl.setLength(0);
					putDictCollectionRestUrl.append(dictCollectionEndPoint);
					putDictCollectionRestUrl.append("/" + pcollection.getCollectionCode());
					putDictCollectionRestUrl.append("/dataform");
					
					params.put(PushCollectionTerm.DATA_FORM, pcollection.getDataForm());
					params.put(PushCollectionTerm.MODIFIED_DATE, pcollection.getModifiedDate().getTime());
									
					if (isFound) {
						JSONObject resDictItem = rest.callPostAPI(configObj.getLong(SyncServerTerm.SERVER_GROUP_ID), HttpMethods.PUT, "application/json",
								rootApiUrl, putDictCollectionRestUrl.toString(), configObj.getString(SyncServerTerm.SERVER_USERNAME),
								configObj.getString(SyncServerTerm.SERVER_SECRET), properties, params, serviceContext);
						
						if (SyncServerUtil.isSyncOk(resDictItem.getInt(RESTFulConfiguration.STATUS))) {
							_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());
						}															
					}
					else {
						_pushCollectionLocalService.deletePushCollection(pcollection.getPushCollectionId());						
					}
					_log.info("PUSH DICT COLLECTION FROM SERVER " + serverConfig.getServerName() + " HAS BEEN DONE " + APIDateTimeUtils.convertDateToString(new Date()));		
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
	private PushCollectionLocalService _pushCollectionLocalService;
	
	@Reference
	private ServerConfigLocalService _serverConfigLocalService;
	
	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(DictCollectionSyncScheduler.class);		
}
