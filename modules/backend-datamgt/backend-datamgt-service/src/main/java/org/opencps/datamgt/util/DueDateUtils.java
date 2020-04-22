
package org.opencps.datamgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.HolidayLocalServiceUtil;
import org.opencps.datamgt.service.WorkTimeLocalServiceUtil;

public class DueDateUtils {

	private static Log _log = LogFactoryUtil.getLog(DueDateUtils.class);

	public static final String HOUR_COLON_TIME_FORMAT = "HH:mm";
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_SPACE_TIME_FORMAT =
		DATE_FORMAT + StringPool.SPACE + HOUR_COLON_TIME_FORMAT;
	public static final int DAY_TO_MILISECOND = 1 * 24 * 60 * 60 * 1000;
	public static final int HOUR_TO_MILISECOND = 1 * 60 * 60 * 1000;
	public static final String DEFAULT_START_AM_STR = "00:00";
	public static final int DAY_TO_HOURS = 8;

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

	private Date ngayTiepNhan;
	private Date toDate;

	public DueDateUtils(
		Date startDate, Double durationCountD, int durationUnit, long groupId) {

		super();

		this.durationUnit = durationUnit;
		this.groupId = groupId;
		this.countNextWorkDay = 0;

		int durationCountInt = durationCountD.intValue();

		if (durationCountInt != durationCountD && durationUnit == 0) {

			// convert to hours
			Double hours = (durationCountD * DAY_TO_HOURS);
			// lam tron den so thap phan t2
			hours = (double) Math.round(hours * 100) / 100;
			this.durationUnit = 1;
			this.durationCount = SupportUtils.reverseToMinutes(hours);

		}
		else if (durationUnit == 0) {

			// because 1d = 8h :((
			this.durationUnit = 1;// durationUnit;
			this.durationCount = durationCountInt * DAY_TO_HOURS * 100;// durationCountInt;
		}
		else {

			this.durationUnit = durationUnit;
			this.durationCount = SupportUtils.reverseToMinutes(durationCountD);
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

	public DueDateUtils(
		Date startDate, Date toDate, int durationUnit, long groupId) {

		super();

		this.groupId = groupId;
		this.durationUnit = durationUnit;

		if (Validator.isNotNull(startDate) && Validator.isNotNull(toDate)) {

			try {

				// 1st set workTimes & set holidays
				this._setWorkTimes();
				this._setHolidays(startDate);

				// set start date
				this.setStartDate(startDate);

				this.toDate = toDate;

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
//		else if (this.startDateTimeNum > this.endPM) {
//
//			// set to startAM
//			Calendar calStartDate = Calendar.getInstance();
//			calStartDate.setTime(startDate);
//			calStartDate.add(Calendar.DATE, 1);
//			startDateDateStr =
//				SupportUtils._dateToString(calStartDate.getTime(), DATE_FORMAT);
//
//			this.startDate = SupportUtils._stringToDate(
//				startDateDateStr + StringPool.SPACE + DEFAULT_START_AM_STR,
//				DATE_SPACE_TIME_FORMAT);
//		}
		else {

			this.startDate = startDate;
			_log.debug("other case");
		}

		this.startDateTimeStr =
			SupportUtils._dateToString(this.startDate, HOUR_COLON_TIME_FORMAT);
		this.startDateTimeNum =
			SupportUtils._stringToNumberHourColon(this.startDateTimeStr);

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
		this.startDateTimeNum = (SupportUtils.addTime(
			this.gioLamViecLe, this.startDateTimeNum) > this.endAM) &&
			this.startDateTimeNum < this.endAM &&
			this.startDateTimeNum < this.startPM
				? SupportUtils.addTime(
					this.gioLamViecLe, this.startDateTimeNum,
					this.thoiGianCanBoNghiTrua)
				: SupportUtils.addTime(
					this.gioLamViecLe, this.startDateTimeNum);

		this.startDateTimeStr =
			SupportUtils._numberToStringHourColon(this.startDateTimeNum);

		_log.debug(
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
		else if (this.startDateTimeNum > this.endPM) {

			// set to startAM
			this.startDateTimeStr = DEFAULT_START_AM_STR;
			this.startDateTimeNum =
				SupportUtils._stringToNumberHourColon(this.startDateTimeStr);
			// case 3
			this._setNextWorkDay();
		}
		else {

			_log.debug("other case");
		}
	}

	private void _calDueDate(Date date, String truncDateStr) {

		// neu la ngay nghi thi next ngay tiep theo
		if (this.isHolidayType0 && countNextDay < 360) {

			_log.debug(
				"ngay nghi============" +
					SupportUtils._dateToString(date, DATE_FORMAT));

			if (SupportUtils._dateToString(this.startDate, DATE_FORMAT).equals(
				truncDateStr)) {
				this.startDateTimeStr = DEFAULT_START_AM_STR;
				this.startDateTimeNum = SupportUtils._stringToNumberHourColon(
					this.startDateTimeStr);
				_log.debug(
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

				int gioTra = (SupportUtils.addTime(
					durationHours, this.startDateTimeNum) > this.endAM) &&
					this.startDateTimeNum < this.endAM &&
					this.startDateTimeNum < this.startPM
						? SupportUtils.addTime(
							durationHours + this.startDateTimeNum +
								this.thoiGianCanBoNghiTrua)
						: SupportUtils.addTime(
							durationHours + this.startDateTimeNum);

				if (gioTra > this.gioCanBoNghiLamViec) {

					// gio tra qua gio lam viec

					// whatttttttt this.countNextWorkDayTime +=
					// SupportUtils.subTime(gioTra,
					// this.gioCanBoNghiLamViec);
					this.countNextWorkDayTime = SupportUtils.addTime(
						this.countNextWorkDayTime,
						SupportUtils.subTime(
							durationHours, SupportUtils.subTime(
								gioTra, this.gioCanBoNghiLamViec)));

					this.startDateTimeStr = DEFAULT_START_AM_STR;
					this.startDateTimeNum =
						SupportUtils._stringToNumberHourColon(
							this.startDateTimeStr);
					_log.debug(
						gioTra + "=======tra ngay hom sau========" +
							SupportUtils._dateToString(date, DATE_FORMAT) +
							"========Da lam========" +
							this.countNextWorkDayTime);
					this._setNextWorkDay();
				}
				else {

					_log.debug(
						"=======tra ho so trong ngay========gioTra=" + gioTra +
							"====gioNghiLam=" + this.gioCanBoNghiLamViec +
							"==this.startDateTimeNum==" +
							this.startDateTimeNum);
					this.gioLamViecLe = durationHours;
					this.countNextWorkDayTime = SupportUtils.addTime(
						this.countNextWorkDayTime, durationHours);
					this._setDueDate();
				}
			}
			else {

				// tra ngay hom sau
				if (this.countNextWorkDayTime == 0 &&
					this.startDateTimeNum > this.startAM &&
					this.startDateTimeNum < this.endAM) {

					// buoi sang

					this.countNextWorkDayTime = SupportUtils.addTime(
						this.countNextWorkDayTime,
						SupportUtils.subTime(
							this.endAM, this.startDateTimeNum) +
							SupportUtils.subTime(this.endPM, this.startPM));

					this.startDateTimeStr = DEFAULT_START_AM_STR;
					this.startDateTimeNum =
						SupportUtils._stringToNumberHourColon(
							this.startDateTimeStr);
					_log.debug(
						"reset gio bat dau xu ly ho so=====" +
							startDateTimeStr);

				}
				else if (this.countNextWorkDayTime == 0 &&
					this.startDateTimeNum > this.startPM &&
					this.startDateTimeNum < this.endPM) {

					// buoi chieu

					this.countNextWorkDayTime = SupportUtils.addTime(
						this.countNextWorkDayTime, SupportUtils.subTime(
							this.endPM, this.startDateTimeNum));

					this.startDateTimeStr = DEFAULT_START_AM_STR;
					this.startDateTimeNum =
						SupportUtils._stringToNumberHourColon(
							this.startDateTimeStr);
					_log.debug(
						"reset gio bat dau xu ly ho so=====" +
							startDateTimeStr);

				}
				else {

					this.countNextWorkDayTime = SupportUtils.addTime(
						this.countNextWorkDayTime,
						this.tongThoiGianCanBoLamViecTrongNgay);
				}
				_log.debug(
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

	private void _calDuration(Date date, String truncDateStr) {

		// neu la ngay nghi thi next ngay tiep theo
		if (this.isHolidayType0 && date.getTime() < this.toDate.getTime()) {

			_log.debug(
				"ngay nghi============" +
					SupportUtils._dateToString(date, DATE_FORMAT));

			if (SupportUtils._dateToString(this.startDate, DATE_FORMAT).equals(
				truncDateStr)) {
				this.startDateTimeStr = DEFAULT_START_AM_STR;
				this.startDateTimeNum = SupportUtils._stringToNumberHourColon(
					this.startDateTimeStr);
				_log.debug(
					"reset gio bat dau xu ly ho so=====" + startDateTimeStr);
			}
			// warning: recusive to find a next work day
			this._setNextWorkDay();

		}
		else if (truncDateStr.equals(
			SupportUtils._dateToString(this.toDate, DATE_FORMAT))) {

			String time =
				SupportUtils._dateToString(this.toDate, HOUR_COLON_TIME_FORMAT);
			// stop here
			if (!DEFAULT_START_AM_STR.equals(this.startDateTimeStr) &&
				this.countNextWorkDayTime == 0) {

				// case tinh trong ngay
				this.countNextWorkDayTime =
					SupportUtils.calcStartDateTimeToEndDateTime(
						startAM, endAM, startPM, endPM, this.startDateTimeNum,
						SupportUtils._stringToNumberHourColon(time));
			}
			else {

				this.countNextWorkDayTime = SupportUtils.addTime(
					this.countNextWorkDayTime,
					SupportUtils.calcStartAMToStartDateTime(
						startAM, endAM, startPM, endPM,
						SupportUtils._stringToNumberHourColon(time)));
			}
		}
		else if (date.getTime() < this.toDate.getTime() &&
			this.countNextDay < 360) {

			if (DEFAULT_START_AM_STR.equals(this.startDateTimeStr)) {

				// lam full ngay
				this.startDateTimeStr = this.startAMStr;
				this.startDateTimeNum = SupportUtils._stringToNumberHourColon(
					this.startDateTimeStr);
				this.countNextWorkDayTime = SupportUtils.addTime(
					this.countNextWorkDayTime,
					this.tongThoiGianCanBoLamViecTrongNgay);
			}
			else {

				this.countNextWorkDayTime =
					SupportUtils.addTime(
						this.countNextWorkDayTime,
						SupportUtils.calcStartDateTimeToEndPM(
							startAM, endAM, startPM, endPM,
							this.startDateTimeNum));
				this.startDateTimeStr = DEFAULT_START_AM_STR;
				this.startDateTimeNum = SupportUtils._stringToNumberHourColon(
					this.startDateTimeStr);
			}

			this.countNextWorkDay += 1;
			_setNextWorkDay();
		}
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

		if (holiday != null) {

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

		// set ngay tiep nhan
		if (!this.isHolidayType0 && Validator.isNull(this.ngayTiepNhan)) {

			if (DEFAULT_START_AM_STR.equals(this.startDateTimeStr)) {

				this.ngayTiepNhan = SupportUtils._stringToDate(
					truncDateStr + StringPool.SPACE + this.startAMStr,
					DATE_SPACE_TIME_FORMAT);
			}
			else {

				this.ngayTiepNhan = SupportUtils._stringToDate(
					truncDateStr + StringPool.SPACE + this.startDateTimeStr,
					DATE_SPACE_TIME_FORMAT);
			}

			_log.debug(
				"ngay tiep nhan ho so=====" + SupportUtils._dateToString(
					this.ngayTiepNhan, DATE_SPACE_TIME_FORMAT));
		}

		if (Validator.isNull(this.toDate)) {

			_calDueDate(date, truncDateStr);
		}
		else {

			_calDuration(date, truncDateStr);
		}
	}

	private void _setWorkTimes() {

		this.workTimes = WorkTimeLocalServiceUtil.getByGroupId(this.groupId);
		_log.debug("==================workTimes=========" + this.workTimes);
	}

	private void _setHolidays(Date startDate) {

		this.holidays =
			HolidayLocalServiceUtil.getHolidayGtThan(this.groupId, startDate);
		_log.debug("==================holidays=========" + this.holidays);
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

		String startAM = "07:30";
		String endAM = "11:30";
		String startPM = "13:00";
		String endPM = "17:00";

		try {

			int checkDay = day % Calendar.DAY_OF_WEEK;

			for (WorkTime workTime : this.workTimes) {

				if (workTime.getDay() == Calendar.MONDAY ||
					workTime.getDay() == checkDay) {

					// workTime.hours = 07:30-11:30,13:00-17:00
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
		this.tongThoiGianCanBoLamViecTrongNgay =
			this.tongThoiGianCanBoLamViecTrongNgay == 0
				? 2400 : this.tongThoiGianCanBoLamViecTrongNgay;
		this.gioCanBoNghiLamViec = Math.max(this.endAM, this.endPM);
		this.thoiGianCanBoNghiTrua =
			Math.max(0, SupportUtils.subTime(this.startPM, this.endAM));

	}

	public Date getDueDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(dueDate);
		// minute / 15 == 0
		int minute = cal.get(Calendar.MINUTE) == 0
			? 0
			: cal.get(Calendar.MINUTE) <= 15
				? 15 : cal.get(Calendar.MINUTE) <= 30
					? 30 : cal.get(Calendar.MINUTE) <= 45 ? 45 : 0;
		if (minute == 0 && cal.get(Calendar.MINUTE) > 0) {
			cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
		}
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();
	}

	public Date getDueDateOriginal() {

		return dueDate;
	}

	public Date getRecivedDate() {

		return ngayTiepNhan;
	}

	public double getDurationCount() {

		return durationCount;
	}

	public int getDurationUnit() {

		return durationUnit;
	}

	public long getOverDue() {

		_log.debug(countNextWorkDayTime);
		if (durationUnit == 0) {
			return countNextWorkDay;
		}
		else {
			return SupportUtils.convertToMiliseconds(countNextWorkDayTime);
		}
	}

	public double getOverDueCalcToDate() {

		double ms = (double) getOverDue();
		double result = ms / (DAY_TO_HOURS * 60 * 60 * 1000);
		// lam tron den so thap phan thu 2
		return (double) Math.round(result * 100) / 100;
	}

	public double getOverDueCalcToHours() {

		double ms = (double) getOverDue();
		double result = ms / (DAY_TO_HOURS * 60 * 1000);
		// lam tron den so thap phan thu 2
		return (double) Math.round(result * 100) / 100;
	}

	public String getOverDueCalcToString() {
		long seconds = getOverDue() / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 8;
		hours = (hours - days * 8) % 24;
		minutes = minutes % 60;
//		seconds = seconds % 60;
		String time = days > 0 ? days + " ngày " : StringPool.BLANK;
		time += hours > 0 ? hours + " giờ " : StringPool.BLANK;
		time += minutes > 0 ? minutes + " phút " : StringPool.BLANK;
		return StringPool.BLANK.equals(time) ? " 0 ngày " : time;
	}

	public long getOverDueTypeDay() {

		return countNextWorkDay;
	}

	public long getOverDueTypeHour() {

		return countNextWorkDayTime / 100;
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
		return padLeft(hh, 2, "0") + StringPool.COLON +
			padLeft(String.valueOf(mm), 2, "0");
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

	// doi gio dang so phut 12.5 sang hhmm 1230
	public static int reverseToMinutes(Double hourNumber) {

		Double duD = hourNumber % 1.0 * 60.0;
		return hourNumber.intValue() * 100 + duD.intValue();
	}

	// tinh 3h80p -> ms
	public static long convertToMiliseconds(int hourNumber) {

		long mm = hourNumber % 100;
		long hh = (hourNumber - mm) / 100;
		long mmToMs = mm * 60 * 60 * 1000 / 60;
		return hh * 60 * 60 * 1000 + mmToMs;
	}

	// tru gio HHmm - HHmm
	public static int subTime(int time, int time2) {

		int diff = convertToMinutes(time) - convertToMinutes(time2);
		int hours = diff / 60;
		int minutes = diff % 60;
		return hours * 100 + minutes;
	}

	// c gio HHmm - HHmm
	public static int addTime(int... times) {

		int diff = 0;
		for (int time : times) {
			diff += convertToMinutes(time);
		}
		int hours = diff / 60;
		int minutes = diff % 60;
		return hours * 100 + minutes;
	}

	public static int calcStartAMToStartDateTime(
		int startAM, int endAM, int startPM, int endPM, int startDateTime) {

		int duration = 0;
		if (startDateTime <= startAM) {

			duration = 0;
		}
		else if (startAM < startDateTime && startDateTime <= endAM) {

			duration = subTime(startDateTime, startAM);
		}
		else if (endAM < startDateTime && startDateTime < startPM) {

			duration = subTime(endAM, startAM);
		}
		else if (startPM <= startDateTime && startDateTime < endPM) {

			duration =
				subTime(endAM, startAM) + subTime(startDateTime, startPM);
		}
		else {

			duration = startDateTime;
		}
		return duration;
	}

	public static int calcStartDateTimeToEndPM(
		int startAM, int endAM, int startPM, int endPM, int startDateTime) {

		int duration = 0;
		if (startAM <= startDateTime && startDateTime <= endAM) {

			duration = subTime(endAM, startDateTime) + subTime(endPM, startPM);
		}
		else if (endAM < startDateTime && startDateTime < startPM) {

			duration = subTime(endPM, startPM);
		}
		else if (startPM <= startDateTime && startDateTime < endPM) {

			duration = subTime(endPM, startDateTime);
		}
		else if (startDateTime > endPM) {

			duration = subTime(endAM, startAM) + subTime(endPM, startPM);
		}
		return duration;
	}

	public static int calcStartDateTimeToEndDateTime(
		int startAM, int endAM, int startPM, int endPM, int startDateTime,
		int endDateTime) {

		if (startDateTime < startAM) {
			startDateTime = startAM;
		}
		else if (startDateTime > endAM && startDateTime < startPM) {
			startDateTime = startPM;
		}
		else if (startDateTime > endAM && startDateTime > endPM) {
			startDateTime = endPM;
		}

		if (endDateTime < startAM) {
			endDateTime = startAM;
		}
		else if (endDateTime > endAM && endDateTime < startPM) {
			endDateTime = startPM;
		}
		else if (endDateTime > endAM && endDateTime > endPM) {
			endDateTime = endPM;
		}

		int duration = 0;
		if (endDateTime >= endAM && endDateTime <= startPM &&
			startDateTime < endAM) {

			duration = subTime(endAM, startDateTime);
		}
		else if (endDateTime >= startPM && startDateTime >= endAM &&
			startDateTime <= startPM) {

			duration = subTime(endDateTime, startPM);
		}
		else if (endDateTime <= endAM && startDateTime <= endAM) {

			duration = subTime(endDateTime, startDateTime);
		}
		else if (endDateTime >= startPM && startDateTime >= startPM) {

			duration = subTime(endDateTime, startDateTime);
		}
		else if (endDateTime >= startPM && startDateTime < endAM) {

			duration = addTime(
				subTime(endAM, startDateTime), subTime(endDateTime, startPM));
		}
		return duration;
	}
}
