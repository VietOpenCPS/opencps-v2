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

package org.opencps.backend.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DossierFileLocalService}.
 *
 * @author huymq
 * @see DossierFileLocalService
 * @generated
 */
@ProviderType
public class DossierFileLocalServiceWrapper implements DossierFileLocalService,
	ServiceWrapper<DossierFileLocalService> {
	public DossierFileLocalServiceWrapper(
		DossierFileLocalService dossierFileLocalService) {
		_dossierFileLocalService = dossierFileLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierFileLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierFileLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierFileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of dossier files.
	*
	* @return the number of dossier files
	*/
	@Override
	public int getDossierFilesCount() {
		return _dossierFileLocalService.getDossierFilesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _dossierFileLocalService.getOSGiServiceIdentifier();
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
		return _dossierFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierFileLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the dossier files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.DossierFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @return the range of dossier files
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.DossierFile> getDossierFiles(
		int start, int end) {
		return _dossierFileLocalService.getDossierFiles(start, end);
	}

	/**
	* Returns all the dossier files matching the UUID and company.
	*
	* @param uuid the UUID of the dossier files
	* @param companyId the primary key of the company
	* @return the matching dossier files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.DossierFile> getDossierFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _dossierFileLocalService.getDossierFilesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier files matching the UUID and company.
	*
	* @param uuid the UUID of the dossier files
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier files
	* @param end the upper bound of the range of dossier files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.DossierFile> getDossierFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.DossierFile> orderByComparator) {
		return _dossierFileLocalService.getDossierFilesByUuidAndCompanyId(uuid,
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
		return _dossierFileLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierFileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the dossier file to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFile the dossier file
	* @return the dossier file that was added
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile addDossierFile(
		org.opencps.backend.dossiermgt.model.DossierFile dossierFile) {
		return _dossierFileLocalService.addDossierFile(dossierFile);
	}

	/**
	* Creates a new dossier file with the primary key. Does not add the dossier file to the database.
	*
	* @param dossierFileId the primary key for the new dossier file
	* @return the new dossier file
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile createDossierFile(
		long dossierFileId) {
		return _dossierFileLocalService.createDossierFile(dossierFileId);
	}

	/**
	* Deletes the dossier file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file that was removed
	* @throws PortalException if a dossier file with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile deleteDossierFile(
		long dossierFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.deleteDossierFile(dossierFileId);
	}

	/**
	* Deletes the dossier file from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierFile the dossier file
	* @return the dossier file that was removed
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile deleteDossierFile(
		org.opencps.backend.dossiermgt.model.DossierFile dossierFile) {
		return _dossierFileLocalService.deleteDossierFile(dossierFile);
	}

	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile fetchDossierFile(
		long dossierFileId) {
		return _dossierFileLocalService.fetchDossierFile(dossierFileId);
	}

	/**
	* Returns the dossier file matching the UUID and group.
	*
	* @param uuid the dossier file's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier file, or <code>null</code> if a matching dossier file could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile fetchDossierFileByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _dossierFileLocalService.fetchDossierFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the dossier file with the primary key.
	*
	* @param dossierFileId the primary key of the dossier file
	* @return the dossier file
	* @throws PortalException if a dossier file with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile getDossierFile(
		long dossierFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getDossierFile(dossierFileId);
	}

	/**
	* Returns the dossier file matching the UUID and group.
	*
	* @param uuid the dossier file's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier file
	* @throws PortalException if a matching dossier file could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile getDossierFileByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierFileLocalService.getDossierFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the dossier file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierFile the dossier file
	* @return the dossier file that was updated
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierFile updateDossierFile(
		org.opencps.backend.dossiermgt.model.DossierFile dossierFile) {
		return _dossierFileLocalService.updateDossierFile(dossierFile);
	}

	@Override
	public DossierFileLocalService getWrappedService() {
		return _dossierFileLocalService;
	}

	@Override
	public void setWrappedService(
		DossierFileLocalService dossierFileLocalService) {
		_dossierFileLocalService = dossierFileLocalService;
	}

	private DossierFileLocalService _dossierFileLocalService;
}