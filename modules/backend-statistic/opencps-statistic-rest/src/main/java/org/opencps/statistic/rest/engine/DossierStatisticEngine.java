package org.opencps.statistic.rest.engine;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.CommonRequest;
import org.opencps.statistic.rest.dto.DossierData;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.dto.GovAgencyData;
import org.opencps.statistic.rest.dto.GovAgencyRequest;
import org.opencps.statistic.rest.dto.GovAgencyResponse;
import org.opencps.statistic.rest.dto.ServiceInfoResponse;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallGovAgencyRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceInfoRestFacadeImpl;
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
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine extends BaseSchedulerEntryMessageListener {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);

	private SchedulerEngineHelper _schedulerEngineHelper;

	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();

	private OpencpsCallRestFacade<CommonRequest, ServiceInfoResponse> callServiceInfo = new OpencpsCallServiceInfoRestFacadeImpl();

	@Override
	protected void doReceive(Message message) throws Exception {
		// doUpdateStatistic();

		getDossierStatistic();
	}

	private void getDossierStatistic() {
		LOG.info("START getDossierStatistic(): " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		GovAgencyRequest agencyRequest = new GovAgencyRequest();

		agencyRequest.setGroupId(
				GetterUtil.getLong(DossierStatisticConfig.get(DossierStatisticConstants.OPENCPS_GROUP_CONFIG)));

		try {
			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			LocalDate cal = LocalDate.now();

			int month = cal.getMonthValue();
			int year = cal.getYear();

			for (GovAgencyData agencyData : agencyResponse.getData()) {
				LOG.info("GovAgencyCode " + agencyData.getItemCode());

				/* 2. Get dossier in GOV */

				GetDossierRequest payload = new GetDossierRequest();

				payload.setGovAgencyCode(agencyData.getItemCode());

				/* Get List dossier in month of a govagency */
				GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);

				/* Store count in HashMap */

				HashMap<String, DossierStatisticData> domainStatisticData = new HashMap<String, DossierStatisticData>();
				int totalCount = dossierResponse.getTotal();
				int deniedCount = 0;
				int cancelledCount = 0;
				int processCount = 0;
				int remainingCount = 0;
				int receivedCount = 0;
				int onlineCount = 0;
				int releaseCount = 0;
				int betimesCount = 0;
				int ontimeCount = 0;
				int overtimeCount = 0;
				int overtimeInside = 0;
				int overtimeOutside = 0;
				int doneCount = 0;
				int releasingCount = 0;
				int unresolvedCount = 0;
				int processingCount = 0;
				int undueCount = 0;
				int overdueCount = 0;
				int interoperatingCount = 0;
				int waitingCount = 0;
				int ontimePercentage = 0;

				int totalCount_domain = 0;
				int deniedCount_domain = 0;
				int cancelledCount_domain = 0;
				int processCount_domain = 0;
				int remainingCount_domain = 0;
				int receivedCount_domain = 0;
				int onlineCount_domain = 0;
				int releaseCount_domain = 0;
				int betimesCount_domain = 0;
				int ontimeCount_domain = 0;
				int overtimeCount_domain = 0;
				int overtimeInside_domain = 0;
				int overtimeOutside_domain = 0;
				int doneCount_domain = 0;
				int releasingCount_domain = 0;
				int unresolvedCount_domain = 0;
				int processingCount_domain = 0;
				int undueCount_domain = 0;
				int overdueCount_domain = 0;
				int interoperatingCount_domain = 0;
				int waitingCount_domain = 0;
				int ontimePercentage_domain = 0;

				if (dossierResponse.getTotal() > 0) {

					totalCount = dossierResponse.getTotal();

					for (GetDossierData dossierData : dossierResponse.getData()) {

						CommonRequest request = new CommonRequest();
						request.setGroupId(dossierData.getGroupId());
						request.setKeyword(dossierData.getServiceCode());

						LOG.info("GROUPID_" + dossierData.getGroupId());
						LOG.info("GROUPID_" + dossierData.getServiceCode());

						ServiceInfoResponse serviceInfoResponse = callServiceInfo.callRestService(request);

						/* DENIED */
						if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DENIED)) {
							deniedCount = deniedCount + 1;
						}

						/* CANCELLED */
						if (dossierData.getDossierStatus()
								.contentEquals(DossierStatusContants.DOSSIER_STATUS_CANCELLED)) {
							cancelledCount = cancelledCount + 1;
						}

						/* PROCESS */
						if (!dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DENIED)
								&& !dossierData.getDossierStatus()
										.contentEquals(DossierStatusContants.DOSSIER_STATUS_CANCELLED)) {
							processCount = processCount + 1;

							/* REVEICED */
							if (Validator.isNotNull(dossierData.getReceiveDate()) && dossierData.getReceiveDate().after(getFirstDay())) {
								receivedCount = receivedCount + 1;

								/* ONLINE */
								if (dossierData.getOnline()) {
									onlineCount = onlineCount + 1;
								}
							} else {
								/* REMAINING */
								remainingCount = remainingCount + 1;
							}

							/* RELEASED */
							if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DONE)
									|| dossierData.getDossierStatus()
											.contentEquals(DossierStatusContants.DOSSIER_STATUS_RELEASING)
									|| dossierData.getDossierStatus()
											.contentEquals(DossierStatusContants.DOSSIER_STATUS_POSTING)
									|| dossierData.getDossierStatus()
											.contentEquals(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED)) {

								releaseCount = releaseCount + 1;

								/* DONE */
								if (dossierData.getDossierStatus()
										.contentEquals(DossierStatusContants.DOSSIER_STATUS_DONE)
										|| dossierData.getDossierStatus()
												.contentEquals(DossierStatusContants.DOSSIER_STATUS_POSTING)) {
									doneCount = doneCount + 1;

									/* BETIME */
									if (Validator.isNotNull(dossierData.getDueDate())
											&& Validator.isNotNull(dossierData.getExtendDate())
											&& dossierData.getExtendDate().after(dossierData.getDueDate())) {
										betimesCount = betimesCount + 1;
									} else {
										/* ONTIMECOUNT */
										if (Validator.isNull(dossierData.getDueDate())
												|| dossierData.getReleaseDate().before(dossierData.getDueDate())) {
											ontimeCount = onlineCount + 1;
										}
									}
								}

								/* RELEASING */
								if (dossierData.getDossierStatus()
										.contentEquals(DossierStatusContants.DOSSIER_STATUS_RELEASING)) {
									releasingCount = releasingCount + 1;
								}

								/* UNRESOLVED */
								if (dossierData.getDossierStatus()
										.contentEquals(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED)) {
									unresolvedCount = unresolvedCount + 1;
								}
							} else if (dossierData.getDossierStatus()
									.contentEquals(DossierStatusContants.DOSSIER_STATUS_WAITING)) {
								/* WAITING */
								waitingCount = waitingCount + 1;
							} else {
								/* PROCESSING */
								processingCount = processingCount + 1;

								/* INTEROPERATING */
								if (dossierData.getDossierStatus()
										.contentEquals(DossierStatusContants.DOSSIER_STATUS_INTEROPERATING)) {
									interoperatingCount = interoperatingCount + 1;
								}

								/* UNDUE */
								if (Validator.isNull(dossierData.getDueDate())
										|| dossierData.getDueDate().after(new Date())) {
									undueCount = undueCount + 1;
								}
							}
						}

						/* add to HashMap */
						if (domainStatisticData.containsKey(serviceInfoResponse.getDomainCode())) {
							/* UPDATE */

							DossierStatisticData domainData = domainStatisticData
									.get(serviceInfoResponse.getDomainCode());
							
							

						} else {
							/* ADDD */
						}

					}

					overtimeOutside = 0;

					overtimeInside = overtimeCount - overtimeOutside;

					overtimeCount = (doneCount + releasingCount) - (ontimeCount + betimesCount);

					overdueCount = processingCount - undueCount;

					int subTotal = undueCount + overdueCount + betimesCount + ontimeCount + overtimeCount;

					if (subTotal != 0) {
						ontimePercentage = (undueCount + betimesCount + ontimeCount) * 100 / subTotal;
					}

					LOG.info("totalCount" + totalCount);
					LOG.info("deniedCount" + deniedCount);
					LOG.info("cancelledCount" + cancelledCount);
					LOG.info("processCount" + processCount);
					LOG.info("receivedCount" + receivedCount);
					LOG.info("onlineCount" + onlineCount);
					LOG.info("releaseCount" + releaseCount);
					LOG.info("betimesCount" + betimesCount);
					LOG.info("ontimeCount" + ontimeCount);
					LOG.info("overtimeCount" + overtimeCount);
					LOG.info("overtimeInside" + overtimeInside);
					LOG.info("overtimeOutside" + overtimeOutside);
					LOG.info("releasingCount" + releasingCount);
					LOG.info("processingCount" + processingCount);
					LOG.info("unresolvedCount" + unresolvedCount);
					LOG.info("undueCount" + undueCount);
					LOG.info("overdueCount" + overdueCount);
					LOG.info("interoperatingCount" + interoperatingCount);
					LOG.info("waitingCount" + waitingCount);
					LOG.info("ontimePercentage" + ontimePercentage);

				}

			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private static Date getFirstDay() {
		LocalDateTime localDateTime = LocalDateTime.now();

		localDateTime.with(TemporalAdjusters.firstDayOfMonth());
		localDateTime.with(LocalTime.MIN);

		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

		return Date.from(instant);
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
				payload.setUndue(false);

				int overdueCount = processingCount - undueCount;

				int waitingCount = processCount - (releaseCount + processingCount);

				double ontimePercentage = 0.0;

				if (releaseCount != 0) {
					ontimePercentage = (betimesCount + onlineCount) * 100 / releaseCount;
				}

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
