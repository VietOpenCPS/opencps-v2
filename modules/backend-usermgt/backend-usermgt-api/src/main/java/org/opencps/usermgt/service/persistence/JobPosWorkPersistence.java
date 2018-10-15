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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.usermgt.exception.NoSuchJobPosWorkException;
import org.opencps.usermgt.model.JobPosWork;

/**
 * The persistence interface for the job pos work service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.JobPosWorkPersistenceImpl
 * @see JobPosWorkUtil
 * @generated
 */
@ProviderType
public interface JobPosWorkPersistence extends BasePersistence<JobPosWork> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JobPosWorkUtil} to access the job pos work persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the job pos works where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching job pos works
	*/
	public java.util.List<JobPosWork> findByUuid(String uuid);

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
	public java.util.List<JobPosWork> findByUuid(String uuid, int start, int end);

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
	public java.util.List<JobPosWork> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

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
	public java.util.List<JobPosWork> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

	/**
	* Returns the job pos works before and after the current job pos work in the ordered set where uuid = &#63;.
	*
	* @param jobPosWorkId the primary key of the current job pos work
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos work
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public JobPosWork[] findByUuid_PrevAndNext(long jobPosWorkId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Removes all the job pos works where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of job pos works where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching job pos works
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the job pos work where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the job pos work where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the job pos work where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the job pos work where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the job pos work that was removed
	*/
	public JobPosWork removeByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the number of job pos works where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching job pos works
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the job pos works where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching job pos works
	*/
	public java.util.List<JobPosWork> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

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
	public java.util.List<JobPosWork> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the first job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the last job pos work in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

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
	public JobPosWork[] findByUuid_C_PrevAndNext(long jobPosWorkId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Removes all the job pos works where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of job pos works where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching job pos works
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) throws NoSuchJobPosWorkException;

	/**
	* Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat);

	/**
	* Returns the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat, boolean retrieveFromCache);

	/**
	* Removes the job pos work where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the job pos work that was removed
	*/
	public JobPosWork removeByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat) throws NoSuchJobPosWorkException;

	/**
	* Returns the number of job pos works where groupId = &#63; and jobPostId = &#63; and checklistCat = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param checklistCat the checklist cat
	* @return the number of matching job pos works
	*/
	public int countByF_ChecklistCat(long groupId, long jobPostId,
		String checklistCat);

	/**
	* Returns all the job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @return the matching job pos works
	*/
	public java.util.List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId);

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
	public java.util.List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId, int start, int end);

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
	public java.util.List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

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
	public java.util.List<JobPosWork> findByF_JobPostId(long groupId,
		long jobPostId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByF_JobPostId_First(long groupId, long jobPostId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the first job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByF_JobPostId_First(long groupId, long jobPostId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

	/**
	* Returns the last job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work
	* @throws NoSuchJobPosWorkException if a matching job pos work could not be found
	*/
	public JobPosWork findByF_JobPostId_Last(long groupId, long jobPostId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the last job pos work in the ordered set where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos work, or <code>null</code> if a matching job pos work could not be found
	*/
	public JobPosWork fetchByF_JobPostId_Last(long groupId, long jobPostId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

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
	public JobPosWork[] findByF_JobPostId_PrevAndNext(long jobPosWorkId,
		long groupId, long jobPostId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator)
		throws NoSuchJobPosWorkException;

	/**
	* Removes all the job pos works where groupId = &#63; and jobPostId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	*/
	public void removeByF_JobPostId(long groupId, long jobPostId);

	/**
	* Returns the number of job pos works where groupId = &#63; and jobPostId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPostId the job post ID
	* @return the number of matching job pos works
	*/
	public int countByF_JobPostId(long groupId, long jobPostId);

	/**
	* Caches the job pos work in the entity cache if it is enabled.
	*
	* @param jobPosWork the job pos work
	*/
	public void cacheResult(JobPosWork jobPosWork);

	/**
	* Caches the job pos works in the entity cache if it is enabled.
	*
	* @param jobPosWorks the job pos works
	*/
	public void cacheResult(java.util.List<JobPosWork> jobPosWorks);

	/**
	* Creates a new job pos work with the primary key. Does not add the job pos work to the database.
	*
	* @param jobPosWorkId the primary key for the new job pos work
	* @return the new job pos work
	*/
	public JobPosWork create(long jobPosWorkId);

	/**
	* Removes the job pos work with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPosWorkId the primary key of the job pos work
	* @return the job pos work that was removed
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public JobPosWork remove(long jobPosWorkId)
		throws NoSuchJobPosWorkException;

	public JobPosWork updateImpl(JobPosWork jobPosWork);

	/**
	* Returns the job pos work with the primary key or throws a {@link NoSuchJobPosWorkException} if it could not be found.
	*
	* @param jobPosWorkId the primary key of the job pos work
	* @return the job pos work
	* @throws NoSuchJobPosWorkException if a job pos work with the primary key could not be found
	*/
	public JobPosWork findByPrimaryKey(long jobPosWorkId)
		throws NoSuchJobPosWorkException;

	/**
	* Returns the job pos work with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jobPosWorkId the primary key of the job pos work
	* @return the job pos work, or <code>null</code> if a job pos work with the primary key could not be found
	*/
	public JobPosWork fetchByPrimaryKey(long jobPosWorkId);

	@Override
	public java.util.Map<java.io.Serializable, JobPosWork> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the job pos works.
	*
	* @return the job pos works
	*/
	public java.util.List<JobPosWork> findAll();

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
	public java.util.List<JobPosWork> findAll(int start, int end);

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
	public java.util.List<JobPosWork> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator);

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
	public java.util.List<JobPosWork> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPosWork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the job pos works from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of job pos works.
	*
	* @return the number of job pos works
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}