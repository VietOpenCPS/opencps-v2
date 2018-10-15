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

import org.opencps.dossiermgt.model.ActionConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the action config service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ActionConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ActionConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ActionConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class ActionConfigUtil {
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
	public static void clearCache(ActionConfig actionConfig) {
		getPersistence().clearCache(actionConfig);
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
	public static List<ActionConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ActionConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ActionConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ActionConfig update(ActionConfig actionConfig) {
		return getPersistence().update(actionConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ActionConfig update(ActionConfig actionConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(actionConfig, serviceContext);
	}

	/**
	* Returns all the action configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching action configs
	*/
	public static List<ActionConfig> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the action configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of matching action configs
	*/
	public static List<ActionConfig> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the action configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action configs
	*/
	public static List<ActionConfig> findByUuid(String uuid, int start,
		int end, OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the action configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching action configs
	*/
	public static List<ActionConfig> findByUuid(String uuid, int start,
		int end, OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByUuid_First(String uuid,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByUuid_First(String uuid,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByUuid_Last(String uuid,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last action config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByUuid_Last(String uuid,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the action configs before and after the current action config in the ordered set where uuid = &#63;.
	*
	* @param actionConfigId the primary key of the current action config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public static ActionConfig[] findByUuid_PrevAndNext(long actionConfigId,
		String uuid, OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence()
				   .findByUuid_PrevAndNext(actionConfigId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the action configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of action configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching action configs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the action config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActionConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the action config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the action config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the action config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the action config that was removed
	*/
	public static ActionConfig removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of action configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching action configs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching action configs
	*/
	public static List<ActionConfig> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of matching action configs
	*/
	public static List<ActionConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action configs
	*/
	public static List<ActionConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the action configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching action configs
	*/
	public static List<ActionConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the action configs before and after the current action config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param actionConfigId the primary key of the current action config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public static ActionConfig[] findByUuid_C_PrevAndNext(long actionConfigId,
		String uuid, long companyId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(actionConfigId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the action configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of action configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching action configs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the action configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching action configs
	*/
	public static List<ActionConfig> findByF_BY_GID(long groupId) {
		return getPersistence().findByF_BY_GID(groupId);
	}

	/**
	* Returns a range of all the action configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of matching action configs
	*/
	public static List<ActionConfig> findByF_BY_GID(long groupId, int start,
		int end) {
		return getPersistence().findByF_BY_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the action configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching action configs
	*/
	public static List<ActionConfig> findByF_BY_GID(long groupId, int start,
		int end, OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence()
				   .findByF_BY_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the action configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching action configs
	*/
	public static List<ActionConfig> findByF_BY_GID(long groupId, int start,
		int end, OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_BY_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByF_BY_GID_First(long groupId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByF_BY_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByF_BY_GID_First(long groupId,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence().fetchByF_BY_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByF_BY_GID_Last(long groupId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByF_BY_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last action config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByF_BY_GID_Last(long groupId,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence().fetchByF_BY_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the action configs before and after the current action config in the ordered set where groupId = &#63;.
	*
	* @param actionConfigId the primary key of the current action config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public static ActionConfig[] findByF_BY_GID_PrevAndNext(
		long actionConfigId, long groupId,
		OrderByComparator<ActionConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence()
				   .findByF_BY_GID_PrevAndNext(actionConfigId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the action configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_BY_GID(long groupId) {
		getPersistence().removeByF_BY_GID(groupId);
	}

	/**
	* Returns the number of action configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching action configs
	*/
	public static int countByF_BY_GID(long groupId) {
		return getPersistence().countByF_BY_GID(groupId);
	}

	/**
	* Returns the action config where groupId = &#63; and actionCode = &#63; or throws a {@link NoSuchActionConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the matching action config
	* @throws NoSuchActionConfigException if a matching action config could not be found
	*/
	public static ActionConfig findByF_BY_ActionCode(long groupId,
		String actionCode)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByF_BY_ActionCode(groupId, actionCode);
	}

	/**
	* Returns the action config where groupId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByF_BY_ActionCode(long groupId,
		String actionCode) {
		return getPersistence().fetchByF_BY_ActionCode(groupId, actionCode);
	}

	/**
	* Returns the action config where groupId = &#63; and actionCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static ActionConfig fetchByF_BY_ActionCode(long groupId,
		String actionCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_BY_ActionCode(groupId, actionCode,
			retrieveFromCache);
	}

	/**
	* Removes the action config where groupId = &#63; and actionCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the action config that was removed
	*/
	public static ActionConfig removeByF_BY_ActionCode(long groupId,
		String actionCode)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().removeByF_BY_ActionCode(groupId, actionCode);
	}

	/**
	* Returns the number of action configs where groupId = &#63; and actionCode = &#63;.
	*
	* @param groupId the group ID
	* @param actionCode the action code
	* @return the number of matching action configs
	*/
	public static int countByF_BY_ActionCode(long groupId, String actionCode) {
		return getPersistence().countByF_BY_ActionCode(groupId, actionCode);
	}

	/**
	* Caches the action config in the entity cache if it is enabled.
	*
	* @param actionConfig the action config
	*/
	public static void cacheResult(ActionConfig actionConfig) {
		getPersistence().cacheResult(actionConfig);
	}

	/**
	* Caches the action configs in the entity cache if it is enabled.
	*
	* @param actionConfigs the action configs
	*/
	public static void cacheResult(List<ActionConfig> actionConfigs) {
		getPersistence().cacheResult(actionConfigs);
	}

	/**
	* Creates a new action config with the primary key. Does not add the action config to the database.
	*
	* @param actionConfigId the primary key for the new action config
	* @return the new action config
	*/
	public static ActionConfig create(long actionConfigId) {
		return getPersistence().create(actionConfigId);
	}

	/**
	* Removes the action config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config that was removed
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public static ActionConfig remove(long actionConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().remove(actionConfigId);
	}

	public static ActionConfig updateImpl(ActionConfig actionConfig) {
		return getPersistence().updateImpl(actionConfig);
	}

	/**
	* Returns the action config with the primary key or throws a {@link NoSuchActionConfigException} if it could not be found.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config
	* @throws NoSuchActionConfigException if a action config with the primary key could not be found
	*/
	public static ActionConfig findByPrimaryKey(long actionConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchActionConfigException {
		return getPersistence().findByPrimaryKey(actionConfigId);
	}

	/**
	* Returns the action config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config, or <code>null</code> if a action config with the primary key could not be found
	*/
	public static ActionConfig fetchByPrimaryKey(long actionConfigId) {
		return getPersistence().fetchByPrimaryKey(actionConfigId);
	}

	public static java.util.Map<java.io.Serializable, ActionConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the action configs.
	*
	* @return the action configs
	*/
	public static List<ActionConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of action configs
	*/
	public static List<ActionConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of action configs
	*/
	public static List<ActionConfig> findAll(int start, int end,
		OrderByComparator<ActionConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of action configs
	*/
	public static List<ActionConfig> findAll(int start, int end,
		OrderByComparator<ActionConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the action configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of action configs.
	*
	* @return the number of action configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ActionConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActionConfigPersistence, ActionConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ActionConfigPersistence.class);

		ServiceTracker<ActionConfigPersistence, ActionConfigPersistence> serviceTracker =
			new ServiceTracker<ActionConfigPersistence, ActionConfigPersistence>(bundle.getBundleContext(),
				ActionConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}