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

package org.opencps.communication.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.communication.exception.NoSuchZaloMapException;
import org.opencps.communication.model.ZaloMap;

/**
 * The persistence interface for the zalo map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see org.opencps.communication.service.persistence.impl.ZaloMapPersistenceImpl
 * @see ZaloMapUtil
 * @generated
 */
@ProviderType
public interface ZaloMapPersistence extends BasePersistence<ZaloMap> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZaloMapUtil} to access the zalo map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the zalo map where uId = &#63; or throws a {@link NoSuchZaloMapException} if it could not be found.
	*
	* @param uId the u ID
	* @return the matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public ZaloMap findByF_UId(String uId) throws NoSuchZaloMapException;

	/**
	* Returns the zalo map where uId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uId the u ID
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_UId(String uId);

	/**
	* Returns the zalo map where uId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uId the u ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_UId(String uId, boolean retrieveFromCache);

	/**
	* Removes the zalo map where uId = &#63; from the database.
	*
	* @param uId the u ID
	* @return the zalo map that was removed
	*/
	public ZaloMap removeByF_UId(String uId) throws NoSuchZaloMapException;

	/**
	* Returns the number of zalo maps where uId = &#63;.
	*
	* @param uId the u ID
	* @return the number of matching zalo maps
	*/
	public int countByF_UId(String uId);

	/**
	* Returns the zalo map where telNo = &#63; or throws a {@link NoSuchZaloMapException} if it could not be found.
	*
	* @param telNo the tel no
	* @return the matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public ZaloMap findByF_TelNo(String telNo) throws NoSuchZaloMapException;

	/**
	* Returns the zalo map where telNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param telNo the tel no
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_TelNo(String telNo);

	/**
	* Returns the zalo map where telNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param telNo the tel no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_TelNo(String telNo, boolean retrieveFromCache);

	/**
	* Removes the zalo map where telNo = &#63; from the database.
	*
	* @param telNo the tel no
	* @return the zalo map that was removed
	*/
	public ZaloMap removeByF_TelNo(String telNo) throws NoSuchZaloMapException;

	/**
	* Returns the number of zalo maps where telNo = &#63;.
	*
	* @param telNo the tel no
	* @return the number of matching zalo maps
	*/
	public int countByF_TelNo(String telNo);

	/**
	* Returns all the zalo maps where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_GID(long groupId);

	/**
	* Returns a range of all the zalo maps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @return the range of matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_GID(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the zalo maps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns an ordered range of all the zalo maps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_GID(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public ZaloMap findByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException;

	/**
	* Returns the first zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_GID_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns the last zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public ZaloMap findByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException;

	/**
	* Returns the last zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_GID_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns the zalo maps before and after the current zalo map in the ordered set where groupId = &#63;.
	*
	* @param zaloMapId the primary key of the current zalo map
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zalo map
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public ZaloMap[] findByF_GID_PrevAndNext(long zaloMapId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException;

	/**
	* Removes all the zalo maps where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_GID(long groupId);

	/**
	* Returns the number of zalo maps where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching zalo maps
	*/
	public int countByF_GID(long groupId);

	/**
	* Returns all the zalo maps where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @return the matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_ZALO_OAID(String zaloOAId);

	/**
	* Returns a range of all the zalo maps where zaloOAId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zaloOAId the zalo oa ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @return the range of matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_ZALO_OAID(String zaloOAId,
		int start, int end);

	/**
	* Returns an ordered range of all the zalo maps where zaloOAId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zaloOAId the zalo oa ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_ZALO_OAID(String zaloOAId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns an ordered range of all the zalo maps where zaloOAId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zaloOAId the zalo oa ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zalo maps
	*/
	public java.util.List<ZaloMap> findByF_ZALO_OAID(String zaloOAId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public ZaloMap findByF_ZALO_OAID_First(String zaloOAId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException;

	/**
	* Returns the first zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_ZALO_OAID_First(String zaloOAId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns the last zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public ZaloMap findByF_ZALO_OAID_Last(String zaloOAId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException;

	/**
	* Returns the last zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public ZaloMap fetchByF_ZALO_OAID_Last(String zaloOAId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns the zalo maps before and after the current zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloMapId the primary key of the current zalo map
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zalo map
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public ZaloMap[] findByF_ZALO_OAID_PrevAndNext(long zaloMapId,
		String zaloOAId,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator)
		throws NoSuchZaloMapException;

	/**
	* Removes all the zalo maps where zaloOAId = &#63; from the database.
	*
	* @param zaloOAId the zalo oa ID
	*/
	public void removeByF_ZALO_OAID(String zaloOAId);

	/**
	* Returns the number of zalo maps where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @return the number of matching zalo maps
	*/
	public int countByF_ZALO_OAID(String zaloOAId);

	/**
	* Caches the zalo map in the entity cache if it is enabled.
	*
	* @param zaloMap the zalo map
	*/
	public void cacheResult(ZaloMap zaloMap);

	/**
	* Caches the zalo maps in the entity cache if it is enabled.
	*
	* @param zaloMaps the zalo maps
	*/
	public void cacheResult(java.util.List<ZaloMap> zaloMaps);

	/**
	* Creates a new zalo map with the primary key. Does not add the zalo map to the database.
	*
	* @param zaloMapId the primary key for the new zalo map
	* @return the new zalo map
	*/
	public ZaloMap create(long zaloMapId);

	/**
	* Removes the zalo map with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zaloMapId the primary key of the zalo map
	* @return the zalo map that was removed
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public ZaloMap remove(long zaloMapId) throws NoSuchZaloMapException;

	public ZaloMap updateImpl(ZaloMap zaloMap);

	/**
	* Returns the zalo map with the primary key or throws a {@link NoSuchZaloMapException} if it could not be found.
	*
	* @param zaloMapId the primary key of the zalo map
	* @return the zalo map
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public ZaloMap findByPrimaryKey(long zaloMapId)
		throws NoSuchZaloMapException;

	/**
	* Returns the zalo map with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param zaloMapId the primary key of the zalo map
	* @return the zalo map, or <code>null</code> if a zalo map with the primary key could not be found
	*/
	public ZaloMap fetchByPrimaryKey(long zaloMapId);

	@Override
	public java.util.Map<java.io.Serializable, ZaloMap> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the zalo maps.
	*
	* @return the zalo maps
	*/
	public java.util.List<ZaloMap> findAll();

	/**
	* Returns a range of all the zalo maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @return the range of zalo maps
	*/
	public java.util.List<ZaloMap> findAll(int start, int end);

	/**
	* Returns an ordered range of all the zalo maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of zalo maps
	*/
	public java.util.List<ZaloMap> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator);

	/**
	* Returns an ordered range of all the zalo maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of zalo maps
	*/
	public java.util.List<ZaloMap> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZaloMap> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the zalo maps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of zalo maps.
	*
	* @return the number of zalo maps
	*/
	public int countAll();
}