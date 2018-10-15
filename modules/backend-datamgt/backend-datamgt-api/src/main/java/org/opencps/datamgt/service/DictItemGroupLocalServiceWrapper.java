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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DictItemGroupLocalService}.
 *
 * @author khoavu
 * @see DictItemGroupLocalService
 * @generated
 */
@ProviderType
public class DictItemGroupLocalServiceWrapper
	implements DictItemGroupLocalService,
		ServiceWrapper<DictItemGroupLocalService> {
	public DictItemGroupLocalServiceWrapper(
		DictItemGroupLocalService dictItemGroupLocalService) {
		_dictItemGroupLocalService = dictItemGroupLocalService;
	}

	/**
	* Adds the dict item group to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was added
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup addDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return _dictItemGroupLocalService.addDictItemGroup(dictItemGroup);
	}

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param DictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup addDictItemGroup(
		long userId, long groupId, long dictGroupId, long dictItemId,
		String groupCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _dictItemGroupLocalService.addDictItemGroup(userId, groupId,
			dictGroupId, dictItemId, groupCode, serviceContext);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictItemGroupLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	@Override
	public long countOlderThanDate(java.util.Date date, long groupId) {
		return _dictItemGroupLocalService.countOlderThanDate(date, groupId);
	}

	/**
	* Creates a new dict item group with the primary key. Does not add the dict item group to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group
	* @return the new dict item group
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup createDictItemGroup(
		long dictItemGroupId) {
		return _dictItemGroupLocalService.createDictItemGroup(dictItemGroupId);
	}

	/**
	* Deletes the dict item group from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was removed
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return _dictItemGroupLocalService.deleteDictItemGroup(dictItemGroup);
	}

	/**
	* Deletes the dict item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group that was removed
	* @throws PortalException if a dict item group with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupLocalService.deleteDictItemGroup(dictItemGroupId);
	}

	/**
	* @author binhth
	* @param dictItemGroupId
	* @param serviceContext
	* @throws UnauthenticationException,
	UnauthorizationException, NotFoundException
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictItemGroupLocalService.deleteDictItemGroup(dictItemGroupId,
			serviceContext);
	}

	@Override
	public org.opencps.datamgt.model.DictItemGroup deleteDictItemGroupNoneAuthen(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictItemGroupLocalService.deleteDictItemGroupNoneAuthen(dictItemGroupId,
			serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictItemGroupLocalService.dynamicQuery();
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
		return _dictItemGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictItemGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictItemGroupLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dictItemGroupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictItemGroupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* @author binhth
	* @param groupId
	* @param dictItemId
	* @param DictItemGroupId
	* @return DictItemGroup
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup fetchByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId) {
		return _dictItemGroupLocalService.fetchByF_dictItemId_dictGroupId(groupId,
			dictGroupId, dictItemId);
	}

	@Override
	public org.opencps.datamgt.model.DictItemGroup fetchDictItemGroup(
		long dictItemGroupId) {
		return _dictItemGroupLocalService.fetchDictItemGroup(dictItemGroupId);
	}

	/**
	* Returns the dict item group matching the UUID and group.
	*
	* @param uuid the dict item group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup fetchDictItemGroupByUuidAndGroupId(
		String uuid, long groupId) {
		return _dictItemGroupLocalService.fetchDictItemGroupByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemGroup> findByDictGroupId(
		long groupId, long dictGroupId) {
		return _dictItemGroupLocalService.findByDictGroupId(groupId, dictGroupId);
	}

	/**
	* @author binhth
	* @param groupId
	* @param dictGroupId
	* @param dictItemId
	* @return List<DictItemGroup>
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemGroup> findByF_dictItemId(
		long groupId, long dictItemId) {
		return _dictItemGroupLocalService.findByF_dictItemId(groupId, dictItemId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemGroup> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return _dictItemGroupLocalService.findOlderThanDate(date, groupId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictItemGroupLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dict item group with the primary key.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group
	* @throws PortalException if a dict item group with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup getDictItemGroup(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupLocalService.getDictItemGroup(dictItemGroupId);
	}

	/**
	* Returns the dict item group matching the UUID and group.
	*
	* @param uuid the dict item group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group
	* @throws PortalException if a matching dict item group could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup getDictItemGroupByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupLocalService.getDictItemGroupByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of dict item groups
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemGroup> getDictItemGroups(
		int start, int end) {
		return _dictItemGroupLocalService.getDictItemGroups(start, end);
	}

	/**
	* Returns all the dict item groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict item groups
	* @param companyId the primary key of the company
	* @return the matching dict item groups, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemGroup> getDictItemGroupsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dictItemGroupLocalService.getDictItemGroupsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dict item groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict item groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict item groups, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemGroup> getDictItemGroupsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItemGroup> orderByComparator) {
		return _dictItemGroupLocalService.getDictItemGroupsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dict item groups.
	*
	* @return the number of dict item groups
	*/
	@Override
	public int getDictItemGroupsCount() {
		return _dictItemGroupLocalService.getDictItemGroupsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dictItemGroupLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictItemGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dictItemGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictItemGroupLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Updates the dict item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was updated
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup updateDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return _dictItemGroupLocalService.updateDictItemGroup(dictItemGroup);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Override
	public org.opencps.datamgt.model.DictItemGroup updateDictItemGroup(
		long userId, long dictItemGroupId, long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return _dictItemGroupLocalService.updateDictItemGroup(userId,
			dictItemGroupId, dictItemId, serviceContext);
	}

	@Override
	public DictItemGroupLocalService getWrappedService() {
		return _dictItemGroupLocalService;
	}

	@Override
	public void setWrappedService(
		DictItemGroupLocalService dictItemGroupLocalService) {
		_dictItemGroupLocalService = dictItemGroupLocalService;
	}

	private DictItemGroupLocalService _dictItemGroupLocalService;
}