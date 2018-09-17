
package org.opencps.kernel.context;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author trungnt
 *
 */
public class MBServiceContextThreadLocal {

	public static ServiceContext getServiceContext() {

		return _serviceContext.get();
	}

	public static void setServiceContext(ServiceContext serviceContext) {

		_serviceContext.set(serviceContext);
	}

	@SuppressWarnings("deprecation")
	private static final ThreadLocal<ServiceContext> _serviceContext =
		new AutoResetThreadLocal<>(
			PortalSessionThreadLocal.class + "._serviceContext");
}
