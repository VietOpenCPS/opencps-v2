package org.opencps.dossiermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
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
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;
import org.opencps.dossiermgt.service.PaymentFileLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import backend.auth.api.exception.ErrorMsgModel;

@Component(immediate = true, service = TimerScheduler.class)
public class TimerScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	
	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		_log.info("Invoke Timer****");
		// get all actions that has preCondition is "timer"

		// Get all dossier
		List<Dossier> allDossierTimer;
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -2);
		Date twoDayAgo = cal.getTime();
		
		Company company = CompanyLocalServiceUtil.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		// This is TEMPORARY code for auto = timer, it need to optimize later
//		allDossierTimer = DossierLocalServiceUtil.getDossiers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		allDossierTimer = DossierLocalServiceUtil.findByNOT_ST_GT_MD(new String[] { DossierTerm.DOSSIER_STATUS_DONE, DossierTerm.DOSSIER_STATUS_CANCELLED, DossierTerm.DOSSIER_STATUS_DENIED, DossierTerm.DOSSIER_STATUS_UNRESOLVED }, twoDayAgo, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		DossierActions dossierActions = new DossierActionsImpl();

		User systemUser = UserLocalServiceUtil.getUserByEmailAddress(company.getCompanyId(),
				RESTFulConfiguration.SERVER_USER);

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setCompanyId(company.getCompanyId());
		serviceContext.setUserId(systemUser.getUserId());

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		//Sort[] sorts = new Sort[] { SortFactoryUtil.create("_sortable", Sort.STRING_TYPE, false) };

		for (Dossier dossier : allDossierTimer) {
			params.put(Field.GROUP_ID, String.valueOf(dossier.getGroupId()));
			params.put(DossierTerm.DOSSIER_ID, String.valueOf(dossier.getDossierId()));
			params.put(DossierTerm.REFERENCE_UID, String.valueOf(dossier.getReferenceUid()));
			params.put(DossierActionTerm.AUTO, "timmer");

			ErrorMsgModel errorModel = new ErrorMsgModel();
			
			if (Validator.isNotNull(dossier.getDossierStatus())) {

				long dossierActionId = dossier.getDossierActionId();

				DossierAction dossierAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);

				long serviceProcessId = dossierAction != null ? dossierAction.getServiceProcessId() : 0;

				String stepCode = dossierAction != null ? dossierAction.getStepCode() : StringPool.BLANK;

				//boolean pending = dossierAction != null ? dossierAction.getPending() : false;

				List<ProcessAction> lstProcessAction;

				lstProcessAction = ProcessActionLocalServiceUtil.getProcessActionByG_SPID_PRESC(dossier.getGroupId(),
						serviceProcessId, stepCode);
				//TODO : test case auto event
				DossierRequestUD dRegUD = DossierRequestUDLocalServiceUtil
						.getDossierRequestByDossierId(dossier.getDossierId());

				boolean flag = false;
				if (dRegUD != null && dRegUD.getStatusReg() == 3) {
					for (ProcessAction processAction : lstProcessAction) {

						if (processAction.getAutoEvent().contains("timmer")) {
							
							String perConditionStr = processAction.getPreCondition();

							boolean checkPreCondition = DossierMgtUtils
									.checkPreCondition(StringUtil.split(perConditionStr, StringPool.COMMA), dossier);

							// do action

							String userActionName = _getUserActionName(perConditionStr, dossier.getGroupId(),
									dossier.getDossierId(), systemUser.getFullName());

							// String subUsers = StringPool.BLANK;
							if (checkPreCondition) {
								
								_log.info("$$$$$dossierId_"+dossier.getDossierId() + "autoEvent_" + processAction.getAutoEvent());

								flag = true;
								ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(dossier.getGroupId(), processAction.getActionCode());
//								_log.info("Action config: " + actConfig);
								String serviceCode = dossier.getServiceCode();
								String govAgencyCode = dossier.getGovAgencyCode();
								String dossierTempNo = dossier.getDossierTemplateNo();
								ProcessOption option = null;
								
								if (actConfig != null) {
									ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(dossier.getGroupId(), 
											serviceCode, govAgencyCode);
									if (config != null) {
										option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(dossier.getGroupId(), 
												dossierTempNo,
												config.getServiceConfigId());
									}
									
									dossierActions.doAction(
										dossier.getGroupId(), 
										systemUser.getUserId(), 
										dossier, 
										option, 
										processAction,
										processAction.getActionCode(), 
										userActionName, 
										StringPool.BLANK, 
										StringPool.BLANK, 
										StringPool.BLANK, 
										StringPool.BLANK,
										actConfig.getSyncType(),
										serviceContext, errorModel);
								
//								dossierActions.doAction(dossier.getGroupId(), dossier.getDossierId(),
//										dossier.getReferenceUid(), processAction.getActionCode(),
//										processAction.getProcessActionId(), userActionName, StringPool.BLANK,
//										processAction.getAssignUserId(), systemUser.getUserId(), StringPool.BLANK,
//										serviceContext);
								}
							}
						}
						
						if (flag) {
							break;
						}
					}
				} else {
					for (ProcessAction processAction : lstProcessAction) {

						if (processAction.getAutoEvent().contains("timmer")) {
							
							String perConditionStr = processAction.getPreCondition();

							boolean checkPreCondition = DossierMgtUtils
									.checkPreCondition(StringUtil.split(perConditionStr, StringPool.COMMA), dossier);

							// do action

							String userActionName = _getUserActionName(perConditionStr, dossier.getGroupId(),
									dossier.getDossierId(), systemUser.getFullName());

							// String subUsers = StringPool.BLANK;
							if (checkPreCondition && perConditionStr.contains("payok")) {
								
								_log.info("$$$$$dossierId_"+dossier.getDossierId() + "autoEvent_" + processAction.getAutoEvent());

								flag = true;

								ActionConfig actConfig = ActionConfigLocalServiceUtil.getByCode(dossier.getGroupId(), processAction.getActionCode());
//								_log.info("Action config: " + actConfig);
								String serviceCode = dossier.getServiceCode();
								String govAgencyCode = dossier.getGovAgencyCode();
								String dossierTempNo = dossier.getDossierTemplateNo();
								ProcessOption option = null;
								
								if (actConfig != null) {
									ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(dossier.getGroupId(), 
											serviceCode, govAgencyCode);
									if (config != null) {
										option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(dossier.getGroupId(), 
												dossierTempNo,
												config.getServiceConfigId());
									}
									dossierActions.doAction(
											dossier.getGroupId(), 
											systemUser.getUserId(), 
											dossier, 
											option, 
											processAction,
											processAction.getActionCode(), 
											userActionName, 
											StringPool.BLANK, 
											StringPool.BLANK, 
											StringPool.BLANK, 
											StringPool.BLANK,
											actConfig.getSyncType(),
											serviceContext, errorModel);
								}
//								dossierActions.doAction(dossier.getGroupId(), dossier.getDossierId(),
//										dossier.getReferenceUid(), processAction.getActionCode(),
//										processAction.getProcessActionId(), userActionName, processAction.getActionName(),
//										processAction.getAssignUserId(), systemUser.getUserId(), StringPool.BLANK,
//										serviceContext);
							}
						}
						
						if (flag) {
							break;
						}
					}
				}
			}

		}

		isRunning = false;
	}

	private String _getUserActionName(String perConditionStr, long groupId, long dossierId, String defaultName) {
		String userActionName = StringPool.BLANK;

		PaymentFile paymentFile = PaymentFileLocalServiceUtil.getByDossierId(groupId, dossierId);

		if (paymentFile != null) {
			long userId = paymentFile.getUserId();

			try {
				userActionName = UserLocalServiceUtil.getUser(userId).getFullName();
			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				_log.info("DEFAULT_NAME");

				userActionName = defaultName;
			}
		}

		return userActionName;
	}

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

	private Log _log = LogFactoryUtil.getLog(TimerScheduler.class);

}
