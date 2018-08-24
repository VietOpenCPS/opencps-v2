
package org.opencps.kernel.message.notification;

import org.opencps.kernel.message.MBMessageEntry;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */

public class MBNotificationSenderFactoryUtil {

	public static void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContext) {

		// send notification
		MBNotificationSenderImpl impl = new MBNotificationSenderImpl();
		impl.send(messageEntry, portletId, serviceContext);

	}

}
