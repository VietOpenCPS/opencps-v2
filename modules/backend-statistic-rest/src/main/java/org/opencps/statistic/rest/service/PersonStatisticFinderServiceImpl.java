package org.opencps.statistic.rest.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.List;

import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.rest.converter.PersonStatisticConverter;
import org.opencps.statistic.rest.dto.PersonRequest;
import org.opencps.statistic.rest.dto.PersonResponse;
import org.opencps.statistic.service.OpencpsPersonStatisticLocalServiceUtil;


public class PersonStatisticFinderServiceImpl implements PersonStatisticFinderService {
	
	//private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticFinderServiceImpl.class);

	@Override
	public PersonResponse finderPersonStatistic(PersonRequest personRequest)
			throws PortalException, SystemException {
		
//		LOG.info("***DossierStatisticFinderServiceImpl");
		
//		List<OpencpsPersonStatistic> votingList = OpencpsPersonStatisticLocalServiceUtil.searchVotingStatistic(
//				personRequest.getGroupId(), personRequest.getMonth(), personRequest.getYear(),
//				personRequest.getVotingCode(), personRequest.getDomain(), personRequest.getGovAgencyCode(),
//				personRequest.getStart(), personRequest.getEnd());
		List<OpencpsPersonStatistic> votingList = new ArrayList<OpencpsPersonStatistic>();

		return PersonStatisticConverter.getPersonResponse().convert(votingList);
	}

	@Override
	public PersonResponse finderPersonStatisticList(PersonRequest personRequest)
			throws PortalException {
//		List<OpencpsPersonStatistic> votingList = OpencpsPersonStatisticLocalServiceUtil.fetchVotingStatistic(
//				personRequest.getGroupId(), personRequest.getMonth(), personRequest.getYear(),
//				personRequest.getVotingCode(), personRequest.getDomain(), personRequest.getGovAgencyCode(),
//				personRequest.getStart(), personRequest.getEnd());
		
		List<OpencpsPersonStatistic> votingList = new ArrayList<OpencpsPersonStatistic>();

		return PersonStatisticConverter.getPersonResponse().convert(votingList);
	}

}
