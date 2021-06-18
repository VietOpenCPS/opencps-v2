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

package org.opencps.synctracking.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DossierTax. This utility wraps
 * {@link org.opencps.synctracking.service.impl.DossierTaxLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DossierTaxLocalService
 * @see org.opencps.synctracking.service.base.DossierTaxLocalServiceBaseImpl
 * @see org.opencps.synctracking.service.impl.DossierTaxLocalServiceImpl
 * @generated
 */
@ProviderType
public class DossierTaxLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.synctracking.service.impl.DossierTaxLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dossier tax to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was added
	*/
	public static org.opencps.synctracking.model.DossierTax addDossierTax(
		org.opencps.synctracking.model.DossierTax dossierTax) {
		return getService().addDossierTax(dossierTax);
	}

	/**
	* Creates a new dossier tax with the primary key. Does not add the dossier tax to the database.
	*
	* @param taxId the primary key for the new dossier tax
	* @return the new dossier tax
	*/
	public static org.opencps.synctracking.model.DossierTax createDossierTax(
		long taxId) {
		return getService().createDossierTax(taxId);
	}

	public static org.opencps.synctracking.model.DossierTax createDossierTaxManual(
		org.opencps.synctracking.model.DossierTaxInput dossierTaxInput) {
		return getService().createDossierTaxManual(dossierTaxInput);
	}

	/**
	* Deletes the dossier tax from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was removed
	*/
	public static org.opencps.synctracking.model.DossierTax deleteDossierTax(
		org.opencps.synctracking.model.DossierTax dossierTax) {
		return getService().deleteDossierTax(dossierTax);
	}

	/**
	* Deletes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax that was removed
	* @throws PortalException if a dossier tax with the primary key could not be found
	*/
	public static org.opencps.synctracking.model.DossierTax deleteDossierTax(
		long taxId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDossierTax(taxId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.synctracking.model.DossierTax fetchDossierTax(
		long taxId) {
		return getService().fetchDossierTax(taxId);
	}

	public static org.opencps.synctracking.model.DossierTax fetchDossierTaxByDMS(
		String dossierNo, String maSoThue, String soQuyetDinh) {
		return getService()
				   .fetchDossierTaxByDMS(dossierNo, maSoThue, soQuyetDinh);
	}

	/**
	* Returns the dossier tax matching the UUID and group.
	*
	* @param uuid the dossier tax's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	public static org.opencps.synctracking.model.DossierTax fetchDossierTaxByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDossierTaxByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dossier tax with the primary key.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax
	* @throws PortalException if a dossier tax with the primary key could not be found
	*/
	public static org.opencps.synctracking.model.DossierTax getDossierTax(
		long taxId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierTax(taxId);
	}

	/**
	* Returns the dossier tax matching the UUID and group.
	*
	* @param uuid the dossier tax's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier tax
	* @throws PortalException if a matching dossier tax could not be found
	*/
	public static org.opencps.synctracking.model.DossierTax getDossierTaxByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierTaxByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dossier taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synctracking.model.impl.DossierTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @return the range of dossier taxs
	*/
	public static java.util.List<org.opencps.synctracking.model.DossierTax> getDossierTaxs(
		int start, int end) {
		return getService().getDossierTaxs(start, end);
	}

	/**
	* Returns all the dossier taxs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier taxs
	* @param companyId the primary key of the company
	* @return the matching dossier taxs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synctracking.model.DossierTax> getDossierTaxsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getDossierTaxsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dossier taxs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier taxs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier taxs
	* @param end the upper bound of the range of dossier taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier taxs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synctracking.model.DossierTax> getDossierTaxsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synctracking.model.DossierTax> orderByComparator) {
		return getService()
				   .getDossierTaxsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of dossier taxs.
	*
	* @return the number of dossier taxs
	*/
	public static int getDossierTaxsCount() {
		return getService().getDossierTaxsCount();
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
	* Updates the dossier tax in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was updated
	*/
	public static org.opencps.synctracking.model.DossierTax updateDossierTax(
		org.opencps.synctracking.model.DossierTax dossierTax) {
		return getService().updateDossierTax(dossierTax);
	}

	public static DossierTaxLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierTaxLocalService, DossierTaxLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierTaxLocalService.class);

		ServiceTracker<DossierTaxLocalService, DossierTaxLocalService> serviceTracker =
			new ServiceTracker<DossierTaxLocalService, DossierTaxLocalService>(bundle.getBundleContext(),
				DossierTaxLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}