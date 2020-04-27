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
		TrackClientStatistic trackClientStatistic = null;
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
			
			trackClientStatisticPersistence.update(trackClientStatisticDay);
		}
		else {
			trackClientStatisticDay.setTotal(trackClientStatisticDay.getTotal() + 1);
			
			trackClientStatisticPersistence.update(trackClientStatisticDay);
		}
		
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
			
			trackClientStatisticPersistence.update(trackClientStatisticMonth);
		}
		else {
			trackClientStatisticMonth.setTotal(trackClientStatisticMonth.getTotal() + 1);
			
			trackClientStatisticPersistence.update(trackClientStatisticMonth);
		}
		
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
			
			trackClientStatisticPersistence.update(trackClientStatisticYear);
		}
		else {
			trackClientStatisticYear.setTotal(trackClientStatisticYear.getTotal() + 1);
			
			trackClientStatisticPersistence.update(trackClientStatisticYear);
		}
	}		
}