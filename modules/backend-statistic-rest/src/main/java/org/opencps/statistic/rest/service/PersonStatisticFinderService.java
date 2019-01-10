package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;

import org.opencps.statistic.rest.dto.PersonRequest;
import org.opencps.statistic.rest.dto.PersonResponse;

public interface PersonStatisticFinderService {
	public PersonResponse finderPersonStatistic(PersonRequest personRequest) throws PortalException;
	public PersonResponse finderPersonStatisticList(PersonRequest personRequest) throws PortalException;
}