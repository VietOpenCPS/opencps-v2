package org.opencps.statistic.rest.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.HashMap;

import org.opencps.statistic.rest.dto.CommonRequest;
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
import org.opencps.statistic.rest.util.DossierStatusContants;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import opencps.statistic.common.webservice.exception.UpstreamServiceFailedException;
import opencps.statistic.common.webservice.exception.UpstreamServiceTimedOutException;

public class DossierStatisticUpdate {
	private OpencpsCallRestFacade<GovAgencyRequest, GovAgencyResponse> callService = new OpencpsCallGovAgencyRestFacadeImpl();

	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();

	private OpencpsCallRestFacade<CommonRequest, ServiceInfoResponse> callServiceInfo = new OpencpsCallServiceInfoRestFacadeImpl();

	private DossierStatisticUpdateService<DossierStatisticData> updateGovService = new DossierStatisticGovUpdateServiceImpl();

	private DossierStatisticUpdateService<HashMap<String, DossierStatisticData>> updateDomainService = new DossierStatisticDomainUpdateServiceImpl();

		
	public static final int TYPE_1_ALL_GOV_ALL_DOMAIN = 1;

	public static final int TYPE_2_EACH_GOV_ALL_DOMAIN_AND_EACH_GOV_EACH_DOMAIN = 2;


	public static final int TYPE_4_ALL_GOV_EACH_DOMAIN = 4;

	public static final int GROUP_TYPE_SITE = 1;

	public void calulateDossierStatistic(int type, long groupId, long companyId)
			throws UpstreamServiceTimedOutException, UpstreamServiceFailedException, SystemException, PortalException {
		LocalDate cal = LocalDate.now();

		int month = cal.getMonthValue();
		int year = cal.getYear();

		GetDossierResponse dossierResponse = new GetDossierResponse();

		HashMap<String, DossierStatisticData> domainStatisticData = new HashMap<String, DossierStatisticData>();


		GetDossierRequest payload = new GetDossierRequest();

		/* TYPE_1_ALL_GOV_ALL_DOMAIN */

		if (type == TYPE_1_ALL_GOV_ALL_DOMAIN) {
			
			DossierStatisticData govDossierStaticData = new DossierStatisticData();

			payload.setGroupId(groupId);

			dossierResponse = callDossierRestService.callRestService(payload);

			if (Validator.isNotNull(dossierResponse.getData())) {

				filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month, year,
						StringPool.BLANK, StringPool.BLANK, false, groupId, companyId);
			}

			updateGovService.updateDossierStatistic(govDossierStaticData);
		}

		if (type == TYPE_2_EACH_GOV_ALL_DOMAIN_AND_EACH_GOV_EACH_DOMAIN) {
			DossierStatisticData govDossierStaticData = new DossierStatisticData();

			GovAgencyRequest agencyRequest = new GovAgencyRequest();

			agencyRequest.setGroupId(groupId);

			GovAgencyResponse agencyResponse = callService.callRestService(agencyRequest);

			if (Validator.isNotNull(agencyResponse.getData())) {

				for (GovAgencyData agencyData : agencyResponse.getData()) {
					payload.setGroupId(groupId);
					payload.setGovAgencyCode(agencyData.getItemCode());

					dossierResponse = callDossierRestService.callRestService(payload);

					if (Validator.isNotNull(dossierResponse.getData())) {
						filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month, year,
								agencyData.getItemCode(), agencyData.getItemName(), true, groupId, companyId);
					}

					updateGovService.updateDossierStatistic(govDossierStaticData);
					updateDomainService.updateDossierStatistic(domainStatisticData);

				}

			}

		}

		if (type == TYPE_4_ALL_GOV_EACH_DOMAIN) {
			payload.setGroupId(groupId);

			DossierStatisticData govDossierStaticData = new DossierStatisticData();

			dossierResponse = callDossierRestService.callRestService(payload);

			if (Validator.isNotNull(dossierResponse.getData())) {
				filterStatistic(domainStatisticData, govDossierStaticData, dossierResponse, month, year,
						StringPool.BLANK, StringPool.BLANK, true, groupId, companyId);
			}

			updateDomainService.updateDossierStatistic(domainStatisticData);
		}

	}
	private void filterStatistic(HashMap<String, DossierStatisticData> domainStatisticData,
			DossierStatisticData govDossierStaticData, GetDossierResponse dossierResponse, int month, int year,
			String govAgencyCode, String govAgencyName, boolean isSetDomain, long groupId, long companyId)
			throws UpstreamServiceFailedException, UpstreamServiceTimedOutException {

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

		for (GetDossierData dossierData : dossierResponse.getData()) {

			CommonRequest request = new CommonRequest();
			request.setGroupId(dossierData.getGroupId());
			request.setKeyword(dossierData.getServiceCode());

			ServiceInfoResponse serviceInfoResponse = callServiceInfo.callRestService(request);

			DossierStatisticData dossierStatisticData = new DossierStatisticData();

			if (domainStatisticData.containsKey(serviceInfoResponse.getDomainCode())) {
				dossierStatisticData = domainStatisticData.get(serviceInfoResponse.getDomainCode());
			} else {
				domainStatisticData.put(serviceInfoResponse.getDomainCode(), dossierStatisticData);
			}

			dossierStatisticData.setTotalCount(dossierStatisticData.getTotalCount() + 1);

			/* DENIED */
			if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DENIED)) {
				deniedCount = deniedCount + 1;

				dossierStatisticData.setDeniedCount(dossierStatisticData.getDeniedCount() + 1);
			}

			/* CANCELLED */
			if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_CANCELLED)) {
				cancelledCount = cancelledCount + 1;

				dossierStatisticData.setCancelledCount(dossierStatisticData.getCancelledCount() + 1);
			}

			/* PROCESS */
			if (!dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DENIED)
					&& !dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_CANCELLED)) {

				processCount = processCount + 1;

				dossierStatisticData.setProcessCount(dossierStatisticData.getProcessCount() + 1);

				/* REVEICED */
				if (Validator.isNotNull(dossierData.getReceiveDate())
						&& dossierData.getReceiveDate().after(getFirstDay())) {
					receivedCount = receivedCount + 1;

					dossierStatisticData.setReceivedCount(dossierStatisticData.getReceivedCount() + 1);

					/* ONLINE */
					if (dossierData.getOnline()) {
						onlineCount = onlineCount + 1;

						dossierStatisticData.setOnlineCount(dossierStatisticData.getOnlineCount() + 1);
					}
				} else {
					/* REMAINING */
					remainingCount = remainingCount + 1;

					dossierStatisticData.setRemainingCount(dossierStatisticData.getRemainingCount() + 1);

				}

				/* RELEASED */
				if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DONE)
						|| dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_RELEASING)
						|| dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_POSTING)
						|| dossierData.getDossierStatus()
								.contentEquals(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED)) {

					releaseCount = releaseCount + 1;

					dossierStatisticData.setReleaseCount(dossierStatisticData.getReleaseCount() + 1);

					/* DONE */
					if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_DONE)
							|| dossierData.getDossierStatus()
									.contentEquals(DossierStatusContants.DOSSIER_STATUS_POSTING)) {
						doneCount = doneCount + 1;

						dossierStatisticData.setDoneCount(dossierStatisticData.getDoneCount() + 1);

						/* BETIME */
						if (Validator.isNotNull(dossierData.getDueDate())
								&& Validator.isNotNull(dossierData.getExtendDate())
								&& dossierData.getExtendDate().after(dossierData.getDueDate())) {

							betimesCount = betimesCount + 1;

							dossierStatisticData.setBetimesCount(dossierStatisticData.getBetimesCount() + 1);

						} else {
							/* ONTIMECOUNT */
							if (Validator.isNull(dossierData.getDueDate())
									|| (Validator.isNotNull(dossierData.getReleaseDate())
											&& dossierData.getReleaseDate().before(dossierData.getDueDate()))) {

								ontimeCount = onlineCount + 1;

								dossierStatisticData.setOntimeCount(dossierStatisticData.getOntimeCount() + 1);
							}
						}
					}

					/* RELEASING */
					if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_RELEASING)) {
						releasingCount = releasingCount + 1;

						dossierStatisticData.setReleasingCount(dossierStatisticData.getReleasingCount() + 1);
					}

					/* UNRESOLVED */
					if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_UNRESOLVED)) {
						unresolvedCount = unresolvedCount + 1;

						dossierStatisticData.setUnresolvedCount(dossierStatisticData.getUnresolvedCount() + 1);
					}
				} else if (dossierData.getDossierStatus().contentEquals(DossierStatusContants.DOSSIER_STATUS_WAITING)) {
					/* WAITING */
					waitingCount = waitingCount + 1;

					dossierStatisticData.setWaitingCount(dossierStatisticData.getWaitingCount() + 1);

				} else {
					/* PROCESSING */
					processingCount = processingCount + 1;

					dossierStatisticData.setProcessingCount(dossierStatisticData.getProcessingCount() + 1);

					/* INTEROPERATING */
					if (dossierData.getDossierStatus()
							.contentEquals(DossierStatusContants.DOSSIER_STATUS_INTEROPERATING)) {
						interoperatingCount = interoperatingCount + 1;

						dossierStatisticData.setInteroperatingCount(dossierStatisticData.getInteroperatingCount() + 1);
					}

					/* UNDUE */
					if (Validator.isNull(dossierData.getDueDate()) || dossierData.getDueDate().after(new Date())) {
						undueCount = undueCount + 1;

						dossierStatisticData.setUndueCount(dossierStatisticData.getUndueCount() + 1);
					}
				}
			}

			dossierStatisticData.setOvertimeOutside(0);
			dossierStatisticData.setOvertimeInside(
					dossierStatisticData.getOvertimeCount() - dossierStatisticData.getOvertimeOutside());
			dossierStatisticData
					.setOvertimeCount((dossierStatisticData.getDoneCount() + dossierStatisticData.getReleasingCount())
							- (dossierStatisticData.getOntimeCount() + dossierStatisticData.getBetimesCount()));

			dossierStatisticData
					.setOverdueCount(dossierStatisticData.getProcessingCount() - dossierStatisticData.getUndueCount());

			int domainSubTotal = dossierStatisticData.getUndueCount() + dossierStatisticData.getOverdueCount()
					+ dossierStatisticData.getBetimesCount() + dossierStatisticData.getOntimeCount()
					+ dossierStatisticData.getOvertimeCount();

			if (domainSubTotal != 0) {

				int domainOntimePercentage = (dossierStatisticData.getUndueCount()
						+ dossierStatisticData.getBetimesCount() + dossierStatisticData.getOntimeCount()) * 100
						/ domainSubTotal;

				dossierStatisticData.setOntimePercentage(domainOntimePercentage);
			}

			dossierStatisticData.setMonth(month);
			dossierStatisticData.setYear(year);
			dossierStatisticData.setGovAgencyCode(govAgencyCode);
			dossierStatisticData.setGovAgencyName(govAgencyName);

			if (isSetDomain) {
				dossierStatisticData.setDomainCode(serviceInfoResponse.getDomainCode());
				dossierStatisticData.setDomainName(serviceInfoResponse.getDomainName());
			}

			dossierStatisticData.setCompanyId(companyId);
			dossierStatisticData.setGroupId(groupId);
			
			domainStatisticData.put(serviceInfoResponse.getDomainCode(), dossierStatisticData);

		}

		// logAsFormattedJson(LOG, domainStatisticData);

		overtimeOutside = 0;

		overtimeInside = overtimeCount - overtimeOutside;

		overtimeCount = (doneCount + releasingCount) - (ontimeCount + betimesCount);

		overdueCount = processingCount - undueCount;

		int subTotal = undueCount + overdueCount + betimesCount + ontimeCount + overtimeCount;

		if (subTotal != 0) {
			ontimePercentage = (undueCount + betimesCount + ontimeCount) * 100 / subTotal;
		}

		govDossierStaticData.setTotalCount(totalCount);
		govDossierStaticData.setDeniedCount(deniedCount);
		govDossierStaticData.setCancelledCount(cancelledCount);
		govDossierStaticData.setProcessCount(processCount);
		govDossierStaticData.setReceivedCount(receivedCount);
		govDossierStaticData.setOnlineCount(onlineCount);
		govDossierStaticData.setReleaseCount(releaseCount);
		govDossierStaticData.setBetimesCount(betimesCount);
		govDossierStaticData.setOntimeCount(ontimeCount);
		govDossierStaticData.setOvertimeCount(overtimeCount);
		govDossierStaticData.setOvertimeInside(overtimeInside);
		govDossierStaticData.setOvertimeOutside(overtimeOutside);
		govDossierStaticData.setReleaseCount(releaseCount);
		govDossierStaticData.setProcessingCount(processingCount);
		govDossierStaticData.setUnresolvedCount(unresolvedCount);
		govDossierStaticData.setUndueCount(undueCount);
		govDossierStaticData.setOverdueCount(overdueCount);
		govDossierStaticData.setInteroperatingCount(interoperatingCount);
		govDossierStaticData.setWaitingCount(waitingCount);
		govDossierStaticData.setOntimePercentage(ontimePercentage);
		govDossierStaticData.setDoneCount(doneCount);
		govDossierStaticData.setRemainingCount(remainingCount);
		
		govDossierStaticData.setMonth(month);
		govDossierStaticData.setYear(year);
		govDossierStaticData.setGovAgencyCode(govAgencyCode);
		govDossierStaticData.setGovAgencyName(govAgencyName);

		govDossierStaticData.setCompanyId(companyId);
		govDossierStaticData.setGroupId(groupId);

		// logAsFormattedJson(LOG, govDossierStaticData);
	}
	
	private static Date getFirstDay() {
		LocalDateTime localDateTime = LocalDateTime.now();

		localDateTime.with(TemporalAdjusters.firstDayOfMonth());
		localDateTime.with(LocalTime.MIN);

		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();

		return Date.from(instant);
	}


}
