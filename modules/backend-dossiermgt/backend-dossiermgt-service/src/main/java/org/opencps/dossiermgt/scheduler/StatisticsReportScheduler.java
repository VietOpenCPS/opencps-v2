package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.PublishQueueTerm;
import org.opencps.dossiermgt.constants.ServerConfigTerm;
import org.opencps.dossiermgt.lgsp.model.MResult;
import org.opencps.dossiermgt.lgsp.model.Mtoken;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.OpencpsDossierStatistic;
import org.opencps.dossiermgt.model.OpencpsVotingStatistic;
import org.opencps.dossiermgt.rest.utils.LGSPRestClient;
import org.opencps.dossiermgt.rest.utils.OpenCPSConverter;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.dossiermgt.service.OpencpsVotingStatisticLocalServiceUtil;
import org.opencps.dossiermgt.service.PublishQueueLocalServiceUtil;
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
		ServiceContext context = new ServiceContext();
		Dossier dossier = DossierLocalServiceUtil.fetchOnePublicService();
		List<ServerConfig> lstScs = ServerConfigLocalServiceUtil.getByProtocol(dossier.getGroupId(), ServerConfigTerm.LGSP_PROTOCOL);
		for (ServerConfig sc : lstScs) {
			try {
				LGSPRestClient client = LGSPRestClient.fromJSONObject(JSONFactoryUtil.createJSONObject(sc.getConfigs()));
				Mtoken token = client.getToken();
				if (Validator.isNotNull(token.getAccessToken())) {
					MResult result = client.updateStatisticsMonth(token.getAccessToken(), dossier.getGroupId(), month, year);
					OpencpsDossierStatistic statistic = OpencpsDossierStatisticLocalServiceUtil.fetchByG_M_Y_G_D(dossier.getGroupId(), month, year, null, null);
					if (statistic != null) {
						JSONObject lgspObj = OpenCPSConverter.convertStatisticsToLGSPJSON(statistic);
						if (client.isWriteLog()) {
							JSONObject messageObj = JSONFactoryUtil.createJSONObject();
							messageObj.put("token", token.getAccessToken());
							messageObj.put("MStatistic", lgspObj.toJSONString());
							String messageText = messageObj.toJSONString();
							String acknowlegement = JSONFactoryUtil.looseSerialize(result);
							PublishQueueLocalServiceUtil.updatePublishQueue(
									sc.getGroupId(), 0l, 3, 0l, 
									sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0, 
									messageText, acknowlegement,
									context);	
						}
					}
					
					MResult resultVoting = client.updateVotingStatisticsMonth(token.getAccessToken(), dossier.getGroupId(), month, year);
					OpencpsVotingStatistic vtstatistic = OpencpsVotingStatisticLocalServiceUtil.fetchByG_M_Y_G_D_VC(sc.getGroupId(), month, year, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK);
					if (vtstatistic != null) {
						JSONObject lgspVotingObj = OpenCPSConverter.convertVotingStatisticsToLGSPJSON(vtstatistic);
						if (client.isWriteLog()) {
							JSONObject messageObj = JSONFactoryUtil.createJSONObject();
							messageObj.put("token", token.getAccessToken());
							messageObj.put("MVotes", lgspVotingObj.toJSONString());
							String messageText = messageObj.toJSONString();
							String acknowlegement = JSONFactoryUtil.looseSerialize(resultVoting);
							
							PublishQueueLocalServiceUtil.updatePublishQueue(
									sc.getGroupId(), 0l, 4, 0l, 
									sc.getServerNo(), StringPool.BLANK, PublishQueueTerm.STATE_RECEIVED_ACK, 0, 
									messageText, acknowlegement,
									context);	
						}
					}
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
