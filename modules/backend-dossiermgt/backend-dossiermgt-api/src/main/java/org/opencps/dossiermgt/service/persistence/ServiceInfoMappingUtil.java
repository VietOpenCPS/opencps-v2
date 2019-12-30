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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.ServiceInfoMapping;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the service info mapping service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ServiceInfoMappingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceInfoMappingPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceInfoMappingPersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceInfoMappingUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ServiceInfoMapping serviceInfoMapping) {
		getPersistence().clearCache(serviceInfoMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ServiceInfoMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceInfoMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceInfoMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceInfoMapping> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceInfoMapping update(
		ServiceInfoMapping serviceInfoMapping) {
		return getPersistence().update(serviceInfoMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceInfoMapping update(
		ServiceInfoMapping serviceInfoMapping, ServiceContext serviceContext) {
		return getPersistence().update(serviceInfoMapping, serviceContext);
	}

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching service info mapping
	* @throws NoSuchServiceInfoMappingException if a matching service info mapping could not be found
	*/
	public static ServiceInfoMapping findByF_GID_SC(long groupId,
		String serviceCode)
		throws org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException {
		return getPersistence().findByF_GID_SC(groupId, serviceCode);
	}

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public static ServiceInfoMapping fetchByF_GID_SC(long groupId,
		String serviceCode) {
		return getPersistence().fetchByF_GID_SC(groupId, serviceCode);
	}

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public static ServiceInfoMapping fetchByF_GID_SC(long groupId,
		String serviceCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_SC(groupId, serviceCode, retrieveFromCache);
	}

	/**
	* Removes the service info mapping where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the service info mapping that was removed
	*/
	public static ServiceInfoMapping removeByF_GID_SC(long groupId,
		String serviceCode)
		throws org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException {
		return getPersistence().removeByF_GID_SC(groupId, serviceCode);
	}

	/**
	* Returns the number of service info mappings where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching service info mappings
	*/
	public static int countByF_GID_SC(long groupId, String serviceCode) {
		return getPersistence().countByF_GID_SC(groupId, serviceCode);
	}

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the matching service info mapping
	* @throws NoSuchServiceInfoMappingException if a matching service info mapping could not be found
	*/
	public static ServiceInfoMapping findByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG)
		throws org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException {
		return getPersistence().findByF_GID_SCDVCQG(groupId, serviceCodeDVCQG);
	}

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public static ServiceInfoMapping fetchByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG) {
		return getPersistence().fetchByF_GID_SCDVCQG(groupId, serviceCodeDVCQG);
	}

	/**
	* Returns the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info mapping, or <code>null</code> if a matching service info mapping could not be found
	*/
	public static ServiceInfoMapping fetchByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_SCDVCQG(groupId, serviceCodeDVCQG,
			retrieveFromCache);
	}

	/**
	* Removes the service info mapping where groupId = &#63; and serviceCodeDVCQG = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the service info mapping that was removed
	*/
	public static ServiceInfoMapping removeByF_GID_SCDVCQG(long groupId,
		String serviceCodeDVCQG)
		throws org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException {
		return getPersistence().removeByF_GID_SCDVCQG(groupId, serviceCodeDVCQG);
	}

	/**
	* Returns the number of service info mappings where groupId = &#63; and serviceCodeDVCQG = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCodeDVCQG the service code dvcqg
	* @return the number of matching service info mappings
	*/
	public static int countByF_GID_SCDVCQG(long groupId, String serviceCodeDVCQG) {
		return getPersistence().countByF_GID_SCDVCQG(groupId, serviceCodeDVCQG);
	}

	/**
	* Caches the service info mapping in the entity cache if it is enabled.
	*
	* @param serviceInfoMapping the service info mapping
	*/
	public static void cacheResult(ServiceInfoMapping serviceInfoMapping) {
		getPersistence().cacheResult(serviceInfoMapping);
	}

	/**
	* Caches the service info mappings in the entity cache if it is enabled.
	*
	* @param serviceInfoMappings the service info mappings
	*/
	public static void cacheResult(List<ServiceInfoMapping> serviceInfoMappings) {
		getPersistence().cacheResult(serviceInfoMappings);
	}

	/**
	* Creates a new service info mapping with the primary key. Does not add the service info mapping to the database.
	*
	* @param serviceInfoMappingId the primary key for the new service info mapping
	* @return the new service info mapping
	*/
	public static ServiceInfoMapping create(long serviceInfoMappingId) {
		return getPersistence().create(serviceInfoMappingId);
	}

	/**
	* Removes the service info mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping that was removed
	* @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	*/
	public static ServiceInfoMapping remove(long serviceInfoMappingId)
		throws org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException {
		return getPersistence().remove(serviceInfoMappingId);
	}

	public static ServiceInfoMapping updateImpl(
		ServiceInfoMapping serviceInfoMapping) {
		return getPersistence().updateImpl(serviceInfoMapping);
	}

	/**
	* Returns the service info mapping with the primary key or throws a {@link NoSuchServiceInfoMappingException} if it could not be found.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping
	* @throws NoSuchServiceInfoMappingException if a service info mapping with the primary key could not be found
	*/
	public static ServiceInfoMapping findByPrimaryKey(long serviceInfoMappingId)
		throws org.opencps.dossiermgt.exception.NoSuchServiceInfoMappingException {
		return getPersistence().findByPrimaryKey(serviceInfoMappingId);
	}

	/**
	* Returns the service info mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceInfoMappingId the primary key of the service info mapping
	* @return the service info mapping, or <code>null</code> if a service info mapping with the primary key could not be found
	*/
	public static ServiceInfoMapping fetchByPrimaryKey(
		long serviceInfoMappingId) {
		return getPersistence().fetchByPrimaryKey(serviceInfoMappingId);
	}

	public static java.util.Map<java.io.Serializable, ServiceInfoMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service info mappings.
	*
	* @return the service info mappings
	*/
	public static List<ServiceInfoMapping> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ServiceInfoMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ServiceInfoMapping> findAll(int start, int end,
		OrderByComparator<ServiceInfoMapping> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ServiceInfoMapping> findAll(int start, int end,
		OrderByComparator<ServiceInfoMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service info mappings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service info mappings.
	*
	* @return the number of service info mappings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ServiceInfoMappingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceInfoMappingPersistence, ServiceInfoMappingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceInfoMappingPersistence.class);

		ServiceTracker<ServiceInfoMappingPersistence, ServiceInfoMappingPersistence> serviceTracker =
			new ServiceTracker<ServiceInfoMappingPersistence, ServiceInfoMappingPersistence>(bundle.getBundleContext(),
				ServiceInfoMappingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}