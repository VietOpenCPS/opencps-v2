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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for PostConnect. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.PostConnectLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see PostConnectLocalService
 * @see org.opencps.dossiermgt.service.base.PostConnectLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.PostConnectLocalServiceImpl
 * @generated
 */
@ProviderType
public class PostConnectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.PostConnectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the post connect to the database. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was added
	*/
	public static org.opencps.dossiermgt.model.PostConnect addPostConnect(
		org.opencps.dossiermgt.model.PostConnect postConnect) {
		return getService().addPostConnect(postConnect);
	}

	public static org.opencps.dossiermgt.model.PostConnect createOrUpdatePostConnect(
		long groupId, long userId, long dossierId, Integer postService,
		Integer postType, String orderNumber, Integer postStatus,
		String metaData, Integer syncState, Integer retry) {
		return getService()
				   .createOrUpdatePostConnect(groupId, userId, dossierId,
			postService, postType, orderNumber, postStatus, metaData,
			syncState, retry);
	}

	/**
	* Creates a new post connect with the primary key. Does not add the post connect to the database.
	*
	* @param postConnectId the primary key for the new post connect
	* @return the new post connect
	*/
	public static org.opencps.dossiermgt.model.PostConnect createPostConnect(
		long postConnectId) {
		return getService().createPostConnect(postConnectId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect that was removed
	* @throws PortalException if a post connect with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.PostConnect deletePostConnect(
		long postConnectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePostConnect(postConnectId);
	}

	/**
	* Deletes the post connect from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was removed
	*/
	public static org.opencps.dossiermgt.model.PostConnect deletePostConnect(
		org.opencps.dossiermgt.model.PostConnect postConnect) {
		return getService().deletePostConnect(postConnect);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.PostConnect fetchPostConnect(
		long postConnectId) {
		return getService().fetchPostConnect(postConnectId);
	}

	/**
	* Returns the post connect matching the UUID and group.
	*
	* @param uuid the post connect's UUID
	* @param groupId the primary key of the group
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public static org.opencps.dossiermgt.model.PostConnect fetchPostConnectByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchPostConnectByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.dossiermgt.model.PostConnect> getBySyncState(
		Integer syncState) {
		return getService().getBySyncState(syncState);
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

	/**
	* Returns the post connect with the primary key.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect
	* @throws PortalException if a post connect with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.PostConnect getPostConnect(
		long postConnectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPostConnect(postConnectId);
	}

	/**
	* Returns the post connect matching the UUID and group.
	*
	* @param uuid the post connect's UUID
	* @param groupId the primary key of the group
	* @return the matching post connect
	* @throws PortalException if a matching post connect could not be found
	*/
	public static org.opencps.dossiermgt.model.PostConnect getPostConnectByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPostConnectByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of post connects
	*/
	public static java.util.List<org.opencps.dossiermgt.model.PostConnect> getPostConnects(
		int start, int end) {
		return getService().getPostConnects(start, end);
	}

	/**
	* Returns all the post connects matching the UUID and company.
	*
	* @param uuid the UUID of the post connects
	* @param companyId the primary key of the company
	* @return the matching post connects, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.PostConnect> getPostConnectsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getPostConnectsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of post connects matching the UUID and company.
	*
	* @param uuid the UUID of the post connects
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching post connects, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.PostConnect> getPostConnectsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.PostConnect> orderByComparator) {
		return getService()
				   .getPostConnectsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of post connects.
	*
	* @return the number of post connects
	*/
	public static int getPostConnectsCount() {
		return getService().getPostConnectsCount();
	}

	/**
	* Updates the post connect in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was updated
	*/
	public static org.opencps.dossiermgt.model.PostConnect updatePostConnect(
		org.opencps.dossiermgt.model.PostConnect postConnect) {
		return getService().updatePostConnect(postConnect);
	}

	public static PostConnectLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PostConnectLocalService, PostConnectLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PostConnectLocalService.class);

		ServiceTracker<PostConnectLocalService, PostConnectLocalService> serviceTracker =
			new ServiceTracker<PostConnectLocalService, PostConnectLocalService>(bundle.getBundleContext(),
				PostConnectLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}