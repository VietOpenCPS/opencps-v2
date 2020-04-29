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

import org.opencps.usermgt.exception.NoSuchTrackClientException;
import org.opencps.usermgt.model.TrackClient;

import java.util.Date;

/**
 * The persistence interface for the track client service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.TrackClientPersistenceImpl
 * @see TrackClientUtil
 * @generated
 */
@ProviderType
public interface TrackClientPersistence extends BasePersistence<TrackClient> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrackClientUtil} to access the track client persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the track clients where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching track clients
	*/
	public java.util.List<TrackClient> findByUuid(String uuid);

	/**
	* Returns a range of all the track clients where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of matching track clients
	*/
	public java.util.List<TrackClient> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the track clients where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns an ordered range of all the track clients where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the first track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the last track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the last track client in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the track clients before and after the current track client in the ordered set where uuid = &#63;.
	*
	* @param trackClientId the primary key of the current track client
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public TrackClient[] findByUuid_PrevAndNext(long trackClientId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Removes all the track clients where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of track clients where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching track clients
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the track clients where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @return the matching track clients
	*/
	public java.util.List<TrackClient> findByS(String sessionId);

	/**
	* Returns a range of all the track clients where sessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of matching track clients
	*/
	public java.util.List<TrackClient> findByS(String sessionId, int start,
		int end);

	/**
	* Returns an ordered range of all the track clients where sessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByS(String sessionId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns an ordered range of all the track clients where sessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByS(String sessionId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first track client in the ordered set where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByS_First(String sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the first track client in the ordered set where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByS_First(String sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the last track client in the ordered set where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByS_Last(String sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the last track client in the ordered set where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByS_Last(String sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the track clients before and after the current track client in the ordered set where sessionId = &#63;.
	*
	* @param trackClientId the primary key of the current track client
	* @param sessionId the session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public TrackClient[] findByS_PrevAndNext(long trackClientId,
		String sessionId,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Removes all the track clients where sessionId = &#63; from the database.
	*
	* @param sessionId the session ID
	*/
	public void removeByS(String sessionId);

	/**
	* Returns the number of track clients where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @return the number of matching track clients
	*/
	public int countByS(String sessionId);

	/**
	* Returns all the track clients where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @return the matching track clients
	*/
	public java.util.List<TrackClient> findByS_NULL_L(String sessionId,
		Date leaveDate);

	/**
	* Returns a range of all the track clients where sessionId = &#63; and leaveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of matching track clients
	*/
	public java.util.List<TrackClient> findByS_NULL_L(String sessionId,
		Date leaveDate, int start, int end);

	/**
	* Returns an ordered range of all the track clients where sessionId = &#63; and leaveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByS_NULL_L(String sessionId,
		Date leaveDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns an ordered range of all the track clients where sessionId = &#63; and leaveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByS_NULL_L(String sessionId,
		Date leaveDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByS_NULL_L_First(String sessionId, Date leaveDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the first track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByS_NULL_L_First(String sessionId, Date leaveDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the last track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByS_NULL_L_Last(String sessionId, Date leaveDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the last track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByS_NULL_L_Last(String sessionId, Date leaveDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the track clients before and after the current track client in the ordered set where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param trackClientId the primary key of the current track client
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public TrackClient[] findByS_NULL_L_PrevAndNext(long trackClientId,
		String sessionId, Date leaveDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Removes all the track clients where sessionId = &#63; and leaveDate = &#63; from the database.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	*/
	public void removeByS_NULL_L(String sessionId, Date leaveDate);

	/**
	* Returns the number of track clients where sessionId = &#63; and leaveDate = &#63;.
	*
	* @param sessionId the session ID
	* @param leaveDate the leave date
	* @return the number of matching track clients
	*/
	public int countByS_NULL_L(String sessionId, Date leaveDate);

	/**
	* Returns all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @return the matching track clients
	*/
	public java.util.List<TrackClient> findByS_LVD(String sessionId,
		Date visitDate);

	/**
	* Returns a range of all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of matching track clients
	*/
	public java.util.List<TrackClient> findByS_LVD(String sessionId,
		Date visitDate, int start, int end);

	/**
	* Returns an ordered range of all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByS_LVD(String sessionId,
		Date visitDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns an ordered range of all the track clients where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching track clients
	*/
	public java.util.List<TrackClient> findByS_LVD(String sessionId,
		Date visitDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByS_LVD_First(String sessionId, Date visitDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the first track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByS_LVD_First(String sessionId, Date visitDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the last track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client
	* @throws NoSuchTrackClientException if a matching track client could not be found
	*/
	public TrackClient findByS_LVD_Last(String sessionId, Date visitDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Returns the last track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching track client, or <code>null</code> if a matching track client could not be found
	*/
	public TrackClient fetchByS_LVD_Last(String sessionId, Date visitDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns the track clients before and after the current track client in the ordered set where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param trackClientId the primary key of the current track client
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public TrackClient[] findByS_LVD_PrevAndNext(long trackClientId,
		String sessionId, Date visitDate,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator)
		throws NoSuchTrackClientException;

	/**
	* Removes all the track clients where sessionId = &#63; and visitDate &lt; &#63; from the database.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	*/
	public void removeByS_LVD(String sessionId, Date visitDate);

	/**
	* Returns the number of track clients where sessionId = &#63; and visitDate &lt; &#63;.
	*
	* @param sessionId the session ID
	* @param visitDate the visit date
	* @return the number of matching track clients
	*/
	public int countByS_LVD(String sessionId, Date visitDate);

	/**
	* Caches the track client in the entity cache if it is enabled.
	*
	* @param trackClient the track client
	*/
	public void cacheResult(TrackClient trackClient);

	/**
	* Caches the track clients in the entity cache if it is enabled.
	*
	* @param trackClients the track clients
	*/
	public void cacheResult(java.util.List<TrackClient> trackClients);

	/**
	* Creates a new track client with the primary key. Does not add the track client to the database.
	*
	* @param trackClientId the primary key for the new track client
	* @return the new track client
	*/
	public TrackClient create(long trackClientId);

	/**
	* Removes the track client with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client that was removed
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public TrackClient remove(long trackClientId)
		throws NoSuchTrackClientException;

	public TrackClient updateImpl(TrackClient trackClient);

	/**
	* Returns the track client with the primary key or throws a {@link NoSuchTrackClientException} if it could not be found.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client
	* @throws NoSuchTrackClientException if a track client with the primary key could not be found
	*/
	public TrackClient findByPrimaryKey(long trackClientId)
		throws NoSuchTrackClientException;

	/**
	* Returns the track client with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackClientId the primary key of the track client
	* @return the track client, or <code>null</code> if a track client with the primary key could not be found
	*/
	public TrackClient fetchByPrimaryKey(long trackClientId);

	@Override
	public java.util.Map<java.io.Serializable, TrackClient> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the track clients.
	*
	* @return the track clients
	*/
	public java.util.List<TrackClient> findAll();

	/**
	* Returns a range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @return the range of track clients
	*/
	public java.util.List<TrackClient> findAll(int start, int end);

	/**
	* Returns an ordered range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of track clients
	*/
	public java.util.List<TrackClient> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator);

	/**
	* Returns an ordered range of all the track clients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TrackClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of track clients
	* @param end the upper bound of the range of track clients (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of track clients
	*/
	public java.util.List<TrackClient> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TrackClient> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the track clients from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of track clients.
	*
	* @return the number of track clients
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}