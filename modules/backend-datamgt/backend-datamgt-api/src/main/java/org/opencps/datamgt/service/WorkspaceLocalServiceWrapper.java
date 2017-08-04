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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorkspaceLocalService}.
 *
 * @author Binhth
 * @see WorkspaceLocalService
 * @generated
 */
@ProviderType
public class WorkspaceLocalServiceWrapper implements WorkspaceLocalService,
	ServiceWrapper<WorkspaceLocalService> {
	public WorkspaceLocalServiceWrapper(
		WorkspaceLocalService workspaceLocalService) {
		_workspaceLocalService = workspaceLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workspaceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workspaceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _workspaceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workspaceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _workspaceLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Returns the number of workspaces.
	*
	* @return the number of workspaces
	*/
	@Override
	public int getWorkspacesCount() {
		return _workspaceLocalService.getWorkspacesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workspaceLocalService.getOSGiServiceIdentifier();
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
		return _workspaceLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _workspaceLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _workspaceLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<org.opencps.datamgt.model.Workspace> getWorkspaces(
		int start, int end) {
		return _workspaceLocalService.getWorkspaces(start, end);
	}

	/**
	* Returns all the workspaces matching the UUID and company.
	*
	* @param uuid the UUID of the workspaces
	* @param companyId the primary key of the company
	* @return the matching workspaces, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Workspace> getWorkspacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _workspaceLocalService.getWorkspacesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.datamgt.model.Workspace> getWorkspacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.Workspace> orderByComparator) {
		return _workspaceLocalService.getWorkspacesByUuidAndCompanyId(uuid,
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
		return _workspaceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workspaceLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.datamgt.model.Workspace addWorkspace(
		long userId, long groupId, java.lang.String name, int seqOrder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workspaceLocalService.addWorkspace(userId, groupId, name,
			seqOrder, serviceContext);
	}

	/**
	* Adds the workspace to the database. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was added
	*/
	@Override
	public org.opencps.datamgt.model.Workspace addWorkspace(
		org.opencps.datamgt.model.Workspace workspace) {
		return _workspaceLocalService.addWorkspace(workspace);
	}

	/**
	* Creates a new workspace with the primary key. Does not add the workspace to the database.
	*
	* @param workspaceId the primary key for the new workspace
	* @return the new workspace
	*/
	@Override
	public org.opencps.datamgt.model.Workspace createWorkspace(
		long workspaceId) {
		return _workspaceLocalService.createWorkspace(workspaceId);
	}

	/**
	* Deletes the workspace with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace that was removed
	* @throws PortalException if a workspace with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Workspace deleteWorkspace(
		long workspaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceLocalService.deleteWorkspace(workspaceId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.datamgt.model.Workspace deleteWorkspace(
		long workspaceId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workspaceLocalService.deleteWorkspace(workspaceId,
			serviceContext);
	}

	/**
	* Deletes the workspace from the database. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was removed
	*/
	@Override
	public org.opencps.datamgt.model.Workspace deleteWorkspace(
		org.opencps.datamgt.model.Workspace workspace) {
		return _workspaceLocalService.deleteWorkspace(workspace);
	}

	@Override
	public org.opencps.datamgt.model.Workspace fetchWorkspace(
		long workspaceId) {
		return _workspaceLocalService.fetchWorkspace(workspaceId);
	}

	/**
	* Returns the workspace matching the UUID and group.
	*
	* @param uuid the workspace's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Workspace fetchWorkspaceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _workspaceLocalService.fetchWorkspaceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the workspace with the primary key.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace
	* @throws PortalException if a workspace with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Workspace getWorkspace(
		long workspaceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceLocalService.getWorkspace(workspaceId);
	}

	/**
	* Returns the workspace matching the UUID and group.
	*
	* @param uuid the workspace's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace
	* @throws PortalException if a matching workspace could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Workspace getWorkspaceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceLocalService.getWorkspaceByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public org.opencps.datamgt.model.Workspace updateWorkspace(
		long userId, long groupId, long workspaceId, java.lang.String name,
		int seqOrder,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workspaceLocalService.updateWorkspace(userId, groupId,
			workspaceId, name, seqOrder, serviceContext);
	}

	/**
	* Updates the workspace in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was updated
	*/
	@Override
	public org.opencps.datamgt.model.Workspace updateWorkspace(
		org.opencps.datamgt.model.Workspace workspace) {
		return _workspaceLocalService.updateWorkspace(workspace);
	}

	@Override
	public WorkspaceLocalService getWrappedService() {
		return _workspaceLocalService;
	}

	@Override
	public void setWrappedService(WorkspaceLocalService workspaceLocalService) {
		_workspaceLocalService = workspaceLocalService;
	}

	private WorkspaceLocalService _workspaceLocalService;
}