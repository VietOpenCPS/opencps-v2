package org.opencps.dossiermgt.action.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.util.HolidayUtils;

import com.liferay.portal.kernel.dao.orm.QueryUtil;

public class DossierOverDueUtils {
	public static final int START_HOUR_OF_DAY = 8;

	public static final int END_HOUR_OF_DAY = 18;

	public static final int END_MINUTE_OF_DAY = 0;

	public static final int START_MINUTE_OF_DAY = 0;

	public static final int START_NAP_HOUR_OF_DAY = 12;

	public static final int END_NAP_HOUR_OF_DAY = 13;

	public static final int START_NAP_MINUTE_OF_DAY = 0;

	public static final int END_NAP_MINUTE_OF_DAY = 0;

	public static Date getStepOverDue(long groupId, int overDuePoint, Date date) {
		return HolidayUtils.getDueDate(date, 0, 0, groupId);
//		return HolidayUtils.getDueDate(date, overDuePoint, 0, groupId);
	}
	
	public static String getEstimateDate(int processingDay) {
		Date now = new Date();
		int start = -1, end = -1;

		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DATE, processingDay);

		List<Holiday> lstHoliday = HolidayLocalServiceUtil.getHolidaies(start, end);

		for (Holiday holiday : lstHoliday) {
			Date holidaydate = holiday.getHolidayDate();
			if ((holidaydate.compareTo(now) > 0 && holidaydate.compareTo(c.getTime()) < 0)
					|| holidaydate.compareTo(c.getTime()) == 0) {
				processingDay++;
			}
		}

		c.setTime(now);
		c.add(Calendar.DATE, processingDay);
		Date estimateDate = c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormat.format(estimateDate);
	}

//	public static void main(String[] args) {
//		System.out.println(calculateEndDate(new Date(), 5));
//		
//	}
	public static Date calculateEndDate(Date startDate, int duration) {
		Calendar startCal = Calendar.getInstance();

		startCal.setTime(startDate);
		
		for (int i = 1; i < duration; i++) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			while (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				startCal.add(Calendar.DAY_OF_MONTH, 1);
			}
		}

		return startCal.getTime();
	}

	public static int calculateDuration(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workDays++;
			}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		return workDays;
	}
	
	private static boolean isHoliday(Date checkDate) {

		boolean isHoliday = false;

		List<Holiday> holidays;

		holidays = HolidayLocalServiceUtil.getHolidaies(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Holiday holiday : holidays) {
			if (equalDay(checkDate, holiday.getHolidayDate()) == 0) {
				isHoliday = true;

				break;
			}
		}

		return isHoliday;
	}

	private static int equalDay(Date date1, Date date2) {
		return truncateToDay(date1).compareTo(date2);
	}

	private static Date truncateToDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date calculateEndDateUsingHoliday(Date startDate, int duration) {
		Calendar startCal = Calendar.getInstance();

		startCal.setTime(startDate);
		
		for (int i = 1; i < duration; i++) {
			while (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				startCal.add(Calendar.DAY_OF_MONTH, 1);
			}
			while (isHoliday(startCal.getTime())) {
				startCal.add(Calendar.DAY_OF_MONTH, 1);
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}

		return startCal.getTime();
	}
}
