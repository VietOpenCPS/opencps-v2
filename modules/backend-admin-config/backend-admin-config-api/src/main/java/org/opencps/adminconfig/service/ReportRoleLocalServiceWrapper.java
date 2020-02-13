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

package org.opencps.adminconfig.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ReportRoleLocalService}.
 *
 * @author binhth
 * @see ReportRoleLocalService
 * @generated
 */
@ProviderType
public class ReportRoleLocalServiceWrapper implements ReportRoleLocalService,
	ServiceWrapper<ReportRoleLocalService> {
	public ReportRoleLocalServiceWrapper(
		ReportRoleLocalService reportRoleLocalService) {
		_reportRoleLocalService = reportRoleLocalService;
	}

	/**
	* Adds the report role to the database. Also notifies the appropriate model listeners.
	*
	* @param reportRole the report role
	* @return the report role that was added
	*/
	@Override
	public org.opencps.adminconfig.model.ReportRole addReportRole(
		org.opencps.adminconfig.model.ReportRole reportRole) {
		return _reportRoleLocalService.addReportRole(reportRole);
	}

	@Override
	public org.opencps.adminconfig.model.ReportRole adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _reportRoleLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.adminconfig.model.ReportRole adminProcessDelete(Long id) {
		return _reportRoleLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new report role with the primary key. Does not add the report role to the database.
	*
	* @param reportRoleId the primary key for the new report role
	* @return the new report role
	*/
	@Override
	public org.opencps.adminconfig.model.ReportRole createReportRole(
		long reportRoleId) {
		return _reportRoleLocalService.createReportRole(reportRoleId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportRoleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the report role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role that was removed
	* @throws PortalException if a report role with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.ReportRole deleteReportRole(
		long reportRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportRoleLocalService.deleteReportRole(reportRoleId);
	}

	/**
	* Deletes the report role from the database. Also notifies the appropriate model listeners.
	*
	* @param reportRole the report role
	* @return the report role that was removed
	*/
	@Override
	public org.opencps.adminconfig.model.ReportRole deleteReportRole(
		org.opencps.adminconfig.model.ReportRole reportRole) {
		return _reportRoleLocalService.deleteReportRole(reportRole);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reportRoleLocalService.dynamicQuery();
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
		return _reportRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _reportRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _reportRoleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _reportRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _reportRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.adminconfig.model.ReportRole fetchByDRID_RID(
		long dynamicReportId, long roleId) {
		return _reportRoleLocalService.fetchByDRID_RID(dynamicReportId, roleId);
	}

	@Override
	public org.opencps.adminconfig.model.ReportRole fetchReportRole(
		long reportRoleId) {
		return _reportRoleLocalService.fetchReportRole(reportRoleId);
	}

	@Override
	public java.util.List<org.opencps.adminconfig.model.ReportRole> findByDRID(
		long dynamicReportId) {
		return _reportRoleLocalService.findByDRID(dynamicReportId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _reportRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _reportRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _reportRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the report role with the primary key.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role
	* @throws PortalException if a report role with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.ReportRole getReportRole(
		long reportRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportRoleLocalService.getReportRole(reportRoleId);
	}

	/**
	* Returns a range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of report roles
	*/
	@Override
	public java.util.List<org.opencps.adminconfig.model.ReportRole> getReportRoles(
		int start, int end) {
		return _reportRoleLocalService.getReportRoles(start, end);
	}

	/**
	* Returns the number of report roles.
	*
	* @return the number of report roles
	*/
	@Override
	public int getReportRolesCount() {
		return _reportRoleLocalService.getReportRolesCount();
	}

	/**
	* Updates the report role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param reportRole the report role
	* @return the report role that was updated
	*/
	@Override
	public org.opencps.adminconfig.model.ReportRole updateReportRole(
		org.opencps.adminconfig.model.ReportRole reportRole) {
		return _reportRoleLocalService.updateReportRole(reportRole);
	}

	@Override
	public ReportRoleLocalService getWrappedService() {
		return _reportRoleLocalService;
	}

	@Override
	public void setWrappedService(ReportRoleLocalService reportRoleLocalService) {
		_reportRoleLocalService = reportRoleLocalService;
	}

	private ReportRoleLocalService _reportRoleLocalService;
}