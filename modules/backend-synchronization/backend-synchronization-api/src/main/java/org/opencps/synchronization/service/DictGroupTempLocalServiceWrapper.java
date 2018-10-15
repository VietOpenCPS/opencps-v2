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
 * Provides a wrapper for {@link DictGroupTempLocalService}.
 *
 * @author trungdk
 * @see DictGroupTempLocalService
 * @generated
 */
@ProviderType
public class DictGroupTempLocalServiceWrapper
	implements DictGroupTempLocalService,
		ServiceWrapper<DictGroupTempLocalService> {
	public DictGroupTempLocalServiceWrapper(
		DictGroupTempLocalService dictGroupTempLocalService) {
		_dictGroupTempLocalService = dictGroupTempLocalService;
	}

	/**
	* Adds the dict group temp to the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupTemp the dict group temp
	* @return the dict group temp that was added
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp addDictGroupTemp(
		org.opencps.synchronization.model.DictGroupTemp dictGroupTemp) {
		return _dictGroupTempLocalService.addDictGroupTemp(dictGroupTemp);
	}

	@Override
	public org.opencps.synchronization.model.DictGroupTemp addDictGroupTemp(
		long userId, long groupId, long dictCollectionId, String groupCode,
		String groupName, String groupNameEN, String groupDescription,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _dictGroupTempLocalService.addDictGroupTemp(userId, groupId,
			dictCollectionId, groupCode, groupName, groupNameEN,
			groupDescription, status, serviceContext);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictGroupTempLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	@Override
	public long countOlderThanDate(java.util.Date date, long groupId) {
		return _dictGroupTempLocalService.countOlderThanDate(date, groupId);
	}

	/**
	* Creates a new dict group temp with the primary key. Does not add the dict group temp to the database.
	*
	* @param dictGroupId the primary key for the new dict group temp
	* @return the new dict group temp
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp createDictGroupTemp(
		long dictGroupId) {
		return _dictGroupTempLocalService.createDictGroupTemp(dictGroupId);
	}

	/**
	* Deletes the dict group temp from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupTemp the dict group temp
	* @return the dict group temp that was removed
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp deleteDictGroupTemp(
		org.opencps.synchronization.model.DictGroupTemp dictGroupTemp) {
		return _dictGroupTempLocalService.deleteDictGroupTemp(dictGroupTemp);
	}

	/**
	* Deletes the dict group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp that was removed
	* @throws PortalException if a dict group temp with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp deleteDictGroupTemp(
		long dictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupTempLocalService.deleteDictGroupTemp(dictGroupId);
	}

	/**
	* @author binhth
	* @param dictGroupId
	* @param serviceContext
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp deleteDictGroupTemp(
		long dictGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictGroupTempLocalService.deleteDictGroupTemp(dictGroupId,
			serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupTempLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictGroupTempLocalService.dynamicQuery();
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
		return _dictGroupTempLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictGroupTempLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictGroupTempLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dictGroupTempLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictGroupTempLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* @author binhth
	* @param groupCode
	* @param groupId
	* @return DictGroupTemp
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp fetchByF_DictGroupCode(
		String groupCode, long groupId) {
		return _dictGroupTempLocalService.fetchByF_DictGroupCode(groupCode,
			groupId);
	}

	@Override
	public org.opencps.synchronization.model.DictGroupTemp fetchDictGroupTemp(
		long dictGroupId) {
		return _dictGroupTempLocalService.fetchDictGroupTemp(dictGroupId);
	}

	/**
	* Returns the dict group temp matching the UUID and group.
	*
	* @param uuid the dict group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp fetchDictGroupTempByUuidAndGroupId(
		String uuid, long groupId) {
		return _dictGroupTempLocalService.fetchDictGroupTempByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.DictGroupTemp> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return _dictGroupTempLocalService.findOlderThanDate(date, groupId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictGroupTempLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.synchronization.model.DictGroupTemp getByGC_GI_DCI(
		String groupCode, long groupId, long dictCollectionId) {
		return _dictGroupTempLocalService.getByGC_GI_DCI(groupCode, groupId,
			dictCollectionId);
	}

	/**
	* Returns the dict group temp with the primary key.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp
	* @throws PortalException if a dict group temp with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp getDictGroupTemp(
		long dictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupTempLocalService.getDictGroupTemp(dictGroupId);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.DictGroupTemp> getDictGroupTempByDictCollection(
		long groupId, long dictCollectionId, int start, int end) {
		return _dictGroupTempLocalService.getDictGroupTempByDictCollection(groupId,
			dictCollectionId, start, end);
	}

	/**
	* Returns the dict group temp matching the UUID and group.
	*
	* @param uuid the dict group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group temp
	* @throws PortalException if a matching dict group temp could not be found
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp getDictGroupTempByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupTempLocalService.getDictGroupTempByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of dict group temps
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictGroupTemp> getDictGroupTemps(
		int start, int end) {
		return _dictGroupTempLocalService.getDictGroupTemps(start, end);
	}

	/**
	* Returns all the dict group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict group temps
	* @param companyId the primary key of the company
	* @return the matching dict group temps, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictGroupTemp> getDictGroupTempsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dictGroupTempLocalService.getDictGroupTempsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dict group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict group temps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict group temps, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.DictGroupTemp> getDictGroupTempsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictGroupTemp> orderByComparator) {
		return _dictGroupTempLocalService.getDictGroupTempsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dict group temps.
	*
	* @return the number of dict group temps
	*/
	@Override
	public int getDictGroupTempsCount() {
		return _dictGroupTempLocalService.getDictGroupTempsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dictGroupTempLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictGroupTempLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dictGroupTempLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupTempLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* @author binhth
	* @param params

	<pre>
	<ol>
	<li> keywords </li>
	<li> groupId </li>
	<li> userId </li>
	<li> collectionCode </li>
	</ol>
	</pre>
	* @param sorts
	* @param start
	* @param end
	* @param searchContext
	* @throws ParseException,
	SearchException
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictGroupTempLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Updates the dict group temp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictGroupTemp the dict group temp
	* @return the dict group temp that was updated
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp updateDictGroupTemp(
		org.opencps.synchronization.model.DictGroupTemp dictGroupTemp) {
		return _dictGroupTempLocalService.updateDictGroupTemp(dictGroupTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictGroupId
	* @param dictCollectionId
	* @param groupCode
	* @param groupName
	* @param groupNameEN
	* @param groupDescription
	* @param serviceContext
	* @return DictGroupTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Override
	public org.opencps.synchronization.model.DictGroupTemp updateDictGroupTemp(
		long userId, long dictGroupId, long dictCollectionId, String groupCode,
		String groupName, String groupNameEN, String groupDescription,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return _dictGroupTempLocalService.updateDictGroupTemp(userId,
			dictGroupId, dictCollectionId, groupCode, groupName, groupNameEN,
			groupDescription, status, serviceContext);
	}

	@Override
	public DictGroupTempLocalService getWrappedService() {
		return _dictGroupTempLocalService;
	}

	@Override
	public void setWrappedService(
		DictGroupTempLocalService dictGroupTempLocalService) {
		_dictGroupTempLocalService = dictGroupTempLocalService;
	}

	private DictGroupTempLocalService _dictGroupTempLocalService;
}