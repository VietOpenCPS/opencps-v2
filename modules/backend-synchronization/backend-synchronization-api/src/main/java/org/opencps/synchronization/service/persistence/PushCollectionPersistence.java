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

package org.opencps.synchronization.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.synchronization.exception.NoSuchPushCollectionException;
import org.opencps.synchronization.model.PushCollection;

/**
 * The persistence interface for the push collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.PushCollectionPersistenceImpl
 * @see PushCollectionUtil
 * @generated
 */
@ProviderType
public interface PushCollectionPersistence extends BasePersistence<PushCollection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushCollectionUtil} to access the push collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the push collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching push collections
	*/
	public java.util.List<PushCollection> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the push collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of matching push collections
	*/
	public java.util.List<PushCollection> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the push collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push collections
	*/
	public java.util.List<PushCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns an ordered range of all the push collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push collections
	*/
	public java.util.List<PushCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Returns the first push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns the last push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Returns the last push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns the push collections before and after the current push collection in the ordered set where uuid = &#63;.
	*
	* @param pushCollectionId the primary key of the current push collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public PushCollection[] findByUuid_PrevAndNext(long pushCollectionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Removes all the push collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of push collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching push collections
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the push collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPushCollectionException;

	/**
	* Returns the push collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the push collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the push collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the push collection that was removed
	*/
	public PushCollection removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPushCollectionException;

	/**
	* Returns the number of push collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching push collections
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching push collections
	*/
	public java.util.List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of matching push collections
	*/
	public java.util.List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push collections
	*/
	public java.util.List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns an ordered range of all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push collections
	*/
	public java.util.List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Returns the first push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns the last push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Returns the last push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns the push collections before and after the current push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param pushCollectionId the primary key of the current push collection
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public PushCollection[] findByUuid_C_PrevAndNext(long pushCollectionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Removes all the push collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of push collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching push collections
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or throws a {@link NoSuchPushCollectionException} if it could not be found.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method)
		throws NoSuchPushCollectionException;

	/**
	* Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method);

	/**
	* Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method,
		boolean retrieveFromCache);

	/**
	* Removes the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the push collection that was removed
	*/
	public PushCollection removeByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method)
		throws NoSuchPushCollectionException;

	/**
	* Returns the number of push collections where groupId = &#63; and collectionCode = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the number of matching push collections
	*/
	public int countByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method);

	/**
	* Returns all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching push collections
	*/
	public java.util.List<PushCollection> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo);

	/**
	* Returns a range of all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of matching push collections
	*/
	public java.util.List<PushCollection> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push collections
	*/
	public java.util.List<PushCollection> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns an ordered range of all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push collections
	*/
	public java.util.List<PushCollection> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Returns the first push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns the last push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public PushCollection findByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Returns the last push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public PushCollection fetchByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns the push collections before and after the current push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param pushCollectionId the primary key of the current push collection
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public PushCollection[] findByF_groupId_serverNo_PrevAndNext(
		long pushCollectionId, long groupId, java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator)
		throws NoSuchPushCollectionException;

	/**
	* Removes all the push collections where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public void removeByF_groupId_serverNo(long groupId,
		java.lang.String serverNo);

	/**
	* Returns the number of push collections where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching push collections
	*/
	public int countByF_groupId_serverNo(long groupId, java.lang.String serverNo);

	/**
	* Caches the push collection in the entity cache if it is enabled.
	*
	* @param pushCollection the push collection
	*/
	public void cacheResult(PushCollection pushCollection);

	/**
	* Caches the push collections in the entity cache if it is enabled.
	*
	* @param pushCollections the push collections
	*/
	public void cacheResult(java.util.List<PushCollection> pushCollections);

	/**
	* Creates a new push collection with the primary key. Does not add the push collection to the database.
	*
	* @param pushCollectionId the primary key for the new push collection
	* @return the new push collection
	*/
	public PushCollection create(long pushCollectionId);

	/**
	* Removes the push collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection that was removed
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public PushCollection remove(long pushCollectionId)
		throws NoSuchPushCollectionException;

	public PushCollection updateImpl(PushCollection pushCollection);

	/**
	* Returns the push collection with the primary key or throws a {@link NoSuchPushCollectionException} if it could not be found.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public PushCollection findByPrimaryKey(long pushCollectionId)
		throws NoSuchPushCollectionException;

	/**
	* Returns the push collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection, or <code>null</code> if a push collection with the primary key could not be found
	*/
	public PushCollection fetchByPrimaryKey(long pushCollectionId);

	@Override
	public java.util.Map<java.io.Serializable, PushCollection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the push collections.
	*
	* @return the push collections
	*/
	public java.util.List<PushCollection> findAll();

	/**
	* Returns a range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of push collections
	*/
	public java.util.List<PushCollection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push collections
	*/
	public java.util.List<PushCollection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator);

	/**
	* Returns an ordered range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of push collections
	*/
	public java.util.List<PushCollection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the push collections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of push collections.
	*
	* @return the number of push collections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}