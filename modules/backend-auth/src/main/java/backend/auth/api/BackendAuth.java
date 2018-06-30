package backend.auth.api;

import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author khoavu
 */
public interface BackendAuth {
	
	/**
	 * @author trungnt
	 * @param context
	 * @param modelName
	 * @return
	 */
	public boolean isAdmin(ServiceContext context, String modelName);
	
	/**
	 * @param context
	 * @param security
	 * @param password
	 * @return
	 */
	public boolean isAuth(ServiceContext context, String security, String password);
	
	/**
	 * @param context
	 * @param modelName
	 * @param actionId
	 * @return
	 */
	public boolean hasResource(ServiceContext context, String modelName, String actionId);
	
	/**
	 * @param context
	 * @param modelName
	 * @param actionId
	 * @return
	 */
	public boolean userHasResource(ServiceContext context, String modelName, String actionId);

}