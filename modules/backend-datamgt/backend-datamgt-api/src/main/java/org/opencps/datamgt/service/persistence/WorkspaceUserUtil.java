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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.datamgt.model.WorkspaceUser;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the workspace user service. This utility wraps {@link org.mobilink.backend.datamgt.service.persistence.impl.WorkspaceUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see WorkspaceUserPersistence
 * @see org.mobilink.backend.datamgt.service.persistence.impl.WorkspaceUserPersistenceImpl
 * @generated
 */
@ProviderType
public class WorkspaceUserUtil {
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
	public static void clearCache(WorkspaceUser workspaceUser) {
		getPersistence().clearCache(workspaceUser);
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
	public static List<WorkspaceUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WorkspaceUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WorkspaceUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WorkspaceUser update(WorkspaceUser workspaceUser) {
		return getPersistence().update(workspaceUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WorkspaceUser update(WorkspaceUser workspaceUser,
		ServiceContext serviceContext) {
		return getPersistence().update(workspaceUser, serviceContext);
	}

	/**
	* Returns all the workspace users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the workspace users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByUuid_First(java.lang.String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByUuid_Last(java.lang.String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the workspace users before and after the current workspace user in the ordered set where uuid = &#63;.
	*
	* @param workspaceUserId the primary key of the current workspace user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public static WorkspaceUser[] findByUuid_PrevAndNext(long workspaceUserId,
		java.lang.String uuid,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByUuid_PrevAndNext(workspaceUserId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the workspace users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of workspace users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching workspace users
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the workspace user where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchWorkspaceUserException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the workspace user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the workspace user where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the workspace user where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the workspace user that was removed
	*/
	public static WorkspaceUser removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of workspace users where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching workspace users
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the workspace users where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace users
	*/
	public static List<WorkspaceUser> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the workspace users before and after the current workspace user in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param workspaceUserId the primary key of the current workspace user
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public static WorkspaceUser[] findByUuid_C_PrevAndNext(
		long workspaceUserId, java.lang.String uuid, long companyId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(workspaceUserId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the workspace users where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of workspace users where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching workspace users
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the workspace users where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @return the matching workspace users
	*/
	public static List<WorkspaceUser> findByF_workspaceId(long workspaceId) {
		return getPersistence().findByF_workspaceId(workspaceId);
	}

	/**
	* Returns a range of all the workspace users where workspaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workspaceId the workspace ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of matching workspace users
	*/
	public static List<WorkspaceUser> findByF_workspaceId(long workspaceId,
		int start, int end) {
		return getPersistence().findByF_workspaceId(workspaceId, start, end);
	}

	/**
	* Returns an ordered range of all the workspace users where workspaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workspaceId the workspace ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching workspace users
	*/
	public static List<WorkspaceUser> findByF_workspaceId(long workspaceId,
		int start, int end, OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .findByF_workspaceId(workspaceId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the workspace users where workspaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param workspaceId the workspace ID
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching workspace users
	*/
	public static List<WorkspaceUser> findByF_workspaceId(long workspaceId,
		int start, int end, OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_workspaceId(workspaceId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByF_workspaceId_First(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByF_workspaceId_First(workspaceId, orderByComparator);
	}

	/**
	* Returns the first workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByF_workspaceId_First(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .fetchByF_workspaceId_First(workspaceId, orderByComparator);
	}

	/**
	* Returns the last workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user
	* @throws NoSuchWorkspaceUserException if a matching workspace user could not be found
	*/
	public static WorkspaceUser findByF_workspaceId_Last(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByF_workspaceId_Last(workspaceId, orderByComparator);
	}

	/**
	* Returns the last workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	public static WorkspaceUser fetchByF_workspaceId_Last(long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence()
				   .fetchByF_workspaceId_Last(workspaceId, orderByComparator);
	}

	/**
	* Returns the workspace users before and after the current workspace user in the ordered set where workspaceId = &#63;.
	*
	* @param workspaceUserId the primary key of the current workspace user
	* @param workspaceId the workspace ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public static WorkspaceUser[] findByF_workspaceId_PrevAndNext(
		long workspaceUserId, long workspaceId,
		OrderByComparator<WorkspaceUser> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence()
				   .findByF_workspaceId_PrevAndNext(workspaceUserId,
			workspaceId, orderByComparator);
	}

	/**
	* Removes all the workspace users where workspaceId = &#63; from the database.
	*
	* @param workspaceId the workspace ID
	*/
	public static void removeByF_workspaceId(long workspaceId) {
		getPersistence().removeByF_workspaceId(workspaceId);
	}

	/**
	* Returns the number of workspace users where workspaceId = &#63;.
	*
	* @param workspaceId the workspace ID
	* @return the number of matching workspace users
	*/
	public static int countByF_workspaceId(long workspaceId) {
		return getPersistence().countByF_workspaceId(workspaceId);
	}

	/**
	* Caches the workspace user in the entity cache if it is enabled.
	*
	* @param workspaceUser the workspace user
	*/
	public static void cacheResult(WorkspaceUser workspaceUser) {
		getPersistence().cacheResult(workspaceUser);
	}

	/**
	* Caches the workspace users in the entity cache if it is enabled.
	*
	* @param workspaceUsers the workspace users
	*/
	public static void cacheResult(List<WorkspaceUser> workspaceUsers) {
		getPersistence().cacheResult(workspaceUsers);
	}

	/**
	* Creates a new workspace user with the primary key. Does not add the workspace user to the database.
	*
	* @param workspaceUserId the primary key for the new workspace user
	* @return the new workspace user
	*/
	public static WorkspaceUser create(long workspaceUserId) {
		return getPersistence().create(workspaceUserId);
	}

	/**
	* Removes the workspace user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user that was removed
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public static WorkspaceUser remove(long workspaceUserId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence().remove(workspaceUserId);
	}

	public static WorkspaceUser updateImpl(WorkspaceUser workspaceUser) {
		return getPersistence().updateImpl(workspaceUser);
	}

	/**
	* Returns the workspace user with the primary key or throws a {@link NoSuchWorkspaceUserException} if it could not be found.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user
	* @throws NoSuchWorkspaceUserException if a workspace user with the primary key could not be found
	*/
	public static WorkspaceUser findByPrimaryKey(long workspaceUserId)
		throws org.opencps.datamgt.exception.NoSuchWorkspaceUserException {
		return getPersistence().findByPrimaryKey(workspaceUserId);
	}

	/**
	* Returns the workspace user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user, or <code>null</code> if a workspace user with the primary key could not be found
	*/
	public static WorkspaceUser fetchByPrimaryKey(long workspaceUserId) {
		return getPersistence().fetchByPrimaryKey(workspaceUserId);
	}

	public static java.util.Map<java.io.Serializable, WorkspaceUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the workspace users.
	*
	* @return the workspace users
	*/
	public static List<WorkspaceUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of workspace users
	*/
	public static List<WorkspaceUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of workspace users
	*/
	public static List<WorkspaceUser> findAll(int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of workspace users
	*/
	public static List<WorkspaceUser> findAll(int start, int end,
		OrderByComparator<WorkspaceUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the workspace users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of workspace users.
	*
	* @return the number of workspace users
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WorkspaceUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkspaceUserPersistence, WorkspaceUserPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WorkspaceUserPersistence.class);
}