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

import org.opencps.statistic.exception.NoSuchOpencpsDossierStatisticMgtException;
import org.opencps.statistic.model.OpencpsDossierStatisticMgt;

/**
 * The persistence interface for the opencps dossier statistic mgt service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.statistic.service.persistence.impl.OpencpsDossierStatisticMgtPersistenceImpl
 * @see OpencpsDossierStatisticMgtUtil
 * @generated
 */
@ProviderType
public interface OpencpsDossierStatisticMgtPersistence extends BasePersistence<OpencpsDossierStatisticMgt> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsDossierStatisticMgtUtil} to access the opencps dossier statistic mgt persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByUuid(String uuid);

	/**
	* Returns a range of all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where uuid = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByUuid_PrevAndNext(
		long dossierStatisticMgtId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Removes all the opencps dossier statistic mgts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of opencps dossier statistic mgts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the opencps dossier statistic mgt where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps dossier statistic mgt that was removed
	*/
	public OpencpsDossierStatisticMgt removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the number of opencps dossier statistic mgts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y(
		long groupId, long userId, int year);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y(
		long groupId, long userId, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y(
		long groupId, long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y(
		long groupId, long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_First(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_UID_Y_Last(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_Last(long groupId,
		long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticMgtId, long groupId, long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public void removeByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode);

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D(long groupId, int month,
		int year, String govAgencyCode, String domainCode,
		boolean retrieveFromCache);

	/**
	* Removes the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the opencps dossier statistic mgt that was removed
	*/
	public OpencpsDossierStatisticMgt removeByG_M_Y_G_D(long groupId,
		int month, int year, String govAgencyCode, String domainCode)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_M_Y_G_D(long groupId, int month, int year,
		String govAgencyCode, String domainCode);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y(
		long groupId, int month, int year);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y(
		long groupId, int month, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y(
		long groupId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y(
		long groupId, int month, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_NM_Y_First(long groupId,
		int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_First(long groupId,
		int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_NM_Y_Last(long groupId,
		int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_Last(long groupId,
		int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_NM_Y_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	*/
	public void removeByG_NM_Y(long groupId, int month, int year);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_NM_Y(long groupId, int month, int year);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_Y_REPO_First(long groupId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_First(long groupId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_Y_REPO_Last(long groupId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_Last(long groupId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_Y_REPO_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO(
		long groupId, int[] years, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param year the year
	*/
	public void removeByG_Y_REPO(long groupId, int year);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_Y_REPO(long groupId, int year);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = any &#63;.
	*
	* @param groupId the group ID
	* @param years the years
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_Y_REPO(long groupId, int[] years);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_UID_Y_GB(
		long groupId, long userId, int year, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_UID_Y_GB_First(long groupId,
		long userId, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_GB_First(long groupId,
		long userId, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_UID_Y_GB_Last(long groupId,
		long userId, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_UID_Y_GB_Last(long groupId,
		long userId, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_UID_Y_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, long userId, int year,
		int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	*/
	public void removeByG_UID_Y_GB(long groupId, long userId, int year,
		int groupBy);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and userId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_UID_Y_GB(long groupId, long userId, int year,
		int groupBy);

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy);

	/**
	* Returns the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy, boolean retrieveFromCache);

	/**
	* Removes the opencps dossier statistic mgt where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the opencps dossier statistic mgt that was removed
	*/
	public OpencpsDossierStatisticMgt removeByG_M_Y_G_D_GB(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy) throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month = &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_M_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_GB(
		long groupId, int month, int year, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_NM_Y_GB_First(long groupId,
		int month, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_GB_First(long groupId,
		int month, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_NM_Y_GB_Last(long groupId,
		int month, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_GB_Last(long groupId,
		int month, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_NM_Y_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	*/
	public void removeByG_NM_Y_GB(long groupId, int month, int year, int groupBy);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_NM_Y_GB(long groupId, int month, int year, int groupBy);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int year, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_Y_REPO_GB_First(long groupId,
		int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_GB_First(long groupId,
		int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_Y_REPO_GB_Last(long groupId,
		int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_Y_REPO_GB_Last(long groupId,
		int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_Y_REPO_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int year, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_Y_REPO_GB(
		long groupId, int[] years, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	*/
	public void removeByG_Y_REPO_GB(long groupId, int year, int groupBy);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param year the year
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_Y_REPO_GB(long groupId, int year, int groupBy);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and year = any &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param years the years
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_Y_REPO_GB(long groupId, int[] years, int groupBy);

	/**
	* Returns all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy);

	/**
	* Returns a range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findByG_NM_Y_G_D_GB(
		long groupId, int month, int year, String govAgencyCode,
		String domainCode, int groupBy, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_NM_Y_G_D_GB_First(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the first opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_G_D_GB_First(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt findByG_NM_Y_G_D_GB_Last(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the last opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier statistic mgt, or <code>null</code> if a matching opencps dossier statistic mgt could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByG_NM_Y_G_D_GB_Last(long groupId,
		int month, int year, String govAgencyCode, String domainCode,
		int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns the opencps dossier statistic mgts before and after the current opencps dossier statistic mgt in the ordered set where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param dossierStatisticMgtId the primary key of the current opencps dossier statistic mgt
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt[] findByG_NM_Y_G_D_GB_PrevAndNext(
		long dossierStatisticMgtId, long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Removes all the opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63; from the database.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	*/
	public void removeByG_NM_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy);

	/**
	* Returns the number of opencps dossier statistic mgts where groupId = &#63; and month &ne; &#63; and year = &#63; and govAgencyCode = &#63; and domainCode = &#63; and groupBy = &#63;.
	*
	* @param groupId the group ID
	* @param month the month
	* @param year the year
	* @param govAgencyCode the gov agency code
	* @param domainCode the domain code
	* @param groupBy the group by
	* @return the number of matching opencps dossier statistic mgts
	*/
	public int countByG_NM_Y_G_D_GB(long groupId, int month, int year,
		String govAgencyCode, String domainCode, int groupBy);

	/**
	* Caches the opencps dossier statistic mgt in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatisticMgt the opencps dossier statistic mgt
	*/
	public void cacheResult(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt);

	/**
	* Caches the opencps dossier statistic mgts in the entity cache if it is enabled.
	*
	* @param opencpsDossierStatisticMgts the opencps dossier statistic mgts
	*/
	public void cacheResult(
		java.util.List<OpencpsDossierStatisticMgt> opencpsDossierStatisticMgts);

	/**
	* Creates a new opencps dossier statistic mgt with the primary key. Does not add the opencps dossier statistic mgt to the database.
	*
	* @param dossierStatisticMgtId the primary key for the new opencps dossier statistic mgt
	* @return the new opencps dossier statistic mgt
	*/
	public OpencpsDossierStatisticMgt create(long dossierStatisticMgtId);

	/**
	* Removes the opencps dossier statistic mgt with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt that was removed
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt remove(long dossierStatisticMgtId)
		throws NoSuchOpencpsDossierStatisticMgtException;

	public OpencpsDossierStatisticMgt updateImpl(
		OpencpsDossierStatisticMgt opencpsDossierStatisticMgt);

	/**
	* Returns the opencps dossier statistic mgt with the primary key or throws a {@link NoSuchOpencpsDossierStatisticMgtException} if it could not be found.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt
	* @throws NoSuchOpencpsDossierStatisticMgtException if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt findByPrimaryKey(
		long dossierStatisticMgtId)
		throws NoSuchOpencpsDossierStatisticMgtException;

	/**
	* Returns the opencps dossier statistic mgt with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatisticMgtId the primary key of the opencps dossier statistic mgt
	* @return the opencps dossier statistic mgt, or <code>null</code> if a opencps dossier statistic mgt with the primary key could not be found
	*/
	public OpencpsDossierStatisticMgt fetchByPrimaryKey(
		long dossierStatisticMgtId);

	@Override
	public java.util.Map<java.io.Serializable, OpencpsDossierStatisticMgt> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the opencps dossier statistic mgts.
	*
	* @return the opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findAll();

	/**
	* Returns a range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @return the range of opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findAll(int start, int end);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossier statistic mgts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierStatisticMgtModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossier statistic mgts
	* @param end the upper bound of the range of opencps dossier statistic mgts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps dossier statistic mgts
	*/
	public java.util.List<OpencpsDossierStatisticMgt> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossierStatisticMgt> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps dossier statistic mgts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of opencps dossier statistic mgts.
	*
	* @return the number of opencps dossier statistic mgts
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}