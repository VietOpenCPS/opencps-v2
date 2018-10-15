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
 * Provides a wrapper for {@link ResourceUserLocalService}.
 *
 * @author khoavu
 * @see ResourceUserLocalService
 * @generated
 */
@ProviderType
public class ResourceUserLocalServiceWrapper implements ResourceUserLocalService,
	ServiceWrapper<ResourceUserLocalService> {
	public ResourceUserLocalServiceWrapper(
		ResourceUserLocalService resourceUserLocalService) {
		_resourceUserLocalService = resourceUserLocalService;
	}

	@Override
	public org.opencps.usermgt.model.ResourceUser addResourceUser(long userId,
		long groupId, String className, String classPK, long toUserId,
		String fullname, String email, boolean readonly,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			backend.auth.api.exception.NotFoundException {
		return _resourceUserLocalService.addResourceUser(userId, groupId,
			className, classPK, toUserId, fullname, email, readonly,
			serviceContext);
	}

	/**
	* Adds the resource user to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUser the resource user
	* @return the resource user that was added
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser addResourceUser(
		org.opencps.usermgt.model.ResourceUser resourceUser) {
		return _resourceUserLocalService.addResourceUser(resourceUser);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _resourceUserLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new resource user with the primary key. Does not add the resource user to the database.
	*
	* @param resourceUserId the primary key for the new resource user
	* @return the new resource user
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser createResourceUser(
		long resourceUserId) {
		return _resourceUserLocalService.createResourceUser(resourceUserId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceUserLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the resource user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user that was removed
	* @throws PortalException if a resource user with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser deleteResourceUser(
		long resourceUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceUserLocalService.deleteResourceUser(resourceUserId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser deleteResourceUser(
		long resourceUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException {
		return _resourceUserLocalService.deleteResourceUser(resourceUserId,
			serviceContext);
	}

	/**
	* Deletes the resource user from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUser the resource user
	* @return the resource user that was removed
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser deleteResourceUser(
		org.opencps.usermgt.model.ResourceUser resourceUser) {
		return _resourceUserLocalService.deleteResourceUser(resourceUser);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _resourceUserLocalService.dynamicQuery();
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
		return _resourceUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _resourceUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _resourceUserLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _resourceUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _resourceUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.ResourceUser fetchByF_className_classPK_toUserId(
		long groupId, String className, String classPK, long toUserId) {
		return _resourceUserLocalService.fetchByF_className_classPK_toUserId(groupId,
			className, classPK, toUserId);
	}

	@Override
	public org.opencps.usermgt.model.ResourceUser fetchResourceUser(
		long resourceUserId) {
		return _resourceUserLocalService.fetchResourceUser(resourceUserId);
	}

	/**
	* Returns the resource user matching the UUID and group.
	*
	* @param uuid the resource user's UUID
	* @param groupId the primary key of the group
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser fetchResourceUserByUuidAndGroupId(
		String uuid, long groupId) {
		return _resourceUserLocalService.fetchResourceUserByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceUser> findByF_className_classPK(
		long groupId, String className, String classPK) {
		return _resourceUserLocalService.findByF_className_classPK(groupId,
			className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _resourceUserLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _resourceUserLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _resourceUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _resourceUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the resource user with the primary key.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user
	* @throws PortalException if a resource user with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser getResourceUser(
		long resourceUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceUserLocalService.getResourceUser(resourceUserId);
	}

	/**
	* Returns the resource user matching the UUID and group.
	*
	* @param uuid the resource user's UUID
	* @param groupId the primary key of the group
	* @return the matching resource user
	* @throws PortalException if a matching resource user could not be found
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser getResourceUserByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _resourceUserLocalService.getResourceUserByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the resource users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @return the range of resource users
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceUser> getResourceUsers(
		int start, int end) {
		return _resourceUserLocalService.getResourceUsers(start, end);
	}

	/**
	* Returns all the resource users matching the UUID and company.
	*
	* @param uuid the UUID of the resource users
	* @param companyId the primary key of the company
	* @return the matching resource users, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceUser> getResourceUsersByUuidAndCompanyId(
		String uuid, long companyId) {
		return _resourceUserLocalService.getResourceUsersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of resource users matching the UUID and company.
	*
	* @param uuid the UUID of the resource users
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching resource users, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.ResourceUser> getResourceUsersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.ResourceUser> orderByComparator) {
		return _resourceUserLocalService.getResourceUsersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of resource users.
	*
	* @return the number of resource users
	*/
	@Override
	public int getResourceUsersCount() {
		return _resourceUserLocalService.getResourceUsersCount();
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _resourceUserLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	@Override
	public org.opencps.usermgt.model.ResourceUser updateResourceUser(
		long userId, long resourceUserId, String className, String classPK,
		long toUserId, String fullname, String email, boolean readonly,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _resourceUserLocalService.updateResourceUser(userId,
			resourceUserId, className, classPK, toUserId, fullname, email,
			readonly, serviceContext);
	}

	/**
	* Updates the resource user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceUser the resource user
	* @return the resource user that was updated
	*/
	@Override
	public org.opencps.usermgt.model.ResourceUser updateResourceUser(
		org.opencps.usermgt.model.ResourceUser resourceUser) {
		return _resourceUserLocalService.updateResourceUser(resourceUser);
	}

	@Override
	public ResourceUserLocalService getWrappedService() {
		return _resourceUserLocalService;
	}

	@Override
	public void setWrappedService(
		ResourceUserLocalService resourceUserLocalService) {
		_resourceUserLocalService = resourceUserLocalService;
	}

	private ResourceUserLocalService _resourceUserLocalService;
}