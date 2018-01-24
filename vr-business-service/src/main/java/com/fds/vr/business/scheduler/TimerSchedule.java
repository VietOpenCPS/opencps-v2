package com.fds.vr.business.scheduler;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.fds.vr.business.action.VROutputDBActions;
import com.fds.vr.business.action.impl.VROutputDBActionsImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;

@Component(immediate = true, service = TimerSchedule.class)
public class TimerSchedule extends BaseSchedulerEntryMessageListener{

	@Override
	protected void doReceive(Message message) throws Exception {
		// TODO Auto-generated method stub
		_log.info("****OutputDB Timer****");

		VROutputDBActions test = new VROutputDBActionsImpl();
		boolean test111 = test.processOutputDB();
		// Get all dossier
//		List<Dossier> allDossierTimer = new ArrayList<Dossier>();

//		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		// This is TEMPORARY code for auto = timer, it need to optimize later
//		allDossierTimer = DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

//		DossierActions dossierActions = new DossierActionsImpl();

//		User systemUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(),
//				RESTFulConfiguration.SERVER_USER);

//		ServiceContext serviceContext = new ServiceContext();
//		serviceContext.setCompanyId(company.getCompanyId());
//		serviceContext.setUserId(systemUser.getUserId());

//		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

//		Sort[] sorts = new Sort[] { SortFactoryUtil.create("_sortable", Sort.STRING_TYPE, false) };
//
//		for (Dossier dossier : allDossierTimer) {
//			params.put(Field.GROUP_ID, String.valueOf(dossier.getGroupId()));
//			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossier.getDossierId()));
//			params.put(DossierTerm.REFERENCE_UID, String.valueOf(dossier.getReferenceUid()));
//			params.put(DossierActionTerm.AUTO, "timmer");
//
//			if (Validator.isNotNull(dossier.getDossierStatus())) {
//
//				JSONArray results = dossierActions.getNextActions(0l, company.getCompanyId(), dossier.getGroupId(),
//						params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);
//
//				int lenght = results.length();
//
//				if (lenght != 0) {
//
//					JSONObject content = results.getJSONObject(0);
//
//
//					ProcessAction processAction = null;
//
//					try {
//						processAction = (ProcessAction) content.get("processAction");
//
//						if (processAction != null && Validator.isNotNull(processAction.getAutoEvent())
//								&& processAction.getAutoEvent().contentEquals("timmer")) {
//
//							_log.info("AUTOEVENT_DOSSIER_ID" + dossier.getPrimaryKey());
//
//							long processActionId = processAction.getPrimaryKey();
//
//							if (processActionId != 0) {
//
//								ProcessAction action = ProcessActionLocalServiceUtil
//										.fetchProcessAction(processActionId);
//
//								if (Validator.isNotNull(action)) {
//									String perConditionStr = StringPool.BLANK;
//
//									if (Validator.isNotNull(action)) {
//										perConditionStr = action.getPreCondition();
//									}
//
//									boolean checkPreCondition = DossierMgtUtils.checkPreCondition(
//											StringUtil.split(perConditionStr, StringPool.COMMA), dossier);
//
//									_log.info("============================================= checkPreCondition "
//											+ checkPreCondition + "|DossierId = " + dossier.getDossierId() + "|split= "
//											+ perConditionStr);
//
//									if (checkPreCondition) {
//
//										// String subUsers = StringPool.BLANK;
//
//										dossierActions.doAction(dossier.getGroupId(), dossier.getDossierId(),
//												dossier.getReferenceUid(), processAction.getActionCode(),
//												processAction.getProcessActionId(), systemUser.getFullName(),
//												processAction.getActionName(), processAction.getAssignUserId(),
//												systemUser.getUserId(), StringPool.BLANK, serviceContext);
//									}
//
//								}
//
//							}
//
//						}
//
//					} catch (Exception e) {
//						// TODO: handle exception
//					}
//
//				}
//
//			}
//
//		}

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

	private SchedulerEngineHelper _schedulerEngineHelper;

	private Log _log = LogFactoryUtil.getLog(TimerSchedule.class);

}