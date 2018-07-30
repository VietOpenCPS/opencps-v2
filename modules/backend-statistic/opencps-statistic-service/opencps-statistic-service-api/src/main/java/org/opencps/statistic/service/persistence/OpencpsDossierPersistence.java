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

import org.opencps.statistic.exception.NoSuchOpencpsDossierException;
import org.opencps.statistic.model.OpencpsDossier;

/**
 * The persistence interface for the opencps dossier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.statistic.service.persistence.impl.OpencpsDossierPersistenceImpl
 * @see OpencpsDossierUtil
 * @generated
 */
@ProviderType
public interface OpencpsDossierPersistence extends BasePersistence<OpencpsDossier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpencpsDossierUtil} to access the opencps dossier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the opencps dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the opencps dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns the opencps dossiers before and after the current opencps dossier in the ordered set where uuid = &#63;.
	*
	* @param dossierId the primary key of the current opencps dossier
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public OpencpsDossier[] findByUuid_PrevAndNext(long dossierId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Removes all the opencps dossiers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of opencps dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching opencps dossiers
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the opencps dossier where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpencpsDossierException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the opencps dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the opencps dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the opencps dossier where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the opencps dossier that was removed
	*/
	public OpencpsDossier removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the number of opencps dossiers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching opencps dossiers
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the first opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the last opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns the opencps dossiers before and after the current opencps dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierId the primary key of the current opencps dossier
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public OpencpsDossier[] findByUuid_C_PrevAndNext(long dossierId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Removes all the opencps dossiers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of opencps dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching opencps dossiers
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the opencps dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByG_(long groupId);

	/**
	* Returns a range of all the opencps dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByG_(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the opencps dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByG_(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findByG_(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByG__First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the first opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByG__First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns the last opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier
	* @throws NoSuchOpencpsDossierException if a matching opencps dossier could not be found
	*/
	public OpencpsDossier findByG__Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the last opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching opencps dossier, or <code>null</code> if a matching opencps dossier could not be found
	*/
	public OpencpsDossier fetchByG__Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns the opencps dossiers before and after the current opencps dossier in the ordered set where groupId = &#63;.
	*
	* @param dossierId the primary key of the current opencps dossier
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public OpencpsDossier[] findByG__PrevAndNext(long dossierId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator)
		throws NoSuchOpencpsDossierException;

	/**
	* Removes all the opencps dossiers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG_(long groupId);

	/**
	* Returns the number of opencps dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching opencps dossiers
	*/
	public int countByG_(long groupId);

	/**
	* Caches the opencps dossier in the entity cache if it is enabled.
	*
	* @param opencpsDossier the opencps dossier
	*/
	public void cacheResult(OpencpsDossier opencpsDossier);

	/**
	* Caches the opencps dossiers in the entity cache if it is enabled.
	*
	* @param opencpsDossiers the opencps dossiers
	*/
	public void cacheResult(java.util.List<OpencpsDossier> opencpsDossiers);

	/**
	* Creates a new opencps dossier with the primary key. Does not add the opencps dossier to the database.
	*
	* @param dossierId the primary key for the new opencps dossier
	* @return the new opencps dossier
	*/
	public OpencpsDossier create(long dossierId);

	/**
	* Removes the opencps dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier that was removed
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public OpencpsDossier remove(long dossierId)
		throws NoSuchOpencpsDossierException;

	public OpencpsDossier updateImpl(OpencpsDossier opencpsDossier);

	/**
	* Returns the opencps dossier with the primary key or throws a {@link NoSuchOpencpsDossierException} if it could not be found.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier
	* @throws NoSuchOpencpsDossierException if a opencps dossier with the primary key could not be found
	*/
	public OpencpsDossier findByPrimaryKey(long dossierId)
		throws NoSuchOpencpsDossierException;

	/**
	* Returns the opencps dossier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierId the primary key of the opencps dossier
	* @return the opencps dossier, or <code>null</code> if a opencps dossier with the primary key could not be found
	*/
	public OpencpsDossier fetchByPrimaryKey(long dossierId);

	@Override
	public java.util.Map<java.io.Serializable, OpencpsDossier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the opencps dossiers.
	*
	* @return the opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findAll();

	/**
	* Returns a range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @return the range of opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator);

	/**
	* Returns an ordered range of all the opencps dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpencpsDossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of opencps dossiers
	* @param end the upper bound of the range of opencps dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of opencps dossiers
	*/
	public java.util.List<OpencpsDossier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpencpsDossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the opencps dossiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of opencps dossiers.
	*
	* @return the number of opencps dossiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}