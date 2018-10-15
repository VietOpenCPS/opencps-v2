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

import org.opencps.dossiermgt.model.DeliverableType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the deliverable type service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DeliverableTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverableTypePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverableTypePersistenceImpl
 * @generated
 */
@ProviderType
public class DeliverableTypeUtil {
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
	public static void clearCache(DeliverableType deliverableType) {
		getPersistence().clearCache(deliverableType);
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
	public static List<DeliverableType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DeliverableType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DeliverableType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DeliverableType update(DeliverableType deliverableType) {
		return getPersistence().update(deliverableType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DeliverableType update(DeliverableType deliverableType,
		ServiceContext serviceContext) {
		return getPersistence().update(deliverableType, serviceContext);
	}

	/**
	* Returns all the deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverable types
	*/
	public static List<DeliverableType> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of matching deliverable types
	*/
	public static List<DeliverableType> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable types
	*/
	public static List<DeliverableType> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable types
	*/
	public static List<DeliverableType> findByUuid(String uuid, int start,
		int end, OrderByComparator<DeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public static DeliverableType findByUuid_First(String uuid,
		OrderByComparator<DeliverableType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByUuid_First(String uuid,
		OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public static DeliverableType findByUuid_Last(String uuid,
		OrderByComparator<DeliverableType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByUuid_Last(String uuid,
		OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the deliverable types before and after the current deliverable type in the ordered set where uuid = &#63;.
	*
	* @param deliverableTypeId the primary key of the current deliverable type
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public static DeliverableType[] findByUuid_PrevAndNext(
		long deliverableTypeId, String uuid,
		OrderByComparator<DeliverableType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableTypeId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the deliverable types where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of deliverable types where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverable types
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the deliverable type where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public static DeliverableType findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable type where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the deliverable type where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable type that was removed
	*/
	public static DeliverableType removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of deliverable types where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverable types
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverable types
	*/
	public static List<DeliverableType> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of matching deliverable types
	*/
	public static List<DeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverable types
	*/
	public static List<DeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverable types
	*/
	public static List<DeliverableType> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public static DeliverableType findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DeliverableType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public static DeliverableType findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DeliverableType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the deliverable types before and after the current deliverable type in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableTypeId the primary key of the current deliverable type
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable type
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public static DeliverableType[] findByUuid_C_PrevAndNext(
		long deliverableTypeId, String uuid, long companyId,
		OrderByComparator<DeliverableType> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableTypeId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the deliverable types where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of deliverable types where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverable types
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the deliverable type where groupId = &#63; and typeCode = &#63; or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching deliverable type
	* @throws NoSuchDeliverableTypeException if a matching deliverable type could not be found
	*/
	public static DeliverableType findByG_DLT(long groupId, String typeCode)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().findByG_DLT(groupId, typeCode);
	}

	/**
	* Returns the deliverable type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByG_DLT(long groupId, String typeCode) {
		return getPersistence().fetchByG_DLT(groupId, typeCode);
	}

	/**
	* Returns the deliverable type where groupId = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	public static DeliverableType fetchByG_DLT(long groupId, String typeCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_DLT(groupId, typeCode, retrieveFromCache);
	}

	/**
	* Removes the deliverable type where groupId = &#63; and typeCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the deliverable type that was removed
	*/
	public static DeliverableType removeByG_DLT(long groupId, String typeCode)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().removeByG_DLT(groupId, typeCode);
	}

	/**
	* Returns the number of deliverable types where groupId = &#63; and typeCode = &#63;.
	*
	* @param groupId the group ID
	* @param typeCode the type code
	* @return the number of matching deliverable types
	*/
	public static int countByG_DLT(long groupId, String typeCode) {
		return getPersistence().countByG_DLT(groupId, typeCode);
	}

	/**
	* Caches the deliverable type in the entity cache if it is enabled.
	*
	* @param deliverableType the deliverable type
	*/
	public static void cacheResult(DeliverableType deliverableType) {
		getPersistence().cacheResult(deliverableType);
	}

	/**
	* Caches the deliverable types in the entity cache if it is enabled.
	*
	* @param deliverableTypes the deliverable types
	*/
	public static void cacheResult(List<DeliverableType> deliverableTypes) {
		getPersistence().cacheResult(deliverableTypes);
	}

	/**
	* Creates a new deliverable type with the primary key. Does not add the deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new deliverable type
	* @return the new deliverable type
	*/
	public static DeliverableType create(long deliverableTypeId) {
		return getPersistence().create(deliverableTypeId);
	}

	/**
	* Removes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type that was removed
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public static DeliverableType remove(long deliverableTypeId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().remove(deliverableTypeId);
	}

	public static DeliverableType updateImpl(DeliverableType deliverableType) {
		return getPersistence().updateImpl(deliverableType);
	}

	/**
	* Returns the deliverable type with the primary key or throws a {@link NoSuchDeliverableTypeException} if it could not be found.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type
	* @throws NoSuchDeliverableTypeException if a deliverable type with the primary key could not be found
	*/
	public static DeliverableType findByPrimaryKey(long deliverableTypeId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableTypeException {
		return getPersistence().findByPrimaryKey(deliverableTypeId);
	}

	/**
	* Returns the deliverable type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type, or <code>null</code> if a deliverable type with the primary key could not be found
	*/
	public static DeliverableType fetchByPrimaryKey(long deliverableTypeId) {
		return getPersistence().fetchByPrimaryKey(deliverableTypeId);
	}

	public static java.util.Map<java.io.Serializable, DeliverableType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the deliverable types.
	*
	* @return the deliverable types
	*/
	public static List<DeliverableType> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of deliverable types
	*/
	public static List<DeliverableType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverable types
	*/
	public static List<DeliverableType> findAll(int start, int end,
		OrderByComparator<DeliverableType> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverable types
	*/
	public static List<DeliverableType> findAll(int start, int end,
		OrderByComparator<DeliverableType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the deliverable types from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of deliverable types.
	*
	* @return the number of deliverable types
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DeliverableTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeliverableTypePersistence, DeliverableTypePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DeliverableTypePersistence.class);

		ServiceTracker<DeliverableTypePersistence, DeliverableTypePersistence> serviceTracker =
			new ServiceTracker<DeliverableTypePersistence, DeliverableTypePersistence>(bundle.getBundleContext(),
				DeliverableTypePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}