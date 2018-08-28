package org.opencps.datamgt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class HolidayUtils {

	private static Log _log = LogFactoryUtil.getLog(HolidayUtils.class);

	public final static String SATURDAY = "SATURDAY";
	public final static String SUNDAY = "SUNDAY";
//	private final static int ACTIVE = 1;
//	private int dayGoing = 0;
//	private int minutesGoing = 0;
//	private Calendar baseCalendar = Calendar.getInstance();
//	private List<Holiday> holidayConfigList1 = null;

	// get value day off
	private static String strDayOff = StringPool.BLANK;

	public static Date getDueDate(Date startDate, double durationCount, int durationUnit, long groupId) {

		//Get info day off and day work
		getDayByGroupId(groupId);
//		_log.info("strDayOff: "+strDayOff);
		// Calculator time working
		long hoursCount = processHoursCount(durationCount, durationUnit);
//		_log.info("hoursCount: "+hoursCount);

		List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupId(groupId);

		Date dueDate = getEndDate( groupId,  startDate,  hoursCount, holidayList);
//		_log.info("dueDate: "+dueDate);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(startDate);
//		int day = cal.get(Calendar.DAY_OF_WEEK);
		return dueDate;
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
//			_log.info("workTimeList.size(): "+workTimeList.size());
			String strHours = StringPool.BLANK;
			sbDayOff = new StringBuilder();
			sbDayWork = new StringBuilder();
			for (WorkTime work : workTimeList) {
				strHours = work.getHours();
//				_log.info("strHours: "+strHours);
				if (Validator.isNull(strHours)) {
					if (Validator.isNull(sbDayOff.toString())) {
						sbDayOff.append(work.getDay());
					} else {
						sbDayOff.append(StringPool.COMMA);
						sbDayOff.append(work.getDay());
					}
					
				} else {
					if (Validator.isNull(sbDayWork.toString())) {
						sbDayWork.append(work.getDay());
					} else {
						sbDayWork.append(StringPool.COMMA);
						sbDayWork.append(work.getDay());
					}
				}
			}
			if (Validator.isNotNull(sbDayOff)) {
				strDayOff = sbDayOff.toString();
//				_log.info("strDayOff: "+strDayOff);
			}
		}
	}

	public static Date getEndDate(long groupId, Date startDate, long hoursCount, List<Holiday> holidayList) {

		/* format pattern = "3 10:30" */
		if (startDate == null) {
			return null;
		}

		Calendar baseDateCal = Calendar.getInstance();
		baseDateCal.setTime(startDate);

		try {
//			int saturdayIsHoliday = 0;
//			int sundayIsHoliday = 0;
//			DateTimeUtils dateTimeUtil = new DateTimeUtils();
//			DateTimeBean dateTimeBean = dateTimeUtil
//					.getDateTimeFromPattern(pattern);
			/* Kiem tra xem flag sunday,saturday co duoc tinh la ngay nghi khong */

//			List<HolidayConfigExtend> holidayConfigExtendList = new ArrayList<HolidayConfigExtend>();
//			holidayConfigExtendList = HolidayConfigExtendLocalServiceUtil
//					.getHolidayConfigExtends(QueryUtil.ALL_POS,
//							QueryUtil.ALL_POS);
//			if (holidayConfigExtendList.size() > 0) {
//				for (HolidayConfigExtend holidayConfigExtend : holidayConfigExtendList) {
//					if (holidayConfigExtend.getKey().equals(SATURDAY)) {
//						saturdayIsHoliday = holidayConfigExtend.getStatus();
//					}
//					if (holidayConfigExtend.getKey().equals(SUNDAY)) {
//						sundayIsHoliday = holidayConfigExtend.getStatus();
//					}
//				}
//			}

			long countDay = hoursCount / 8;
			int countHours = (int) hoursCount % 8;
			if (countDay > 0 && countDay == 1) {
				baseDateCal.add(Calendar.DATE, 1);
				baseDateCal = checkDay(baseDateCal, startDate, holidayList);
			} else if (countDay > 1) {
				for (int i = 0; i < countDay; i++) {
					baseDateCal.add(Calendar.DATE, 1);
					baseDateCal = checkDay(baseDateCal, startDate, holidayList);
//							saturdayIsHoliday, sundayIsHoliday);
				}
			}

			if (countHours > 0) {
//				_log.info("countHours: "+countHours);
				int hours = baseDateCal.get(Calendar.HOUR_OF_DAY);
//				_log.info("hours: "+hours);
				int minutes = baseDateCal.get(Calendar.MINUTE);
//				_log.info("minutes: "+minutes);
				int dayOfWeek = baseDateCal.get(Calendar.DAY_OF_WEEK);
				if (Validator.isNotNull(dayOfWeek)) {
					WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, dayOfWeek);
					if (workTime != null) {
						String strHours = workTime.getHours();
						String[] hoursList = StringUtil.split(strHours);
						
						if (hoursList != null && hoursList.length > 0) {
							String[] hourArr1 = null;
							String[] hourArr2 = null;
							for (int i = 0; i < hoursList.length; i ++) {
								String hour = hoursList[i];
								if (i == 0) {
									hourArr1 = hour.split(StringPool.DASH);
								} else {
									hourArr2 = hour.split(StringPool.DASH);
								}
							}
							
							
							int hoursOverdue = 0;
							if (hourArr1 != null && hourArr2 != null) {
								int startMorning = Integer.parseInt(hourArr1[0]);
								int endMorning = Integer.parseInt(hourArr1[1]);
								int startAfterNoon = Integer.parseInt(hourArr2[0]);
								int endAfterNoon = Integer.parseInt(hourArr2[1]);
//								_log.info("startMorning: "+startMorning);
//								_log.info("endMorning: "+endMorning);
//								_log.info("startAfterNoon: "+startAfterNoon);
//								_log.info("endAfterNoon: "+endAfterNoon);
//								_log.info("hours: "+hours);
								if (startMorning < hours && hours < endMorning) {
									hoursOverdue = hours + countHours;
									if (hoursOverdue == endMorning && minutes > 0) {
										hoursOverdue = startAfterNoon;
										//
										baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
										baseDateCal.set(Calendar.MINUTE, minutes);
										
										return baseDateCal.getTime();
									} else if (hoursOverdue > endMorning){
										int countTest2 = hoursOverdue - endMorning;
										hoursOverdue = startAfterNoon + countTest2;
										if (hoursOverdue > endAfterNoon) {
											baseDateCal.add(Calendar.DATE, 1);
											int countTest = hoursOverdue - endAfterNoon;
											//
											dayOfWeek += 1;
											//Check dayOfWeek is dayOff or dayWork
											while (strDayOff.contains(String.valueOf(dayOfWeek))) {
												baseDateCal.add(Calendar.DATE, 1);
												dayOfWeek += 1;
											}
											//
											hoursOverdue = startMorning + countTest;
											
										}
									}
									
								} else if (hours == endMorning && minutes == 0) {
									hoursOverdue = startAfterNoon + countHours;
									if (hoursOverdue > endAfterNoon) {
										baseDateCal.add(Calendar.DATE, 1);
										int countTest = hoursOverdue - endAfterNoon;
										//
										dayOfWeek += 1;
										//Check dayOfWeek is dayOff or dayWork
										while (strDayOff.contains(String.valueOf(dayOfWeek))) {
											baseDateCal.add(Calendar.DATE, 1);
											dayOfWeek += 1;
										}
										
										//
										hoursOverdue = startMorning + countTest;
										if (hoursOverdue > endMorning) {
											int countTest1 = hoursOverdue - endMorning;
											hoursOverdue = startAfterNoon + countTest1;
										}
									}
									//
									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
									baseDateCal.set(Calendar.MINUTE, minutes);
									
									return baseDateCal.getTime();
								}
							
								//TODO:
								if (startAfterNoon < hours && hours < endAfterNoon) {
									hoursOverdue = hours + countHours;
//									_log.info("hoursOverdue: "+hoursOverdue);
									if (hoursOverdue == endAfterNoon && minutes > 0) {
										baseDateCal.add(Calendar.DATE, 1);
										//
										dayOfWeek += 1;
										//Check dayOfWeek is dayOff or dayWork
										while (strDayOff.contains(String.valueOf(dayOfWeek))) {
											baseDateCal.add(Calendar.DATE, 1);
											dayOfWeek += 1;
										}
										//
										hoursOverdue = startMorning;
										//
									} else if (hoursOverdue > endAfterNoon){
										int countTest11 = hoursOverdue - endAfterNoon;
//										_log.info("countTest11: "+countTest11);
										//
										baseDateCal.add(Calendar.DATE, 1);
										//
										dayOfWeek += 1;
										//Check dayOfWeek is dayOff or dayWork
										while (strDayOff.contains(String.valueOf(dayOfWeek))) {
											baseDateCal.add(Calendar.DATE, 1);
											dayOfWeek += 1;
										}
										//
										hoursOverdue = startMorning + countTest11;
//										_log.info("hoursOverdue: "+hoursOverdue);
//										_log.info("startMorning: "+startMorning);
//										_log.info("endMorning: "+endMorning);
										if (hoursOverdue > endMorning) {
											int countTest12 = hoursOverdue - endMorning;
											//
											hoursOverdue = startAfterNoon + countTest12;
											
										} else if (hoursOverdue == endMorning && minutes > 0) {
											hoursOverdue = startAfterNoon;
//											_log.info("hoursOverdue: "+hoursOverdue);
										}
									}
//									_log.info("hoursOverdue: "+hoursOverdue);
									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
									baseDateCal.set(Calendar.MINUTE, minutes);
									
//									_log.info("baseDateCal.getTime(): "+baseDateCal.getTime());
									return baseDateCal.getTime();
									
								} else if (hours == endAfterNoon && minutes == 0) {
									baseDateCal.add(Calendar.DATE, 1);
									//
									dayOfWeek += 1;
									//Check dayOfWeek is dayOff or dayWork
									while (strDayOff.contains(String.valueOf(dayOfWeek))) {
										baseDateCal.add(Calendar.DATE, 1);
										dayOfWeek += 1;
									}
									//
									hoursOverdue = startMorning + countHours;
									if (hoursOverdue > endMorning) {
										int countTest13 = hoursOverdue - endMorning;
										hoursOverdue = startAfterNoon + countTest13;
										
									}
								
									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
									baseDateCal.set(Calendar.MINUTE, minutes);

									return baseDateCal.getTime();
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return baseDateCal.getTime();
	}

	//LamTV_ Process checkDay
	private static Calendar checkDay(Calendar baseDateCal, Date startDate, List<Holiday> holidayList) {

		try {
			/**
			 * Kiem tra ngay xu ly co trung vao list ngay nghi da config hay
			 * chua, Neu trung thi + them ngay xu ly
			 */
			boolean isHoliday = isHoliday(baseDateCal, holidayList);
			//Check day is Day off
			boolean isDayOff = false;
			if (strDayOff.contains(String.valueOf(baseDateCal.get(Calendar.DAY_OF_WEEK)))) {
				isDayOff = true;
			}

			if (isHoliday || isDayOff) {
				baseDateCal.add(Calendar.DATE, 1);
			}
			
		}
		catch (Exception e) {
			_log.error(e);
		}

		return baseDateCal;
	}

	
//	private static Calendar checkDay(Calendar baseDateCal, Date startDate, List<Holiday> holidayList,
//			int saturdayIsHoliday, int sundayIsHoliday) {
//
//		boolean isHoliday = false;
//
//		try {
//			/**
//			 * Kiem tra ngay xu ly co trung vao list ngay nghi da config hay
//			 * chua, Neu trung thi + them ngay xu ly
//			 */
//			isHoliday = isHoliday(baseDateCal, holidayList);
//
//			if (isHoliday) {
//				if (strDayOff.contains(String.valueOf(baseDateCal.get(Calendar.DAY_OF_WEEK)))) {
//					baseDateCal.add(Calendar.DATE, 2);
//				} else {
//					baseDateCal.add(Calendar.DATE, 1);
//				}
//
//			} else {
//				if (strDayOff.contains(String.valueOf(baseDateCal.get(Calendar.DAY_OF_WEEK)))) {
//					baseDateCal.add(Calendar.DATE, 1);
//				}
//			}
//			
////			if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
////				baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || isHoliday) {
////
////				baseDateCal = isHolidayCal(baseDateCal, holidayList);
////
////				/*
////				 * Neu flag saturday,sunday bat thi tinh la ngay nghi, + them
////				 * ngay xu ly
////				 */
////
//////				if (saturdayIsHoliday == ACTIVE) {
//////
//////					baseDateCal = checkSaturday(baseDateCal);
//////				}
////
//////				if (sundayIsHoliday == ACTIVE) {
//////					baseDateCal = checkSunday(baseDateCal);
//////				}
////
////				checkDay(baseDateCal, baseDate, holidayConfigList, saturdayIsHoliday, sundayIsHoliday);
////			}
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//
//		return baseDateCal;
//	}

//	private static Calendar checkSaturday(Calendar baseDateCal) {
//
//		if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//			baseDateCal.add(Calendar.DATE, 2);
//		}
//		return baseDateCal;
//	}

//	private static Calendar checkSunday(Calendar baseDateCal) {
//
//		if (baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//			baseDateCal.add(Calendar.DATE, 1);
//		}
//		return baseDateCal;
//	}

//	private static int checkCountHoliday(long groupId) {
//
//		List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupId(groupId);
////		if
//		
//		
//		return 0;
//	}

//	private static Calendar isHolidayCal(Calendar baseDateCal, List<Holiday> holidayList) {
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
//			if (holidayList != null && holidayList.size() > 0) {
//				for (Holiday holiday: holidayList) {
//
//					holidayCal.setTime(holiday.getHolidayDate());
//
////					baseDay = baseDateCal.get(Calendar.DATE);
//					holidayDay = holidayCal.get(Calendar.DATE);
//
////					baseMonth = baseDateCal.get(Calendar.MONTH);
//					holidayMonth = holidayCal.get(Calendar.MONTH);
//
////					baseYear = baseDateCal.get(Calendar.YEAR);
//					holidayYear = holidayCal.get(Calendar.YEAR);
//
//					if (baseDay == holidayDay && baseMonth == holidayMonth && baseYear == holidayYear) {
//						baseDateCal.add(Calendar.DATE, 1);
//					}
//				}
//			}
//		}
//		catch (Exception e) {
//			_log.error(e);
//		}
//		return baseDateCal;
//	}

	private static boolean isHoliday(Calendar baseDateCal, List<Holiday> holidayList) {

		int baseDay = 0;
		int baseMonth = 0;
		int baseYear = 0;

		int holidayDay = 0;
		int holidayMonth = 0;
		int holidayYear = 0;

		Calendar holidayCal = Calendar.getInstance();

		try {
			if (holidayList != null && holidayList.size() > 0) {
				for (Holiday holiday: holidayList) {

					holidayCal.setTime(holiday.getHolidayDate());

					baseDay = baseDateCal.get(Calendar.DATE);
					holidayDay = holidayCal.get(Calendar.DATE);

					baseMonth = baseDateCal.get(Calendar.MONTH);
					holidayMonth = holidayCal.get(Calendar.MONTH);

					baseYear = baseDateCal.get(Calendar.YEAR);
					holidayYear = holidayCal.get(Calendar.YEAR);

					if (baseDay == holidayDay && baseMonth == holidayMonth && baseYear == holidayYear) {
						return true;
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		return false;

	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return minutesGoing
	 */
	public static int getCountDateByHoliday(long startDate, long endDate, int numberDate, long groupId) {

		int count = 0;
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTimeInMillis(startDate);

		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTimeInMillis(endDate);

		boolean flagCompareDate = false;
		for (int i = 0; i < numberDate; i++) {
			List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupId(groupId);
			boolean isHoliday = false;
			if (holidayList != null && holidayList.size() > 0) {
				isHoliday = isHoliday(startDateCal, holidayList);
			}
			//Check day is Day off
			boolean isDayOff = false;
			if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
				isDayOff = true;
			}
			if (isHoliday || isDayOff) {
//				if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
//					startDateCal.add(Calendar.DAY_OF_MONTH, 2);
//					count += 2;
//					flagCompareDate = compareDate(startDateCal, endDateCal);
//				} else {
				startDateCal.add(Calendar.DAY_OF_MONTH, 1);
				count += 1;
				flagCompareDate = compareDate(startDateCal, endDateCal);
//				}

			}
//			else {
//				if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
//					startDateCal.add(Calendar.DAY_OF_MONTH, 1);
//					count += 1;
//					flagCompareDate = compareDate(startDateCal, endDateCal);
//				}
//			}
			if (flagCompareDate) {
				return count;
			}
		}

		return count;
	}

	//Compare startDate and endDate
	private static boolean compareDate(Calendar startDateCal, Calendar endDateCal) {
		int startDay = startDateCal.get(Calendar.DAY_OF_MONTH);
		int startMonth = startDateCal.get(Calendar.MONTH);
		int startYear = startDateCal.get(Calendar.YEAR);
		int endDay = endDateCal.get(Calendar.DAY_OF_MONTH);
		int endMonth = endDateCal.get(Calendar.MONTH);
		int endYear = endDateCal.get(Calendar.YEAR);
		if (startDay == endDay && startMonth == endMonth && startYear == endYear) {
			return true;
		}
		return false;
	}

//	private void checkDay1(int saturdayIsHoliday, int sundayIsHoliday) {
//
//		boolean isHoliday = false;
//
//		try {
//
//			if (Validator.isNull(holidayConfigList1) || (holidayConfigList1.size() <= 0)) {
////				holidayConfigList1 = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
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
////				holidayConfigList1 = HolidayConfigLocalServiceUtil.getHolidayConfig(ACTIVE);
//			}
//
//			for (int i = 0; i < holidayConfigList1.size(); i++) {
//
//				holidayCal.setTime(holidayConfigList1.get(i).getHolidayDate());
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

//	private Calendar checkSunday1() {
//
//		if (baseCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
//
//			--dayGoing;
//			minutesGoing = minutesGoing - 1440;
//		}
//		return baseCalendar;
//	}

//	private Calendar checkSaturday1() {
//
//		if (baseCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//			--dayGoing;
//			minutesGoing = minutesGoing - 1440;
//
//		}
//		return baseCalendar;
//	}

}