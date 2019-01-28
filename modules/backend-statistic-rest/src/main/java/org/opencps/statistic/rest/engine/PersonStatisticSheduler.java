package org.opencps.statistic.rest.engine;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.GetPersonData;
import org.opencps.statistic.rest.dto.GetPersonRequest;
import org.opencps.statistic.rest.dto.GetPersonResponse;
import org.opencps.statistic.rest.dto.PersonStatisticData;
import org.opencps.statistic.rest.dto.ServiceDomainData;
import org.opencps.statistic.rest.engine.service.StatisticEngineFetch;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.engine.service.StatisticSumYearService;
import org.opencps.statistic.rest.engine.service.StatisticUtils;
import org.opencps.statistic.rest.facade.OpencpsCallPersonRestFacadeImpl;
import org.opencps.statistic.rest.facade.OpencpsCallRestFacade;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = PersonStatisticSheduler.class)
public class PersonStatisticSheduler extends BaseSchedulerEntryMessageListener {
	private static volatile boolean isRunning = false;
	private final static Logger LOG = LoggerFactory.getLogger(PersonStatisticSheduler.class);

	private SchedulerEngineHelper _schedulerEngineHelper;

	public static final int GROUP_TYPE_SITE = 1;
	OpencpsCallRestFacade<GetPersonRequest, GetPersonResponse> callPersonResultService = new OpencpsCallPersonRestFacadeImpl();

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		//OpencpsCallRestFacade<ServiceDomainRequest, ServiceDomainResponse> callServiceDomainService = new OpencpsCallServiceDomainRestFacadeImpl();
		
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

		for (Group site : sites) {

			GetPersonRequest payload = new GetPersonRequest();
			
			payload.setGroupId(site.getGroupId());
			int monthCurrent = LocalDate.now().getMonthValue();
			int yearCurrent = LocalDate.now().getYear();
			for (int month = 1; month <= monthCurrent; month ++) {
				processUpdatePersonStatistic(site.getGroupId(), month, yearCurrent, payload,
						engineUpdateAction);
			}
			//TODO: Calculator again year ago
			int lastYear = LocalDate.now().getYear() - 1;
			for (int lastMonth = 1; lastMonth <= 12; lastMonth++) {
				processUpdatePersonStatistic(site.getGroupId(), lastMonth, lastYear, payload,
						engineUpdateAction);
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
		isRunning = false;
	}

	private void processUpdatePersonStatistic(long groupId, int month, int year, GetPersonRequest payload,
			StatisticEngineUpdateAction engineUpdateAction) throws Exception {
		//
		engineUpdateAction.removePersonStatisticByMonthYear(groupId, month, year);
		
		payload.setMonth(Integer.toString(month));
		payload.setYear(Integer.toString(year));
		payload.setClassName("employee");
		// Check calculate = true => month
		payload.setCalculate(true);
		
		GetPersonResponse personResponse = callPersonResultService.callRestService(payload);
		if (personResponse != null) {
			List<GetPersonData> personDataList = personResponse.getData();
			if (personDataList != null && personDataList.size() > 0) {
				//LOG.info("***** " + site.getGroupId() + source.size());
				
				StatisticEngineFetch engineFetch = new StatisticEngineFetch();
				Date firstDay = StatisticUtils.getFirstDay(month, year);
				Date lastDay = StatisticUtils.getLastDay(month, year);
				// Calculate
				Map<String, PersonStatisticData> statisticData = engineFetch.getStatisticPersonData(
						groupId, personDataList, firstDay, lastDay);
				
				StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
				statisticEngineUpdate.updatePersonStatisticData(statisticData);														
			}
			else {
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
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 3, TimeUnit.MINUTE));
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
