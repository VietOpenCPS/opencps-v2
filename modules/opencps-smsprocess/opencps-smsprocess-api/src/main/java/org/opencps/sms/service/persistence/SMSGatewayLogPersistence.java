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

package org.opencps.sms.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.sms.exception.NoSuchGatewayLogException;
import org.opencps.sms.model.SMSGatewayLog;

/**
 * The persistence interface for the sms gateway log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoa
 * @see org.opencps.sms.service.persistence.impl.SMSGatewayLogPersistenceImpl
 * @see SMSGatewayLogUtil
 * @generated
 */
@ProviderType
public interface SMSGatewayLogPersistence extends BasePersistence<SMSGatewayLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SMSGatewayLogUtil} to access the sms gateway log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sms gateway logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid(String uuid);

	/**
	* Returns a range of all the sms gateway logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException;

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException;

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns the sms gateway logs before and after the current sms gateway log in the ordered set where uuid = &#63;.
	*
	* @param smsId the primary key of the current sms gateway log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms gateway log
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public SMSGatewayLog[] findByUuid_PrevAndNext(long smsId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException;

	/**
	* Removes all the sms gateway logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of sms gateway logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sms gateway logs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the sms gateway log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchGatewayLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog findByUUID_G(String uuid, long groupId)
		throws NoSuchGatewayLogException;

	/**
	* Returns the sms gateway log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the sms gateway log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the sms gateway log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sms gateway log that was removed
	*/
	public SMSGatewayLog removeByUUID_G(String uuid, long groupId)
		throws NoSuchGatewayLogException;

	/**
	* Returns the number of sms gateway logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sms gateway logs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns an ordered range of all the sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException;

	/**
	* Returns the first sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log
	* @throws NoSuchGatewayLogException if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException;

	/**
	* Returns the last sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sms gateway log, or <code>null</code> if a matching sms gateway log could not be found
	*/
	public SMSGatewayLog fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns the sms gateway logs before and after the current sms gateway log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param smsId the primary key of the current sms gateway log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sms gateway log
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public SMSGatewayLog[] findByUuid_C_PrevAndNext(long smsId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator)
		throws NoSuchGatewayLogException;

	/**
	* Removes all the sms gateway logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of sms gateway logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sms gateway logs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the sms gateway log in the entity cache if it is enabled.
	*
	* @param smsGatewayLog the sms gateway log
	*/
	public void cacheResult(SMSGatewayLog smsGatewayLog);

	/**
	* Caches the sms gateway logs in the entity cache if it is enabled.
	*
	* @param smsGatewayLogs the sms gateway logs
	*/
	public void cacheResult(java.util.List<SMSGatewayLog> smsGatewayLogs);

	/**
	* Creates a new sms gateway log with the primary key. Does not add the sms gateway log to the database.
	*
	* @param smsId the primary key for the new sms gateway log
	* @return the new sms gateway log
	*/
	public SMSGatewayLog create(long smsId);

	/**
	* Removes the sms gateway log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log that was removed
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public SMSGatewayLog remove(long smsId) throws NoSuchGatewayLogException;

	public SMSGatewayLog updateImpl(SMSGatewayLog smsGatewayLog);

	/**
	* Returns the sms gateway log with the primary key or throws a {@link NoSuchGatewayLogException} if it could not be found.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log
	* @throws NoSuchGatewayLogException if a sms gateway log with the primary key could not be found
	*/
	public SMSGatewayLog findByPrimaryKey(long smsId)
		throws NoSuchGatewayLogException;

	/**
	* Returns the sms gateway log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param smsId the primary key of the sms gateway log
	* @return the sms gateway log, or <code>null</code> if a sms gateway log with the primary key could not be found
	*/
	public SMSGatewayLog fetchByPrimaryKey(long smsId);

	@Override
	public java.util.Map<java.io.Serializable, SMSGatewayLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sms gateway logs.
	*
	* @return the sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findAll();

	/**
	* Returns a range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @return the range of sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator);

	/**
	* Returns an ordered range of all the sms gateway logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SMSGatewayLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sms gateway logs
	* @param end the upper bound of the range of sms gateway logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sms gateway logs
	*/
	public java.util.List<SMSGatewayLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SMSGatewayLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sms gateway logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sms gateway logs.
	*
	* @return the number of sms gateway logs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}