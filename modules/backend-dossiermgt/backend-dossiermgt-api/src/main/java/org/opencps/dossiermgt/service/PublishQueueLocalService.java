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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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

import org.opencps.dossiermgt.model.PublishQueue;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for PublishQueue. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see PublishQueueLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.PublishQueueLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.PublishQueueLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PublishQueueLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PublishQueueLocalServiceUtil} to access the publish queue local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.PublishQueueLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the publish queue to the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueue the publish queue
	* @return the publish queue that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PublishQueue addPublishQueue(PublishQueue publishQueue);

	/**
	* Creates a new publish queue with the primary key. Does not add the publish queue to the database.
	*
	* @param publishQueueId the primary key for the new publish queue
	* @return the new publish queue
	*/
	@Transactional(enabled = false)
	public PublishQueue createPublishQueue(long publishQueueId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the publish queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue that was removed
	* @throws PortalException if a publish queue with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PublishQueue deletePublishQueue(long publishQueueId)
		throws PortalException;

	/**
	* Deletes the publish queue from the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueue the publish queue
	* @return the publish queue that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PublishQueue deletePublishQueue(PublishQueue publishQueue);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public PublishQueue fetchPublishQueue(long publishQueueId);

	/**
	* Returns the publish queue matching the UUID and group.
	*
	* @param uuid the publish queue's UUID
	* @param groupId the primary key of the group
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PublishQueue fetchPublishQueueByUuidAndGroupId(String uuid,
		long groupId);

	public List<PublishQueue> findByST_LT_MD(int[] statuses, Date d, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PublishQueue getByG_DID_SN(long groupId, long dossierId,
		String serverNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PublishQueue> getByG_DID_SN_NST(long groupId, long dossierId,
		String serverNo, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PublishQueue> getByG_DID_SN_ST(long groupId, long dossierId,
		String serverNo, int[] status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PublishQueue> getByStatus(int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PublishQueue> getByStatuses(int[] statuses, int start, int end);

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
	* Returns the publish queue with the primary key.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue
	* @throws PortalException if a publish queue with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PublishQueue getPublishQueue(long publishQueueId)
		throws PortalException;

	/**
	* Returns the publish queue matching the UUID and group.
	*
	* @param uuid the publish queue's UUID
	* @param groupId the primary key of the group
	* @return the matching publish queue
	* @throws PortalException if a matching publish queue could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PublishQueue getPublishQueueByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of publish queues
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PublishQueue> getPublishQueues(int start, int end);

	/**
	* Returns the number of publish queues.
	*
	* @return the number of publish queues
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPublishQueuesCount();

	@Indexable(type = IndexableType.DELETE)
	public PublishQueue removePublishQueue(long publishQueueId)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public PublishQueue updatePublishQueue(long groupId, long publishQueueId,
		int publishType, long dossierId, String serverNo, String publishData,
		int status, int retry, String messageText, String acknowlegement,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public PublishQueue updatePublishQueue(long groupId, long publishQueueId,
		long dossierId, String serverNo, int status, int retry,
		ServiceContext context) throws PortalException;

	/**
	* Updates the publish queue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param publishQueue the publish queue
	* @return the publish queue that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PublishQueue updatePublishQueue(PublishQueue publishQueue);
}