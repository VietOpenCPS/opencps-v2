
package org.opencps.communication.bussiness;

import java.util.Date;

import org.opencps.communication.model.NotificationQueue;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public interface NotificationQueueBusiness {

	NotificationQueue create(long userId, long groupId, String notificationType, String className, String classPK,
			String payload, String fromUsername, String toUsername, long toUserId, String toEmail, String toTelNo,
			Date publicationDate, Date expireDate, ServiceContext serviceContext)
			throws PortalException, SystemException;

	JSONObject getNotificationQueues(ServiceContext serviceContext) throws PortalException, SystemException;

	NotificationQueue read(long notificationQueueId, ServiceContext serviceContext)
			throws PortalException, SystemException;

}
