
package org.opencps.datamgt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class DueDateUtils {

	private static Log _log = LogFactoryUtil.getLog(DueDateUtils.class);

	public static final String HOUR_COLON_TIME_FORMAT = "HH:mm";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_SPACE_TIME_FORMAT =
		DATE_FORMAT + StringPool.SPACE + HOUR_COLON_TIME_FORMAT;
	public static final int DAY_TO_MILISECOND = 1 * 24 * 60 * 60 * 1000;
	public static final int HOUR_TO_MILISECOND = 1 * 60 * 60 * 1000;

	private Date startDate;
	private Date dueDate;
	private double durationCount;
	private int durationUnit;
	private long groupId;
	private String startAMStr;
	private String endAMStr;
	private String startPMStr;
	private String endPMStr;
	private double startAM;
	private double endAM;
	private double startPM;
	private double endPM;
	private String startDateTimeStr;
	private double startDateTimeNum;

	// start date la ngay khong lam viec
	private boolean isHolidayType0;

	// start date la ngay lam bu
	private boolean isHolidayType1;

	private List<WorkTime> workTimes;

	// ngay lam viec tiep theo
	private Date nextWorkDay;
	private int countNextDay;

	public DueDateUtils(
		Date startDate, double durationCount, int durationUnit, long groupId) {

		super();

		this.durationCount = durationCount;
		this.durationUnit = durationUnit;
		this.groupId = groupId;

		if (startDate != null) {

			try {
				Calendar calStartDate = Calendar.getInstance();
				calStartDate.setTime(startDate);
				if (this.durationUnit == 0) {

					calStartDate.add(
						Calendar.MILLISECOND,
						(int) (this.durationCount * DAY_TO_MILISECOND));
				}
				else if (this.durationUnit == 1) {

					calStartDate.add(
						Calendar.MILLISECOND,
						(int) (this.durationCount * HOUR_TO_MILISECOND));
				}
				this.startDate = calStartDate.getTime();
				this.dueDate = calStartDate.getTime();
				this.startDateTimeStr =
					_dateToString(this.startDate, HOUR_COLON_TIME_FORMAT);
				this.startDateTimeNum =
					_stringToNumberHourColon(this.startDateTimeStr);
				_log.debug("========" + startDateTimeStr + startDateTimeNum);
				// 1st set workTimes
				this._setWorkTimes();
				// 2st find a working day
				this._findAWorkingDayNSetDueDate(this.startDate);
			}
			catch (Exception e) {

				_log.error(e);
			}
		}

	}

	private void _setDueDate() {

		Date primaryStartDate = Validator.isNotNull(this.nextWorkDay)
			? this.nextWorkDay : this.startDate;
		String startDateDateStr = _dateToString(primaryStartDate, DATE_FORMAT);

		this._setHourTimeWorking(primaryStartDate);

		// TODO: check logic to calculate dueDate

		// ngay lam viec
		//
		// 00 ---> startAM ---> endAM ---> startPM ---> endPM ---> 23.59
		//
		// -----1-------------0----------2------------0----------3-------
		// ngay nghi
		//
		// 00 ---> startAM ---> endAM ---> startPM ---> endPM ---> 23.59
		//
		// ----3-------------3----------3------------3----------3--------
		// 0: khong thay doi
		// 1: den dau gio sang
		// 2: den dau gio chieu
		// 3: den dau gio sang ngay lam viec tiep theo

		_log.debug("========" + this.startDateTimeNum);
		_log.debug("========" + this.startAM);
		_log.debug("========" + this.endAM);
		_log.debug("========" + this.startPM);
		_log.debug("========" + this.endPM);
		_log.debug("========" + this.nextWorkDay);

		if (this.startDateTimeNum <= this.startAM ||
			Validator.isNotNull(this.nextWorkDay)) {

			// case 1 or forword from case 3
			this.dueDate = this._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startAMStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.startAM &&
			this.startDateTimeNum <= this.endAM) {

			// case 0
			this.dueDate = this._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.endAM &&
			this.startDateTimeNum <= this.startPM) {

			// case 2
			this.dueDate = this._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startPMStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.startPM &&
			this.startDateTimeNum <= this.endPM) {

			// case 0
			this.dueDate = this._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.endPM) {

			// case 3
			this._setNextWorkDay();
		}
		else {

			_log.debug("other case");
		}

	}

	private void _findAWorkingDayNSetDueDate(Date date) {

		// TODO:
		// call HolidayLocalServiceUtil.fetchByF_holidayDate(groupId, startDate)
		String truncDateStr = this._dateToString(date, DATE_FORMAT);
		Date truncDate = this._stringToDate(
			truncDateStr + " 00:00:00", DATE_FORMAT + " HH:mm:ss");
		Holiday holiday = HolidayLocalServiceUtil.fetchByF_holidayDate(
			this.groupId, truncDate);
		if (Validator.isNotNull(holiday)) {

			this.isHolidayType0 = holiday.getHolidayType() == 0;
			this.isHolidayType1 = holiday.getHolidayType() == 1;
		}
		else {

			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(date);
			int dayOfStartDate = calStartDate.get(Calendar.DAY_OF_WEEK);

			_log.debug(
				"========this.workTimes.size()========" +
					this.workTimes.size());
			boolean isWeekend = false;
			for (WorkTime workTime : this.workTimes) {

				if (workTime.getDay() == dayOfStartDate) {

					isWeekend = true;
					break;
				}
			}
			_log.debug(
				dayOfStartDate + "========isWeekend==========" + isWeekend);
			this.isHolidayType0 = isWeekend && this.workTimes.size() > 0;
		}

		// neu la ngay nghi thi next ngay tiep theo
		if (this.isHolidayType0 && countNextDay < 15) {

			// warning: recusive to find a next work day
			this._setNextWorkDay();
		}
		else {

			this._setDueDate();
		}
	}

	private void _setWorkTimes() {

		this.workTimes = WorkTimeLocalServiceUtil.getByGroupId(this.groupId);
	}

	private void _setHourTimeWorking(Date date) {

		Calendar calStartDate = Calendar.getInstance();
		calStartDate.setTime(date);
		int dayOfStartDate = calStartDate.get(Calendar.DAY_OF_WEEK);

		// Step 1 Check is holiday
		// holiday hoursWorking is MONDAY
		// TODO: check holiday

		if (this.isHolidayType1) {

			// ngay lam bu
			this._setHourTimeWorkingByDay(Calendar.MONDAY);
		}
		else {

			// ngay binh thuong
			this._setHourTimeWorkingByDay(dayOfStartDate);
		}

	}

	private void _setNextWorkDay() {

		this.countNextDay += 1;
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.setTime(
			Validator.isNull(this.nextWorkDay)
				? this.startDate : this.nextWorkDay);
		calStartDate.add(Calendar.DATE, 1);
		this.nextWorkDay = calStartDate.getTime();
		this._findAWorkingDayNSetDueDate(this.nextWorkDay);

		// set to startAM
		this.startDateTimeStr = this.startAMStr;
		this.startDateTimeNum = this.startAM;
	}

	private void _setHourTimeWorkingByDay(int day) {

		String startAM = "00:00";
		String endAM = "00:00";
		String startPM = "00:00";
		String endPM = "00:00";

		try {

			int checkDay = day % Calendar.DAY_OF_WEEK;

			for (WorkTime workTime : this.workTimes) {

				if (workTime.getDay() == Calendar.MONDAY ||
					workTime.getDay() == checkDay) {

					_log.debug(
						"workTime.getDay() == checkDay===========" +
							(workTime.getDay() == checkDay));
					// workTime.hours = 07:00-11:30,13:30-17:00
					String am = workTime.getHours().split(StringPool.COMMA)[0];
					String pm = workTime.getHours().split(StringPool.COMMA)[1];
					startAM = am.split(StringPool.DASH)[0];
					endAM = am.split(StringPool.DASH)[1];
					startPM = pm.split(StringPool.DASH)[0];
					endPM = pm.split(StringPool.DASH)[1];
					if (workTime.getDay() == checkDay) {
						break;
					}
				}
			}
		}
		catch (Exception e) {

			_log.error(e);
		}

		this.startAMStr = startAM;
		this.endAMStr = endAM;
		this.startPMStr = startPM;
		this.endPMStr = endPM;
		this.startAM = _stringToNumberHourColon(startAM);
		this.endAM = _stringToNumberHourColon(endAM);
		this.startPM = _stringToNumberHourColon(startPM);
		this.endPM = _stringToNumberHourColon(endPM);
	}

	private String _dateToString(Date date, String format) {

		return new SimpleDateFormat(format).format(date);
	}

	private Date _stringToDate(String dateStr, String format) {

		try {

			return new SimpleDateFormat(format).parse(dateStr);
		}
		catch (ParseException e) {

			return null;
		}
	}

	private double _stringToNumberHourColon(String hour) {

		// format: HH:mm
		String h = hour.replace(StringPool.COLON, StringPool.PERIOD);
		return Double.parseDouble(h);
	}

	public Date getDueDate() {

		return dueDate;
	}

}
