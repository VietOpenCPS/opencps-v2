package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author trungnt
 *
 */
public class DatetimeUtil {
	public static final String _YYYY_MM_DD = "yyyy-MM-dd";

	public static final String _DD_MM_YYYY = "dd/MM/yyyy";

	public static final String _VN_TIME_ZONE = "Asia/Ho_Chi_Minh";

	public static String convertTimestampToStringDatetime(long time, String pattern) {
		if (time <= 0) {
			return StringPool.BLANK;
		}
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

	public static String convertDateToString(Date date, String format) {
		if (date == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static int getCurrentYear() {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		return c.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		return c.get(Calendar.MONTH) + 1;
	}

	public static int getCurrentDay() {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		return c.get(Calendar.DAY_OF_YEAR);
	}

}
