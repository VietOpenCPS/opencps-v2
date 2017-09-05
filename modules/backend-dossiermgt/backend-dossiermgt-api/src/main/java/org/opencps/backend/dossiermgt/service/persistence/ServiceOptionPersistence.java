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

import org.opencps.backend.dossiermgt.exception.NoSuchServiceOptionException;
import org.opencps.backend.dossiermgt.model.ServiceOption;

/**
 * The persistence interface for the service option service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.ServiceOptionPersistenceImpl
 * @see ServiceOptionUtil
 * @generated
 */
@ProviderType
public interface ServiceOptionPersistence extends BasePersistence<ServiceOption> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceOptionUtil} to access the service option persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service options where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service options
	*/
	public java.util.List<ServiceOption> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the service options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @return the range of matching service options
	*/
	public java.util.List<ServiceOption> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the service options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service options
	*/
	public java.util.List<ServiceOption> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns an ordered range of all the service options where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service options
	*/
	public java.util.List<ServiceOption> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service option
	* @throws NoSuchServiceOptionException if a matching service option could not be found
	*/
	public ServiceOption findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator)
		throws NoSuchServiceOptionException;

	/**
	* Returns the first service option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public ServiceOption fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns the last service option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service option
	* @throws NoSuchServiceOptionException if a matching service option could not be found
	*/
	public ServiceOption findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator)
		throws NoSuchServiceOptionException;

	/**
	* Returns the last service option in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public ServiceOption fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns the service options before and after the current service option in the ordered set where uuid = &#63;.
	*
	* @param serviceOptionId the primary key of the current service option
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service option
	* @throws NoSuchServiceOptionException if a service option with the primary key could not be found
	*/
	public ServiceOption[] findByUuid_PrevAndNext(long serviceOptionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator)
		throws NoSuchServiceOptionException;

	/**
	* Removes all the service options where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of service options where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service options
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the service option where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceOptionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service option
	* @throws NoSuchServiceOptionException if a matching service option could not be found
	*/
	public ServiceOption findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchServiceOptionException;

	/**
	* Returns the service option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public ServiceOption fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the service option where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public ServiceOption fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the service option where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service option that was removed
	*/
	public ServiceOption removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchServiceOptionException;

	/**
	* Returns the number of service options where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service options
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the service options where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service options
	*/
	public java.util.List<ServiceOption> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the service options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @return the range of matching service options
	*/
	public java.util.List<ServiceOption> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the service options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service options
	*/
	public java.util.List<ServiceOption> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns an ordered range of all the service options where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service options
	*/
	public java.util.List<ServiceOption> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service option
	* @throws NoSuchServiceOptionException if a matching service option could not be found
	*/
	public ServiceOption findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator)
		throws NoSuchServiceOptionException;

	/**
	* Returns the first service option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public ServiceOption fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns the last service option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service option
	* @throws NoSuchServiceOptionException if a matching service option could not be found
	*/
	public ServiceOption findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator)
		throws NoSuchServiceOptionException;

	/**
	* Returns the last service option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service option, or <code>null</code> if a matching service option could not be found
	*/
	public ServiceOption fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns the service options before and after the current service option in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param serviceOptionId the primary key of the current service option
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service option
	* @throws NoSuchServiceOptionException if a service option with the primary key could not be found
	*/
	public ServiceOption[] findByUuid_C_PrevAndNext(long serviceOptionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator)
		throws NoSuchServiceOptionException;

	/**
	* Removes all the service options where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of service options where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service options
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the service option in the entity cache if it is enabled.
	*
	* @param serviceOption the service option
	*/
	public void cacheResult(ServiceOption serviceOption);

	/**
	* Caches the service options in the entity cache if it is enabled.
	*
	* @param serviceOptions the service options
	*/
	public void cacheResult(java.util.List<ServiceOption> serviceOptions);

	/**
	* Creates a new service option with the primary key. Does not add the service option to the database.
	*
	* @param serviceOptionId the primary key for the new service option
	* @return the new service option
	*/
	public ServiceOption create(long serviceOptionId);

	/**
	* Removes the service option with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceOptionId the primary key of the service option
	* @return the service option that was removed
	* @throws NoSuchServiceOptionException if a service option with the primary key could not be found
	*/
	public ServiceOption remove(long serviceOptionId)
		throws NoSuchServiceOptionException;

	public ServiceOption updateImpl(ServiceOption serviceOption);

	/**
	* Returns the service option with the primary key or throws a {@link NoSuchServiceOptionException} if it could not be found.
	*
	* @param serviceOptionId the primary key of the service option
	* @return the service option
	* @throws NoSuchServiceOptionException if a service option with the primary key could not be found
	*/
	public ServiceOption findByPrimaryKey(long serviceOptionId)
		throws NoSuchServiceOptionException;

	/**
	* Returns the service option with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceOptionId the primary key of the service option
	* @return the service option, or <code>null</code> if a service option with the primary key could not be found
	*/
	public ServiceOption fetchByPrimaryKey(long serviceOptionId);

	@Override
	public java.util.Map<java.io.Serializable, ServiceOption> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service options.
	*
	* @return the service options
	*/
	public java.util.List<ServiceOption> findAll();

	/**
	* Returns a range of all the service options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @return the range of service options
	*/
	public java.util.List<ServiceOption> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service options
	*/
	public java.util.List<ServiceOption> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator);

	/**
	* Returns an ordered range of all the service options.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceOptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service options
	* @param end the upper bound of the range of service options (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service options
	*/
	public java.util.List<ServiceOption> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceOption> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service options from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service options.
	*
	* @return the number of service options
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}