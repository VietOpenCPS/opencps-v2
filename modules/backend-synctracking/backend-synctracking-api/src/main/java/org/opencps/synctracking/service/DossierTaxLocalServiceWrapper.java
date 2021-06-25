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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DossierTaxLocalService}.
 *
 * @author duongnt
 * @see DossierTaxLocalService
 * @generated
 */
@ProviderType
public class DossierTaxLocalServiceWrapper implements DossierTaxLocalService,
	ServiceWrapper<DossierTaxLocalService> {
	public DossierTaxLocalServiceWrapper(
		DossierTaxLocalService dossierTaxLocalService) {
		_dossierTaxLocalService = dossierTaxLocalService;
	}

	/**
	* Adds the dossier tax to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was added
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax addDossierTax(
		org.opencps.synctracking.model.DossierTax dossierTax) {
		return _dossierTaxLocalService.addDossierTax(dossierTax);
	}

	/**
	* Creates a new dossier tax with the primary key. Does not add the dossier tax to the database.
	*
	* @param taxId the primary key for the new dossier tax
	* @return the new dossier tax
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax createDossierTax(
		long taxId) {
		return _dossierTaxLocalService.createDossierTax(taxId);
	}

	@Override
	public org.opencps.synctracking.model.DossierTax createDossierTaxManual(
		org.opencps.synctracking.model.DossierTaxInput dossierTaxInput) {
		return _dossierTaxLocalService.createDossierTaxManual(dossierTaxInput);
	}

	/**
	* Deletes the dossier tax from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was removed
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax deleteDossierTax(
		org.opencps.synctracking.model.DossierTax dossierTax) {
		return _dossierTaxLocalService.deleteDossierTax(dossierTax);
	}

	/**
	* Deletes the dossier tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax that was removed
	* @throws PortalException if a dossier tax with the primary key could not be found
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax deleteDossierTax(
		long taxId) throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTaxLocalService.deleteDossierTax(taxId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTaxLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierTaxLocalService.dynamicQuery();
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
		return _dossierTaxLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _dossierTaxLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _dossierTaxLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dossierTaxLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierTaxLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.synctracking.model.DossierTax fetchDossierTax(long taxId) {
		return _dossierTaxLocalService.fetchDossierTax(taxId);
	}

	@Override
	public org.opencps.synctracking.model.DossierTax fetchDossierTaxByDMS(
		String dossierNo, String maSoThue, String soQuyetDinh) {
		return _dossierTaxLocalService.fetchDossierTaxByDMS(dossierNo,
			maSoThue, soQuyetDinh);
	}

	/**
	* Returns the dossier tax matching the UUID and group.
	*
	* @param uuid the dossier tax's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier tax, or <code>null</code> if a matching dossier tax could not be found
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax fetchDossierTaxByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierTaxLocalService.fetchDossierTaxByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierTaxLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getByDossierIdAndStatusCTT(
		long dossierId, int statuses) {
		return _dossierTaxLocalService.getByDossierIdAndStatusCTT(dossierId,
			statuses);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getByDossierIdAndStatusTBT(
		long dossierId, int statuses) {
		return _dossierTaxLocalService.getByDossierIdAndStatusTBT(dossierId,
			statuses);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getByStatusCTT(
		int statusTBT, int statusesCTT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synctracking.model.DossierTax> orderByComparator) {
		return _dossierTaxLocalService.getByStatusCTT(statusTBT, statusesCTT,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getByStatusTBT(
		int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synctracking.model.DossierTax> orderByComparator) {
		return _dossierTaxLocalService.getByStatusTBT(statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns the dossier tax with the primary key.
	*
	* @param taxId the primary key of the dossier tax
	* @return the dossier tax
	* @throws PortalException if a dossier tax with the primary key could not be found
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax getDossierTax(long taxId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTaxLocalService.getDossierTax(taxId);
	}

	/**
	* Returns the dossier tax matching the UUID and group.
	*
	* @param uuid the dossier tax's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier tax
	* @throws PortalException if a matching dossier tax could not be found
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax getDossierTaxByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTaxLocalService.getDossierTaxByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getDossierTaxs(
		int start, int end) {
		return _dossierTaxLocalService.getDossierTaxs(start, end);
	}

	/**
	* Returns all the dossier taxs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier taxs
	* @param companyId the primary key of the company
	* @return the matching dossier taxs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getDossierTaxsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierTaxLocalService.getDossierTaxsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.synctracking.model.DossierTax> getDossierTaxsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synctracking.model.DossierTax> orderByComparator) {
		return _dossierTaxLocalService.getDossierTaxsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier taxs.
	*
	* @return the number of dossier taxs
	*/
	@Override
	public int getDossierTaxsCount() {
		return _dossierTaxLocalService.getDossierTaxsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierTaxLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierTaxLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierTaxLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTaxLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dossier tax in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierTax the dossier tax
	* @return the dossier tax that was updated
	*/
	@Override
	public org.opencps.synctracking.model.DossierTax updateDossierTax(
		org.opencps.synctracking.model.DossierTax dossierTax) {
		return _dossierTaxLocalService.updateDossierTax(dossierTax);
	}

	@Override
	public DossierTaxLocalService getWrappedService() {
		return _dossierTaxLocalService;
	}

	@Override
	public void setWrappedService(DossierTaxLocalService dossierTaxLocalService) {
		_dossierTaxLocalService = dossierTaxLocalService;
	}

	private DossierTaxLocalService _dossierTaxLocalService;
}