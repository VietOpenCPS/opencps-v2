package org.opencps.statistic.rest.engine.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VotingStatisticUpdateServiceImpl implements VotingStatisticUpdateService<VotingResultStatisticData> {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticUpdateServiceImpl.class);

	@Override
	public void updateVotingStatistic(VotingResultStatisticData payload) throws PortalException, SystemException {
		
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		if (payload.getGroupId() != 0 && payload.getYear() != 0) {
			
			engineUpdateAction.updateVotingStatistic(payload);
		}

	}

}
