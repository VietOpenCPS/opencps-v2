package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatisticManual;
import org.opencps.statistic.rest.converter.DossierStatisticConverter;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.service.OpencpsDossierStatisticManualLocalServiceUtil;

public class DossierStatisticManualFinderServiceImpl implements DossierStatisticManualFinderService {
	@Override
	public DossierStatisticResponse finderDossierStatisticSystem(DossierStatisticRequest dossierStatisticRequest)
			throws PortalException, SystemException {		
		List<OpencpsDossierStatisticManual> dossierStatistics = OpencpsDossierStatisticManualLocalServiceUtil
				.searchDossierStatisticSystem(dossierStatisticRequest.getGroupId(), dossierStatisticRequest.getMonth(),
						dossierStatisticRequest.getYear(), dossierStatisticRequest.getDomain(),
						dossierStatisticRequest.getGovAgencyCode(), dossierStatisticRequest.getSystem(), "",
						dossierStatisticRequest.getStart(), dossierStatisticRequest.getEnd());

		return DossierStatisticConverter.getDossierStatisticManualResponse().convert(dossierStatistics);
	}

	@Override
	public DossierStatisticResponse finderDossierStatistics(DossierStatisticRequest dossierStatisticRequest)
			throws PortalException {
		List<OpencpsDossierStatisticManual> dossierStatistics = OpencpsDossierStatisticManualLocalServiceUtil.fetchDossierStatistic(
				dossierStatisticRequest.getGroupId(), dossierStatisticRequest.getMonth(),
				dossierStatisticRequest.getYear(), dossierStatisticRequest.getDomain(),
				dossierStatisticRequest.getGovAgencyCode(), dossierStatisticRequest.getSystem(),
				dossierStatisticRequest.getGroupAgencyCode(), dossierStatisticRequest.getStart(),
				dossierStatisticRequest.getEnd());

		if (dossierStatistics != null && dossierStatistics.size() > 0) {
			return DossierStatisticConverter.getDossierStatisticManualResponse().convert(dossierStatistics);
		}

		return null;
	}
}
