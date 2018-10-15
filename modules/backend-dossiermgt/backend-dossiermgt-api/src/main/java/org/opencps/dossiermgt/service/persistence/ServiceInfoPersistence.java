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

import org.opencps.dossiermgt.exception.NoSuchServiceInfoException;
import org.opencps.dossiermgt.model.ServiceInfo;

/**
 * The persistence interface for the service info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceInfoPersistenceImpl
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
	public java.util.List<ServiceInfo> findByUuid(String uuid);

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
	public java.util.List<ServiceInfo> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ServiceInfo> findByUuid(String uuid, int start,
		int end,
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
	public java.util.List<ServiceInfo> findByUuid(String uuid, int start,
		int end,
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
	public ServiceInfo findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the first service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the last service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the last service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUuid_Last(String uuid,
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
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service infos
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service info that was removed
	*/
	public ServiceInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the number of service infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service infos
	*/
	public java.util.List<ServiceInfo> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<ServiceInfo> findByUuid_C(String uuid,
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
	public java.util.List<ServiceInfo> findByUuid_C(String uuid,
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
	public java.util.List<ServiceInfo> findByUuid_C(String uuid,
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
	public ServiceInfo findByUuid_C_First(String uuid, long companyId,
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
	public ServiceInfo fetchByUuid_C_First(String uuid, long companyId,
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
	public ServiceInfo findByUuid_C_Last(String uuid, long companyId,
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
	public ServiceInfo fetchByUuid_C_Last(String uuid, long companyId,
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
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service infos
	*/
	public int countByUuid_C(String uuid, long companyId);

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
	* Returns the service info where serviceCode = &#63; and groupId = &#63; or throws a {@link NoSuchServiceInfoException} if it could not be found.
	*
	* @param serviceCode the service code
	* @param groupId the group ID
	* @return the matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findBySC_GI(String serviceCode, long groupId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the service info where serviceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceCode the service code
	* @param groupId the group ID
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchBySC_GI(String serviceCode, long groupId);

	/**
	* Returns the service info where serviceCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceCode the service code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchBySC_GI(String serviceCode, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service info where serviceCode = &#63; and groupId = &#63; from the database.
	*
	* @param serviceCode the service code
	* @param groupId the group ID
	* @return the service info that was removed
	*/
	public ServiceInfo removeBySC_GI(String serviceCode, long groupId)
		throws NoSuchServiceInfoException;

	/**
	* Returns the number of service infos where serviceCode = &#63; and groupId = &#63;.
	*
	* @param serviceCode the service code
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public int countBySC_GI(String serviceCode, long groupId);

	/**
	* Returns all the service infos where domainCode = &#63; and groupId = &#63;.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @return the matching service infos
	*/
	public java.util.List<ServiceInfo> findByGI_DC(String domainCode,
		long groupId);

	/**
	* Returns a range of all the service infos where domainCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @return the range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByGI_DC(String domainCode,
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the service infos where domainCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByGI_DC(String domainCode,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns an ordered range of all the service infos where domainCode = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param start the lower bound of the range of service infos
	* @param end the upper bound of the range of service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service infos
	*/
	public java.util.List<ServiceInfo> findByGI_DC(String domainCode,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByGI_DC_First(String domainCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the first service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByGI_DC_First(String domainCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the last service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public ServiceInfo findByGI_DC_Last(String domainCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Returns the last service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public ServiceInfo fetchByGI_DC_Last(String domainCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator);

	/**
	* Returns the service infos before and after the current service info in the ordered set where domainCode = &#63; and groupId = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param domainCode the domain code
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public ServiceInfo[] findByGI_DC_PrevAndNext(long serviceInfoId,
		String domainCode, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfo> orderByComparator)
		throws NoSuchServiceInfoException;

	/**
	* Removes all the service infos where domainCode = &#63; and groupId = &#63; from the database.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	*/
	public void removeByGI_DC(String domainCode, long groupId);

	/**
	* Returns the number of service infos where domainCode = &#63; and groupId = &#63;.
	*
	* @param domainCode the domain code
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public int countByGI_DC(String domainCode, long groupId);

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

	@Override
	public java.util.Set<String> getBadColumnNames();
}