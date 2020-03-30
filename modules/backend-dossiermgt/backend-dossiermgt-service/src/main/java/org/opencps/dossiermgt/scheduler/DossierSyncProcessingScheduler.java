package org.opencps.dossiermgt.scheduler;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.model.DossierSync;
import org.opencps.dossiermgt.processor.IMessageProcessor;
import org.opencps.dossiermgt.processor.MessageProcessor;
import org.opencps.dossiermgt.service.DossierSyncLocalServiceUtil;
import org.opencps.kernel.prop.PropValues;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = DossierSyncProcessingScheduler.class)
public class DossierSyncProcessingScheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		try {
			_log.info("OpenCPS SYNC DOSSIERS IS  : " + APIDateTimeUtils.convertDateToString(new Date()));
			
			List<DossierSync> lstSyncs = DossierSyncLocalServiceUtil.findByState(DossierSyncTerm.STATE_WAITING_SYNC, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			
			for (DossierSync ds : lstSyncs) {
				IMessageProcessor processor = MessageProcessor.getProcessor(ds);
				if (processor != null) {
	//				_log.info("Processing: " + ds);
					processor.process();				
				}
				else {
					_log.info("Do not config sync server");
				}
			}
			
			_log.info("OpenCPS SYNC DOSSIERS HAS BEEN DONE : " + APIDateTimeUtils.convertDateToString(new Date()));	
		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}

	//Time engine dossier
	private static int TIME_SYNC = Validator.isNotNull(PropValues.TIME_STATISTIC_DOSSIER) ? Integer.valueOf(PropValues.TIME_STATISTIC_DOSSIER) : 45;
	  @Activate
	  @Modified
	  protected void activate(Map<String,Object> properties) throws SchedulerException {
		  String listenerClass = getClass().getName();
		  _log.info("TIME_SYNC: "+TIME_SYNC);
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, TIME_SYNC, TimeUnit.SECOND);

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
	  
	private Log _log = LogFactoryUtil.getLog(DossierSyncProcessingScheduler.class);
	
}
