package org.opencps.statistic.rest.engine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.util.DossierStatisticConfig;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierStatusContants;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine extends BaseSchedulerEntryMessageListener {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);

	private SchedulerEngineHelper _schedulerEngineHelper;

	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();

	@Override
	protected void doReceive(Message message) throws Exception {
		doUpdateStatistic();
	}

	private void doUpdateStatistic() {
		LOG.info("START doUpdateStatistic(): " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		GovAgencyRequest agencyRequest = new GovAgencyRequest();

		agencyRequest.setGroupId(
				GetterUtil.getLong(DossierStatisticConfig.get(DossierStatisticConstants.OPENCPS_GROUP_CONFIG)));

		try {
			/* 1. Get GovAgency List */
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			for (GovAgencyData agencyData : agencyResponse.getData()) {
				LOG.info("GovAgencyCode " + agencyData.getItemCode());

				/* 2. Get dossier in GOV */

				GetDossierRequest payload = new GetDossierRequest();

				payload.setGovAgencyCode(agencyData.getItemCode());

				GetDossierResponse dossierResponse = new GetDossierResponse();

				dossierResponse = callDossierRestService.callRestService(payload);

				int totalCount = dossierResponse.getTotal();

				/* Set dossier status = denied */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_DENIED);

				int deniedCount = callDossierRestService.callRestService(payload).getTotal();

				/* Set dossier status = cancelled */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_CANCELLED);

				int cancelledCount = callDossierRestService.callRestService(payload).getTotal();

				int processCount = totalCount - (deniedCount + cancelledCount);

				LOG.info("totalCount" + totalCount + "deniedCount_" + deniedCount + "cancelledCount_" + cancelledCount
						+ "processCount_" + processCount);

				/* reset dossier status */
				payload.setDossierStatus(StringPool.BLANK);

				/* add condition received */
				payload.setReceived(true);

				int receivedCount = callDossierRestService.callRestService(payload).getTotal();

				/* add condition online */
				payload.setOnline(true);
				payload.setOnlineValue(true);

				int onlineCount = callDossierRestService.callRestService(payload).getTotal();

				int onegateCount = receivedCount - onlineCount;

				int remainingCount = processCount - receivedCount;

				LOG.info("receivedCount" + receivedCount + "onlineCount" + onlineCount + "onegateCount" + onegateCount
						+ "remainingCount" + remainingCount);

				/* reset received, online */
				payload.setReceived(false);
				payload.setOnline(false);
				payload.setOnlineValue(false);

				/* add release count */
				payload.setReleased(true);
				int releaseCount = callDossierRestService.callRestService(payload).getTotal();

				/* reset dossier release */
				payload.setReleased(false);

				/* add dossier status = done */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_DONE);

				int doneIsDone = callDossierRestService.callRestService(payload).getTotal();

				/* add dossier status = done */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_POSTING);

				int doneIsPosting = callDossierRestService.callRestService(payload).getTotal();

				int doneCount = doneIsDone + doneIsPosting;

				LOG.info("releaseCount" + releaseCount + "doneCount" + doneCount);

				/* set dossier status = unresolved */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED);

				int unresolvedCount = callDossierRestService.callRestService(payload).getTotal();

				int releasingCount = receivedCount - (doneCount + unresolvedCount);

				LOG.info("releasingCount" + releasingCount + "unresolvedCount" + unresolvedCount);

				/* reset dossier status */
				payload.setDossierStatus(StringPool.BLANK);

				/* set betime */
				payload.setBetime(true);
				payload.setReleased(true);

				int betimesCount = callDossierRestService.callRestService(payload).getTotal();

				/* set ontime */
				payload.setBetime(false);
				payload.setOntime(true);

				int ontimeCount = callDossierRestService.callRestService(payload).getTotal();

				int overtimeCount = releaseCount - (ontimeCount + betimesCount);

				LOG.info("overtimeCount" + overtimeCount + "ontimeCount" + ontimeCount + "betimesCount" + betimesCount);

				/* reset release, ontime, betime */
				payload.setBetime(false);
				payload.setOntime(false);
				payload.setReleased(false);

				/* TODO update this value later, need more logic from BA */
				int overtimeOutside = 0;

				int overtimeInside = overtimeCount - overtimeOutside;

				/* set dossier status = procesing */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_PROCESING);

				int processingIsPosting = callDossierRestService.callRestService(payload).getTotal();

				/* set dossier status = interoperating */
				payload.setDossierStatus(DossierStatusContants.DOSSIER_STATUS_INTEROPERATING);

				int processingIsInteroperating = callDossierRestService.callRestService(payload).getTotal();

				int processingCount = processingIsPosting + processingIsInteroperating;

				LOG.info("overtimeInside" + overtimeInside + "processingIsPosting" + processingIsPosting
						+ "processingIsInteroperating" + processingIsInteroperating + "processingCount"
						+ processingCount);

				/* reset dossier status */
				payload.setDossierStatus(StringPool.BLANK);
				payload.setUndue(true);

				int undueCount = callDossierRestService.callRestService(payload).getTotal();

				/* reset undue */
				payload.setUndue(true);

				int overdueCount = processingCount - undueCount;

				int waitingCount = processCount - (releaseCount + processingCount);

				double ontimePercentage = (betimesCount + onlineCount) * 100 / releaseCount;

				LOG.info("undueCount" + undueCount + "overdueCount" + overdueCount + "waitingCount" + waitingCount
						+ "ontimePercentage" + ontimePercentage);

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
