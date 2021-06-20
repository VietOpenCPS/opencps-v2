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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ReportLandTax. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ReportLandTaxLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ReportLandTaxLocalService
 * @see org.opencps.dossiermgt.service.base.ReportLandTaxLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ReportLandTaxLocalServiceImpl
 * @generated
 */
@ProviderType
public class ReportLandTaxLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ReportLandTaxLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.ReportLandTax addReportLandTax(
		long groupId, String dossierNo, String request, String response,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addReportLandTax(groupId, dossierNo, request, response,
			serviceContext);
	}

	/**
	* Adds the report land tax to the database. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was added
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax addReportLandTax(
		org.opencps.dossiermgt.model.ReportLandTax reportLandTax) {
		return getService().addReportLandTax(reportLandTax);
	}

	/**
	* Creates a new report land tax with the primary key. Does not add the report land tax to the database.
	*
	* @param reportId the primary key for the new report land tax
	* @return the new report land tax
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax createReportLandTax(
		long reportId) {
		return getService().createReportLandTax(reportId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax that was removed
	* @throws PortalException if a report land tax with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax deleteReportLandTax(
		long reportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteReportLandTax(reportId);
	}

	/**
	* Deletes the report land tax from the database. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was removed
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax deleteReportLandTax(
		org.opencps.dossiermgt.model.ReportLandTax reportLandTax) {
		return getService().deleteReportLandTax(reportLandTax);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ReportLandTax fetchReportLandTax(
		long reportId) {
		return getService().fetchReportLandTax(reportId);
	}

	/**
	* Returns the report land tax matching the UUID and group.
	*
	* @param uuid the report land tax's UUID
	* @param groupId the primary key of the group
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax fetchReportLandTaxByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchReportLandTaxByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the report land tax with the primary key.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax
	* @throws PortalException if a report land tax with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax getReportLandTax(
		long reportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReportLandTax(reportId);
	}

	/**
	* Returns the report land tax matching the UUID and group.
	*
	* @param uuid the report land tax's UUID
	* @param groupId the primary key of the group
	* @return the matching report land tax
	* @throws PortalException if a matching report land tax could not be found
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax getReportLandTaxByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getReportLandTaxByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of report land taxs
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ReportLandTax> getReportLandTaxs(
		int start, int end) {
		return getService().getReportLandTaxs(start, end);
	}

	/**
	* Returns all the report land taxs matching the UUID and company.
	*
	* @param uuid the UUID of the report land taxs
	* @param companyId the primary key of the company
	* @return the matching report land taxs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ReportLandTax> getReportLandTaxsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getReportLandTaxsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.ReportLandTax> getReportLandTaxsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ReportLandTax> orderByComparator) {
		return getService()
				   .getReportLandTaxsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of report land taxs.
	*
	* @return the number of report land taxs
	*/
	public static int getReportLandTaxsCount() {
		return getService().getReportLandTaxsCount();
	}

	/**
	* Updates the report land tax in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param reportLandTax the report land tax
	* @return the report land tax that was updated
	*/
	public static org.opencps.dossiermgt.model.ReportLandTax updateReportLandTax(
		org.opencps.dossiermgt.model.ReportLandTax reportLandTax) {
		return getService().updateReportLandTax(reportLandTax);
	}

	public static ReportLandTaxLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReportLandTaxLocalService, ReportLandTaxLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ReportLandTaxLocalService.class);

		ServiceTracker<ReportLandTaxLocalService, ReportLandTaxLocalService> serviceTracker =
			new ServiceTracker<ReportLandTaxLocalService, ReportLandTaxLocalService>(bundle.getBundleContext(),
				ReportLandTaxLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}