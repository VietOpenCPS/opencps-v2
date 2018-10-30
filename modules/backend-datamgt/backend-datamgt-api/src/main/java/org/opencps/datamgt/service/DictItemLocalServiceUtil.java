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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DictItem. This utility wraps
 * {@link org.opencps.datamgt.service.impl.DictItemLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see DictItemLocalService
 * @see org.opencps.datamgt.service.base.DictItemLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictItemLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictItemLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dict item to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was added
	*/
	public static org.opencps.datamgt.model.DictItem addDictItem(
		org.opencps.datamgt.model.DictItem dictItem) {
		return getService().addDictItem(dictItem);
	}

	public static org.opencps.datamgt.model.DictItem addDictItem(long userId,
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
		return getService()
				   .addDictItem(userId, groupId, dictCollectionId, itemCode,
			itemName, itemNameEN, itemDescription, parentItemId, sibling,
			level, metaData, serviceContext);
	}

	public static org.opencps.datamgt.model.DictItem adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.datamgt.model.DictItem adminProcessDelete(Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countByOlderThanDate(java.util.Date date, long groupId) {
		return getService().countByOlderThanDate(date, groupId);
	}

	public static long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLuceneSearchEngine(params, searchContext);
	}

	/**
	* Creates a new dict item with the primary key. Does not add the dict item to the database.
	*
	* @param dictItemId the primary key for the new dict item
	* @return the new dict item
	*/
	public static org.opencps.datamgt.model.DictItem createDictItem(
		long dictItemId) {
		return getService().createDictItem(dictItemId);
	}

	/**
	* Deletes the dict item from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was removed
	*/
	public static org.opencps.datamgt.model.DictItem deleteDictItem(
		org.opencps.datamgt.model.DictItem dictItem) {
		return getService().deleteDictItem(dictItem);
	}

	/**
	* Deletes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item that was removed
	* @throws PortalException if a dict item with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictItem deleteDictItem(
		long dictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictItem(dictItemId);
	}

	public static org.opencps.datamgt.model.DictItem deleteDictItem(
		long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService().deleteDictItem(dictItemId, serviceContext);
	}

	public static void deleteDictItem(long groupId, String itemCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		getService().deleteDictItem(groupId, itemCode, serviceContext);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.datamgt.model.DictItem fetchByF_dictItemCode(
		String itemCode, long dictCollectionId, long groupId) {
		return getService()
				   .fetchByF_dictItemCode(itemCode, dictCollectionId, groupId);
	}

	public static org.opencps.datamgt.model.DictItem fetchDictItem(
		long dictItemId) {
		return getService().fetchDictItem(dictItemId);
	}

	/**
	* Returns the dict item matching the UUID and group.
	*
	* @param uuid the dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static org.opencps.datamgt.model.DictItem fetchDictItemByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDictItemByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItem> findByF_dictCollectionId(
		long dictCollectionId) {
		return getService().findByF_dictCollectionId(dictCollectionId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItem> findByF_dictCollectionId(
		long dictCollectionId, int stat, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItem> comparator) {
		return getService()
				   .findByF_dictCollectionId(dictCollectionId, stat, end,
			comparator);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId) {
		return getService()
				   .findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId);
	}

	/**
	* @param parentItemId
	* @return
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItem> findByF_parentItemId(
		long parentItemId) {
		return getService().findByF_parentItemId(parentItemId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItem> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItem> comparator) {
		return getService()
				   .findByF_treeIndex(dictCollectionId, parentItemId,
			treeIndex, comparator);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItem> findByOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return getService().findByOlderThanDate(date, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dict item with the primary key.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item
	* @throws PortalException if a dict item with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictItem getDictItem(
		long dictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItem(dictItemId);
	}

	/**
	* Returns the dict item matching the UUID and group.
	*
	* @param uuid the dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item
	* @throws PortalException if a matching dict item could not be found
	*/
	public static org.opencps.datamgt.model.DictItem getDictItemByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.datamgt.model.DictItem> getDictItems(
		int start, int end) {
		return getService().getDictItems(start, end);
	}

	/**
	* Returns all the dict items matching the UUID and company.
	*
	* @param uuid the UUID of the dict items
	* @param companyId the primary key of the company
	* @return the matching dict items, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItem> getDictItemsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getDictItemsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.datamgt.model.DictItem> getDictItemsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItem> orderByComparator) {
		return getService()
				   .getDictItemsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of dict items.
	*
	* @return the number of dict items
	*/
	public static int getDictItemsCount() {
		return getService().getDictItemsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean initDictItem(long groupId, long dictCollectionId) {
		return getService().initDictItem(groupId, dictCollectionId);
	}

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	/**
	* Updates the dict item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was updated
	*/
	public static org.opencps.datamgt.model.DictItem updateDictItem(
		org.opencps.datamgt.model.DictItem dictItem) {
		return getService().updateDictItem(dictItem);
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
	public static org.opencps.datamgt.model.DictItem updateDictItem(
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
		return getService()
				   .updateDictItem(userId, dictItemId, dictCollectionId,
			itemCode, itemName, itemNameEN, itemDescription, parentItemId,
			sibling, level, metaData, serviceContext);
	}

	public static org.opencps.datamgt.model.DictItem updateDictItemDB(
		long userId, long groupId, long dictCollectionId, String itemCode,
		String itemName, String itemNameEN, String itemDescription,
		long dictItemParentId, Integer level, String sibling, String metadata)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateDictItemDB(userId, groupId, dictCollectionId,
			itemCode, itemName, itemNameEN, itemDescription, dictItemParentId,
			level, sibling, metadata);
	}

	public static org.opencps.datamgt.model.DictItem updateDictItemListener(
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
		return getService()
				   .updateDictItemListener(userId, dictItemId,
			dictCollectionId, itemCode, itemName, itemNameEN, itemDescription,
			parentItemId, sibling, level, metaData, serviceContext);
	}

	public static DictItemLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemLocalService, DictItemLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemLocalService.class);

		ServiceTracker<DictItemLocalService, DictItemLocalService> serviceTracker =
			new ServiceTracker<DictItemLocalService, DictItemLocalService>(bundle.getBundleContext(),
				DictItemLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}