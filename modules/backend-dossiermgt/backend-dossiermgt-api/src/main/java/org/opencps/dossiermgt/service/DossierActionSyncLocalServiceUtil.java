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
 * Provides the local service utility for DossierActionSync. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.DossierActionSyncLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see DossierActionSyncLocalService
 * @see org.opencps.dossiermgt.service.base.DossierActionSyncLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierActionSyncLocalServiceImpl
 * @generated
 */
@ProviderType
public class DossierActionSyncLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierActionSyncLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dossier action sync to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionSync the dossier action sync
	* @return the dossier action sync that was added
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync addDossierActionSync(
		org.opencps.dossiermgt.model.DossierActionSync dossierActionSync) {
		return getService().addDossierActionSync(dossierActionSync);
	}

	/**
	* Creates a new dossier action sync with the primary key. Does not add the dossier action sync to the database.
	*
	* @param dossierActionSyncId the primary key for the new dossier action sync
	* @return the new dossier action sync
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync createDossierActionSync(
		long dossierActionSyncId) {
		return getService().createDossierActionSync(dossierActionSyncId);
	}

	/**
	* Deletes the dossier action sync from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionSync the dossier action sync
	* @return the dossier action sync that was removed
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync deleteDossierActionSync(
		org.opencps.dossiermgt.model.DossierActionSync dossierActionSync) {
		return getService().deleteDossierActionSync(dossierActionSync);
	}

	/**
	* Deletes the dossier action sync with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync that was removed
	* @throws PortalException if a dossier action sync with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync deleteDossierActionSync(
		long dossierActionSyncId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDossierActionSync(dossierActionSyncId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.DossierActionSync fetchDossierActionSync(
		long dossierActionSyncId) {
		return getService().fetchDossierActionSync(dossierActionSyncId);
	}

	/**
	* Returns the dossier action sync matching the UUID and group.
	*
	* @param uuid the dossier action sync's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier action sync, or <code>null</code> if a matching dossier action sync could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync fetchDossierActionSyncByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDossierActionSyncByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dossier action sync with the primary key.
	*
	* @param dossierActionSyncId the primary key of the dossier action sync
	* @return the dossier action sync
	* @throws PortalException if a dossier action sync with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync getDossierActionSync(
		long dossierActionSyncId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierActionSync(dossierActionSyncId);
	}

	/**
	* Returns the dossier action sync matching the UUID and group.
	*
	* @param uuid the dossier action sync's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier action sync
	* @throws PortalException if a matching dossier action sync could not be found
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync getDossierActionSyncByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierActionSyncByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dossier action syncs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierActionSyncModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @return the range of dossier action syncs
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierActionSync> getDossierActionSyncs(
		int start, int end) {
		return getService().getDossierActionSyncs(start, end);
	}

	/**
	* Returns all the dossier action syncs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier action syncs
	* @param companyId the primary key of the company
	* @return the matching dossier action syncs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierActionSync> getDossierActionSyncsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getDossierActionSyncsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dossier action syncs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier action syncs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier action syncs
	* @param end the upper bound of the range of dossier action syncs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier action syncs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.DossierActionSync> getDossierActionSyncsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierActionSync> orderByComparator) {
		return getService()
				   .getDossierActionSyncsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier action syncs.
	*
	* @return the number of dossier action syncs
	*/
	public static int getDossierActionSyncsCount() {
		return getService().getDossierActionSyncsCount();
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
	* Updates the dossier action sync in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierActionSync the dossier action sync
	* @return the dossier action sync that was updated
	*/
	public static org.opencps.dossiermgt.model.DossierActionSync updateDossierActionSync(
		org.opencps.dossiermgt.model.DossierActionSync dossierActionSync) {
		return getService().updateDossierActionSync(dossierActionSync);
	}

	public static DossierActionSyncLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierActionSyncLocalService, DossierActionSyncLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierActionSyncLocalService.class);

		ServiceTracker<DossierActionSyncLocalService, DossierActionSyncLocalService> serviceTracker =
			new ServiceTracker<DossierActionSyncLocalService, DossierActionSyncLocalService>(bundle.getBundleContext(),
				DossierActionSyncLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}