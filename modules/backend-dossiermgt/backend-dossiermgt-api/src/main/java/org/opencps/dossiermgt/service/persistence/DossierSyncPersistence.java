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

import org.opencps.dossiermgt.exception.NoSuchDossierSyncException;
import org.opencps.dossiermgt.model.DossierSync;

/**
 * The persistence interface for the dossier sync service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierSyncPersistenceImpl
 * @see DossierSyncUtil
 * @generated
 */
@ProviderType
public interface DossierSyncPersistence extends BasePersistence<DossierSync> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierSyncUtil} to access the dossier sync persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dossier syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier syncs
	*/
	public java.util.List<DossierSync> findByUuid(String uuid);

	/**
	* Returns a range of all the dossier syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dossier syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the first dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the last dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the last dossier sync in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where uuid = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public DossierSync[] findByUuid_PrevAndNext(long DossierSyncId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Removes all the dossier syncs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dossier syncs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier syncs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dossier sync where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierSyncException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByUUID_G(String uuid, long groupId)
		throws NoSuchDossierSyncException;

	/**
	* Returns the dossier sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dossier sync where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dossier sync where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier sync that was removed
	*/
	public DossierSync removeByUUID_G(String uuid, long groupId)
		throws NoSuchDossierSyncException;

	/**
	* Returns the number of dossier syncs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier syncs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dossier syncs where state = &#63;.
	*
	* @param state the state
	* @return the matching dossier syncs
	*/
	public java.util.List<DossierSync> findByST(int state);

	/**
	* Returns a range of all the dossier syncs where state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param state the state
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByST(int state, int start, int end);

	/**
	* Returns an ordered range of all the dossier syncs where state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param state the state
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByST(int state, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs where state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param state the state
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByST(int state, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByST_First(int state,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the first dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByST_First(int state,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the last dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByST_Last(int state,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the last dossier sync in the ordered set where state = &#63;.
	*
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByST_Last(int state,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where state = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public DossierSync[] findByST_PrevAndNext(long DossierSyncId, int state,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Removes all the dossier syncs where state = &#63; from the database.
	*
	* @param state the state
	*/
	public void removeByST(int state);

	/**
	* Returns the number of dossier syncs where state = &#63;.
	*
	* @param state the state
	* @return the number of matching dossier syncs
	*/
	public int countByST(int state);

	/**
	* Returns all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @return the matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType);

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType, int start, int end);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int infoType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByDRID_IT_First(long groupId, String dossierRefUid,
		int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByDRID_IT_First(long groupId, String dossierRefUid,
		int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByDRID_IT_Last(long groupId, String dossierRefUid,
		int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByDRID_IT_Last(long groupId, String dossierRefUid,
		int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public DossierSync[] findByDRID_IT_PrevAndNext(long DossierSyncId,
		long groupId, String dossierRefUid, int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @return the matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes);

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes, int start, int end);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByDRID_IT(long groupId,
		String dossierRefUid, int[] infoTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	*/
	public void removeByDRID_IT(long groupId, String dossierRefUid, int infoType);

	/**
	* Returns the number of dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoType the info type
	* @return the number of matching dossier syncs
	*/
	public int countByDRID_IT(long groupId, String dossierRefUid, int infoType);

	/**
	* Returns the number of dossier syncs where groupId = &#63; and dossierRefUid = &#63; and infoType = any &#63;.
	*
	* @param groupId the group ID
	* @param dossierRefUid the dossier ref uid
	* @param infoTypes the info types
	* @return the number of matching dossier syncs
	*/
	public int countByDRID_IT(long groupId, String dossierRefUid,
		int[] infoTypes);

	/**
	* Returns all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @return the matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType);

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType, int start, int end);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int infoType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByG_AC_ST_IT_First(long groupId, String actionCode,
		int syncType, int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the first dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByG_AC_ST_IT_First(long groupId, String actionCode,
		int syncType, int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync
	* @throws NoSuchDossierSyncException if a matching dossier sync could not be found
	*/
	public DossierSync findByG_AC_ST_IT_Last(long groupId, String actionCode,
		int syncType, int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns the last dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier sync, or <code>null</code> if a matching dossier sync could not be found
	*/
	public DossierSync fetchByG_AC_ST_IT_Last(long groupId, String actionCode,
		int syncType, int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns the dossier syncs before and after the current dossier sync in the ordered set where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param DossierSyncId the primary key of the current dossier sync
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public DossierSync[] findByG_AC_ST_IT_PrevAndNext(long DossierSyncId,
		long groupId, String actionCode, int syncType, int infoType,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator)
		throws NoSuchDossierSyncException;

	/**
	* Returns all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @return the matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes);

	/**
	* Returns a range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes, int start, int end);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier syncs
	*/
	public java.util.List<DossierSync> findByG_AC_ST_IT(long groupId,
		String actionCode, int syncType, int[] infoTypes, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	*/
	public void removeByG_AC_ST_IT(long groupId, String actionCode,
		int syncType, int infoType);

	/**
	* Returns the number of dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoType the info type
	* @return the number of matching dossier syncs
	*/
	public int countByG_AC_ST_IT(long groupId, String actionCode, int syncType,
		int infoType);

	/**
	* Returns the number of dossier syncs where groupId = &#63; and actionCode = &#63; and syncType = &#63; and infoType = any &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param syncType the sync type
	* @param infoTypes the info types
	* @return the number of matching dossier syncs
	*/
	public int countByG_AC_ST_IT(long groupId, String actionCode, int syncType,
		int[] infoTypes);

	/**
	* Caches the dossier sync in the entity cache if it is enabled.
	*
	* @param dossierSync the dossier sync
	*/
	public void cacheResult(DossierSync dossierSync);

	/**
	* Caches the dossier syncs in the entity cache if it is enabled.
	*
	* @param dossierSyncs the dossier syncs
	*/
	public void cacheResult(java.util.List<DossierSync> dossierSyncs);

	/**
	* Creates a new dossier sync with the primary key. Does not add the dossier sync to the database.
	*
	* @param DossierSyncId the primary key for the new dossier sync
	* @return the new dossier sync
	*/
	public DossierSync create(long DossierSyncId);

	/**
	* Removes the dossier sync with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync that was removed
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public DossierSync remove(long DossierSyncId)
		throws NoSuchDossierSyncException;

	public DossierSync updateImpl(DossierSync dossierSync);

	/**
	* Returns the dossier sync with the primary key or throws a {@link NoSuchDossierSyncException} if it could not be found.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync
	* @throws NoSuchDossierSyncException if a dossier sync with the primary key could not be found
	*/
	public DossierSync findByPrimaryKey(long DossierSyncId)
		throws NoSuchDossierSyncException;

	/**
	* Returns the dossier sync with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param DossierSyncId the primary key of the dossier sync
	* @return the dossier sync, or <code>null</code> if a dossier sync with the primary key could not be found
	*/
	public DossierSync fetchByPrimaryKey(long DossierSyncId);

	@Override
	public java.util.Map<java.io.Serializable, DossierSync> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dossier syncs.
	*
	* @return the dossier syncs
	*/
	public java.util.List<DossierSync> findAll();

	/**
	* Returns a range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @return the range of dossier syncs
	*/
	public java.util.List<DossierSync> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier syncs
	*/
	public java.util.List<DossierSync> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator);

	/**
	* Returns an ordered range of all the dossier syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier syncs
	* @param end the upper bound of the range of dossier syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier syncs
	*/
	public java.util.List<DossierSync> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DossierSync> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dossier syncs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dossier syncs.
	*
	* @return the number of dossier syncs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}