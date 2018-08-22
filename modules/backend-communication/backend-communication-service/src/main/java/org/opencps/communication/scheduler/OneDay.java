package org.opencps.communication.scheduler;
//package org.mobilink.backend.activity.scheduler;
//
//import java.util.Date;
//import java.util.List;
//
//import org.mobilink.meetup.backend.MsgData;
//import org.mobilink.meetup.model.MActivitys;
//import org.mobilink.meetup.service.MActivitysLocalServiceUtil;
//import org.mobilink.meetup.service.constants.ActivitiesConstants;
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Deactivate;
//import org.osgi.service.component.annotations.Modified;
//import org.osgi.service.component.annotations.Reference;
//
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
//import com.liferay.portal.kernel.messaging.DestinationNames;
//import com.liferay.portal.kernel.messaging.Message;
//import com.liferay.portal.kernel.messaging.MessageBusUtil;
//import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
//import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
//import com.liferay.portal.kernel.scheduler.TimeUnit;
//import com.liferay.portal.kernel.scheduler.TriggerFactory;
//import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
//import com.liferay.portal.kernel.service.ServiceContext;
//
//import activity.service.util.ServiceProps;
//
//@Component(immediate = true, service = OneDay.class)
//public class OneDay extends BaseSchedulerEntryMessageListener {
//
//	@Override
//	protected void doReceive(Message message){
//		
//		List<MActivitys> listActivitys = MActivitysLocalServiceUtil.findByF_Activitis_OpeningStage_OverDate(1,
//				ActivitiesConstants.IS_TEMPLATE_NOT);
//		for (MActivitys mActivitys : listActivitys) {
//			ServiceContext serviceContext = new ServiceContext();
//			serviceContext.setCompanyId(mActivitys.getCompanyId());
//			// update opening stage
//			if (mActivitys.getDueDate().before(new Date())) {
//				// update change log create event
//				Message messageLog = new Message();
//
//				MsgData msgDataLog = new MsgData();
//				
//				msgDataLog.setActivityId(mActivitys.getActivtyId());
//				msgDataLog.setAuthor(mActivitys.getUserName());
//				msgDataLog.setContent(ServiceProps.get("reminder-over-date-activity") + mActivitys.getSubject());
//				msgDataLog.setNotificationType(9);
//				msgDataLog.setFromParticipantId(0);
//				msgDataLog.setToParticipantId(0);
//				msgDataLog.setUserId(mActivitys.getUserId());
//				messageLog.put("msgToEngine", msgDataLog);
//				
//				MessageBusUtil.sendMessage("mobilink/engine/out/destination", messageLog);
//			}
//		}
//	}
//
//	@Activate
//	@Modified
//	protected void activate() {
//		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
//				1, TimeUnit.DAY));
//		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
//	}
//
//	@Deactivate
//	protected void deactivate() {
//		_schedulerEngineHelper.unregister(this);
//	}
//
//	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
//	protected void setModuleServiceLifecycle(
//		ModuleServiceLifecycle moduleServiceLifecycle) {
//	}
//
//	
//	@Reference(unbind = "-")
//	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
//
//		_schedulerEngineHelper = schedulerEngineHelper;
//	}
//
//	@Reference(unbind = "-")
//	protected void setTriggerFactory(TriggerFactory triggerFactory) {
//	}
//
//	private SchedulerEngineHelper _schedulerEngineHelper;
//
//	private Log _log = LogFactoryUtil.getLog(OneDay.class);
//
//}