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

package org.opencps.deliverable.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OpenCPSDeliverableTypeLocalService}.
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeLocalService
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeLocalServiceWrapper
	implements OpenCPSDeliverableTypeLocalService,
		ServiceWrapper<OpenCPSDeliverableTypeLocalService> {
	public OpenCPSDeliverableTypeLocalServiceWrapper(
		OpenCPSDeliverableTypeLocalService openCPSDeliverableTypeLocalService) {
		_openCPSDeliverableTypeLocalService = openCPSDeliverableTypeLocalService;
	}

	/**
	* Adds the open cps deliverable type to the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	* @return the open cps deliverable type that was added
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType addOpenCPSDeliverableType(
		org.opencps.deliverable.model.OpenCPSDeliverableType openCPSDeliverableType) {
		return _openCPSDeliverableTypeLocalService.addOpenCPSDeliverableType(openCPSDeliverableType);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _openCPSDeliverableTypeLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType adminProcessDelete(
		Long id) {
		return _openCPSDeliverableTypeLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new open cps deliverable type with the primary key. Does not add the open cps deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new open cps deliverable type
	* @return the new open cps deliverable type
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType createOpenCPSDeliverableType(
		long deliverableTypeId) {
		return _openCPSDeliverableTypeLocalService.createOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Deletes the open cps deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type that was removed
	* @throws PortalException if a open cps deliverable type with the primary key could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType deleteOpenCPSDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeLocalService.deleteOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Deletes the open cps deliverable type from the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	* @return the open cps deliverable type that was removed
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType deleteOpenCPSDeliverableType(
		org.opencps.deliverable.model.OpenCPSDeliverableType openCPSDeliverableType) {
		return _openCPSDeliverableTypeLocalService.deleteOpenCPSDeliverableType(openCPSDeliverableType);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _openCPSDeliverableTypeLocalService.dynamicQuery();
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
		return _openCPSDeliverableTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _openCPSDeliverableTypeLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _openCPSDeliverableTypeLocalService.dynamicQuery(dynamicQuery,
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
		return _openCPSDeliverableTypeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _openCPSDeliverableTypeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType fetchOpenCPSDeliverableType(
		long deliverableTypeId) {
		return _openCPSDeliverableTypeLocalService.fetchOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Returns the open cps deliverable type matching the UUID and group.
	*
	* @param uuid the open cps deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType fetchOpenCPSDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return _openCPSDeliverableTypeLocalService.fetchOpenCPSDeliverableTypeByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _openCPSDeliverableTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType getByTypeCode(
		String typeCode, long groupId) {
		return _openCPSDeliverableTypeLocalService.getByTypeCode(typeCode,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getDeliverableTypes(
		long groupId, int start, int end) {
		return _openCPSDeliverableTypeLocalService.getDeliverableTypes(groupId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _openCPSDeliverableTypeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _openCPSDeliverableTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the open cps deliverable type with the primary key.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type
	* @throws PortalException if a open cps deliverable type with the primary key could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType getOpenCPSDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeLocalService.getOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Returns the open cps deliverable type matching the UUID and group.
	*
	* @param uuid the open cps deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type
	* @throws PortalException if a matching open cps deliverable type could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType getOpenCPSDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeLocalService.getOpenCPSDeliverableTypeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of open cps deliverable types
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getOpenCPSDeliverableTypes(
		int start, int end) {
		return _openCPSDeliverableTypeLocalService.getOpenCPSDeliverableTypes(start,
			end);
	}

	/**
	* Returns all the open cps deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable types
	* @param companyId the primary key of the company
	* @return the matching open cps deliverable types, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getOpenCPSDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _openCPSDeliverableTypeLocalService.getOpenCPSDeliverableTypesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of open cps deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable types
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching open cps deliverable types, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getOpenCPSDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.deliverable.model.OpenCPSDeliverableType> orderByComparator) {
		return _openCPSDeliverableTypeLocalService.getOpenCPSDeliverableTypesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of open cps deliverable types.
	*
	* @return the number of open cps deliverable types
	*/
	@Override
	public int getOpenCPSDeliverableTypesCount() {
		return _openCPSDeliverableTypeLocalService.getOpenCPSDeliverableTypesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _openCPSDeliverableTypeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the open cps deliverable type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	* @return the open cps deliverable type that was updated
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableType updateOpenCPSDeliverableType(
		org.opencps.deliverable.model.OpenCPSDeliverableType openCPSDeliverableType) {
		return _openCPSDeliverableTypeLocalService.updateOpenCPSDeliverableType(openCPSDeliverableType);
	}

	@Override
	public OpenCPSDeliverableTypeLocalService getWrappedService() {
		return _openCPSDeliverableTypeLocalService;
	}

	@Override
	public void setWrappedService(
		OpenCPSDeliverableTypeLocalService openCPSDeliverableTypeLocalService) {
		_openCPSDeliverableTypeLocalService = openCPSDeliverableTypeLocalService;
	}

	private OpenCPSDeliverableTypeLocalService _openCPSDeliverableTypeLocalService;
}