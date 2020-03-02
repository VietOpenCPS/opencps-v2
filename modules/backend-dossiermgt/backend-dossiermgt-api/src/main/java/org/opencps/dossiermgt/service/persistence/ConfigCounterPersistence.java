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

import org.opencps.dossiermgt.exception.NoSuchConfigCounterException;
import org.opencps.dossiermgt.model.ConfigCounter;

/**
 * The persistence interface for the config counter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ConfigCounterPersistenceImpl
 * @see ConfigCounterUtil
 * @generated
 */
@ProviderType
public interface ConfigCounterPersistence extends BasePersistence<ConfigCounter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ConfigCounterUtil} to access the config counter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the config counters where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid(String uuid);

	/**
	* Returns a range of all the config counters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the config counters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns an ordered range of all the config counters where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Returns the first config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the last config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Returns the last config counter in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the config counters before and after the current config counter in the ordered set where uuid = &#63;.
	*
	* @param configCounterId the primary key of the current config counter
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public ConfigCounter[] findByUuid_PrevAndNext(long configCounterId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Removes all the config counters where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of config counters where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching config counters
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the config counter where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchConfigCounterException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByUUID_G(String uuid, long groupId)
		throws NoSuchConfigCounterException;

	/**
	* Returns the config counter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the config counter where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the config counter where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the config counter that was removed
	*/
	public ConfigCounter removeByUUID_G(String uuid, long groupId)
		throws NoSuchConfigCounterException;

	/**
	* Returns the number of config counters where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching config counters
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns an ordered range of all the config counters where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Returns the first config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the last config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Returns the last config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the config counters before and after the current config counter in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param configCounterId the primary key of the current config counter
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public ConfigCounter[] findByUuid_C_PrevAndNext(long configCounterId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Removes all the config counters where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of config counters where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching config counters
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the config counters where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching config counters
	*/
	public java.util.List<ConfigCounter> findByG_ID(long groupId);

	/**
	* Returns a range of all the config counters where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByG_ID(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the config counters where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByG_ID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns an ordered range of all the config counters where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching config counters
	*/
	public java.util.List<ConfigCounter> findByG_ID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByG_ID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Returns the first config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByG_ID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the last config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByG_ID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Returns the last config counter in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByG_ID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns the config counters before and after the current config counter in the ordered set where groupId = &#63;.
	*
	* @param configCounterId the primary key of the current config counter
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public ConfigCounter[] findByG_ID_PrevAndNext(long configCounterId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator)
		throws NoSuchConfigCounterException;

	/**
	* Removes all the config counters where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG_ID(long groupId);

	/**
	* Returns the number of config counters where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching config counters
	*/
	public int countByG_ID(long groupId);

	/**
	* Returns the config counter where groupId = &#63; and counterCode = &#63; or throws a {@link NoSuchConfigCounterException} if it could not be found.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the matching config counter
	* @throws NoSuchConfigCounterException if a matching config counter could not be found
	*/
	public ConfigCounter findByGID_CODE(long groupId, String counterCode)
		throws NoSuchConfigCounterException;

	/**
	* Returns the config counter where groupId = &#63; and counterCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByGID_CODE(long groupId, String counterCode);

	/**
	* Returns the config counter where groupId = &#63; and counterCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public ConfigCounter fetchByGID_CODE(long groupId, String counterCode,
		boolean retrieveFromCache);

	/**
	* Removes the config counter where groupId = &#63; and counterCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the config counter that was removed
	*/
	public ConfigCounter removeByGID_CODE(long groupId, String counterCode)
		throws NoSuchConfigCounterException;

	/**
	* Returns the number of config counters where groupId = &#63; and counterCode = &#63;.
	*
	* @param groupId the group ID
	* @param counterCode the counter code
	* @return the number of matching config counters
	*/
	public int countByGID_CODE(long groupId, String counterCode);

	/**
	* Caches the config counter in the entity cache if it is enabled.
	*
	* @param configCounter the config counter
	*/
	public void cacheResult(ConfigCounter configCounter);

	/**
	* Caches the config counters in the entity cache if it is enabled.
	*
	* @param configCounters the config counters
	*/
	public void cacheResult(java.util.List<ConfigCounter> configCounters);

	/**
	* Creates a new config counter with the primary key. Does not add the config counter to the database.
	*
	* @param configCounterId the primary key for the new config counter
	* @return the new config counter
	*/
	public ConfigCounter create(long configCounterId);

	/**
	* Removes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter that was removed
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public ConfigCounter remove(long configCounterId)
		throws NoSuchConfigCounterException;

	public ConfigCounter updateImpl(ConfigCounter configCounter);

	/**
	* Returns the config counter with the primary key or throws a {@link NoSuchConfigCounterException} if it could not be found.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter
	* @throws NoSuchConfigCounterException if a config counter with the primary key could not be found
	*/
	public ConfigCounter findByPrimaryKey(long configCounterId)
		throws NoSuchConfigCounterException;

	/**
	* Returns the config counter with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter, or <code>null</code> if a config counter with the primary key could not be found
	*/
	public ConfigCounter fetchByPrimaryKey(long configCounterId);

	@Override
	public java.util.Map<java.io.Serializable, ConfigCounter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the config counters.
	*
	* @return the config counters
	*/
	public java.util.List<ConfigCounter> findAll();

	/**
	* Returns a range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of config counters
	*/
	public java.util.List<ConfigCounter> findAll(int start, int end);

	/**
	* Returns an ordered range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of config counters
	*/
	public java.util.List<ConfigCounter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator);

	/**
	* Returns an ordered range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of config counters
	*/
	public java.util.List<ConfigCounter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ConfigCounter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the config counters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of config counters.
	*
	* @return the number of config counters
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}