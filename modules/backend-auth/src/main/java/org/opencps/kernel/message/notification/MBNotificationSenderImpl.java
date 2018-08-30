
package org.opencps.kernel.message.notification;

import java.util.Date;

import org.opencps.kernel.message.MBMessageEntry;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;

/**
 * @author trungnt
 */
@Component(immediate = true, service = MBNotificationSenderImpl.class)
public class MBNotificationSenderImpl implements MBNotificationSender {

	private static Log _log = LogFactoryUtil.getLog(MBNotificationSenderImpl.class);
	@Override
	public void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContext) {

		JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();
		payloadJSON.put("userId", messageEntry.getUserId());
		payloadJSON.put("title", messageEntry.getEmailSubject());
		payloadJSON.put("senderName", messageEntry.getFromName());
		payloadJSON.put("notificationText", messageEntry.getTextMessage());
		payloadJSON.put("userUrl", messageEntry.getUserUrl());
		payloadJSON.put("guestUrl", messageEntry.getGuestUrl());
		if (messageEntry.getToUserIds() != null) {
			for (Long toUserId : messageEntry.getToUserIds()) {
				
				try {
					/*userNotificationEventLocalService.addUserNotificationEvent(
						toUserId, portletId, (new Date()).getTime(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						toUserId, payloadJSON.toString(), false,
						serviceContext[0]);*/
					
					_log.info(serviceContext[0].getScopeGroupId());
					
					UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
						toUserId, portletId, (new Date()).getTime(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						toUserId, payloadJSON.toString(), false,
						serviceContext[0]);
				}
				catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}

	}

	/*private UserNotificationEventLocalService userNotificationEventLocalService;

	@Reference(unbind = "-")
	public void setUserNotificationEventLocalService(
		UserNotificationEventLocalService userNotificationEventLocalService) {

		this.userNotificationEventLocalService =
			userNotificationEventLocalService;
	}*/
}
