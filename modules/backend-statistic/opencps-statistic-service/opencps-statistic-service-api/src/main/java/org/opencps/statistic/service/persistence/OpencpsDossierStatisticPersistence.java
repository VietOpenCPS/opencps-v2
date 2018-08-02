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

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticException;
import org.opencps.statistic.model.OpencpsDossierStatistic;

/**
 * The persistence interface for the opencps dossier statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.statistic.service.persistence.impl.OpencpsDossierStatisticPersistenceImpl
 * @see OpencpsDossierStatisticUtil
 * @generated
 */
@ProviderType
public interface OpencpsDossierStatisticPersistence extends BasePersistence<OpencpsDossierStatistic> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsDossierStatisticUtil} to access the opencps dossier statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the opencps dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid(
		java.lang.String uuid);

	/**
	* Returns a range of all the opencps dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public OpencpsDossierStatistic[] findByUuid_PrevAndNext(
		long dossierStatisticId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Removes all the opencps dossier statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of opencps dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps dossier statistics
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByUUID_G(java.lang.String uuid,
		long groupId);

	/**
	* Returns the opencps dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the opencps dossier statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps dossier statistic that was removed
	*/
	public OpencpsDossierStatistic removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the number of opencps dossier statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps dossier statistics
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the first opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the last opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public OpencpsDossierStatistic[] findByUuid_C_PrevAndNext(
		long dossierStatisticId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Removes all the opencps dossier statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of opencps dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps dossier statistics
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year);

	/**
	* Returns a range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the first opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByG_UID_Y_Last(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the last opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns the opencps dossier statistics before and after the current opencps dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current opencps dossier statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public OpencpsDossierStatistic[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticId, long groupId, long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Removes all the opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public void removeByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the number of opencps dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps dossier statistics
	*/
	public int countByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the opencps dossier statistic where govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic findByM_Y_DM_G(
		java.lang.String govAgencyCode, int month, int year,
		java.lang.String domainCode)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the opencps dossier statistic where govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByM_Y_DM_G(
		java.lang.String govAgencyCode, int month, int year,
		java.lang.String domainCode);

	/**
	* Returns the opencps dossier statistic where govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic, or <code>null</code> if a matching opencps dossier statistic could not be found
	*/
	public OpencpsDossierStatistic fetchByM_Y_DM_G(
		java.lang.String govAgencyCode, int month, int year,
		java.lang.String domainCode, boolean retrieveFromCache);

	/**
	* Removes the opencps dossier statistic where govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63; from the database.
	*
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the opencps dossier statistic that was removed
	*/
	public OpencpsDossierStatistic removeByM_Y_DM_G(
		java.lang.String govAgencyCode, int month, int year,
		java.lang.String domainCode)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the number of opencps dossier statistics where govAgencyCode = &#63; and month = &#63; and year = &#63; and domainCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param month the month
	* @param year the year
	* @param domainCode the domain code
	* @return the number of matching opencps dossier statistics
	*/
	public int countByM_Y_DM_G(java.lang.String govAgencyCode, int month,
		int year, java.lang.String domainCode);

	/**
	* Caches the opencps dossier statistic in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatistic the opencps dossier statistic
	*/
	public void cacheResult(OpencpsDossierStatistic opencpsDossierStatistic);

	/**
	* Caches the opencps dossier statistics in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatistics the opencps dossier statistics
	*/
	public void cacheResult(
		java.util.List<OpencpsDossierStatistic> opencpsDossierStatistics);

	/**
	* Creates a new opencps dossier statistic with the primary key. Does not add the opencps dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new opencps dossier statistic
	* @return the new opencps dossier statistic
	*/
	public OpencpsDossierStatistic create(long dossierStatisticId);

	/**
	* Removes the opencps dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic that was removed
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public OpencpsDossierStatistic remove(long dossierStatisticId)
		throws NoSuchOpencpsDossierStatisticException;

	public OpencpsDossierStatistic updateImpl(
		OpencpsDossierStatistic opencpsDossierStatistic);

	/**
	* Returns the opencps dossier statistic with the primary key or throws a {@link NoSuchOpencpsDossierStatisticException} if it could not be found.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic
	* @throws NoSuchOpencpsDossierStatisticException if a opencps dossier statistic with the primary key could not be found
	*/
	public OpencpsDossierStatistic findByPrimaryKey(long dossierStatisticId)
		throws NoSuchOpencpsDossierStatisticException;

	/**
	* Returns the opencps dossier statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatisticId the primary key of the opencps dossier statistic
	* @return the opencps dossier statistic, or <code>null</code> if a opencps dossier statistic with the primary key could not be found
	*/
	public OpencpsDossierStatistic fetchByPrimaryKey(long dossierStatisticId);

	@Override
	public java.util.Map<java.io.Serializable, OpencpsDossierStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the opencps dossier statistics.
	*
	* @return the opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findAll();

	/**
	* Returns a range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @return the range of opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findAll(int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistics
	* @param end the upper bound of the range of opencps dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps dossier statistics
	*/
	public java.util.List<OpencpsDossierStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps dossier statistics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of opencps dossier statistics.
	*
	* @return the number of opencps dossier statistics
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}