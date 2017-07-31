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

package org.mobilink.backend.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Workspace. This utility wraps
 * {@link org.mobilink.backend.datamgt.service.impl.WorkspaceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see WorkspaceLocalService
 * @see org.mobilink.backend.datamgt.service.base.WorkspaceLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.impl.WorkspaceLocalServiceImpl
 * @generated
 */
@ProviderType
public class WorkspaceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.datamgt.service.impl.WorkspaceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of workspaces.
	*
	* @return the number of workspaces
	*/
	public static int getWorkspacesCount() {
		return getService().getWorkspacesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the workspaces.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @return the range of workspaces
	*/
	public static java.util.List<org.mobilink.backend.datamgt.model.Workspace> getWorkspaces(
		int start, int end) {
		return getService().getWorkspaces(start, end);
	}

	/**
	* Returns all the workspaces matching the UUID and company.
	*
	* @param uuid the UUID of the workspaces
	* @param companyId the primary key of the company
	* @return the matching workspaces, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.datamgt.model.Workspace> getWorkspacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getWorkspacesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of workspaces matching the UUID and company.
	*
	* @param uuid the UUID of the workspaces
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching workspaces, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.datamgt.model.Workspace> getWorkspacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.datamgt.model.Workspace> orderByComparator) {
		return getService()
				   .getWorkspacesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	public static org.mobilink.backend.datamgt.model.Workspace addWorkspace(
		long userId, long groupId, java.lang.String name, int seqOrder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .addWorkspace(userId, groupId, name, seqOrder, serviceContext);
	}

	/**
	* Adds the workspace to the database. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was added
	*/
	public static org.mobilink.backend.datamgt.model.Workspace addWorkspace(
		org.mobilink.backend.datamgt.model.Workspace workspace) {
		return getService().addWorkspace(workspace);
	}

	/**
	* Creates a new workspace with the primary key. Does not add the workspace to the database.
	*
	* @param workspaceId the primary key for the new workspace
	* @return the new workspace
	*/
	public static org.mobilink.backend.datamgt.model.Workspace createWorkspace(
		long workspaceId) {
		return getService().createWorkspace(workspaceId);
	}

	/**
	* Deletes the workspace with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace that was removed
	* @throws PortalException if a workspace with the primary key could not be found
	*/
	public static org.mobilink.backend.datamgt.model.Workspace deleteWorkspace(
		long workspaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWorkspace(workspaceId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	public static org.mobilink.backend.datamgt.model.Workspace deleteWorkspace(
		long workspaceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deleteWorkspace(workspaceId, serviceContext);
	}

	/**
	* Deletes the workspace from the database. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was removed
	*/
	public static org.mobilink.backend.datamgt.model.Workspace deleteWorkspace(
		org.mobilink.backend.datamgt.model.Workspace workspace) {
		return getService().deleteWorkspace(workspace);
	}

	public static org.mobilink.backend.datamgt.model.Workspace fetchWorkspace(
		long workspaceId) {
		return getService().fetchWorkspace(workspaceId);
	}

	/**
	* Returns the workspace matching the UUID and group.
	*
	* @param uuid the workspace's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	public static org.mobilink.backend.datamgt.model.Workspace fetchWorkspaceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchWorkspaceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the workspace with the primary key.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace
	* @throws PortalException if a workspace with the primary key could not be found
	*/
	public static org.mobilink.backend.datamgt.model.Workspace getWorkspace(
		long workspaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkspace(workspaceId);
	}

	/**
	* Returns the workspace matching the UUID and group.
	*
	* @param uuid the workspace's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace
	* @throws PortalException if a matching workspace could not be found
	*/
	public static org.mobilink.backend.datamgt.model.Workspace getWorkspaceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkspaceByUuidAndGroupId(uuid, groupId);
	}

	public static org.mobilink.backend.datamgt.model.Workspace updateWorkspace(
		long userId, long groupId, long workspaceId, java.lang.String name,
		int seqOrder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .updateWorkspace(userId, groupId, workspaceId, name,
			seqOrder, serviceContext);
	}

	/**
	* Updates the workspace in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was updated
	*/
	public static org.mobilink.backend.datamgt.model.Workspace updateWorkspace(
		org.mobilink.backend.datamgt.model.Workspace workspace) {
		return getService().updateWorkspace(workspace);
	}

	public static WorkspaceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkspaceLocalService, WorkspaceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WorkspaceLocalService.class);
}