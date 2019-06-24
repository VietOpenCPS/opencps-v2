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

import org.opencps.dossiermgt.exception.NoSuchDossierException;
import org.opencps.dossiermgt.model.Dossier;

import java.util.Date;

/**
 * The persistence interface for the dossier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierPersistenceImpl
 * @see DossierUtil
 * @generated
 */
@ProviderType
public interface DossierPersistence extends BasePersistence<Dossier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierUtil} to access the dossier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByUuid(String uuid);

	/**
	* Returns a range of all the dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where uuid = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByUuid_PrevAndNext(long dossierId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossiers
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierException;

	/**
	* Returns the dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier that was removed
	*/
	public Dossier removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierException;

	/**
	* Returns the number of dossiers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByUuid_C_PrevAndNext(long dossierId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossiers
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_UID(long groupId, long userId);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_UID(long groupId, long userId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_UID(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_UID(long groupId, long userId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_UID_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_UID_First(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_UID_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_UID_Last(long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_UID_PrevAndNext(long dossierId, long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public void removeByG_UID(long groupId, long userId);

	/**
	* Returns the number of dossiers where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching dossiers
	*/
	public int countByG_UID(long groupId, long userId);

	/**
	* Returns the dossier where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_REF(long groupId, String referenceUid)
		throws NoSuchDossierException;

	/**
	* Returns the dossier where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_REF(long groupId, String referenceUid);

	/**
	* Returns the dossier where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_REF(long groupId, String referenceUid,
		boolean retrieveFromCache);

	/**
	* Removes the dossier where groupId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the dossier that was removed
	*/
	public Dossier removeByG_REF(long groupId, String referenceUid)
		throws NoSuchDossierException;

	/**
	* Returns the number of dossiers where groupId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the number of matching dossiers
	*/
	public int countByG_REF(long groupId, String referenceUid);

	/**
	* Returns all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_SC(long groupId, String serviceCode);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_SC_First(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_SC_First(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_SC_Last(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_SC_Last(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_SC_PrevAndNext(long dossierId, long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public void removeByG_SC(long groupId, String serviceCode);

	/**
	* Returns the number of dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public int countByG_SC(long groupId, String serviceCode);

	/**
	* Returns all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_GAC(long groupId, String serviceCode);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_GAC(long groupId,
		String serviceCode, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_GAC(long groupId,
		String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_GAC(long groupId,
		String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_GAC_First(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_GAC_First(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_GAC_Last(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_GAC_Last(long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_GAC_PrevAndNext(long dossierId, long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public void removeByG_GAC(long groupId, String serviceCode);

	/**
	* Returns the number of dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public int countByG_GAC(long groupId, String serviceCode);

	/**
	* Returns all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_DTN_First(long groupId, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_DTN_First(long groupId, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_DTN_Last(long groupId, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_DTN_Last(long groupId, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_DTN_PrevAndNext(long dossierId, long groupId,
		String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and dossierTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	*/
	public void removeByG_DTN(long groupId, String dossierTemplateNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossiers
	*/
	public int countByG_DTN(long groupId, String dossierTemplateNo);

	/**
	* Returns all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_DID(long groupId, long dossierId);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_DID(long groupId, long dossierId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_DID(long groupId, long dossierId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_DID(long groupId, long dossierId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_DID_First(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_DID_First(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_DID_Last(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_DID_Last(long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Removes all the dossiers where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public void removeByG_DID(long groupId, long dossierId);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching dossiers
	*/
	public int countByG_DID(long groupId, long dossierId);

	/**
	* Returns all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus);

	/**
	* Returns a range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByNOTO_DS_First(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByNOTO_DS_First(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByNOTO_DS_Last(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByNOTO_DS_Last(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByNOTO_DS_PrevAndNext(long dossierId, int originality,
		String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus);

	/**
	* Returns a range of all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers where originality &ne; &#63; and dossierStatus = &#63; from the database.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	*/
	public void removeByNOTO_DS(int originality, String dossierStatus);

	/**
	* Returns the number of dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public int countByNOTO_DS(int originality, String dossierStatus);

	/**
	* Returns the number of dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public int countByNOTO_DS(int[] originalities, String dossierStatus);

	/**
	* Returns all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus);

	/**
	* Returns a range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByF_OG_DS_First(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByF_OG_DS_First(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByF_OG_DS_Last(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByF_OG_DS_Last(int originality, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByF_OG_DS_PrevAndNext(long dossierId, int originality,
		String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where originality = &#63; and dossierStatus = &#63; from the database.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	*/
	public void removeByF_OG_DS(int originality, String dossierStatus);

	/**
	* Returns the number of dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public int countByF_OG_DS(int originality, String dossierStatus);

	/**
	* Returns all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_C_GAC_SC_DTNO_NOTDS_First(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_C_GAC_SC_DTNO_NOTDS_First(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_C_GAC_SC_DTNO_NOTDS_Last(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_C_GAC_SC_DTNO_NOTDS_Last(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(long dossierId,
		long groupId, long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	*/
	public void removeByG_C_GAC_SC_DTNO_NOTDS(long groupId, long companyId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus);

	/**
	* Returns the number of dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public int countByG_C_GAC_SC_DTNO_NOTDS(long groupId, long companyId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus);

	/**
	* Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) throws NoSuchDossierException;

	/**
	* Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId);

	/**
	* Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId, boolean retrieveFromCache);

	/**
	* Removes the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the dossier that was removed
	*/
	public Dossier removeByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) throws NoSuchDossierException;

	/**
	* Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the number of matching dossiers
	*/
	public int countByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId);

	/**
	* Returns all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTO_DS_SC_GC_First(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTO_DS_SC_GC_First(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTO_DS_SC_GC_Last(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTO_DS_SC_GC_Last(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_NOTO_DS_SC_GC_PrevAndNext(long dossierId,
		long groupId, int originality, String dossierStatus,
		String serviceCode, String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	*/
	public void removeByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode);

	/**
	* Returns the number of dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @return the number of matching dossiers
	*/
	public int countByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode);

	/**
	* Returns all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdNo,
		String applicantIdType, String delegateIdNo, int originality);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdNo,
		String applicantIdType, String delegateIdNo, int originality,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdNo,
		String applicantIdType, String delegateIdNo, int originality,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdNo,
		String applicantIdType, String delegateIdNo, int originality,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByGID_GC_SC_DTN_DS_APP_DELEGATE_First(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByGID_GC_SC_DTN_DS_APP_DELEGATE_First(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByGID_GC_SC_DTN_DS_APP_DELEGATE_Last(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByGID_GC_SC_DTN_DS_APP_DELEGATE_Last(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByGID_GC_SC_DTN_DS_APP_DELEGATE_PrevAndNext(
		long dossierId, long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdNo,
		String applicantIdType, String delegateIdNo, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String[] dossierStatuses,
		String applicantIdNo, String applicantIdType, String delegateIdNo,
		int originality);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String[] dossierStatuses,
		String applicantIdNo, String applicantIdType, String delegateIdNo,
		int originality, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String[] dossierStatuses,
		String applicantIdNo, String applicantIdType, String delegateIdNo,
		int originality, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_GC_SC_DTN_DS_APP_DELEGATE(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String[] dossierStatuses,
		String applicantIdNo, String applicantIdType, String delegateIdNo,
		int originality, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	*/
	public void removeByGID_GC_SC_DTN_DS_APP_DELEGATE(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality);

	/**
	* Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public int countByGID_GC_SC_DTN_DS_APP_DELEGATE(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality);

	/**
	* Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdNo = &#63; and applicantIdType = &#63; and delegateIdNo = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdNo the applicant ID no
	* @param applicantIdType the applicant ID type
	* @param delegateIdNo the delegate ID no
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public int countByGID_GC_SC_DTN_DS_APP_DELEGATE(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdNo, String applicantIdType,
		String delegateIdNo, int originality);

	/**
	* Returns all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_O_DID(long groupId,
		long originDossierId);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_O_DID(long groupId,
		long originDossierId, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_O_DID(long groupId,
		long originDossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_O_DID(long groupId,
		long originDossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_O_DID_First(long groupId, long originDossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_O_DID_First(long groupId, long originDossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_O_DID_Last(long groupId, long originDossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_O_DID_Last(long groupId, long originDossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_O_DID_PrevAndNext(long dossierId, long groupId,
		long originDossierId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and originDossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	*/
	public void removeByG_O_DID(long groupId, long originDossierId);

	/**
	* Returns the number of dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @return the number of matching dossiers
	*/
	public int countByG_O_DID(long groupId, long originDossierId);

	/**
	* Returns all the dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG(long groupId);

	/**
	* Returns a range of all the dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG(long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG(long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_PrevAndNext(long dossierId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG(long groupId);

	/**
	* Returns the number of dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public int countByG(long groupId);

	/**
	* Returns all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo);

	/**
	* Returns a range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByDN_AN_First(String dossierNo, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByDN_AN_First(String dossierNo, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByDN_AN_Last(String dossierNo, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByDN_AN_Last(String dossierNo, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByDN_AN_PrevAndNext(long dossierId, String dossierNo,
		String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where dossierNo = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	*/
	public void removeByDN_AN(String dossierNo, String applicantIdNo);

	/**
	* Returns the number of dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @return the number of matching dossiers
	*/
	public int countByDN_AN(String dossierNo, String applicantIdNo);

	/**
	* Returns all the dossiers where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByVIAPOSTAL(int viaPostal);

	/**
	* Returns a range of all the dossiers where viaPostal = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param viaPostal the via postal
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByVIAPOSTAL(int viaPostal, int start,
		int end);

	/**
	* Returns an ordered range of all the dossiers where viaPostal = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param viaPostal the via postal
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByVIAPOSTAL(int viaPostal, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where viaPostal = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param viaPostal the via postal
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByVIAPOSTAL(int viaPostal, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByVIAPOSTAL_First(int viaPostal,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByVIAPOSTAL_First(int viaPostal,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByVIAPOSTAL_Last(int viaPostal,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByVIAPOSTAL_Last(int viaPostal,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where viaPostal = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByVIAPOSTAL_PrevAndNext(long dossierId, int viaPostal,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where viaPostal = &#63; from the database.
	*
	* @param viaPostal the via postal
	*/
	public void removeByVIAPOSTAL(int viaPostal);

	/**
	* Returns the number of dossiers where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @return the number of matching dossiers
	*/
	public int countByVIAPOSTAL(int viaPostal);

	/**
	* Returns all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality);

	/**
	* Returns a range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByU_G_GAC_SC_DTNO_DAI_O_First(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByU_G_GAC_SC_DTNO_DAI_O_First(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByU_G_GAC_SC_DTNO_DAI_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByU_G_GAC_SC_DTNO_DAI_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(long dossierId,
		long userId, long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	*/
	public void removeByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality);

	/**
	* Returns the number of dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public int countByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality);

	/**
	* Returns the dossier where groupId = &#63; and dossierNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_DN(long groupId, String dossierNo)
		throws NoSuchDossierException;

	/**
	* Returns the dossier where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_DN(long groupId, String dossierNo);

	/**
	* Returns the dossier where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_DN(long groupId, String dossierNo,
		boolean retrieveFromCache);

	/**
	* Removes the dossier where groupId = &#63; and dossierNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the dossier that was removed
	*/
	public Dossier removeByG_DN(long groupId, String dossierNo)
		throws NoSuchDossierException;

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the number of matching dossiers
	*/
	public int countByG_DN(long groupId, String dossierNo);

	/**
	* Returns all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_AN(long groupId, String applicantIdNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_AN(long groupId,
		String applicantIdNo, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_AN(long groupId,
		String applicantIdNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_AN(long groupId,
		String applicantIdNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_AN_First(long groupId, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_AN_First(long groupId, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_AN_Last(long groupId, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_AN_Last(long groupId, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_AN_PrevAndNext(long dossierId, long groupId,
		String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	*/
	public void removeByG_AN(long groupId, String applicantIdNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the number of matching dossiers
	*/
	public int countByG_AN(long groupId, String applicantIdNo);

	/**
	* Returns all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByF_GID_AN_DS_First(long groupId, String applicantIdNo,
		String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByF_GID_AN_DS_First(long groupId, String applicantIdNo,
		String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByF_GID_AN_DS_Last(long groupId, String applicantIdNo,
		String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByF_GID_AN_DS_Last(long groupId, String applicantIdNo,
		String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByF_GID_AN_DS_PrevAndNext(long dossierId,
		long groupId, String applicantIdNo, String dossierStatus,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	*/
	public void removeByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus);

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public int countByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus);

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId)
		throws NoSuchDossierException;

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId);

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the dossier that was removed
	*/
	public Dossier removeByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId)
		throws NoSuchDossierException;

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the number of matching dossiers
	*/
	public int countByG_AN_SC_GAC_DTNO_ODID(long groupId, String applicantIdNo,
		String serviceCode, String govAgencyCode, String dossierTemplateNo,
		long originDossierId);

	/**
	* Returns all the dossiers where originality = &#63;.
	*
	* @param originality the originality
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByO(int originality);

	/**
	* Returns a range of all the dossiers where originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByO(int originality, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByO(int originality, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByO(int originality, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByO_First(int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByO_First(int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByO_Last(int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByO_Last(int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByO_PrevAndNext(long dossierId, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where originality = &#63; from the database.
	*
	* @param originality the originality
	*/
	public void removeByO(int originality);

	/**
	* Returns the number of dossiers where originality = &#63;.
	*
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public int countByO(int originality);

	/**
	* Returns all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByGID_PNO(long groupId, String processNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_PNO(long groupId,
		String processNo, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_PNO(long groupId,
		String processNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_PNO(long groupId,
		String processNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByGID_PNO_First(long groupId, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByGID_PNO_First(long groupId, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByGID_PNO_Last(long groupId, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByGID_PNO_Last(long groupId, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByGID_PNO_PrevAndNext(long dossierId, long groupId,
		String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and processNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processNo the process no
	*/
	public void removeByGID_PNO(long groupId, String processNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @return the number of matching dossiers
	*/
	public int countByGID_PNO(long groupId, String processNo);

	/**
	* Returns all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate);

	/**
	* Returns a range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByNOT_ST_GT_MD_First(String dossierStatus,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByNOT_ST_GT_MD_First(String dossierStatus,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByNOT_ST_GT_MD_Last(String dossierStatus,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByNOT_ST_GT_MD_Last(String dossierStatus,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByNOT_ST_GT_MD_PrevAndNext(long dossierId,
		String dossierStatus, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(
		String[] dossierStatuses, Date modifiedDate);

	/**
	* Returns a range of all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(
		String[] dossierStatuses, Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(
		String[] dossierStatuses, Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByNOT_ST_GT_MD(
		String[] dossierStatuses, Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63; from the database.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	*/
	public void removeByNOT_ST_GT_MD(String dossierStatus, Date modifiedDate);

	/**
	* Returns the number of dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @return the number of matching dossiers
	*/
	public int countByNOT_ST_GT_MD(String dossierStatus, Date modifiedDate);

	/**
	* Returns the number of dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @return the number of matching dossiers
	*/
	public int countByNOT_ST_GT_MD(String[] dossierStatuses, Date modifiedDate);

	/**
	* Returns all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo, int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByGID_ORI_NO_First(long groupId, String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByGID_ORI_NO_First(long groupId,
		String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByGID_ORI_NO_Last(long groupId, String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByGID_ORI_NO_Last(long groupId, String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByGID_ORI_NO_PrevAndNext(long dossierId, long groupId,
		String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where groupId = &#63; and originDossierNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	*/
	public void removeByGID_ORI_NO(long groupId, String originDossierNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @return the number of matching dossiers
	*/
	public int countByGID_ORI_NO(long groupId, String originDossierNo);

	/**
	* Returns all the dossiers where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByORIGIN_NO(String originDossierNo);

	/**
	* Returns a range of all the dossiers where originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByORIGIN_NO(String originDossierNo,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByORIGIN_NO(String originDossierNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByORIGIN_NO(String originDossierNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByORIGIN_NO_First(String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByORIGIN_NO_First(String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByORIGIN_NO_Last(String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByORIGIN_NO_Last(String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByORIGIN_NO_PrevAndNext(long dossierId,
		String originDossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where originDossierNo = &#63; from the database.
	*
	* @param originDossierNo the origin dossier no
	*/
	public void removeByORIGIN_NO(String originDossierNo);

	/**
	* Returns the number of dossiers where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @return the number of matching dossiers
	*/
	public int countByORIGIN_NO(String originDossierNo);

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode, int start,
		int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTS_O_SC_First(long groupId, String dossierStatus,
		int originality, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTS_O_SC_First(long groupId, String dossierStatus,
		int originality, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTS_O_SC_Last(long groupId, String dossierStatus,
		int originality, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTS_O_SC_Last(long groupId, String dossierStatus,
		int originality, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_NOTS_O_SC_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	*/
	public void removeByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public int countByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public int countByG_NOTS_O_SC(long groupId, String[] dossierStatuses,
		int originality, String serviceCode);

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTS_O_DTN_First(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTS_O_DTN_First(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTS_O_DTN_Last(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTS_O_DTN_Last(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_NOTS_O_DTN_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality,
		String dossierTemplateNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	*/
	public void removeByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossiers
	*/
	public int countByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossiers
	*/
	public int countByG_NOTS_O_DTN(long groupId, String[] dossierStatuses,
		int originality, String dossierTemplateNo);

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo, int start,
		int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTS_O_PN_First(long groupId, String dossierStatus,
		int originality, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTS_O_PN_First(long groupId, String dossierStatus,
		int originality, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByG_NOTS_O_PN_Last(long groupId, String dossierStatus,
		int originality, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByG_NOTS_O_PN_Last(long groupId, String dossierStatus,
		int originality, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByG_NOTS_O_PN_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality, String processNo,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo);

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	*/
	public void removeByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @return the number of matching dossiers
	*/
	public int countByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo);

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @return the number of matching dossiers
	*/
	public int countByG_NOTS_O_PN(long groupId, String[] dossierStatuses,
		int originality, String processNo);

	/**
	* Returns all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @return the matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality);

	/**
	* Returns a range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		int start, int end);

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public java.util.List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByU_G_GAC_SC_DTNO_DS_O_First(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByU_G_GAC_SC_DTNO_DS_O_First(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public Dossier findByU_G_GAC_SC_DTNO_DS_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public Dossier fetchByU_G_GAC_SC_DTNO_DS_O_Last(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier[] findByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(long dossierId,
		long userId, long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator)
		throws NoSuchDossierException;

	/**
	* Removes all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	*/
	public void removeByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality);

	/**
	* Returns the number of dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public int countByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality);

	/**
	* Caches the dossier in the entity cache if it is enabled.
	*
	* @param dossier the dossier
	*/
	public void cacheResult(Dossier dossier);

	/**
	* Caches the dossiers in the entity cache if it is enabled.
	*
	* @param dossiers the dossiers
	*/
	public void cacheResult(java.util.List<Dossier> dossiers);

	/**
	* Creates a new dossier with the primary key. Does not add the dossier to the database.
	*
	* @param dossierId the primary key for the new dossier
	* @return the new dossier
	*/
	public Dossier create(long dossierId);

	/**
	* Removes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier that was removed
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier remove(long dossierId) throws NoSuchDossierException;

	public Dossier updateImpl(Dossier dossier);

	/**
	* Returns the dossier with the primary key or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public Dossier findByPrimaryKey(long dossierId)
		throws NoSuchDossierException;

	/**
	* Returns the dossier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier, or <code>null</code> if a dossier with the primary key could not be found
	*/
	public Dossier fetchByPrimaryKey(long dossierId);

	@Override
	public java.util.Map<java.io.Serializable, Dossier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossiers.
	*
	* @return the dossiers
	*/
	public java.util.List<Dossier> findAll();

	/**
	* Returns a range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of dossiers
	*/
	public java.util.List<Dossier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossiers
	*/
	public java.util.List<Dossier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns an ordered range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossiers
	*/
	public java.util.List<Dossier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossiers.
	*
	* @return the number of dossiers
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}