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

import org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException;
import org.opencps.deliverable.model.OpenCPSDeliverableType;

/**
 * The persistence interface for the open cps deliverable type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableTypePersistenceImpl
 * @see OpenCPSDeliverableTypeUtil
 * @generated
 */
@ProviderType
public interface OpenCPSDeliverableTypePersistence extends BasePersistence<OpenCPSDeliverableType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OpenCPSDeliverableTypeUtil} to access the open cps deliverable type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the open cps deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid(String uuid);

	/**
	* Returns a range of all the open cps deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeId the primary key of the current open cps deliverable type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public OpenCPSDeliverableType[] findByUuid_PrevAndNext(
		long deliverableTypeId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Removes all the open cps deliverable types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of open cps deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverable types
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the open cps deliverable type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable type that was removed
	*/
	public OpenCPSDeliverableType removeByUUID_G(String uuid, long groupId)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the number of open cps deliverable types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverable types
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByUuid_C_Last(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeId the primary key of the current open cps deliverable type
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public OpenCPSDeliverableType[] findByUuid_C_PrevAndNext(
		long deliverableTypeId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Removes all the open cps deliverable types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverable types
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the open cps deliverable type where typeCode = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	*
	* @param typeCode the type code
	* @param groupId the group ID
	* @return the matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByF_typeCode(String typeCode, long groupId)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the open cps deliverable type where typeCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typeCode the type code
	* @param groupId the group ID
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByF_typeCode(String typeCode,
		long groupId);

	/**
	* Returns the open cps deliverable type where typeCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typeCode the type code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByF_typeCode(String typeCode,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the open cps deliverable type where typeCode = &#63; and groupId = &#63; from the database.
	*
	* @param typeCode the type code
	* @param groupId the group ID
	* @return the open cps deliverable type that was removed
	*/
	public OpenCPSDeliverableType removeByF_typeCode(String typeCode,
		long groupId) throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the number of open cps deliverable types where typeCode = &#63; and groupId = &#63;.
	*
	* @param typeCode the type code
	* @param groupId the group ID
	* @return the number of matching open cps deliverable types
	*/
	public int countByF_typeCode(String typeCode, long groupId);

	/**
	* Returns all the open cps deliverable types where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByF_groupId(long groupId);

	/**
	* Returns a range of all the open cps deliverable types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByF_groupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByF_groupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findByF_groupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByF_groupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the first open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByF_groupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns the last open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType findByF_groupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the last open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public OpenCPSDeliverableType fetchByF_groupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param deliverableTypeId the primary key of the current open cps deliverable type
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public OpenCPSDeliverableType[] findByF_groupId_PrevAndNext(
		long deliverableTypeId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Removes all the open cps deliverable types where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_groupId(long groupId);

	/**
	* Returns the number of open cps deliverable types where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching open cps deliverable types
	*/
	public int countByF_groupId(long groupId);

	/**
	* Caches the open cps deliverable type in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	*/
	public void cacheResult(OpenCPSDeliverableType openCPSDeliverableType);

	/**
	* Caches the open cps deliverable types in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableTypes the open cps deliverable types
	*/
	public void cacheResult(
		java.util.List<OpenCPSDeliverableType> openCPSDeliverableTypes);

	/**
	* Creates a new open cps deliverable type with the primary key. Does not add the open cps deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new open cps deliverable type
	* @return the new open cps deliverable type
	*/
	public OpenCPSDeliverableType create(long deliverableTypeId);

	/**
	* Removes the open cps deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type that was removed
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public OpenCPSDeliverableType remove(long deliverableTypeId)
		throws NoSuchOpenCPSDeliverableTypeException;

	public OpenCPSDeliverableType updateImpl(
		OpenCPSDeliverableType openCPSDeliverableType);

	/**
	* Returns the open cps deliverable type with the primary key or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public OpenCPSDeliverableType findByPrimaryKey(long deliverableTypeId)
		throws NoSuchOpenCPSDeliverableTypeException;

	/**
	* Returns the open cps deliverable type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type, or <code>null</code> if a open cps deliverable type with the primary key could not be found
	*/
	public OpenCPSDeliverableType fetchByPrimaryKey(long deliverableTypeId);

	@Override
	public java.util.Map<java.io.Serializable, OpenCPSDeliverableType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the open cps deliverable types.
	*
	* @return the open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findAll();

	/**
	* Returns a range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findAll(int start, int end);

	/**
	* Returns an ordered range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator);

	/**
	* Returns an ordered range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of open cps deliverable types
	*/
	public java.util.List<OpenCPSDeliverableType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the open cps deliverable types from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of open cps deliverable types.
	*
	* @return the number of open cps deliverable types
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}