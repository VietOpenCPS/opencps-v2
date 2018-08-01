package org.opencps.datamgt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class HolidayUtils {

	private static Log _log = LogFactoryUtil.getLog(HolidayUtils.class);

	public final static String SATURDAY = "SATURDAY";
	public final static String SUNDAY = "SUNDAY";
	private final static int ACTIVE = 1;
	private int dayGoing = 0;
	private int minutesGoing = 0;
	private Calendar baseCalendar = Calendar.getInstance();
	private List<Holiday> holidayConfigList1 = null;
	// get value day off
	private static String strDayOff = StringPool.BLANK;
	// get value day off
	private static String strDayWork = StringPool.BLANK;

	public static int countDueDate(Date startDate, double durationCount, int durationUnit, long groupId) {

		//Get info day off and day work
		getDayByGroupId(groupId);
		// Calculator time working
		long hoursCount = processHoursCount(durationCount, durationUnit);

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		
		
		
		return 0;
	}

	private static long processHoursCount(double durationCount, int durationUnit) {
		long resultHours = 0;
		if (durationUnit == 0) {
			resultHours = (long) durationCount * 8;
		} else {
			resultHours = (long) durationCount;
		}
		return resultHours;
	}

	// Calculator day off and day work
	private static void getDayByGroupId(long groupId) {
		List<WorkTime> workTimeList = WorkTimeLocalServiceUtil.getByGroupId(groupId);
		StringBuilder sbDayOff = null;
		StringBuilder sbDayWork = null;
		// Get day off and day work.
		if (workTimeList != null && workTimeList.size() > 0) {
			String strHours = StringPool.BLANK;
			sbDayOff = new StringBuilder();
			sbDayWork = new StringBuilder();
			for (WorkTime work : workTimeList) {
				strHours = work.getHours();
				if (Validator.isNull(strHours)) {
					if (Validator.isNull(sbDayOff)) {
						sbDayOff.append(work.getDay());
					} else {
						sbDayOff.append(StringPool.COMMA);
						sbDayOff.append(work.getDay());
					}
					
				} else {
					if (Validator.isNull(sbDayWork)) {
						sbDayWork.append(work.getDay());
					} else {
						sbDayOff.append(StringPool.COMMA);
						sbDayWork.append(work.getDay());
					}
				}
			}
			if (Validator.isNull(sbDayOff)) {
				strDayOff = sbDayOff.toString();
			}
			if (Validator.isNull(sbDayWork)) {
				strDayWork = sbDayWork.toString();
			}
		}
	}

//	public static Calendar getEndDate(Date baseDate, String pattern) {
//
//		/* format pattern = "3 10:30" */
//
//		if (baseDate == null) {
//			baseDate = new Date();
//		}
//
//		Calendar baseDateCal = Calendar.getInstance();
//		baseDateCal.setTime(baseDate);
//
//		try {
//
//			int saturdayIsHoliday = 0;
//			int sundayIsHoliday = 0;
//
//			DateTimeUtil dateTimeUtil = new DateTimeUtil();
//			DateTimeBean dateTimeBean = dateTimeUtil
//					.getDateTimeFromPattern(pattern);
//
//			/* Kiem tra xem flag sunday,saturday co duoc tinh la ngay nghi khong */
//
//			List<HolidayConfigExtend> holidayConfigExtendList = new ArrayList<HolidayConfigExtend>();
//			holidayConfigExtendList = HolidayConfigExtendLocalServiceUtil
//					.getHolidayConfigExtends(QueryUtil.ALL_POS,
//							QueryUtil.ALL_POS);
//
//			if (holidayConfigExtendList.size() > 0) {
//
//				for (HolidayConfigExtend holidayConfigExtend : holidayConfigExtendList) {
//
//					if (holidayConfigExtend.getKey().equals(SATURDAY)) {
//						saturdayIsHoliday = holidayConfigExtend.getStatus();
//					}
//
//					if (holidayConfigExtend.getKey().equals(SUNDAY)) {
//						sundayIsHoliday = holidayConfigExtend.getStatus();
//					}
//				}
//			}
//
//			for (int i = 0; i < dateTimeBean.getDays(); i++) {
//
//				baseDateCal.add(Calendar.DATE, 1);
//
//				baseDateCal = checkDay(baseDateCal, baseDate, null,
//						saturdayIsHoliday, sundayIsHoliday);
//
//			}
//			baseDateCal.add(Calendar.HOUR, dateTimeBean.getHours());
//			baseDateCal.add(Calendar.MINUTE, dateTimeBean.getMinutes());
//		} catch (Exception e) {
//			_log.error(e);
//		}
//
//		return baseDateCal;
//	}

//	private static Calendar checkDay(
//		Calendar baseDateCal, Date baseDate, List<Holiday> holidayConfigList,
//		int saturdayIsHoliday, int sundayIsHoliday) {
//
//		boolean isHoliday = false;
//
//		try {
//
//			if (Validator.isNull(holidayConfigList) || (holidayConfigList.size() <= 0)) {
//				holidayConfigList = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
//			}
//
//			/*
//			 * Kiem tra ngay xu ly co trung vao list ngay nghi da config hay
//			 * chua, Neu trung thi + them ngay xu ly
//			 */
//			isHoliday = isHoliday(baseDateCal, holidayConfigList);
//
//			if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//				baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || isHoliday) {
//
//				baseDateCal = isHolidayCal(baseDateCal, holidayConfigList);
//
//				/*
//				 * Neu flag saturday,sunday bat thi tinh la ngay nghi, + them
//				 * ngay xu ly
//				 */
//
//				if (saturdayIsHoliday == ACTIVE) {
//
//					baseDateCal = checkSaturday(baseDateCal);
//				}
//
//				if (sundayIsHoliday == ACTIVE) {
//					baseDateCal = checkSunday(baseDateCal);
//				}
//
//				checkDay(
//					baseDateCal, baseDate, holidayConfigList, saturdayIsHoliday, sundayIsHoliday);
//			}
//			else {
//
//			}
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//
//		return baseDateCal;
//	}

	private static Calendar checkSaturday(Calendar baseDateCal) {

		if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			baseDateCal.add(Calendar.DATE, 2);
		}
		return baseDateCal;
	}

	private static Calendar checkSunday(Calendar baseDateCal) {

		if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			baseDateCal.add(Calendar.DATE, 1);
		}
		return baseDateCal;
	}

//	private static Calendar isHolidayCal(Calendar baseDateCal, List<HolidayConfig> holidayConfigList) {
//
//		int baseDay = 0;
//		int baseMonth = 0;
//		int baseYear = 0;
//
//		int holidayDay = 0;
//		int holidayMonth = 0;
//		int holidayYear = 0;
//
//		Calendar holidayCal = Calendar.getInstance();
//
//		try {
//
//			if (Validator.isNull(holidayConfigList) || (holidayConfigList.size() <= 0)) {
//
//				holidayConfigList = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
//			}
//
//			for (int i = 0; i < holidayConfigList.size(); i++) {
//
//				holidayCal.setTime(holidayConfigList.get(i).getHoliday());
//
//				baseDay = baseDateCal.get(Calendar.DATE);
//				holidayDay = holidayCal.get(Calendar.DATE);
//
//				baseMonth = baseDateCal.get(Calendar.MONTH);
//				holidayMonth = holidayCal.get(Calendar.MONTH);
//
//				baseYear = baseDateCal.get(Calendar.YEAR);
//				holidayYear = holidayCal.get(Calendar.YEAR);
//
//				if (baseDay == holidayDay && baseMonth == holidayMonth && baseYear == holidayYear) {
//					baseDateCal.add(Calendar.DATE, 1);
//				}
//			}
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			_log.error(e);
//		}
//		return baseDateCal;
//	}

//	private static boolean isHoliday(Calendar baseDateCal, List<HolidayConfig> holidayConfigList) {
//
//		int baseDay = 0;
//		int baseMonth = 0;
//		int baseYear = 0;
//
//		int holidayDay = 0;
//		int holidayMonth = 0;
//		int holidayYear = 0;
//
//		Calendar holidayCal = Calendar.getInstance();
//
//		try {
//
//			if (Validator.isNull(holidayConfigList) || (holidayConfigList.size() <= 0)) {
//
//				holidayConfigList = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
//			}
//
//			for (int i = 0; i < holidayConfigList.size(); i++) {
//
//				holidayCal.setTime(holidayConfigList.get(i).getHoliday());
//
//				baseDay = baseDateCal.get(Calendar.DATE);
//				holidayDay = holidayCal.get(Calendar.DATE);
//
//				baseMonth = baseDateCal.get(Calendar.MONTH);
//				holidayMonth = holidayCal.get(Calendar.MONTH);
//
//				baseYear = baseDateCal.get(Calendar.YEAR);
//				holidayYear = holidayCal.get(Calendar.YEAR);
//
//				if (baseDay == holidayDay && baseMonth == holidayMonth && baseYear == holidayYear) {
//					return true;
//				}
//			}
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//
//		return false;
//
//	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return minutesGoing
	 */
	public int getDurationMinutes(Date startDate, Date endDate) {

		if (Validator.isNull(startDate)) {
			startDate = new Date();
		}

		baseCalendar.setTime(startDate);
		baseCalendar.set(Calendar.HOUR, 0);
		baseCalendar.set(Calendar.MINUTE, 0);
		baseCalendar.set(Calendar.SECOND, 0);

		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTime(endDate);
		endDateCal.set(Calendar.HOUR, 0);
		endDateCal.set(Calendar.MINUTE, 0);
		endDateCal.set(Calendar.SECOND, 0);

		Calendar startDateCal1 = Calendar.getInstance();
		startDateCal1.setTime(startDate);

		Calendar endDateCal1 = Calendar.getInstance();
		endDateCal1.setTime(endDate);

		long timeInMillis = endDateCal.getTimeInMillis() - baseCalendar.getTimeInMillis();
		long timeInMillis1 = endDateCal1.getTimeInMillis() - startDateCal1.getTimeInMillis();

		long diffMinutes = DateTimeUtils.convertTimemilisecondsToMinutes(timeInMillis1);
		int diffDays = DateTimeUtils.convertTimemilisecondsToDays(timeInMillis);

		minutesGoing = (int) diffMinutes;
		dayGoing = diffDays;

//		try {
//
//			int saturdayIsHoliday = 0;
//			int sundayIsHoliday = 0;
//
//			/* Kiem tra xem flag sunday,saturday co duoc tinh la ngay nghi khong */
//
//			List<HolidayConfigExtend> holidayConfigExtendList =
//				HolidayConfigExtendLocalServiceUtil.getHolidayConfigExtends(
//					QueryUtil.ALL_POS, QueryUtil.ALL_POS);
//
//			for (HolidayConfigExtend holidayConfigExtend : holidayConfigExtendList) {
//
//				if (holidayConfigExtend.getKey().equals(SATURDAY)) {
//					saturdayIsHoliday = holidayConfigExtend.getStatus();
//				}
//
//				if (holidayConfigExtend.getKey().equals(SUNDAY)) {
//					sundayIsHoliday = holidayConfigExtend.getStatus();
//				}
//			}
//
//			for (int i = 0; i < diffDays; i++) {
//
//				baseCalendar.add(Calendar.DATE, 1);
//
//				checkDay1(saturdayIsHoliday, sundayIsHoliday);
//
//			}
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}

		int minutesReturn = minutesGoing;
		int daysReturn = dayGoing;

		return minutesReturn;
	}

//	private void checkDay1(int saturdayIsHoliday, int sundayIsHoliday) {
//
//		boolean isHoliday = false;
//
//		try {
//
//			if (Validator.isNull(holidayConfigList1) || (holidayConfigList1.size() <= 0)) {
//				holidayConfigList1 = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
//			}
//
//			isHoliday = isHoliday(baseCalendar, holidayConfigList1);
//
//			if (baseCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//				baseCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || isHoliday) {
//
//				if (isHoliday) {
//					isHolidayCal1();
//				}
//
//				if (saturdayIsHoliday == ACTIVE) {
//
//					checkSaturday1();
//				}
//
//				if (sundayIsHoliday == ACTIVE) {
//					checkSunday1();
//				}
//
//			}
//			else {
//
//			}
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//	}

//	private void isHolidayCal1() {
//
//		int baseDay = 0;
//		int baseMonth = 0;
//		int baseYear = 0;
//
//		int holidayDay = 0;
//		int holidayMonth = 0;
//		int holidayYear = 0;
//
//		Calendar holidayCal = Calendar.getInstance();
//
//		try {
//
//			if (Validator.isNull(holidayConfigList1) || (holidayConfigList1.size() <= 0)) {
//
//				holidayConfigList1 = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
//			}
//
//			for (int i = 0; i < holidayConfigList1.size(); i++) {
//
//				holidayCal.setTime(holidayConfigList1.get(i).getHoliday());
//
//				baseDay = baseCalendar.get(Calendar.DATE);
//				holidayDay = holidayCal.get(Calendar.DATE);
//
//				baseMonth = baseCalendar.get(Calendar.MONTH);
//				holidayMonth = holidayCal.get(Calendar.MONTH);
//
//				baseYear = baseCalendar.get(Calendar.YEAR);
//				holidayYear = holidayCal.get(Calendar.YEAR);
//
//				if (baseDay == holidayDay && baseMonth == holidayMonth && baseYear == holidayYear) {
//					--dayGoing;
//					minutesGoing = minutesGoing - 1440;
//				}
//			}
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			_log.error(e);
//		}
//
//	}

	private Calendar checkSunday1() {

		if (baseCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

			--dayGoing;
			minutesGoing = minutesGoing - 1440;
		}
		return baseCalendar;
	}

	private Calendar checkSaturday1() {

		if (baseCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			--dayGoing;
			minutesGoing = minutesGoing - 1440;

		}
		return baseCalendar;
	}

}