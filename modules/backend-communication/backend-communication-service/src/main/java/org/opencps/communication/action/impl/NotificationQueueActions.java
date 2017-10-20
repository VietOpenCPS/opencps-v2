package org.opencps.communication.action.impl;

import java.util.List;

import org.opencps.communication.action.NotificationQueueInterface;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

public class NotificationQueueActions implements NotificationQueueInterface {

	public Log _log = LogFactoryUtil.getLog(NotificationQueueActions.class);

	@Override
	public JSONObject getNotificationQueues(ServiceContext serviceContext) {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		List<NotificationQueue> listQueue = NotificationQueueLocalServiceUtil.getNotificationQueues(QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		result.put("data", listQueue);

		result.put("total", listQueue.size());

		return result;
	}

	@Override
	public NotificationQueue read(long notificationQueueId, ServiceContext serviceContext) {
		NotificationQueue notificationQueue = NotificationQueueLocalServiceUtil
				.fetchNotificationQueue(notificationQueueId);

		return notificationQueue;
	}

}
