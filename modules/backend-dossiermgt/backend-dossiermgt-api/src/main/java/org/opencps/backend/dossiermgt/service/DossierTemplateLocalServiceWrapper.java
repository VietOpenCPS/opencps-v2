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
 * Provides a wrapper for {@link DossierTemplateLocalService}.
 *
 * @author huymq
 * @see DossierTemplateLocalService
 * @generated
 */
@ProviderType
public class DossierTemplateLocalServiceWrapper
	implements DossierTemplateLocalService,
		ServiceWrapper<DossierTemplateLocalService> {
	public DossierTemplateLocalServiceWrapper(
		DossierTemplateLocalService dossierTemplateLocalService) {
		_dossierTemplateLocalService = dossierTemplateLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierTemplateLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierTemplateLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierTemplateLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierTemplateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTemplateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of dossier templates.
	*
	* @return the number of dossier templates
	*/
	@Override
	public int getDossierTemplatesCount() {
		return _dossierTemplateLocalService.getDossierTemplatesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _dossierTemplateLocalService.getOSGiServiceIdentifier();
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
		return _dossierTemplateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierTemplateLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierTemplateLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the dossier templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.DossierTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier templates
	* @param end the upper bound of the range of dossier templates (not inclusive)
	* @return the range of dossier templates
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.DossierTemplate> getDossierTemplates(
		int start, int end) {
		return _dossierTemplateLocalService.getDossierTemplates(start, end);
	}

	/**
	* Returns all the dossier templates matching the UUID and company.
	*
	* @param uuid the UUID of the dossier templates
	* @param companyId the primary key of the company
	* @return the matching dossier templates, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.DossierTemplate> getDossierTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _dossierTemplateLocalService.getDossierTemplatesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier templates matching the UUID and company.
	*
	* @param uuid the UUID of the dossier templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier templates
	* @param end the upper bound of the range of dossier templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier templates, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.DossierTemplate> getDossierTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.DossierTemplate> orderByComparator) {
		return _dossierTemplateLocalService.getDossierTemplatesByUuidAndCompanyId(uuid,
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
		return _dossierTemplateLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierTemplateLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the dossier template to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplate the dossier template
	* @return the dossier template that was added
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate addDossierTemplate(
		org.opencps.backend.dossiermgt.model.DossierTemplate dossierTemplate) {
		return _dossierTemplateLocalService.addDossierTemplate(dossierTemplate);
	}

	/**
	* Creates a new dossier template with the primary key. Does not add the dossier template to the database.
	*
	* @param dossierTemplateId the primary key for the new dossier template
	* @return the new dossier template
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate createDossierTemplate(
		long dossierTemplateId) {
		return _dossierTemplateLocalService.createDossierTemplate(dossierTemplateId);
	}

	/**
	* Deletes the dossier template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplateId the primary key of the dossier template
	* @return the dossier template that was removed
	* @throws PortalException if a dossier template with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate deleteDossierTemplate(
		long dossierTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTemplateLocalService.deleteDossierTemplate(dossierTemplateId);
	}

	/**
	* Deletes the dossier template from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplate the dossier template
	* @return the dossier template that was removed
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate deleteDossierTemplate(
		org.opencps.backend.dossiermgt.model.DossierTemplate dossierTemplate) {
		return _dossierTemplateLocalService.deleteDossierTemplate(dossierTemplate);
	}

	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate fetchDossierTemplate(
		long dossierTemplateId) {
		return _dossierTemplateLocalService.fetchDossierTemplate(dossierTemplateId);
	}

	/**
	* Returns the dossier template matching the UUID and group.
	*
	* @param uuid the dossier template's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier template, or <code>null</code> if a matching dossier template could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate fetchDossierTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _dossierTemplateLocalService.fetchDossierTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the dossier template with the primary key.
	*
	* @param dossierTemplateId the primary key of the dossier template
	* @return the dossier template
	* @throws PortalException if a dossier template with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate getDossierTemplate(
		long dossierTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTemplateLocalService.getDossierTemplate(dossierTemplateId);
	}

	/**
	* Returns the dossier template matching the UUID and group.
	*
	* @param uuid the dossier template's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier template
	* @throws PortalException if a matching dossier template could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate getDossierTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierTemplateLocalService.getDossierTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the dossier template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierTemplate the dossier template
	* @return the dossier template that was updated
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.DossierTemplate updateDossierTemplate(
		org.opencps.backend.dossiermgt.model.DossierTemplate dossierTemplate) {
		return _dossierTemplateLocalService.updateDossierTemplate(dossierTemplate);
	}

	@Override
	public DossierTemplateLocalService getWrappedService() {
		return _dossierTemplateLocalService;
	}

	@Override
	public void setWrappedService(
		DossierTemplateLocalService dossierTemplateLocalService) {
		_dossierTemplateLocalService = dossierTemplateLocalService;
	}

	private DossierTemplateLocalService _dossierTemplateLocalService;
}