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

import org.opencps.dossiermgt.model.Deliverable;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the deliverable service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DeliverablePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DeliverablePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DeliverablePersistenceImpl
 * @generated
 */
@ProviderType
public class DeliverableUtil {
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
	public static void clearCache(Deliverable deliverable) {
		getPersistence().clearCache(deliverable);
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
	public static List<Deliverable> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Deliverable> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Deliverable> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Deliverable update(Deliverable deliverable) {
		return getPersistence().update(deliverable);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Deliverable update(Deliverable deliverable,
		ServiceContext serviceContext) {
		return getPersistence().update(deliverable, serviceContext);
	}

	/**
	* Returns all the deliverables where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching deliverables
	*/
	public static List<Deliverable> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of matching deliverables
	*/
	public static List<Deliverable> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverables
	*/
	public static List<Deliverable> findByUuid(String uuid, int start, int end,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverables
	*/
	public static List<Deliverable> findByUuid(String uuid, int start, int end,
		OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByUuid_First(String uuid,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByUuid_First(String uuid,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByUuid_Last(String uuid,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByUuid_Last(String uuid,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the deliverables before and after the current deliverable in the ordered set where uuid = &#63;.
	*
	* @param deliverableId the primary key of the current deliverable
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public static Deliverable[] findByUuid_PrevAndNext(long deliverableId,
		String uuid, OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByUuid_PrevAndNext(deliverableId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the deliverables where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of deliverables where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching deliverables
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the deliverable where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the deliverable where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the deliverable where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the deliverable that was removed
	*/
	public static Deliverable removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of deliverables where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching deliverables
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching deliverables
	*/
	public static List<Deliverable> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of matching deliverables
	*/
	public static List<Deliverable> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverables
	*/
	public static List<Deliverable> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverables where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverables
	*/
	public static List<Deliverable> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the deliverables before and after the current deliverable in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param deliverableId the primary key of the current deliverable
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public static Deliverable[] findByUuid_C_PrevAndNext(long deliverableId,
		String uuid, long companyId,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(deliverableId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the deliverables where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of deliverables where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching deliverables
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the deliverable where deliverableId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableId the deliverable ID
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByDID(long deliverableId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByDID(deliverableId);
	}

	/**
	* Returns the deliverable where deliverableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableId the deliverable ID
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByDID(long deliverableId) {
		return getPersistence().fetchByDID(deliverableId);
	}

	/**
	* Returns the deliverable where deliverableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableId the deliverable ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByDID(long deliverableId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByDID(deliverableId, retrieveFromCache);
	}

	/**
	* Removes the deliverable where deliverableId = &#63; from the database.
	*
	* @param deliverableId the deliverable ID
	* @return the deliverable that was removed
	*/
	public static Deliverable removeByDID(long deliverableId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().removeByDID(deliverableId);
	}

	/**
	* Returns the number of deliverables where deliverableId = &#63;.
	*
	* @param deliverableId the deliverable ID
	* @return the number of matching deliverables
	*/
	public static int countByDID(long deliverableId) {
		return getPersistence().countByDID(deliverableId);
	}

	/**
	* Returns the deliverable where groupId = &#63; and deliverableId = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByG_DID(long groupId, long deliverableId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByG_DID(groupId, deliverableId);
	}

	/**
	* Returns the deliverable where groupId = &#63; and deliverableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByG_DID(long groupId, long deliverableId) {
		return getPersistence().fetchByG_DID(groupId, deliverableId);
	}

	/**
	* Returns the deliverable where groupId = &#63; and deliverableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByG_DID(long groupId, long deliverableId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_DID(groupId, deliverableId, retrieveFromCache);
	}

	/**
	* Removes the deliverable where groupId = &#63; and deliverableId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the deliverable that was removed
	*/
	public static Deliverable removeByG_DID(long groupId, long deliverableId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().removeByG_DID(groupId, deliverableId);
	}

	/**
	* Returns the number of deliverables where groupId = &#63; and deliverableId = &#63;.
	*
	* @param groupId the group ID
	* @param deliverableId the deliverable ID
	* @return the number of matching deliverables
	*/
	public static int countByG_DID(long groupId, long deliverableId) {
		return getPersistence().countByG_DID(groupId, deliverableId);
	}

	/**
	* Returns all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @return the matching deliverables
	*/
	public static List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo) {
		return getPersistence()
				   .findByG_ID(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo);
	}

	/**
	* Returns a range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of matching deliverables
	*/
	public static List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end) {
		return getPersistence()
				   .findByG_ID(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, start, end);
	}

	/**
	* Returns an ordered range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching deliverables
	*/
	public static List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end, OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .findByG_ID(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching deliverables
	*/
	public static List<Deliverable> findByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		int start, int end, OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_ID(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByG_ID_First(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByG_ID_First(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the first deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByG_ID_First(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .fetchByG_ID_First(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the last deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByG_ID_Last(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByG_ID_Last(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the last deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByG_ID_Last(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence()
				   .fetchByG_ID_Last(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the deliverables before and after the current deliverable in the ordered set where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableId the primary key of the current deliverable
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public static Deliverable[] findByG_ID_PrevAndNext(long deliverableId,
		String deliverableState, String govAgencyCode, String deliverableType,
		String applicantIdNo, OrderByComparator<Deliverable> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByG_ID_PrevAndNext(deliverableId, deliverableState,
			govAgencyCode, deliverableType, applicantIdNo, orderByComparator);
	}

	/**
	* Removes all the deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	*/
	public static void removeByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo) {
		getPersistence()
			.removeByG_ID(deliverableState, govAgencyCode, deliverableType,
			applicantIdNo);
	}

	/**
	* Returns the number of deliverables where deliverableState = &#63; and govAgencyCode = &#63; and deliverableType = &#63; and applicantIdNo = &#63;.
	*
	* @param deliverableState the deliverable state
	* @param govAgencyCode the gov agency code
	* @param deliverableType the deliverable type
	* @param applicantIdNo the applicant ID no
	* @return the number of matching deliverables
	*/
	public static int countByG_ID(String deliverableState,
		String govAgencyCode, String deliverableType, String applicantIdNo) {
		return getPersistence()
				   .countByG_ID(deliverableState, govAgencyCode,
			deliverableType, applicantIdNo);
	}

	/**
	* Returns the deliverable where deliverableCode = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableCode the deliverable code
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByFB_DCODE(String deliverableCode)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByFB_DCODE(deliverableCode);
	}

	/**
	* Returns the deliverable where deliverableCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByFB_DCODE(String deliverableCode) {
		return getPersistence().fetchByFB_DCODE(deliverableCode);
	}

	/**
	* Returns the deliverable where deliverableCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByFB_DCODE(String deliverableCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByFB_DCODE(deliverableCode, retrieveFromCache);
	}

	/**
	* Removes the deliverable where deliverableCode = &#63; from the database.
	*
	* @param deliverableCode the deliverable code
	* @return the deliverable that was removed
	*/
	public static Deliverable removeByFB_DCODE(String deliverableCode)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().removeByFB_DCODE(deliverableCode);
	}

	/**
	* Returns the number of deliverables where deliverableCode = &#63;.
	*
	* @param deliverableCode the deliverable code
	* @return the number of matching deliverables
	*/
	public static int countByFB_DCODE(String deliverableCode) {
		return getPersistence().countByFB_DCODE(deliverableCode);
	}

	/**
	* Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the matching deliverable
	* @throws NoSuchDeliverableException if a matching deliverable could not be found
	*/
	public static Deliverable findByFB_DCODE_STATE(String deliverableCode,
		String deliverableState)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .findByFB_DCODE_STATE(deliverableCode, deliverableState);
	}

	/**
	* Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) {
		return getPersistence()
				   .fetchByFB_DCODE_STATE(deliverableCode, deliverableState);
	}

	/**
	* Returns the deliverable where deliverableCode = &#63; and deliverableState = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching deliverable, or <code>null</code> if a matching deliverable could not be found
	*/
	public static Deliverable fetchByFB_DCODE_STATE(String deliverableCode,
		String deliverableState, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByFB_DCODE_STATE(deliverableCode, deliverableState,
			retrieveFromCache);
	}

	/**
	* Removes the deliverable where deliverableCode = &#63; and deliverableState = &#63; from the database.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the deliverable that was removed
	*/
	public static Deliverable removeByFB_DCODE_STATE(String deliverableCode,
		String deliverableState)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence()
				   .removeByFB_DCODE_STATE(deliverableCode, deliverableState);
	}

	/**
	* Returns the number of deliverables where deliverableCode = &#63; and deliverableState = &#63;.
	*
	* @param deliverableCode the deliverable code
	* @param deliverableState the deliverable state
	* @return the number of matching deliverables
	*/
	public static int countByFB_DCODE_STATE(String deliverableCode,
		String deliverableState) {
		return getPersistence()
				   .countByFB_DCODE_STATE(deliverableCode, deliverableState);
	}

	/**
	* Caches the deliverable in the entity cache if it is enabled.
	*
	* @param deliverable the deliverable
	*/
	public static void cacheResult(Deliverable deliverable) {
		getPersistence().cacheResult(deliverable);
	}

	/**
	* Caches the deliverables in the entity cache if it is enabled.
	*
	* @param deliverables the deliverables
	*/
	public static void cacheResult(List<Deliverable> deliverables) {
		getPersistence().cacheResult(deliverables);
	}

	/**
	* Creates a new deliverable with the primary key. Does not add the deliverable to the database.
	*
	* @param deliverableId the primary key for the new deliverable
	* @return the new deliverable
	*/
	public static Deliverable create(long deliverableId) {
		return getPersistence().create(deliverableId);
	}

	/**
	* Removes the deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable that was removed
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public static Deliverable remove(long deliverableId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().remove(deliverableId);
	}

	public static Deliverable updateImpl(Deliverable deliverable) {
		return getPersistence().updateImpl(deliverable);
	}

	/**
	* Returns the deliverable with the primary key or throws a {@link NoSuchDeliverableException} if it could not be found.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable
	* @throws NoSuchDeliverableException if a deliverable with the primary key could not be found
	*/
	public static Deliverable findByPrimaryKey(long deliverableId)
		throws org.opencps.dossiermgt.exception.NoSuchDeliverableException {
		return getPersistence().findByPrimaryKey(deliverableId);
	}

	/**
	* Returns the deliverable with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deliverableId the primary key of the deliverable
	* @return the deliverable, or <code>null</code> if a deliverable with the primary key could not be found
	*/
	public static Deliverable fetchByPrimaryKey(long deliverableId) {
		return getPersistence().fetchByPrimaryKey(deliverableId);
	}

	public static java.util.Map<java.io.Serializable, Deliverable> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the deliverables.
	*
	* @return the deliverables
	*/
	public static List<Deliverable> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @return the range of deliverables
	*/
	public static List<Deliverable> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deliverables
	*/
	public static List<Deliverable> findAll(int start, int end,
		OrderByComparator<Deliverable> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverables
	* @param end the upper bound of the range of deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deliverables
	*/
	public static List<Deliverable> findAll(int start, int end,
		OrderByComparator<Deliverable> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the deliverables from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of deliverables.
	*
	* @return the number of deliverables
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DeliverablePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeliverablePersistence, DeliverablePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DeliverablePersistence.class);

		ServiceTracker<DeliverablePersistence, DeliverablePersistence> serviceTracker =
			new ServiceTracker<DeliverablePersistence, DeliverablePersistence>(bundle.getBundleContext(),
				DeliverablePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}