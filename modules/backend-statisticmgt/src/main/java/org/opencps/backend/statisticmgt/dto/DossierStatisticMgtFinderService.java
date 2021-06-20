package org.opencps.backend.statisticmgt.dto;

import com.liferay.portal.kernel.exception.PortalException;


public interface DossierStatisticMgtFinderService {
	public DossierStatisticMgtResponse finderDossierStatisticMgt(DossierStatisticMgtRequest dossierStatisticMgtRequest) throws PortalException;

}
