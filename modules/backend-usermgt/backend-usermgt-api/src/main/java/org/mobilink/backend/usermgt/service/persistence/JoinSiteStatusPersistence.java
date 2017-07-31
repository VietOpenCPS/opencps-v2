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

package org.mobilink.backend.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.mobilink.backend.usermgt.exception.NoSuchJoinSiteStatusException;
import org.mobilink.backend.usermgt.model.JoinSiteStatus;

/**
 * The persistence interface for the join site status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see org.mobilink.backend.usermgt.service.persistence.impl.JoinSiteStatusPersistenceImpl
 * @see JoinSiteStatusUtil
 * @generated
 */
@ProviderType
public interface JoinSiteStatusPersistence extends BasePersistence<JoinSiteStatus> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JoinSiteStatusUtil} to access the join site status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the join site statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the join site statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public JoinSiteStatus findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the first join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns the last join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public JoinSiteStatus findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the last join site status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns the join site statuses before and after the current join site status in the ordered set where uuid = &#63;.
	*
	* @param JoinSiteStatusId the primary key of the current join site status
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next join site status
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public JoinSiteStatus[] findByUuid_PrevAndNext(long JoinSiteStatusId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator)
		throws NoSuchJoinSiteStatusException;

	/**
	* Removes all the join site statuses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of join site statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching join site statuses
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the join site status where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJoinSiteStatusException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public JoinSiteStatus findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the join site status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the join site status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the join site status where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the join site status that was removed
	*/
	public JoinSiteStatus removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the number of join site statuses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching join site statuses
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns an ordered range of all the join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching join site statuses
	*/
	public java.util.List<JoinSiteStatus> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public JoinSiteStatus findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the first join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns the last join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public JoinSiteStatus findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the last join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns the join site statuses before and after the current join site status in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param JoinSiteStatusId the primary key of the current join site status
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next join site status
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public JoinSiteStatus[] findByUuid_C_PrevAndNext(long JoinSiteStatusId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator)
		throws NoSuchJoinSiteStatusException;

	/**
	* Removes all the join site statuses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of join site statuses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching join site statuses
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the join site status where employeeId = &#63; and joinSiteGroupId = &#63; or throws a {@link NoSuchJoinSiteStatusException} if it could not be found.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the matching join site status
	* @throws NoSuchJoinSiteStatusException if a matching join site status could not be found
	*/
	public JoinSiteStatus findByF_employeeId_joinSiteGroupId(long employeeId,
		long joinSiteGroupId) throws NoSuchJoinSiteStatusException;

	/**
	* Returns the join site status where employeeId = &#63; and joinSiteGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByF_employeeId_joinSiteGroupId(long employeeId,
		long joinSiteGroupId);

	/**
	* Returns the join site status where employeeId = &#63; and joinSiteGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public JoinSiteStatus fetchByF_employeeId_joinSiteGroupId(long employeeId,
		long joinSiteGroupId, boolean retrieveFromCache);

	/**
	* Removes the join site status where employeeId = &#63; and joinSiteGroupId = &#63; from the database.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the join site status that was removed
	*/
	public JoinSiteStatus removeByF_employeeId_joinSiteGroupId(
		long employeeId, long joinSiteGroupId)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the number of join site statuses where employeeId = &#63; and joinSiteGroupId = &#63;.
	*
	* @param employeeId the employee ID
	* @param joinSiteGroupId the join site group ID
	* @return the number of matching join site statuses
	*/
	public int countByF_employeeId_joinSiteGroupId(long employeeId,
		long joinSiteGroupId);

	/**
	* Caches the join site status in the entity cache if it is enabled.
	*
	* @param joinSiteStatus the join site status
	*/
	public void cacheResult(JoinSiteStatus joinSiteStatus);

	/**
	* Caches the join site statuses in the entity cache if it is enabled.
	*
	* @param joinSiteStatuses the join site statuses
	*/
	public void cacheResult(java.util.List<JoinSiteStatus> joinSiteStatuses);

	/**
	* Creates a new join site status with the primary key. Does not add the join site status to the database.
	*
	* @param JoinSiteStatusId the primary key for the new join site status
	* @return the new join site status
	*/
	public JoinSiteStatus create(long JoinSiteStatusId);

	/**
	* Removes the join site status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status that was removed
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public JoinSiteStatus remove(long JoinSiteStatusId)
		throws NoSuchJoinSiteStatusException;

	public JoinSiteStatus updateImpl(JoinSiteStatus joinSiteStatus);

	/**
	* Returns the join site status with the primary key or throws a {@link NoSuchJoinSiteStatusException} if it could not be found.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status
	* @throws NoSuchJoinSiteStatusException if a join site status with the primary key could not be found
	*/
	public JoinSiteStatus findByPrimaryKey(long JoinSiteStatusId)
		throws NoSuchJoinSiteStatusException;

	/**
	* Returns the join site status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status, or <code>null</code> if a join site status with the primary key could not be found
	*/
	public JoinSiteStatus fetchByPrimaryKey(long JoinSiteStatusId);

	@Override
	public java.util.Map<java.io.Serializable, JoinSiteStatus> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the join site statuses.
	*
	* @return the join site statuses
	*/
	public java.util.List<JoinSiteStatus> findAll();

	/**
	* Returns a range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of join site statuses
	*/
	public java.util.List<JoinSiteStatus> findAll(int start, int end);

	/**
	* Returns an ordered range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of join site statuses
	*/
	public java.util.List<JoinSiteStatus> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator);

	/**
	* Returns an ordered range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of join site statuses
	*/
	public java.util.List<JoinSiteStatus> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JoinSiteStatus> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the join site statuses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of join site statuses.
	*
	* @return the number of join site statuses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}