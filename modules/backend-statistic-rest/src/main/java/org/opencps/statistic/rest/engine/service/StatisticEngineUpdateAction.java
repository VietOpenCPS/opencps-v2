package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;

public class StatisticEngineUpdateAction {

	private static Log _log = LogFactoryUtil.getLog(StatisticEngineUpdateAction.class);

	public OpencpsDossierStatistic updateStatistic(DossierStatisticData payload) {
		if (Validator.isNull(payload.getDomainCode())) {
			payload.setDomainCode((String) null);
		}

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}

		long dossierStatisticId = 0L;

		try {
			OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsit(
					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
					payload.getDomainCode(), false);
			if (Validator.isNotNull(dossierStatistic)) {
				dossierStatisticId = dossierStatistic.getDossierStatisticId();
			}
		} catch (Exception e) {
			_log.error(e);
		}

		byte pausingCount = 0;

		try {
			return OpencpsDossierStatisticLocalServiceUtil.updateStatistic(dossierStatisticId, payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
					payload.getDomainName(), false, payload.getOnegateCount(), payload.getOutsideCount(),
					payload.getInsideCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}
	
	public void removeDossierStatisticByD_M_Y(long groupId, String domainCode, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByD_M_Y(groupId, domainCode, month, year);
	}

	public void removeDossierStatisticByMonthYear(long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByMonthYear(groupId, month, year);
	}

	public void removeDossierStatisticByYear(long companyId, long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByYear(companyId, groupId, month, year);
	}

	//Process Voting
	public OpencpsVotingStatistic updateVotingStatistic(VotingResultStatisticData payload) {

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}
		if (Validator.isNull(payload.getServiceCode())) {
			payload.setServiceCode((String) null);
		}
		if (Validator.isNull(payload.getVotingCode())) {
			payload.setVotingCode((String) null);
		}

		long votingStatisticId = 0L;

		try {
			OpencpsVotingStatistic votingStatistic = OpencpsVotingStatisticLocalServiceUtil.checkExsit(
					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
					payload.getServiceCode(), payload.getVotingCode());
			//System.out.println("votingStatistic: "+votingStatistic);
			if (Validator.isNotNull(votingStatistic)) {
				votingStatisticId = votingStatistic.getVotingStatisticId();
			}
		} catch (Exception e) {
			_log.error(e);
		}

		//System.out.println("votingStatisticId: "+votingStatisticId);
		try {
			return OpencpsVotingStatisticLocalServiceUtil.updateVotingStatistic(votingStatisticId,
					payload.getCompanyId(), payload.getGroupId(), -1L, "VDM", payload.getMonth(), payload.getYear(),
					payload.getTotalCount(), payload.getPercentVeryGood(), payload.getPercentGood(),
					payload.getPercentBad(), payload.getGovAgencyCode(), payload.getGovAgencyName(),
					payload.getServiceCode(), payload.getServiceName(), payload.getVotingCode(),
					payload.getTotalCount());
		} catch (SystemException e) {
			_log.error(e);
			System.out.println(e);
			return null;
		}
	}
}