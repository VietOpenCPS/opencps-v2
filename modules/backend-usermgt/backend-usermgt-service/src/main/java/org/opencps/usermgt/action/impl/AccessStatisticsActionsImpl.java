package org.opencps.usermgt.action.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.usermgt.action.AccessStatisticsActions;
import org.opencps.usermgt.model.TrackClientStatistic;
import org.opencps.usermgt.service.TrackClientLocalServiceUtil;
import org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil;
import org.opencps.usermgt.service.persistence.TrackClientStatisticUtil;

import java.util.List;
import java.util.Map;

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
	public Map<String, Long> getAccessStatisticsForPeriod(String  startDay, String endDay)
	{
		return TrackClientStatisticLocalServiceUtil.countAccessPeriod(startDay,endDay);
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
		List<Object[]> list = TrackClientLocalServiceUtil.getOnline();
		if (list.size()==1)
		{
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				String user =list.get(0)[0].toString();
				String guest = list.get(0)[1].toString();

				jsonObject.put("user" ,user );
				jsonObject.put("guest", guest);

				jsonObject.toString();
				return jsonObject;
		}
		return null;
	}

}
