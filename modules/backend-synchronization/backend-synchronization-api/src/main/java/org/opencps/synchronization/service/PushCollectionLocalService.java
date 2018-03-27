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

import org.opencps.synchronization.exception.NoSuchPushCollectionException;
import org.opencps.synchronization.model.PushCollection;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for PushCollection. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author trungdk
 * @see PushCollectionLocalServiceUtil
 * @see org.opencps.synchronization.service.base.PushCollectionLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.PushCollectionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PushCollectionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushCollectionLocalServiceUtil} to access the push collection local service. Add custom service methods to {@link org.opencps.synchronization.service.impl.PushCollectionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Returns the number of push collections.
	*
	* @return the number of push collections
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPushCollectionsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<PushCollection> findAll(int start, int end);

	public List<PushCollection> findByGroupId_ServerNo(long groupId,
		java.lang.String serverNo, int start, int end);

	/**
	* Returns a range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of push collections
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushCollection> getPushCollections(int start, int end);

	/**
	* Returns all the push collections matching the UUID and company.
	*
	* @param uuid the UUID of the push collections
	* @param companyId the primary key of the company
	* @return the matching push collections, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushCollection> getPushCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of push collections matching the UUID and company.
	*
	* @param uuid the UUID of the push collections
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching push collections, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PushCollection> getPushCollectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<PushCollection> orderByComparator);

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

	public PushCollection addPushCollection(long userId, long groupId,
		java.lang.String serverNo, java.lang.String collectionCode,
		java.lang.String collectionName, java.lang.String collectionNameEN,
		java.lang.String description, java.lang.String method,
		java.lang.String dataForm, ServiceContext serviceContext)
		throws NoSuchUserException, SystemException, UnauthenticationException,
			UnauthorizationException;

	/**
	* Adds the push collection to the database. Also notifies the appropriate model listeners.
	*
	* @param pushCollection the push collection
	* @return the push collection that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PushCollection addPushCollection(PushCollection pushCollection);

	/**
	* Creates a new push collection with the primary key. Does not add the push collection to the database.
	*
	* @param pushCollectionId the primary key for the new push collection
	* @return the new push collection
	*/
	public PushCollection createPushCollection(long pushCollectionId);

	/**
	* Deletes the push collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection that was removed
	* @throws PortalException if a push collection with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PushCollection deletePushCollection(long pushCollectionId)
		throws PortalException;

	public PushCollection deletePushCollection(long pushCollectionId,
		ServiceContext serviceContext)
		throws NotFoundException, UnauthenticationException,
			UnauthorizationException;

	/**
	* Deletes the push collection from the database. Also notifies the appropriate model listeners.
	*
	* @param pushCollection the push collection
	* @return the push collection that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PushCollection deletePushCollection(PushCollection pushCollection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushCollection fetchPushCollection(long pushCollectionId);

	/**
	* Returns the push collection matching the UUID and group.
	*
	* @param uuid the push collection's UUID
	* @param groupId the primary key of the group
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushCollection fetchPushCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	public PushCollection findByCollectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method)
		throws NoSuchPushCollectionException;

	/**
	* Returns the push collection with the primary key.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection
	* @throws PortalException if a push collection with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushCollection getPushCollection(long pushCollectionId)
		throws PortalException;

	/**
	* Returns the push collection matching the UUID and group.
	*
	* @param uuid the push collection's UUID
	* @param groupId the primary key of the group
	* @return the matching push collection
	* @throws PortalException if a matching push collection could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PushCollection getPushCollectionByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	public PushCollection updatePushCollection(long userId, long groupId,
		long pushCollectionId, java.lang.String serverNo,
		java.lang.String collectionCode, java.lang.String collectionName,
		java.lang.String collectionNameEN, java.lang.String description,
		java.lang.String method, java.lang.String dataForm,
		ServiceContext serviceContext)
		throws NoSuchUserException, SystemException, UnauthenticationException,
			UnauthorizationException, NoSuchPushCollectionException;

	/**
	* Updates the push collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushCollection the push collection
	* @return the push collection that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PushCollection updatePushCollection(PushCollection pushCollection);
}