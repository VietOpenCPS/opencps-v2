package org.opencps.statistic.rest.engine;

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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
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

@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine extends BaseMessageListener {
	private volatile boolean isRunningDossier = false;
	
	//private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);
	protected Log _log = LogFactoryUtil.getLog(DossierStatisticEngine.class);
	
	public static final int GROUP_TYPE_SITE = 1;
	
	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();

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
		try {
			_log.debug("STATISTICS START TIME: " + (System.currentTimeMillis() - startTime) + " ms");
			
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
				Map<Integer, Map<Integer, Map<String, DossierStatisticData>>> calculateDatas = new HashMap<>();
				List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(site.getGroupId(), DossierStatisticConstants.STATISTIC_PROTOCOL);
				
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
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							sdPayload.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT_KEY)) {
							sdPayload.setEndpoint(scObject.getString(DossierStatisticConstants.SERVICE_DOMAIN_ENDPOINT_KEY));
						}						
					}
				}
				_log.debug("STATISTICS CALL SERVICE DOMAIN: " + (System.currentTimeMillis() - startTime) + " ms");;
				ServiceDomainResponse serviceDomainResponse = null;
				if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
					serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
				}
				else {
					serviceDomainResponse = StatisticDataUtil.getLocalServiceDomain(sdPayload);
				}
				_log.debug("STATISTICS CALL SERVICE DOMAIN END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;
				
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
						if (scObject.has(DossierStatisticConstants.PASSWORD_KEY)) {
							payload.setPassword(scObject.getString(DossierStatisticConstants.PASSWORD_KEY));
						}
						if (scObject.has(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY)) {
							payload.setEndpoint(scObject.getString(DossierStatisticConstants.DOSSIER_ENDPOINT_KEY));
						}						
					}
				}
				
				int monthCurrent = LocalDate.now().getMonthValue();
//				int monthCurrent = 4;
				int yearCurrent = LocalDate.now().getYear();
				Map<Integer, Boolean> mapFlagCurrent = new HashMap<>();
				for (int month = 1; month <= monthCurrent; month ++) {
					boolean flagStatistic = true;
					if (month < monthCurrent) {
						_log.debug("STATISTICS CALCULATE ONE MONTH SITE: " + site.getName(Locale.getDefault()) + " " + (System.currentTimeMillis() - startTime) + " ms");;
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
									engineUpdateAction, serviceDomainResponse, calculateData);
								calculateDatas.put(yearCurrent, calculateData);
							}
							catch (Exception e) {
								_log.debug(e);
							}
						}
						_log.debug("STATISTICS CALCULATE ONE MONTH SITE : " + site.getName(Locale.getDefault()) + " END TIME " + (System.currentTimeMillis() - startTime) + " ms");;
					} else {
						try {
//							Map<Integer, Map<String, DossierStatisticData>> calculateData = new HashMap<>();
							processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
								engineUpdateAction, serviceDomainResponse, calculateData);
							calculateDatas.put(yearCurrent, calculateData);
						}
						catch (Exception e) {
							_log.debug(e);
						}
					}
					mapFlagCurrent.put(month, flagStatistic);
				}

				//Recalculate data				
				//TODO: Calculator again year ago
				int lastYear = LocalDate.now().getYear() - 1;
				boolean flagLastYear = true;
				Map<Integer, Boolean> mapFlagPrev = new HashMap<>();
				Map<Integer, Map<String, DossierStatisticData>> calculateLastData = new HashMap<>();
				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
							.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear, true);
					if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
						flagLastYear = false;
					}
					if (flagLastYear) {
						try {
							processUpdateStatistic(site.getGroupId(), lastMonth, lastYear, payload,
								engineUpdateAction, serviceDomainResponse, calculateLastData);
							calculateDatas.put(lastYear, calculateLastData);
						}
						catch (Exception e) {
							_log.debug(e);
						}
					}
					mapFlagPrev.put(lastMonth, flagLastYear);
				}

//				List<OpencpsDossierStatistic> allSiteDatas = OpencpsDossierStatisticLocalServiceUtil.findByG(site.getGroupId());
//				if (allSiteDatas.size() == 0 && site.getGroupId() == 52737) {
//					System.out.println("STATISTIC SIZE 0: " + site.getGroupId());
//				}
				for (int month = 1; month <= monthCurrent; month ++) {
					if (mapFlagCurrent.get(month)) {						
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, yearCurrent);
//						}
//						catch (Exception e) {
//							
//						}
						if (calculateDatas.get(yearCurrent) != null &&
								calculateDatas.get(yearCurrent).get(month) != null) {
//						if (calculateData.get(month) != null) {
							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
//							_log.debug("DOSSIER CAL MEMORY SIZE CURRENT: " + calculateDatas.get(yearCurrent).get(month).size());
//							statisticEngineUpdate.updateStatisticData(calculateDatas.get(yearCurrent).get(month), allSiteDatas);
							statisticEngineUpdate.updateStatisticData(calculateData.get(month));
						}
					}
				}			
				
				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					if (mapFlagPrev.get(lastMonth)) {
						if (calculateDatas.get(lastYear) != null &&
								calculateDatas.get(lastYear).get(lastMonth) != null) {
//							statisticEngineUpdate.updateStatisticData(calculateDatas.get(lastYear).get(lastMonth), allSiteDatas);
							statisticEngineUpdate.updateStatisticData(calculateDatas.get(lastYear).get(lastMonth));
						}
					}
				}			
	
				/* Update summary */
				//Delete record
//				try {
//					engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, LocalDate.now().getYear());
//				}
//				catch (Exception e) {
//					
//				}
				//
				StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
				
				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear());
				//TODO: Calculator again last year
				//Delete record
//				try {
//					engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, lastYear);
//				}
//				catch (Exception e) {
//					
//				}
				//
				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear);
	
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
		_log.debug("STATISTICS END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

		isRunningDossier = false;
	}

	private void processUpdateStatistic(long groupId, int month, int year, GetDossierRequest payload,
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
//											
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
//											
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
//								
//							}
						}
						
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();

						Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

						Date firstDay = StatisticUtils.getFirstDay(month, year);
						Date lastDay = StatisticUtils.getLastDay(month, year);
						engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, false);
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
//										
//								}
							}
						}	
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//						}
//						catch (NoSuchOpencpsDossierStatisticException e) {
//							
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
//									
//							}
						}
					}
//					try {
//						engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//					}
//					catch (Exception e) {
//						
//					}
				}
			}
			else {
//				try {
//					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//				}
//				catch (Exception e) {
//					
//				}
			}
			
		}
		else {
			DossierActions actions = new DossierActionsImpl();
			Sort[] sorts = null;
			sorts = new Sort[] { SortFactoryUtil.create(DossierTerm.CREATE_DATE + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean("true")) };
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			if (payload.isCalculate()) {
				params.put(DossierTerm.YEAR, year);
				params.put(DossierTerm.MONTH, month);				
			}
			else {
				if (Validator.isNotNull(payload.getGovAgencyCode())) {
					params.put("agency", payload.getGovAgencyCode());
				}
				if (Validator.isNotNull(payload.getFromStatisticDate())) {
					params.put("fromStatisticDate", payload.getFromStatisticDate());
				}
				if (Validator.isNotNull(payload.getToStatisticDate())) {
					params.put("toStatisticDate", payload.getToStatisticDate());
				}				
			}
			params.put("top", "statistic");
			
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
			long companyId = company.getCompanyId(); 
			
			JSONObject jsonData = actions.getDossiers(-1, companyId, groupId, params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new ServiceContext());
			List<Document> datas = (List<Document>) jsonData.get("data");
			List<GetDossierData> dossierData = new ArrayList<>();
			_log.debug("GET DOSSIER SIZE: " + datas.size());
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
				
				dossierData.add(model);
			}
			
			//GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
			//if (dossierResponse != null) {
				//List<GetDossierData> dossierData = dossierResponse.getData();
	//			List<GetDossierData> dossierData = new ArrayList<>();
				
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
//											
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
//											
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
//								
//							}
						}
						
						StatisticEngineFetch engineFetch = new StatisticEngineFetch();
	
						Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();
	
						Date firstDay = StatisticUtils.getFirstDay(month, year);
						Date lastDay = StatisticUtils.getLastDay(month, year);
						engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, false);
	//					StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
	//					
	//					statisticEngineUpdate.updateStatisticData(statisticData);	
						calculateData.put(month, statisticData);
					}
					else {
						List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
						if (serviceDomainData != null) {
							for (ServiceDomainData sdd : serviceDomainData) {
//								try {
//									engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
//								} catch (NoSuchOpencpsDossierStatisticException e) {
//										
//								}
							}
						}	
//						try {
//							engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
//						}
//						catch (NoSuchOpencpsDossierStatisticException e) {
//							
//						}
					}
				}
	//			else {
	//				List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
	//				if (serviceDomainData != null) {
	//					for (ServiceDomainData sdd : serviceDomainData) {
	//						try {
	//							engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
	//						} catch (NoSuchOpencpsDossierStatisticException e) {
	//								
	//						}
	//					}
	//				}
	//				try {
	//					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
	//				}
	//				catch (Exception e) {
	//					
	//				}
	//			}
			//}
			/*
			else {
				try {
					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
				}
				catch (Exception e) {
					
				}
			}
			*/
		}
	}

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
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 10, TimeUnit.MINUTE);

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
	    
	    return StorageType.MEMORY_CLUSTERED;
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
