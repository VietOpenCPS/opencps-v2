package org.opencps.usermgt.listener;

import java.util.Calendar;
import java.util.Date;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.ApplicantLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

@Component(immediate = true, service = ModelListener.class)
public class ApplicantListener extends BaseModelListener<Applicant>{
	@Override
	public void onAfterCreate(Applicant model) throws ModelListenerException {
		
		try {
			
			NotificationQueue queue = null;
			
			long notificationQueueId = CounterLocalServiceUtil.increment(NotificationQueue.class.getName());
			
			queue = NotificationQueueLocalServiceUtil.createNotificationQueue(notificationQueueId);
			
			Date now = new Date();
			
			Calendar cal = Calendar.getInstance();
			
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);
			
			queue.setCreateDate(now);
			queue.setModifiedDate(now);
			
			queue.setNotificationType(NotificationType.USER_01);
			queue.setClassName(Applicant.class.getName());
			queue.setClassPK(String.valueOf(model.getPrimaryKey()));
			queue.setToUsername(model.getApplicantName());
			queue.setToUserId(model.getUserId());
			queue.setToEmail(model.getContactEmail());
			queue.setToTelNo(model.getContactTelNo());
			queue.setExpireDate(cal.getTime());
			
			NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
		} catch (Exception e) {
			// TODO: handle exception
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
