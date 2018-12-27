package org.opencps.dossiermgt.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.MResult;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = StatisticsReportScheduler.class)
public class StatisticsReportScheduler extends BaseSchedulerEntryMessageListener {
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("OpenCPS PUBLISH STATISTICS IS  : " + APIDateTimeUtils.convertDateToString(new Date()));
		
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);

		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		
		Dossier dossier = DossierLocalServiceUtil.fetchOnePublicService();
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.LGSP_PROTOCOL);
		for (ServerConfig sc : lstScs) {
			try {
				LGSPRestClient client = LGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
				Mtoken token = client.getToken();
				if (Validator.isNotNull(token.getAccessToken())) {
					MResult result = client.updateStatisticsMonth(token.getAccessToken(), dossier.getGroupId(), month, year);
					_log.info("UPDATE STATISTICS LGSP: " + result.getMessage());
					MResult resultVoting = client.updateVotingStatisticsMonth(token.getAccessToken(), dossier.getGroupId(), month, year);
					_log.info("UPDATE STATISTICS LGSP: " + resultVoting.getMessage());
				}
			} catch (PortalException e) {
				_log.debug(e);
			}
		}
		_log.info("OpenCPS PUBlISH STATISTICS HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));		
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

	private Log _log = LogFactoryUtil.getLog(StatisticsReportScheduler.class);
	
}
