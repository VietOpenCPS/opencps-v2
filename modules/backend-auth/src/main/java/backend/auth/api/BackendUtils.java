package backend.auth.api;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;

import javax.servlet.http.HttpServletRequest;

public class BackendUtils {
	
	/**
	 * @author khoavu
	 * @param req
	 * @return
	 */
	public static ServiceContext getServiceContext(HttpServletRequest req) {
		ServiceContext context = new ServiceContext();

		try {
			context = ServiceContextFactory.getInstance(req);
		} catch (PortalException e) {
			_log.error(e);
		}

		return context;
	}
	
	/**
	 * @author khoavu
	 * @param security
	 * @return
	 */
	/*
	public static Activity getActivity(String security) {
		Activity activity = null;

		try {
			activity = ActivityLocalServiceUtil.getBySecurity(security);
		} catch (Exception e) {
			_log.error(e);
		}

		return activity;
	}
	
	/**
	 * @param activityId
	 * @param password
	 * @return
	 */
	
	/*
	public static Participant getParticipant(long activityId, String password) {
		Participant participant = null;
		
		try {
			participant = ParticipantLocalServiceUtil.getByActivityAndPassword(activityId, password);
		} catch (Exception e) {
			_log.error(e);
		}
		
		return participant;
	}
	
	*/
	
	static Log _log = LogFactoryUtil.getLog(BackendUtils.class);
}
