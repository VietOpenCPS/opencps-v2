package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;

import org.opencps.statistic.rest.dto.DossierStatisticManualResponse;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;

public interface DossierStatisticManualFinderService {
	public DossierStatisticManualResponse finderDossierStatistics(DossierStatisticRequest dossierStatisticRequest) throws PortalException;
	public DossierStatisticManualResponse finderDossierStatisticSystem(DossierStatisticRequest dossierStatisticRequest) throws PortalException;

}
