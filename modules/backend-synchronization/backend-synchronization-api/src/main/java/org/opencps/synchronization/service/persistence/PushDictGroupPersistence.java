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

import org.opencps.synchronization.exception.NoSuchPushDictGroupException;
import org.opencps.synchronization.model.PushDictGroup;

/**
 * The persistence interface for the push dict group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.PushDictGroupPersistenceImpl
 * @see PushDictGroupUtil
 * @generated
 */
@ProviderType
public interface PushDictGroupPersistence extends BasePersistence<PushDictGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushDictGroupUtil} to access the push dict group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the push dict groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the push dict groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @return the range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the push dict groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the push dict groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the first push dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns the last push dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the last push dict group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns the push dict groups before and after the current push dict group in the ordered set where uuid = &#63;.
	*
	* @param pushDictGroupId the primary key of the current push dict group
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict group
	* @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	*/
	public PushDictGroup[] findByUuid_PrevAndNext(long pushDictGroupId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Removes all the push dict groups where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of push dict groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching push dict groups
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the push dict group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the push dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the push dict group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the push dict group where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the push dict group that was removed
	*/
	public PushDictGroup removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the number of push dict groups where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching push dict groups
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the push dict groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the push dict groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @return the range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the push dict groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the push dict groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the first push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns the last push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the last push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns the push dict groups before and after the current push dict group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param pushDictGroupId the primary key of the current push dict group
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict group
	* @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	*/
	public PushDictGroup[] findByUuid_C_PrevAndNext(long pushDictGroupId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Removes all the push dict groups where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of push dict groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching push dict groups
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param method the method
	* @return the matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByF_collectionCode_groupCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String groupCode,
		java.lang.String method) throws NoSuchPushDictGroupException;

	/**
	* Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param method the method
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByF_collectionCode_groupCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String method);

	/**
	* Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param method the method
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByF_collectionCode_groupCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String method,
		boolean retrieveFromCache);

	/**
	* Removes the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param method the method
	* @return the push dict group that was removed
	*/
	public PushDictGroup removeByF_collectionCode_groupCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String method)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the number of push dict groups where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param method the method
	* @return the number of matching push dict groups
	*/
	public int countByF_collectionCode_groupCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String groupCode,
		java.lang.String method);

	/**
	* Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param itemCode the item code
	* @param method the method
	* @return the matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByF_collectionCode_groupCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String itemCode,
		java.lang.String method) throws NoSuchPushDictGroupException;

	/**
	* Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param itemCode the item code
	* @param method the method
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByF_collectionCode_groupCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String itemCode,
		java.lang.String method);

	/**
	* Returns the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param itemCode the item code
	* @param method the method
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByF_collectionCode_groupCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String itemCode,
		java.lang.String method, boolean retrieveFromCache);

	/**
	* Removes the push dict group where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param itemCode the item code
	* @param method the method
	* @return the push dict group that was removed
	*/
	public PushDictGroup removeByF_collectionCode_groupCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String groupCode, java.lang.String itemCode,
		java.lang.String method) throws NoSuchPushDictGroupException;

	/**
	* Returns the number of push dict groups where groupId = &#63; and collectionCode = &#63; and groupCode = &#63; and itemCode = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param groupCode the group code
	* @param itemCode the item code
	* @param method the method
	* @return the number of matching push dict groups
	*/
	public int countByF_collectionCode_groupCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String groupCode,
		java.lang.String itemCode, java.lang.String method);

	/**
	* Returns all the push dict groups where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo);

	/**
	* Returns a range of all the push dict groups where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @return the range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo, int start, int end);

	/**
	* Returns an ordered range of all the push dict groups where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the push dict groups where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict groups
	*/
	public java.util.List<PushDictGroup> findByF_groupId_serverNo(
		long groupId, java.lang.String serverNo, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the first push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns the last push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict group
	* @throws NoSuchPushDictGroupException if a matching push dict group could not be found
	*/
	public PushDictGroup findByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the last push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	public PushDictGroup fetchByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns the push dict groups before and after the current push dict group in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param pushDictGroupId the primary key of the current push dict group
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict group
	* @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	*/
	public PushDictGroup[] findByF_groupId_serverNo_PrevAndNext(
		long pushDictGroupId, long groupId, java.lang.String serverNo,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator)
		throws NoSuchPushDictGroupException;

	/**
	* Removes all the push dict groups where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public void removeByF_groupId_serverNo(long groupId,
		java.lang.String serverNo);

	/**
	* Returns the number of push dict groups where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching push dict groups
	*/
	public int countByF_groupId_serverNo(long groupId, java.lang.String serverNo);

	/**
	* Caches the push dict group in the entity cache if it is enabled.
	*
	* @param pushDictGroup the push dict group
	*/
	public void cacheResult(PushDictGroup pushDictGroup);

	/**
	* Caches the push dict groups in the entity cache if it is enabled.
	*
	* @param pushDictGroups the push dict groups
	*/
	public void cacheResult(java.util.List<PushDictGroup> pushDictGroups);

	/**
	* Creates a new push dict group with the primary key. Does not add the push dict group to the database.
	*
	* @param pushDictGroupId the primary key for the new push dict group
	* @return the new push dict group
	*/
	public PushDictGroup create(long pushDictGroupId);

	/**
	* Removes the push dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group that was removed
	* @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	*/
	public PushDictGroup remove(long pushDictGroupId)
		throws NoSuchPushDictGroupException;

	public PushDictGroup updateImpl(PushDictGroup pushDictGroup);

	/**
	* Returns the push dict group with the primary key or throws a {@link NoSuchPushDictGroupException} if it could not be found.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group
	* @throws NoSuchPushDictGroupException if a push dict group with the primary key could not be found
	*/
	public PushDictGroup findByPrimaryKey(long pushDictGroupId)
		throws NoSuchPushDictGroupException;

	/**
	* Returns the push dict group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group, or <code>null</code> if a push dict group with the primary key could not be found
	*/
	public PushDictGroup fetchByPrimaryKey(long pushDictGroupId);

	@Override
	public java.util.Map<java.io.Serializable, PushDictGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the push dict groups.
	*
	* @return the push dict groups
	*/
	public java.util.List<PushDictGroup> findAll();

	/**
	* Returns a range of all the push dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @return the range of push dict groups
	*/
	public java.util.List<PushDictGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the push dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push dict groups
	*/
	public java.util.List<PushDictGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator);

	/**
	* Returns an ordered range of all the push dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of push dict groups
	*/
	public java.util.List<PushDictGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushDictGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the push dict groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of push dict groups.
	*
	* @return the number of push dict groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}