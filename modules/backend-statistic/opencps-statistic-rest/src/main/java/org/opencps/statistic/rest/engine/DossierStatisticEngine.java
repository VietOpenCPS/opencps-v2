package org.opencps.statistic.rest.engine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.opencps.statistic.rest.service.DossierStatisticSumYearService;
import org.opencps.statistic.rest.service.DossierStatisticUpdate;
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

	private DossierStatisticSumYearService dossierStatisticSumYearService = new DossierStatisticSumYearService();
	
	private int CUR_YEAR = LocalDate.now().getYear();
	
	public static final int TYPE_1_ALL_GOV_ALL_DOMAIN = 1;

	public static final int TYPE_2_EACH_GOV_ALL_DOMAIN_AND_EACH_GOV_EACH_DOMAIN = 2;

	public static final int TYPE_4_ALL_GOV_EACH_DOMAIN = 4;

	public static final int GROUP_TYPE_SITE = 1;

	@Override
	protected void doReceive(Message message) throws Exception {

		LOG.info("START getDossierStatistic(): " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		List<Group> groups = GroupLocalServiceUtil.getCompanyGroups(company.getCompanyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		List<Group> sites = new ArrayList<Group>();

		for (Group group : groups) {
			if (group.getType() == GROUP_TYPE_SITE && group.isSite()) {
				sites.add(group);
			}
		}

		for (Group site : sites) {
			
			DossierStatisticUpdate dossierStatisticUpdateType1 = new DossierStatisticUpdate();
			DossierStatisticUpdate dossierStatisticUpdateType2 = new DossierStatisticUpdate();
			DossierStatisticUpdate dossierStatisticUpdateType3 = new DossierStatisticUpdate();

			/* Run TYPE_1 */
			dossierStatisticUpdateType1.calulateDossierStatistic(TYPE_1_ALL_GOV_ALL_DOMAIN, site.getGroupId(), company.getCompanyId());

			/* Run TYPE_2 */
			dossierStatisticUpdateType2.calulateDossierStatistic(TYPE_2_EACH_GOV_ALL_DOMAIN_AND_EACH_GOV_EACH_DOMAIN, site.getGroupId(),
					company.getCompanyId());

			/* Run TYPE_3 */
			dossierStatisticUpdateType3.calulateDossierStatistic(TYPE_4_ALL_GOV_EACH_DOMAIN, site.getGroupId(), company.getCompanyId());
			
			dossierStatisticSumYearService.caculateSumYear(site.getCompanyId(), site.getGroupId(), CUR_YEAR);
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
