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
 * Provides a wrapper for {@link RegistrationTemplatesLocalService}.
 *
 * @author huymq
 * @see RegistrationTemplatesLocalService
 * @generated
 */
@ProviderType
public class RegistrationTemplatesLocalServiceWrapper
	implements RegistrationTemplatesLocalService,
		ServiceWrapper<RegistrationTemplatesLocalService> {
	public RegistrationTemplatesLocalServiceWrapper(
		RegistrationTemplatesLocalService registrationTemplatesLocalService) {
		_registrationTemplatesLocalService = registrationTemplatesLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates addRegistrationTemplates(
		long groupId, String govAgencyCode, String govAgencyName,
		String formNo, String formName, boolean multiple, String formScript,
		String formReport, String sampleData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationTemplatesLocalService.addRegistrationTemplates(groupId,
			govAgencyCode, govAgencyName, formNo, formName, multiple,
			formScript, formReport, sampleData, serviceContext);
	}

	/**
	* Adds the registration templates to the database. Also notifies the appropriate model listeners.
	*
	* @param registrationTemplates the registration templates
	* @return the registration templates that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates addRegistrationTemplates(
		org.opencps.dossiermgt.model.RegistrationTemplates registrationTemplates) {
		return _registrationTemplatesLocalService.addRegistrationTemplates(registrationTemplates);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _registrationTemplatesLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates adminProcessDelete(
		Long id) {
		return _registrationTemplatesLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new registration templates with the primary key. Does not add the registration templates to the database.
	*
	* @param registrationTemplateId the primary key for the new registration templates
	* @return the new registration templates
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates createRegistrationTemplates(
		long registrationTemplateId) {
		return _registrationTemplatesLocalService.createRegistrationTemplates(registrationTemplateId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the registration templates with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates that was removed
	* @throws PortalException if a registration templates with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates deleteRegistrationTemplates(
		long registrationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.deleteRegistrationTemplates(registrationTemplateId);
	}

	/**
	* Deletes the registration templates from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationTemplates the registration templates
	* @return the registration templates that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates deleteRegistrationTemplates(
		org.opencps.dossiermgt.model.RegistrationTemplates registrationTemplates) {
		return _registrationTemplatesLocalService.deleteRegistrationTemplates(registrationTemplates);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _registrationTemplatesLocalService.dynamicQuery();
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
		return _registrationTemplatesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _registrationTemplatesLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _registrationTemplatesLocalService.dynamicQuery(dynamicQuery,
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
		return _registrationTemplatesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _registrationTemplatesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates fetchRegistrationTemplates(
		long registrationTemplateId) {
		return _registrationTemplatesLocalService.fetchRegistrationTemplates(registrationTemplateId);
	}

	/**
	* Returns the registration templates matching the UUID and group.
	*
	* @param uuid the registration templates's UUID
	* @param groupId the primary key of the group
	* @return the matching registration templates, or <code>null</code> if a matching registration templates could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates fetchRegistrationTemplatesByUuidAndGroupId(
		String uuid, long groupId) {
		return _registrationTemplatesLocalService.fetchRegistrationTemplatesByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _registrationTemplatesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _registrationTemplatesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _registrationTemplatesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates getRegistrationTemplatebyId(
		long groupId, String registrationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.getRegistrationTemplatebyId(groupId,
			registrationTemplateId);
	}

	/**
	* Returns the registration templates with the primary key.
	*
	* @param registrationTemplateId the primary key of the registration templates
	* @return the registration templates
	* @throws PortalException if a registration templates with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates getRegistrationTemplates(
		long registrationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.getRegistrationTemplates(registrationTemplateId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationTemplates> getRegistrationTemplatesbyFormNo(
		long groupId, String formNo) {
		return _registrationTemplatesLocalService.getRegistrationTemplatesbyFormNo(groupId,
			formNo);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationTemplates> getRegistrationTemplatesbyGOVCODE(
		long groupId, String govAgencyCode) {
		return _registrationTemplatesLocalService.getRegistrationTemplatesbyGOVCODE(groupId,
			govAgencyCode);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationTemplates> getRegistrationTemplatesbyGroupId(
		long groupId) {
		return _registrationTemplatesLocalService.getRegistrationTemplatesbyGroupId(groupId);
	}

	/**
	* Returns the registration templates matching the UUID and group.
	*
	* @param uuid the registration templates's UUID
	* @param groupId the primary key of the group
	* @return the matching registration templates
	* @throws PortalException if a matching registration templates could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates getRegistrationTemplatesByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.getRegistrationTemplatesByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the registration templateses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationTemplatesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration templateses
	* @param end the upper bound of the range of registration templateses (not inclusive)
	* @return the range of registration templateses
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationTemplates> getRegistrationTemplateses(
		int start, int end) {
		return _registrationTemplatesLocalService.getRegistrationTemplateses(start,
			end);
	}

	/**
	* Returns the number of registration templateses.
	*
	* @return the number of registration templateses
	*/
	@Override
	public int getRegistrationTemplatesesCount() {
		return _registrationTemplatesLocalService.getRegistrationTemplatesesCount();
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates getRegTempbyFormNoGovCode(
		long groupId, String formNo, String govAgencyCode) {
		return _registrationTemplatesLocalService.getRegTempbyFormNoGovCode(groupId,
			formNo, govAgencyCode);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates getRegTempbyRegId(
		long groupId, long registrationTemplatesId) {
		return _registrationTemplatesLocalService.getRegTempbyRegId(groupId,
			registrationTemplatesId);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates removeRegistrationTemplate(
		long groupId, String registrationTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.removeRegistrationTemplate(groupId,
			registrationTemplateId);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates updateFormReport(
		long groupId, long registrationTemplatesId, String formReport,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationTemplatesLocalService.updateFormReport(groupId,
			registrationTemplatesId, formReport, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates updateFormScript(
		long groupId, long registrationTemplateId, String formScript,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationTemplatesLocalService.updateFormScript(groupId,
			registrationTemplateId, formScript, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates updateRegistrationTemplates(
		long groupId, long registrationTemplateId, String govAgencyCode,
		String govAgencyName, String formNo, String formName, boolean multiple,
		String formScript, String formReport, String sampleData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationTemplatesLocalService.updateRegistrationTemplates(groupId,
			registrationTemplateId, govAgencyCode, govAgencyName, formNo,
			formName, multiple, formScript, formReport, sampleData,
			serviceContext);
	}

	/**
	* Updates the registration templates in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registrationTemplates the registration templates
	* @return the registration templates that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates updateRegistrationTemplates(
		org.opencps.dossiermgt.model.RegistrationTemplates registrationTemplates) {
		return _registrationTemplatesLocalService.updateRegistrationTemplates(registrationTemplates);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationTemplates updateSampledata(
		long groupId, long registrationTemplatesId, String sampleData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationTemplatesLocalService.updateSampledata(groupId,
			registrationTemplatesId, sampleData, serviceContext);
	}

	@Override
	public RegistrationTemplatesLocalService getWrappedService() {
		return _registrationTemplatesLocalService;
	}

	@Override
	public void setWrappedService(
		RegistrationTemplatesLocalService registrationTemplatesLocalService) {
		_registrationTemplatesLocalService = registrationTemplatesLocalService;
	}

	private RegistrationTemplatesLocalService _registrationTemplatesLocalService;
}