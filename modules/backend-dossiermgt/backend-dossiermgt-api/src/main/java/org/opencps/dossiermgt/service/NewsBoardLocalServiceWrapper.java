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
 * Provides a wrapper for {@link NewsBoardLocalService}.
 *
 * @author huymq
 * @see NewsBoardLocalService
 * @generated
 */
@ProviderType
public class NewsBoardLocalServiceWrapper implements NewsBoardLocalService,
	ServiceWrapper<NewsBoardLocalService> {
	public NewsBoardLocalServiceWrapper(
		NewsBoardLocalService newsBoardLocalService) {
		_newsBoardLocalService = newsBoardLocalService;
	}

	/**
	* Adds the news board to the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard addNewsBoard(
		org.opencps.dossiermgt.model.NewsBoard newsBoard) {
		return _newsBoardLocalService.addNewsBoard(newsBoard);
	}

	@Override
	public org.opencps.dossiermgt.model.NewsBoard adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _newsBoardLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.NewsBoard adminProcessDelete(Long id)
		throws Exception {
		return _newsBoardLocalService.adminProcessDelete(id);
	}

	@Override
	public int countByNewsBoardList(long groupId) {
		return _newsBoardLocalService.countByNewsBoardList(groupId);
	}

	/**
	* Creates a new news board with the primary key. Does not add the news board to the database.
	*
	* @param newsBoardId the primary key for the new news board
	* @return the new news board
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard createNewsBoard(
		long newsBoardId) {
		return _newsBoardLocalService.createNewsBoard(newsBoardId);
	}

	@Override
	public org.opencps.dossiermgt.model.NewsBoard createNewsBoard(
		long groupId, long userId, String newsTitle, String newsContent,
		int newsStatus, com.liferay.portal.kernel.service.ServiceContext context) {
		return _newsBoardLocalService.createNewsBoard(groupId, userId,
			newsTitle, newsContent, newsStatus, context);
	}

	/**
	* Deletes the news board with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board that was removed
	* @throws PortalException if a news board with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard deleteNewsBoard(
		long newsBoardId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _newsBoardLocalService.deleteNewsBoard(newsBoardId);
	}

	/**
	* Deletes the news board from the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard deleteNewsBoard(
		org.opencps.dossiermgt.model.NewsBoard newsBoard) {
		return _newsBoardLocalService.deleteNewsBoard(newsBoard);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _newsBoardLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _newsBoardLocalService.dynamicQuery();
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
		return _newsBoardLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _newsBoardLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _newsBoardLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _newsBoardLocalService.dynamicQueryCount(dynamicQuery);
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
		return _newsBoardLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.dossiermgt.model.NewsBoard fetchNewsBoard(
		long newsBoardId) {
		return _newsBoardLocalService.fetchNewsBoard(newsBoardId);
	}

	/**
	* Returns the news board matching the UUID and group.
	*
	* @param uuid the news board's UUID
	* @param groupId the primary key of the group
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard fetchNewsBoardByUuidAndGroupId(
		String uuid, long groupId) {
		return _newsBoardLocalService.fetchNewsBoardByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _newsBoardLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _newsBoardLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _newsBoardLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the news board with the primary key.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board
	* @throws PortalException if a news board with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard getNewsBoard(long newsBoardId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _newsBoardLocalService.getNewsBoard(newsBoardId);
	}

	/**
	* Returns the news board matching the UUID and group.
	*
	* @param uuid the news board's UUID
	* @param groupId the primary key of the group
	* @return the matching news board
	* @throws PortalException if a matching news board could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard getNewsBoardByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _newsBoardLocalService.getNewsBoardByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoardList(
		long groupId, int start, int end) {
		return _newsBoardLocalService.getNewsBoardList(groupId, start, end);
	}

	/**
	* Returns a range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of news boards
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoards(
		int start, int end) {
		return _newsBoardLocalService.getNewsBoards(start, end);
	}

	/**
	* Returns all the news boards matching the UUID and company.
	*
	* @param uuid the UUID of the news boards
	* @param companyId the primary key of the company
	* @return the matching news boards, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoardsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _newsBoardLocalService.getNewsBoardsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of news boards matching the UUID and company.
	*
	* @param uuid the UUID of the news boards
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching news boards, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoardsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.NewsBoard> orderByComparator) {
		return _newsBoardLocalService.getNewsBoardsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of news boards.
	*
	* @return the number of news boards
	*/
	@Override
	public int getNewsBoardsCount() {
		return _newsBoardLocalService.getNewsBoardsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _newsBoardLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _newsBoardLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.NewsBoard updateNewsBoard(
		long newsBoardId, long groupId, long userId, String newsTitle,
		String newsContent, int newsStatus,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return _newsBoardLocalService.updateNewsBoard(newsBoardId, groupId,
			userId, newsTitle, newsContent, newsStatus, context);
	}

	/**
	* Updates the news board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.NewsBoard updateNewsBoard(
		org.opencps.dossiermgt.model.NewsBoard newsBoard) {
		return _newsBoardLocalService.updateNewsBoard(newsBoard);
	}

	@Override
	public NewsBoardLocalService getWrappedService() {
		return _newsBoardLocalService;
	}

	@Override
	public void setWrappedService(NewsBoardLocalService newsBoardLocalService) {
		_newsBoardLocalService = newsBoardLocalService;
	}

	private NewsBoardLocalService _newsBoardLocalService;
}