package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;

import org.opencps.backend.statisticmgt.dto.DossierStatisticMgtData;
import org.opencps.backend.statisticmgt.dto.DossierStatisticMgtResponse;
import org.opencps.statistic.model.OpencpsDossierStatisticMgt;
import org.springframework.core.convert.converter.Converter;

public class ConvertUtil {

	private static Log _log = LogFactoryUtil.getLog(ConvertUtil.class);

	public static Converter<List<OpencpsDossierStatisticMgt>, DossierStatisticMgtResponse> getDossierStatisticMgtResponse() {
		try {
			return (source) -> {

				DossierStatisticMgtResponse dossierStatisticMgtResponse = new DossierStatisticMgtResponse();

				dossierStatisticMgtResponse.setTotal(source.size());
				
				List<DossierStatisticMgtData> dossierStatisticMgtDatas = new ArrayList<>();
				
				for (OpencpsDossierStatisticMgt dossierStatistic : source) {


					DossierStatisticMgtData dossierStatisticMgtData = new DossierStatisticMgtData();

					dossierStatisticMgtData.setGroupId(dossierStatistic.getGroupId());
					dossierStatisticMgtData.setMonth(dossierStatistic.getMonth());
					dossierStatisticMgtData.setYear(dossierStatistic.getYear());
					dossierStatisticMgtData.setTotalCount(dossierStatistic.getTotalCount());
					dossierStatisticMgtData.setProcessCount(dossierStatistic.getProcessCount());
					dossierStatisticMgtData.setRemainingCount(dossierStatistic.getRemainingCount());
					dossierStatisticMgtData.setReceivedCount(dossierStatistic.getReceivedCount());
					dossierStatisticMgtData.setOnlineCount(dossierStatistic.getOnlineCount());
					dossierStatisticMgtData.setReleaseCount(dossierStatistic.getReleaseCount());
					dossierStatisticMgtData.setBetimesCount(dossierStatistic.getBetimesCount());
					dossierStatisticMgtData.setOntimeCount(dossierStatistic.getOntimeCount());
					dossierStatisticMgtData.setOvertimeCount(dossierStatistic.getOvertimeCount());
					dossierStatisticMgtData.setWaitingCount(dossierStatistic.getWaitingCount());
					dossierStatisticMgtData.setDoneCount(dossierStatistic.getDoneCount());
					dossierStatisticMgtData.setReleasingCount(dossierStatistic.getReleasingCount());
					dossierStatisticMgtData.setProcessingCount(dossierStatistic.getProcessingCount());
					dossierStatisticMgtData.setUndueCount(dossierStatistic.getUndueCount());
					dossierStatisticMgtData.setOverdueCount(dossierStatistic.getOverdueCount());
					dossierStatisticMgtData.setOntimePercentage(dossierStatistic.getOntimePercentage());
					dossierStatisticMgtData.setGovAgencyCode(dossierStatistic.getGovAgencyCode());
					dossierStatisticMgtData.setGovAgencyName(dossierStatistic.getGovAgencyName());
					dossierStatisticMgtData.setDomainCode(dossierStatistic.getDomainCode());
					dossierStatisticMgtData.setDomainName(dossierStatistic.getDomainName());
					dossierStatisticMgtData.setServiceCode(dossierStatistic.getServiceCode());
					dossierStatisticMgtData.setServiceName(dossierStatistic.getServiceName());
					dossierStatisticMgtData.setOnegateCount(dossierStatistic.getOnegateCount());
					dossierStatisticMgtData.setGroupBy(dossierStatistic.getGroupBy());
					
					dossierStatisticMgtDatas.add(dossierStatisticMgtData);
				}

				dossierStatisticMgtResponse.setDossierStatisticMgtData(dossierStatisticMgtDatas);

				return dossierStatisticMgtResponse;
			};
		} catch (Exception e) {
			_log.error(e);
		}
		return null;
	}
}
