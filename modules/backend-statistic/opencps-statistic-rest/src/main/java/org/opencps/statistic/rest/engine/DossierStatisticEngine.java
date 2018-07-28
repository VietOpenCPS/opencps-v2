package org.opencps.statistic.rest.engine;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyServiceImpl;
import org.opencps.statistic.rest.facade.OpencpsCallServiceFacade;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine extends BaseSchedulerEntryMessageListener {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);

	private SchedulerEngineHelper _schedulerEngineHelper;

	private SimpleDateFormat _simpleDateTimeFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");

	private OpencpsCallServiceFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyServiceImpl();

	@Override
	protected void doReceive(Message message) throws Exception {
		doUpdateStatistic();
	}

	private void doUpdateStatistic() {
		LOG.info("START DOING doUpdateStatistic(): " + _simpleDateTimeFormat.format(new Date()));

		GovAgencyRequest agencyRequest = new GovAgencyRequest();

		agencyRequest.setGroupId(DossierStatisticConstants.GROUP_ID_PROCESS_PORTAL);

		try {
			/* 1. Get GovAgency List*/
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);
			
			LOG.info(agencyResponse.getTotal() + "TOTAL");
			
			for (GovAgencyData agencyData : agencyResponse.getData()) {
				LOG.info("DATA_1 " + agencyData.getItemCode());
				LOG.info("DATA_2 " + agencyData.getItemName());
			}
			

		} catch (Exception e) {
			LOG.info(e.getMessage());
		}

	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(),
				45, TimeUnit.SECOND));
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
}
