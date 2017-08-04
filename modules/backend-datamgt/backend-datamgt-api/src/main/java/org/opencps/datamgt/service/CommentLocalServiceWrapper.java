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
 * Provides a wrapper for {@link CommentLocalService}.
 *
 * @author Binhth
 * @see CommentLocalService
 * @generated
 */
@ProviderType
public class CommentLocalServiceWrapper implements CommentLocalService,
	ServiceWrapper<CommentLocalService> {
	public CommentLocalServiceWrapper(CommentLocalService commentLocalService) {
		_commentLocalService = commentLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _commentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _commentLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _commentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of comments.
	*
	* @return the number of comments
	*/
	@Override
	public int getCommentsCount() {
		return _commentLocalService.getCommentsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _commentLocalService.getOSGiServiceIdentifier();
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
		return _commentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _commentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of comments
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Comment> getComments(
		int start, int end) {
		return _commentLocalService.getComments(start, end);
	}

	/**
	* Returns all the comments matching the UUID and company.
	*
	* @param uuid the UUID of the comments
	* @param companyId the primary key of the company
	* @return the matching comments, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Comment> getCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _commentLocalService.getCommentsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of comments matching the UUID and company.
	*
	* @param uuid the UUID of the comments
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching comments, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.Comment> getCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.Comment> orderByComparator) {
		return _commentLocalService.getCommentsByUuidAndCompanyId(uuid,
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
		return _commentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _commentLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Adds the comment to the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment
	* @return the comment that was added
	*/
	@Override
	public org.opencps.datamgt.model.Comment addComment(
		org.opencps.datamgt.model.Comment comment) {
		return _commentLocalService.addComment(comment);
	}

	/**
	* Creates a new comment with the primary key. Does not add the comment to the database.
	*
	* @param commentId the primary key for the new comment
	* @return the new comment
	*/
	@Override
	public org.opencps.datamgt.model.Comment createComment(
		long commentId) {
		return _commentLocalService.createComment(commentId);
	}

	/**
	* Deletes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commentId the primary key of the comment
	* @return the comment that was removed
	* @throws PortalException if a comment with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Comment deleteComment(
		long commentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commentLocalService.deleteComment(commentId);
	}

	/**
	* Deletes the comment from the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment
	* @return the comment that was removed
	*/
	@Override
	public org.opencps.datamgt.model.Comment deleteComment(
		org.opencps.datamgt.model.Comment comment) {
		return _commentLocalService.deleteComment(comment);
	}

	@Override
	public org.opencps.datamgt.model.Comment fetchComment(
		long commentId) {
		return _commentLocalService.fetchComment(commentId);
	}

	/**
	* Returns the comment matching the UUID and group.
	*
	* @param uuid the comment's UUID
	* @param groupId the primary key of the group
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Comment fetchCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _commentLocalService.fetchCommentByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the comment with the primary key.
	*
	* @param commentId the primary key of the comment
	* @return the comment
	* @throws PortalException if a comment with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Comment getComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commentLocalService.getComment(commentId);
	}

	/**
	* Returns the comment matching the UUID and group.
	*
	* @param uuid the comment's UUID
	* @param groupId the primary key of the group
	* @return the matching comment
	* @throws PortalException if a matching comment could not be found
	*/
	@Override
	public org.opencps.datamgt.model.Comment getCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commentLocalService.getCommentByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param comment the comment
	* @return the comment that was updated
	*/
	@Override
	public org.opencps.datamgt.model.Comment updateComment(
		org.opencps.datamgt.model.Comment comment) {
		return _commentLocalService.updateComment(comment);
	}

	@Override
	public CommentLocalService getWrappedService() {
		return _commentLocalService;
	}

	@Override
	public void setWrappedService(CommentLocalService commentLocalService) {
		_commentLocalService = commentLocalService;
	}

	private CommentLocalService _commentLocalService;
}