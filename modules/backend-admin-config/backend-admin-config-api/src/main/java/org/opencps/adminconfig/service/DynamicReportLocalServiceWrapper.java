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
 * Provides a wrapper for {@link DynamicReportLocalService}.
 *
 * @author binhth
 * @see DynamicReportLocalService
 * @generated
 */
@ProviderType
public class DynamicReportLocalServiceWrapper
	implements DynamicReportLocalService,
		ServiceWrapper<DynamicReportLocalService> {
	public DynamicReportLocalServiceWrapper(
		DynamicReportLocalService dynamicReportLocalService) {
		_dynamicReportLocalService = dynamicReportLocalService;
	}

	/**
	* Adds the dynamic report to the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicReport the dynamic report
	* @return the dynamic report that was added
	*/
	@Override
	public org.opencps.adminconfig.model.DynamicReport addDynamicReport(
		org.opencps.adminconfig.model.DynamicReport dynamicReport) {
		return _dynamicReportLocalService.addDynamicReport(dynamicReport);
	}

	@Override
	public org.opencps.adminconfig.model.DynamicReport adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _dynamicReportLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.adminconfig.model.DynamicReport adminProcessDelete(
		Long id) {
		return _dynamicReportLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new dynamic report with the primary key. Does not add the dynamic report to the database.
	*
	* @param dynamicReportId the primary key for the new dynamic report
	* @return the new dynamic report
	*/
	@Override
	public org.opencps.adminconfig.model.DynamicReport createDynamicReport(
		long dynamicReportId) {
		return _dynamicReportLocalService.createDynamicReport(dynamicReportId);
	}

	/**
	* Deletes the dynamic report from the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicReport the dynamic report
	* @return the dynamic report that was removed
	*/
	@Override
	public org.opencps.adminconfig.model.DynamicReport deleteDynamicReport(
		org.opencps.adminconfig.model.DynamicReport dynamicReport) {
		return _dynamicReportLocalService.deleteDynamicReport(dynamicReport);
	}

	/**
	* Deletes the dynamic report with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report that was removed
	* @throws PortalException if a dynamic report with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.DynamicReport deleteDynamicReport(
		long dynamicReportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicReportLocalService.deleteDynamicReport(dynamicReportId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicReportLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dynamicReportLocalService.dynamicQuery();
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
		return _dynamicReportLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dynamicReportLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dynamicReportLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _dynamicReportLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dynamicReportLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.adminconfig.model.DynamicReport fetchByCode(
		long groupId, String reportCode) {
		return _dynamicReportLocalService.fetchByCode(groupId, reportCode);
	}

	@Override
	public org.opencps.adminconfig.model.DynamicReport fetchDynamicReport(
		long dynamicReportId) {
		return _dynamicReportLocalService.fetchDynamicReport(dynamicReportId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dynamicReportLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.adminconfig.model.DynamicReport> getByGroup(
		long groupId, int start, int end) {
		return _dynamicReportLocalService.getByGroup(groupId, start, end);
	}

	@Override
	public java.util.List<org.opencps.adminconfig.model.DynamicReport> getByGroupType(
		long groupId, String reportType, int start, int end) {
		return _dynamicReportLocalService.getByGroupType(groupId, reportType,
			start, end);
	}

	/**
	* Returns the dynamic report with the primary key.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report
	* @throws PortalException if a dynamic report with the primary key could not be found
	*/
	@Override
	public org.opencps.adminconfig.model.DynamicReport getDynamicReport(
		long dynamicReportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicReportLocalService.getDynamicReport(dynamicReportId);
	}

	/**
	* Returns a range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of dynamic reports
	*/
	@Override
	public java.util.List<org.opencps.adminconfig.model.DynamicReport> getDynamicReports(
		int start, int end) {
		return _dynamicReportLocalService.getDynamicReports(start, end);
	}

	/**
	* Returns the number of dynamic reports.
	*
	* @return the number of dynamic reports
	*/
	@Override
	public int getDynamicReportsCount() {
		return _dynamicReportLocalService.getDynamicReportsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dynamicReportLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dynamicReportLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dynamicReportLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dynamic report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dynamicReport the dynamic report
	* @return the dynamic report that was updated
	*/
	@Override
	public org.opencps.adminconfig.model.DynamicReport updateDynamicReport(
		org.opencps.adminconfig.model.DynamicReport dynamicReport) {
		return _dynamicReportLocalService.updateDynamicReport(dynamicReport);
	}

	@Override
	public DynamicReportLocalService getWrappedService() {
		return _dynamicReportLocalService;
	}

	@Override
	public void setWrappedService(
		DynamicReportLocalService dynamicReportLocalService) {
		_dynamicReportLocalService = dynamicReportLocalService;
	}

	private DynamicReportLocalService _dynamicReportLocalService;
}