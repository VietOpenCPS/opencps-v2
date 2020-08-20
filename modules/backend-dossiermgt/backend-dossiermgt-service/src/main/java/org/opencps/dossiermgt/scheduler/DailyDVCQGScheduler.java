package org.opencps.dossiermgt.scheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DVCQGIntegrationAction;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.Map;

@Component(immediate = true, service = DailyDVCQGScheduler.class)
public class DailyDVCQGScheduler extends BaseMessageListener {
    private volatile boolean isRunning = false;

    @Override
    protected void doReceive(Message message) throws Exception {
        System.out.println("Sync data votes to DVCQG...");

        if (!OpenCPSConfigUtil.isCanSyncToDVCQG()) {
            System.out.println("Syn data to DVCQG has stopped because of this server is mcdt");
            return;
        }
        System.out.println("Server has permission to sync DVCQG");

        System.out.println("isRunning variable: " + isRunning);
        if (!isRunning) {
            isRunning = true;
        } else {
            return;
        }

        try {
            System.out.println("Daily sync data to DVCQG  : " + APIDateTimeUtils.convertDateToString(new Date()));
            _log.info("Daily sync data to DVCQG  : " + APIDateTimeUtils.convertDateToString(new Date()));
            DVCQGIntegrationAction action = new DVCQGIntegrationActionImpl();
            action.syncSummaryVote();
        } catch (Exception e) {
            System.out.println("---ERROR SYNC: " + e.getMessage());
            _log.error(e.getMessage());
        } finally {
            isRunning = false;
            System.out.println("isRunning variable finally: " + isRunning);
        }
    }

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 20, TimeUnit.HOUR);

        _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
        _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);

//		  _schedulerEntryImpl.setTrigger(jobTrigger);

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

    private Log _log = LogFactoryUtil.getLog(DailyDVCQGScheduler.class);
}