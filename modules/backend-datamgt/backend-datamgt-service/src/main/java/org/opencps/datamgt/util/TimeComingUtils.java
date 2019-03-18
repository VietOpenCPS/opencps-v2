package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

public class TimeComingUtils {

	private static Log _log = LogFactoryUtil.getLog(TimeComingUtils.class);

	public static final int VALUE_HOUR = 60;
	public static final int VALUE_TIME_ZONE = 7;
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

	public static long getTimeComing(Date dueDate, double durationCountComing, int durationUnit, long groupId) {

		// Calculator time working
		long hoursCount = processHoursCount(durationCountComing, durationUnit);
		//_log.info("resultHours: "+hoursCount);

		List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupIdAndType(groupId, 0);
		List<Holiday> extendWorkDayList = HolidayLocalServiceUtil.getHolidayByGroupIdAndType(groupId, 1);

		long dueDateComing = getTimeComingDate(groupId, dueDate, hoursCount, holidayList, extendWorkDayList);
		_log.info("dueDateComing: "+dueDateComing);
		return dueDateComing;
	}

	private static long processHoursCount(double durationCount, int durationUnit) {
		long resultHours = 0;
		//_log.info("durationCount: "+durationCount);
		if (durationUnit == 0) {
			resultHours = (long) (durationCount * 8);
		} else {
			resultHours = (long) durationCount;
		}
		return resultHours;
	}

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

	public static long getTimeComingDate(long groupId, Date dueDate, long hoursCountComing, List<Holiday> holidayList,
			List<Holiday> extendWorkDayList) {

		/* format pattern = "3 10:30" */
		if (dueDate == null) {
			return 0;
		}
		//Get info day off and day work
		getDayByGroupId(groupId);
		//_log.info("strDayOff: "+strDayOff);

		Calendar baseDateCal = Calendar.getInstance();
		baseDateCal.setTime(dueDate);

		try {

			long countDay = hoursCountComing / 8;
			int countHours = (int) hoursCountComing % 8;
//			int count = 0;
			if (countDay > 0 && countDay == 1) {
				baseDateCal.add(Calendar.DATE, -1);
				boolean flagCheckDay = checkDay(baseDateCal, dueDate, holidayList, extendWorkDayList);
				while(flagCheckDay) {
					baseDateCal.add(Calendar.DATE, -1);
					flagCheckDay = checkDay(baseDateCal, dueDate, holidayList, extendWorkDayList);
				}
			} else if (countDay > 1) {

				int i = 0;
				while(i < countDay) {
					baseDateCal.add(Calendar.DATE, -1);
					boolean flagCheckDay = checkDay(baseDateCal, dueDate, holidayList, extendWorkDayList);
					if(!flagCheckDay) {
						i++;
					}
				}
			}
			
			if (countHours > 0) {
				//_log.info("countHours: "+countHours);
				
				int hours = baseDateCal.get(Calendar.HOUR_OF_DAY);
				//_log.info("hours: "+hours);
				int minutes = baseDateCal.get(Calendar.MINUTE);
				//_log.info("minutes: "+minutes);
				int dayOfWeek = baseDateCal.get(Calendar.DAY_OF_WEEK);
				if (Validator.isNotNull(dayOfWeek)) {
					WorkTime workTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, dayOfWeek);
					//_log.info("workTime: "+JSONFactoryUtil.looseSerialize(workTime));
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
							//_log.info("hoursList: "+JSONFactoryUtil.looseSerialize(hoursList));
							int hoursComming = 0;
							if (hourArr1 != null && hourArr2 != null) {
								processTimeWorking(hourArr1, hourArr2);
								//_log.info("startHourMorning: "+startHourMorning);
								//_log.info("startMinuteMorning: "+startMinuteMorning);
								//_log.info("endHourMorning: "+endHourMorning);
								//_log.info("endMinuteMorning: "+endMinuteMorning);
								//_log.info("startHourAfterNoon: "+startHourAfterNoon);
								//_log.info("startMinuteAfterNoon: "+startMinuteAfterNoon);
								//_log.info("endHourAfterNoon: "+endHourAfterNoon);
								//_log.info("endMinuteAfterNoon: "+endMinuteAfterNoon);
								if (startHourMorning <= hours && hours <= endHourMorning) {
									hoursComming = hours - countHours;
									//_log.info("hoursComming_morning: "+hoursComming);
									if (hoursComming == startHourMorning && minutes < startMinuteMorning) {
										int minuteComing = endMinuteAfterNoon - (startMinuteMorning - minutes);
										if (minuteComing < 0) {
											hoursComming = endHourAfterNoon - 1;
											minutes = 60 + minuteComing;
										} else {
											hoursComming = endHourAfterNoon;
											minutes = minuteComing;
										}
										//
									} else if (hoursComming < startHourMorning){
										int subHourMorning =  countHours - (hours - startHourMorning);
										//_log.info("subHourMorning: "+subHourMorning);
										hoursComming = endHourAfterNoon - subHourMorning;
										//_log.info("hoursComming: "+hoursComming);
										
										baseDateCal = processHourCalendar(baseDateCal);
										
										if (hoursComming == startHourAfterNoon && minutes < startMinuteAfterNoon) {
											int minuteComing = endMinuteMorning - (startMinuteAfterNoon - minutes);
											if (minuteComing < 0) {
												hoursComming = endHourMorning - 1;
												minutes = 60 + minuteComing;
											} else {
												hoursComming = endHourMorning;
												minutes = minuteComing;
											}
										} else if(hoursComming < startHourAfterNoon){
											hoursComming = endHourMorning - (startHourAfterNoon - hoursComming);
										}
									}

									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursComming);
									baseDateCal.set(Calendar.MINUTE, minutes);

									return baseDateCal.getTimeInMillis();
									//
								}

								if (startHourAfterNoon <= hours && hours <= endHourAfterNoon) {
									hoursComming = hours - countHours;
									//_log.info("hoursComming: "+hoursComming);
									if (hoursComming == startHourAfterNoon && minutes < startMinuteAfterNoon) {
										int minuteComing = endMinuteMorning - (startMinuteAfterNoon - minutes);
										if (minuteComing < 0) {
											hoursComming = endHourMorning - 1;
											minutes = 60 + minuteComing;
										} else {
											hoursComming = endHourMorning;
											minutes = minuteComing;
										}
										//
									} else if (hoursComming < startHourAfterNoon){
										hoursComming = endHourMorning - (startHourAfterNoon - hoursComming);
										//_log.info("hoursComming: "+hoursComming);
										if (hoursComming == startHourMorning && minutes < startMinuteMorning) {
											baseDateCal = processHourCalendar(baseDateCal);
											//
											int minuteComing = endMinuteAfterNoon - (startMinuteMorning - minutes);
											if (minuteComing < 0) {
												hoursComming = endHourAfterNoon - 1;
												minutes = 60 + minuteComing;
											} else {
												hoursComming = endHourAfterNoon;
												minutes = minuteComing;
											}
										} else if (hoursComming < startHourMorning) {
											//
											baseDateCal = processHourCalendar(baseDateCal);
											//
											hoursComming = endHourAfterNoon - (startHourMorning - hoursComming);
											//_log.info("hoursComming: "+hoursComming);
										}
									}
//									_log.info("hoursOverdue: "+hoursOverdue);
									baseDateCal.set(Calendar.HOUR_OF_DAY, hoursComming);
									baseDateCal.set(Calendar.MINUTE, minutes);
									
									return baseDateCal.getTimeInMillis();
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return baseDateCal.getTimeInMillis();
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

	//LamTV_ Process checkDay
	private static boolean checkDay(Calendar baseDateCal, Date dueDate, List<Holiday> holidayList,
			List<Holiday> extendWorkDayList) {

		boolean flagCheckDay = false;
		try {
			/**
			 * Kiem tra ngay xu ly co trung vao list ngay nghi da config hay
			 * chua, Neu trung thi + them ngay xu ly
			 */
			boolean isHoliday = isHoliday(baseDateCal, holidayList);
			//Check day is Day off
			boolean isDayOff = false;
//			_log.info("baseDateCal.get(Calendar.DAY_OF_WEEK): "+baseDateCal.get(Calendar.DAY_OF_WEEK));
			if (strDayOff.contains(String.valueOf(baseDateCal.get(Calendar.DAY_OF_WEEK)))
					&& !isHoliday(baseDateCal, extendWorkDayList)) {
				isDayOff = true;
			}

			if (isHoliday || isDayOff) {
				flagCheckDay = true;
			}
			
		}
		catch (Exception e) {
			_log.error(e);
		}

		return flagCheckDay;
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

	/**
	 * @param startDate
	 * @param endDate
	 * @return minutesGoing
	 */
	public static int getCountDateByHoliday(long startDate, long endDate, int numberDate, long groupId) {

		int count = 0;
		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTimeInMillis(startDate);
//		_log.info("startDateCal: "+startDateCal.get(Calendar.DATE));

		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTimeInMillis(endDate);
//		_log.info("endDateCal: "+endDateCal.get(Calendar.DATE));
		
		//Get info day off and day work
		getDayByGroupId(groupId);
//		_log.info("strDayOff: "+strDayOff);

		boolean flagCompareDate = false;
//		_log.info("numberDate: "+numberDate);
		for (int i = 0; i <= numberDate; i++) {
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
//			_log.info("isHoliday: "+isHoliday);
//			_log.info("isDayOff: "+isDayOff);
			startDateCal.add(Calendar.DATE, 1);
			if (isHoliday || isDayOff) {
//				if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
//					startDateCal.add(Calendar.DAY_OF_MONTH, 2);
//					count += 2;
//					flagCompareDate = compareDate(startDateCal, endDateCal);
//				} else {
//				startDateCal.add(Calendar.DATE, 1);
				count += 1;
//				}

			}
			flagCompareDate = compareDate(startDateCal, endDateCal);
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

	//Process count hours
	public static int getCountHoursByHoliday(long startDate, long endDate, long groupId) {

		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTimeInMillis(startDate);
//		_log.info("startDateCalHOUR: "+startDateCal.get(Calendar.DATE));

		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTimeInMillis(endDate);
//		_log.info("endDateCalHOUR: "+endDateCal.get(Calendar.DATE));
		
		//Get info day off and day work
		getDayByGroupId(groupId);
//		_log.info("strDayOff: "+strDayOff);

		int startDayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
		int startHourOfDay = startDateCal.get(Calendar.HOUR_OF_DAY);

		int endDayOfWeek = endDateCal.get(Calendar.DAY_OF_WEEK);
		int endHourOfDay = endDateCal.get(Calendar.HOUR_OF_DAY);

		int hasOverDue = 0;
		boolean flagCompareDate = compareDate(startDateCal, endDateCal);
		if (flagCompareDate) {
//			_log.info("111: ");
			if (startHourMorning > startHourOfDay) {
				// Process hours of morning
				if (endHourOfDay < endHourMorning) {
					hasOverDue = endHourOfDay - startHourMorning;
				} else if (endHourOfDay == endHourMorning) {
					hasOverDue = endHourMorning - startHourMorning;
					// hasOverDue = countHoursWork(hourArr1);
				}
				// Process hours of afternoon
				if (startHourAfterNoon <= endHourOfDay && endHourOfDay < endHourAfterNoon) {
					hasOverDue = (endHourOfDay - startHourAfterNoon) + (endHourMorning - startHourMorning);
				} else if (endHourOfDay == endHourAfterNoon) {
					hasOverDue = (endHourAfterNoon - startHourAfterNoon) + (endHourMorning - startHourMorning);
					// hasOverDue = countHoursWork(hourArr1) + countHoursWork(hourArr1);
				}
			}
			//
			if (startHourMorning <= startHourOfDay && startHourOfDay < endHourMorning) {
				if (endHourOfDay <= endHourMorning) {
					hasOverDue = endHourOfDay - startHourOfDay;
				} else if (endHourOfDay <= endHourAfterNoon) {
					hasOverDue = (endHourOfDay - startHourAfterNoon) + (endHourMorning - startHourOfDay);
				}
			}

			if (endHourMorning < startHourOfDay) {
				if (startHourOfDay < startHourAfterNoon) {
					hasOverDue = endHourOfDay - startHourAfterNoon;
				} else if (startHourOfDay <= endHourAfterNoon) {
					hasOverDue = endHourOfDay - startHourOfDay;
				}
			}

//			return hasOverDue;
		} else {
//			_log.info("222: ");
			List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupId(groupId);
			boolean isHoliday = false;
			if (holidayList != null && holidayList.size() > 0) {
				isHoliday = isHoliday(startDateCal, holidayList);
			}
			// Check day is Day off
			boolean isDayOff = false;
			if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
				isDayOff = true;
			}

			if (isHoliday || isDayOff) {
//				_log.info("333: ");
				// Process hours of morning
				if (endHourOfDay <= endHourMorning) {
					hasOverDue = endHourOfDay - startHourMorning;
				} else if (startHourAfterNoon <= endHourOfDay && endHourOfDay <= endHourAfterNoon) {
					hasOverDue = (endHourOfDay - startHourAfterNoon) + (endHourMorning - startHourMorning);
				}
			} else {
//				_log.info("444: ");
				WorkTime startWorkTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, startDayOfWeek);
				if (startWorkTime != null) {
					String strHours = startWorkTime.getHours();
					String[] hoursList = StringUtil.split(strHours);

					if (hoursList != null && hoursList.length > 0) {
						String[] hourArrOne = hoursList[0].split(StringPool.DASH);
						String[] hourArrTwo = hoursList[1].split(StringPool.DASH);

						if (hourArrOne != null && hourArrTwo != null) {
							processTimeWorking(hourArrOne, hourArrTwo);

							if (startHourMorning <= startHourOfDay && startHourOfDay <= endHourMorning) {
								hasOverDue += (endHourMorning - startHourOfDay)
										+ (endHourAfterNoon - startHourAfterNoon);
							}

							if (endHourMorning < startHourOfDay) {
								if (startHourOfDay < startHourAfterNoon) {
									hasOverDue += endHourAfterNoon - startHourAfterNoon;
								} else if (startHourOfDay <= endHourAfterNoon) {
									hasOverDue += endHourAfterNoon - startHourOfDay;
								}
							}
						}
					}
				}
			}

			WorkTime endWorkTime = WorkTimeLocalServiceUtil.fetchByF_day(groupId, endDayOfWeek);
			if (endWorkTime != null) {
				String strHours = endWorkTime.getHours();
				String[] hoursList = StringUtil.split(strHours);

				if (hoursList != null && hoursList.length > 0) {
					String[] hourArrOne = hoursList[0].split(StringPool.DASH);
					String[] hourArrTwo = hoursList[1].split(StringPool.DASH);

					if (hourArrOne != null && hourArrTwo != null) {
						processTimeWorking(hourArrOne, hourArrTwo);
						//
						if (startHourMorning <= endHourOfDay && endHourOfDay <= endHourMorning) {
							hasOverDue += endHourOfDay - startHourMorning;
						}

						if (startHourAfterNoon <= endHourOfDay && endHourOfDay <= endHourAfterNoon) {
							hasOverDue += (endHourMorning - startHourMorning) + (endHourOfDay - startHourAfterNoon);
						}
					}
				}
			}
		}
//		_log.info("hasOverDue: "+hasOverDue);
		return hasOverDue;
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

	private static Calendar processHourCalendar(Calendar baseDateCal) {

		baseDateCal.add(Calendar.DATE, -1);
		int dayOfWeekCheck = baseDateCal.get(Calendar.DAY_OF_WEEK);
		//Check dayOfWeek is dayOff or dayWork
		while (strDayOff.contains(String.valueOf(dayOfWeekCheck))) {
			baseDateCal.add(Calendar.DATE, -1);
			dayOfWeekCheck = baseDateCal.get(Calendar.DAY_OF_WEEK);
		}

		return baseDateCal;
	}

}