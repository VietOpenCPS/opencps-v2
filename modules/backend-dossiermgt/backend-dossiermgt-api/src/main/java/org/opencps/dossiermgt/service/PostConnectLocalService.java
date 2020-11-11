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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.PostConnect;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for PostConnect. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see PostConnectLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.PostConnectLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.PostConnectLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PostConnectLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PostConnectLocalServiceUtil} to access the post connect local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.PostConnectLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the post connect to the database. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PostConnect addPostConnect(PostConnect postConnect);

	public PostConnect createOrUpdatePostConnect(long groupId, long userId,
		long dossierId, Integer postService, Integer postType,
		String orderNumber, Integer postStatus, String metaData,
		Integer syncState, Integer retry);

	/**
	* Creates a new post connect with the primary key. Does not add the post connect to the database.
	*
	* @param postConnectId the primary key for the new post connect
	* @return the new post connect
	*/
	@Transactional(enabled = false)
	public PostConnect createPostConnect(long postConnectId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect that was removed
	* @throws PortalException if a post connect with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PostConnect deletePostConnect(long postConnectId)
		throws PortalException;

	/**
	* Deletes the post connect from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PostConnect deletePostConnect(PostConnect postConnect);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public PostConnect fetchPostConnect(long postConnectId);

//	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//	public List<PostConnect> fetchPostConnectByDossierId(long dossierId)
//		throws PortalException;

	/**
	* Returns the post connect matching the UUID and group.
	*
	* @param uuid the post connect's UUID
	* @param groupId the primary key of the group
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PostConnect fetchPostConnectByUuidAndGroupId(String uuid,
		long groupId);

//	public PostConnect findByPostByDossierIdAndPostType(long groupId,
//		long dossierId, int postType) throws PortalException;
//
//	public List<PostConnect> findByPostConnectByDossierId(long groupId,
//		long dossierId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PostConnect> getBySyncState(Integer syncState);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

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

	/**
	* Returns the post connect with the primary key.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect
	* @throws PortalException if a post connect with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PostConnect getPostConnect(long postConnectId)
		throws PortalException;

	/**
	* Returns the post connect matching the UUID and group.
	*
	* @param uuid the post connect's UUID
	* @param groupId the primary key of the group
	* @return the matching post connect
	* @throws PortalException if a matching post connect could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PostConnect getPostConnectByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PostConnect findByPostByDossierIdAndPostType( long groupId, long dossierId, int postType)
			throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PostConnect> findByPostConnectByDossierId(long groupId, long dossierId)
			throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PostConnect> fetchPostConnectByDossierId(long dossierId)
			throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PostConnect> getPostConnects(int start, int end);

	/**
	* Returns all the post connects matching the UUID and company.
	*
	* @param uuid the UUID of the post connects
	* @param companyId the primary key of the company
	* @return the matching post connects, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PostConnect> getPostConnectsByUuidAndCompanyId(String uuid,
		long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PostConnect> getPostConnectsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the number of post connects.
	*
	* @return the number of post connects
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPostConnectsCount();

	/**
	* Updates the post connect in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param postConnect the post connect
	* @return the post connect that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PostConnect updatePostConnect(PostConnect postConnect);
}