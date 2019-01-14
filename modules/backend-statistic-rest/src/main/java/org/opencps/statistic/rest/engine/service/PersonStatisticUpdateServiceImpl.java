package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonStatisticUpdateServiceImpl implements PersonStatisticUpdateService<PersonStatisticData> {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticUpdateServiceImpl.class);

	@Override
	public void updatePersonStatistic(PersonStatisticData payload) throws PortalException, SystemException {
		
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		if (payload.getGroupId() != 0 && payload.getYear() != 0) {
			
			engineUpdateAction.updatePersonStatistic(payload);
		}

	}

}
