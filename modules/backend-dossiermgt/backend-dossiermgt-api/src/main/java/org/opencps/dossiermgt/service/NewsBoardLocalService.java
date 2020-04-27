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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.NewsBoard;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for NewsBoard. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see NewsBoardLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.NewsBoardLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.NewsBoardLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface NewsBoardLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsBoardLocalServiceUtil} to access the news board local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.NewsBoardLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the news board to the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public NewsBoard addNewsBoard(NewsBoard newsBoard);

	public int countByNewsBoardList(long groupId);

	/**
	* Creates a new news board with the primary key. Does not add the news board to the database.
	*
	* @param newBoardId the primary key for the new news board
	* @return the new news board
	*/
	@Transactional(enabled = false)
	public NewsBoard createNewsBoard(long newBoardId);

	public NewsBoard createNewsBoard(long groupId, long userId,
		String newsTitle, String newsContent, int newsStatus,
		ServiceContext context);

	/**
	* Deletes the news board with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param newBoardId the primary key of the news board
	* @return the news board that was removed
	* @throws PortalException if a news board with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public NewsBoard deleteNewsBoard(long newBoardId) throws PortalException;

	/**
	* Deletes the news board from the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public NewsBoard deleteNewsBoard(NewsBoard newsBoard);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NewsBoard fetchNewsBoard(long newBoardId);

	/**
	* Returns the news board matching the UUID and group.
	*
	* @param uuid the news board's UUID
	* @param groupId the primary key of the group
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NewsBoard fetchNewsBoardByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the news board with the primary key.
	*
	* @param newBoardId the primary key of the news board
	* @return the news board
	* @throws PortalException if a news board with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NewsBoard getNewsBoard(long newBoardId) throws PortalException;

	/**
	* Returns the news board matching the UUID and group.
	*
	* @param uuid the news board's UUID
	* @param groupId the primary key of the group
	* @return the matching news board
	* @throws PortalException if a matching news board could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public NewsBoard getNewsBoardByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NewsBoard> getNewsBoardList(long groupId, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NewsBoard> getNewsBoards(int start, int end);

	/**
	* Returns all the news boards matching the UUID and company.
	*
	* @param uuid the UUID of the news boards
	* @param companyId the primary key of the company
	* @return the matching news boards, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NewsBoard> getNewsBoardsByUuidAndCompanyId(String uuid,
		long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<NewsBoard> getNewsBoardsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the number of news boards.
	*
	* @return the number of news boards
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNewsBoardsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public NewsBoard updateNewsBoard(long newsBoardId, long groupId,
		long userId, String newsTitle, String newsContent, int newsStatus,
		ServiceContext context);

	/**
	* Updates the news board in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param newsBoard the news board
	* @return the news board that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public NewsBoard updateNewsBoard(NewsBoard newsBoard);
}