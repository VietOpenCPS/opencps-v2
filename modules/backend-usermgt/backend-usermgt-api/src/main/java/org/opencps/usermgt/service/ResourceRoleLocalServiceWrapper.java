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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ResourceRoleLocalService}.
 *
 * @author khoavu
 * @see ResourceRoleLocalService
 * @generated
 */
@ProviderType
public class ResourceRoleLocalServiceWrapper implements ResourceRoleLocalService,
	ServiceWrapper<ResourceRoleLocalService> {
	public ResourceRoleLocalServiceWrapper(
		ResourceRoleLocalService resourceRoleLocalService) {
		_resourceRoleLocalService = resourceRoleLocalService;
	}

	@Override
	public org.opencps.usermgt.model.ResourceRole addResourceRole(long userId,
		long groupId, String className, String classPK, long roleId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			backend.auth.api.exception.NotFoundException {
		return _resourceRoleLocalService.addResourceRole(userId, groupId,
			className, classPK, roleId, serviceContext);
	}

	/**
	* Adds the resource role to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRole the resource role
	* @return the resource role that was added
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole addResourceRole(
		org.opencps.usermgt.model.ResourceRole resourceRole) {
		return _resourceRoleLocalService.addResourceRole(resourceRole);
	}

	@Override
	public org.opencps.usermgt.model.ResourceRole adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _resourceRoleLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.usermgt.model.ResourceRole adminProcessDelete(Long id) {
		return _resourceRoleLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _resourceRoleLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new resource role with the primary key. Does not add the resource role to the database.
	*
	* @param resourceRoleId the primary key for the new resource role
	* @return the new resource role
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole createResourceRole(
		long resourceRoleId) {
		return _resourceRoleLocalService.createResourceRole(resourceRoleId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceRoleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the resource role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role that was removed
	* @throws PortalException if a resource role with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole deleteResourceRole(
		long resourceRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceRoleLocalService.deleteResourceRole(resourceRoleId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole deleteResourceRole(
		long resourceRoleId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException {
		return _resourceRoleLocalService.deleteResourceRole(resourceRoleId,
			serviceContext);
	}

	/**
	* Deletes the resource role from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRole the resource role
	* @return the resource role that was removed
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole deleteResourceRole(
		org.opencps.usermgt.model.ResourceRole resourceRole) {
		return _resourceRoleLocalService.deleteResourceRole(resourceRole);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _resourceRoleLocalService.dynamicQuery();
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
		return _resourceRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _resourceRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _resourceRoleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _resourceRoleLocalService.dynamicQueryCount(dynamicQuery);
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
		return _resourceRoleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.ResourceRole fetchByF_className_classPK_roleId(
		long groupId, String className, String classPK, long roleId) {
		return _resourceRoleLocalService.fetchByF_className_classPK_roleId(groupId,
			className, classPK, roleId);
	}

	@Override
	public org.opencps.usermgt.model.ResourceRole fetchResourceRole(
		long resourceRoleId) {
		return _resourceRoleLocalService.fetchResourceRole(resourceRoleId);
	}

	/**
	* Returns the resource role matching the UUID and group.
	*
	* @param uuid the resource role's UUID
	* @param groupId the primary key of the group
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole fetchResourceRoleByUuidAndGroupId(
		String uuid, long groupId) {
		return _resourceRoleLocalService.fetchResourceRoleByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceRole> findByF_className_classPK(
		long groupId, String className, String classPK) {
		return _resourceRoleLocalService.findByF_className_classPK(groupId,
			className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _resourceRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _resourceRoleLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _resourceRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _resourceRoleLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the resource role with the primary key.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role
	* @throws PortalException if a resource role with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole getResourceRole(
		long resourceRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceRoleLocalService.getResourceRole(resourceRoleId);
	}

	/**
	* Returns the resource role matching the UUID and group.
	*
	* @param uuid the resource role's UUID
	* @param groupId the primary key of the group
	* @return the matching resource role
	* @throws PortalException if a matching resource role could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole getResourceRoleByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceRoleLocalService.getResourceRoleByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the resource roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @return the range of resource roles
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceRole> getResourceRoles(
		int start, int end) {
		return _resourceRoleLocalService.getResourceRoles(start, end);
	}

	/**
	* Returns all the resource roles matching the UUID and company.
	*
	* @param uuid the UUID of the resource roles
	* @param companyId the primary key of the company
	* @return the matching resource roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceRole> getResourceRolesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _resourceRoleLocalService.getResourceRolesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of resource roles matching the UUID and company.
	*
	* @param uuid the UUID of the resource roles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching resource roles, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceRole> getResourceRolesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.ResourceRole> orderByComparator) {
		return _resourceRoleLocalService.getResourceRolesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of resource roles.
	*
	* @return the number of resource roles
	*/
	@Override
	public int getResourceRolesCount() {
		return _resourceRoleLocalService.getResourceRolesCount();
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _resourceRoleLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	@Override
	public org.opencps.usermgt.model.ResourceRole updateResourceRole(
		long userId, long resourceRoleId, String className, String classPK,
		long roleId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _resourceRoleLocalService.updateResourceRole(userId,
			resourceRoleId, className, classPK, roleId, serviceContext);
	}

	/**
	* Updates the resource role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceRole the resource role
	* @return the resource role that was updated
	*/
	@Override
	public org.opencps.usermgt.model.ResourceRole updateResourceRole(
		org.opencps.usermgt.model.ResourceRole resourceRole) {
		return _resourceRoleLocalService.updateResourceRole(resourceRole);
	}

	@Override
	public ResourceRoleLocalService getWrappedService() {
		return _resourceRoleLocalService;
	}

	@Override
	public void setWrappedService(
		ResourceRoleLocalService resourceRoleLocalService) {
		_resourceRoleLocalService = resourceRoleLocalService;
	}

	private ResourceRoleLocalService _resourceRoleLocalService;
}