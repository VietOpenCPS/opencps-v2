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

package org.opencps.reportland.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.opencps.reportland.model.ReportLandTax;

/**
 * Provides a wrapper for {@link ReportLandTaxLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ReportLandTaxLocalService
 * @generated
 */
@ProviderType
public class ReportLandTaxLocalServiceWrapper
	implements ReportLandTaxLocalService,
		ServiceWrapper<ReportLandTaxLocalService> {
	public ReportLandTaxLocalServiceWrapper(
		ReportLandTaxLocalService reportLandTaxLocalService) {
		_reportLandTaxLocalService = reportLandTaxLocalService;
	}

	/**
	* Adds the report land tax to the database. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was added
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax addReportLandTax(
		org.opencps.reportland.model.ReportLandTax reportLandTax) {
		return _reportLandTaxLocalService.addReportLandTax(reportLandTax);
	}

	/**
	* Creates a new report land tax with the primary key. Does not add the report land tax to the database.
	*
	* @param reportId the primary key for the new report land tax
	* @return the new report land tax
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax createReportLandTax(
		long reportId) {
		return _reportLandTaxLocalService.createReportLandTax(reportId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportLandTaxLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax that was removed
	* @throws PortalException if a report land tax with the primary key could not be found
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax deleteReportLandTax(
		long reportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportLandTaxLocalService.deleteReportLandTax(reportId);
	}

	/**
	* Deletes the report land tax from the database. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was removed
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax deleteReportLandTax(
		org.opencps.reportland.model.ReportLandTax reportLandTax) {
		return _reportLandTaxLocalService.deleteReportLandTax(reportLandTax);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _reportLandTaxLocalService.dynamicQuery();
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
		return _reportLandTaxLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.reportland.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _reportLandTaxLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.reportland.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _reportLandTaxLocalService.dynamicQuery(dynamicQuery, start,
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
		return _reportLandTaxLocalService.dynamicQueryCount(dynamicQuery);
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
		return _reportLandTaxLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.reportland.model.ReportLandTax fetchReportLandTax(
		long reportId) {
		return _reportLandTaxLocalService.fetchReportLandTax(reportId);
	}

	/**
	* Returns the report land tax matching the UUID and group.
	*
	* @param uuid the report land tax's UUID
	* @param groupId the primary key of the group
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax fetchReportLandTaxByUuidAndGroupId(
		String uuid, long groupId) {
		return _reportLandTaxLocalService.fetchReportLandTaxByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _reportLandTaxLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _reportLandTaxLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _reportLandTaxLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _reportLandTaxLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportLandTaxLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the report land tax with the primary key.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax
	* @throws PortalException if a report land tax with the primary key could not be found
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax getReportLandTax(
		long reportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportLandTaxLocalService.getReportLandTax(reportId);
	}

	/**
	* Returns the report land tax matching the UUID and group.
	*
	* @param uuid the report land tax's UUID
	* @param groupId the primary key of the group
	* @return the matching report land tax
	* @throws PortalException if a matching report land tax could not be found
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax getReportLandTaxByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _reportLandTaxLocalService.getReportLandTaxByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.reportland.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of report land taxs
	*/
	@Override
	public java.util.List<org.opencps.reportland.model.ReportLandTax> getReportLandTaxs(
		int start, int end) {
		return _reportLandTaxLocalService.getReportLandTaxs(start, end);
	}

	/**
	* Returns all the report land taxs matching the UUID and company.
	*
	* @param uuid the UUID of the report land taxs
	* @param companyId the primary key of the company
	* @return the matching report land taxs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.reportland.model.ReportLandTax> getReportLandTaxsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _reportLandTaxLocalService.getReportLandTaxsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of report land taxs matching the UUID and company.
	*
	* @param uuid the UUID of the report land taxs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching report land taxs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.reportland.model.ReportLandTax> getReportLandTaxsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.reportland.model.ReportLandTax> orderByComparator) {
		return _reportLandTaxLocalService.getReportLandTaxsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of report land taxs.
	*
	* @return the number of report land taxs
	*/
	@Override
	public int getReportLandTaxsCount() {
		return _reportLandTaxLocalService.getReportLandTaxsCount();
	}

	/**
	* Updates the report land tax in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was updated
	*/
	@Override
	public org.opencps.reportland.model.ReportLandTax updateReportLandTax(
		org.opencps.reportland.model.ReportLandTax reportLandTax) {
		return _reportLandTaxLocalService.updateReportLandTax(reportLandTax);
	}

	@Override
	public ReportLandTaxLocalService getWrappedService() {
		return _reportLandTaxLocalService;
	}

	@Override
	public void setWrappedService(
		ReportLandTaxLocalService reportLandTaxLocalService) {
		_reportLandTaxLocalService = reportLandTaxLocalService;
	}

	private ReportLandTaxLocalService _reportLandTaxLocalService;

	@Override
	public ReportLandTax addReportLandTax(long groupId, String dossierNo, String request, String response,
			ServiceContext serviceContext) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return _reportLandTaxLocalService.addReportLandTax(groupId, dossierNo, request, response, serviceContext);
	}
}