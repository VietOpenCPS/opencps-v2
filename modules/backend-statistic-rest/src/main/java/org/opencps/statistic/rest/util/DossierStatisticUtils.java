package org.opencps.statistic.rest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.*;
import org.opencps.statistic.rest.engine.service.*;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceDomainRestFacadeImpl;
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

	private static OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private static OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();


	private static void processUpdateStatistic(long groupId, int month, int year, GetDossierRequest payload,
										StatisticEngineUpdateAction engineUpdateAction, ServiceDomainResponse serviceDomainResponse,
										Map<Integer, Map<String, DossierStatisticData>> calculateData)
			throws Exception {
//		engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);

		payload.setMonth(Integer.toString(month));
		payload.setYear(Integer.toString(year));
		payload.setCalculate(true);
		payload.setStart(QueryUtil.ALL_POS);
		payload.setEnd(QueryUtil.ALL_POS);
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
			if (dossierResponse != null) {
				List<GetDossierData> dossierData = dossierResponse.getData();

				if (dossierData != null) {
					//LOG.info("***** " + site.getGroupId() + source.size());

					if(dossierData.size() > 0) {
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();

						Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

						Date firstDay = StatisticUtils.getFirstDay(month, year);
						Date lastDay = StatisticUtils.getLastDay(month, year);
						engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, 0);
//						StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
//
//						statisticEngineUpdate.updateStatisticData(statisticData);
						calculateData.put(month, statisticData);
					}
				}
			}
		}
		else {
			DossierActions actions = new DossierActionsImpl();
			Sort[] sorts = null;
			sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
					true) };
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			if (payload.isCalculate()) {
				if (payload.getMonth() != null) {
					params.put(DossierTerm.MONTH, payload.getMonth());
				}
				else {
					params.put(DossierTerm.MONTH, Integer.toString(LocalDate.now().getMonthValue()));
				}
				if (payload.getYear() != null) {
					params.put(DossierTerm.YEAR, payload.getYear());
				}
				else {
					params.put(DossierTerm.YEAR, Integer.toString(LocalDate.now().getYear()));
				}
			}
			else {
				if (Validator.isNotNull(payload.getGovAgencyCode())) {
					params.put(DossierTerm.AGENCYS, payload.getGovAgencyCode());
				}
				if (Validator.isNotNull(payload.getFromStatisticDate())) {
					params.put(DossierTerm.FROM_STATISTIC_DATE, payload.getFromStatisticDate());
				}
				if (Validator.isNotNull(payload.getToStatisticDate())) {
					params.put(DossierTerm.TO_STATISTIC_DATE, payload.getToStatisticDate());
				}
			}
			//Add common params
			String strSystemId = DossierStatisticConstants.ALL_SYSTEM;
			params.put(DossierTerm.SYSTEM_ID, strSystemId);
			params.put(DossierTerm.TOP, DossierStatisticConstants.TOP_STATISTIC);

			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			long companyId = company.getCompanyId();
			int start = QueryUtil.ALL_POS;
			int end = QueryUtil.ALL_POS;

			if (payload.getStart() != 0) {
				start = payload.getStart();
			}
			else {
			}
			if (payload.getEnd() != 0) {
				end = payload.getEnd();
			}
			else {
			}

			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, start, end, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get(ConstantUtils.DATA);
			List<GetDossierData> dossierData = new ArrayList<>();
			int total = jsonData.getInt(ConstantUtils.TOTAL);

			//_log.info("GET DOSSIER SIZE: " + datas != null ? datas.size() : 0);
//			_log.info("GET DOSSIER total: " + total);

			if (total > datas.size()) {
				JSONObject jsonData2 = actions.getDossiers(-1, companyId, groupId, params, sorts, 0, total, new ServiceContext());
				datas = (List<Document>) jsonData2.get(ConstantUtils.DATA);
//				_log.debug("_GET ALL DOSSIER SIZE_: " + datas.size());
			}

			for (Document doc : datas) {
				GetDossierData model = new GetDossierData();
				model.setGroupId(GetterUtil.getInteger(doc.get(Field.GROUP_ID)));
				model.setServiceCode(doc.get(DossierTerm.SERVICE_CODE));
				model.setGovAgencyCode(doc.get(DossierTerm.GOV_AGENCY_CODE));
				model.setGovAgencyName(doc.get(DossierTerm.GOV_AGENCY_NAME));
				if (Validator.isNotNull(doc.get(DossierTerm.RECEIVE_DATE))) {
					Date receiveDate = APIDateTimeUtils.convertStringToDate(doc.get(DossierTerm.RECEIVE_DATE), APIDateTimeUtils._LUCENE_PATTERN);
					model.setReceiveDate(APIDateTimeUtils.convertDateToString(receiveDate, APIDateTimeUtils._NORMAL_PARTTERN));
				} else {
					model.setReceiveDate(doc.get(DossierTerm.RECEIVE_DATE));
				}
				model.setDueDate(doc.get(DossierTerm.DUE_DATE));
				model.setExtendDate(doc.get(DossierTerm.EXTEND_DATE));
				model.setFinishDate(doc.get(DossierTerm.FINISH_DATE));
				model.setReleaseDate(doc.get(DossierTerm.RELEASE_DATE));
				model.setDossierStatus(doc.get(DossierTerm.DOSSIER_STATUS));
				model.setDossierSubStatus(doc.get(DossierTerm.DOSSIER_SUB_STATUS));
				model.setLockState(doc.get(DossierTerm.LOCK_STATE));
				model.setDomainCode(doc.get(DossierTerm.DOMAIN_CODE));
				model.setDomainName(doc.get(DossierTerm.DOMAIN_NAME));
				model.setOnline(Boolean.parseBoolean(doc.get(DossierTerm.ONLINE)));
				model.setSystem(doc.get(DossierTerm.SYSTEM_ID));
//				if (!"0".contentEquals(doc.get(DossierTerm.VIA_POSTAL))) {
//					_log.debug("FIND DOSSIER: " + doc.get(DossierTerm.VIA_POSTAL));
//				}
				model.setViaPostal(Integer.parseInt(doc.get(DossierTerm.VIA_POSTAL)));
				if (Validator.isNotNull(doc.get(DossierTerm.SERVICE_LEVEL))) {
					try {
						model.setServiceLevel(Integer.parseInt(doc.get(DossierTerm.SERVICE_LEVEL)));
					}
					catch (Exception e) {
						_log.debug(e);
					}
				}
				model.setFromViaPostal(GetterUtil.getInteger(doc.get(DossierTerm.FROM_VIA_POSTAL)));

				dossierData.add(model);
			}

			//GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
			//if (dossierResponse != null) {
			//List<GetDossierData> dossierData = dossierResponse.getData();
			//			List<GetDossierData> dossierData = new ArrayList<>();

			if (dossierData != null && dossierData.size() > 0) {
				//LOG.info("***** " + site.getGroupId() + source.size());

				StatisticEngineFetch engineFetch = new StatisticEngineFetch();

				Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

				Date firstDay = StatisticUtils.getFirstDay(month, year);
				Date lastDay = StatisticUtils.getLastDay(month, year);
				engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, 0);
//					StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();

				calculateData.put(month, statisticData);
//					}
			}
		}
	}

	public static final int GROUP_TYPE_SITE = 1;

	public static void invokeDossierStatistic() throws Exception{
		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		List<Group> groups = GroupLocalServiceUtil.getCompanyGroups(company.getCompanyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

		List<Group> sites = new ArrayList<Group>();

		for (Group group : groups) {
			if (group.getType() == GROUP_TYPE_SITE && group.isSite()) {
				sites.add(group);
			}
		}

		Map<Integer, Map<String, DossierStatisticData>> calculateData = new HashMap<>();

		for (Group site : sites) {
//				if (site.getGroupId() != 45027) {
//					continue;
//				}
			Map<Integer, Map<Integer, Map<String, DossierStatisticData>>> calculateDatas = new HashMap<>();
			List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(site.getGroupId(), DossierStatisticConstants.STATISTIC_PROTOCOL);
//				_log.info("CALCULATE AFTER GET SERVER CONFIG: " + (System.currentTimeMillis() - startTime) + " ms");

			//			LOG.info("START getDossierStatistic(): " + site.getGroupId());

			//			GetDossierResponse dossierResponse = new GetDossierResponse();

			ServiceDomainRequest sdPayload = new ServiceDomainRequest();
			sdPayload.setGroupId(site.getGroupId());
			sdPayload.setStart(QueryUtil.ALL_POS);
			sdPayload.setEnd(QueryUtil.ALL_POS);
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
					if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
						sdPayload.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
					}
					if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
						sdPayload.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
					}
					if (scObject.has(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT_KEY)) {
						sdPayload.setEndpoint(scObject.getString(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT_KEY));
					}
				}
			}
//				_log.debug("STATISTICS CALL SERVICE DOMAIN: " + (System.currentTimeMillis() - startTime) + " ms");;
			ServiceDomainResponse serviceDomainResponse = null;
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
			}
			else {
				serviceDomainResponse = StatisticDataUtil.getLocalServiceDomain(sdPayload);
			}
//				_log.debug("STATISTICS CALL SERVICE DOMAIN END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

//				_log.info("CALCULATE AFTER GET SERVICE DOMAIN: " + serviceDomainResponse.getTotal() + ", " + (System.currentTimeMillis() - startTime) + " ms");
			GetDossierRequest payload = new GetDossierRequest();

			payload.setGroupId(site.getGroupId());
			payload.setStart(QueryUtil.ALL_POS);
			payload.setEnd(QueryUtil.ALL_POS);
			if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
				if (lstScs.size() >= 1) {
					JSONObject scObject = JSONFactoryUtil.createJSONObject(lstScs.get(0).getConfigs());
					if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
						payload.setUsername(scObject.getString(DossierStatisticConstants.USERNAME_KEY));
					}
					if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
						payload.setPassword(scObject.getString(DossierStatisticConstants.SECRET_KEY));
					}
					if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
						payload.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
					}
				}
			}
			//
			ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(site.getGroupId(),
					"SERVER_STATISTIC_SYNC", DossierStatisticConstants.STATISTIC_PROTOCOL);
			if (serverConfig == null) {
				serverConfig = ServerConfigLocalServiceUtil.getByServerNoAndProtocol(0,
						"SERVER_STATISTIC_SYNC", DossierStatisticConstants.STATISTIC_PROTOCOL);
			}

			JSONObject jsonEndPoint = JSONFactoryUtil.createJSONObject();
			if (serverConfig != null) {
				JSONObject scObject = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				if (scObject.has(DossierStatisticConstants.USERNAME_KEY)) {
					jsonEndPoint.put(DossierStatisticConstants.USERNAME_KEY,
							scObject.getString(DossierStatisticConstants.USERNAME_KEY));
				}
				if (scObject.has(DossierStatisticConstants.SECRET_KEY)) {
					jsonEndPoint.put(DossierStatisticConstants.SECRET_KEY,
							scObject.getString(DossierStatisticConstants.SECRET_KEY));
				}
				if (scObject.has(DossierStatisticConstants.URL_ENDPOINT)) {
					jsonEndPoint.put(DossierStatisticConstants.URL_ENDPOINT,
							scObject.getString(DossierStatisticConstants.URL_ENDPOINT));
				}
			}
			int monthCurrent = LocalDate.now().getMonthValue();
//				int monthCurrent = 4;
			_log.info("jsonEndPoint: "+jsonEndPoint);
			_log.info("monthCurrent: "+monthCurrent);
			int yearCurrent = LocalDate.now().getYear();
			Map<Integer, Boolean> mapFlagCurrent = new HashMap<>();
			for (int month = 1; month <= monthCurrent; month ++) {
				boolean flagStatistic = true;
				if (month < monthCurrent) {
//						_log.debug("STATISTICS CALCULATE ONE MONTH SITE: " + month + ", " + site.getGroupId() + ", " + site.getName(Locale.getDefault()) + " " + (System.currentTimeMillis() - startTime) + " ms");;
					OpencpsDossierStatistic statisticInfo = engineUpdateAction
							.getStatisticByMonthYearAndNotReport(site.getGroupId(), month, yearCurrent, 0);
					if (statisticInfo != null) {
						if (statisticInfo.getReporting() == 1 && jsonEndPoint != null) {
							List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
									.getDossierStatisticByMonthYearAndReport(site.getGroupId(), month, yearCurrent, 1);
							if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
								StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
								JSONArray jsonArr = statisticEngineUpdate.convertStatisticDataArray(dossierStatisticList);
								//
								String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint, JSONFactoryUtil.looseSerialize(jsonArr));
								if (Validator.isNotNull(sbUpdate)) {
									try {
										JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
										//
										if (jsonUpdate.has("value") && "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
											for (OpencpsDossierStatistic statistic : dossierStatisticList) {
												statistic.setReporting(2);
												OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(statistic);
											}
										}
									} catch (JSONException e1) {
										_log.debug(e1);
									}
								}
							}
						}
						flagStatistic = false;
					}
					if (flagStatistic) {
						try {
//								Map<Integer, Map<String, DossierStatisticData>> calculateData = new HashMap<>();
							processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
									engineUpdateAction, serviceDomainResponse, calculateData);
							//
							if (calculateData != null && jsonEndPoint != null) {
								for (Map.Entry<Integer, Map<String, DossierStatisticData>> mapInt : calculateData.entrySet()) {
									if (mapInt.getKey() == month) {
										StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
										JSONArray jsonArr = statisticEngineUpdate.convertMapDataList(mapInt.getValue());
										//
										String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint,
												JSONFactoryUtil.looseSerialize(jsonArr));
										if (Validator.isNotNull(sbUpdate)) {
											try {
												JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
												//
												if (jsonUpdate.has("value")
														&& "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
//														List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
//																.getDossierStatisticByMonthYearAndReport(site.getGroupId(), month, yearCurrent, 0);
//														for (OpencpsDossierStatistic statistic : dossierStatisticList) {
//															statistic.setReporting(1);
//															OpencpsDossierStatisticLocalServiceUtil
//																	.updateDossierStatistic(statistic);
//														}
												}
											} catch (JSONException e1) {
												_log.debug(e1);
											}
										}
									}
								}
							}
							calculateDatas.put(yearCurrent, calculateData);
						}
						catch (Exception e) {
							_log.debug(e);
						}
					}
//						_log.debug("STATISTICS CALCULATE ONE MONTH SITE : " + site.getName(Locale.getDefault()) + " END TIME " + (System.currentTimeMillis() - startTime) + " ms");;
				} else {
					//_log.info("START CAL monthCurrent: "+monthCurrent);
					try {
//							Map<Integer, Map<String, DossierStatisticData>> calculateData = new HashMap<>();
						processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
								engineUpdateAction, serviceDomainResponse, calculateData);
						if (calculateData != null && jsonEndPoint != null) {
							for (Map.Entry<Integer, Map<String, DossierStatisticData>> mapInt : calculateData.entrySet()) {
								if (mapInt.getKey() == month) {
									//_log.info("month: "+ month + "| SIZE: "+ mapInt.getValue().size());
									StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
									JSONArray jsonArr = statisticEngineUpdate.convertMapDataList(mapInt.getValue());
									//
									String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint,
											JSONFactoryUtil.looseSerialize(jsonArr));
									if (Validator.isNotNull(sbUpdate)) {
										try {
											JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
											//
											if (jsonUpdate.has("value")
													&& "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
											}
										} catch (JSONException e1) {
											_log.debug(e1);
										}
									}
								}
							}
						}
						calculateDatas.put(yearCurrent, calculateData);
					}
					catch (Exception e) {
						_log.debug(e);
					}
				}
				mapFlagCurrent.put(month, flagStatistic);
//					_log.info("CALCULATE AFTER GET PROCESS UPDATE STATISTIC: " + (System.currentTimeMillis() - startTime) + " ms");
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					_log.debug(e);
				}
			}

			//Recalculate data
			//TODO: Calculator again year ago
			int lastYear = LocalDate.now().getYear() - 1;
			boolean flagLastYear = true;
			Map<Integer, Boolean> mapFlagPrev = new HashMap<>();
			Map<Integer, Map<String, DossierStatisticData>> calculateLastData = new HashMap<>();
			for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
//					List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
//							.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear, 1);
				OpencpsDossierStatistic statisticInfo = engineUpdateAction
						.getStatisticByMonthYearAndNotReport(site.getGroupId(), lastMonth, lastYear, 0);
				if (statisticInfo != null) {
					if (statisticInfo.getReporting() == 1 && jsonEndPoint != null) {
						List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
								.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear, 1);
						if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
							JSONArray jsonArr = statisticEngineUpdate.convertStatisticDataArray(dossierStatisticList);
							//
							String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint, JSONFactoryUtil.looseSerialize(jsonArr));
							if (Validator.isNotNull(sbUpdate)) {
								try {
									JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
									//
									if (jsonUpdate.has("value") && "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
										for (OpencpsDossierStatistic statistic : dossierStatisticList) {
											statistic.setReporting(2);
											OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(statistic);
										}
									}
								} catch (JSONException e1) {
									_log.debug(e1);
								}
							}
						}
					}
					flagLastYear = false;
				}
				if (flagLastYear) {
					try {
						processUpdateStatistic(site.getGroupId(), lastMonth, lastYear, payload,
								engineUpdateAction, serviceDomainResponse, calculateLastData);
						if (calculateData != null && jsonEndPoint != null) {

							for (Map.Entry<Integer, Map<String, DossierStatisticData>> mapInt : calculateData.entrySet()) {
								if (mapInt.getKey() == lastMonth) {

									StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
									JSONArray jsonArr = statisticEngineUpdate.convertMapDataList(mapInt.getValue());
									//
									String sbUpdate = DossierStatisticUtils.invokeUpdateStatistic(jsonEndPoint,
											JSONFactoryUtil.looseSerialize(jsonArr));
									if (Validator.isNotNull(sbUpdate)) {
										try {
											JSONObject jsonUpdate = JSONFactoryUtil.createJSONObject(sbUpdate);
											//
											if (jsonUpdate.has("value")
													&& "SUCCESSFULL".equals(jsonUpdate.getString("value"))) {
											}
										} catch (JSONException e1) {
											_log.debug(e1);
										}
									}
								}
							}
						}
						calculateDatas.put(lastYear, calculateLastData);
					}
					catch (Exception e) {
						_log.debug(e);
					}
				}
				mapFlagPrev.put(lastMonth, flagLastYear);
			}
//				_log.info("CALCULATE AFTER GET FLAG LAST YEAR: " + (System.currentTimeMillis() - startTime) + " ms");

			StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
			List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
			for (int month = 1; month <= monthCurrent; month ++) {
				if (mapFlagCurrent.get(month)) {
					if (calculateDatas.get(yearCurrent) != null
							&& calculateDatas.get(yearCurrent).get(month) != null) {

						lstDossierDataObjs
								.addAll(statisticEngineUpdate.convertStatisticDataList(calculateData.get(month)));
					}
				}
			}
//				_log.info("CALCULATE AFTER CURRENT MONTH YEAR UPDATE STATISTIC DATE TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");

			for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
				if (mapFlagPrev.get(lastMonth)) {
					if (calculateDatas.get(lastYear) != null &&
							calculateDatas.get(lastYear).get(lastMonth) != null) {
//							statisticEngineUpdate.updateStatisticData(calculateDatas.get(lastYear).get(lastMonth), allSiteDatas);
						lstDossierDataObjs.addAll(statisticEngineUpdate.convertStatisticDataList(calculateDatas.get(lastYear).get(lastMonth)));
					}
				}
			}
//				_log.info("SIZE TOTAL MONTH: "+lstDossierDataObjs.size());
			engineUpdateAction.updateStatistic(lstDossierDataObjs);
//				_log.info("CALCULATE AFTER LAST MONTH YEAR UPDATE STATISTIC DATE TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");

			// Caculate statistic each year
			StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
//				List<OpencpsDossierStatistic> lstCurrents = OpencpsDossierStatisticLocalServiceUtil.fetchDossierStatistic(site.getGroupId(), -1, LocalDate.now().getYear(), "total", "total", "total", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			List<OpencpsDossierStatistic> lstCurrents = OpencpsDossierStatisticLocalServiceUtil.findByG_NM_Y(site.getGroupId(), 0, LocalDate.now().getYear());
			//_log.info("lstCurrents: "+lstCurrents.size());
			Comparator<OpencpsDossierStatistic> compareByYearMonth = new Comparator<OpencpsDossierStatistic>() {
				@Override
				public int compare(OpencpsDossierStatistic o1, OpencpsDossierStatistic o2) {
					if (o1.getYear() == o2.getYear()) {
						if (o1.getMonth() > o2.getMonth()) {
							return -1;
						} else if (o1.getMonth() == o2.getMonth()) {
							return 0;
						} else {
							return 1;
						}
					} else if (o1.getYear() > o2.getYear()) {
						return -1;
					} else {
						return 1;
					}
				}
			};
			ArrayList<OpencpsDossierStatistic> lstSortCurrents = new ArrayList<OpencpsDossierStatistic>();
			lstSortCurrents.addAll(lstCurrents);
			Collections.sort(lstSortCurrents, compareByYearMonth);
			//Current year
			statisticSumYearService.batchCaculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear(), lstScs, lstSortCurrents, jsonEndPoint);
//				_log.info("CALCULATE AFTER SUM CURRENT YEAR TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");
			// Last year
			List<OpencpsDossierStatistic> lstLasts = OpencpsDossierStatisticLocalServiceUtil.fetchDossierStatistic(site.getGroupId(), -1, lastYear, "total", "total", "total", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			ArrayList<OpencpsDossierStatistic> lstSortLasts = new ArrayList<OpencpsDossierStatistic>();
			lstSortLasts.addAll(lstLasts);
			Collections.sort(lstSortLasts, compareByYearMonth);
			statisticSumYearService.batchCaculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear, lstScs, lstSortLasts, jsonEndPoint);

//				_log.info("CALCULATE AFTER SUM LAST YEAR TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");
			statisticSumYearService.batchCaculateSumAllYear(site.getCompanyId(), site.getGroupId(), 0, lstScs, jsonEndPoint);

		}
	}


}
