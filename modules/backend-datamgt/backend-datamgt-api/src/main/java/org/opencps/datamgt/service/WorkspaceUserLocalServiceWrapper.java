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
 * Provides a wrapper for {@link WorkspaceUserLocalService}.
 *
 * @author Binhth
 * @see WorkspaceUserLocalService
 * @generated
 */
@ProviderType
public class WorkspaceUserLocalServiceWrapper
	implements WorkspaceUserLocalService,
		ServiceWrapper<WorkspaceUserLocalService> {
	public WorkspaceUserLocalServiceWrapper(
		WorkspaceUserLocalService workspaceUserLocalService) {
		_workspaceUserLocalService = workspaceUserLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workspaceUserLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workspaceUserLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _workspaceUserLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workspaceUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceUserLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits workspaceUserFilter(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _workspaceUserLocalService.workspaceUserFilter(params, sorts,
			start, end, searchContext);
	}

	/**
	* Returns the number of workspace users.
	*
	* @return the number of workspace users
	*/
	@Override
	public int getWorkspaceUsersCount() {
		return _workspaceUserLocalService.getWorkspaceUsersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workspaceUserLocalService.getOSGiServiceIdentifier();
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
		return _workspaceUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workspaceUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workspaceUserLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceUser> findByF_workspaceId(
		long workspaceId) {
		return _workspaceUserLocalService.findByF_workspaceId(workspaceId);
	}

	/**
	* Returns a range of all the workspace users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @return the range of workspace users
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceUser> getWorkspaceUsers(
		int start, int end) {
		return _workspaceUserLocalService.getWorkspaceUsers(start, end);
	}

	/**
	* Returns all the workspace users matching the UUID and company.
	*
	* @param uuid the UUID of the workspace users
	* @param companyId the primary key of the company
	* @return the matching workspace users, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceUser> getWorkspaceUsersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _workspaceUserLocalService.getWorkspaceUsersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of workspace users matching the UUID and company.
	*
	* @param uuid the UUID of the workspace users
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of workspace users
	* @param end the upper bound of the range of workspace users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching workspace users, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkspaceUser> getWorkspaceUsersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.WorkspaceUser> orderByComparator) {
		return _workspaceUserLocalService.getWorkspaceUsersByUuidAndCompanyId(uuid,
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
		return _workspaceUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workspaceUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.datamgt.model.WorkspaceUser addWorkspaceUser(
		long userId, long groupId, long workspaceId, long assignUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workspaceUserLocalService.addWorkspaceUser(userId, groupId,
			workspaceId, assignUserId, serviceContext);
	}

	/**
	* Adds the workspace user to the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceUser the workspace user
	* @return the workspace user that was added
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser addWorkspaceUser(
		org.opencps.datamgt.model.WorkspaceUser workspaceUser) {
		return _workspaceUserLocalService.addWorkspaceUser(workspaceUser);
	}

	/**
	* Creates a new workspace user with the primary key. Does not add the workspace user to the database.
	*
	* @param workspaceUserId the primary key for the new workspace user
	* @return the new workspace user
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser createWorkspaceUser(
		long workspaceUserId) {
		return _workspaceUserLocalService.createWorkspaceUser(workspaceUserId);
	}

	/**
	* Deletes the workspace user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user that was removed
	* @throws PortalException if a workspace user with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser deleteWorkspaceUser(
		long workspaceUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceUserLocalService.deleteWorkspaceUser(workspaceUserId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser deleteWorkspaceUser(
		long workspaceUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workspaceUserLocalService.deleteWorkspaceUser(workspaceUserId,
			serviceContext);
	}

	/**
	* Deletes the workspace user from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceUser the workspace user
	* @return the workspace user that was removed
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser deleteWorkspaceUser(
		org.opencps.datamgt.model.WorkspaceUser workspaceUser) {
		return _workspaceUserLocalService.deleteWorkspaceUser(workspaceUser);
	}

	@Override
	public org.opencps.datamgt.model.WorkspaceUser fetchWorkspaceUser(
		long workspaceUserId) {
		return _workspaceUserLocalService.fetchWorkspaceUser(workspaceUserId);
	}

	/**
	* Returns the workspace user matching the UUID and group.
	*
	* @param uuid the workspace user's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace user, or <code>null</code> if a matching workspace user could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser fetchWorkspaceUserByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _workspaceUserLocalService.fetchWorkspaceUserByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the workspace user with the primary key.
	*
	* @param workspaceUserId the primary key of the workspace user
	* @return the workspace user
	* @throws PortalException if a workspace user with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser getWorkspaceUser(
		long workspaceUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceUserLocalService.getWorkspaceUser(workspaceUserId);
	}

	/**
	* Returns the workspace user matching the UUID and group.
	*
	* @param uuid the workspace user's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace user
	* @throws PortalException if a matching workspace user could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser getWorkspaceUserByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workspaceUserLocalService.getWorkspaceUserByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* @param userId
	* @param dictCollectionId
	* @param fullName
	* @param companyName
	* @param telNo
	* @param email
	* @param mobilinkId
	* @param userMappingId
	* @param outSide
	* @param isOrg
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser updateWorkspaceUser(
		long userId, long groupId, long workspaceUserId, long workspaceId,
		long assignUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workspaceUserLocalService.updateWorkspaceUser(userId, groupId,
			workspaceUserId, workspaceId, assignUserId, serviceContext);
	}

	/**
	* Updates the workspace user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workspaceUser the workspace user
	* @return the workspace user that was updated
	*/
	@Override
	public org.opencps.datamgt.model.WorkspaceUser updateWorkspaceUser(
		org.opencps.datamgt.model.WorkspaceUser workspaceUser) {
		return _workspaceUserLocalService.updateWorkspaceUser(workspaceUser);
	}

	@Override
	public WorkspaceUserLocalService getWrappedService() {
		return _workspaceUserLocalService;
	}

	@Override
	public void setWrappedService(
		WorkspaceUserLocalService workspaceUserLocalService) {
		_workspaceUserLocalService = workspaceUserLocalService;
	}

	private WorkspaceUserLocalService _workspaceUserLocalService;
}