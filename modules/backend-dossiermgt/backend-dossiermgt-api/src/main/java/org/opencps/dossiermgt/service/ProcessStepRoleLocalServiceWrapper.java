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
 * Provides a wrapper for {@link ProcessStepRoleLocalService}.
 *
 * @author huymq
 * @see ProcessStepRoleLocalService
 * @generated
 */
@ProviderType
public class ProcessStepRoleLocalServiceWrapper
	implements ProcessStepRoleLocalService,
		ServiceWrapper<ProcessStepRoleLocalService> {
	public ProcessStepRoleLocalServiceWrapper(
		ProcessStepRoleLocalService processStepRoleLocalService) {
		_processStepRoleLocalService = processStepRoleLocalService;
	}

	/**
	* Adds the process step role to the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole addProcessStepRole(
		org.opencps.dossiermgt.model.ProcessStepRole processStepRole) {
		return _processStepRoleLocalService.addProcessStepRole(processStepRole);
	}

	/**
	* Creates a new process step role with the primary key. Does not add the process step role to the database.
	*
	* @param processStepRolePK the primary key for the new process step role
	* @return the new process step role
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole createProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK) {
		return _processStepRoleLocalService.createProcessStepRole(processStepRolePK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepRoleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process step role from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole deleteProcessStepRole(
		org.opencps.dossiermgt.model.ProcessStepRole processStepRole) {
		return _processStepRoleLocalService.deleteProcessStepRole(processStepRole);
	}

	/**
	* Deletes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role that was removed
	* @throws PortalException if a process step role with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole deleteProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepRoleLocalService.deleteProcessStepRole(processStepRolePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processStepRoleLocalService.dynamicQuery();
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
		return _processStepRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processStepRoleLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processStepRoleLocalService.dynamicQuery(dynamicQuery, start,
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
		return _processStepRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _processStepRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole fetchProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK) {
		return _processStepRoleLocalService.fetchProcessStepRole(processStepRolePK);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStepRole> findByP_S_ID(
		long processStepId) {
		return _processStepRoleLocalService.findByP_S_ID(processStepId);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole findByStepAndRole(
		long processStepId, long roleId) {
		return _processStepRoleLocalService.findByStepAndRole(processStepId,
			roleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processStepRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public long getByRoleCode(String roleCode) {
		return _processStepRoleLocalService.getByRoleCode(roleCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processStepRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processStepRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process step role with the primary key.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role
	* @throws PortalException if a process step role with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole getProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processStepRoleLocalService.getProcessStepRole(processStepRolePK);
	}

	/**
	* Returns a range of all the process step roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @return the range of process step roles
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessStepRole> getProcessStepRoles(
		int start, int end) {
		return _processStepRoleLocalService.getProcessStepRoles(start, end);
	}

	/**
	* Returns the number of process step roles.
	*
	* @return the number of process step roles
	*/
	@Override
	public int getProcessStepRolesCount() {
		return _processStepRoleLocalService.getProcessStepRolesCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole removeProcessStepRole(
		long processStepId, long roleId) {
		return _processStepRoleLocalService.removeProcessStepRole(processStepId,
			roleId);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole updateProcessStepRole(
		long processStepId, long roleId, boolean moderator, String condition) {
		return _processStepRoleLocalService.updateProcessStepRole(processStepId,
			roleId, moderator, condition);
	}

	/**
	* Updates the process step role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole updateProcessStepRole(
		org.opencps.dossiermgt.model.ProcessStepRole processStepRole) {
		return _processStepRoleLocalService.updateProcessStepRole(processStepRole);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessStepRole updateProcessStepRoleDB(
		long userId, long groupId, long processStepId, long roleId,
		String roleCode, String roleName, boolean moderator, String condition,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _processStepRoleLocalService.updateProcessStepRoleDB(userId,
			groupId, processStepId, roleId, roleCode, roleName, moderator,
			condition, serviceContext);
	}

	@Override
	public ProcessStepRoleLocalService getWrappedService() {
		return _processStepRoleLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessStepRoleLocalService processStepRoleLocalService) {
		_processStepRoleLocalService = processStepRoleLocalService;
	}

	private ProcessStepRoleLocalService _processStepRoleLocalService;
}