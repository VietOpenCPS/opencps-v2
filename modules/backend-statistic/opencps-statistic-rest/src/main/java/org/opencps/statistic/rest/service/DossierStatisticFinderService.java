package org.opencps.statistic.rest.service;

import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;

public interface DossierStatisticFinderService {
	public DossierStatisticResponse finderDossierStatistic(DossierStatisticRequest dossierStatisticRequest);
}
