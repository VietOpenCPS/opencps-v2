package org.opencps.statistic.rest.engine.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;;
import java.util.stream.Collectors;

import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetPersonData;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;

public class StatisticEngineFetch {

	//private final static Logger LOG = LoggerFactory.getLogger(StatisticEngineFetch.class);

	public void fecthStatisticData(long groupId, Map<String, DossierStatisticData> statisticData,
			List<GetDossierData> lsDossierData, Date fromStatisticDate, Date toStatisticDate, int reporting) {

		//LOG.info("STARTTING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		for (GetDossierData dossierData : lsDossierData) {
			if (Validator.isNotNull(dossierData.getDomainCode())) {
				StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();
				// all site, all domain, all system, all group gov
				
				String type1 = "all@all@all@all@" + groupId;
	
				DossierStatisticData dataType1 = new DossierStatisticData();
				dataType1.setGroupId(groupId);
				dataType1.setGovAgencyCode(StringPool.BLANK);
				dataType1.setGovAgencyName(StringPool.BLANK);
				dataType1.setDomainCode(StringPool.BLANK);
				dataType1.setDomainName(StringPool.BLANK);
				dataType1.setSystem(StringPool.BLANK);

				if (statisticData.containsKey(type1)) {
					dataType1 = statisticData.get(type1);
				}

				engineFetchEntry.updateDossierStatisticData(dataType1, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType1 = processOnTimePercent(dataType1);
	
				statisticData.put(type1, dataType1);

				String type2 = "all@all@" + dossierData.getSystem() + "@" + groupId;

				DossierStatisticData dataType2 = new DossierStatisticData();
				dataType2.setGroupId(groupId);
				dataType2.setGovAgencyCode(StringPool.BLANK);
				dataType2.setGovAgencyName(StringPool.BLANK);
				dataType2.setDomainCode(StringPool.BLANK);
				dataType2.setDomainName(StringPool.BLANK);
				dataType2.setSystem(dossierData.getSystem());
	
				if (statisticData.containsKey(type2)) {
					dataType2 = statisticData.get(type2);
				}
	
				engineFetchEntry.updateDossierStatisticData(dataType2, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType2 = processOnTimePercent(dataType2);
	
				statisticData.put(type2, dataType2);
					
				// all site, each domain, all system
				String type3 = "all@" + dossierData.getDomainCode() + "@all@" + groupId;
				
				DossierStatisticData dataType3 = new DossierStatisticData();
				dataType3.setGroupId(groupId);
				dataType3.setGovAgencyCode(StringPool.BLANK);
				dataType3.setGovAgencyName(StringPool.BLANK);
				dataType3.setDomainCode(dossierData.getDomainCode());
				dataType3.setDomainName(dossierData.getDomainName());
				dataType3.setSystem(StringPool.BLANK);
	
				if (statisticData.containsKey(type3)) {
					dataType3 = statisticData.get(type3);
				}
	
				engineFetchEntry.updateDossierStatisticData(dataType3, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType3 = processOnTimePercent(dataType3);
	
				statisticData.put(type3, dataType3);
	
				// each site all domain
				
				String type4 = dossierData.getGovAgencyCode() + "@all@all@" + groupId;
				//System.out.println("type3: "+type3);
	
				DossierStatisticData dataType4 = new DossierStatisticData();
				dataType4.setGroupId(groupId);
				dataType4.setGovAgencyCode(dossierData.getGovAgencyCode());
				dataType4.setGovAgencyName(dossierData.getGovAgencyName());
				dataType4.setDomainCode(StringPool.BLANK);
				dataType4.setDomainName(StringPool.BLANK);
				dataType4.setSystem(StringPool.BLANK);

				if (statisticData.containsKey(type4)) {
					dataType4 = statisticData.get(type4);
				}
	
				engineFetchEntry.updateDossierStatisticData(dataType4, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType4 = processOnTimePercent(dataType4);
				//System.out.println("dataType3: "+dataType3.getTotalCount());
	
				statisticData.put(type4, dataType4);
				//System.out.println("statisticData: "+statisticData.get(type3).getTotalCount());

				// all site, each domain, each system
				String type5 = "all@" + dossierData.getDomainCode() + "@" + dossierData.getSystem() + "@" + groupId;

				DossierStatisticData dataType5 = new DossierStatisticData();
				dataType5.setGroupId(groupId);
				dataType5.setGovAgencyCode(StringPool.BLANK);
				dataType5.setGovAgencyName(StringPool.BLANK);
				dataType5.setDomainCode(dossierData.getDomainCode());
				dataType5.setDomainName(dossierData.getDomainName());
				dataType5.setSystem(dossierData.getSystem());

				if (statisticData.containsKey(type5)) {
					dataType5 = statisticData.get(type5);
				}

				engineFetchEntry.updateDossierStatisticData(dataType5, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType5 = processOnTimePercent(dataType5);

				statisticData.put(type5, dataType5);

				// all site, each domain, each system
				String type6 = dossierData.getGovAgencyCode() + "@all@" + dossierData.getSystem() + "@" + groupId;

				DossierStatisticData dataType6 = new DossierStatisticData();
				dataType6.setGroupId(groupId);
				dataType6.setGovAgencyCode(dossierData.getGovAgencyCode());
				dataType6.setGovAgencyName(dossierData.getGovAgencyName());
				dataType6.setDomainCode(StringPool.BLANK);
				dataType6.setDomainName(StringPool.BLANK);
				dataType6.setSystem(dossierData.getSystem());

				if (statisticData.containsKey(type6)) {
					dataType6 = statisticData.get(type6);
				}

				engineFetchEntry.updateDossierStatisticData(dataType6, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType6 = processOnTimePercent(dataType6);

				statisticData.put(type6, dataType6);

				// each site, each domain, all system
				String type7 = dossierData.getGovAgencyCode() + "@" + dossierData.getDomainCode() + "@all@" + groupId;

				DossierStatisticData dataType7 = new DossierStatisticData();
				dataType7.setGroupId(groupId);
				dataType7.setGovAgencyCode(dossierData.getGovAgencyCode());
				dataType7.setGovAgencyName(dossierData.getGovAgencyName());
				dataType7.setDomainCode(dossierData.getDomainCode());
				dataType7.setDomainName(dossierData.getDomainName());
				dataType7.setSystem(StringPool.BLANK);

				if (statisticData.containsKey(type7)) {
					dataType7 = statisticData.get(type7);
				}

				engineFetchEntry.updateDossierStatisticData(dataType7, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType7 = processOnTimePercent(dataType7);

				statisticData.put(type7, dataType7);

				// each site, each domain, all system
				String type8 = dossierData.getGovAgencyCode() + "@" + dossierData.getDomainCode() + "@"
						+ dossierData.getSystem() + "@" + groupId;
				
				DossierStatisticData dataType8 = new DossierStatisticData();
				dataType8.setGroupId(groupId);
				dataType8.setGovAgencyCode(dossierData.getGovAgencyCode());
				dataType8.setGovAgencyName(dossierData.getGovAgencyName());
				dataType8.setDomainCode(dossierData.getDomainCode());
				dataType8.setDomainName(dossierData.getDomainName());
				dataType8.setSystem(dossierData.getSystem());

				if (statisticData.containsKey(type8)) {
					dataType8 = statisticData.get(type8);
				}

				engineFetchEntry.updateDossierStatisticData(dataType8, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType8 = processOnTimePercent(dataType8);

				statisticData.put(type8, dataType8);

				// all site, all domain, all system, each group gov
//				for (String groupGovAgency : lstGroupGovs) {
//					List<String> lstGovs = Arrays.asList(groupGovAgency.split(StringPool.COMMA));
//					
//					if (lstGovs.contains(dossierData.getGovAgencyCode())) {
//						String type9 = "all@all@all@" + groupGovAgency + "@" + groupId;
//						
//						DossierStatisticData dataType9 = new DossierStatisticData();
//						dataType9.setGroupId(groupId);
//						dataType9.setGovAgencyCode(StringPool.BLANK);
//						dataType9.setGovAgencyName(StringPool.BLANK);
//						dataType9.setDomainCode(StringPool.BLANK);
//						dataType9.setDomainName(StringPool.BLANK);
//						dataType9.setSystem(StringPool.BLANK);
//						dataType9.setGroupAgencyCode(groupGovAgency);
//						
//						if (statisticData.containsKey(type9)) {
//							dataType9 = statisticData.get(type9);
//						}
//			
//						engineFetchEntry.updateDossierStatisticData(dataType9, dossierData, fromStatisticDate, toStatisticDate,
//								reporting);
//						dataType9 = processOnTimePercent(dataType9);
//						
//						statisticData.put(type9, dataType9);											
//					}
//				}
				
			}
		}
	}

	//LamTV_Process statistic check
	private static final String MSG_ALL = "all";

	public Map<String, VotingResultStatisticData> getStatisticVotingData(long groupId,
			List<GetVotingResultData> votingDataList, Date fromCalDate, Date toCalDate) {

		//LOG.info("STARTTING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Map<String, VotingResultStatisticData> statisticData = new HashMap<String, VotingResultStatisticData>();
		String MSG_ALL_SITE_ALL_DOMAIN = MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + groupId;

		for (GetVotingResultData votingData : votingDataList) {
			//System.out.println("DossierDataJSON: "+JSONFactoryUtil.looseSerialize(votingData));
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
			engineFetchEntry.calculateVotingStatisticData(dataType1, votingData, fromCalDate, toCalDate);
			dataType1.setGovAgencyCode(StringPool.BLANK);
			dataType1.setGovAgencyName(StringPool.BLANK);
			dataType1.setDomain(StringPool.BLANK);
			dataType1.setDomainName(StringPool.BLANK);
			dataType1.setVotingCode(StringPool.BLANK);
			dataType1.setVotingSubject(StringPool.BLANK);

			statisticData.put(MSG_ALL_SITE_ALL_DOMAIN, dataType1);
			
			/** Calculator all site, all domain - END */

			// all site each domain
			String type2 = MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + votingData.getVotingCode()
					+ StringPool.AT + groupId;
			
			VotingResultStatisticData dataType2 = new VotingResultStatisticData();

			dataType2.setVotingCode(votingData.getVotingCode());
			dataType2.setVotingSubject(votingData.getVotingSubject());
			dataType2.setGovAgencyCode(StringPool.BLANK);
			dataType2.setGovAgencyName(StringPool.BLANK);
			dataType2.setDomain(StringPool.BLANK);
			dataType2.setDomainName(StringPool.BLANK);

			if (statisticData.containsKey(type2)) {
				dataType2 = statisticData.get(type2);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType2, votingData, fromCalDate, toCalDate);
			//dataType2 = processOnTimePercent(dataType2);

			statisticData.put(type2, dataType2);

			// each site all domain
			String type3 = MSG_ALL + StringPool.AT + votingData.getDomain() + StringPool.AT
					+ votingData.getVotingCode() + StringPool.AT + groupId;


			VotingResultStatisticData dataType3 = new VotingResultStatisticData();
			dataType3.setDomain(votingData.getDomain());
			dataType3.setDomainName(votingData.getDomainName());
			dataType3.setVotingCode(votingData.getVotingCode());
			dataType3.setVotingSubject(votingData.getVotingSubject());
			dataType3.setGovAgencyCode(StringPool.BLANK);
			dataType3.setGovAgencyName(StringPool.BLANK);

			if (statisticData.containsKey(type3)) {
				dataType3 = statisticData.get(type3);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType3, votingData, fromCalDate, toCalDate);
			//dataType3 = processOnTimePercent(dataType3);

			statisticData.put(type3, dataType3);

			// each site each domain
			
			String type4 = votingData.getGovAgencyCode() + StringPool.AT + MSG_ALL + StringPool.AT
					+ votingData.getVotingCode() + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType4 = new VotingResultStatisticData();
			dataType4.setGovAgencyCode(votingData.getGovAgencyCode());
			dataType4.setGovAgencyName(votingData.getGovAgencyName());
			dataType4.setVotingCode(votingData.getVotingCode());
			dataType4.setVotingSubject(votingData.getVotingSubject());
			dataType4.setDomain(StringPool.BLANK);
			dataType4.setDomainName(StringPool.BLANK);

			if (statisticData.containsKey(type4)) {
				dataType4 = statisticData.get(type4);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType4, votingData, fromCalDate, toCalDate);
			//dataType4 = processOnTimePercent(dataType4);

			statisticData.put(type4, dataType4);
			
			// each site each domain
			String type5 = votingData.getGovAgencyCode() + StringPool.AT + votingData.getDomain() + StringPool.AT
					+ votingData.getVotingCode() + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType5 = new VotingResultStatisticData();
			dataType5.setGovAgencyCode(votingData.getGovAgencyCode());
			dataType5.setGovAgencyName(votingData.getGovAgencyName());
			dataType5.setDomain(votingData.getDomain());
			dataType5.setDomainName(votingData.getDomainName());
			dataType5.setVotingCode(votingData.getVotingCode());
			dataType5.setVotingSubject(votingData.getVotingSubject());

			if (statisticData.containsKey(type5)) {
				dataType5 = statisticData.get(type5);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType5, votingData, fromCalDate, toCalDate);
			//dataType4 = processOnTimePercent(dataType4);

			statisticData.put(type5, dataType5);

			// each site each domain
			String type6 = MSG_ALL + StringPool.AT + votingData.getDomain() + StringPool.AT
					+ MSG_ALL + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType6 = new VotingResultStatisticData();
			dataType6.setDomain(votingData.getDomain());
			dataType6.setDomainName(votingData.getDomainName());
			dataType6.setVotingCode(StringPool.BLANK);
			dataType6.setVotingSubject(StringPool.BLANK);
			dataType6.setGovAgencyCode(StringPool.BLANK);
			dataType6.setGovAgencyName(StringPool.BLANK);

			if (statisticData.containsKey(type6)) {
				dataType6 = statisticData.get(type6);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType6, votingData, fromCalDate, toCalDate);
			statisticData.put(type6, dataType6);

			// each site each domain
			String type7 = votingData.getGovAgencyCode() + StringPool.AT + MSG_ALL + StringPool.AT
					+ MSG_ALL + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType7 = new VotingResultStatisticData();
			dataType7.setGovAgencyCode(votingData.getGovAgencyCode());
			dataType7.setGovAgencyName(votingData.getGovAgencyName());
			dataType7.setVotingCode(StringPool.BLANK);
			dataType7.setVotingSubject(StringPool.BLANK);
			dataType7.setDomain(StringPool.BLANK);
			dataType7.setDomainName(StringPool.BLANK);

			if (statisticData.containsKey(type7)) {
				dataType7 = statisticData.get(type7);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType7, votingData, fromCalDate, toCalDate);
			statisticData.put(type7, dataType7);

			// each site each domain
			String type8 = votingData.getGovAgencyCode() + StringPool.AT + votingData.getDomain() + StringPool.AT
					+ MSG_ALL + StringPool.AT + groupId;
			
			VotingResultStatisticData dataType8 = new VotingResultStatisticData();
			dataType8.setGovAgencyCode(votingData.getGovAgencyCode());
			dataType8.setGovAgencyName(votingData.getGovAgencyName());
			dataType8.setDomain(votingData.getDomain());
			dataType8.setDomainName(votingData.getDomainName());
			dataType8.setVotingCode(StringPool.BLANK);
			dataType8.setVotingSubject(StringPool.BLANK);

			if (statisticData.containsKey(type8)) {
				dataType8 = statisticData.get(type8);
			}

			engineFetchEntry.calculateVotingStatisticData(dataType8, votingData, fromCalDate, toCalDate);
			statisticData.put(type8, dataType8);
		}
		//
		Map<String, VotingResultStatisticData> votingStatisticData = new HashMap<String, VotingResultStatisticData>();
		if (statisticData != null && statisticData.size() > 0) {
			for (Map.Entry<String, VotingResultStatisticData> entry : statisticData.entrySet()) {
				VotingResultStatisticData dataType = entry.getValue();
				//System.out.println("dataType"+ JSONFactoryUtil.looseSerialize(dataType));
				if (dataType != null) {
					dataType = processVotingPercent(dataType);
				}
				votingStatisticData.put(entry.getKey(), dataType);
			}
		}
		
		return votingStatisticData;
	}

	public Map<String, PersonStatisticData> getOldStatisticPersonData(long groupId, List<GetPersonData> personDataList,
			Date fromStatisticDate, Date toStatisticDate) {

		//LOG.info("STARTTING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		Map<String, PersonStatisticData> statisticData = new HashMap<String, PersonStatisticData>();
		String MSG_ALL_SITE_ALL_DOMAIN = MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT
				+ groupId;

		for (GetPersonData personData : personDataList) {
			//System.out.println("DossierDataJSON: "+JSONFactoryUtil.looseSerialize(votingData));
			StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();

			/** Calculator all site, all domain - START */

			PersonStatisticData dataType1 = new PersonStatisticData();
			dataType1.setGovAgencyCode(StringPool.BLANK);
			dataType1.setGovAgencyName(StringPool.BLANK);
			dataType1.setEmployeeId(0);
			dataType1.setEmployeeName(StringPool.BLANK);
			dataType1.setVotingCode(StringPool.BLANK);
			dataType1.setVotingSubject(StringPool.BLANK);

			if (statisticData.containsKey(MSG_ALL_SITE_ALL_DOMAIN)) {
				dataType1 = statisticData.get(MSG_ALL_SITE_ALL_DOMAIN);
			}

			//System.out.println("type1: "+MSG_ALL_SITE_ALL_DOMAIN);
			engineFetchEntry.calculatePersonStatisticData(dataType1, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(MSG_ALL_SITE_ALL_DOMAIN, dataType1);
			
			/** Calculator all site, all domain - END */

			// all site each domain
			String type2 = MSG_ALL + StringPool.AT + MSG_ALL + StringPool.AT + personData.getVotingCode()
					+ StringPool.AT + groupId;
			
			PersonStatisticData dataType2 = new PersonStatisticData();
			dataType2.setVotingCode(personData.getVotingCode());
			dataType2.setVotingSubject(personData.getVotingSubject());
			dataType2.setGovAgencyCode(StringPool.BLANK);
			dataType2.setGovAgencyName(StringPool.BLANK);
			dataType2.setEmployeeId(0);
			dataType2.setEmployeeName(StringPool.BLANK);

			if (statisticData.containsKey(type2)) {
				dataType2 = statisticData.get(type2);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType2, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type2, dataType2);

			// each site all domain
			String type3 = MSG_ALL + StringPool.AT + personData.getEmployeeId() + StringPool.AT
					+ personData.getVotingCode() + StringPool.AT + groupId;


			PersonStatisticData dataType3 = new PersonStatisticData();
			dataType3.setEmployeeId(personData.getEmployeeId());
			dataType3.setEmployeeName(personData.getEmployeeName());
			dataType3.setVotingCode(personData.getVotingCode());
			dataType3.setVotingSubject(personData.getVotingSubject());
			dataType3.setGovAgencyCode(StringPool.BLANK);
			dataType3.setGovAgencyName(StringPool.BLANK);

			if (statisticData.containsKey(type3)) {
				dataType3 = statisticData.get(type3);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType3, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type3, dataType3);

			// each site each domain
			
			String type4 = personData.getGovAgencyCode() + StringPool.AT + MSG_ALL + StringPool.AT
					+ personData.getVotingCode() + StringPool.AT + groupId;
			
			PersonStatisticData dataType4 = new PersonStatisticData();
			dataType4.setGovAgencyCode(personData.getGovAgencyCode());
			dataType4.setGovAgencyName(personData.getGovAgencyName());
			dataType4.setVotingCode(personData.getVotingCode());
			dataType4.setVotingSubject(personData.getVotingSubject());
			dataType4.setEmployeeId(0);
			dataType4.setEmployeeName(StringPool.BLANK);

			if (statisticData.containsKey(type4)) {
				dataType4 = statisticData.get(type4);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType4, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type4, dataType4);
			
			// each site each domain
			String type5 = personData.getGovAgencyCode() + StringPool.AT + personData.getEmployeeId() + StringPool.AT
					+ personData.getVotingCode() + StringPool.AT + groupId;
			
			PersonStatisticData dataType5 = new PersonStatisticData();
			dataType5.setGovAgencyCode(personData.getGovAgencyCode());
			dataType5.setGovAgencyName(personData.getGovAgencyName());
			dataType5.setEmployeeId(personData.getEmployeeId());
			dataType5.setEmployeeName(personData.getEmployeeName());
			dataType5.setVotingCode(personData.getVotingCode());
			dataType5.setVotingSubject(personData.getVotingSubject());

			if (statisticData.containsKey(type5)) {
				dataType5 = statisticData.get(type5);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType5, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type5, dataType5);

			// each site each domain
			String type6 = MSG_ALL + StringPool.AT + personData.getEmployeeId() + StringPool.AT
					+ MSG_ALL + StringPool.AT + groupId;
			
			PersonStatisticData dataType6 = new PersonStatisticData();
			dataType6.setEmployeeId(personData.getEmployeeId());
			dataType6.setEmployeeName(personData.getEmployeeName());
			dataType6.setVotingCode(StringPool.BLANK);
			dataType6.setVotingSubject(StringPool.BLANK);
			dataType6.setGovAgencyCode(StringPool.BLANK);
			dataType6.setGovAgencyName(StringPool.BLANK);

			if (statisticData.containsKey(type6)) {
				dataType6 = statisticData.get(type6);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType6, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type6, dataType6);

			// each site each domain
			String type7 = personData.getGovAgencyCode() + StringPool.AT + MSG_ALL + StringPool.AT
					+ MSG_ALL + StringPool.AT + groupId;
			
			PersonStatisticData dataType7 = new PersonStatisticData();
			dataType7.setGovAgencyCode(personData.getGovAgencyCode());
			dataType7.setGovAgencyName(personData.getGovAgencyName());
			dataType7.setVotingCode(StringPool.BLANK);
			dataType7.setVotingSubject(StringPool.BLANK);
			dataType7.setEmployeeId(0);
			dataType7.setEmployeeName(StringPool.BLANK);

			if (statisticData.containsKey(type7)) {
				dataType7 = statisticData.get(type7);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType7, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type7, dataType7);

			// each site each domain
			String type8 = personData.getGovAgencyCode() + StringPool.AT + personData.getEmployeeId() + StringPool.AT
					+ MSG_ALL + StringPool.AT + groupId;
			
			PersonStatisticData dataType8 = new PersonStatisticData();
			dataType8.setGovAgencyCode(personData.getGovAgencyCode());
			dataType8.setGovAgencyName(personData.getGovAgencyName());
			dataType8.setEmployeeId(personData.getEmployeeId());
			dataType8.setEmployeeName(personData.getEmployeeName());
			dataType8.setVotingCode(StringPool.BLANK);
			dataType8.setVotingSubject(StringPool.BLANK);

			if (statisticData.containsKey(type8)) {
				dataType8 = statisticData.get(type8);
			}

			engineFetchEntry.calculatePersonStatisticData(dataType8, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type8, dataType8);
		}
		//
		Map<String, PersonStatisticData> personStatisticData = new HashMap<String, PersonStatisticData>();
		if (statisticData != null && statisticData.size() > 0) {
			for (Map.Entry<String, PersonStatisticData> entry : statisticData.entrySet()) {
				PersonStatisticData dataType = entry.getValue();
				//System.out.println("dataType"+ JSONFactoryUtil.looseSerialize(dataType));
				if (dataType != null) {
					dataType = processPersonPercent(dataType);
				}
				personStatisticData.put(entry.getKey(), dataType);
			}
		}

		return personStatisticData;
	}

	public Map<String, PersonStatisticData> getStatisticPersonDataForVotingCode(long groupId, List<GetPersonData> personDataList,
			Date fromStatisticDate, Date toStatisticDate) {
		Map<String, PersonStatisticData> statisticData = new HashMap<String, PersonStatisticData>();
		for (GetPersonData personData : personDataList) {
			
			StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();
			String type4 = personData.getGovAgencyCode()+ "@" + personData.getEmployeeId() + "@" + personData.getVotingCode() + "@" + groupId;
			PersonStatisticData dataType4 = new PersonStatisticData();
			dataType4.setGovAgencyCode(personData.getGovAgencyCode());
			dataType4.setGovAgencyName(personData.getGovAgencyName());
			dataType4.setEmployeeId(personData.getEmployeeId());
			dataType4.setEmployeeName(personData.getEmployeeName());
			dataType4.setVotingCode(personData.getVotingCode());
			dataType4.setVotingSubject(personData.getVotingSubject());
			
			if (statisticData.containsKey(type4)) {
				dataType4 = statisticData.get(type4);
			}
			engineFetchEntry.calculatePersonStatisticData(dataType4, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type4, dataType4);
		}
			
			Map<String, PersonStatisticData> personStatisticData = new HashMap<String, PersonStatisticData>();
			if (statisticData != null && statisticData.size() > 0) {
				for (Map.Entry<String, PersonStatisticData> entry : statisticData.entrySet()) {
					PersonStatisticData dataType = entry.getValue();				
					if (dataType != null) {
						dataType = processPersonPercent(dataType);
					}
					personStatisticData.put(entry.getKey(), dataType);
				}
			}

			return personStatisticData;
	}
	
	public List<PersonStatisticData> getStatisticPersonData(List<PersonStatisticData> statisticDataList) {
		List<PersonStatisticData> newStatisticData = new ArrayList<PersonStatisticData>();
		//int totalVoteInSite = 0;
		if (statisticDataList != null && statisticDataList.size() > 0) {
			List<PersonStatisticData> statisticData = statisticDataList.stream()
					.sorted(Comparator.comparing(p -> p.getEmployeeId()))
					.collect(Collectors.toList());
			do {
				List<PersonStatisticData> tempData = new ArrayList<PersonStatisticData>();
				
				PersonStatisticData data = statisticData.get(0);
				tempData.add(data);
				for (int i = 1; i< statisticData.size(); i++) {
					if (data.getEmployeeId() == statisticData.get(i).getEmployeeId()) {
						tempData.add(statisticData.get(i));
					}					
				}
				statisticData.removeAll(tempData);
				
				PersonStatisticData totalPersonData = new PersonStatisticData();
				totalPersonData.setEmployeeId(data.getEmployeeId());
				totalPersonData.setEmployeeName(data.getEmployeeName());
				totalPersonData.setGroupId(data.getGroupId());
				totalPersonData.setMonth(data.getMonth());
				totalPersonData.setGovAgencyCode(data.getGovAgencyCode());
				totalPersonData.setGovAgencyName(data.getGovAgencyName());
				totalPersonData.setTotalVoted(data.getBadCount()+data.getGoodCount()+data.getVeryGoodCount());
				totalPersonData.setVotingCode(StringPool.BLANK);
				totalPersonData.setVotingSubject(StringPool.BLANK);
				int badCount = 0;
				int goodCount = 0;
				int veryGoodCount = 0;
				for (int i = 0; i< tempData.size(); i++) {
					badCount = badCount + tempData.get(i).getBadCount();
					goodCount = goodCount + tempData.get(i).getGoodCount();
					veryGoodCount = veryGoodCount + tempData.get(i).getVeryGoodCount();
				}
				totalPersonData.setBadCount(badCount);
				totalPersonData.setGoodCount(goodCount);
				totalPersonData.setVeryGoodCount(veryGoodCount);
				totalPersonData = newProcessPersonPercent(totalPersonData);
				
				//tempData.add(totalPersonData);				
				//totalVoteInSite = totalVoteInSite + totalPersonData.getTotalVoted();
				newStatisticData.add(totalPersonData);				
				
			} while (statisticData.size() > 0);
			
			PersonStatisticData eachSitePersonData = new PersonStatisticData();
			eachSitePersonData.setEmployeeId(0);
			eachSitePersonData.setEmployeeName(StringPool.BLANK);
			eachSitePersonData.setGroupId(newStatisticData.get(0).getGroupId());
			eachSitePersonData.setMonth(newStatisticData.get(0).getMonth());
			eachSitePersonData.setGovAgencyCode(newStatisticData.get(0).getGovAgencyCode());
			eachSitePersonData.setGovAgencyName(newStatisticData.get(0).getGovAgencyName());
			//eachSitePersonData.setTotalVoted(totalVoteInSite);
			eachSitePersonData.setVotingCode(StringPool.BLANK);
			eachSitePersonData.setVotingSubject(StringPool.BLANK);
			int totalBadCount = 0;
			int totalGoodCount = 0;
			int totalVeryGoodCount = 0;
			int totalVoteInSite = 0;
			for (int i = 0; i< newStatisticData.size(); i++) {
				totalBadCount = totalBadCount + newStatisticData.get(i).getBadCount();
				totalGoodCount = totalGoodCount + newStatisticData.get(i).getGoodCount();
				totalVeryGoodCount = totalVeryGoodCount + newStatisticData.get(i).getVeryGoodCount();
				totalVoteInSite = totalVoteInSite + newStatisticData.get(i).getTotalVoted();
			}
			eachSitePersonData.setBadCount(totalBadCount);
			eachSitePersonData.setGoodCount(totalGoodCount);
			eachSitePersonData.setVeryGoodCount(totalVeryGoodCount);
			eachSitePersonData.setTotalVoted(totalVoteInSite);
			eachSitePersonData = newProcessPersonPercent(eachSitePersonData);
			newStatisticData.add(0, eachSitePersonData);			
			
		}
		
		return newStatisticData;
	}
	
	public Map<String, PersonStatisticData> getNewStatisticPersonData(long groupId, List<GetPersonData> personDataList,
			Date fromStatisticDate, Date toStatisticDate) {

		Map<String, PersonStatisticData> statisticData = new HashMap<String, PersonStatisticData>();

		for (GetPersonData personData : personDataList) {
			StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();

			// all site, all employee, all votingCode
			String type1 = "all@all@all@" + groupId;
			PersonStatisticData dataType1 = new PersonStatisticData();
			dataType1.setGovAgencyCode(StringPool.BLANK);
			dataType1.setGovAgencyName(StringPool.BLANK);
			dataType1.setEmployeeId(0);
			dataType1.setEmployeeName(StringPool.BLANK);
			dataType1.setVotingCode(StringPool.BLANK);
			dataType1.setVotingSubject(StringPool.BLANK);
			
			if (statisticData.containsKey(type1)) {
				dataType1 = statisticData.get(type1);
			}
			engineFetchEntry.calculatePersonStatisticData(dataType1, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type1, dataType1);
			
			// each site, all employee, all votingCode
			String type2 = personData.getGovAgencyCode()+ "@all@all@" + groupId;
			PersonStatisticData dataType2 = new PersonStatisticData();
			dataType2.setGovAgencyCode(personData.getGovAgencyCode());
			dataType2.setGovAgencyName(personData.getGovAgencyName());
			dataType2.setEmployeeId(0);
			dataType2.setEmployeeName(StringPool.BLANK);
			dataType2.setVotingCode(StringPool.BLANK);
			dataType2.setVotingSubject(StringPool.BLANK);
			
			if (statisticData.containsKey(type2)) {
				dataType2 = statisticData.get(type2);
			}
			engineFetchEntry.calculatePersonStatisticData(dataType2, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type2, dataType2);
			
			// each site, each employee, all votingCode
			String type3 = personData.getGovAgencyCode()+ "@" + personData.getEmployeeId() + "@" + "all@" + groupId;
			PersonStatisticData dataType3 = new PersonStatisticData();
			dataType3.setGovAgencyCode(personData.getGovAgencyCode());
			dataType3.setGovAgencyName(personData.getGovAgencyName());
			dataType3.setEmployeeId(personData.getEmployeeId());
			dataType3.setEmployeeName(personData.getEmployeeName());
			dataType3.setVotingCode(StringPool.BLANK);
			dataType3.setVotingSubject(StringPool.BLANK);
			
			if (statisticData.containsKey(type3)) {
				dataType3 = statisticData.get(type3);
			}
			engineFetchEntry.calculatePersonStatisticData(dataType3, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type3, dataType3);
			
			// each site, each employee, each votingCode
			String type4 = personData.getGovAgencyCode()+ "@" + personData.getEmployeeId() + "@" + personData.getVotingId() + "@" + groupId;
			PersonStatisticData dataType4 = new PersonStatisticData();
			dataType4.setGovAgencyCode(personData.getGovAgencyCode());
			dataType4.setGovAgencyName(personData.getGovAgencyName());
			dataType4.setEmployeeId(personData.getEmployeeId());
			dataType4.setEmployeeName(personData.getEmployeeName());
			dataType4.setVotingCode(personData.getVotingCode());
			dataType4.setVotingSubject(personData.getVotingSubject());
			
			if (statisticData.containsKey(type4)) {
				dataType4 = statisticData.get(type4);
			}
			engineFetchEntry.calculatePersonStatisticData(dataType4, personData, fromStatisticDate, toStatisticDate);
			statisticData.put(type4, dataType4);
						
		}

		//
		Map<String, PersonStatisticData> personStatisticData = new HashMap<String, PersonStatisticData>();
		if (statisticData != null && statisticData.size() > 0) {
			for (Map.Entry<String, PersonStatisticData> entry : statisticData.entrySet()) {
				PersonStatisticData dataType = entry.getValue();				
				// System.out.println("dataType"+ JSONFactoryUtil.looseSerialize(dataType));
				if (dataType != null) {
					dataType = processPersonPercent(dataType);
				}
				personStatisticData.put(entry.getKey(), dataType);
			}
		}

		return personStatisticData;
	}


	private DossierStatisticData processOnTimePercent(DossierStatisticData dataType) {
		int ontimePercent = 100;
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
		int totalVoted = dataType.getTotalVoted();
		
		if (dataType.getVeryGoodCount() > 0) {
			firstPercentage = dataType.getVeryGoodCount() * 100 / totalVoted;
		}
		if (dataType.getGoodCount() > 0) {
			secondPercentage = dataType.getGoodCount() * 100 / totalVoted;
		}
		if (dataType.getBadCount() > 0) {
			thirdPercentage = 100 - (secondPercentage + firstPercentage);
		}
		dataType.setPercentVeryGood(firstPercentage);
		dataType.setPercentGood(secondPercentage);
		dataType.setPercentBad(thirdPercentage);

		return dataType;
	}

	private PersonStatisticData processPersonPercent(PersonStatisticData dataType) {
		int firstPercentage = 0;
		int secondPercentage = 0;
		int thirdPercentage = 0;
		//
		int totalVoted = dataType.getTotalVoted();
		
		if (dataType.getVeryGoodCount() > 0) {
			firstPercentage = dataType.getVeryGoodCount() * 100 / totalVoted;
		}
		if (dataType.getGoodCount() > 0) {
			secondPercentage = dataType.getGoodCount() * 100 / totalVoted;
		}
		if (dataType.getBadCount() > 0) {
			thirdPercentage = 100 - (secondPercentage + firstPercentage);
		}
		dataType.setPercentVeryGood(firstPercentage);
		dataType.setPercentGood(secondPercentage);
		dataType.setPercentBad(thirdPercentage);

		return dataType;
	}

	private PersonStatisticData newProcessPersonPercent(PersonStatisticData dataType) {
		int firstPercentage = 0;
		int secondPercentage = 0;
		int thirdPercentage = 0;
		//
		int totalVoted = dataType.getVeryGoodCount() + dataType.getGoodCount() + dataType.getBadCount();

		if (dataType.getVeryGoodCount() > 0) {
			firstPercentage = dataType.getVeryGoodCount() * 100 / totalVoted;
		}
		if (dataType.getGoodCount() > 0) {
			secondPercentage = dataType.getGoodCount() * 100 / totalVoted;
		}
		if (dataType.getBadCount() > 0) {
			thirdPercentage = 100 - (secondPercentage + firstPercentage);
		}
		dataType.setPercentVeryGood(firstPercentage);
		dataType.setPercentGood(secondPercentage);
		dataType.setPercentBad(thirdPercentage);

		return dataType;
	}
	public void fetchSumStatisticData(long groupId, Map<String, DossierStatisticData> statisticData,
			List<GetDossierData> lsDossierData, Date fromStatisticDate, Date toStatisticDate, int reporting) {

		//LOG.info("STARTTING TIME " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		for (GetDossierData dossierData : lsDossierData) {
			if (Validator.isNotNull(dossierData.getDomainCode())) {
				StatisticEngineFetchEntry engineFetchEntry = new StatisticEngineFetchEntry();
				// all site, all domain
				
				String type1 = "all@all@" + groupId;
	
				DossierStatisticData dataType1 = new DossierStatisticData();
				dataType1.setGovAgencyCode(StringPool.BLANK);
				dataType1.setGovAgencyName(StringPool.BLANK);
				dataType1.setDomainCode(StringPool.BLANK);
				dataType1.setDomainName(StringPool.BLANK);
	
				if (statisticData.containsKey(type1)) {
					dataType1 = statisticData.get(type1);
				}
	
				engineFetchEntry.updateSumDossierStatisticData(dataType1, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType1 = processOnTimePercent(dataType1);
	
				statisticData.put(type1, dataType1);
				
	
				// all site each domain
				String type2 = "all@" + dossierData.getDomainCode() + "@" + groupId;
				
				DossierStatisticData dataType2 = new DossierStatisticData();
				dataType2.setGovAgencyCode(StringPool.BLANK);
				dataType2.setGovAgencyName(StringPool.BLANK);
				dataType2.setDomainCode(dossierData.getDomainCode());
				dataType2.setDomainName(dossierData.getDomainName());
	
				if (statisticData.containsKey(type2)) {
					dataType2 = statisticData.get(type2);
				}
	
				engineFetchEntry.updateSumDossierStatisticData(dataType2, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType2 = processOnTimePercent(dataType2);
	
				statisticData.put(type2, dataType2);
	
				// each site all domain
				
				String type3 = dossierData.getGovAgencyCode() + "@all@" + groupId;
				//System.out.println("type3: "+type3);
	
				DossierStatisticData dataType3 = new DossierStatisticData();
				dataType3.setGovAgencyCode(dossierData.getGovAgencyCode());
				dataType3.setGovAgencyName(dossierData.getGovAgencyName());
				dataType3.setDomainCode(StringPool.BLANK);
				dataType3.setDomainName(StringPool.BLANK);

				if (statisticData.containsKey(type3)) {
					//System.out.println("type3_TRUE: "+type3);
					dataType3 = statisticData.get(type3);
				}
	
				engineFetchEntry.updateSumDossierStatisticData(dataType3, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
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

				engineFetchEntry.updateSumDossierStatisticData(dataType4, dossierData, fromStatisticDate, toStatisticDate,
						reporting);
				dataType4 = processOnTimePercent(dataType4);

				statisticData.put(type4, dataType4);
			}
			
			//}
		}
	}	
}
