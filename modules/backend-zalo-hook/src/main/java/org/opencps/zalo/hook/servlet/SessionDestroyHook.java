package org.opencps.zalo.hook.servlet;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;

@Component(immediate = true, property = { "key=servlet.session.destroy.events" }, service = LifecycleAction.class)
public class SessionDestroyHook extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		System.out.println("### Start SessionDestroyHook Action ######################");

		for (Enumeration<String> names = request.getAttributeNames(); names.hasMoreElements();) {
			String name = names.nextElement();
			Object value = request.getAttribute(name);
			System.out.println("A - " + name + " : " + value);
		}

		System.out.println("### End SessionDestroyHook Action ######################");
	}
}