package org.opencps.statistic.rest.service;

import org.opencps.statistic.rest.dto.DossierRequest;
import org.opencps.statistic.rest.dto.DossierResponse;

import com.liferay.portal.kernel.exception.PortalException;

public interface DossierFinderService {
	public DossierResponse searchDossierService(DossierRequest payload) throws PortalException;
}
