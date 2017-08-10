package org.opencps.auth.api;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

public class BackendUtils {
	
	/**
	 * @author khoavu
	 * @param req
	 * @return
	 */
	public static ServiceContext getServiceContext(HttpServletRequest req) {
		ServiceContext context = new ServiceContext();

		try {
			context = ServiceContextFactory.getInstance(req);
		} catch (Exception e) {
			_log.error(e);
		}

		return context;
	}
	
	static Log _log = LogFactoryUtil.getLog(BackendUtils.class);
}
