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
 * Provides a wrapper for {@link ServiceProcessRoleLocalService}.
 *
 * @author huymq
 * @see ServiceProcessRoleLocalService
 * @generated
 */
@ProviderType
public class ServiceProcessRoleLocalServiceWrapper
	implements ServiceProcessRoleLocalService,
		ServiceWrapper<ServiceProcessRoleLocalService> {
	public ServiceProcessRoleLocalServiceWrapper(
		ServiceProcessRoleLocalService serviceProcessRoleLocalService) {
		_serviceProcessRoleLocalService = serviceProcessRoleLocalService;
	}

	/**
	* Adds the service process role to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRole the service process role
	* @return the service process role that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole addServiceProcessRole(
		org.opencps.dossiermgt.model.ServiceProcessRole serviceProcessRole) {
		return _serviceProcessRoleLocalService.addServiceProcessRole(serviceProcessRole);
	}

	/**
	* Creates a new service process role with the primary key. Does not add the service process role to the database.
	*
	* @param serviceProcessRolePK the primary key for the new service process role
	* @return the new service process role
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole createServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK) {
		return _serviceProcessRoleLocalService.createServiceProcessRole(serviceProcessRolePK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessRoleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the service process role from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRole the service process role
	* @return the service process role that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole deleteServiceProcessRole(
		org.opencps.dossiermgt.model.ServiceProcessRole serviceProcessRole) {
		return _serviceProcessRoleLocalService.deleteServiceProcessRole(serviceProcessRole);
	}

	/**
	* Deletes the service process role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role that was removed
	* @throws PortalException if a service process role with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole deleteServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessRoleLocalService.deleteServiceProcessRole(serviceProcessRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _serviceProcessRoleLocalService.dynamicQuery();
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
		return _serviceProcessRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceProcessRoleLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _serviceProcessRoleLocalService.dynamicQuery(dynamicQuery,
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
		return _serviceProcessRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _serviceProcessRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole fetchServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK) {
		return _serviceProcessRoleLocalService.fetchServiceProcessRole(serviceProcessRolePK);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcessRole> findByS_P_ID(
		long serviceProcessId) {
		return _serviceProcessRoleLocalService.findByS_P_ID(serviceProcessId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _serviceProcessRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public long getByServiceRoleCode(String roleCode) {
		return _serviceProcessRoleLocalService.getByServiceRoleCode(roleCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _serviceProcessRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceProcessRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the service process role with the primary key.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role
	* @throws PortalException if a service process role with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole getServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _serviceProcessRoleLocalService.getServiceProcessRole(serviceProcessRolePK);
	}

	/**
	* Returns a range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of service process roles
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ServiceProcessRole> getServiceProcessRoles(
		int start, int end) {
		return _serviceProcessRoleLocalService.getServiceProcessRoles(start, end);
	}

	/**
	* Returns the number of service process roles.
	*
	* @return the number of service process roles
	*/
	@Override
	public int getServiceProcessRolesCount() {
		return _serviceProcessRoleLocalService.getServiceProcessRolesCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole removeServiceProcessRole(
		long serviceProcessId, long roleId) {
		return _serviceProcessRoleLocalService.removeServiceProcessRole(serviceProcessId,
			roleId);
	}

	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole updateServiceProcessRole(
		long groupId, long serviceProcessId, long roleId, boolean moderator,
		String condition) {
		return _serviceProcessRoleLocalService.updateServiceProcessRole(groupId,
			serviceProcessId, roleId, moderator, condition);
	}

	/**
	* Updates the service process role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRole the service process role
	* @return the service process role that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ServiceProcessRole updateServiceProcessRole(
		org.opencps.dossiermgt.model.ServiceProcessRole serviceProcessRole) {
		return _serviceProcessRoleLocalService.updateServiceProcessRole(serviceProcessRole);
	}

	@Override
	public void updateServiceProcessRoleDB(long groupId, long serviceProcessId,
		long roleId, String roleCode, String roleName, boolean moderator,
		String condition,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		_serviceProcessRoleLocalService.updateServiceProcessRoleDB(groupId,
			serviceProcessId, roleId, roleCode, roleName, moderator, condition,
			serviceContext);
	}

	@Override
	public ServiceProcessRoleLocalService getWrappedService() {
		return _serviceProcessRoleLocalService;
	}

	@Override
	public void setWrappedService(
		ServiceProcessRoleLocalService serviceProcessRoleLocalService) {
		_serviceProcessRoleLocalService = serviceProcessRoleLocalService;
	}

	private ServiceProcessRoleLocalService _serviceProcessRoleLocalService;
}