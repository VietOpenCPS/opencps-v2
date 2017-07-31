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
 * Provides the local service utility for JoinSiteStatus. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.JoinSiteStatusLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see JoinSiteStatusLocalService
 * @see org.mobilink.backend.usermgt.service.base.JoinSiteStatusLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.JoinSiteStatusLocalServiceImpl
 * @generated
 */
@ProviderType
public class JoinSiteStatusLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.JoinSiteStatusLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of join site statuses.
	*
	* @return the number of join site statuses
	*/
	public static int getJoinSiteStatusesCount() {
		return getService().getJoinSiteStatusesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the join site statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.JoinSiteStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @return the range of join site statuses
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.JoinSiteStatus> getJoinSiteStatuses(
		int start, int end) {
		return getService().getJoinSiteStatuses(start, end);
	}

	/**
	* Returns all the join site statuses matching the UUID and company.
	*
	* @param uuid the UUID of the join site statuses
	* @param companyId the primary key of the company
	* @return the matching join site statuses, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.JoinSiteStatus> getJoinSiteStatusesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getJoinSiteStatusesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of join site statuses matching the UUID and company.
	*
	* @param uuid the UUID of the join site statuses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of join site statuses
	* @param end the upper bound of the range of join site statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching join site statuses, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.JoinSiteStatus> getJoinSiteStatusesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.JoinSiteStatus> orderByComparator) {
		return getService()
				   .getJoinSiteStatusesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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

	public static org.mobilink.backend.usermgt.model.JoinSiteStatus addJoinSiteStatus(
		long userId, long groupId, long employeeId, long joinSiteGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addJoinSiteStatus(userId, groupId, employeeId,
			joinSiteGroupId, serviceContext);
	}

	/**
	* Adds the join site status to the database. Also notifies the appropriate model listeners.
	*
	* @param joinSiteStatus the join site status
	* @return the join site status that was added
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus addJoinSiteStatus(
		org.mobilink.backend.usermgt.model.JoinSiteStatus joinSiteStatus) {
		return getService().addJoinSiteStatus(joinSiteStatus);
	}

	public static org.mobilink.backend.usermgt.model.JoinSiteStatus assignJoinSiteStatus(
		long userId, long groupId, long employeeId, long joinSiteGroupId,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .assignJoinSiteStatus(userId, groupId, employeeId,
			joinSiteGroupId, status, serviceContext);
	}

	/**
	* Creates a new join site status with the primary key. Does not add the join site status to the database.
	*
	* @param JoinSiteStatusId the primary key for the new join site status
	* @return the new join site status
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus createJoinSiteStatus(
		long JoinSiteStatusId) {
		return getService().createJoinSiteStatus(JoinSiteStatusId);
	}

	/**
	* Deletes the join site status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status that was removed
	* @throws PortalException if a join site status with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus deleteJoinSiteStatus(
		long JoinSiteStatusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteJoinSiteStatus(JoinSiteStatusId);
	}

	public static org.mobilink.backend.usermgt.model.JoinSiteStatus deleteJoinSiteStatus(
		long JoinSiteStatusId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .deleteJoinSiteStatus(JoinSiteStatusId, serviceContext);
	}

	/**
	* Deletes the join site status from the database. Also notifies the appropriate model listeners.
	*
	* @param joinSiteStatus the join site status
	* @return the join site status that was removed
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus deleteJoinSiteStatus(
		org.mobilink.backend.usermgt.model.JoinSiteStatus joinSiteStatus) {
		return getService().deleteJoinSiteStatus(joinSiteStatus);
	}

	public static org.mobilink.backend.usermgt.model.JoinSiteStatus fetchJoinSiteStatus(
		long JoinSiteStatusId) {
		return getService().fetchJoinSiteStatus(JoinSiteStatusId);
	}

	/**
	* Returns the join site status matching the UUID and group.
	*
	* @param uuid the join site status's UUID
	* @param groupId the primary key of the group
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus fetchJoinSiteStatusByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchJoinSiteStatusByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the join site status with the primary key.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status
	* @throws PortalException if a join site status with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus getJoinSiteStatus(
		long JoinSiteStatusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJoinSiteStatus(JoinSiteStatusId);
	}

	/**
	* Returns the join site status matching the UUID and group.
	*
	* @param uuid the join site status's UUID
	* @param groupId the primary key of the group
	* @return the matching join site status
	* @throws PortalException if a matching join site status could not be found
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus getJoinSiteStatusByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJoinSiteStatusByUuidAndGroupId(uuid, groupId);
	}

	public static org.mobilink.backend.usermgt.model.JoinSiteStatus updateJoinSiteStatus(
		long userId, long JoinSiteStatusId, long joinSiteGroupId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateJoinSiteStatus(userId, JoinSiteStatusId,
			joinSiteGroupId, status, serviceContext);
	}

	/**
	* Updates the join site status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param joinSiteStatus the join site status
	* @return the join site status that was updated
	*/
	public static org.mobilink.backend.usermgt.model.JoinSiteStatus updateJoinSiteStatus(
		org.mobilink.backend.usermgt.model.JoinSiteStatus joinSiteStatus) {
		return getService().updateJoinSiteStatus(joinSiteStatus);
	}

	public static JoinSiteStatusLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JoinSiteStatusLocalService, JoinSiteStatusLocalService> _serviceTracker =
		ServiceTrackerFactory.open(JoinSiteStatusLocalService.class);
}