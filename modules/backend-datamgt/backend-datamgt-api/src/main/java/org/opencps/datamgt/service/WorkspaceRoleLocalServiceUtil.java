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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WorkspaceRole. This utility wraps
 * {@link org.mobilink.backend.datamgt.service.impl.WorkspaceRoleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see WorkspaceRoleLocalService
 * @see org.mobilink.backend.datamgt.service.base.WorkspaceRoleLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.impl.WorkspaceRoleLocalServiceImpl
 * @generated
 */
@ProviderType
public class WorkspaceRoleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.datamgt.service.impl.WorkspaceRoleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	/**
	* Returns the number of workspace roles.
	*
	* @return the number of workspace roles
	*/
	public static int getWorkspaceRolesCount() {
		return getService().getWorkspaceRolesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<org.opencps.datamgt.model.WorkspaceRole> getWorkspaceRoles(
		int start, int end) {
		return getService().getWorkspaceRoles(start, end);
	}

	/**
	* Returns all the workspace roles matching the UUID and company.
	*
	* @param uuid the UUID of the workspace roles
	* @param companyId the primary key of the company
	* @return the matching workspace roles, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.WorkspaceRole> getWorkspaceRolesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getWorkspaceRolesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.datamgt.model.WorkspaceRole> getWorkspaceRolesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.WorkspaceRole> orderByComparator) {
		return getService()
				   .getWorkspaceRolesByUuidAndCompanyId(uuid, companyId, start,
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

	/**
	* Adds the workspace role to the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRole the workspace role
	* @return the workspace role that was added
	*/
	public static org.opencps.datamgt.model.WorkspaceRole addWorkspaceRole(
		org.opencps.datamgt.model.WorkspaceRole workspaceRole) {
		return getService().addWorkspaceRole(workspaceRole);
	}

	/**
	* Creates a new workspace role with the primary key. Does not add the workspace role to the database.
	*
	* @param workspaceRoleId the primary key for the new workspace role
	* @return the new workspace role
	*/
	public static org.opencps.datamgt.model.WorkspaceRole createWorkspaceRole(
		long workspaceRoleId) {
		return getService().createWorkspaceRole(workspaceRoleId);
	}

	/**
	* Deletes the workspace role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role that was removed
	* @throws PortalException if a workspace role with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.WorkspaceRole deleteWorkspaceRole(
		long workspaceRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWorkspaceRole(workspaceRoleId);
	}

	/**
	* Deletes the workspace role from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceRole the workspace role
	* @return the workspace role that was removed
	*/
	public static org.opencps.datamgt.model.WorkspaceRole deleteWorkspaceRole(
		org.opencps.datamgt.model.WorkspaceRole workspaceRole) {
		return getService().deleteWorkspaceRole(workspaceRole);
	}

	public static org.opencps.datamgt.model.WorkspaceRole fetchWorkspaceRole(
		long workspaceRoleId) {
		return getService().fetchWorkspaceRole(workspaceRoleId);
	}

	/**
	* Returns the workspace role matching the UUID and group.
	*
	* @param uuid the workspace role's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace role, or <code>null</code> if a matching workspace role could not be found
	*/
	public static org.opencps.datamgt.model.WorkspaceRole fetchWorkspaceRoleByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchWorkspaceRoleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the workspace role with the primary key.
	*
	* @param workspaceRoleId the primary key of the workspace role
	* @return the workspace role
	* @throws PortalException if a workspace role with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.WorkspaceRole getWorkspaceRole(
		long workspaceRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkspaceRole(workspaceRoleId);
	}

	/**
	* Returns the workspace role matching the UUID and group.
	*
	* @param uuid the workspace role's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace role
	* @throws PortalException if a matching workspace role could not be found
	*/
	public static org.opencps.datamgt.model.WorkspaceRole getWorkspaceRoleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkspaceRoleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the workspace role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workspaceRole the workspace role
	* @return the workspace role that was updated
	*/
	public static org.opencps.datamgt.model.WorkspaceRole updateWorkspaceRole(
		org.opencps.datamgt.model.WorkspaceRole workspaceRole) {
		return getService().updateWorkspaceRole(workspaceRole);
	}

	public static WorkspaceRoleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkspaceRoleLocalService, WorkspaceRoleLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WorkspaceRoleLocalService.class);
}