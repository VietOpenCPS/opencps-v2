
package org.opencps.kernel.message.email;

import org.opencps.kernel.message.MBMessageEntry;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */

public class MBEmailSenderFactoryUtil {

	public static void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... serviceContext) {

		MBEmailSenderImpl impl = new MBEmailSenderImpl();
		impl.send(messageEntry, portletId, serviceContext);

	}

}
