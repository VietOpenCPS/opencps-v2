
package org.opencps.datamgt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	public static final String DEFAULT_START_AM_STR = "00:00";

	private Date startDate;
	private Date dueDate;
	private int durationCount;
	private int durationUnit;
	private long groupId;
	private String startAMStr = "00:00";
	private String endAMStr = "00:00";
	private String startPMStr = "00:00";
	private String endPMStr = "00:00";
	private int startAM;
	private int endAM;
	private int startPM;
	private int endPM;
	private int tongThoiGianCanBoLamViecTrongNgay;
	private int thoiGianCanBoNghiTrua;
	private int gioCanBoNghiLamViec;

	private String startDateTimeStr;
	private int startDateTimeNum;

	// start date la ngay khong lam viec
	private boolean isHolidayType0;

	// start date la ngay lam bu
	private boolean isHolidayType1;

	private List<WorkTime> workTimes;
	private List<Holiday> holidays;

	// ngay lam viec tiep theo
	private Date nextWorkDay;
	private int countNextDay;
	private int countNextWorkDay;
	private int countNextWorkDayTime;
	private int gioLamViecLe;

	public DueDateUtils(
		Date startDate, int durationCount, int durationUnit, long groupId) {

		super();

		this.durationUnit = durationUnit;
		this.groupId = groupId;
		this.countNextWorkDay = 0;

		if (durationUnit == 0) {
			this.durationCount = durationCount;
		}
		else {
			this.durationCount = durationCount * 100;
		}

		if (startDate != null) {

			try {

				// 1st set workTimes & set holidays
				this._setWorkTimes();
				this._setHolidays(startDate);

				// set start date
				this.setStartDate(startDate);

				// 2st find a working day
				this._findAWorkingDayNSetDueDate(this.startDate);

			}
			catch (Exception e) {

				_log.error(e);
			}
		}

	}

	private void setStartDate(Date startDate) {

		String startDateDateStr =
			SupportUtils._dateToString(startDate, DATE_FORMAT);
		this.startDateTimeStr =
			SupportUtils._dateToString(startDate, HOUR_COLON_TIME_FORMAT);
		this.startDateTimeNum =
			SupportUtils._stringToNumberHourColon(startDateTimeStr);

		this._setHourTimeWorking(startDate);

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

		if (this.startDateTimeNum <= this.startAM) {

			// case 1 or forword from case 3
			this.startDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startAMStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.startAM &&
			this.startDateTimeNum <= this.endAM) {

			// case 0
			this.startDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.endAM &&
			this.startDateTimeNum <= this.startPM) {

			// case 2
			this.startDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startPMStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.startPM &&
			this.startDateTimeNum <= this.endPM) {

			// case 0
			this.startDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		//else if (this.startDateTimeNum > this.endPM) {
		else {

			// set to startAM
			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(startDate);
			calStartDate.add(Calendar.DATE, 1);
			startDateDateStr =
				SupportUtils._dateToString(calStartDate.getTime(), DATE_FORMAT);

			this.startDateTimeStr = DEFAULT_START_AM_STR;
			this.startDateTimeNum =
				SupportUtils._stringToNumberHourColon(this.startDateTimeStr);
			this.startDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		//else {

		//	this.startDate = startDate;
		//	_log.info("other case");
		//}

		_log.info(
			"ngay tiep nhan ho so=====" + SupportUtils._dateToString(
				this.startDate, DATE_SPACE_TIME_FORMAT));
	}

	private void _setDueDate() {

		Date primaryStartDate = Validator.isNotNull(this.nextWorkDay)
			? this.nextWorkDay : this.startDate;
		String startDateDateStr =
			SupportUtils._dateToString(primaryStartDate, DATE_FORMAT);

		this._setHourTimeWorking(primaryStartDate);

		// dich thoi gian tra neu som hon gio lam viec
		if (this.startDateTimeStr.equals(DEFAULT_START_AM_STR) ||
			this.startDateTimeNum < this.startAM) {
			this.startDateTimeStr = this.startAMStr;
			this.startDateTimeNum =
				SupportUtils._stringToNumberHourColon(this.startDateTimeStr);
		}

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

		// this.startDateTimeNum = this.gioLamViecLe + this.startDateTimeNum >
		// this.endAM
		// ? this.gioLamViecLe + this.startDateTimeNum +
		// this.thoiGianCanBoNghiTrua
		// : this.gioLamViecLe + this.startDateTimeNum;
		//// sai logic
		this.startDateTimeNum =
			(this.gioLamViecLe + this.startDateTimeNum > this.endAM) &&
				this.startDateTimeNum < this.endAM &&
				this.startDateTimeNum < this.startPM
					? this.gioLamViecLe + this.startDateTimeNum +
						this.thoiGianCanBoNghiTrua
					: this.gioLamViecLe + this.startDateTimeNum;

		this.startDateTimeStr =
			SupportUtils._numberToStringHourColon(this.startDateTimeNum);

		_log.info(
			gioLamViecLe + "====DUA DATE=====" + startDateDateStr + "==HOUR==" +
				this.startDateTimeStr + " " + this.startAM);
		if (this.startDateTimeNum <= this.startAM) {

			// case 1 or forword from case 3
			this.dueDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startAMStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.startAM &&
			this.startDateTimeNum <= this.endAM) {

			// case 0
			this.dueDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.endAM &&
			this.startDateTimeNum <= this.startPM) {

			// case 2
			this.dueDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startPMStr,
				DATE_SPACE_TIME_FORMAT);
		}
		else if (this.startDateTimeNum > this.startPM &&
			this.startDateTimeNum <= this.endPM) {

			// case 0
			this.dueDate = SupportUtils._stringToDate(
				startDateDateStr + StringPool.SPACE + this.startDateTimeStr,
				DATE_SPACE_TIME_FORMAT);
		}
		//else if (this.startDateTimeNum > this.endPM) {
		else {

			// set to startAM
			this.startDateTimeStr = DEFAULT_START_AM_STR;
			this.startDateTimeNum =
				SupportUtils._stringToNumberHourColon(this.startDateTimeStr);
			// case 3
			this._setNextWorkDay();
		}
//		else {
//			_log.info("other case");
//		}
	}

	private void _findAWorkingDayNSetDueDate(Date date) {

		// TODO:
		// call HolidayLocalServiceUtil.fetchByF_holidayDate(groupId, startDate)
		String truncDateStr = SupportUtils._dateToString(date, DATE_FORMAT);
		Holiday holiday = null;
		for (Holiday h : this.holidays) {

			if (SupportUtils._dateToString(
				h.getHolidayDate(), DATE_FORMAT).equals(truncDateStr)) {
				holiday = h;
				break;
			}
		}

		if (Validator.isNotNull(holiday)) {

			this.isHolidayType0 = holiday.getHolidayType() == 0;
			this.isHolidayType1 = holiday.getHolidayType() == 1;
		}
		else {

			this.isHolidayType1 = false;
			Calendar calStartDate = Calendar.getInstance();
			calStartDate.setTime(date);
			int dayOfStartDate = calStartDate.get(Calendar.DAY_OF_WEEK);

			boolean isWeekend = true;
			for (WorkTime workTime : this.workTimes) {

				if (workTime.getDay() == dayOfStartDate) {

					isWeekend = false;
					break;
				}
			}

			this.isHolidayType0 = isWeekend && this.workTimes.size() > 0;
		}

		// neu la ngay nghi thi next ngay tiep theo
		if (this.isHolidayType0 && countNextDay < 360) {

			_log.info(
				"ngay nghi============" +
					SupportUtils._dateToString(date, DATE_FORMAT));

			if (SupportUtils._dateToString(this.startDate, DATE_FORMAT).equals(
				truncDateStr)) {
				this.startDateTimeStr = DEFAULT_START_AM_STR;
				this.startDateTimeNum = SupportUtils._stringToNumberHourColon(
					this.startDateTimeStr);
				_log.info(
					"reset gio bat dau xu ly ho so=====" + startDateTimeStr);
			}
			// warning: recusive to find a next work day
			this._setNextWorkDay();

		}
		else if (this.durationUnit == 0 &&
			this.countNextWorkDay < this.durationCount && countNextDay < 360) {

			// tinh thoi gian tra theo ngay
			this.countNextWorkDay += 1;
			this._setNextWorkDay();

		}
		else if (this.durationUnit == 1 &&
			this.countNextWorkDayTime < this.durationCount &&
			countNextDay < 360) {

			// tinh thoi gian tra theo gio
			int durationHours = SupportUtils.subTime(
				this.durationCount, this.countNextWorkDayTime);
			if (durationHours < this.tongThoiGianCanBoLamViecTrongNgay) {

				// tra ho so trong ngay

				int gioTra =
					(durationHours + this.startDateTimeNum > this.endAM) &&
						this.startDateTimeNum < this.endAM &&
						this.startDateTimeNum < this.startPM
							? durationHours + this.startDateTimeNum +
								this.thoiGianCanBoNghiTrua
							: durationHours + this.startDateTimeNum;

				if (gioTra > this.gioCanBoNghiLamViec) {

					// gio tra qua gio lam viec

					this.countNextWorkDayTime +=
						SupportUtils.subTime(gioTra, this.gioCanBoNghiLamViec);
					this.startDateTimeStr = DEFAULT_START_AM_STR;
					this.startDateTimeNum =
						SupportUtils._stringToNumberHourColon(
							this.startDateTimeStr);
					_log.info(
						"=======tra ngay hom sau========" +
							SupportUtils._dateToString(date, DATE_FORMAT) +
							"========Da lam========" +
							this.countNextWorkDayTime);
					this._setNextWorkDay();
				}
				else {

					_log.info(
						"=======tra ho so trong ngay========gioTra=" + gioTra +
							"====gioNghiLam=" + this.gioCanBoNghiLamViec +
							"==this.startDateTimeNum==" +
							this.startDateTimeNum);
					this.gioLamViecLe = durationHours;
					this._setDueDate();
				}
			}
			else {

				// tra ngay hom sau
				if (this.countNextWorkDayTime == 0 &&
					this.startDateTimeNum > this.startAM &&
					this.startDateTimeNum < this.endAM) {

					// buoi sang

					this.countNextWorkDayTime += SupportUtils.subTime(
						this.endAM, this.startDateTimeNum) +
						SupportUtils.subTime(this.endPM, this.startPM);

					this.startDateTimeStr = DEFAULT_START_AM_STR;
					this.startDateTimeNum =
						SupportUtils._stringToNumberHourColon(
							this.startDateTimeStr);
					_log.info(
						"reset gio bat dau xu ly ho so=====" +
							startDateTimeStr);

				}
				else if (this.countNextWorkDayTime == 0 &&
					this.startDateTimeNum > this.startPM &&
					this.startDateTimeNum < this.endPM) {

					// buoi chieu

					this.countNextWorkDayTime +=
						SupportUtils.subTime(this.endPM, this.startDateTimeNum);

					this.startDateTimeStr = DEFAULT_START_AM_STR;
					this.startDateTimeNum =
						SupportUtils._stringToNumberHourColon(
							this.startDateTimeStr);
					_log.info(
						"reset gio bat dau xu ly ho so=====" +
							startDateTimeStr);

				}
				else {

					this.countNextWorkDayTime +=
						this.tongThoiGianCanBoLamViecTrongNgay;
				}
				_log.info(
					"=======tra ngay hom sau========" +
						SupportUtils._dateToString(date, DATE_FORMAT) +
						"========Da lam========" + this.countNextWorkDayTime);
				this._setNextWorkDay();
			}
		}
		else {

			this._setDueDate();
		}
	}

	private void _setWorkTimes() {

		this.workTimes = WorkTimeLocalServiceUtil.getByGroupId(this.groupId);
	}

	private void _setHolidays(Date startDate) {

		this.holidays =
			HolidayLocalServiceUtil.getHolidayGtThan(this.groupId, startDate);
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
		this._setHourTimeWorking(this.nextWorkDay);
		this._findAWorkingDayNSetDueDate(this.nextWorkDay);

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
		this.startAM = SupportUtils._stringToNumberHourColon(startAM);
		this.endAM = SupportUtils._stringToNumberHourColon(endAM);
		this.startPM = SupportUtils._stringToNumberHourColon(startPM);
		this.endPM = SupportUtils._stringToNumberHourColon(endPM);
		this.tongThoiGianCanBoLamViecTrongNgay =
			SupportUtils.subTime(this.endAM, this.startAM) +
				SupportUtils.subTime(this.endPM, this.startPM);
		this.gioCanBoNghiLamViec = Math.max(this.endAM, this.endPM);
		this.thoiGianCanBoNghiTrua =
			Math.max(0, SupportUtils.subTime(this.startPM, this.endAM));

	}

	public Date getDueDate() {

		return dueDate;
	}

	public double getDurationCount() {

		return durationCount;
	}

	public int getDurationUnit() {

		return durationUnit;
	}

}

class SupportUtils {

	public static String _dateToString(Date date, String format) {

		return new SimpleDateFormat(format).format(date);
	}

	public static Date _stringToDate(String dateStr, String format) {

		try {

			return new SimpleDateFormat(format).parse(dateStr);
		}
		catch (ParseException e) {

			return null;
		}
	}

	public static int _stringToNumberHourColon(String hour) {

		// format: HH:mm -> HHmm
		String[] h = hour.split(StringPool.COLON);
		return Integer.parseInt(h[0] + h[1]);
	}

	public static String _numberToStringHourColon(int hourNumber) {

		// format: HHmm -> HH:mm
		int mm = hourNumber % 100;
		String hh = String.valueOf((hourNumber - mm) / 100);
		return padLeft(hh, 2, "0") + StringPool.COLON + mm;
	}

	public static String padRight(String s, int n, String c) {

		return String.format("%-" + n + "s", s).replaceAll(StringPool.SPACE, c);
	}

	public static String padLeft(String s, int n, String c) {

		return String.format("%" + n + "s", s).replaceAll(StringPool.SPACE, c);
	}

	// doi gio dang hh:mm sang so phut
	public static int convertToMinutes(int hourNumber) {

		int mm = hourNumber % 100;
		int hh = (hourNumber - mm) / 100;

		return hh * 60 + mm;
	}

	// tru gio HHmm - HHmm
	public static int subTime(int time, int time2) {

		int diff = convertToMinutes(time) - convertToMinutes(time2);
		int hours = diff / 60;
		int minutes = diff % 60;
		return hours * 100 + minutes;
	}
}
