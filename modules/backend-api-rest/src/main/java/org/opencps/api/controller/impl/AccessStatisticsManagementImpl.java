package org.opencps.api.controller.impl;

import backend.auth.api.exception.BusinessExceptionImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.accessstatistics.model.AccessStatistics;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.AccessStatisticsManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.action.AccessStatisticsActions;
import org.opencps.usermgt.action.impl.AccessStatisticsActionsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.Map;

public class AccessStatisticsManagementImpl implements AccessStatisticsManagement
{
	private static Log _log = LogFactoryUtil.getLog(AccessStatisticsManagementImpl.class);

	@Override
	public Response getAccessStatistics(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,
		User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		Map<String,Long>  result=actions.getAccessStatistics(accessStatistics.getDay(),accessStatistics.getMonth(),accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("accessStatistics" , result);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override
	public Response getAccessStatisticsForDay(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,
		User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		Map<String,Long>  result=actions.getAccessStatisticsForDay(accessStatistics.getDay(),accessStatistics.getMonth(),accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("accessStatistics" , result);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override public Response getAccessStatisticsForPeriod(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,String startDay,String endDay)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		Map<String,Long> result=actions.getAccessStatisticsForPeriod(startDay,endDay);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("accessStatistics" , result);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override public Response getAccessStatisticsForMonth(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		Map<String,Long>  result=actions.getAccessStatisticsForMonth(accessStatistics.getMonth(),accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("accessStatistics" , result);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override public Response getAccessStatisticsForYear(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		Map<String,Long>  result=actions.getAccessStatisticsForYear(accessStatistics.getYear());
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("accessStatistics" , result);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override public Response getAccessStatisticsForAllYear(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		Long  result=actions.getAccessStatisticsForAllYear();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		jsonObject.put("result" , result);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override public Response getAccessStatisticsURLForDay(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		int day = accessStatistics.getDay();
		int month =accessStatistics.getMonth();
		int year = accessStatistics.getYear();
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		JSONObject jsonObject = actions.getAccessStatisticsURL(day,month,year);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override
	public Response getAccessStatisticsURLForPeriod(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,String startDay,String endDay)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		JSONObject jsonObject = actions.getAccessStatisticsURLForPeriod();
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override
	public Response getAccessStatisticsURLForMonth(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		int day = 0;
		int month =accessStatistics.getMonth();
		int year = accessStatistics.getYear();
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		JSONObject jsonObject = actions.getAccessStatisticsURL(day,month,year);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override public Response getAccessStatisticsURLForYear(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		int day = 0;
		int month =0;
		int year = accessStatistics.getYear();
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		JSONObject jsonObject = actions.getAccessStatisticsURL(day,month,year);
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override
	public Response getAccessStatisticsURLForAllYear(HttpServletRequest request,HttpHeaders header,Company company,
		Locale locale,User user,ServiceContext serviceContext,AccessStatistics accessStatistics)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		JSONObject jsonObject = actions.getAccessStatisticsURLForAllYear();
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override
	public Response getOnline(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,User user,
		ServiceContext serviceContext)
	{
		AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
		JSONObject jsonObject = actions.getOnline();
		return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
	}

	@Override
	public Response getUserAccessStatistics(HttpServletRequest request,HttpHeaders header,Company company,Locale locale,
		User user,ServiceContext serviceContext)
	{
		BackendAuth auth = new BackendAuthImpl();
		backend.auth.api.BackendAuth auth2 = new backend.auth.api.BackendAuthImpl();

		try
		{

			if (!auth.isAuth(serviceContext))
			{
				throw new UnauthenticationException();
			}
			if (!auth2.isAdmin(serviceContext,ConstantUtils.ROLE_ADMIN_LOWER))
			{
				return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(MessageUtil.getMessage(ConstantUtils.API_USER_NOTHAVEPERMISSION)).build();
			}

			long userId = serviceContext.getUserId();
			String sessionId = request.getSession().getId();
			AccessStatisticsActions actions = new AccessStatisticsActionsImpl();
			JSONObject jsonObject = actions.getUserAccessStatistics(userId , sessionId);

			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.serialize(jsonObject.toString())).build();
		}
		catch (Exception e)
		{
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}


	}
}
