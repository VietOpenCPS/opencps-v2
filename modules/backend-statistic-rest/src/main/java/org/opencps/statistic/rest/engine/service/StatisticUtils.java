package org.opencps.statistic.rest.engine.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class StatisticUtils {

	public static final String DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm:ss";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	private static Log _log = LogFactoryUtil.getLog(StatisticUtils.class);

	public static Date convertStringToDate(String source) {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
		if (sdf != null && Validator.isNotNull(source)) {
			try {
				return sdf.parse(source);

			} catch (ParseException e) {
				_log.error(e);
			}
		}
		return null;
	}

	public static Date convertStringToDate(String source, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (sdf != null && Validator.isNotNull(source)) {
			try {
				return sdf.parse(source);

			} catch (ParseException e) {
				_log.error(e);
			}
		}
		return null;
	}

	public static Date getFirstDay(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		//Set calendar first of month
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getLastDay(int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);
		//Set calendar first of month
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getStartDay(Date statisticDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(statisticDate);
		//Set calendar first of month
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		return cal.getTime();
	}

	public static Date getEndDay(Date statisticDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(statisticDate);
		//Set calendar first of month
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
		return cal.getTime();
	}

}
