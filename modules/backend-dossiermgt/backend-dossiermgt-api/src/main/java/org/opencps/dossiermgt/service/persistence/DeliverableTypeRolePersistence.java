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

import org.opencps.dossiermgt.exception.NoSuchDeliverableTypeRoleException;
import org.opencps.dossiermgt.model.DeliverableTypeRole;

/**
 * The persistence interface for the deliverable type role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverableTypeRolePersistenceImpl
 * @see DeliverableTypeRoleUtil
 * @generated
 */
@ProviderType
public interface DeliverableTypeRolePersistence extends BasePersistence<DeliverableTypeRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliverableTypeRoleUtil} to access the deliverable type role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid(String uuid);

	/**
	* Returns a range of all the deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns the deliverable type roles before and after the current deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current deliverable type role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public DeliverableTypeRole[] findByUuid_PrevAndNext(
		long deliverableTypeRoleId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Removes all the deliverable type roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverable type roles
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the deliverable type role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the deliverable type role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable type role that was removed
	*/
	public DeliverableTypeRole removeByUUID_G(String uuid, long groupId)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the number of deliverable type roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverable type roles
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the first deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the last deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns the deliverable type roles before and after the current deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current deliverable type role
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public DeliverableTypeRole[] findByUuid_C_PrevAndNext(
		long deliverableTypeRoleId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Removes all the deliverable type roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverable type roles
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @return the matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId);

	/**
	* Returns a range of all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableTypeId the deliverable type ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end);

	/**
	* Returns an ordered range of all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableTypeId the deliverable type ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable type roles where deliverableTypeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableTypeId the deliverable type ID
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findByF_deliverableTypeId(
		long deliverableTypeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByF_deliverableTypeId_First(
		long deliverableTypeId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the first deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByF_deliverableTypeId_First(
		long deliverableTypeId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns the last deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByF_deliverableTypeId_Last(
		long deliverableTypeId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the last deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByF_deliverableTypeId_Last(
		long deliverableTypeId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns the deliverable type roles before and after the current deliverable type role in the ordered set where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current deliverable type role
	* @param deliverableTypeId the deliverable type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public DeliverableTypeRole[] findByF_deliverableTypeId_PrevAndNext(
		long deliverableTypeRoleId, long deliverableTypeId,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Removes all the deliverable type roles where deliverableTypeId = &#63; from the database.
	*
	* @param deliverableTypeId the deliverable type ID
	*/
	public void removeByF_deliverableTypeId(long deliverableTypeId);

	/**
	* Returns the number of deliverable type roles where deliverableTypeId = &#63;.
	*
	* @param deliverableTypeId the deliverable type ID
	* @return the number of matching deliverable type roles
	*/
	public int countByF_deliverableTypeId(long deliverableTypeId);

	/**
	* Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the matching deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole findByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId);

	/**
	* Returns the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	public DeliverableTypeRole fetchByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId, boolean retrieveFromCache);

	/**
	* Removes the deliverable type role where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the deliverable type role that was removed
	*/
	public DeliverableTypeRole removeByF_GID_DID_RID(long groupId,
		long deliverableTypeId, long roleId)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the number of deliverable type roles where groupId = &#63; and deliverableTypeId = &#63; and roleId = &#63;.
	*
	* @param groupId the group ID
	* @param deliverableTypeId the deliverable type ID
	* @param roleId the role ID
	* @return the number of matching deliverable type roles
	*/
	public int countByF_GID_DID_RID(long groupId, long deliverableTypeId,
		long roleId);

	/**
	* Caches the deliverable type role in the entity cache if it is enabled.
	*
	* @param deliverableTypeRole the deliverable type role
	*/
	public void cacheResult(DeliverableTypeRole deliverableTypeRole);

	/**
	* Caches the deliverable type roles in the entity cache if it is enabled.
	*
	* @param deliverableTypeRoles the deliverable type roles
	*/
	public void cacheResult(
		java.util.List<DeliverableTypeRole> deliverableTypeRoles);

	/**
	* Creates a new deliverable type role with the primary key. Does not add the deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new deliverable type role
	* @return the new deliverable type role
	*/
	public DeliverableTypeRole create(long deliverableTypeRoleId);

	/**
	* Removes the deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role that was removed
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public DeliverableTypeRole remove(long deliverableTypeRoleId)
		throws NoSuchDeliverableTypeRoleException;

	public DeliverableTypeRole updateImpl(
		DeliverableTypeRole deliverableTypeRole);

	/**
	* Returns the deliverable type role with the primary key or throws a {@link NoSuchDeliverableTypeRoleException} if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role
	* @throws NoSuchDeliverableTypeRoleException if a deliverable type role with the primary key could not be found
	*/
	public DeliverableTypeRole findByPrimaryKey(long deliverableTypeRoleId)
		throws NoSuchDeliverableTypeRoleException;

	/**
	* Returns the deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role, or <code>null</code> if a deliverable type role with the primary key could not be found
	*/
	public DeliverableTypeRole fetchByPrimaryKey(long deliverableTypeRoleId);

	@Override
	public java.util.Map<java.io.Serializable, DeliverableTypeRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deliverable type roles.
	*
	* @return the deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findAll();

	/**
	* Returns a range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverable type roles
	*/
	public java.util.List<DeliverableTypeRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deliverable type roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deliverable type roles.
	*
	* @return the number of deliverable type roles
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}