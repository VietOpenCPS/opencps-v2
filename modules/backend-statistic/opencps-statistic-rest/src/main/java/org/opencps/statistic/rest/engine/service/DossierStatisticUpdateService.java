package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public interface DossierStatisticUpdateService<R1> {
	void updateDossierStatistic(R1 payload) throws PortalException, SystemException;
}
