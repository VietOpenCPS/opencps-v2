package org.opencps.statistic.rest.converter;

import java.util.ArrayList;
import java.util.List;

import org.opencps.statistic.model.OpencpsPersonStatistic;
import org.opencps.statistic.rest.dto.PersonResponse;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.springframework.core.convert.converter.Converter;

public class PersonStatisticConverter {

	//private static Log LOG = LogFactoryUtil.getLog(VotingStatisticConverter.class);
	//private static DateTimeFormatter _formartter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


	public static Converter<List<OpencpsPersonStatistic>, PersonResponse> getPersonResponse() {
		return (source) -> {

			PersonResponse personResponse = new PersonResponse();

			personResponse.setTotal(source.size());

			List<PersonStatisticData> personStatisticList = new ArrayList<>();
			PersonStatisticData personData = null;

			for (OpencpsPersonStatistic personStatistic : source) {
				personData = new PersonStatisticData();

				personData.setMonth(personStatistic.getMonth());
				personData.setYear(personStatistic.getYear());
				personData.setTotalVoted(personStatistic.getTotalVoted());
				personData.setVeryGoodCount(personStatistic.getVeryGoodCount());
				personData.setGoodCount(personStatistic.getGoodCount());
				personData.setBadCount(personStatistic.getBadCount());
				personData.setPercentVeryGood(personStatistic.getPercentVeryGood());
				personData.setPercentGood(personStatistic.getPercentGood());
				personData.setPercentBad(personStatistic.getPercentBad());
				// Add common
				personData.setGovAgencyCode(personStatistic.getGovAgencyCode());
				personData.setGovAgencyName(personStatistic.getGovAgencyName());
				personData.setEmployeeId(personStatistic.getEmployeeId());
				personData.setVotingCode(personStatistic.getVotingCode());
				personData.setVotingSubject(personStatistic.getVotingSubject());

				personStatisticList.add(personData);
			}

			personResponse.setData(personStatisticList);

			return personResponse;
		};
	}

}
