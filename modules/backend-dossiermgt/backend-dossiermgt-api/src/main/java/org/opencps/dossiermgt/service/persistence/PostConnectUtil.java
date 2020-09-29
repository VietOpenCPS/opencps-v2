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

import org.opencps.dossiermgt.model.PostConnect;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the post connect service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.PostConnectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see PostConnectPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.PostConnectPersistenceImpl
 * @generated
 */
@ProviderType
public class PostConnectUtil {
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
	public static void clearCache(PostConnect postConnect) {
		getPersistence().clearCache(postConnect);
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
	public static List<PostConnect> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PostConnect> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PostConnect> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PostConnect update(PostConnect postConnect) {
		return getPersistence().update(postConnect);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PostConnect update(PostConnect postConnect,
		ServiceContext serviceContext) {
		return getPersistence().update(postConnect, serviceContext);
	}

	/**
	* Returns all the post connects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching post connects
	*/
	public static List<PostConnect> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the post connects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public static List<PostConnect> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the post connects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByUuid(String uuid, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the post connects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByUuid(String uuid, int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByUuid_First(String uuid,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByUuid_First(String uuid,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByUuid_Last(String uuid,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByUuid_Last(String uuid,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the post connects before and after the current post connect in the ordered set where uuid = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect[] findByUuid_PrevAndNext(long postConnectId,
		String uuid, OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByUuid_PrevAndNext(postConnectId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the post connects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of post connects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching post connects
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the post connect where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the post connect where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the post connect where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the post connect where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the post connect that was removed
	*/
	public static PostConnect removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of post connects where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching post connects
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching post connects
	*/
	public static List<PostConnect> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public static List<PostConnect> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the post connects before and after the current post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect[] findByUuid_C_PrevAndNext(long postConnectId,
		String uuid, long companyId,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(postConnectId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the post connects where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of post connects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching post connects
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @return the matching post connects
	*/
	public static List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState) {
		return getPersistence().findByF_GID_SYNC_STATE(groupId, syncState);
	}

	/**
	* Returns a range of all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public static List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end) {
		return getPersistence()
				   .findByF_GID_SYNC_STATE(groupId, syncState, start, end);
	}

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SYNC_STATE(groupId, syncState, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SYNC_STATE(groupId, syncState, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_GID_SYNC_STATE_First(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_GID_SYNC_STATE_First(groupId, syncState,
			orderByComparator);
	}

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_GID_SYNC_STATE_First(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SYNC_STATE_First(groupId, syncState,
			orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_GID_SYNC_STATE_Last(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_GID_SYNC_STATE_Last(groupId, syncState,
			orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_GID_SYNC_STATE_Last(long groupId,
		int syncState, OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SYNC_STATE_Last(groupId, syncState,
			orderByComparator);
	}

	/**
	* Returns the post connects before and after the current post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect[] findByF_GID_SYNC_STATE_PrevAndNext(
		long postConnectId, long groupId, int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_GID_SYNC_STATE_PrevAndNext(postConnectId, groupId,
			syncState, orderByComparator);
	}

	/**
	* Removes all the post connects where groupId = &#63; and syncState = &#63; from the database.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	*/
	public static void removeByF_GID_SYNC_STATE(long groupId, int syncState) {
		getPersistence().removeByF_GID_SYNC_STATE(groupId, syncState);
	}

	/**
	* Returns the number of post connects where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @return the number of matching post connects
	*/
	public static int countByF_GID_SYNC_STATE(long groupId, int syncState) {
		return getPersistence().countByF_GID_SYNC_STATE(groupId, syncState);
	}

	/**
	* Returns all the post connects where syncState = &#63;.
	*
	* @param syncState the sync state
	* @return the matching post connects
	*/
	public static List<PostConnect> findByF_SYNC_STATE(int syncState) {
		return getPersistence().findByF_SYNC_STATE(syncState);
	}

	/**
	* Returns a range of all the post connects where syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public static List<PostConnect> findByF_SYNC_STATE(int syncState,
		int start, int end) {
		return getPersistence().findByF_SYNC_STATE(syncState, start, end);
	}

	/**
	* Returns an ordered range of all the post connects where syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByF_SYNC_STATE(int syncState,
		int start, int end, OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .findByF_SYNC_STATE(syncState, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the post connects where syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByF_SYNC_STATE(int syncState,
		int start, int end, OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_SYNC_STATE(syncState, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_SYNC_STATE_First(int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_SYNC_STATE_First(syncState, orderByComparator);
	}

	/**
	* Returns the first post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_SYNC_STATE_First(int syncState,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByF_SYNC_STATE_First(syncState, orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_SYNC_STATE_Last(int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_SYNC_STATE_Last(syncState, orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_SYNC_STATE_Last(int syncState,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByF_SYNC_STATE_Last(syncState, orderByComparator);
	}

	/**
	* Returns the post connects before and after the current post connect in the ordered set where syncState = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect[] findByF_SYNC_STATE_PrevAndNext(
		long postConnectId, int syncState,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_SYNC_STATE_PrevAndNext(postConnectId, syncState,
			orderByComparator);
	}

	/**
	* Removes all the post connects where syncState = &#63; from the database.
	*
	* @param syncState the sync state
	*/
	public static void removeByF_SYNC_STATE(int syncState) {
		getPersistence().removeByF_SYNC_STATE(syncState);
	}

	/**
	* Returns the number of post connects where syncState = &#63;.
	*
	* @param syncState the sync state
	* @return the number of matching post connects
	*/
	public static int countByF_SYNC_STATE(int syncState) {
		return getPersistence().countByF_SYNC_STATE(syncState);
	}

	/**
	* Returns the post connect where orderNumber = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param orderNumber the order number
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_ORDER_NUMBER(String orderNumber)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().findByF_ORDER_NUMBER(orderNumber);
	}

	/**
	* Returns the post connect where orderNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param orderNumber the order number
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_ORDER_NUMBER(String orderNumber) {
		return getPersistence().fetchByF_ORDER_NUMBER(orderNumber);
	}

	/**
	* Returns the post connect where orderNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param orderNumber the order number
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_ORDER_NUMBER(String orderNumber,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_ORDER_NUMBER(orderNumber, retrieveFromCache);
	}

	/**
	* Removes the post connect where orderNumber = &#63; from the database.
	*
	* @param orderNumber the order number
	* @return the post connect that was removed
	*/
	public static PostConnect removeByF_ORDER_NUMBER(String orderNumber)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().removeByF_ORDER_NUMBER(orderNumber);
	}

	/**
	* Returns the number of post connects where orderNumber = &#63;.
	*
	* @param orderNumber the order number
	* @return the number of matching post connects
	*/
	public static int countByF_ORDER_NUMBER(String orderNumber) {
		return getPersistence().countByF_ORDER_NUMBER(orderNumber);
	}

	/**
	* Returns the post connect where orderNumber = &#63; and postStatus = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_ORDER_NUMBER_POST_STATUS(
		String orderNumber, int postStatus)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_ORDER_NUMBER_POST_STATUS(orderNumber, postStatus);
	}

	/**
	* Returns the post connect where orderNumber = &#63; and postStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_ORDER_NUMBER_POST_STATUS(
		String orderNumber, int postStatus) {
		return getPersistence()
				   .fetchByF_ORDER_NUMBER_POST_STATUS(orderNumber, postStatus);
	}

	/**
	* Returns the post connect where orderNumber = &#63; and postStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_ORDER_NUMBER_POST_STATUS(
		String orderNumber, int postStatus, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_ORDER_NUMBER_POST_STATUS(orderNumber, postStatus,
			retrieveFromCache);
	}

	/**
	* Removes the post connect where orderNumber = &#63; and postStatus = &#63; from the database.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the post connect that was removed
	*/
	public static PostConnect removeByF_ORDER_NUMBER_POST_STATUS(
		String orderNumber, int postStatus)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .removeByF_ORDER_NUMBER_POST_STATUS(orderNumber, postStatus);
	}

	/**
	* Returns the number of post connects where orderNumber = &#63; and postStatus = &#63;.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the number of matching post connects
	*/
	public static int countByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) {
		return getPersistence()
				   .countByF_ORDER_NUMBER_POST_STATUS(orderNumber, postStatus);
	}

	/**
	* Returns all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching post connects
	*/
	public static List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId) {
		return getPersistence().findByF_POST_BY_DOSSIER_ID(groupId, dossierId);
	}

	/**
	* Returns a range of all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public static List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId, int start, int end) {
		return getPersistence()
				   .findByF_POST_BY_DOSSIER_ID(groupId, dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId, int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .findByF_POST_BY_DOSSIER_ID(groupId, dossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public static List<PostConnect> findByF_POST_BY_DOSSIER_ID(long groupId,
		long dossierId, int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_POST_BY_DOSSIER_ID(groupId, dossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_POST_BY_DOSSIER_ID_First(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_POST_BY_DOSSIER_ID_First(groupId, dossierId,
			orderByComparator);
	}

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_POST_BY_DOSSIER_ID_First(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByF_POST_BY_DOSSIER_ID_First(groupId, dossierId,
			orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_POST_BY_DOSSIER_ID_Last(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_POST_BY_DOSSIER_ID_Last(groupId, dossierId,
			orderByComparator);
	}

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_POST_BY_DOSSIER_ID_Last(long groupId,
		long dossierId, OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence()
				   .fetchByF_POST_BY_DOSSIER_ID_Last(groupId, dossierId,
			orderByComparator);
	}

	/**
	* Returns the post connects before and after the current post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect[] findByF_POST_BY_DOSSIER_ID_PrevAndNext(
		long postConnectId, long groupId, long dossierId,
		OrderByComparator<PostConnect> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_POST_BY_DOSSIER_ID_PrevAndNext(postConnectId,
			groupId, dossierId, orderByComparator);
	}

	/**
	* Removes all the post connects where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public static void removeByF_POST_BY_DOSSIER_ID(long groupId, long dossierId) {
		getPersistence().removeByF_POST_BY_DOSSIER_ID(groupId, dossierId);
	}

	/**
	* Returns the number of post connects where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching post connects
	*/
	public static int countByF_POST_BY_DOSSIER_ID(long groupId, long dossierId) {
		return getPersistence().countByF_POST_BY_DOSSIER_ID(groupId, dossierId);
	}

	/**
	* Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public static PostConnect findByF_POST_BY_D_TYPE(long groupId,
		long dossierId, int postType)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .findByF_POST_BY_D_TYPE(groupId, dossierId, postType);
	}

	/**
	* Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_POST_BY_D_TYPE(long groupId,
		long dossierId, int postType) {
		return getPersistence()
				   .fetchByF_POST_BY_D_TYPE(groupId, dossierId, postType);
	}

	/**
	* Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static PostConnect fetchByF_POST_BY_D_TYPE(long groupId,
		long dossierId, int postType, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_POST_BY_D_TYPE(groupId, dossierId, postType,
			retrieveFromCache);
	}

	/**
	* Removes the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the post connect that was removed
	*/
	public static PostConnect removeByF_POST_BY_D_TYPE(long groupId,
		long dossierId, int postType)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence()
				   .removeByF_POST_BY_D_TYPE(groupId, dossierId, postType);
	}

	/**
	* Returns the number of post connects where groupId = &#63; and dossierId = &#63; and postType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the number of matching post connects
	*/
	public static int countByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) {
		return getPersistence()
				   .countByF_POST_BY_D_TYPE(groupId, dossierId, postType);
	}

	/**
	* Caches the post connect in the entity cache if it is enabled.
	*
	* @param postConnect the post connect
	*/
	public static void cacheResult(PostConnect postConnect) {
		getPersistence().cacheResult(postConnect);
	}

	/**
	* Caches the post connects in the entity cache if it is enabled.
	*
	* @param postConnects the post connects
	*/
	public static void cacheResult(List<PostConnect> postConnects) {
		getPersistence().cacheResult(postConnects);
	}

	/**
	* Creates a new post connect with the primary key. Does not add the post connect to the database.
	*
	* @param postConnectId the primary key for the new post connect
	* @return the new post connect
	*/
	public static PostConnect create(long postConnectId) {
		return getPersistence().create(postConnectId);
	}

	/**
	* Removes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect that was removed
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect remove(long postConnectId)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().remove(postConnectId);
	}

	public static PostConnect updateImpl(PostConnect postConnect) {
		return getPersistence().updateImpl(postConnect);
	}

	/**
	* Returns the post connect with the primary key or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public static PostConnect findByPrimaryKey(long postConnectId)
		throws org.opencps.dossiermgt.exception.NoSuchPostConnectException {
		return getPersistence().findByPrimaryKey(postConnectId);
	}

	/**
	* Returns the post connect with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect, or <code>null</code> if a post connect with the primary key could not be found
	*/
	public static PostConnect fetchByPrimaryKey(long postConnectId) {
		return getPersistence().fetchByPrimaryKey(postConnectId);
	}

	public static java.util.Map<java.io.Serializable, PostConnect> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the post connects.
	*
	* @return the post connects
	*/
	public static List<PostConnect> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of post connects
	*/
	public static List<PostConnect> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of post connects
	*/
	public static List<PostConnect> findAll(int start, int end,
		OrderByComparator<PostConnect> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of post connects
	*/
	public static List<PostConnect> findAll(int start, int end,
		OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the post connects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of post connects.
	*
	* @return the number of post connects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PostConnectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PostConnectPersistence, PostConnectPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PostConnectPersistence.class);

		ServiceTracker<PostConnectPersistence, PostConnectPersistence> serviceTracker =
			new ServiceTracker<PostConnectPersistence, PostConnectPersistence>(bundle.getBundleContext(),
				PostConnectPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}