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

package org.opencps.synchronization.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DictItemGroupTempLocalService}.
 *
 * @author trungdk
 * @see DictItemGroupTempLocalService
 * @generated
 */
@ProviderType
public class DictItemGroupTempLocalServiceWrapper
	implements DictItemGroupTempLocalService,
		ServiceWrapper<DictItemGroupTempLocalService> {
	public DictItemGroupTempLocalServiceWrapper(
		DictItemGroupTempLocalService dictItemGroupTempLocalService) {
		_dictItemGroupTempLocalService = dictItemGroupTempLocalService;
	}

	/**
	* Adds the dict item group temp to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupTemp the dict item group temp
	* @return the dict item group temp that was added
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp addDictItemGroupTemp(
		org.opencps.synchronization.model.DictItemGroupTemp dictItemGroupTemp) {
		return _dictItemGroupTempLocalService.addDictItemGroupTemp(dictItemGroupTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param DictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroupTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp addDictItemGroupTemp(
		long userId, long groupId, long dictGroupId, long dictItemId,
		String groupCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _dictItemGroupTempLocalService.addDictItemGroupTemp(userId,
			groupId, dictGroupId, dictItemId, groupCode, serviceContext);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictItemGroupTempLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	@Override
	public long countOlderThanDate(java.util.Date date, long groupId) {
		return _dictItemGroupTempLocalService.countOlderThanDate(date, groupId);
	}

	/**
	* Creates a new dict item group temp with the primary key. Does not add the dict item group temp to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group temp
	* @return the new dict item group temp
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp createDictItemGroupTemp(
		long dictItemGroupId) {
		return _dictItemGroupTempLocalService.createDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* Deletes the dict item group temp from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupTemp the dict item group temp
	* @return the dict item group temp that was removed
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTemp(
		org.opencps.synchronization.model.DictItemGroupTemp dictItemGroupTemp) {
		return _dictItemGroupTempLocalService.deleteDictItemGroupTemp(dictItemGroupTemp);
	}

	/**
	* Deletes the dict item group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp that was removed
	* @throws PortalException if a dict item group temp with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTemp(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupTempLocalService.deleteDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* @author binhth
	* @param dictItemGroupId
	* @param serviceContext
	* @throws UnauthenticationException,
	UnauthorizationException, NotFoundException
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTemp(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictItemGroupTempLocalService.deleteDictItemGroupTemp(dictItemGroupId,
			serviceContext);
	}

	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTempNoneAuthen(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictItemGroupTempLocalService.deleteDictItemGroupTempNoneAuthen(dictItemGroupId,
			serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupTempLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictItemGroupTempLocalService.dynamicQuery();
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
		return _dictItemGroupTempLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictItemGroupTempLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictItemGroupTempLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dictItemGroupTempLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictItemGroupTempLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* @author binhth
	* @param groupId
	* @param dictItemId
	* @param DictItemGroupId
	* @return DictItemGroupTemp
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp fetchByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId) {
		return _dictItemGroupTempLocalService.fetchByF_dictItemId_dictGroupId(groupId,
			dictGroupId, dictItemId);
	}

	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp fetchDictItemGroupTemp(
		long dictItemGroupId) {
		return _dictItemGroupTempLocalService.fetchDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* Returns the dict item group temp matching the UUID and group.
	*
	* @param uuid the dict item group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp fetchDictItemGroupTempByUuidAndGroupId(
		String uuid, long groupId) {
		return _dictItemGroupTempLocalService.fetchDictItemGroupTempByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findByDictGroupId(
		long groupId, long dictGroupId) {
		return _dictItemGroupTempLocalService.findByDictGroupId(groupId,
			dictGroupId);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findByDictItemTemp(
		long groupId, long dictItemId, int start, int end) {
		return _dictItemGroupTempLocalService.findByDictItemTemp(groupId,
			dictItemId, start, end);
	}

	/**
	* @author binhth
	* @param groupId
	* @param dictGroupId
	* @param dictItemId
	* @return List<DictItemGroupTemp>
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findByF_dictItemId(
		long groupId, long dictItemId) {
		return _dictItemGroupTempLocalService.findByF_dictItemId(groupId,
			dictItemId);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return _dictItemGroupTempLocalService.findOlderThanDate(date, groupId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictItemGroupTempLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dict item group temp with the primary key.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp
	* @throws PortalException if a dict item group temp with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp getDictItemGroupTemp(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupTempLocalService.getDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* Returns the dict item group temp matching the UUID and group.
	*
	* @param uuid the dict item group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group temp
	* @throws PortalException if a matching dict item group temp could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp getDictItemGroupTempByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupTempLocalService.getDictItemGroupTempByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of dict item group temps
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> getDictItemGroupTemps(
		int start, int end) {
		return _dictItemGroupTempLocalService.getDictItemGroupTemps(start, end);
	}

	/**
	* Returns all the dict item group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict item group temps
	* @param companyId the primary key of the company
	* @return the matching dict item group temps, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> getDictItemGroupTempsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dictItemGroupTempLocalService.getDictItemGroupTempsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dict item group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict item group temps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict item group temps, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> getDictItemGroupTempsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictItemGroupTemp> orderByComparator) {
		return _dictItemGroupTempLocalService.getDictItemGroupTempsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dict item group temps.
	*
	* @return the number of dict item group temps
	*/
	@Override
	public int getDictItemGroupTempsCount() {
		return _dictItemGroupTempLocalService.getDictItemGroupTempsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dictItemGroupTempLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictItemGroupTempLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dictItemGroupTempLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemGroupTempLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictItemGroupTempLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Updates the dict item group temp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupTemp the dict item group temp
	* @return the dict item group temp that was updated
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp updateDictItemGroupTemp(
		org.opencps.synchronization.model.DictItemGroupTemp dictItemGroupTemp) {
		return _dictItemGroupTempLocalService.updateDictItemGroupTemp(dictItemGroupTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroupTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Override
	public org.opencps.synchronization.model.DictItemGroupTemp updateDictItemGroupTemp(
		long userId, long dictItemGroupId, long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return _dictItemGroupTempLocalService.updateDictItemGroupTemp(userId,
			dictItemGroupId, dictItemId, serviceContext);
	}

	@Override
	public DictItemGroupTempLocalService getWrappedService() {
		return _dictItemGroupTempLocalService;
	}

	@Override
	public void setWrappedService(
		DictItemGroupTempLocalService dictItemGroupTempLocalService) {
		_dictItemGroupTempLocalService = dictItemGroupTempLocalService;
	}

	private DictItemGroupTempLocalService _dictItemGroupTempLocalService;
}