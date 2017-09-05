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
 * Provides a wrapper for {@link FileTemplateLocalService}.
 *
 * @author huymq
 * @see FileTemplateLocalService
 * @generated
 */
@ProviderType
public class FileTemplateLocalServiceWrapper implements FileTemplateLocalService,
	ServiceWrapper<FileTemplateLocalService> {
	public FileTemplateLocalServiceWrapper(
		FileTemplateLocalService fileTemplateLocalService) {
		_fileTemplateLocalService = fileTemplateLocalService;
	}

	@Override
	public boolean hasServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId) {
		return _fileTemplateLocalService.hasServiceInfoFileTemplate(serviceInfoId,
			fileTemplateId);
	}

	@Override
	public boolean hasServiceInfoFileTemplates(long serviceInfoId) {
		return _fileTemplateLocalService.hasServiceInfoFileTemplates(serviceInfoId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fileTemplateLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fileTemplateLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _fileTemplateLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _fileTemplateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileTemplateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of file templates.
	*
	* @return the number of file templates
	*/
	@Override
	public int getFileTemplatesCount() {
		return _fileTemplateLocalService.getFileTemplatesCount();
	}

	@Override
	public int getServiceInfoFileTemplatesCount(long serviceInfoId) {
		return _fileTemplateLocalService.getServiceInfoFileTemplatesCount(serviceInfoId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _fileTemplateLocalService.getOSGiServiceIdentifier();
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
		return _fileTemplateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fileTemplateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fileTemplateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the file templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @return the range of file templates
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		int start, int end) {
		return _fileTemplateLocalService.getFileTemplates(start, end);
	}

	/**
	* Returns all the file templates matching the UUID and company.
	*
	* @param uuid the UUID of the file templates
	* @param companyId the primary key of the company
	* @return the matching file templates, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _fileTemplateLocalService.getFileTemplatesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of file templates matching the UUID and company.
	*
	* @param uuid the UUID of the file templates
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of file templates
	* @param end the upper bound of the range of file templates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching file templates, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.FileTemplate> orderByComparator) {
		return _fileTemplateLocalService.getFileTemplatesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getServiceInfoFileTemplates(
		long serviceInfoId) {
		return _fileTemplateLocalService.getServiceInfoFileTemplates(serviceInfoId);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getServiceInfoFileTemplates(
		long serviceInfoId, int start, int end) {
		return _fileTemplateLocalService.getServiceInfoFileTemplates(serviceInfoId,
			start, end);
	}

	@Override
	public java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getServiceInfoFileTemplates(
		long serviceInfoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.FileTemplate> orderByComparator) {
		return _fileTemplateLocalService.getServiceInfoFileTemplates(serviceInfoId,
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
		return _fileTemplateLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fileTemplateLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the serviceInfoIds of the service infos associated with the file template.
	*
	* @param fileTemplateId the fileTemplateId of the file template
	* @return long[] the serviceInfoIds of service infos associated with the file template
	*/
	@Override
	public long[] getServiceInfoPrimaryKeys(long fileTemplateId) {
		return _fileTemplateLocalService.getServiceInfoPrimaryKeys(fileTemplateId);
	}

	/**
	* Adds the file template to the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was added
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate addFileTemplate(
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		return _fileTemplateLocalService.addFileTemplate(fileTemplate);
	}

	/**
	* Creates a new file template with the primary key. Does not add the file template to the database.
	*
	* @param fileTemplateId the primary key for the new file template
	* @return the new file template
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate createFileTemplate(
		long fileTemplateId) {
		return _fileTemplateLocalService.createFileTemplate(fileTemplateId);
	}

	/**
	* Deletes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template that was removed
	* @throws PortalException if a file template with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate deleteFileTemplate(
		long fileTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileTemplateLocalService.deleteFileTemplate(fileTemplateId);
	}

	/**
	* Deletes the file template from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was removed
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate deleteFileTemplate(
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		return _fileTemplateLocalService.deleteFileTemplate(fileTemplate);
	}

	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate fetchFileTemplate(
		long fileTemplateId) {
		return _fileTemplateLocalService.fetchFileTemplate(fileTemplateId);
	}

	/**
	* Returns the file template matching the UUID and group.
	*
	* @param uuid the file template's UUID
	* @param groupId the primary key of the group
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate fetchFileTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _fileTemplateLocalService.fetchFileTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the file template with the primary key.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template
	* @throws PortalException if a file template with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate getFileTemplate(
		long fileTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileTemplateLocalService.getFileTemplate(fileTemplateId);
	}

	/**
	* Returns the file template matching the UUID and group.
	*
	* @param uuid the file template's UUID
	* @param groupId the primary key of the group
	* @return the matching file template
	* @throws PortalException if a matching file template could not be found
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate getFileTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileTemplateLocalService.getFileTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the file template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was updated
	*/
	@Override
	public org.opencps.backend.dossiermgt.model.FileTemplate updateFileTemplate(
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		return _fileTemplateLocalService.updateFileTemplate(fileTemplate);
	}

	@Override
	public void addServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId) {
		_fileTemplateLocalService.addServiceInfoFileTemplate(serviceInfoId,
			fileTemplateId);
	}

	@Override
	public void addServiceInfoFileTemplate(long serviceInfoId,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		_fileTemplateLocalService.addServiceInfoFileTemplate(serviceInfoId,
			fileTemplate);
	}

	@Override
	public void addServiceInfoFileTemplates(long serviceInfoId,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		_fileTemplateLocalService.addServiceInfoFileTemplates(serviceInfoId,
			fileTemplates);
	}

	@Override
	public void addServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds) {
		_fileTemplateLocalService.addServiceInfoFileTemplates(serviceInfoId,
			fileTemplateIds);
	}

	@Override
	public void clearServiceInfoFileTemplates(long serviceInfoId) {
		_fileTemplateLocalService.clearServiceInfoFileTemplates(serviceInfoId);
	}

	@Override
	public void deleteServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId) {
		_fileTemplateLocalService.deleteServiceInfoFileTemplate(serviceInfoId,
			fileTemplateId);
	}

	@Override
	public void deleteServiceInfoFileTemplate(long serviceInfoId,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		_fileTemplateLocalService.deleteServiceInfoFileTemplate(serviceInfoId,
			fileTemplate);
	}

	@Override
	public void deleteServiceInfoFileTemplates(long serviceInfoId,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		_fileTemplateLocalService.deleteServiceInfoFileTemplates(serviceInfoId,
			fileTemplates);
	}

	@Override
	public void deleteServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds) {
		_fileTemplateLocalService.deleteServiceInfoFileTemplates(serviceInfoId,
			fileTemplateIds);
	}

	@Override
	public void setServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds) {
		_fileTemplateLocalService.setServiceInfoFileTemplates(serviceInfoId,
			fileTemplateIds);
	}

	@Override
	public FileTemplateLocalService getWrappedService() {
		return _fileTemplateLocalService;
	}

	@Override
	public void setWrappedService(
		FileTemplateLocalService fileTemplateLocalService) {
		_fileTemplateLocalService = fileTemplateLocalService;
	}

	private FileTemplateLocalService _fileTemplateLocalService;
}