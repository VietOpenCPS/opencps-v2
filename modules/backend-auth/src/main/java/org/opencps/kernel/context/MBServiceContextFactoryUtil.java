
package org.opencps.kernel.context;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author trungnt
 *
 */
public class MBServiceContextFactoryUtil {

	public static ServiceContext create(
		long companyId, long groupId, long... userId) {

		MBServiceContext context =
			new MBServiceContext(companyId, groupId, userId);
		
		return context.getServiceContext();
	}
	
	public static ServiceContext createWidthSignedState(
		long companyId, long groupId, Object... user) {

		MBServiceContext context =
			new MBServiceContext(companyId, groupId, user);
		
		return context.getServiceContext();
	}
}
