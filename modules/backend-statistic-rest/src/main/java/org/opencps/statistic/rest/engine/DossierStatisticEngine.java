package org.opencps.statistic.rest.engine;

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
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

@Component(immediate = true, service = DossierStatisticEngine.class)
public class DossierStatisticEngine extends BaseSchedulerEntryMessageListener {

	private final static Logger LOG = LoggerFactory.getLogger(DossierStatisticEngine.class);

	private SchedulerEngineHelper _schedulerEngineHelper;

	public static final int GROUP_TYPE_SITE = 1;
	
	private OpencpsCallRestFacade<GetDossierRequest, GetDossierResponse> callDossierRestService = new OpencpsCallDossierRestFacadeImpl();
	private OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();

	@Override
	protected void doReceive(Message message) throws Exception {

		//LOG.info("START getDossierStatistic(): " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		
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

		for (Group site : sites) {
			
//			LOG.info("START getDossierStatistic(): " + site.getGroupId());

//			GetDossierResponse dossierResponse = new GetDossierResponse();
			
			ServiceDomainRequest sdPayload = new ServiceDomainRequest();
			sdPayload.setGroupId(site.getGroupId());
			
			ServiceDomainResponse serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
			
			GetDossierRequest payload = new GetDossierRequest();
			
			payload.setGroupId(site.getGroupId());
			int monthCurrent = LocalDate.now().getMonthValue();
			for (int month = 1; month <= monthCurrent; month ++) {
				boolean flagStatistic = false;
				if (month < monthCurrent) {
					List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
							.getDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());
					if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
						for (OpencpsDossierStatistic dossierStatistic : dossierStatisticList) {
							boolean reporting = dossierStatistic.getReporting();
							if (!reporting) {
								flagStatistic = true;
								break;
							}
						}
					} else {
						flagStatistic = true;
					}
					if (flagStatistic) {
						processUpdateStatistic(site.getGroupId(), month, LocalDate.now().getYear() , payload,
								engineUpdateAction, serviceDomainResponse);
					}
				} else {
					processUpdateStatistic(site.getGroupId(), month, LocalDate.now().getYear(), payload,
							engineUpdateAction, serviceDomainResponse);
				}
			}
				/////////////////////////////////
//				engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());
//				
//				payload.setMonth(Integer.toString(month));
//				payload.setYear(Integer.toString(LocalDate.now().getYear()));
//				payload.setCalculate(true);
//				
//				GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
//				if (dossierResponse != null) {
//					List<GetDossierData> dossierData = dossierResponse.getData();
//					if (dossierData != null) {
//						//LOG.info("***** " + site.getGroupId() + source.size());
//						
//						if(dossierData.size() > 0) {
//							if (serviceDomainResponse != null) {
//								List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
//								if (serviceDomainData != null) {
//									for (ServiceDomainData sdd : serviceDomainData) {
//										boolean existsDomain = false;
//										for (GetDossierData dd : dossierData) {
//											if (dd.getDomainCode().equals(sdd.getItemCode())) {
//												existsDomain = true;
//												break;
//											}
//										}
//										if (existsDomain) {
//											
//										}
//										else {
//											try {
//												engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), sdd.getItemCode(), month, LocalDate.now().getYear());
//											} catch (NoSuchOpencpsDossierStatisticException e) {
//												
//											}
//										}
//									}
//									for (GetDossierData dd : dossierData) {
//										boolean existsDomain = false;
//										for (ServiceDomainData sdd : serviceDomainData) {
//											if (dd.getDomainCode().equals(sdd.getItemCode())) {
//												existsDomain = true;
//												break;
//											}											
//										}
//										if (existsDomain) {
//											
//										}
//										else {
//											try {
//												engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), dd.getDomainCode(), month, LocalDate.now().getYear());
//											} catch (NoSuchOpencpsDossierStatisticException e) {
//												
//											}
//										}
//									}
//								}
//							}
//							else {
//								engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());								
//							}
//							
//							StatisticEngineFetch engineFetch = new StatisticEngineFetch();
//	
//							Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();
//	
//							engineFetch.fecthStatisticData(site.getGroupId(), statisticData, dossierData, month);
//							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
//							
//							statisticEngineUpdate.updateStatisticData(statisticData);														
//						}
//						else {
//							List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
//							if (serviceDomainData != null) {
//								for (ServiceDomainData sdd : serviceDomainData) {
//									try {
//										engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), sdd.getItemCode(), month, LocalDate.now().getYear());
//									} catch (NoSuchOpencpsDossierStatisticException e) {
//											
//									}
//								}
//							}	
//							engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());
//						}
//					}
//					else {
//						List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
//						if (serviceDomainData != null) {
//							for (ServiceDomainData sdd : serviceDomainData) {
//								try {
//									engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), sdd.getItemCode(), month, LocalDate.now().getYear());
//								} catch (NoSuchOpencpsDossierStatisticException e) {
//										
//								}
//							}
//						}											
//						engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());
//					}
//				}
//				else {
//					engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());					
//				}
//			Optional<List<GetDossierData>> dossierData = Optional.ofNullable(dossierResponse.getData());
//			
//			dossierData.ifPresent(source -> {
//				
//				LOG.info("***** " + site.getGroupId() + source.size());
//				
//				if(source.size() > 0) {
//					StatisticEngineFetch engineFetch = new StatisticEngineFetch();
//					
//					Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();
//
//					engineFetch.fecthStatisticData(site.getGroupId(), statisticData, source);
//					
//					StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
//					
//					statisticEngineUpdate.updateStatisticData(statisticData);
//					
//				}
//				
//			});
			//TODO: Calculator again year ago
			int lastYear = LocalDate.now().getYear() - 1;
			boolean flagLastYear = false;
			for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
				List<OpencpsDossierStatistic> dossierStatisticList = engineUpdateAction
						.getDossierStatisticByMonthYear(site.getGroupId(), lastMonth, lastYear);
				if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
					for (OpencpsDossierStatistic dossierStatistic : dossierStatisticList) {
						boolean reporting = dossierStatistic.getReporting();
						if (!reporting) {
							flagLastYear = true;
							break;
						}
					}
				} else {
					flagLastYear = true;
				}
				if (flagLastYear) {
					processUpdateStatistic(site.getGroupId(), lastMonth, lastYear, payload,
							engineUpdateAction, serviceDomainResponse);
				}
			}

			/* Update summary */
			//Delete record
			engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, LocalDate.now().getYear());
			//
			StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
			
			statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear());
			//TODO: Calculator again last year
			//Delete record
			engineUpdateAction.removeDossierStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, lastYear);
			//
			statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear);

		}

	}

	private void processUpdateStatistic(long groupId, int month, int year, GetDossierRequest payload,
			StatisticEngineUpdateAction engineUpdateAction, ServiceDomainResponse serviceDomainResponse)
			throws Exception {
		engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
		
		payload.setMonth(Integer.toString(month));
		payload.setYear(Integer.toString(year));
		payload.setCalculate(true);
		
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
						engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
					}
					
					StatisticEngineFetch engineFetch = new StatisticEngineFetch();

					Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();

					Date firstDay = StatisticUtils.getFirstDay(month, year);
					Date lastDay = StatisticUtils.getLastDay(month, year);
					engineFetch.fecthStatisticData(groupId, statisticData, dossierData, firstDay, lastDay, false);
					StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
					
					statisticEngineUpdate.updateStatisticData(statisticData);														
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
					engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
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
				engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
			}
		}
		else {
			engineUpdateAction.removeDossierStatisticByMonthYear(groupId, month, year);
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
