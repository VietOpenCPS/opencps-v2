package org.opencps.synctracking.scheduler;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.*;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;
import java.util.Map;

//@Component(immediate = true, service = FakeCounterScheduler.class)
public class FakeCounterScheduler extends BaseMessageListener {
    private static volatile boolean isRunning;
    private static final Integer timeRunScheduler = 1;
    @Override
    protected void doReceive(Message message) throws Exception {
        if(isRunning) {
            return;
        }
        Counter currentCounter = CounterLocalServiceUtil.fetchCounter("opencps.dossier#100349#1082020");
        _log.info("======Scheduler-Current Id in Scheduler: "  + currentCounter.getCurrentId());
        long _counterNumber = currentCounter.getCurrentId() + 1;
        currentCounter.setCurrentId(_counterNumber);
//        Counter newCounter = CounterLocalServiceUtil.updateCounter(currentCounter);

//        _log.info("======Scheduler-Current Id in Schedulerafter Tang: "  + newCounter.getCurrentId());
        isRunning = false;
    }


    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, timeRunScheduler, TimeUnit.SECOND);

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

    /**
     * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
     * @return StorageType The storage type to use.
     */
    protected StorageType getStorageType() {
        if (_schedulerEntryImpl instanceof StorageTypeAware) {
            return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
        }

        return StorageType.PERSISTED;
    }

    /**
     * setModuleServiceLifecycle: So this requires some explanation...
     *
     * OSGi will start a component once all of it's dependencies are satisfied.  However, there
     * are times where you want to hold off until the portal is completely ready to go.
     *
     * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
     * component which will not be available until, surprise surprise, the portal has finished
     * initializing.
     *
     * With this reference, this component activation waits until portal initialization has completed.
     * @param moduleServiceLifecycle
     */
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

    private Log _log = LogFactoryUtil.getLog(FakeCounterScheduler.class);
}
