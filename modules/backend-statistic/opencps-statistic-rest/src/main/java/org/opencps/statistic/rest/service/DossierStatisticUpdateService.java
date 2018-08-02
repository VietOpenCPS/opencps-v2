package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public interface DossierStatisticUpdateService<R1, R2> {
	R2 updateDossierStatistic(R1 payload) throws PortalException, SystemException;
}
