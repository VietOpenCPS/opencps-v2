package org.opencps.statistic.rest.util;

import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

public class DossierStatisticUtils {
	public static void logAsFormattedJson(Logger logger, Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String contentFormatted = mapper.writeValueAsString(obj);
			logger.info("Content: \n {}", contentFormatted);
		} catch (JsonProcessingException e) {
			logger.info("Error printing REST request! {}", e);
		}
	}
	
	public static OpencpsDossierStatistic checkExists(int month, int year, String domainCode, String govAgencyCode, List<OpencpsDossierStatistic> datas) {
		for (OpencpsDossierStatistic statistic : datas) {
			
			boolean checkDate = false;
			if (month == statistic.getMonth() && year == statistic.getYear()) {
				checkDate = true;
			}
			boolean checkGovAgency = (Validator.isNull(govAgencyCode)) ? (Validator.isNull(statistic.getGovAgencyCode()) ? true : false) : (govAgencyCode.contentEquals(statistic.getGovAgencyCode()));
			boolean checkDomain = (Validator.isNull(domainCode)) ? (Validator.isNull(statistic.getDomainCode()) ? true : false) : (domainCode.contentEquals(statistic.getDomainCode()));
//			if (statistic.getGroupId() == 52737 && domainCode != null && domainCode.equals("SCT_ATTP") && month == 4) {
//				System.out.println("Check exists 2: " + month + "," + year + "," + domainCode + "," + govAgencyCode + "," + checkDate + "," + checkDomain + "," + checkGovAgency);
//			}
//
//			if (statistic.getGroupId() == 52737 && statistic.getDomainCode() != null && statistic.getDomainCode().equals("SCT_ATTP") && statistic.getMonth() == 4) {
//				System.out.println("Check exists 2: " + month + "," + year + "," + domainCode + "," + govAgencyCode + "," + checkDate + "," + checkDomain + "," + checkGovAgency);
//			}

			if (checkDate && checkGovAgency && checkDomain) {
				return statistic;
			}
		}
		
		return null;
	}
}
