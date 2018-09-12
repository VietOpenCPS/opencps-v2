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

	// get value day off
	private static String strDayOff = StringPool.BLANK;
	// get value time working
	private static Integer startHourMorning = 0;
	private static Integer startMinuteMorning = 0;
	private static Integer endHourMorning = 0;
	private static Integer endMinuteMorning = 0;
	private static Integer startHourAfterNoon = 0;
	private static Integer startMinuteAfterNoon = 0;
	private static Integer endHourAfterNoon = 0;
	private static Integer endMinuteAfterNoon = 0;

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
//			String strHours = StringPool.BLANK;
			sbDayOff = new StringBuilder();
			sbDayWork = new StringBuilder();
			for (WorkTime work : workTimeList) {
				String strHours = work.getHours();
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
			//Process day off
			getDayOff(sbDayOff, sbDayWork);
		}
	}

	private static void getDayOff(StringBuilder sbDayOff, StringBuilder sbDayWork) {
		if (Validator.isNotNull(sbDayOff)) {
			strDayOff = sbDayOff.toString();
		} else if (Validator.isNotNull(sbDayWork)) {
			StringBuilder sb = new StringBuilder();
			String strDayWork = sbDayWork.toString();
			if (!strDayWork.contains(String.valueOf(Calendar.SUNDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.SUNDAY);
				} else {
					sb.append(Calendar.SUNDAY);
				}
			} 
			if (!strDayWork.contains(String.valueOf(Calendar.MONDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.MONDAY);
				} else {
					sb.append(Calendar.MONDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.TUESDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.TUESDAY);
				} else {
					sb.append(Calendar.TUESDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.WEDNESDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.WEDNESDAY);
				} else {
					sb.append(Calendar.WEDNESDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.THURSDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.THURSDAY);
				} else {
					sb.append(Calendar.THURSDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.FRIDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.FRIDAY);
				} else {
					sb.append(Calendar.FRIDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.SATURDAY))) {
				if (Validator.isNotNull(sbDayOff)) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.SATURDAY);
				} else {
					sb.append(Calendar.SATURDAY);
				}
			}

			if (Validator.isNotNull(sb)) {
				strDayOff = sb.toString();
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
								processTimeWorking(hourArr1, hourArr2);
//								_log.info("startMorning: "+startMorning);
//								_log.info("endMorning: "+endMorning);
//								_log.info("startAfterNoon: "+startAfterNoon);
//								_log.info("endAfterNoon: "+endAfterNoon);
//								_log.info("hours: "+hours);
								if (startHourMorning < hours && hours < endHourMorning) {
									hoursOverdue = hours + countHours;
									if (hoursOverdue == endHourMorning && minutes > endMinuteMorning) {
										hoursOverdue = startHourAfterNoon;
										//
										baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
										baseDateCal.set(Calendar.MINUTE, minutes);
										
										return baseDateCal.getTime();
									} else if (hoursOverdue > endHourMorning){
										int countTest2 = hoursOverdue - endHourMorning;
										hoursOverdue = startHourAfterNoon + countTest2;
										if (hoursOverdue > endHourAfterNoon) {
											baseDateCal.add(Calendar.DATE, 1);
											int countTest = hoursOverdue - endHourAfterNoon;
											//
											dayOfWeek += 1;
											//Check dayOfWeek is dayOff or dayWork
											while (strDayOff.contains(String.valueOf(dayOfWeek))) {
												baseDateCal.add(Calendar.DATE, 1);
												dayOfWeek += 1;
											}
											//
											hoursOverdue = startHourMorning + countTest;
											//
											baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
											baseDateCal.set(Calendar.MINUTE, minutes);
											
											return baseDateCal.getTime();
										}
									}
									
								} else if (hours == endHourMorning && minutes == endMinuteMorning) {
									hoursOverdue = startHourAfterNoon + countHours;
									if (hoursOverdue > endHourAfterNoon) {
										baseDateCal.add(Calendar.DATE, 1);
										int countTest = hoursOverdue - endHourAfterNoon;
										//
										dayOfWeek += 1;
										//Check dayOfWeek is dayOff or dayWork
										while (strDayOff.contains(String.valueOf(dayOfWeek))) {
											baseDateCal.add(Calendar.DATE, 1);
											dayOfWeek += 1;
										}
										
										//
										hoursOverdue = startHourMorning + countTest;
										if (hoursOverdue > endHourMorning) {
											int countTest1 = hoursOverdue - endHourMorning;
											hoursOverdue = startHourAfterNoon + countTest1;
										}
									}
									//
									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
									baseDateCal.set(Calendar.MINUTE, minutes);
									
									return baseDateCal.getTime();
								}
							
								//TODO:
								if (startHourAfterNoon < hours && hours < endHourAfterNoon) {
									hoursOverdue = hours + countHours;
//									_log.info("hoursOverdue: "+hoursOverdue);
									if (hoursOverdue == endHourAfterNoon && minutes > endMinuteAfterNoon) {
										baseDateCal.add(Calendar.DATE, 1);
										//
										dayOfWeek += 1;
										//Check dayOfWeek is dayOff or dayWork
										while (strDayOff.contains(String.valueOf(dayOfWeek))) {
											baseDateCal.add(Calendar.DATE, 1);
											dayOfWeek += 1;
										}
										//
										hoursOverdue = startHourMorning;
										//
									} else if (hoursOverdue > endHourAfterNoon){
										int countTest11 = hoursOverdue - endHourAfterNoon;
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
										hoursOverdue = startHourMorning + countTest11;
//										_log.info("hoursOverdue: "+hoursOverdue);
//										_log.info("startMorning: "+startMorning);
//										_log.info("endMorning: "+endMorning);
										if (hoursOverdue > endHourMorning) {
											int countTest12 = hoursOverdue - endHourMorning;
											//
											hoursOverdue = startHourAfterNoon + countTest12;
											
										} else if (hoursOverdue == endHourMorning && minutes > endMinuteMorning) {
											hoursOverdue = startHourAfterNoon;
//											_log.info("hoursOverdue: "+hoursOverdue);
										}
									}
//									_log.info("hoursOverdue: "+hoursOverdue);
									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursOverdue);
									baseDateCal.set(Calendar.MINUTE, minutes);
									
//									_log.info("baseDateCal.getTime(): "+baseDateCal.getTime());
									return baseDateCal.getTime();
									
								} else if (hours == endHourAfterNoon && minutes == endMinuteAfterNoon) {
									baseDateCal.add(Calendar.DATE, 1);
									//
									dayOfWeek += 1;
									//Check dayOfWeek is dayOff or dayWork
									while (strDayOff.contains(String.valueOf(dayOfWeek))) {
										baseDateCal.add(Calendar.DATE, 1);
										dayOfWeek += 1;
									}
									//
									hoursOverdue = startHourMorning + countHours;
									if (hoursOverdue > endHourMorning) {
										int countTest13 = hoursOverdue - endHourMorning;
										hoursOverdue = startHourAfterNoon + countTest13;
										
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

	private static void processTimeWorking(String[] hourArr1, String[] hourArr2) {
		if (Validator.isNotNull(hourArr1[0])) {
			String[] strMorningSplit = StringUtil.split(hourArr1[0], StringPool.COLON);
			if (strMorningSplit != null) {
				for (int i = 0; i < strMorningSplit.length; i++) {
					if (i == 0) {
						startHourMorning = Integer.parseInt(strMorningSplit[i]);
					} else {
						startMinuteMorning = Integer.parseInt(strMorningSplit[i]);
					}
				}
			}
		}
		if (Validator.isNotNull(hourArr1[1])) {
			String[] strMorningSplit = StringUtil.split(hourArr1[1], StringPool.COLON);
			if (strMorningSplit != null) {
				for (int i = 0; i < strMorningSplit.length; i++) {
					if (i == 0) {
						endHourMorning = Integer.parseInt(strMorningSplit[i]);
					} else {
						endMinuteMorning = Integer.parseInt(strMorningSplit[i]);
					}
				}
			}
		}
		if (Validator.isNotNull(hourArr2[0])) {
			String[] strAfternoonSplit = StringUtil.split(hourArr2[0], StringPool.COLON);
			if (strAfternoonSplit != null) {
				for (int i = 0; i < strAfternoonSplit.length; i++) {
					if (i == 0) {
						startHourAfterNoon = Integer.parseInt(strAfternoonSplit[i]);
					} else {
						startMinuteAfterNoon = Integer.parseInt(strAfternoonSplit[i]);
					}
				}
			}
		}
		if (Validator.isNotNull(hourArr2[1])) {
			String[] strAfternoonSplit = StringUtil.split(hourArr2[1], StringPool.COLON);
			if (strAfternoonSplit != null) {
				for (int i = 0; i < strAfternoonSplit.length; i++) {
					if (i == 0) {
						endHourAfterNoon = Integer.parseInt(strAfternoonSplit[i]);
					} else {
						endMinuteAfterNoon = Integer.parseInt(strAfternoonSplit[i]);
					}
				}
			}
		}
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