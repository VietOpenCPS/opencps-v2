package org.opencps.usermgt.scheduler;

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
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.Map;

import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.usermgt.action.ApplicantActions;
import org.opencps.usermgt.action.impl.ApplicantActionsImpl;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.SyncScheduler;
import org.opencps.usermgt.scheduler.utils.AdministrativeRegionUtils;
import org.opencps.usermgt.service.SyncSchedulerLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


/**
 * @author trungnt
 */
//@Component(immediate = true, service = UserRegisterUpdateSheduler.class)
public class UserRegisterUpdateSheduler extends BaseMessageListener {
	private volatile boolean isRunning = false;
	
	@Override
	protected void doReceive(Message message) {
		if (!isRunning) {
			isRunning = true;
		}
		else {
			return;
		}
		try {
			doProcessRegisterUser();
		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}

	private void doProcessRegisterUser() throws Exception {
		
		SyncScheduler syncScheduler = SyncSchedulerLocalServiceUtil.getByClassNameAndTypeCode(Applicant.class.getName(), "citizen");

		if (syncScheduler != null) {

			Date syncDate;// = syncScheduler.getSyncDate();
			//Co thong tin rooif
			ServiceContext serviceContext = new ServiceContext();
			long groupId = 35166;
			//
			for (int i = 0; i < 5; i++) {
				JSONObject jsonCongDan = JSONFactoryUtil.createJSONObject();
				//String idCongDan = jsonCongDan.getString("id");
				String applicantName = jsonCongDan.getString("tenDayDu");
				String applicantIdType = "citizen";
				String applicantIdNo = jsonCongDan.getString("soCMND");
				String applicantIdDate = "";
				String contactEmail = jsonCongDan.getString("tenDayDu");
				String address = jsonCongDan.getString("diaChiThuongTru");
				String contactName = jsonCongDan.getString("tenDayDu");
				String contactTelNo = jsonCongDan.getString("dienThoaiDiDong");
				String secret = "";
				//
				String cityId = jsonCongDan.getString("diaChiThuongTruTinhId");
				String districtId = jsonCongDan.getString("diaChiThuongTruHuyenId");
				String warId = jsonCongDan.getString("diaChiThuongTruXaId");
				// Call get info Tỉnh / Thành phố
				Map<String, String> mapCity = AdministrativeRegionUtils.getInfoRegion(cityId);
				String cityCode = mapCity.get("ma");
				String cityName = mapCity.get("ten");
				//
				Map<String, String> mapDistrict = AdministrativeRegionUtils.getInfoRegion(districtId);
				String districtCode = mapDistrict.get("ma");
				String districtName = mapDistrict.get("ten");
				//
				Map<String, String> mapWar = AdministrativeRegionUtils.getInfoRegion(warId);
				String wardCode = mapWar.get("ma");
				String wardName = mapWar.get("ten");
				//
				ApplicantActions actions = new ApplicantActionsImpl();
				actions.register(serviceContext, groupId, applicantName, applicantIdType,
						applicantIdNo, applicantIdDate, contactEmail, address, cityCode, cityName, districtCode,
						districtName, wardCode, wardName, contactName, contactTelNo, StringPool.BLANK, secret);
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
		  Trigger jobTrigger = _triggerFactory.createTrigger(listenerClass, listenerClass, new Date(), null, 5, TimeUnit.MINUTE);

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
