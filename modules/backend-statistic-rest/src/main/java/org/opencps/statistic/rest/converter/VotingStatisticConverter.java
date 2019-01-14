package org.opencps.statistic.rest.converter;

import java.util.ArrayList;
import java.util.List;

import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.rest.dto.VotingResultResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.springframework.core.convert.converter.Converter;

public class VotingStatisticConverter {

	//private static Log LOG = LogFactoryUtil.getLog(VotingStatisticConverter.class);
	//private static DateTimeFormatter _formartter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


	public static Converter<List<OpencpsVotingStatistic>, VotingResultResponse> getVotingResultResponse() {
		return (source) -> {

			VotingResultResponse votingResponse = new VotingResultResponse();

			votingResponse.setTotal(source.size());

			List<VotingResultStatisticData> votingStatisticList = new ArrayList<>();
			VotingResultStatisticData votingData = null;

			for (OpencpsVotingStatistic votingStatistic : source) {
				votingData = new VotingResultStatisticData();

				votingData.setMonth(votingStatistic.getMonth());
				votingData.setYear(votingStatistic.getYear());
				votingData.setTotalVoted(votingStatistic.getTotalVoted());
				votingData.setVeryGoodCount(votingStatistic.getVeryGoodCount());
				votingData.setGoodCount(votingStatistic.getGoodCount());
				votingData.setBadCount(votingStatistic.getBadCount());
				votingData.setPercentVeryGood(votingStatistic.getPercentVeryGood());
				votingData.setPercentGood(votingStatistic.getPercentGood());
				votingData.setPercentBad(votingStatistic.getPercentBad());
				// Add common
				votingData.setGovAgencyCode(votingStatistic.getGovAgencyCode());
				votingData.setGovAgencyName(votingStatistic.getGovAgencyName());
				votingData.setDomain(votingStatistic.getDomainCode());
				votingData.setDomainName(votingStatistic.getDomainName());
				votingData.setVotingCode(votingStatistic.getVotingCode());
				votingData.setVotingSubject(votingStatistic.getVotingSubject());

				votingStatisticList.add(votingData);
			}

			votingResponse.setData(votingStatisticList);

			return votingResponse;
		};
	}

}
