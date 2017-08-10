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

package org.opencps.backend.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException;
import org.opencps.backend.dossiermgt.model.ServiceInfo;

/**
 * The persistence interface for the service info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.ServiceInfoPersistenceImpl
 * @see ServiceInfoUtil
 * @generated
 */
@ProviderType
public interface ServiceInfoPersistence extends BasePersistence<ServiceInfo> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceInfoUtil} to access the service info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the first service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the last service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the last service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the service infos before and after the current service info in the ordered set where uuid = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo[] findByUuid_PrevAndNext(long serviceInfoId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service infos
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service info that was removed
	*/
	public ServiceInfo removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the number of service infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the first service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the last service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the last service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the service infos before and after the current service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo[] findByUuid_C_PrevAndNext(long serviceInfoId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service infos
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the service infos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching service infos
	*/
	public java.util.List<ServiceInfo> findByGroupId(long groupId);

	/**
	* Returns a range of all the service infos where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the service infos where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the service infos where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the first service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the last service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the last service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the service infos before and after the current service info in the ordered set where groupId = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo[] findByGroupId_PrevAndNext(long serviceInfoId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of service infos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the service infos where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching service infos
	*/
	public java.util.List<ServiceInfo> findByCompanyId(long companyId);

	/**
	* Returns a range of all the service infos where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the service infos where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the service infos where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the first service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the last service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the last service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the service infos before and after the current service info in the ordered set where companyId = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo[] findByCompanyId_PrevAndNext(long serviceInfoId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of service infos where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching service infos
	*/
	public int countByCompanyId(long companyId);

	/**
	* Caches the service info in the entity cache if it is enabled.
	*
	* @param serviceInfo the service info
	*/
	public void cacheResult(ServiceInfo serviceInfo);

	/**
	* Caches the service infos in the entity cache if it is enabled.
	*
	* @param serviceInfos the service infos
	*/
	public void cacheResult(java.util.List<ServiceInfo> serviceInfos);

	/**
	* Creates a new service info with the primary key. Does not add the service info to the database.
	*
	* @param serviceInfoId the primary key for the new service info
	* @return the new service info
	*/
	public ServiceInfo create(long serviceInfoId);

	/**
	* Removes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info that was removed
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo remove(long serviceInfoId)
		throws NoSuchServiceInfoException;

	public ServiceInfo updateImpl(ServiceInfo serviceInfo);

	/**
	* Returns the service info with the primary key or throws a {@link NoSuchServiceInfoException} if it could not be found.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo findByPrimaryKey(long serviceInfoId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the service info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info, or <code>null</code> if a service info with the primary key could not be found
	*/
	public ServiceInfo fetchByPrimaryKey(long serviceInfoId);

	@Override
	public java.util.Map<java.io.Serializable, ServiceInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service infos.
	*
	* @return the service infos
	*/
	public java.util.List<ServiceInfo> findAll();

	/**
	* Returns a range of all the service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of service infos
	*/
	public java.util.List<ServiceInfo> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service infos
	*/
	public java.util.List<ServiceInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service infos
	*/
	public java.util.List<ServiceInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service infos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service infos.
	*
	* @return the number of service infos
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of file templates associated with the service info.
	*
	* @param pk the primary key of the service info
	* @return long[] of the primaryKeys of file templates associated with the service info
	*/
	public long[] getFileTemplatePrimaryKeys(long pk);

	/**
	* Returns all the file templates associated with the service info.
	*
	* @param pk the primary key of the service info
	* @return the file templates associated with the service info
	*/
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		long pk);

	/**
	* Returns a range of all the file templates associated with the service info.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the service info
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of file templates associated with the service info
	*/
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the file templates associated with the service info.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the service info
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of file templates associated with the service info
	*/
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.FileTemplate> orderByComparator);

	/**
	* Returns the number of file templates associated with the service info.
	*
	* @param pk the primary key of the service info
	* @return the number of file templates associated with the service info
	*/
	public int getFileTemplatesSize(long pk);

	/**
	* Returns <code>true</code> if the file template is associated with the service info.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePK the primary key of the file template
	* @return <code>true</code> if the file template is associated with the service info; <code>false</code> otherwise
	*/
	public boolean containsFileTemplate(long pk, long fileTemplatePK);

	/**
	* Returns <code>true</code> if the service info has any file templates associated with it.
	*
	* @param pk the primary key of the service info to check for associations with file templates
	* @return <code>true</code> if the service info has any file templates associated with it; <code>false</code> otherwise
	*/
	public boolean containsFileTemplates(long pk);

	/**
	* Adds an association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePK the primary key of the file template
	*/
	public void addFileTemplate(long pk, long fileTemplatePK);

	/**
	* Adds an association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplate the file template
	*/
	public void addFileTemplate(long pk,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate);

	/**
	* Adds an association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePKs the primary keys of the file templates
	*/
	public void addFileTemplates(long pk, long[] fileTemplatePKs);

	/**
	* Adds an association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplates the file templates
	*/
	public void addFileTemplates(long pk,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates);

	/**
	* Clears all associations between the service info and its file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info to clear the associated file templates from
	*/
	public void clearFileTemplates(long pk);

	/**
	* Removes the association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePK the primary key of the file template
	*/
	public void removeFileTemplate(long pk, long fileTemplatePK);

	/**
	* Removes the association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplate the file template
	*/
	public void removeFileTemplate(long pk,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate);

	/**
	* Removes the association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePKs the primary keys of the file templates
	*/
	public void removeFileTemplates(long pk, long[] fileTemplatePKs);

	/**
	* Removes the association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplates the file templates
	*/
	public void removeFileTemplates(long pk,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates);

	/**
	* Sets the file templates associated with the service info, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePKs the primary keys of the file templates to be associated with the service info
	*/
	public void setFileTemplates(long pk, long[] fileTemplatePKs);

	/**
	* Sets the file templates associated with the service info, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplates the file templates to be associated with the service info
	*/
	public void setFileTemplates(long pk,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}