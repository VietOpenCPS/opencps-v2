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

import org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException;
import org.opencps.dossiermgt.model.ServiceInfoMapping;

/**
 * The persistence interface for the service info mapping service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceInfoMappingPersistenceImpl
 * @see ServiceInfoMappingUtil
 * @generated
 */
@ProviderType
public interface ServiceInfoMappingPersistence extends BasePersistence<ServiceInfoMapping> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceInfoMappingUtil} to access the service info mapping persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching service info mapping
	* @throws NoSuchServiceInfoMappingException if a matching service info mapping could not be found
	*/
	public ServiceInfoMapping findByF_GID_SC(long groupId, String serviceCode)
		throws NoSuchServiceInfoMappingException;

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public ServiceInfoMapping fetchByF_GID_SC(long groupId, String serviceCode);

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public ServiceInfoMapping fetchByF_GID_SC(long groupId, String serviceCode,
		boolean retrieveFromCache);

	/**
	* Removes the service info mapping where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the service info mapping that was removed
	*/
	public ServiceInfoMapping removeByF_GID_SC(long groupId, String serviceCode)
		throws NoSuchServiceInfoMappingException;

	/**
	* Returns the number of service info mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching service info mappings
	*/
	public int countByF_GID_SC(long groupId, String serviceCode);

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the matching service info mapping
	* @throws NoSuchServiceInfoMappingException if a matching service info mapping could not be found
	*/
	public ServiceInfoMapping findByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG) throws NoSuchServiceInfoMappingException;

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public ServiceInfoMapping fetchByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG);

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public ServiceInfoMapping fetchByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG, boolean retrieveFromCache);

	/**
	* Removes the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the service info mapping that was removed
	*/
	public ServiceInfoMapping removeByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG) throws NoSuchServiceInfoMappingException;

	/**
	* Returns the number of service info mappings where groupId = &#63; and serviceCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the number of matching service info mappings
	*/
	public int countByF_GID_SCDVCQG(long groupId, String serviceCodeDVCQG);

	/**
	* Caches the service info mapping in the entity cache if it is enabled.
	*
	* @param serviceInfoMapping the service info mapping
	*/
	public void cacheResult(ServiceInfoMapping serviceInfoMapping);

	/**
	* Caches the service info mappings in the entity cache if it is enabled.
	*
	* @param serviceInfoMappings the service info mappings
	*/
	public void cacheResult(
		java.util.List<ServiceInfoMapping> serviceInfoMappings);

	/**
	* Creates a new service info mapping with the primary key. Does not add the service info mapping to the database.
	*
	* @param serviceInfoMappingId the primary key for the new service info mapping
	* @return the new service info mapping
	*/
	public ServiceInfoMapping create(long serviceInfoMappingId);

	/**
	* Removes the service info mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping that was removed
	* @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	*/
	public ServiceInfoMapping remove(long serviceInfoMappingId)
		throws NoSuchServiceInfoMappingException;

	public ServiceInfoMapping updateImpl(ServiceInfoMapping serviceInfoMapping);

	/**
	* Returns the service info mapping with the primary key or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping
	* @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	*/
	public ServiceInfoMapping findByPrimaryKey(long serviceInfoMappingId)
		throws NoSuchServiceInfoMappingException;

	/**
	* Returns the service info mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping, or <code>null</code> if a service info mapping with the primary key could not be found
	*/
	public ServiceInfoMapping fetchByPrimaryKey(long serviceInfoMappingId);

	@Override
	public java.util.Map<java.io.Serializable, ServiceInfoMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the service info mappings.
	*
	* @return the service info mappings
	*/
	public java.util.List<ServiceInfoMapping> findAll();

	/**
	* Returns a range of all the service info mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service info mappings
	* @param end the upper bound of the range of service info mappings (not inclusive)
	* @return the range of service info mappings
	*/
	public java.util.List<ServiceInfoMapping> findAll(int start, int end);

	/**
	* Returns an ordered range of all the service info mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service info mappings
	* @param end the upper bound of the range of service info mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of service info mappings
	*/
	public java.util.List<ServiceInfoMapping> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfoMapping> orderByComparator);

	/**
	* Returns an ordered range of all the service info mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ServiceInfoMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service info mappings
	* @param end the upper bound of the range of service info mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of service info mappings
	*/
	public java.util.List<ServiceInfoMapping> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceInfoMapping> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the service info mappings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of service info mappings.
	*
	* @return the number of service info mappings
	*/
	public int countAll();
}