package org.opencps.usermgt.action.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.usermgt.action.AccessStatisticsActions;
import org.opencps.usermgt.model.TrackClientStatistic;
import org.opencps.usermgt.service.TrackClientLocalServiceUtil;
import org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccessStatisticsActionsImpl implements AccessStatisticsActions
{

	@Override
	public Map<String, Long> getAccessStatistics(int day, int month, int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(day, month, yaer);

	}

	@Override
	public Map<String, Long> getAccessStatisticsForDay(int day, int month, int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(day, month, yaer);
	}

	@Override
	public JSONObject getAccessStatisticsForPeriod(String  startDay, String endDay)
	{
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date stratDate = dateFormat.parse(startDay);
			Date endDate = dateFormat.parse(endDay);


			List<Object[]> list = TrackClientLocalServiceUtil.findPeriodCountDay(startDay, endDay);

			Calendar calendar = Calendar.getInstance();
			for (calendar.setTime(stratDate); calendar.getTime().getTime()<=endDate.getTime(); calendar.add(Calendar.DAY_OF_MONTH,1) )
			{
				JSONObject  jsonObject = JSONFactoryUtil.createJSONObject();
				boolean added = false;

				for (int i = 0; i < list.size(); i++)
				{
					Date date = (Date) list.get(i)[0];
					int count =(int) list.get(i)[1];

					if (date.getTime()==calendar.getTime().getTime())
					{
						jsonObject.put("day" , date.toString());
						jsonObject.put("count", count);
						added = true;
						break;
					}
				}
				if (!added)
				{
					String date = dateFormat.format(calendar.getTime()).toString();
					jsonObject.put("day" , date );
					jsonObject.put("count", 0);
				}
				jsonArray.put(jsonObject);

			}

		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		Map<String,Long> result=TrackClientStatisticLocalServiceUtil.countAccessPeriod(startDay, endDay);
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		JSONArray regionJSONArray = JSONFactoryUtil.createJSONArray();

		List<Object[]> regions = TrackClientLocalServiceUtil.findPeriodRegion(startDay, endDay);

		for (int i = 0; i < regions.size(); i++)
		{
			JSONObject object = JSONFactoryUtil.createJSONObject();
			String region = (String) regions.get(i)[0];
			int count = (int) regions.get(i)[1];
			object.put("region" ,region);
			object.put("count", count);
			regionJSONArray.put(object);
		}
		jsonObject.put("detail",jsonArray);
		jsonObject.put("accessStatistics", result );
		jsonObject.put("region" ,regionJSONArray);
		return jsonObject;
	}

	@Override
	public Map<String, Long> getAccessStatisticsForMonth( int month, int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(0,month, yaer);
	}

	@Override
	public Map<String, Long> getAccessStatisticsForYear( int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(0,0, yaer);
	}

	@Override
	public long getAccessStatisticsForAllYear()
	{
		return TrackClientStatisticLocalServiceUtil.countAccessAllYear();
	}

	@Override
	public JSONObject getAccessStatisticsURL(int day, int month, int yaer)
	{
		List<TrackClientStatistic> trackClientStatistics =TrackClientStatisticLocalServiceUtil.accessStatisticsURL(day, month, yaer);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		for (TrackClientStatistic trackClientStatistic: trackClientStatistics)
		{
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("url" , trackClientStatistic.getUrl());
			jsonObject.put("value", trackClientStatistic.getTotal());
			jsonArray.put(jsonObject);
		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("accessStatisticsURL", jsonArray);
		return result;
	}

	public JSONObject getAccessStatisticsURLForAllYear()
	{
		return  TrackClientStatisticLocalServiceUtil.accessStatisticsURLForAllYear();
	}
	public JSONObject getAccessStatisticsURLForPeriod()
	{
		return  TrackClientStatisticLocalServiceUtil.accessStatisticsURLForAllYear();
	}


	public JSONObject getOnline()
	{
		List<Object[]> list = (List<Object[]>) TrackClientLocalServiceUtil.getOnline();
		if (list.size()==1)
		{
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				String user =list.get(0)[0].toString();
				String guest = list.get(0)[1].toString();

				jsonObject.put("user" ,user );
				jsonObject.put("guest", guest);

				return jsonObject;
		}
		return null;
	}

	@Override public JSONObject getUserAccessStatistics(long userId,String sessionId )
	{
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		List<Object[]> lists = (List<Object[]>) TrackClientLocalServiceUtil.getTopURLUserAccess(userId);
		if (lists.size()!=0)
			for (Object[] list:lists)
			{
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put("url",list[0]);
				jsonObject.put("count",list[1]);
				jsonArray.put(jsonObject);
			}
		result.put("topURLUserAccess", jsonArray);
		return result;
	}
}
