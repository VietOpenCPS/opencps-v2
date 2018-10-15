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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.JobPos;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the job pos service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.JobPosPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see JobPosPersistence
 * @see org.opencps.usermgt.service.persistence.impl.JobPosPersistenceImpl
 * @generated
 */
@ProviderType
public class JobPosUtil {
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
	public static void clearCache(JobPos jobPos) {
		getPersistence().clearCache(jobPos);
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
	public static List<JobPos> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JobPos> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JobPos> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<JobPos> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JobPos update(JobPos jobPos) {
		return getPersistence().update(jobPos);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JobPos update(JobPos jobPos, ServiceContext serviceContext) {
		return getPersistence().update(jobPos, serviceContext);
	}

	/**
	* Returns all the job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching job poses
	*/
	public static List<JobPos> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the job poses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public static List<JobPos> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the job poses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public static List<JobPos> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPos> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the job poses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public static List<JobPos> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPos> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByUuid_First(String uuid,
		OrderByComparator<JobPos> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByUuid_First(String uuid,
		OrderByComparator<JobPos> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByUuid_Last(String uuid,
		OrderByComparator<JobPos> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByUuid_Last(String uuid,
		OrderByComparator<JobPos> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the job poses before and after the current job pos in the ordered set where uuid = &#63;.
	*
	* @param jobPosId the primary key of the current job pos
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public static JobPos[] findByUuid_PrevAndNext(long jobPosId, String uuid,
		OrderByComparator<JobPos> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence()
				   .findByUuid_PrevAndNext(jobPosId, uuid, orderByComparator);
	}

	/**
	* Removes all the job poses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching job poses
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the job pos where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the job pos where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the job pos that was removed
	*/
	public static JobPos removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of job poses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching job poses
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching job poses
	*/
	public static List<JobPos> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the job poses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public static List<JobPos> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the job poses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public static List<JobPos> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<JobPos> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the job poses where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public static List<JobPos> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPos> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the job poses before and after the current job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param jobPosId the primary key of the current job pos
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public static JobPos[] findByUuid_C_PrevAndNext(long jobPosId, String uuid,
		long companyId, OrderByComparator<JobPos> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(jobPosId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the job poses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching job poses
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the job pos where groupId = &#63; and title = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByF_title(long groupId, String title)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByF_title(groupId, title);
	}

	/**
	* Returns the job pos where groupId = &#63; and title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByF_title(long groupId, String title) {
		return getPersistence().fetchByF_title(groupId, title);
	}

	/**
	* Returns the job pos where groupId = &#63; and title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param title the title
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByF_title(long groupId, String title,
		boolean retrieveFromCache) {
		return getPersistence().fetchByF_title(groupId, title, retrieveFromCache);
	}

	/**
	* Removes the job pos where groupId = &#63; and title = &#63; from the database.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the job pos that was removed
	*/
	public static JobPos removeByF_title(long groupId, String title)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().removeByF_title(groupId, title);
	}

	/**
	* Returns the number of job poses where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the number of matching job poses
	*/
	public static int countByF_title(long groupId, String title) {
		return getPersistence().countByF_title(groupId, title);
	}

	/**
	* Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByF_mappingRoleId(long groupId, long mappingRoleId)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByF_mappingRoleId(groupId, mappingRoleId);
	}

	/**
	* Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByF_mappingRoleId(long groupId, long mappingRoleId) {
		return getPersistence().fetchByF_mappingRoleId(groupId, mappingRoleId);
	}

	/**
	* Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByF_mappingRoleId(long groupId,
		long mappingRoleId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_mappingRoleId(groupId, mappingRoleId,
			retrieveFromCache);
	}

	/**
	* Removes the job pos where groupId = &#63; and mappingRoleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the job pos that was removed
	*/
	public static JobPos removeByF_mappingRoleId(long groupId,
		long mappingRoleId)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().removeByF_mappingRoleId(groupId, mappingRoleId);
	}

	/**
	* Returns the number of job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the number of matching job poses
	*/
	public static int countByF_mappingRoleId(long groupId, long mappingRoleId) {
		return getPersistence().countByF_mappingRoleId(groupId, mappingRoleId);
	}

	/**
	* Returns the job pos where groupId = &#63; and jobPosCode = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public static JobPos findByF_CODE(long groupId, String jobPosCode)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByF_CODE(groupId, jobPosCode);
	}

	/**
	* Returns the job pos where groupId = &#63; and jobPosCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByF_CODE(long groupId, String jobPosCode) {
		return getPersistence().fetchByF_CODE(groupId, jobPosCode);
	}

	/**
	* Returns the job pos where groupId = &#63; and jobPosCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static JobPos fetchByF_CODE(long groupId, String jobPosCode,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_CODE(groupId, jobPosCode, retrieveFromCache);
	}

	/**
	* Removes the job pos where groupId = &#63; and jobPosCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the job pos that was removed
	*/
	public static JobPos removeByF_CODE(long groupId, String jobPosCode)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().removeByF_CODE(groupId, jobPosCode);
	}

	/**
	* Returns the number of job poses where groupId = &#63; and jobPosCode = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the number of matching job poses
	*/
	public static int countByF_CODE(long groupId, String jobPosCode) {
		return getPersistence().countByF_CODE(groupId, jobPosCode);
	}

	/**
	* Caches the job pos in the entity cache if it is enabled.
	*
	* @param jobPos the job pos
	*/
	public static void cacheResult(JobPos jobPos) {
		getPersistence().cacheResult(jobPos);
	}

	/**
	* Caches the job poses in the entity cache if it is enabled.
	*
	* @param jobPoses the job poses
	*/
	public static void cacheResult(List<JobPos> jobPoses) {
		getPersistence().cacheResult(jobPoses);
	}

	/**
	* Creates a new job pos with the primary key. Does not add the job pos to the database.
	*
	* @param jobPosId the primary key for the new job pos
	* @return the new job pos
	*/
	public static JobPos create(long jobPosId) {
		return getPersistence().create(jobPosId);
	}

	/**
	* Removes the job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos that was removed
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public static JobPos remove(long jobPosId)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().remove(jobPosId);
	}

	public static JobPos updateImpl(JobPos jobPos) {
		return getPersistence().updateImpl(jobPos);
	}

	/**
	* Returns the job pos with the primary key or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public static JobPos findByPrimaryKey(long jobPosId)
		throws org.opencps.usermgt.exception.NoSuchJobPosException {
		return getPersistence().findByPrimaryKey(jobPosId);
	}

	/**
	* Returns the job pos with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos, or <code>null</code> if a job pos with the primary key could not be found
	*/
	public static JobPos fetchByPrimaryKey(long jobPosId) {
		return getPersistence().fetchByPrimaryKey(jobPosId);
	}

	public static java.util.Map<java.io.Serializable, JobPos> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the job poses.
	*
	* @return the job poses
	*/
	public static List<JobPos> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of job poses
	*/
	public static List<JobPos> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of job poses
	*/
	public static List<JobPos> findAll(int start, int end,
		OrderByComparator<JobPos> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of job poses
	*/
	public static List<JobPos> findAll(int start, int end,
		OrderByComparator<JobPos> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the job poses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of job poses.
	*
	* @return the number of job poses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JobPosPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JobPosPersistence, JobPosPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JobPosPersistence.class);

		ServiceTracker<JobPosPersistence, JobPosPersistence> serviceTracker = new ServiceTracker<JobPosPersistence, JobPosPersistence>(bundle.getBundleContext(),
				JobPosPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}