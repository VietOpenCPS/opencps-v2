package org.opencps.auth.api;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

public class BackendAuthImpl implements BackendAuth {

	@Override
	public boolean isAuth(ServiceContext context) {

		boolean isAuth = context.isSignedIn();

		return isAuth;
	}

	@Override
	public boolean hasResource(ServiceContext context, String name, String actionId) {

		boolean hasPermission = false;
		List<Role> roles = RoleLocalServiceUtil.getUserRoles(context.getUserId());

		try {
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					if ("Administrator".equals(role.getName())) {
						hasPermission = true;
						break;
					}

					hasPermission = ResourcePermissionLocalServiceUtil.hasResourcePermission(context.getCompanyId(),
							name, ResourceConstants.SCOPE_INDIVIDUAL, Long.toString(role.getRoleId()), role.getRoleId(),
							actionId);

					if (hasPermission) {
						break;
					}
				}
			}
		} catch (PortalException e) {
			_log.error(e);
		}
		
		return hasPermission;
	}

	Log _log = LogFactoryUtil.getLog(BackendAuthImpl.class);

}
