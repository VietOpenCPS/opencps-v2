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
 * Provides a wrapper for {@link JoinSiteStatusLocalService}.
 *
 * @author Binhth
 * @see JoinSiteStatusLocalService
 * @generated
 */
@ProviderType
public class JoinSiteStatusLocalServiceWrapper
	implements JoinSiteStatusLocalService,
		ServiceWrapper<JoinSiteStatusLocalService> {
	public JoinSiteStatusLocalServiceWrapper(
		JoinSiteStatusLocalService joinSiteStatusLocalService) {
		_joinSiteStatusLocalService = joinSiteStatusLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _joinSiteStatusLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _joinSiteStatusLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _joinSiteStatusLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _joinSiteStatusLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _joinSiteStatusLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Returns the number of join site statuses.
	*
	* @return the number of join site statuses
	*/
	@Override
	public int getJoinSiteStatusesCount() {
		return _joinSiteStatusLocalService.getJoinSiteStatusesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _joinSiteStatusLocalService.getOSGiServiceIdentifier();
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
		return _joinSiteStatusLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _joinSiteStatusLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _joinSiteStatusLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.JoinSiteStatus> getJoinSiteStatuses(
		int start, int end) {
		return _joinSiteStatusLocalService.getJoinSiteStatuses(start, end);
	}

	/**
	* Returns all the join site statuses matching the UUID and company.
	*
	* @param uuid the UUID of the join site statuses
	* @param companyId the primary key of the company
	* @return the matching join site statuses, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.JoinSiteStatus> getJoinSiteStatusesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _joinSiteStatusLocalService.getJoinSiteStatusesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.JoinSiteStatus> getJoinSiteStatusesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.JoinSiteStatus> orderByComparator) {
		return _joinSiteStatusLocalService.getJoinSiteStatusesByUuidAndCompanyId(uuid,
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
		return _joinSiteStatusLocalService.dynamicQueryCount(dynamicQuery);
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
		return _joinSiteStatusLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.JoinSiteStatus addJoinSiteStatus(
		long userId, long groupId, long employeeId, long joinSiteGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.addJoinSiteStatus(userId, groupId,
			employeeId, joinSiteGroupId, serviceContext);
	}

	/**
	* Adds the join site status to the database. Also notifies the appropriate model listeners.
	*
	* @param joinSiteStatus the join site status
	* @return the join site status that was added
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus addJoinSiteStatus(
		org.opencps.usermgt.model.JoinSiteStatus joinSiteStatus) {
		return _joinSiteStatusLocalService.addJoinSiteStatus(joinSiteStatus);
	}

	@Override
	public org.opencps.usermgt.model.JoinSiteStatus assignJoinSiteStatus(
		long userId, long groupId, long employeeId, long joinSiteGroupId,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.assignJoinSiteStatus(userId,
			groupId, employeeId, joinSiteGroupId, status, serviceContext);
	}

	/**
	* Creates a new join site status with the primary key. Does not add the join site status to the database.
	*
	* @param JoinSiteStatusId the primary key for the new join site status
	* @return the new join site status
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus createJoinSiteStatus(
		long JoinSiteStatusId) {
		return _joinSiteStatusLocalService.createJoinSiteStatus(JoinSiteStatusId);
	}

	/**
	* Deletes the join site status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status that was removed
	* @throws PortalException if a join site status with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus deleteJoinSiteStatus(
		long JoinSiteStatusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.deleteJoinSiteStatus(JoinSiteStatusId);
	}

	@Override
	public org.opencps.usermgt.model.JoinSiteStatus deleteJoinSiteStatus(
		long JoinSiteStatusId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _joinSiteStatusLocalService.deleteJoinSiteStatus(JoinSiteStatusId,
			serviceContext);
	}

	/**
	* Deletes the join site status from the database. Also notifies the appropriate model listeners.
	*
	* @param joinSiteStatus the join site status
	* @return the join site status that was removed
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus deleteJoinSiteStatus(
		org.opencps.usermgt.model.JoinSiteStatus joinSiteStatus) {
		return _joinSiteStatusLocalService.deleteJoinSiteStatus(joinSiteStatus);
	}

	@Override
	public org.opencps.usermgt.model.JoinSiteStatus fetchJoinSiteStatus(
		long JoinSiteStatusId) {
		return _joinSiteStatusLocalService.fetchJoinSiteStatus(JoinSiteStatusId);
	}

	/**
	* Returns the join site status matching the UUID and group.
	*
	* @param uuid the join site status's UUID
	* @param groupId the primary key of the group
	* @return the matching join site status, or <code>null</code> if a matching join site status could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus fetchJoinSiteStatusByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _joinSiteStatusLocalService.fetchJoinSiteStatusByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the join site status with the primary key.
	*
	* @param JoinSiteStatusId the primary key of the join site status
	* @return the join site status
	* @throws PortalException if a join site status with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus getJoinSiteStatus(
		long JoinSiteStatusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.getJoinSiteStatus(JoinSiteStatusId);
	}

	/**
	* Returns the join site status matching the UUID and group.
	*
	* @param uuid the join site status's UUID
	* @param groupId the primary key of the group
	* @return the matching join site status
	* @throws PortalException if a matching join site status could not be found
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus getJoinSiteStatusByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.getJoinSiteStatusByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.usermgt.model.JoinSiteStatus updateJoinSiteStatus(
		long userId, long JoinSiteStatusId, long joinSiteGroupId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _joinSiteStatusLocalService.updateJoinSiteStatus(userId,
			JoinSiteStatusId, joinSiteGroupId, status, serviceContext);
	}

	/**
	* Updates the join site status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param joinSiteStatus the join site status
	* @return the join site status that was updated
	*/
	@Override
	public org.opencps.usermgt.model.JoinSiteStatus updateJoinSiteStatus(
		org.opencps.usermgt.model.JoinSiteStatus joinSiteStatus) {
		return _joinSiteStatusLocalService.updateJoinSiteStatus(joinSiteStatus);
	}

	@Override
	public JoinSiteStatusLocalService getWrappedService() {
		return _joinSiteStatusLocalService;
	}

	@Override
	public void setWrappedService(
		JoinSiteStatusLocalService joinSiteStatusLocalService) {
		_joinSiteStatusLocalService = joinSiteStatusLocalService;
	}

	private JoinSiteStatusLocalService _joinSiteStatusLocalService;
}