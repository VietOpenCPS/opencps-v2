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
 * Provides a wrapper for {@link DictItemLocalService}.
 *
 * @author khoavu
 * @see DictItemLocalService
 * @generated
 */
@ProviderType
public class DictItemLocalServiceWrapper implements DictItemLocalService,
	ServiceWrapper<DictItemLocalService> {
	public DictItemLocalServiceWrapper(
		DictItemLocalService dictItemLocalService) {
		_dictItemLocalService = dictItemLocalService;
	}

	/**
	* Adds the dict item to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was added
	*/
	@Override
	public org.opencps.datamgt.model.DictItem addDictItem(
		org.opencps.datamgt.model.DictItem dictItem) {
		return _dictItemLocalService.addDictItem(dictItem);
	}

	@Override
	public org.opencps.datamgt.model.DictItem addDictItem(long userId,
		long groupId, long dictCollectionId, String itemCode, String itemName,
		String itemNameEN, String itemDescription, long parentItemId,
		String sibling, int level, String metaData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.datamgt.exception.NoSuchDictItemException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dictItemLocalService.addDictItem(userId, groupId,
			dictCollectionId, itemCode, itemName, itemNameEN, itemDescription,
			parentItemId, sibling, level, metaData, serviceContext);
	}

	@Override
	public org.opencps.datamgt.model.DictItem adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _dictItemLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.datamgt.model.DictItem adminProcessDelete(Long id) {
		return _dictItemLocalService.adminProcessDelete(id);
	}

	@Override
	public long countByOlderThanDate(java.util.Date date, long groupId) {
		return _dictItemLocalService.countByOlderThanDate(date, groupId);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictItemLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new dict item with the primary key. Does not add the dict item to the database.
	*
	* @param dictItemId the primary key for the new dict item
	* @return the new dict item
	*/
	@Override
	public org.opencps.datamgt.model.DictItem createDictItem(long dictItemId) {
		return _dictItemLocalService.createDictItem(dictItemId);
	}

	/**
	* Deletes the dict item from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was removed
	*/
	@Override
	public org.opencps.datamgt.model.DictItem deleteDictItem(
		org.opencps.datamgt.model.DictItem dictItem) {
		return _dictItemLocalService.deleteDictItem(dictItem);
	}

	/**
	* Deletes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item that was removed
	* @throws PortalException if a dict item with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItem deleteDictItem(long dictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.deleteDictItem(dictItemId);
	}

	@Override
	public org.opencps.datamgt.model.DictItem deleteDictItem(long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _dictItemLocalService.deleteDictItem(dictItemId, serviceContext);
	}

	@Override
	public void deleteDictItem(long groupId, String itemCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		_dictItemLocalService.deleteDictItem(groupId, itemCode, serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictItemLocalService.dynamicQuery();
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
		return _dictItemLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictItemLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictItemLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dictItemLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictItemLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.datamgt.model.DictItem fetchByF_dictItemCode(
		String itemCode, long dictCollectionId, long groupId) {
		return _dictItemLocalService.fetchByF_dictItemCode(itemCode,
			dictCollectionId, groupId);
	}

	@Override
	public org.opencps.datamgt.model.DictItem fetchDictItem(long dictItemId) {
		return _dictItemLocalService.fetchDictItem(dictItemId);
	}

	/**
	* Returns the dict item matching the UUID and group.
	*
	* @param uuid the dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItem fetchDictItemByUuidAndGroupId(
		String uuid, long groupId) {
		return _dictItemLocalService.fetchDictItemByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> findByF_dictCollectionId(
		long dictCollectionId) {
		return _dictItemLocalService.findByF_dictCollectionId(dictCollectionId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> findByF_dictCollectionId(
		long dictCollectionId, int stat, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItem> comparator) {
		return _dictItemLocalService.findByF_dictCollectionId(dictCollectionId,
			stat, end, comparator);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId) {
		return _dictItemLocalService.findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId);
	}

	/**
	* @param parentItemId
	* @return
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> findByF_parentItemId(
		long parentItemId) {
		return _dictItemLocalService.findByF_parentItemId(parentItemId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItem> comparator) {
		return _dictItemLocalService.findByF_treeIndex(dictCollectionId,
			parentItemId, treeIndex, comparator);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> findByOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return _dictItemLocalService.findByOlderThanDate(date, groupId, start,
			end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictItemLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dict item with the primary key.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item
	* @throws PortalException if a dict item with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItem getDictItem(long dictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.getDictItem(dictItemId);
	}

	/**
	* Returns the dict item matching the UUID and group.
	*
	* @param uuid the dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item
	* @throws PortalException if a matching dict item could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItem getDictItemByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.getDictItemByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of dict items
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> getDictItems(
		int start, int end) {
		return _dictItemLocalService.getDictItems(start, end);
	}

	/**
	* Returns all the dict items matching the UUID and company.
	*
	* @param uuid the UUID of the dict items
	* @param companyId the primary key of the company
	* @return the matching dict items, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> getDictItemsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dictItemLocalService.getDictItemsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dict items matching the UUID and company.
	*
	* @param uuid the UUID of the dict items
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict items, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItem> getDictItemsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItem> orderByComparator) {
		return _dictItemLocalService.getDictItemsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dict items.
	*
	* @return the number of dict items
	*/
	@Override
	public int getDictItemsCount() {
		return _dictItemLocalService.getDictItemsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dictItemLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictItemLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dictItemLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean initDictItem(long groupId, long dictCollectionId) {
		return _dictItemLocalService.initDictItem(groupId, dictCollectionId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dictItemLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Updates the dict item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was updated
	*/
	@Override
	public org.opencps.datamgt.model.DictItem updateDictItem(
		org.opencps.datamgt.model.DictItem dictItem) {
		return _dictItemLocalService.updateDictItem(dictItem);
	}

	/**
	* @param userId
	* @param dictCollectionId
	* @param fullName
	* @param companyName
	* @param telNo
	* @param email
	* @param mobilinkId
	* @param userMappingId
	* @param outSide
	* @param isOrg
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.datamgt.model.DictItem updateDictItem(long userId,
		long dictItemId, long dictCollectionId, String itemCode,
		String itemName, String itemNameEN, String itemDescription,
		long parentItemId, String sibling, int level, String metaData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.updateDictItem(userId, dictItemId,
			dictCollectionId, itemCode, itemName, itemNameEN, itemDescription,
			parentItemId, sibling, level, metaData, serviceContext);
	}

	@Override
	public org.opencps.datamgt.model.DictItem updateDictItemDB(long userId,
		long groupId, long dictCollectionId, String itemCode, String itemName,
		String itemNameEN, String itemDescription, long dictItemParentId,
		Integer level, String sibling, String metadata)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return _dictItemLocalService.updateDictItemDB(userId, groupId,
			dictCollectionId, itemCode, itemName, itemNameEN, itemDescription,
			dictItemParentId, level, sibling, metadata);
	}

	@Override
	public org.opencps.datamgt.model.DictItem updateDictItemListener(
		long userId, long dictItemId, long dictCollectionId, String itemCode,
		String itemName, String itemNameEN, String itemDescription,
		long parentItemId, String sibling, int level, String metaData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		return _dictItemLocalService.updateDictItemListener(userId, dictItemId,
			dictCollectionId, itemCode, itemName, itemNameEN, itemDescription,
			parentItemId, sibling, level, metaData, serviceContext);
	}

	@Override
	public DictItemLocalService getWrappedService() {
		return _dictItemLocalService;
	}

	@Override
	public void setWrappedService(DictItemLocalService dictItemLocalService) {
		_dictItemLocalService = dictItemLocalService;
	}

	private DictItemLocalService _dictItemLocalService;
}