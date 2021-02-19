package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.auth.utils.APIDateTimeUtils;
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
	//Caculate dueDate by day
	private static final Boolean CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE = Validator.isNotNull(PropsUtil.get("opencps.statistic.dossier.dueDate.day.enable"))
			? Boolean.valueOf(PropsUtil.get("opencps.statistic.dossier.dueDate.day.enable")) : false;
	private static final String _VN_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	public static Integer getValueCompareRelease(long groupId, Date releaseDate, Date dueDate) {
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
		
		// 
		String releaseDateTem = DateTimeUtils.convertDateToString(releaseDate, _VN_DATE_TIME_FORMAT);
		Date releaseDateSpec = APIDateTimeUtils.convertStringToDate(releaseDateTem, APIDateTimeUtils._NORMAL_DATE);
		String dueDateTem = DateTimeUtils.convertDateToString(dueDate, _VN_DATE_TIME_FORMAT);
		Date dueDateSpec = APIDateTimeUtils.convertStringToDate(dueDateTem, APIDateTimeUtils._NORMAL_DATE);
		DateFormat dateFormat =
				DateFormatFactoryUtil.getSimpleDateFormat(_VN_DATE_TIME_FORMAT);
		String betimeDateTem = dateFormat.format(betimeDate);
		Date betimeDateSpec = APIDateTimeUtils.convertStringToDate(betimeDateTem, APIDateTimeUtils._NORMAL_DATE);
		
		int overdue = 1; // 3: sớm hạn, 2: đúng hạn, 1: quá hạn
		if(CALCULATE_DOSSIER_STATISTIC_DUEDATE_DAY_ENABLE) {
			if (releaseDateSpec.before(betimeDateSpec)) {
				overdue = 3;
			}
			if (releaseDateSpec.compareTo(betimeDateSpec) >= 0 && releaseDateSpec.compareTo(dueDateSpec) <= 0) {
				overdue = 2;
			}
		} else {			
			if (releaseDateSpec.before(betimeDateSpec)) {
				overdue = 3;
			}
			if (releaseDateSpec.compareTo(dueDateSpec) == 0 && releaseDate.compareTo(dueDate) <= 0) {
				overdue = 2;
			}
		} 
		return overdue;
	}
	
	static Log _log = LogFactoryUtil.getLog(BetimeUtils.class);
}
