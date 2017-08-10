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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.dossiermgt.model.ServiceInfo;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the service info service. This utility wraps {@link org.opencps.backend.dossiermgt.service.persistence.impl.ServiceInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ServiceInfoPersistence
 * @see org.opencps.backend.dossiermgt.service.persistence.impl.ServiceInfoPersistenceImpl
 * @generated
 */
@ProviderType
public class ServiceInfoUtil {
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
	public static void clearCache(ServiceInfo serviceInfo) {
		getPersistence().clearCache(serviceInfo);
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
	public static List<ServiceInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceInfo update(ServiceInfo serviceInfo) {
		return getPersistence().update(serviceInfo);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceInfo update(ServiceInfo serviceInfo,
		ServiceContext serviceContext) {
		return getPersistence().update(serviceInfo, serviceContext);
	}

	/**
	* Returns all the service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching service infos
	*/
	public static List<ServiceInfo> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ServiceInfo> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ServiceInfo> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ServiceInfo> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByUuid_First(java.lang.String uuid,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the service infos before and after the current service info in the ordered set where uuid = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public static ServiceInfo[] findByUuid_PrevAndNext(long serviceInfoId,
		java.lang.String uuid, OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByUuid_PrevAndNext(serviceInfoId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the service infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching service infos
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchServiceInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the service info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the service info that was removed
	*/
	public static ServiceInfo removeByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of service infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching service infos
	*/
	public static List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<ServiceInfo> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static ServiceInfo[] findByUuid_C_PrevAndNext(long serviceInfoId,
		java.lang.String uuid, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(serviceInfoId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the service infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching service infos
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the service infos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching service infos
	*/
	public static List<ServiceInfo> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<ServiceInfo> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<ServiceInfo> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<ServiceInfo> findByGroupId(long groupId, int start,
		int end, OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByGroupId_First(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByGroupId_First(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByGroupId_Last(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByGroupId_Last(long groupId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the service infos before and after the current service info in the ordered set where groupId = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public static ServiceInfo[] findByGroupId_PrevAndNext(long serviceInfoId,
		long groupId, OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(serviceInfoId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the service infos where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of service infos where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching service infos
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the service infos where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching service infos
	*/
	public static List<ServiceInfo> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static List<ServiceInfo> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static List<ServiceInfo> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

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
	public static List<ServiceInfo> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByCompanyId_First(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByCompanyId_First(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info
	* @throws NoSuchServiceInfoException if a matching service info could not be found
	*/
	public static ServiceInfo findByCompanyId_Last(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last service info in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching service info, or <code>null</code> if a matching service info could not be found
	*/
	public static ServiceInfo fetchByCompanyId_Last(long companyId,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the service infos before and after the current service info in the ordered set where companyId = &#63;.
	*
	* @param serviceInfoId the primary key of the current service info
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public static ServiceInfo[] findByCompanyId_PrevAndNext(
		long serviceInfoId, long companyId,
		OrderByComparator<ServiceInfo> orderByComparator)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(serviceInfoId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the service infos where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of service infos where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching service infos
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Caches the service info in the entity cache if it is enabled.
	*
	* @param serviceInfo the service info
	*/
	public static void cacheResult(ServiceInfo serviceInfo) {
		getPersistence().cacheResult(serviceInfo);
	}

	/**
	* Caches the service infos in the entity cache if it is enabled.
	*
	* @param serviceInfos the service infos
	*/
	public static void cacheResult(List<ServiceInfo> serviceInfos) {
		getPersistence().cacheResult(serviceInfos);
	}

	/**
	* Creates a new service info with the primary key. Does not add the service info to the database.
	*
	* @param serviceInfoId the primary key for the new service info
	* @return the new service info
	*/
	public static ServiceInfo create(long serviceInfoId) {
		return getPersistence().create(serviceInfoId);
	}

	/**
	* Removes the service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info that was removed
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public static ServiceInfo remove(long serviceInfoId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().remove(serviceInfoId);
	}

	public static ServiceInfo updateImpl(ServiceInfo serviceInfo) {
		return getPersistence().updateImpl(serviceInfo);
	}

	/**
	* Returns the service info with the primary key or throws a {@link NoSuchServiceInfoException} if it could not be found.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info
	* @throws NoSuchServiceInfoException if a service info with the primary key could not be found
	*/
	public static ServiceInfo findByPrimaryKey(long serviceInfoId)
		throws org.opencps.backend.dossiermgt.exception.NoSuchServiceInfoException {
		return getPersistence().findByPrimaryKey(serviceInfoId);
	}

	/**
	* Returns the service info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param serviceInfoId the primary key of the service info
	* @return the service info, or <code>null</code> if a service info with the primary key could not be found
	*/
	public static ServiceInfo fetchByPrimaryKey(long serviceInfoId) {
		return getPersistence().fetchByPrimaryKey(serviceInfoId);
	}

	public static java.util.Map<java.io.Serializable, ServiceInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the service infos.
	*
	* @return the service infos
	*/
	public static List<ServiceInfo> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ServiceInfo> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ServiceInfo> findAll(int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ServiceInfo> findAll(int start, int end,
		OrderByComparator<ServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the service infos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of service infos.
	*
	* @return the number of service infos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of file templates associated with the service info.
	*
	* @param pk the primary key of the service info
	* @return long[] of the primaryKeys of file templates associated with the service info
	*/
	public static long[] getFileTemplatePrimaryKeys(long pk) {
		return getPersistence().getFileTemplatePrimaryKeys(pk);
	}

	/**
	* Returns all the file templates associated with the service info.
	*
	* @param pk the primary key of the service info
	* @return the file templates associated with the service info
	*/
	public static List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		long pk) {
		return getPersistence().getFileTemplates(pk);
	}

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
	public static List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		long pk, int start, int end) {
		return getPersistence().getFileTemplates(pk, start, end);
	}

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
	public static List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		long pk, int start, int end,
		OrderByComparator<org.opencps.backend.dossiermgt.model.FileTemplate> orderByComparator) {
		return getPersistence()
				   .getFileTemplates(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of file templates associated with the service info.
	*
	* @param pk the primary key of the service info
	* @return the number of file templates associated with the service info
	*/
	public static int getFileTemplatesSize(long pk) {
		return getPersistence().getFileTemplatesSize(pk);
	}

	/**
	* Returns <code>true</code> if the file template is associated with the service info.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePK the primary key of the file template
	* @return <code>true</code> if the file template is associated with the service info; <code>false</code> otherwise
	*/
	public static boolean containsFileTemplate(long pk, long fileTemplatePK) {
		return getPersistence().containsFileTemplate(pk, fileTemplatePK);
	}

	/**
	* Returns <code>true</code> if the service info has any file templates associated with it.
	*
	* @param pk the primary key of the service info to check for associations with file templates
	* @return <code>true</code> if the service info has any file templates associated with it; <code>false</code> otherwise
	*/
	public static boolean containsFileTemplates(long pk) {
		return getPersistence().containsFileTemplates(pk);
	}

	/**
	* Adds an association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePK the primary key of the file template
	*/
	public static void addFileTemplate(long pk, long fileTemplatePK) {
		getPersistence().addFileTemplate(pk, fileTemplatePK);
	}

	/**
	* Adds an association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplate the file template
	*/
	public static void addFileTemplate(long pk,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		getPersistence().addFileTemplate(pk, fileTemplate);
	}

	/**
	* Adds an association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePKs the primary keys of the file templates
	*/
	public static void addFileTemplates(long pk, long[] fileTemplatePKs) {
		getPersistence().addFileTemplates(pk, fileTemplatePKs);
	}

	/**
	* Adds an association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplates the file templates
	*/
	public static void addFileTemplates(long pk,
		List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		getPersistence().addFileTemplates(pk, fileTemplates);
	}

	/**
	* Clears all associations between the service info and its file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info to clear the associated file templates from
	*/
	public static void clearFileTemplates(long pk) {
		getPersistence().clearFileTemplates(pk);
	}

	/**
	* Removes the association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePK the primary key of the file template
	*/
	public static void removeFileTemplate(long pk, long fileTemplatePK) {
		getPersistence().removeFileTemplate(pk, fileTemplatePK);
	}

	/**
	* Removes the association between the service info and the file template. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplate the file template
	*/
	public static void removeFileTemplate(long pk,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		getPersistence().removeFileTemplate(pk, fileTemplate);
	}

	/**
	* Removes the association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePKs the primary keys of the file templates
	*/
	public static void removeFileTemplates(long pk, long[] fileTemplatePKs) {
		getPersistence().removeFileTemplates(pk, fileTemplatePKs);
	}

	/**
	* Removes the association between the service info and the file templates. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplates the file templates
	*/
	public static void removeFileTemplates(long pk,
		List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		getPersistence().removeFileTemplates(pk, fileTemplates);
	}

	/**
	* Sets the file templates associated with the service info, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplatePKs the primary keys of the file templates to be associated with the service info
	*/
	public static void setFileTemplates(long pk, long[] fileTemplatePKs) {
		getPersistence().setFileTemplates(pk, fileTemplatePKs);
	}

	/**
	* Sets the file templates associated with the service info, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the service info
	* @param fileTemplates the file templates to be associated with the service info
	*/
	public static void setFileTemplates(long pk,
		List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		getPersistence().setFileTemplates(pk, fileTemplates);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ServiceInfoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceInfoPersistence, ServiceInfoPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ServiceInfoPersistence.class);
}