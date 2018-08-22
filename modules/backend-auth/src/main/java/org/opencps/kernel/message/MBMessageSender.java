
package org.opencps.kernel.message;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 */
public interface MBMessageSender {

	void send(
		MBMessageEntry messageEntry, String type,
		ServiceContext... ServiceContext);
}
