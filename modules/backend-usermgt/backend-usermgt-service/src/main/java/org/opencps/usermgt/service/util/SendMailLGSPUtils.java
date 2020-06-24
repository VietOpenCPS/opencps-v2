package org.opencps.usermgt.service.util;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;

import java.util.Calendar;
import java.util.Date;

import org.opencps.auth.api.keys.NotificationType;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.usermgt.constants.UserTerm;
import org.opencps.usermgt.model.Applicant;

public class SendMailLGSPUtils {

	public static void sendMailVerifyAcc(Applicant applicant, String activeCode, long groupId) {
		long notificationQueueId = CounterLocalServiceUtil.increment(
				NotificationQueue.class.getName());
			NotificationQueue queue =
				NotificationQueueLocalServiceUtil.createNotificationQueue(
					notificationQueueId);

			Date now = new Date();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 1);

			queue.setCreateDate(now);
			queue.setModifiedDate(now);
			queue.setGroupId(groupId > 0 ? groupId : 35417l);
			queue.setCompanyId(20099l);

			queue.setNotificationType(NotificationType.USER_05);
			queue.setClassName(User.class.getName());
			if (applicant != null) {
			queue.setClassPK(String.valueOf(applicant.getPrimaryKey()));
			queue.setToUsername(applicant.getApplicantName());
			queue.setToUserId(applicant.getUserId());
			queue.setToEmail(applicant.getContactEmail());
			queue.setToTelNo(applicant.getContactTelNo());
		}

			JSONObject payload = JSONFactoryUtil.createJSONObject();
			// _log.info("START PAYLOAD: ");
			JSONObject subPayload = JSONFactoryUtil.createJSONObject();
			if (applicant != null) {
			subPayload.put(UserTerm.USER_NAME, applicant.getApplicantName());
			subPayload.put(UserTerm.USER_ID, applicant.getUserId());
			subPayload.put(UserTerm.EMAIL, applicant.getContactEmail());
			subPayload.put(UserTerm.TELNO, applicant.getContactTelNo());
			}
			subPayload.put(UserTerm.SECRET_KEY, activeCode);
			payload.put(UserTerm.USER, subPayload);

			// _log.info("payloadTest: "+payload.toJSONString());
			queue.setPayload(payload.toJSONString());
			queue.setExpireDate(cal.getTime());

			NotificationQueueLocalServiceUtil.addNotificationQueue(queue);
	}
}
