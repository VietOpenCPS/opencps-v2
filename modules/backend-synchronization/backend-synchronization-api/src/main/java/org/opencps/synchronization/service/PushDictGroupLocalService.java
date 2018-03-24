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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.NoSuchUserException;
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

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

import org.opencps.synchronization.exception.NoSuchPushDictGroupException;
import org.opencps.synchronization.model.PushDictGroup;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for PushDictGroup. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author trungdk
 * @see PushDictGroupLocalServiceUtil
 * @see org.opencps.synchronization.service.base.PushDictGroupLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.PushDictGroupLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PushDictGroupLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushDictGroupLocalServiceUtil} to access the push dict group local service. Add custom service methods to {@link org.opencps.synchronization.service.impl.PushDictGroupLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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

	/**
	* Returns the number of push dict groups.
	*
	* @return the number of push dict groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPushDictGroupsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<PushDictGroup> findAll(int start, int end);

	public List<PushDictGroup> findByGroupId_ServerNo(long groupId,
		java.lang.String serverNo, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushDictGroup> getPushDictGroups(int start, int end);

	/**
	* Returns all the push dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the push dict groups
	* @param companyId the primary key of the company
	* @return the matching push dict groups, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushDictGroup> getPushDictGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushDictGroup> getPushDictGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<PushDictGroup> orderByComparator);

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

	public PushDictGroup addPushDictGroup(long userId, long groupId,
		java.lang.String serverNo, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String groupName,
		java.lang.String groupNameEN, java.lang.String groupDescription,
		java.lang.String itemCode, java.lang.String method,
		ServiceContext serviceContext)
		throws NoSuchUserException, SystemException, UnauthenticationException,
			UnauthorizationException;

	/**
	* Adds the push dict group to the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PushDictGroup addPushDictGroup(PushDictGroup pushDictGroup);

	/**
	* Creates a new push dict group with the primary key. Does not add the push dict group to the database.
	*
	* @param pushDictGroupId the primary key for the new push dict group
	* @return the new push dict group
	*/
	public PushDictGroup createPushDictGroup(long pushDictGroupId);

	/**
	* Deletes the push dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group that was removed
	* @throws PortalException if a push dict group with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PushDictGroup deletePushDictGroup(long pushDictGroupId)
		throws PortalException;

	public PushDictGroup deletePushDictGroup(long pushDictGroupId,
		ServiceContext serviceContext)
		throws NotFoundException, UnauthenticationException,
			UnauthorizationException;

	/**
	* Deletes the push dict group from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PushDictGroup deletePushDictGroup(PushDictGroup pushDictGroup);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushDictGroup fetchPushDictGroup(long pushDictGroupId);

	/**
	* Returns the push dict group matching the UUID and group.
	*
	* @param uuid the push dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushDictGroup fetchPushDictGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	public PushDictGroup findByCollectionCode_GroupCode_ItemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String itemCode,
		java.lang.String method) throws NoSuchPushDictGroupException;

	public PushDictGroup findByCollectionCode_GroupCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String groupCode,
		java.lang.String method) throws NoSuchPushDictGroupException;

	/**
	* Returns the push dict group with the primary key.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group
	* @throws PortalException if a push dict group with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushDictGroup getPushDictGroup(long pushDictGroupId)
		throws PortalException;

	/**
	* Returns the push dict group matching the UUID and group.
	*
	* @param uuid the push dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict group
	* @throws PortalException if a matching push dict group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushDictGroup getPushDictGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	public PushDictGroup updatePushDictGroup(long userId, long groupId,
		long pushDictGroupId, java.lang.String serverNo,
		java.lang.String collectionCode, java.lang.String groupCode,
		java.lang.String groupName, java.lang.String groupNameEN,
		java.lang.String groupDescription, java.lang.String itemCode,
		java.lang.String method, ServiceContext serviceContext)
		throws NoSuchUserException, SystemException, UnauthenticationException,
			UnauthorizationException, NoSuchPushDictGroupException;

	/**
	* Updates the push dict group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PushDictGroup updatePushDictGroup(PushDictGroup pushDictGroup);
}