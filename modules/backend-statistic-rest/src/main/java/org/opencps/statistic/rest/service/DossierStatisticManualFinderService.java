package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;

import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;

public interface DossierStatisticManualFinderService {
	public DossierStatisticResponse finderDossierStatistics(DossierStatisticRequest dossierStatisticRequest) throws PortalException;
	public DossierStatisticResponse finderDossierStatisticSystem(DossierStatisticRequest dossierStatisticRequest) throws PortalException;

}
