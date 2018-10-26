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
 * Provides a wrapper for {@link DeliverableTypeLocalService}.
 *
 * @author huymq
 * @see DeliverableTypeLocalService
 * @generated
 */
@ProviderType
public class DeliverableTypeLocalServiceWrapper
	implements DeliverableTypeLocalService,
		ServiceWrapper<DeliverableTypeLocalService> {
	public DeliverableTypeLocalServiceWrapper(
		DeliverableTypeLocalService deliverableTypeLocalService) {
		_deliverableTypeLocalService = deliverableTypeLocalService;
	}

	/**
	* Adds the deliverable type to the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType addDeliverableType(
		org.opencps.dossiermgt.model.DeliverableType deliverableType) {
		return _deliverableTypeLocalService.addDeliverableType(deliverableType);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType addDeliverableType(
		long groupId, String deliverableName, String deliverableType_,
		String codePattern, String counter, String formScript,
		String formReport, String mappingData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _deliverableTypeLocalService.addDeliverableType(groupId,
			deliverableName, deliverableType_, codePattern, counter,
			formScript, formReport, mappingData, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _deliverableTypeLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType adminProcessDelete(
		Long id) {
		return _deliverableTypeLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new deliverable type with the primary key. Does not add the deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new deliverable type
	* @return the new deliverable type
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType createDeliverableType(
		long deliverableTypeId) {
		return _deliverableTypeLocalService.createDeliverableType(deliverableTypeId);
	}

	/**
	* Deletes the deliverable type from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType deleteDeliverableType(
		org.opencps.dossiermgt.model.DeliverableType deliverableType) {
		return _deliverableTypeLocalService.deleteDeliverableType(deliverableType);
	}

	/**
	* Deletes the deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type that was removed
	* @throws PortalException if a deliverable type with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType deleteDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.deleteDeliverableType(deliverableTypeId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _deliverableTypeLocalService.dynamicQuery();
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
		return _deliverableTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deliverableTypeLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deliverableTypeLocalService.dynamicQuery(dynamicQuery, start,
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
		return _deliverableTypeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _deliverableTypeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType fetchDeliverableType(
		long deliverableTypeId) {
		return _deliverableTypeLocalService.fetchDeliverableType(deliverableTypeId);
	}

	/**
	* Returns the deliverable type matching the UUID and group.
	*
	* @param uuid the deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type, or <code>null</code> if a matching deliverable type could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType fetchDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return _deliverableTypeLocalService.fetchDeliverableTypeByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _deliverableTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType getByCode(
		long groupId, String typeCode) {
		return _deliverableTypeLocalService.getByCode(groupId, typeCode);
	}

	/**
	* Returns the deliverable type with the primary key.
	*
	* @param deliverableTypeId the primary key of the deliverable type
	* @return the deliverable type
	* @throws PortalException if a deliverable type with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType getDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.getDeliverableType(deliverableTypeId);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType getDeliverableTypebyId(
		long groupId, String deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.getDeliverableTypebyId(groupId,
			deliverableTypeId);
	}

	/**
	* Returns the deliverable type matching the UUID and group.
	*
	* @param uuid the deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type
	* @throws PortalException if a matching deliverable type could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType getDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.getDeliverableTypeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @return the range of deliverable types
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DeliverableType> getDeliverableTypes(
		int start, int end) {
		return _deliverableTypeLocalService.getDeliverableTypes(start, end);
	}

	/**
	* Returns all the deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable types
	* @param companyId the primary key of the company
	* @return the matching deliverable types, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DeliverableType> getDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _deliverableTypeLocalService.getDeliverableTypesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable types
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of deliverable types
	* @param end the upper bound of the range of deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching deliverable types, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DeliverableType> getDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DeliverableType> orderByComparator) {
		return _deliverableTypeLocalService.getDeliverableTypesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of deliverable types.
	*
	* @return the number of deliverable types
	*/
	@Override
	public int getDeliverableTypesCount() {
		return _deliverableTypeLocalService.getDeliverableTypesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _deliverableTypeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _deliverableTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _deliverableTypeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType removeDeliverableType(
		long groupId, String deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.removeDeliverableType(groupId,
			deliverableTypeId);
	}

	/**
	* Updates the deliverable type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deliverableType the deliverable type
	* @return the deliverable type that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableType updateDeliverableType(
		org.opencps.dossiermgt.model.DeliverableType deliverableType) {
		return _deliverableTypeLocalService.updateDeliverableType(deliverableType);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType updateDeliverableType(
		long groupId, long deliverableTypeId, String deliverableName,
		String deliverableType_, String codePattern, String counter,
		String formScript, String formReport, String mappingData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeLocalService.updateDeliverableType(groupId,
			deliverableTypeId, deliverableName, deliverableType_, codePattern,
			counter, formScript, formReport, mappingData, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType updateDeliverableTypeDB(
		long userId, long groupId, String typeCode, String typeName,
		String codePattern, Integer docSync, String mappingData,
		String govAgencies, String formReport, String formScript) {
		return _deliverableTypeLocalService.updateDeliverableTypeDB(userId,
			groupId, typeCode, typeName, codePattern, docSync, mappingData,
			govAgencies, formReport, formScript);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType updateFormReport(
		long groupId, long deliverableTypeId, String formReport,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _deliverableTypeLocalService.updateFormReport(groupId,
			deliverableTypeId, formReport, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType updateFormScript(
		long groupId, long deliverableTypeId, String formScript,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _deliverableTypeLocalService.updateFormScript(groupId,
			deliverableTypeId, formScript, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableType updateMappingData(
		long groupId, long deliverableTypeId, String mappingData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _deliverableTypeLocalService.updateMappingData(groupId,
			deliverableTypeId, mappingData, serviceContext);
	}

	@Override
	public DeliverableTypeLocalService getWrappedService() {
		return _deliverableTypeLocalService;
	}

	@Override
	public void setWrappedService(
		DeliverableTypeLocalService deliverableTypeLocalService) {
		_deliverableTypeLocalService = deliverableTypeLocalService;
	}

	private DeliverableTypeLocalService _deliverableTypeLocalService;
}