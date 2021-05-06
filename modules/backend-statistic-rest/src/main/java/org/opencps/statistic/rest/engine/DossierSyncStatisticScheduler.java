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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.statistic.model.OpencpsDossierStatistic;
import org.opencps.statistic.rest.dto.DossierStatisticData;
import org.opencps.statistic.rest.dto.DossierStatisticKey;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdate;
import org.opencps.statistic.rest.engine.service.StatisticEngineUpdateAction;
import org.opencps.statistic.rest.util.DossierStatisticConstants;
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
	// Time engine dossier
	private static int TIME_STATISTIC_CALCULATOR = Validator.isNotNull(PropsUtil.get("org.opencps.statistic.calculator"))
			? Integer.valueOf(PropsUtil.get("org.opencps.statistic.calculator")) : 30;
	
	private boolean enableJob = Validator.isNotNull(PropsUtil.get("opencps.sync.dossierstatistic.enable"))
			? GetterUtil.getBoolean(PropsUtil.get("opencps.sync.dossierstatistic.enable")) : true;
	
	@Override
	protected void doReceive(Message message) throws Exception {
		_log.info("Cau hinh sync dossierstatistic running: " + isRunningStatisticSync + ", enable: " + enableJob);
		if (!isRunningStatisticSync && enableJob) {
			isRunningStatisticSync = true;
		}
		else {
			return;
		}
		long startTime = System.currentTimeMillis();
		Date nowLog = new Date();
		try {
			_log.info("START TRACE LOG CALCULATOR TIME: " + nowLog);
			_log.info("START CALCULATOR TIME: " + (System.currentTimeMillis() - startTime) + " ms");
			
			List<ServerConfig> configList = ServerConfigLocalServiceUtil.getByServerAndProtocol("SERVER_STATISTIC_DVC", DossierStatisticConstants.STATISTIC_PROTOCOL);
			ServerConfig config = null;
			if (configList != null && configList.size() > 0) {
				config = configList.get(0);
				if (config == null) {
					return;
				}
			} else {
				_log.info("RETURN CALCULATOR END: " + (System.currentTimeMillis() - startTime) + " ms");
				return ;
			}

			JSONObject scObject = JSONFactoryUtil.createJSONObject(config.getConfigs());
			long groupId = 0;
			if (scObject != null) {
				if (scObject.has(Field.GROUP_ID)) {
					groupId = scObject.getLong(Field.GROUP_ID) > 0 ? scObject.getLong(Field.GROUP_ID) : 35417;
				}
			}
			StatisticEngineUpdateAction engineUpdateAction = new StatisticEngineUpdateAction();

			int[] reportArr = {0, 1};
			List<OpencpsDossierStatistic> statisticList = OpencpsDossierStatisticLocalServiceUtil.findByREPO_ARR(reportArr);
			Map<String, DossierStatisticKey> mapKey = null;
			DossierStatisticKey statisticKey = null;
			if (statisticList != null && statisticList.size() > 0) {
				mapKey = new HashMap<String, DossierStatisticKey>();
				for (OpencpsDossierStatistic opencpsDossierStatistic : statisticList) {
					StringBuilder sb = new StringBuilder();
					if (Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
						sb.append(opencpsDossierStatistic.getGovAgencyCode());
					} else {
						sb.append("all");
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
						sb.append(StringPool.AT);
						sb.append(opencpsDossierStatistic.getDomainCode());
					} else {
						sb.append(StringPool.AT);
						sb.append("all");
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getSystem())) {
						sb.append(StringPool.AT);
						sb.append(opencpsDossierStatistic.getSystem());
					} else {
						sb.append(StringPool.AT);
						sb.append("all");
					}
					if (Validator.isNotNull(opencpsDossierStatistic.getGroupAgencyCode())) {
						sb.append(StringPool.AT);
						sb.append(opencpsDossierStatistic.getGroupAgencyCode());
					} else {
						sb.append(StringPool.AT);
						sb.append("all");
					}
					//month and year
					sb.append(StringPool.AT);
					sb.append(opencpsDossierStatistic.getMonth());
					sb.append(StringPool.AT);
					sb.append(opencpsDossierStatistic.getYear());

					if (mapKey.isEmpty() || !mapKey.containsKey(sb.toString())
							|| (mapKey.containsKey(sb.toString()) && opencpsDossierStatistic.getReporting() == 0)) {
						statisticKey = new DossierStatisticKey();
						if (Validator.isNotNull(opencpsDossierStatistic.getGovAgencyCode())) {
							statisticKey.setGovAgencyCode(opencpsDossierStatistic.getGovAgencyCode());
						}
						if (Validator.isNotNull(opencpsDossierStatistic.getDomainCode())) {
							statisticKey.setDomainCode(opencpsDossierStatistic.getDomainCode());
						}
						if (Validator.isNotNull(opencpsDossierStatistic.getSystem())) {
							statisticKey.setSystem(opencpsDossierStatistic.getSystem());
						}
						if (Validator.isNotNull(opencpsDossierStatistic.getGroupAgencyCode())) {
							statisticKey.setGroupAgencyCode(opencpsDossierStatistic.getGroupAgencyCode());
						}
						if (Validator.isNotNull(opencpsDossierStatistic.getReporting())) {
							statisticKey.setReporting(opencpsDossierStatistic.getReporting());
						}
						statisticKey.setMonth(opencpsDossierStatistic.getMonth());
						statisticKey.setYear(opencpsDossierStatistic.getYear());
						//
						mapKey.put(sb.toString(), statisticKey);
					}
				}
			}

			Map<String, DossierStatisticData> mapStatistic = new HashMap<>();
			for (Map.Entry<String, DossierStatisticKey> entry : mapKey.entrySet()) {
				DossierStatisticKey objectKey = entry.getValue();
				List<OpencpsDossierStatistic> dossierStatisticList = OpencpsDossierStatisticLocalServiceUtil
						.getByNOT_G_M_Y_GOV_DOM_GRO_SYS(groupId, objectKey.getMonth(), objectKey.getYear(),
								objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
								objectKey.getSystem());
				//_log.info("mapKey.getKey: "+entry.getKey());
				//int size = dossierStatisticList != null ? dossierStatisticList.size() : -1;
				//_log.info("mapKey_dossierStatisticList: "+ size);
				//
				DossierStatisticData dossierStatistic = processCalStatistic(groupId, objectKey.getMonth(), objectKey.getYear(), objectKey.getGovAgencyCode(), objectKey.getDomainCode(), objectKey.getGroupAgencyCode(),
						objectKey.getSystem(), objectKey.getReporting(), dossierStatisticList);
				//
				if (dossierStatistic != null) {
					mapStatistic.put(entry.getKey(), dossierStatistic);
				}
			}
			// Convert Map to List jsonObject
			StatisticEngineUpdate statisticEngineUpdate = new StatisticEngineUpdate();
			List<JSONObject> lstDossierDataObjs = statisticEngineUpdate.convertStatisticDataList(mapStatistic);
//			//
			engineUpdateAction.updateStatistic(lstDossierDataObjs);
			
			// Update reporting from 1 to 2
//			List<OpencpsDossierStatistic> opencpsStatisticList = OpencpsDossierStatisticLocalServiceUtil.getListByReporting(1);
//			if (opencpsStatisticList != null && opencpsStatisticList.size() > 0) {
//				for (OpencpsDossierStatistic opencpsStatistic : opencpsStatisticList) {
//					opencpsStatistic.setReporting(2);
//					OpencpsDossierStatisticLocalServiceUtil.updateDossierStatistic(opencpsStatistic);
//				}
//			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		_log.info("END TRACE LOG CALCULATOR TIME: " + nowLog);
		_log.info("CALCULATOR END TIME: " + (System.currentTimeMillis() - startTime) + " ms");;

		isRunningStatisticSync = false;
	}

	private DossierStatisticData processCalStatistic(long groupId, int month, int year, String govAgencyCode, String domainCode,
			String groupGovAgencyCode, String system, int reporting,
			List<OpencpsDossierStatistic> dossierStatisticList) {
		DossierStatisticData dossierStatistic = null;
		int totalCount = 0;
		int deniedCount = 0;
		int cancelledCount = 0;
		int processCount = 0;
		int remainingCount = 0;
		int receivedCount = 0;
		int onlineCount = 0;
		int fromViaPostalCount = 0;
		int releaseCount = 0;
		int betimesCount = 0;
		int ontimeCount = 0;
		int overtimeCount = 0;
		int doneCount = 0;
		int releasingCount = 0;
		int unresolvedCount = 0;
		int processingCount = 0;
		int undueCount = 0;
		int overdueCount = 0;
		int ontimePercentage = 100;
		int overtimeInside = 0;
		int overtimeOutside = 0;
		int interoperatingCount = 0;
		int waitingCount = 0;
		int onegateCount = 0;
		int outsideCount = 0;
		int insideCount = 0;
		int viaPostalCount = 0;
		int saturdayCount = 0;
		int dossierOnline3Count = 0;
		int dossierOnline4Count = 0;
		int receiveDossierSatCount = 0;
		int releaseDossierSatCount = 0;
		long companyId = 0;
		int processingInAPeriodCount = 0;
		int releaseInAPeriodCount = 0;
		if (dossierStatisticList != null && dossierStatisticList.size() > 0) {
			dossierStatistic = new DossierStatisticData();
			for (OpencpsDossierStatistic opencpsDossierStatistic : dossierStatisticList) {
				//
				companyId = opencpsDossierStatistic.getCompanyId() > 0 ? opencpsDossierStatistic.getCompanyId() : 0; 
				//
				totalCount += opencpsDossierStatistic.getTotalCount();
				deniedCount += opencpsDossierStatistic.getDeniedCount();
				cancelledCount += opencpsDossierStatistic.getCancelledCount();
				processCount += opencpsDossierStatistic.getProcessCount();
				remainingCount += opencpsDossierStatistic.getRemainingCount();
				receivedCount += opencpsDossierStatistic.getReceivedCount();
				onlineCount += opencpsDossierStatistic.getOnlineCount();
				fromViaPostalCount += opencpsDossierStatistic.getFromViaPostalCount();
				releaseCount += opencpsDossierStatistic.getReleaseCount();
				betimesCount += opencpsDossierStatistic.getBetimesCount();
				ontimeCount += opencpsDossierStatistic.getOntimeCount();
				overtimeCount += opencpsDossierStatistic.getOvertimeCount();
				doneCount += opencpsDossierStatistic.getDoneCount();
				releasingCount += opencpsDossierStatistic.getReleasingCount();
				unresolvedCount += opencpsDossierStatistic.getUnresolvedCount();
				processingCount += opencpsDossierStatistic.getProcessingCount();
				undueCount += opencpsDossierStatistic.getUndueCount();
				overdueCount += opencpsDossierStatistic.getOverdueCount();
				overtimeInside += opencpsDossierStatistic.getOvertimeInside();
				overtimeOutside += opencpsDossierStatistic.getOvertimeOutside();
				interoperatingCount += opencpsDossierStatistic.getInteroperatingCount();
				waitingCount += opencpsDossierStatistic.getWaitingCount();
				onegateCount += opencpsDossierStatistic.getOnegateCount();
				outsideCount += opencpsDossierStatistic.getOutsideCount();
				insideCount += opencpsDossierStatistic.getInsideCount();
				viaPostalCount += opencpsDossierStatistic.getViaPostalCount();
				saturdayCount += opencpsDossierStatistic.getSaturdayCount();
				dossierOnline3Count += opencpsDossierStatistic.getDossierOnline3Count();
				dossierOnline4Count += opencpsDossierStatistic.getDossierOnline4Count();
				receiveDossierSatCount += opencpsDossierStatistic.getReceiveDossierSatCount();
				releaseDossierSatCount += opencpsDossierStatistic.getReleaseDossierSatCount();
				processingInAPeriodCount += opencpsDossierStatistic.getProcessingInAPeriodCount();
				releaseInAPeriodCount += opencpsDossierStatistic.getReleaseInAPeriodCount();
			}
			//
			if (releaseCount > 0) {
				ontimePercentage = (betimesCount + ontimeCount) * 100 / releaseCount;
			}
			//
			dossierStatistic.setCompanyId(companyId);
			dossierStatistic.setGroupId(groupId);
			dossierStatistic.setMonth(month);
			dossierStatistic.setYear(year);
			dossierStatistic.setSystem(system);
			dossierStatistic.setTotalCount(totalCount);
			dossierStatistic.setDeniedCount(deniedCount);
			dossierStatistic.setCancelledCount(cancelledCount);
			dossierStatistic.setProcessCount(processCount);
			dossierStatistic.setRemainingCount(remainingCount);
			dossierStatistic.setReceivedCount(receivedCount);
			dossierStatistic.setOnlineCount(onlineCount);
			dossierStatistic.setFromViaPostalCount(fromViaPostalCount);
			dossierStatistic.setReleaseCount(releaseCount);
			dossierStatistic.setBetimesCount(betimesCount);
			dossierStatistic.setOntimeCount(ontimeCount);
			dossierStatistic.setOvertimeCount(overtimeCount);
			dossierStatistic.setDoneCount(doneCount);
			dossierStatistic.setReleasingCount(releasingCount);
			dossierStatistic.setUnresolvedCount(unresolvedCount);
			dossierStatistic.setProcessingCount(processingCount);
			dossierStatistic.setUndueCount(undueCount);
			dossierStatistic.setOverdueCount(overdueCount);
			dossierStatistic.setOntimePercentage(ontimePercentage);
			dossierStatistic.setOvertimeInside(overtimeInside);
			dossierStatistic.setOvertimeOutside(overtimeOutside);
			dossierStatistic.setInteroperatingCount(interoperatingCount);
			dossierStatistic.setWaitingCount(waitingCount);
			dossierStatistic.setGovAgencyCode(govAgencyCode);
			dossierStatistic.setGovAgencyName(dossierStatisticList.get(0).getGovAgencyName());
			dossierStatistic.setDomainCode(domainCode);
			dossierStatistic.setDomainName(dossierStatisticList.get(0).getDomainName());
			dossierStatistic.setReporting(reporting);
			dossierStatistic.setOnegateCount(onegateCount);
			dossierStatistic.setOutsideCount(outsideCount);
			dossierStatistic.setInsideCount(insideCount);
			dossierStatistic.setViaPostalCount(viaPostalCount);
			dossierStatistic.setSaturdayCount(saturdayCount);
			dossierStatistic.setDossierOnline3Count(dossierOnline3Count);
			dossierStatistic.setDossierOnline4Count(dossierOnline4Count);
			dossierStatistic.setReceiveDossierSatCount(receiveDossierSatCount);
			dossierStatistic.setReleaseDossierSatCount(releaseDossierSatCount);
			dossierStatistic.setGroupAgencyCode(groupGovAgencyCode);
			dossierStatistic.setProcessingInAPeriodCount(processingInAPeriodCount);
			dossierStatistic.setReleaseInAPeriodCount(releaseInAPeriodCount);
		}
		//
		return dossierStatistic;
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
		String listenerClass = getClass().getName();
		Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null,
				TIME_STATISTIC_CALCULATOR, TimeUnit.MINUTE);

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

	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;
}
