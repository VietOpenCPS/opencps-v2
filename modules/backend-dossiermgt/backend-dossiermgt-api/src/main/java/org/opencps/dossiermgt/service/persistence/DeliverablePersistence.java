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

import org.opencps.dossiermgt.exception.NoSuchDeliverableException;
import org.opencps.dossiermgt.model.Deliverable;

/**
 * The persistence interface for the deliverable service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverablePersistenceImpl
 * @see DeliverableUtil
 * @generated
 */
@ProviderType
public interface DeliverablePersistence extends BasePersistence<Deliverable> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliverableUtil} to access the deliverable persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the deliverables where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid(String uuid);

	/**
	* Returns a range of all the deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the deliverables before and after the current deliverable in the ordered set where uuid = &#63;.
	*
	* @param deliverableId the primary key of the current deliverable
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public Deliverable[] findByUuid_PrevAndNext(long deliverableId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Removes all the deliverables where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of deliverables where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverables
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the deliverable where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableException;

	/**
	* Returns the deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable that was removed
	*/
	public Deliverable removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableException;

	/**
	* Returns the number of deliverables where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverables
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverables
	*/
	public java.util.List<Deliverable> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the deliverables before and after the current deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableId the primary key of the current deliverable
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public Deliverable[] findByUuid_C_PrevAndNext(long deliverableId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Removes all the deliverables where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of deliverables where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverables
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the deliverable where deliverableId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableId the deliverable ID
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByDID(long deliverableId)
		throws NoSuchDeliverableException;

	/**
	* Returns the deliverable where deliverableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableId the deliverable ID
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByDID(long deliverableId);

	/**
	* Returns the deliverable where deliverableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableId the deliverable ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByDID(long deliverableId, boolean retrieveFromCache);

	/**
	* Removes the deliverable where deliverableId = &#63; from the database.
	*
	* @param deliverableId the deliverable ID
	* @return the deliverable that was removed
	*/
	public Deliverable removeByDID(long deliverableId)
		throws NoSuchDeliverableException;

	/**
	* Returns the number of deliverables where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @return the number of matching deliverables
	*/
	public int countByDID(long deliverableId);

	/**
	* Returns the deliverable where groupId = &#63; and deliverableId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByG_DID(long groupId, long deliverableId)
		throws NoSuchDeliverableException;

	/**
	* Returns the deliverable where groupId = &#63; and deliverableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByG_DID(long groupId, long deliverableId);

	/**
	* Returns the deliverable where groupId = &#63; and deliverableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByG_DID(long groupId, long deliverableId,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable where groupId = &#63; and deliverableId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the deliverable that was removed
	*/
	public Deliverable removeByG_DID(long groupId, long deliverableId)
		throws NoSuchDeliverableException;

	/**
	* Returns the number of deliverables where groupId = &#63; and deliverableId = &#63;.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the number of matching deliverables
	*/
	public int countByG_DID(long groupId, long deliverableId);

	/**
	* Returns all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @return the matching deliverables
	*/
	public java.util.List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo);

	/**
	* Returns a range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of matching deliverables
	*/
	public java.util.List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end);

	/**
	* Returns an ordered range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverables
	*/
	public java.util.List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns an ordered range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverables
	*/
	public java.util.List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByG_ID_First(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Returns the first deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByG_ID_First(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the last deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByG_ID_Last(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Returns the last deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByG_ID_Last(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns the deliverables before and after the current deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableId the primary key of the current deliverable
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public Deliverable[] findByG_ID_PrevAndNext(long deliverableId,
		String deliverableState, String govAgencyCode, String deliverableType,
		String applicantIdNo,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator)
		throws NoSuchDeliverableException;

	/**
	* Removes all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	*/
	public void removeByG_ID(String deliverableState, String govAgencyCode,
		String deliverableType, String applicantIdNo);

	/**
	* Returns the number of deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @return the number of matching deliverables
	*/
	public int countByG_ID(String deliverableState, String govAgencyCode,
		String deliverableType, String applicantIdNo);

	/**
	* Returns the deliverable where deliverableCode = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableCode the deliverable code
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByFB_DCODE(String deliverableCode)
		throws NoSuchDeliverableException;

	/**
	* Returns the deliverable where deliverableCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByFB_DCODE(String deliverableCode);

	/**
	* Returns the deliverable where deliverableCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByFB_DCODE(String deliverableCode,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable where deliverableCode = &#63; from the database.
	*
	* @param deliverableCode the deliverable code
	* @return the deliverable that was removed
	*/
	public Deliverable removeByFB_DCODE(String deliverableCode)
		throws NoSuchDeliverableException;

	/**
	* Returns the number of deliverables where deliverableCode = &#63;.
	*
	* @param deliverableCode the deliverable code
	* @return the number of matching deliverables
	*/
	public int countByFB_DCODE(String deliverableCode);

	/**
	* Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public Deliverable findByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) throws NoSuchDeliverableException;

	/**
	* Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByFB_DCODE_STATE(String deliverableCode,
		String deliverableState);

	/**
	* Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public Deliverable fetchByFB_DCODE_STATE(String deliverableCode,
		String deliverableState, boolean retrieveFromCache);

	/**
	* Removes the deliverable where deliverableCode = &#63; and deliverableState = &#63; from the database.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the deliverable that was removed
	*/
	public Deliverable removeByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) throws NoSuchDeliverableException;

	/**
	* Returns the number of deliverables where deliverableCode = &#63; and deliverableState = &#63;.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the number of matching deliverables
	*/
	public int countByFB_DCODE_STATE(String deliverableCode,
		String deliverableState);

	/**
	* Caches the deliverable in the entity cache if it is enabled.
	*
	* @param deliverable the deliverable
	*/
	public void cacheResult(Deliverable deliverable);

	/**
	* Caches the deliverables in the entity cache if it is enabled.
	*
	* @param deliverables the deliverables
	*/
	public void cacheResult(java.util.List<Deliverable> deliverables);

	/**
	* Creates a new deliverable with the primary key. Does not add the deliverable to the database.
	*
	* @param deliverableId the primary key for the new deliverable
	* @return the new deliverable
	*/
	public Deliverable create(long deliverableId);

	/**
	* Removes the deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable that was removed
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public Deliverable remove(long deliverableId)
		throws NoSuchDeliverableException;

	public Deliverable updateImpl(Deliverable deliverable);

	/**
	* Returns the deliverable with the primary key or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public Deliverable findByPrimaryKey(long deliverableId)
		throws NoSuchDeliverableException;

	/**
	* Returns the deliverable with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable, or <code>null</code> if a deliverable with the primary key could not be found
	*/
	public Deliverable fetchByPrimaryKey(long deliverableId);

	@Override
	public java.util.Map<java.io.Serializable, Deliverable> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deliverables.
	*
	* @return the deliverables
	*/
	public java.util.List<Deliverable> findAll();

	/**
	* Returns a range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of deliverables
	*/
	public java.util.List<Deliverable> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverables
	*/
	public java.util.List<Deliverable> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator);

	/**
	* Returns an ordered range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverables
	*/
	public java.util.List<Deliverable> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deliverables from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deliverables.
	*
	* @return the number of deliverables
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}