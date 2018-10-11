package org.opencps.statistic.rest.engine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.rest.dto.DossierData;
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
			int month = LocalDate.now().getMonthValue();
			
				payload.setMonth(Integer.toString(month));
				payload.setYear(Integer.toString(LocalDate.now().getYear()));
				
				GetDossierResponse dossierResponse = callDossierRestService.callRestService(payload);
				if (dossierResponse != null) {
					List<GetDossierData> dossierData = dossierResponse.getData();
					if (dossierData != null) {
						
						//LOG.info("***** " + site.getGroupId() + source.size());
						
						if(dossierData.size() > 0) {
							if (serviceDomainResponse != null) {
								List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
								boolean existsDomain = false;
								if (serviceDomainData != null) {
									for (ServiceDomainData sdd : serviceDomainData) {
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
												engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), sdd.getItemCode(), month, LocalDate.now().getYear());
											} catch (NoSuchOpencpsDossierStatisticException e) {
												
											}
										}
									}
								}
							}
							
							StatisticEngineFetch engineFetch = new StatisticEngineFetch();
	
							Map<String, DossierStatisticData> statisticData = new HashMap<String, DossierStatisticData>();
	
							engineFetch.fecthStatisticData(site.getGroupId(), statisticData, dossierData, month);
							
							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
							
							statisticEngineUpdate.updateStatisticData(statisticData);														
						}
						else {
							List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
							if (serviceDomainData != null) {
								for (ServiceDomainData sdd : serviceDomainData) {
									try {
										engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), sdd.getItemCode(), month, LocalDate.now().getYear());
									} catch (NoSuchOpencpsDossierStatisticException e) {
											
									}
								}
							}	
							engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());
						}
					}
					else {
						List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
						if (serviceDomainData != null) {
							for (ServiceDomainData sdd : serviceDomainData) {
								try {
									engineUpdateAction.removeDossierStatisticByD_M_Y(site.getGroupId(), sdd.getItemCode(), month, LocalDate.now().getYear());
								} catch (NoSuchOpencpsDossierStatisticException e) {
										
								}
							}
						}											
						engineUpdateAction.removeDossierStatisticByMonthYear(site.getGroupId(), month, LocalDate.now().getYear());
					}
				}
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
			
			/* Update summary */
			StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
			
			statisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId());

		}

	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 5, TimeUnit.MINUTE));
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
