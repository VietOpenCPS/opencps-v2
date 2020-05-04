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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.TrackClientStatistic;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the track client statistic service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.TrackClientStatisticPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see TrackClientStatisticPersistence
 * @see org.opencps.usermgt.service.persistence.impl.TrackClientStatisticPersistenceImpl
 * @generated
 */
@ProviderType
public class TrackClientStatisticUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TrackClientStatistic trackClientStatistic) {
		getPersistence().clearCache(trackClientStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrackClientStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrackClientStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrackClientStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TrackClientStatistic update(
		TrackClientStatistic trackClientStatistic) {
		return getPersistence().update(trackClientStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TrackClientStatistic update(
		TrackClientStatistic trackClientStatistic, ServiceContext serviceContext) {
		return getPersistence().update(trackClientStatistic, serviceContext);
	}

	/**
	* Returns all the track client statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching track client statistics
	*/
	public static List<TrackClientStatistic> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the track client statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @return the range of matching track client statistics
	*/
	public static List<TrackClientStatistic> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the track client statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track client statistics
	*/
	public static List<TrackClientStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the track client statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track client statistics
	*/
	public static List<TrackClientStatistic> findByUuid(String uuid, int start,
		int end, OrderByComparator<TrackClientStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic findByUuid_First(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic fetchByUuid_First(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic findByUuid_Last(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic fetchByUuid_Last(String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the track client statistics before and after the current track client statistic in the ordered set where uuid = &#63;.
	*
	* @param trackClientStatisticId the primary key of the current track client statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client statistic
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public static TrackClientStatistic[] findByUuid_PrevAndNext(
		long trackClientStatisticId, String uuid,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence()
				   .findByUuid_PrevAndNext(trackClientStatisticId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the track client statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of track client statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching track client statistics
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the track client statistic where url = &#63; and year = &#63; and month = &#63; and day = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or throws a {@link NoSuchTrackClientStatisticException} if it could not be found.
	*
	* @param url the url
	* @param year the year
	* @param month the month
	* @param day the day
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic findByU_Y_M_D_D_M_T(String url,
		int year, int month, int day, boolean desktop, boolean mobile,
		boolean tablet)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence()
				   .findByU_Y_M_D_D_M_T(url, year, month, day, desktop, mobile,
			tablet);
	}

	/**
	* Returns the track client statistic where url = &#63; and year = &#63; and month = &#63; and day = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param url the url
	* @param year the year
	* @param month the month
	* @param day the day
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic fetchByU_Y_M_D_D_M_T(String url,
		int year, int month, int day, boolean desktop, boolean mobile,
		boolean tablet) {
		return getPersistence()
				   .fetchByU_Y_M_D_D_M_T(url, year, month, day, desktop,
			mobile, tablet);
	}

	/**
	* Returns the track client statistic where url = &#63; and year = &#63; and month = &#63; and day = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param url the url
	* @param year the year
	* @param month the month
	* @param day the day
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic fetchByU_Y_M_D_D_M_T(String url,
		int year, int month, int day, boolean desktop, boolean mobile,
		boolean tablet, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_Y_M_D_D_M_T(url, year, month, day, desktop,
			mobile, tablet, retrieveFromCache);
	}

	/**
	* Removes the track client statistic where url = &#63; and year = &#63; and month = &#63; and day = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; from the database.
	*
	* @param url the url
	* @param year the year
	* @param month the month
	* @param day the day
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the track client statistic that was removed
	*/
	public static TrackClientStatistic removeByU_Y_M_D_D_M_T(String url,
		int year, int month, int day, boolean desktop, boolean mobile,
		boolean tablet)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence()
				   .removeByU_Y_M_D_D_M_T(url, year, month, day, desktop,
			mobile, tablet);
	}

	/**
	* Returns the number of track client statistics where url = &#63; and year = &#63; and month = &#63; and day = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63;.
	*
	* @param url the url
	* @param year the year
	* @param month the month
	* @param day the day
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the number of matching track client statistics
	*/
	public static int countByU_Y_M_D_D_M_T(String url, int year, int month,
		int day, boolean desktop, boolean mobile, boolean tablet) {
		return getPersistence()
				   .countByU_Y_M_D_D_M_T(url, year, month, day, desktop,
			mobile, tablet);
	}

	/**
	* Returns all the track client statistics where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @return the matching track client statistics
	*/
	public static List<TrackClientStatistic> findByD_M_Y(int day, int month,
		int year) {
		return getPersistence().findByD_M_Y(day, month, year);
	}

	/**
	* Returns a range of all the track client statistics where day = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @return the range of matching track client statistics
	*/
	public static List<TrackClientStatistic> findByD_M_Y(int day, int month,
		int year, int start, int end) {
		return getPersistence().findByD_M_Y(day, month, year, start, end);
	}

	/**
	* Returns an ordered range of all the track client statistics where day = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track client statistics
	*/
	public static List<TrackClientStatistic> findByD_M_Y(int day, int month,
		int year, int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence()
				   .findByD_M_Y(day, month, year, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the track client statistics where day = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track client statistics
	*/
	public static List<TrackClientStatistic> findByD_M_Y(int day, int month,
		int year, int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_M_Y(day, month, year, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first track client statistic in the ordered set where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic findByD_M_Y_First(int day, int month,
		int year, OrderByComparator<TrackClientStatistic> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence()
				   .findByD_M_Y_First(day, month, year, orderByComparator);
	}

	/**
	* Returns the first track client statistic in the ordered set where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic fetchByD_M_Y_First(int day, int month,
		int year, OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByD_M_Y_First(day, month, year, orderByComparator);
	}

	/**
	* Returns the last track client statistic in the ordered set where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic findByD_M_Y_Last(int day, int month,
		int year, OrderByComparator<TrackClientStatistic> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence()
				   .findByD_M_Y_Last(day, month, year, orderByComparator);
	}

	/**
	* Returns the last track client statistic in the ordered set where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public static TrackClientStatistic fetchByD_M_Y_Last(int day, int month,
		int year, OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence()
				   .fetchByD_M_Y_Last(day, month, year, orderByComparator);
	}

	/**
	* Returns the track client statistics before and after the current track client statistic in the ordered set where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param trackClientStatisticId the primary key of the current track client statistic
	* @param day the day
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client statistic
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public static TrackClientStatistic[] findByD_M_Y_PrevAndNext(
		long trackClientStatisticId, int day, int month, int year,
		OrderByComparator<TrackClientStatistic> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence()
				   .findByD_M_Y_PrevAndNext(trackClientStatisticId, day, month,
			year, orderByComparator);
	}

	/**
	* Removes all the track client statistics where day = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	*/
	public static void removeByD_M_Y(int day, int month, int year) {
		getPersistence().removeByD_M_Y(day, month, year);
	}

	/**
	* Returns the number of track client statistics where day = &#63; and month = &#63; and year = &#63;.
	*
	* @param day the day
	* @param month the month
	* @param year the year
	* @return the number of matching track client statistics
	*/
	public static int countByD_M_Y(int day, int month, int year) {
		return getPersistence().countByD_M_Y(day, month, year);
	}

	/**
	* Caches the track client statistic in the entity cache if it is enabled.
	*
	* @param trackClientStatistic the track client statistic
	*/
	public static void cacheResult(TrackClientStatistic trackClientStatistic) {
		getPersistence().cacheResult(trackClientStatistic);
	}

	/**
	* Caches the track client statistics in the entity cache if it is enabled.
	*
	* @param trackClientStatistics the track client statistics
	*/
	public static void cacheResult(
		List<TrackClientStatistic> trackClientStatistics) {
		getPersistence().cacheResult(trackClientStatistics);
	}

	/**
	* Creates a new track client statistic with the primary key. Does not add the track client statistic to the database.
	*
	* @param trackClientStatisticId the primary key for the new track client statistic
	* @return the new track client statistic
	*/
	public static TrackClientStatistic create(long trackClientStatisticId) {
		return getPersistence().create(trackClientStatisticId);
	}

	/**
	* Removes the track client statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic that was removed
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public static TrackClientStatistic remove(long trackClientStatisticId)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence().remove(trackClientStatisticId);
	}

	public static TrackClientStatistic updateImpl(
		TrackClientStatistic trackClientStatistic) {
		return getPersistence().updateImpl(trackClientStatistic);
	}

	/**
	* Returns the track client statistic with the primary key or throws a {@link NoSuchTrackClientStatisticException} if it could not be found.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public static TrackClientStatistic findByPrimaryKey(
		long trackClientStatisticId)
		throws org.opencps.usermgt.exception.NoSuchTrackClientStatisticException {
		return getPersistence().findByPrimaryKey(trackClientStatisticId);
	}

	/**
	* Returns the track client statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic, or <code>null</code> if a track client statistic with the primary key could not be found
	*/
	public static TrackClientStatistic fetchByPrimaryKey(
		long trackClientStatisticId) {
		return getPersistence().fetchByPrimaryKey(trackClientStatisticId);
	}

	public static java.util.Map<java.io.Serializable, TrackClientStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the track client statistics.
	*
	* @return the track client statistics
	*/
	public static List<TrackClientStatistic> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the track client statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @return the range of track client statistics
	*/
	public static List<TrackClientStatistic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the track client statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of track client statistics
	*/
	public static List<TrackClientStatistic> findAll(int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the track client statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track client statistics
	* @param end the upper bound of the range of track client statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of track client statistics
	*/
	public static List<TrackClientStatistic> findAll(int start, int end,
		OrderByComparator<TrackClientStatistic> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the track client statistics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of track client statistics.
	*
	* @return the number of track client statistics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TrackClientStatisticPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TrackClientStatisticPersistence, TrackClientStatisticPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TrackClientStatisticPersistence.class);

		ServiceTracker<TrackClientStatisticPersistence, TrackClientStatisticPersistence> serviceTracker =
			new ServiceTracker<TrackClientStatisticPersistence, TrackClientStatisticPersistence>(bundle.getBundleContext(),
				TrackClientStatisticPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}