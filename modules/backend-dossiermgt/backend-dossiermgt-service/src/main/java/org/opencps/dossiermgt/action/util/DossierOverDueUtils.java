package org.opencps.dossiermgt.action.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;

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

	
	public static Date getStepOverDue(int overDuePoint, Date date) {

		// TODO add logic here
		return null;
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

		return dateFormat.format(estimateDate);
	}
	
	private int hourRemaining(Date checkDate) {
		
	}
	
	private int minuteRemaining(Date checkDate) {
		
	}
	
	private boolean isWorkingDay(Date checkDate) {
		boolean isWorkingDay = false;
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(checkDate);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		if (dayOfWeek < 6) {
			isWorkingDay = true;
		}
		
		return isWorkingDay;
	}

	private boolean isHoliday(Date checkDate) {
		
		boolean isHoliday = false;
		
		List<Holiday> holidays = new ArrayList<Holiday>();
		
		holidays = HolidayLocalServiceUtil.getHolidaies(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		for (Holiday holiday : holidays) {
			if (equalDay(checkDate, holiday.getHolidayDate()) == 0) {
				isHoliday = true;
				
				break;
			}
		}
		
		return isHoliday;
	}
	
	private int equalDay(Date date1, Date date2) {
		return truncateToDay(date1).compareTo(date2);
	}

	private Date truncateToDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
}
