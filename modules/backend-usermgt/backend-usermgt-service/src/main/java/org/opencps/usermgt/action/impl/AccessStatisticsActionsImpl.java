package org.opencps.usermgt.action.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.usermgt.action.AccessStatisticsActions;
import org.opencps.usermgt.model.TrackClientStatistic;
import org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil;

import java.util.List;

public class AccessStatisticsActionsImpl implements AccessStatisticsActions
{

	@Override
	public long getAccessStatistics(int day, int month, int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(day, month, yaer);

	}

	@Override
	public long getAccessStatisticsForDay(int day, int month, int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(day, month, yaer);
	}

	@Override
	public long getAccessStatisticsForPeriod(String  startDay, String endDay)
	{
		return TrackClientStatisticLocalServiceUtil.countAccessPeriod(startDay,endDay);
	}

	@Override
	public long getAccessStatisticsForMonth( int month, int yaer)
	{
		return TrackClientStatisticLocalServiceUtil.countAccess(0,month, yaer);
	}

	@Override
	public long getAccessStatisticsForYear( int yaer)
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

}
