package org.opencps.statistic.rest.service;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierStatisticGovUpdateServiceImpl
		implements DossierStatisticUpdateService<DossierStatisticData, OpencpsDossierStatistic> {

	@Override
	public OpencpsDossierStatistic updateDossierStatistic(DossierStatisticData payload)
			throws PortalException, SystemException {

		/* Check if exsist */
		OpencpsDossierStatistic opencpsDossierStatistic = OpencpsDossierStatisticLocalServiceUtil
				.getByGovMonthYearDomain(payload.getGroupId(), payload.getGovAgencyCode(), payload.getMonth(),
						payload.getYear(), payload.getDomainCode());

		long dossierStatisticId = 0;

		if (Validator.isNotNull(opencpsDossierStatistic)) {
			dossierStatisticId = opencpsDossierStatistic.getDossierStatisticId();
		}

		/* TODO Pausing ??? */

		int pausingCount = 0;

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
				payload.getWaitingCount(), payload.getGovAgencyCode(), payload.getGovAgencyName(), StringPool.BLANK,
				StringPool.BLANK, false);
	}

}
