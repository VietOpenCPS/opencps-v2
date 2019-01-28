package org.opencps.communication.scheduler;

import java.util.Date;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.service.NotificationQueueLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

//@Component(immediate = true, service = NotificationScheduler.class)
public class NotificationScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("OpenCPS NOTIFICATION QUEUE IS  : " + APIDateTimeUtils.convertDateToString(new Date()));
	
		_log.info("OpenCPS NOTIFICATION QUEUE IS HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));		
	}
	
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 1, TimeUnit.MINUTE));
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

	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(NotificationScheduler.class);	
}

//public class NotificationScheduler extends BaseSchedulerEntryMessageListener {
//	private Log _log = LogFactoryUtil.getLog(NotificationScheduler.class);
//
//	@Override
//	protected void doReceive(Message message) {
//
//		_log.info("NOTIFICATION QUEUE START  : " + APIDateTimeUtils.convertDateToString(new Date()));
//		try {
//			doGarbageCollector(message);
//		} catch (Exception e) {
//			_log.error(e);
//		}
//		_log.info("NOTIFICATION QUEUE END : " + APIDateTimeUtils.convertDateToString(new Date()));
//
//	}
//
//	private void doGarbageCollector(Message message) {
//
//		// TODO Auto-generated method stub
//		_log.info("doGarbageCollector: ");
//		try {
//			_notificationQueueLocalService.deleteExpiredNotificationQueue(new Date());
//		} catch (Exception e) {
//			_log.error(e);
//		}
//	}
//
//	@Activate
//	@Modified
//	protected void activate() {
//
//		schedulerEntryImpl.setTrigger(
//			TriggerFactoryUtil.createTrigger(
//				getEventListenerClass(), getEventListenerClass(), 2,
//				TimeUnit.MINUTE));
//		_schedulerEngineHelper.register(
//			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
//	}
//
//	@Deactivate
//	protected void deactivate() {
//
//		_schedulerEngineHelper.unregister(this);
//	}
//
//	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
//	protected void setModuleServiceLifecycle(
//		ModuleServiceLifecycle moduleServiceLifecycle) {
//
//	}
//
//	@Reference(unbind = "-")
//	protected void setSchedulerEngineHelper(
//		SchedulerEngineHelper schedulerEngineHelper) {
//
//		_schedulerEngineHelper = schedulerEngineHelper;
//	}
//
//	@Reference(unbind = "-")
//	protected void setTriggerFactory(TriggerFactory triggerFactory) {
//
//	}
//
//	private SchedulerEngineHelper _schedulerEngineHelper;
//
//	@Reference
//	private NotificationQueueLocalService _notificationQueueLocalService;
//
//}
