
package backend.auth.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class BackendAuthImpl implements BackendAuth {

	Log _log = LogFactoryUtil.getLog(BackendAuthImpl.class);

	@Override
	public boolean hasResource(
		ServiceContext context, String name, String actionId) {

		boolean hasPermission = false;
		List<Role> roles = RoleLocalServiceUtil.getUserRoles(context.getUserId());
		try {
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					// LamTV_Fix sonarqube
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

	@Override
	public boolean userHasResource(
		ServiceContext context, String name, String actionId) {

		boolean hasPermission = false;
		List<Role> roles = RoleLocalServiceUtil.getUserRoles(context.getUserId());

		try {
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
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
	
	@Override
	public boolean isAdmin(ServiceContext context, String modelName) {

		boolean isAdmin = false;

		List<Role> roles = RoleLocalServiceUtil.getUserRoles(context.getUserId());
		try {
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					// LamTV_Fix sonarqube
					if ("Administrator".equals(role.getName())) {
						isAdmin = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		return isAdmin;
	}

	@Override
	public boolean isAuth(ServiceContext context, String security, String password) {

		boolean isAuth = false;

		if (Validator.isNotNull(security)) {

		} else {
			isAuth = context.isSignedIn();
		}

		return isAuth;
	}

	@Override
	public boolean checkToken(HttpServletRequest request, HttpHeaders header) {
		
//		String tokenFromSession = (String) request.getSession().getAttribute(CSRF_TOKEN_FOR_SESSION_NAME);
//		
//		_log.info("TOKEN***SESSION:"+tokenFromSession);
//		
//		String tokenFromRequest = header.getHeaderString(CPS_AUTH);
//		_log.info("TOKEN***REQUEST:"+tokenFromRequest);
//
//		if (tokenFromRequest.contentEquals(tokenFromSession)) {
		return true;
//		}
		
//		return false;
	}
}
