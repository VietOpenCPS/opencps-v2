package org.opencps.usermgt.action;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;

public interface AccessStatisticsActions
{

	Map<String,Long> getAccessStatistics(int day,int month,int yaer);

	Map<String,Long> getAccessStatisticsForDay(int day,int month,int yaer);

	Map<String, Long> getAccessStatisticsForPeriod(String startDay,String endDay);

	Map<String,Long> getAccessStatisticsForMonth(int month,int yaer);

	Map<String,Long> getAccessStatisticsForYear(int yaer);

	long getAccessStatisticsForAllYear();

	JSONObject getAccessStatisticsURL(int day,int month,int yaer);

	JSONObject getAccessStatisticsURLForAllYear();

	JSONObject getAccessStatisticsURLForPeriod();

	JSONObject getOnline();

	JSONObject getUserAccessStatistics(long userId,String sessionId );
}
