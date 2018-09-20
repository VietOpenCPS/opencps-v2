package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author trungnt
 */
public class DateTimeUtils {

	public static String convertDateToString(Date date, String pattern) {

		DateFormat dateFormat =
			DateFormatFactoryUtil.getSimpleDateFormat(pattern);
		if (date == null || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}
		dateFormat.setTimeZone(TimeZoneUtil.getDefault());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
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

	public static Date convertStringToDate(String strDate) {

		DateFormat df = getDateTimeFormat(_VN_DATE_FORMAT);
		Date date = null;
		try {
			if (df != null && Validator.isNotNull(strDate)) {
				date = df.parse(strDate);
			}

		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
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
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
		}
		return date;
	}

	public static Date getDate(int day, int month, int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}
	
	public static Date getDateBeginOfDay(int day, int month, int year) {

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date getDateEndOfDay(int day, int month, int year) {

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTime();
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

	public static Calendar getInstance(Date date, int... ignores) {

		Calendar calendar = Calendar.getInstance();
		if (ignores != null && ignores.length > 0) {
			for (int f = 0; f < ignores.length; f++) {
				calendar.set(ignores[f], 0);
			}
		}
		return calendar;
	}
	
	public static int convertTimemilisecondsToDays(long time) {

		int days = 0;
		days = (int) (time / (24 * 60 * 60 * 1000));

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

	public static String convertTimemilisecondsToFormat(long time, Locale locale) {

		String format = new String(DATE_TIME_FORMAT);
//		String day = LanguageUtil.get(locale, DAY_PROPERTIES).toLowerCase();
		String day = "day";

		long diffMinutes = 0;
		long diffHours = 0;
		long diffDays = 0;

		diffMinutes = time / (60 * 1000) % 60;
		diffHours = time / (60 * 60 * 1000) % 24;
		diffDays = time / (24 * 60 * 60 * 1000);

		if (diffDays != 0) {
			
			format = format.replace(DAY, String.valueOf(Math.abs(diffDays)));
			format = format.replace(DAY_LANG, day);
			
		} else {
			
			format = format.replace(DAY, StringPool.BLANK);
			format = format.replace(DAY_LANG, StringPool.BLANK);
			
		}
		
		if (diffHours == 0 && diffMinutes == 0) {
			format = format.replace(SUB_DATE_TIME_FORMAT, StringPool.BLANK);
		} else {
			format = format.replace(HOUR, String.valueOf(Math.abs(diffHours)));
			format = format.replace(MINUTE,
					String.valueOf(Math.abs(diffMinutes)));
		}

		if (time > 0) {
//			format = LanguageUtil.get(locale, EARLY) + StringPool.SPACE
//					+ format.trim();
		} else if (time < 0) {
//			format = LanguageUtil.get(locale, LATE) + StringPool.SPACE
//					
//					+ format.trim();
		} else {
//			format = LanguageUtil.get(locale, ONTIME) + format.trim();
		}

		return format;
	}
	
	public DateTimeBean getDateTimeFromPattern(String pattern) {
		
		DateTimeBean dateTimeBean = new DateTimeBean();

		int Days = 0;
		int Hours = 0;
		int Minutes = 0;
		
		/* Pattern Format Example : "3 10:30" */
		
		if (pattern.trim().length() > 0) {

			String[] splitPattern = StringUtil.split(pattern, StringPool.SPACE);

			if (splitPattern.length == 1) {
				
				Days = GetterUtil.getInteger(splitPattern[0], 0);

			} else if (splitPattern.length == 2) {

				Days = GetterUtil.getInteger(splitPattern[0], 0);

				String[] splitHour = StringUtil.split(splitPattern[1],
						StringPool.COLON);

				if (splitHour.length == 2) {
					Hours = GetterUtil.getInteger(splitHour[0]);
					Minutes = GetterUtil.getInteger(splitHour[1]);
				} else if (splitHour.length == 1) {
					Hours = GetterUtil.getInteger(splitHour[0]);
				}

			}
		}
		dateTimeBean.setDays(Days);
		dateTimeBean.setHours(Hours);
		dateTimeBean.setMinutes(Minutes);
		
		
		return dateTimeBean;

	}
	
	public class DateTimeBean {

		public DateTimeBean() {

			this.Days = 0;
			this.Hours = 0;
			this.Minutes = 0;
		}

		protected int Days;
		protected int Hours;
		protected int Minutes;

		public int getDays() {
			return Days;
		}

		public void setDays(int days2) {
			Days = days2;
		}

		public int getHours() {
			return Hours;
		}

		public void setHours(int hours2) {
			Hours = hours2;
		}

		public int getMinutes() {
			return Minutes;
		}

		public void setMinutes(int minutes2) {
			Minutes = minutes2;
		}
	}
	
	private static final String DAY = "{d}";
	
	private static final String DAY_LANG = "{D}";
	
//	private static final String DAY_PROPERTIES = "day";
	
	private static final String HOUR = "{HH}";
	
	private static final String MINUTE = "{MM}";
	
//	private static final String EARLY = "status-soon";
//	
//	private static final String LATE = "status-late";
//	
//	private static final String ONTIME = "status-ontime";
	
	private static final String DATE_TIME_FORMAT = "{d} {D} {HH}:{MM}";
	
	private static final String SUB_DATE_TIME_FORMAT = "{HH}:{MM}";

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String _VN_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static final String _VN_DATE_FORMAT = "dd/MM/yyyy";

	public static final String _EMPTY_DATE_TIME = "__/__/__";
	
	public static final String _DATE_TIME_TO_NAME = "yyyyMMdd";

	private static Log _log = LogFactoryUtil.getLog(DateTimeUtils.class);

	private static volatile DateTimeBean DateTimeBean;

	public static DateTimeBean getDateTimeBean() {
		return DateTimeBean;
	}

	public static void setDateTimeBean(DateTimeBean dateTimeBean) {
		DateTimeBean = dateTimeBean;
	}

}