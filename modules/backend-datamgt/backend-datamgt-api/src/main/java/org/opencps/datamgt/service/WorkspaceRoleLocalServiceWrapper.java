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
 * Provides a wrapper for {@link WorkspaceRoleLocalService}.
 *
 * @author Binhth
 * @see WorkspaceRoleLocalService
 * @generated
 */
@ProviderType
public class WorkspaceRoleLocalServiceWrapper
	implements WorkspaceRoleLocalService,
		ServiceWrapper<WorkspaceRoleLocalService> {
	public WorkspaceRoleLocalServiceWrapper(
		WorkspaceRoleLocalService workspaceRoleLocalService) {
		_workspaceRoleLocalService = workspaceRoleLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workspaceRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workspaceRoleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _workspaceRoleLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workspaceRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of workspace roles.
	*
	* @return the number of workspace roles
	*/
	@Override
	public int getWorkspaceRolesCount() {
		return _workspaceRoleLocalService.getWorkspaceRolesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workspaceRoleLocalService.getOSGiServiceIdentifier();
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
		return _workspaceRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workspaceRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workspaceRoleLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the workspace roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @return the range of workspace roles
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceRole> getWorkspaceRoles(
		int start, int end) {
		return _workspaceRoleLocalService.getWorkspaceRoles(start, end);
	}

	/**
	* Returns all the workspace roles matching the UUID and company.
	*
	* @param uuid the UUID of the workspace roles
	* @param companyId the primary key of the company
	* @return the matching workspace roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceRole> getWorkspaceRolesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _workspaceRoleLocalService.getWorkspaceRolesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of workspace roles matching the UUID and company.
	*
	* @param uuid the UUID of the workspace roles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of workspace roles
	* @param end the upper bound of the range of workspace roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching workspace roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceRole> getWorkspaceRolesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.WorkspaceRole> orderByComparator) {
		return _workspaceRoleLocalService.getWorkspaceRolesByUuidAndCompanyId(uuid,
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
		return _workspaceRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workspaceRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the workspace role to the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRole the workspace role
	* @return the workspace role that was added
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole addWorkspaceRole(
		org.opencps.datamgt.model.WorkspaceRole workspaceRole) {
		return _workspaceRoleLocalService.addWorkspaceRole(workspaceRole);
	}

	/**
	* Creates a new workspace role with the primary key. Does not add the workspace role to the database.
	*
	* @param workspaceRoleId the primary key for the new workspace role
	* @return the new workspace role
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole createWorkspaceRole(
		long workspaceRoleId) {
		return _workspaceRoleLocalService.createWorkspaceRole(workspaceRoleId);
	}

	/**
	* Deletes the workspace role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role that was removed
	* @throws PortalException if a workspace role with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole deleteWorkspaceRole(
		long workspaceRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceRoleLocalService.deleteWorkspaceRole(workspaceRoleId);
	}

	/**
	* Deletes the workspace role from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRole the workspace role
	* @return the workspace role that was removed
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole deleteWorkspaceRole(
		org.opencps.datamgt.model.WorkspaceRole workspaceRole) {
		return _workspaceRoleLocalService.deleteWorkspaceRole(workspaceRole);
	}

	@Override
	public org.opencps.datamgt.model.WorkspaceRole fetchWorkspaceRole(
		long workspaceRoleId) {
		return _workspaceRoleLocalService.fetchWorkspaceRole(workspaceRoleId);
	}

	/**
	* Returns the workspace role matching the UUID and group.
	*
	* @param uuid the workspace role's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole fetchWorkspaceRoleByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _workspaceRoleLocalService.fetchWorkspaceRoleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the workspace role with the primary key.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role
	* @throws PortalException if a workspace role with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole getWorkspaceRole(
		long workspaceRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceRoleLocalService.getWorkspaceRole(workspaceRoleId);
	}

	/**
	* Returns the workspace role matching the UUID and group.
	*
	* @param uuid the workspace role's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace role
	* @throws PortalException if a matching workspace role could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole getWorkspaceRoleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceRoleLocalService.getWorkspaceRoleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the workspace role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workspaceRole the workspace role
	* @return the workspace role that was updated
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceRole updateWorkspaceRole(
		org.opencps.datamgt.model.WorkspaceRole workspaceRole) {
		return _workspaceRoleLocalService.updateWorkspaceRole(workspaceRole);
	}

	@Override
	public WorkspaceRoleLocalService getWrappedService() {
		return _workspaceRoleLocalService;
	}

	@Override
	public void setWrappedService(
		WorkspaceRoleLocalService workspaceRoleLocalService) {
		_workspaceRoleLocalService = workspaceRoleLocalService;
	}

	private WorkspaceRoleLocalService _workspaceRoleLocalService;
}