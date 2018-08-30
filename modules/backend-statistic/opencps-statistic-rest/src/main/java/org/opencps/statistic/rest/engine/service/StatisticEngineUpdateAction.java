package org.opencps.statistic.rest.engine.service;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

public class StatisticEngineUpdateAction {
	public OpencpsDossierStatistic updateStatistic(DossierStatisticData payload) {
		
		if (Validator.isNull(payload.getDomainCode())) {
			payload.setDomainCode(null);
		}
		
		if (Validator.isNull(payload.getGovAgencyCode())) {
			payload.setGovAgencyCode(null);
		}
		
		long dossierStatisticId = 0;

		OpencpsDossierStatistic dossierStatistic = OpencpsDossierStatisticLocalServiceUtil.checkExsit(payload.getGroupId(), payload.getMonth(), payload.getYear(), payload.getGovAgencyCode(), payload.getDomainCode(), false);
		
		if (Validator.isNotNull(dossierStatistic)) {
			dossierStatisticId = dossierStatistic.getDossierStatisticId();
		}

		int pausingCount = 0;


		try {
			return OpencpsDossierStatisticLocalServiceUtil.updateStatistic(dossierStatisticId, payload.getCompanyId(),
					payload.getGroupId(), DossierStatisticConstants.STATISTIC_USER_ID,
					DossierStatisticConstants.STATISTIC_USER_NAME, payload.getMonth(), payload.getYear(),
					payload.getTotalCount(), payload.getDeniedCount(), payload.getCancelledCount(),
					payload.getProcessCount(), payload.getRemainingCount(), payload.getReceivedCount(),
					payload.getOnlineCount(), payload.getReleaseCount(), payload.getBetimesCount(),
					payload.getOntimeCount(), payload.getOvertimeCount(), payload.getDoneCount(),
					payload.getReleasingCount(), payload.getUnresolvedCount(), payload.getProcessingCount(),
					payload.getUndueCount(), payload.getOverdueCount(), pausingCount, payload.getOntimePercentage(),
					payload.getOvertimeInside(), payload.getOvertimeOutside(), payload.getInteroperatingCount(),
					payload.getWaitingCount(), payload.getGovAgencyCode(), payload.getGovAgencyName(),
					payload.getDomainCode(), payload.getDomainName(), false, payload.getOnegateCount(),
					payload.getOutsideCount(), payload.getInsideCount());
		} catch (SystemException | PortalException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
