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

package backend.systemlogmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import backend.systemlogmgt.exception.NoSuchSystemLogException;

import backend.systemlogmgt.model.SystemLog;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the system log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see backend.systemlogmgt.service.persistence.impl.SystemLogPersistenceImpl
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
	* Returns all the system logs where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateGreater(Date createDate);

	/**
	* Returns a range of all the system logs where createDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateGreater(Date createDate,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where createDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateGreater(Date createDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where createDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateGreater(Date createDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByCreateDateGreater_First(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByCreateDateGreater_First(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByCreateDateGreater_Last(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByCreateDateGreater_Last(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where createDate &ge; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByCreateDateGreater_PrevAndNext(long logId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where createDate &ge; &#63; from the database.
	*
	* @param createDate the create date
	*/
	public void removeByCreateDateGreater(Date createDate);

	/**
	* Returns the number of system logs where createDate &ge; &#63;.
	*
	* @param createDate the create date
	* @return the number of matching system logs
	*/
	public int countByCreateDateGreater(Date createDate);

	/**
	* Returns all the system logs where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateLesser(Date createDate);

	/**
	* Returns a range of all the system logs where createDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateLesser(Date createDate,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where createDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateLesser(Date createDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where createDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByCreateDateLesser(Date createDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByCreateDateLesser_First(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByCreateDateLesser_First(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByCreateDateLesser_Last(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByCreateDateLesser_Last(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where createDate &le; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByCreateDateLesser_PrevAndNext(long logId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where createDate &le; &#63; from the database.
	*
	* @param createDate the create date
	*/
	public void removeByCreateDateLesser(Date createDate);

	/**
	* Returns the number of system logs where createDate &le; &#63;.
	*
	* @param createDate the create date
	* @return the number of matching system logs
	*/
	public int countByCreateDateLesser(Date createDate);

	/**
	* Returns the system log where logId = &#63; or throws a {@link NoSuchSystemLogException} if it could not be found.
	*
	* @param logId the log ID
	* @return the matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLogId(long logId) throws NoSuchSystemLogException;

	/**
	* Returns the system log where logId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param logId the log ID
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLogId(long logId);

	/**
	* Returns the system log where logId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param logId the log ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLogId(long logId, boolean retrieveFromCache);

	/**
	* Removes the system log where logId = &#63; from the database.
	*
	* @param logId the log ID
	* @return the system log that was removed
	*/
	public SystemLog removeByLogId(long logId) throws NoSuchSystemLogException;

	/**
	* Returns the number of system logs where logId = &#63;.
	*
	* @param logId the log ID
	* @return the number of matching system logs
	*/
	public int countByLogId(long logId);

	/**
	* Returns all the system logs where className = &#63;.
	*
	* @param className the class name
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByClassName(String className);

	/**
	* Returns a range of all the system logs where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByClassName(String className,
		int start, int end);

	/**
	* Returns an ordered range of all the system logs where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByClassName(String className,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByClassName(String className,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByClassName_First(String className,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByClassName_First(String className,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByClassName_Last(String className,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByClassName_Last(String className,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where className = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByClassName_PrevAndNext(long logId,
		String className,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where className = &#63; from the database.
	*
	* @param className the class name
	*/
	public void removeByClassName(String className);

	/**
	* Returns the number of system logs where className = &#63;.
	*
	* @param className the class name
	* @return the number of matching system logs
	*/
	public int countByClassName(String className);

	/**
	* Returns all the system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByModuleName(String moduleName);

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
	public java.util.List<SystemLog> findByModuleName(String moduleName,
		int start, int end);

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
	public java.util.List<SystemLog> findByModuleName(String moduleName,
		int start, int end,
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
	public java.util.List<SystemLog> findByModuleName(String moduleName,
		int start, int end,
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
	public SystemLog findByModuleName_First(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByModuleName_First(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByModuleName_Last(String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByModuleName_Last(String moduleName,
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
	public SystemLog[] findByModuleName_PrevAndNext(long logId,
		String moduleName,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where moduleName = &#63; from the database.
	*
	* @param moduleName the module name
	*/
	public void removeByModuleName(String moduleName);

	/**
	* Returns the number of system logs where moduleName = &#63;.
	*
	* @param moduleName the module name
	* @return the number of matching system logs
	*/
	public int countByModuleName(String moduleName);

	/**
	* Returns all the system logs where type = &#63;.
	*
	* @param type the type
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByType(String type);

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
	public java.util.List<SystemLog> findByType(String type, int start, int end);

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
	public java.util.List<SystemLog> findByType(String type, int start,
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
	public java.util.List<SystemLog> findByType(String type, int start,
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
	public SystemLog findByType_First(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByType_First(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByType_Last(String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByType_Last(String type,
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
	public SystemLog[] findByType_PrevAndNext(long logId, String type,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where type = &#63; from the database.
	*
	* @param type the type
	*/
	public void removeByType(String type);

	/**
	* Returns the number of system logs where type = &#63;.
	*
	* @param type the type
	* @return the number of matching system logs
	*/
	public int countByType(String type);

	/**
	* Returns all the system logs where line = &#63;.
	*
	* @param line the line
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByLine(int line);

	/**
	* Returns a range of all the system logs where line = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByLine(int line, int start, int end);

	/**
	* Returns an ordered range of all the system logs where line = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByLine(int line, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where line = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByLine(int line, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLine_First(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLine_First(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLine_Last(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where line = &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLine_Last(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where line = &#63;.
	*
	* @param logId the primary key of the current system log
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByLine_PrevAndNext(long logId, int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where line = &#63; from the database.
	*
	* @param line the line
	*/
	public void removeByLine(int line);

	/**
	* Returns the number of system logs where line = &#63;.
	*
	* @param line the line
	* @return the number of matching system logs
	*/
	public int countByLine(int line);

	/**
	* Returns all the system logs where line &gt; &#63;.
	*
	* @param line the line
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByLineGreater(int line);

	/**
	* Returns a range of all the system logs where line &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByLineGreater(int line, int start,
		int end);

	/**
	* Returns an ordered range of all the system logs where line &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByLineGreater(int line, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where line &gt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByLineGreater(int line, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLineGreater_First(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLineGreater_First(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLineGreater_Last(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where line &gt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLineGreater_Last(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where line &gt; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByLineGreater_PrevAndNext(long logId, int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where line &gt; &#63; from the database.
	*
	* @param line the line
	*/
	public void removeByLineGreater(int line);

	/**
	* Returns the number of system logs where line &gt; &#63;.
	*
	* @param line the line
	* @return the number of matching system logs
	*/
	public int countByLineGreater(int line);

	/**
	* Returns all the system logs where line &lt; &#63;.
	*
	* @param line the line
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByLineLesser(int line);

	/**
	* Returns a range of all the system logs where line &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of matching system logs
	*/
	public java.util.List<SystemLog> findByLineLesser(int line, int start,
		int end);

	/**
	* Returns an ordered range of all the system logs where line &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByLineLesser(int line, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns an ordered range of all the system logs where line &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param line the line
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching system logs
	*/
	public java.util.List<SystemLog> findByLineLesser(int line, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLineLesser_First(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLineLesser_First(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByLineLesser_Last(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where line &lt; &#63;.
	*
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByLineLesser_Last(int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the system logs before and after the current system log in the ordered set where line &lt; &#63;.
	*
	* @param logId the primary key of the current system log
	* @param line the line
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next system log
	* @throws NoSuchSystemLogException if a system log with the primary key could not be found
	*/
	public SystemLog[] findByLineLesser_PrevAndNext(long logId, int line,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where line &lt; &#63; from the database.
	*
	* @param line the line
	*/
	public void removeByLineLesser(int line);

	/**
	* Returns the number of system logs where line &lt; &#63;.
	*
	* @param line the line
	* @return the number of matching system logs
	*/
	public int countByLineLesser(int line);

	/**
	* Returns all the system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching system logs
	*/
	public java.util.List<SystemLog> findByGroupId(long groupId);

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
	public java.util.List<SystemLog> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<SystemLog> findByGroupId(long groupId, int start,
		int end,
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
	public java.util.List<SystemLog> findByGroupId(long groupId, int start,
		int end,
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
	public SystemLog findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the first system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator);

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log
	* @throws NoSuchSystemLogException if a matching system log could not be found
	*/
	public SystemLog findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Returns the last system log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public SystemLog fetchByGroupId_Last(long groupId,
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
	public SystemLog[] findByGroupId_PrevAndNext(long logId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<SystemLog> orderByComparator)
		throws NoSuchSystemLogException;

	/**
	* Removes all the system logs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of system logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching system logs
	*/
	public int countByGroupId(long groupId);

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