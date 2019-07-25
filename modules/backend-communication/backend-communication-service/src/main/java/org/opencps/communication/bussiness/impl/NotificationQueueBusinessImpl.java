
package org.opencps.communication.bussiness.impl;

import java.util.Date;
import java.util.List;

import org.opencps.communication.bussiness.BaseNotificationQueueBusiness;
import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.utils.NotificationTemplateBusinessFactoryUtil;
import org.opencps.kernel.util.DateTimeUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public class NotificationQueueBusinessImpl
	extends BaseNotificationQueueBusiness {

	@Override
	public NotificationQueue create(
		long userId, long groupId, String notificationType, String className,
		String classPK, String payload, String fromUsername, String toUsername,
		long toUserId, String toEmail, String toTelNo, Date publicationDate,
		Date expireDate, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (publicationDate == null) {
			publicationDate = new Date();
		}

		if (expireDate == null) {
			Notificationtemplate notificationtemplate =
				NotificationTemplateBusinessFactoryUtil.fetchByNotificationType(
					groupId, notificationType);

			expireDate = (notificationtemplate != null &&
				notificationtemplate.getExpireDuration() > 0)
					? DateTimeUtil.incrementHour(
						new Date(), notificationtemplate.getExpireDuration())
					: DateTimeUtil.incrementHour(new Date(), 24);
		}
		
		return NotificationQueueLocalServiceUtil.addNotificationQueue(
			userId, groupId, notificationType, className, classPK, payload,
			fromUsername, toUsername, toUserId, toEmail, toTelNo,
			publicationDate, expireDate, serviceContext);
	}

	public NotificationQueue delete(
		long notificationQueueId, ServiceContext serviceContext)
		throws NoSuchNotificationQueueException {

		return NotificationQueueLocalServiceUtil.deleteNotificationQueue(
			notificationQueueId, serviceContext);
	}

	@Override
	public JSONObject getNotificationQueues(ServiceContext serviceContext)
		throws PortalException, SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<NotificationQueue> listQueue =
			NotificationQueueLocalServiceUtil.getNotificationQueues(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		result.put("data", listQueue);

		result.put("total", listQueue.size());

		return result;
	}

	@Override
	public NotificationQueue read(
		long notificationQueueId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		NotificationQueue notificationQueue =
			NotificationQueueLocalServiceUtil.fetchNotificationQueue(
				notificationQueueId);

		return notificationQueue;
	}

	public List<NotificationQueue> findByNotificationType_LessThanExpireDate(
		String notificationType, Date date) {

		return NotificationQueueLocalServiceUtil.findByF_notificationType_LessThanExpireDate(
			notificationType, date);
	}

	public NotificationQueue update(
		NotificationQueue notificationQueue, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return NotificationQueueLocalServiceUtil.updateNotificationQueue(notificationQueue);
	}

	public Log _log =
		LogFactoryUtil.getLog(NotificationQueueBusinessImpl.class);

}
