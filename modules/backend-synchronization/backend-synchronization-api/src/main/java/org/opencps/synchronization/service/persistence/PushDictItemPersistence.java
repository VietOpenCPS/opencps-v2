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

import org.opencps.synchronization.exception.NoSuchPushDictItemException;
import org.opencps.synchronization.model.PushDictItem;

/**
 * The persistence interface for the push dict item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.PushDictItemPersistenceImpl
 * @see PushDictItemUtil
 * @generated
 */
@ProviderType
public interface PushDictItemPersistence extends BasePersistence<PushDictItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushDictItemUtil} to access the push dict item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the push dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the push dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns the push dict items before and after the current push dict item in the ordered set where uuid = &#63;.
	*
	* @param pushDictItemId the primary key of the current push dict item
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public PushDictItem[] findByUuid_PrevAndNext(long pushDictItemId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Removes all the push dict items where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of push dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching push dict items
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the push dict item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushDictItemException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPushDictItemException;

	/**
	* Returns the push dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the push dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the push dict item where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the push dict item that was removed
	*/
	public PushDictItem removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPushDictItemException;

	/**
	* Returns the number of push dict items where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching push dict items
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns the push dict items before and after the current push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param pushDictItemId the primary key of the current push dict item
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public PushDictItem[] findByUuid_C_PrevAndNext(long pushDictItemId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Removes all the push dict items where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of push dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching push dict items
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictItemException} if it could not be found.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByF_collectionCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String method) throws NoSuchPushDictItemException;

	/**
	* Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByF_collectionCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String method);

	/**
	* Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByF_collectionCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String method, boolean retrieveFromCache);

	/**
	* Removes the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the push dict item that was removed
	*/
	public PushDictItem removeByF_collectionCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String method) throws NoSuchPushDictItemException;

	/**
	* Returns the number of push dict items where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the number of matching push dict items
	*/
	public int countByF_collectionCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String method);

	/**
	* Returns all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching push dict items
	*/
	public java.util.List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo);

	/**
	* Returns a range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns an ordered range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict items
	*/
	public java.util.List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Returns the first push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns the last push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public PushDictItem findByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Returns the last push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public PushDictItem fetchByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns the push dict items before and after the current push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param pushDictItemId the primary key of the current push dict item
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public PushDictItem[] findByF_groupId_serverNo_PrevAndNext(
		long pushDictItemId, long groupId, java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator)
		throws NoSuchPushDictItemException;

	/**
	* Removes all the push dict items where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public void removeByF_groupId_serverNo(long groupId,
		java.lang.String serverNo);

	/**
	* Returns the number of push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching push dict items
	*/
	public int countByF_groupId_serverNo(long groupId, java.lang.String serverNo);

	/**
	* Caches the push dict item in the entity cache if it is enabled.
	*
	* @param pushDictItem the push dict item
	*/
	public void cacheResult(PushDictItem pushDictItem);

	/**
	* Caches the push dict items in the entity cache if it is enabled.
	*
	* @param pushDictItems the push dict items
	*/
	public void cacheResult(java.util.List<PushDictItem> pushDictItems);

	/**
	* Creates a new push dict item with the primary key. Does not add the push dict item to the database.
	*
	* @param pushDictItemId the primary key for the new push dict item
	* @return the new push dict item
	*/
	public PushDictItem create(long pushDictItemId);

	/**
	* Removes the push dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item that was removed
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public PushDictItem remove(long pushDictItemId)
		throws NoSuchPushDictItemException;

	public PushDictItem updateImpl(PushDictItem pushDictItem);

	/**
	* Returns the push dict item with the primary key or throws a {@link NoSuchPushDictItemException} if it could not be found.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public PushDictItem findByPrimaryKey(long pushDictItemId)
		throws NoSuchPushDictItemException;

	/**
	* Returns the push dict item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item, or <code>null</code> if a push dict item with the primary key could not be found
	*/
	public PushDictItem fetchByPrimaryKey(long pushDictItemId);

	@Override
	public java.util.Map<java.io.Serializable, PushDictItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the push dict items.
	*
	* @return the push dict items
	*/
	public java.util.List<PushDictItem> findAll();

	/**
	* Returns a range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of push dict items
	*/
	public java.util.List<PushDictItem> findAll(int start, int end);

	/**
	* Returns an ordered range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push dict items
	*/
	public java.util.List<PushDictItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator);

	/**
	* Returns an ordered range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of push dict items
	*/
	public java.util.List<PushDictItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the push dict items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of push dict items.
	*
	* @return the number of push dict items
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}