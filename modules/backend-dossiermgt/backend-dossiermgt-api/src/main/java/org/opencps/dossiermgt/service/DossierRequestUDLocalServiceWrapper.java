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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DossierRequestUDLocalService}.
 *
 * @author huymq
 * @see DossierRequestUDLocalService
 * @generated
 */
@ProviderType
public class DossierRequestUDLocalServiceWrapper
	implements DossierRequestUDLocalService,
		ServiceWrapper<DossierRequestUDLocalService> {
	public DossierRequestUDLocalServiceWrapper(
		DossierRequestUDLocalService dossierRequestUDLocalService) {
		_dossierRequestUDLocalService = dossierRequestUDLocalService;
	}

	/**
	* Adds the dossier request ud to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierRequestUD the dossier request ud
	* @return the dossier request ud that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD addDossierRequestUD(
		org.opencps.dossiermgt.model.DossierRequestUD dossierRequestUD) {
		return _dossierRequestUDLocalService.addDossierRequestUD(dossierRequestUD);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _dossierRequestUDLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD adminProcessDelete(
		Long id) {
		return _dossierRequestUDLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new dossier request ud with the primary key. Does not add the dossier request ud to the database.
	*
	* @param dossierRequestId the primary key for the new dossier request ud
	* @return the new dossier request ud
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD createDossierRequestUD(
		long dossierRequestId) {
		return _dossierRequestUDLocalService.createDossierRequestUD(dossierRequestId);
	}

	/**
	* Deletes the dossier request ud from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierRequestUD the dossier request ud
	* @return the dossier request ud that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD deleteDossierRequestUD(
		org.opencps.dossiermgt.model.DossierRequestUD dossierRequestUD) {
		return _dossierRequestUDLocalService.deleteDossierRequestUD(dossierRequestUD);
	}

	/**
	* Deletes the dossier request ud with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud that was removed
	* @throws PortalException if a dossier request ud with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD deleteDossierRequestUD(
		long dossierRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierRequestUDLocalService.deleteDossierRequestUD(dossierRequestId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierRequestUDLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierRequestUDLocalService.dynamicQuery();
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
		return _dossierRequestUDLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierRequestUDLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierRequestUDLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dossierRequestUDLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierRequestUDLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD fetchDossierRequestUD(
		long dossierRequestId) {
		return _dossierRequestUDLocalService.fetchDossierRequestUD(dossierRequestId);
	}

	/**
	* Returns the dossier request ud matching the UUID and group.
	*
	* @param uuid the dossier request ud's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier request ud, or <code>null</code> if a matching dossier request ud could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD fetchDossierRequestUDByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierRequestUDLocalService.fetchDossierRequestUDByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierRequestUDLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierRequestUD> getDossierRequest(
		long dossierId, int isNew)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierRequestUDLocalService.getDossierRequest(dossierId, isNew);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD getDossierRequestByDossierId(
		long dossierId) {
		return _dossierRequestUDLocalService.getDossierRequestByDossierId(dossierId);
	}

	/**
	* Returns the dossier request ud with the primary key.
	*
	* @param dossierRequestId the primary key of the dossier request ud
	* @return the dossier request ud
	* @throws PortalException if a dossier request ud with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD getDossierRequestUD(
		long dossierRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierRequestUDLocalService.getDossierRequestUD(dossierRequestId);
	}

	/**
	* Returns the dossier request ud matching the UUID and group.
	*
	* @param uuid the dossier request ud's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier request ud
	* @throws PortalException if a matching dossier request ud could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD getDossierRequestUDByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierRequestUDLocalService.getDossierRequestUDByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dossier request uds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierRequestUDModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @return the range of dossier request uds
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierRequestUD> getDossierRequestUDs(
		int start, int end) {
		return _dossierRequestUDLocalService.getDossierRequestUDs(start, end);
	}

	/**
	* Returns all the dossier request uds matching the UUID and company.
	*
	* @param uuid the UUID of the dossier request uds
	* @param companyId the primary key of the company
	* @return the matching dossier request uds, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierRequestUD> getDossierRequestUDsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierRequestUDLocalService.getDossierRequestUDsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier request uds matching the UUID and company.
	*
	* @param uuid the UUID of the dossier request uds
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier request uds
	* @param end the upper bound of the range of dossier request uds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier request uds, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierRequestUD> getDossierRequestUDsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierRequestUD> orderByComparator) {
		return _dossierRequestUDLocalService.getDossierRequestUDsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier request uds.
	*
	* @return the number of dossier request uds
	*/
	@Override
	public int getDossierRequestUDsCount() {
		return _dossierRequestUDLocalService.getDossierRequestUDsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierRequestUDLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierRequestUDLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierRequestUDLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierRequestUDLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD updateDossierRequest(
		long dossierRequestId, long dossierId, String referenceUid,
		String requestType, String comment, int isNew, int status,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierRequestUDLocalService.updateDossierRequest(dossierRequestId,
			dossierId, referenceUid, requestType, comment, isNew, status,
			context);
	}

	/**
	* Updates the dossier request ud in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierRequestUD the dossier request ud
	* @return the dossier request ud that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierRequestUD updateDossierRequestUD(
		org.opencps.dossiermgt.model.DossierRequestUD dossierRequestUD) {
		return _dossierRequestUDLocalService.updateDossierRequestUD(dossierRequestUD);
	}

	@Override
	public DossierRequestUDLocalService getWrappedService() {
		return _dossierRequestUDLocalService;
	}

	@Override
	public void setWrappedService(
		DossierRequestUDLocalService dossierRequestUDLocalService) {
		_dossierRequestUDLocalService = dossierRequestUDLocalService;
	}

	private DossierRequestUDLocalService _dossierRequestUDLocalService;
}