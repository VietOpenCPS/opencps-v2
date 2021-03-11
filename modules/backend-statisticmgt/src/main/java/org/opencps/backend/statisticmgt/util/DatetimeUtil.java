package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.util.DateFormatFactoryUtil;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author trungnt
 *
 */
public class DatetimeUtil {
	public static final String _YYYY_MM_DD = "yyyy-MM-dd";

	public static final String _VN_TIME_ZONE = "Asia/Ho_Chi_Minh";

	public static String convertTimestampToStringDatetime(long time, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(pattern);

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(time);

		calendar.setTimeZone(TimeZone.getTimeZone(_VN_TIME_ZONE));

		dateFormat.setTimeZone(TimeZone.getTimeZone(_VN_TIME_ZONE));

		return dateFormat.format(calendar.getTime());
	}

	public static long convertDateToTimestamp(Date date) {

		if (date == null) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.getTimeInMillis();
	}

}
