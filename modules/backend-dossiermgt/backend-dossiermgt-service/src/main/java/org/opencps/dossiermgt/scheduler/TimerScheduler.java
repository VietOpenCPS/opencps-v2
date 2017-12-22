package org.opencps.dossiermgt.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

public class TimerScheduler extends BaseSchedulerEntryMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		// TODO Auto-generated method stub
		_log.info("Invoke Timer****");
		//get all actions that has preCondition is "timer"
		List<ProcessAction> processActions = ProcessActionLocalServiceUtil.getByGroupAndAutoEvent(0, "timer",
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		List<ProcessStep> lsPs = new ArrayList<ProcessStep>();
		
		for (ProcessAction pa : processActions) {
			
			List<ProcessStep> processSteps = ProcessStepLocalServiceUtil.getBySC_SPID(pa.getPreStepCode(), pa.getServiceProcessId());
			
			lsPs.addAll(processSteps);
		}
		
		List<Dossier> allDossierTimer = new ArrayList<Dossier>();
		
		for (ProcessStep processStep : lsPs) {
			
		}
		
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

	private Log _log = LogFactoryUtil.getLog(TimerScheduler.class);

}
