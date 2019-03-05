package org.opencps.usermgt.scheduler.utils;

import java.util.List;

import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import backend.utils.SendMailUtils;

public class SchedulerUtilProcessing {

	public static void notificationByType(String[] notificationTypeList) {

		List<NotificationQueue> listQueue = NotificationQueueLocalServiceUtil.getNotificationQueues(QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		ServiceContext serviceContext = new ServiceContext();

		for (NotificationQueue notificationQueue : listQueue) {
			
			serviceContext.setCompanyId(notificationQueue.getCompanyId());
			serviceContext.setUserId(notificationQueue.getUserId());
			serviceContext.setScopeGroupId(notificationQueue.getGroupId());
			
			long groupId = notificationQueue.getGroupId();

			String queueType = notificationQueue.getNotificationType();

			try {

				JSONObject payLoadData = JSONFactoryUtil.createJSONObject(notificationQueue.getPayload());

				for (String type : notificationTypeList) {

					if (queueType.equals(type)) {

						NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, type);

						JSONObject payLoad = JSONFactoryUtil.createJSONObject();

						payLoad.put("toName", payLoadData.get("toName") );
						payLoad.put("toAddress", payLoadData.get("toAddress"));
						payLoad.put("subject", payLoadData.get("subject"));
						payLoad.put("body", payLoadData.get("body") );
						
//								StringUtil.replace(notificationtemplate.getEmailBody(),
//										new String[] { "[$USERNAME$]", "[$USEREMAIL$]", "[$PASSWORD$]", "[$PASSWORD_CODE$]", "[$USERSTATUS$]" },
//										new String[] { payLoadData.getString("USERNAME"),
//												payLoadData.getString("toName"),
//												payLoadData.getString("PASSWORD"),
//												payLoadData.getString("PASSWORD_CODE"),
//												payLoadData.getString("USERSTATUS") }));

						SendMailUtils.sendEmailNotification(payLoad, serviceContext);

						try {
							NotificationQueueLocalServiceUtil.deleteNotificationQueue(
									notificationQueue.getNotificationQueueId(), serviceContext);
						} catch (NoSuchNotificationQueueException e) {
							_log.error(e);
						}

					}

				}

			} catch (JSONException e1) {
				_log.error(e1);
			}

		}

	}

	private static Log _log = LogFactoryUtil.getLog(SchedulerUtilProcessing.class);
}
