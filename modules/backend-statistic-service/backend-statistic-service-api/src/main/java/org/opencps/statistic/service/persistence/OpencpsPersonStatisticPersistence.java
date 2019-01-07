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

package org.opencps.statistic.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.statistic.exception.NoSuchOpencpsPersonStatisticException;
import org.opencps.statistic.model.OpencpsPersonStatistic;

/**
 * The persistence interface for the opencps person statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.statistic.service.persistence.impl.OpencpsPersonStatisticPersistenceImpl
 * @see OpencpsPersonStatisticUtil
 * @generated
 */
@ProviderType
public interface OpencpsPersonStatisticPersistence extends BasePersistence<OpencpsPersonStatistic> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsPersonStatisticUtil} to access the opencps person statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the opencps person statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid(String uuid);

	/**
	* Returns a range of all the opencps person statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where uuid = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic[] findByUuid_PrevAndNext(
		long personStatisticId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Removes all the opencps person statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of opencps person statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps person statistics
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the opencps person statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the opencps person statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps person statistic that was removed
	*/
	public OpencpsPersonStatistic removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the number of opencps person statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps person statistics
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the first opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the last opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic[] findByUuid_C_PrevAndNext(
		long personStatisticId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Removes all the opencps person statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of opencps person statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps person statistics
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year);

	/**
	* Returns a range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByG_UID_Y_Last(long groupId, long userId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic[] findByG_UID_Y_PrevAndNext(
		long personStatisticId, long groupId, long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Removes all the opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public void removeByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public int countByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId);

	/**
	* Returns the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId,
		boolean retrieveFromCache);

	/**
	* Removes the opencps person statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the opencps person statistic that was removed
	*/
	public OpencpsPersonStatistic removeByM_Y_GOV_EMP(long groupId, int month,
		int year, String govAgencyCode, long employeeId)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and employeeId = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param employeeId the employee ID
	* @return the number of matching opencps person statistics
	*/
	public int countByM_Y_GOV_EMP(long groupId, int month, int year,
		String govAgencyCode, long employeeId);

	/**
	* Returns all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year);

	/**
	* Returns a range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_D_M_Y(long groupId,
		long employeeId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByG_D_M_Y_First(long groupId,
		long employeeId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByG_D_M_Y_First(long groupId,
		long employeeId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByG_D_M_Y_Last(long groupId,
		long employeeId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByG_D_M_Y_Last(long groupId,
		long employeeId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic[] findByG_D_M_Y_PrevAndNext(
		long personStatisticId, long groupId, long employeeId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Removes all the opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	*/
	public void removeByG_D_M_Y(long groupId, long employeeId, int month,
		int year);

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and employeeId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param employeeId the employee ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public int countByG_D_M_Y(long groupId, long employeeId, int month, int year);

	/**
	* Returns all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year);

	/**
	* Returns a range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByG_M_Y_First(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the first opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByG_M_Y_First(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByG_M_Y_Last(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the last opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByG_M_Y_Last(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic[] findByG_M_Y_PrevAndNext(
		long personStatisticId, long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Removes all the opencps person statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public void removeByG_M_Y(long groupId, int month, int year);

	/**
	* Returns the number of opencps person statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public int countByG_M_Y(long groupId, int month, int year);

	/**
	* Returns all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year);

	/**
	* Returns a range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the first opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the last opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the last opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps person statistic, or <code>null</code> if a matching opencps person statistic could not be found
	*/
	public OpencpsPersonStatistic fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns the opencps person statistics before and after the current opencps person statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param personStatisticId the primary key of the current opencps person statistic
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic[] findByCID_GID_Y_PrevAndNext(
		long personStatisticId, long companyId, long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Removes all the opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public void removeByCID_GID_Y(long companyId, long groupId, int month,
		int year);

	/**
	* Returns the number of opencps person statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps person statistics
	*/
	public int countByCID_GID_Y(long companyId, long groupId, int month,
		int year);

	/**
	* Caches the opencps person statistic in the entity cache if it is enabled.
	*
	* @param opencpsPersonStatistic the opencps person statistic
	*/
	public void cacheResult(OpencpsPersonStatistic opencpsPersonStatistic);

	/**
	* Caches the opencps person statistics in the entity cache if it is enabled.
	*
	* @param opencpsPersonStatistics the opencps person statistics
	*/
	public void cacheResult(
		java.util.List<OpencpsPersonStatistic> opencpsPersonStatistics);

	/**
	* Creates a new opencps person statistic with the primary key. Does not add the opencps person statistic to the database.
	*
	* @param personStatisticId the primary key for the new opencps person statistic
	* @return the new opencps person statistic
	*/
	public OpencpsPersonStatistic create(long personStatisticId);

	/**
	* Removes the opencps person statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic that was removed
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic remove(long personStatisticId)
		throws NoSuchOpencpsPersonStatisticException;

	public OpencpsPersonStatistic updateImpl(
		OpencpsPersonStatistic opencpsPersonStatistic);

	/**
	* Returns the opencps person statistic with the primary key or throws a {@link NoSuchOpencpsPersonStatisticException} if it could not be found.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic
	* @throws NoSuchOpencpsPersonStatisticException if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic findByPrimaryKey(long personStatisticId)
		throws NoSuchOpencpsPersonStatisticException;

	/**
	* Returns the opencps person statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param personStatisticId the primary key of the opencps person statistic
	* @return the opencps person statistic, or <code>null</code> if a opencps person statistic with the primary key could not be found
	*/
	public OpencpsPersonStatistic fetchByPrimaryKey(long personStatisticId);

	@Override
	public java.util.Map<java.io.Serializable, OpencpsPersonStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the opencps person statistics.
	*
	* @return the opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findAll();

	/**
	* Returns a range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @return the range of opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findAll(int start, int end);

	/**
	* Returns an ordered range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps person statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsPersonStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps person statistics
	* @param end the upper bound of the range of opencps person statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps person statistics
	*/
	public java.util.List<OpencpsPersonStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsPersonStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps person statistics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of opencps person statistics.
	*
	* @return the number of opencps person statistics
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}