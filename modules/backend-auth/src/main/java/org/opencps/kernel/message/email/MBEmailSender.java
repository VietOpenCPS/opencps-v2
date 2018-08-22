
package org.opencps.kernel.message.email;

import org.opencps.kernel.message.MBMessageEntry;
import org.opencps.kernel.message.MBMessageSender;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public interface MBEmailSender extends MBMessageSender {

	@Override
	default void send(
		MBMessageEntry messageEntry, String portletId,
		ServiceContext... ServiceContext) {

	}

}
