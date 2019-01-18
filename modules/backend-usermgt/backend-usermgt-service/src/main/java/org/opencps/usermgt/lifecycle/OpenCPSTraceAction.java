package org.opencps.usermgt.lifecycle;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencps.usermgt.model.UserLogin;
import org.opencps.usermgt.service.UserLoginLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

@Component (
	    immediate = true,
	    property = {
	        "key=servlet.service.events.pre"
	    },
	    service = LifecycleAction.class
	)
public class OpenCPSTraceAction extends Action {
    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        System.out.println("### Start Post Trace Action ######################");
      
        Long userId = request.getAttribute("USER_ID") != null ? (Long)request.getAttribute("USER_ID") : 0;
        
		String sessionId = request.getSession() != null ? request.getSession().getId() : StringPool.BLANK;
		try {
			UserLogin userLogin = UserLoginLocalServiceUtil.fetchByU_S(userId, sessionId);
			if (userLogin != null) {
				userLogin.setHits(userLogin.getHits() + 1);
				userLogin.setLogout(new Date());
				userLogin.setModifiedDate(new Date());
				UserLoginLocalServiceUtil.updateUserLogin(userLogin);
			}
		} catch (SystemException e) {
		} 
		
        System.out.println("### End Post Trace Action ######################");
    }
}
