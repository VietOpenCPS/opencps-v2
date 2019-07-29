
package org.opencps.communication.utils;

import java.util.Date;
import java.util.List;

import org.opencps.communication.bussiness.impl.NotificationQueueBusinessImpl;
import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public class NotificationQueueBusinessFactoryUtil {

	private static NotificationQueueBusinessImpl _notificationQueueBusinessImpl;

	public NotificationQueueBusinessImpl getNotificationQueueBusinessImpl() {

		return _notificationQueueBusinessImpl;
	}

	public static void setNotificationQueueBusinessImpl(
		NotificationQueueBusinessImpl notificationQueueBusinessImpl) {

		NotificationQueueBusinessFactoryUtil._notificationQueueBusinessImpl =
			notificationQueueBusinessImpl;
	}

	public static NotificationQueueBusinessImpl getNotificationQueueBusiness() {

		if (_notificationQueueBusinessImpl == null) {
			setNotificationQueueBusinessImpl(
				new NotificationQueueBusinessImpl());
		}
		return _notificationQueueBusinessImpl;
	}

	public static NotificationQueue create(
		long userId, long groupId, String notificationType, String className,
		String classPK, String payload, String fromUsername, String toUsername,
		long toUserId, String toEmail, String toTelNo, Date publicationDate,
		Date expireDate, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationQueueBusiness().create(
			userId, groupId, notificationType, className, classPK, payload,
			fromUsername, toUsername, toUserId, toEmail, toTelNo,
			publicationDate, expireDate, serviceContext);
	}

	public static JSONObject getNotificationQueues(
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationQueueBusiness().getNotificationQueues(
			serviceContext);
	}

	public static NotificationQueue delete(
		long notificationQueueId, ServiceContext serviceContext)
		throws NoSuchNotificationQueueException {

		return getNotificationQueueBusiness().delete(
			notificationQueueId, serviceContext);
	}

	public static NotificationQueue read(
		long notificationQueueId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return getNotificationQueueBusiness().read(
			notificationQueueId, serviceContext);
	}

	public static List<NotificationQueue> findByNotificationType_LessThanExpireDate(
		String notificationType, Date date) {

		return getNotificationQueueBusiness().findByNotificationType_LessThanExpireDate(
			notificationType, date);
	}

	public static NotificationQueue update(
		NotificationQueue notificationQueue, ServiceContext serviceContext)
		throws NoSuchNotificationQueueException {

		try {
			return getNotificationQueueBusiness().update(notificationQueue, serviceContext);
		}
		catch (SystemException | PortalException e) {
			_log.debug(e);
			return null;
		}
	}

	private static final Log _log =
					LogFactoryUtil.getLog(NotificationQueueBusinessFactoryUtil.class);

}
