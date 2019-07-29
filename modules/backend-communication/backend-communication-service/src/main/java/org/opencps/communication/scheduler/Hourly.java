package org.opencps.communication.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.communication.constants.NotificationTemplateTerm;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationtemplateLocalService;
import org.opencps.communication.utils.NotificationQueueBusinessFactoryUtil;
import org.opencps.communication.utils.NotificationUtil;
import org.opencps.kernel.context.MBServiceContextFactoryUtil;
import org.opencps.kernel.message.MBMessageEntry;
import org.opencps.kernel.message.email.MBEmailSenderFactoryUtil;
import org.opencps.kernel.message.notification.MBNotificationSenderFactoryUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = Hourly.class)
public class Hourly extends BaseMessageListener {
	private volatile boolean isRunning = false;
	
	@Override
	protected void doReceive(Message message) {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		doProcessNotification(message);
		isRunning = false;
	}

	private void doProcessNotification(Message message) {

		List<Notificationtemplate> notificationtemplates =
			_notificationTemplateLocalService.findByInterval(
				NotificationTemplateTerm.HOURLY);

		if (notificationtemplates != null) {
			for (Notificationtemplate notificationtemplate : notificationtemplates) {

				List<NotificationQueue> notificationQueues =
					NotificationQueueBusinessFactoryUtil.findByNotificationType_LessThanExpireDate(
						notificationtemplate.getNotificationType(), new Date());

				if (notificationQueues != null) {
					for (NotificationQueue notificationQueue : notificationQueues) {
						try {
							ServiceContext serviceContext =
								MBServiceContextFactoryUtil.create(
									notificationQueue.getCompanyId(),
									notificationQueue.getGroupId(),
									notificationQueue.getUserId());

							MBMessageEntry messageEntry =
								NotificationUtil.createMBMessageEntry(
									notificationQueue, notificationtemplate,
									serviceContext);

							if (messageEntry.isSendEmail()) {
								MBEmailSenderFactoryUtil.send(
									messageEntry, StringPool.BLANK);
							}

							if (messageEntry.isSendNotify()) {
								MBNotificationSenderFactoryUtil.send(
									messageEntry, messageEntry.getClassName(),
									serviceContext);
							}
							
							NotificationQueueBusinessFactoryUtil.delete(
								notificationQueue.getNotificationQueueId(),
								serviceContext);
						}
						catch (Exception e) {
							_log.warn("Can't send message from queue " + e);
						}
					}
				}
			}
		}
	}

//	@Activate
//	@Modified
//	protected void activate() {
//
//		schedulerEntryImpl.setTrigger(
//			TriggerFactoryUtil.createTrigger(
//				getEventListenerClass(), getEventListenerClass(), 1,
//				TimeUnit.HOUR));
//		_schedulerEngineHelper.register(
//			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
//	}

	/**
	   * activate: Called whenever the properties for the component change (ala Config Admin)
	   * or OSGi is activating the component.
	   * @param properties The properties map from Config Admin.
	   * @throws SchedulerException in case of error.
	   */
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 1, TimeUnit.HOUR);

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
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
	 * @return StorageType The storage type to use.
	*/
	protected StorageType getStorageType() {
	    if (_schedulerEntryImpl instanceof StorageTypeAware) {
	    	return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.MEMORY_CLUSTERED;
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
	@Reference
	private NotificationtemplateLocalService _notificationTemplateLocalService;

	private Log _log = LogFactoryUtil.getLog(Hourly.class);
}
