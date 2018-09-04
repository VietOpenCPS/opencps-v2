package org.opencps.statistic.rest.engine.service;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class DossierStatisticUpdateServiceImpl implements DossierStatisticUpdateService<DossierStatisticData> {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticUpdateServiceImpl.class);

	@Override
	public void updateDossierStatistic(DossierStatisticData payload) throws PortalException, SystemException {
		
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		if (payload.getGroupId() != 0 && payload.getYear() != 0) {
			
			engineUpdateAction.updateStatistic(payload);
		}

	}

}
