package org.opencps.datamgt.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class DueDatePharseUtil {

	public static String ALL_DAY = "allDay";
	public static String WORK_DAY = "workDay";
	public static String DAY_OF_WEEK = "dayOfWeek";
	public static String DAY_OF_MONTH = "dayOfMonth";
	public static String MONTH = "month";

	int dateOption;
	long groupId;
	private Date startDate;
	private Date dueDate;
	private String dueDatePharses;

	public Date getDueDate() {
		return dueDate;
	}

	public DueDatePharseUtil(long groupId, Date startDate, int dateOption, String dueDatePharses) {
		super();
		this.groupId = groupId;
		this.startDate = startDate;
		this.dateOption = dateOption;
		this.dueDatePharses = dueDatePharses;
		init();
	}

	private void init() {

		try {

			JSONArray dueDatePharses = JSONFactoryUtil.createJSONArray(this.dueDatePharses);
			JSONObject pharse = JSONFactoryUtil.createJSONObject();
			switch (this.dateOption) {
			case 11:
				pharse = dueDatePharses.getJSONObject(0);
				break;
			case 12:
				pharse = dueDatePharses.getJSONObject(1);
				break;
			case 13:
				pharse = dueDatePharses.getJSONObject(2);
				break;

			default:
				break;
			}

			if (pharse.has(ALL_DAY) && pharse.has(WORK_DAY)) {

				setByAllDay(pharse);
			} else if (pharse.has(DAY_OF_WEEK)) {

				setByDayOfWeek(pharse);
			} else if (pharse.has(DAY_OF_MONTH) && pharse.has(MONTH)) {

				setByDayOfMonth(pharse);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void setByAllDay(JSONObject pharse) {

		int allDay = pharse.getInt(ALL_DAY, 0);
		double workDay = pharse.getDouble(WORK_DAY, 0D);
		Calendar calStartDate = Calendar.getInstance();

		calStartDate.setTime(this.startDate);
		calStartDate.add(Calendar.DATE, allDay);
		DueDateUtils dueDateUtil = new DueDateUtils(calStartDate.getTime(), workDay, 0, this.groupId);
		this.dueDate = dueDateUtil.getDueDate();
	}

	private void setByDayOfWeek(JSONObject pharse) {

		// TODO: cal day of week
	}

	private void setByDayOfMonth(JSONObject pharse) {

		// TODO: cal day of Month
	}
}