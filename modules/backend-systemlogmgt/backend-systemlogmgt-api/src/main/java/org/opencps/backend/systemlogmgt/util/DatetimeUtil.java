package org.opencps.backend.systemlogmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;

import java.sql.Timestamp;
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
	public static final String _YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

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
	public static String convertDatetoDateString(Date date) {
		if (date == null) {
			return null;
		}
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
         String strDate = dateFormat.format(date);  
         return strDate;
	}
	public static String convertDatetoDateTimeString(Date date) {
		if (date == null) {
			return null;
		}
		date.setSeconds(0);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);  
        return strDate;
	}

}
