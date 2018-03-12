package org.opencps.dossiermgt.scheduler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = TimerScheduler.class)
public class TimerScheduler extends BaseSchedulerEntryMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		// TODO Auto-generated method stub
		_log.info("Invoke Timer****");
		// get all actions that has preCondition is "timer"

		// Get all dossier
		List<Dossier> allDossierTimer = new ArrayList<Dossier>();

		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		// This is TEMPORARY code for auto = timer, it need to optimize later
		allDossierTimer = DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		DossierActions dossierActions = new DossierActionsImpl();

		User systemUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(),
				RESTFulConfiguration.SERVER_USER);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());
		serviceContext.setUserId(systemUser.getUserId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		Sort[] sorts = new Sort[] { SortFactoryUtil.create("_sortable", Sort.STRING_TYPE, false) };

		for (Dossier dossier : allDossierTimer) {
			params.put(Field.GROUP_ID, String.valueOf(dossier.getGroupId()));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossier.getDossierId()));
			params.put(DossierTerm.REFERENCE_UID, String.valueOf(dossier.getReferenceUid()));
			params.put(DossierActionTerm.AUTO, "timmer");

			if (Validator.isNotNull(dossier.getDossierStatus())) {
				
				DossierActionsImpl actions = new DossierActionsImpl();
				
				JSONArray results = actions.getNextActionTimmer(0l, company.getCompanyId(), dossier.getGroupId(),
						params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

				int lenght = results.length();

				if (lenght != 0) {

					JSONObject content = results.getJSONObject(0);


					ProcessAction processAction = null;

					try {
						processAction = (ProcessAction) content.get("processAction");

						if (processAction != null && Validator.isNotNull(processAction.getAutoEvent())
								&& processAction.getAutoEvent().contentEquals("timmer")) {

							_log.info("AUTOEVENT_DOSSIER_ID" + dossier.getPrimaryKey());

							long processActionId = processAction.getPrimaryKey();

							if (processActionId != 0) {

								ProcessAction action = ProcessActionLocalServiceUtil
										.fetchProcessAction(processActionId);

								if (Validator.isNotNull(action)) {
									String perConditionStr = StringPool.BLANK;

									if (Validator.isNotNull(action)) {
										perConditionStr = action.getPreCondition();
									}

									boolean checkPreCondition = DossierMgtUtils.checkPreCondition(
											StringUtil.split(perConditionStr, StringPool.COMMA), dossier);

									_log.info("============================================= checkPreCondition "
											+ checkPreCondition + "|DossierId = " + dossier.getDossierId() + "|split= "
											+ perConditionStr);

									if (checkPreCondition) {
										
										String userActionName = _getUserActionName(perConditionStr, dossier.getDossierId(), systemUser.getFullName());

										// String subUsers = StringPool.BLANK;

										dossierActions.doAction(dossier.getGroupId(), dossier.getDossierId(),
												dossier.getReferenceUid(), processAction.getActionCode(),
												processAction.getProcessActionId(), userActionName,
												processAction.getActionName(), processAction.getAssignUserId(),
												systemUser.getUserId(), StringPool.BLANK, serviceContext);
									}

								}

							}

						}

					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}

		}

	}
	
	private String _getUserActionName(String perConditionStr, long dossierId, String defaultName) {
		String userActionName = StringPool.BLANK;
		
		if (perConditionStr.contains("payok")) {
			
			List<PaymentFile> paymentFiles = PaymentFileLocalServiceUtil.getByDossierId(dossierId);
			
			if (paymentFiles.size() > 0) {
				PaymentFile paymentFile = paymentFiles.get(0);
				
				long userId = paymentFile.getUserId();
				
				try {
					userActionName = UserLocalServiceUtil.getUser(userId).getFullName();
				} catch (Exception e) {
					_log.info("DEFAULT_NAME");
					
					userActionName = defaultName;
				}
			}
			
		} else {
			userActionName = defaultName;
		}
		
		return userActionName;
	}

	@Activate
	@Modified
	protected void activate() {
		schedulerEntryImpl.setTrigger(
				TriggerFactoryUtil.createTrigger(getEventListenerClass(), getEventListenerClass(), 1, TimeUnit.MINUTE));
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

	private Log _log = LogFactoryUtil.getLog(TimerScheduler.class);

}
