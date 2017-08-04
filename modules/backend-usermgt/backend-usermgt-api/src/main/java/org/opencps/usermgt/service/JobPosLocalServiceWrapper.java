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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JobPosLocalService}.
 *
 * @author Binhth
 * @see JobPosLocalService
 * @generated
 */
@ProviderType
public class JobPosLocalServiceWrapper implements JobPosLocalService,
	ServiceWrapper<JobPosLocalService> {
	public JobPosLocalServiceWrapper(JobPosLocalService jobPosLocalService) {
		_jobPosLocalService = jobPosLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _jobPosLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _jobPosLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _jobPosLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _jobPosLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _jobPosLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Returns the number of job poses.
	*
	* @return the number of job poses
	*/
	@Override
	public int getJobPosesCount() {
		return _jobPosLocalService.getJobPosesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _jobPosLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _jobPosLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _jobPosLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _jobPosLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.JobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @return the range of job poses
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.JobPos> getJobPoses(
		int start, int end) {
		return _jobPosLocalService.getJobPoses(start, end);
	}

	/**
	* Returns all the job poses matching the UUID and company.
	*
	* @param uuid the UUID of the job poses
	* @param companyId the primary key of the company
	* @return the matching job poses, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.JobPos> getJobPosesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _jobPosLocalService.getJobPosesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of job poses matching the UUID and company.
	*
	* @param uuid the UUID of the job poses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of job poses
	* @param end the upper bound of the range of job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching job poses, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.JobPos> getJobPosesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.JobPos> orderByComparator) {
		return _jobPosLocalService.getJobPosesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _jobPosLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _jobPosLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.JobPos addJobPos(long userId,
		long groupId, long workingUnitId, java.lang.String title,
		java.lang.String description, long directWorkingUnitId, int leader,
		long mappingRoleId, boolean hiddenJobPos,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.addJobPos(userId, groupId, workingUnitId,
			title, description, directWorkingUnitId, leader, mappingRoleId,
			hiddenJobPos, serviceContext);
	}

	/**
	* Adds the job pos to the database. Also notifies the appropriate model listeners.
	*
	* @param jobPos the job pos
	* @return the job pos that was added
	*/
	@Override
	public org.opencps.usermgt.model.JobPos addJobPos(
		org.opencps.usermgt.model.JobPos jobPos) {
		return _jobPosLocalService.addJobPos(jobPos);
	}

	/**
	* Creates a new job pos with the primary key. Does not add the job pos to the database.
	*
	* @param jobPosId the primary key for the new job pos
	* @return the new job pos
	*/
	@Override
	public org.opencps.usermgt.model.JobPos createJobPos(long jobPosId) {
		return _jobPosLocalService.createJobPos(jobPosId);
	}

	@Override
	public org.opencps.usermgt.model.JobPos deleteJobPos(
		long JobPosId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _jobPosLocalService.deleteJobPos(JobPosId, serviceContext);
	}

	/**
	* Deletes the job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos that was removed
	* @throws PortalException if a job pos with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JobPos deleteJobPos(long jobPosId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.deleteJobPos(jobPosId);
	}

	/**
	* Deletes the job pos from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPos the job pos
	* @return the job pos that was removed
	*/
	@Override
	public org.opencps.usermgt.model.JobPos deleteJobPos(
		org.opencps.usermgt.model.JobPos jobPos) {
		return _jobPosLocalService.deleteJobPos(jobPos);
	}

	@Override
	public org.opencps.usermgt.model.JobPos fetchJobPos(long jobPosId) {
		return _jobPosLocalService.fetchJobPos(jobPosId);
	}

	/**
	* Returns the job pos matching the UUID and group.
	*
	* @param uuid the job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JobPos fetchJobPosByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _jobPosLocalService.fetchJobPosByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the job pos with the primary key.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos
	* @throws PortalException if a job pos with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JobPos getJobPos(long jobPosId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.getJobPos(jobPosId);
	}

	/**
	* Returns the job pos matching the UUID and group.
	*
	* @param uuid the job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching job pos
	* @throws PortalException if a matching job pos could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JobPos getJobPosByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.getJobPosByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public org.opencps.usermgt.model.JobPos updateJobPos(long userId,
		long JobPosId, java.lang.String title, java.lang.String description,
		long directWorkingUnitId, int leader, long mappingRoleId,
		boolean hiddenJobPos,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jobPosLocalService.updateJobPos(userId, JobPosId, title,
			description, directWorkingUnitId, leader, mappingRoleId,
			hiddenJobPos, serviceContext);
	}

	/**
	* Updates the job pos in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jobPos the job pos
	* @return the job pos that was updated
	*/
	@Override
	public org.opencps.usermgt.model.JobPos updateJobPos(
		org.opencps.usermgt.model.JobPos jobPos) {
		return _jobPosLocalService.updateJobPos(jobPos);
	}

	@Override
	public void assignPermission(long JobPosId, java.lang.String[] actionIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_jobPosLocalService.assignPermission(JobPosId, actionIds, serviceContext);
	}

	@Override
	public JobPosLocalService getWrappedService() {
		return _jobPosLocalService;
	}

	@Override
	public void setWrappedService(JobPosLocalService jobPosLocalService) {
		_jobPosLocalService = jobPosLocalService;
	}

	private JobPosLocalService _jobPosLocalService;
}