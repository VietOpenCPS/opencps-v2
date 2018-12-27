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
import java.util.List;
import java.util.Map;

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
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.opencps.statistic.rest.facade.OpencpsCallServiceDomainRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallVotingRestFacadeImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = VotingStatisticScheduler.class)
public class VotingStatisticScheduler extends BaseSchedulerEntryMessageListener {

	private final static Log _log = LogFactoryUtil.getLog(DossierStatisticEngine.class);

	private SchedulerEngineHelper _schedulerEngineHelper;

	public static final int GROUP_TYPE_SITE = 1;
	
	@Override
	protected void doReceive(Message message) throws Exception {

		//System.out.println("START getVotingStatistic(): " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		OpencpsCallRestFacade<GetVotingResultRequest, GetVotingResultResponse> callVotingResultService = new OpencpsCallVotingRestFacadeImpl();
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
				if (site.getGroupId() == 35818) {

				/** Get dictItem by collectionCode = "SERVICE_DOMAIN" - START */
				ServiceDomainRequest sdPayload = new ServiceDomainRequest();
				sdPayload.setGroupId(site.getGroupId());
				ServiceDomainResponse serviceDomainResponse = callServiceDomainService.callRestService(sdPayload);
				/** Get dictItem by collectionCode = "SERVICE_DOMAIN" - END */

				// Get dossier by groupId - START
				GetVotingResultRequest payload = new GetVotingResultRequest();
				payload.setGroupId(site.getGroupId());
				for (int month = 1; month <= LocalDate.now().getMonthValue(); month++) {
					// Delete dossier statistic by month/year
					engineUpdateAction.removeVotingStatisticByMonthYear(site.getGroupId(), month,
							LocalDate.now().getYear());

					payload.setMonth(Integer.toString(LocalDate.now().getMonthValue()));
					payload.setYear(Integer.toString(LocalDate.now().getYear()));
					payload.setClassName("dossier");
					// Check calculate = true => month
					payload.setCalculate(true);
					// System.out.println("payload: "+payload);

					GetVotingResultResponse votingResultResponse = callVotingResultService.callRestService(payload);
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
											engineUpdateAction.removeVotingStatisticByD_M_Y(site.getGroupId(),
													sdd.getItemCode(), month, LocalDate.now().getYear());
										}
									}
								}
							} else {
								engineUpdateAction.removeVotingStatisticByMonthYear(site.getGroupId(), month,
										LocalDate.now().getYear());
							}

							StatisticEngineFetch engineFetch = new StatisticEngineFetch();
							// Calculate
							Map<String, VotingResultStatisticData> statisticData = engineFetch.getStatisticVotingData(
									site.getGroupId(), votingData, LocalDate.now().getMonthValue());

							StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();

							statisticEngineUpdate.updateVotingStatisticData(statisticData);
						} else {
							List<ServiceDomainData> serviceDomainData = serviceDomainResponse.getData();
							if (serviceDomainData != null) {
								for (ServiceDomainData sdd : serviceDomainData) {
									engineUpdateAction.removeVotingStatisticByD_M_Y(site.getGroupId(),
											sdd.getItemCode(), month, LocalDate.now().getYear());
								}
							}
							engineUpdateAction.removeVotingStatisticByMonthYear(site.getGroupId(), month,
									LocalDate.now().getYear());
						}
					} else {
						engineUpdateAction.removeVotingStatisticByMonthYear(site.getGroupId(), month,
								LocalDate.now().getYear());
					}
				}
				// System.out.println("END getVotingStatistic(): " +
				// LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

				/* Update summary */
				// Delete record
				engineUpdateAction.removeVotingStatisticByYear(site.getCompanyId(), site.getGroupId(), 0,
						LocalDate.now().getYear());
				//
				StatisticSumYearService statisticSumYearService = new StatisticSumYearService();

				statisticSumYearService.votingCalculateSumYear(site.getCompanyId(), site.getGroupId());
			}
		}
		}
	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 2, TimeUnit.MINUTE));
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
