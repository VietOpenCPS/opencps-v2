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

package org.mobilink.backend.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for JobPos. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.JobPosLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see JobPosLocalService
 * @see org.mobilink.backend.usermgt.service.base.JobPosLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.JobPosLocalServiceImpl
 * @generated
 */
@ProviderType
public class JobPosLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.JobPosLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	/**
	* Returns the number of job poses.
	*
	* @return the number of job poses
	*/
	public static int getJobPosesCount() {
		return getService().getJobPosesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<org.mobilink.backend.usermgt.model.JobPos> getJobPoses(
		int start, int end) {
		return getService().getJobPoses(start, end);
	}

	/**
	* Returns all the job poses matching the UUID and company.
	*
	* @param uuid the UUID of the job poses
	* @param companyId the primary key of the company
	* @return the matching job poses, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.JobPos> getJobPosesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getJobPosesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.mobilink.backend.usermgt.model.JobPos> getJobPosesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.JobPos> orderByComparator) {
		return getService()
				   .getJobPosesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.mobilink.backend.usermgt.model.JobPos addJobPos(
		long userId, long groupId, long workingUnitId, java.lang.String title,
		java.lang.String description, long directWorkingUnitId, int leader,
		long mappingRoleId, boolean hiddenJobPos,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addJobPos(userId, groupId, workingUnitId, title,
			description, directWorkingUnitId, leader, mappingRoleId,
			hiddenJobPos, serviceContext);
	}

	/**
	* Adds the job pos to the database. Also notifies the appropriate model listeners.
	*
	* @param jobPos the job pos
	* @return the job pos that was added
	*/
	public static org.mobilink.backend.usermgt.model.JobPos addJobPos(
		org.mobilink.backend.usermgt.model.JobPos jobPos) {
		return getService().addJobPos(jobPos);
	}

	/**
	* Creates a new job pos with the primary key. Does not add the job pos to the database.
	*
	* @param jobPosId the primary key for the new job pos
	* @return the new job pos
	*/
	public static org.mobilink.backend.usermgt.model.JobPos createJobPos(
		long jobPosId) {
		return getService().createJobPos(jobPosId);
	}

	public static org.mobilink.backend.usermgt.model.JobPos deleteJobPos(
		long JobPosId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deleteJobPos(JobPosId, serviceContext);
	}

	/**
	* Deletes the job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos that was removed
	* @throws PortalException if a job pos with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JobPos deleteJobPos(
		long jobPosId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteJobPos(jobPosId);
	}

	/**
	* Deletes the job pos from the database. Also notifies the appropriate model listeners.
	*
	* @param jobPos the job pos
	* @return the job pos that was removed
	*/
	public static org.mobilink.backend.usermgt.model.JobPos deleteJobPos(
		org.mobilink.backend.usermgt.model.JobPos jobPos) {
		return getService().deleteJobPos(jobPos);
	}

	public static org.mobilink.backend.usermgt.model.JobPos fetchJobPos(
		long jobPosId) {
		return getService().fetchJobPos(jobPosId);
	}

	/**
	* Returns the job pos matching the UUID and group.
	*
	* @param uuid the job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching job pos, or <code>null</code> if a matching job pos could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JobPos fetchJobPosByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchJobPosByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the job pos with the primary key.
	*
	* @param jobPosId the primary key of the job pos
	* @return the job pos
	* @throws PortalException if a job pos with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JobPos getJobPos(
		long jobPosId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJobPos(jobPosId);
	}

	/**
	* Returns the job pos matching the UUID and group.
	*
	* @param uuid the job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching job pos
	* @throws PortalException if a matching job pos could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JobPos getJobPosByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJobPosByUuidAndGroupId(uuid, groupId);
	}

	public static org.mobilink.backend.usermgt.model.JobPos updateJobPos(
		long userId, long JobPosId, java.lang.String title,
		java.lang.String description, long directWorkingUnitId, int leader,
		long mappingRoleId, boolean hiddenJobPos,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateJobPos(userId, JobPosId, title, description,
			directWorkingUnitId, leader, mappingRoleId, hiddenJobPos,
			serviceContext);
	}

	/**
	* Updates the job pos in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param jobPos the job pos
	* @return the job pos that was updated
	*/
	public static org.mobilink.backend.usermgt.model.JobPos updateJobPos(
		org.mobilink.backend.usermgt.model.JobPos jobPos) {
		return getService().updateJobPos(jobPos);
	}

	public static void assignPermission(long JobPosId,
		java.lang.String[] actionIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().assignPermission(JobPosId, actionIds, serviceContext);
	}

	public static JobPosLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JobPosLocalService, JobPosLocalService> _serviceTracker =
		ServiceTrackerFactory.open(JobPosLocalService.class);
}