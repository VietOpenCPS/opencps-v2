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

package org.opencps.backend.systemlogmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.backend.systemlogmgt.exception.NoSuchSystemLogException;
import org.opencps.backend.systemlogmgt.model.SystemLog;

/**
 * The persistence interface for the system log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.opencps.backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl
 * @see SystemLogUtil
 * @generated
 */
@ProviderType
public interface SystemLogPersistence extends BasePersistence<SystemLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SystemLogUtil} to access the system log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the system logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByUuid(String uuid);

	/**
	* Returns a range of all the system logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the system logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where uuid = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByUuid_PrevAndNext(long logId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of system logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching system logs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the system log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSystemLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByUUID_G(String uuid, long groupId)
		throws NoSuchSystemLogException;

	/**
	* Returns the system log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the system log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the system log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the system log that was removed
	*/
	public SystemLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchSystemLogException;

	/**
	* Returns the number of system logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching system logs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long groupId);

	/**
	* Returns a range of all the system logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where groupId = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBymultipleGroupId_PrevAndNext(long logId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns all the system logs where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long[] groupIds);

	/**
	* Returns a range of all the system logs where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long[] groupIds,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where groupId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupIds the group IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long[] groupIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where groupId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleGroupId(long[] groupIds,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeBymultipleGroupId(long groupId);

	/**
	* Returns the number of system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching system logs
	*/
	public int countBymultipleGroupId(long groupId);

	/**
	* Returns the number of system logs where groupId = any &#63;.
	*
	* @param groupIds the group IDs
	* @return the number of matching system logs
	*/
	public int countBymultipleGroupId(long[] groupIds);

	/**
	* Returns all the system logs where type = &#63;.
	*
	* @param type the type
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String type);

	/**
	* Returns a range of all the system logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String type, int start,
		int end);

	/**
	* Returns an ordered range of all the system logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleType_First(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleType_First(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleType_Last(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleType_Last(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where type = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBymultipleType_PrevAndNext(long logId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns all the system logs where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String[] types);

	/**
	* Returns a range of all the system logs where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String[] types,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where type = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param types the types
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String[] types,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where type = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleType(String[] types,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs where type = &#63; from the database.
	*
	* @param type the type
	*/
	public void removeBymultipleType(String type);

	/**
	* Returns the number of system logs where type = &#63;.
	*
	* @param type the type
	* @return the number of matching system logs
	*/
	public int countBymultipleType(String type);

	/**
	* Returns the number of system logs where type = any &#63;.
	*
	* @param types the types
	* @return the number of matching system logs
	*/
	public int countBymultipleType(String[] types);

	/**
	* Returns all the system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(String moduleName);

	/**
	* Returns a range of all the system logs where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String moduleName, int start, int end);

	/**
	* Returns an ordered range of all the system logs where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where moduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String moduleName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultiplemoduleName_First(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultiplemoduleName_First(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultiplemoduleName_Last(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultiplemoduleName_Last(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where moduleName = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBymultiplemoduleName_PrevAndNext(long logId,
		String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns all the system logs where moduleName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleNames the module names
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames);

	/**
	* Returns a range of all the system logs where moduleName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleNames the module names
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames, int start, int end);

	/**
	* Returns an ordered range of all the system logs where moduleName = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleNames the module names
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where moduleName = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param moduleName the module name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplemoduleName(
		String[] moduleNames, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public void removeBymultiplemoduleName(String moduleName);

	/**
	* Returns the number of system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching system logs
	*/
	public int countBymultiplemoduleName(String moduleName);

	/**
	* Returns the number of system logs where moduleName = any &#63;.
	*
	* @param moduleNames the module names
	* @return the number of matching system logs
	*/
	public int countBymultiplemoduleName(String[] moduleNames);

	/**
	* Returns all the system logs where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(String preMethod);

	/**
	* Returns a range of all the system logs where preMethod = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(String preMethod,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where preMethod = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(String preMethod,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where preMethod = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(String preMethod,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultiplePreMethod_First(String preMethod,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultiplePreMethod_First(String preMethod,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultiplePreMethod_Last(String preMethod,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultiplePreMethod_Last(String preMethod,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where preMethod = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param preMethod the pre method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBymultiplePreMethod_PrevAndNext(long logId,
		String preMethod,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns all the system logs where preMethod = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethods the pre methods
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(
		String[] preMethods);

	/**
	* Returns a range of all the system logs where preMethod = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethods the pre methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(
		String[] preMethods, int start, int end);

	/**
	* Returns an ordered range of all the system logs where preMethod = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethods the pre methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(
		String[] preMethods, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where preMethod = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param preMethod the pre method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultiplePreMethod(
		String[] preMethods, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs where preMethod = &#63; from the database.
	*
	* @param preMethod the pre method
	*/
	public void removeBymultiplePreMethod(String preMethod);

	/**
	* Returns the number of system logs where preMethod = &#63;.
	*
	* @param preMethod the pre method
	* @return the number of matching system logs
	*/
	public int countBymultiplePreMethod(String preMethod);

	/**
	* Returns the number of system logs where preMethod = any &#63;.
	*
	* @param preMethods the pre methods
	* @return the number of matching system logs
	*/
	public int countBymultiplePreMethod(String[] preMethods);

	/**
	* Returns all the system logs where method = &#63;.
	*
	* @param method the method
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String method);

	/**
	* Returns a range of all the system logs where method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String method,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String method,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where method = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String method,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleMethod_First(String method,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleMethod_First(String method,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleMethod_Last(String method,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where method = &#63;.
	*
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleMethod_Last(String method,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where method = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param method the method
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBymultipleMethod_PrevAndNext(long logId,
		String method,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns all the system logs where method = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param methods the methods
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String[] methods);

	/**
	* Returns a range of all the system logs where method = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param methods the methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String[] methods,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where method = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param methods the methods
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String[] methods,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where method = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param method the method
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleMethod(String[] methods,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs where method = &#63; from the database.
	*
	* @param method the method
	*/
	public void removeBymultipleMethod(String method);

	/**
	* Returns the number of system logs where method = &#63;.
	*
	* @param method the method
	* @return the number of matching system logs
	*/
	public int countBymultipleMethod(String method);

	/**
	* Returns the number of system logs where method = any &#63;.
	*
	* @param methods the methods
	* @return the number of matching system logs
	*/
	public int countBymultipleMethod(String[] methods);

	/**
	* Returns all the system logs where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(String threadId);

	/**
	* Returns a range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(String threadId,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(String threadId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(String threadId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleThreadId_First(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleThreadId_First(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBymultipleThreadId_Last(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBymultipleThreadId_Last(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where threadId = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBymultipleThreadId_PrevAndNext(long logId,
		String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns all the system logs where threadId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadIds the thread IDs
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(String[] threadIds);

	/**
	* Returns a range of all the system logs where threadId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadIds the thread IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(
		String[] threadIds, int start, int end);

	/**
	* Returns an ordered range of all the system logs where threadId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadIds the thread IDs
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(
		String[] threadIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBymultipleThreadId(
		String[] threadIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs where threadId = &#63; from the database.
	*
	* @param threadId the thread ID
	*/
	public void removeBymultipleThreadId(String threadId);

	/**
	* Returns the number of system logs where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @return the number of matching system logs
	*/
	public int countBymultipleThreadId(String threadId);

	/**
	* Returns the number of system logs where threadId = any &#63;.
	*
	* @param threadIds the thread IDs
	* @return the number of matching system logs
	*/
	public int countBymultipleThreadId(String[] threadIds);

	/**
	* Returns all the system logs where message LIKE &#63;.
	*
	* @param message the message
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBylikeMessage(String message);

	/**
	* Returns a range of all the system logs where message LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param message the message
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBylikeMessage(String message,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where message LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param message the message
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBylikeMessage(String message,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where message LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param message the message
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBylikeMessage(String message,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBylikeMessage_First(String message,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBylikeMessage_First(String message,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBylikeMessage_Last(String message,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where message LIKE &#63;.
	*
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBylikeMessage_Last(String message,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where message LIKE &#63;.
	*
	* @param logId the primary key of the current system log
	* @param message the message
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBylikeMessage_PrevAndNext(long logId,
		String message,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where message LIKE &#63; from the database.
	*
	* @param message the message
	*/
	public void removeBylikeMessage(String message);

	/**
	* Returns the number of system logs where message LIKE &#63;.
	*
	* @param message the message
	* @return the number of matching system logs
	*/
	public int countBylikeMessage(String message);

	/**
	* Returns all the system logs where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findBythreadId(String threadId);

	/**
	* Returns a range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findBythreadId(String threadId, int start,
		int end);

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBythreadId(String threadId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where threadId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param threadId the thread ID
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findBythreadId(String threadId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBythreadId_First(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBythreadId_First(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findBythreadId_Last(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchBythreadId_Last(String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where threadId = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param threadId the thread ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findBythreadId_PrevAndNext(long logId, String threadId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where threadId = &#63; from the database.
	*
	* @param threadId the thread ID
	*/
	public void removeBythreadId(String threadId);

	/**
	* Returns the number of system logs where threadId = &#63;.
	*
	* @param threadId the thread ID
	* @return the number of matching system logs
	*/
	public int countBythreadId(String threadId);

	/**
	* Caches the system log in the entity cache if it is enabled.
	*
	* @param systemLog the system log
	*/
	public void cacheResult(SystemLog systemLog);

	/**
	* Caches the system logs in the entity cache if it is enabled.
	*
	* @param systemLogs the system logs
	*/
	public void cacheResult(java.util.List<SystemLog> systemLogs);

	/**
	* Creates a new system log with the primary key. Does not add the system log to the database.
	*
	* @param logId the primary key for the new system log
	* @return the new system log
	*/
	public SystemLog create(long logId);

	/**
	* Removes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the system log
	* @return the system log that was removed
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog remove(long logId) throws NoSuchSystemLogException;

	public SystemLog updateImpl(SystemLog systemLog);

	/**
	* Returns the system log with the primary key or throws a {@link NoSuchSystemLogException} if it could not be found.
	*
	* @param logId the primary key of the system log
	* @return the system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog findByPrimaryKey(long logId)
		throws NoSuchSystemLogException;

	/**
	* Returns the system log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param logId the primary key of the system log
	* @return the system log, or <code>null</code> if a system log with the primary key could not be found
	*/
	public SystemLog fetchByPrimaryKey(long logId);

	@Override
	public java.util.Map<java.io.Serializable, SystemLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the system logs.
	*
	* @return the system logs
	*/
	public java.util.List<SystemLog> findAll();

	/**
	* Returns a range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of system logs
	*/
	public java.util.List<SystemLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of system logs
	*/
	public java.util.List<SystemLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of system logs
	*/
	public java.util.List<SystemLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the system logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of system logs.
	*
	* @return the number of system logs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}