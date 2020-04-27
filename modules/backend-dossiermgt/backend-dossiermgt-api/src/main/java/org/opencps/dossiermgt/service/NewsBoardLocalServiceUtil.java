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
 * Provides the local service utility for NewsBoard. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.NewsBoardLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see NewsBoardLocalService
 * @see org.opencps.dossiermgt.service.base.NewsBoardLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.NewsBoardLocalServiceImpl
 * @generated
 */
@ProviderType
public class NewsBoardLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.NewsBoardLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the news board to the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was added
	*/
	public static org.opencps.dossiermgt.model.NewsBoard addNewsBoard(
		org.opencps.dossiermgt.model.NewsBoard newsBoard) {
		return getService().addNewsBoard(newsBoard);
	}

	public static int countByNewsBoardList(long groupId) {
		return getService().countByNewsBoardList(groupId);
	}

	/**
	* Creates a new news board with the primary key. Does not add the news board to the database.
	*
	* @param newBoardId the primary key for the new news board
	* @return the new news board
	*/
	public static org.opencps.dossiermgt.model.NewsBoard createNewsBoard(
		long newBoardId) {
		return getService().createNewsBoard(newBoardId);
	}

	public static org.opencps.dossiermgt.model.NewsBoard createNewsBoard(
		long groupId, long userId, String newsTitle, String newsContent,
		int newsStatus, com.liferay.portal.kernel.service.ServiceContext context) {
		return getService()
				   .createNewsBoard(groupId, userId, newsTitle, newsContent,
			newsStatus, context);
	}

	/**
	* Deletes the news board with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param newBoardId the primary key of the news board
	* @return the news board that was removed
	* @throws PortalException if a news board with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.NewsBoard deleteNewsBoard(
		long newBoardId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteNewsBoard(newBoardId);
	}

	/**
	* Deletes the news board from the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was removed
	*/
	public static org.opencps.dossiermgt.model.NewsBoard deleteNewsBoard(
		org.opencps.dossiermgt.model.NewsBoard newsBoard) {
		return getService().deleteNewsBoard(newsBoard);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.NewsBoard fetchNewsBoard(
		long newBoardId) {
		return getService().fetchNewsBoard(newBoardId);
	}

	/**
	* Returns the news board matching the UUID and group.
	*
	* @param uuid the news board's UUID
	* @param groupId the primary key of the group
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static org.opencps.dossiermgt.model.NewsBoard fetchNewsBoardByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchNewsBoardByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the news board with the primary key.
	*
	* @param newBoardId the primary key of the news board
	* @return the news board
	* @throws PortalException if a news board with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.NewsBoard getNewsBoard(
		long newBoardId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNewsBoard(newBoardId);
	}

	/**
	* Returns the news board matching the UUID and group.
	*
	* @param uuid the news board's UUID
	* @param groupId the primary key of the group
	* @return the matching news board
	* @throws PortalException if a matching news board could not be found
	*/
	public static org.opencps.dossiermgt.model.NewsBoard getNewsBoardByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getNewsBoardByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoardList(
		long groupId, int start, int end) {
		return getService().getNewsBoardList(groupId, start, end);
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
	public static java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoards(
		int start, int end) {
		return getService().getNewsBoards(start, end);
	}

	/**
	* Returns all the news boards matching the UUID and company.
	*
	* @param uuid the UUID of the news boards
	* @param companyId the primary key of the company
	* @return the matching news boards, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoardsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getNewsBoardsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.NewsBoard> getNewsBoardsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.NewsBoard> orderByComparator) {
		return getService()
				   .getNewsBoardsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of news boards.
	*
	* @return the number of news boards
	*/
	public static int getNewsBoardsCount() {
		return getService().getNewsBoardsCount();
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

	public static org.opencps.dossiermgt.model.NewsBoard updateNewsBoard(
		long newsBoardId, long groupId, long userId, String newsTitle,
		String newsContent, int newsStatus,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return getService()
				   .updateNewsBoard(newsBoardId, groupId, userId, newsTitle,
			newsContent, newsStatus, context);
	}

	/**
	* Updates the news board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was updated
	*/
	public static org.opencps.dossiermgt.model.NewsBoard updateNewsBoard(
		org.opencps.dossiermgt.model.NewsBoard newsBoard) {
		return getService().updateNewsBoard(newsBoard);
	}

	public static NewsBoardLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NewsBoardLocalService, NewsBoardLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NewsBoardLocalService.class);

		ServiceTracker<NewsBoardLocalService, NewsBoardLocalService> serviceTracker =
			new ServiceTracker<NewsBoardLocalService, NewsBoardLocalService>(bundle.getBundleContext(),
				NewsBoardLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}