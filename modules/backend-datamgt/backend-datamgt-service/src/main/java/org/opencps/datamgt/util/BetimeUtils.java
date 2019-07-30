package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

public class BetimeUtils {
	private static int DEFAULT_START_AM_HOUR = 7;
	private static int DEFAULT_START_AM_MINUTE = 0;
	private static int DEFAULT_END_AM_HOUR = 11;
	private static int DEFAULT_END_AM_MINUTE = 30;
	private static int DEFAULT_START_PM_HOUR = 13;
	private static int DEFAULT_START_PM_MINUTE = 30;
	private static int DEFAULT_END_PM_HOUR = 17;
	private static int DEFAULT_END_PM_MINUTE = 0;
	
	public static int getValueCompareRelease(long groupId, Date releaseDate, Date dueDate) {
		if (dueDate == null || Validator.isNull(dueDate)) return 3;
		if (releaseDate == null) return -1;
		Calendar c = Calendar.getInstance();
		c.setTime(dueDate);
		List<WorkTime> workTimeList = WorkTimeLocalServiceUtil.getByGroupId(groupId);
		int day = c.get(Calendar.DAY_OF_WEEK);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		int startAmH = DEFAULT_START_AM_HOUR;
		int startAmM = DEFAULT_START_AM_MINUTE;
		int endAmH = DEFAULT_END_AM_HOUR;
		int endAmM = DEFAULT_END_AM_MINUTE;
		int startPmH = DEFAULT_START_PM_HOUR;
		int startPmM = DEFAULT_START_PM_MINUTE;
		int endPmH = DEFAULT_END_PM_HOUR;
		int endPmM = DEFAULT_END_PM_MINUTE;
		
		for (WorkTime wt : workTimeList) {
			if (wt.getDay() == day) {
				if (Validator.isNotNull(wt.getHours())) {
					String[] timeOffsets = wt.getHours().split(StringPool.COMMA);
					if (timeOffsets.length == 2) {
						String amStr = timeOffsets[0];
						String pmStr = timeOffsets[1];
						if (amStr.split(StringPool.COMMA).length == 2) {
							String startAms = amStr.split(StringPool.DASH)[0];
							String endAms = amStr.split(StringPool.DASH)[1];
							if (startAms.split(StringPool.COLON).length == 2) {
								startAmH = GetterUtil.getInteger(startAms.split(StringPool.COLON)[0]);
								startAmM = GetterUtil.getInteger(startAms.split(StringPool.COLON)[1]);		
							}
							if (endAms.split(StringPool.COLON).length == 2) {
								endAmH = GetterUtil.getInteger(endAms.split(StringPool.COLON)[0]);
								endAmM = GetterUtil.getInteger(endAms.split(StringPool.COLON)[1]);		
							}
						}						
						if (pmStr.split(StringPool.COMMA).length == 2) {
							String startPms = pmStr.split(StringPool.DASH)[0];
							String endPms = pmStr.split(StringPool.DASH)[1];
							if (startPms.split(StringPool.COLON).length == 2) {
								startPmH = GetterUtil.getInteger(startPms.split(StringPool.COLON)[0]);
								startPmM = GetterUtil.getInteger(startPms.split(StringPool.COLON)[1]);		
							}
							if (endPms.split(StringPool.COLON).length == 2) {
								endPmH = GetterUtil.getInteger(endPms.split(StringPool.COLON)[0]);
								endPmM = GetterUtil.getInteger(endPms.split(StringPool.COLON)[1]);		
							}
						}						
					}
				}
				break;
			}
		}
		
		int timeHM = hour * 60 + minute;
		int defaultStartAm = startAmH * 60 + startAmM;
		int defaultEndAm = endAmH * 60 + endAmM;
		int defaultStartPm = startPmH * 60 + startPmM;
		int defaultEndPm = endPmH * 60 + endPmM;
		
		int timeCompareH = 0;
		int timeCompareM = 0;
		
		if (timeHM >= defaultStartAm && timeHM <= defaultEndAm) {
			timeCompareH = startAmH;
			timeCompareM = startAmM;
		}
		else if (timeHM >= defaultStartPm && timeHM <= defaultEndPm) {
			timeCompareH = startPmH;
			timeCompareM = endPmM;
		}
		c.set(Calendar.HOUR_OF_DAY, timeCompareH);
		c.set(Calendar.MINUTE, timeCompareM);
		Date betimeDate = c.getTime();
		if (releaseDate.before(betimeDate)) {
			return 3;
		}
		if (releaseDate.compareTo(betimeDate) >= 0 && releaseDate.compareTo(dueDate) <= 0) {
			return 2;
		}
		
		return 1;
	}	
}
