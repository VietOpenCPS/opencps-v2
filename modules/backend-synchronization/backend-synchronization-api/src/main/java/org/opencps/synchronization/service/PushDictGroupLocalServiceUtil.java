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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for PushDictGroup. This utility wraps
 * {@link org.opencps.synchronization.service.impl.PushDictGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author trungdk
 * @see PushDictGroupLocalService
 * @see org.opencps.synchronization.service.base.PushDictGroupLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.PushDictGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class PushDictGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.synchronization.service.impl.PushDictGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of push dict groups.
	*
	* @return the number of push dict groups
	*/
	public static int getPushDictGroupsCount() {
		return getService().getPushDictGroupsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<org.opencps.synchronization.model.PushDictGroup> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}

	public static java.util.List<org.opencps.synchronization.model.PushDictGroup> findByGroupId_ServerNo(
		long groupId, java.lang.String serverNo, int start, int end) {
		return getService().findByGroupId_ServerNo(groupId, serverNo, start, end);
	}

	/**
	* Returns a range of all the push dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @return the range of push dict groups
	*/
	public static java.util.List<org.opencps.synchronization.model.PushDictGroup> getPushDictGroups(
		int start, int end) {
		return getService().getPushDictGroups(start, end);
	}

	/**
	* Returns all the push dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the push dict groups
	* @param companyId the primary key of the company
	* @return the matching push dict groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.PushDictGroup> getPushDictGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPushDictGroupsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of push dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the push dict groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching push dict groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.PushDictGroup> getPushDictGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.PushDictGroup> orderByComparator) {
		return getService()
				   .getPushDictGroupsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	public static org.opencps.synchronization.model.PushDictGroup addPushDictGroup(
		long userId, long groupId, java.lang.String serverNo,
		java.lang.String collectionCode, java.lang.String groupCode,
		java.lang.String groupName, java.lang.String groupNameEN,
		java.lang.String groupDescription, java.lang.String itemCode,
		java.lang.String method,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException {
		return getService()
				   .addPushDictGroup(userId, groupId, serverNo, collectionCode,
			groupCode, groupName, groupNameEN, groupDescription, itemCode,
			method, serviceContext);
	}

	/**
	* Adds the push dict group to the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was added
	*/
	public static org.opencps.synchronization.model.PushDictGroup addPushDictGroup(
		org.opencps.synchronization.model.PushDictGroup pushDictGroup) {
		return getService().addPushDictGroup(pushDictGroup);
	}

	/**
	* Creates a new push dict group with the primary key. Does not add the push dict group to the database.
	*
	* @param pushDictGroupId the primary key for the new push dict group
	* @return the new push dict group
	*/
	public static org.opencps.synchronization.model.PushDictGroup createPushDictGroup(
		long pushDictGroupId) {
		return getService().createPushDictGroup(pushDictGroupId);
	}

	/**
	* Deletes the push dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group that was removed
	* @throws PortalException if a push dict group with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.PushDictGroup deletePushDictGroup(
		long pushDictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePushDictGroup(pushDictGroupId);
	}

	public static org.opencps.synchronization.model.PushDictGroup deletePushDictGroup(
		long pushDictGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.NotFoundException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException {
		return getService().deletePushDictGroup(pushDictGroupId, serviceContext);
	}

	/**
	* Deletes the push dict group from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was removed
	*/
	public static org.opencps.synchronization.model.PushDictGroup deletePushDictGroup(
		org.opencps.synchronization.model.PushDictGroup pushDictGroup) {
		return getService().deletePushDictGroup(pushDictGroup);
	}

	public static org.opencps.synchronization.model.PushDictGroup fetchPushDictGroup(
		long pushDictGroupId) {
		return getService().fetchPushDictGroup(pushDictGroupId);
	}

	/**
	* Returns the push dict group matching the UUID and group.
	*
	* @param uuid the push dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public static org.opencps.synchronization.model.PushDictGroup fetchPushDictGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPushDictGroupByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.synchronization.model.PushDictGroup findByCollectionCode_GroupCode_ItemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String itemCode,
		java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictGroupException {
		return getService()
				   .findByCollectionCode_GroupCode_ItemCode_Method(groupId,
			collectionCode, groupCode, itemCode, method);
	}

	public static org.opencps.synchronization.model.PushDictGroup findByCollectionCode_GroupCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictGroupException {
		return getService()
				   .findByCollectionCode_GroupCode_Method(groupId,
			collectionCode, groupCode, method);
	}

	/**
	* Returns the push dict group with the primary key.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group
	* @throws PortalException if a push dict group with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.PushDictGroup getPushDictGroup(
		long pushDictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPushDictGroup(pushDictGroupId);
	}

	/**
	* Returns the push dict group matching the UUID and group.
	*
	* @param uuid the push dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict group
	* @throws PortalException if a matching push dict group could not be found
	*/
	public static org.opencps.synchronization.model.PushDictGroup getPushDictGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPushDictGroupByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.synchronization.model.PushDictGroup updatePushDictGroup(
		long userId, long groupId, long pushDictGroupId,
		java.lang.String serverNo, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String groupName,
		java.lang.String groupNameEN, java.lang.String groupDescription,
		java.lang.String itemCode, java.lang.String method,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.synchronization.exception.NoSuchPushDictGroupException {
		return getService()
				   .updatePushDictGroup(userId, groupId, pushDictGroupId,
			serverNo, collectionCode, groupCode, groupName, groupNameEN,
			groupDescription, itemCode, method, serviceContext);
	}

	/**
	* Updates the push dict group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was updated
	*/
	public static org.opencps.synchronization.model.PushDictGroup updatePushDictGroup(
		org.opencps.synchronization.model.PushDictGroup pushDictGroup) {
		return getService().updatePushDictGroup(pushDictGroup);
	}

	public static PushDictGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PushDictGroupLocalService, PushDictGroupLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PushDictGroupLocalService.class);
}