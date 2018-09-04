package org.opencps.statistic.rest.service;

import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;

import com.liferay.portal.kernel.exception.PortalException;

public interface DossierStatisticFinderService {
	public DossierStatisticResponse finderDossierStatistic(DossierStatisticRequest dossierStatisticRequest) throws PortalException;
	public DossierStatisticResponse finderDossierStatistics(DossierStatisticRequest dossierStatisticRequest) throws PortalException;
}
