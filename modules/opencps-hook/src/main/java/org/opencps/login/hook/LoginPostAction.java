package org.opencps.login.hook;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * 
 * @author binhth
 * @see LoginPostAction.class
 */
@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class LoginPostAction implements LifecycleAction {
	private static final Log _log = LogFactoryUtil.getLog(LoginPostAction.class);
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		User user;
		try {
			user = PortalUtil.getUser(lifecycleEvent.getRequest());
			
			PrincipalThreadLocal.setName(user.getUserId());

			PermissionChecker permissionChecker;

			permissionChecker = PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			String friendlyUrl = user.getSiteGroups().get(0).getFriendlyURL();

			lifecycleEvent.getResponse().sendRedirect("/web" + friendlyUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			_log.error(e);
		}

	}

}
