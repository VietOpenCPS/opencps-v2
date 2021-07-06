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
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.*;

import java.util.Date;
import java.util.Map;

@Component(immediate = true, service = FakeCounterScheduler.class)
public class FakeCounterScheduler extends BaseMessageListener {
    private volatile boolean isRunning = false;
    private static String cronExpression = "0 0/1 * 1/1 * ? *";

    static class CounterPublishEvent {
        private volatile static int count = 0;
        public static int getCount(){
            return count;
        }
        public static synchronized void decreaseCount(){
            count--;
        }

        public static synchronized void setCount(int countNew){
            count = countNew;
        }
    }

    public static int getCount(){
        return CounterPublishEvent.getCount();
    }

    public static int resetCount(){
        CounterPublishEvent.count = 0;
        return getCount();
    }

    @Override
    protected void doReceive(Message message) throws Exception {
        if(isRunning) {
            return;
        }
        isRunning = true;
        _log.info("OPENCPS FAKE SCHEDULER START IN " + APIDateTimeUtils.convertDateToString(new Date()));



        while (true) {
            int i = CounterPublishEvent.getCount();
            i++;
            CounterPublishEvent.setCount(i);
            Thread.sleep(1000);
        }

//        _log.info("OPENCPS FAKE SCHEDULER FINISH IN " + APIDateTimeUtils.convertDateToString(new Date()));

    }


    @Activate
    @Modified
    protected void activate(Map<String,Object> properties) throws SchedulerException {
        String listenerClass = getClass().getName();
        Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null,
                cronExpression);
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

    private Log _log = LogFactoryUtil.getLog(FakeCounterScheduler.class);
}
