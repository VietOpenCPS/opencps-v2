
package org.opencps.kernel.message.notification;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.Validator;

import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.opencps.auth.api.keys.Constants;
import org.opencps.auth.api.keys.ModelNameKeys;
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
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_USER_ID, messageEntry.getUserId());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_TITLE, messageEntry.getEmailSubject());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_SENDER_NAME, messageEntry.getFromName());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_NOTIFICATION_TEXT, messageEntry.getTextMessage());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_USER_URL, messageEntry.getUserUrl());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_GUEST_URL, messageEntry.getGuestUrl());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_DATA, messageEntry.getData());
		payloadJSON.put(ModelNameKeys.MB_SENDER_PAYLOAD_NOTIFY_MESSAGE, messageEntry.getNotifyMessage());

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
					ModelNameKeys.MB_SENDER_SEND_ZALO_POST_URL +
						zaloAccessToken;

				// "{\"recipient\":{\"user_id\":\"X\"}, \"message\":{\"text\":\"X\"}}"
				JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();
				JSONObject recipient = JSONFactoryUtil.createJSONObject();
				JSONObject message = JSONFactoryUtil.createJSONObject();

				recipient.put(ModelNameKeys.MB_SENDER_SEND_ZALO_USER_ID, zaloUid);

				message.put(ModelNameKeys.MB_SENDER_SEND_ZALO_TEXT, textMessage);

				payloadJSON.put(ModelNameKeys.MB_SENDER_SEND_ZALO_RECIPIENT, recipient);

				payloadJSON.put(ModelNameKeys.MB_SENDER_SEND_ZALO_MESSAGE, message);

				postMessZalo(targetURL, payloadJSON.toJSONString());
			}

		}
		catch (Exception e) {
			_log.error(e);
		}

	}

	private void postMessZalo(String url, String param) {

		try {
			URLConnection connection = new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

			OutputStream output = connection.getOutputStream();
			output.write(param.getBytes(Constants.HTTP_CHARSET));

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
