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

import org.opencps.usermgt.model.JobPosWork;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the job pos work service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.JobPosWorkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see JobPosWorkPersistence
 * @see org.opencps.usermgt.service.persistence.impl.JobPosWorkPersistenceImpl
 * @generated
 */
@ProviderType
public class JobPosWorkUtil {
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
	public static void clearCache(JobPosWork jobPosWork) {
		getPersistence().clearCache(jobPosWork);
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
	public static List<JobPosWork> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JobPosWork> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JobPosWork> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static JobPosWork update(JobPosWork jobPosWork) {
		return getPersistence().update(jobPosWork);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static JobPosWork update(JobPosWork jobPosWork,
		ServiceContext serviceContext) {
		return getPersistence().update(jobPosWork, serviceContext);
	}

	/**
	* Returns all the job pos works where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching job pos works
	*/
	public static List<JobPosWork> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the job pos works where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @return the range of matching job pos works
	*/
	public static List<JobPosWork> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the job pos works where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job pos works
	*/
	public static List<JobPosWork> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the job pos works where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job pos works
	*/
	public static List<JobPosWork> findByUuid(String uuid, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByUuid_First(String uuid,
		OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByUuid_First(String uuid,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByUuid_Last(String uuid,
		OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByUuid_Last(String uuid,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the job pos works before and after the current job pos work in the ordered set where uuid = &#63;.
	*
	* @param jobPosWorkId the primary key of the current job pos work
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos work
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public static JobPosWork[] findByUuid_PrevAndNext(long jobPosWorkId,
		String uuid, OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByUuid_PrevAndNext(jobPosWorkId, uuid, orderByComparator);
	}

	/**
	* Removes all the job pos works where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of job pos works where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching job pos works
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the job pos work where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the job pos work where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the job pos work where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the job pos work where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the job pos work that was removed
	*/
	public static JobPosWork removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of job pos works where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching job pos works
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the job pos works where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching job pos works
	*/
	public static List<JobPosWork> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the job pos works where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @return the range of matching job pos works
	*/
	public static List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the job pos works where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job pos works
	*/
	public static List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the job pos works where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job pos works
	*/
	public static List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the job pos works before and after the current job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param jobPosWorkId the primary key of the current job pos work
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos work
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public static JobPosWork[] findByUuid_C_PrevAndNext(long jobPosWorkId,
		String uuid, long companyId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(jobPosWorkId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the job pos works where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of job pos works where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching job pos works
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByF_ChecklistCat(groupId, jobPostId, checklistCat);
	}

	/**
	* Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByF_ChecklistCat(long groupId,
		long jobPostId, String checklistCat) {
		return getPersistence()
				   .fetchByF_ChecklistCat(groupId, jobPostId, checklistCat);
	}

	/**
	* Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByF_ChecklistCat(long groupId,
		long jobPostId, String checklistCat, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_ChecklistCat(groupId, jobPostId, checklistCat,
			retrieveFromCache);
	}

	/**
	* Removes the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the job pos work that was removed
	*/
	public static JobPosWork removeByF_ChecklistCat(long groupId,
		long jobPostId, String checklistCat)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .removeByF_ChecklistCat(groupId, jobPostId, checklistCat);
	}

	/**
	* Returns the number of job pos works where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the number of matching job pos works
	*/
	public static int countByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) {
		return getPersistence()
				   .countByF_ChecklistCat(groupId, jobPostId, checklistCat);
	}

	/**
	* Returns all the job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @return the matching job pos works
	*/
	public static List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId) {
		return getPersistence().findByF_JobPostId(groupId, jobPostId);
	}

	/**
	* Returns a range of all the job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @return the range of matching job pos works
	*/
	public static List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId, int start, int end) {
		return getPersistence().findByF_JobPostId(groupId, jobPostId, start, end);
	}

	/**
	* Returns an ordered range of all the job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job pos works
	*/
	public static List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .findByF_JobPostId(groupId, jobPostId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job pos works
	*/
	public static List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId, int start, int end,
		OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_JobPostId(groupId, jobPostId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByF_JobPostId_First(long groupId,
		long jobPostId, OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByF_JobPostId_First(groupId, jobPostId,
			orderByComparator);
	}

	/**
	* Returns the first job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByF_JobPostId_First(long groupId,
		long jobPostId, OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .fetchByF_JobPostId_First(groupId, jobPostId,
			orderByComparator);
	}

	/**
	* Returns the last job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public static JobPosWork findByF_JobPostId_Last(long groupId,
		long jobPostId, OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByF_JobPostId_Last(groupId, jobPostId, orderByComparator);
	}

	/**
	* Returns the last job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public static JobPosWork fetchByF_JobPostId_Last(long groupId,
		long jobPostId, OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence()
				   .fetchByF_JobPostId_Last(groupId, jobPostId,
			orderByComparator);
	}

	/**
	* Returns the job pos works before and after the current job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param jobPosWorkId the primary key of the current job pos work
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos work
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public static JobPosWork[] findByF_JobPostId_PrevAndNext(
		long jobPosWorkId, long groupId, long jobPostId,
		OrderByComparator<JobPosWork> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence()
				   .findByF_JobPostId_PrevAndNext(jobPosWorkId, groupId,
			jobPostId, orderByComparator);
	}

	/**
	* Removes all the job pos works where groupId = &#63; and jobPostId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	*/
	public static void removeByF_JobPostId(long groupId, long jobPostId) {
		getPersistence().removeByF_JobPostId(groupId, jobPostId);
	}

	/**
	* Returns the number of job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @return the number of matching job pos works
	*/
	public static int countByF_JobPostId(long groupId, long jobPostId) {
		return getPersistence().countByF_JobPostId(groupId, jobPostId);
	}

	/**
	* Caches the job pos work in the entity cache if it is enabled.
	*
	* @param jobPosWork the job pos work
	*/
	public static void cacheResult(JobPosWork jobPosWork) {
		getPersistence().cacheResult(jobPosWork);
	}

	/**
	* Caches the job pos works in the entity cache if it is enabled.
	*
	* @param jobPosWorks the job pos works
	*/
	public static void cacheResult(List<JobPosWork> jobPosWorks) {
		getPersistence().cacheResult(jobPosWorks);
	}

	/**
	* Creates a new job pos work with the primary key. Does not add the job pos work to the database.
	*
	* @param jobPosWorkId the primary key for the new job pos work
	* @return the new job pos work
	*/
	public static JobPosWork create(long jobPosWorkId) {
		return getPersistence().create(jobPosWorkId);
	}

	/**
	* Removes the job pos work with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPosWorkId the primary key of the job pos work
	* @return the job pos work that was removed
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public static JobPosWork remove(long jobPosWorkId)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence().remove(jobPosWorkId);
	}

	public static JobPosWork updateImpl(JobPosWork jobPosWork) {
		return getPersistence().updateImpl(jobPosWork);
	}

	/**
	* Returns the job pos work with the primary key or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	*
	* @param jobPosWorkId the primary key of the job pos work
	* @return the job pos work
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public static JobPosWork findByPrimaryKey(long jobPosWorkId)
		throws org.opencps.usermgt.exception.NoSuchJobPosWorkException {
		return getPersistence().findByPrimaryKey(jobPosWorkId);
	}

	/**
	* Returns the job pos work with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jobPosWorkId the primary key of the job pos work
	* @return the job pos work, or <code>null</code> if a job pos work with the primary key could not be found
	*/
	public static JobPosWork fetchByPrimaryKey(long jobPosWorkId) {
		return getPersistence().fetchByPrimaryKey(jobPosWorkId);
	}

	public static java.util.Map<java.io.Serializable, JobPosWork> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the job pos works.
	*
	* @return the job pos works
	*/
	public static List<JobPosWork> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the job pos works.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @return the range of job pos works
	*/
	public static List<JobPosWork> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the job pos works.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of job pos works
	*/
	public static List<JobPosWork> findAll(int start, int end,
		OrderByComparator<JobPosWork> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the job pos works.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosWorkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job pos works
	* @param end the upper bound of the range of job pos works (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of job pos works
	*/
	public static List<JobPosWork> findAll(int start, int end,
		OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the job pos works from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of job pos works.
	*
	* @return the number of job pos works
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static JobPosWorkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JobPosWorkPersistence, JobPosWorkPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JobPosWorkPersistence.class);

		ServiceTracker<JobPosWorkPersistence, JobPosWorkPersistence> serviceTracker =
			new ServiceTracker<JobPosWorkPersistence, JobPosWorkPersistence>(bundle.getBundleContext(),
				JobPosWorkPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}