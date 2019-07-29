package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.OpencpsPersonStatisticLocalServiceUtil;
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

//		long dossierStatisticId = 0L;
//
//		try {
//			OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsit(
//					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
//					payload.getDomainCode());
//			if (Validator.isNotNull(dossierStatistic)) {
//				dossierStatisticId = dossierStatistic.getDossierStatisticId();
//			}
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		byte pausingCount = 0;
//
//		try {
//			return OpencpsDossierStatisticLocalServiceUtil.updateStatistic(dossierStatisticId, payload.getCompanyId(),
//					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
//					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
//					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
//					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
//					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
//					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
//					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
//					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
//					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
//					payload.getDomainName(), payload.isReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
//					payload.getInsideCount());
//		} catch (PortalException | SystemException e) {
//			_log.error(e);
//			return null;
//		}

		byte pausingCount = 0;

		try {
			return OpencpsDossierStatisticLocalServiceUtil.createOrUpdateStatistic(payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
					payload.getDomainName(), payload.isReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
					payload.getInsideCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}
	
//	public void removeDossierStatisticByD_M_Y(long groupId, String domainCode, int month, int year) throws NoSuchOpencpsDossierStatisticException {
//		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByD_M_Y(groupId, domainCode, month, year);
//	}
//
//	public void removeDossierStatisticByMonthYear(long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
//		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByMonthYear(groupId, month, year);
//	}
//
//	public void removeDossierStatisticByYear(long companyId, long groupId, int month, int year) throws NoSuchOpencpsDossierStatisticException {
//		OpencpsDossierStatisticLocalServiceUtil.removeDossierStatisticByYear(companyId, groupId, month, year);
//	}

	//Get list dossierStatistic by groupId, month, year
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYear(long groupId, int month, int year) {
		return OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByMonthYear(groupId, month, year);
	}

	//Get list dossierStatistic by groupId, month, year and reporting
	public List<OpencpsDossierStatistic> getDossierStatisticByMonthYearAndReport(long groupId, int month, int year, boolean reporting) {
		return OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByMonthYearAndReport(groupId, month, year, reporting);
	}

	//Remove record by domain and govAgencyCode
//	public void removeDossierStatisticByG_M_Y_G_D(long groupId, int month, int year, String agency, String domainCode)
//			throws NoSuchOpencpsDossierStatisticException {
//		OpencpsDossierStatisticLocalServiceUtil.removeByG_M_Y_G_D(groupId, month, year, agency, domainCode);
//	}

	//Get list statistic by year
	public List<OpencpsDossierStatistic> getDossierStatisticByYear(long companyId, long groupId, int month, int year) {
		return OpencpsDossierStatisticLocalServiceUtil.getDossierStatisticByYear(companyId, groupId, month, year);
	}

	//Process Voting
	public OpencpsVotingStatistic updateVotingStatistic(VotingResultStatisticData payload) {

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}
		if (Validator.isNull(payload.getDomain())) {
			payload.setDomain((String) null);
		}
		if (Validator.isNull(payload.getVotingCode())) {
			payload.setVotingCode((String) null);
		}

		long votingStatisticId = 0L;

		try {
			OpencpsVotingStatistic votingStatistic = OpencpsVotingStatisticLocalServiceUtil.checkExsit(
					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
					payload.getDomain(), payload.getVotingCode());
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
					payload.getVotingSubject(), payload.getTotalVoted(), payload.getVeryGoodCount(),
					payload.getGoodCount(), payload.getBadCount(), payload.getPercentVeryGood(),
					payload.getPercentGood(), payload.getPercentBad(), payload.getGovAgencyCode(),
					payload.getGovAgencyName(), payload.getDomain(), payload.getDomainName(), payload.getVotingCode(),
					0);
		} catch (SystemException e) {
			_log.error(e);
			return null;
		}
	}

	public void removeVotingStatisticByD_M_Y(long groupId, String domainCode, int month, int year) {
		OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByD_M_Y(groupId, domainCode, month, year);
	}

	public void removeVotingStatisticByMonthYear(long groupId, int month, int year) {
		OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByMonthYear(groupId, month, year);
	}

	public void removeVotingStatisticByYear(long companyId, long groupId, int month, int year) {
		OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByYear(companyId, groupId, month, year);
	}

	//Process Person Voting
	public OpencpsPersonStatistic updatePersonStatistic(PersonStatisticData payload) {

		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode((String) null);
		}
		if (Validator.isNull(payload.getEmployeeId())) {
			payload.setEmployeeId(0);
		}
		if (Validator.isNull(payload.getVotingCode())) {
			payload.setVotingCode((String) null);
		}

		long personStatisticId = 0L;

		try {
			OpencpsPersonStatistic personStatistic = OpencpsPersonStatisticLocalServiceUtil.checkExsit(
					payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(),
					payload.getEmployeeId(), payload.getVotingCode());
			//System.out.println("votingStatistic: "+votingStatistic);
			if (Validator.isNotNull(personStatistic)) {
				personStatisticId = personStatistic.getPersonStatisticId();
			}
		} catch (Exception e) {
			_log.error(e);
		}

		//System.out.println("votingStatisticId: "+votingStatisticId);
		try {
			return OpencpsPersonStatisticLocalServiceUtil.updatePersonStatistic(personStatisticId,
					payload.getCompanyId(), payload.getGroupId(), -1L, "VDM", payload.getMonth(), payload.getYear(),
					payload.getVotingSubject(), payload.getTotalVoted(),
					payload.getVeryGoodCount(), payload.getGoodCount(), payload.getBadCount(),
					payload.getPercentVeryGood(),
					payload.getPercentGood(), payload.getPercentBad(), payload.getGovAgencyCode(),
					payload.getGovAgencyName(), payload.getEmployeeId(), payload.getVotingCode(), 0);
		} catch (SystemException e) {
			_log.error(e);
		}

		return null;
	}

//	public void removePersonStatisticByD_M_Y(long groupId, String domainCode, int month, int year) {
//		OpencpsPersonStatisticLocalServiceUtil.removeVotingStatisticByD_M_Y(groupId, domainCode, month, year);
//	}

	public void removePersonStatisticByMonthYear(long groupId, int month, int year) {
		OpencpsPersonStatisticLocalServiceUtil.removePersonStatisticByMonthYear(groupId, month, year);
	}

	public void removePersonStatisticByYear(long companyId, long groupId, int month, int year) {
		OpencpsPersonStatisticLocalServiceUtil.removePersonStatisticByYear(companyId, groupId, month, year);
	}
	
	public OpencpsDossierStatistic createStatistic(DossierStatisticData payload) {
		byte pausingCount = 0;

		try {
			return OpencpsDossierStatisticLocalServiceUtil.createOnlyStatistic(payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
					payload.getDomainName(), payload.isReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
					payload.getInsideCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}
	
	
	public OpencpsDossierStatistic updateOnlyStatistic(OpencpsDossierStatistic statistic, DossierStatisticData payload) {
		byte pausingCount = 0;

		try {
			return OpencpsDossierStatisticLocalServiceUtil.updateOnlyStatistic(statistic, payload.getCompanyId(),
					payload.getGroupId(), -1L, "ADM", payload.getMonth(), payload.getYear(), payload.getTotalCount(),
					payload.getDeniedCount(), payload.getCancelledCount(), payload.getProcessCount(),
					payload.getRemainingCount(), payload.getReceivedCount(), payload.getOnlineCount(),
					payload.getReleaseCount(), payload.getBetimesCount(), payload.getOntimeCount(),
					payload.getOvertimeCount(), payload.getDoneCount(), payload.getReleasingCount(),
					payload.getUnresolvedCount(), payload.getProcessingCount(), payload.getUndueCount(),
					payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(), payload.getOvertimeInside(),
					payload.getOvertimeOutside(), payload.getInteroperatingCount(), payload.getWaitingCount(),
					payload.getGovAgencyCode(), payload.getGovAgencyName(), payload.getDomainCode(),
					payload.getDomainName(), payload.isReporting(), payload.getOnegateCount(), payload.getOutsideCount(),
					payload.getInsideCount());
		} catch (PortalException | SystemException e) {
			_log.error(e);
			return null;
		}
	}	
	
}