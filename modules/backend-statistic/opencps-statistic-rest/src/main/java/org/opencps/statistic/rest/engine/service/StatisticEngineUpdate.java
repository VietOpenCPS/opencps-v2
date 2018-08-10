package org.opencps.statistic.rest.engine.service;

import java.util.Map;
import java.util.Optional;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class StatisticEngineUpdate {
	public void updateStatisticData(Map<String, DossierStatisticData> statisticData) {

		for (Map.Entry me : statisticData.entrySet()) {

			DossierStatisticData payload = (DossierStatisticData) me.getValue();

			Optional<OpencpsDossierStatistic> dossierStatistics = Optional
					.ofNullable(OpencpsDossierStatisticLocalServiceUtil.getByGovMonthYearDomain(payload.getGroupId(),
							payload.getGovAgencyCode(), payload.getMonth(), payload.getYear(), payload.getDomainCode(),
							false));

			int pausingCount = 0;

			long dossierStatisticId = 0;

			if (dossierStatistics.isPresent()) {
				dossierStatisticId = dossierStatistics.get().getDossierStatisticId();
			}

			try {
				OpencpsDossierStatisticLocalServiceUtil.updateStatistic(dossierStatisticId, payload.getCompanyId(),
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
						payload.getOutsideCount(), payload.getRemainingCount());
			} catch (SystemException | PortalException e) {
				e.printStackTrace();
			}
		}

	}
}
