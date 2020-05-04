package org.opencps.usermgt.action.impl;

import org.opencps.usermgt.action.AccessStatisticsActions;
import org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil;


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
}
