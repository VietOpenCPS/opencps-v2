package org.opencps.auth.api;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author khoavu
 */
public interface BackendAuth {
	
	/**
	 * @param context
	 * @param security
	 * @param password
	 * @return
	 */
	public boolean isAuth(ServiceContext context);
	
	/**
	 * @param context
	 * @param modelName
	 * @param actionId
	 * @return
	 */
	public boolean hasResource(ServiceContext context, String modelName, String actionId);

}