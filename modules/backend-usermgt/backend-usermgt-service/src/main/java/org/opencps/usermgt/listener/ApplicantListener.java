package org.opencps.usermgt.listener;

import java.util.Calendar;
import java.util.Date;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.model.impl.OfficeSiteImpl;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(immediate = true, service = ModelListener.class)
public class ApplicantListener extends BaseModelListener<Applicant>{
	
	@Override
	public void onAfterUpdate(Applicant model) throws ModelListenerException {
		
		/*
		 * 03/01/2018 ThanhNv: Khong gui thong bao khi cap nhat thong tin doanh nghiep - MR_DUAN
		if (modelBefore.getActivationCode().length() != 0 && Validator.isNull(model.getActivationCode())) {
			try {
				
				
				NotificationQueue queue = null;
				
				long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
				
				queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
				
				Date now = new Date();
				
				Calendar cal = Calendar.getInstance();
				
				cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
				
				queue.setCreateDate(now);
				queue.setModifiedDate(now);
				queue.setGroupId(model.getGroupId());
				queue.setCompanyId(model.getCompanyId());
				
				queue.setNotificationType(NotificationType.USER_02);
				queue.setClassName(Applicant.class.getName());
				queue.setClassPK(String.valueOf(model.getPrimaryKey()));
				queue.setToUsername(model.getApplicantName());
				queue.setToUserId(model.getUserId());
				queue.setToEmail(model.getContactEmail());
				queue.setToTelNo(model.getContactTelNo());
				
				JSONObject object = JSONFactoryUtil.createJSONObject();
				
				
				object.put(ApplicantListenerMessageKeys.PASSWORD, modelBefore.getTmpPass());
				object.put(ApplicantListenerMessageKeys.USER_NAME, model.getApplicantName());
				object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL, "http://v2.opencps.vn");
				object.put("toName", model.getApplicantName());
				object.put("toAddress", model.getContactEmail());
				
				String payload = ApplicantListenerUtils.getPayload(NotificationType.USER_02, object, model.getGroupId()).toString();
				
				queue.setPayload(payload);
				
				queue.setExpireDate(cal.getTime());
				
				NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			} catch (Exception e) {
				_log.error(e);
			}
		}
		*/
	}
	
	@Override
	public void onAfterCreate(Applicant model) throws ModelListenerException {
		
		try {
			
			_log.info("Applicant Log trigger!");
			
			NotificationQueue queue = null;
			
			long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
			
			queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
			
			Date now = new Date();
			
			Calendar cal = Calendar.getInstance();
			
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
			
			queue.setCreateDate(now);
			queue.setModifiedDate(now);
			queue.setGroupId(model.getGroupId());
			queue.setCompanyId(model.getCompanyId());
			
			queue.setNotificationType(NotificationType.APPLICANT_01);
			queue.setClassName(Applicant.class.getName());
			queue.setClassPK(String.valueOf(model.getPrimaryKey()));
			queue.setToUsername(model.getApplicantName());
			queue.setToUserId(model.getUserId());
			queue.setToEmail(model.getContactEmail());
			queue.setToTelNo(model.getContactTelNo());
			
			JSONObject object = JSONFactoryUtil.createJSONObject();
			
			object.put(ApplicantListenerMessageKeys.ACTIVATION_CODE, model.getActivationCode());
			object.put(ApplicantListenerMessageKeys.ACTIVATION_LINK, "/confirm-account?active_user_id="+ model.getApplicantId());
			object.put(ApplicantListenerMessageKeys.USER_NAME, model.getApplicantName());
			//object.put(ApplicantListenerMessageKeys.HOME_PAGE_URL, "http://v2.opencps.vn");
			object.put("toName", model.getApplicantName());
			object.put("toAddress", model.getContactEmail());
			
			String payload = ApplicantListenerUtils.getPayload(NotificationType.APPLICANT_01, object, model.getGroupId()).toString();
			
			queue.setPayload(payload);
			
			queue.setExpireDate(cal.getTime());
			
			NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
			
			//binhth add user applicant to siteGroup
			
			long userId = model.getMappingUserId();
			
			GroupLocalServiceUtil.addUserGroup(userId, model.getGroupId());
			
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	
	@Override
	public void onBeforeUpdate(Applicant model) throws ModelListenerException {
		long applicantId = model.getPrimaryKey();

		try {
			modelBefore = ApplicantLocalServiceUtil.fetchApplicant(applicantId);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	
	public static Applicant modelBefore;

	Log _log = LogFactoryUtil.getLog(ApplicantListener.class);

}
