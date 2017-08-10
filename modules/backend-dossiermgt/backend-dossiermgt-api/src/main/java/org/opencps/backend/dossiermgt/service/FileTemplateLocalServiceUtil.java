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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for FileTemplate. This utility wraps
 * {@link org.opencps.backend.dossiermgt.service.impl.FileTemplateLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see FileTemplateLocalService
 * @see org.opencps.backend.dossiermgt.service.base.FileTemplateLocalServiceBaseImpl
 * @see org.opencps.backend.dossiermgt.service.impl.FileTemplateLocalServiceImpl
 * @generated
 */
@ProviderType
public class FileTemplateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.dossiermgt.service.impl.FileTemplateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId) {
		return getService()
				   .hasServiceInfoFileTemplate(serviceInfoId, fileTemplateId);
	}

	public static boolean hasServiceInfoFileTemplates(long serviceInfoId) {
		return getService().hasServiceInfoFileTemplates(serviceInfoId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of file templates.
	*
	* @return the number of file templates
	*/
	public static int getFileTemplatesCount() {
		return getService().getFileTemplatesCount();
	}

	public static int getServiceInfoFileTemplatesCount(long serviceInfoId) {
		return getService().getServiceInfoFileTemplatesCount(serviceInfoId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.dossiermgt.model.impl.FileTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplates(
		int start, int end) {
		return getService().getFileTemplates(start, end);
	}

	/**
	* Returns all the file templates matching the UUID and company.
	*
	* @param uuid the UUID of the file templates
	* @param companyId the primary key of the company
	* @return the matching file templates, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getFileTemplatesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getFileTemplatesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.FileTemplate> orderByComparator) {
		return getService()
				   .getFileTemplatesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getServiceInfoFileTemplates(
		long serviceInfoId) {
		return getService().getServiceInfoFileTemplates(serviceInfoId);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getServiceInfoFileTemplates(
		long serviceInfoId, int start, int end) {
		return getService()
				   .getServiceInfoFileTemplates(serviceInfoId, start, end);
	}

	public static java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> getServiceInfoFileTemplates(
		long serviceInfoId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.dossiermgt.model.FileTemplate> orderByComparator) {
		return getService()
				   .getServiceInfoFileTemplates(serviceInfoId, start, end,
			orderByComparator);
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

	/**
	* Returns the serviceInfoIds of the service infos associated with the file template.
	*
	* @param fileTemplateId the fileTemplateId of the file template
	* @return long[] the serviceInfoIds of service infos associated with the file template
	*/
	public static long[] getServiceInfoPrimaryKeys(long fileTemplateId) {
		return getService().getServiceInfoPrimaryKeys(fileTemplateId);
	}

	/**
	* Adds the file template to the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was added
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate addFileTemplate(
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		return getService().addFileTemplate(fileTemplate);
	}

	/**
	* Creates a new file template with the primary key. Does not add the file template to the database.
	*
	* @param fileTemplateId the primary key for the new file template
	* @return the new file template
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate createFileTemplate(
		long fileTemplateId) {
		return getService().createFileTemplate(fileTemplateId);
	}

	/**
	* Deletes the file template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template that was removed
	* @throws PortalException if a file template with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate deleteFileTemplate(
		long fileTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFileTemplate(fileTemplateId);
	}

	/**
	* Deletes the file template from the database. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was removed
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate deleteFileTemplate(
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		return getService().deleteFileTemplate(fileTemplate);
	}

	public static org.opencps.backend.dossiermgt.model.FileTemplate fetchFileTemplate(
		long fileTemplateId) {
		return getService().fetchFileTemplate(fileTemplateId);
	}

	/**
	* Returns the file template matching the UUID and group.
	*
	* @param uuid the file template's UUID
	* @param groupId the primary key of the group
	* @return the matching file template, or <code>null</code> if a matching file template could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate fetchFileTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchFileTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the file template with the primary key.
	*
	* @param fileTemplateId the primary key of the file template
	* @return the file template
	* @throws PortalException if a file template with the primary key could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate getFileTemplate(
		long fileTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileTemplate(fileTemplateId);
	}

	/**
	* Returns the file template matching the UUID and group.
	*
	* @param uuid the file template's UUID
	* @param groupId the primary key of the group
	* @return the matching file template
	* @throws PortalException if a matching file template could not be found
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate getFileTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFileTemplateByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the file template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileTemplate the file template
	* @return the file template that was updated
	*/
	public static org.opencps.backend.dossiermgt.model.FileTemplate updateFileTemplate(
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		return getService().updateFileTemplate(fileTemplate);
	}

	public static void addServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId) {
		getService().addServiceInfoFileTemplate(serviceInfoId, fileTemplateId);
	}

	public static void addServiceInfoFileTemplate(long serviceInfoId,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		getService().addServiceInfoFileTemplate(serviceInfoId, fileTemplate);
	}

	public static void addServiceInfoFileTemplates(long serviceInfoId,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		getService().addServiceInfoFileTemplates(serviceInfoId, fileTemplates);
	}

	public static void addServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds) {
		getService().addServiceInfoFileTemplates(serviceInfoId, fileTemplateIds);
	}

	public static void clearServiceInfoFileTemplates(long serviceInfoId) {
		getService().clearServiceInfoFileTemplates(serviceInfoId);
	}

	public static void deleteServiceInfoFileTemplate(long serviceInfoId,
		long fileTemplateId) {
		getService().deleteServiceInfoFileTemplate(serviceInfoId, fileTemplateId);
	}

	public static void deleteServiceInfoFileTemplate(long serviceInfoId,
		org.opencps.backend.dossiermgt.model.FileTemplate fileTemplate) {
		getService().deleteServiceInfoFileTemplate(serviceInfoId, fileTemplate);
	}

	public static void deleteServiceInfoFileTemplates(long serviceInfoId,
		java.util.List<org.opencps.backend.dossiermgt.model.FileTemplate> fileTemplates) {
		getService().deleteServiceInfoFileTemplates(serviceInfoId, fileTemplates);
	}

	public static void deleteServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds) {
		getService()
			.deleteServiceInfoFileTemplates(serviceInfoId, fileTemplateIds);
	}

	public static void setServiceInfoFileTemplates(long serviceInfoId,
		long[] fileTemplateIds) {
		getService().setServiceInfoFileTemplates(serviceInfoId, fileTemplateIds);
	}

	public static FileTemplateLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FileTemplateLocalService, FileTemplateLocalService> _serviceTracker =
		ServiceTrackerFactory.open(FileTemplateLocalService.class);
}