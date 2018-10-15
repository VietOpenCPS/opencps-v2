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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchRegistrationLogException;
import org.opencps.dossiermgt.model.RegistrationLog;

/**
 * The persistence interface for the registration log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationLogPersistenceImpl
 * @see RegistrationLogUtil
 * @generated
 */
@ProviderType
public interface RegistrationLogPersistence extends BasePersistence<RegistrationLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RegistrationLogUtil} to access the registration log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the registration logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid(String uuid);

	/**
	* Returns a range of all the registration logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the first registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns the last registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the last registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns the registration logs before and after the current registration log in the ordered set where uuid = &#63;.
	*
	* @param registrationLogId the primary key of the current registration log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public RegistrationLog[] findByUuid_PrevAndNext(long registrationLogId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Removes all the registration logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of registration logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registration logs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the registration log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the registration log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the registration log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the registration log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration log that was removed
	*/
	public RegistrationLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the number of registration logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registration logs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the first registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns the last registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the last registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns the registration logs before and after the current registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param registrationLogId the primary key of the current registration log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public RegistrationLog[] findByUuid_C_PrevAndNext(long registrationLogId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Removes all the registration logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of registration logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching registration logs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration logs
	*/
	public java.util.List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId);

	/**
	* Returns a range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end);

	/**
	* Returns an ordered range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns an ordered range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration logs
	*/
	public java.util.List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByG_REGID_First(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the first registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByG_REGID_First(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns the last registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public RegistrationLog findByG_REGID_Last(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the last registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public RegistrationLog fetchByG_REGID_Last(long groupId,
		long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns the registration logs before and after the current registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param registrationLogId the primary key of the current registration log
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public RegistrationLog[] findByG_REGID_PrevAndNext(long registrationLogId,
		long groupId, long registrationId,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator)
		throws NoSuchRegistrationLogException;

	/**
	* Removes all the registration logs where groupId = &#63; and registrationId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	*/
	public void removeByG_REGID(long groupId, long registrationId);

	/**
	* Returns the number of registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the number of matching registration logs
	*/
	public int countByG_REGID(long groupId, long registrationId);

	/**
	* Caches the registration log in the entity cache if it is enabled.
	*
	* @param registrationLog the registration log
	*/
	public void cacheResult(RegistrationLog registrationLog);

	/**
	* Caches the registration logs in the entity cache if it is enabled.
	*
	* @param registrationLogs the registration logs
	*/
	public void cacheResult(java.util.List<RegistrationLog> registrationLogs);

	/**
	* Creates a new registration log with the primary key. Does not add the registration log to the database.
	*
	* @param registrationLogId the primary key for the new registration log
	* @return the new registration log
	*/
	public RegistrationLog create(long registrationLogId);

	/**
	* Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log that was removed
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public RegistrationLog remove(long registrationLogId)
		throws NoSuchRegistrationLogException;

	public RegistrationLog updateImpl(RegistrationLog registrationLog);

	/**
	* Returns the registration log with the primary key or throws a {@link NoSuchRegistrationLogException} if it could not be found.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public RegistrationLog findByPrimaryKey(long registrationLogId)
		throws NoSuchRegistrationLogException;

	/**
	* Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	*/
	public RegistrationLog fetchByPrimaryKey(long registrationLogId);

	@Override
	public java.util.Map<java.io.Serializable, RegistrationLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the registration logs.
	*
	* @return the registration logs
	*/
	public java.util.List<RegistrationLog> findAll();

	/**
	* Returns a range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of registration logs
	*/
	public java.util.List<RegistrationLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of registration logs
	*/
	public java.util.List<RegistrationLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator);

	/**
	* Returns an ordered range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of registration logs
	*/
	public java.util.List<RegistrationLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the registration logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of registration logs.
	*
	* @return the number of registration logs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}