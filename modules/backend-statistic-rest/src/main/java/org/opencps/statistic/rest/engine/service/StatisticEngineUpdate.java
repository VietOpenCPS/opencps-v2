package org.opencps.statistic.rest.engine.service;

import java.util.Map;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;

public class StatisticEngineUpdate {
	public void updateStatisticData(Map<String, DossierStatisticData> statisticData) {

		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		for (Map.Entry me : statisticData.entrySet()) {

			DossierStatisticData payload = (DossierStatisticData) me.getValue();

			engineUpdateAction.updateStatistic(payload);

		}

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

			//engineUpdateAction.updateStatistic(payload);

		}

	}
}
