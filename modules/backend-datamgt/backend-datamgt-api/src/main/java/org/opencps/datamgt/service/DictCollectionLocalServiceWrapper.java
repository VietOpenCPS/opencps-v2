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
 * Provides a wrapper for {@link DictCollectionLocalService}.
 *
 * @author khoavu
 * @see DictCollectionLocalService
 * @generated
 */
@ProviderType
public class DictCollectionLocalServiceWrapper
	implements DictCollectionLocalService,
		ServiceWrapper<DictCollectionLocalService> {
	public DictCollectionLocalServiceWrapper(
		DictCollectionLocalService dictCollectionLocalService) {
		_dictCollectionLocalService = dictCollectionLocalService;
	}

	@Override
	public org.opencps.datamgt.model.DictCollection active(
		long dictCollectionId) {
		return _dictCollectionLocalService.active(dictCollectionId);
	}

	/**
	* Adds the dict collection to the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollection the dict collection
	* @return the dict collection that was added
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection addDictCollection(
		org.opencps.datamgt.model.DictCollection dictCollection) {
		return _dictCollectionLocalService.addDictCollection(dictCollection);
	}

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param collectionCode
	* @param collectionName
	* @param collectionNameEN
	* @param description
	* @param serviceContext
	* @return DictCollection
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection addDictCollection(
		long userId, long groupId, String collectionCode,
		String collectionName, String collectionNameEN, String description,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _dictCollectionLocalService.addDictCollection(userId, groupId,
			collectionCode, collectionName, collectionNameEN, description,
			serviceContext);
	}

	@Override
	public org.opencps.datamgt.model.DictCollection changeStatus(
		long dictCollectionId, int status) {
		return _dictCollectionLocalService.changeStatus(dictCollectionId, status);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictCollectionLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	@Override
	public long countOlderThanDate(java.util.Date date, long groupId) {
		return _dictCollectionLocalService.countOlderThanDate(date, groupId);
	}

	/**
	* Creates a new dict collection with the primary key. Does not add the dict collection to the database.
	*
	* @param dictCollectionId the primary key for the new dict collection
	* @return the new dict collection
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection createDictCollection(
		long dictCollectionId) {
		return _dictCollectionLocalService.createDictCollection(dictCollectionId);
	}

	/**
	* Deletes the dict collection from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollection the dict collection
	* @return the dict collection that was removed
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection deleteDictCollection(
		org.opencps.datamgt.model.DictCollection dictCollection) {
		return _dictCollectionLocalService.deleteDictCollection(dictCollection);
	}

	/**
	* Deletes the dict collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection that was removed
	* @throws PortalException if a dict collection with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection deleteDictCollection(
		long dictCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictCollectionLocalService.deleteDictCollection(dictCollectionId);
	}

	/**
	* @author binhth
	* @param dictCollectionId
	* @param serviceContext
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection deleteDictCollection(
		long dictCollectionId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictCollectionLocalService.deleteDictCollection(dictCollectionId,
			serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictCollectionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictCollectionLocalService.dynamicQuery();
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
		return _dictCollectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictCollectionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictCollectionLocalService.dynamicQuery(dynamicQuery, start,
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
		return _dictCollectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictCollectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* @author binhth
	* @param collectionCode
	* @param groupId
	* @return DictCollection
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection fetchByF_dictCollectionCode(
		String collectionCode, long groupId) {
		return _dictCollectionLocalService.fetchByF_dictCollectionCode(collectionCode,
			groupId);
	}

	@Override
	public org.opencps.datamgt.model.DictCollection fetchDictCollection(
		long dictCollectionId) {
		return _dictCollectionLocalService.fetchDictCollection(dictCollectionId);
	}

	/**
	* Returns the dict collection matching the UUID and group.
	*
	* @param uuid the dict collection's UUID
	* @param groupId the primary key of the group
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection fetchDictCollectionByUuidAndGroupId(
		String uuid, long groupId) {
		return _dictCollectionLocalService.fetchDictCollectionByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictCollection> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return _dictCollectionLocalService.findOlderThanDate(date, groupId,
			start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictCollectionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dict collection with the primary key.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection
	* @throws PortalException if a dict collection with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection getDictCollection(
		long dictCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictCollectionLocalService.getDictCollection(dictCollectionId);
	}

	/**
	* Returns the dict collection matching the UUID and group.
	*
	* @param uuid the dict collection's UUID
	* @param groupId the primary key of the group
	* @return the matching dict collection
	* @throws PortalException if a matching dict collection could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection getDictCollectionByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictCollectionLocalService.getDictCollectionByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of dict collections
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictCollection> getDictCollections(
		int start, int end) {
		return _dictCollectionLocalService.getDictCollections(start, end);
	}

	/**
	* Returns all the dict collections matching the UUID and company.
	*
	* @param uuid the UUID of the dict collections
	* @param companyId the primary key of the company
	* @return the matching dict collections, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictCollection> getDictCollectionsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dictCollectionLocalService.getDictCollectionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dict collections matching the UUID and company.
	*
	* @param uuid the UUID of the dict collections
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict collections, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictCollection> getDictCollectionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictCollection> orderByComparator) {
		return _dictCollectionLocalService.getDictCollectionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dict collections.
	*
	* @return the number of dict collections
	*/
	@Override
	public int getDictCollectionsCount() {
		return _dictCollectionLocalService.getDictCollectionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dictCollectionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictCollectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dictCollectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictCollectionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.datamgt.model.DictCollection inactive(
		long dictCollectionId) {
		return _dictCollectionLocalService.inactive(dictCollectionId);
	}

	/**
	* @author binhth
	* @param groupId
	* @return true if dict collection not found, init Record, false not thing todo
	*/
	@Override
	public boolean initDictCollection(long groupId) {
		return _dictCollectionLocalService.initDictCollection(groupId);
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
		return _dictCollectionLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Updates the dict collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictCollection the dict collection
	* @return the dict collection that was updated
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection updateDictCollection(
		org.opencps.datamgt.model.DictCollection dictCollection) {
		return _dictCollectionLocalService.updateDictCollection(dictCollection);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictCollectionId
	* @param collectionCode
	* @param collectionName
	* @param collectionNameEN
	* @param description
	* @param serviceContext
	* @return DictCollection
	*/
	@Override
	public org.opencps.datamgt.model.DictCollection updateDictCollection(
		long userId, long dictCollectionId, String collectionCode,
		String collectionName, String collectionNameEN, String description,
		String dataForm,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return _dictCollectionLocalService.updateDictCollection(userId,
			dictCollectionId, collectionCode, collectionName, collectionNameEN,
			description, dataForm, serviceContext);
	}

	@Override
	public org.opencps.datamgt.model.DictCollection updateDictCollectionDB(
		long userId, long groupId, String collectionCode,
		String collectionName, String collectionNameEN, String description,
		Integer status)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return _dictCollectionLocalService.updateDictCollectionDB(userId,
			groupId, collectionCode, collectionName, collectionNameEN,
			description, status);
	}

	@Override
	public DictCollectionLocalService getWrappedService() {
		return _dictCollectionLocalService;
	}

	@Override
	public void setWrappedService(
		DictCollectionLocalService dictCollectionLocalService) {
		_dictCollectionLocalService = dictCollectionLocalService;
	}

	private DictCollectionLocalService _dictCollectionLocalService;
}