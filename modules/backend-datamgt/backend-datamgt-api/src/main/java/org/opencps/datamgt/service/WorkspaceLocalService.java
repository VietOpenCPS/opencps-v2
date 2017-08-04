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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.model.Workspace;

/**
 * Provides the local service interface for Workspace. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Binhth
 * @see WorkspaceLocalServiceUtil
 * @see org.mobilink.backend.datamgt.service.base.WorkspaceLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.impl.WorkspaceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WorkspaceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WorkspaceLocalServiceUtil} to access the workspace local service. Add custom service methods to {@link org.mobilink.backend.datamgt.service.impl.WorkspaceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public Hits luceneSearchEngine(
		LinkedHashMap<java.lang.String, java.lang.Object> params, Sort[] sorts,
		int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Returns the number of workspaces.
	*
	* @return the number of workspaces
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWorkspacesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the workspaces.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.WorkspaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @return the range of workspaces
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Workspace> getWorkspaces(int start, int end);

	/**
	* Returns all the workspaces matching the UUID and company.
	*
	* @param uuid the UUID of the workspaces
	* @param companyId the primary key of the company
	* @return the matching workspaces, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Workspace> getWorkspacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of workspaces matching the UUID and company.
	*
	* @param uuid the UUID of the workspaces
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of workspaces
	* @param end the upper bound of the range of workspaces (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching workspaces, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Workspace> getWorkspacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Workspace> orderByComparator);

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

	@Indexable(type = IndexableType.REINDEX)
	public Workspace addWorkspace(long userId, long groupId,
		java.lang.String name, int seqOrder, ServiceContext serviceContext)
		throws java.lang.Exception;

	/**
	* Adds the workspace to the database. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Workspace addWorkspace(Workspace workspace);

	/**
	* Creates a new workspace with the primary key. Does not add the workspace to the database.
	*
	* @param workspaceId the primary key for the new workspace
	* @return the new workspace
	*/
	public Workspace createWorkspace(long workspaceId);

	/**
	* Deletes the workspace with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace that was removed
	* @throws PortalException if a workspace with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Workspace deleteWorkspace(long workspaceId)
		throws PortalException;

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Indexable(type = IndexableType.DELETE)
	public Workspace deleteWorkspace(long workspaceId,
		ServiceContext serviceContext) throws java.lang.Exception;

	/**
	* Deletes the workspace from the database. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Workspace deleteWorkspace(Workspace workspace);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Workspace fetchWorkspace(long workspaceId);

	/**
	* Returns the workspace matching the UUID and group.
	*
	* @param uuid the workspace's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace, or <code>null</code> if a matching workspace could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Workspace fetchWorkspaceByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the workspace with the primary key.
	*
	* @param workspaceId the primary key of the workspace
	* @return the workspace
	* @throws PortalException if a workspace with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Workspace getWorkspace(long workspaceId) throws PortalException;

	/**
	* Returns the workspace matching the UUID and group.
	*
	* @param uuid the workspace's UUID
	* @param groupId the primary key of the group
	* @return the matching workspace
	* @throws PortalException if a matching workspace could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Workspace getWorkspaceByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Workspace updateWorkspace(long userId, long groupId,
		long workspaceId, java.lang.String name, int seqOrder,
		ServiceContext serviceContext) throws java.lang.Exception;

	/**
	* Updates the workspace in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workspace the workspace
	* @return the workspace that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Workspace updateWorkspace(Workspace workspace);
}