
package org.opencps.kernel.message.notification;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import org.opencps.kernel.message.MBMessageEntry;
import org.osgi.service.component.annotations.Component;

/**
 * @author trungnt
 */
@Component(immediate = true, service = MBNotificationSenderImpl.class)
public class MBNotificationSenderImpl implements MBNotificationSender {

	private static Log _log =
		LogFactoryUtil.getLog(MBNotificationSenderImpl.class);

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
		payloadJSON.put("data", messageEntry.getData());
		payloadJSON.put("notifyMessage", messageEntry.getNotifyMessage());

		List<Long> toUserList = messageEntry.getToUserIds();
		if (toUserList != null && toUserList.size() > 0) {
			for (Long toUserId : messageEntry.getToUserIds()) {
				try {
					/*
					 * userNotificationEventLocalService.
					 * addUserNotificationEvent( toUserId, portletId, (new
					 * Date()).getTime(),
					 * UserNotificationDeliveryConstants.TYPE_WEBSITE, toUserId,
					 * payloadJSON.toString(), false, serviceContext[0]);
					 */

					// _log.info(serviceContext[0].getScopeGroupId());

					if (toUserId > 0) {
						UserNotificationEvent event = UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
								toUserId, portletId, (new Date()).getTime(),
								UserNotificationDeliveryConstants.TYPE_WEBSITE, toUserId, payloadJSON.toString(), false,
								serviceContext[0]);

						event.setDelivered(false);

						UserNotificationEventLocalServiceUtil.updateUserNotificationEvent(
							event);
					}
				}
				catch (Exception e) {
					_log.debug(e);
				}

				// send to zalo
				if (messageEntry.isSendZalo()) {
					String zaloUid =
						messageEntry.getMappingZaloUid().get(toUserId);
					sendZalo(
						messageEntry.getTextMessage(),
						messageEntry.getZaloAccessToken(), zaloUid);
				}
			}
		}
		else if (messageEntry.isSendZalo()) {

			// send to zalo
			String zaloUid = messageEntry.getMappingZaloUid().get(new Long(0));
			sendZalo(
				messageEntry.getTextMessage(),
				messageEntry.getZaloAccessToken(), zaloUid);
		}

	}

	private void sendZalo(
		String textMessage, String zaloAccessToken, String zaloUid) {

		try {

			if (Validator.isNotNull(zaloUid)) {

				String targetURL =
					"https://openapi.zalo.me/v2.0/oa/message?access_token=" +
						zaloAccessToken;

				JSONObject payloadJSON = JSONFactoryUtil.createJSONObject(
					"{\"recipient\":{\"user_id\":\"1893010867233038754\"}, \"message\":{\"text\":\"1893010867233038754\"}}");
				JSONObject recipient = JSONFactoryUtil.createJSONObject();
				JSONObject message = JSONFactoryUtil.createJSONObject();

				recipient.put("user_id", zaloUid);

				message.put("text", textMessage);

				payloadJSON.put("recipient", recipient);

				payloadJSON.put("message", message);

				postMessZalo(targetURL, payloadJSON.toJSONString());
			}

		}
		catch (JSONException e) {
			_log.error(e);
		}

	}

	private void postMessZalo(String url, String param) {

		try {
			String charset = "UTF-8";
			URLConnection connection = new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Content-Type", "application/json;");

			OutputStream output = connection.getOutputStream();
			output.write(param.getBytes(charset));

			connection.getInputStream();
			_log.info("Send zalo message success");
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	/*
	 * private UserNotificationEventLocalService
	 * userNotificationEventLocalService;
	 * @Reference(unbind = "-") public void
	 * setUserNotificationEventLocalService( UserNotificationEventLocalService
	 * userNotificationEventLocalService) {
	 * this.userNotificationEventLocalService =
	 * userNotificationEventLocalService; }
	 */
}
