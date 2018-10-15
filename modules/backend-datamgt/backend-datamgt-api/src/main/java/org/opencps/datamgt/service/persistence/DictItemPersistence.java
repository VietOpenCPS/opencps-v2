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

import org.opencps.datamgt.exception.NoSuchDictItemException;
import org.opencps.datamgt.model.DictItem;

import java.util.Date;

/**
 * The persistence interface for the dict item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see org.opencps.datamgt.service.persistence.impl.DictItemPersistenceImpl
 * @see DictItemUtil
 * @generated
 */
@ProviderType
public interface DictItemPersistence extends BasePersistence<DictItem> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemUtil} to access the dict item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByUuid(String uuid);

	/**
	* Returns a range of all the dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where uuid = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByUuid_PrevAndNext(long dictItemId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict items
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dict item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemException;

	/**
	* Returns the dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item that was removed
	*/
	public DictItem removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemException;

	/**
	* Returns the number of dict items where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByUuid_C(String uuid, long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByUuid_C(String uuid, long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByUuid_C_PrevAndNext(long dictItemId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict items
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dict items where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId(
		long dictCollectionId);

	/**
	* Returns a range of all the dict items where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId(
		long dictCollectionId, int start, int end);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId(
		long dictCollectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId(
		long dictCollectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictCollectionId_First(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictCollectionId_First(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictCollectionId_Last(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictCollectionId_Last(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_dictCollectionId_PrevAndNext(long dictItemId,
		long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where dictCollectionId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	*/
	public void removeByF_dictCollectionId(long dictCollectionId);

	/**
	* Returns the number of dict items where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict items
	*/
	public int countByF_dictCollectionId(long dictCollectionId);

	/**
	* Returns the dict item where itemCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemException;

	/**
	* Returns the dict item where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemCode(String itemCode, long groupId);

	/**
	* Returns the dict item where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemCode(String itemCode, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item where itemCode = &#63; and groupId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the dict item that was removed
	*/
	public DictItem removeByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemException;

	/**
	* Returns the number of dict items where itemCode = &#63; and groupId = &#63;.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public int countByF_dictItemCode(String itemCode, long groupId);

	/**
	* Returns all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemByGroup(
		long dictCollectionId, long groupId);

	/**
	* Returns a range of all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemByGroup(
		long dictCollectionId, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemByGroup(
		long dictCollectionId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemByGroup(
		long dictCollectionId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictItemByGroup_First(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemByGroup_First(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_dictItemByGroup_PrevAndNext(long dictItemId,
		long dictCollectionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where dictCollectionId = &#63; and groupId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	*/
	public void removeByF_dictItemByGroup(long dictCollectionId, long groupId);

	/**
	* Returns the number of dict items where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public int countByF_dictItemByGroup(long dictCollectionId, long groupId);

	/**
	* Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) throws NoSuchDictItemException;

	/**
	* Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId);

	/**
	* Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId, boolean retrieveFromCache);

	/**
	* Removes the dict item where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the dict item that was removed
	*/
	public DictItem removeByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) throws NoSuchDictItemException;

	/**
	* Returns the number of dict items where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public int countByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId);

	/**
	* Returns all the dict items where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId(long parentItemId);

	/**
	* Returns a range of all the dict items where parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId(long parentItemId,
		int start, int end);

	/**
	* Returns an ordered range of all the dict items where parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId(long parentItemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId(long parentItemId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_parentItemId_First(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_parentItemId_First(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_parentItemId_Last(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_parentItemId_Last(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where parentItemId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_parentItemId_PrevAndNext(long dictItemId,
		long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where parentItemId = &#63; from the database.
	*
	* @param parentItemId the parent item ID
	*/
	public void removeByF_parentItemId(long parentItemId);

	/**
	* Returns the number of dict items where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @return the number of matching dict items
	*/
	public int countByF_parentItemId(long parentItemId);

	/**
	* Returns all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level);

	/**
	* Returns a range of all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start, int end);

	/**
	* Returns an ordered range of all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_parentItemId_level_PrevAndNext(long dictItemId,
		long groupId, long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	*/
	public void removeByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level);

	/**
	* Returns the number of dict items where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @return the number of matching dict items
	*/
	public int countByF_parentItemId_level(long groupId, long dictCollectionId,
		long parentItemId, int level);

	/**
	* Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemException;

	/**
	* Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByIC_DCI(String itemCode, long dictCollectionId);

	/**
	* Returns the dict item where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByIC_DCI(String itemCode, long dictCollectionId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item where itemCode = &#63; and dictCollectionId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the dict item that was removed
	*/
	public DictItem removeByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemException;

	/**
	* Returns the number of dict items where itemCode = &#63; and dictCollectionId = &#63;.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict items
	*/
	public int countByIC_DCI(String itemCode, long dictCollectionId);

	/**
	* Returns all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex);

	/**
	* Returns a range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_treeIndex_PrevAndNext(long dictItemId,
		long dictCollectionId, long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	*/
	public void removeByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex);

	/**
	* Returns the number of dict items where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @return the number of matching dict items
	*/
	public int countByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex);

	/**
	* Returns all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId);

	/**
	* Returns a range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_dictCollectionId_parentItemId_PrevAndNext(
		long dictItemId, long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where dictCollectionId = &#63; and parentItemId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	*/
	public void removeByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId);

	/**
	* Returns the number of dict items where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @return the number of matching dict items
	*/
	public int countByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId);

	/**
	* Returns all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId);

	/**
	* Returns a range of all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public java.util.List<DictItem> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the first dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the last dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public DictItem findByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Returns the last dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public DictItem fetchByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the dict items before and after the current dict item in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem[] findByF_dictItemNewerThan_PrevAndNext(long dictItemId,
		Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator)
		throws NoSuchDictItemException;

	/**
	* Removes all the dict items where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public void removeByF_dictItemNewerThan(Date modifiedDate, long groupId);

	/**
	* Returns the number of dict items where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public int countByF_dictItemNewerThan(Date modifiedDate, long groupId);

	/**
	* Caches the dict item in the entity cache if it is enabled.
	*
	* @param dictItem the dict item
	*/
	public void cacheResult(DictItem dictItem);

	/**
	* Caches the dict items in the entity cache if it is enabled.
	*
	* @param dictItems the dict items
	*/
	public void cacheResult(java.util.List<DictItem> dictItems);

	/**
	* Creates a new dict item with the primary key. Does not add the dict item to the database.
	*
	* @param dictItemId the primary key for the new dict item
	* @return the new dict item
	*/
	public DictItem create(long dictItemId);

	/**
	* Removes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item that was removed
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem remove(long dictItemId) throws NoSuchDictItemException;

	public DictItem updateImpl(DictItem dictItem);

	/**
	* Returns the dict item with the primary key or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public DictItem findByPrimaryKey(long dictItemId)
		throws NoSuchDictItemException;

	/**
	* Returns the dict item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item, or <code>null</code> if a dict item with the primary key could not be found
	*/
	public DictItem fetchByPrimaryKey(long dictItemId);

	@Override
	public java.util.Map<java.io.Serializable, DictItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict items.
	*
	* @return the dict items
	*/
	public java.util.List<DictItem> findAll();

	/**
	* Returns a range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of dict items
	*/
	public java.util.List<DictItem> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict items
	*/
	public java.util.List<DictItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns an ordered range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict items
	*/
	public java.util.List<DictItem> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict items from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict items.
	*
	* @return the number of dict items
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}