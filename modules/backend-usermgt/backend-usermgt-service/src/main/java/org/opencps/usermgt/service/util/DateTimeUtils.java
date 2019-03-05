package org.opencps.usermgt.service.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String _TYPEDATE = "dd/MM/yyyy";
	public static final String _TYPEDATE2 = "yyyyMMdd";
	public static final String _TYPEDATETIME = "HH:mm dd/MM/yyyy";
	public static final String _TYPEDATETIME2 = "yyyyMMddHHmm";

	public static Date stringToDate(String dateString) {
		Date output = null;
		SimpleDateFormat sdf = null;

		try {

			if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
				sdf = new SimpleDateFormat(_TYPEDATE);
			else if (dateString.matches("([0-9]{8})"))
				sdf = new SimpleDateFormat(_TYPEDATE2);
			else if (dateString.matches("([0-9]{2}):([0-9]{2}) ([0-9]{2})/([0-9]{2})/([0-9]{4})"))
				sdf = new SimpleDateFormat(_TYPEDATETIME);
			else if (dateString.matches("([0-9]{12})"))
				sdf = new SimpleDateFormat(_TYPEDATETIME2);
			else
				return output;

			output = sdf.parse(dateString);
			
		} catch (ParseException e) {
			_log.error("DateTimeUtils parse string to date error: ");
			_log.error(e);
		}

		return output;
	}

	public static Date stringToDate(String dateString, String parttern) {
		Date output = null;

		SimpleDateFormat sdf = new SimpleDateFormat(parttern);

		try {
			output = sdf.parse(dateString);
		} catch (ParseException e) {
			_log.error(e);
		}

		return output;
	}

	public static String dateToString(Date date, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	public static String dateToString(Date date) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(_TIMESTAMP);

		if (Validator.isNull(date) || Validator.isNull(_TIMESTAMP)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	private static Log _log = LogFactoryUtil.getLog(DateTimeUtils.class);
}
