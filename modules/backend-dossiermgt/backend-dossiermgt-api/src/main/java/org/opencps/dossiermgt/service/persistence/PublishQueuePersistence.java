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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchPublishQueueException;
import org.opencps.dossiermgt.model.PublishQueue;

import java.util.Date;

/**
 * The persistence interface for the publish queue service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.PublishQueuePersistenceImpl
 * @see PublishQueueUtil
 * @generated
 */
@ProviderType
public interface PublishQueuePersistence extends BasePersistence<PublishQueue> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PublishQueueUtil} to access the publish queue persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the publish queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByUuid(String uuid);

	/**
	* Returns a range of all the publish queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the publish queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where uuid = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByUuid_PrevAndNext(long publishQueueId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Removes all the publish queues where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of publish queues where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching publish queues
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the publish queue where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPublishQueueException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByUUID_G(String uuid, long groupId)
		throws NoSuchPublishQueueException;

	/**
	* Returns the publish queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the publish queue where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the publish queue where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the publish queue that was removed
	*/
	public PublishQueue removeByUUID_G(String uuid, long groupId)
		throws NoSuchPublishQueueException;

	/**
	* Returns the number of publish queues where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching publish queues
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the publish queues where status = &#63;.
	*
	* @param status the status
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByST(int status);

	/**
	* Returns a range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST(int status, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByST_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByST_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByST_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByST_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where status = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByST_PrevAndNext(long publishQueueId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Removes all the publish queues where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByST(int status);

	/**
	* Returns the number of publish queues where status = &#63;.
	*
	* @param status the status
	* @return the number of matching publish queues
	*/
	public int countByST(int status);

	/**
	* Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or throws a {@link NoSuchPublishQueueException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByG_DID_SN(long groupId, long dossierId,
		String serverNo) throws NoSuchPublishQueueException;

	/**
	* Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByG_DID_SN(long groupId, long dossierId,
		String serverNo);

	/**
	* Returns the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByG_DID_SN(long groupId, long dossierId,
		String serverNo, boolean retrieveFromCache);

	/**
	* Removes the publish queue where groupId = &#63; and dossierId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the publish queue that was removed
	*/
	public PublishQueue removeByG_DID_SN(long groupId, long dossierId,
		String serverNo) throws NoSuchPublishQueueException;

	/**
	* Returns the number of publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countByG_DID_SN(long groupId, long dossierId, String serverNo);

	/**
	* Returns all the publish queues where dossierId = &#63; and serverNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN(long dossierId,
		String serverNo);

	/**
	* Returns a range of all the publish queues where dossierId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN(long dossierId,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where dossierId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN(long dossierId,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where dossierId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN(long dossierId,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where dossierId = &#63; and serverNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByDID_SN_First(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where dossierId = &#63; and serverNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByDID_SN_First(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where dossierId = &#63; and serverNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByDID_SN_Last(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where dossierId = &#63; and serverNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByDID_SN_Last(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where dossierId = &#63; and serverNo = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByDID_SN_PrevAndNext(long publishQueueId,
		long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Removes all the publish queues where dossierId = &#63; and serverNo = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	*/
	public void removeByDID_SN(long dossierId, String serverNo);

	/**
	* Returns the number of publish queues where dossierId = &#63; and serverNo = &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countByDID_SN(long dossierId, String serverNo);

	/**
	* Returns all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_NST(long groupId,
		long dossierId, String serverNo, int status);

	/**
	* Returns a range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_NST(long groupId,
		long dossierId, String serverNo, int status, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_NST(long groupId,
		long dossierId, String serverNo, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_NST(long groupId,
		long dossierId, String serverNo, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByG_DID_SN_NST_First(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByG_DID_SN_NST_First(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByG_DID_SN_NST_Last(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByG_DID_SN_NST_Last(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByG_DID_SN_NST_PrevAndNext(long publishQueueId,
		long groupId, long dossierId, String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Removes all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	*/
	public void removeByG_DID_SN_NST(long groupId, long dossierId,
		String serverNo, int status);

	/**
	* Returns the number of publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @return the number of matching publish queues
	*/
	public int countByG_DID_SN_NST(long groupId, long dossierId,
		String serverNo, int status);

	/**
	* Returns all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int status);

	/**
	* Returns a range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int status, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByG_DID_SN_ST_First(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByG_DID_SN_ST_First(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByG_DID_SN_ST_Last(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByG_DID_SN_ST_Last(long groupId, long dossierId,
		String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByG_DID_SN_ST_PrevAndNext(long publishQueueId,
		long groupId, long dossierId, String serverNo, int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param statuses the statuses
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int[] statuses);

	/**
	* Returns a range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param statuses the statuses
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param statuses the statuses
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByG_DID_SN_ST(long groupId,
		long dossierId, String serverNo, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	*/
	public void removeByG_DID_SN_ST(long groupId, long dossierId,
		String serverNo, int status);

	/**
	* Returns the number of publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param status the status
	* @return the number of matching publish queues
	*/
	public int countByG_DID_SN_ST(long groupId, long dossierId,
		String serverNo, int status);

	/**
	* Returns the number of publish queues where groupId = &#63; and dossierId = &#63; and serverNo = &#63; and status = any &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param statuses the statuses
	* @return the number of matching publish queues
	*/
	public int countByG_DID_SN_ST(long groupId, long dossierId,
		String serverNo, int[] statuses);

	/**
	* Returns all the publish queues where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int status,
		Date modifiedDate);

	/**
	* Returns a range of all the publish queues where status = &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int status,
		Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int status,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int status,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByST_LT_MD_First(int status, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByST_LT_MD_First(int status, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByST_LT_MD_Last(int status, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByST_LT_MD_Last(int status, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param status the status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByST_LT_MD_PrevAndNext(long publishQueueId,
		int status, Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns all the publish queues where status = any &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param modifiedDate the modified date
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int[] statuses,
		Date modifiedDate);

	/**
	* Returns a range of all the publish queues where status = any &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int[] statuses,
		Date modifiedDate, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = any &#63; and modifiedDate &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int[] statuses,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and modifiedDate &le; &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByST_LT_MD(int[] statuses,
		Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publish queues where status = &#63; and modifiedDate &le; &#63; from the database.
	*
	* @param status the status
	* @param modifiedDate the modified date
	*/
	public void removeByST_LT_MD(int status, Date modifiedDate);

	/**
	* Returns the number of publish queues where status = &#63; and modifiedDate &le; &#63;.
	*
	* @param status the status
	* @param modifiedDate the modified date
	* @return the number of matching publish queues
	*/
	public int countByST_LT_MD(int status, Date modifiedDate);

	/**
	* Returns the number of publish queues where status = any &#63; and modifiedDate &le; &#63;.
	*
	* @param statuses the statuses
	* @param modifiedDate the modified date
	* @return the number of matching publish queues
	*/
	public int countByST_LT_MD(int[] statuses, Date modifiedDate);

	/**
	* Returns all the publish queues where status = &#63;.
	*
	* @param status the status
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int status);

	/**
	* Returns a range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int status, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findBySTS_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchBySTS_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findBySTS_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchBySTS_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where status = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findBySTS_PrevAndNext(long publishQueueId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns all the publish queues where status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int[] statuses);

	/**
	* Returns a range of all the publish queues where status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int[] statuses, int start,
		int end);

	/**
	* Returns an ordered range of all the publish queues where status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS(int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publish queues where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeBySTS(int status);

	/**
	* Returns the number of publish queues where status = &#63;.
	*
	* @param status the status
	* @return the number of matching publish queues
	*/
	public int countBySTS(int status);

	/**
	* Returns the number of publish queues where status = any &#63;.
	*
	* @param statuses the statuses
	* @return the number of matching publish queues
	*/
	public int countBySTS(int[] statuses);

	/**
	* Returns all the publish queues where status = &#63; and serverNo &ne; &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int status,
		String serverNo);

	/**
	* Returns a range of all the publish queues where status = &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int status,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int status,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int status,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where status = &#63; and serverNo &ne; &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findBySTS_SN_NOT_First(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where status = &#63; and serverNo &ne; &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchBySTS_SN_NOT_First(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where status = &#63; and serverNo &ne; &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findBySTS_SN_NOT_Last(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where status = &#63; and serverNo &ne; &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchBySTS_SN_NOT_Last(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where status = &#63; and serverNo &ne; &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findBySTS_SN_NOT_PrevAndNext(long publishQueueId,
		int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns all the publish queues where status = any &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int[] statuses,
		String serverNo);

	/**
	* Returns a range of all the publish queues where status = any &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int[] statuses,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = any &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int[] statuses,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and serverNo &ne; &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN_NOT(int[] statuses,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publish queues where status = &#63; and serverNo &ne; &#63; from the database.
	*
	* @param status the status
	* @param serverNo the server no
	*/
	public void removeBySTS_SN_NOT(int status, String serverNo);

	/**
	* Returns the number of publish queues where status = &#63; and serverNo &ne; &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countBySTS_SN_NOT(int status, String serverNo);

	/**
	* Returns the number of publish queues where status = any &#63; and serverNo &ne; &#63;.
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countBySTS_SN_NOT(int[] statuses, String serverNo);

	/**
	* Returns all the publish queues where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN_NOT(long dossierId,
		String serverNo);

	/**
	* Returns a range of all the publish queues where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN_NOT(long dossierId,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN_NOT(long dossierId,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findByDID_SN_NOT(long dossierId,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByDID_SN_NOT_First(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByDID_SN_NOT_First(long dossierId,
		String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findByDID_SN_NOT_Last(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchByDID_SN_NOT_Last(long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findByDID_SN_NOT_PrevAndNext(long publishQueueId,
		long dossierId, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Removes all the publish queues where dossierId = &#63; and serverNo &ne; &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	*/
	public void removeByDID_SN_NOT(long dossierId, String serverNo);

	/**
	* Returns the number of publish queues where dossierId = &#63; and serverNo &ne; &#63;.
	*
	* @param dossierId the dossier ID
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countByDID_SN_NOT(long dossierId, String serverNo);

	/**
	* Returns all the publish queues where status = &#63; and serverNo = &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int status, String serverNo);

	/**
	* Returns a range of all the publish queues where status = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int status,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int status,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int status,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publish queue in the ordered set where status = &#63; and serverNo = &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findBySTS_SN_First(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the first publish queue in the ordered set where status = &#63; and serverNo = &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchBySTS_SN_First(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the last publish queue in the ordered set where status = &#63; and serverNo = &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue
	* @throws NoSuchPublishQueueException if a matching publish queue could not be found
	*/
	public PublishQueue findBySTS_SN_Last(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns the last publish queue in the ordered set where status = &#63; and serverNo = &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publish queue, or <code>null</code> if a matching publish queue could not be found
	*/
	public PublishQueue fetchBySTS_SN_Last(int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns the publish queues before and after the current publish queue in the ordered set where status = &#63; and serverNo = &#63;.
	*
	* @param publishQueueId the primary key of the current publish queue
	* @param status the status
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue[] findBySTS_SN_PrevAndNext(long publishQueueId,
		int status, String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator)
		throws NoSuchPublishQueueException;

	/**
	* Returns all the publish queues where status = any &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @return the matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int[] statuses,
		String serverNo);

	/**
	* Returns a range of all the publish queues where status = any &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int[] statuses,
		String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the publish queues where status = any &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int[] statuses,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues where status = &#63; and serverNo = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param serverNo the server no
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publish queues
	*/
	public java.util.List<PublishQueue> findBySTS_SN(int[] statuses,
		String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publish queues where status = &#63; and serverNo = &#63; from the database.
	*
	* @param status the status
	* @param serverNo the server no
	*/
	public void removeBySTS_SN(int status, String serverNo);

	/**
	* Returns the number of publish queues where status = &#63; and serverNo = &#63;.
	*
	* @param status the status
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countBySTS_SN(int status, String serverNo);

	/**
	* Returns the number of publish queues where status = any &#63; and serverNo = &#63;.
	*
	* @param statuses the statuses
	* @param serverNo the server no
	* @return the number of matching publish queues
	*/
	public int countBySTS_SN(int[] statuses, String serverNo);

	/**
	* Caches the publish queue in the entity cache if it is enabled.
	*
	* @param publishQueue the publish queue
	*/
	public void cacheResult(PublishQueue publishQueue);

	/**
	* Caches the publish queues in the entity cache if it is enabled.
	*
	* @param publishQueues the publish queues
	*/
	public void cacheResult(java.util.List<PublishQueue> publishQueues);

	/**
	* Creates a new publish queue with the primary key. Does not add the publish queue to the database.
	*
	* @param publishQueueId the primary key for the new publish queue
	* @return the new publish queue
	*/
	public PublishQueue create(long publishQueueId);

	/**
	* Removes the publish queue with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue that was removed
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue remove(long publishQueueId)
		throws NoSuchPublishQueueException;

	public PublishQueue updateImpl(PublishQueue publishQueue);

	/**
	* Returns the publish queue with the primary key or throws a {@link NoSuchPublishQueueException} if it could not be found.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue
	* @throws NoSuchPublishQueueException if a publish queue with the primary key could not be found
	*/
	public PublishQueue findByPrimaryKey(long publishQueueId)
		throws NoSuchPublishQueueException;

	/**
	* Returns the publish queue with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param publishQueueId the primary key of the publish queue
	* @return the publish queue, or <code>null</code> if a publish queue with the primary key could not be found
	*/
	public PublishQueue fetchByPrimaryKey(long publishQueueId);

	@Override
	public java.util.Map<java.io.Serializable, PublishQueue> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the publish queues.
	*
	* @return the publish queues
	*/
	public java.util.List<PublishQueue> findAll();

	/**
	* Returns a range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @return the range of publish queues
	*/
	public java.util.List<PublishQueue> findAll(int start, int end);

	/**
	* Returns an ordered range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of publish queues
	*/
	public java.util.List<PublishQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator);

	/**
	* Returns an ordered range of all the publish queues.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublishQueueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publish queues
	* @param end the upper bound of the range of publish queues (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of publish queues
	*/
	public java.util.List<PublishQueue> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublishQueue> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publish queues from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of publish queues.
	*
	* @return the number of publish queues
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}