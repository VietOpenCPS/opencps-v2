package org.opencps.usermgt.lifecycle;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.usermgt.action.util.UserMgtConfigUtil;
import org.opencps.usermgt.service.UserLoginLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "key=login.events.post" }, service = LifecycleAction.class)
public class OpenCPSLoginAction extends Action {
	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
//        System.out.println("### Start Post Login Action ######################");
		if (UserMgtConfigUtil.isTrackUserEnable()) {
			Long userId = request.getAttribute("USER_ID") != null ? (Long) request.getAttribute("USER_ID") : 0;

			User user = UserLocalServiceUtil.fetchUser(userId);
			String sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
			String clientIP = request.getHeader("X-FORWARDED-FOR");
			if (clientIP == null) {
				clientIP = request.getRemoteAddr();
			}

			try {
				UserLoginLocalServiceUtil.updateUserLogin(user.getCompanyId(), user.getGroupId(), userId,
						user.getFullName(), new Date(), new Date(), 0l, sessionId, 0, null, clientIP);
			} catch (SystemException e) {
				_log.debug(e);
			} catch (PortalException e) {
				_log.debug(e);
			}
		}

//        System.out.println("### End Post Login Action ######################");
	}

	private static Log _log = LogFactoryUtil.getLog(OpenCPSLoginAction.class);
}
