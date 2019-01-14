package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.rest.converter.DossierStatisticConverter;
import org.opencps.statistic.rest.converter.VotingStatisticConverter;
import org.opencps.statistic.rest.dto.DossierStatisticRequest;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.opencps.statistic.rest.dto.VotingResultRequest;
import org.opencps.statistic.rest.dto.VotingResultResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VotingStatisticFinderServiceImpl implements VotingStatisticFinderService {
	
	//private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticFinderServiceImpl.class);

	@Override
	public VotingResultResponse finderVotingStatistic(VotingResultRequest votingRequest)
			throws PortalException, SystemException {
		
//		LOG.info("***DossierStatisticFinderServiceImpl");
		
		List<OpencpsVotingStatistic> votingList = OpencpsVotingStatisticLocalServiceUtil.searchVotingStatistic(
				votingRequest.getGroupId(), votingRequest.getMonth(), votingRequest.getYear(),
				votingRequest.getVotingCode(), votingRequest.getDomain(), votingRequest.getGovAgencyCode(),
				votingRequest.getStart(), votingRequest.getEnd());

		return VotingStatisticConverter.getVotingResultResponse().convert(votingList);
	}

	@Override
	public VotingResultResponse finderVotingStatisticList(VotingResultRequest votingRequest)
			throws PortalException {
		List<OpencpsVotingStatistic> votingList = OpencpsVotingStatisticLocalServiceUtil.fetchVotingStatistic(
				votingRequest.getGroupId(), votingRequest.getMonth(), votingRequest.getYear(),
				votingRequest.getVotingCode(), votingRequest.getDomain(), votingRequest.getGovAgencyCode(),
				votingRequest.getStart(), votingRequest.getEnd());

		return VotingStatisticConverter.getVotingResultResponse().convert(votingList);
	}

}
