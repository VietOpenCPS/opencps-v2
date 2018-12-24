package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierActionUserTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierActionUser;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionUserLocalServiceUtil;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = ActionOverdueScheduler.class)
public class ActionOverdueScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("OpenCPS Check Action Overdue IS  : " + APIDateTimeUtils.convertDateToString(new Date()));
		
		Date now = new Date();		
		List<DossierAction> lstactions = DossierActionLocalServiceUtil.findOverdue(now);
		for (DossierAction action : lstactions) {
			List<DossierActionUser> lstDaus = DossierActionUserLocalServiceUtil.getListUser(action.getDossierActionId());
			Notificationtemplate notiTemplate = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(action.getGroupId(), "EMPL-02");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(now);
	        	  
	        JSONObject payloadObj = JSONFactoryUtil.createJSONObject();
	        try {		
	        	payloadObj = JSONFactoryUtil.createJSONObject(JSONFactoryUtil.looseSerialize(action));
	        }
	        catch (Exception e) {
	        	_log.debug(e);
	        }
	        
			if (notiTemplate != null) {
				if ("minutely".equals(notiTemplate.getInterval())) {
			        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());					
				}
				else if ("hourly".equals(notiTemplate.getInterval())) {
			        cal.add(Calendar.HOUR, notiTemplate.getExpireDuration());										
				}
				else {
			        cal.add(Calendar.MINUTE, notiTemplate.getExpireDuration());										
				}
				Date expired = cal.getTime();
				ServiceContext serviceContext = new ServiceContext();
				
				for (DossierActionUser dau : lstDaus) {
					if (dau.getAssigned() == DossierActionUserTerm.ASSIGNED_TH || dau.getAssigned() == DossierActionUserTerm.ASSIGNED_PH) {
						Employee employee = EmployeeLocalServiceUtil.fetchByF_mappingUserId(action.getGroupId(), dau.getUserId());
						String telNo = employee != null ? employee.getTelNo() : StringPool.BLANK;
						String fullName = employee != null ? employee.getFullName() : StringPool.BLANK;
						User u = UserLocalServiceUtil.fetchUser(dau.getUserId());
						
						NotificationQueueLocalServiceUtil.addNotificationQueue(
								dau.getUserId(), action.getGroupId(), 
								"EMPL-02", 
								Dossier.class.getName(), 
								String.valueOf(action.getDossierId()), 
								payloadObj.toJSONString(), 
								fullName, 
								fullName, 
								dau.getUserId(), 
								u != null ? u.getEmailAddress() : StringPool.BLANK, 
								telNo, 
								now, 
								expired, 
								serviceContext);																		
					}	
				}
			}
		}
		_log.info("OpenCPS Check Action Overdue HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));		
	}
	
	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 1, TimeUnit.DAY));
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

	private Log _log = LogFactoryUtil.getLog(ActionOverdueScheduler.class);
	
}
