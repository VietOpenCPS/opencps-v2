package org.opencps.statistic.rest.engine;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictGroup;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.model.DictItemGroup;
import org.opencps.datamgt.service.DictGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemGroupLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetDossierData;
import org.opencps.statistic.rest.dto.GetDossierRequest;
import org.opencps.statistic.rest.dto.GetDossierResponse;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;
import org.opencps.statistic.rest.engine.service.StatisticEngineFetch;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.engine.service.StatisticSumYearService;
import org.opencps.statistic.rest.engine.service.StatisticUtils;
import org.opencps.statistic.rest.facade.OpencpsCallDossierRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceDomainRestFacadeImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.StatisticDataUtil;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
//import org.opencps.systemmgt.constants.SchedulerRecordTerm;
//import org.opencps.systemmgt.model.SchedulerRecord;
//import org.opencps.systemmgt.service.SchedulerRecordLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

//@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine_bk extends BaseMessageListener {
	private volatile boolean isRunningDossier = false;
	
	//private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);
	protected Log _log = LogFactoryUtil.getLog(DossierStatisticEngine_bk.class);
	
	public static final int GROUP_TYPE_SITE = 1;
	
	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();

	private static final String GROUP_SBN = "SBN";
	private static final String GROUP_QUAN_HUYEN = "QUAN_HUYEN";
	private static final String GROUP_XA_PHUONG = "XA_PHUONG";
	private static final String CALCULATE_GROUP_STATISTIC_ENABLE = "org.opencps.statistic.group.enable";
	
	private boolean isCalculateGroupStatistic() {
		String calculateGroupStatisticEnable = PropsUtil.get(CALCULATE_GROUP_STATISTIC_ENABLE);
		return Validator.isNotNull(calculateGroupStatisticEnable) ? Boolean.parseBoolean(calculateGroupStatisticEnable) : true;
	}
	
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.debug("START STATISTIC DOSSIER: " + isRunningDossier);
		if (!isRunningDossier) {
			isRunningDossier = true;
		}
		else {
			return;
		}
		long startTime = System.currentTimeMillis();
		Date nowLog = new Date();
		try {
			_log.info("START TRACE LOG STATISTICS TIME: " + nowLog);
			_log.info("START STATISTICS TIME: " + (System.currentTimeMillis() - startTime) + " ms");
			
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
			Comparator<DictItem> compareByItemCode = new Comparator<DictItem>() {
				@Override
			    public int compare(DictItem o1, DictItem o2) {
			        return o1.getItemCode().compareTo(o2.getItemCode());
			    }
			};
			
			for (Group site : sites) {
				StringBuilder groupGovAgency = new StringBuilder();
				List<String> lstGroupGovs = new ArrayList<String>();
//				_log.info("CALCULATE FOR SITE: " + site.getName() + ", " + (System.currentTimeMillis() - startTime) + " ms");
				if (isCalculateGroupStatistic()) {
					DictGroup dg = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(GROUP_SBN, site.getGroupId());
					List<DictItemGroup> lstDigs = (dg != null) ? DictItemGroupLocalServiceUtil.findByDictGroupId(site.getGroupId(), dg.getDictGroupId()) : new ArrayList<DictItemGroup>();
					List<DictItem> lstGovs = new ArrayList<DictItem>();
					int count = 0;
					long[] ids;
					if (lstDigs.size() > 0) {
						ids = new long[lstDigs.size()];
						for (DictItemGroup dig : lstDigs) {
							ids[count++] = dig.getDictItemId();
//							DictItem di = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
//							lstGovs.add(di);
						}
						lstGovs = DictItemLocalServiceUtil.findByF_IDS(ids);					
					}
					ArrayList<DictItem> lstSortItems = new ArrayList<DictItem>();
					lstSortItems.addAll(lstGovs);
					
					Collections.sort(lstSortItems, compareByItemCode);
					lstGovs = lstSortItems;
					
					for (DictItem di : lstGovs) {
						if (!StringPool.BLANK.contentEquals(groupGovAgency.toString())) {
							groupGovAgency.append(StringPool.COMMA);
						}
						groupGovAgency.append(di.getItemCode());
					}
					if (!StringPool.BLANK.contentEquals(groupGovAgency.toString())) {
						lstGroupGovs.add(groupGovAgency.toString());					
					}

					dg = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(GROUP_QUAN_HUYEN, site.getGroupId());
					lstDigs = (dg != null) ? DictItemGroupLocalServiceUtil.findByDictGroupId(site.getGroupId(), dg.getDictGroupId()) : new ArrayList<DictItemGroup>();
					lstGovs = new ArrayList<DictItem>();
					StringBuilder groupGovAgencyQH = new StringBuilder();
					count = 0;
					if (lstDigs.size() > 0) {
						ids = new long[lstDigs.size()];
						
						for (DictItemGroup dig : lstDigs) {
							ids[count++] = dig.getDictItemId();
//							DictItem di = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
//							lstGovs.add(di);
						}
						lstGovs = DictItemLocalServiceUtil.findByF_IDS(ids);					
					}
					lstSortItems = new ArrayList<DictItem>();
					lstSortItems.addAll(lstGovs);
					
					Collections.sort(lstSortItems, compareByItemCode);
					lstGovs = lstSortItems;
					
					for (DictItem di : lstGovs) {
						if (!StringPool.BLANK.contentEquals(groupGovAgencyQH.toString())) {
							groupGovAgencyQH.append(StringPool.COMMA);
						}
						groupGovAgencyQH.append(di.getItemCode());
					}
					if (!StringPool.BLANK.contentEquals(groupGovAgencyQH.toString())) {
						lstGroupGovs.add(groupGovAgencyQH.toString());					
					}
					
					DictGroup dgqh = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(GROUP_QUAN_HUYEN, site.getGroupId());
					List<DictItemGroup> lstDgqhs = (dgqh != null) ? DictItemGroupLocalServiceUtil.findByDictGroupId(site.getGroupId(), dgqh.getDictGroupId()) : new ArrayList<DictItemGroup>();
					lstGovs = new ArrayList<DictItem>();
					
					if(lstDgqhs.size() > 0) {
						for (DictItemGroup dig : lstDgqhs) {
							lstGovs = DictItemLocalServiceUtil.findByF_parentItemId(dig.getDictItemId());
							lstSortItems = new ArrayList<DictItem>();
							lstSortItems.addAll(lstGovs);
							
							Collections.sort(lstSortItems, compareByItemCode);
							lstGovs = lstSortItems;
							
							StringBuilder groupGovAgencyXPByQH = new StringBuilder();
							for (DictItem di : lstGovs) {
								if (!StringPool.BLANK.contentEquals(groupGovAgencyXPByQH.toString())) {
									groupGovAgencyXPByQH.append(StringPool.COMMA);
								}
								groupGovAgencyXPByQH.append(di.getItemCode());
							}
							if (!StringPool.BLANK.contentEquals(groupGovAgencyXPByQH.toString())) {
								lstGroupGovs.add(groupGovAgencyXPByQH.toString());					
							}							
						}
					}
					/*dg = DictGroupLocalServiceUtil.fetchByF_DictGroupCode(GROUP_XA_PHUONG, site.getGroupId());
					lstDigs = (dg != null) ? DictItemGroupLocalServiceUtil.findByDictGroupId(site.getGroupId(), dg.getDictGroupId()) : new ArrayList<DictItemGroup>();
					lstGovs = new ArrayList<DictItem>();
					StringBuilder groupGovAgencyXP = new StringBuilder();
					if (lstDigs.size() > 0) {
						ids = new long[lstDigs.size()];
						count = 0;
						for (DictItemGroup dig : lstDigs) {
							ids[count++] = dig.getDictItemId();
//							DictItem di = DictItemLocalServiceUtil.fetchDictItem(dig.getDictItemId());
//							lstGovs.add(di);
						}
						lstGovs = DictItemLocalServiceUtil.findByF_IDS(ids);					
					}
					lstSortItems = new ArrayList<DictItem>();
					lstSortItems.addAll(lstGovs);
					
					Collections.sort(lstSortItems, compareByItemCode);
					lstGovs = lstSortItems;
					
					for (DictItem di : lstGovs) {
						if (!StringPool.BLANK.contentEquals(groupGovAgencyXP.toString())) {
							groupGovAgencyXP.append(StringPool.COMMA);
						}
						groupGovAgencyXP.append(di.getItemCode());
					}
					if (!StringPool.BLANK.contentEquals(groupGovAgencyXP.toString())) {
						lstGroupGovs.add(groupGovAgencyXP.toString());					
					}
					*/
//					for (String groupGovAgencyCode : lstGroupGovs) {
//						_log.info("CALCULATE GROUP AGENCY CODE: " + groupGovAgencyCode);
//					}
					
				
				}
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
				
				int monthCurrent = LocalDate.now().getMonthValue();
//				int monthCurrent = 4;
				_log.info("monthCurrent: "+monthCurrent);
				int yearCurrent = LocalDate.now().getYear();
				Map<Integer, Boolean> mapFlagCurrent = new HashMap<>();
				/**
				for (int month = 1; month <= monthCurrent; month ++) {
					boolean flagStatistic = true;
					if (month < monthCurrent) {
//						_log.debug("STATISTICS CALCULATE ONE MONTH SITE: " + month + ", " + site.getGroupId() + ", " + site.getName(Locale.getDefault()) + " " + (System.currentTimeMillis() - startTime) + " ms");;
						List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
								.getDossierStatisticByMonthYearAndReport(site.getGroupId(), month, yearCurrent, true);
						if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
							//for (OpencpsDossierStatistic dossierStatistic : dossierStatisticList) {
								//boolean reporting = dossierStatistic.getReporting();
								//if (!reporting) {
							flagStatistic = false;
									//break;
								//}
							//}
						}
						if (flagStatistic) {
							try {
//								Map<Integer, Map<String, DossierStatisticData>> calculateData = new HashMap<>();
								processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
									engineUpdateAction, serviceDomainResponse, calculateData, lstGroupGovs);
								calculateDatas.put(yearCurrent, calculateData);
							}
							catch (Exception e) {
								_log.debug(e);
							}
						}
//						_log.debug("STATISTICS CALCULATE ONE MONTH SITE : " + site.getName(Locale.getDefault()) + " END TIME " + (System.currentTimeMillis() - startTime) + " ms");;
					} else {
						_log.info("START CAL monthCurrent: "+monthCurrent);
						try {
//							Map<Integer, Map<String, DossierStatisticData>> calculateData = new HashMap<>();
							processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
								engineUpdateAction, serviceDomainResponse, calculateData, lstGroupGovs);
							calculateDatas.put(yearCurrent, calculateData);
						}
						catch (Exception e) {
							_log.debug(e);
						}
					}
					mapFlagCurrent.put(month, flagStatistic);
//					_log.info("CALCULATE AFTER GET PROCESS UPDATE STATISTIC: " + (System.currentTimeMillis() - startTime) + " ms");
					
				}
				**/

				//Recalculate data				
				//TODO: Calculator again year ago
				int lastYear = LocalDate.now().getYear() - 1;
				boolean flagLastYear = true;
				Map<Integer, Boolean> mapFlagPrev = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticData>> calculateLastData = new HashMap<>();
				/**
				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
							.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear, true);
					if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
						flagLastYear = false;
					}
					if (flagLastYear) {
						try {
							processUpdateStatistic(site.getGroupId(), lastMonth, lastYear, payload,
								engineUpdateAction, serviceDomainResponse, calculateLastData, lstGroupGovs);
							calculateDatas.put(lastYear, calculateLastData);
						}
						catch (Exception e) {
							_log.debug(e);
						}
					}
					mapFlagPrev.put(lastMonth, flagLastYear);
				}
				**/
//				_log.info("CALCULATE AFTER GET FLAG LAST YEAR: " + (System.currentTimeMillis() - startTime) + " ms");
				
//				3 year before
//				int lastYear2 = LocalDate.now().getYear() - 2;
//				boolean flagLastYear2 = true;
//				Map<Integer, Boolean> mapFlagPrev2 = new HashMap<>();
//				Map<Integer, Map<String, DossierStatisticData>> calculateLastData2 = new HashMap<>();
//				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
//					List<OpencpsDossierStatistic> dossierStatisticList2 = engineUpdateAction
//							.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear2, true);
//					if (dossierStatisticList2 != null && dossierStatisticList2.size() > 0) {
//						flagLastYear2 = false;
//					}
//					if (flagLastYear2) {
//						try {
//							processUpdateStatistic(site.getGroupId(), lastMonth, lastYear2, payload,
//								engineUpdateAction, serviceDomainResponse, calculateLastData2);
//							calculateDatas.put(lastYear2, calculateLastData2);
//						}
//						catch (Exception e) {
//							_log.debug(e);
//						}
//					}
//					mapFlagPrev2.put(lastMonth, flagLastYear2);
//				}
//
//				int lastYear3 = LocalDate.now().getYear() - 3;
//				boolean flagLastYear3 = true;
//				Map<Integer, Boolean> mapFlagPrev3 = new HashMap<>();
//				Map<Integer, Map<String, DossierStatisticData>> calculateLastData3 = new HashMap<>();
//				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
//					List<OpencpsDossierStatistic> dossierStatisticList3 = engineUpdateAction
//							.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear3, true);
//					if (dossierStatisticList3 != null && dossierStatisticList3.size() > 0) {
//						flagLastYear3 = false;
//					}
//					if (flagLastYear3) {
//						try {
//							processUpdateStatistic(site.getGroupId(), lastMonth, lastYear3, payload,
//								engineUpdateAction, serviceDomainResponse, calculateLastData3);
//							calculateDatas.put(lastYear3, calculateLastData3);
//						}
//						catch (Exception e) {
//							_log.debug(e);
//						}
//					}
//					mapFlagPrev3.put(lastMonth, flagLastYear3);
//				}

//				List<OpencpsDossierStatistic> allSiteDatas = OpencpsDossierStatisticLocalServiceUtil.findByG(site.getGroupId());
//				if (allSiteDatas.size() == 0 && site.getGroupId() == 52737) {
//					System.out.println("STATISTIC SIZE 0: " + site.getGroupId());
//				}
				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
				List<JSONObject> lstDossierDataObjs = new ArrayList<JSONObject>();
				for (int month = 1; month <= monthCurrent; month ++) {
					if (mapFlagCurrent.get(month)) {						
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, yearCurrent);
//						}
//						catch (Exception e) {
//							_log.debug(e);
//						}
						if (calculateDatas.get(yearCurrent) != null &&
								calculateDatas.get(yearCurrent).get(month) != null) {
//						if (calculateData.get(month) != null) {
//							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
//							_log.debug("DOSSIER CAL MEMORY SIZE CURRENT: " + calculateDatas.get(yearCurrent).get(month).size());
//							statisticEngineUpdate.updateStatisticData(calculateDatas.get(yearCurrent).get(month), allSiteDatas);
//							statisticEngineUpdate.updateStatisticData(calculateData.get(month));
							lstDossierDataObjs.addAll(statisticEngineUpdate.convertStatisticDataList(calculateData.get(month)));
						}
					}
				}
//				_log.info("CALCULATE AFTER CURRENT MONTH YEAR UPDATE STATISTIC DATE TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");

				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					if (mapFlagPrev.get(lastMonth)) {
						if (calculateDatas.get(lastYear) != null &&
								calculateDatas.get(lastYear).get(lastMonth) != null) {
//							statisticEngineUpdate.updateStatisticData(calculateDatas.get(lastYear).get(lastMonth), allSiteDatas);
//							statisticEngineUpdate.updateStatisticData(calculateDatas.get(lastYear).get(lastMonth));
//							lstDossierDataObjs.addAll(statisticEngineUpdate.convertStatisticDataList(calculateDatas.get(lastYear).get(lastMonth)));
							lstDossierDataObjs.addAll(statisticEngineUpdate.convertStatisticDataList(calculateDatas.get(lastYear).get(lastMonth)));
						}
					}
				}
				engineUpdateAction.updateStatistic(lstDossierDataObjs);
				
//				_log.info("CALCULATE AFTER LAST MONTH YEAR UPDATE STATISTIC DATE TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");

//				3 year before
//				StatisticEngineUpdate statisticEngineUpdate2 = new StatisticEngineUpdate();
//				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
//					if (mapFlagPrev2.get(lastMonth)) {
//					if (mapFlagPrev2.get(lastMonth)) {
//					if (mapFlagPrev2.get(lastMonth)) {
//						if (calculateDatas.get(lastYear2) != null &&
//								calculateDatas.get(lastYear2).get(lastMonth) != null) {
//							statisticEngineUpdate2.updateStatisticData(calculateDatas.get(lastYear2).get(lastMonth));
//						}
//					}
//				}
//
//				StatisticEngineUpdate statisticEngineUpdate3 = new StatisticEngineUpdate();
//				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
//					if (mapFlagPrev3.get(lastMonth)) {
//						if (calculateDatas.get(lastYear3) != null &&
//								calculateDatas.get(lastYear3).get(lastMonth) != null) {
//							statisticEngineUpdate3.updateStatisticData(calculateDatas.get(lastYear3).get(lastMonth));
//						}
//					}
//				}

				/* Update summary */
				//Delete record
//				try {
//					engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, LocalDate.now().getYear());
//				}
//				catch (Exception e) {
//					_log.debug(e);
//				}
				// Caculate statistic each year
				StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
//				List<OpencpsDossierStatistic> lstCurrents = OpencpsDossierStatisticLocalServiceUtil.fetchDossierStatistic(site.getGroupId(), -1, LocalDate.now().getYear(), "total", "total", "total", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				List<OpencpsDossierStatistic> lstCurrents = OpencpsDossierStatisticLocalServiceUtil.findByG_NM_Y(site.getGroupId(), 0, LocalDate.now().getYear());
				Comparator<OpencpsDossierStatistic> compareByYearMonth = new Comparator<OpencpsDossierStatistic>() {
					@Override
				    public int compare(OpencpsDossierStatistic o1, OpencpsDossierStatistic o2) {
				        if (o1.getYear() == o2.getYear()) {
				        	if (o1.getMonth() > o2.getMonth()) {
				        		return -1;
				        	}
				        	else if (o1.getMonth() == o2.getMonth()) {
				        		return 0;
				        	}
				        	else {
				        		return 1;
				        	}
				        }
				        else if (o1.getYear() > o2.getYear()) {
				        	return -1;
				        }
				        else {
				        	return 1;
				        }
				    }
				};
				ArrayList<OpencpsDossierStatistic> lstSortCurrents = new ArrayList<OpencpsDossierStatistic>();
				lstSortCurrents.addAll(lstCurrents);
				Collections.sort(lstSortCurrents, compareByYearMonth);
				//Current year
				//statisticSumYearService.batchCaculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear(), lstScs, lstSortCurrents);
//				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear(), lstGroupGovs, lstScs);
//				_log.info("CALCULATE AFTER SUM CURRENT YEAR TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");
				// Last year
				List<OpencpsDossierStatistic> lstLasts = OpencpsDossierStatisticLocalServiceUtil.fetchDossierStatistic(site.getGroupId(), -1, lastYear, "total", "total", "total", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				ArrayList<OpencpsDossierStatistic> lstSortLasts = new ArrayList<OpencpsDossierStatistic>();
				lstSortLasts.addAll(lstLasts);
				Collections.sort(lstSortLasts, compareByYearMonth);
				//statisticSumYearService.batchCaculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear, lstScs, lstSortLasts);

//				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear, lstGroupGovs, lstScs, lstLasts);
//				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear, lstGroupGovs, lstScs);
//				_log.info("CALCULATE AFTER SUM LAST YEAR TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");

//				3 year before
//				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear2);
//				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear3);
				//Caculate statistic all year
//				_log.info("START STATISTIC ALL YEAR: ");
				//statisticSumYearService.batchCaculateSumAllYear(site.getCompanyId(), site.getGroupId(), 0, lstScs);
				
//				statisticSumYearService.caculateSumAllYear(site.getCompanyId(), site.getGroupId(), 0, lstGroupGovs, lstScs);
//				statisticSumYearService.caculateSumAllYear(site.getCompanyId(), site.getGroupId(), 0, lstGroupGovs, lstScs);
//				_log.info("CALCULATE AFTER SUM ALL YEAR TO DATABASE: " + (System.currentTimeMillis() - startTime) + " ms");
			}
	
			
	//		if (schedulerRecord != null) {
	//			SchedulerRecordLocalServiceUtil.updateSchedulerRecord(
	//					schedulerRecord.getSchedulerId(), schedulerRecord.getSchedulerType(), 
	//					null, null, null, SchedulerRecordTerm.DOSSIER_STATISTIC_SCHEDULER_MIN_DURATION, SchedulerRecordTerm.DOSSIER_STATISTIC_SCHEDULER_MAX_DURATION);
	//		}
			}
		catch (Exception e) {
			_log.error(e);
		}
		_log.info("END TRACE LOG STATISTICS TIME: " + nowLog);
		_log.info("STATISTICS END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

		isRunningDossier = false;
	}

	@SuppressWarnings("unchecked")
	private void processUpdateStatistic(long groupId, int month, int year, GetDossierRequest payload,
			StatisticEngineUpdateAction engineUpdateAction, ServiceDomainResponse serviceDomainResponse,
			Map<Integer, Map<String, DossierStatisticData>> calculateData, List<String> lstGroupGovs)
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
						if (serviceDomainResponse != null) {
							List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
							if (serviceDomainData != null) {
								for (ServiceDomainData sdd : serviceDomainData) {
									boolean existsDomain = false;
									for (GetDossierData dd : dossierData) {
										if (dd.getDomainCode().equals(sdd.getItemCode())) {
											existsDomain = true;
											break;
										}
									}
									if (existsDomain) {
										
									}
									else {
//										try {
//											engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//										} catch (NoSuchOpencpsDossierStatisticException e) {
//											_log.debug(e);
//										}
									}
								}
								for (GetDossierData dd : dossierData) {
									boolean existsDomain = false;
									for (ServiceDomainData sdd : serviceDomainData) {
										if (dd.getDomainCode().equals(sdd.getItemCode())) {
											existsDomain = true;
											break;
										}
									}
									if (existsDomain) {
										
									}
									else {
//										try {
//											engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, dd.getDomainCode(), month, year);
//										} catch (NoSuchOpencpsDossierStatisticException e) {
//											_log.debug(e);
//										}
									}
								}
							}
						}
						else {
//							try {
//								engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//							}
//							catch (NoSuchOpencpsDossierStatisticException e) {
//								_log.debug(e);
//							}
						}
						
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();

						Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

						Date firstDay = StatisticUtils.getFirstDay(month, year);
						Date lastDay = StatisticUtils.getLastDay(month, year);
						/**engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, false, lstGroupGovs); **/
//						StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
//						
//						statisticEngineUpdate.updateStatisticData(statisticData);	
						calculateData.put(month, statisticData);
					}
					else {
						List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
						if (serviceDomainData != null) {
							for (ServiceDomainData sdd : serviceDomainData) {
//								try {
//									engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//								} catch (NoSuchOpencpsDossierStatisticException e) {
//									_log.debug(e);
//								}
							}
						}	
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//						}
//						catch (NoSuchOpencpsDossierStatisticException e) {
//							_log.debug(e);
//						}
					}
				}
				else {
					List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
					if (serviceDomainData != null) {
						for (ServiceDomainData sdd : serviceDomainData) {
//							try {
//								engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//							} catch (NoSuchOpencpsDossierStatisticException e) {
//								_log.debug(e);	
//							}
						}
					}
//					try {
//						engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//					}
//					catch (Exception e) {
//						_log.debug(e);
//					}
				}
			}
			else {
//				try {
//					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//				}
//				catch (Exception e) {
//					_log.debug(e);
//				}
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

			_log.info("GET DOSSIER SIZE: " + datas != null ? datas.size() : 0);
			_log.info("GET DOSSIER total: " + total);

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
//					if(dossierData.size() > 0) {
					if (serviceDomainResponse != null) {
						List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
						if (serviceDomainData != null) {
							for (ServiceDomainData sdd : serviceDomainData) {
								boolean existsDomain = false;
								for (GetDossierData dd : dossierData) {
									if (dd.getDomainCode().equals(sdd.getItemCode())) {
										existsDomain = true;
										break;
									}
								}
								if (existsDomain) {
									
								}
								else {
//									try {
//										engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//									} catch (NoSuchOpencpsDossierStatisticException e) {
//										_log.debug(e);
//									}
								}
							}
							for (GetDossierData dd : dossierData) {
								boolean existsDomain = false;
								for (ServiceDomainData sdd : serviceDomainData) {
									if (dd.getDomainCode().equals(sdd.getItemCode())) {
										existsDomain = true;
										break;
									}
								}
								if (existsDomain) {
									
								}
								else {
//									try {
//										engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, dd.getDomainCode(), month, year);
//									} catch (NoSuchOpencpsDossierStatisticException e) {
//										_log.debug(e);
//									}
								}
							}
						}
					}
					else {
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//						}
//						catch (NoSuchOpencpsDossierStatisticException e) {
//							_log.debug(e);
//						}
					}
						
					StatisticEngineFetch engineFetch = new StatisticEngineFetch();

					Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

					Date firstDay = StatisticUtils.getFirstDay(month, year);
					Date lastDay = StatisticUtils.getLastDay(month, year);
					/** engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, false, lstGroupGovs); **/
	//					StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
	//					
	//					statisticEngineUpdate.updateStatisticData(statisticData);	
//					_log.debug("PUT MONTH: " + month + ", " + groupId);
					calculateData.put(month, statisticData);
//					}
//					else {
//						List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
//						if (serviceDomainData != null) {
//							for (ServiceDomainData sdd : serviceDomainData) {
//								try {
//									engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//								} catch (NoSuchOpencpsDossierStatisticException e) {
//									_log.debug(e);	
//								}
//							}
//						}	
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//						}
//						catch (NoSuchOpencpsDossierStatisticException e) {
//							_log.debug(e);
//						}
//					}
				}
				else {
					List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
					if (serviceDomainData != null) {
						for (ServiceDomainData sdd : serviceDomainData) {
//							try {
//								engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//							} catch (NoSuchOpencpsDossierStatisticException e) {
//								_log.debug(e);	
//							}
						}
					}
//					try {
//						engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//					}
//					catch (Exception e) {
//						_log.debug(e);
//					}
				}
			}
			/*
			else {
				try {
					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
				}
				catch (Exception e) {
					_log.debug(e);
				}
			}
			*/
//		}
	}

	//Time engine dossier
	private static int timeStatistic = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.time"))
			? Integer.valueOf(PropsUtil.get("opencps.statistic.dossier.time"))
			: 10;
	/**
	   * activate: Called whenever the properties for the component change (ala Config Admin)
	   * or OSGi is activating the component.
	   * @param properties The properties map from Config Admin.
	   * @throws SchedulerException in case of error.
	   */
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, timeStatistic, TimeUnit.MINUTE);

		  _schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		  _schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);
		  
//		  _schedulerEntryImpl.setTrigger(jobTrigger);

		  if (_initialized) {
			  deactivate();
		  }

	    _schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	    _initialized = true;
	  }
	  
	@Deactivate
	protected void deactivate() {
		if (_initialized) {
			try {
				_schedulerEngineHelper.unschedule(_schedulerEntryImpl, getStorageType());
		    } catch (SchedulerException se) {
		        if (_log.isWarnEnabled()) {
		        	_log.warn("Unable to unschedule trigger", se);
		        }
		    }

		      _schedulerEngineHelper.unregister(this);
		}
		_initialized = false;
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler entry wrapper.
	 * @return StorageType The storage type to use.
	*/
	protected StorageType getStorageType() {
	    if (_schedulerEntryImpl instanceof StorageTypeAware) {
	    	return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
	    }
	    
	    return StorageType.PERSISTED;
	}
	  
	/**
	   * setModuleServiceLifecycle: So this requires some explanation...
	   * 
	   * OSGi will start a component once all of it's dependencies are satisfied.  However, there
	   * are times where you want to hold off until the portal is completely ready to go.
	   * 
	   * This reference declaration is waiting for the ModuleServiceLifecycle's PORTAL_INITIALIZED
	   * component which will not be available until, surprise surprise, the portal has finished
	   * initializing.
	   * 
	   * With this reference, this component activation waits until portal initialization has completed.
	   * @param moduleServiceLifecycle
	   */
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}
	  
	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
		_triggerFactory = triggerFactory;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {
		_schedulerEngineHelper = schedulerEngineHelper;
	}
	
	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;	
}
