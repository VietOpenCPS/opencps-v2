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
 * Provides a wrapper for {@link DossierStatusMappingLocalService}.
 *
 * @author huymq
 * @see DossierStatusMappingLocalService
 * @generated
 */
@ProviderType
public class DossierStatusMappingLocalServiceWrapper
	implements DossierStatusMappingLocalService,
		ServiceWrapper<DossierStatusMappingLocalService> {
	public DossierStatusMappingLocalServiceWrapper(
		DossierStatusMappingLocalService dossierStatusMappingLocalService) {
		_dossierStatusMappingLocalService = dossierStatusMappingLocalService;
	}

	/**
	* Adds the dossier status mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatusMapping the dossier status mapping
	* @return the dossier status mapping that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping addDossierStatusMapping(
		org.opencps.dossiermgt.model.DossierStatusMapping dossierStatusMapping) {
		return _dossierStatusMappingLocalService.addDossierStatusMapping(dossierStatusMapping);
	}

	/**
	* Creates a new dossier status mapping with the primary key. Does not add the dossier status mapping to the database.
	*
	* @param dossierStatusMappingId the primary key for the new dossier status mapping
	* @return the new dossier status mapping
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping createDossierStatusMapping(
		long dossierStatusMappingId) {
		return _dossierStatusMappingLocalService.createDossierStatusMapping(dossierStatusMappingId);
	}

	/**
	* Deletes the dossier status mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatusMapping the dossier status mapping
	* @return the dossier status mapping that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping deleteDossierStatusMapping(
		org.opencps.dossiermgt.model.DossierStatusMapping dossierStatusMapping) {
		return _dossierStatusMappingLocalService.deleteDossierStatusMapping(dossierStatusMapping);
	}

	/**
	* Deletes the dossier status mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierStatusMappingId the primary key of the dossier status mapping
	* @return the dossier status mapping that was removed
	* @throws PortalException if a dossier status mapping with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping deleteDossierStatusMapping(
		long dossierStatusMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatusMappingLocalService.deleteDossierStatusMapping(dossierStatusMappingId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatusMappingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierStatusMappingLocalService.dynamicQuery();
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
		return _dossierStatusMappingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierStatusMappingLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierStatusMappingLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _dossierStatusMappingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierStatusMappingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping fetchByF_GID_SUBSC(
		long groupId, String subStatusCode) {
		return _dossierStatusMappingLocalService.fetchByF_GID_SUBSC(groupId,
			subStatusCode);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping fetchDossierStatusMapping(
		long dossierStatusMappingId) {
		return _dossierStatusMappingLocalService.fetchDossierStatusMapping(dossierStatusMappingId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierStatusMapping> findByF_GID_SC(
		long groupId, String statusCode) {
		return _dossierStatusMappingLocalService.findByF_GID_SC(groupId,
			statusCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierStatusMappingLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dossier status mapping with the primary key.
	*
	* @param dossierStatusMappingId the primary key of the dossier status mapping
	* @return the dossier status mapping
	* @throws PortalException if a dossier status mapping with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping getDossierStatusMapping(
		long dossierStatusMappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatusMappingLocalService.getDossierStatusMapping(dossierStatusMappingId);
	}

	/**
	* Returns a range of all the dossier status mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierStatusMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier status mappings
	* @param end the upper bound of the range of dossier status mappings (not inclusive)
	* @return the range of dossier status mappings
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierStatusMapping> getDossierStatusMappings(
		int start, int end) {
		return _dossierStatusMappingLocalService.getDossierStatusMappings(start,
			end);
	}

	/**
	* Returns the number of dossier status mappings.
	*
	* @return the number of dossier status mappings
	*/
	@Override
	public int getDossierStatusMappingsCount() {
		return _dossierStatusMappingLocalService.getDossierStatusMappingsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierStatusMappingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierStatusMappingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierStatusMappingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dossier status mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierStatusMapping the dossier status mapping
	* @return the dossier status mapping that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierStatusMapping updateDossierStatusMapping(
		org.opencps.dossiermgt.model.DossierStatusMapping dossierStatusMapping) {
		return _dossierStatusMappingLocalService.updateDossierStatusMapping(dossierStatusMapping);
	}

	@Override
	public DossierStatusMappingLocalService getWrappedService() {
		return _dossierStatusMappingLocalService;
	}

	@Override
	public void setWrappedService(
		DossierStatusMappingLocalService dossierStatusMappingLocalService) {
		_dossierStatusMappingLocalService = dossierStatusMappingLocalService;
	}

	private DossierStatusMappingLocalService _dossierStatusMappingLocalService;
}