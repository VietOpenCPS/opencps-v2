package org.opencps.statistic.rest.engine;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.*;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticKey;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
import org.opencps.statistic.rest.util.DossierSyncStatisticUtil;
import org.opencps.statistic.service.OpencpsDossierStatisticLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = DossierSyncStatisticScheduler.class)
public class DossierSyncStatisticScheduler extends BaseMessageListener {
	protected Log _log = LogFactoryUtil.getLog(DossierSyncStatisticScheduler.class);

	public static final int GROUP_TYPE_SITE = 1;
	private volatile boolean isRunningStatisticSync = false;

	private  final Boolean CALCULATE_DOSSIER_STATISTIC_ENABLE = Validator.isNotNull(PropsUtil.get("org.opencps.statistic.sync.enable"))
			? Boolean.valueOf(PropsUtil.get("org.opencps.statistic.sync.enable")) : false;

	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("START STATISTIC DOSSIER STATISTIC: " + isRunningStatisticSync);
		if (!isRunningStatisticSync && CALCULATE_DOSSIER_STATISTIC_ENABLE) {
			isRunningStatisticSync = true;
		}
		else {
			return;
		}
		long startTime = System.currentTimeMillis();
		Date nowLog = new Date();
		try {
			DossierSyncStatisticUtil dossierSyncStatisticUtil = new DossierSyncStatisticUtil();
			dossierSyncStatisticUtil.processSyncStatistic();

		}
		catch (Exception e) {
			_log.error(e);
		}
		_log.info("END TRACE LOG CALCULATOR TIME: " + nowLog);
		_log.info("CALCULATOR END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

		isRunningStatisticSync = false;
	}



	/**
	 * activate: Called whenever the properties for the component change (ala Config
	 * Admin) or OSGi is activating the component.
	 *
	 * @param properties The properties map from Config Admin.
	 * @throws SchedulerException in case of error.
	 */
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws SchedulerException {


		String cronExpression = Validator.isNotNull(PropsUtil.get("org.opencps.statistic.sync.cron.expression"))
				? String.valueOf(PropsUtil.get("org.opencps.statistic.sync.cron.expression")) : _DEFAULT_CRON_EXPRESSION;

		_log.info("--cronExpression:"+cronExpression);

		if (_initialized) {
			deactivate();
		}

		if(Validator.isNotNull(cronExpression) && CALCULATE_DOSSIER_STATISTIC_ENABLE) {
			String listenerClass = getClass().getName();

			Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null,
					cronExpression);

			_schedulerEntryImpl = new SchedulerEntryImpl(getClass().getName(), jobTrigger);
			_schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);


			_schedulerEngineHelper.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
			_initialized = true;
		}

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
	 * OSGi will start a component once all of it's dependencies are satisfied.
	 * However, there are times where you want to hold off until the portal is
	 * completely ready to go.
	 *
	 * This reference declaration is waiting for the ModuleServiceLifecycle's
	 * PORTAL_INITIALIZED component which will not be available until, surprise
	 * surprise, the portal has finished initializing.
	 *
	 * With this reference, this component activation waits until portal
	 * initialization has completed.
	 *
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

	//private final String _DEFAULT_CRON_EXPRESSION = "0 0/3 * 1/1 * ? *";
	private final String _DEFAULT_CRON_EXPRESSION = "0 1 1 1/1 * ? *";
	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;
}
