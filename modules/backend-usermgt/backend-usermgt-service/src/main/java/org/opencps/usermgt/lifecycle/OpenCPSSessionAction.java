package org.opencps.usermgt.lifecycle;

import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpSession;

import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.service.TrackClientLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Date;

//@Component(immediate = true, property = { "key=servlet.session.destroy.events" }, service = LifecycleAction.class)
public class OpenCPSSessionAction extends SessionAction {	
	private static final Log _log = LogFactoryUtil.getLog(OpenCPSSessionAction.class);

	@Override
	public void run(HttpSession session) {
		TrackClient previousPage = TrackClientLocalServiceUtil.findPreviousPage(session.getId());
		if (previousPage != null)
		{
			Date now = new Date();
			long timeOnPage = now.getTime() - previousPage.getVisitDate().getTime();
			previousPage.setLeaveDate(now);
			previousPage.setTimeOnPage(timeOnPage);

			TrackClientLocalServiceUtil.updateTrackClient(previousPage);
		}
	}
}
