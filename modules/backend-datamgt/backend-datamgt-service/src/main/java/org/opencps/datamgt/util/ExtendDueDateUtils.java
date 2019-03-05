package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

public class ExtendDueDateUtils {

	private static Log _log = LogFactoryUtil.getLog(ExtendDueDateUtils.class);

	public static final int VALUE_HOUR = 60;
	public static final int VALUE_TIME_ZONE = 7;
	private static final long VALUE_CONVERT_DATE_WORKING_TIMESTAMP = 1000 * 60 * 60 * 8;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;
	private static final long VALUE_CONVERT_MINUTE_TIMESTAMP = 1000 * 60;
	// get value day off
	private static volatile String strDayOff = StringPool.BLANK;
	// get value time working
	private static volatile Integer startHourMorning = 0;
	private static volatile Integer startMinuteMorning = 0;
	private static volatile Integer endHourMorning = 0;
	private static volatile Integer endMinuteMorning = 0;
	private static volatile Integer startHourAfterNoon = 0;
	private static volatile Integer startMinuteAfterNoon = 0;
	private static volatile Integer endHourAfterNoon = 0;
	private static volatile Integer endMinuteAfterNoon = 0;

	// Calculator day off and day work
	private static void getDayByGroupId(long groupId) {
//		_log.info("groupId: "+groupId);
		List<WorkTime> workTimeList = WorkTimeLocalServiceUtil.getByGroupId(groupId);
//		_log.info("workTimeList: "+workTimeList);
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
//			_log.info("sbDayOff: "+sbDayOff.toString());
//			_log.info("sbDayWork: "+sbDayWork.toString());
			getDayOff(sbDayOff, sbDayWork);
		}
	}

	private static void getDayOff(StringBuilder sbDayOff, StringBuilder sbDayWork) {
		if (Validator.isNotNull(sbDayOff.toString())) {
			strDayOff = sbDayOff.toString();
//			_log.info("sbDayOff: "+sbDayOff.toString());
		} else if (Validator.isNotNull(sbDayWork.toString())) {
			StringBuilder sb = new StringBuilder();
			String strDayWork = sbDayWork.toString();
			if (!strDayWork.contains(String.valueOf(Calendar.SUNDAY))) {
//				if (Validator.isNotNull(sbDayOff.toString())) {
//					sb.append(StringPool.COMMA);
//					sb.append(Calendar.SUNDAY);
//				} else {
				sb.append(Calendar.SUNDAY);
//				}
			}
//			_log.info("sbDayOff11: "+sb.toString());
			if (!strDayWork.contains(String.valueOf(Calendar.MONDAY))) {
				if (Validator.isNotNull(sb.toString())) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.MONDAY);
				} else {
					sb.append(Calendar.MONDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.TUESDAY))) {
				if (Validator.isNotNull(sb.toString())) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.TUESDAY);
				} else {
					sb.append(Calendar.TUESDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.WEDNESDAY))) {
				if (Validator.isNotNull(sb.toString())) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.WEDNESDAY);
				} else {
					sb.append(Calendar.WEDNESDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.THURSDAY))) {
				if (Validator.isNotNull(sb.toString())) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.THURSDAY);
				} else {
					sb.append(Calendar.THURSDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.FRIDAY))) {
				if (Validator.isNotNull(sb.toString())) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.FRIDAY);
				} else {
					sb.append(Calendar.FRIDAY);
				}
			}
			if (!strDayWork.contains(String.valueOf(Calendar.SATURDAY))) {
				if (Validator.isNotNull(sb.toString())) {
					sb.append(StringPool.COMMA);
					sb.append(Calendar.SATURDAY);
				} else {
					sb.append(Calendar.SATURDAY);
				}
			}

//			_log.info("sbDayOff112: "+sb.toString());
			if (Validator.isNotNull(sb.toString())) {
				strDayOff = sb.toString();
			}
		}
	}

	private static void processTimeWorking(String[] hourArr1, String[] hourArr2) {
		if (Validator.isNotNull(hourArr1[0])) {
//			String[] strMorningSplit = StringUtil.split(hourArr1[0], StringPool.PERIOD);
			String[] strMorningSplit = StringUtil.split(hourArr1[0], StringPool.COLON);
			if (strMorningSplit != null) {
				startHourMorning = Integer.parseInt(strMorningSplit[0]);
				startMinuteMorning = Integer.parseInt(strMorningSplit[1]);
			}
		}
		if (Validator.isNotNull(hourArr1[1])) {
			//TODO
//			String[] strMorningSplit = StringUtil.split(hourArr1[1], StringPool.PERIOD);
			String[] strMorningSplit = StringUtil.split(hourArr1[1], StringPool.COLON);
			if (strMorningSplit != null) {
				endHourMorning = Integer.parseInt(strMorningSplit[0]);
				endMinuteMorning = Integer.parseInt(strMorningSplit[1]);
			}
		}

		if (Validator.isNotNull(hourArr2[0])) {
			String[] strAfternoonSplit = StringUtil.split(hourArr2[0], StringPool.COLON);
			if (strAfternoonSplit != null) {
				startHourAfterNoon = Integer.parseInt(strAfternoonSplit[0]);
				startMinuteAfterNoon = Integer.parseInt(strAfternoonSplit[1]);
			}
		}

		if (Validator.isNotNull(hourArr2[1])) {
			String[] strAfternoonSplit = StringUtil.split(hourArr2[1], StringPool.COLON);
			if (strAfternoonSplit != null) {
				endHourAfterNoon = Integer.parseInt(strAfternoonSplit[0]);
				endMinuteAfterNoon = Integer.parseInt(strAfternoonSplit[1]);
			}
		}
	}

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

	//TODO
	public static long getTimeWaitingByHoliday(long startDate, long endDate, long groupId) {

		long extendHour = 0;
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTimeInMillis(startDate);
//		_log.info("startDateCal: "+startDateCal.get(Calendar.DATE));

		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTimeInMillis(endDate);
//		_log.info("endDateCal: "+endDateCal.get(Calendar.DATE));
		
		//Get info day off and day work
		getDayByGroupId(groupId);
//		_log.info("strDayOff: "+strDayOff);

		//boolean flagCompareDate = false;
//		_log.info("numberDate: "+numberDate);
		boolean flagCompareDate = compareDate(startDateCal, endDateCal);
//		String[] hourArrStart = null;
//		String[] hourArrEnd = null;
		if (flagCompareDate) {
			int dayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
			//_log.info("dayOfWeek: "+dayOfWeek);
			int hoursStart = startDateCal.get(Calendar.HOUR_OF_DAY);
			//_log.info("hoursStart: "+hoursStart);
			//int minuteStart = startDateCal.get(Calendar.MINUTE);

			//int dayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
			int hoursEnd = endDateCal.get(Calendar.HOUR_OF_DAY);
			//_log.info("hoursEnd: "+hoursEnd);
			//int minuteEnd = endDateCal.get(Calendar.MINUTE);
			//
			List<String[]> workingArr = processPartWorking(dayOfWeek, groupId);
//			if (Validator.isNotNull(dayOfWeek)) {
//				WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, dayOfWeek);
//				if (workTime != null) {
//					String strHours = workTime.getHours();
//					String[] hoursList = StringUtil.split(strHours);
//
//					if (hoursList != null && hoursList.length > 0) {
//						for (int i = 0; i < hoursList.length; i++) {
//							String hour = hoursList[i];
//							if (i == 0) {
//								hourArrStart = hour.split(StringPool.DASH);
//							} else {
//								hourArrEnd = hour.split(StringPool.DASH);
//							}
//						}
//					}
//				}
//			}
			//
//			_log.info("hourArrStart: "+hourArrStart);
//			_log.info("hourArrEnd: "+hourArrEnd);
			if (workingArr != null && workingArr.size() > 0) {
				String[] hourArrStart = workingArr.get(0);
				String[] hourArrEnd = workingArr.get(1);
				if (hourArrStart != null && hourArrEnd != null) {
					processTimeWorking(hourArrStart, hourArrEnd);
					if (startHourMorning < hoursEnd && hoursEnd <= endHourMorning) {
						extendHour = endDate - startDate;
					} else if (startHourAfterNoon < hoursEnd && hoursEnd <= endHourAfterNoon) {
						if (startHourMorning <= hoursStart && hoursStart <= endHourMorning) {
							extendHour = endDate - startDate - countTimeOffNoon();
						} else if (startHourAfterNoon < hoursStart && hoursStart <= endHourAfterNoon) {
							extendHour = endDate - startDate;
						}
					}
				}
			}
		} else {
			int dayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
			//
			List<String[]> workingArr = processPartWorking(dayOfWeek, groupId);
//			if (Validator.isNotNull(dayOfWeek)) {
//				WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, dayOfWeek);
//				if (workTime != null) {
//					String strHours = workTime.getHours();
//					String[] hoursList = StringUtil.split(strHours);
//
//					if (hoursList != null && hoursList.length > 0) {
//						for (int i = 0; i < hoursList.length; i++) {
//							String hour = hoursList[i];
//							if (i == 0) {
//								hourArrStart = hour.split(StringPool.DASH);
//							} else {
//								hourArrEnd = hour.split(StringPool.DASH);
//							}
//						}
//					}
//				}
//			}
			//
			//_log.info("workingArr.get(0)111: "+JSONFactoryUtil.looseSerialize(workingArr));
			if (workingArr != null && workingArr.size() > 0) {
				String[] hourArrStart = workingArr.get(0);
				String[] hourArrEnd = workingArr.get(1);
				if (hourArrStart != null && hourArrEnd != null) {
					processTimeWorking(hourArrStart, hourArrEnd);
					//
					int hoursStart = startDateCal.get(Calendar.HOUR_OF_DAY);
					int minuteStart = startDateCal.get(Calendar.MINUTE);
					//_log.info("hoursStart: "+hoursStart);
					//_log.info("minuteStart: "+minuteStart);
					long timeStampStart = hoursStart * VALUE_CONVERT_HOUR_TIMESTAMP
							+ minuteStart * VALUE_CONVERT_MINUTE_TIMESTAMP;
					long timeStampEnd = endHourAfterNoon * VALUE_CONVERT_HOUR_TIMESTAMP
							+ endMinuteAfterNoon * VALUE_CONVERT_MINUTE_TIMESTAMP;
					//long returnTest = 0;
					//_log.info("startHourMorning: "+startHourMorning);
					//_log.info("endHourMorning: "+endHourMorning);
					//_log.info("endHourAfterNoon: "+endHourAfterNoon);
					//_log.info("endMinuteAfterNoon: "+endMinuteAfterNoon);
					if (startHourMorning <= hoursStart && hoursStart <= endHourMorning) {
						extendHour = timeStampEnd - timeStampStart - countTimeOffNoon();
						//_log.info("extendHour11: "+extendHour);
					} else if (startHourAfterNoon <= hoursStart && hoursStart <= endHourAfterNoon) {
						extendHour = timeStampEnd - timeStampStart;
						//_log.info("extendHour11: "+extendHour);
					}
				}
			}

			// Then encrease startDate + 1
			startDateCal.add(Calendar.DATE, 1);
			int dayOfWeek111 = startDateCal.get(Calendar.DAY_OF_WEEK);
			for (int i = 0; i < 2; i++) {
				if (strDayOff.contains(String.valueOf(dayOfWeek111))) {
					startDateCal.add(Calendar.DATE, 1);
					dayOfWeek111 = startDateCal.get(Calendar.DAY_OF_WEEK);
				} else {
					break;
				}
			}
			
			List<String[]> workingEndArr = processPartWorking(dayOfWeek111, groupId);
			//_log.info("workingEndArr: "+JSONFactoryUtil.looseSerialize(workingEndArr));
			if (workingEndArr != null && workingEndArr.size() > 0) {
				String[] hourArr1 = workingEndArr.get(0);
				String[] hourArr2 = workingEndArr.get(1);
				//_log.info("hourArr1: "+hourArr1);
				//_log.info("hourArr2: "+hourArr2);
				if (hourArr1 != null && hourArr2 != null) {
					processTimeWorking(hourArr1, hourArr2);
					//
					
					int hoursEnd = endDateCal.get(Calendar.HOUR_OF_DAY);
					int minuteEnd = endDateCal.get(Calendar.MINUTE);
					//_log.info("hoursEnd: "+hoursEnd);
					//_log.info("minuteEnd: "+minuteEnd);
					long timeStampEnd = hoursEnd * VALUE_CONVERT_HOUR_TIMESTAMP
							+ minuteEnd * VALUE_CONVERT_MINUTE_TIMESTAMP;
					long timeStampStart = startHourMorning * VALUE_CONVERT_HOUR_TIMESTAMP
							+ startMinuteMorning * VALUE_CONVERT_MINUTE_TIMESTAMP;
					//_log.info("startHourMorning: "+startHourMorning);
					//_log.info("endHourMorning: "+endHourMorning);
					//_log.info("endHourAfterNoon: "+endHourAfterNoon);
					//_log.info("endMinuteAfterNoon: "+endMinuteAfterNoon);
					if (startHourMorning <= hoursEnd && hoursEnd <= endHourMorning) {
						extendHour += timeStampEnd - timeStampStart;
						//_log.info("extendHour22: "+extendHour);
					} else if (startHourAfterNoon <= hoursEnd && hoursEnd <= endHourAfterNoon) {
						extendHour += timeStampEnd - timeStampStart - countTimeOffNoon();
						//_log.info("extendHour22: "+extendHour);
					}
				}
			}
			//Process time of day
			extendHour += getCountTimeWorkingOfDay(startDateCal, endDateCal, groupId);

		}

		return extendHour;
	}

	private static List<String[]> processPartWorking(int dayOfWeek, long groupId) {
		List<String[]> workingArr = new ArrayList<>();
		if (Validator.isNotNull(dayOfWeek)) {
			WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, dayOfWeek);
			if (workTime != null) {
				String strHours = workTime.getHours();
				String[] hoursList = StringUtil.split(strHours);

				if (hoursList != null && hoursList.length > 0) {
					for (int i = 0; i < hoursList.length; i++) {
						String hour = hoursList[i];
						if (i == 0) {
							String[] hourArrStart = hour.split(StringPool.DASH);
							workingArr.add(hourArrStart);
						} else {
							String[] hourArrEnd = hour.split(StringPool.DASH);
							workingArr.add(hourArrEnd);
						}
					}
				}
			}
		}

		return workingArr;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return minutesGoing
	 */
	public static long getCountTimeWorkingOfDay(Calendar startDateCal, Calendar endDateCal, long groupId) {

		int count = 0;
//		_log.info("strDayOff: "+strDayOff);
		List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupId(groupId);

		boolean flagCompareDate = compareDate(startDateCal, endDateCal);
//		_log.info("flagCompareDate: "+flagCompareDate);
		while(!flagCompareDate) {
			boolean isHoliday = false;
			if (holidayList != null && holidayList.size() > 0) {
				isHoliday = isHoliday(startDateCal, holidayList);
			}
			//Check day is Day off
			boolean isDayOff = false;
			if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
				isDayOff = true;
			}
			startDateCal.add(Calendar.DATE, 1);
			if (!isHoliday && !isDayOff) {
				count += 1;
			}
			flagCompareDate = compareDate(startDateCal, endDateCal);
		}

		return count * VALUE_CONVERT_DATE_WORKING_TIMESTAMP;
	}

	//Process Time Off between morning and afternoon
	private static long countTimeOffNoon() {
		double start = endHourMorning + endMinuteMorning / 60;
		double end = startHourAfterNoon + startMinuteAfterNoon / 60;
		long subTime = (long) ((end - start) * VALUE_CONVERT_HOUR_TIMESTAMP);

		return subTime;
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

}