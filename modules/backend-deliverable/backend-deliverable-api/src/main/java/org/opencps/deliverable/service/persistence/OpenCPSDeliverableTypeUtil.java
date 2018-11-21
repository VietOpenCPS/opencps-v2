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

package org.opencps.deliverable.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.deliverable.model.OpenCPSDeliverableType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the open cps deliverable type service. This utility wraps {@link org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see OpenCPSDeliverableTypePersistence
 * @see org.opencps.deliverable.service.persistence.impl.OpenCPSDeliverableTypePersistenceImpl
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeUtil {
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
	public static void clearCache(OpenCPSDeliverableType openCPSDeliverableType) {
		getPersistence().clearCache(openCPSDeliverableType);
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
	public static List<OpenCPSDeliverableType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OpenCPSDeliverableType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OpenCPSDeliverableType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OpenCPSDeliverableType update(
		OpenCPSDeliverableType openCPSDeliverableType) {
		return getPersistence().update(openCPSDeliverableType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OpenCPSDeliverableType update(
		OpenCPSDeliverableType openCPSDeliverableType,
		ServiceContext serviceContext) {
		return getPersistence().update(openCPSDeliverableType, serviceContext);
	}

	/**
	* Returns all the open cps deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the open cps deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid(String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByUuid_First(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByUuid_Last(String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeId the primary key of the current open cps deliverable type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public static OpenCPSDeliverableType[] findByUuid_PrevAndNext(
		long deliverableTypeId, String uuid,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableTypeId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the open cps deliverable types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of open cps deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching open cps deliverable types
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByUUID_G(String uuid, long groupId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the open cps deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the open cps deliverable type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the open cps deliverable type that was removed
	*/
	public static OpenCPSDeliverableType removeByUUID_G(String uuid,
		long groupId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of open cps deliverable types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching open cps deliverable types
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeId the primary key of the current open cps deliverable type
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public static OpenCPSDeliverableType[] findByUuid_C_PrevAndNext(
		long deliverableTypeId, String uuid, long companyId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableTypeId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the open cps deliverable types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of open cps deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching open cps deliverable types
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the open cps deliverable type where typeCode = &#63; or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	*
	* @param typeCode the type code
	* @return the matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByF_typeCode(String typeCode)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByF_typeCode(typeCode);
	}

	/**
	* Returns the open cps deliverable type where typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param typeCode the type code
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByF_typeCode(String typeCode) {
		return getPersistence().fetchByF_typeCode(typeCode);
	}

	/**
	* Returns the open cps deliverable type where typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByF_typeCode(String typeCode,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_typeCode(typeCode, retrieveFromCache);
	}

	/**
	* Removes the open cps deliverable type where typeCode = &#63; from the database.
	*
	* @param typeCode the type code
	* @return the open cps deliverable type that was removed
	*/
	public static OpenCPSDeliverableType removeByF_typeCode(String typeCode)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().removeByF_typeCode(typeCode);
	}

	/**
	* Returns the number of open cps deliverable types where typeCode = &#63;.
	*
	* @param typeCode the type code
	* @return the number of matching open cps deliverable types
	*/
	public static int countByF_typeCode(String typeCode) {
		return getPersistence().countByF_typeCode(typeCode);
	}

	/**
	* Returns all the open cps deliverable types where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByF_groupId(long groupId) {
		return getPersistence().findByF_groupId(groupId);
	}

	/**
	* Returns a range of all the open cps deliverable types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByF_groupId(long groupId,
		int start, int end) {
		return getPersistence().findByF_groupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByF_groupId(long groupId,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence()
				   .findByF_groupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findByF_groupId(long groupId,
		int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_groupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByF_groupId_First(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByF_groupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByF_groupId_First(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType findByF_groupId_Last(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByF_groupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static OpenCPSDeliverableType fetchByF_groupId_Last(long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence().fetchByF_groupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the open cps deliverable types before and after the current open cps deliverable type in the ordered set where groupId = &#63;.
	*
	* @param deliverableTypeId the primary key of the current open cps deliverable type
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public static OpenCPSDeliverableType[] findByF_groupId_PrevAndNext(
		long deliverableTypeId, long groupId,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence()
				   .findByF_groupId_PrevAndNext(deliverableTypeId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the open cps deliverable types where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_groupId(long groupId) {
		getPersistence().removeByF_groupId(groupId);
	}

	/**
	* Returns the number of open cps deliverable types where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching open cps deliverable types
	*/
	public static int countByF_groupId(long groupId) {
		return getPersistence().countByF_groupId(groupId);
	}

	/**
	* Caches the open cps deliverable type in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	*/
	public static void cacheResult(
		OpenCPSDeliverableType openCPSDeliverableType) {
		getPersistence().cacheResult(openCPSDeliverableType);
	}

	/**
	* Caches the open cps deliverable types in the entity cache if it is enabled.
	*
	* @param openCPSDeliverableTypes the open cps deliverable types
	*/
	public static void cacheResult(
		List<OpenCPSDeliverableType> openCPSDeliverableTypes) {
		getPersistence().cacheResult(openCPSDeliverableTypes);
	}

	/**
	* Creates a new open cps deliverable type with the primary key. Does not add the open cps deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new open cps deliverable type
	* @return the new open cps deliverable type
	*/
	public static OpenCPSDeliverableType create(long deliverableTypeId) {
		return getPersistence().create(deliverableTypeId);
	}

	/**
	* Removes the open cps deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type that was removed
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public static OpenCPSDeliverableType remove(long deliverableTypeId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().remove(deliverableTypeId);
	}

	public static OpenCPSDeliverableType updateImpl(
		OpenCPSDeliverableType openCPSDeliverableType) {
		return getPersistence().updateImpl(openCPSDeliverableType);
	}

	/**
	* Returns the open cps deliverable type with the primary key or throws a {@link NoSuchOpenCPSDeliverableTypeException} if it could not be found.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type
	* @throws NoSuchOpenCPSDeliverableTypeException if a open cps deliverable type with the primary key could not be found
	*/
	public static OpenCPSDeliverableType findByPrimaryKey(
		long deliverableTypeId)
		throws org.opencps.deliverable.exception.NoSuchOpenCPSDeliverableTypeException {
		return getPersistence().findByPrimaryKey(deliverableTypeId);
	}

	/**
	* Returns the open cps deliverable type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type, or <code>null</code> if a open cps deliverable type with the primary key could not be found
	*/
	public static OpenCPSDeliverableType fetchByPrimaryKey(
		long deliverableTypeId) {
		return getPersistence().fetchByPrimaryKey(deliverableTypeId);
	}

	public static java.util.Map<java.io.Serializable, OpenCPSDeliverableType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the open cps deliverable types.
	*
	* @return the open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of open cps deliverable types
	*/
	public static List<OpenCPSDeliverableType> findAll(int start, int end,
		OrderByComparator<OpenCPSDeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the open cps deliverable types from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of open cps deliverable types.
	*
	* @return the number of open cps deliverable types
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OpenCPSDeliverableTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpenCPSDeliverableTypePersistence, OpenCPSDeliverableTypePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpenCPSDeliverableTypePersistence.class);

		ServiceTracker<OpenCPSDeliverableTypePersistence, OpenCPSDeliverableTypePersistence> serviceTracker =
			new ServiceTracker<OpenCPSDeliverableTypePersistence, OpenCPSDeliverableTypePersistence>(bundle.getBundleContext(),
				OpenCPSDeliverableTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}