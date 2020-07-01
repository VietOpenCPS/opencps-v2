package org.opencps.usermgt.scheduler;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.usermgt.constants.UserRegisterTerm;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.SyncScheduler;
import org.opencps.usermgt.scheduler.utils.RegisterLGSPUtils;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.SyncSchedulerLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


/**
 * @author trungnt
 */
//@Component(immediate = true, service = ActiveUserLGSPScheduler.class)
public class ActiveUserLGSPScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	//Time engine dossier
	private static boolean syncUserLGSP = Validator.isNotNull(PropsUtil.get("opencps.register.lgsp"))
			? GetterUtil.getBoolean(PropsUtil.get("opencps.register.lgsp"))
			: false;
	
	@Override
	protected void doReceive(Message message) {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		try {
			if (syncUserLGSP) {
				doProcessChangePass();
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}

	private void doProcessChangePass() throws Exception {
		
		System.out.println("START CHANGE PASS");
		List<SyncScheduler> syncList = SyncSchedulerLocalServiceUtil.getByF_NAME_RETRY(User.class.getName(), 5);
		_log.info("syncList: "+syncList);
		_log.info("User.class.getName(): "+User.class.getName());
		_log.info("syncList.size(): "+syncList.size());

		if (syncList != null && syncList.size() > 0) {

			for (SyncScheduler syncScheduler : syncList) {
				long groupId = syncScheduler.getGroupId();
				_log.info("groupId: "+groupId);
				String contactEmail = syncScheduler.getTypeCode();
				int retry = syncScheduler.getRetry();
				_log.info("contactEmail: "+contactEmail);
				_log.info("retry: "+retry);
				//
				Applicant aplc = ApplicantLocalServiceUtil.fetchByF_GID_CTEM(groupId, contactEmail);
				_log.info("aplc: "+aplc);
				if (aplc != null) {
					String profile = aplc.getProfile();
					_log.info("profile: "+profile);
					String oldSecrect = StringPool.BLANK;
					if (Validator.isNotNull(profile)) {
						JSONObject jsonProfile = JSONFactoryUtil.createJSONObject(profile);
						//
						if (jsonProfile.has(UserRegisterTerm.MAT_KHAU)
								&& Validator.isNotNull(jsonProfile.getString(UserRegisterTerm.MAT_KHAU))) {
							oldSecrect = jsonProfile.getString(UserRegisterTerm.MAT_KHAU);
							_log.info("oldSecrect: "+oldSecrect);
						}
					}
					
					//
					String strToken = RegisterLGSPUtils.getTokenLGSP();
					_log.info("strToken: "+strToken);
					if (Validator.isNotNull(strToken)) {
						JSONObject jsonToken = JSONFactoryUtil.createJSONObject(strToken);
						//
						if (jsonToken.has("access_token") && jsonToken.has("token_type")
								&& Validator.isNotNull(jsonToken.getString("access_token"))
								&& Validator.isNotNull(jsonToken.getString("token_type"))) {
							String accessToken = jsonToken.getString("access_token");
							String tokenType = jsonToken.getString("token_type");
							
							_log.info("accessToken: " + accessToken);
							_log.info("tokenType: " + tokenType);
							String authStrEnc = tokenType + StringPool.SPACE + accessToken;

							//boolean flagChange = RegisterLGSPUtils.changePassLGSP(oldSecrect, aplc.getTmpPass(), contactEmail, authStrEnc);
							boolean flagChange = false;
							if (!flagChange) {
								retry += 1;
								//Update sync scheduler
								syncScheduler.setRetry(retry);
								SyncSchedulerLocalServiceUtil.updateSyncScheduler(syncScheduler);
							}
						}
					} else {
						retry += 1;
						//Update sync scheduler
						syncScheduler.setRetry(retry);
						SyncSchedulerLocalServiceUtil.updateSyncScheduler(syncScheduler);
					}
				}
			}
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
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 30, TimeUnit.SECOND);

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

	private Log _log = LogFactoryUtil.getLog(UserRegisterUpdateSheduler.class);

}
