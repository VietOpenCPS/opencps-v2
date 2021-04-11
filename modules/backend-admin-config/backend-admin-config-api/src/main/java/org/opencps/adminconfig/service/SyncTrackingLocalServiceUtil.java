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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SyncTracking. This utility wraps
 * {@link org.opencps.adminconfig.service.impl.SyncTrackingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author binhth
 * @see SyncTrackingLocalService
 * @see org.opencps.adminconfig.service.base.SyncTrackingLocalServiceBaseImpl
 * @see org.opencps.adminconfig.service.impl.SyncTrackingLocalServiceImpl
 * @generated
 */
@ProviderType
public class SyncTrackingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.adminconfig.service.impl.SyncTrackingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the sync tracking to the database. Also notifies the appropriate model listeners.
	*
	* @param syncTracking the sync tracking
	* @return the sync tracking that was added
	*/
	public static org.opencps.adminconfig.model.SyncTracking addSyncTracking(
		org.opencps.adminconfig.model.SyncTracking syncTracking) {
		return getService().addSyncTracking(syncTracking);
	}

	/**
	* Creates a new sync tracking with the primary key. Does not add the sync tracking to the database.
	*
	* @param trackingId the primary key for the new sync tracking
	* @return the new sync tracking
	*/
	public static org.opencps.adminconfig.model.SyncTracking createSyncTracking(
		long trackingId) {
		return getService().createSyncTracking(trackingId);
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
	* Deletes the sync tracking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking that was removed
	* @throws PortalException if a sync tracking with the primary key could not be found
	*/
	public static org.opencps.adminconfig.model.SyncTracking deleteSyncTracking(
		long trackingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSyncTracking(trackingId);
	}

	/**
	* Deletes the sync tracking from the database. Also notifies the appropriate model listeners.
	*
	* @param syncTracking the sync tracking
	* @return the sync tracking that was removed
	*/
	public static org.opencps.adminconfig.model.SyncTracking deleteSyncTracking(
		org.opencps.adminconfig.model.SyncTracking syncTracking) {
		return getService().deleteSyncTracking(syncTracking);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.adminconfig.model.SyncTracking fetchSyncTracking(
		long trackingId) {
		return getService().fetchSyncTracking(trackingId);
	}

	/**
	* Returns the sync tracking matching the UUID and group.
	*
	* @param uuid the sync tracking's UUID
	* @param groupId the primary key of the group
	* @return the matching sync tracking, or <code>null</code> if a matching sync tracking could not be found
	*/
	public static org.opencps.adminconfig.model.SyncTracking fetchSyncTrackingByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchSyncTrackingByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByApiAndDate(
		String api, java.util.Date fromDate, java.util.Date toDate, int start,
		int end) {
		return getService().getByApiAndDate(api, fromDate, toDate, start, end);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByApiAndDossierNoAndDate(
		String api, String dossierNo, java.util.Date fromDate,
		java.util.Date toDate, int start, int end) {
		return getService()
				   .getByApiAndDossierNoAndDate(api, dossierNo, fromDate,
			toDate, start, end);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByApiAndServiceCodeAndDate(
		String api, String serviceCode, java.util.Date fromDate,
		java.util.Date toDate, int start, int end) {
		return getService()
				   .getByApiAndServiceCodeAndDate(api, serviceCode, fromDate,
			toDate, start, end);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByApiAndServiceCodeAndDossierNoAndDate(
		String api, String dossierNo, String serviceCode,
		java.util.Date fromDate, java.util.Date toDate, int start, int end) {
		return getService()
				   .getByApiAndServiceCodeAndDossierNoAndDate(api, dossierNo,
			serviceCode, fromDate, toDate, start, end);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByDate(
		java.util.Date fromDate, java.util.Date toDate, int start, int end) {
		return getService().getByDate(fromDate, toDate, start, end);
	}

	public static org.opencps.adminconfig.model.SyncTracking getByDossierNo(
		String dossierNo) {
		return getService().getByDossierNo(dossierNo);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByDossierNoAndDate(
		String dossierNo, java.util.Date fromDate, java.util.Date toDate,
		int start, int end) {
		return getService()
				   .getByDossierNoAndDate(dossierNo, fromDate, toDate, start,
			end);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByDossierNoAndServiceCodeAndDate(
		String dossierNo, String serviceCode, java.util.Date fromDate,
		java.util.Date toDate, int start, int end) {
		return getService()
				   .getByDossierNoAndServiceCodeAndDate(dossierNo, serviceCode,
			fromDate, toDate, start, end);
	}

	public static org.opencps.adminconfig.model.SyncTracking getByReferenceUid(
		String referenceUid) {
		return getService().getByReferenceUid(referenceUid);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByReferenceUidAndDate(
		String referenceUid, java.util.Date fromDate, java.util.Date toDate,
		int start, int end) {
		return getService()
				   .getByReferenceUidAndDate(referenceUid, fromDate, toDate,
			start, end);
	}

	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getByServiceCodeAndDate(
		String serviceCode, java.util.Date fromDate, java.util.Date toDate,
		int start, int end) {
		return getService()
				   .getByServiceCodeAndDate(serviceCode, fromDate, toDate,
			start, end);
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
	* Returns the sync tracking with the primary key.
	*
	* @param trackingId the primary key of the sync tracking
	* @return the sync tracking
	* @throws PortalException if a sync tracking with the primary key could not be found
	*/
	public static org.opencps.adminconfig.model.SyncTracking getSyncTracking(
		long trackingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncTracking(trackingId);
	}

	/**
	* Returns the sync tracking matching the UUID and group.
	*
	* @param uuid the sync tracking's UUID
	* @param groupId the primary key of the group
	* @return the matching sync tracking
	* @throws PortalException if a matching sync tracking could not be found
	*/
	public static org.opencps.adminconfig.model.SyncTracking getSyncTrackingByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSyncTrackingByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the sync trackings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.adminconfig.model.impl.SyncTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @return the range of sync trackings
	*/
	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getSyncTrackings(
		int start, int end) {
		return getService().getSyncTrackings(start, end);
	}

	/**
	* Returns all the sync trackings matching the UUID and company.
	*
	* @param uuid the UUID of the sync trackings
	* @param companyId the primary key of the company
	* @return the matching sync trackings, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getSyncTrackingsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getSyncTrackingsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of sync trackings matching the UUID and company.
	*
	* @param uuid the UUID of the sync trackings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sync trackings
	* @param end the upper bound of the range of sync trackings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sync trackings, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.adminconfig.model.SyncTracking> getSyncTrackingsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.adminconfig.model.SyncTracking> orderByComparator) {
		return getService()
				   .getSyncTrackingsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of sync trackings.
	*
	* @return the number of sync trackings
	*/
	public static int getSyncTrackingsCount() {
		return getService().getSyncTrackingsCount();
	}

	public static org.opencps.adminconfig.model.SyncTracking updateSyncTracking(
		long userId, long groupId, long trackingId, String dossierNo,
		String referenceUid, String serviceCode, int stateSync, String api,
		String bodyRequest, String bodyResponse) {
		return getService()
				   .updateSyncTracking(userId, groupId, trackingId, dossierNo,
			referenceUid, serviceCode, stateSync, api, bodyRequest, bodyResponse);
	}

	/**
	* Updates the sync tracking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncTracking the sync tracking
	* @return the sync tracking that was updated
	*/
	public static org.opencps.adminconfig.model.SyncTracking updateSyncTracking(
		org.opencps.adminconfig.model.SyncTracking syncTracking) {
		return getService().updateSyncTracking(syncTracking);
	}

	public static SyncTrackingLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SyncTrackingLocalService, SyncTrackingLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SyncTrackingLocalService.class);

		ServiceTracker<SyncTrackingLocalService, SyncTrackingLocalService> serviceTracker =
			new ServiceTracker<SyncTrackingLocalService, SyncTrackingLocalService>(bundle.getBundleContext(),
				SyncTrackingLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}