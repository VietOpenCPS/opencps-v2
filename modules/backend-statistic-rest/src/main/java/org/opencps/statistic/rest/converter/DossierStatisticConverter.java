package org.opencps.statistic.rest.converter;

import java.util.ArrayList;
import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.springframework.core.convert.converter.Converter;

public class DossierStatisticConverter {

	//private static Log LOG = LogFactoryUtil.getLog(DossierStatisticConverter.class);

	//private static DateTimeFormatter _formartter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


	public static Converter<List<OpencpsDossierStatistic>, DossierStatisticResponse> getDossierStatisticResponse() {
		return (source) -> {

			DossierStatisticResponse dossierStatisticResponse = new DossierStatisticResponse();

			dossierStatisticResponse.setTotal(source.size());

			List<DossierStatisticData> dossierStatisticDatas = new ArrayList<>();

			for (OpencpsDossierStatistic dossierStatistic : source) {


				DossierStatisticData dossierStatisticData = new DossierStatisticData();

				dossierStatisticData.setGroupId(dossierStatistic.getGroupId());
				dossierStatisticData.setMonth(dossierStatistic.getMonth());
				dossierStatisticData.setYear(dossierStatistic.getYear());
				dossierStatisticData.setTotalCount(dossierStatistic.getTotalCount());
				dossierStatisticData.setDeniedCount(dossierStatistic.getDeniedCount());
				dossierStatisticData.setCancelledCount(dossierStatistic.getCancelledCount());
				dossierStatisticData.setProcessCount(dossierStatistic.getProcessCount());
				dossierStatisticData.setRemainingCount(dossierStatistic.getRemainingCount());
				dossierStatisticData.setReceivedCount(dossierStatistic.getReceivedCount());
				dossierStatisticData.setOnlineCount(dossierStatistic.getOnlineCount());
				dossierStatisticData.setReleaseCount(dossierStatistic.getReleaseCount());
				dossierStatisticData.setBetimesCount(dossierStatistic.getBetimesCount());
				dossierStatisticData.setOntimeCount(dossierStatistic.getOntimeCount());
				dossierStatisticData.setOvertimeCount(dossierStatistic.getOvertimeCount());

				dossierStatisticData.setOvertimeInside(dossierStatistic.getOvertimeInside());
				dossierStatisticData.setOvertimeOutside(dossierStatistic.getOvertimeOutside());
				dossierStatisticData.setInteroperatingCount(dossierStatistic.getInteroperatingCount());
				dossierStatisticData.setWaitingCount(dossierStatistic.getWaitingCount());

				dossierStatisticData.setDoneCount(dossierStatistic.getDoneCount());
				dossierStatisticData.setReleasingCount(dossierStatistic.getReleasingCount());
				dossierStatisticData.setUnresolvedCount(dossierStatistic.getUnresolvedCount());
				dossierStatisticData.setProcessingCount(dossierStatistic.getProcessingCount());
				dossierStatisticData.setUndueCount(dossierStatistic.getUndueCount());
				dossierStatisticData.setOverdueCount(dossierStatistic.getOverdueCount());
				dossierStatisticData.setOntimePercentage(dossierStatistic.getOntimePercentage());
				dossierStatisticData.setGovAgencyCode(dossierStatistic.getGovAgencyCode());
				
				dossierStatisticData.setGovAgencyName(dossierStatistic.getGovAgencyName());
				dossierStatisticData.setDomainCode(dossierStatistic.getDomainCode());
				dossierStatisticData.setDomainName(dossierStatistic.getDomainName());
				dossierStatisticData.setReporting(dossierStatistic.getReporting());

				dossierStatisticData.setOnegateCount(dossierStatistic.getOnegateCount());
				dossierStatisticData.setInsideCount(dossierStatistic.getInsideCount());
				dossierStatisticData.setOutsideCount(dossierStatistic.getOutsideCount());
				
				dossierStatisticDatas.add(dossierStatisticData);
			}

			dossierStatisticResponse.setDossierStatisticData(dossierStatisticDatas);

			return dossierStatisticResponse;
		};
	}

}
