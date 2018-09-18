package org.opencps.communication.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binhth
 * DateTimeUtils.class
 */
public class DateTimeUtils {

	public static final String _DATE_TIME_TO_NAME = "yyyyMMdd";

	public static final String _EMPTY_DATE_TIME = "__/__/__";

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String _VN_DATE_FORMAT = "dd/MM/yyyy";

	public static final String _VN_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public static final String _VN_DATE_TIME_FORMAT_HOUR = "dd/MM/yyyy HH:mm";

	public static final String _VN_TIME_FORMAT = "hh:mm a";
	
	public static final String _VN_TIME_FORMAT_HOUR = "HH:mm";
	
	public static String convertDateToString(Date date, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	public static Date convertStringToDateAPI(String strDate) {
		DateFormat df = getDateTimeFormat(_DATE_TIME_TO_NAME);
		Date date = null;

		try {
			if (df != null && Validator.isNotNull(strDate)) {
				date = df.parse(strDate);
			}
		}
		catch (ParseException pe) {
			_log.debug(pe);
			//_log.error(pe);
		}

		return date;
	}
	
	public static Date convertStringToDate(String strDate) {
		DateFormat df = getDateTimeFormat(_VN_DATE_FORMAT);
		Date date = null;

		try {
			if (df != null && Validator.isNotNull(strDate)) {
				date = df.parse(strDate);
			}
		}
		catch (ParseException pe) {
			_log.debug(pe);
			//_log.error(pe);
		}

		return date;
	}

	public static Date convertStringToFullDate(String strDate) {
		DateFormat df = getDateTimeFormat(_VN_DATE_TIME_FORMAT);
		Date date = null;

		try {
			if (df != null && Validator.isNotNull(strDate)) {
				date = df.parse(strDate);
			}
		}
		catch (ParseException pe) {
			_log.debug(pe);
			//_log.error(pe);
		}

		return date;
	}
	
	public static Date convertDateTimeToString(String strDate) {
		DateFormat df = getDateTimeFormat(_VN_DATE_TIME_FORMAT_HOUR);
		Date date = null;

		try {
			if (df != null && Validator.isNotNull(strDate)) {
				date = df.parse(strDate);
			}
		}
		catch (ParseException pe) {
			_log.debug(pe);
			//_log.error(pe);
		}

		return date;
	}

	public static int convertTimemilisecondsToDays(long time) {
		int days = 0;

		days = (int)(time / (24 * 60 * 60 * 1000));

		return days;
	}

	public static long convertTimemilisecondsToHours(long time) {
		long hours = 0;

		hours = time / (60 * 60 * 1000);

		return hours;
	}

	public static long convertTimemilisecondsToMinutes(long time) {
		long minutes = 0;

		minutes = time / (60 * 1000);

		return minutes;
	}

	public static long convertTimemilisecondsToSeconds(long time) {
		long seconds = 0;

		seconds = time / 1000;

		return seconds;
	}

	public static Date getDate(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}

	public static Date getDateNow() {
		return new Date();
	}

	public static DateFormat getDateTimeFormat(String pattern) {

//		DateFormat dateFormat =
//			DateFormatFactoryUtil.getSimpleDateFormat(pattern);

		if (Validator.isNotNull(pattern)) {
//			pattern = _VN_DATE_TIME_FORMAT;
			DateFormat dateFormat =
					DateFormatFactoryUtil.getSimpleDateFormat(_VN_DATE_TIME_FORMAT);
			dateFormat.setTimeZone(TimeZoneUtil.getDefault());

			return dateFormat;
		}

//		dateFormat.setTimeZone(TimeZoneUtil.getDefault());
		// dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//		return dateFormat;
		return null;
	}

	public static Calendar getInstance(Date date, int... ignores) {
		Calendar calendar = Calendar.getInstance();

		if (ignores.length > 0) {
			for (int f = 0; f < ignores.length; f++) {
				calendar.set(ignores[f], 0);
			}
		}

		return calendar;
	}

	public static String getStringDate() {
		Calendar calendar = Calendar.getInstance();

		StringBuffer sb = new StringBuffer();

		int month = calendar.get(Calendar.MONTH) + 1;

		int day = calendar.get(Calendar.DAY_OF_MONTH);

		sb.append(calendar.get(Calendar.YEAR));

		if (month < 10) {
			sb.append(0);
			sb.append(month);
		}
		else {
			sb.append(month);
		}

		if (day < 10) {
			sb.append(0);
			sb.append(day);
		}
		else {
			sb.append(day);
		}

		return sb.toString();
	}

	public static int getDayFromDate(Date date) {

		int day = 0;

		if (date != null) {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);
			day += calendar.get(Calendar.DAY_OF_MONTH);

//			calendar.setTime(date);
//			day = calendar.get(Calendar.DAY_OF_MONTH);
//			return day;
		} else {
			day += 1;
		}

		return day;
	}

	public static int getMonthFromDate(Date date) {

		int month = 0;

		if (date != null) {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);
			month += calendar.get(Calendar.MONTH);

//			calendar.setTime(date);
//			month = calendar.get(Calendar.MONTH);
//			return month;
		} else {
			month += 1;
		}

		return month;
	}

	public static int getYearFromDate(Date date) {

//		int year = 1990;
		int year = 0;

		if (date != null) {
			Calendar calendar = Calendar.getInstance();

			calendar.setTime(date);
			year += calendar.get(Calendar.YEAR);

//			calendar.setTime(date);
//			year = calendar.get(Calendar.YEAR);
		} else {
			year += 1990;
		}

		return year;
	}

	public static int daysBetween(Date date1, Date date2){
	    Calendar dayOne =Calendar.getInstance();
	    dayOne.setTime(date1);
	    Calendar dayTwo =Calendar.getInstance();
	    dayOne.setTime(date2);

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	        return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
	    }
	}
	
	public static String[] hoursBetween(Date date1, Date date2){
		//in milliseconds
		long diff = date2.getTime() - date1.getTime();

		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;

	    return new String[]{String.valueOf(diffHours), String.valueOf(diffMinutes)} ;
	}
	
	public static long hourBetween(Date date1, Date date2){
		//in milliseconds
		long diff = date2.getTime() - date1.getTime();

		long diffHours = diff / (60 * 60 * 1000) % 24;

	    return diffHours ;
	}
	
	public static long minuteBetween(Date date1, Date date2){
		//in milliseconds
		long diff = date2.getTime() - date1.getTime();

		long diffMinutes = diff / (60 * 1000) % 60;

	    return diffMinutes ;
	}
	
	public static String getMailSendDate() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(new Date());
		
		return "Ng\u00E0y "+ cal.get(Calendar.DAY_OF_MONTH) + " th\u00E1ng " + ( cal.get(Calendar.MONTH) + 1 ) + " n\u0103m " + cal.get(Calendar.YEAR) ;
		
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(DateTimeUtils.class);

}