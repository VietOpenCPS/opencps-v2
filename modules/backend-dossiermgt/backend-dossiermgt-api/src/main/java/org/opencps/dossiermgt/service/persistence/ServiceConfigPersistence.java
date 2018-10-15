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

import org.opencps.dossiermgt.exception.NoSuchServiceConfigException;
import org.opencps.dossiermgt.model.ServiceConfig;

/**
 * The persistence interface for the service config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceConfigPersistenceImpl
 * @see ServiceConfigUtil
 * @generated
 */
@ProviderType
public interface ServiceConfigPersistence extends BasePersistence<ServiceConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceConfigUtil} to access the service config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid(String uuid);

	/**
	* Returns a range of all the service configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the service configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the first service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the last service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the last service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the service configs before and after the current service config in the ordered set where uuid = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig[] findByUuid_PrevAndNext(long serviceConfigId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Removes all the service configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of service configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service configs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the service config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigException;

	/**
	* Returns the service config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the service config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service config that was removed
	*/
	public ServiceConfig removeByUUID_G(String uuid, long groupId)
		throws NoSuchServiceConfigException;

	/**
	* Returns the number of service configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service configs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the service configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the service configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the service configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the first service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the last service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the last service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the service configs before and after the current service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig[] findByUuid_C_PrevAndNext(long serviceConfigId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Removes all the service configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of service configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service configs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchServiceConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) throws NoSuchServiceConfigException;

	/**
	* Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode);

	/**
	* Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode, boolean retrieveFromCache);

	/**
	* Removes the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the service config that was removed
	*/
	public ServiceConfig removeByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) throws NoSuchServiceConfigException;

	/**
	* Returns the number of service configs where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the number of matching service configs
	*/
	public int countByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode);

	/**
	* Returns all the service configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching service configs
	*/
	public java.util.List<ServiceConfig> findByG_(long groupId);

	/**
	* Returns a range of all the service configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByG_(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the service configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByG_(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByG_(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByG__First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the first service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByG__First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the last service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByG__Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the last service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByG__Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the service configs before and after the current service config in the ordered set where groupId = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig[] findByG__PrevAndNext(long serviceConfigId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Removes all the service configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG_(long groupId);

	/**
	* Returns the number of service configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching service configs
	*/
	public int countByG_(long groupId);

	/**
	* Returns all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @return the matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId);

	/**
	* Returns a range of all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end);

	/**
	* Returns an ordered range of all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByF_GID_SID_First(long groupId,
		long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByF_GID_SID_First(long groupId,
		long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByF_GID_SID_Last(long groupId, long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByF_GID_SID_Last(long groupId,
		long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the service configs before and after the current service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig[] findByF_GID_SID_PrevAndNext(long serviceConfigId,
		long groupId, long serviceInfoId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Removes all the service configs where groupId = &#63; and serviceInfoId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	*/
	public void removeByF_GID_SID(long groupId, long serviceInfoId);

	/**
	* Returns the number of service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @return the number of matching service configs
	*/
	public int countByF_GID_SID(long groupId, long serviceInfoId);

	/**
	* Returns all the service configs where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @return the matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GAC(String govAgencyCode);

	/**
	* Returns a range of all the service configs where govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GAC(String govAgencyCode,
		int start, int end);

	/**
	* Returns an ordered range of all the service configs where govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GAC(String govAgencyCode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs where govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByF_GAC(String govAgencyCode,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByF_GAC_First(String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the first service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByF_GAC_First(String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the last service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByF_GAC_Last(String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the last service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByF_GAC_Last(String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the service configs before and after the current service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig[] findByF_GAC_PrevAndNext(long serviceConfigId,
		String govAgencyCode,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Removes all the service configs where govAgencyCode = &#63; from the database.
	*
	* @param govAgencyCode the gov agency code
	*/
	public void removeByF_GAC(String govAgencyCode);

	/**
	* Returns the number of service configs where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @return the number of matching service configs
	*/
	public int countByF_GAC(String govAgencyCode);

	/**
	* Returns all the service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @return the matching service configs
	*/
	public java.util.List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel);

	/**
	* Returns a range of all the service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel, int start, int end);

	/**
	* Returns an ordered range of all the service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service configs
	*/
	public java.util.List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByGID_LEVEL_First(long groupId, int serviceLevel,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByGID_LEVEL_First(long groupId, int serviceLevel,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public ServiceConfig findByGID_LEVEL_Last(long groupId, int serviceLevel,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public ServiceConfig fetchByGID_LEVEL_Last(long groupId, int serviceLevel,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns the service configs before and after the current service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig[] findByGID_LEVEL_PrevAndNext(long serviceConfigId,
		long groupId, int serviceLevel,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator)
		throws NoSuchServiceConfigException;

	/**
	* Removes all the service configs where groupId = &#63; and serviceLevel = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	*/
	public void removeByGID_LEVEL(long groupId, int serviceLevel);

	/**
	* Returns the number of service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @return the number of matching service configs
	*/
	public int countByGID_LEVEL(long groupId, int serviceLevel);

	/**
	* Caches the service config in the entity cache if it is enabled.
	*
	* @param serviceConfig the service config
	*/
	public void cacheResult(ServiceConfig serviceConfig);

	/**
	* Caches the service configs in the entity cache if it is enabled.
	*
	* @param serviceConfigs the service configs
	*/
	public void cacheResult(java.util.List<ServiceConfig> serviceConfigs);

	/**
	* Creates a new service config with the primary key. Does not add the service config to the database.
	*
	* @param serviceConfigId the primary key for the new service config
	* @return the new service config
	*/
	public ServiceConfig create(long serviceConfigId);

	/**
	* Removes the service config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config that was removed
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig remove(long serviceConfigId)
		throws NoSuchServiceConfigException;

	public ServiceConfig updateImpl(ServiceConfig serviceConfig);

	/**
	* Returns the service config with the primary key or throws a {@link NoSuchServiceConfigException} if it could not be found.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public ServiceConfig findByPrimaryKey(long serviceConfigId)
		throws NoSuchServiceConfigException;

	/**
	* Returns the service config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config, or <code>null</code> if a service config with the primary key could not be found
	*/
	public ServiceConfig fetchByPrimaryKey(long serviceConfigId);

	@Override
	public java.util.Map<java.io.Serializable, ServiceConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service configs.
	*
	* @return the service configs
	*/
	public java.util.List<ServiceConfig> findAll();

	/**
	* Returns a range of all the service configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @return the range of service configs
	*/
	public java.util.List<ServiceConfig> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service configs
	*/
	public java.util.List<ServiceConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator);

	/**
	* Returns an ordered range of all the service configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service configs
	* @param end the upper bound of the range of service configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service configs
	*/
	public java.util.List<ServiceConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service configs.
	*
	* @return the number of service configs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}