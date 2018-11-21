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

package org.opencps.deliverable.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeRoleException;
import org.opencps.deliverable.model.OpenCPSDeliverableTypeRole;

/**
 * The persistence interface for the open cps deliverable type role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableTypeRolePersistenceImpl
 * @see OpenCPSDeliverableTypeRoleUtil
 * @generated
 */
@ProviderType
public interface OpenCPSDeliverableTypeRolePersistence extends BasePersistence<OpenCPSDeliverableTypeRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpenCPSDeliverableTypeRoleUtil} to access the open cps deliverable type role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the open cps deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid(String uuid);

	/**
	* Returns a range of all the open cps deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public OpenCPSDeliverableTypeRole[] findByUuid_PrevAndNext(
		long deliverableTypeRoleId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Removes all the open cps deliverable type roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of open cps deliverable type roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverable type roles
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeRoleException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the open cps deliverable type role where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the open cps deliverable type role where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable type role that was removed
	*/
	public OpenCPSDeliverableTypeRole removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the number of open cps deliverable type roles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverable type roles
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid_C(
		String uuid, long companyId);

	/**
	* Returns a range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the first open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the last open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns the open cps deliverable type roles before and after the current open cps deliverable type role in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeRoleId the primary key of the current open cps deliverable type role
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public OpenCPSDeliverableTypeRole[] findByUuid_C_PrevAndNext(
		long deliverableTypeRoleId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Removes all the open cps deliverable type roles where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of open cps deliverable type roles where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverable type roles
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the open cps deliverable type role in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	*/
	public void cacheResult(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole);

	/**
	* Caches the open cps deliverable type roles in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableTypeRoles the open cps deliverable type roles
	*/
	public void cacheResult(
		java.util.List<OpenCPSDeliverableTypeRole> openCPSDeliverableTypeRoles);

	/**
	* Creates a new open cps deliverable type role with the primary key. Does not add the open cps deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new open cps deliverable type role
	* @return the new open cps deliverable type role
	*/
	public OpenCPSDeliverableTypeRole create(long deliverableTypeRoleId);

	/**
	* Removes the open cps deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role that was removed
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public OpenCPSDeliverableTypeRole remove(long deliverableTypeRoleId)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	public OpenCPSDeliverableTypeRole updateImpl(
		OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole);

	/**
	* Returns the open cps deliverable type role with the primary key or throws a {@link NoSuchOpenCPSDeliverableTypeRoleException} if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role
	* @throws NoSuchOpenCPSDeliverableTypeRoleException if a open cps deliverable type role with the primary key could not be found
	*/
	public OpenCPSDeliverableTypeRole findByPrimaryKey(
		long deliverableTypeRoleId)
		throws NoSuchOpenCPSDeliverableTypeRoleException;

	/**
	* Returns the open cps deliverable type role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role, or <code>null</code> if a open cps deliverable type role with the primary key could not be found
	*/
	public OpenCPSDeliverableTypeRole fetchByPrimaryKey(
		long deliverableTypeRoleId);

	@Override
	public java.util.Map<java.io.Serializable, OpenCPSDeliverableTypeRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the open cps deliverable type roles.
	*
	* @return the open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findAll();

	/**
	* Returns a range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of open cps deliverable type roles
	*/
	public java.util.List<OpenCPSDeliverableTypeRole> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableTypeRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the open cps deliverable type roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of open cps deliverable type roles.
	*
	* @return the number of open cps deliverable type roles
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}