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

import org.opencps.dossiermgt.exception.NoSuchDossierActionSyncException;
import org.opencps.dossiermgt.model.DossierActionSync;

/**
 * The persistence interface for the dossier action sync service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierActionSyncPersistenceImpl
 * @see DossierActionSyncUtil
 * @generated
 */
@ProviderType
public interface DossierActionSyncPersistence extends BasePersistence<DossierActionSync> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierActionSyncUtil} to access the dossier action sync persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier action syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier action syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns the dossier action syncs before and after the current dossier action sync in the ordered set where uuid = &#63;.
	*
	* @param dossierActionSyncId the primary key of the current dossier action sync
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public DossierActionSync[] findByUuid_PrevAndNext(
		long dossierActionSyncId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Removes all the dossier action syncs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier action syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier action syncs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier action sync where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierActionSyncException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the dossier action sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier action sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier action sync where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier action sync that was removed
	*/
	public DossierActionSync removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the number of dossier action syncs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier action syncs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the first dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the last dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns the dossier action syncs before and after the current dossier action sync in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierActionSyncId the primary key of the current dossier action sync
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public DossierActionSync[] findByUuid_C_PrevAndNext(
		long dossierActionSyncId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Removes all the dossier action syncs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dossier action syncs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier action syncs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dossier action syncs where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByDID(long dossierActionId);

	/**
	* Returns a range of all the dossier action syncs where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByDID(long dossierActionId,
		int start, int end);

	/**
	* Returns an ordered range of all the dossier action syncs where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByDID(long dossierActionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action syncs where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action syncs
	*/
	public java.util.List<DossierActionSync> findByDID(long dossierActionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByDID_First(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the first dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByDID_First(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns the last dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync
	* @throws NoSuchDossierActionSyncException if a matching dossier action sync could not be found
	*/
	public DossierActionSync findByDID_Last(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the last dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public DossierActionSync fetchByDID_Last(long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns the dossier action syncs before and after the current dossier action sync in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionSyncId the primary key of the current dossier action sync
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public DossierActionSync[] findByDID_PrevAndNext(long dossierActionSyncId,
		long dossierActionId,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator)
		throws NoSuchDossierActionSyncException;

	/**
	* Removes all the dossier action syncs where dossierActionId = &#63; from the database.
	*
	* @param dossierActionId the dossier action ID
	*/
	public void removeByDID(long dossierActionId);

	/**
	* Returns the number of dossier action syncs where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the number of matching dossier action syncs
	*/
	public int countByDID(long dossierActionId);

	/**
	* Caches the dossier action sync in the entity cache if it is enabled.
	*
	* @param dossierActionSync the dossier action sync
	*/
	public void cacheResult(DossierActionSync dossierActionSync);

	/**
	* Caches the dossier action syncs in the entity cache if it is enabled.
	*
	* @param dossierActionSyncs the dossier action syncs
	*/
	public void cacheResult(
		java.util.List<DossierActionSync> dossierActionSyncs);

	/**
	* Creates a new dossier action sync with the primary key. Does not add the dossier action sync to the database.
	*
	* @param dossierActionSyncId the primary key for the new dossier action sync
	* @return the new dossier action sync
	*/
	public DossierActionSync create(long dossierActionSyncId);

	/**
	* Removes the dossier action sync with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync that was removed
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public DossierActionSync remove(long dossierActionSyncId)
		throws NoSuchDossierActionSyncException;

	public DossierActionSync updateImpl(DossierActionSync dossierActionSync);

	/**
	* Returns the dossier action sync with the primary key or throws a {@link NoSuchDossierActionSyncException} if it could not be found.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync
	* @throws NoSuchDossierActionSyncException if a dossier action sync with the primary key could not be found
	*/
	public DossierActionSync findByPrimaryKey(long dossierActionSyncId)
		throws NoSuchDossierActionSyncException;

	/**
	* Returns the dossier action sync with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync, or <code>null</code> if a dossier action sync with the primary key could not be found
	*/
	public DossierActionSync fetchByPrimaryKey(long dossierActionSyncId);

	@Override
	public java.util.Map<java.io.Serializable, DossierActionSync> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier action syncs.
	*
	* @return the dossier action syncs
	*/
	public java.util.List<DossierActionSync> findAll();

	/**
	* Returns a range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of dossier action syncs
	*/
	public java.util.List<DossierActionSync> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier action syncs
	*/
	public java.util.List<DossierActionSync> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier action syncs
	*/
	public java.util.List<DossierActionSync> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierActionSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier action syncs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier action syncs.
	*
	* @return the number of dossier action syncs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}