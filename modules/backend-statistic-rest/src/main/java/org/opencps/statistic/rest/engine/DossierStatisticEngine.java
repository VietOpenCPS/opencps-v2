package org.opencps.statistic.rest.engine;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//import org.opencps.systemmgt.constants.SchedulerRecordTerm;
//import org.opencps.systemmgt.model.SchedulerRecord;
//import org.opencps.systemmgt.service.SchedulerRecordLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine extends BaseSchedulerEntryMessageListener {
	private static volatile boolean isRunning = false;
	
	//private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);
	protected Log _log = LogFactoryUtil.getLog(DossierStatisticEngine.class);
	
	private SchedulerEngineHelper _schedulerEngineHelper;

	public static final int GROUP_TYPE_SITE = 1;
	
	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		long startTime = System.currentTimeMillis();
		try {
			_log.debug("STATISTICS START TIME: " + (System.currentTimeMillis() - startTime) + " ms");;
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
				
	//			LOG.info("START getDossierStatistic(): " + site.getGroupId());
	
	//			GetDossierResponse dossierResponse = new GetDossierResponse();
				
				ServiceDomainRequest sdPayload = new ServiceDomainRequest();
				sdPayload.setGroupId(site.getGroupId());
				sdPayload.setStart(QueryUtil.ALL_POS);
				sdPayload.setEnd(QueryUtil.ALL_POS);
				
				_log.debug("STATISTICS CALL SERVICE DOMAIN: " + (System.currentTimeMillis() - startTime) + " ms");;
				ServiceDomainResponse serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
				_log.debug("STATISTICS CALL SERVICE DOMAIN END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;
				
				GetDossierRequest payload = new GetDossierRequest();
				
				payload.setGroupId(site.getGroupId());
				payload.setStart(QueryUtil.ALL_POS);
				payload.setEnd(QueryUtil.ALL_POS);
				
				int monthCurrent = LocalDate.now().getMonthValue();
				int yearCurrent = LocalDate.now().getYear();
				Map<Integer, Boolean> mapFlag = new HashMap<>();
				for (int month = 1; month <= monthCurrent; month ++) {
					boolean flagStatistic = true;
					if (month < monthCurrent) {
						_log.debug("STATISTICS CALCULATE ONE MONTH SITE: " + site.getName() + " " + (System.currentTimeMillis() - startTime) + " ms");;
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
								processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
									engineUpdateAction, serviceDomainResponse, calculateData);
							}
							catch (Exception e) {
							}
						}
						_log.debug("STATISTICS CALCULATE ONE MONTH SITE : " + site.getName() + " END TIME " + (System.currentTimeMillis() - startTime) + " ms");;
					} else {
						try {
							processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
								engineUpdateAction, serviceDomainResponse, calculateData);
						}
						catch (Exception e) {
						}
					}
					mapFlag.put(month, flagStatistic);
				}
				//Recalculate data
				for (int month = 1; month <= monthCurrent; month ++) {
					if (mapFlag.get(month)) {
						try {
							engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, yearCurrent);
						}
						catch (Exception e) {
							
						}
						if (calculateData.get(month) != null) {
							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
							
							statisticEngineUpdate.updateStatisticData(calculateData.get(month));
						}
					}
				}			
				
				//TODO: Calculator again year ago
				int lastYear = LocalDate.now().getYear() - 1;
				boolean flagLastYear = true;
				calculateData = new HashMap<>();
				mapFlag = new HashMap<>();
				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
							.getDossierStatisticByMonthYearAndReport(site.getGroupId(), lastMonth, lastYear, true);
					if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
						flagLastYear = false;
					}
					if (flagLastYear) {
						try {
							processUpdateStatistic(site.getGroupId(), lastMonth, lastYear, payload,
								engineUpdateAction, serviceDomainResponse, calculateData);
						}
						catch (Exception e) {
							
						}
					}
					mapFlag.put(lastMonth, flagLastYear);
				}
				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
				for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
					if (mapFlag.get(lastMonth)) {
						try {
							engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), lastMonth, lastYear);
						}
						catch (Exception e) {
							
						}
						if (calculateData.get(lastMonth) != null) {
							statisticEngineUpdate.updateStatisticData(calculateData.get(lastMonth));
						}
					}
				}			
	
				/* Update summary */
				//Delete record
				try {
					engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, LocalDate.now().getYear());
				}
				catch (Exception e) {
					
				}
				//
				StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
				
				statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear());
				//TODO: Calculator again last year
				//Delete record
				try {
					engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, lastYear);
				}
				catch (Exception e) {
					
				}
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
			e.printStackTrace();
		}
		_log.debug("STATISTICS END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

		isRunning = false;
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
									try {
										engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
									} catch (NoSuchOpencpsDossierStatisticException e) {
										
									}
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
									try {
										engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, dd.getDomainCode(), month, year);
									} catch (NoSuchOpencpsDossierStatisticException e) {
										
									}
								}
							}
						}
					}
					else {
						try {
							engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
						}
						catch (NoSuchOpencpsDossierStatisticException e) {
							
						}
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
							try {
								engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
							} catch (NoSuchOpencpsDossierStatisticException e) {
									
							}
						}
					}	
					try {
						engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
					}
					catch (NoSuchOpencpsDossierStatisticException e) {
						
					}
				}
			}
			else {
				List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
				if (serviceDomainData != null) {
					for (ServiceDomainData sdd : serviceDomainData) {
						try {
							engineUpdateAction.removeDossierStatisticByD_M_Y(groupId, sdd.getItemCode(), month, year);
						} catch (NoSuchOpencpsDossierStatisticException e) {
								
						}
					}
				}
				try {
					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
				}
				catch (Exception e) {
					
				}
			}
		}
		else {
			try {
				engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
			}
			catch (Exception e) {
				
			}
		}
	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 10, TimeUnit.MINUTE));
		_schedulerEngineHelper.register(this, schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {
	}
}
