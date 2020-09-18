package org.opencps.datamgt.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Calendar;
import java.util.Date;

public class DueDatePhaseUtil {

	public static String ALL_DAY = "allDay";
	public static String WORK_DAY = "workDay";
	public static String DAY_OF_WEEK = "dayOfWeek";
	public static String DAY_OF_MONTH = "dayOfMonth";
	public static String MONTH = "month";
	public static String DUEDATE_PHASE = "dueDatePhase";
	public static String IS_WORK_DAY = "isWorkDay";

	int dateOption;
	long groupId;
	private Date startDate;
	private Date receiveDate;
	private double duration;
	private Date dueDate;
	private String dueDatePhases;

	public double getDuration() {
		return duration;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public Date getDueDate() {
		return dueDate;
	}

	public DueDatePhaseUtil(long groupId, Date startDate, int dateOption, String dueDatePhases) {
		super();
		this.groupId = groupId;
		this.startDate = startDate;
		this.dateOption = dateOption;
		this.dueDatePhases = dueDatePhases;
		init();
	}

	private void init() {

		JSONObject phase = JSONFactoryUtil.createJSONObject();
		try {

			JSONArray dueDatePhases = JSONFactoryUtil.createJSONObject(this.dueDatePhases).getJSONArray(DUEDATE_PHASE);

			switch (this.dateOption) {
			case 11:
				phase = dueDatePhases.getJSONObject(0);
				break;
			case 12:
				phase = dueDatePhases.getJSONObject(1);
				break;
			case 13:
				phase = dueDatePhases.getJSONObject(2);
				break;

			default:
				break;
			}

			if (phase.has(ALL_DAY) && phase.has(WORK_DAY) && !phase.has(IS_WORK_DAY)) {

				setByAllDay(phase);
			} else if (phase.has(ALL_DAY) && phase.has(WORK_DAY) && phase.has(IS_WORK_DAY)) {
				
				setByWorkDay(phase);
			} else if (phase.has(DAY_OF_WEEK)) {

				setByDayOfWeek(phase);
			} else if (phase.has(DAY_OF_MONTH) && phase.has(MONTH)) {

				setByDayOfMonth(phase);
			}

		} catch (Exception e) {
			_log.error(e);
			// DuanBA: if BA not config
			setByAllDay(phase);
		}

	}

	private void setByAllDay(JSONObject phase) {

		int allDay = phase.getInt(ALL_DAY, 0);
		double workDay = phase.getDouble(WORK_DAY, 0D);			
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.setTime(this.startDate);
		calStartDate.add(Calendar.DATE, allDay);
		DueDateUtils dueDateUtil = new DueDateUtils(calStartDate.getTime(), workDay, 0, this.groupId);
		this.dueDate = dueDateUtil.getDueDate();
		this.receiveDate = this.startDate;
		this.duration = allDay + workDay;

	}
	
	private void setByWorkDay(JSONObject phase) {

		int allDay = phase.getInt(ALL_DAY, 0);
		double workDay = phase.getDouble(WORK_DAY, 0D);			
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.setTime(this.startDate);
		calStartDate.add(Calendar.DATE, allDay);
		DueDateUtils dueDateUtil = new DueDateUtils(calStartDate.getTime(), workDay, 0, this.groupId);
		this.dueDate = dueDateUtil.getDueDate();
		this.receiveDate = this.startDate;
		this.duration = allDay + workDay;

	}

	private void setByDayOfWeek(JSONObject phase) {

		// TODO: cal day of week
	}

	private void setByDayOfMonth(JSONObject phase) {

		// TODO: cal day of Month
	}

	private static Log _log = LogFactoryUtil.getLog(DueDatePhaseUtil.class);
}