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

package opencps.dvcqg.extend.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import opencps.dvcqg.extend.sync.exception.NoSuchServiceConfigMappingException;
import opencps.dvcqg.extend.sync.model.ServiceConfigMapping;

/**
 * The persistence interface for the service config mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see opencps.dvcqg.extend.sync.service.persistence.impl.ServiceConfigMappingPersistenceImpl
 * @see ServiceConfigMappingUtil
 * @generated
 */
@ProviderType
public interface ServiceConfigMappingPersistence extends BasePersistence<ServiceConfigMapping> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceConfigMappingUtil} to access the service config mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service config mappings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid(String uuid);

	/**
	* Returns a range of all the service config mappings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the service config mappings before and after the current service config mapping in the ordered set where uuid = &#63;.
	*
	* @param serviceConfigMappingId the primary key of the current service config mapping
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public ServiceConfigMapping[] findByUuid_PrevAndNext(
		long serviceConfigMappingId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Removes all the service config mappings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of service config mappings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service config mappings
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the service config mapping where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceConfigMappingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the service config mapping where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the service config mapping where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service config mapping where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service config mapping that was removed
	*/
	public ServiceConfigMapping removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the number of service config mappings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service config mappings
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns an ordered range of all the service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the first service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByUuid_C_First(String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the last service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the service config mappings before and after the current service config mapping in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param serviceConfigMappingId the primary key of the current service config mapping
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public ServiceConfigMapping[] findByUuid_C_PrevAndNext(
		long serviceConfigMappingId, String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Removes all the service config mappings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of service config mappings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service config mappings
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the service config mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByG_ServiceCode(
		long groupId, String serviceCode);

	/**
	* Returns a range of all the service config mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByG_ServiceCode(
		long groupId, String serviceCode, int start, int end);

	/**
	* Returns an ordered range of all the service config mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByG_ServiceCode(
		long groupId, String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns an ordered range of all the service config mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findByG_ServiceCode(
		long groupId, String serviceCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config mapping in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByG_ServiceCode_First(long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the first service config mapping in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByG_ServiceCode_First(long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the last service config mapping in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping
	* @throws NoSuchServiceConfigMappingException if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping findByG_ServiceCode_Last(long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the last service config mapping in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config mapping, or <code>null</code> if a matching service config mapping could not be found
	*/
	public ServiceConfigMapping fetchByG_ServiceCode_Last(long groupId,
		String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns the service config mappings before and after the current service config mapping in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param serviceConfigMappingId the primary key of the current service config mapping
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public ServiceConfigMapping[] findByG_ServiceCode_PrevAndNext(
		long serviceConfigMappingId, long groupId, String serviceCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator)
		throws NoSuchServiceConfigMappingException;

	/**
	* Removes all the service config mappings where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public void removeByG_ServiceCode(long groupId, String serviceCode);

	/**
	* Returns the number of service config mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching service config mappings
	*/
	public int countByG_ServiceCode(long groupId, String serviceCode);

	/**
	* Caches the service config mapping in the entity cache if it is enabled.
	*
	* @param serviceConfigMapping the service config mapping
	*/
	public void cacheResult(ServiceConfigMapping serviceConfigMapping);

	/**
	* Caches the service config mappings in the entity cache if it is enabled.
	*
	* @param serviceConfigMappings the service config mappings
	*/
	public void cacheResult(
		java.util.List<ServiceConfigMapping> serviceConfigMappings);

	/**
	* Creates a new service config mapping with the primary key. Does not add the service config mapping to the database.
	*
	* @param serviceConfigMappingId the primary key for the new service config mapping
	* @return the new service config mapping
	*/
	public ServiceConfigMapping create(long serviceConfigMappingId);

	/**
	* Removes the service config mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping that was removed
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public ServiceConfigMapping remove(long serviceConfigMappingId)
		throws NoSuchServiceConfigMappingException;

	public ServiceConfigMapping updateImpl(
		ServiceConfigMapping serviceConfigMapping);

	/**
	* Returns the service config mapping with the primary key or throws a {@link NoSuchServiceConfigMappingException} if it could not be found.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping
	* @throws NoSuchServiceConfigMappingException if a service config mapping with the primary key could not be found
	*/
	public ServiceConfigMapping findByPrimaryKey(long serviceConfigMappingId)
		throws NoSuchServiceConfigMappingException;

	/**
	* Returns the service config mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceConfigMappingId the primary key of the service config mapping
	* @return the service config mapping, or <code>null</code> if a service config mapping with the primary key could not be found
	*/
	public ServiceConfigMapping fetchByPrimaryKey(long serviceConfigMappingId);

	@Override
	public java.util.Map<java.io.Serializable, ServiceConfigMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service config mappings.
	*
	* @return the service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findAll();

	/**
	* Returns a range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @return the range of service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator);

	/**
	* Returns an ordered range of all the service config mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service config mappings
	* @param end the upper bound of the range of service config mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service config mappings
	*/
	public java.util.List<ServiceConfigMapping> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfigMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service config mappings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service config mappings.
	*
	* @return the number of service config mappings
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}