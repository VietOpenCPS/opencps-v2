package org.opencps.usermgt.lifecycle;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "key=servlet.session.destroy.events" }, service = LifecycleAction.class)
public class OpenCPSSessionAction extends SessionAction {	
	private static Log _log = LogFactoryUtil.getLog(OpenCPSSessionAction.class);

	@Override
	public void run(HttpSession session) throws ActionException {
		_log.info("### START SESSION Trace Action ######################");
		_log.info("SESSION ACTION TIMEOUT: " + session.getId());		
		_log.info("### START SESSION Trace Action ######################");
	}
}
