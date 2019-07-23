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
 * Provides a wrapper for {@link DeliverableTypeRoleLocalService}.
 *
 * @author huymq
 * @see DeliverableTypeRoleLocalService
 * @generated
 */
@ProviderType
public class DeliverableTypeRoleLocalServiceWrapper
	implements DeliverableTypeRoleLocalService,
		ServiceWrapper<DeliverableTypeRoleLocalService> {
	public DeliverableTypeRoleLocalServiceWrapper(
		DeliverableTypeRoleLocalService deliverableTypeRoleLocalService) {
		_deliverableTypeRoleLocalService = deliverableTypeRoleLocalService;
	}

	/**
	* Adds the deliverable type role to the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRole the deliverable type role
	* @return the deliverable type role that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole addDeliverableTypeRole(
		org.opencps.dossiermgt.model.DeliverableTypeRole deliverableTypeRole) {
		return _deliverableTypeRoleLocalService.addDeliverableTypeRole(deliverableTypeRole);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _deliverableTypeRoleLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole adminProcessDelete(
		Long id) {
		return _deliverableTypeRoleLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new deliverable type role with the primary key. Does not add the deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new deliverable type role
	* @return the new deliverable type role
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole createDeliverableTypeRole(
		long deliverableTypeRoleId) {
		return _deliverableTypeRoleLocalService.createDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Deletes the deliverable type role from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRole the deliverable type role
	* @return the deliverable type role that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole deleteDeliverableTypeRole(
		org.opencps.dossiermgt.model.DeliverableTypeRole deliverableTypeRole) {
		return _deliverableTypeRoleLocalService.deleteDeliverableTypeRole(deliverableTypeRole);
	}

	/**
	* Deletes the deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role that was removed
	* @throws PortalException if a deliverable type role with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole deleteDeliverableTypeRole(
		long deliverableTypeRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeRoleLocalService.deleteDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _deliverableTypeRoleLocalService.dynamicQuery();
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
		return _deliverableTypeRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deliverableTypeRoleLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _deliverableTypeRoleLocalService.dynamicQuery(dynamicQuery,
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
		return _deliverableTypeRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _deliverableTypeRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole fetchDeliverableTypeRole(
		long deliverableTypeRoleId) {
		return _deliverableTypeRoleLocalService.fetchDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Returns the deliverable type role matching the UUID and group.
	*
	* @param uuid the deliverable type role's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type role, or <code>null</code> if a matching deliverable type role could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole fetchDeliverableTypeRoleByUuidAndGroupId(
		String uuid, long groupId) {
		return _deliverableTypeRoleLocalService.fetchDeliverableTypeRoleByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _deliverableTypeRoleLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the deliverable type role with the primary key.
	*
	* @param deliverableTypeRoleId the primary key of the deliverable type role
	* @return the deliverable type role
	* @throws PortalException if a deliverable type role with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole getDeliverableTypeRole(
		long deliverableTypeRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeRoleLocalService.getDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Returns the deliverable type role matching the UUID and group.
	*
	* @param uuid the deliverable type role's UUID
	* @param groupId the primary key of the group
	* @return the matching deliverable type role
	* @throws PortalException if a matching deliverable type role could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole getDeliverableTypeRoleByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeRoleLocalService.getDeliverableTypeRoleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @return the range of deliverable type roles
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DeliverableTypeRole> getDeliverableTypeRoles(
		int start, int end) {
		return _deliverableTypeRoleLocalService.getDeliverableTypeRoles(start,
			end);
	}

	/**
	* Returns all the deliverable type roles matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable type roles
	* @param companyId the primary key of the company
	* @return the matching deliverable type roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DeliverableTypeRole> getDeliverableTypeRolesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _deliverableTypeRoleLocalService.getDeliverableTypeRolesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of deliverable type roles matching the UUID and company.
	*
	* @param uuid the UUID of the deliverable type roles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of deliverable type roles
	* @param end the upper bound of the range of deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching deliverable type roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DeliverableTypeRole> getDeliverableTypeRolesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DeliverableTypeRole> orderByComparator) {
		return _deliverableTypeRoleLocalService.getDeliverableTypeRolesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of deliverable type roles.
	*
	* @return the number of deliverable type roles
	*/
	@Override
	public int getDeliverableTypeRolesCount() {
		return _deliverableTypeRoleLocalService.getDeliverableTypeRolesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _deliverableTypeRoleLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _deliverableTypeRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _deliverableTypeRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _deliverableTypeRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<Long> getRoleIdByTypes(long deliverableTypeId) {
		return _deliverableTypeRoleLocalService.getRoleIdByTypes(deliverableTypeId);
	}

	/**
	* Updates the deliverable type role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRole the deliverable type role
	* @return the deliverable type role that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole updateDeliverableTypeRole(
		org.opencps.dossiermgt.model.DeliverableTypeRole deliverableTypeRole) {
		return _deliverableTypeRoleLocalService.updateDeliverableTypeRole(deliverableTypeRole);
	}

	@Override
	public org.opencps.dossiermgt.model.DeliverableTypeRole updateDeliverableTypeRoleDB(
		long userId, long groupId, long deliverableTypeId, long mappingRoleId,
		boolean moderator) {
		return _deliverableTypeRoleLocalService.updateDeliverableTypeRoleDB(userId,
			groupId, deliverableTypeId, mappingRoleId, moderator);
	}

	@Override
	public DeliverableTypeRoleLocalService getWrappedService() {
		return _deliverableTypeRoleLocalService;
	}

	@Override
	public void setWrappedService(
		DeliverableTypeRoleLocalService deliverableTypeRoleLocalService) {
		_deliverableTypeRoleLocalService = deliverableTypeRoleLocalService;
	}

	private DeliverableTypeRoleLocalService _deliverableTypeRoleLocalService;
}