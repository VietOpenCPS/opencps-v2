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
 * Provides a wrapper for {@link OpenCPSDeliverableTypeRoleLocalService}.
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeRoleLocalService
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeRoleLocalServiceWrapper
	implements OpenCPSDeliverableTypeRoleLocalService,
		ServiceWrapper<OpenCPSDeliverableTypeRoleLocalService> {
	public OpenCPSDeliverableTypeRoleLocalServiceWrapper(
		OpenCPSDeliverableTypeRoleLocalService openCPSDeliverableTypeRoleLocalService) {
		_openCPSDeliverableTypeRoleLocalService = openCPSDeliverableTypeRoleLocalService;
	}

	/**
	* Adds the open cps deliverable type role to the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	* @return the open cps deliverable type role that was added
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole addOpenCPSDeliverableTypeRole(
		org.opencps.deliverable.model.OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return _openCPSDeliverableTypeRoleLocalService.addOpenCPSDeliverableTypeRole(openCPSDeliverableTypeRole);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _openCPSDeliverableTypeRoleLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole adminProcessDelete(
		Long id) {
		return _openCPSDeliverableTypeRoleLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new open cps deliverable type role with the primary key. Does not add the open cps deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new open cps deliverable type role
	* @return the new open cps deliverable type role
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole createOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId) {
		return _openCPSDeliverableTypeRoleLocalService.createOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Deletes the open cps deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role that was removed
	* @throws PortalException if a open cps deliverable type role with the primary key could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole deleteOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeRoleLocalService.deleteOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Deletes the open cps deliverable type role from the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	* @return the open cps deliverable type role that was removed
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole deleteOpenCPSDeliverableTypeRole(
		org.opencps.deliverable.model.OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return _openCPSDeliverableTypeRoleLocalService.deleteOpenCPSDeliverableTypeRole(openCPSDeliverableTypeRole);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _openCPSDeliverableTypeRoleLocalService.dynamicQuery();
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
		return _openCPSDeliverableTypeRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _openCPSDeliverableTypeRoleLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _openCPSDeliverableTypeRoleLocalService.dynamicQuery(dynamicQuery,
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
		return _openCPSDeliverableTypeRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _openCPSDeliverableTypeRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole fetchOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId) {
		return _openCPSDeliverableTypeRoleLocalService.fetchOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Returns the open cps deliverable type role matching the UUID and group.
	*
	* @param uuid the open cps deliverable type role's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole fetchOpenCPSDeliverableTypeRoleByUuidAndGroupId(
		String uuid, long groupId) {
		return _openCPSDeliverableTypeRoleLocalService.fetchOpenCPSDeliverableTypeRoleByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _openCPSDeliverableTypeRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _openCPSDeliverableTypeRoleLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _openCPSDeliverableTypeRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the open cps deliverable type role with the primary key.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role
	* @throws PortalException if a open cps deliverable type role with the primary key could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole getOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeRoleLocalService.getOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Returns the open cps deliverable type role matching the UUID and group.
	*
	* @param uuid the open cps deliverable type role's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type role
	* @throws PortalException if a matching open cps deliverable type role could not be found
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole getOpenCPSDeliverableTypeRoleByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeRoleLocalService.getOpenCPSDeliverableTypeRoleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of open cps deliverable type roles
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> getOpenCPSDeliverableTypeRoles(
		int start, int end) {
		return _openCPSDeliverableTypeRoleLocalService.getOpenCPSDeliverableTypeRoles(start,
			end);
	}

	/**
	* Returns all the open cps deliverable type roles matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable type roles
	* @param companyId the primary key of the company
	* @return the matching open cps deliverable type roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _openCPSDeliverableTypeRoleLocalService.getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of open cps deliverable type roles matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable type roles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching open cps deliverable type roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> orderByComparator) {
		return _openCPSDeliverableTypeRoleLocalService.getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of open cps deliverable type roles.
	*
	* @return the number of open cps deliverable type roles
	*/
	@Override
	public int getOpenCPSDeliverableTypeRolesCount() {
		return _openCPSDeliverableTypeRoleLocalService.getOpenCPSDeliverableTypeRolesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _openCPSDeliverableTypeRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _openCPSDeliverableTypeRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<Long> getRoleIdByTypes(long deliverableTypeId) {
		return _openCPSDeliverableTypeRoleLocalService.getRoleIdByTypes(deliverableTypeId);
	}

	/**
	* Updates the open cps deliverable type role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	* @return the open cps deliverable type role that was updated
	*/
	@Override
	public org.opencps.deliverable.model.OpenCPSDeliverableTypeRole updateOpenCPSDeliverableTypeRole(
		org.opencps.deliverable.model.OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return _openCPSDeliverableTypeRoleLocalService.updateOpenCPSDeliverableTypeRole(openCPSDeliverableTypeRole);
	}

	@Override
	public OpenCPSDeliverableTypeRoleLocalService getWrappedService() {
		return _openCPSDeliverableTypeRoleLocalService;
	}

	@Override
	public void setWrappedService(
		OpenCPSDeliverableTypeRoleLocalService openCPSDeliverableTypeRoleLocalService) {
		_openCPSDeliverableTypeRoleLocalService = openCPSDeliverableTypeRoleLocalService;
	}

	private OpenCPSDeliverableTypeRoleLocalService _openCPSDeliverableTypeRoleLocalService;
}