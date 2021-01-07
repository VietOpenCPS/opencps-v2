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

import org.opencps.usermgt.exception.NoSuchJobPosException;
import org.opencps.usermgt.model.JobPos;

/**
 * The persistence interface for the job pos service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.usermgt.service.persistence.impl.JobPosPersistenceImpl
 * @see JobPosUtil
 * @generated
 */
@ProviderType
public interface JobPosPersistence extends BasePersistence<JobPos> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JobPosUtil} to access the job pos persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByUuid(String uuid);

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
	public java.util.List<JobPos> findByUuid(String uuid, int start, int end);

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
	public java.util.List<JobPos> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

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
	public java.util.List<JobPos> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the first job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the last job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the last job pos in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the job poses before and after the current job pos in the ordered set where uuid = &#63;.
	*
	* @param jobPosId the primary key of the current job pos
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public JobPos[] findByUuid_PrevAndNext(long jobPosId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Removes all the job poses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of job poses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching job poses
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the job pos where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosException;

	/**
	* Returns the job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the job pos where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the job pos where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the job pos that was removed
	*/
	public JobPos removeByUUID_G(String uuid, long groupId)
		throws NoSuchJobPosException;

	/**
	* Returns the number of job poses where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching job poses
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<JobPos> findByUuid_C(String uuid, long companyId,
		int start, int end);

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
	public java.util.List<JobPos> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

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
	public java.util.List<JobPos> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the first job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the last job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the last job pos in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

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
	public JobPos[] findByUuid_C_PrevAndNext(long jobPosId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Removes all the job poses where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of job poses where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching job poses
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the job pos where groupId = &#63; and title = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_title(long groupId, String title)
		throws NoSuchJobPosException;

	/**
	* Returns the job pos where groupId = &#63; and title = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_title(long groupId, String title);

	/**
	* Returns the job pos where groupId = &#63; and title = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param title the title
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_title(long groupId, String title,
		boolean retrieveFromCache);

	/**
	* Removes the job pos where groupId = &#63; and title = &#63; from the database.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the job pos that was removed
	*/
	public JobPos removeByF_title(long groupId, String title)
		throws NoSuchJobPosException;

	/**
	* Returns the number of job poses where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the number of matching job poses
	*/
	public int countByF_title(long groupId, String title);

	/**
	* Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_mappingRoleId(long groupId, long mappingRoleId)
		throws NoSuchJobPosException;

	/**
	* Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_mappingRoleId(long groupId, long mappingRoleId);

	/**
	* Returns the job pos where groupId = &#63; and mappingRoleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_mappingRoleId(long groupId, long mappingRoleId,
		boolean retrieveFromCache);

	/**
	* Removes the job pos where groupId = &#63; and mappingRoleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the job pos that was removed
	*/
	public JobPos removeByF_mappingRoleId(long groupId, long mappingRoleId)
		throws NoSuchJobPosException;

	/**
	* Returns the number of job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the number of matching job poses
	*/
	public int countByF_mappingRoleId(long groupId, long mappingRoleId);

	/**
	* Returns the job pos where groupId = &#63; and jobPosCode = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_CODE(long groupId, String jobPosCode)
		throws NoSuchJobPosException;

	/**
	* Returns the job pos where groupId = &#63; and jobPosCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_CODE(long groupId, String jobPosCode);

	/**
	* Returns the job pos where groupId = &#63; and jobPosCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_CODE(long groupId, String jobPosCode,
		boolean retrieveFromCache);

	/**
	* Removes the job pos where groupId = &#63; and jobPosCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the job pos that was removed
	*/
	public JobPos removeByF_CODE(long groupId, String jobPosCode)
		throws NoSuchJobPosException;

	/**
	* Returns the number of job poses where groupId = &#63; and jobPosCode = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosCode the job pos code
	* @return the number of matching job poses
	*/
	public int countByF_CODE(long groupId, String jobPosCode);

	/**
	* Returns the job pos where jobPosCode = &#63; or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param jobPosCode the job pos code
	* @return the matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_JOB_POS_CODE(String jobPosCode)
		throws NoSuchJobPosException;

	/**
	* Returns the job pos where jobPosCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param jobPosCode the job pos code
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_JOB_POS_CODE(String jobPosCode);

	/**
	* Returns the job pos where jobPosCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param jobPosCode the job pos code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_JOB_POS_CODE(String jobPosCode,
		boolean retrieveFromCache);

	/**
	* Removes the job pos where jobPosCode = &#63; from the database.
	*
	* @param jobPosCode the job pos code
	* @return the job pos that was removed
	*/
	public JobPos removeByF_JOB_POS_CODE(String jobPosCode)
		throws NoSuchJobPosException;

	/**
	* Returns the number of job poses where jobPosCode = &#63;.
	*
	* @param jobPosCode the job pos code
	* @return the number of matching job poses
	*/
	public int countByF_JOB_POS_CODE(String jobPosCode);

	/**
	* Returns all the job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long mappingRoleId);

	/**
	* Returns a range of all the job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long mappingRoleId, int start, int end);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long mappingRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long mappingRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos in the ordered set where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_mappingRoleIds_First(long groupId,
		long mappingRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the first job pos in the ordered set where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_mappingRoleIds_First(long groupId,
		long mappingRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the last job pos in the ordered set where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_mappingRoleIds_Last(long groupId, long mappingRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the last job pos in the ordered set where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_mappingRoleIds_Last(long groupId,
		long mappingRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the job poses before and after the current job pos in the ordered set where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param jobPosId the primary key of the current job pos
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public JobPos[] findByF_mappingRoleIds_PrevAndNext(long jobPosId,
		long groupId, long mappingRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns all the job poses where groupId = &#63; and mappingRoleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleIds the mapping role IDs
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long[] mappingRoleIds);

	/**
	* Returns a range of all the job poses where groupId = &#63; and mappingRoleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleIds the mapping role IDs
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long[] mappingRoleIds, int start, int end);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and mappingRoleId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleIds the mapping role IDs
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long[] mappingRoleIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and mappingRoleId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_mappingRoleIds(long groupId,
		long[] mappingRoleIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the job poses where groupId = &#63; and mappingRoleId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	*/
	public void removeByF_mappingRoleIds(long groupId, long mappingRoleId);

	/**
	* Returns the number of job poses where groupId = &#63; and mappingRoleId = &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleId the mapping role ID
	* @return the number of matching job poses
	*/
	public int countByF_mappingRoleIds(long groupId, long mappingRoleId);

	/**
	* Returns the number of job poses where groupId = &#63; and mappingRoleId = any &#63;.
	*
	* @param groupId the group ID
	* @param mappingRoleIds the mapping role IDs
	* @return the number of matching job poses
	*/
	public int countByF_mappingRoleIds(long groupId, long[] mappingRoleIds);

	/**
	* Returns all the job poses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByG(long groupId);

	/**
	* Returns a range of all the job poses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public java.util.List<JobPos> findByG(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByG(long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByG(long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the first job pos in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByG_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the last job pos in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the last job pos in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByG_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the job poses before and after the current job pos in the ordered set where groupId = &#63;.
	*
	* @param jobPosId the primary key of the current job pos
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public JobPos[] findByG_PrevAndNext(long jobPosId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Removes all the job poses where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByG(long groupId);

	/**
	* Returns the number of job poses where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching job poses
	*/
	public int countByG(long groupId);

	/**
	* Returns all the job poses where groupId = &#63; and jobPosId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId, long jobPosId);

	/**
	* Returns a range of all the job poses where groupId = &#63; and jobPosId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long jobPosId, int start, int end);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and jobPosId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long jobPosId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and jobPosId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long jobPosId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first job pos in the ordered set where groupId = &#63; and jobPosId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_jobPosIds_First(long groupId, long jobPosId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the first job pos in the ordered set where groupId = &#63; and jobPosId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_jobPosIds_First(long groupId, long jobPosId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns the last job pos in the ordered set where groupId = &#63; and jobPosId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos
	* @throws NoSuchJobPosException if a matching job pos could not be found
	*/
	public JobPos findByF_jobPosIds_Last(long groupId, long jobPosId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator)
		throws NoSuchJobPosException;

	/**
	* Returns the last job pos in the ordered set where groupId = &#63; and jobPosId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public JobPos fetchByF_jobPosIds_Last(long groupId, long jobPosId,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns all the job poses where groupId = &#63; and jobPosId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosIds the job pos IDs
	* @return the matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long[] jobPosIds);

	/**
	* Returns a range of all the job poses where groupId = &#63; and jobPosId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosIds the job pos IDs
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long[] jobPosIds, int start, int end);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and jobPosId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosIds the job pos IDs
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long[] jobPosIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

	/**
	* Returns an ordered range of all the job poses where groupId = &#63; and jobPosId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching job poses
	*/
	public java.util.List<JobPos> findByF_jobPosIds(long groupId,
		long[] jobPosIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the job poses where groupId = &#63; and jobPosId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	*/
	public void removeByF_jobPosIds(long groupId, long jobPosId);

	/**
	* Returns the number of job poses where groupId = &#63; and jobPosId = &#63;.
	*
	* @param groupId the group ID
	* @param jobPosId the job pos ID
	* @return the number of matching job poses
	*/
	public int countByF_jobPosIds(long groupId, long jobPosId);

	/**
	* Returns the number of job poses where groupId = &#63; and jobPosId = any &#63;.
	*
	* @param groupId the group ID
	* @param jobPosIds the job pos IDs
	* @return the number of matching job poses
	*/
	public int countByF_jobPosIds(long groupId, long[] jobPosIds);

	/**
	* Caches the job pos in the entity cache if it is enabled.
	*
	* @param jobPos the job pos
	*/
	public void cacheResult(JobPos jobPos);

	/**
	* Caches the job poses in the entity cache if it is enabled.
	*
	* @param jobPoses the job poses
	*/
	public void cacheResult(java.util.List<JobPos> jobPoses);

	/**
	* Creates a new job pos with the primary key. Does not add the job pos to the database.
	*
	* @param jobPosId the primary key for the new job pos
	* @return the new job pos
	*/
	public JobPos create(long jobPosId);

	/**
	* Removes the job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos that was removed
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public JobPos remove(long jobPosId) throws NoSuchJobPosException;

	public JobPos updateImpl(JobPos jobPos);

	/**
	* Returns the job pos with the primary key or throws a {@link NoSuchJobPosException} if it could not be found.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos
	* @throws NoSuchJobPosException if a job pos with the primary key could not be found
	*/
	public JobPos findByPrimaryKey(long jobPosId) throws NoSuchJobPosException;

	/**
	* Returns the job pos with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos, or <code>null</code> if a job pos with the primary key could not be found
	*/
	public JobPos fetchByPrimaryKey(long jobPosId);

	@Override
	public java.util.Map<java.io.Serializable, JobPos> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the job poses.
	*
	* @return the job poses
	*/
	public java.util.List<JobPos> findAll();

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
	public java.util.List<JobPos> findAll(int start, int end);

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
	public java.util.List<JobPos> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator);

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
	public java.util.List<JobPos> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JobPos> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the job poses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of job poses.
	*
	* @return the number of job poses
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}