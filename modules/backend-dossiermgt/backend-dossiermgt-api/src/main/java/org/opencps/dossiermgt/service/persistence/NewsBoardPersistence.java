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

import org.opencps.dossiermgt.exception.NoSuchNewsBoardException;
import org.opencps.dossiermgt.model.NewsBoard;

/**
 * The persistence interface for the news board service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.NewsBoardPersistenceImpl
 * @see NewsBoardUtil
 * @generated
 */
@ProviderType
public interface NewsBoardPersistence extends BasePersistence<NewsBoard> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NewsBoardUtil} to access the news board persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the news boards where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid(String uuid);

	/**
	* Returns a range of all the news boards where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the news boards where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns an ordered range of all the news boards where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Returns the first news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the last news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Returns the last news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the news boards before and after the current news board in the ordered set where uuid = &#63;.
	*
	* @param newsBoardId the primary key of the current news board
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public NewsBoard[] findByUuid_PrevAndNext(long newsBoardId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Removes all the news boards where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of news boards where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching news boards
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the news board where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchNewsBoardException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByUUID_G(String uuid, long groupId)
		throws NoSuchNewsBoardException;

	/**
	* Returns the news board where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the news board where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the news board where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the news board that was removed
	*/
	public NewsBoard removeByUUID_G(String uuid, long groupId)
		throws NoSuchNewsBoardException;

	/**
	* Returns the number of news boards where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching news boards
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns an ordered range of all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching news boards
	*/
	public java.util.List<NewsBoard> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Returns the first news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the last news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Returns the last news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the news boards before and after the current news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param newsBoardId the primary key of the current news board
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public NewsBoard[] findByUuid_C_PrevAndNext(long newsBoardId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Removes all the news boards where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of news boards where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching news boards
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the news boards where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching news boards
	*/
	public java.util.List<NewsBoard> findByF_GID(long groupId);

	/**
	* Returns a range of all the news boards where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of matching news boards
	*/
	public java.util.List<NewsBoard> findByF_GID(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the news boards where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching news boards
	*/
	public java.util.List<NewsBoard> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns an ordered range of all the news boards where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching news boards
	*/
	public java.util.List<NewsBoard> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Returns the first news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the last news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public NewsBoard findByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Returns the last news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public NewsBoard fetchByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns the news boards before and after the current news board in the ordered set where groupId = &#63;.
	*
	* @param newsBoardId the primary key of the current news board
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public NewsBoard[] findByF_GID_PrevAndNext(long newsBoardId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator)
		throws NoSuchNewsBoardException;

	/**
	* Removes all the news boards where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_GID(long groupId);

	/**
	* Returns the number of news boards where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching news boards
	*/
	public int countByF_GID(long groupId);

	/**
	* Caches the news board in the entity cache if it is enabled.
	*
	* @param newsBoard the news board
	*/
	public void cacheResult(NewsBoard newsBoard);

	/**
	* Caches the news boards in the entity cache if it is enabled.
	*
	* @param newsBoards the news boards
	*/
	public void cacheResult(java.util.List<NewsBoard> newsBoards);

	/**
	* Creates a new news board with the primary key. Does not add the news board to the database.
	*
	* @param newsBoardId the primary key for the new news board
	* @return the new news board
	*/
	public NewsBoard create(long newsBoardId);

	/**
	* Removes the news board with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board that was removed
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public NewsBoard remove(long newsBoardId) throws NoSuchNewsBoardException;

	public NewsBoard updateImpl(NewsBoard newsBoard);

	/**
	* Returns the news board with the primary key or throws a {@link NoSuchNewsBoardException} if it could not be found.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public NewsBoard findByPrimaryKey(long newsBoardId)
		throws NoSuchNewsBoardException;

	/**
	* Returns the news board with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board, or <code>null</code> if a news board with the primary key could not be found
	*/
	public NewsBoard fetchByPrimaryKey(long newsBoardId);

	@Override
	public java.util.Map<java.io.Serializable, NewsBoard> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the news boards.
	*
	* @return the news boards
	*/
	public java.util.List<NewsBoard> findAll();

	/**
	* Returns a range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of news boards
	*/
	public java.util.List<NewsBoard> findAll(int start, int end);

	/**
	* Returns an ordered range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of news boards
	*/
	public java.util.List<NewsBoard> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator);

	/**
	* Returns an ordered range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of news boards
	*/
	public java.util.List<NewsBoard> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the news boards from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of news boards.
	*
	* @return the number of news boards
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}