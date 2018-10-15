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

import org.opencps.dossiermgt.exception.NoSuchServiceProcessRoleException;
import org.opencps.dossiermgt.model.ServiceProcessRole;

/**
 * The persistence interface for the service process role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceProcessRolePersistenceImpl
 * @see ServiceProcessRoleUtil
 * @generated
 */
@ProviderType
public interface ServiceProcessRolePersistence extends BasePersistence<ServiceProcessRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceProcessRoleUtil} to access the service process role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the service process roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByUuid(String uuid);

	/**
	* Returns a range of all the service process roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByUuid(String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the service process roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns an ordered range of all the service process roles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByUuid(String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public ServiceProcessRole findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the first service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public ServiceProcessRole fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns the last service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public ServiceProcessRole findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the last service process role in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public ServiceProcessRole fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns the service process roles before and after the current service process role in the ordered set where uuid = &#63;.
	*
	* @param serviceProcessRolePK the primary key of the current service process role
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process role
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public ServiceProcessRole[] findByUuid_PrevAndNext(
		ServiceProcessRolePK serviceProcessRolePK, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException;

	/**
	* Removes all the service process roles where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of service process roles where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service process roles
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the service process roles where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByP_S_ID(
		long serviceProcessId);

	/**
	* Returns a range of all the service process roles where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByP_S_ID(
		long serviceProcessId, int start, int end);

	/**
	* Returns an ordered range of all the service process roles where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByP_S_ID(
		long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns an ordered range of all the service process roles where serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching service process roles
	*/
	public java.util.List<ServiceProcessRole> findByP_S_ID(
		long serviceProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public ServiceProcessRole findByP_S_ID_First(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the first service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public ServiceProcessRole fetchByP_S_ID_First(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns the last service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public ServiceProcessRole findByP_S_ID_Last(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the last service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public ServiceProcessRole fetchByP_S_ID_Last(long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns the service process roles before and after the current service process role in the ordered set where serviceProcessId = &#63;.
	*
	* @param serviceProcessRolePK the primary key of the current service process role
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service process role
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public ServiceProcessRole[] findByP_S_ID_PrevAndNext(
		ServiceProcessRolePK serviceProcessRolePK, long serviceProcessId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator)
		throws NoSuchServiceProcessRoleException;

	/**
	* Removes all the service process roles where serviceProcessId = &#63; from the database.
	*
	* @param serviceProcessId the service process ID
	*/
	public void removeByP_S_ID(long serviceProcessId);

	/**
	* Returns the number of service process roles where serviceProcessId = &#63;.
	*
	* @param serviceProcessId the service process ID
	* @return the number of matching service process roles
	*/
	public int countByP_S_ID(long serviceProcessId);

	/**
	* Returns the service process role where roleCode = &#63; or throws a {@link NoSuchServiceProcessRoleException} if it could not be found.
	*
	* @param roleCode the role code
	* @return the matching service process role
	* @throws NoSuchServiceProcessRoleException if a matching service process role could not be found
	*/
	public ServiceProcessRole findByF_CODE(String roleCode)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the service process role where roleCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param roleCode the role code
	* @return the matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public ServiceProcessRole fetchByF_CODE(String roleCode);

	/**
	* Returns the service process role where roleCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param roleCode the role code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service process role, or <code>null</code> if a matching service process role could not be found
	*/
	public ServiceProcessRole fetchByF_CODE(String roleCode,
		boolean retrieveFromCache);

	/**
	* Removes the service process role where roleCode = &#63; from the database.
	*
	* @param roleCode the role code
	* @return the service process role that was removed
	*/
	public ServiceProcessRole removeByF_CODE(String roleCode)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the number of service process roles where roleCode = &#63;.
	*
	* @param roleCode the role code
	* @return the number of matching service process roles
	*/
	public int countByF_CODE(String roleCode);

	/**
	* Caches the service process role in the entity cache if it is enabled.
	*
	* @param serviceProcessRole the service process role
	*/
	public void cacheResult(ServiceProcessRole serviceProcessRole);

	/**
	* Caches the service process roles in the entity cache if it is enabled.
	*
	* @param serviceProcessRoles the service process roles
	*/
	public void cacheResult(
		java.util.List<ServiceProcessRole> serviceProcessRoles);

	/**
	* Creates a new service process role with the primary key. Does not add the service process role to the database.
	*
	* @param serviceProcessRolePK the primary key for the new service process role
	* @return the new service process role
	*/
	public ServiceProcessRole create(ServiceProcessRolePK serviceProcessRolePK);

	/**
	* Removes the service process role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role that was removed
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public ServiceProcessRole remove(ServiceProcessRolePK serviceProcessRolePK)
		throws NoSuchServiceProcessRoleException;

	public ServiceProcessRole updateImpl(ServiceProcessRole serviceProcessRole);

	/**
	* Returns the service process role with the primary key or throws a {@link NoSuchServiceProcessRoleException} if it could not be found.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role
	* @throws NoSuchServiceProcessRoleException if a service process role with the primary key could not be found
	*/
	public ServiceProcessRole findByPrimaryKey(
		ServiceProcessRolePK serviceProcessRolePK)
		throws NoSuchServiceProcessRoleException;

	/**
	* Returns the service process role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role, or <code>null</code> if a service process role with the primary key could not be found
	*/
	public ServiceProcessRole fetchByPrimaryKey(
		ServiceProcessRolePK serviceProcessRolePK);

	@Override
	public java.util.Map<java.io.Serializable, ServiceProcessRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service process roles.
	*
	* @return the service process roles
	*/
	public java.util.List<ServiceProcessRole> findAll();

	/**
	* Returns a range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of service process roles
	*/
	public java.util.List<ServiceProcessRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service process roles
	*/
	public java.util.List<ServiceProcessRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator);

	/**
	* Returns an ordered range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service process roles
	*/
	public java.util.List<ServiceProcessRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceProcessRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service process roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service process roles.
	*
	* @return the number of service process roles
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();

	public java.util.Set<String> getCompoundPKColumnNames();
}