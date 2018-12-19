package org.opencps.statistic.rest.engine.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;

public class StatisticEngineFetch {

	//private final static Logger LOG = LoggerFactory.getLogger(StatisticEngineFetch.class);

	public void fecthStatisticData(long groupId, Map<String, DossierStatisticData> statisticData,
			List<GetDossierData> lsDossierData, int month) {

		//LOG.info("STARTTING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		for (GetDossierData dossierData : lsDossierData) {
			StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();
			//Test
			//if (dossierData.getGovAgencyCode().equals("TXPT")) {
				
			// all site, all domain
			
			String type1 = "all@all@" + groupId;

			DossierStatisticData dataType1 = new DossierStatisticData();

			if (statisticData.containsKey(type1)) {
				dataType1 = statisticData.get(type1);
			}

			engineFetchEntry.updateDossierStatisticData(dataType1, dossierData, month);
			dataType1 = processOnTimePercent(dataType1);

			statisticData.put(type1, dataType1);
			

			// all site each domain
			String type2 = "all@" + dossierData.getDomainCode() + "@" + groupId;
			
			DossierStatisticData dataType2 = new DossierStatisticData();


			dataType2.setDomainCode(dossierData.getDomainCode());
			dataType2.setDomainName(dossierData.getDomainName());

			if (statisticData.containsKey(type2)) {
				dataType2 = statisticData.get(type2);
			}

			engineFetchEntry.updateDossierStatisticData(dataType2, dossierData, month);
			dataType2 = processOnTimePercent(dataType2);

			statisticData.put(type2, dataType2);

			// each site all domain
			
			String type3 = dossierData.getGovAgencyCode() + "@all@" + groupId;
			//System.out.println("type3: "+type3);

			DossierStatisticData dataType3 = new DossierStatisticData();
			dataType3.setGovAgencyCode(dossierData.getGovAgencyCode());
			dataType3.setGovAgencyName(dossierData.getGovAgencyName());


			if (statisticData.containsKey(type3)) {
				//System.out.println("type3_TRUE: "+type3);
				dataType3 = statisticData.get(type3);
			}

			engineFetchEntry.updateDossierStatisticData(dataType3, dossierData, month);
			dataType3 = processOnTimePercent(dataType3);
			//System.out.println("dataType3: "+dataType3.getTotalCount());

			statisticData.put(type3, dataType3);
			//System.out.println("statisticData: "+statisticData.get(type3).getTotalCount());

			// each site each domain
			
			String type4 = dossierData.getGovAgencyCode() + "@" + dossierData.getDomainCode() + "@" + groupId;
			
			DossierStatisticData dataType4 = new DossierStatisticData();
			dataType4.setGovAgencyCode(dossierData.getGovAgencyCode());
			dataType4.setGovAgencyName(dossierData.getGovAgencyName());

			dataType4.setDomainCode(dossierData.getDomainCode());
			dataType4.setDomainName(dossierData.getDomainName());
			
			if (statisticData.containsKey(type4)) {
				dataType4 = statisticData.get(type4);
			}

			engineFetchEntry.updateDossierStatisticData(dataType4, dossierData, month);
			dataType4 = processOnTimePercent(dataType4);

			statisticData.put(type4, dataType4);
			//}
		}
	}

	//LamTV_Process statistic check
	private static final String MSG_ALL = "all";

	public Map<String, VotingResultStatisticData> getStatisticVotingData(long groupId,
			List<GetVotingResultData> votingDataList, int month) {

		//LOG.info("STARTTING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Map<String, VotingResultStatisticData> statisticData = new HashMap<String, VotingResultStatisticData>();
		String MSG_ALL_SITE_ALL_DOMAIN = MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + groupId;

		for (GetVotingResultData votingData : votingDataList) {
			System.out.println("DossierDataJSON: "+JSONFactoryUtil.looseSerialize(votingData));
			StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();

			/** Calculator all site, all domain - START */
			
			//String type1 = "all@all@" + groupId;
			//System.out.println("type1: "+type1);

			VotingResultStatisticData dataType1 = new VotingResultStatisticData();

			if (statisticData.containsKey(MSG_ALL_SITE_ALL_DOMAIN)) {
				//System.out.println("type1: "+MSG_ALL_SITE_ALL_DOMAIN);
				dataType1 = statisticData.get(MSG_ALL_SITE_ALL_DOMAIN);
			}

			//System.out.println("type1: "+MSG_ALL_SITE_ALL_DOMAIN);
			engineFetchEntry.calculateVotingStatisticData(dataType1, votingData, month);
			//dataType1 = processOnTimePercent(dataType1);

			statisticData.put(MSG_ALL_SITE_ALL_DOMAIN, dataType1);
			
			/** Calculator all site, all domain - END */

			// all site each domain
			String type2 = MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + votingData.getVotingCode()
					+ StringPool.AT + groupId;
			
			VotingResultStatisticData dataType2 = new VotingResultStatisticData();

			dataType2.setVotingCode(votingData.getVotingCode());

			if (statisticData.containsKey(type2)) {
				dataType2 = statisticData.get(type2);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType2, votingData, month);
			//dataType2 = processOnTimePercent(dataType2);

			statisticData.put(type2, dataType2);

			// each site all domain
			String type3 = MSG_ALL + StringPool.AT + votingData.getServiceCode() + StringPool.AT
					+ votingData.getVotingCode() + StringPool.AT + groupId;


			VotingResultStatisticData dataType3 = new VotingResultStatisticData();
			dataType3.setServiceCode(votingData.getServiceCode());
			dataType3.setServiceName(votingData.getServiceName());
			dataType3.setVotingCode(votingData.getVotingCode());

			if (statisticData.containsKey(type3)) {
				dataType3 = statisticData.get(type3);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType3, votingData, month);
			//dataType3 = processOnTimePercent(dataType3);

			statisticData.put(type3, dataType3);

			// each site each domain
			
			String type4 = votingData.getGovAgencyCode() + StringPool.AT + MSG_ALL + StringPool.AT
					+ votingData.getVotingCode() + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType4 = new VotingResultStatisticData();
			dataType4.setGovAgencyCode(votingData.getGovAgencyCode());
			dataType4.setGovAgencyName(votingData.getGovAgencyName());
			dataType4.setVotingCode(votingData.getVotingCode());

			if (statisticData.containsKey(type4)) {
				dataType4 = statisticData.get(type4);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType4, votingData, month);
			//dataType4 = processOnTimePercent(dataType4);

			statisticData.put(type4, dataType4);
			
			// each site each domain
			String type5 = votingData.getGovAgencyCode() + StringPool.AT + votingData.getServiceCode() + StringPool.AT
					+ votingData.getVotingCode() + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType5 = new VotingResultStatisticData();
			dataType4.setGovAgencyCode(votingData.getGovAgencyCode());
			dataType4.setGovAgencyName(votingData.getGovAgencyName());
			dataType3.setServiceCode(votingData.getServiceCode());
			dataType3.setServiceName(votingData.getServiceName());
			dataType4.setVotingCode(votingData.getVotingCode());

			if (statisticData.containsKey(type5)) {
				dataType5 = statisticData.get(type5);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType5, votingData, month);
			//dataType4 = processOnTimePercent(dataType4);

			statisticData.put(type5, dataType5);
		}
		//
		Map<String, VotingResultStatisticData> votingStatisitcData = new HashMap<String, VotingResultStatisticData>();
		if (statisticData != null && statisticData.size() > 0) {
			for (Map.Entry<String, VotingResultStatisticData> entry : statisticData.entrySet()) {
				VotingResultStatisticData dataType = entry.getValue();
				if (dataType != null) {
					dataType = processVotingPercent(dataType);
				}
				votingStatisitcData.put(entry.getKey(), dataType);
			}
		}
		
		return votingStatisitcData;
	}

	private DossierStatisticData processOnTimePercent(DossierStatisticData dataType) {
		int ontimePercent = 0;
		int releaseCount = dataType.getReleaseCount();
		//_log.info("releaseCount: "+releaseCount);
		
		if (releaseCount > 0) {
			int betimesCount = dataType.getBetimesCount();
			int ontimeCount = dataType.getOntimeCount();
			ontimePercent = (betimesCount + ontimeCount) * 100 / releaseCount;
			//_log.info("ontimePercent: "+ontimePercent);
		}
		dataType.setOntimePercentage(ontimePercent);

		return dataType;
	}

	private VotingResultStatisticData processVotingPercent(VotingResultStatisticData dataType) {
		int firstPercentage = 0;
		int secondPercentage = 0;
		int thirdPercentage = 0;
		//
		int totalCount = dataType.getTotalCount();
		
		if (dataType.getVeryGoodCount() > 0) {
			firstPercentage = dataType.getVeryGoodCount() * 100 / totalCount;
		}
		if (dataType.getGoodCount() > 0) {
			secondPercentage = dataType.getGoodCount() * 100 / totalCount;
		}
		if (dataType.getBadCount() > 0) {
			thirdPercentage = 100 - (secondPercentage + firstPercentage);
		}
		dataType.setPercentVeryGood(firstPercentage);
		dataType.setPercentGood(secondPercentage);
		dataType.setBadCount(thirdPercentage);

		return dataType;
	}

}
