package org.opencps.zalo.hook.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

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
	private static final Log _log = LogFactoryUtil.getLog(DatetimeUtil.class);

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
	public static String convertDateToString(Date date) {
		return convertDateToString(date, _YYYY_MM_DD_HH_MM_SS);
	}

	public static String convertDateToString(Date date, String pattern) {
		try {
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
					pattern);

			if (Validator.isNull(date) || Validator.isNull(pattern)) {
				return StringPool.BLANK;
			}

			dateFormat.setTimeZone(TimeZoneUtil.getTimeZone(_VN_TIME_ZONE));

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);

			return dateFormat.format(calendar.getTime());
		} catch(Exception e) {
			_log.debug(e);
			return StringPool.BLANK;
		}
	}

}
