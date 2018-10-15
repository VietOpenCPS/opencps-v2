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

import org.opencps.dossiermgt.model.StepConfig;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the step config service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.StepConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see StepConfigPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.StepConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class StepConfigUtil {
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
	public static void clearCache(StepConfig stepConfig) {
		getPersistence().clearCache(stepConfig);
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
	public static List<StepConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StepConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StepConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StepConfig update(StepConfig stepConfig) {
		return getPersistence().update(stepConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StepConfig update(StepConfig stepConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(stepConfig, serviceContext);
	}

	/**
	* Returns all the step configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching step configs
	*/
	public static List<StepConfig> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the step configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public static List<StepConfig> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the step configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByUuid(String uuid, int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByUuid_First(String uuid,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByUuid_First(String uuid,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByUuid_Last(String uuid,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByUuid_Last(String uuid,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the step configs before and after the current step config in the ordered set where uuid = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public static StepConfig[] findByUuid_PrevAndNext(long stepConfigId,
		String uuid, OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByUuid_PrevAndNext(stepConfigId, uuid, orderByComparator);
	}

	/**
	* Removes all the step configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of step configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching step configs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the step config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the step config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the step config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the step config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the step config that was removed
	*/
	public static StepConfig removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of step configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching step configs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching step configs
	*/
	public static List<StepConfig> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public static List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the step configs before and after the current step config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public static StepConfig[] findByUuid_C_PrevAndNext(long stepConfigId,
		String uuid, long companyId,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(stepConfigId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the step configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of step configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching step configs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByF_BY_stepCode(long groupId, String stepCode)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByF_BY_stepCode(groupId, stepCode);
	}

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_BY_stepCode(long groupId, String stepCode) {
		return getPersistence().fetchByF_BY_stepCode(groupId, stepCode);
	}

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_BY_stepCode(long groupId,
		String stepCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_BY_stepCode(groupId, stepCode, retrieveFromCache);
	}

	/**
	* Removes the step config where groupId = &#63; and stepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the step config that was removed
	*/
	public static StepConfig removeByF_BY_stepCode(long groupId, String stepCode)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().removeByF_BY_stepCode(groupId, stepCode);
	}

	/**
	* Returns the number of step configs where groupId = &#63; and stepCode = &#63;.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the number of matching step configs
	*/
	public static int countByF_BY_stepCode(long groupId, String stepCode) {
		return getPersistence().countByF_BY_stepCode(groupId, stepCode);
	}

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByF_GT_STEP(long groupId, String stepCode)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByF_GT_STEP(groupId, stepCode);
	}

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_GT_STEP(long groupId, String stepCode) {
		return getPersistence().fetchByF_GT_STEP(groupId, stepCode);
	}

	/**
	* Returns the step config where groupId = &#63; and stepCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_GT_STEP(long groupId, String stepCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GT_STEP(groupId, stepCode, retrieveFromCache);
	}

	/**
	* Removes the step config where groupId = &#63; and stepCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the step config that was removed
	*/
	public static StepConfig removeByF_GT_STEP(long groupId, String stepCode)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().removeByF_GT_STEP(groupId, stepCode);
	}

	/**
	* Returns the number of step configs where groupId = &#63; and stepCode = &#63;.
	*
	* @param groupId the group ID
	* @param stepCode the step code
	* @return the number of matching step configs
	*/
	public static int countByF_GT_STEP(long groupId, String stepCode) {
		return getPersistence().countByF_GT_STEP(groupId, stepCode);
	}

	/**
	* Returns all the step configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching step configs
	*/
	public static List<StepConfig> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the step configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public static List<StepConfig> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the step configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByF_GID(long groupId, int start,
		int end, OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step configs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByF_GID(long groupId, int start,
		int end, OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByF_GID_First(long groupId,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_GID_First(long groupId,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByF_GID_Last(long groupId,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_GID_Last(long groupId,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the step configs before and after the current step config in the ordered set where groupId = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public static StepConfig[] findByF_GID_PrevAndNext(long stepConfigId,
		long groupId, OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(stepConfigId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the step configs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of step configs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching step configs
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Returns all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @return the matching step configs
	*/
	public static List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus) {
		return getPersistence()
				   .findByF_MS_SS(groupId, dossierStatus, dossierSubStatus);
	}

	/**
	* Returns a range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of matching step configs
	*/
	public static List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus, int start, int end) {
		return getPersistence()
				   .findByF_MS_SS(groupId, dossierStatus, dossierSubStatus,
			start, end);
	}

	/**
	* Returns an ordered range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus, int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .findByF_MS_SS(groupId, dossierStatus, dossierSubStatus,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching step configs
	*/
	public static List<StepConfig> findByF_MS_SS(long groupId,
		String dossierStatus, String dossierSubStatus, int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_MS_SS(groupId, dossierStatus, dossierSubStatus,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByF_MS_SS_First(long groupId,
		String dossierStatus, String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByF_MS_SS_First(groupId, dossierStatus,
			dossierSubStatus, orderByComparator);
	}

	/**
	* Returns the first step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_MS_SS_First(long groupId,
		String dossierStatus, String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_MS_SS_First(groupId, dossierStatus,
			dossierSubStatus, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config
	* @throws NoSuchStepConfigException if a matching step config could not be found
	*/
	public static StepConfig findByF_MS_SS_Last(long groupId,
		String dossierStatus, String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByF_MS_SS_Last(groupId, dossierStatus,
			dossierSubStatus, orderByComparator);
	}

	/**
	* Returns the last step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static StepConfig fetchByF_MS_SS_Last(long groupId,
		String dossierStatus, String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence()
				   .fetchByF_MS_SS_Last(groupId, dossierStatus,
			dossierSubStatus, orderByComparator);
	}

	/**
	* Returns the step configs before and after the current step config in the ordered set where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param stepConfigId the primary key of the current step config
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public static StepConfig[] findByF_MS_SS_PrevAndNext(long stepConfigId,
		long groupId, String dossierStatus, String dossierSubStatus,
		OrderByComparator<StepConfig> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence()
				   .findByF_MS_SS_PrevAndNext(stepConfigId, groupId,
			dossierStatus, dossierSubStatus, orderByComparator);
	}

	/**
	* Removes all the step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	*/
	public static void removeByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus) {
		getPersistence()
			.removeByF_MS_SS(groupId, dossierStatus, dossierSubStatus);
	}

	/**
	* Returns the number of step configs where groupId = &#63; and dossierStatus = &#63; and dossierSubStatus = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param dossierSubStatus the dossier sub status
	* @return the number of matching step configs
	*/
	public static int countByF_MS_SS(long groupId, String dossierStatus,
		String dossierSubStatus) {
		return getPersistence()
				   .countByF_MS_SS(groupId, dossierStatus, dossierSubStatus);
	}

	/**
	* Caches the step config in the entity cache if it is enabled.
	*
	* @param stepConfig the step config
	*/
	public static void cacheResult(StepConfig stepConfig) {
		getPersistence().cacheResult(stepConfig);
	}

	/**
	* Caches the step configs in the entity cache if it is enabled.
	*
	* @param stepConfigs the step configs
	*/
	public static void cacheResult(List<StepConfig> stepConfigs) {
		getPersistence().cacheResult(stepConfigs);
	}

	/**
	* Creates a new step config with the primary key. Does not add the step config to the database.
	*
	* @param stepConfigId the primary key for the new step config
	* @return the new step config
	*/
	public static StepConfig create(long stepConfigId) {
		return getPersistence().create(stepConfigId);
	}

	/**
	* Removes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config that was removed
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public static StepConfig remove(long stepConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().remove(stepConfigId);
	}

	public static StepConfig updateImpl(StepConfig stepConfig) {
		return getPersistence().updateImpl(stepConfig);
	}

	/**
	* Returns the step config with the primary key or throws a {@link NoSuchStepConfigException} if it could not be found.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config
	* @throws NoSuchStepConfigException if a step config with the primary key could not be found
	*/
	public static StepConfig findByPrimaryKey(long stepConfigId)
		throws org.opencps.dossiermgt.exception.NoSuchStepConfigException {
		return getPersistence().findByPrimaryKey(stepConfigId);
	}

	/**
	* Returns the step config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config, or <code>null</code> if a step config with the primary key could not be found
	*/
	public static StepConfig fetchByPrimaryKey(long stepConfigId) {
		return getPersistence().fetchByPrimaryKey(stepConfigId);
	}

	public static java.util.Map<java.io.Serializable, StepConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the step configs.
	*
	* @return the step configs
	*/
	public static List<StepConfig> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of step configs
	*/
	public static List<StepConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of step configs
	*/
	public static List<StepConfig> findAll(int start, int end,
		OrderByComparator<StepConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of step configs
	*/
	public static List<StepConfig> findAll(int start, int end,
		OrderByComparator<StepConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the step configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of step configs.
	*
	* @return the number of step configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StepConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StepConfigPersistence, StepConfigPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(StepConfigPersistence.class);

		ServiceTracker<StepConfigPersistence, StepConfigPersistence> serviceTracker =
			new ServiceTracker<StepConfigPersistence, StepConfigPersistence>(bundle.getBundleContext(),
				StepConfigPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}