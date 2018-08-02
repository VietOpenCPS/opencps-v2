package org.opencps.statistic.rest.service;

import java.util.HashMap;
import java.util.Map;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

public class DossierStatisticDomainUpdateServiceImpl
		implements DossierStatisticUpdateService<HashMap<String, DossierStatisticData>, OpencpsDossierStatistic> {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticDomainUpdateServiceImpl.class);

	@Override
	public OpencpsDossierStatistic updateDossierStatistic(HashMap<String, DossierStatisticData> input)
			throws PortalException, SystemException {

		for (Map.Entry me : input.entrySet()) {

			LOG.info("&******&" + me.getKey().toString());

			DossierStatisticData payload = (DossierStatisticData) me.getValue();

			/* Check if exsist */
			OpencpsDossierStatistic opencpsDossierStatistic = OpencpsDossierStatisticLocalServiceUtil
					.getByGovMonthYearDomain(payload.getGroupId(), payload.getGovAgencyCode(), payload.getMonth(),
							payload.getYear(), payload.getDomainCode());

			long dossierStatisticId = 0;

			if (Validator.isNotNull(opencpsDossierStatistic)) {
				dossierStatisticId = opencpsDossierStatistic.getDossierStatisticId();
			}

			int pausingCount = 0;

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
					payload.getDomainCode(), payload.getDomainName(), false);
		}

		return null;
	}

}
