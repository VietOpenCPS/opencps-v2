package org.opencps.statistic.rest.converter;

import java.util.List;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.springframework.core.convert.converter.Converter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DossierStatisticConverter {
	
	private static Log LOG = LogFactoryUtil.getLog(DossierStatisticConverter.class);
	
	public static Converter<List<OpencpsDossierStatistic>, DossierStatisticResponse> getDossierStatisticResponse() {
		return (source) -> {
			LOG.info("source_" + source.size());
			
			DossierStatisticResponse dossierStatisticResponse = new DossierStatisticResponse();
			
			for (OpencpsDossierStatistic dossierStatistic : source) {

				dossierStatistic.setBetimesCount(22);

				DossierStatisticData dossierStatisticData = new DossierStatisticData();

				dossierStatisticData.setBetimesCount(dossierStatistic.getBetimesCount());
				dossierStatisticResponse.setDossierStatisticData(dossierStatisticData);

			}
			return dossierStatisticResponse;
		};
	}
}
