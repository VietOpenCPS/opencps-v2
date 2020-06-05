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

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import org.opencps.usermgt.model.TrackClient;
import org.opencps.usermgt.service.base.TrackClientLocalServiceBaseImpl;

/**
 * The implementation of the track client local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.usermgt.service.TrackClientLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see TrackClientLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.TrackClientLocalServiceUtil
 */
public class TrackClientLocalServiceImpl extends TrackClientLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.usermgt.service.TrackClientLocalServiceUtil} to access the track client local service.
	 */
	public TrackClient updateTrackClient(long trackClientId, String sessionId, String url, int year, int month, int day, Date visitDate, Date leaveDate, String clientIP,
			String macAddress, String region, String nation, String latitude, String longitude, long timeOnPage, boolean desktop, boolean mobile, boolean tablet) {
		TrackClient trackClient = null;
		if (trackClientId == 0) {
			trackClientId = counterLocalService.increment(TrackClient.class.getName());
			trackClient = trackClientPersistence.create(trackClientId);
		}
		else {
			trackClient = trackClientPersistence.fetchByPrimaryKey(trackClientId);
		}

		if (trackClient != null) {
			trackClient.setSessionId(sessionId);
			trackClient.setUrl(url);
			trackClient.setYear(year);
			trackClient.setMonth(month);
			trackClient.setDay(day);
			trackClient.setVisitDate(visitDate);
			trackClient.setLeaveDate(leaveDate);
			trackClient.setClientIP(clientIP);
			trackClient.setMacAddress(macAddress);
			trackClient.setRegion(region);
			trackClient.setNation(nation);
			trackClient.setLatitude(latitude);
			trackClient.setLongitude(longitude);
			trackClient.setTimeOnPage(timeOnPage);
			trackClient.setDesktop(desktop);
			trackClient.setMobile(mobile);
			trackClient.setTablet(tablet);

			trackClient = trackClientPersistence.update(trackClient);
		}

		return trackClient;
	}

	public TrackClient updateTrackClient(long trackClientId, String sessionId, String url, int year, int month, int day, Date visitDate, Date leaveDate, String clientIP,
			String macAddress, String region, String nation, String latitude, String longitude, long timeOnPage, boolean desktop, boolean mobile,
			boolean tablet, long userId, String userName) {
		TrackClient trackClient = null;
		if (trackClientId == 0) {
			trackClientId = counterLocalService.increment(TrackClient.class.getName());
			trackClient = trackClientPersistence.create(trackClientId);
		}
		else {
			trackClient = trackClientPersistence.fetchByPrimaryKey(trackClientId);
		}

		if (trackClient != null) {
			trackClient.setSessionId(sessionId);
			trackClient.setUrl(url);
			trackClient.setYear(year);
			trackClient.setMonth(month);
			trackClient.setDay(day);
			trackClient.setVisitDate(visitDate);
			trackClient.setLeaveDate(leaveDate);
			trackClient.setClientIP(clientIP);
			trackClient.setMacAddress(macAddress);
			trackClient.setRegion(region);
			trackClient.setNation(nation);
			trackClient.setLatitude(latitude);
			trackClient.setLongitude(longitude);
			trackClient.setTimeOnPage(timeOnPage);
			trackClient.setDesktop(desktop);
			trackClient.setMobile(mobile);
			trackClient.setTablet(tablet);
			trackClient.setUserId(userId);
			trackClient.setUserName(userName);

			trackClient = trackClientPersistence.update(trackClient);
		}

		return trackClient;
	}

	public TrackClient findPreviousPage(String sessionId)
	{
		List<TrackClient> trackClients = trackClientFinder.findPreviousPage(sessionId);
		if (trackClients.isEmpty())
			return null;
		return trackClients.get(0);
	}

	public List<TrackClient> findByS(String sessionId, int start, int end, OrderByComparator<TrackClient> orderBy) {
		return trackClientPersistence.findByS(sessionId, start, end, orderBy);
	}

	public List<TrackClient> findByS(String sessionId, int start, int end) {
		return trackClientPersistence.findByS(sessionId, start, end);
	}

	public List<TrackClient> findByS_NULL_L(String sessionId, Date leaveDate, int start, int end) {
		return trackClientPersistence.findByS_NULL_L(sessionId, leaveDate);
	}

	public List<TrackClient> findByS_NULL_L(String sessionId, Date leaveDate) {
		return trackClientPersistence.findByS_NULL_L(sessionId, leaveDate);
	}

	public List<TrackClient> findByS_LVD(String sessionId, Date visitDate) {
		return trackClientPersistence.findByS_LVD(sessionId, visitDate);
	}

	public List<TrackClient> findByS_LVD(String sessionId, Date visitDate, int start, int end) {
		return trackClientPersistence.findByS_LVD(sessionId, visitDate, start, end);
	}

	public List<Object[]> getOnline()
	{
		return trackClientFinder.getOnline();
	}
	public List<Object[]> getTopURLUserAccess(long userId)
	{
		return trackClientFinder.getTopURLUserAccess(userId);
	}

	public List<Object[]> findPeriodCountDay(String startDay, String endDay)
	{
		return trackClientFinder.findPeriodCountDay(startDay,endDay);
	}
	public List<Object[]> findPeriodRegion(String startDay, String endDay)
	{
		return trackClientFinder.findPeriodRegion(startDay,endDay);
	}
}