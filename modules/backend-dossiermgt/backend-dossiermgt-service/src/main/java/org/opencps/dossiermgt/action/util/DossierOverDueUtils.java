package org.opencps.dossiermgt.action.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;

public class DossierOverDueUtils {

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
			if ((holidaydate.compareTo(now) > 0 && holidaydate.compareTo(c.getTime())<0) || holidaydate.compareTo(c.getTime()) == 0) {
				processingDay++;
			}
		}

		c.setTime(now);
		c.add(Calendar.DATE, processingDay);
		Date estimateDate = c.getTime();

		return dateFormat.format(estimateDate);
	}

	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
}
