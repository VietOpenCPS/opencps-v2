package org.opencps.statistic.rest.engine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.rest.util.DossierStatisticUtils;

public class StatisticEngineUpdate {
	public void updateStatisticData(Map<String, DossierStatisticData> statisticData, List<OpencpsDossierStatistic> datas) {

		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		for (Map.Entry<String, DossierStatisticData> me : statisticData.entrySet()) {

			DossierStatisticData payload = (DossierStatisticData) me.getValue();
			OpencpsDossierStatistic checkData = DossierStatisticUtils.checkExists(payload.getMonth(), payload.getYear(), payload.getDomainCode(), payload.getGovAgencyCode(), datas);
			if (checkData == null) {
				engineUpdateAction.createStatistic(payload);
			}
			else {
				engineUpdateAction.updateStatistic(payload);
			}
		}

//		try {
//			OpencpsDossierStatisticLocalServiceUtil.updateStatisticData(mappingDossierStatisticData(statisticData));
//		} catch (SystemException e) {
//		} catch (PortalException e) {
//		}
	}
	
	public void updateStatisticData(Map<String, DossierStatisticData> statisticData) {

		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		for (Map.Entry<String, DossierStatisticData> me : statisticData.entrySet()) {

			DossierStatisticData payload = (DossierStatisticData) me.getValue();

			engineUpdateAction.updateStatistic(payload);

		}

//		try {
//			OpencpsDossierStatisticLocalServiceUtil.updateStatisticData(mappingDossierStatisticData(statisticData));
//		} catch (SystemException e) {
//		} catch (PortalException e) {
//		}
	}

	private Map<String, org.opencps.statistic.dto.DossierStatisticData> mappingDossierStatisticData(Map<String, DossierStatisticData> statisticData) {
		Map<String, org.opencps.statistic.dto.DossierStatisticData> statisticDataResult = new HashMap<String, org.opencps.statistic.dto.DossierStatisticData>();
		for (Map.Entry<String, DossierStatisticData> entry : statisticData.entrySet()) {
			String key = entry.getKey();
			org.opencps.statistic.dto.DossierStatisticData data = new org.opencps.statistic.dto.DossierStatisticData();
			DossierStatisticData tempData = entry.getValue();
			data.setMonth(tempData.getMonth());
			data.setYear(tempData.getYear());
			data.setTotalCount(tempData.getTotalCount());
			data.setDeniedCount(tempData.getDeniedCount());
			data.setCancelledCount(tempData.getCancelledCount());
			data.setProcessCount(tempData.getProcessCount());
			data.setRemainingCount(tempData.getRemainingCount());
			data.setReceivedCount(tempData.getReceivedCount());
			data.setOnlineCount(tempData.getOnlineCount());
			data.setOnegateCount(tempData.getOnegateCount());
			data.setOutsideCount(tempData.getOutsideCount());
			data.setInsideCount(tempData.getInsideCount());
			data.setReleaseCount(tempData.getReleaseCount());
			data.setBetimesCount(tempData.getBetimesCount());
			data.setOntimeCount(tempData.getOntimeCount());
			data.setOvertimeCount(tempData.getOvertimeCount());
			data.setOvertimeInside(tempData.getOvertimeInside());
			data.setOvertimeOutside(tempData.getOvertimeOutside());
			data.setDoneCount(tempData.getDoneCount());
			data.setReleasingCount(tempData.getReleasingCount());
			data.setUnresolvedCount(tempData.getUnresolvedCount());
			data.setProcessingCount(tempData.getProcessingCount());
			data.setUndueCount(tempData.getUndueCount());
			data.setOverdueCount(tempData.getOverdueCount());
			data.setInteroperatingCount(tempData.getInteroperatingCount());
			data.setWaitingCount(tempData.getWaitingCount());
			data.setOntimePercentage(tempData.getOntimePercentage());
			data.setGovAgencyCode(tempData.getGovAgencyCode());
			data.setGovAgencyName(tempData.getGovAgencyName());
			data.setDomainCode(tempData.getDomainCode());
			data.setDomainName(tempData.getDomainName());
			data.setReporting(tempData.isReporting());
			data.setCompanyId(tempData.getCompanyId());
			data.setGroupId(tempData.getGroupId());
			
			statisticDataResult.put(key, data);
		}
		return statisticDataResult;
	}
//	public void updateStatisticData(Map<String, DossierStatisticData> statisticData,
//			List<OpencpsDossierStatistic> statisticList) throws NoSuchOpencpsDossierStatisticException {
//
//		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
//
//		if (statisticList != null && statisticList.size() > 0) {
//			
//		}
//		for (OpencpsDossierStatistic dossierStatistic : statisticList) {
//
//			if (statisticData != null && !statisticData.isEmpty()) {
//				boolean flagCompare = true;
//				for (Map.Entry<String, DossierStatisticData> me : statisticData.entrySet()) {
//					//Get value dossierStatisticData
//					DossierStatisticData payload = (DossierStatisticData) me.getValue();
//					// Check list DB and map
//						if (payload.getDomainCode().equals(dossierStatistic.getDomainCode())
//								&& payload.getGovAgencyCode().equals(dossierStatistic.getGovAgencyCode())) {
//							
//							engineUpdateAction.removeDossierStatisticByG_M_Y_G_D(payload.getGroupId(), payload.getMonth(),
//									payload.getYear(), payload.getGovAgencyCode(), payload.getDomainCode());
//							//
//							//statisticList.remove(dossierStatistic);
//							statisticData.remove(me.getKey());
//							//
//							engineUpdateAction.updateStatistic(payload);
//							flagCompare = false;
//							break;
//						}
//				}
//				if (flagCompare) {
//					engineUpdateAction.removeDossierStatisticByG_M_Y_G_D(dossierStatistic.getGroupId(),
//							dossierStatistic.getMonth(), dossierStatistic.getYear(), dossierStatistic.getGovAgencyCode(),
//							dossierStatistic.getDomainCode());
//				}
//			} else {
//				engineUpdateAction.removeDossierStatisticByG_M_Y_G_D(dossierStatistic.getGroupId(),
//						dossierStatistic.getMonth(), dossierStatistic.getYear(), dossierStatistic.getGovAgencyCode(),
//						dossierStatistic.getDomainCode());
//			}
//		}
//
//	}


	public void updateVotingStatisticData(Map<String, VotingResultStatisticData> votingData) {

		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		for (Map.Entry<String, VotingResultStatisticData> me : votingData.entrySet()) {

			VotingResultStatisticData payload = me.getValue();

			engineUpdateAction.updateVotingStatistic(payload);

		}
	}

	public void updatePersonStatisticData(Map<String, PersonStatisticData> statisticData) {

		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		for (Map.Entry<String, PersonStatisticData> me : statisticData.entrySet()) {

			PersonStatisticData payload = (PersonStatisticData) me.getValue();

			engineUpdateAction.updatePersonStatistic(payload);

		}

	}
}
