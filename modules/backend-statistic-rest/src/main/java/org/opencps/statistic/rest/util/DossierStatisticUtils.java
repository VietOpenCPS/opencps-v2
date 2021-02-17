package org.opencps.statistic.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticKey;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.slf4j.Logger;

public class DossierStatisticUtils {

	private static Log _log = LogFactoryUtil.getLog(DossierStatisticUtils.class);

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

	public static String invokeUpdateStatistic(JSONObject jsonEnpoint, String jsonArr) {
		StringBuilder sbUpdate = new StringBuilder();
		try {

			URL urlVal = new URL(jsonEnpoint.getString(DossierStatisticConstants.URL_ENDPOINT));

			StringBuilder postData = new StringBuilder();
			//
			postData.append("data");
			postData.append(StringPool.EQUAL);
			postData.append(jsonArr);

			java.net.HttpURLConnection conUpdate = (java.net.HttpURLConnection) urlVal.openConnection();
			conUpdate.setRequestMethod(HttpMethod.POST);
			String authStrEnc = Base64.getEncoder()
					.encodeToString((jsonEnpoint.getString(DossierStatisticConstants.USERNAME_KEY) + ":"
							+ jsonEnpoint.getString(DossierStatisticConstants.SECRET_KEY)).getBytes());
			conUpdate.setRequestProperty(HttpHeaders.AUTHORIZATION, "Basic " + authStrEnc);
			conUpdate.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
			conUpdate.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);
			conUpdate.setRequestProperty(HttpHeaders.CONTENT_LENGTH,
					StringPool.BLANK + Integer.toString(postData.toString().getBytes().length));

			conUpdate.setUseCaches(false);
			conUpdate.setDoInput(true);
			conUpdate.setDoOutput(true);
			OutputStream osUpdate = conUpdate.getOutputStream();
			osUpdate.write(postData.toString().getBytes());
			osUpdate.close();

			BufferedReader brfUpdate = new BufferedReader(new InputStreamReader(conUpdate.getInputStream()));

			int cpUpdate;
			while ((cpUpdate = brfUpdate.read()) != -1) {
				sbUpdate.append((char) cpUpdate);
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return sbUpdate.toString();
	}

	public static DossierStatisticData processCalAllStatistic(long groupId, int month, int year, String agencyTotal,
			String domainTotal, String system, List<DossierStatisticData> dossierStatisticList) {
		DossierStatisticData dossierStatistic = null;
		int totalCount = 0;
		int deniedCount = 0;
		int cancelledCount = 0;
		int processCount = 0;
		int remainingCount = 0;
		int receivedCount = 0;
		int onlineCount = 0;
		int fromViaPostalCount = 0;
		int releaseCount = 0;
		int betimesCount = 0;
		int ontimeCount = 0;
		int overtimeCount = 0;
		int doneCount = 0;
		int releasingCount = 0;
		int unresolvedCount = 0;
		int processingCount = 0;
		int undueCount = 0;
		int overdueCount = 0;
		int ontimePercentage = 100;
		int overtimeInside = 0;
		int overtimeOutside = 0;
		int interoperatingCount = 0;
		int waitingCount = 0;
		int onegateCount = 0;
		int outsideCount = 0;
		int insideCount = 0;
		int viaPostalCount = 0;
		int saturdayCount = 0;
		int dossierOnline3Count = 0;
		int dossierOnline4Count = 0;
		int receiveDossierSatCount = 0;
		int releaseDossierSatCount = 0;
		long companyId = 0;
		int processingInAPeriodCount = 0;
		int releaseInAPeriodCount = 0;
		if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
			dossierStatistic = new DossierStatisticData();
			for (DossierStatisticData statistic : dossierStatisticList) {
				//
				companyId = statistic.getCompanyId() > 0 ? statistic.getCompanyId() : 0; 
				//
				totalCount += statistic.getTotalCount();
				deniedCount += statistic.getDeniedCount();
				cancelledCount += statistic.getCancelledCount();
				processCount += statistic.getProcessCount();
				remainingCount += statistic.getRemainingCount();
				receivedCount += statistic.getReceivedCount();
				onlineCount += statistic.getOnlineCount();
				fromViaPostalCount += statistic.getFromViaPostalCount();
				releaseCount += statistic.getReleaseCount();
				betimesCount += statistic.getBetimesCount();
				ontimeCount += statistic.getOntimeCount();
				overtimeCount += statistic.getOvertimeCount();
				doneCount += statistic.getDoneCount();
				releasingCount += statistic.getReleasingCount();
				unresolvedCount += statistic.getUnresolvedCount();
				processingCount += statistic.getProcessingCount();
				undueCount += statistic.getUndueCount();
				overdueCount += statistic.getOverdueCount();
				overtimeInside += statistic.getOvertimeInside();
				overtimeOutside += statistic.getOvertimeOutside();
				interoperatingCount += statistic.getInteroperatingCount();
				waitingCount += statistic.getWaitingCount();
				onegateCount += statistic.getOnegateCount();
				outsideCount += statistic.getOutsideCount();
				insideCount += statistic.getInsideCount();
				viaPostalCount += statistic.getViaPostalCount();
				saturdayCount += statistic.getSaturdayCount();
				dossierOnline3Count += statistic.getDossierOnline3Count();
				dossierOnline4Count += statistic.getDossierOnline4Count();
				receiveDossierSatCount += statistic.getReceiveDossierSatCount();
				releaseDossierSatCount += statistic.getReleaseDossierSatCount();
				processingInAPeriodCount += statistic.getProcessingInAPeriodCount();
				releaseInAPeriodCount += statistic.getReleaseInAPeriodCount();
			}
			//
			if (releaseCount > 0) {
				ontimePercentage = (betimesCount + ontimeCount) * 100 / releaseCount;
			}
			//
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setSystem(system);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setGovAgencyCode(agencyTotal);
			dossierStatistic.setGovAgencyName(StringPool.BLANK);
			dossierStatistic.setDomainCode(domainTotal);
			dossierStatistic.setDomainName(StringPool.BLANK);
			dossierStatistic.setReporting(0);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			dossierStatistic.setViaPostalCount(viaPostalCount);
			dossierStatistic.setSaturdayCount(saturdayCount);
			dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
			dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
			dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
			dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);
			dossierStatistic.setGroupAgencyCode(StringPool.BLANK);
			dossierStatistic.setProcessingInAPeriodCount(processingInAPeriodCount);
			dossierStatistic.setReleaseInAPeriodCount(releaseInAPeriodCount);
		}
		//
		return dossierStatistic;
	}

	private static DossierStatisticData processCalStatistic(long groupId, int month, int year, String govAgencyCode, String domainCode,
															String groupGovAgencyCode, String system, int reporting,
															List<OpencpsDossierStatistic> dossierStatisticList) {
		DossierStatisticData dossierStatistic = null;
		int totalCount = 0;
		int deniedCount = 0;
		int cancelledCount = 0;
		int processCount = 0;
		int remainingCount = 0;
		int receivedCount = 0;
		int onlineCount = 0;
		int fromViaPostalCount = 0;
		int releaseCount = 0;
		int betimesCount = 0;
		int ontimeCount = 0;
		int overtimeCount = 0;
		int doneCount = 0;
		int releasingCount = 0;
		int unresolvedCount = 0;
		int processingCount = 0;
		int undueCount = 0;
		int overdueCount = 0;
		int ontimePercentage = 100;
		int overtimeInside = 0;
		int overtimeOutside = 0;
		int interoperatingCount = 0;
		int waitingCount = 0;
		int onegateCount = 0;
		int outsideCount = 0;
		int insideCount = 0;
		int viaPostalCount = 0;
		int saturdayCount = 0;
		int dossierOnline3Count = 0;
		int dossierOnline4Count = 0;
		int receiveDossierSatCount = 0;
		int releaseDossierSatCount = 0;
		long companyId = 0;
		int processingInAPeriodCount = 0;
		int releaseInAPeriodCount = 0;
		if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
			dossierStatistic = new DossierStatisticData();
			for (OpencpsDossierStatistic opencpsDossierStatistic : dossierStatisticList) {
				//
				companyId = opencpsDossierStatistic.getCompanyId() > 0 ? opencpsDossierStatistic.getCompanyId() : 0;
				//
				totalCount += opencpsDossierStatistic.getTotalCount();
				deniedCount += opencpsDossierStatistic.getDeniedCount();
				cancelledCount += opencpsDossierStatistic.getCancelledCount();
				processCount += opencpsDossierStatistic.getProcessCount();
				remainingCount += opencpsDossierStatistic.getRemainingCount();
				receivedCount += opencpsDossierStatistic.getReceivedCount();
				onlineCount += opencpsDossierStatistic.getOnlineCount();
				fromViaPostalCount += opencpsDossierStatistic.getFromViaPostalCount();
				releaseCount += opencpsDossierStatistic.getReleaseCount();
				betimesCount += opencpsDossierStatistic.getBetimesCount();
				ontimeCount += opencpsDossierStatistic.getOntimeCount();
				overtimeCount += opencpsDossierStatistic.getOvertimeCount();
				doneCount += opencpsDossierStatistic.getDoneCount();
				releasingCount += opencpsDossierStatistic.getReleasingCount();
				unresolvedCount += opencpsDossierStatistic.getUnresolvedCount();
				processingCount += opencpsDossierStatistic.getProcessingCount();
				undueCount += opencpsDossierStatistic.getUndueCount();
				overdueCount += opencpsDossierStatistic.getOverdueCount();
				overtimeInside += opencpsDossierStatistic.getOvertimeInside();
				overtimeOutside += opencpsDossierStatistic.getOvertimeOutside();
				interoperatingCount += opencpsDossierStatistic.getInteroperatingCount();
				waitingCount += opencpsDossierStatistic.getWaitingCount();
				onegateCount += opencpsDossierStatistic.getOnegateCount();
				outsideCount += opencpsDossierStatistic.getOutsideCount();
				insideCount += opencpsDossierStatistic.getInsideCount();
				viaPostalCount += opencpsDossierStatistic.getViaPostalCount();
				saturdayCount += opencpsDossierStatistic.getSaturdayCount();
				dossierOnline3Count += opencpsDossierStatistic.getDossierOnline3Count();
				dossierOnline4Count += opencpsDossierStatistic.getDossierOnline4Count();
				receiveDossierSatCount += opencpsDossierStatistic.getReceiveDossierSatCount();
				releaseDossierSatCount += opencpsDossierStatistic.getReleaseDossierSatCount();
				processingInAPeriodCount += opencpsDossierStatistic.getProcessingInAPeriodCount();
				releaseInAPeriodCount += opencpsDossierStatistic.getReleaseInAPeriodCount();
			}
			//
			if (releaseCount > 0) {
				ontimePercentage = (betimesCount + ontimeCount) * 100 / releaseCount;
			}
			//
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setSystem(system);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setGovAgencyCode(govAgencyCode);
			dossierStatistic.setGovAgencyName(dossierStatisticList.get(0).getGovAgencyName());
			dossierStatistic.setDomainCode(domainCode);
			dossierStatistic.setDomainName(dossierStatisticList.get(0).getDomainName());
			dossierStatistic.setReporting(reporting);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			dossierStatistic.setViaPostalCount(viaPostalCount);
			dossierStatistic.setSaturdayCount(saturdayCount);
			dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
			dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
			dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
			dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);
			dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
			dossierStatistic.setProcessingInAPeriodCount(processingInAPeriodCount);
			dossierStatistic.setReleaseInAPeriodCount(releaseInAPeriodCount);
		}
		//
		return dossierStatistic;
	}

	public static void invokeProcessCalStatistic() throws Exception{

		List<ServerConfig> configList = ServerConfigLocalServiceUtil.getByServerAndProtocol("SERVER_STATISTIC_DVC", DossierStatisticConstants.STATISTIC_PROTOCOL);
		ServerConfig config = null;
		if (configList != null && configList.size() > 0) {
			config = configList.get(0);
			if (config == null) {
				return;
			}
		} else {

			return ;
		}

		JSONObject scObject = JSONFactoryUtil.createJSONObject(config.getConfigs());
		long groupId = 0;
		if (scObject != null) {
			if (scObject.has(Field.GROUP_ID)) {
				groupId = scObject.getLong(Field.GROUP_ID) > 0 ? scObject.getLong(Field.GROUP_ID) : 35417;
			}
		}
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		int[] reportArr = {0, 1};
		List<OpencpsDossierStatistic> statisticList = OpencpsDossierStatisticLocalServiceUtil.findByREPO_ARR(reportArr);
		Map<String, DossierStatisticKey> mapKey = null;
		DossierStatisticKey statisticKey = null;
		if (statisticList != null && statisticList.size() > 0) {
			mapKey = new HashMap<String, DossierStatisticKey>();
			for (OpencpsDossierStatistic opencpsDossierStatistic : statisticList) {
				StringBuilder sb = new StringBuilder();
				if (Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
					sb.append(opencpsDossierStatistic.getGovAgencyCode());
				} else {
					sb.append("all");
				}
				if (Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
					sb.append(StringPool.AT);
					sb.append(opencpsDossierStatistic.getDomainCode());
				} else {
					sb.append(StringPool.AT);
					sb.append("all");
				}
				if (Validator.isNotNull(opencpsDossierStatistic.getSystem())) {
					sb.append(StringPool.AT);
					sb.append(opencpsDossierStatistic.getSystem());
				} else {
					sb.append(StringPool.AT);
					sb.append("all");
				}
				if (Validator.isNotNull(opencpsDossierStatistic.getGroupAgencyCode())) {
					sb.append(StringPool.AT);
					sb.append(opencpsDossierStatistic.getGroupAgencyCode());
				} else {
					sb.append(StringPool.AT);
					sb.append("all");
				}
				//month and year
				sb.append(StringPool.AT);
				sb.append(opencpsDossierStatistic.getMonth());
				sb.append(StringPool.AT);
				sb.append(opencpsDossierStatistic.getYear());

				if (mapKey.isEmpty() || !mapKey.containsKey(sb.toString())
						|| (mapKey.containsKey(sb.toString()) && opencpsDossierStatistic.getReporting() == 0)) {
					statisticKey = new DossierStatisticKey();
					if (Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
						statisticKey.setGovAgencyCode(opencpsDossierStatistic.getGovAgencyCode());
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
						statisticKey.setDomainCode(opencpsDossierStatistic.getDomainCode());
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getSystem())) {
						statisticKey.setSystem(opencpsDossierStatistic.getSystem());
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getGroupAgencyCode())) {
						statisticKey.setGroupAgencyCode(opencpsDossierStatistic.getGroupAgencyCode());
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getReporting())) {
						statisticKey.setReporting(opencpsDossierStatistic.getReporting());
					}
					statisticKey.setMonth(opencpsDossierStatistic.getMonth());
					statisticKey.setYear(opencpsDossierStatistic.getYear());
					//
					mapKey.put(sb.toString(), statisticKey);
				}
			}
		}

		Map<String, DossierStatisticData> mapStatistic = new HashMap<>();
		for (Map.Entry<String, DossierStatisticKey> entry : mapKey.entrySet()) {
			DossierStatisticKey objectKey = entry.getValue();
			List<OpencpsDossierStatistic> dossierStatisticList = OpencpsDossierStatisticLocalServiceUtil
					.getByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, objectKey.getMonth(), objectKey.getYear(),
							objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
							objectKey.getSystem());

			DossierStatisticData dossierStatistic = processCalStatistic(groupId, objectKey.getMonth(), objectKey.getYear(), objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
					objectKey.getSystem(), objectKey.getReporting(), dossierStatisticList);

			if (dossierStatistic != null) {
				mapStatistic.put(entry.getKey(), dossierStatistic);
			}
		}
		// Convert Map to List jsonObject
		StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
		List<JSONObject> lstDossierDataObjs = statisticEngineUpdate.convertStatisticDataList(mapStatistic);

		engineUpdateAction.updateStatistic(lstDossierDataObjs);

	}

}
