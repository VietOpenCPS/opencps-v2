package org.opencps.usermgt.lifecycle;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { "key=servlet.session.destroy.events" }, service = LifecycleAction.class)
public class OpenCPSSessionAction implements LifecycleAction {
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		// final HttpSession session = lifecycleEvent.getSession();
		lifecycleEvent.getSession();

	}
}
