package org.opencps.usermgt.sso.authentication;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component(immediate = true, service = AutoLogin.class)
public class MICSSOAutoLogin extends BaseAutoLogin {
    private String _SESSION_API_PRIFIX = "equinox.http.rest.v2";
    private Log _log = LogFactoryUtil.getLog(MICSSOAutoLogin.class.getName());
    private UserLocalService _userLocalService;

    @Reference(unbind = "-")
    protected void setUserLocalService(UserLocalService userLocalService) {
        _userLocalService = userLocalService;
    }

    @Override
    protected String[] doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = getUser(request);
        if(user == null) {
            return null;
        }

        _log.debug("--->>> MICSSOAutoLogin");

        String[] credentials = new String[3];

        credentials[0] = String.valueOf(user.getUserId());
        credentials[1] = user.getPassword();
        credentials[2] = Boolean.FALSE.toString();

        return credentials;
    }

    protected User getUser(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String identityUser = (String) session.getAttribute(_SESSION_API_PRIFIX + "_EMPLOYEE_NO");

            if(Validator.isNull(identityUser) || identityUser.isEmpty()) {
                throw new Exception("Not found identity user in session");
            }

            final int EMPLOYEE_ACTIVE = 1;
            Employee employee = EmployeeLocalServiceUtil.findByWorkingStatusAndEmployeeNo(EMPLOYEE_ACTIVE, identityUser);
            if(Validator.isNull(employee)) {
                throw new Exception("Not found employee with identity: " + identityUser);
            }
            return _userLocalService.fetchUser(employee.getMappingUserId());
        } catch (Exception e) {
            _log.debug("Error when get user in auto login: " + e.getMessage());
            return null;
        }
    }
}
