
package org.opencps.kernel.message.email;

import backend.service.IntegrateLgsp;
import backend.service.impl.IntegrateLgspImpl;
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

	public static void sendLGSP(
			MBMessageEntry messageEntry, String portletId,
			ServiceContext... serviceContext) {

			MBEmailSenderImpl impl = new MBEmailSenderImpl();
			impl.sendLGSP(messageEntry, portletId, serviceContext);

		}

	public static void send(MBMessageEntry messageEntry, String contactEmail) {
		IntegrateLgsp integrateLgsp = new IntegrateLgspImpl();
		MBEmailSender emailSender = new MBEmailSenderImpl(integrateLgsp);
		emailSender.send(messageEntry, contactEmail);
	}
}
