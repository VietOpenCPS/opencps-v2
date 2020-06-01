/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.usermgt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.opencps.usermgt.model.TrackClientStatistic;
import org.opencps.usermgt.service.base.TrackClientStatisticLocalServiceBaseImpl;

/**
 * The implementation of the track client statistic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.TrackClientStatisticLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see TrackClientStatisticLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil
 */
public class TrackClientStatisticLocalServiceImpl
	extends TrackClientStatisticLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.TrackClientStatisticLocalServiceUtil} to access the track client statistic local service.
	 */
	public TrackClientStatistic updateTrackClientStatistic(
			long trackClientStatisticId,
			String url,
			int year,
			int month,
			int day,
			String region,
			boolean desktop, boolean mobile, boolean tablet) {
		TrackClientStatistic trackClientStatistic;
		Date now = new Date();
		if (trackClientStatisticId == 0) {
			trackClientStatisticId = counterLocalService.increment(TrackClientStatistic.class.getName());
			trackClientStatistic = trackClientStatisticPersistence.create(trackClientStatisticId);
			
			trackClientStatistic.setCreateDate(now);
		}
		else {
			trackClientStatistic = trackClientStatisticPersistence.fetchByPrimaryKey(trackClientStatisticId);
		}
		
		if (trackClientStatistic != null) {
			trackClientStatistic.setModifiedDate(now);
			trackClientStatistic.setUrl(url);
			trackClientStatistic.setYear(year);
			trackClientStatistic.setMonth(month);
			trackClientStatistic.setDay(day);
			trackClientStatistic.setRegion(region);
			trackClientStatistic.setDesktop(desktop);
			trackClientStatistic.setMobile(mobile);
			trackClientStatistic.setTablet(tablet);
			trackClientStatistic = trackClientStatisticPersistence.update(trackClientStatistic);
		}
		
		return trackClientStatistic;
	}	
	
	public void updateStatisticTotal(
			String url,
			int year,
			int month,
			int day,
			String region,
			boolean desktop, boolean mobile, boolean tablet) {
		//By day
		TrackClientStatistic trackClientStatisticDay = trackClientStatisticPersistence.fetchByU_Y_M_D_D_M_T(url, year, month, day, desktop, mobile, tablet);
		if (trackClientStatisticDay == null) {
			long trackClientStatisticDayId = counterLocalService.increment(TrackClientStatistic.class.getName());
			trackClientStatisticDay = trackClientStatisticPersistence.create(trackClientStatisticDayId);
			
			trackClientStatisticDay.setTotal(1);
			trackClientStatisticDay.setUrl(url);
			trackClientStatisticDay.setYear(year);
			trackClientStatisticDay.setMonth(month);
			trackClientStatisticDay.setDay(day);
			trackClientStatisticDay.setDesktop(desktop);
			trackClientStatisticDay.setMobile(mobile);
			trackClientStatisticDay.setTablet(tablet);

		}
		else {
			trackClientStatisticDay.setTotal(trackClientStatisticDay.getTotal() + 1);

		}
		trackClientStatisticPersistence.update(trackClientStatisticDay);

		//By month
		TrackClientStatistic trackClientStatisticMonth = trackClientStatisticPersistence.fetchByU_Y_M_D_D_M_T(url, year, month, 0, desktop, mobile, tablet);
		if (trackClientStatisticMonth == null) {
			long trackClientStatisticMonthId = counterLocalService.increment(TrackClientStatistic.class.getName());
			trackClientStatisticMonth = trackClientStatisticPersistence.create(trackClientStatisticMonthId);
			
			trackClientStatisticMonth.setTotal(1);
			trackClientStatisticMonth.setUrl(url);
			trackClientStatisticMonth.setYear(year);
			trackClientStatisticMonth.setMonth(month);
			trackClientStatisticMonth.setDay(0);
			trackClientStatisticMonth.setDesktop(desktop);
			trackClientStatisticMonth.setMobile(mobile);
			trackClientStatisticMonth.setTablet(tablet);

		}
		else {
			trackClientStatisticMonth.setTotal(trackClientStatisticMonth.getTotal() + 1);

		}
		trackClientStatisticPersistence.update(trackClientStatisticMonth);

		//By year
		TrackClientStatistic trackClientStatisticYear = trackClientStatisticPersistence.fetchByU_Y_M_D_D_M_T(url, year, 0, 0, desktop, mobile, tablet);
		if (trackClientStatisticYear == null) {
			long trackClientStatisticYearId = counterLocalService.increment(TrackClientStatistic.class.getName());
			trackClientStatisticYear = trackClientStatisticPersistence.create(trackClientStatisticYearId);
			
			trackClientStatisticYear.setTotal(1);
			trackClientStatisticYear.setUrl(url);
			trackClientStatisticYear.setYear(year);
			trackClientStatisticYear.setMonth(0);
			trackClientStatisticYear.setDay(0);
			trackClientStatisticYear.setDesktop(desktop);
			trackClientStatisticYear.setMobile(mobile);
			trackClientStatisticYear.setTablet(tablet);

		}
		else {
			trackClientStatisticYear.setTotal(trackClientStatisticYear.getTotal() + 1);

		}
		trackClientStatisticPersistence.update(trackClientStatisticYear);
	}
	public Map<String,Long> countAccess(int day,int month, int year)
	{
		Map<String,Long> result = new HashMap<>();
		long count=0;
		List<TrackClientStatistic> trackClientStatistics = trackClientStatisticPersistence.findByD_M_Y(day, month, year);
		for (TrackClientStatistic trackClientStatistic: trackClientStatistics)
			count = count + trackClientStatistic.getTotal();
		result.put("total",count);

		count=0;
		List<TrackClientStatistic> trackClientStatisticsDesktop = trackClientStatisticPersistence.findByD_M_Y_D_M_T(day,month,year,true,false,false);
		for (TrackClientStatistic trackClientStatistic: trackClientStatisticsDesktop)
			count = count + trackClientStatistic.getTotal();
		result.put("desktop",count);

		count=0;
		List<TrackClientStatistic> trackClientStatisticsMobile = trackClientStatisticPersistence.findByD_M_Y_D_M_T(day,month,year,false,true,false);
		for (TrackClientStatistic trackClientStatistic: trackClientStatisticsMobile)
			count = count + trackClientStatistic.getTotal();
		result.put("mobile",count);

		count=0;
		List<TrackClientStatistic> trackClientStatisticsTablet = trackClientStatisticPersistence.findByD_M_Y_D_M_T(day,month,year,false,false,true);
		for (TrackClientStatistic trackClientStatistic: trackClientStatisticsTablet)
			count = count + trackClientStatistic.getTotal();
		result.put("tablet",count);


		return result;
	}

	public Map<String, Long> countAccessPeriod(String startDay, String endDay)
	{
		Map<String,Long> result = new HashMap<>();
		long count = 0;


		List<TrackClientStatistic> trackClientStatistics = trackClientStatisticFinder.findPeriod(startDay, endDay);
		for (TrackClientStatistic trackClientStatistic: trackClientStatistics)
			count = count + trackClientStatistic.getTotal();
		result.put("total",count);

		count=0;
		List<TrackClientStatistic> trackClientStatisticsDesktop = trackClientStatisticFinder.findAccessPeriodDesktopMobileTablet(startDay,endDay,true , false , false);
		for (TrackClientStatistic trackClientStatistic: trackClientStatisticsDesktop)
			count = count + trackClientStatistic.getTotal();
		result.put("desktop",count);

		count=0;
		List<TrackClientStatistic> trackClientStatisticsMobile = trackClientStatisticFinder.findAccessPeriodDesktopMobileTablet(startDay,endDay,false , true , false);
		for (TrackClientStatistic trackClientStatistic: trackClientStatisticsMobile)
			count = count + trackClientStatistic.getTotal();
		result.put("mobile",count);

		count=0;
		List<TrackClientStatistic> trackClientStatisticsTablet = trackClientStatisticFinder.findAccessPeriodDesktopMobileTablet(startDay,endDay,false , false , true);
		for (TrackClientStatistic trackClientStatistic: trackClientStatisticsTablet)
			count = count + trackClientStatistic.getTotal();
		result.put("tablet",count);



		return result;
	}

	public long countAccessAllYear()
	{
		//sử dụng nếu muốn thống kê theo từng năm
		//Map<Integer,Long> map = new HashMap<>();
		List<org.opencps.usermgt.model.TrackClientStatistic> l = trackClientStatisticFinder.findAllYear();
		long count = 0 ;
		for (TrackClientStatistic trackClientStatistic : l)
		{
			count = count + trackClientStatistic.getTotal();
			//sử dụng nếu muốn thống kê theo từng năm
			//map.put(l.get(i).getYear(),l.get(i).getTotal());
		}
		return count;
	}

	public List<TrackClientStatistic> accessStatisticsURL(int day,int month, int year)
	{

		return trackClientStatisticPersistence.findByD_M_Y(day, month, year);
	}
	public JSONObject accessStatisticsURLForAllYear()
	{
		JSONArray  jsonArray = JSONFactoryUtil.createJSONArray();
		List<TrackClientStatistic> trackClientStatistics = trackClientStatisticFinder.findURLAllYear();
		for (TrackClientStatistic trackClientStatistic: trackClientStatistics )
		{
			long count =0;
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			List<TrackClientStatistic> list = trackClientStatisticFinder.findAccessURLAllYear(trackClientStatistic.getUrl());
			for (TrackClientStatistic clientStatistic : list)
				count = count + clientStatistic.getTotal();
			jsonObject.put("value", count);
			jsonObject.put("url",trackClientStatistic.getUrl());
			jsonArray.put(jsonObject);

		}
		JSONObject result = JSONFactoryUtil.createJSONObject();
		result.put("accessStatisticsURL", jsonArray);
		return result;
	}

	public JSONObject accessStatisticsURLForPeriod(String startDay, String endDay)
	{
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		List<TrackClientStatistic> trackClientStatistics = trackClientStatisticFinder.findURLPeriod(startDay,endDay);
		for (TrackClientStatistic trackClientStatistic: trackClientStatistics )
		{
			long count =0;
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			List<TrackClientStatistic> list = trackClientStatisticFinder.findAccessURLPeriod(startDay,endDay,trackClientStatistic.getUrl());
			for (TrackClientStatistic clientStatistic : list)
			{
				count = count + clientStatistic.getTotal();
			}
			jsonObject.put("value", count);
			jsonObject.put("url",trackClientStatistic.getUrl());
			jsonArray.put(jsonObject);

		}

		result.put("accessStatisticsURL", jsonArray);
		return result;
	}

	public JSONObject getOnline()
	{
		JSONObject result = JSONFactoryUtil.createJSONObject();


		List list = trackClientFinder.getOnline();
		if (list.size() ==1)
		{
			result.put("online" ,list.get(0));
		}

		return result;
	}
}