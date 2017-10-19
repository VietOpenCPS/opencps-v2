package org.opencps.communication.action;

import org.opencps.communication.model.NotificationQueue;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public interface NotificationQueueInterface {

	public JSONObject getNotificationQueues(ServiceContext serviceContext);

	public NotificationQueue read(long notificationQueueId, ServiceContext serviceContext);

}
