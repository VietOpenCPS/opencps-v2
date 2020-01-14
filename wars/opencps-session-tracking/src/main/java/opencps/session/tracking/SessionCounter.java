package opencps.session.tracking;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.ArrayList;

public class SessionCounter implements HttpSessionListener {
	private static List<String> sessions = new ArrayList<String>();

	public SessionCounter() {
		super();
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.add(session.getId());
		session.setAttribute("counter", this);
		session.setAttribute("incrementCount", true);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId());
		session.setAttribute("counter", this);
	}

	public int getActiveSessionNumber() {
		return sessions.size();
	}

	private Log _log = LogFactoryUtil.getLog(SessionCounter.class.getName());
}
