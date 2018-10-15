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
 * Provides a wrapper for {@link ServiceFileTemplateLocalService}.
 *
 * @author huymq
 * @see ServiceFileTemplateLocalService
 * @generated
 */
@ProviderType
public class ServiceFileTemplateLocalServiceWrapper
	implements ServiceFileTemplateLocalService,
		ServiceWrapper<ServiceFileTemplateLocalService> {
	public ServiceFileTemplateLocalServiceWrapper(
		ServiceFileTemplateLocalService serviceFileTemplateLocalService) {
		_serviceFileTemplateLocalService = serviceFileTemplateLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate addServiceFileTemplate(
		long userId, long groupId, long folderId, long serviceInfoId,
		String fileTemplateNo, String templateName, String sourceFileName,
		java.io.InputStream inputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _serviceFileTemplateLocalService.addServiceFileTemplate(userId,
			groupId, folderId, serviceInfoId, fileTemplateNo, templateName,
			sourceFileName, inputStream, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate addServiceFileTemplate(
		long serviceInfoId, String fileTemplateNo, String templateName,
		long fileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _serviceFileTemplateLocalService.addServiceFileTemplate(serviceInfoId,
			fileTemplateNo, templateName, fileEntryId, serviceContext);
	}

	/**
	* Adds the service file template to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplate the service file template
	* @return the service file template that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate addServiceFileTemplate(
		org.opencps.dossiermgt.model.ServiceFileTemplate serviceFileTemplate) {
		return _serviceFileTemplateLocalService.addServiceFileTemplate(serviceFileTemplate);
	}

	@Override
	public int countByServiceInfoId(long serviceInfoId) {
		return _serviceFileTemplateLocalService.countByServiceInfoId(serviceInfoId);
	}

	/**
	* Creates a new service file template with the primary key. Does not add the service file template to the database.
	*
	* @param serviceFileTemplatePK the primary key for the new service file template
	* @return the new service file template
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate createServiceFileTemplate(
		org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK serviceFileTemplatePK) {
		return _serviceFileTemplateLocalService.createServiceFileTemplate(serviceFileTemplatePK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceFileTemplateLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the service file template from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplate the service file template
	* @return the service file template that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate deleteServiceFileTemplate(
		org.opencps.dossiermgt.model.ServiceFileTemplate serviceFileTemplate) {
		return _serviceFileTemplateLocalService.deleteServiceFileTemplate(serviceFileTemplate);
	}

	/**
	* Deletes the service file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template that was removed
	* @throws PortalException if a service file template with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate deleteServiceFileTemplate(
		org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK serviceFileTemplatePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceFileTemplateLocalService.deleteServiceFileTemplate(serviceFileTemplatePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceFileTemplateLocalService.dynamicQuery();
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
		return _serviceFileTemplateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceFileTemplateLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceFileTemplateLocalService.dynamicQuery(dynamicQuery,
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
		return _serviceFileTemplateLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serviceFileTemplateLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate fetchServiceFileTemplate(
		org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK serviceFileTemplatePK) {
		return _serviceFileTemplateLocalService.fetchServiceFileTemplate(serviceFileTemplatePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceFileTemplateLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceFileTemplate> getByServiceInfoId(
		long serviceInfoId) {
		return _serviceFileTemplateLocalService.getByServiceInfoId(serviceInfoId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serviceFileTemplateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceFileTemplateLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceFileTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the service file template with the primary key.
	*
	* @param serviceFileTemplatePK the primary key of the service file template
	* @return the service file template
	* @throws PortalException if a service file template with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate getServiceFileTemplate(
		org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK serviceFileTemplatePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceFileTemplateLocalService.getServiceFileTemplate(serviceFileTemplatePK);
	}

	/**
	* Returns a range of all the service file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceFileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service file templates
	* @param end the upper bound of the range of service file templates (not inclusive)
	* @return the range of service file templates
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceFileTemplate> getServiceFileTemplates(
		int start, int end) {
		return _serviceFileTemplateLocalService.getServiceFileTemplates(start,
			end);
	}

	/**
	* Returns the number of service file templates.
	*
	* @return the number of service file templates
	*/
	@Override
	public int getServiceFileTemplatesCount() {
		return _serviceFileTemplateLocalService.getServiceFileTemplatesCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate removeServiceFileTemplate(
		long serviceInfoId, String fileTemplateNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceFileTemplateLocalService.removeServiceFileTemplate(serviceInfoId,
			fileTemplateNo);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate updateServiceFileTemplate(
		long userId, long groupId, long folderId, long serviceInfoId,
		String fileTemplateNo, String templateName, String sourceFileName,
		java.io.InputStream inputStream,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _serviceFileTemplateLocalService.updateServiceFileTemplate(userId,
			groupId, folderId, serviceInfoId, fileTemplateNo, templateName,
			sourceFileName, inputStream, serviceContext);
	}

	/**
	* Updates the service file template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceFileTemplate the service file template
	* @return the service file template that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate updateServiceFileTemplate(
		org.opencps.dossiermgt.model.ServiceFileTemplate serviceFileTemplate) {
		return _serviceFileTemplateLocalService.updateServiceFileTemplate(serviceFileTemplate);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceFileTemplate updateServiceFileTemplateDB(
		long serviceInfoId, String fileTemplateNo, String fileTemplateName,
		String fileName, long fileEntryId) {
		return _serviceFileTemplateLocalService.updateServiceFileTemplateDB(serviceInfoId,
			fileTemplateNo, fileTemplateName, fileName, fileEntryId);
	}

	@Override
	public ServiceFileTemplateLocalService getWrappedService() {
		return _serviceFileTemplateLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceFileTemplateLocalService serviceFileTemplateLocalService) {
		_serviceFileTemplateLocalService = serviceFileTemplateLocalService;
	}

	private ServiceFileTemplateLocalService _serviceFileTemplateLocalService;
}