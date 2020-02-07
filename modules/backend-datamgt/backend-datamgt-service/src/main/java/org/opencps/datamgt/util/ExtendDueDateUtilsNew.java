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

public class ExtendDueDateUtilsNew {

	private static Log _log = LogFactoryUtil.getLog(ExtendDueDateUtils.class);

	public static final int VALUE_HOUR = 60;
	public static final int VALUE_TIME_ZONE = 7;
	private static final long VALUE_CONVERT_DATE_WORKING_TIMESTAMP = 1000 * 60 * 60 * 8;
	private static final long VALUE_CONVERT_HOUR_TIMESTAMP = 1000 * 60 * 60;
	private static final long VALUE_CONVERT_MINUTE_TIMESTAMP = 1000 * 60;
	// get value day off
	//private static volatile String strDayOff = StringPool.BLANK;
	// get value time working
	private static volatile Integer startAM = 0;
	private static volatile Integer startHourAM = 0;
	private static volatile Integer startMinuteAM = 0;
	private static volatile Integer strStartAM = 0;
	private static volatile Integer endAM = 0;
	private static volatile Integer endHourAM = 0;
	private static volatile Integer endMinuteAM = 0;
	private static volatile Integer strEndAM = 0;
	private static volatile Integer startPM = 0;
	private static volatile Integer startHourPM = 0;
	private static volatile Integer startMinutePM = 0;
	private static volatile Integer endPM = 0;
	private static volatile Integer endHourPM = 0;
	private static volatile Integer endMinutePM = 0;

	private static void processTimeWorking(String[] hourArr1, String[] hourArr2) {
		if (Validator.isNotNull(hourArr1[0])) {
			String[] strAMSplit = StringUtil.split(hourArr1[0], StringPool.COLON);
			if (strAMSplit != null) {
				startAM = Integer.parseInt(strAMSplit[0] + strAMSplit[1]);
				//startMinuteAM = Integer.parseInt(strAMSplit[1]);
			}
		}
		if (Validator.isNotNull(hourArr1[1])) {
			String[] strAMSplit = StringUtil.split(hourArr1[1], StringPool.COLON);
			if (strAMSplit != null) {
				endAM = Integer.parseInt(strAMSplit[0] + strAMSplit[1]);
				//endMinuteAM = Integer.parseInt(strAMSplit[1]);
			}
		}

		if (Validator.isNotNull(hourArr2[0])) {
			String[] strPMSplit = StringUtil.split(hourArr2[0], StringPool.COLON);
			if (strPMSplit != null) {
				startPM = Integer.parseInt(strPMSplit[0] + strPMSplit[1]);
				//startMinutePM = Integer.parseInt(strPMSplit[1]);
			}
		}

		if (Validator.isNotNull(hourArr2[1])) {
			String[] strPMSplit = StringUtil.split(hourArr2[1], StringPool.COLON);
			if (strPMSplit != null) {
				endPM = Integer.parseInt(strPMSplit[0] + strPMSplit[1]);
				//endMinutePM = Integer.parseInt(strPMSplit[1]);
			}
		}
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
		//String strDayOff = CommonDateUtils.getDayByGroupId(groupId);
//		_log.info("strDayOff: "+strDayOff);

		//boolean flagCompareDate = false;
//		_log.info("numberDate: "+numberDate);
		boolean flagCompareDate = CommonDateUtils.compareDate(startDateCal, endDateCal);
//		String[] hourArrStart = null;
//		String[] hourArrEnd = null;
		if (flagCompareDate) {
			int dayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
			//_log.info("dayOfWeek: "+dayOfWeek);
			//int hoursStart = startDateCal.get(Calendar.HOUR_OF_DAY);
			//_log.info("hoursStart: "+hoursStart);
			//int minuteStart = startDateCal.get(Calendar.MINUTE);

			//int dayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
			int hoursEnd = endDateCal.get(Calendar.HOUR_OF_DAY);
			//_log.info("hoursEnd: "+hoursEnd);
			int minuteEnd = endDateCal.get(Calendar.MINUTE);
			//
			int timeEnd = Integer.parseInt(String.valueOf(hoursEnd) + minuteEnd);
			//
			List<String[]> workingArr = CommonDateUtils.processPartWorking(dayOfWeek, groupId);
			//
//			_log.info("hourArrStart: "+hourArrStart);
//			_log.info("hourArrEnd: "+hourArrEnd);
			if (workingArr != null && workingArr.size() > 0) {
				String[] hourArrStart = workingArr.get(0);
				String[] hourArrEnd = workingArr.get(1);
				if (hourArrStart != null && hourArrEnd != null) {
					processTimeWorking(hourArrStart, hourArrEnd);
					//
					int statusWorking = processStatusWorking(timeEnd);
					switch (statusWorking) {
					//Case 1: Làm việc ngoài giờ buổi sáng
					case 1:
						break;
					// Case 2: Làm việc vào buổi sáng 
					case 2:
						int statusWorkingAM = processStatusWorking(timeEnd);
						if (statusWorkingAM == 1) {
							startDateCal.set(Calendar.HOUR_OF_DAY, startHourAM);
							startDateCal.set(Calendar.MINUTE, startMinuteAM);
							//
							extendHour = endDate - startDateCal.getTimeInMillis();
						} else if (statusWorkingAM == 2) {
							extendHour = endDate - startDate;
						}
						break;
					//Case 3:  Làm việc vào giờ nghỉ trưa
					case 3:
						int statusWorkingOffAM = processStatusWorking(timeEnd);
						// Set time end = endAM
						endDateCal.set(Calendar.HOUR_OF_DAY, endHourAM);
						endDateCal.set(Calendar.HOUR_OF_DAY, endHourAM);
						
						if (statusWorkingOffAM == 1) {
							startDateCal.set(Calendar.HOUR_OF_DAY, startHourAM);
							startDateCal.set(Calendar.MINUTE, startMinuteAM);
							//
							extendHour = endDateCal.getTimeInMillis() - startDateCal.getTimeInMillis();
						} else if (statusWorkingOffAM == 2) {
							extendHour = endDateCal.getTimeInMillis() - startDate;
						}
						break;
					//Case 4: Làm việc vào buổi chiều
					case 4:
						int statusWorkingPM = processStatusWorking(timeEnd);
						if (statusWorkingPM == 1) {
							startDateCal.set(Calendar.HOUR_OF_DAY, startHourAM);
							startDateCal.set(Calendar.MINUTE, startMinuteAM);
							//
							extendHour = endDate - startDateCal.getTimeInMillis() - countTimeOffNoon();
						} else if (statusWorkingPM == 2) {
							extendHour = endDate - startDate - countTimeOffNoon();
						} else if (statusWorkingPM == 3) {
							startDateCal.set(Calendar.HOUR_OF_DAY, startHourPM);
							startDateCal.set(Calendar.MINUTE, startMinutePM);
							//
							extendHour = endDate - startDateCal.getTimeInMillis();
						} else if (statusWorkingPM == 4) {
							extendHour = endDate - startDate;
						}
						break;
					//Case 5: Làm việc ngoài buổi chiều
					case 5:
						int statusWorkingOverPM = processStatusWorking(timeEnd);
						// Set time end = endPM
						endDateCal.set(Calendar.HOUR_OF_DAY, endHourPM);
						endDateCal.set(Calendar.HOUR_OF_DAY, endHourPM);
						switch(statusWorkingOverPM) {
						case 1:
							startDateCal.set(Calendar.HOUR_OF_DAY, startHourAM);
							startDateCal.set(Calendar.MINUTE, startMinuteAM);
							//
							extendHour = endDateCal.getTimeInMillis() - startDateCal.getTimeInMillis() - countTimeOffNoon();
							break;
						case 2:
							extendHour = endDateCal.getTimeInMillis() - startDate - countTimeOffNoon();
							break;
						case 3:
							startDateCal.set(Calendar.HOUR_OF_DAY, startHourPM);
							startDateCal.set(Calendar.MINUTE, startMinutePM);
							//
							extendHour = endDateCal.getTimeInMillis() - startDateCal.getTimeInMillis();
							break;
						case 4:
							//
							extendHour = endDateCal.getTimeInMillis() - startDate;
							break;
						}
						break;
					default:
						break;
					}
				}
			}
		} 
//		else {
//			int dayOfWeek = startDateCal.get(Calendar.DAY_OF_WEEK);
//			
//			
//			
//			
//			List<String[]> workingArr = CommonDateUtils.processPartWorking(dayOfWeek, groupId);
//			//
//			//_log.info("workingArr.get(0)111: "+JSONFactoryUtil.looseSerialize(workingArr));
//			if (workingArr != null && workingArr.size() > 0) {
//				String[] hourArrStart = workingArr.get(0);
//				String[] hourArrEnd = workingArr.get(1);
//				if (hourArrStart != null && hourArrEnd != null) {
//					processTimeWorking(hourArrStart, hourArrEnd);
//					//
//					int hoursStart = startDateCal.get(Calendar.HOUR_OF_DAY);
//					int minuteStart = startDateCal.get(Calendar.MINUTE);
//					//_log.info("hoursStart: "+hoursStart);
//					//_log.info("minuteStart: "+minuteStart);
//					long timeStampStart = hoursStart * VALUE_CONVERT_HOUR_TIMESTAMP
//							+ minuteStart * VALUE_CONVERT_MINUTE_TIMESTAMP;
//					long timeStampEnd = endHourPM * VALUE_CONVERT_HOUR_TIMESTAMP
//							+ endMinutePM * VALUE_CONVERT_MINUTE_TIMESTAMP;
//					//long returnTest = 0;
//					//_log.info("startHourAM: "+startHourAM);
//					//_log.info("endHourAM: "+endHourAM);
//					//_log.info("endHourPM: "+endHourPM);
//					//_log.info("endMinutePM: "+endMinutePM);
//					if (startHourAM <= hoursStart && hoursStart <= endHourAM) {
//						extendHour = timeStampEnd - timeStampStart - countTimeOffNoon();
//						//_log.info("extendHour11: "+extendHour);
//					} else if (startHourPM <= hoursStart && hoursStart <= endHourPM) {
//						extendHour = timeStampEnd - timeStampStart;
//						//_log.info("extendHour11: "+extendHour);
//					}
//				}
//			}
//
//			// Then encrease startDate + 1
//			startDateCal.add(Calendar.DATE, 1);
//			int dayOfWeek111 = startDateCal.get(Calendar.DAY_OF_WEEK);
//			for (int i = 0; i < 2; i++) {
//				if (strDayOff.contains(String.valueOf(dayOfWeek111))) {
//					startDateCal.add(Calendar.DATE, 1);
//					dayOfWeek111 = startDateCal.get(Calendar.DAY_OF_WEEK);
//				} else {
//					break;
//				}
//			}
//			
//			List<String[]> workingEndArr = CommonDateUtils.processPartWorking(dayOfWeek111, groupId);
//			//_log.info("workingEndArr: "+JSONFactoryUtil.looseSerialize(workingEndArr));
//			if (workingEndArr != null && workingEndArr.size() > 0) {
//				String[] hourArr1 = workingEndArr.get(0);
//				String[] hourArr2 = workingEndArr.get(1);
//				//_log.info("hourArr1: "+hourArr1);
//				//_log.info("hourArr2: "+hourArr2);
//				if (hourArr1 != null && hourArr2 != null) {
//					processTimeWorking(hourArr1, hourArr2);
//					//
//					
//					int hoursEnd = endDateCal.get(Calendar.HOUR_OF_DAY);
//					int minuteEnd = endDateCal.get(Calendar.MINUTE);
//					//_log.info("hoursEnd: "+hoursEnd);
//					//_log.info("minuteEnd: "+minuteEnd);
//					long timeStampEnd = hoursEnd * VALUE_CONVERT_HOUR_TIMESTAMP
//							+ minuteEnd * VALUE_CONVERT_MINUTE_TIMESTAMP;
//					long timeStampStart = startHourAM * VALUE_CONVERT_HOUR_TIMESTAMP
//							+ startMinuteAM * VALUE_CONVERT_MINUTE_TIMESTAMP;
//					//_log.info("startHourAM: "+startHourAM);
//					//_log.info("endHourAM: "+endHourAM);
//					//_log.info("endHourPM: "+endHourPM);
//					//_log.info("endMinutePM: "+endMinutePM);
//					if (startHourAM <= hoursEnd && hoursEnd <= endHourAM) {
//						extendHour += timeStampEnd - timeStampStart;
//						//_log.info("extendHour22: "+extendHour);
//					} else if (startHourPM <= hoursEnd && hoursEnd <= endHourPM) {
//						extendHour += timeStampEnd - timeStampStart - countTimeOffNoon();
//						//_log.info("extendHour22: "+extendHour);
//					}
//				}
//			}
//			//Process time of day
//			extendHour += getCountTimeWorkingOfDay(startDateCal, endDateCal, groupId);
//
//		}

		return extendHour;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @return minutesGoing
	 */
//	public static long getCountTimeWorkingOfDay(Calendar startDateCal, Calendar endDateCal, long groupId) {
//
//		int count = 0;
////		_log.info("strDayOff: "+strDayOff);
//		List<Holiday> holidayList = HolidayLocalServiceUtil.getHolidayByGroupId(groupId);
//
//		boolean flagCompareDate = CommonDateUtils.compareDate(startDateCal, endDateCal);
////		_log.info("flagCompareDate: "+flagCompareDate);
//		while(!flagCompareDate) {
//			boolean isHoliday = false;
//			if (holidayList != null && holidayList.size() > 0) {
//				isHoliday = CommonDateUtils.isHoliday(startDateCal, holidayList);
//			}
//			//Check day is Day off
//			boolean isDayOff = false;
//			if (strDayOff.contains(String.valueOf(startDateCal.get(Calendar.DAY_OF_WEEK)))) {
//				isDayOff = true;
//			}
//			startDateCal.add(Calendar.DATE, 1);
//			if (!isHoliday && !isDayOff) {
//				count += 1;
//			}
//			flagCompareDate = CommonDateUtils.compareDate(startDateCal, endDateCal);
//		}
//
//		return count * VALUE_CONVERT_DATE_WORKING_TIMESTAMP;
//	}

	/** Process Time Off between AM and PM **/
	private static long countTimeOffNoon() {
		long start = endHourAM * 60 + endMinuteAM;
		long end = startHourPM * 60 + startMinutePM;
		return (end - start) * VALUE_CONVERT_MINUTE_TIMESTAMP;
	}

	/** Status working user **/
	private static int processStatusWorking(int timeEnd) {
		//Thời gian thực hiện dateOption = 3

		//Case 1: Làm việc ngoài giờ buổi sáng
		if (timeEnd < startAM) {
			return 1;
		}

		// Case 2: Làm việc vào buổi sáng 
		if (startAM <= timeEnd && timeEnd <= endAM) {
			return 2;
		}

		//Case 3:  Làm việc vào giờ nghỉ trưa 
		if (endAM < timeEnd && timeEnd <= startPM) {

			return 3;
		}

		//Case 4: Làm việc vào buổi chiều
		if (startPM < timeEnd && timeEnd <= endPM) {
			return 4;
		}

		//Case 4: Làm việc ngoài giờ buổi chiều
		if (timeEnd > endPM) {
			return 5;
		}

		return 0;
	}

}