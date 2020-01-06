package opencps.session.tracking.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Calendar;
import java.util.Date;

public class SessionTrackingUtil {
	
	private static Log _log = LogFactoryUtil.getLog(SessionTrackingUtil.class.getName());
	
	public final static long visitorDefault = 886688;
	
	public static Date roundDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
}

