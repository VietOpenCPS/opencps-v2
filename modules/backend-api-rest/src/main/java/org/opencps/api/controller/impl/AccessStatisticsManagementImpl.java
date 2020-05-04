package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.accessstatistics.model.AccessStatistics;
import org.opencps.api.controller.AccessStatisticsManagement;
import org.opencps.usermgt.action.AccessStatisticsActions;
import org.opencps.usermgt.action.impl.AccessStatisticsActionsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Locale;

public class AccessStatisticsManagementImpl implements AccessStatisticsManagement
{

	@Override
	public Response getAccessStatistics(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,
		User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		long value =actions.getAccessStatistics(accessStatistics.getDay(),accessStatistics.getMonth(),accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("value" , value);
		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject).build();
	}

	@Override
	public Response getAccessStatisticsForDay(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,
		User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		long value =actions.getAccessStatisticsForDay(accessStatistics.getDay(),accessStatistics.getMonth(),accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("value" , value);
		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject).build();
	}

	@Override public Response getAccessStatisticsForPeriod(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,String startDay,String endDay)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		long value =actions.getAccessStatisticsForPeriod(startDay,endDay);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("value" , value);
		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject).build();
	}

	@Override public Response getAccessStatisticsForMonth(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		long value =actions.getAccessStatisticsForMonth(accessStatistics.getMonth(),accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("value" , value);
		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject).build();
	}

	@Override public Response getAccessStatisticsForYear(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		long value =actions.getAccessStatisticsForYear(accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("value" , value);
		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject).build();
	}

	@Override public Response getAccessStatisticsForAllYear(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		long value =actions.getAccessStatisticsForAllYear();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("value" , value);
		return Response.status(HttpURLConnection.HTTP_OK).entity(jsonObject).build();
	}
}
