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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchTrackClientStatisticException;
import org.opencps.usermgt.model.TrackClientStatistic;

/**
 * The persistence interface for the track client statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.TrackClientStatisticPersistenceImpl
 * @see TrackClientStatisticUtil
 * @generated
 */
@ProviderType
public interface TrackClientStatisticPersistence extends BasePersistence<TrackClientStatistic> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrackClientStatisticUtil} to access the track client statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the track client statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching track client statistics
	*/
	public java.util.List<TrackClientStatistic> findByUuid(String uuid);

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
	public java.util.List<TrackClientStatistic> findByUuid(String uuid,
		int start, int end);

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
	public java.util.List<TrackClientStatistic> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator);

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
	public java.util.List<TrackClientStatistic> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public TrackClientStatistic findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator)
		throws NoSuchTrackClientStatisticException;

	/**
	* Returns the first track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public TrackClientStatistic fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator);

	/**
	* Returns the last track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public TrackClientStatistic findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator)
		throws NoSuchTrackClientStatisticException;

	/**
	* Returns the last track client statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public TrackClientStatistic fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator);

	/**
	* Returns the track client statistics before and after the current track client statistic in the ordered set where uuid = &#63;.
	*
	* @param trackClientStatisticId the primary key of the current track client statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client statistic
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public TrackClientStatistic[] findByUuid_PrevAndNext(
		long trackClientStatisticId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator)
		throws NoSuchTrackClientStatisticException;

	/**
	* Removes all the track client statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of track client statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching track client statistics
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or throws a {@link NoSuchTrackClientStatisticException} if it could not be found.
	*
	* @param url the url
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the matching track client statistic
	* @throws NoSuchTrackClientStatisticException if a matching track client statistic could not be found
	*/
	public TrackClientStatistic findByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet)
		throws NoSuchTrackClientStatisticException;

	/**
	* Returns the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param url the url
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public TrackClientStatistic fetchByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet);

	/**
	* Returns the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param url the url
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching track client statistic, or <code>null</code> if a matching track client statistic could not be found
	*/
	public TrackClientStatistic fetchByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet, boolean retrieveFromCache);

	/**
	* Removes the track client statistic where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63; from the database.
	*
	* @param url the url
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the track client statistic that was removed
	*/
	public TrackClientStatistic removeByU_D_M_T(String url, boolean desktop,
		boolean mobile, boolean tablet)
		throws NoSuchTrackClientStatisticException;

	/**
	* Returns the number of track client statistics where url = &#63; and desktop = &#63; and mobile = &#63; and tablet = &#63;.
	*
	* @param url the url
	* @param desktop the desktop
	* @param mobile the mobile
	* @param tablet the tablet
	* @return the number of matching track client statistics
	*/
	public int countByU_D_M_T(String url, boolean desktop, boolean mobile,
		boolean tablet);

	/**
	* Caches the track client statistic in the entity cache if it is enabled.
	*
	* @param trackClientStatistic the track client statistic
	*/
	public void cacheResult(TrackClientStatistic trackClientStatistic);

	/**
	* Caches the track client statistics in the entity cache if it is enabled.
	*
	* @param trackClientStatistics the track client statistics
	*/
	public void cacheResult(
		java.util.List<TrackClientStatistic> trackClientStatistics);

	/**
	* Creates a new track client statistic with the primary key. Does not add the track client statistic to the database.
	*
	* @param trackClientStatisticId the primary key for the new track client statistic
	* @return the new track client statistic
	*/
	public TrackClientStatistic create(long trackClientStatisticId);

	/**
	* Removes the track client statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic that was removed
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public TrackClientStatistic remove(long trackClientStatisticId)
		throws NoSuchTrackClientStatisticException;

	public TrackClientStatistic updateImpl(
		TrackClientStatistic trackClientStatistic);

	/**
	* Returns the track client statistic with the primary key or throws a {@link NoSuchTrackClientStatisticException} if it could not be found.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic
	* @throws NoSuchTrackClientStatisticException if a track client statistic with the primary key could not be found
	*/
	public TrackClientStatistic findByPrimaryKey(long trackClientStatisticId)
		throws NoSuchTrackClientStatisticException;

	/**
	* Returns the track client statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackClientStatisticId the primary key of the track client statistic
	* @return the track client statistic, or <code>null</code> if a track client statistic with the primary key could not be found
	*/
	public TrackClientStatistic fetchByPrimaryKey(long trackClientStatisticId);

	@Override
	public java.util.Map<java.io.Serializable, TrackClientStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the track client statistics.
	*
	* @return the track client statistics
	*/
	public java.util.List<TrackClientStatistic> findAll();

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
	public java.util.List<TrackClientStatistic> findAll(int start, int end);

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
	public java.util.List<TrackClientStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator);

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
	public java.util.List<TrackClientStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClientStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the track client statistics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of track client statistics.
	*
	* @return the number of track client statistics
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}