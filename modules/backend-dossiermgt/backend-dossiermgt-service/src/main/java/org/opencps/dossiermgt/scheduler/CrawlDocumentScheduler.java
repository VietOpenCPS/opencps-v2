package org.opencps.dossiermgt.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.QLVBIntegrationAction;
import org.opencps.dossiermgt.action.impl.QLVBIntegrationActionImpl;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(immediate = true, service = CrawlDocumentScheduler.class)
public class CrawlDocumentScheduler extends BaseMessageListener {
    private volatile boolean isRunning = false;
    private static final Boolean ENABLE_JOB = Validator.isNotNull(PropsUtil.get("org.opencps.frequency.enable"))
            ? Boolean.valueOf(PropsUtil.get("org.opencps.frequency.enable")) : false;
    private static final String API_SYNC_QLVB = "API_SYNC_QLVB";
    @Override
    protected void doReceive(Message message) throws Exception {
        if (!isRunning && ENABLE_JOB) {
            isRunning = true;
        }
        else {
            return;
        }
        _log.info("-----Start job crawl document frequency---");

        try {
            _log.info("Crawl document frequency at : " + APIDateTimeUtils.convertDateToString(new Date()));
            List<ServerConfig> listConfig = ServerConfigLocalServiceUtil
                    .getByServerAndProtocol(API_SYNC_QLVB, API_SYNC_QLVB);

            if(Validator.isNull(listConfig) || listConfig.isEmpty()) {
                throw new Exception("No server config");
            }

            ServerConfig serverConfig = listConfig.get(0);
            if(Validator.isNull(serverConfig)) {
                throw new Exception("No server config");
            }

            QLVBIntegrationAction docAction = new QLVBIntegrationActionImpl(serverConfig);
            docAction.getDocEOfficeTTTT();

            _log.info("End crawl document frequency!!!");
        } catch (Exception e){
            _log.error("Error crawl document frequency: " + e.getMessage());
        }
        isRunning = false;
    }


    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 6, TimeUnit.MINUTE);

        _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
        _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);

        if (_initialized) {
            deactivate();
        }

        _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
        _initialized = true;
    }

    @Deactivate
    protected void deactivate() {
        if (_initialized) {
            try {
                _schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
            } catch (SchedulerException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn("Unable to unschedule trigger", se);
                }
            }

            _schedulerEngineHelper.unregister(this);
        }
        _initialized = false;
        isRunning = false;
    }

    protected StorageType getStorageType() {
        if (_schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
        }

        return StorageType.PERSISTED;
    }

    @Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
    protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
    }

    @Reference(unbind = "-")
    protected void setTriggerFactory(TriggerFactory triggerFactory) {
        _triggerFactory = triggerFactory;
    }

    @Reference(unbind = "-")
    protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
        _schedulerEngineHelper = schedulerEngineHelper;
    }

    private SchedulerEngineHelper _schedulerEngineHelper;
    private TriggerFactory _triggerFactory;
    private volatile boolean _initialized;
    private SchedulerEntryImpl _schedulerEntryImpl = null;

    private Log _log = LogFactoryUtil.getLog(CrawlDocumentScheduler.class);

}