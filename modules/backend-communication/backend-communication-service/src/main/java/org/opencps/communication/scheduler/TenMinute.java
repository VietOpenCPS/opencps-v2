
/*package org.mobilink.common.scheduler;

import java.util.Date;
import java.util.List;

import org.mobilink.common.business.util.NotificationQueueBusinessFactoryUtil;
import org.mobilink.common.constants.NotificationTemplateTerm;
import org.mobilink.common.model.NotificationQueue;
import org.mobilink.common.model.Notificationtemplate;
import org.mobilink.common.service.NotificationtemplateLocalService;
import org.mobilink.common.util.NotificationUtil;
import org.mobilink.kernel.message.MBMessageEntry;
import org.mobilink.kernel.message.email.MBEmailSenderFactoryUtil;
import org.mobilink.kernel.service.context.MBServiceContextFactoryUtil;
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
import com.liferay.portal.kernel.service.ServiceContext;

@Component(immediate = true, service = TenMinute.class)
public class TenMinute extends BaseSchedulerEntryMessageListener {

	@Override
	protected void doReceive(Message message) {
		doProcessNotification(message);
	}
	
	private void doProcessNotification(Message message) {

		List<Notificationtemplate> notificationtemplates =
			_notificationTemplateLocalService.findByInterval(
				NotificationTemplateTerm.FIVE_MINUTES);

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

							MBEmailSenderFactoryUtil.send(messageEntry);
						}
						catch (Exception e) {
							_log.warn("Can't send message from queue " + e);
						}
					}
				}
			}
		}
	}

	@Activate
	@Modified
	protected void activate() {

		schedulerEntryImpl.setTrigger(
			TriggerFactoryUtil.createTrigger(
				getEventListenerClass(), getEventListenerClass(), 10,
				TimeUnit.MINUTE));
		_schedulerEngineHelper.register(
			this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {

		_schedulerEngineHelper.unregister(this);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {

	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {

	}
	
	@Reference
	private NotificationtemplateLocalService _notificationTemplateLocalService;

	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(TenMinute.class);

}*/
