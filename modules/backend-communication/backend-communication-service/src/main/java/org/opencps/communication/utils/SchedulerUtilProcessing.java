package org.opencps.communication.utils;

import java.util.List;

import org.opencps.communication.action.impl.SendMailUtils;
import org.opencps.communication.exception.NoSuchNotificationQueueException;
import org.opencps.communication.model.NotificationQueue;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.Preferences;
import org.opencps.communication.service.NotificationQueueLocalServiceUtil;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.PreferencesLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

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

				long toUserQueueId = notificationQueue.getToUserId();

				//_log.info("toUserQueueId: " + toUserQueueId);

				Preferences preferences = PreferencesLocalServiceUtil.fetchByF_userId(groupId, toUserQueueId);
				
				//_log.info("preferences: " + preferences);
				
				boolean sendMail = true;

				for (String type : notificationTypeList) {

					if (queueType.equals(type)) {

						Notificationtemplate notificationtemplate = NotificationtemplateLocalServiceUtil
								.fetchByF_NotificationtemplateByType(groupId, type);

						if (Validator.isNotNull(preferences)) {

							JSONObject settingNotificationObject = JSONFactoryUtil
									.createJSONObject(preferences.getPreferences());

							JSONArray jsonArray = settingNotificationObject.getJSONArray("settingNotification");

							for (int i = 0; i < jsonArray.length(); i++) {

								JSONObject currenObj = jsonArray.getJSONObject(i);

								if (notificationtemplate.getNotificationType().equals(currenObj.get("settingType"))) {
									if (!currenObj.getBoolean("email")) {
										sendMail = false;
									}
								}

							}

						}

						//_log.info("sendMailsendMailsendMailsendMailsendMail: " + sendMail);

						if (sendMail) {
							JSONObject payLoad = EmailUtilities.createEmailBody(notificationtemplate, notificationQueue);
														
							SendMailUtils.sendEmailNotification(payLoad, serviceContext);
//							System.out.println("SchedulerUtilProcessing.notificationByType(SendMailUtil)");
							try {
								NotificationQueueLocalServiceUtil.deleteNotificationQueue(
										notificationQueue.getNotificationQueueId(), serviceContext);
//								System.out.println("SchedulerUtilProcessing.notificationByType(notificationQueue.getNotificationQueueId())"+notificationQueue.getNotificationQueueId());
							} catch (NoSuchNotificationQueueException e) {
								_log.debug(e);
								//_log.error(e);
							}
						} else {
							try {
								NotificationQueueLocalServiceUtil.deleteNotificationQueue(
										notificationQueue.getNotificationQueueId(), serviceContext);
//								System.out.println("SchedulerUtilProcessing.notificationByType(notificationQueue.getNotificationQueueId(1))"+notificationQueue.getNotificationQueueId());
							} catch (NoSuchNotificationQueueException e) {
								_log.debug(e);
								//_log.error(e);
							}
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
