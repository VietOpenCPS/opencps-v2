package org.opencps.statistic.rest.service;

import java.util.Optional;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierStatisticUtils;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

public class DossierStatisticGovUpdateServiceImpl implements DossierStatisticUpdateService<DossierStatisticData> {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticGovUpdateServiceImpl.class);

	@Override
	public void updateDossierStatistic(DossierStatisticData payload) throws PortalException, SystemException {

		LOG.info("Ahihi, HA HA****>>>>");

		if (payload.getGroupId() != 0 && payload.getYear() != 0) {

			DossierStatisticUtils.logAsFormattedJson(LOG, payload);
			
			if (Validator.isNull(payload.getGovAgencyCode())) {
				payload.setGovAgencyCode(null);
			}
			
			if (Validator.isNull(payload.getDomainCode())) {
				payload.setDomainCode(null);
			}

			Optional<OpencpsDossierStatistic> dossierStatistics = Optional
					.ofNullable(OpencpsDossierStatisticLocalServiceUtil.getByGovMonthYearDomain(payload.getGroupId(),
							payload.getGovAgencyCode(), payload.getMonth(), payload.getYear(), payload.getDomainCode()));
			
			int pausingCount = 0;
			
			long dossierStatisticId = 0;

			if (dossierStatistics.isPresent()) {
				dossierStatisticId = dossierStatistics.get().getDossierStatisticId();
				LOG.info("***UPDATE >>>>");
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
						payload.getUndueCount(), payload.getOverdueCount(), pausingCount,
						payload.getOntimePercentage(), payload.getOvertimeInside(), payload.getOvertimeOutside(),
						payload.getInteroperatingCount(), payload.getWaitingCount(), payload.getGovAgencyCode(),
						payload.getGovAgencyName(), payload.getDomainCode(), payload.getDomainName(), false);
			} catch (SystemException | PortalException e) {
				e.printStackTrace();
			}
		}

	}

}
