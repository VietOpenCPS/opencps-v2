package org.opencps.usermgt.action;

import com.liferay.portal.kernel.json.JSONObject;

public interface AccessStatisticsActions
{

	long getAccessStatistics(int day,int month,int yaer);

	long getAccessStatisticsForDay(int day,int month,int yaer);

	long getAccessStatisticsForPeriod(String startDay,String endDay);

	long getAccessStatisticsForMonth(int month,int yaer);

	long getAccessStatisticsForYear(int yaer);

	long getAccessStatisticsForAllYear();

	JSONObject getAccessStatisticsURL(int day,int month,int yaer);

	JSONObject getAccessStatisticsURLForAllYear();

	JSONObject getAccessStatisticsURLForPeriod();
}
