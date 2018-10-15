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

import org.opencps.synchronization.exception.NoSuchDictItemTempException;
import org.opencps.synchronization.model.DictItemTemp;

import java.util.Date;

/**
 * The persistence interface for the dict item temp service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see org.opencps.synchronization.service.persistence.impl.DictItemTempPersistenceImpl
 * @see DictItemTempUtil
 * @generated
 */
@ProviderType
public interface DictItemTempPersistence extends BasePersistence<DictItemTemp> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemTempUtil} to access the dict item temp persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dict item temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid(String uuid);

	/**
	* Returns a range of all the dict item temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dict item temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where uuid = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByUuid_PrevAndNext(long dictItemId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dict item temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict item temps
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dict item temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the dict item temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dict item temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item temp where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item temp that was removed
	*/
	public DictItemTemp removeByUUID_G(String uuid, long groupId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the number of dict item temps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict item temps
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dict item temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid_C(String uuid, long companyId);

	/**
	* Returns a range of all the dict item temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByUuid_C_PrevAndNext(long dictItemId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dict item temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict item temps
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns all the dict item temps where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId(
		long dictCollectionId);

	/**
	* Returns a range of all the dict item temps where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId(
		long dictCollectionId, int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId(
		long dictCollectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId(
		long dictCollectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictCollectionId_First(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictCollectionId_First(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictCollectionId_Last(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictCollectionId_Last(long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_dictCollectionId_PrevAndNext(
		long dictItemId, long dictCollectionId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where dictCollectionId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	*/
	public void removeByF_dictCollectionId(long dictCollectionId);

	/**
	* Returns the number of dict item temps where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict item temps
	*/
	public int countByF_dictCollectionId(long dictCollectionId);

	/**
	* Returns the dict item temp where itemCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the dict item temp where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemCode(String itemCode, long groupId);

	/**
	* Returns the dict item temp where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemCode(String itemCode, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item temp where itemCode = &#63; and groupId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the dict item temp that was removed
	*/
	public DictItemTemp removeByF_dictItemCode(String itemCode, long groupId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the number of dict item temps where itemCode = &#63; and groupId = &#63;.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the number of matching dict item temps
	*/
	public int countByF_dictItemCode(String itemCode, long groupId);

	/**
	* Returns all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemByGroup(
		long dictCollectionId, long groupId);

	/**
	* Returns a range of all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemByGroup(
		long dictCollectionId, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemByGroup(
		long dictCollectionId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemByGroup(
		long dictCollectionId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictItemByGroup_First(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemByGroup_First(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemByGroup_Last(long dictCollectionId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_dictItemByGroup_PrevAndNext(long dictItemId,
		long dictCollectionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where dictCollectionId = &#63; and groupId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	*/
	public void removeByF_dictItemByGroup(long dictCollectionId, long groupId);

	/**
	* Returns the number of dict item temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the number of matching dict item temps
	*/
	public int countByF_dictItemByGroup(long dictCollectionId, long groupId);

	/**
	* Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId) throws NoSuchDictItemTempException;

	/**
	* Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemCode_dictCollectionId(
		String itemCode, long dictCollectionId, long groupId);

	/**
	* Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemCode_dictCollectionId(
		String itemCode, long dictCollectionId, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item temp where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the dict item temp that was removed
	*/
	public DictItemTemp removeByF_dictItemCode_dictCollectionId(
		String itemCode, long dictCollectionId, long groupId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the number of dict item temps where itemCode = &#63; and dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the number of matching dict item temps
	*/
	public int countByF_dictItemCode_dictCollectionId(String itemCode,
		long dictCollectionId, long groupId);

	/**
	* Returns all the dict item temps where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId(long parentItemId);

	/**
	* Returns a range of all the dict item temps where parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId(
		long parentItemId, int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId(
		long parentItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId(
		long parentItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_parentItemId_First(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_parentItemId_First(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_parentItemId_Last(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_parentItemId_Last(long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where parentItemId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_parentItemId_PrevAndNext(long dictItemId,
		long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where parentItemId = &#63; from the database.
	*
	* @param parentItemId the parent item ID
	*/
	public void removeByF_parentItemId(long parentItemId);

	/**
	* Returns the number of dict item temps where parentItemId = &#63;.
	*
	* @param parentItemId the parent item ID
	* @return the number of matching dict item temps
	*/
	public int countByF_parentItemId(long parentItemId);

	/**
	* Returns all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId_level(
		long groupId, long dictCollectionId, long parentItemId, int level);

	/**
	* Returns a range of all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId_level(
		long groupId, long dictCollectionId, long parentItemId, int level,
		int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId_level(
		long groupId, long dictCollectionId, long parentItemId, int level,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_parentItemId_level(
		long groupId, long dictCollectionId, long parentItemId, int level,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_parentItemId_level_First(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_parentItemId_level_Last(long groupId,
		long dictCollectionId, long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_parentItemId_level_PrevAndNext(
		long dictItemId, long groupId, long dictCollectionId,
		long parentItemId, int level,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	*/
	public void removeByF_parentItemId_level(long groupId,
		long dictCollectionId, long parentItemId, int level);

	/**
	* Returns the number of dict item temps where groupId = &#63; and dictCollectionId = &#63; and parentItemId = &#63; and level = &#63;.
	*
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param level the level
	* @return the number of matching dict item temps
	*/
	public int countByF_parentItemId_level(long groupId, long dictCollectionId,
		long parentItemId, int level);

	/**
	* Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictItemTempException} if it could not be found.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByIC_DCI(String itemCode, long dictCollectionId);

	/**
	* Returns the dict item temp where itemCode = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByIC_DCI(String itemCode, long dictCollectionId,
		boolean retrieveFromCache);

	/**
	* Removes the dict item temp where itemCode = &#63; and dictCollectionId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the dict item temp that was removed
	*/
	public DictItemTemp removeByIC_DCI(String itemCode, long dictCollectionId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the number of dict item temps where itemCode = &#63; and dictCollectionId = &#63;.
	*
	* @param itemCode the item code
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict item temps
	*/
	public int countByIC_DCI(String itemCode, long dictCollectionId);

	/**
	* Returns all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex);

	/**
	* Returns a range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex, int start,
		int end);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_treeIndex_First(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_treeIndex_Last(long dictCollectionId,
		long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_treeIndex_PrevAndNext(long dictItemId,
		long dictCollectionId, long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	*/
	public void removeByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex);

	/**
	* Returns the number of dict item temps where dictCollectionId = &#63; and parentItemId = &#63; and treeIndex LIKE &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param treeIndex the tree index
	* @return the number of matching dict item temps
	*/
	public int countByF_treeIndex(long dictCollectionId, long parentItemId,
		String treeIndex);

	/**
	* Returns all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId);

	/**
	* Returns a range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictCollectionId_parentItemId_First(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictCollectionId_parentItemId_Last(
		long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_dictCollectionId_parentItemId_PrevAndNext(
		long dictItemId, long dictCollectionId, long parentItemId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where dictCollectionId = &#63; and parentItemId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	*/
	public void removeByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId);

	/**
	* Returns the number of dict item temps where dictCollectionId = &#63; and parentItemId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param parentItemId the parent item ID
	* @return the number of matching dict item temps
	*/
	public int countByF_dictCollectionId_parentItemId(long dictCollectionId,
		long parentItemId);

	/**
	* Returns all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId);

	/**
	* Returns a range of all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item temps
	*/
	public java.util.List<DictItemTemp> findByF_dictItemNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the first dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemNewerThan_First(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the last dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp
	* @throws NoSuchDictItemTempException if a matching dict item temp could not be found
	*/
	public DictItemTemp findByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Returns the last dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public DictItemTemp fetchByF_dictItemNewerThan_Last(Date modifiedDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns the dict item temps before and after the current dict item temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item temp
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp[] findByF_dictItemNewerThan_PrevAndNext(
		long dictItemId, Date modifiedDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator)
		throws NoSuchDictItemTempException;

	/**
	* Removes all the dict item temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public void removeByF_dictItemNewerThan(Date modifiedDate, long groupId);

	/**
	* Returns the number of dict item temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict item temps
	*/
	public int countByF_dictItemNewerThan(Date modifiedDate, long groupId);

	/**
	* Caches the dict item temp in the entity cache if it is enabled.
	*
	* @param dictItemTemp the dict item temp
	*/
	public void cacheResult(DictItemTemp dictItemTemp);

	/**
	* Caches the dict item temps in the entity cache if it is enabled.
	*
	* @param dictItemTemps the dict item temps
	*/
	public void cacheResult(java.util.List<DictItemTemp> dictItemTemps);

	/**
	* Creates a new dict item temp with the primary key. Does not add the dict item temp to the database.
	*
	* @param dictItemId the primary key for the new dict item temp
	* @return the new dict item temp
	*/
	public DictItemTemp create(long dictItemId);

	/**
	* Removes the dict item temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item temp
	* @return the dict item temp that was removed
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp remove(long dictItemId)
		throws NoSuchDictItemTempException;

	public DictItemTemp updateImpl(DictItemTemp dictItemTemp);

	/**
	* Returns the dict item temp with the primary key or throws a {@link NoSuchDictItemTempException} if it could not be found.
	*
	* @param dictItemId the primary key of the dict item temp
	* @return the dict item temp
	* @throws NoSuchDictItemTempException if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp findByPrimaryKey(long dictItemId)
		throws NoSuchDictItemTempException;

	/**
	* Returns the dict item temp with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemId the primary key of the dict item temp
	* @return the dict item temp, or <code>null</code> if a dict item temp with the primary key could not be found
	*/
	public DictItemTemp fetchByPrimaryKey(long dictItemId);

	@Override
	public java.util.Map<java.io.Serializable, DictItemTemp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dict item temps.
	*
	* @return the dict item temps
	*/
	public java.util.List<DictItemTemp> findAll();

	/**
	* Returns a range of all the dict item temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of dict item temps
	*/
	public java.util.List<DictItemTemp> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dict item temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item temps
	*/
	public java.util.List<DictItemTemp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator);

	/**
	* Returns an ordered range of all the dict item temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item temps
	*/
	public java.util.List<DictItemTemp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DictItemTemp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dict item temps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dict item temps.
	*
	* @return the number of dict item temps
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}