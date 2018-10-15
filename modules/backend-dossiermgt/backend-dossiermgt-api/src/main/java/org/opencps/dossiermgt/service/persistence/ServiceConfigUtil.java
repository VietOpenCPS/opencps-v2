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

import org.opencps.dossiermgt.model.ServiceConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the service config service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ServiceConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ServiceConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceConfigUtil {
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
	public static void clearCache(ServiceConfig serviceConfig) {
		getPersistence().clearCache(serviceConfig);
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
	public static List<ServiceConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceConfig update(ServiceConfig serviceConfig) {
		return getPersistence().update(serviceConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceConfig update(ServiceConfig serviceConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(serviceConfig, serviceContext);
	}

	/**
	* Returns all the service configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service configs
	*/
	public static List<ServiceConfig> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ServiceConfig> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ServiceConfig> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ServiceConfig> findByUuid(String uuid, int start,
		int end, OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByUuid_First(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByUuid_First(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByUuid_Last(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByUuid_Last(String uuid,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the service configs before and after the current service config in the ordered set where uuid = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public static ServiceConfig[] findByUuid_PrevAndNext(long serviceConfigId,
		String uuid, OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByUuid_PrevAndNext(serviceConfigId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the service configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of service configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service configs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the service config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the service config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service config that was removed
	*/
	public static ServiceConfig removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of service configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service configs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the service configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service configs
	*/
	public static List<ServiceConfig> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<ServiceConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<ServiceConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<ServiceConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static ServiceConfig[] findByUuid_C_PrevAndNext(
		long serviceConfigId, String uuid, long companyId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(serviceConfigId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the service configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of service configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service configs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or throws a {@link NoSuchServiceConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByGID_SI_GAC(long groupId,
		long serviceInfoId, String govAgencyCode)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode);
	}

	/**
	* Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByGID_SI_GAC(long groupId,
		long serviceInfoId, String govAgencyCode) {
		return getPersistence()
				   .fetchByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode);
	}

	/**
	* Returns the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByGID_SI_GAC(long groupId,
		long serviceInfoId, String govAgencyCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode,
			retrieveFromCache);
	}

	/**
	* Removes the service config where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the service config that was removed
	*/
	public static ServiceConfig removeByGID_SI_GAC(long groupId,
		long serviceInfoId, String govAgencyCode)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .removeByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode);
	}

	/**
	* Returns the number of service configs where groupId = &#63; and serviceInfoId = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param govAgencyCode the gov agency code
	* @return the number of matching service configs
	*/
	public static int countByGID_SI_GAC(long groupId, long serviceInfoId,
		String govAgencyCode) {
		return getPersistence()
				   .countByGID_SI_GAC(groupId, serviceInfoId, govAgencyCode);
	}

	/**
	* Returns all the service configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching service configs
	*/
	public static List<ServiceConfig> findByG_(long groupId) {
		return getPersistence().findByG_(groupId);
	}

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
	public static List<ServiceConfig> findByG_(long groupId, int start, int end) {
		return getPersistence().findByG_(groupId, start, end);
	}

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
	public static List<ServiceConfig> findByG_(long groupId, int start,
		int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().findByG_(groupId, start, end, orderByComparator);
	}

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
	public static List<ServiceConfig> findByG_(long groupId, int start,
		int end, OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByG__First(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().findByG__First(groupId, orderByComparator);
	}

	/**
	* Returns the first service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByG__First(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().fetchByG__First(groupId, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByG__Last(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().findByG__Last(groupId, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByG__Last(long groupId,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().fetchByG__Last(groupId, orderByComparator);
	}

	/**
	* Returns the service configs before and after the current service config in the ordered set where groupId = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public static ServiceConfig[] findByG__PrevAndNext(long serviceConfigId,
		long groupId, OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByG__PrevAndNext(serviceConfigId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the service configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG_(long groupId) {
		getPersistence().removeByG_(groupId);
	}

	/**
	* Returns the number of service configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching service configs
	*/
	public static int countByG_(long groupId) {
		return getPersistence().countByG_(groupId);
	}

	/**
	* Returns all the service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @return the matching service configs
	*/
	public static List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId) {
		return getPersistence().findByF_GID_SID(groupId, serviceInfoId);
	}

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
	public static List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end) {
		return getPersistence()
				   .findByF_GID_SID(groupId, serviceInfoId, start, end);
	}

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
	public static List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SID(groupId, serviceInfoId, start, end,
			orderByComparator);
	}

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
	public static List<ServiceConfig> findByF_GID_SID(long groupId,
		long serviceInfoId, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SID(groupId, serviceInfoId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByF_GID_SID_First(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByF_GID_SID_First(groupId, serviceInfoId,
			orderByComparator);
	}

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByF_GID_SID_First(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SID_First(groupId, serviceInfoId,
			orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByF_GID_SID_Last(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByF_GID_SID_Last(groupId, serviceInfoId,
			orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByF_GID_SID_Last(long groupId,
		long serviceInfoId, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SID_Last(groupId, serviceInfoId,
			orderByComparator);
	}

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
	public static ServiceConfig[] findByF_GID_SID_PrevAndNext(
		long serviceConfigId, long groupId, long serviceInfoId,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByF_GID_SID_PrevAndNext(serviceConfigId, groupId,
			serviceInfoId, orderByComparator);
	}

	/**
	* Removes all the service configs where groupId = &#63; and serviceInfoId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	*/
	public static void removeByF_GID_SID(long groupId, long serviceInfoId) {
		getPersistence().removeByF_GID_SID(groupId, serviceInfoId);
	}

	/**
	* Returns the number of service configs where groupId = &#63; and serviceInfoId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceInfoId the service info ID
	* @return the number of matching service configs
	*/
	public static int countByF_GID_SID(long groupId, long serviceInfoId) {
		return getPersistence().countByF_GID_SID(groupId, serviceInfoId);
	}

	/**
	* Returns all the service configs where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @return the matching service configs
	*/
	public static List<ServiceConfig> findByF_GAC(String govAgencyCode) {
		return getPersistence().findByF_GAC(govAgencyCode);
	}

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
	public static List<ServiceConfig> findByF_GAC(String govAgencyCode,
		int start, int end) {
		return getPersistence().findByF_GAC(govAgencyCode, start, end);
	}

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
	public static List<ServiceConfig> findByF_GAC(String govAgencyCode,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .findByF_GAC(govAgencyCode, start, end, orderByComparator);
	}

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
	public static List<ServiceConfig> findByF_GAC(String govAgencyCode,
		int start, int end, OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GAC(govAgencyCode, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByF_GAC_First(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByF_GAC_First(govAgencyCode, orderByComparator);
	}

	/**
	* Returns the first service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByF_GAC_First(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_GAC_First(govAgencyCode, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByF_GAC_Last(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByF_GAC_Last(govAgencyCode, orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByF_GAC_Last(String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_GAC_Last(govAgencyCode, orderByComparator);
	}

	/**
	* Returns the service configs before and after the current service config in the ordered set where govAgencyCode = &#63;.
	*
	* @param serviceConfigId the primary key of the current service config
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public static ServiceConfig[] findByF_GAC_PrevAndNext(
		long serviceConfigId, String govAgencyCode,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByF_GAC_PrevAndNext(serviceConfigId, govAgencyCode,
			orderByComparator);
	}

	/**
	* Removes all the service configs where govAgencyCode = &#63; from the database.
	*
	* @param govAgencyCode the gov agency code
	*/
	public static void removeByF_GAC(String govAgencyCode) {
		getPersistence().removeByF_GAC(govAgencyCode);
	}

	/**
	* Returns the number of service configs where govAgencyCode = &#63;.
	*
	* @param govAgencyCode the gov agency code
	* @return the number of matching service configs
	*/
	public static int countByF_GAC(String govAgencyCode) {
		return getPersistence().countByF_GAC(govAgencyCode);
	}

	/**
	* Returns all the service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @return the matching service configs
	*/
	public static List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel) {
		return getPersistence().findByGID_LEVEL(groupId, serviceLevel);
	}

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
	public static List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel, int start, int end) {
		return getPersistence()
				   .findByGID_LEVEL(groupId, serviceLevel, start, end);
	}

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
	public static List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .findByGID_LEVEL(groupId, serviceLevel, start, end,
			orderByComparator);
	}

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
	public static List<ServiceConfig> findByGID_LEVEL(long groupId,
		int serviceLevel, int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_LEVEL(groupId, serviceLevel, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByGID_LEVEL_First(long groupId,
		int serviceLevel, OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByGID_LEVEL_First(groupId, serviceLevel,
			orderByComparator);
	}

	/**
	* Returns the first service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByGID_LEVEL_First(long groupId,
		int serviceLevel, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByGID_LEVEL_First(groupId, serviceLevel,
			orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config
	* @throws NoSuchServiceConfigException if a matching service config could not be found
	*/
	public static ServiceConfig findByGID_LEVEL_Last(long groupId,
		int serviceLevel, OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByGID_LEVEL_Last(groupId, serviceLevel,
			orderByComparator);
	}

	/**
	* Returns the last service config in the ordered set where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service config, or <code>null</code> if a matching service config could not be found
	*/
	public static ServiceConfig fetchByGID_LEVEL_Last(long groupId,
		int serviceLevel, OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence()
				   .fetchByGID_LEVEL_Last(groupId, serviceLevel,
			orderByComparator);
	}

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
	public static ServiceConfig[] findByGID_LEVEL_PrevAndNext(
		long serviceConfigId, long groupId, int serviceLevel,
		OrderByComparator<ServiceConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence()
				   .findByGID_LEVEL_PrevAndNext(serviceConfigId, groupId,
			serviceLevel, orderByComparator);
	}

	/**
	* Removes all the service configs where groupId = &#63; and serviceLevel = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	*/
	public static void removeByGID_LEVEL(long groupId, int serviceLevel) {
		getPersistence().removeByGID_LEVEL(groupId, serviceLevel);
	}

	/**
	* Returns the number of service configs where groupId = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceLevel the service level
	* @return the number of matching service configs
	*/
	public static int countByGID_LEVEL(long groupId, int serviceLevel) {
		return getPersistence().countByGID_LEVEL(groupId, serviceLevel);
	}

	/**
	* Caches the service config in the entity cache if it is enabled.
	*
	* @param serviceConfig the service config
	*/
	public static void cacheResult(ServiceConfig serviceConfig) {
		getPersistence().cacheResult(serviceConfig);
	}

	/**
	* Caches the service configs in the entity cache if it is enabled.
	*
	* @param serviceConfigs the service configs
	*/
	public static void cacheResult(List<ServiceConfig> serviceConfigs) {
		getPersistence().cacheResult(serviceConfigs);
	}

	/**
	* Creates a new service config with the primary key. Does not add the service config to the database.
	*
	* @param serviceConfigId the primary key for the new service config
	* @return the new service config
	*/
	public static ServiceConfig create(long serviceConfigId) {
		return getPersistence().create(serviceConfigId);
	}

	/**
	* Removes the service config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config that was removed
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public static ServiceConfig remove(long serviceConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().remove(serviceConfigId);
	}

	public static ServiceConfig updateImpl(ServiceConfig serviceConfig) {
		return getPersistence().updateImpl(serviceConfig);
	}

	/**
	* Returns the service config with the primary key or throws a {@link NoSuchServiceConfigException} if it could not be found.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config
	* @throws NoSuchServiceConfigException if a service config with the primary key could not be found
	*/
	public static ServiceConfig findByPrimaryKey(long serviceConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchServiceConfigException {
		return getPersistence().findByPrimaryKey(serviceConfigId);
	}

	/**
	* Returns the service config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceConfigId the primary key of the service config
	* @return the service config, or <code>null</code> if a service config with the primary key could not be found
	*/
	public static ServiceConfig fetchByPrimaryKey(long serviceConfigId) {
		return getPersistence().fetchByPrimaryKey(serviceConfigId);
	}

	public static java.util.Map<java.io.Serializable, ServiceConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service configs.
	*
	* @return the service configs
	*/
	public static List<ServiceConfig> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ServiceConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ServiceConfig> findAll(int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ServiceConfig> findAll(int start, int end,
		OrderByComparator<ServiceConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service configs.
	*
	* @return the number of service configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ServiceConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceConfigPersistence, ServiceConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceConfigPersistence.class);

		ServiceTracker<ServiceConfigPersistence, ServiceConfigPersistence> serviceTracker =
			new ServiceTracker<ServiceConfigPersistence, ServiceConfigPersistence>(bundle.getBundleContext(),
				ServiceConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}