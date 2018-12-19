package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;

import org.opencps.statistic.rest.dto.VotingResultRequest;
import org.opencps.statistic.rest.dto.VotingResultResponse;

public interface VotingStatisticFinderService {
	public VotingResultResponse finderVotingStatistic(VotingResultRequest votingResultRequest) throws PortalException;
	public VotingResultResponse finderVotingStatisticList(VotingResultRequest votingResultRequest) throws PortalException;
}