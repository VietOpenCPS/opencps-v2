package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public interface PersonStatisticUpdateService<R1> {
	void updatePersonStatistic(R1 payload) throws PortalException, SystemException;
}