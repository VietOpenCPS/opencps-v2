package org.opencps.usermgt.scheduler;

import com.liferay.portal.kernel.json.JSONException;
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
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.service.NotificationtemplateLocalService;
import org.opencps.kernel.prop.PropValues;
import org.opencps.kernel.scheduler.StorageTypeAwareSchedulerEntryImpl;
import org.opencps.usermgt.listener.ApplicantListenerMessageKeys;
import org.opencps.usermgt.listener.ApplicantListenerUtils;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, service = OneDay.class)
public class OneDay extends BaseMessageListener {

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
			createExpiredPasswordNotification(message);
		}
		catch (Exception e) {
			_log.debug(e);
		}
		isRunning = false;
	}

	private void createExpiredPasswordNotification(Message message) {

		try {
			
			List<Employee> employees = EmployeeLocalServiceUtil.findByWorkstatus(0l, 1);
			
			for (Employee emp : employees) {

				User user = UserLocalServiceUtil.getUser(emp.getMappingUserId());
				if (Validator.isNotNull(user) && UserLocalServiceUtil.isPasswordExpiringSoon(user)) {

//					createNotificationQueueExpiredSoon(emp);
					break;
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			_log.error(e);
		}
	}
	
	private void createNotificationQueueExpiredSoon (Employee model) {
		
		_log.info("Employee Log: Employee has expired password soon!");
//		NotificationQueue queue = null;

//		long notificationQueueId = CounterLocalServiceUtil.increment(
//			NotificationQueue.class.getName());
//
//		queue =
//			NotificationQueueLocalServiceUtil.createNotificationQueue(
//				notificationQueueId);

//		Date now = new Date();

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
		

//		queue.setCreateDate(now);
//		queue.setModifiedDate(now);
//		queue.setGroupId(model.getGroupId());
//		queue.setCompanyId(model.getCompanyId());

//		queue.setNotificationType(NotificationType.APPLICANT_01);
//		queue.setClassName(Applicant.class.getName());
//		queue.setClassPK(String.valueOf(model.getPrimaryKey()));
//		queue.setToUsername(model.getFullName());
//		queue.setToUserId(model.getUserId());
//		queue.setToEmail(model.getEmail());
//		queue.setToTelNo(model.getTelNo());

		JSONObject object = JSONFactoryUtil.createJSONObject();

		 String guestBaseUrl = PropValues.PORTAL_DOMAIN +
		 "/web/cong-dich-vu-cong";
		object.put(
			ApplicantListenerMessageKeys.ACTIVATION_CODE,
			"sssss");
		object.put(
			ApplicantListenerMessageKeys.ACTIVATION_LINK,
			guestBaseUrl +
				"/register#/xac-thuc-tai-khoan?active_user_id=" +
				model.getEmployeeId());
		object.put(
			ApplicantListenerMessageKeys.USER_NAME,
			model.getFullName());
		 object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL,
		 "http:v2.opencps.vn");
		object.put("toName", model.getFullName());
		object.put("toAddress", "nguyenthanh.ptiter@gmail.com");
		
		 String payload1 =
		 ApplicantListenerUtils.getPayload(NotificationType.APPLICANT_01,
		 object, model.getGroupId()).toString();
		 _log.info("payloadTest1: "+payload1);
		JSONObject payload = JSONFactoryUtil.createJSONObject();
		try {
			 _log.info("START PAYLOAD: ");
			payload.put(
				"Applicant", JSONFactoryUtil.createJSONObject(
					JSONFactoryUtil.looseSerialize(model)));
		}
		catch (JSONException parse) {
			_log.error(parse);
		}
		 _log.info("payloadTest: "+payload.toJSONString());
//		queue.setPayload(payload.toJSONString());

//		queue.setExpireDate(cal.getTime());

		// NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
	}

	/**
	 * activate: Called whenever the properties for the component change (ala
	 * Config Admin) or OSGi is activating the component.
	 * 
	 * @param properties
	 *            The properties map from Config Admin.
	 * @throws SchedulerException
	 *             in case of error.
	 */
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws SchedulerException {

		Date startDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
//		startDate = cal.getTime();

		String listenerClass = getClass().getName();
		Trigger jobTrigger = _triggerFactory.createTrigger(
			listenerClass, listenerClass, new Date(), null, 1, TimeUnit.DAY);

		_schedulerEntryImpl =
			new SchedulerEntryImpl(getClass().getName(), jobTrigger);
		_schedulerEntryImpl = new StorageTypeAwareSchedulerEntryImpl(
			_schedulerEntryImpl, StorageType.MEMORY_CLUSTERED);

		// _schedulerEntryImpl.setTrigger(jobTrigger);

		if (_initialized) {
			deactivate();
		}

		_schedulerEngineHelper.register(
			this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);
		_initialized = true;
	}

	@Deactivate
	protected void deactivate() {

		if (_initialized) {
			try {
				_schedulerEngineHelper.unschedule(
					_schedulerEntryImpl, getStorageType());
			}
			catch (SchedulerException se) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to unschedule trigger", se);
				}
			}

			_schedulerEngineHelper.unregister(this);
		}
		_initialized = false;
	}

	/**
	 * getStorageType: Utility method to get the storage type from the scheduler
	 * entry wrapper.
	 * 
	 * @return StorageType The storage type to use.
	 */
	protected StorageType getStorageType() {

		if (_schedulerEntryImpl instanceof StorageTypeAware) {
			return ((StorageTypeAware) _schedulerEntryImpl).getStorageType();
		}

		return StorageType.MEMORY_CLUSTERED;
	}

	/**
	 * setModuleServiceLifecycle: So this requires some explanation... OSGi will
	 * start a component once all of it's dependencies are satisfied. However,
	 * there are times where you want to hold off until the portal is completely
	 * ready to go. This reference declaration is waiting for the
	 * ModuleServiceLifecycle's PORTAL_INITIALIZED component which will not be
	 * available until, surprise surprise, the portal has finished initializing.
	 * With this reference, this component activation waits until portal
	 * initialization has completed.
	 * 
	 * @param moduleServiceLifecycle
	 */
	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {

	}

	@Reference(unbind = "-")
	protected void setTriggerFactory(TriggerFactory triggerFactory) {

		_triggerFactory = triggerFactory;
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	private SchedulerEngineHelper _schedulerEngineHelper;
	private TriggerFactory _triggerFactory;
	private volatile boolean _initialized;
	private SchedulerEntryImpl _schedulerEntryImpl = null;
	@Reference
	private NotificationtemplateLocalService _notificationTemplateLocalService;

	private Log _log = LogFactoryUtil.getLog(OneDay.class);

}
