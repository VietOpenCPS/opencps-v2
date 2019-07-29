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
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.statistic.rest.dto.GetVotingResultData;
import org.opencps.statistic.rest.dto.GetVotingResultRequest;
import org.opencps.statistic.rest.dto.GetVotingResultResponse;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.dto.ServiceDomainRequest;
import org.opencps.statistic.rest.dto.ServiceDomainResponse;
import org.opencps.statistic.rest.dto.VotingResultStatisticData;
import org.opencps.statistic.rest.engine.service.StatisticEngineFetch;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.engine.service.StatisticSumYearService;
import org.opencps.statistic.rest.engine.service.StatisticUtils;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceDomainRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallVotingRestFacadeImpl;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.StatisticDataUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = VotingStatisticScheduler.class)
public class VotingStatisticScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	//private final static Log _log = LogFactoryUtil.getLog(VotingStatisticScheduler.class);
	protected Log _log = LogFactoryUtil.getLog(VotingStatisticScheduler.class);
	
	public static final int GROUP_TYPE_SITE = 1;
	OpencpsCallRestFacade<GetVotingResultRequest, GetVotingResultResponse> callVotingResultService = new OpencpsCallVotingRestFacadeImpl();

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		try {
			//System.out.println("START getVotingStatistic(): " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
			
			OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();
			
			Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
	
			List<Group> groupList = GroupLocalServiceUtil.getCompanyGroups(company.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);
			StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();
			List<Group> sites = new ArrayList<Group>();
	
			if (groupList != null && groupList.size() > 0) {
				for (Group group : groupList) {
					if (group.getType() == GROUP_TYPE_SITE && group.isSite()) {
						sites.add(group);
					}
				}
			}
	
			if (sites != null && sites.size() > 0) {
				for (Group site : sites) {
					List<ServerConfig> lstScs =  ServerConfigLocalServiceUtil.getByProtocol(site.getGroupId(), DossierStatisticConstants.STATISTIC_PROTOCOL);
	
					/** Get dictItem by collectionCode = "SERVICE_DOMAIN" - START */
	
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

					ServiceDomainResponse serviceDomainResponse = null;
					if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
						serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
					}
					else {
						serviceDomainResponse = StatisticDataUtil.getLocalServiceDomain(sdPayload);
					}

					/** Get dictItem by collectionCode = "SERVICE_DOMAIN" - END */
	
					// Get dossier by groupId - START
					GetVotingResultRequest payload = new GetVotingResultRequest();
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
							if (scObject.has(DossierStatisticConstants.VOTING_ENDPOINT_KEY)) {
								payload.setEndpoint(scObject.getString(DossierStatisticConstants.VOTING_ENDPOINT_KEY));
							}						
						}
					}
					
					int monthCurrent = LocalDate.now().getMonthValue();
					int yearCurrent = LocalDate.now().getYear();
					for (int month = 1; month <= monthCurrent; month ++) {
						processUpdateStatistic(site.getGroupId(), month, yearCurrent, payload,
								engineUpdateAction, serviceDomainResponse);
					}
	
					//TODO: Calculator again year ago
					int lastYear = LocalDate.now().getYear() - 1;
					for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
						processUpdateStatistic(site.getGroupId(), lastMonth, lastYear, payload,
								engineUpdateAction, serviceDomainResponse);
					}
					// System.out.println("END getVotingStatistic(): " +
					// LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	
					/* Update summary */
					// Delete record
					engineUpdateAction.removeVotingStatisticByYear(site.getCompanyId(), site.getGroupId(), 0,
							LocalDate.now().getYear());
					StatisticSumYearService statisticSumYearService = new StatisticSumYearService();
					statisticSumYearService.votingCalculateSumYear(site.getCompanyId(), site.getGroupId(), LocalDate.now().getYear());
					//
					//TODO: Calculator again last year
					//Delete record
					engineUpdateAction.removeVotingStatisticByYear(site.getCompanyId(), site.getGroupId(), 0, lastYear);
					
					statisticSumYearService.votingCalculateSumYear(site.getCompanyId(), site.getGroupId(), lastYear);
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		isRunning = false;
	}

	private void processUpdateStatistic(long groupId, int month, int year, GetVotingResultRequest payload,
			StatisticEngineUpdateAction engineUpdateAction, ServiceDomainResponse serviceDomainResponse)
			throws Exception{
		// Delete dossier statistic by month/year
		engineUpdateAction.removeVotingStatisticByMonthYear(groupId, month, year);

		payload.setMonth(Integer.toString(month));
		payload.setYear(Integer.toString(year));
		payload.setClassName("dossier");
		payload.setStart(QueryUtil.ALL_POS);
		payload.setEnd(QueryUtil.ALL_POS);
		// Check calculate = true => month
		payload.setCalculate(true);
		// System.out.println("payload: "+payload);

		GetVotingResultResponse votingResultResponse = null;
		if (OpenCPSConfigUtil.isStatisticMultipleServerEnable()) {
			votingResultResponse = callVotingResultService.callRestService(payload);
		}
		else {
			votingResultResponse = StatisticDataUtil.getLocalVotingResponse(payload);
		}
		// System.out.println("votingResultResponse: "+votingResultResponse);
		// System.out.println("getGroupId: "+payload.getGroupId());
		if (votingResultResponse != null) {
			// System.out.println("votingResultResponse: "+votingResultResponse.getTotal());
			List<GetVotingResultData> votingData = votingResultResponse.getData();
			// System.out.println("votingData: "+votingData);
			if (votingData != null && votingData.size() > 0) {
				if (serviceDomainResponse != null) {
					List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
					if (serviceDomainData != null) {
						for (ServiceDomainData sdd : serviceDomainData) {
							boolean existsDomain = false;
							for (GetVotingResultData result : votingData) {
								if (result.getDomain().equals(sdd.getItemCode())) {
									existsDomain = true;
									break;
								}
							}
							if (!existsDomain) {
								engineUpdateAction.removeVotingStatisticByD_M_Y(groupId,
										sdd.getItemCode(), month, LocalDate.now().getYear());
							}
						}
					}
				} else {
					engineUpdateAction.removeVotingStatisticByMonthYear(groupId, month,
							LocalDate.now().getYear());
				}

				StatisticEngineFetch engineFetch = new StatisticEngineFetch();
				// Calculate
				Date firstDay = StatisticUtils.getFirstDay(month, year);
				Date lastDay = StatisticUtils.getLastDay(month, year);
				Map<String, VotingResultStatisticData> statisticData = engineFetch.getStatisticVotingData(
						groupId, votingData, firstDay, lastDay);

				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();

				statisticEngineUpdate.updateVotingStatisticData(statisticData);
			} else {
				List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
				if (serviceDomainData != null) {
					for (ServiceDomainData sdd : serviceDomainData) {
						engineUpdateAction.removeVotingStatisticByD_M_Y(groupId,
								sdd.getItemCode(), month, year);
					}
				}
				engineUpdateAction.removeVotingStatisticByMonthYear(groupId, month, year);
			}
		} else {
			engineUpdateAction.removeVotingStatisticByMonthYear(groupId, month, year);
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
