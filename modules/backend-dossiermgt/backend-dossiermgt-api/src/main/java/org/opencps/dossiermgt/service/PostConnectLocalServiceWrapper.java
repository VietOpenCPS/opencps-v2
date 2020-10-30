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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PostConnectLocalService}.
 *
 * @author huymq
 * @see PostConnectLocalService
 * @generated
 */
@ProviderType
public class PostConnectLocalServiceWrapper implements PostConnectLocalService,
	ServiceWrapper<PostConnectLocalService> {
	public PostConnectLocalServiceWrapper(
		PostConnectLocalService postConnectLocalService) {
		_postConnectLocalService = postConnectLocalService;
	}

	/**
	* Adds the post connect to the database. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect addPostConnect(
		org.opencps.dossiermgt.model.PostConnect postConnect) {
		return _postConnectLocalService.addPostConnect(postConnect);
	}

	@Override
	public org.opencps.dossiermgt.model.PostConnect createOrUpdatePostConnect(
		long groupId, long userId, long dossierId, Integer postService,
		Integer postType, String orderNumber, Integer postStatus,
		String metaData, Integer syncState, Integer retry) {
		return _postConnectLocalService.createOrUpdatePostConnect(groupId,
			userId, dossierId, postService, postType, orderNumber, postStatus,
			metaData, syncState, retry);
	}

	/**
	* Creates a new post connect with the primary key. Does not add the post connect to the database.
	*
	* @param postConnectId the primary key for the new post connect
	* @return the new post connect
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect createPostConnect(
		long postConnectId) {
		return _postConnectLocalService.createPostConnect(postConnectId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect that was removed
	* @throws PortalException if a post connect with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect deletePostConnect(
		long postConnectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.deletePostConnect(postConnectId);
	}

	/**
	* Deletes the post connect from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect deletePostConnect(
		org.opencps.dossiermgt.model.PostConnect postConnect) {
		return _postConnectLocalService.deletePostConnect(postConnect);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _postConnectLocalService.dynamicQuery();
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
		return _postConnectLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _postConnectLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _postConnectLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _postConnectLocalService.dynamicQueryCount(dynamicQuery);
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
		return _postConnectLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.PostConnect fetchPostConnect(
		long postConnectId) {
		return _postConnectLocalService.fetchPostConnect(postConnectId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PostConnect> fetchPostConnectByDossierId(
		long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.fetchPostConnectByDossierId(dossierId);
	}

	/**
	* Returns the post connect matching the UUID and group.
	*
	* @param uuid the post connect's UUID
	* @param groupId the primary key of the group
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect fetchPostConnectByUuidAndGroupId(
		String uuid, long groupId) {
		return _postConnectLocalService.fetchPostConnectByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.dossiermgt.model.PostConnect findByPostByDossierIdAndPostType(
		long groupId, long dossierId, int postType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.findByPostByDossierIdAndPostType(groupId,
			dossierId, postType);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PostConnect> findByPostConnectByDossierId(
		long groupId, long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.findByPostConnectByDossierId(groupId,
			dossierId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _postConnectLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.PostConnect> getBySyncState(
		Integer syncState) {
		return _postConnectLocalService.getBySyncState(syncState);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _postConnectLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _postConnectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _postConnectLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the post connect with the primary key.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect
	* @throws PortalException if a post connect with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect getPostConnect(
		long postConnectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.getPostConnect(postConnectId);
	}

	/**
	* Returns the post connect matching the UUID and group.
	*
	* @param uuid the post connect's UUID
	* @param groupId the primary key of the group
	* @return the matching post connect
	* @throws PortalException if a matching post connect could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect getPostConnectByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _postConnectLocalService.getPostConnectByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PostConnect> getPostConnects(
		int start, int end) {
		return _postConnectLocalService.getPostConnects(start, end);
	}

	/**
	* Returns all the post connects matching the UUID and company.
	*
	* @param uuid the UUID of the post connects
	* @param companyId the primary key of the company
	* @return the matching post connects, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PostConnect> getPostConnectsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _postConnectLocalService.getPostConnectsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.PostConnect> getPostConnectsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.PostConnect> orderByComparator) {
		return _postConnectLocalService.getPostConnectsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of post connects.
	*
	* @return the number of post connects
	*/
	@Override
	public int getPostConnectsCount() {
		return _postConnectLocalService.getPostConnectsCount();
	}

	/**
	* Updates the post connect in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.PostConnect updatePostConnect(
		org.opencps.dossiermgt.model.PostConnect postConnect) {
		return _postConnectLocalService.updatePostConnect(postConnect);
	}

	@Override
	public PostConnectLocalService getWrappedService() {
		return _postConnectLocalService;
	}

	@Override
	public void setWrappedService(
		PostConnectLocalService postConnectLocalService) {
		_postConnectLocalService = postConnectLocalService;
	}

	private PostConnectLocalService _postConnectLocalService;
}