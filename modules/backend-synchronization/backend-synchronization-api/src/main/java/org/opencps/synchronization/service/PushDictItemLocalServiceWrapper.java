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
 * Provides a wrapper for {@link PushDictItemLocalService}.
 *
 * @author trungdk
 * @see PushDictItemLocalService
 * @generated
 */
@ProviderType
public class PushDictItemLocalServiceWrapper implements PushDictItemLocalService,
	ServiceWrapper<PushDictItemLocalService> {
	public PushDictItemLocalServiceWrapper(
		PushDictItemLocalService pushDictItemLocalService) {
		_pushDictItemLocalService = pushDictItemLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _pushDictItemLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pushDictItemLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _pushDictItemLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _pushDictItemLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictItemLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictItemLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of push dict items.
	*
	* @return the number of push dict items
	*/
	@Override
	public int getPushDictItemsCount() {
		return _pushDictItemLocalService.getPushDictItemsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _pushDictItemLocalService.getOSGiServiceIdentifier();
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
		return _pushDictItemLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushDictItemLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushDictItemLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictItem> findAll(
		int start, int end) {
		return _pushDictItemLocalService.findAll(start, end);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictItem> findByGroupId_ServerNo(
		long groupId, java.lang.String serverNo, int start, int end) {
		return _pushDictItemLocalService.findByGroupId_ServerNo(groupId,
			serverNo, start, end);
	}

	/**
	* Returns a range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of push dict items
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictItem> getPushDictItems(
		int start, int end) {
		return _pushDictItemLocalService.getPushDictItems(start, end);
	}

	/**
	* Returns all the push dict items matching the UUID and company.
	*
	* @param uuid the UUID of the push dict items
	* @param companyId the primary key of the company
	* @return the matching push dict items, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictItem> getPushDictItemsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _pushDictItemLocalService.getPushDictItemsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of push dict items matching the UUID and company.
	*
	* @param uuid the UUID of the push dict items
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching push dict items, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictItem> getPushDictItemsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.PushDictItem> orderByComparator) {
		return _pushDictItemLocalService.getPushDictItemsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _pushDictItemLocalService.dynamicQueryCount(dynamicQuery);
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
		return _pushDictItemLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.synchronization.model.PushDictItem addPushDictItem(
		long userId, long groupId, java.lang.String serverNo,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String itemName, java.lang.String itemNameEN,
		java.lang.String itemDescription, java.lang.String parentItemCode,
		java.lang.String sibling, java.lang.String method,
		java.lang.String metaData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException {
		return _pushDictItemLocalService.addPushDictItem(userId, groupId,
			serverNo, collectionCode, itemCode, itemName, itemNameEN,
			itemDescription, parentItemCode, sibling, method, metaData,
			serviceContext);
	}

	/**
	* Adds the push dict item to the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictItem the push dict item
	* @return the push dict item that was added
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem addPushDictItem(
		org.opencps.synchronization.model.PushDictItem pushDictItem) {
		return _pushDictItemLocalService.addPushDictItem(pushDictItem);
	}

	/**
	* Creates a new push dict item with the primary key. Does not add the push dict item to the database.
	*
	* @param pushDictItemId the primary key for the new push dict item
	* @return the new push dict item
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem createPushDictItem(
		long pushDictItemId) {
		return _pushDictItemLocalService.createPushDictItem(pushDictItemId);
	}

	/**
	* Deletes the push dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item that was removed
	* @throws PortalException if a push dict item with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem deletePushDictItem(
		long pushDictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictItemLocalService.deletePushDictItem(pushDictItemId);
	}

	@Override
	public org.opencps.synchronization.model.PushDictItem deletePushDictItem(
		long pushDictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.NotFoundException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException {
		return _pushDictItemLocalService.deletePushDictItem(pushDictItemId,
			serviceContext);
	}

	/**
	* Deletes the push dict item from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictItem the push dict item
	* @return the push dict item that was removed
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem deletePushDictItem(
		org.opencps.synchronization.model.PushDictItem pushDictItem) {
		return _pushDictItemLocalService.deletePushDictItem(pushDictItem);
	}

	@Override
	public org.opencps.synchronization.model.PushDictItem fetchPushDictItem(
		long pushDictItemId) {
		return _pushDictItemLocalService.fetchPushDictItem(pushDictItemId);
	}

	/**
	* Returns the push dict item matching the UUID and group.
	*
	* @param uuid the push dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem fetchPushDictItemByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _pushDictItemLocalService.fetchPushDictItemByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.synchronization.model.PushDictItem findByCollectionCode_ItemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String itemCode, java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return _pushDictItemLocalService.findByCollectionCode_ItemCode_Method(groupId,
			collectionCode, itemCode, method);
	}

	/**
	* Returns the push dict item with the primary key.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item
	* @throws PortalException if a push dict item with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem getPushDictItem(
		long pushDictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictItemLocalService.getPushDictItem(pushDictItemId);
	}

	/**
	* Returns the push dict item matching the UUID and group.
	*
	* @param uuid the push dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict item
	* @throws PortalException if a matching push dict item could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem getPushDictItemByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictItemLocalService.getPushDictItemByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.synchronization.model.PushDictItem updatePushDictItem(
		long userId, long groupId, long pushDictItemId,
		java.lang.String serverNo, java.lang.String collectionCode,
		java.lang.String itemCode, java.lang.String itemName,
		java.lang.String itemNameEN, java.lang.String itemDescription,
		java.lang.String parentItemCode, java.lang.String sibling,
		java.lang.String method, java.lang.String metaData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return _pushDictItemLocalService.updatePushDictItem(userId, groupId,
			pushDictItemId, serverNo, collectionCode, itemCode, itemName,
			itemNameEN, itemDescription, parentItemCode, sibling, method,
			metaData, serviceContext);
	}

	/**
	* Updates the push dict item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushDictItem the push dict item
	* @return the push dict item that was updated
	*/
	@Override
	public org.opencps.synchronization.model.PushDictItem updatePushDictItem(
		org.opencps.synchronization.model.PushDictItem pushDictItem) {
		return _pushDictItemLocalService.updatePushDictItem(pushDictItem);
	}

	@Override
	public PushDictItemLocalService getWrappedService() {
		return _pushDictItemLocalService;
	}

	@Override
	public void setWrappedService(
		PushDictItemLocalService pushDictItemLocalService) {
		_pushDictItemLocalService = pushDictItemLocalService;
	}

	private PushDictItemLocalService _pushDictItemLocalService;
}