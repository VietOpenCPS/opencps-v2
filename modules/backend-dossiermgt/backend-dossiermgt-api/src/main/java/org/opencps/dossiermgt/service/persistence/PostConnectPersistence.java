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

import org.opencps.dossiermgt.exception.NoSuchPostConnectException;
import org.opencps.dossiermgt.model.PostConnect;

/**
 * The persistence interface for the post connect service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.PostConnectPersistenceImpl
 * @see PostConnectUtil
 * @generated
 */
@ProviderType
public interface PostConnectPersistence extends BasePersistence<PostConnect> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PostConnectUtil} to access the post connect persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the post connects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching post connects
	*/
	public java.util.List<PostConnect> findByUuid(String uuid);

	/**
	* Returns a range of all the post connects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public java.util.List<PostConnect> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the post connects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns an ordered range of all the post connects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the first post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the last post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the last post connect in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the post connects before and after the current post connect in the ordered set where uuid = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect[] findByUuid_PrevAndNext(long postConnectId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Removes all the post connects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of post connects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching post connects
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the post connect where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByUUID_G(String uuid, long groupId)
		throws NoSuchPostConnectException;

	/**
	* Returns the post connect where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the post connect where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the post connect where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the post connect that was removed
	*/
	public PostConnect removeByUUID_G(String uuid, long groupId)
		throws NoSuchPostConnectException;

	/**
	* Returns the number of post connects where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching post connects
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching post connects
	*/
	public java.util.List<PostConnect> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public java.util.List<PostConnect> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns an ordered range of all the post connects where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the first post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the last post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the last post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the post connects before and after the current post connect in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect[] findByUuid_C_PrevAndNext(long postConnectId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Removes all the post connects where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of post connects where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching post connects
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @return the matching post connects
	*/
	public java.util.List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState);

	/**
	* Returns a range of all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end);

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_GID_SYNC_STATE(long groupId,
		int syncState, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_GID_SYNC_STATE_First(long groupId,
		int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_GID_SYNC_STATE_First(long groupId,
		int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_GID_SYNC_STATE_Last(long groupId, int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_GID_SYNC_STATE_Last(long groupId,
		int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the post connects before and after the current post connect in the ordered set where groupId = &#63; and syncState = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param groupId the group ID
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect[] findByF_GID_SYNC_STATE_PrevAndNext(
		long postConnectId, long groupId, int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Removes all the post connects where groupId = &#63; and syncState = &#63; from the database.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	*/
	public void removeByF_GID_SYNC_STATE(long groupId, int syncState);

	/**
	* Returns the number of post connects where groupId = &#63; and syncState = &#63;.
	*
	* @param groupId the group ID
	* @param syncState the sync state
	* @return the number of matching post connects
	*/
	public int countByF_GID_SYNC_STATE(long groupId, int syncState);

	/**
	* Returns all the post connects where syncState = &#63;.
	*
	* @param syncState the sync state
	* @return the matching post connects
	*/
	public java.util.List<PostConnect> findByF_SYNC_STATE(int syncState);

	/**
	* Returns a range of all the post connects where syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_SYNC_STATE(int syncState,
		int start, int end);

	/**
	* Returns an ordered range of all the post connects where syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_SYNC_STATE(int syncState,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns an ordered range of all the post connects where syncState = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param syncState the sync state
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_SYNC_STATE(int syncState,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_SYNC_STATE_First(int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the first post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_SYNC_STATE_First(int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the last post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_SYNC_STATE_Last(int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the last post connect in the ordered set where syncState = &#63;.
	*
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_SYNC_STATE_Last(int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the post connects before and after the current post connect in the ordered set where syncState = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param syncState the sync state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect[] findByF_SYNC_STATE_PrevAndNext(long postConnectId,
		int syncState,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Removes all the post connects where syncState = &#63; from the database.
	*
	* @param syncState the sync state
	*/
	public void removeByF_SYNC_STATE(int syncState);

	/**
	* Returns the number of post connects where syncState = &#63;.
	*
	* @param syncState the sync state
	* @return the number of matching post connects
	*/
	public int countByF_SYNC_STATE(int syncState);

	/**
	* Returns the post connect where orderNumber = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param orderNumber the order number
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_ORDER_NUMBER(String orderNumber)
		throws NoSuchPostConnectException;

	/**
	* Returns the post connect where orderNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param orderNumber the order number
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_ORDER_NUMBER(String orderNumber);

	/**
	* Returns the post connect where orderNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param orderNumber the order number
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_ORDER_NUMBER(String orderNumber,
		boolean retrieveFromCache);

	/**
	* Removes the post connect where orderNumber = &#63; from the database.
	*
	* @param orderNumber the order number
	* @return the post connect that was removed
	*/
	public PostConnect removeByF_ORDER_NUMBER(String orderNumber)
		throws NoSuchPostConnectException;

	/**
	* Returns the number of post connects where orderNumber = &#63;.
	*
	* @param orderNumber the order number
	* @return the number of matching post connects
	*/
	public int countByF_ORDER_NUMBER(String orderNumber);

	/**
	* Returns the post connect where orderNumber = &#63; and postStatus = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) throws NoSuchPostConnectException;

	/**
	* Returns the post connect where orderNumber = &#63; and postStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus);

	/**
	* Returns the post connect where orderNumber = &#63; and postStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus, boolean retrieveFromCache);

	/**
	* Removes the post connect where orderNumber = &#63; and postStatus = &#63; from the database.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the post connect that was removed
	*/
	public PostConnect removeByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus) throws NoSuchPostConnectException;

	/**
	* Returns the number of post connects where orderNumber = &#63; and postStatus = &#63;.
	*
	* @param orderNumber the order number
	* @param postStatus the post status
	* @return the number of matching post connects
	*/
	public int countByF_ORDER_NUMBER_POST_STATUS(String orderNumber,
		int postStatus);

	/**
	* Returns all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching post connects
	*/
	public java.util.List<PostConnect> findByF_POST_BY_DOSSIER_ID(
		long groupId, long dossierId);

	/**
	* Returns a range of all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_POST_BY_DOSSIER_ID(
		long groupId, long dossierId, int start, int end);

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_POST_BY_DOSSIER_ID(
		long groupId, long dossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns an ordered range of all the post connects where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching post connects
	*/
	public java.util.List<PostConnect> findByF_POST_BY_DOSSIER_ID(
		long groupId, long dossierId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_POST_BY_DOSSIER_ID_First(long groupId,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the first post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_POST_BY_DOSSIER_ID_First(long groupId,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_POST_BY_DOSSIER_ID_Last(long groupId,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Returns the last post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_POST_BY_DOSSIER_ID_Last(long groupId,
		long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns the post connects before and after the current post connect in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param postConnectId the primary key of the current post connect
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect[] findByF_POST_BY_DOSSIER_ID_PrevAndNext(
		long postConnectId, long groupId, long dossierId,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator)
		throws NoSuchPostConnectException;

	/**
	* Removes all the post connects where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public void removeByF_POST_BY_DOSSIER_ID(long groupId, long dossierId);

	/**
	* Returns the number of post connects where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching post connects
	*/
	public int countByF_POST_BY_DOSSIER_ID(long groupId, long dossierId);

	/**
	* Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the matching post connect
	* @throws NoSuchPostConnectException if a matching post connect could not be found
	*/
	public PostConnect findByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) throws NoSuchPostConnectException;

	/**
	* Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType);

	/**
	* Returns the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching post connect, or <code>null</code> if a matching post connect could not be found
	*/
	public PostConnect fetchByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType, boolean retrieveFromCache);

	/**
	* Removes the post connect where groupId = &#63; and dossierId = &#63; and postType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the post connect that was removed
	*/
	public PostConnect removeByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType) throws NoSuchPostConnectException;

	/**
	* Returns the number of post connects where groupId = &#63; and dossierId = &#63; and postType = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param postType the post type
	* @return the number of matching post connects
	*/
	public int countByF_POST_BY_D_TYPE(long groupId, long dossierId,
		int postType);

	/**
	* Caches the post connect in the entity cache if it is enabled.
	*
	* @param postConnect the post connect
	*/
	public void cacheResult(PostConnect postConnect);

	/**
	* Caches the post connects in the entity cache if it is enabled.
	*
	* @param postConnects the post connects
	*/
	public void cacheResult(java.util.List<PostConnect> postConnects);

	/**
	* Creates a new post connect with the primary key. Does not add the post connect to the database.
	*
	* @param postConnectId the primary key for the new post connect
	* @return the new post connect
	*/
	public PostConnect create(long postConnectId);

	/**
	* Removes the post connect with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect that was removed
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect remove(long postConnectId)
		throws NoSuchPostConnectException;

	public PostConnect updateImpl(PostConnect postConnect);

	/**
	* Returns the post connect with the primary key or throws a {@link NoSuchPostConnectException} if it could not be found.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect
	* @throws NoSuchPostConnectException if a post connect with the primary key could not be found
	*/
	public PostConnect findByPrimaryKey(long postConnectId)
		throws NoSuchPostConnectException;

	/**
	* Returns the post connect with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param postConnectId the primary key of the post connect
	* @return the post connect, or <code>null</code> if a post connect with the primary key could not be found
	*/
	public PostConnect fetchByPrimaryKey(long postConnectId);

	@Override
	public java.util.Map<java.io.Serializable, PostConnect> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the post connects.
	*
	* @return the post connects
	*/
	public java.util.List<PostConnect> findAll();

	/**
	* Returns a range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @return the range of post connects
	*/
	public java.util.List<PostConnect> findAll(int start, int end);

	/**
	* Returns an ordered range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of post connects
	*/
	public java.util.List<PostConnect> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator);

	/**
	* Returns an ordered range of all the post connects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PostConnectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of post connects
	* @param end the upper bound of the range of post connects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of post connects
	*/
	public java.util.List<PostConnect> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PostConnect> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the post connects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of post connects.
	*
	* @return the number of post connects
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}