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

import org.opencps.statistic.exception.NoSuchOpencpsVotingStatisticException;
import org.opencps.statistic.model.OpencpsVotingStatistic;

/**
 * The persistence interface for the opencps voting statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.statistic.service.persistence.impl.OpencpsVotingStatisticPersistenceImpl
 * @see OpencpsVotingStatisticUtil
 * @generated
 */
@ProviderType
public interface OpencpsVotingStatisticPersistence extends BasePersistence<OpencpsVotingStatistic> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsVotingStatisticUtil} to access the opencps voting statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the opencps voting statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid(String uuid);

	/**
	* Returns a range of all the opencps voting statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where uuid = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic[] findByUuid_PrevAndNext(
		long votingStatisticId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Removes all the opencps voting statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of opencps voting statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps voting statistics
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the opencps voting statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the opencps voting statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps voting statistic that was removed
	*/
	public OpencpsVotingStatistic removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the number of opencps voting statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps voting statistics
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the first opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the last opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic[] findByUuid_C_PrevAndNext(
		long votingStatisticId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Removes all the opencps voting statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of opencps voting statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps voting statistics
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year);

	/**
	* Returns a range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByG_UID_Y_Last(long groupId, long userId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic[] findByG_UID_Y_PrevAndNext(
		long votingStatisticId, long groupId, long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Removes all the opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public void removeByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public int countByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String domainCode);

	/**
	* Returns the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache);

	/**
	* Removes the opencps voting statistic where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the opencps voting statistic that was removed
	*/
	public OpencpsVotingStatistic removeByM_Y_DM_G(long groupId, int month,
		int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the number of matching opencps voting statistics
	*/
	public int countByM_Y_DM_G(long groupId, int month, int year,
		String govAgencyCode, String domainCode);

	/**
	* Returns all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year);

	/**
	* Returns a range of all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_D_M_Y(long groupId,
		String domainCode, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByG_D_M_Y_First(long groupId,
		String domainCode, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByG_D_M_Y_Last(long groupId,
		String domainCode, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic[] findByG_D_M_Y_PrevAndNext(
		long votingStatisticId, long groupId, String domainCode, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Removes all the opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	*/
	public void removeByG_D_M_Y(long groupId, String domainCode, int month,
		int year);

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and domainCode = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param domainCode the domain code
	* @param month the month
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public int countByG_D_M_Y(long groupId, String domainCode, int month,
		int year);

	/**
	* Returns all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year);

	/**
	* Returns a range of all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByG_M_Y(long groupId,
		int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByG_M_Y_First(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the first opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByG_M_Y_First(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByG_M_Y_Last(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the last opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByG_M_Y_Last(long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic[] findByG_M_Y_PrevAndNext(
		long votingStatisticId, long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Removes all the opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public void removeByG_M_Y(long groupId, int month, int year);

	/**
	* Returns the number of opencps voting statistics where groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public int countByG_M_Y(long groupId, int month, int year);

	/**
	* Returns all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year);

	/**
	* Returns a range of all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findByCID_GID_Y(
		long companyId, long groupId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the first opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByCID_GID_Y_First(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the last opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic findByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the last opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps voting statistic, or <code>null</code> if a matching opencps voting statistic could not be found
	*/
	public OpencpsVotingStatistic fetchByCID_GID_Y_Last(long companyId,
		long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns the opencps voting statistics before and after the current opencps voting statistic in the ordered set where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param votingStatisticId the primary key of the current opencps voting statistic
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic[] findByCID_GID_Y_PrevAndNext(
		long votingStatisticId, long companyId, long groupId, int month,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Removes all the opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public void removeByCID_GID_Y(long companyId, long groupId, int month,
		int year);

	/**
	* Returns the number of opencps voting statistics where companyId = &#63; and groupId = &#63; and month = &#63; and year = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps voting statistics
	*/
	public int countByCID_GID_Y(long companyId, long groupId, int month,
		int year);

	/**
	* Caches the opencps voting statistic in the entity cache if it is enabled.
	*
	* @param opencpsVotingStatistic the opencps voting statistic
	*/
	public void cacheResult(OpencpsVotingStatistic opencpsVotingStatistic);

	/**
	* Caches the opencps voting statistics in the entity cache if it is enabled.
	*
	* @param opencpsVotingStatistics the opencps voting statistics
	*/
	public void cacheResult(
		java.util.List<OpencpsVotingStatistic> opencpsVotingStatistics);

	/**
	* Creates a new opencps voting statistic with the primary key. Does not add the opencps voting statistic to the database.
	*
	* @param votingStatisticId the primary key for the new opencps voting statistic
	* @return the new opencps voting statistic
	*/
	public OpencpsVotingStatistic create(long votingStatisticId);

	/**
	* Removes the opencps voting statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic that was removed
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic remove(long votingStatisticId)
		throws NoSuchOpencpsVotingStatisticException;

	public OpencpsVotingStatistic updateImpl(
		OpencpsVotingStatistic opencpsVotingStatistic);

	/**
	* Returns the opencps voting statistic with the primary key or throws a {@link NoSuchOpencpsVotingStatisticException} if it could not be found.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic
	* @throws NoSuchOpencpsVotingStatisticException if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic findByPrimaryKey(long votingStatisticId)
		throws NoSuchOpencpsVotingStatisticException;

	/**
	* Returns the opencps voting statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param votingStatisticId the primary key of the opencps voting statistic
	* @return the opencps voting statistic, or <code>null</code> if a opencps voting statistic with the primary key could not be found
	*/
	public OpencpsVotingStatistic fetchByPrimaryKey(long votingStatisticId);

	@Override
	public java.util.Map<java.io.Serializable, OpencpsVotingStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the opencps voting statistics.
	*
	* @return the opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findAll();

	/**
	* Returns a range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @return the range of opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findAll(int start, int end);

	/**
	* Returns an ordered range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps voting statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsVotingStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps voting statistics
	* @param end the upper bound of the range of opencps voting statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps voting statistics
	*/
	public java.util.List<OpencpsVotingStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsVotingStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps voting statistics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of opencps voting statistics.
	*
	* @return the number of opencps voting statistics
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}