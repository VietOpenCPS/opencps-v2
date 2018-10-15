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

import org.opencps.dossiermgt.exception.NoSuchDossierStatisticException;
import org.opencps.dossiermgt.model.DossierStatistic;

/**
 * The persistence interface for the dossier statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierStatisticPersistenceImpl
 * @see DossierStatisticUtil
 * @generated
 */
@ProviderType
public interface DossierStatisticPersistence extends BasePersistence<DossierStatistic> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierStatisticUtil} to access the dossier statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns the dossier statistics before and after the current dossier statistic in the ordered set where uuid = &#63;.
	*
	* @param dossierStatisticId the primary key of the current dossier statistic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public DossierStatistic[] findByUuid_PrevAndNext(long dossierStatisticId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Removes all the dossier statistics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier statistics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier statistics
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier statistic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierStatisticException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier statistic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier statistic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier statistic that was removed
	*/
	public DossierStatistic removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the number of dossier statistics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier statistics
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the first dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the last dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns the dossier statistics before and after the current dossier statistic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierStatisticId the primary key of the current dossier statistic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public DossierStatistic[] findByUuid_C_PrevAndNext(
		long dossierStatisticId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Removes all the dossier statistics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossier statistics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier statistics
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year);

	/**
	* Returns a range of all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end);

	/**
	* Returns an ordered range of all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier statistics
	*/
	public java.util.List<DossierStatistic> findByG_UID_Y(long groupId,
		long userId, int year, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByG_UID_Y_First(long groupId, long userId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the first dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByG_UID_Y_First(long groupId, long userId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns the last dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic
	* @throws NoSuchDossierStatisticException if a matching dossier statistic could not be found
	*/
	public DossierStatistic findByG_UID_Y_Last(long groupId, long userId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the last dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier statistic, or <code>null</code> if a matching dossier statistic could not be found
	*/
	public DossierStatistic fetchByG_UID_Y_Last(long groupId, long userId,
		int year,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns the dossier statistics before and after the current dossier statistic in the ordered set where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param dossierStatisticId the primary key of the current dossier statistic
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public DossierStatistic[] findByG_UID_Y_PrevAndNext(
		long dossierStatisticId, long groupId, long userId, int year,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator)
		throws NoSuchDossierStatisticException;

	/**
	* Removes all the dossier statistics where groupId = &#63; and userId = &#63; and year = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	*/
	public void removeByG_UID_Y(long groupId, long userId, int year);

	/**
	* Returns the number of dossier statistics where groupId = &#63; and userId = &#63; and year = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param year the year
	* @return the number of matching dossier statistics
	*/
	public int countByG_UID_Y(long groupId, long userId, int year);

	/**
	* Caches the dossier statistic in the entity cache if it is enabled.
	*
	* @param dossierStatistic the dossier statistic
	*/
	public void cacheResult(DossierStatistic dossierStatistic);

	/**
	* Caches the dossier statistics in the entity cache if it is enabled.
	*
	* @param dossierStatistics the dossier statistics
	*/
	public void cacheResult(java.util.List<DossierStatistic> dossierStatistics);

	/**
	* Creates a new dossier statistic with the primary key. Does not add the dossier statistic to the database.
	*
	* @param dossierStatisticId the primary key for the new dossier statistic
	* @return the new dossier statistic
	*/
	public DossierStatistic create(long dossierStatisticId);

	/**
	* Removes the dossier statistic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic that was removed
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public DossierStatistic remove(long dossierStatisticId)
		throws NoSuchDossierStatisticException;

	public DossierStatistic updateImpl(DossierStatistic dossierStatistic);

	/**
	* Returns the dossier statistic with the primary key or throws a {@link NoSuchDossierStatisticException} if it could not be found.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic
	* @throws NoSuchDossierStatisticException if a dossier statistic with the primary key could not be found
	*/
	public DossierStatistic findByPrimaryKey(long dossierStatisticId)
		throws NoSuchDossierStatisticException;

	/**
	* Returns the dossier statistic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierStatisticId the primary key of the dossier statistic
	* @return the dossier statistic, or <code>null</code> if a dossier statistic with the primary key could not be found
	*/
	public DossierStatistic fetchByPrimaryKey(long dossierStatisticId);

	@Override
	public java.util.Map<java.io.Serializable, DossierStatistic> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier statistics.
	*
	* @return the dossier statistics
	*/
	public java.util.List<DossierStatistic> findAll();

	/**
	* Returns a range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @return the range of dossier statistics
	*/
	public java.util.List<DossierStatistic> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier statistics
	*/
	public java.util.List<DossierStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator);

	/**
	* Returns an ordered range of all the dossier statistics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierStatisticModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier statistics
	* @param end the upper bound of the range of dossier statistics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier statistics
	*/
	public java.util.List<DossierStatistic> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierStatistic> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier statistics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier statistics.
	*
	* @return the number of dossier statistics
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}