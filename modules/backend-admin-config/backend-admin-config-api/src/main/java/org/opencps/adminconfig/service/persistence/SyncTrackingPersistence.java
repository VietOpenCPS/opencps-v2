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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.adminconfig.exception.NoSuchSyncTrackingException;
import org.opencps.adminconfig.model.SyncTracking;

import java.util.Date;

/**
 * The persistence interface for the sync tracking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.adminconfig.service.persistence.impl.SyncTrackingPersistenceImpl
 * @see SyncTrackingUtil
 * @generated
 */
@ProviderType
public interface SyncTrackingPersistence extends BasePersistence<SyncTracking> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncTrackingUtil} to access the sync tracking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sync trackings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid(String uuid);

	/**
	* Returns a range of all the sync trackings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where uuid = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByUuid_PrevAndNext(long trackingId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of sync trackings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync trackings
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the sync tracking where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByUUID_G(String uuid, long groupId)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the sync tracking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the sync tracking where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the sync tracking where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the sync tracking that was removed
	*/
	public SyncTracking removeByUUID_G(String uuid, long groupId)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the number of sync trackings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sync trackings
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByUuid_C_PrevAndNext(long trackingId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of sync trackings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sync trackings
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the sync tracking where referenceUid = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param referenceUid the reference uid
	* @return the matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_ReferenceUid(String referenceUid)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the sync tracking where referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param referenceUid the reference uid
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_ReferenceUid(String referenceUid);

	/**
	* Returns the sync tracking where referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_ReferenceUid(String referenceUid,
		boolean retrieveFromCache);

	/**
	* Removes the sync tracking where referenceUid = &#63; from the database.
	*
	* @param referenceUid the reference uid
	* @return the sync tracking that was removed
	*/
	public SyncTracking removeByF_ReferenceUid(String referenceUid)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the number of sync trackings where referenceUid = &#63;.
	*
	* @param referenceUid the reference uid
	* @return the number of matching sync trackings
	*/
	public int countByF_ReferenceUid(String referenceUid);

	/**
	* Returns the sync tracking where dossierNo = &#63; or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param dossierNo the dossier no
	* @return the matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_DossierNo(String dossierNo)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the sync tracking where dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierNo the dossier no
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_DossierNo(String dossierNo);

	/**
	* Returns the sync tracking where dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierNo the dossier no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_DossierNo(String dossierNo,
		boolean retrieveFromCache);

	/**
	* Removes the sync tracking where dossierNo = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @return the sync tracking that was removed
	*/
	public SyncTracking removeByF_DossierNo(String dossierNo)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the number of sync trackings where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @return the number of matching sync trackings
	*/
	public int countByF_DossierNo(String dossierNo);

	/**
	* Returns all the sync trackings where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol(String protocol);

	/**
	* Returns a range of all the sync trackings where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol(String protocol,
		int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol(String protocol,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol(String protocol,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_Protocol_First(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_Protocol_First(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_Protocol_Last(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63;.
	*
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_Protocol_Last(String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where protocol = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param protocol the protocol
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_Protocol_PrevAndNext(long trackingId,
		String protocol,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where protocol = &#63; from the database.
	*
	* @param protocol the protocol
	*/
	public void removeByF_Protocol(String protocol);

	/**
	* Returns the number of sync trackings where protocol = &#63;.
	*
	* @param protocol the protocol
	* @return the number of matching sync trackings
	*/
	public int countByF_Protocol(String protocol);

	/**
	* Returns all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo);

	/**
	* Returns a range of all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_DossierNo(
		String protocol, String dossierNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_Protocol_DossierNo_First(String protocol,
		String dossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_Protocol_DossierNo_First(String protocol,
		String dossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_Protocol_DossierNo_Last(String protocol,
		String dossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_Protocol_DossierNo_Last(String protocol,
		String dossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where protocol = &#63; and dossierNo = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_Protocol_DossierNo_PrevAndNext(
		long trackingId, String protocol, String dossierNo,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where protocol = &#63; and dossierNo = &#63; from the database.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	*/
	public void removeByF_Protocol_DossierNo(String protocol, String dossierNo);

	/**
	* Returns the number of sync trackings where protocol = &#63; and dossierNo = &#63;.
	*
	* @param protocol the protocol
	* @param dossierNo the dossier no
	* @return the number of matching sync trackings
	*/
	public int countByF_Protocol_DossierNo(String protocol, String dossierNo);

	/**
	* Returns all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode);

	/**
	* Returns a range of all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_Protocol_ServiceCode(
		String protocol, String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_Protocol_ServiceCode_First(String protocol,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_Protocol_ServiceCode_First(String protocol,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_Protocol_ServiceCode_Last(String protocol,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_Protocol_ServiceCode_Last(String protocol,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where protocol = &#63; and serviceCode = &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param protocol the protocol
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_Protocol_ServiceCode_PrevAndNext(
		long trackingId, String protocol, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where protocol = &#63; and serviceCode = &#63; from the database.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	*/
	public void removeByF_Protocol_ServiceCode(String protocol,
		String serviceCode);

	/**
	* Returns the number of sync trackings where protocol = &#63; and serviceCode = &#63;.
	*
	* @param protocol the protocol
	* @param serviceCode the service code
	* @return the number of matching sync trackings
	*/
	public int countByF_Protocol_ServiceCode(String protocol, String serviceCode);

	/**
	* Returns all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_CREATED_BETWEEN(
		Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_CREATED_BETWEEN(
		Date createDate, Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_CREATED_BETWEEN(
		Date createDate, Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_CREATED_BETWEEN(
		Date createDate, Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_CREATED_BETWEEN_First(Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_CREATED_BETWEEN_First(Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_CREATED_BETWEEN_Last(Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_CREATED_BETWEEN_Last(Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_CREATED_BETWEEN_PrevAndNext(long trackingId,
		Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_CREATED_BETWEEN(Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_CREATED_BETWEEN(Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_DOSSIERNO_CREATED_BETWEEN(
		String dossierNo, Date createDate, Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_DOSSIERNO_CREATED_BETWEEN_First(
		String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_DOSSIERNO_CREATED_BETWEEN_First(
		String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_DOSSIERNO_CREATED_BETWEEN_Last(
		String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_DOSSIERNO_CREATED_BETWEEN_Last(
		String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_DOSSIERNO_CREATED_BETWEEN(String dossierNo,
		Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_DOSSIERNO_CREATED_BETWEEN(String dossierNo,
		Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate, int start,
		int end);

	/**
	* Returns an ordered range of all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_REFERENCE_UID_CREATED_BETWEEN(
		String referenceUid, Date createDate, Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_REFERENCE_UID_CREATED_BETWEEN_First(
		String referenceUid, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_REFERENCE_UID_CREATED_BETWEEN_First(
		String referenceUid, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_REFERENCE_UID_CREATED_BETWEEN_Last(
		String referenceUid, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_REFERENCE_UID_CREATED_BETWEEN_Last(
		String referenceUid, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_REFERENCE_UID_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String referenceUid, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_REFERENCE_UID_CREATED_BETWEEN(String referenceUid,
		Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where referenceUid = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param referenceUid the reference uid
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_REFERENCE_UID_CREATED_BETWEEN(String referenceUid,
		Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate, int start,
		int end);

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_CREATED_BETWEEN(
		String serviceCode, Date createDate, Date modifiedDate, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_SERVICECODE_CREATED_BETWEEN_First(
		String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_SERVICECODE_CREATED_BETWEEN_First(
		String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_SERVICECODE_CREATED_BETWEEN_Last(
		String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_SERVICECODE_CREATED_BETWEEN_Last(
		String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_SERVICECODE_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String serviceCode, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_SERVICECODE_CREATED_BETWEEN(String serviceCode,
		Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_SERVICECODE_CREATED_BETWEEN(String serviceCode,
		Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, String serviceCode, String dossierNo,
		Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(
		String api, String serviceCode, String dossierNo, Date createDate,
		Date modifiedDate);

	/**
	* Returns the number of sync trackings where api = &#63; and serviceCode = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_API_SERVICECODE_DOSSIERNO_CREATED_BETWEEN(String api,
		String serviceCode, String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_SERVICECODE_CREATED_BETWEEN(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_SERVICECODE_CREATED_BETWEEN_First(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_SERVICECODE_CREATED_BETWEEN_First(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_SERVICECODE_CREATED_BETWEEN_Last(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_SERVICECODE_CREATED_BETWEEN_Last(
		String api, String serviceCode, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_API_SERVICECODE_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, String serviceCode, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_API_SERVICECODE_CREATED_BETWEEN(String api,
		String serviceCode, Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where api = &#63; and serviceCode = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param serviceCode the service code
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_API_SERVICECODE_CREATED_BETWEEN(String api,
		String serviceCode, Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_DOSSIERNO_CREATED_BETWEEN(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_DOSSIERNO_CREATED_BETWEEN_First(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_DOSSIERNO_CREATED_BETWEEN_Last(String api,
		String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_DOSSIERNO_CREATED_BETWEEN_Last(
		String api, String dossierNo, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_API_DOSSIERNO_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, String dossierNo, Date createDate,
		Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_API_DOSSIERNO_CREATED_BETWEEN(String api,
		String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns the number of sync trackings where api = &#63; and dossierNo = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param dossierNo the dossier no
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_API_DOSSIERNO_CREATED_BETWEEN(String api,
		String dossierNo, Date createDate, Date modifiedDate);

	/**
	* Returns all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_CREATED_BETWEEN(
		String api, Date createDate, Date modifiedDate);

	/**
	* Returns a range of all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_CREATED_BETWEEN(
		String api, Date createDate, Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_CREATED_BETWEEN(
		String api, Date createDate, Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sync trackings
	*/
	public java.util.List<SyncTracking> findByF_API_CREATED_BETWEEN(
		String api, Date createDate, Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_CREATED_BETWEEN_First(String api,
		Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the first sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_CREATED_BETWEEN_First(String api,
		Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking
	* @throws NoSuchSyncTrackingException if a matching sync tracking could not be found
	*/
	public SyncTracking findByF_API_CREATED_BETWEEN_Last(String api,
		Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the last sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public SyncTracking fetchByF_API_CREATED_BETWEEN_Last(String api,
		Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns the sync trackings before and after the current sync tracking in the ordered set where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param trackingId the primary key of the current sync tracking
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking[] findByF_API_CREATED_BETWEEN_PrevAndNext(
		long trackingId, String api, Date createDate, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator)
		throws NoSuchSyncTrackingException;

	/**
	* Removes all the sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	*/
	public void removeByF_API_CREATED_BETWEEN(String api, Date createDate,
		Date modifiedDate);

	/**
	* Returns the number of sync trackings where api = &#63; and createDate &ge; &#63; and modifiedDate &le; &#63;.
	*
	* @param api the api
	* @param createDate the create date
	* @param modifiedDate the modified date
	* @return the number of matching sync trackings
	*/
	public int countByF_API_CREATED_BETWEEN(String api, Date createDate,
		Date modifiedDate);

	/**
	* Caches the sync tracking in the entity cache if it is enabled.
	*
	* @param syncTracking the sync tracking
	*/
	public void cacheResult(SyncTracking syncTracking);

	/**
	* Caches the sync trackings in the entity cache if it is enabled.
	*
	* @param syncTrackings the sync trackings
	*/
	public void cacheResult(java.util.List<SyncTracking> syncTrackings);

	/**
	* Creates a new sync tracking with the primary key. Does not add the sync tracking to the database.
	*
	* @param trackingId the primary key for the new sync tracking
	* @return the new sync tracking
	*/
	public SyncTracking create(long trackingId);

	/**
	* Removes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking that was removed
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking remove(long trackingId)
		throws NoSuchSyncTrackingException;

	public SyncTracking updateImpl(SyncTracking syncTracking);

	/**
	* Returns the sync tracking with the primary key or throws a {@link NoSuchSyncTrackingException} if it could not be found.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking
	* @throws NoSuchSyncTrackingException if a sync tracking with the primary key could not be found
	*/
	public SyncTracking findByPrimaryKey(long trackingId)
		throws NoSuchSyncTrackingException;

	/**
	* Returns the sync tracking with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking, or <code>null</code> if a sync tracking with the primary key could not be found
	*/
	public SyncTracking fetchByPrimaryKey(long trackingId);

	@Override
	public java.util.Map<java.io.Serializable, SyncTracking> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sync trackings.
	*
	* @return the sync trackings
	*/
	public java.util.List<SyncTracking> findAll();

	/**
	* Returns a range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of sync trackings
	*/
	public java.util.List<SyncTracking> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync trackings
	*/
	public java.util.List<SyncTracking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator);

	/**
	* Returns an ordered range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync trackings
	*/
	public java.util.List<SyncTracking> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncTracking> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sync trackings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sync trackings.
	*
	* @return the number of sync trackings
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}