package org.opencps.statistic.rest.converter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.opencps.statistic.model.OpencpsDossier;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierData;
import org.opencps.statistic.rest.dto.DossierResponse;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticResponse;
import org.springframework.core.convert.converter.Converter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class DossierStatisticConverter {

	private static Log LOG = LogFactoryUtil.getLog(DossierStatisticConverter.class);

	private static DateTimeFormatter _formartter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	public static Converter<List<OpencpsDossier>, DossierResponse> getDossierResponse() {
		return (source) -> {
			
			//LOG.info("SOURCE_******" + source.size());

			DossierResponse dossierResponse = new DossierResponse();
			dossierResponse.setTotal(source.size());

			List<DossierData> dossierData = new ArrayList<>();

			for (OpencpsDossier dossier : source) {
				DossierData data = new DossierData();

				data.setDossierStatus(dossier.getDossierStatus());
				data.setDossierSubStatus(dossier.getDossierSubStatus());
				data.setReceiveDate(convertDate(dossier.getReceiveDate()));
				data.setOnline(Boolean.toString(dossier.getOnline()));
				data.setDueDate(convertDate(dossier.getDueDate()));
				data.setExtendDate(convertDate(dossier.getExtendDate()));
				data.setReceiveDate(convertDate(dossier.getReceiveDate()));
				data.setGovAgencyCode(dossier.getGovAgencyCode());
				data.setServiceCode(dossier.getServiceCode());
				data.setGroupId(dossier.getGroupId());
				data.setGovAgencyName(dossier.getGovAgencyName());
				
				dossierData.add(data);
			}

			dossierResponse.setData(dossierData);

			return dossierResponse;
		};
	}

	private static String convertDate(Date date) {
		if (Validator.isNull(date)) {
			return StringPool.BLANK;
		} else {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(_formartter);
		}
	}

	public static Converter<List<OpencpsDossierStatistic>, DossierStatisticResponse> getDossierStatisticResponse() {
		return (source) -> {

			DossierStatisticResponse dossierStatisticResponse = new DossierStatisticResponse();

			dossierStatisticResponse.setTotal(source.size());

			List<DossierStatisticData> dossierStatisticDatas = new ArrayList<>();

			for (OpencpsDossierStatistic dossierStatistic : source) {


				DossierStatisticData dossierStatisticData = new DossierStatisticData();

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

				dossierStatisticDatas.add(dossierStatisticData);
			}

			dossierStatisticResponse.setDossierStatisticData(dossierStatisticDatas);

			return dossierStatisticResponse;
		};
	}

}
