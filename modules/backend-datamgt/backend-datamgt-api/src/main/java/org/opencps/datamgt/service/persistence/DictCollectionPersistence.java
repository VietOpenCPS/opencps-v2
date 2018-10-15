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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.datamgt.exception.NoSuchDictCollectionException;
import org.opencps.datamgt.model.DictCollection;

import java.util.Date;

/**
 * The persistence interface for the dict collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.datamgt.service.persistence.impl.DictCollectionPersistenceImpl
 * @see DictCollectionUtil
 * @generated
 */
@ProviderType
public interface DictCollectionPersistence extends BasePersistence<DictCollection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictCollectionUtil} to access the dict collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid(String uuid);

	/**
	* Returns a range of all the dict collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the dict collections before and after the current dict collection in the ordered set where uuid = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public DictCollection[] findByUuid_PrevAndNext(long dictCollectionId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Removes all the dict collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dict collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict collections
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dict collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByUUID_G(String uuid, long groupId)
		throws NoSuchDictCollectionException;

	/**
	* Returns the dict collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dict collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict collection that was removed
	*/
	public DictCollection removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictCollectionException;

	/**
	* Returns the number of dict collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict collections
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the dict collections before and after the current dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public DictCollection[] findByUuid_C_PrevAndNext(long dictCollectionId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Removes all the dict collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dict collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict collections
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the dict collection where collectionCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionException} if it could not be found.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByF_dictCollectionCode(String collectionCode,
		long groupId) throws NoSuchDictCollectionException;

	/**
	* Returns the dict collection where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByF_dictCollectionCode(String collectionCode,
		long groupId);

	/**
	* Returns the dict collection where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByF_dictCollectionCode(String collectionCode,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the dict collection where collectionCode = &#63; and groupId = &#63; from the database.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the dict collection that was removed
	*/
	public DictCollection removeByF_dictCollectionCode(String collectionCode,
		long groupId) throws NoSuchDictCollectionException;

	/**
	* Returns the number of dict collections where collectionCode = &#63; and groupId = &#63;.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the number of matching dict collections
	*/
	public int countByF_dictCollectionCode(String collectionCode, long groupId);

	/**
	* Returns all the dict collections where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionByGroup(
		long groupId);

	/**
	* Returns a range of all the dict collections where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionByGroup(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict collections where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionByGroup(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns an ordered range of all the dict collections where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionByGroup(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByF_dictCollectionByGroup_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the first dict collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByF_dictCollectionByGroup_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the last dict collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByF_dictCollectionByGroup_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the last dict collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByF_dictCollectionByGroup_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the dict collections before and after the current dict collection in the ordered set where groupId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public DictCollection[] findByF_dictCollectionByGroup_PrevAndNext(
		long dictCollectionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Removes all the dict collections where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_dictCollectionByGroup(long groupId);

	/**
	* Returns the number of dict collections where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dict collections
	*/
	public int countByF_dictCollectionByGroup(long groupId);

	/**
	* Returns all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId);

	/**
	* Returns a range of all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns an ordered range of all the dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collections
	*/
	public java.util.List<DictCollection> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the first dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the last dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public DictCollection findByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Returns the last dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public DictCollection fetchByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns the dict collections before and after the current dict collection in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public DictCollection[] findByF_dictCollectionNewerThan_PrevAndNext(
		long dictCollectionId, Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator)
		throws NoSuchDictCollectionException;

	/**
	* Removes all the dict collections where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public void removeByF_dictCollectionNewerThan(Date modifiedDate,
		long groupId);

	/**
	* Returns the number of dict collections where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict collections
	*/
	public int countByF_dictCollectionNewerThan(Date modifiedDate, long groupId);

	/**
	* Caches the dict collection in the entity cache if it is enabled.
	*
	* @param dictCollection the dict collection
	*/
	public void cacheResult(DictCollection dictCollection);

	/**
	* Caches the dict collections in the entity cache if it is enabled.
	*
	* @param dictCollections the dict collections
	*/
	public void cacheResult(java.util.List<DictCollection> dictCollections);

	/**
	* Creates a new dict collection with the primary key. Does not add the dict collection to the database.
	*
	* @param dictCollectionId the primary key for the new dict collection
	* @return the new dict collection
	*/
	public DictCollection create(long dictCollectionId);

	/**
	* Removes the dict collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection that was removed
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public DictCollection remove(long dictCollectionId)
		throws NoSuchDictCollectionException;

	public DictCollection updateImpl(DictCollection dictCollection);

	/**
	* Returns the dict collection with the primary key or throws a {@link NoSuchDictCollectionException} if it could not be found.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public DictCollection findByPrimaryKey(long dictCollectionId)
		throws NoSuchDictCollectionException;

	/**
	* Returns the dict collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection, or <code>null</code> if a dict collection with the primary key could not be found
	*/
	public DictCollection fetchByPrimaryKey(long dictCollectionId);

	@Override
	public java.util.Map<java.io.Serializable, DictCollection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict collections.
	*
	* @return the dict collections
	*/
	public java.util.List<DictCollection> findAll();

	/**
	* Returns a range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of dict collections
	*/
	public java.util.List<DictCollection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict collections
	*/
	public java.util.List<DictCollection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator);

	/**
	* Returns an ordered range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict collections
	*/
	public java.util.List<DictCollection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict collections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict collections.
	*
	* @return the number of dict collections
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}