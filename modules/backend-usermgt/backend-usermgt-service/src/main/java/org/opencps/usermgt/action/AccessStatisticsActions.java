package org.opencps.usermgt.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Locale;

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
